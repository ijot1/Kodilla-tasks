package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {
    @InjectMocks
    private TaskMapper taskMapper;

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title_#1", "content_#1");
        //When
        String str = taskMapper.mapToTask(taskDto).getTitle();
        System.out.println(str);
        //Then
        Assert.assertEquals("title_#1", str);
    }

    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title_#1", "content_#1");
        //When
        String str = taskMapper.mapToTaskDto(task).getTitle();
        System.out.println(str);
        //Then
        Assert.assertEquals("title_#1", str);
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        Task task1 = new Task(1L, "title_#1", "content_#1");
        Task task2 = new Task(2L, "title_#2", "content_#2");
        tasks.add(task1);
        tasks.add(task2);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        int number = taskDtos.size();
        System.out.println(taskDtos);
        //Then
        Assert.assertEquals(2, number);
    }
}
