package com.placementhub.placementhub.dto;

import java.time.LocalDateTime;

public class AdminApplicationResponse {

    private Long applicationId;
    private Long jobId;
    private String jobTitle;
    private Long studentId;
    private String studentName;
    private String registerNumber;
    private String department;
    private Double cgpa;
    private String email;
    private LocalDateTime appliedAt;
    private String status;
    public AdminApplicationResponse(
            Long applicationId,
            Long jobId,
            String jobTitle,
            Long studentId,
            String studentName,
            String registerNumber,
            String department,
            Double cgpa,
            String email,
            LocalDateTime appliedAt,
            String status) {

        this.applicationId = applicationId;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.studentId = studentId;
        this.studentName = studentName;
        this.registerNumber = registerNumber;
        this.department = department;
        this.cgpa = cgpa;
        this.email = email;
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

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public String getDepartment() {
        return department;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public String getStatus() {
        return status;
    }
}