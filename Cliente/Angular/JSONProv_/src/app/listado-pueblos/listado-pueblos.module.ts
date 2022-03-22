import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ListaPueblosComponent } from '../lista-pueblos/lista-pueblos.component';
import { ModificaProvComponent } from '../modifica-prov/modifica-prov.component';


export const rutas: Routes = [
  {path: 'pueblos/:parametro', component: ListaPueblosComponent },
  {path: 'modificar/:parametro', component: ModificaProvComponent }
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
