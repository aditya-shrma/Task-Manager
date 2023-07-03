package com.scaler.taskmanagerv2.Controllers;

import com.scaler.taskmanagerv2.Dtos.CreateTaskDTO;
import com.scaler.taskmanagerv2.Dtos.ErrorResponseDTO;
import com.scaler.taskmanagerv2.Dtos.TaskResponseDTO;
import com.scaler.taskmanagerv2.Dtos.UpdateTaskDTO;
import com.scaler.taskmanagerv2.Entities.TaskEntity;
import com.scaler.taskmanagerv2.Services.NotesService;
import com.scaler.taskmanagerv2.Services.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.ArrayList;

@RestController                 // Tells Spring that this class is a controller
@RequestMapping("/tasks")       // Tells Spring that all the endpoints in this class will start with /tasks
public class TasksController {
    private final TasksService tasksService;
    private final NotesService notesService;

    private ModelMapper modelMapper = new ModelMapper(); // ModelMapper is used to map one object to another

    public TasksController(TasksService tasksService, NotesService notesService) {
        this.tasksService = tasksService;
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<TaskEntity>> getTasks() {
        var tasks = tasksService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id})")
    public ResponseEntity<TaskResponseDTO> getTaskbyId(@PathVariable("id") Integer id)
    {
        var task =tasksService.getTaskById(id);
        var notes = notesService.getNotesForTasks(id);

        if(task == null)
        {
            return ResponseEntity.notFound().build();
        }

        var taskResponse = modelMapper.map(task, TaskResponseDTO.class); //map task to taskResponseDTO
        taskResponse.setNotes(notes);

        return ResponseEntity.ok(taskResponse);

    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException
    {
        var task = tasksService.addTask(body.getTitle(), body.getDescription(), body.getDueDate());

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException
    {
        var task = tasksService.updateTask(id,body.getDescription(), body.getDueDate(), body.getCompleted());

        if(task == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)  // This method will be called when any exception is thrown in this class
    public ResponseEntity<ErrorResponseDTO> handlesException(Exception e)
    {
        if(e instanceof ParseException)
        {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
        }

        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal server error"));
    }



}
