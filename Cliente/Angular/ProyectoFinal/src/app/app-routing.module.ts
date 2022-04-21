import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddusersComponent } from './addusers/addusers.component';
import { DatosusersComponent } from './datosusers/datosusers.component';
import { InicioComponent } from './inicio/inicio.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  { path: '', component: InicioComponent },
  { path: 'users', component: UsersComponent },
  { path: 'addusers', component: AddusersComponent },
  { path: 'datosusers/:param', component: DatosusersComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
