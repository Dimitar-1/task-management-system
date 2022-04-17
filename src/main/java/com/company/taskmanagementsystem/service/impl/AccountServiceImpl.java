package com.company.taskmanagementsystem.service.impl;

import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.entity.ProjectEntity;
import com.company.taskmanagementsystem.domain.model.AccountModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.repository.AccountRepository;
import com.company.taskmanagementsystem.repository.BoardRepository;
import com.company.taskmanagementsystem.repository.ProjectRepository;
import com.company.taskmanagementsystem.service.AccountService;
import com.company.taskmanagementsystem.service.serviceHelper.AccountServiceHelper;
import com.company.taskmanagementsystem.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @description: This class is responsive for business logic - crud for account
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final AccountServiceHelper helper;
    private final Validator validator;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ProjectRepository projectRepository, BoardRepository boardRepository, ModelMapper modelMapper, AccountServiceHelper helper, Validator validator) {
        this.accountRepository = accountRepository;
        this.projectRepository = projectRepository;
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
        this.helper = helper;
        this.validator = validator;
    }

    /**
     * @args: void
     * @return: List<AccountModel>
     * @description: This method return all accounts
     */
    @Override
    public List<AccountModel> findAll() {
        return accountRepository.findAll().stream()
                .map(accountEntity -> modelMapper.map(accountEntity, AccountModel.class))
                .collect(Collectors.toList());
    }

    /**
     * @args: int id
     * @return: Optional<AccountModel>
     * @description: This method return account by id
     */
    @Override
    public Optional<AccountModel> findById(Integer modelId) throws NotFoundException {
        log.debug("Entering method findById in service layer");
        AccountEntity byId = accountRepository.findById(modelId).orElse(null);
        validator.validateObject(byId, "Account with this id is not found");

        log.info("Account is successfully found by id");
        return Optional.of(modelMapper.map(byId, AccountModel.class));
    }


    /**
     * @args: AccountModel accountModel
     * @return: new account id - int
     * @description: This method create and return account by id or throw business exception if is already exist
     */
    @Override
    @Transactional
    public int save(AccountModel model) throws BusinessException {
        log.debug("Entering method save in service layer");
        validator.validateInputData(model, model.getEmail(), model.getFirstName(), model.getLastName());

        AccountEntity byId = accountRepository.findById(model.getId()).orElse(null);
        AccountEntity byEmail = accountRepository.findByEmail(model.getEmail()).orElse(null);

        validator.validateObjectForDuplicating(byId, byEmail);

        AccountEntity accountEntity = modelMapper.map(model, AccountEntity.class);
        AccountEntity savedEntity = accountRepository.save(accountEntity);

        log.info("Account is successfully created");
        return savedEntity.getId();
    }

    /**
     * @args: Integer id, AccountModel accountModel
     * @return: AccountModel accountModel
     * @description: This method update account by model as input
     */
    @Override
    @Transactional
    public AccountModel update(Integer id, AccountModel accountModel) throws NotFoundException, BusinessException {
        log.debug("Entering method update in service layer");

        AccountEntity oldEntity = accountRepository.findById(id).orElse(null);
        validator.validateObject(oldEntity, "Account with this id is not found. Cannot be updated.");

        helper.validateInputDataForDuplicating(oldEntity, accountModel);
        deleteById(oldEntity.getId());

        AccountEntity newMappedAccountEntity = modelMapper.map(accountModel, AccountEntity.class);
        newMappedAccountEntity.setUpdatedAt(LocalDateTime.now());

        AccountEntity newAccount = accountRepository.save(newMappedAccountEntity);
        log.info("Account is successfully updated");
        return modelMapper.map(newAccount, AccountModel.class);
    }

    /**
     * @args: Integer
     * @return: void
     * @description: This method delete account if exist by id
     */
    @Override
    @Transactional
    public void deleteById(Integer id) throws NotFoundException {
        AccountEntity accountEntityById = accountRepository.findById(id).orElse(null);
        validator.validateObject(accountEntityById, "Account with this id is not found. Cannot be deleted.");

        ProjectEntity projectEntity = projectRepository.findByAccount(accountEntityById).orElse(null);
        if (projectEntity != null) {
            int projectEntityOwnerId = projectEntity.getAccount().getId();
            if (projectEntityOwnerId == accountEntityById.getId()) {
                projectEntity.setAccount(null);
            }
        } else {
            log.info("Cannot find project by account. For account: " + accountEntityById);
        }

//        // FIXME: 4/10/2022 - by delete remove many to many association
//        List<BoardEntity> boardEntitiesByAccounts = boardRepository.findAllByAccounts(accountEntityById.getId());
//        for (BoardEntity boardEntitiesByAccount : boardEntitiesByAccounts) {
//            if (boardEntitiesByAccount != null) {
//                boardEntitiesByAccount.setAccounts(null);
//            }
//        }

        accountRepository.deleteById(accountEntityById.getId());
        log.info("Account is successfully deleted");
    }

}
