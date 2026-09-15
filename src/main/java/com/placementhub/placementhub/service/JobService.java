package com.placementhub.placementhub.service;

import com.placementhub.placementhub.dto.JobRequest;
import com.placementhub.placementhub.entity.Company;
import com.placementhub.placementhub.entity.Job;
import com.placementhub.placementhub.repository.CompanyRepository;
import com.placementhub.placementhub.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobService(
            JobRepository jobRepository,
            CompanyRepository companyRepository) {

        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    public Job createJob(JobRequest request) {

        Company company = companyRepository
                .findById(request.getCompanyId())
                .orElseThrow(() ->
                        new RuntimeException("Company not found"));

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setMinimumCgpa(request.getMinimumCgpa());
        job.setMaximumBacklogs(request.getMaximumBacklogs());
        job.setApplicationDeadline(
                request.getApplicationDeadline()
        );
        job.setCompany(company);

        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {

        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));
    }
}