import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import {CustomersRouting} from "./customers.routing";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {CustomersComponent} from "./customers.component";
import {TableModule} from "primeng/table";
import {CardModule} from "primeng/card";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {ButtonModule} from "primeng/button";



@NgModule({
  declarations: [
    CustomersComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(CustomersRouting),
    TableModule,
    CardModule,
    InputTextModule,
    FormsModule,
    ButtonModule
  ]
})
export class CustomersModule { }
