package com.example.demo.controller;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping(path = "/api/user/{id}")
    public ResponseEntity search(@PathVariable("id") Long id) {
        return this.repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/user")
    public Iterable<User> listAll() {
        return this.repository.findAll();
    }


    @PostMapping(path = "/signup")
    public ResponseEntity<User> save(@RequestBody User user) throws Exception {

        Optional<User> existsUser = this.repository.findByUsername(user.getUsername());

        if(!existsUser.isEmpty()) {
            throw new Error("User already exists");
        }

        String newPassword = encoder.encode(user.getPassword());

        user.setPassword(newPassword);
        return ResponseEntity.ok(this.repository.save(user));
    }

}
