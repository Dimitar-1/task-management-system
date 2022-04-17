package com.company.taskmanagementsystem.service;

import com.company.taskmanagementsystem.domain.model.AccountModel;
import com.company.taskmanagementsystem.domain.model.TaskModel;

import java.util.List;

public interface TaskService extends AbstractBaseService<TaskModel, Integer> {

    List<TaskModel> findAllByType(String taskType);

    List<TaskModel> findAllByTitle(String title);

    List<TaskModel> findAllByAssignedTo(AccountModel assignedTo);

}
