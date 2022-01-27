import {Routes} from '@angular/router';
import {CustomersComponent} from "./customers.component";


export const CustomersRouting: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: '/customers'
      },
      {
        path: 'customers',
        component: CustomersComponent,
      }
    ]
  }
];

