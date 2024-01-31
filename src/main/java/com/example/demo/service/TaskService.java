package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling Task-related business logic.
 */
@Service
public class TaskService {

    /**
     * The TaskRepository for interacting with the database.
     */
    private final TaskRepository taskRepository;

    /**
     * Constructor for TaskService.
     *
     * @param taskRepository The TaskRepository for database interaction.
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Saves a new Task in the database.
     *
     * @param task The Task object to be saved.
     * @return The saved Task object.
     */
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Retrieves a Task by its ID from the database.
     *
     * @param id The ID of the Task to be retrieved.
     * @return An Optional containing the Task if found, or an empty Optional if not.
     */
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    /**
     * Retrieves a list of all Tasks from the database.
     *
     * @return A List of all Task objects.
     */
    public List<Task> getALLTask() {
        return taskRepository.findAll();
    }

    /**
     * Updates an existing Task in the database by its ID.
     *
     * @param id   The ID of the Task to be updated.
     * @param task The updated Task object.
     * @return The updated Task object.
     * @throws RuntimeException if the Task with the specified ID is not found.
     */
    public Task updateTask(int id, Task task) {
        Task taskToUpdate = taskRepository.findById(id).orElseThrow();
        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setStatus(task.getStatus());
        return taskRepository.save(taskToUpdate);
    }

    /**
     * Deletes a Task from the database by its ID.
     *
     * @param id The ID of the Task to be deleted.
     */
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
