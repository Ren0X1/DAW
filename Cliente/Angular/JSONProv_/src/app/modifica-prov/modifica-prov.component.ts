import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProvinService } from '../provin.service';

@Component({
  selector: 'app-modifica-prov',
  templateUrl: './modifica-prov.component.html',
  styleUrls: ['./modifica-prov.component.css']
})
export class ModificaProvComponent implements OnInit {

  provincia:any;
  provincia_:string[]=[];

  constructor(private ruta: ActivatedRoute, private service: ProvinService) { }

  ngOnInit(): void {
    this.provincia = JSON.parse(this.ruta.snapshot.paramMap.get("parametro"))
  }

}
