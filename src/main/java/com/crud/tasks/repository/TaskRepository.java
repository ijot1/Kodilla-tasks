package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Optional<Task> findById(Long id);

    @Override
    Task save(Task task);

    @Override
    void deleteById(Long id);
}