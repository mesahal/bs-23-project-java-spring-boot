package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Task in the system.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task {

    /**
     * The unique identifier for the task.
     */
    @Id
    @Column(name = "task_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The title of the task.
     */
    @Column(name = "task_title", length = 255)
    private String title;

    /**
     * The description of the task.
     */
    @Column(name = "task_description", length = 255)
    private String description;

    /**
     * The status of the task.
     */
    @Column(name = "task_status", length = 255)
    private String status;

}
