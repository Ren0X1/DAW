import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProvinService {

  constructor(private http:HttpClient) { 

  }

  getProvincias():any{
    return this.http.get("http://localhost:8084/ExamenAngular/ProvPueblos");
  }

  getPueblos(idProvincia: string):any{
    return this.http.get("http://localhost:8084/ExamenAngular/ProvPueblos?provincia="+idProvincia);
  }

}
