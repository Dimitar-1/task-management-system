package com.company.taskmanagementsystem.service.serviceHelper;

import com.company.taskmanagementsystem.constant.ErrorType;
import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.model.AccountModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.ErrorModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountServiceHelper {

    /**
     * @args: AccountEntity accountEntity, AccountModel accountModel
     * @return: void
     * @description: This method check for duplicating,
     * if no one field is changed throw BusinessException with code and message
     */
    public void validateInputDataForDuplicating(AccountEntity entity, AccountModel accountModel) throws BusinessException {
        if (entity.getEmail().equals(accountModel.getEmail())
                && entity.getFirstName().equals(accountModel.getFirstName())
                && entity.getLastName().equals(accountModel.getLastName())) {
            throwException("Identical data. Nothing to update.");
        }
    }

    /**
     * @args: AccountEntity accountEntity, AccountModel accountModel
     * @return: void
     * @description: This method check set new data to the entity from model
     */
    public void setInputData(AccountEntity accountEntity, AccountModel accountModel) throws BusinessException {
        boolean isSetNewProperties = false;

        final String modelNewEmail = accountModel.getEmail();
        if (modelNewEmail != null && !modelNewEmail.isEmpty()) {
            accountEntity.setEmail(modelNewEmail);
            isSetNewProperties = true;
        }
        final String modelNewFirstName = accountModel.getFirstName();
        if (modelNewFirstName != null && !modelNewFirstName.isEmpty()) {
            accountEntity.setFirstName(modelNewFirstName);
            isSetNewProperties = true;
        }
        final String modelNewLastName = accountModel.getLastName();
        if (modelNewLastName != null && !modelNewLastName.isEmpty()) {
            accountEntity.setLastName(modelNewLastName);
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
