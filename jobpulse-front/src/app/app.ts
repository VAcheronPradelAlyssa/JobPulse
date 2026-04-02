import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { JobListComponent } from './components/job-list.component';
import { JobFormComponent } from './components/job-form.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HttpClientModule, JobListComponent, JobFormComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('jobpulse-front');
}
