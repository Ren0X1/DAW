import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lista-pueblos',
  templateUrl: './lista-pueblos.component.html',
  styleUrls: ['./lista-pueblos.component.scss']
})
export class ListaPueblosComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.provincias.getProvincias$().subscribe({
      next: (prov: any) => this.listaProvincias = prov,
      error: (error: any) => console.log(error)
    })
  }

  getPueblos()

}
