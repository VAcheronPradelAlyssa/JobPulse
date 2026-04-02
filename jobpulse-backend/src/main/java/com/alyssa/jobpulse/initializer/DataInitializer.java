package com.alyssa.jobpulse.initializer;

import com.alyssa.jobpulse.model.ApplicationStatus;
import com.alyssa.jobpulse.model.JobApplication;
import com.alyssa.jobpulse.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JobApplicationRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if database is empty
        if (repository.count() == 0) {
            List<JobApplication> sampleJobs = Arrays.asList(
                    new JobApplication(
                            null,
                            "Google",
                            "Senior Software Engineer",
                            ApplicationStatus.INTERVIEW,
                            LocalDate.of(2026, 3, 15)
                    ),
                    new JobApplication(
                            null,
                            "Microsoft",
                            "Cloud Architect",
                            ApplicationStatus.APPLIED,
                            LocalDate.of(2026, 3, 20)
                    ),
                    new JobApplication(
                            null,
                            "Amazon",
                            "Backend Engineer",
                            ApplicationStatus.INTERVIEW,
                            LocalDate.of(2026, 2, 28)
                    ),
                    new JobApplication(
                            null,
                            "Meta",
                            "Full Stack Developer",
                            ApplicationStatus.REJECTED,
                            LocalDate.of(2026, 2, 10)
                    ),
                    new JobApplication(
                            null,
                            "Apple",
                            "iOS Developer",
                            ApplicationStatus.ACCEPTED,
                            LocalDate.of(2026, 1, 25)
                    ),
                    new JobApplication(
                            null,
                            "Netflix",
                            "DevOps Engineer",
                            ApplicationStatus.APPLIED,
                            LocalDate.of(2026, 3, 25)
                    ),
                    new JobApplication(
                            null,
                            "Tesla",
                            "Machine Learning Engineer",
                            ApplicationStatus.INTERVIEW,
                            LocalDate.of(2026, 3, 10)
                    ),
                    new JobApplication(
                            null,
                            "LinkedIn",
                            "Frontend Engineer",
                            ApplicationStatus.APPLIED,
                            LocalDate.of(2026, 3, 22)
                    ),
                    new JobApplication(
                            null,
                            "Spotify",
                            "Data Scientist",
                            ApplicationStatus.REJECTED,
                            LocalDate.of(2026, 3, 5)
                    ),
                    new JobApplication(
                            null,
                            "Adobe",
                            "Product Manager",
                            ApplicationStatus.ACCEPTED,
                            LocalDate.of(2026, 2, 14)
                    )
            );

            repository.saveAll(sampleJobs);
            System.out.println("✅ Sample job applications initialized successfully!");
        } else {
            System.out.println("⏭️  Database already contains data. Skipping initialization.");
        }
    }
}
