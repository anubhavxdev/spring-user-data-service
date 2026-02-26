package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAge(int age);

    // Case-insensitive search by name
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Support pagination for age search
    Page<User> findByAge(int age, Pageable pageable);

    // Stats
    long countByStatus(User.UserStatus status);
}
