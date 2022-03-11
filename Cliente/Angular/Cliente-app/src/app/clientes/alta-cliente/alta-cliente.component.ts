import { Cliente, Grupo } from './../cliente.model';
import { ClientesService } from './../clientes.service';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-alta-cliente',
  templateUrl: './alta-cliente.component.html',
  styleUrls: ['./alta-cliente.component.css']
})
export class AltaClienteComponent implements OnInit {

  cliente: Cliente;
  grupos: Grupo[];
  public miForm!: FormGroup;

  constructor(private clientesService: ClientesService, private fb: FormBuilder) {
    this.cliente = this.clientesService.nuevoCliente();
    this.grupos = this.clientesService.getGrupos();
  }

  ngOnInit():void {
    this.creaFormulario()
  }

  creaFormulario() {
    this.miForm = this.fb.group({
      nombre:["Pepe",[Validators.required,Validators.minLength(3)]],
      cif:["11111111G",[
          Validators.required,
          Validators.pattern("[0-9](8)[A-Z]"),
          this.validaNif()
        ]
      ],
      direccion:"",
      grupo:""
    })
  }

  alta() {
    
  }

  private validaNif(control: AbstractControl) {
    const nif = control.value
    let error = null
    if (nif.length<9) {
      error = { nifCorto: 'El nif es corto'}
    }
    return error;
  }

  nuevoCliente(): void {
    this.clientesService.agregarCliente(this.cliente);
    this.cliente = this.clientesService.nuevoCliente();
  }
}
