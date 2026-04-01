package com.jobpulse.controller;

import com.jobpulse.dto.JobApplicationRequestDTO;
import com.jobpulse.dto.JobApplicationResponseDTO;
import com.jobpulse.model.JobApplication.Status;
import com.jobpulse.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
@CrossOrigin // Enable CORS for all origins (customize as needed)
public class JobApplicationController {
    private final JobApplicationService service;

    /**
     * Create a new job application
     */
    @PostMapping
    public ResponseEntity<JobApplicationResponseDTO> create(@Valid @RequestBody JobApplicationRequestDTO requestDTO) {
        return ResponseEntity.ok(service.createApplication(requestDTO));
    }

    /**
     * Get all job applications
     */
    @GetMapping
    public ResponseEntity<List<JobApplicationResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    /**
     * Update the status of a job application
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplicationResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam Status status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    /**
     * Delete a job application
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get applications by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplicationResponseDTO>> getByStatus(@PathVariable Status status) {
        return ResponseEntity.ok(service.getApplicationsByStatus(status));
    }
}
