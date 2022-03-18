import { KeyValue } from '@angular/common';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'listaObj',
  templateUrl: './listado-obj.component.html',
  styleUrls: ['./listado-obj.component.css']
})
export class ListadoObjComponent implements OnInit {
  ordenOriginal = (a: KeyValue<number,string>, b: KeyValue<number,string>): number => {
    return 0;
  }
  @Input()
  objProvin: any;

  constructor() { }

  ngOnInit(): void {
  }

}
