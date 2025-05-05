package com.example.tasks.services.impl;

import com.example.tasks.domain.entities.Task;
import com.example.tasks.domain.entities.TaskList;
import com.example.tasks.domain.entities.TaskPriority;
import com.example.tasks.domain.entities.TaskStatus;
import com.example.tasks.repositories.TaskListRepository;
import com.example.tasks.repositories.TaskRepository;
import com.example.tasks.repositories.specs.TaskSpecifications;
import com.example.tasks.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskListRepository taskListRepository;

    @Override
    public List<Task> listTasks(UUID taskListId, TaskStatus status, TaskPriority priority, LocalDate dueDate) {

        Specification<Task> spec = Specification.where(TaskSpecifications.hasTaskListId(taskListId));

        if(status != null) spec = spec.and(TaskSpecifications.hasStatus(status));
        if(priority != null) spec = spec.and(TaskSpecifications.hasPriority(priority));
        if(dueDate != null) spec = spec.and(TaskSpecifications.hasDueDateBeforeOrEqual(dueDate));

        return taskRepository.findAll(spec);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(task.getId() != null){
            throw new IllegalArgumentException("Task already has a ID.");
        }
        if(task.getTitle() == null || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task must have a title.");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task List not found."));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now, now);

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(task.getId() == null){
            throw new IllegalArgumentException("Task must have an ID.");
        }
        if(!Objects.equals(taskId, task.getId())){
            throw new IllegalArgumentException("Task IDs do not match.");
        }
        if(task.getStatus() == null){
            throw new IllegalArgumentException("Task must have a status.");
        }
        if(task.getPriority() == null){
            throw new IllegalArgumentException("Task must have a priority.");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found."));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setPriority(task.getPriority());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setUpdated(LocalDateTime.now());

        return this.taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        this.taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
