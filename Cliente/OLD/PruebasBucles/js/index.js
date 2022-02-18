//JS
window.onload = function() {
    var mensaje = "Hola que tal"
    var letras = mensaje.split("")
    
    var p = document.getElementById("print")
    var p2 = document.getElementById("print2")
    var p3 = document.getElementById("print3")
    var p4 = document.getElementById("print4")

    //FOR
    for (let i=0;i!=mensaje.length;i++) {
        p.innerHTML+=mensaje.charAt(i)+"-"
    }
    //FOR OF
    for (let x of letras) {
        p2.innerHTML+=x+"-"
    }
    //FOR IN
    for (let k in letras) {
        p3.innerHTML+=letras[k]+"-"
    }
    //FOR EACH
    letras.forEach(function(char,index,arr) {
        p4.innerHTML+=char+"-"
    })
}