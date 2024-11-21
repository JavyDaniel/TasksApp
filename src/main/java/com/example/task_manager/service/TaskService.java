package com.example.task_manager.service;

import com.example.task_manager.mappers.TaskMapper;
import com.example.task_manager.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class TaskService {
    private final TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    // Guardar tarea
    public void saveTask(Task task) {
        validateTask(task);
        taskMapper.insertTask(task);
    }

    // Obtener todas las tareas
    public List<Task> getAllTasks() {
        List<Task> tasks = taskMapper.findAllTasks();
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No se encontraron tareas en el sistema.");
        }
        return tasks;
    }

    // Obtener tarea por ID
    public Task getTaskById(Long id) {
        validateId(id);
        Task task = taskMapper.findTaskById(id);
        if (task == null) {
            throw new IllegalArgumentException("No se encontró una tarea con el ID: " + id);
        }
        return task;
    }

    // Actualizar tarea
    public void updateTask(Task task) {
        validateId(task.getId());
        validateTask(task);
        if (taskMapper.findTaskById(task.getId()) == null) {
            throw new IllegalArgumentException("No se puede actualizar. No existe una tarea con el ID: " + task.getId());
        }
        taskMapper.updateTask(task);
    }

    // Eliminar tarea
    public void deleteTask(Long id) {
        validateId(id);
        if (taskMapper.findTaskById(id) == null) {
            throw new IllegalArgumentException("No se puede eliminar. No existe una tarea con el ID: " + id);
        }
        taskMapper.deleteTask(id);
    }

    // Validación genérica para ID
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID proporcionado no es válido.");
        }
    }

    // Validación genérica para tarea
    private void validateTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("La tarea no puede ser nula.");
        }
        if (!StringUtils.hasText(task.getName())) {
            throw new IllegalArgumentException("El nombre de la tarea no puede estar vacío.");
        }
        if (task.getStartDate() == null) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula.");
        }
    }
}
