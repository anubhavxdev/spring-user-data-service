package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 120, message = "Age should not be greater than 120")
    private int age;
}
