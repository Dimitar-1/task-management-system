package com.company.taskmanagementsystem.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorModel {

    private String code;
    private String message;
    private LocalDateTime time;
}
