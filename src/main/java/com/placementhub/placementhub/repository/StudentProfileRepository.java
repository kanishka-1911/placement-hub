package com.placementhub.placementhub.repository;
import com.placementhub.placementhub.entity.StudentProfile;
import com.placementhub.placementhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentProfileRepository
        extends JpaRepository<StudentProfile, Long> {
    Optional<StudentProfile> findByUser(User user);
    boolean existsByRegisterNumber(String registerNumber);
}