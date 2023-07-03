package com.scaler.taskmanagerv2.Entities;

import lombok.Data;

@Data   // Lombok annotation to generate getters and setters
public class NotesEntity {
    private int id;
    private String title;
    private String body;

}
