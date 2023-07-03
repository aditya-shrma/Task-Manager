package com.scaler.taskmanagerv2.Services;

import com.scaler.taskmanagerv2.Entities.TaskEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TasksService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 0;
    private final SimpleDateFormat dueDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addTask(String title, String description, String dueDate) throws ParseException
    {
        // parse error exception can be thrown by dueDateFormat.parse(dueDate)
        TaskEntity task = new TaskEntity();
        task.setId(taskId++);
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDateFormat.parse(dueDate)); // convert string to date
        task.setCompleted(false);
        tasks.add(task);
        return task;
    }

    public ArrayList<TaskEntity> getTasks()
    {
        return tasks;
    }

    public TaskEntity getTaskById(int id)
    {
        for(TaskEntity task : tasks)
        {
            if(task.getId() == id)
            {
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id,String description, String dueDate,Boolean completed) throws ParseException
    {
        TaskEntity task = getTaskById(id);
        if(task == null)
        {
            return null;
        }
        if(description != null)
        {
            task.setDescription(description);
        }
        if(dueDate != null)
        {
            task.setDueDate(dueDateFormat.parse(dueDate));
        }

        if(completed != null)
        {
            task.setCompleted(completed);
        }
        return task;
    }
}
