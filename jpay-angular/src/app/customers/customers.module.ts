import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import {CustomersRouting} from "./customers.routing";



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(CustomersRouting),
  ]
})
export class CustomersModule { }
