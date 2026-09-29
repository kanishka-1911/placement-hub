package com.placementhub.placementhub.controller;

import com.placementhub.placementhub.dto.AdminApplicationResponse;
import com.placementhub.placementhub.dto.ApplicationStatusRequest;
import com.placementhub.placementhub.service.ApplicationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/applications")
public class AdminApplicationController {

    private final ApplicationService applicationService;

    public AdminApplicationController(
            ApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<AdminApplicationResponse>>
            getApplicationsForJob(
                    @PathVariable Long jobId) {

        return ResponseEntity.ok(
                applicationService
                        .getApplicationsForJob(jobId)
        );
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<AdminApplicationResponse>
            updateStatus(
                    @PathVariable Long applicationId,
                    @RequestBody ApplicationStatusRequest request) {

        return ResponseEntity.ok(
                applicationService
                        .updateApplicationStatus(
                                applicationId,
                                request.getStatus()
                        )
        );
    }
}