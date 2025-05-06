package com.example.tasks.controllers;

import com.example.tasks.domain.dtos.CreateTaskListDto;
import com.example.tasks.domain.dtos.ResponseTaskListDto;
import com.example.tasks.domain.dtos.UpdateTaskListDto;
import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.mappers.TaskListMapper;
import com.example.tasks.services.TaskListService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    public List<ResponseTaskListDto> listTaskLists(){
        return taskListService.listTaskLists()
                .stream()
                .map(this.taskListMapper::toResponseListDto)
                .toList();
    }

    @Operation(summary = "Recupera uma lista de tarefas específica")
    @GetMapping("/{taskListId}")
    public Optional<ResponseTaskListDto> getTaskList(@PathVariable("taskListId")UUID id){
        return this.taskListService.getTaskList(id).map(this.taskListMapper::toResponseListDto);
    }

    @Operation(summary = "Cria uma lista de tarefas com os dados fornecidos")
    @PostMapping
    public ResponseTaskListDto crateTaskList(@RequestBody @Valid CreateTaskListDto dto){
        TaskList taskList = taskListService.createTaskList(this.taskListMapper.fromCreateListDto(dto));
        return this.taskListMapper.toResponseListDto(taskList);
    }

    @Operation(summary = "Atualiza uma lista de tarefas existente")
    @PutMapping("/{taskListId}")
    public ResponseTaskListDto updateTaskList(@PathVariable("taskListId")UUID id, @RequestBody @Valid UpdateTaskListDto dto){
        TaskList updatedTask = taskListService.updateTaskList(id, this.taskListMapper.fromUpdateListDto(dto));
        return this.taskListMapper.toResponseListDto(updatedTask);
    }

    @Operation(summary = "Exclui uma lista de tarefas específica")
    @DeleteMapping("/{taskListId}")
    public void deleteTaskList(@PathVariable("taskListId")UUID id){
        this.taskListService.deleteTaskList(id);
    }

}
