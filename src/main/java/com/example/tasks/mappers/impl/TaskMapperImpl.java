package com.example.tasks.mappers.impl;

import com.example.tasks.domain.dtos.CreateTaskDto;
import com.example.tasks.domain.dtos.ResponseTaskDto;
import com.example.tasks.domain.dtos.TaskDto;
import com.example.tasks.domain.dtos.UpdateTaskDto;
import com.example.tasks.domain.entities.Task;
import com.example.tasks.domain.entities.TaskStatus;
import com.example.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromCreateDto(CreateTaskDto createTaskDto) {
        return new Task(
                null,
                createTaskDto.title(),
                createTaskDto.description(),
                createTaskDto.dueDate(),
                TaskStatus.OPEN,
                createTaskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public Task fromUpdateDto(UpdateTaskDto updateTaskDto) {
        return new Task(
                updateTaskDto.id(),
                updateTaskDto.title(),
                updateTaskDto.description(),
                updateTaskDto.dueDate(),
                updateTaskDto.status(),
                updateTaskDto.priority(),
                null, null, null);
    }

    @Override
    public ResponseTaskDto toResponseDto(Task task) {
        return new ResponseTaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus(),
                task.getCreated(),
                task.getUpdated());
    }
}
