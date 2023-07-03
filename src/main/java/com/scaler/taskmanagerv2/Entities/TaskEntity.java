package com.scaler.taskmanagerv2.Entities;

import lombok.Data;

import java.util.Date;

@Data   // Lombok annotation to generate getters and setters
public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private boolean completed;


}