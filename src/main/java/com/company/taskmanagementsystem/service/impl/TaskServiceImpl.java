package com.company.taskmanagementsystem.service.impl;

import com.company.taskmanagementsystem.constant.TaskType;
import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.entity.TaskEntity;
import com.company.taskmanagementsystem.domain.model.AccountModel;
import com.company.taskmanagementsystem.domain.model.TaskModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.repository.TaskRepository;
import com.company.taskmanagementsystem.service.TaskService;
import com.company.taskmanagementsystem.service.serviceHelper.TaskServiceHelper;
import com.company.taskmanagementsystem.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: This class is responsive for business logic - crud for project
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final TaskServiceHelper helper;
    private final Validator validator;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, TaskServiceHelper helper, Validator validator) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.helper = helper;
        this.validator = validator;
    }


    /**
     * @args: void
     * @return: List<TaskModel>
     * @description: This method return all tasks
     */
    @Override
    public List<TaskModel> findAll() {
        return taskRepository.findAll().stream()
                .map(taskEntity -> modelMapper.map(taskEntity, TaskModel.class))
                .collect(Collectors.toList());
    }

    /**
     * @args: int id
     * @return: Optional<TaskModel>
     * @description: This method return task by id
     */
    @Override
    public Optional<TaskModel> findById(Integer modelId) throws NotFoundException {
        log.debug("Entering method findById in service layer");

        TaskEntity byId = taskRepository.findById(modelId).orElse(null);
        validator.validateObject(byId, "task with this id is not found.");

        log.info("Task is successfully found by id");
        return Optional.of(modelMapper.map(byId, TaskModel.class));
    }

    /**
     * @args: TaskModel model
     * @return: int
     * @description: This method create and return project by id or throw business exception if is already exist
     */
    @Override
    @Transactional
    public int save(TaskModel model) throws BusinessException {
        log.debug("Entering method save in service layer");

        TaskEntity byId = taskRepository.findById(model.getId()).orElse(null);
        validator.validateObjectForDuplicating(byId);

        TaskEntity taskEntity = modelMapper.map(model, TaskEntity.class);
        TaskEntity savedEntity = taskRepository.save(taskEntity);

        log.info("Task is successfully created");
        return savedEntity.getId();
    }

    /**
     * @args: Integer old, TaskModel model
     * @return: TaskModel taskModel
     * @description: This method update task by model as input
     */
    @Override
    @Transactional
    public TaskModel update(Integer old, TaskModel model) throws NotFoundException, BusinessException {
        log.debug("Entering method update in service layer");

        TaskEntity oldEntity = taskRepository.findById(old).orElse(null);
        validator.validateObject(oldEntity, "Task with this id is not found. Cannot be updated.");

        helper.validateInputDataForDuplicating(oldEntity, model);
        helper.setInputData(oldEntity, model);

        oldEntity.setUpdatedAt(LocalDateTime.now());

        TaskEntity newTask = taskRepository.save(oldEntity);
        log.info("Task is successfully updated");
        return modelMapper.map(newTask, TaskModel.class);
    }

    /**
     * @args: Integer
     * @return: void
     * @description: This method delete task if exist by id
     */
    @Override
    @Transactional
    public void deleteById(Integer modelId) throws NotFoundException {
        TaskEntity byId = taskRepository.findById(modelId).orElse(null);
        validator.validateObject(byId, "Task with this id is not found. Cannot be deleted.");

//        taskRepository.deleteAllByProject(byId.get());
        taskRepository.deleteById(byId.getId());

        log.info("Task is successfully deleted");
    }

    /**
     * @args: String type
     * @return: List<TaskModel>
     * @description: This method return all tasks by type
     */
    @Override
    public List<TaskModel> findAllByType(String taskType) {
        Optional<String> optionalType = Arrays.stream(TaskType.values())
                .map(String::valueOf)
                .filter(s -> s.equalsIgnoreCase(taskType))
                .findFirst();
        if (optionalType.isPresent()) {
            TaskType type = TaskType.valueOf(optionalType.get().toUpperCase().trim());
            List<TaskModel> collectionOfTasks = taskRepository.findAllByType(type)
                    .stream()
                    .map(taskEntity -> modelMapper.map(taskEntity, TaskModel.class))
                    .collect(Collectors.toList());
            if (!collectionOfTasks.isEmpty()) {
                log.info("Successfully founded tasks by type");
                return collectionOfTasks;
            }
        }
        log.warn("Not founded by type, return all tasks instead");
        return findAll();
    }

    /**
     * @args: String title
     * @return: List<TaskModel>
     * @description: This method return all tasks by title
     */
    @Override
    public List<TaskModel> findAllByTitle(String title) {
        List<TaskModel> collect = taskRepository.findAllByTitle(title)
                .stream()
                .map(taskEntity -> modelMapper.map(taskEntity, TaskModel.class))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            log.warn("Not founded by title, return all tasks instead");
            return findAll();
        }
        log.info("Successfully founded tasks by title");
        return collect;
    }

    /**
     * @args: AccountEntity assignedTo
     * @return: List<TaskModel>
     * @description: This method return all tasks by assigned-to
     */
    @Override
    public List<TaskModel> findAllByAssignedTo(AccountModel assignedTo) {
        List<TaskModel> collect = taskRepository.findAllByAssignedTo(modelMapper.map(assignedTo, AccountEntity.class))
                .stream()
                .map(taskEntity -> modelMapper.map(taskEntity, TaskModel.class))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            log.warn("Not founded by assignedTo, return all tasks instead");
            return findAll();
        }
        log.info("Successfully founded tasks by findAllByAssignedTo");
        return collect;
    }
}
