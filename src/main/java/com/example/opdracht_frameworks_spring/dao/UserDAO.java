package com.example.opdracht_frameworks_spring.dao;

import com.example.opdracht_frameworks_spring.data.entity.User;
import com.example.opdracht_frameworks_spring.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {
    private final UserRepo repo;

    public UserDAO(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    public Optional<User> findById(Integer id) {
        return repo.findById(id);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public Optional<User> update(Integer id, User updatedUser) {
        return repo.findById(id).map(existingUser -> {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setRoles(updatedUser.getRoles());
            return repo.save(existingUser);
        });
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
