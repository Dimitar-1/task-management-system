package com.company.taskmanagementsystem.domain.entity;

import com.company.taskmanagementsystem.constant.TaskPriority;
import com.company.taskmanagementsystem.constant.TaskStatus;
import com.company.taskmanagementsystem.constant.TaskType;
import lombok.*;

import javax.persistence.*;

@Entity(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TaskEntity extends AbstractBaseEntity {

    @Column(nullable = false)
    private String title;

    @ManyToOne(targetEntity = ProjectEntity.class, cascade = CascadeType.ALL)
    private ProjectEntity project;

    @ManyToOne(targetEntity = BoardEntity.class, cascade = CascadeType.ALL)
    private BoardEntity board;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, columnDefinition = "varchar(50)")
    private TaskType type;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, columnDefinition = "varchar(50) default 'NEW'")
    private TaskStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(targetEntity = AccountEntity.class, cascade = CascadeType.MERGE)
    private AccountEntity reportedBy;

    @ManyToOne(targetEntity = AccountEntity.class, cascade = CascadeType.MERGE)
    private AccountEntity assignedTo;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, columnDefinition = "varchar(50) default 'LOW'")
    private TaskPriority priority;

    @Column(name = "story_points")
    private int storyPoints;
}
