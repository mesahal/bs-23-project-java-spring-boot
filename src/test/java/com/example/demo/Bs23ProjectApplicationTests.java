package com.example.demo;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class Bs23ProjectApplicationTests {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void getTasksTest() {
        when(taskRepository.findAll()).thenReturn(
                Stream.of(new Task(1, "Task1", "Description1", "Pending"),
                                new Task(2, "Task2", "Description2", "Completed"))
                        .collect(Collectors.toList()));

        List<Task> tasks = taskService.getALLTask();
        assertEquals(2, tasks.size());
    }

    @Test
    public void saveTaskTest() {
        Task task = new Task(3, "Task3", "Description3", "Pending");
        when(taskRepository.save(task)).thenReturn(task);

        Task savedTask = taskService.saveTask(task);
        assertEquals(task, savedTask);
    }

    @Test
    public void updateTaskTest() {
        int taskId = 1;
        Task existingTask = new Task(taskId, "Task1", "Description1", "Pending");
        Task updatedTask = new Task(taskId, "UpdatedTask", "UpdatedDescription", "Completed");

        when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(existingTask));
        when(taskRepository.save(updatedTask)).thenReturn(updatedTask);

        Task result = taskService.updateTask(taskId, updatedTask);
        assertEquals(updatedTask.getTitle(), result.getTitle());
        assertEquals(updatedTask.getDescription(), result.getDescription());
        assertEquals(updatedTask.getStatus(), result.getStatus());
    }

    @Test
    public void deleteTaskTest() {
        int taskId = 1;
        Task taskToDelete = new Task(taskId, "Task1", "Description1", "Pending");

        taskService.deleteTask(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
