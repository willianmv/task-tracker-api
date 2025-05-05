package com.example.tasks.repositories;

import com.example.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {

    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id);

    void deleteByTaskListIdAndId(UUID taskListId, UUID id);
}
