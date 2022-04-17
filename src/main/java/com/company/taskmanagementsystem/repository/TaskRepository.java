package com.company.taskmanagementsystem.repository;

import com.company.taskmanagementsystem.constant.TaskType;
import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.entity.BoardEntity;
import com.company.taskmanagementsystem.domain.entity.ProjectEntity;
import com.company.taskmanagementsystem.domain.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findAllByProject(ProjectEntity project);

    void deleteAllByProject(ProjectEntity project);

    List<TaskEntity> findAllByBoard(BoardEntity board);

    List<TaskEntity> findAllByTitle(String title);

    List<TaskEntity> findAllByType(TaskType type);

    List<TaskEntity> findAllByAssignedTo(AccountEntity assignedTo);
}
