import { KeyValue } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UsersService } from '../servicios/users.service';

const ICONO = `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
               </svg>`;

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public listaEmpleados: Array<any> = [];

  ordenOriginal = (a: KeyValue<number,string>, b: KeyValue<number,string>): number => {
    return 0;
  }

  constructor(private users: UsersService) { }

  ngOnInit(): void {
    this.users.getUsuario$().subscribe((response: { data: Object[]; }) => {
      this.listaEmpleados = response.data
    })
  }

  vacio(v:string):boolean {
    if (v.length == 0) {
      return true
    }
    return false
  }

}
