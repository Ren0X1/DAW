import { KeyValue } from '@angular/common';
import { Component } from '@angular/core';
import { UsuariosService } from './usuarios.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'JSON';
  listaUsuarios:any[]=[];
  ordenOriginal = (a: KeyValue<number,string>, b: KeyValue<number,string>): number => {
    return 0;
  }
  constructor(private usuarios:UsuariosService) {}
  ngOnInit() {
    this.usuarios.getUsuario$().subscribe({
      next: (personas: any[]) => this.listaUsuarios = personas,
      error: (error: any) => console.log(error)
    })
  }

  isObject(v:any):boolean {
    if(typeof v == 'object') {
      return true
    }
    return false
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
