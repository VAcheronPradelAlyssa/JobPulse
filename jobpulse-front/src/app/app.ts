import { Component, signal, ViewChild } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { JobListComponent } from './components/job-list.component';
import { JobFormComponent } from './components/job-form.component';
import { Job } from './model/job.model';

@Component({
  selector: 'app-root',
  imports: [HttpClientModule, JobListComponent, JobFormComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('jobpulse-front');

  @ViewChild(JobListComponent) jobListComponent!: JobListComponent;

  /**
   * Handle job created event and refresh the list
   */
  onJobCreated(job: Job): void {
    console.log('New job created:', job);
    // Reload the job list to show the new job
    if (this.jobListComponent) {
      this.jobListComponent.loadJobs();
    }
  }
}