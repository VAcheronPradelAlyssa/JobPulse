package com.jobpulse;

import com.jobpulse.model.JobApplication;
import com.jobpulse.model.JobApplication.Status;
import com.jobpulse.repository.JobApplicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(JobApplicationRepository repository) {
        return args -> {
            repository.save(JobApplication.builder()
                    .company("Microsoft")
                    .position("Software Engineer")
                    .status(Status.APPLIED)
                    .appliedDate(LocalDate.now().minusDays(10))
                    .build());
            repository.save(JobApplication.builder()
                    .company("Google")
                    .position("Backend Developer")
                    .status(Status.INTERVIEW)
                    .appliedDate(LocalDate.now().minusDays(5))
                    .build());
            repository.save(JobApplication.builder()
                    .company("Amazon")
                    .position("Cloud Architect")
                    .status(Status.REJECTED)
                    .appliedDate(LocalDate.now().minusDays(2))
                    .build());
        };
    }
}
