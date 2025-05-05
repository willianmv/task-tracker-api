package com.example.tasks.controllers;

import com.example.tasks.domain.dtos.TaskListDto;
import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.mappers.TaskListMapper;
import com.example.tasks.services.TaskListService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Recupera todas as listas de tarefas")
    @GetMapping
    public List<TaskListDto> listTaskLists(){
        return taskListService.listTaskLists()
                .stream()
                .map(this.taskListMapper::toDto)
                .toList();
    }

    @Operation(summary = "Recupera uma lista de tarefas específica")
    @GetMapping("/{taskListId}")
    public Optional<TaskListDto> getTaskList(@PathVariable("taskListId")UUID id){
        return this.taskListService.getTaskList(id).map(this.taskListMapper::toDto);
    }

    @Operation(summary = "Cria uma lista de tarefas com os dados fornecidos")
    @PostMapping
    public TaskListDto crateTaskList(@RequestBody TaskListDto dto){
        TaskList taskList = taskListService.createTaskList(this.taskListMapper.fromDto(dto));
        return this.taskListMapper.toDto(taskList);
    }

    @Operation(summary = "Atualiza uma lista de tarefas existente")
    @PutMapping("/{taskListId}")
    public TaskListDto updateTaskList(@PathVariable("taskListId")UUID id, @RequestBody TaskListDto dto){
        TaskList updatedTask = taskListService.updateTaskList(id, this.taskListMapper.fromDto(dto));
        return this.taskListMapper.toDto(updatedTask);
    }

    @Operation(summary = "Exclui uma lista de tarefas específica")
    @DeleteMapping("/{taskListId}")
    public void deleteTaskList(@PathVariable("taskListId")UUID id){
        this.taskListService.deleteTaskList(id);
    }

}
