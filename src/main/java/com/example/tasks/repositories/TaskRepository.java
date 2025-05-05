package com.example.tasks.repositories;

import com.example.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTaskListId(UUID taskListId);

    Optional<Task> findByTaskIdAndId(UUID taskListId, UUID id);
}
