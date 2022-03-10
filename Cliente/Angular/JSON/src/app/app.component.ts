import { Component } from '@angular/core';
import { UsuariosService } from './usuarios.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'JSON';
  listaUsuarios:any[]=[];
  constructor(private usuarios:UsuariosService) {}
  ngOnInit() {
    this.usuarios.getUsuario$().subscribe({
      next: (personas: any[]) => this.listaUsuarios = personas,
      error: (error: any) => console.log(error)
    })
  }
}
