package com.example.tasks.mappers.impl;

import com.example.tasks.domain.dtos.TaskListDto;
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
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),

                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto).toList()).orElse(null),
                null,
                null);
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),

                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0),

                calculateTaskListProgress(taskList.getTasks()),

                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto).toList())
                        .orElse(null));
    }

    private Double calculateTaskListProgress(List<Task> tasks){
        if(tasks == null) return null;

        long closedTasks = tasks.stream().filter(task ->
                task.getStatus() == TaskStatus.CLOSED).count();

        return (double) closedTasks / tasks.size();
    }
}
