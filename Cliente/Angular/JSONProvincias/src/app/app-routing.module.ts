import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaProvinciasComponent } from './provincias/lista-provincias/lista-provincias.component';
import { ListaPueblosComponent } from './provincias/lista-pueblos/lista-pueblos.component';

const routes: Routes = [
  { path: '', component: ListaProvinciasComponent },
  { path: 'pueblos', component: ListaPueblosComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
