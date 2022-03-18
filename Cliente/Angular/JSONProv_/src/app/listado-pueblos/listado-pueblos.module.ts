import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ListaPueblosComponent } from '../lista-pueblos/lista-pueblos.component';


export const rutas: Routes = [
  {path: 'pueblos/:parametro', component: ListaPueblosComponent }
];


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(rutas)
  ],
  exports:[RouterModule]
})
export class ListadoPueblosModule { }
