package com.example.tasks.repositories.specs;

import com.example.tasks.domain.entities.Task;
import com.example.tasks.domain.entities.TaskPriority;
import com.example.tasks.domain.entities.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public class TaskSpecifications {

    public static Specification<Task> hasTaskListId(UUID taskListId){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("taskList").get("id"), taskListId));
    }

    public static Specification<Task> hasPriority(TaskPriority priority){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("priority"), priority);
    }

    public static Specification<Task> hasStatus(TaskStatus status){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> hasDueDateBeforeOrEqual(LocalDate dueDate){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), dueDate);
    }

}
