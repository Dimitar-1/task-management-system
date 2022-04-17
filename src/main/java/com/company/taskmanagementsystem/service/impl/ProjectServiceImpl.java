package com.company.taskmanagementsystem.service.impl;

import com.company.taskmanagementsystem.domain.entity.ProjectEntity;
import com.company.taskmanagementsystem.domain.model.ProjectModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.repository.BoardRepository;
import com.company.taskmanagementsystem.repository.ProjectRepository;
import com.company.taskmanagementsystem.repository.TaskRepository;
import com.company.taskmanagementsystem.service.ProjectService;
import com.company.taskmanagementsystem.service.serviceHelper.ProjectServiceHelper;
import com.company.taskmanagementsystem.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description: This class is responsive for business logic - crud for project
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final ProjectServiceHelper helper;
    private final Validator validator;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository, BoardRepository boardRepository, ModelMapper modelMapper, ProjectServiceHelper helper, Validator validator) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
        this.helper = helper;
        this.validator = validator;
    }

    /**
     * @args: void
     * @return: List<ProjectModel>
     * @description: This method return all projects
     */
    @Override
    public List<ProjectModel> findAll() {
        return projectRepository.findAll().stream()
                .map(projectEntity -> modelMapper.map(projectEntity, ProjectModel.class))
                .collect(Collectors.toList());
    }

    /**
     * @args: int id
     * @return: Optional<ProjectModel>
     * @description: This method return project by id
     */
    @Override
    public Optional<ProjectModel> findById(Integer modelId) throws NotFoundException {
        log.debug("Entering method findById in service layer");

        ProjectEntity byId = projectRepository.findById(modelId).orElse(null);
        validator.validateObject(byId, "project with this id is not found.");

        log.info("Project is successfully found by id");
        return Optional.of(modelMapper.map(byId, ProjectModel.class));
    }

    /**
     * @args: ProjectModel model
     * @return: new project id - int
     * @description: This method create and return project by id or throw business exception if is already exist
     */
    @Override
    @Transactional
    public int save(ProjectModel model) throws BusinessException {
        log.debug("Entering method save in service layer");
        validator.validateInputData(model, model.getProjectKey(), model.getTitle());

        ProjectEntity byId = projectRepository.findById(model.getId()).orElse(null);
        ProjectEntity byProjectKey = projectRepository.findByProjectKey(model.getProjectKey()).orElse(null);

        validator.validateObjectForDuplicating(byId, byProjectKey);

        ProjectEntity projectEntity = modelMapper.map(model, ProjectEntity.class);
        ProjectEntity saved = projectRepository.save(projectEntity);

        log.info("Project is successfully created");
        return saved.getId();
    }

    @Override
    @Transactional
    public ProjectModel update(Integer old, ProjectModel model) throws NotFoundException, BusinessException {
        log.debug("Entering method update in service layer");

        ProjectEntity projectEntityById = projectRepository.findById(old).orElse(null);
        validator.validateObject(projectEntityById, "Project with this id is not found. Cannot be updated.");

        helper.validateInputDataForDuplicating(projectEntityById, model);
        helper.setInputData(projectEntityById, model);

        projectEntityById.setUpdatedAt(LocalDateTime.now());

        ProjectEntity newProject = projectRepository.save(projectEntityById);
        log.info("Project is successfully updated");
        return modelMapper.map(newProject, ProjectModel.class);
    }

    /**
     * @args: Integer
     * @return: void
     * @description: This method update delete project if exist by id
     */
    @Override
    @Transactional
    public void deleteById(Integer modelId) throws NotFoundException {
        ProjectEntity byId = projectRepository.findById(modelId).orElse(null);
        validator.validateObject(byId, "Project with this id is not found. Cannot be deleted.");

        taskRepository.deleteAllByProject(byId);
        byId.setAccount(null);
        projectRepository.deleteById(byId.getId());

        log.info("Project is successfully deleted");
    }
}
