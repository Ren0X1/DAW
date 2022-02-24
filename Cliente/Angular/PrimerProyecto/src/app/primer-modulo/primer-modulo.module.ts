import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SegundoComponenteComponent } from './segundo-componente/segundo-componente.component';



@NgModule({
  declarations: [
    SegundoComponenteComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    SegundoComponenteComponent
  ]
})
export class PrimerModuloModule { }
