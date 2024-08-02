import { Component } from '@angular/core';
import { BankLogoComponent } from '../../assets/bank-logo/bank-logo.component';
import {MatButtonModule} from '@angular/material/button';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [BankLogoComponent,MatButtonModule,RouterLink],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

}
