import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CustomersComponent} from './customers/customers.component';
import {CustomersModule} from "./customers/customers.module";
import {TableModule} from "primeng/table";
import {HttpClientModule} from "@angular/common/http";
import {CardModule} from "primeng/card";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    CustomersModule,
    TableModule,
    CardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
