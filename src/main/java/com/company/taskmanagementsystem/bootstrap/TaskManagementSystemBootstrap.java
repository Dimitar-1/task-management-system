package com.company.taskmanagementsystem.bootstrap;

import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.entity.BoardEntity;
import com.company.taskmanagementsystem.domain.entity.ProjectEntity;
import com.company.taskmanagementsystem.repository.AccountRepository;
import com.company.taskmanagementsystem.repository.BoardRepository;
import com.company.taskmanagementsystem.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class TaskManagementSystemBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TaskManagementSystemBootstrap(ProjectRepository projectRepository, BoardRepository boardRepository, AccountRepository accountRepository) {
        this.projectRepository = projectRepository;
        this.boardRepository = boardRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        taskRepository.save(getTask());
//        accountRepository.save(getAccount());
//        projectRepository.save(getProject());
//        boardRepository.save(getBoard());
    }

    private AccountEntity getAccount() {
        AccountEntity build = AccountEntity.builder()
                .email("account@email.com")
                .firstName("Account first name")
                .lastName("Account last name")
                .build();
        build.setId(1);
        return build;
    }

    private ProjectEntity getProject() {
        AccountEntity owner = getAccount();
        ProjectEntity build = ProjectEntity.builder()
                .projectKey("project key")
                .title("project name")
                .account(owner)
                .build();
        build.setId(1);
        return build;
    }

    private BoardEntity getBoard() {
        AccountEntity accountOne = getAccount();
        AccountEntity accountTwo = getAccount();
        accountTwo.setId(2);
        accountTwo.setEmail("Second email");

        Set<AccountEntity> accountEntitySet = Set.of(accountOne, accountTwo);

        BoardEntity build = BoardEntity.builder()
                .name("Board name")
                .accounts(accountEntitySet)
                .build();
        build.setId(1);
        return build;
    }

}
