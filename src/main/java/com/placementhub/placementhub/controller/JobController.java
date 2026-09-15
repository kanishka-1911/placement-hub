package com.placementhub.placementhub.controller;

import com.placementhub.placementhub.dto.JobRequest;
import com.placementhub.placementhub.entity.Job;
import com.placementhub.placementhub.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(
            @RequestBody JobRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobService.createJob(request));
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {

        return ResponseEntity.ok(
                jobService.getAllJobs()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                jobService.getJobById(id)
        );
    }
}
