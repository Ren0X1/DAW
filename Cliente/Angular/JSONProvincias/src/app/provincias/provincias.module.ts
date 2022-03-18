import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListaProvinciasComponent } from './lista-provincias/lista-provincias.component';
import { ProvinciasService } from './provincias.service';
import { HttpClientModule } from '@angular/common/http';
import { ListaPueblosComponent } from './lista-pueblos/lista-pueblos.component';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    ListaProvinciasComponent,
    ListaPueblosComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule
  ],
  exports: [
    ListaProvinciasComponent,
    ListaPueblosComponent,
    HttpClientModule
  ],
  providers: [
    ProvinciasService
  ]
})
export class ProvinciasModule { }
