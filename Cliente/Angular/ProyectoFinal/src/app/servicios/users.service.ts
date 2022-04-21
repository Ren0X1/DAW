import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private http: HttpClient) { }

  getUsuario$():any {
    return this.http.get("http://dummy.restapiexample.com/api/v1/employees");
  }

  getUsuario(v:string):any {
    return this.http.get("http://dummy.restapiexample.com/api/v1/employee/"+v);
  }
}
