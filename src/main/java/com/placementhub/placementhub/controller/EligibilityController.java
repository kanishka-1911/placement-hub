package com.placementhub.placementhub.controller;

import com.placementhub.placementhub.dto.EligibilityResponse;
import com.placementhub.placementhub.service.EligibilityService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class EligibilityController {

    private final EligibilityService eligibilityService;

    public EligibilityController(
            EligibilityService eligibilityService) {

        this.eligibilityService = eligibilityService;
    }

    @GetMapping("/jobs/{jobId}/eligibility")
    public ResponseEntity<EligibilityResponse> checkEligibility(
            @PathVariable Long jobId,
            Authentication authentication) {

        EligibilityResponse response =
                eligibilityService.checkEligibility(
                        authentication.getName(),
                        jobId);

        return ResponseEntity.ok(response);
    }
}