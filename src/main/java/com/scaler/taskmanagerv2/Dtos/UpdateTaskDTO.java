package com.scaler.taskmanagerv2.Dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {
    String description;
    String dueDate;
    Boolean completed;
}
