import {Component, OnInit} from '@angular/core';
import {CustomersService} from "./services/customers.service";
import {Customer} from "../models/Customer";
import {Table} from "primeng/table";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

  first = 0;
  rows = 10;
  totalRecords = 40;
  customers: Customer[] = [];

  constructor(private customerService: CustomersService) {
  }

  ngOnInit(): void {
    this.getAllCustomers();
  }


  getAllCustomers() {
    this.customerService.findAllCustomers().subscribe(data => {
      this.customers = data;
    });
  }

  searchForCustomers($event: any) {
    this.customerService.searchForCustomers($event.target.value).subscribe(data => {
      this.customers = data;
    });
  }

  next() {
    this.first = this.first + this.rows;
  }

  prev() {
    this.first = this.first - this.rows;
  }

  reset() {
    this.first = 0;
  }

  isLastPage(): boolean {
    return this.customers ? this.first === (this.customers.length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.customers ? this.first === 0 : true;
  }

  clear(table: Table) {
    table.clear();
  }
}
