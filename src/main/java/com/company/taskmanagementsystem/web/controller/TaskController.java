package com.company.taskmanagementsystem.web.controller;

import com.company.taskmanagementsystem.domain.entity.AccountEntity;
import com.company.taskmanagementsystem.domain.model.AccountModel;
import com.company.taskmanagementsystem.domain.model.TaskModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.service.TaskService;
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
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskModel>> getAllTasks() {
        List<TaskModel> all = taskService.findAll();

        log.info("Return all tasks");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Optional<TaskModel>> getTaskById(@PathVariable(name = "taskId") int id) {
        Optional<TaskModel> taskModel = taskService.findById(id);

        log.info("Return task with id: " + id);
        return new ResponseEntity<>(taskModel, HttpStatus.OK);
    }

    @GetMapping("/tasks/title")
    public ResponseEntity<List<TaskModel>> findAllTaskByTitle(@RequestParam(value = "title") String title) {
        if (title == null || title.isEmpty() ||  title.replaceAll("\"", "").isEmpty()) {
            log.warn("Cannot search task by title, because is null or empty");
            return new ResponseEntity("Title cannot be empty", HttpStatus.BAD_REQUEST);
        }
        List<TaskModel> allByTitle = taskService.findAllByTitle(title);

        log.info("Return tasks by title: " + title);
        return new ResponseEntity<>(allByTitle, HttpStatus.OK);
    }

    @GetMapping("/tasks/type")
    public ResponseEntity<List<TaskModel>> findAllTaskByType(@RequestParam(value = "type") String type) {
        if (type == null || type.isEmpty() ||  type.replaceAll("\"", "").isEmpty()) {
            log.warn("Cannot search task by type, because is null or empty");
            return new ResponseEntity("Task type cannot be empty", HttpStatus.BAD_REQUEST);
        }
        List<TaskModel> allByType = taskService.findAllByType(type);

        log.info("Return tasks by type: " + type);
        return new ResponseEntity<>(allByType, HttpStatus.OK);
    }

    @GetMapping("/tasks/assignedTo")
    public ResponseEntity<List<TaskModel>> findAllTaskByType(@RequestBody AccountModel assignedTo) {
        if (assignedTo == null) {
            log.warn("Cannot search task by assignedTo account, because is null");
            return new ResponseEntity("AssignedTo (Account) type cannot be empty", HttpStatus.BAD_REQUEST);
        }
        List<TaskModel> allByType = taskService.findAllByAssignedTo(assignedTo);

        log.info("Return tasks by assignedTo: " + assignedTo);
        return new ResponseEntity<>(allByType, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Integer> createTask(@RequestBody TaskModel model) throws BusinessException {
        int saved = taskService.save(model);

        log.info("Create new task: " + model.toString());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable(name = "taskId") int id, @RequestBody TaskModel model) throws BusinessException {
        TaskModel updated = taskService.update(id, model);

        log.info("Update task with id: " + id + " New task vision: " + updated.toString());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "taskId") int id) {
        taskService.deleteById(id);

        log.info("Deleted task was with id: " + id);
        return new ResponseEntity<>("Task was successfully deleted.", HttpStatus.NO_CONTENT);
    }


}
