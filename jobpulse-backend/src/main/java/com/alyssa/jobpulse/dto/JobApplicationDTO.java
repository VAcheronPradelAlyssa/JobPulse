package com.alyssa.jobpulse.dto;

import com.alyssa.jobpulse.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {

    @NotBlank(message = "Company name is required")
    private String company;

    @NotBlank(message = "Position is required")
    private String position;

    private ApplicationStatus status;

    @NotNull(message = "Applied date is required")
    private LocalDate appliedDate;
}
