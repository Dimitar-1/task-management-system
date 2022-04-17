package com.company.taskmanagementsystem.web.controller;

import com.company.taskmanagementsystem.domain.model.AccountModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: This controller is accountable for providing crud operations.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountModel>> getAllAccounts() {
        List<AccountModel> all = accountService.findAll();

        log.info("Return all accounts");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountModel> getAccountById(@PathVariable(name = "accountId") int id) throws NotFoundException {
        AccountModel byId = accountService.findById(id).get();

        log.info("Return account with id: " + id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Integer> createAccount(@RequestBody AccountModel accountModel) throws BusinessException {
        int save = accountService.save(accountModel);

        log.info("Create new account: " + accountModel.toString());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<AccountModel> updateAccountById(@PathVariable(name = "accountId") int accountId, @RequestBody AccountModel accountModel) throws NotFoundException, BusinessException {
        AccountModel updated = accountService.update(accountId, accountModel);

        log.info("Update account with id: " + accountId + " New account vision: " + updated.toString());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<String> deleteAccountBy(@PathVariable(name = "accountId") Integer accountId) throws NotFoundException {
        accountService.deleteById(accountId);

        log.info("Deleted account was with id: " + accountId);
        return new ResponseEntity<>("Account was successfully deleted.", HttpStatus.NO_CONTENT);
    }
}
