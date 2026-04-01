package com.jobpulse.service;

import com.jobpulse.dto.JobApplicationRequestDTO;
import com.jobpulse.dto.JobApplicationResponseDTO;
import com.jobpulse.model.JobApplication;
import com.jobpulse.model.JobApplication.Status;
import com.jobpulse.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {
    private final JobApplicationRepository repository;

    @Override
    public JobApplicationResponseDTO createApplication(JobApplicationRequestDTO requestDTO) {
        JobApplication entity = JobApplication.builder()
                .company(requestDTO.getCompany())
                .position(requestDTO.getPosition())
                .status(requestDTO.getStatus())
                .appliedDate(requestDTO.getAppliedDate())
                .build();
        JobApplication saved = repository.save(entity);
        return toResponseDTO(saved);
    }

    @Override
    public List<JobApplicationResponseDTO> getAllApplications() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobApplicationResponseDTO updateStatus(Long id, Status status) {
        JobApplication entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        entity.setStatus(status);
        return toResponseDTO(repository.save(entity));
    }

    @Override
    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<JobApplicationResponseDTO> getApplicationsByStatus(Status status) {
        return repository.findByStatus(status).stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private JobApplicationResponseDTO toResponseDTO(JobApplication entity) {
        JobApplicationResponseDTO dto = new JobApplicationResponseDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
