import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ProvinService } from '../provin.service';

@Component({
  selector: 'app-lista-pueblos',
  templateUrl: './lista-pueblos.component.html',
  styleUrls: ['./lista-pueblos.component.css']
})
export class ListaPueblosComponent implements OnInit {

  idProvincia:any;

  pueblos: any[] = [];

  constructor(private rutaActiva: ActivatedRoute,private service:ProvinService) { 
   
  }

  ngOnInit(): void {
    this.idProvincia=this.rutaActiva.snapshot.paramMap.get("parametro");
    if (window.localStorage.getItem(this.idProvincia) == null) {
      this.service.getPueblos(this.idProvincia).subscribe((response: any) =>this.pueblos=response);
      window.localStorage.setItem(this.idProvincia, JSON.stringify(this.pueblos))
    } else {
      this.pueblos = window.localStorage.getItem(JSON.parse(this.idProvincia)) || this.service.getPueblos(this.idProvincia).subscribe((response: any) =>this.pueblos=response);
    }
    this.rutaActiva.params.subscribe(
      params => {
         this.idProvincia=params['parametro'];
         this.service.getPueblos(this.idProvincia).subscribe((response: any) =>this.pueblos=response);
      }
    );
  }

}
