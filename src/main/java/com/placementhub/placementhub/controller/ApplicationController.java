package com.placementhub.placementhub.controller;

import com.placementhub.placementhub.dto.ApplicationRequest;
import com.placementhub.placementhub.dto.ApplicationResponse;
import com.placementhub.placementhub.service.ApplicationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(
            ApplicationService applicationService) {

        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> apply(
            @RequestBody ApplicationRequest request,
            Authentication authentication) {

        ApplicationResponse response =
                applicationService.apply(
                        authentication.getName(),
                        request.getJobId()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>>
            getMyApplications(
                    Authentication authentication) {

        return ResponseEntity.ok(
                applicationService.getMyApplications(
                        authentication.getName()
                )
        );
    }
}