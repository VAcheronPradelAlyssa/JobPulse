import { Component, Output, EventEmitter, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-job-filter',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './job-filter.component.html',
  styleUrls: ['./job-filter.component.scss']
})
export class JobFilterComponent {

  statuses = ['ALL', 'APPLIED', 'INTERVIEW', 'REJECTED', 'ACCEPTED'];
  selectedStatus = 'ALL';
  isLoading = false;

  @Input() isActionInProgress = false;
  @Output() filterChanged = new EventEmitter<string>();

  /**
   * Handle filter selection
   */
  selectFilter(status: string): void {
    this.selectedStatus = status;
    this.filterChanged.emit(status);
  }
}
