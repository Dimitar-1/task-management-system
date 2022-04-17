package com.company.taskmanagementsystem.service.impl;

import com.company.taskmanagementsystem.domain.entity.BoardEntity;
import com.company.taskmanagementsystem.domain.model.BoardModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.repository.AccountRepository;
import com.company.taskmanagementsystem.repository.BoardRepository;
import com.company.taskmanagementsystem.repository.TaskRepository;
import com.company.taskmanagementsystem.service.BoardService;
import com.company.taskmanagementsystem.service.serviceHelper.BoardServiceHelper;
import com.company.taskmanagementsystem.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: This class is responsive for business logic - crud for project
 */
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final BoardServiceHelper helper;
    private final Validator validator;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, TaskRepository taskRepository, AccountRepository accountRepository, ModelMapper modelMapper, BoardServiceHelper helper, Validator validator) {
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.helper = helper;
        this.validator = validator;
    }


    /**
     * @args: void
     * @return: List<BoardModel>
     * @description: This method return all boards
     */
    @Override
    public List<BoardModel> findAll() {
        return boardRepository.findAll().stream()
                .map(boardEntity -> modelMapper.map(boardEntity, BoardModel.class))
                .collect(Collectors.toList());
    }

    /**
     * @args: int id
     * @return: Optional<BoardModel>
     * @description: This method return board by id
     */
    @Override
    public Optional<BoardModel> findById(Integer modelId) throws NotFoundException {
        log.debug("Entering method findById in service layer");

        BoardEntity byId = boardRepository.findById(modelId).orElse(null);
        validator.validateObject(byId, "board with this id is not found.");

        log.info("Board is successfully found by id");
        return Optional.of(modelMapper.map(byId, BoardModel.class));
    }

    /**
     * @args: BoardModel model
     * @return: new project id - int
     * @description: This method create and return project by id or throw business exception if is already exist
     */
    @Override
    @Transactional
    public int save(BoardModel model) throws BusinessException {
        log.debug("Entering method save in service layer");
        validator.validateInputData(model, model.getName());

        BoardEntity byId = boardRepository.findById(model.getId()).orElse(null);
        BoardEntity byName = boardRepository.findByName(model.getName()).orElse(null);

        validator.validateObjectForDuplicating(byId, byName);

        BoardEntity boardEntity = modelMapper.map(model, BoardEntity.class);
        BoardEntity saved = boardRepository.save(boardEntity);

        log.info("Board is successfully created");
        return saved.getId();
    }

    /**
     * @args: Integer
     * @return: void
     * @description: This method update project if exist by id
     */
    @Override
    @Transactional
    public BoardModel update(Integer old, BoardModel model) throws NotFoundException, BusinessException {
        log.debug("Entering method update in service layer");

        BoardEntity optionalBoardEntity = boardRepository.findById(old).orElse(null);
        validator.validateObject(optionalBoardEntity, "Board with this id is not found. Cannot be updated.");

        helper.validateInputDataForDuplicating(optionalBoardEntity, model);
        helper.setInputData(optionalBoardEntity, model);

        optionalBoardEntity.setUpdatedAt(LocalDateTime.now());

        BoardEntity newBoard = boardRepository.save(optionalBoardEntity);
        log.info("Project is successfully updated");
        return modelMapper.map(newBoard, BoardModel.class);
    }

    /**
     * @args: Integer
     * @return: void
     * @description: This method delete project if exist by id
     */
    @Override
    @Transactional
    public void deleteById(Integer modelId) throws NotFoundException {
        BoardEntity boardById = boardRepository.findById(modelId).orElse(null);
        validator.validateObject(boardById, "Board with this id is not found. Cannot be deleted.");

        Objects.requireNonNull(boardById).setAccounts(null);
        taskRepository.findAllByBoard(boardById).forEach(taskEntity -> taskEntity.setBoard(null));
        boardRepository.deleteById(boardById.getId());

        log.info("Board is successfully deleted");
    }
}
