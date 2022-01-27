import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";
import {Customer} from "../../models/Customer";

const baseUrl = environment.baseUrl;
const getCustomerUrl = baseUrl + environment.apiUrl.getAllCustomers;

@Injectable({
  providedIn: 'root'
})
export class CustomersService {

  private httpOptions: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private httpClient: HttpClient) {
  }

  findAllCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(getCustomerUrl, {headers: this.httpOptions})
  }
}
