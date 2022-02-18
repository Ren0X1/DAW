//JS
var $ = (v)=>{
    return document.querySelector(v)
}

window.onload = function() {
    let p = $('#p')
    var nombre
    
    this.addEventListener('click', function() {
        alert('Hola')
    }, true)

    document.addEventListener('click', function() {
        alert('Como')
    }, true)

    document.body.addEventListener('click', function(e) {
        alert('Estas')
    }, true)

    p.addEventListener('click', function(e) {
        nombre = prompt('Quieres decirme tu nombre?')
        if (nombre===null || nombre===undefined || nombre=="") {
            e.stopPropagation()
            alert('Adios Desconocido')
        }
    }, true)

    p.addEventListener('click', function() {
        alert('Bienvenido')
    })

    document.body.addEventListener('click', function() {
        alert('Buenos')
    })

    document.addEventListener('click', function() {
        alert('Dias')
    })

    this.addEventListener('click', function() {
        alert(nombre)
    })
}
