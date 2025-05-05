package com.example.tasks.repositories;

import com.example.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {
}
