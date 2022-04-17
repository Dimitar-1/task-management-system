package com.company.taskmanagementsystem.exception;

import com.company.taskmanagementsystem.constant.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException be) {
        ResponseEntity<List<ErrorModel>> responseEntity = new ResponseEntity<>(be.getErrorList(), HttpStatus.BAD_REQUEST);

        log.debug("Business Exception in handleBusinessException");
        be.getErrorList().forEach(em -> log.debug("Code: " + em.getCode() + " *** Message: " + em.getMessage()));

        return responseEntity;
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<List<ErrorModel>> notFoundException(NotFoundException nfe) {
        ResponseEntity<List<ErrorModel>> responseEntity = new ResponseEntity<>(nfe.getErrorModelList(), HttpStatus.NOT_FOUND);

        log.debug("Business Exception in handleBusinessException");
        nfe.getErrorModelList().forEach(em -> log.debug("Code: " + em.getCode() + " *** Message: " + em.getMessage()));

        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<ErrorModel>> handleAllException(Exception e) {
        List<ErrorModel> errorModels = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(ErrorType.UNKNOWN_SERVER_ERROR.toString());
        errorModel.setMessage("Unknown server error");
        errorModel.setTime(LocalDateTime.now());
        log.debug("Inside handleAllException - With code: " + errorModel.getCode() + " *** Message: " + errorModel.getMessage());

        errorModels.add(errorModel);
        log.error("Error Inside handleAllException, cause: " + e.getMessage());
        return new ResponseEntity<>(errorModels, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
