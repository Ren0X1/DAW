import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersService } from '../servicios/users.service';

@Component({
  selector: 'app-datosusers',
  templateUrl: './datosusers.component.html',
  styleUrls: ['./datosusers.component.css']
})
export class DatosusersComponent implements OnInit {

  private idEmpleado:string | undefined | null;
  public empleado:Object | undefined;

  constructor(private rutaActiva: ActivatedRoute,private service:UsersService) { }

  ngOnInit(): void {
    this.rutaActiva.params.subscribe(
      params => {
         this.idEmpleado=params['param'];
         this.service.getUsuario(this.idEmpleado).subscribe((response: any) =>this.empleado=response.data);
      }
    );
  }

}
