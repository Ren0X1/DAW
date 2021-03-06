import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private http: HttpClient) { }

  getUsuario$():any {
    return this.http.get("https://jsonplaceholder.typicode.com/users");
  }
}
