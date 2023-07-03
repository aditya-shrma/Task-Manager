package com.scaler.taskmanagerv2.Services;

import com.scaler.taskmanagerv2.Entities.NotesEntity;
import com.scaler.taskmanagerv2.Entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NotesService {
    class TaskNotesHolder{
        protected int noteId=0;
        protected ArrayList<NotesEntity> notes=new ArrayList<>();
    }
    private TasksService tasksService;
    private HashMap<Integer,TaskNotesHolder> taskNotesHolders = new HashMap<>(); // TaskId, TaskNotesHolder

    public NotesService(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    public ArrayList<NotesEntity> getNotesForTasks(int taskId){
        TaskEntity task=tasksService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNotesHolders.get(taskId)==null){
            taskNotesHolders.put(taskId,new TaskNotesHolder());
        }

        return taskNotesHolders.get(taskId).notes;
    }

    public NotesEntity addNoteForTask(int taskId,String title,String body)
    {
        TaskEntity task=tasksService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        // adding a entry in hashmap for the taskId if it is not present
        if(taskNotesHolders.get(taskId)==null){
            taskNotesHolders.put(taskId,new TaskNotesHolder());
        }

        // adding a noteholder
        TaskNotesHolder taskNoteHolder = taskNotesHolders.get(taskId);
        //adding note to the noteholder
        NotesEntity note=new NotesEntity();
        note.setId(taskNoteHolder.noteId++);
        note.setTitle(title);
        note.setBody(body);
        taskNoteHolder.notes.add(note);
        return note;
    }


}
