package com.example.task_manager.mappers;


import com.example.task_manager.models.Task;

import java.util.List;

public interface TaskMapper {
    void insertTask(Task task);
    List<Task> findAllTasks();
    Task findTaskById(Long id);
    void updateTask(Task task);
    void deleteTask(Long id);
}
