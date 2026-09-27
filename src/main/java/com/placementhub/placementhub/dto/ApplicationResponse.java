package com.placementhub.placementhub.dto;

import java.time.LocalDateTime;

public class ApplicationResponse {

    private Long applicationId;
    private Long jobId;
    private String jobTitle;
    private String companyName;
    private LocalDateTime appliedAt;
    private String status;

    public ApplicationResponse(
            Long applicationId,
            Long jobId,
            String jobTitle,
            String companyName,
            LocalDateTime appliedAt,
            String status) {

        this.applicationId = applicationId;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.appliedAt = appliedAt;
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public String getStatus() {
        return status;
    }
}