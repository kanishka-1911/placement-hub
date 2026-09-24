package com.placementhub.placementhub.dto;

import java.util.List;

public class EligibilityResponse {

    private Long jobId;
    private String jobTitle;
    private boolean eligible;
    private List<String> reasons;

    public EligibilityResponse(
            Long jobId,
            String jobTitle,
            boolean eligible,
            List<String> reasons) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.eligible = eligible;
        this.reasons = reasons;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean isEligible() {
        return eligible;
    }

    public List<String> getReasons() {
        return reasons;
    }
}