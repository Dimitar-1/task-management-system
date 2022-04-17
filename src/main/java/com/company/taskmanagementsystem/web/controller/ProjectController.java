package com.company.taskmanagementsystem.web.controller;

import com.company.taskmanagementsystem.domain.model.ProjectModel;
import com.company.taskmanagementsystem.exception.BusinessException;
import com.company.taskmanagementsystem.service.ProjectService;
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
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectModel>> getAllProjects() {
        List<ProjectModel> all = projectService.findAll();

        log.info("Return all projects");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectModel> getProjectById(@PathVariable(name = "projectId") int id) {
        ProjectModel byId = projectService.findById(id).get();

        log.info("Return project with id: " + id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping("/projects")
    public ResponseEntity<Integer> createProject(@RequestBody ProjectModel projectModel) throws BusinessException {
        int save = projectService.save(projectModel);

        log.info("Create new project: " + projectModel.toString());
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectModel> updateProjectById(@PathVariable(name = "projectId") int id, @RequestBody ProjectModel model) throws BusinessException {
        ProjectModel updated = projectService.update(id, model);

        log.info("Update project with id: " + id + " New project vision: " + updated.toString());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable(name = "projectId") int id) {
        projectService.deleteById(id);

        log.info("Deleted project was with id: " + id);
        return new ResponseEntity<>("Project was successfully deleted.", HttpStatus.NO_CONTENT);
    }
}
