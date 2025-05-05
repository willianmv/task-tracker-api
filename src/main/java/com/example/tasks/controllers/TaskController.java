package com.example.tasks.controllers;

import com.example.tasks.domain.dtos.TaskDto;
import com.example.tasks.domain.entities.Task;
import com.example.tasks.mappers.TaskMapper;
import com.example.tasks.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{taskListId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @Operation(summary = "Retorna todas as tarefas associadas à lista especificada")
    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("taskListId")UUID taskListId){
        return this.taskService.listTasks(taskListId)
                .stream()
                .map(this.taskMapper::toDto)
                .toList();
    }

    @Operation(summary = "Retorna uma tarefa específica associada à lista fornecida" )
    @GetMapping("/{taskId}")
    public Optional<TaskDto> getTask(@PathVariable("taskListId")UUID taskListId, @PathVariable("taskId") UUID taskId){
        return this.taskService.getTask(taskListId, taskId).map(this.taskMapper::toDto);
    }

    @Operation(summary = "Cria uma tarefa e a associa à lista especificada" )
    @PostMapping
    public TaskDto createTask(@PathVariable("taskListId") UUID taskListId, @RequestBody TaskDto dto){
        Task task = this.taskService.createTask(taskListId, this.taskMapper.fromDto(dto));
        return this.taskMapper.toDto(task);
    }

    @Operation(summary = "Localiza a tarefa na lista fornecida e realiza a atualização")
    @PutMapping("/{taskId}")
    public TaskDto updateTask(
            @PathVariable("taskListId")UUID taskListId,
            @PathVariable("taskId") UUID taskId, @RequestBody TaskDto dto){
        Task updatedTask = this.taskService.updateTask(taskListId, taskId, this.taskMapper.fromDto(dto));
        return this.taskMapper.toDto(updatedTask);
    }

    @Operation(summary = "Localiza a tarefa na lista fornecida e a exclui")
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskListId")UUID taskListId, @PathVariable("taskId") UUID taskId){
        this.taskService.deleteTask(taskListId, taskId);
    }

}
