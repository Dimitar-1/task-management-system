//package com.company.taskmanagementsystem.domain.entity;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BoardEntityTest {
//
//    BoardEntity boardEntity;
//    AccountEntity accountEntity;
//    Set<AccountEntity> accountEntitySet;
//
//    final String boardName = "board name";
//
//    @BeforeEach
//    void setUp() {
//        accountEntitySet = new HashSet<>();
//        accountEntity = AccountEntity.builder().build();
//        accountEntitySet.add(accountEntity);
//
//        boardEntity = BoardEntity
//                .builder()
//                .name(boardName)
//                .accountEntities(accountEntitySet)
//                .build();
//    }
//
//    @Test
//    void testBoardWithCorrectData() {
//        assertNotNull(boardEntity);
//    }
//
//    @Test
//    void testBoardWithIncorrectData() {
//        BoardEntity boardEntity = new BoardEntity();
//        assertNull(boardEntity.getName());
//    }
//
//    @Test
//    void getName() {
//        assertEquals(boardName, boardEntity.getName());
//    }
//
//    @Test
//    void getBoardAccountEntities() {
//        assertNotNull(boardEntity.getAccountEntities());
//        assertEquals(1, boardEntity.getAccountEntities().size());
//    }
//
//    @Test
//    void getCreatedAt() {
//        assertNotNull(boardEntity.getCreatedAt());
//    }
//
//    @Test
//    void getUpdatedAt() {
//        assertNotNull(boardEntity.getCreatedAt());
//    }
//
//    @Test
//    void setId() {
//        int newId = 5;
//    }
//
//    @Test
//    void setName() {
//        String newName = "new board name";
//        boardEntity.setName(newName);
//        assertEquals(newName, boardEntity.getName());
//    }
//
//    @Test
//    void setBoardAccountEntities() {
//        AccountEntity accountEntity1 = AccountEntity.builder().accountId(2).build();
//        accountEntitySet.add(accountEntity1);
//        assertEquals(2, boardEntity.getAccountEntities().size());
//    }
//
//    @Test
//    void setCreatedAt() {
//        LocalDate newCreatedAt = LocalDate.now();
//        boardEntity.setCreatedAt(newCreatedAt);
//        assertEquals(newCreatedAt, boardEntity.getCreatedAt());
//    }
//
//    @Test
//    void setUpdatedAt() {
//        LocalDate newUpdateAt = LocalDate.now();
//        boardEntity.setUpdatedAt(newUpdateAt);
//        assertEquals(newUpdateAt, boardEntity.getUpdatedAt());
//    }
//}