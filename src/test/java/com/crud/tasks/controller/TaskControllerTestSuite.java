package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTasksTest() throws Exception {
        //Given
        List<TaskDto> taskList = new ArrayList<>();
        taskList.add(new TaskDto(1L, "test_title", "test_content"));

        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test_title")))
                .andExpect(jsonPath("$[0].content", is("test_content")));
    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_title", "test_content");
        Task task = new Task(1L, "test_title", "test_content");

        when(service.getTask(1l)).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test_title")))
                .andExpect(jsonPath("$.content", is("test_content")));
    }

    @Test
    public void createTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_title", "test_content");
        Task task = new Task(1L, "test_title", "test_content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void updateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "updated_title", "updated_content");
        Task task = new Task(1L, "test_title", "test_content");

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("updated_title")))
                .andExpect(jsonPath("$.content", is("updated_content")));
    }

    @Test
    public void deleteTaskTest() throws Exception {

        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
        verify(service, times(1)).deleteTask(anyLong());
    }
}