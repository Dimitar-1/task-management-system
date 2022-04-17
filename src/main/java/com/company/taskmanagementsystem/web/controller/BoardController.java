package com.company.taskmanagementsystem.web.controller;

import com.company.taskmanagementsystem.domain.model.BoardModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @description: This controller is accountable for providing crud operations.
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardModel>> getAllBoards() {
        List<BoardModel> all = boardService.findAll();

        log.info("Find all boards");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<Optional<BoardModel>> getBoardById(@PathVariable(name = "boardId") int id) {
        Optional<BoardModel> byId = boardService.findById(id);

        log.info("Return board with id: " + id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<Integer> createBoard(@RequestBody BoardModel boardModel) throws BusinessException {
        int saved = boardService.save(boardModel);

        log.info("Create new board: " + boardModel.toString());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<BoardModel> updateBoard(@PathVariable(name = "boardId") int id, @RequestBody BoardModel boardModel) throws BusinessException {
        BoardModel updated = boardService.update(id, boardModel);

        log.info("Update board with id: " + id + " New board vision: " + updated.toString());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable(name = "boardId") int id) {
        boardService.deleteById(id);

        log.info("Deleted board was with id: " + id);
        return new ResponseEntity<>("Board was deleted.", HttpStatus.NO_CONTENT);
    }


}
