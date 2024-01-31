package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task saveTask(@RequestBody Task Task){
        return taskService.saveTask(Task);
    }

    @GetMapping
    public List<Task> getAllTask(){
        return taskService.getALLTask();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskId(@PathVariable int id){
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task Task){
        return taskService.updateTask(id,Task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }



}