package com.placementhub.placementhub.dto;

import java.util.List;

public class JobEligibilityResponse {

    private Long jobId;
    private String jobTitle;
    private String companyName;
    private boolean eligible;
    private List<String> reasons;

    public JobEligibilityResponse(
            Long jobId,
            String jobTitle,
            String companyName,
            boolean eligible,
            List<String> reasons) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.eligible = eligible;
        this.reasons = reasons;
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

    public boolean isEligible() {
        return eligible;
    }

    public List<String> getReasons() {
        return reasons;
    }
}