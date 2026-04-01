package com.jobpulse.dto;

import com.jobpulse.model.JobApplication.Status;
import lombok.Data;
import java.time.LocalDate;

@Data
public class JobApplicationResponseDTO {
    private Long id;
    private String company;
    private String position;
    private Status status;
    private LocalDate appliedDate;
}
