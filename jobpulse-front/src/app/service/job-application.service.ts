import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Job } from '../model/job.model';

@Injectable({
  providedIn: 'root'
})
export class JobApplicationService {

  private apiUrl = 'http://localhost:8080/api/applications';

  constructor(private http: HttpClient) { }

  /**
   * Get all job applications
   */
  getJobs(): Observable<Job[]> {
    return this.http.get<Job[]>(this.apiUrl);
  }

  /**
   * Create a new job application
   */
  createJob(job: Job): Observable<Job> {
    return this.http.post<Job>(this.apiUrl, job);
  }

  /**
   * Delete a job application by id
   */
  deleteJob(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  /**
   * Update the status of a job application
   */
  updateStatus(id: number, status: string): Observable<Job> {
    return this.http.put<Job>(`${this.apiUrl}/${id}/status`, { status });
  }

  /**
   * Filter job applications by status
   */
  filterByStatus(status: string): Observable<Job[]> {
    return this.http.get<Job[]>(`${this.apiUrl}/status/${status}`);
  }
}
