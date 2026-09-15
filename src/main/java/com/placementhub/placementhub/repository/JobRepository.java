package com.placementhub.placementhub.repository;
import com.placementhub.placementhub.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JobRepository
        extends JpaRepository<Job, Long> {

    List<Job> findByCompanyId(Long companyId);
}
