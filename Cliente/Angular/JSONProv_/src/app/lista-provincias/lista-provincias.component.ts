import { KeyValue } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProvinService } from '../provin.service';


@Component({
  selector: 'app-lista-provincias',
  templateUrl: './lista-provincias.component.html',
  styleUrls: ['./lista-provincias.component.css']
})
export class ListaProvinciasComponent implements OnInit {

  listaProv:any=[];
  constructor(private provincias: ProvinService) { }
  ordenOriginal = (a: KeyValue<number,string>, b: KeyValue<number,string>): number => {
    return 0;
  }

  ngOnInit(): void {
    this.provincias.getProvincias().subscribe((response: any) => this.listaProv=response);
  }

  getArray():any {
    return Object.values(this.listaProv);
  }

}
