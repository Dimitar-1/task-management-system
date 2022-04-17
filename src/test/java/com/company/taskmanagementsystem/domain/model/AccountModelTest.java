//package com.company.taskmanagementsystem.domain.model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountModelTest {
//
//    AccountModel accountModel;
//
//    final String email = "mail@gmail.com";
//    final String firstName = "Joe";
//    final String lastName = "Doe";
//
//    @BeforeEach
//    void setUp() {
//        accountModel = new AccountModel();
//        accountModel.setId(1);
//        accountModel.setEmail(email);
//        accountModel.setFirstName(firstName);
//        accountModel.setLastName(lastName);
//        accountModel.setCreatedAt(LocalDateTime.now());
//        accountModel.setCreatedAt(LocalDateTime.now());
//    }
//
//    @Test
//    void testAccountWithCorrectData() {
//        assertNotNull(accountModel);
//    }
//
//    @Test
//    void testAccountWithIncorrectData() {
//        AccountModel accountModel = new AccountModel();
//        assertNull(accountModel.getFirstName());
//    }
//
//    @Test
//    void getAccountId() {
//        assertEquals(1, accountModel.getId());
//    }
//
//    @Test
//    void getFirstName() {
//        assertEquals(firstName, accountModel.getFirstName());
//    }
//
//    @Test
//    void getLastName() {
//        assertEquals(lastName, accountModel.getLastName());
//    }
//
//    @Test
//    void getEmail() {
//        assertEquals(email, accountModel.getEmail());
//    }
//
//    @Test
//    void getCreatedAt() {
//        assertNotNull(accountModel.getCreatedAt());
//    }
//
//    @Test
//    void getUpdatedAt() {
//        assertNotNull(accountModel.getUpdatedAt());
//    }
//
//    @Test
//    void setAccountId() {
//        int newId = 5;
//        accountModel.setId(newId);
//        assertEquals(newId, accountModel.getId());
//    }
//
//    @Test
//    void setFirstName() {
//        String newFirstName = "new first name";
//        accountModel.setFirstName(newFirstName);
//        assertEquals(newFirstName, accountModel.getFirstName());
//    }
//
//    @Test
//    void setLastName() {
//        String newLastName = "new last name";
//        accountModel.setLastName(newLastName);
//        assertEquals(newLastName, accountModel.getLastName());
//    }
//
//    @Test
//    void setEmail() {
//        String newEmail = "new email";
//        accountModel.setEmail(newEmail);
//        assertEquals(newEmail, accountModel.getEmail());
//    }
//
//    @Test
//    void setCreatedAt() {
//        LocalDateTime newCreatedAt = LocalDateTime.now();
//        accountModel.setCreatedAt(newCreatedAt);
//        assertEquals(newCreatedAt, accountModel.getCreatedAt());
//    }
//
//    @Test
//    void setUpdatedAt() {
//        LocalDateTime newUpdatedAt = LocalDateTime.now();
//        accountModel.setUpdatedAt(newUpdatedAt);
//        assertEquals(newUpdatedAt, accountModel.getCreatedAt());
//    }
//}