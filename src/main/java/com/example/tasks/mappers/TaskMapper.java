package com.example.tasks.mappers;

import com.example.tasks.domain.dtos.CreateTaskDto;
import com.example.tasks.domain.dtos.ResponseTaskDto;
import com.example.tasks.domain.dtos.TaskDto;
import com.example.tasks.domain.dtos.UpdateTaskDto;
import com.example.tasks.domain.entities.Task;

public interface TaskMapper {

    Task fromCreateDto(CreateTaskDto createTaskDto);

    Task fromUpdateDto(UpdateTaskDto updateTaskDto);

    ResponseTaskDto toResponseDto(Task task);

}
