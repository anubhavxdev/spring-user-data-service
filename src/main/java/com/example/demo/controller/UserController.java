package com.example.demo.controller;

import java.util.List;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Find All
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Find By ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Find By Age
    @GetMapping("/age/{age}")
    public List<User> getUsersByAge(@PathVariable int age) {
        return userRepository.findByAge(age);
    }

    // Create / Update
    @PostMapping
    public User createOrUpdateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
