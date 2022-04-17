//package com.company.taskmanagementsystem.service.impl;
//
//import com.company.taskmanagementsystem.domain.entity.AccountEntity;
//import com.company.taskmanagementsystem.domain.model.AccountModel;
//import com.company.taskmanagementsystem.exception.BusinessException;
//import com.company.taskmanagementsystem.exception.NotFoundException;
//import com.company.taskmanagementsystem.repository.AccountRepository;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.crossstore.ChangeSetPersister;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class AccountServiceImplTest {
//
//    @Autowired
//    AccountServiceImpl accountService;
//    @Mock
//    AccountRepository accountRepository;
//    @Mock
//    ModelMapper modelMapper;
//
//    AccountEntity accountEntity;
//    AccountModel accountModel;
//
//    final String email = "mail@gmail.com";
//    final String firstName = "Joe";
//    final String lastName = "Doe";
//
//    @BeforeAll
//    void setUp() throws BusinessException {
//        accountModel = new AccountModel();
//        accountModel.setId(1);
//        accountModel.setEmail(email);
//        accountModel.setFirstName(firstName);
//        accountModel.setLastName(lastName);
//        accountModel.setCreatedAt(LocalDateTime.now());
//        accountModel.setCreatedAt(LocalDateTime.now());
//        when(modelMapper.map(any(), any())).thenReturn(accountModel);
//
//        int save = accountService.save(accountModel);
//        System.out.println(save);
//    }
//
//    @AfterAll
//    void tearDown() {
//        accountService.deleteById(1);
//    }
//
//    @Test
//    void findAll() {
//        List<AccountEntity> accountEntityList = Arrays.asList(accountEntity);
//        when(accountRepository.findAll()).thenReturn(accountEntityList);
//
//        List<AccountModel> all = accountService.findAll();
//
//        assertEquals(1, all.size());
//        verify(accountRepository, times(1)).findAll();
//    }
//
//    @Test
//    void findById() {
//        Optional<AccountEntity> accountEntityOptional = Optional.of(this.accountEntity);
//
//        when(accountRepository.findById(anyInt())).thenReturn(accountEntityOptional);
//
//        Optional<AccountModel> byId = accountService.findById(1);
//
//        assertEquals(1, byId.get().getId());
//        verify(accountRepository, times(1)).findById(1);
//    }
//
//    @Test
//    void save() throws BusinessException {
//        when(accountRepository.save(any())).thenReturn(accountEntity);
//
//        int save = accountService.save(modelMapper.map(accountEntity, AccountModel.class));
//
//        assertEquals(1, save);
//        verify(accountRepository, times(1)).save(any(AccountEntity.class));
//    }
//
//    @Test
//    void update() throws BusinessException {
//        Optional<AccountModel> updated = accountService.findById(1);
//        updated.get().setEmail("example@gmail.com");
//        accountService.update(1, updated.get());
//        assertEquals("example@gmail.com", updated.get().getEmail());
//    }
//
//    @Test
//    void delete() {
//    }
//}