//JS
function Libro (titulo,editorial,precio,unidades,isbn) {
    var self={}
    self.titulo = titulo
    self.editorial = editorial
    self.precio = precio
    self.unidades = unidades
    self.isbn = isbn
    return self
}

function Cliente (nombre,localidad,email,nif) {
    var self={}
    self.nombre = nombre
    self.localidad = localidad
    self.email = email
    self.nif = nif
    return self
}