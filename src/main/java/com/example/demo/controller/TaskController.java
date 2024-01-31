package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing Task-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Handles the creation of a new Task.
     *
     * @param task The Task object to be saved.
     * @return The saved Task object.
     */
    @PostMapping
    public Task saveTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    /**
     * Retrieves a list of all Tasks.
     *
     * @return A List of all Task objects.
     */
    @GetMapping
    public List<Task> getAllTask() {
        return taskService.getALLTask();
    }

    /**
     * Retrieves a Task by its ID.
     *
     * @param id The ID of the Task to be retrieved.
     * @return An Optional containing the Task if found, or an empty Optional if not.
     */
    @GetMapping("/{id}")
    public Optional<Task> getTaskId(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    /**
     * Updates an existing Task by its ID.
     *
     * @param id   The ID of the Task to be updated.
     * @param task The updated Task object.
     * @return The updated Task object.
     */
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    /**
     * Deletes a Task by its ID.
     *
     * @param id The ID of the Task to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}
