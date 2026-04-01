package com.jobpulse.service;

import com.jobpulse.dto.JobApplicationRequestDTO;
import com.jobpulse.dto.JobApplicationResponseDTO;
import com.jobpulse.model.JobApplication;
import com.jobpulse.model.JobApplication.Status;

import java.util.List;

public interface JobApplicationService {
    JobApplicationResponseDTO createApplication(JobApplicationRequestDTO requestDTO);
    List<JobApplicationResponseDTO> getAllApplications();
    JobApplicationResponseDTO updateStatus(Long id, Status status);
    void deleteApplication(Long id);
    List<JobApplicationResponseDTO> getApplicationsByStatus(Status status);
}
