package com.scaler.taskmanagerv2.Controllers;
import org.springframework.http.ResponseEntity;
import com.scaler.taskmanagerv2.Dtos.CreateNoteDTO;
import com.scaler.taskmanagerv2.Dtos.CreateNoteResponseDTO;
import com.scaler.taskmanagerv2.Entities.NotesEntity;
import com.scaler.taskmanagerv2.Services.NotesService;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
@RequestMapping("tasks/{taskId}/notes")
public class NotesController {
    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<NotesEntity>> getNotesForTask(@PathVariable("taskId") Integer taskId)
    {
        var notes= notesService.getNotesForTasks(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNoteForTask(@PathVariable("taskId") Integer taskId, @RequestBody CreateNoteDTO body)
    {
        var note=notesService.addNoteForTask(taskId,body.getTitle(),body.getBody());

        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,note));
    }


}
