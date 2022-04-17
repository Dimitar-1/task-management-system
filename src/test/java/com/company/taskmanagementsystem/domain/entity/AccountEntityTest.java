//package com.company.taskmanagementsystem.domain.entity;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountEntityTest {
//
//    AccountEntity accountEntity;
//
//    final String email = "mail@gmail.com";
//    final String firstName = "Joe";
//    final String lastName = "Doe";
//
//    @BeforeEach
//    void setUp() {
//        accountEntity = new AccountEntity();
//        accountEntity.setId(1);
//        accountEntity.setEmail(email);
//        accountEntity.setFirstName(firstName);
//        accountEntity.setLastName(lastName);
//        accountEntity.setCreatedAt(LocalDateTime.now());
//        accountEntity.setCreatedAt(LocalDateTime.now());
//    }
//
//    @Test
//    void testAccountWithCorrectData() {
//        assertNotNull(accountEntity);
//    }
//
//    @Test
//    void testAccountWithIncorrectData() {
//        AccountEntity accountEntity = new AccountEntity();
//        assertNull(accountEntity.getFirstName());
//    }
//
//
//    @Test
//    void getFirstName() {
//        assertEquals(firstName, accountEntity.getFirstName());
//    }
//
//    @Test
//    void getLastName() {
//        assertEquals(lastName, accountEntity.getLastName());
//    }
//
//    @Test
//    void getEmail() {
//        assertEquals(email, accountEntity.getEmail());
//    }
//
//    @Test
//    void getCreatedAt() {
//        assertNotNull(accountEntity.getCreatedAt());
//    }
//
//    @Test
//    void getUpdatedAt() {
//        assertNotNull(accountEntity.getUpdatedAt());
//    }
//
//    @Test
//    void setFirstName() {
//        String newFirstName = "new first name";
//        accountEntity.setFirstName(newFirstName);
//        assertEquals(newFirstName, accountEntity.getFirstName());
//    }
//
//    @Test
//    void setLastName() {
//        String newLastName = "new last name";
//        accountEntity.setLastName(newLastName);
//        assertEquals(newLastName, accountEntity.getLastName());
//    }
//
//    @Test
//    void setEmail() {
//        String newEmail = "new email";
//        accountEntity.setEmail(newEmail);
//        assertEquals(newEmail, accountEntity.getEmail());
//    }
//
//    @Test
//    void setCreatedAt() {
//        LocalDateTime newCreatedAt = LocalDateTime.now();
//        accountEntity.setCreatedAt(newCreatedAt);
//        assertEquals(newCreatedAt, accountEntity.getCreatedAt());
//    }
//
//    @Test
//    void setUpdatedAt() {
//        LocalDateTime newUpdatedAt = LocalDateTime.now();
//        accountEntity.setUpdatedAt(newUpdatedAt);
//        assertEquals(newUpdatedAt, accountEntity.getCreatedAt());
//    }
//}