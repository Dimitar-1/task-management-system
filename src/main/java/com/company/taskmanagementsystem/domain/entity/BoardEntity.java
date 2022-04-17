package com.company.taskmanagementsystem.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "board")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BoardEntity extends AbstractBaseEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "board_account",
            joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id")
    )
    private Set<AccountEntity> accounts;

}