package com.alyssa.jobpulse.repository;

import com.alyssa.jobpulse.model.ApplicationStatus;
import com.alyssa.jobpulse.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStatus(ApplicationStatus status);
}
