import { KeyValue } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProvinciasService } from '../provincias.service';

@Component({
  selector: 'app-lista-provincias',
  templateUrl: './lista-provincias.component.html',
  styleUrls: ['./lista-provincias.component.scss']
})
export class ListaProvinciasComponent implements OnInit {

  title = 'JSON';
  listaProvincias:any={};
  ordenOriginal = (a: KeyValue<number,string>, b: KeyValue<number,string>): number => {
    return 0;
  }
  neededArray: any;
  constructor(private provincias:ProvinciasService) {}
  ngOnInit() {
    this.provincias.getProvincias$().subscribe({
      next: (prov: any) => this.listaProvincias = prov,
      error: (error: any) => console.log(error)
    })
  }

  isObject(v:any):boolean {
    if(typeof v == 'object') {
      return true
    }
    return false
  }

  getArray():any {
    return Object.values(this.listaProvincias);
  }

  convertir(v:any):string {
    let resultado:string = ""
    Object.keys(v).forEach(function(key) {
      if (typeof v[key] == 'object') {
        Object.keys(v[key]).forEach(function(k) {
          resultado += k+": "+v[key][k]+ '\n'
        })
      } else {
        resultado += key+": "+v[key]+ '\n'
      }
    })
    return resultado
  }

}
