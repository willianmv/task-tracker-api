package com.example.tasks.mappers;

import com.example.tasks.domain.dtos.TaskListDto;
import com.example.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}
