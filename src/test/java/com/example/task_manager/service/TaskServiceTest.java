package com.example.task_manager.service;

import com.example.task_manager.mappers.TaskMapper;
import com.example.task_manager.models.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testGetAllTasks() {
        Task task = new Task();
        task.setId(1L);
        task.setName("Task1");
        task.setDescription("Description1");
        task.setStartDate(LocalDate.now());
        List<Task> mockTasks = new ArrayList<>(Collections.singletonList(task));
        Mockito.when(taskMapper.findAllTasks()).thenReturn(mockTasks);

        List<Task> tasks = taskService.getAllTasks();

        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Task1", tasks.get(0).getName());
    }

}