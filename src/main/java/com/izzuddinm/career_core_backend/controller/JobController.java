package com.izzuddinm.career_core_backend.controller;

import com.izzuddinm.career_core_backend.base.controller.BaseController;
import com.izzuddinm.career_core_backend.base.response.BaseResponse;
import com.izzuddinm.career_core_backend.model.document.Job;
import com.izzuddinm.career_core_backend.model.dto.JobDto;
import com.izzuddinm.career_core_backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(BaseController.PATH)
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * Create a new job posting.
     *
     * @param locale Locale information.
     * @param jobDto DTO containing job details.
     * @return ResponseEntity with status and message.
     */
    @PostMapping
    public ResponseEntity<BaseResponse<Job>> createJob(Locale locale, @Valid @RequestBody JobDto jobDto) {
        return jobService.createJob(locale, jobDto);
    }

    /**
     * Get all job postings.
     *
     * @param locale Locale information.
     * @return ResponseEntity with the list of jobs.
     */
    @GetMapping
    public ResponseEntity<BaseResponse<List<Job>>> findAll(Locale locale) {
        return jobService.findAllJob(locale);
    }

    /**
     * Get details of a specific job posting by ID.
     *
     * @param locale Locale information.
     * @param jobId ID of the job.
     * @return ResponseEntity with job details.
     */
    @GetMapping("/{jobId}")
    public ResponseEntity<BaseResponse<Job>> findJobById(Locale locale, @PathVariable String jobId) {
        return jobService.findJobById(locale, jobId);
    }

    /**
     * Delete of a specific job posting by ID.
     *
     * @param locale Locale information.
     * @param jobId ID of the job.
     * @return ResponseEntity with job details.
     */
    @DeleteMapping("/{jobId}")
    public ResponseEntity<BaseResponse<Job>> deleteJobById(Locale locale, @PathVariable String jobId) {
        return jobService.deleteJob(locale, jobId);
    }
}
