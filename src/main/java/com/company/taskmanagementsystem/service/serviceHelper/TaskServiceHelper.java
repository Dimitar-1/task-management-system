package com.company.taskmanagementsystem.service.serviceHelper;

import com.company.taskmanagementsystem.constant.ErrorType;
import com.company.taskmanagementsystem.constant.TaskPriority;
import com.company.taskmanagementsystem.constant.TaskStatus;
import com.company.taskmanagementsystem.constant.TaskType;
import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.entity.BoardEntity;
import com.company.taskmanagementsystem.domain.entity.ProjectEntity;
import com.company.taskmanagementsystem.domain.entity.TaskEntity;
import com.company.taskmanagementsystem.domain.model.ProjectModel;
import com.company.taskmanagementsystem.domain.model.TaskModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.ErrorModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskServiceHelper {

    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * @args: TaskEntity entity, TaskModel model
     * @return: void
     * @description: This method check for duplicating,
     * if no one field is changed throw BusinessException with code and message
     */
    public void validateInputDataForDuplicating(TaskEntity entity, TaskModel model) throws BusinessException {
        boolean firstGroupFieldsCheck = entity.getTitle().equals(model.getTitle())
                && entity.getProject().getId() == model.getProject().getId()
                && entity.getBoard().getId() == model.getBoard().getId();

        boolean secondGroupFieldsCheck = entity.getType().equals(model.getType())
                && entity.getStatus().equals(model.getStatus())
                && entity.getReportedBy().getId() == model.getReportedBy().getId();

        boolean thirdGroupFieldsCheck = entity.getDescription().equals(model.getDescription())
                && entity.getPriority().equals(model.getPriority())
                && entity.getStoryPoints() == model.getStoryPoints();

        if (firstGroupFieldsCheck && secondGroupFieldsCheck && thirdGroupFieldsCheck) {
            throwException("Identical data. Nothing to update.");
        }
    }

    /**
     * @args: TaskEntity entity, TaskModel model
     * @return: void
     * @description: This method check set new data to the entity from model
     */
    public void setInputData(TaskEntity entity, TaskModel model) throws BusinessException {
        boolean isSetNewProperties = false;

        final String newTitle = model.getTitle();
        if (newTitle != null && !newTitle.isEmpty()) {
            entity.setTitle(newTitle);
            isSetNewProperties = true;
        }
        ProjectEntity newProject = modelMapper.map(model.getProject().getId(), ProjectEntity.class);
        if (newProject != null) {
            entity.setProject(newProject);
            isSetNewProperties = true;
        }
        BoardEntity newBoard = modelMapper.map(model.getBoard().getId(), BoardEntity.class);
        if (newBoard != null) {
            entity.setBoard(newBoard);
            isSetNewProperties = true;
        }
        TaskType newType = model.getType();
        if (newType != null) {
            entity.setType(newType);
            isSetNewProperties = true;
        }
        TaskStatus newStatus = model.getStatus();
        if (newStatus != null) {
            entity.setStatus(newStatus);
            isSetNewProperties = true;
        } else {
            entity.setStatus(TaskStatus.NEW);
        }
        AccountEntity newReportedBy = model.getReportedBy();
        if (newReportedBy != null) {
            entity.setReportedBy(newReportedBy);
            isSetNewProperties = true;
        }
        String newDescription = model.getDescription();
        if (newDescription != null) {
            entity.setDescription(newDescription);
            isSetNewProperties = true;
        }
        TaskPriority newPriority = model.getPriority();
        if (newPriority != null) {
            entity.setPriority(newPriority);
            isSetNewProperties = true;
        } else {
            entity.setPriority(TaskPriority.LOW);
        }
        int newStoryPoints = model.getStoryPoints();
        if (newStoryPoints != 0) {
            entity.setStoryPoints(newStoryPoints);
            isSetNewProperties = true;
        }

        if (isSetNewProperties) {
            throwException("Empty data. Nothing to update.");
        }
    }

    private void throwException(String message) throws BusinessException {
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(ErrorType.BAD_REQUEST.toString());
        errorModel.setMessage(message);
        errorModel.setTime(LocalDateTime.now());

        errorModelList.add(errorModel);
        throw new BusinessException(errorModelList);
    }

}
