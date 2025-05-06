package com.example.tasks.mappers;

import com.example.tasks.domain.dtos.CreateTaskListDto;
import com.example.tasks.domain.dtos.ResponseTaskListDto;
import com.example.tasks.domain.dtos.UpdateTaskListDto;
import com.example.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromCreateListDto(CreateTaskListDto dto);

    TaskList fromUpdateListDto(UpdateTaskListDto dto);

    ResponseTaskListDto toResponseListDto(TaskList taskList);
}
