package com.company.taskmanagementsystem.domain.model;

import com.company.taskmanagementsystem.constant.TaskPriority;
import com.company.taskmanagementsystem.constant.TaskStatus;
import com.company.taskmanagementsystem.constant.TaskType;
import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskModel extends AbstractBaseModel {

    private String title;
    private ProjectModel project;
    private BoardModel board;

    private TaskType type;
    private TaskStatus status = TaskStatus.NEW;

    private String description;

    private AccountEntity reportedBy;
    private AccountEntity assignedTo;

    private TaskPriority priority = TaskPriority.LOW;
    private int storyPoints;
}
