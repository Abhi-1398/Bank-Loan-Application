import { ChangeDetectionStrategy, Component, signal } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { merge } from 'rxjs';
import { NgIf } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { transition } from '@angular/animations';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-customer',
  standalone: true,
  imports: [
    MatButtonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    FormsModule, 
    ReactiveFormsModule, 
    NgIf
  ],
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'],
  animations :[transition],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AddCustomerComponent {
  readonly name = new FormControl('', [Validators.required]);
  readonly emailAddress = new FormControl('', [Validators.required, Validators.email]);
  readonly mobileNumber = new FormControl('', [Validators.required, Validators.pattern(/^\d{10}$/)]);
  readonly address = new FormControl('', [Validators.required]);

  errorMessage = signal('');

  constructor(private router : Router ,private http: HttpClient, private snackBar: MatSnackBar) {
    merge(
      this.name.statusChanges, this.name.valueChanges,
      this.emailAddress.statusChanges, this.emailAddress.valueChanges,
      this.mobileNumber.statusChanges, this.mobileNumber.valueChanges,
      this.address.statusChanges, this.address.valueChanges
    )
    .pipe(takeUntilDestroyed())
    .subscribe(() => this.updateErrorMessage());
  }

  updateErrorMessage() {
    if (this.name.invalid) {
      this.errorMessage.set('You must enter a name');
    } else if (this.emailAddress.hasError('required')) {
      this.errorMessage.set('You must enter a value');
    } else if (this.emailAddress.hasError('emailAddress')) {
      this.errorMessage.set('Not a valid emailAddress');
    } else if (this.mobileNumber.hasError('required')) {
      this.errorMessage.set('You must enter a mobile number');
    } else if (this.mobileNumber.hasError('pattern')) {
      this.errorMessage.set('Not a valid mobile number');
    } else if (this.address.hasError('required')) {
      this.errorMessage.set('You must enter an address');
    } else {
      this.errorMessage.set('');
    }
  }

  createCustomer() {
    if (this.name.valid && this.emailAddress.valid && this.mobileNumber.valid && this.address.valid) {
      const customerData = {
        name: this.name.value,
        emailAddress: this.emailAddress.value,
        mobileNumber: this.mobileNumber.value,
        address: this.address.value
      };
      this.http.post('http://localhost:8005/api/addCustomer', customerData)
        .subscribe({
          next: (response) => {
            this.snackBar.open('Customer created successfully', 'Close', {
              duration: 3000,
            });
            this.resetForm();
            this.router.navigate(["/dashboard/search"]);
          },
          error: (error) => {
            this.snackBar.open('Error creating customer', 'Close', {
              duration: 3000,
            });
            console.error('Error creating customer', error);
          }
        });
    }
  }

  resetForm() {
    this.name.reset();
    this.emailAddress.reset();
    this.mobileNumber.reset();
    this.address.reset();
  }
}
