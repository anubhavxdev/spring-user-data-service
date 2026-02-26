package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        log.info("Fetching paginated users: {}", pageable);
        return userRepository.findAll(pageable).map(UserResponse::fromEntity);
    }

    public UserResponse getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public Page<UserResponse> searchByName(String name, Pageable pageable) {
        log.info("Searching users by name: '{}'", name);
        return userRepository.findByNameContainingIgnoreCase(name, pageable).map(UserResponse::fromEntity);
    }

    public Page<UserResponse> getUsersByAge(int age, Pageable pageable) {
        log.info("Fetching users with age: {}", age);
        return userRepository.findByAge(age, pageable).map(UserResponse::fromEntity);
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        log.info("Creating new user with email: {}", request.getEmail());
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        return UserResponse.fromEntity(userRepository.save(user));
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        log.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        return UserResponse.fromEntity(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        log.warn("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponse toggleStatus(Long id) {
        log.info("Toggling status for user id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setStatus(user.getStatus() == User.UserStatus.ACTIVE ? User.UserStatus.INACTIVE : User.UserStatus.ACTIVE);
        return UserResponse.fromEntity(userRepository.save(user));
    }

    public Map<String, Object> getStats() {
        log.info("Generating user statistics");
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
