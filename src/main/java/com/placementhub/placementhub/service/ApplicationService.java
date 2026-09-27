package com.placementhub.placementhub.service;

import com.placementhub.placementhub.dto.ApplicationResponse;
import com.placementhub.placementhub.entity.Application;
import com.placementhub.placementhub.entity.Job;
import com.placementhub.placementhub.entity.StudentProfile;
import com.placementhub.placementhub.entity.User;
import com.placementhub.placementhub.repository.ApplicationRepository;
import com.placementhub.placementhub.repository.JobRepository;
import com.placementhub.placementhub.repository.StudentProfileRepository;
import com.placementhub.placementhub.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;
    private final EligibilityService eligibilityService;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository,
            StudentProfileRepository studentProfileRepository,
            UserRepository userRepository,
            EligibilityService eligibilityService) {

        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
        this.eligibilityService = eligibilityService;
    }

    public ApplicationResponse apply(
            String email,
            Long jobId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile student =
                studentProfileRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        // 1. Check duplicate application
        if (applicationRepository
                .existsByStudentIdAndJobId(
                        student.getId(),
                        jobId)) {

            throw new RuntimeException(
                    "You have already applied for this job");
        }

        // 2. Check application deadline
        if (LocalDate.now()
                .isAfter(job.getApplicationDeadline())) {

            throw new RuntimeException(
                    "Application deadline has passed");
        }

        // 3. Check eligibility
        var eligibility =
                eligibilityService.checkEligibility(
                        email,
                        jobId);

        if (!eligibility.isEligible()) {

            throw new RuntimeException(
                    "You are not eligible for this job: "
                    + String.join(
                            "; ",
                            eligibility.getReasons()
                    )
            );
        }

        // 4. Create application
        Application application =
                new Application();

        application.setStudent(student);
        application.setJob(job);
        application.setAppliedAt(
                LocalDateTime.now()
        );
        application.setStatus("APPLIED");

        Application saved =
                applicationRepository.save(application);

        return convertToResponse(saved);
    }

    public List<ApplicationResponse> getMyApplications(
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        StudentProfile student =
                studentProfileRepository.findByUser(user)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student profile not found"));

        return applicationRepository
                .findByStudent(student)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private ApplicationResponse convertToResponse(
            Application application) {

        return new ApplicationResponse(
                application.getId(),
                application.getJob().getId(),
                application.getJob().getTitle(),
                application.getJob()
                        .getCompany()
                        .getName(),
                application.getAppliedAt(),
                application.getStatus()
        );
    }
}