import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'primer-componente',
  templateUrl: './componente-primero.component.html',
  styleUrls: ['./componente-primero.component.css']
})
export class ComponentePrimeroComponent implements OnInit {

    dato = 'Creando el primer componente en Angular 13'

    constructor() {
        console.log(this.dato)
    }
    
    ngOnInit(): void {
        console.log('init')
    }

}
