package com.example.tasks.controllers;

import com.example.tasks.domain.dtos.CreateTaskDto;
import com.example.tasks.domain.dtos.ResponseTaskDto;
import com.example.tasks.domain.dtos.UpdateTaskDto;
import com.example.tasks.domain.entities.Task;
import com.example.tasks.domain.entities.TaskPriority;
import com.example.tasks.domain.entities.TaskStatus;
import com.example.tasks.mappers.TaskMapper;
import com.example.tasks.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/task-lists/{taskListId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    @Operation(summary = "Retorna todas as tarefas associadas à lista especificada utilizando filtros de busca")
    @GetMapping
    public List<ResponseTaskDto> listTasks(
            @PathVariable("taskListId") UUID taskListId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dueDate){
        return this.taskService.listTasks(taskListId, status, priority, dueDate)
                .stream()
                .map(this.taskMapper::toResponseDto)
                .toList();
    }

    @Operation(summary = "Retorna uma tarefa específica associada à lista fornecida" )
    @GetMapping("/{taskId}")
    public Optional<ResponseTaskDto> getTask(@PathVariable("taskListId")UUID taskListId, @PathVariable("taskId") UUID taskId){
        return this.taskService.getTask(taskListId, taskId).map(this.taskMapper::toResponseDto);
    }

    @Operation(summary = "Cria uma tarefa e a associa à lista especificada" )
    @PostMapping
    public ResponseTaskDto createTask(@PathVariable("taskListId") UUID taskListId, @RequestBody @Valid CreateTaskDto dto){
        Task task = this.taskService.createTask(taskListId, this.taskMapper.fromCreateDto(dto));
        return this.taskMapper.toResponseDto(task);
    }

    @Operation(summary = "Localiza a tarefa na lista fornecida e realiza a atualização")
    @PutMapping("/{taskId}")
    public ResponseTaskDto updateTask(
            @PathVariable("taskListId")UUID taskListId,
            @PathVariable("taskId") UUID taskId, @RequestBody @Valid UpdateTaskDto dto){
        Task updatedTask = this.taskService.updateTask(taskListId, taskId, this.taskMapper.fromUpdateDto(dto));
        return this.taskMapper.toResponseDto(updatedTask);
    }

    @Operation(summary = "Localiza a tarefa na lista fornecida e a exclui")
    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskListId")UUID taskListId, @PathVariable("taskId") UUID taskId){
        this.taskService.deleteTask(taskListId, taskId);
    }

}
