package com.placementhub.placementhub.service;

import com.placementhub.placementhub.dto.EligibilityResponse;
import com.placementhub.placementhub.dto.JobEligibilityResponse;
import com.placementhub.placementhub.entity.Job;
import com.placementhub.placementhub.entity.StudentProfile;
import com.placementhub.placementhub.entity.User;
import com.placementhub.placementhub.repository.JobRepository;
import com.placementhub.placementhub.repository.StudentProfileRepository;
import com.placementhub.placementhub.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EligibilityService {

    private final JobRepository jobRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public EligibilityService(
            JobRepository jobRepository,
            StudentProfileRepository studentProfileRepository,
            UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    public EligibilityResponse checkEligibility(
            String email,
            Long jobId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile student = studentProfileRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        List<String> reasons = new ArrayList<>();

        // 1. CGPA check
        if (student.getCgpa() == null ||
                student.getCgpa() < job.getMinimumCgpa()) {

            reasons.add(
                    "CGPA requirement: " +
                    job.getMinimumCgpa() +
                    " → You have " +
                    student.getCgpa()
            );
        }

        // 2. Backlog check
        if (student.getActiveBacklogs() == null ||
                student.getActiveBacklogs() > job.getMaximumBacklogs()) {

            reasons.add(
                    "Maximum backlogs allowed: " +
                    job.getMaximumBacklogs() +
                    " → You have " +
                    student.getActiveBacklogs()
            );
        }

        // 3. Graduation year check
        if (student.getGraduationYear() == null ||
                !student.getGraduationYear()
                        .equals(job.getEligibleGraduationYear())) {

            reasons.add(
                    "Graduation year required: " +
                    job.getEligibleGraduationYear() +
                    " → You graduate in " +
                    student.getGraduationYear()
            );
        }

        // 4. Department check
        Set<String> allowedDepartments =
                job.getAllowedDepartments();

        boolean departmentEligible =
                allowedDepartments != null &&
                allowedDepartments.stream()
                        .anyMatch(department ->
                                department.equalsIgnoreCase(
                                        student.getDepartment()));

        if (!departmentEligible) {

            reasons.add(
                    "Department not eligible: " +
                    student.getDepartment()
            );
        }

        // 5. Skills check
        Set<String> studentSkills =
                student.getSkills() == null
                        ? Set.of()
                        : student.getSkills();

        Set<String> normalizedStudentSkills =
                studentSkills.stream()
                        .map(String::toLowerCase)
                        .collect(Collectors.toSet());

        Set<String> requiredSkills =
                job.getRequiredSkills() == null
                        ? Set.of()
                        : job.getRequiredSkills();

        List<String> missingSkills =
                requiredSkills.stream()
                        .filter(skill ->
                                !normalizedStudentSkills.contains(
                                        skill.toLowerCase()))
                        .collect(Collectors.toList());

        if (!missingSkills.isEmpty()) {

            reasons.add(
                    "Missing required skills: " +
                    String.join(", ", missingSkills)
            );
        }

        boolean eligible = reasons.isEmpty();

        return new EligibilityResponse(
                job.getId(),
                job.getTitle(),
                eligible,
                reasons
        );
    }
    public List<JobEligibilityResponse> getAllJobEligibility(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile student = studentProfileRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Student profile not found"));

        List<Job> jobs = jobRepository.findAll();

        List<JobEligibilityResponse> results = new ArrayList<>();

        for (Job job : jobs) {

            List<String> reasons = new ArrayList<>();

            // CGPA
            if (student.getCgpa() == null ||
                    student.getCgpa() < job.getMinimumCgpa()) {

                reasons.add(
                        "Minimum CGPA required: " +
                        job.getMinimumCgpa() +
                        " → You have " +
                        student.getCgpa()
                );
            }

            // Backlogs
            if (student.getActiveBacklogs() == null ||
                    student.getActiveBacklogs() >
                            job.getMaximumBacklogs()) {

                reasons.add(
                        "Maximum backlogs allowed: " +
                        job.getMaximumBacklogs() +
                        " → You have " +
                        student.getActiveBacklogs()
                );
            }

            // Graduation year
            if (student.getGraduationYear() == null ||
                    !student.getGraduationYear()
                            .equals(job.getEligibleGraduationYear())) {

                reasons.add(
                        "Graduation year required: " +
                        job.getEligibleGraduationYear() +
                        " → You graduate in " +
                        student.getGraduationYear()
                );
            }

            // Department
            boolean departmentEligible =
                    job.getAllowedDepartments() != null &&
                    job.getAllowedDepartments()
                            .stream()
                            .anyMatch(department ->
                                    department.equalsIgnoreCase(
                                            student.getDepartment()));

            if (!departmentEligible) {

                reasons.add(
                        "Department not eligible: " +
                        student.getDepartment()
                );
            }

            // Skills
            Set<String> studentSkills =
                    student.getSkills() == null
                            ? Set.of()
                            : student.getSkills();

            Set<String> normalizedStudentSkills =
                    studentSkills.stream()
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet());

            Set<String> requiredSkills =
                    job.getRequiredSkills() == null
                            ? Set.of()
                            : job.getRequiredSkills();

            List<String> missingSkills =
                    requiredSkills.stream()
                            .filter(skill ->
                                    !normalizedStudentSkills.contains(
                                            skill.toLowerCase()))
                            .collect(Collectors.toList());

            if (!missingSkills.isEmpty()) {

                reasons.add(
                        "Missing required skills: " +
                        String.join(", ", missingSkills)
                );
            }

            boolean eligible = reasons.isEmpty();

            results.add(
                    new JobEligibilityResponse(
                            job.getId(),
                            job.getTitle(),
                            job.getCompany().getName(),
                            eligible,
                            reasons
                    )
            );
        }

        return results;
    }
}