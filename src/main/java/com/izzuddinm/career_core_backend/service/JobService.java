package com.izzuddinm.career_core_backend.service;

import com.izzuddinm.career_core_backend.base.response.BaseResponse;
import com.izzuddinm.career_core_backend.model.document.Job;
import com.izzuddinm.career_core_backend.model.dto.JobDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

public interface JobService {

    ResponseEntity<BaseResponse<Job>> createJob(Locale locale, JobDto job);
    ResponseEntity<BaseResponse<List<Job>>> findAllJob(Locale locale);
    ResponseEntity<BaseResponse<Job>> findJobById(Locale locale, String jobId);
}
