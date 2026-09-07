package com.placementhub.placementhub.dto;

public class StudentProfileResponse {

    private Long id;
    private String email;
    private String fullName;
    private String registerNumber;
    private String department;
    private Double cgpa;
    private Integer graduationYear;
    private Integer activeBacklogs;

    public StudentProfileResponse(
            Long id,
            String email,
            String fullName,
            String registerNumber,
            String department,
            Double cgpa,
            Integer graduationYear,
            Integer activeBacklogs) {

        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.registerNumber = registerNumber;
        this.department = department;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
        this.activeBacklogs = activeBacklogs;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
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

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public Integer getActiveBacklogs() {
        return activeBacklogs;
    }
}