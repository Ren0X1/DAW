window.onload = ()=>{
    var select = document.querySelector('#select1')
    var boton = document.getElementById('boton')
    boton.disabled = true
    boton.style.backgroundColor = '#3D3D3D'
    boton.style.cursor = 'not-allowed'
    select.onchange = function() {
        boton.disabled = false
        boton.style.backgroundColor = '#215A8E'
        boton.style.cursor = 'pointer'
    }
}