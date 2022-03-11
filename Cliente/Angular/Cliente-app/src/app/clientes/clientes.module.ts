import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientesService } from './clientes.service';
import { AltaClienteComponent } from './alta-cliente/alta-cliente.component';
import { FormsModule } from '@angular/forms';
import { ListadoClientesComponent } from './listado-clientes/listado-clientes.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AltaClienteComponent,
    ListadoClientesComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    AltaClienteComponent,
    ListadoClientesComponent
  ],
  providers: [
    ClientesService
  ]
})
export class ClientesModule { }

