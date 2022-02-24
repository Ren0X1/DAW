import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ComponentePrimeroComponent } from './componente-primero/componente-primero.component';
import { PrimerModuloModule } from './primer-modulo/primer-modulo.module';

@NgModule({
  declarations: [
    AppComponent,
    ComponentePrimeroComponent
  ],
  imports: [
    BrowserModule,
    PrimerModuloModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
