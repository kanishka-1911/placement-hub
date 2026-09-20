package com.placementhub.placementhub.dto;
import java.util.Set;
public class StudentProfileResponse {

    private Long id;
    private String email;
    private String fullName;
    private String registerNumber;
    private String department;
    private Double cgpa;
    private Integer graduationYear;
    private Integer activeBacklogs;
    private Set<String> skills;
    public StudentProfileResponse(
            Long id,
            String email,
            String fullName,
            String registerNumber,
            String department,
            Double cgpa,
            Integer graduationYear,
            Integer activeBacklogs,
            Set<String> skills) {

        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.registerNumber = registerNumber;
        this.department = department;
        this.cgpa = cgpa;
        this.graduationYear = graduationYear;
        this.activeBacklogs = activeBacklogs;
        this.skills = skills;
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
    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}