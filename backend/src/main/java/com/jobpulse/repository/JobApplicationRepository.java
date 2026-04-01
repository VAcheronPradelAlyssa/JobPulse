package com.jobpulse.repository;

import com.jobpulse.model.JobApplication;
import com.jobpulse.model.JobApplication.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByStatus(Status status);
}
