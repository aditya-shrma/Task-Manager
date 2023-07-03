package com.scaler.taskmanagerv2.Dtos;

import com.scaler.taskmanagerv2.Entities.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor  // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate an all-args constructor
@Getter
@Setter
public class CreateNoteResponseDTO {

    private Integer taskId;
    private NotesEntity note;

}
