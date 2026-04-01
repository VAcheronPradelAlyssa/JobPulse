package com.jobpulse.dto;

import com.jobpulse.model.JobApplication.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class JobApplicationRequestDTO {
    @NotBlank
    private String company;

    @NotBlank
    private String position;

    @NotNull
    private Status status;

    @NotNull
    private LocalDate appliedDate;
}
