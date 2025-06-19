import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ApiService } from '../../core/api.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  standalone: true,
  imports: [CommonModule, MatCardModule, MatProgressSpinnerModule]
})
export class DashboardComponent {
  stats: any = null;
  loading = true;
  error = '';

  constructor(private api: ApiService) {
    this.api.get<any>('/dashboard/summary').subscribe({
      next: (data) => { this.stats = data; this.loading = false; },
      error: (err) => { this.error = 'Erreur lors du chargement'; this.loading = false; }
    });
  }
}
