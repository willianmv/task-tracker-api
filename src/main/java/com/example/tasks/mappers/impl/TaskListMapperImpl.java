package com.example.tasks.mappers.impl;

import com.example.tasks.domain.dtos.CreateTaskListDto;
import com.example.tasks.domain.dtos.ResponseTaskListDto;
import com.example.tasks.domain.dtos.UpdateTaskListDto;
import com.example.tasks.domain.entities.Task;
import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.domain.entities.TaskStatus;
import com.example.tasks.mappers.TaskListMapper;
import com.example.tasks.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    @Override
    public TaskList fromCreateListDto(CreateTaskListDto dto) {
        return new TaskList(
                null,
                dto.title(),
                dto.description(),
                null,
                null, null);
    }

    @Override
    public TaskList fromUpdateListDto(UpdateTaskListDto dto) {
        return new TaskList(
                dto.id(),
                dto.title(),
                dto.description(),
                null, null, null);
    }

    @Override
    public ResponseTaskListDto toResponseListDto(TaskList taskList) {
        return new ResponseTaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),

                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),

                calculateTaskListProgress(taskList.getTasks()),

                taskList.getCreated(),
                taskList.getUpdated(),

                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(this.taskMapper::toResponseDto).toList())
                        .orElse(null));
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(tasks == null) return null;

        long closedTasks = tasks.stream().filter(task ->
                task.getStatus() == TaskStatus.CLOSED).count();

        return (double) closedTasks / tasks.size();
    }
}
