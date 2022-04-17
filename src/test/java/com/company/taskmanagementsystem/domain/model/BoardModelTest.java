//package com.company.taskmanagementsystem.domain.model;
//
//import com.company.taskmanagementsystem.domain.entity.AccountEntity;
//import com.company.taskmanagementsystem.domain.entity.BoardEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class BoardModelTest {
//
//    BoardModel boardModel;
//    AccountModel accountModel;
//    Set<AccountModel> accountModelSet;
//
//    final String boardName = "board name";
//
//    @BeforeEach
//    void setUp() {
//        accountModelSet = new HashSet<>();
//        accountModel = AccountModel.builder().accountId(1).build();
//        accountModelSet.add(accountModel);
//
//        boardModel = BoardModel
//                .builder()
//                .boardId(1)
//                .name(boardName)
//                .accountModels(accountModelSet)
//                .createdAt(LocalDate.now())
//                .updatedAt(LocalDate.now())
//                .build();
//    }
//
//    @Test
//    void testBoardWithCorrectData() {
//        assertNotNull(boardModel);
//    }
//
//    @Test
//    void testBoardWithIncorrectData() {
//        BoardModel boardModel = new BoardModel();
//        assertNull(boardModel.getName());
//    }
//
//    @Test
//    void getId() {
//        assertEquals(1, boardModel.getBoardId());
//    }
//
//    @Test
//    void getName() {
//        assertEquals(boardName, boardModel.getName());
//    }
//
//    @Test
//    void getBoardAccountEntities() {
//        assertNotNull(boardModel.getAccountModels());
//        assertEquals(1, boardModel.getAccountModels().size());
//    }
//
//    @Test
//    void getCreatedAt() {
//        assertNotNull(boardModel.getCreatedAt());
//    }
//
//    @Test
//    void getUpdatedAt() {
//        assertNotNull(boardModel.getCreatedAt());
//    }
//
//    @Test
//    void setId() {
//        int newId = 5;
//        boardModel.setBoardId(newId);
//        assertEquals(newId, boardModel.getBoardId());
//    }
//
//    @Test
//    void setName() {
//        String newName = "new board name";
//        boardModel.setName(newName);
//        assertEquals(newName, boardModel.getName());
//    }
//
//    @Test
//    void setBoardAccountEntities() {
//        AccountModel accountEntity1 = AccountModel.builder().accountId(2).build();
//        accountModelSet.add(accountEntity1);
//        assertEquals(2, boardModel.getAccountModels().size());
//    }
//
//    @Test
//    void setCreatedAt() {
//        LocalDate newCreatedAt = LocalDate.now();
//        boardModel.setCreatedAt(newCreatedAt);
//        assertEquals(newCreatedAt, boardModel.getCreatedAt());
//    }
//
//    @Test
//    void setUpdatedAt() {
//        LocalDate newUpdateAt = LocalDate.now();
//        boardModel.setUpdatedAt(newUpdateAt);
//        assertEquals(newUpdateAt, boardModel.getUpdatedAt());
//    }
//}