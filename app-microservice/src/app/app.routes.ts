import { Routes } from '@angular/router';
import {ProductsComponent} from "./products/products.component";
import {CustomersComponent} from "./customers/customers.component";
import {BillsComponent} from "./bills/bills.component";
import {BillDetailsComponent} from "./bill-details/bill-details.component";

export const routes: Routes = [
  {path: 'products', component: ProductsComponent},
  {path: 'customers', component: CustomersComponent},
  {path: 'bills/:id', component: BillsComponent},
  {path: 'bill-details/:billId', component: BillDetailsComponent},
];
