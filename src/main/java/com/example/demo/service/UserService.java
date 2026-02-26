package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Page<User> searchByName(String name, Pageable pageable) {
        return userRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public Page<User> getUsersByAge(int age, Pageable pageable) {
        return userRepository.findByAge(age, pageable);
    }

    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> toggleStatus(Long id) {
        return userRepository.findById(id).map(user -> {
            user.setStatus(
                    user.getStatus() == User.UserStatus.ACTIVE ? User.UserStatus.INACTIVE : User.UserStatus.ACTIVE);
            return userRepository.save(user);
        });
    }

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("activeUsers", userRepository.countByStatus(User.UserStatus.ACTIVE));
        stats.put("inactiveUsers", userRepository.countByStatus(User.UserStatus.INACTIVE));
        stats.put("averageAge", userRepository.findAll().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0.0));
        return stats;
    }
}
