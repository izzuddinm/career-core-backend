package com.izzuddinm.career_core_backend.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    private String id;

    // Basic Job Information
    @NotBlank(message = "Job title cannot be blank")
    @Size(max = 100, message = "Job title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Job description cannot be blank")
    @Size(max = 2000, message = "Job description must not exceed 2000 characters")
    private String description;

    @NotNull(message = "Job type cannot be null")
    private String jobType; // E.g., Full-time, Part-time, Contract, Internship

    @NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private Double salary;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    // Company Information
    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @Size(max = 500, message = "Company description must not exceed 500 characters")
    private String companyDescription;

    @NotBlank(message = "Company website cannot be blank")
    @Pattern(regexp = "^(http|https)://.*$", message = "Invalid URL format for company website")
    private String companyWebsite;

    // Job Requirements
    @NotNull(message = "Minimum qualifications cannot be null")
    @Size(max = 500, message = "Minimum qualifications must not exceed 500 characters")
    private String minimumQualifications;

    @Size(max = 1000, message = "Preferred qualifications must not exceed 1000 characters")
    private String preferredQualifications;

    @Size(max = 1000, message = "Job responsibilities must not exceed 1000 characters")
    private List<String> responsibilities;

    // Benefits
    @Size(max = 1000, message = "Job benefits must not exceed 1000 characters")
    private List<String> benefits;

    // Application Details
    @NotNull(message = "Application deadline cannot be null")
    @FutureOrPresent(message = "Application deadline must be a present or future date")
    private LocalDate applicationDeadline;

    @NotBlank(message = "Contact email cannot be blank")
    @Email(message = "Invalid email format")
    private String contactEmail;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String contactPhone;

    // Other Metadata
    private String createdBy;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
