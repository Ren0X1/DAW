import { Component } from '@angular/core';
import { Preguntas } from './interfaz';

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
    cambio = false;
    texto = 'SI';

    preguntasObj : Preguntas[] = [
        {
            pregunta: 'España es la mejor?',
            si: 1,
            no: 0
        },
        {
            pregunta: 'España es la mejor2?',
            si: 2,
            no: 3
        }
    ]

    pruebas = () => {
        this.cambio = !this.cambio
        if (this.texto=='SI') {
            this.texto = 'NO'
        } else {
            this.texto = 'SI'
        }
    }

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
