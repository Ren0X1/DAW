import { ListaProvinciasComponent } from './provincias/lista-provincias/lista-provincias.component';
import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { ListaPueblosComponent } from './provincias/lista-pueblos/lista-pueblos.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'JSONProvincias';
}
