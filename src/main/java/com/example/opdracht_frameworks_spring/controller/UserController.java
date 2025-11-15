package com.example.opdracht_frameworks_spring.controller;

import com.example.opdracht_frameworks_spring.dao.UserDAO;
import com.example.opdracht_frameworks_spring.data.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserDAO dao;

    public UserController(UserDAO dao) {
        this.dao = dao;
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        User savedUser = dao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return dao.update(id, user)
                .map(updatedUser -> new ResponseEntity<>("", HttpStatus.NO_CONTENT))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Integer id) {
        if (dao.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        dao.deleteById(id);
    }
}
