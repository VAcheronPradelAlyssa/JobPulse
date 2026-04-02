package com.alyssa.jobpulse.service;

import com.alyssa.jobpulse.model.ApplicationStatus;
import com.alyssa.jobpulse.model.JobApplication;
import com.alyssa.jobpulse.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JobApplicationService {

    private final JobApplicationRepository repository;

    /**
     * Get all job applications
     */
    public List<JobApplication> getAll() {
        return repository.findAll();
    }

    /**
     * Create a new job application
     */
    public JobApplication create(JobApplication jobApplication) {
        return repository.save(jobApplication);
    }

    /**
     * Find job applications by status
     */
    public List<JobApplication> findByStatus(ApplicationStatus status) {
        return repository.findByStatus(status);
    }

    /**
     * Update the status of a job application
     */
    public JobApplication updateStatus(Long id, ApplicationStatus status) {
        JobApplication jobApplication = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job application not found with id: " + id));
        jobApplication.setStatus(status);
        return repository.save(jobApplication);
    }

    /**
     * Delete a job application by id
     */
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Job application not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
