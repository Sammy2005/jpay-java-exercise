import {Component, OnInit} from '@angular/core';
import {CustomersService} from "./services/customers.service";
import {Customer} from "../models/Customer";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {

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
}
