package com.company.taskmanagementsystem.repository;

import com.company.taskmanagementsystem.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    Optional<BoardEntity> findByName(String name);

}
