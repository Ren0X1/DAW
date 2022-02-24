import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'tercer-componente',
  templateUrl: './tercer-componente.component.html',
  styleUrls: ['./tercer-componente.component.css']
})
export class TercerComponenteComponent implements OnInit {
  lado = 0;
  constructor() {}
  ngOnInit(): void {}

  cambiado = (v: number) => {
    this.lado = v;
  }
}
