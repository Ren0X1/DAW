//JS
window.onload = function() {
    
    Lib.CargarFichas($('#rojos').getElementsByTagName('img'))
    Lib.CargarFichas($('#azules').getElementsByTagName('img'))

    Lib.CargarTablero($('#tablero').getElementsByTagName('td'))

}


/*
var objetoProvincias={};
    
var mueven="rojo";
var ganadoras=["123","456","789",
               "147","258","369",
               "159","357"];
var jugadores={};

function nuevoJugador(nombre,apellidos,edad,sexo,nif,email,provincia,localidad){
    this.nombre	=nombre;
    this.apellidos=apellidos;
    this.edad		=edad;
    this.sexo		=sexo;
    this.nif		=nif;
    this.email	=email;
    this.provincia=provincia;
    this.localidad=localidad;
}*/