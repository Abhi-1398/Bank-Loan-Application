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
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';

type Customer = {
  id : number,
  name : String,
  mobileNumber : String,
  Address : String,
  emailAddress : String
}

type loanTypeObject ={
  key : String,
  value : String
}
@Component({
  selector: 'app-add-loan',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [
    MatButtonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatSelectModule,
    FormsModule, 
    ReactiveFormsModule, 
    NgIf,
    MatDatepickerModule
  ],
  templateUrl: './add-loan.component.html',
  styleUrl: './add-loan.component.css',
  animations :[transition],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AddLoanComponent {
  readonly customer = new FormControl('', [Validators.required]);
  readonly loanType = new FormControl('', [Validators.required]);
  readonly loanAmount = new FormControl('', [Validators.required]);
  readonly loanDate = new FormControl('', [Validators.required]);
  interestRate  : number = 0; 
  customerList : Customer[] = [];
  errorMessage = signal('');
  loanTypes : loanTypeObject[] = [ 
                { key : "HOME_LOAN" , value : "Home Loan" },
                { key : "BUSINESS_LOAN" , value : "Business Loan"},
                { key : "PERSONAL_LOAN" , value : "Personal Loan"},
              ]; 

  minDate = new Date();

  constructor(private router : Router ,private http: HttpClient, private snackBar: MatSnackBar) {
    merge(
      this.customer.statusChanges, this.customer.valueChanges,
      this.loanType.statusChanges, this.loanType.valueChanges,
      this.loanAmount.statusChanges, this.loanAmount.valueChanges,
      this.loanDate.statusChanges, this.loanDate.valueChanges
    )
    .pipe(takeUntilDestroyed())
    .subscribe(() => this.updateErrorMessage());
    this.getCustomer();
  }

  private getLoanInterestRate = () : number => {
    console.log(this.loanType.value)
    if(this.loanType.value == "HOME_LOAN"){
      return 6.5;
    }else if(this.loanType.value == "BUSINESS_LOAN"){
      return 7.5;
    }
    return 5.5;
  }
  updateErrorMessage() {
    if (this.customer.invalid) {
      this.errorMessage.set('You must enter a name');
    }else if (this.loanAmount.hasError('required')) {
      this.errorMessage.set('You must enter a mobile number');
    } else if (this.loanAmount.hasError('required')) {
      this.errorMessage.set('Not a valid loan amount');
    } else if (this.loanDate.hasError('required')) {
      this.errorMessage.set('You must select an date');
    } else {
      this.errorMessage.set('');
    }
  }

  getCustomer(){
    this.http.get('http://localhost:8005/api/getCustomer')
      .subscribe({
        next: (response) => {
          this.customerList = response as Customer[];
        },
        error: (error) => {
          this.snackBar.open('Error retrieving customer data', 'Close', {
            duration: 3000,
          });
          console.error('Error retrieving customer data', error);
        }
      });
  }

  createLoan() {
    if (this.customer.valid &&  this.loanAmount.valid && this.loanDate.valid) {
      
      const loanData = {
        customer_id: this.customer.value,
        loanType: this.loanType.value,
        interest_rate: this.getLoanInterestRate(),
        loanAmount: this.loanAmount.value,
        reducedInterestBalance: this.loanAmount.value,
        loanDate: this.loanDate.value,
        isActive: true
      };
      console.log(loanData)
      this.http.post('http://localhost:8005/api/addLoanAccount', loanData)
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
    this.customer.reset();
    this.loanType.reset();
    this.loanAmount.reset();
    this.loanDate.reset();
  }
}
