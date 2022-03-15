import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProvinciasService {

  constructor(private http: HttpClient) { }

  public getProvincias$():any {
    return this.http.get("http://localhost:8084/ComoLlegar21/BuscaPoblaciones?provincias=todas");
  }

  public getProvincias_$(value?:any):any {
    return this.http.get("http://localhost:8084/ComoLlegar21/BuscaPoblaciones?provincias="+value);
  }
}
