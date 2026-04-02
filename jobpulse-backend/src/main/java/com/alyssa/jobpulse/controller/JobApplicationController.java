package com.alyssa.jobpulse.controller;

import com.alyssa.jobpulse.dto.JobApplicationDTO;
import com.alyssa.jobpulse.dto.UpdateStatusRequest;
import com.alyssa.jobpulse.model.ApplicationStatus;
import com.alyssa.jobpulse.model.JobApplication;
import com.alyssa.jobpulse.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class JobApplicationController {

    private final JobApplicationService service;

    /**
     * Get all job applications
     */
    @GetMapping
    public ResponseEntity<List<JobApplication>> getAll() {
        List<JobApplication> applications = service.getAll();
        return ResponseEntity.ok(applications);
    }

    /**
     * Create a new job application
     */
    @PostMapping
    public ResponseEntity<JobApplication> create(@Valid @RequestBody JobApplicationDTO dto) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setCompany(dto.getCompany());
        jobApplication.setPosition(dto.getPosition());
        jobApplication.setStatus(dto.getStatus() != null ? dto.getStatus() : ApplicationStatus.APPLIED);
        jobApplication.setAppliedDate(dto.getAppliedDate());

        JobApplication created = service.create(jobApplication);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Get job applications by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplication>> findByStatus(@PathVariable ApplicationStatus status) {
        List<JobApplication> applications = service.findByStatus(status);
        return ResponseEntity.ok(applications);
    }

    /**
     * Update the status of a job application
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request) {
        JobApplication updated = service.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete a job application
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
