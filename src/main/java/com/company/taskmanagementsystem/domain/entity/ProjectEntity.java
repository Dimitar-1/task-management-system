package com.company.taskmanagementsystem.domain.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity(name = "project")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectEntity extends AbstractBaseEntity {

    @Column(name = "projectKey", length = 50, unique = true)
    private String projectKey;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToOne(targetEntity = AccountEntity.class, cascade = CascadeType.ALL)
    private AccountEntity account;

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "projectKey='" + projectKey + '\'' +
                ", title='" + title + '\'' +
                ", account=" + account +
                '}';
    }
}
