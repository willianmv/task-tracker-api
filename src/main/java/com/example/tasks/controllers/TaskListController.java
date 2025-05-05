package com.example.tasks.controllers;

import com.example.tasks.domain.dtos.TaskListDto;
import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.mappers.TaskListMapper;
import com.example.tasks.services.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    private final TaskListMapper taskListMapper;

    @GetMapping
    public List<TaskListDto> listTaskLists(){
        return taskListService.listTaskLists()
                .stream()
                .map(this.taskListMapper::toDto)
                .toList();
    }

    @GetMapping("/{taskListId}")
    public Optional<TaskListDto> getTaskList(@PathVariable("taskListId")UUID id){
        return this.taskListService.getTaskList(id).map(this.taskListMapper::toDto);
    }

    @PostMapping
    public TaskListDto crateTaskList(@RequestBody TaskListDto dto){
        TaskList taskList = taskListService.createTaskList(this.taskListMapper.fromDto(dto));
        return this.taskListMapper.toDto(taskList);
    }

    @PutMapping("/{taskListId}")
    public TaskListDto updateTaskList(@PathVariable("taskListId")UUID id, @RequestBody TaskListDto dto){
        TaskList updatedTask = taskListService.updateTaskList(id, this.taskListMapper.fromDto(dto));
        return this.taskListMapper.toDto(updatedTask);
    }

}
