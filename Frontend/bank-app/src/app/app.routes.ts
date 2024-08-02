import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ErrorComponent } from './error/error.component';
import { CloseAccountComponent } from '../close-account/close-account.component';
import { AddLoanComponent } from '../add-loan/add-loan.component';
import { AddCustomerComponent } from '../add-customer/add-customer.component';
import { SearchComponent } from '../search/search.component';

export const routes: Routes = [
    { path : "login" , component : LoginComponent},
    { 
        path : "dashboard",
        component : DashboardComponent,
        children : [
            { path : "search" , component : SearchComponent},
            { path : "addCustomer" , component : AddCustomerComponent},
            { path : "addLoan" , component : AddLoanComponent},
            { path : "closeAccount" , component : CloseAccountComponent},
        ]
    },
    { path : "error" , component : ErrorComponent },
    { path : "**", component : LoginComponent}
];
