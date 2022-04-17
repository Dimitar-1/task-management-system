package com.company.taskmanagementsystem.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractBaseModel {

    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AbstractBaseModel() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
