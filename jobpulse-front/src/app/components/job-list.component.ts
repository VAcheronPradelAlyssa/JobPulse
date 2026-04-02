import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Job } from '../model/job.model';
import { JobApplicationService } from '../service/job-application.service';

@Component({
  selector: 'app-job-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './job-list.component.html',
  styleUrls: ['./job-list.component.scss']
})
export class JobListComponent implements OnInit {

  jobs: Job[] = [];
  statuses = ['APPLIED', 'INTERVIEW', 'REJECTED', 'ACCEPTED'];
  isLoading = false;
  errorMessage = '';
  successMessage = '';
  actionInProgress: Set<number> = new Set();

  constructor(private jobService: JobApplicationService) { }

  ngOnInit(): void {
    this.loadJobs();
  }

  /**
   * Load all job applications from API
   */
  loadJobs(): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';
    this.jobService.getJobs().subscribe({
      next: (data) => {
        this.jobs = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load job applications';
        console.error(err);
        this.isLoading = false;
      }
    });
  }

  /**
   * Add a new job to the list immediately
   */
  addJob(job: Job): void {
    this.jobs.unshift(job);
  }

  /**
   * Delete a job application
   */
  deleteJob(id: number | undefined): void {
    if (!id) return;

    if (confirm('Are you sure you want to delete this application?')) {
      this.actionInProgress.add(id);
      this.errorMessage = '';
      this.successMessage = '';

      this.jobService.deleteJob(id).subscribe({
        next: () => {
          this.jobs = this.jobs.filter(job => job.id !== id);
          this.successMessage = 'Job application deleted successfully';
          this.actionInProgress.delete(id);
          // Clear success message after 3 seconds
          setTimeout(() => {
            this.successMessage = '';
          }, 3000);
        },
        error: (err) => {
          this.errorMessage = 'Failed to delete job application';
          console.error(err);
          this.actionInProgress.delete(id);
        }
      });
    }
  }

  /**
   * Update job status
   */
  updateStatus(job: Job, newStatus: string): void {
    if (!job.id) return;

    this.actionInProgress.add(job.id);
    this.errorMessage = '';
    this.successMessage = '';

    this.jobService.updateStatus(job.id, newStatus).subscribe({
      next: (updatedJob) => {
        job.status = updatedJob.status;
        this.successMessage = `Status updated to ${newStatus}`;
        this.actionInProgress.delete(job.id!);
        // Clear success message after 3 seconds
        setTimeout(() => {
          this.successMessage = '';
        }, 3000);
      },
      error: (err) => {
        this.errorMessage = 'Failed to update job status';
        console.error(err);
        this.actionInProgress.delete(job.id!);
      }
    });
  }

  /**
   * Check if action is in progress for a specific job
   */
  isActionInProgress(id: number | undefined): boolean {
    return id ? this.actionInProgress.has(id) : false;
  }
}
