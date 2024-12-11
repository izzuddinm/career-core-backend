package com.izzuddinm.career_core_backend.service;

import com.izzuddinm.career_core_backend.base.response.BaseResponse;
import com.izzuddinm.career_core_backend.model.document.Job;
import com.izzuddinm.career_core_backend.model.dto.JobDto;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<BaseResponse<Job>> createJob(Locale locale, JobDto jobDto) {
        try {
            Job job = new Job();
            BeanUtils.copyProperties(jobDto, job);
            Job savedJob = mongoTemplate.save(job, Job.COLLECTION);
            log.info("Job created successfully: {}", savedJob);
            return ResponseEntity.ok(BaseResponse.success("Job created successfully", savedJob));
        } catch (Exception e) {
            log.error("Error creating job: {}", e.getMessage());
            return ResponseEntity.status(500).body(BaseResponse.error("Failed to create job", null));
        }
    }

    @Override
    public ResponseEntity<BaseResponse<List<Job>>> findAllJob(Locale locale) {
        try {
            List<Job> jobs = mongoTemplate.findAll(Job.class);
            log.info("Retrieved {} jobs", jobs.size());
            return ResponseEntity.ok(BaseResponse.success("Jobs retrieved successfully", jobs));
        } catch (Exception e) {
            log.error("Error retrieving jobs: {}", e.getMessage());
            return ResponseEntity.status(500).body(BaseResponse.error("Failed to retrieve jobs", null));
        }
    }

    @Override
    public ResponseEntity<BaseResponse<Job>> findJobById(Locale locale, String jobId) {
        try {
            Job job = mongoTemplate.findById(jobId, Job.class);
            if (job == null) {
                log.warn("Job not found with ID: {}", jobId);
                return ResponseEntity.status(404).body(BaseResponse.error("Job not found", null));
            }
            log.info("Job found: {}", job);
            return ResponseEntity.ok(BaseResponse.success("Job found", job));
        } catch (Exception e) {
            log.error("Error finding job by ID: {}", e.getMessage());
            return ResponseEntity.status(500).body(BaseResponse.error("Failed to find job", null));
        }
    }

    @Override
    public ResponseEntity<BaseResponse<Job>> updateJob(Locale locale, JobDto job) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse<Job>> deleteJob(Locale locale, String jobId) {
        if (jobId == null || jobId.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(BaseResponse.error("Job ID is required for deletion", null));
        }
        Query query = new Query(Criteria.where("_id").is(jobId));
        DeleteResult deleteResult = mongoTemplate.remove(query, Job.class);
        if (deleteResult.getDeletedCount() > 0) {
            return ResponseEntity.ok(BaseResponse.success("Job deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.error("Job not found with ID: " + jobId, null));
    }

}
