package com.scaler.taskmanagerv2.Dtos;

import com.scaler.taskmanagerv2.Entities.NotesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
    private Integer id;
    private String title;
    private String description;
    private Date dueDate;
    private Boolean completed;
    private ArrayList<NotesEntity> notes;
}
