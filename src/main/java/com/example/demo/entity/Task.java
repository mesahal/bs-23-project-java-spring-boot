package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "task_id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "task_title", length = 255)
    private String title;
    @Column(name = "task_description", length = 255)
    private String description;
    @Column(name = "task_status", length = 255)
    private String status;

    public Task() {

    }
}
