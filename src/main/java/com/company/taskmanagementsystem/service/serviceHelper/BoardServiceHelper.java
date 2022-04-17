package com.company.taskmanagementsystem.service.serviceHelper;

import com.company.taskmanagementsystem.constant.ErrorType;
import com.company.taskmanagementsystem.domain.entity.BoardEntity;
import com.company.taskmanagementsystem.domain.model.BoardModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.ErrorModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardServiceHelper {

    /**
     * @args: BoardEntity entity, BoardModel model
     * @return: void
     * @description: This method check for duplicating,
     *              if no one field is changed throw BusinessException with code and message
     */
    public void validateInputDataForDuplicating(BoardEntity entity, BoardModel model) throws BusinessException {
        if (entity.getName().equals(model.getName())) {
            List<ErrorModel> errorModelList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.BAD_REQUEST.toString());
            errorModel.setMessage("Identical data. Nothing to update.");
            errorModel.setTime(LocalDateTime.now());

            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
    }


    /**
     * @args: BoardEntity entity, BoardModel model
     * @return: void
     * @description: This method check set new data to the entity from model
     */
    public void setInputData(BoardEntity entity, BoardModel model) {
        final String newName = model.getName();
        if (newName != null && !newName.isEmpty()) {
            entity.setName(newName);
        }
    }
}
