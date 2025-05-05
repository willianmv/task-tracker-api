package com.example.tasks.services;

import com.example.tasks.domain.entities.Task;
import com.example.tasks.domain.entities.TaskPriority;
import com.example.tasks.domain.entities.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<Task> listTasks(UUID taskListId, TaskStatus status, TaskPriority priority, LocalDate dueDate);

    Task createTask(UUID taskListId, Task task);

    Optional<Task> getTask(UUID taskListId, UUID taskId);

    Task updateTask(UUID taskListId, UUID taskId, Task task);

    void deleteTask(UUID taskListId, UUID taskId);

}
