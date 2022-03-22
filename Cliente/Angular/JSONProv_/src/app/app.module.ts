import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ListaProvinciasComponent } from './lista-provincias/lista-provincias.component';
import { ListadoObjComponent } from './listado-obj/listado-obj.component';
import { ListaPueblosComponent } from './lista-pueblos/lista-pueblos.component';

import { ListadoPueblosModule } from './listado-pueblos/listado-pueblos.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatIconModule } from '@angular/material/icon';
import { ModificaProvComponent } from './modifica-prov/modifica-prov.component';

@NgModule({
  declarations: [
    AppComponent,
    ListaProvinciasComponent,
    ListadoObjComponent,
    ListaPueblosComponent,
    ModificaProvComponent
  ],
  imports: [
    BrowserModule,
    ListadoPueblosModule,
    HttpClientModule,
    MatButtonModule,
    MatTooltipModule,
    MatIconModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
