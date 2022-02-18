//JS
window.onload = function() {
    var contenedor = document.querySelector('.contenedor')
    var boton = document.querySelector('.btn')

    this.addEventListener('keypress', function(e) {
        let key = e.key
        if (key==='Enter') {
            contenedor.innerHTML+='<br>'
        } else {
            contenedor.innerHTML+=key
        }
    })

    boton.onclick = function() {
        contenedor.innerHTML=''
    }
}