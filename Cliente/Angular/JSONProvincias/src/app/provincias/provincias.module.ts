import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaProvinciasComponent } from './lista-provincias/lista-provincias.component';
import { ProvinciasService } from './provincias.service';



@NgModule({
  declarations: [
    ListaProvinciasComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ListaProvinciasComponent
  ],
  providers: [
    ProvinciasService
  ]
})
export class ProvinciasModule { }
