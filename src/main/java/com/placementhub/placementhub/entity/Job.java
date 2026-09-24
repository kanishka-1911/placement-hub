package com.placementhub.placementhub.entity;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer eligibleGraduationYear;
    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Double minimumCgpa;

    @Column(nullable = false)
    private Integer maximumBacklogs;

    @Column(nullable = false)
    private LocalDate applicationDeadline;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    @ElementCollection
    @CollectionTable(
        name = "job_required_skills",joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "skill")
    private Set<String> requiredSkills = new HashSet<>();

    @ElementCollection
    @CollectionTable(
        name = "job_allowed_departments",
        joinColumns = @JoinColumn(name = "job_id")
    )
    @Column(name = "department")
    private Set<String> allowedDepartments = new HashSet<>();
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(Double minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }

    public Integer getMaximumBacklogs() {
        return maximumBacklogs;
    }

    public void setMaximumBacklogs(Integer maximumBacklogs) {
        this.maximumBacklogs = maximumBacklogs;
    }

    public LocalDate getApplicationDeadline() {
        return applicationDeadline;
    }

    public void setApplicationDeadline(LocalDate applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    public Integer getEligibleGraduationYear() {
        return eligibleGraduationYear;
    }

    public void setEligibleGraduationYear(Integer eligibleGraduationYear) {
        this.eligibleGraduationYear = eligibleGraduationYear;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Set<String> getAllowedDepartments() {
        return allowedDepartments;
    }

    public void setAllowedDepartments(Set<String> allowedDepartments) {
        this.allowedDepartments = allowedDepartments;
    }
}