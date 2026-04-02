import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Job } from '../model/job.model';
import { JobApplicationService } from '../service/job-application.service';

@Component({
  selector: 'app-job-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './job-form.component.html',
  styleUrls: ['./job-form.component.scss']
})
export class JobFormComponent implements OnInit {

  jobForm!: FormGroup;
  statuses = ['APPLIED', 'INTERVIEW', 'REJECTED', 'ACCEPTED'];
  isLoading = false;
  errorMessage = '';
  successMessage = '';

  @Output() jobCreated = new EventEmitter<Job>();

  constructor(
    private formBuilder: FormBuilder,
    private jobService: JobApplicationService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  /**
   * Initialize the reactive form
   */
  private initializeForm(): void {
    this.jobForm = this.formBuilder.group({
      company: ['', [Validators.required, Validators.minLength(2)]],
      position: ['', [Validators.required, Validators.minLength(2)]],
      status: ['APPLIED', Validators.required],
      appliedDate: [new Date().toISOString().split('T')[0], Validators.required]
    });
  }

  /**
   * Submit the form to create a new job application
   */
  onSubmit(): void {
    if (this.jobForm.invalid) {
      this.errorMessage = 'Please fill all required fields';
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    const jobData: Job = {
      company: this.jobForm.get('company')?.value,
      position: this.jobForm.get('position')?.value,
      status: this.jobForm.get('status')?.value,
      appliedDate: this.jobForm.get('appliedDate')?.value
    };

    this.jobService.createJob(jobData).subscribe({
      next: (response) => {
        this.successMessage = 'Job application created successfully!';
        this.jobCreated.emit(response);
        this.resetForm();
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Failed to create job application';
        console.error(err);
        this.isLoading = false;
      }
    });
  }

  /**
   * Reset the form to initial state
   */
  resetForm(): void {
    this.jobForm.reset({
      status: 'APPLIED',
      appliedDate: new Date().toISOString().split('T')[0]
    });
  }

  /**
   * Get form control for template
   */
  getControl(name: string) {
    return this.jobForm.get(name);
  }
}
