package com.scaler.taskmanagerv2.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateTaskDTO {
    private String title;
    private String description;
    private String dueDate;
}
