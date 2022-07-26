package com.example.demo.repository;

import com.example.demo.model.Task;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
