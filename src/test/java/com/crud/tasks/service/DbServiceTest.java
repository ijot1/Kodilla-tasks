package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void shouldGetNoTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> fetchedTasks = dbService.getAllTasks();
        //Then
        assertEquals(0, fetchedTasks.size());
    }

    @Test
    public void shouldGetAllTasks() {
        //Given
        Task task = new Task(1L, "test_task_#1", "test_task_#1_content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        //When
        List<Task> fetchedTasks = dbService.getAllTasks();
        //Then
        assertEquals(1, fetchedTasks.size());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "test_task_#1", "test_task_#1_content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.findById(task.getId())).thenReturn(java.util.Optional.of((Task) task));
        //When
        boolean taskExists = dbService.getTask(1L).isPresent();
        //Then
        assertTrue("The task is present", taskExists == true);
    }
}