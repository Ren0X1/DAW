import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    titulo = 'Primer Proyecto Angular';
    msg = 'Adios';
    msg2 = 'Hola';
    mostrar = false;
    mensaje = () => {
        this.mostrar = !this.mostrar
        if (this.mostrar) {
            this.msg2 = this.msg
            this.msg = 'Hola'
        } else {
            this.msg2 = this.msg
            this.msg = 'Adios'
        }
    }
}
