package com.company.taskmanagementsystem.repository;

import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    Optional<ProjectEntity> findByProjectKey(String projectKey);

    Optional<ProjectEntity> findByAccount(AccountEntity account);
}
