package com.company.taskmanagementsystem.service.serviceHelper;

import com.company.taskmanagementsystem.constant.ErrorType;
import com.company.taskmanagementsystem.domain.entity.ProjectEntity;
import com.company.taskmanagementsystem.domain.model.ProjectModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.ErrorModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectServiceHelper {

    /**
     * @args: ProjectEntity entity, ProjectModel model
     * @return: void
     * @description: This method check for duplicating,
     *              if no one field is changed throw BusinessException with code and message
     */
    public void validateInputDataForDuplicating(ProjectEntity entity, ProjectModel model) throws BusinessException {
        if (entity.getProjectKey().equals(model.getProjectKey())
                && entity.getTitle().equals(model.getTitle())) {
            throwException("Identical data. Nothing to update.");
        }
    }


    /**
     * @args: ProjectEntity entity, ProjectModel model
     * @return: void
     * @description: This method check set new data to the entity from model
     */
    public void setInputData(ProjectEntity entity, ProjectModel model) throws BusinessException {
        boolean isSetNewProperties = false;
        final String newProjectKey = model.getProjectKey();
        if (newProjectKey != null && !newProjectKey.isEmpty()) {
            entity.setProjectKey(newProjectKey);
            isSetNewProperties = true;
        }
        final String newTitle = model.getTitle();
        if (newTitle != null && !newTitle.isEmpty()) {
            entity.setTitle(newTitle);
            isSetNewProperties = true;
        }
        if (!isSetNewProperties) {
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
