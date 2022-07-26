package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TaskController {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/api/task/{id}")
    public ResponseEntity search(@PathVariable("id") Long id) {
        return this.taskRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping(path = "/api/user/{id}/task")
    public ResponseEntity save(@PathVariable("id") Long id, @RequestBody Task task) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent())
            return new ResponseEntity(new ErrorController("Usuário não encontrado"), HttpStatus.NOT_FOUND);

        task.setOwner(user.get());
        Task taskCreated = taskRepository.save(task);
        return ResponseEntity.ok().body(taskCreated);
    }

}

