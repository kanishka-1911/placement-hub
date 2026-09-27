package com.placementhub.placementhub.repository;

import com.placementhub.placementhub.entity.Application;
import com.placementhub.placementhub.entity.StudentProfile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository
        extends JpaRepository<Application, Long> {

    boolean existsByStudentIdAndJobId(
            Long studentId,
            Long jobId
    );

    List<Application> findByStudent(StudentProfile student);
}