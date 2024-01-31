package com.example.demo.service;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task Task) {
        return taskRepository.save(Task);
    }

    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public List<Task> getALLTask() {
        return taskRepository.findAll();
    }

    public Task updateTask(int id, Task task) {
        Task TaskToUpdate = taskRepository.findById(id).orElseThrow();
        TaskToUpdate.setTitle(task.getTitle());
        TaskToUpdate.setDescription(task.getDescription());
        TaskToUpdate.setStatus(task.getStatus());
        return taskRepository.save(TaskToUpdate);
    }

    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
