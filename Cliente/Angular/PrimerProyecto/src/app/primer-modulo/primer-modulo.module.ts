import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SegundoComponenteComponent } from './segundo-componente/segundo-componente.component';
import { TercerComponenteComponent } from './tercer-componente/tercer-componente.component';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    SegundoComponenteComponent,
    TercerComponenteComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    SegundoComponenteComponent,
    TercerComponenteComponent
  ]
})
export class PrimerModuloModule { }
