package com.izzuddinm.career_core_backend.model.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = Job.COLLECTION)
public class Job {

    public static final String COLLECTION = "jobs";

    @Id
    private String id = "JOB-" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

    // Basic Job Information
    private String title;
    private String description;
    private String jobType; // E.g., Full-time, Part-time, Contract, Internship
    private Double salary;
    private String location;

    // Company Information
    private String companyName;
    private String companyDescription;
    private String companyWebsite;

    // Job Requirements
    private String minimumQualifications;
    private String preferredQualifications;
    private List<String> responsibilities;

    // Benefits
    private List<String> benefits;

    // Application Details
    private LocalDate applicationDeadline;
    private String contactEmail;
    private String contactPhone;

    // Other Metadata
    private String createdBy;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}
