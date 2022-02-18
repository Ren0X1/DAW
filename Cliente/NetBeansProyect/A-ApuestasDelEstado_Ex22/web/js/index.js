//JS
$(function() {
    console.clear()//LIMPIAR CONSOLA ANTES DE INICIAR EL PROGRAMA PARA VER BIEN LOS FALLOS
    let utils_ = UTILS__
    $('input[type=button]').on('click', function() {
        let name = $(this).attr('name')
        if (name!==null && name!==undefined && name!="") {
            utils_.cambiarPanel(name)
        }
    })

    $('#listaApu').on('change', function() {
        utils_.calcularApuestas()
        document.getElementById('listaTipoApu').innerHTML = this.innerHTML
    })
    
    $('#numApuestas').on('change', function() {
        utils_.calcularApuestas()
    })

    $('#comprar').on('click', function() {
        utils_.save()
    })

    $('#listaApuTipo').ready(function() {
        utils_.load()
    }).on('change', function() {
        let b = boletos__[$(this).val()]
        utils_.pintar(b,'#salidaResguardo',b.nombre)
    })

    $('#btp').on('click', function() {
        utils_.altaApuesta()
    })

    $('#nueva').on('click', function() {
        utils_.newCombo()
    })

    $('.borrar').on('click', function(e) {
        e.preventDefault()
        utils_.borrar(this)
    })

    $('#bltp').on('click', function() {
        utils_.mostrarTabla()
    })

    $('#betp').on('click', function() {
        utils_.editarDatos()
    })

    $('#formEdita').validate({
        onkeyup: true,
        rules: {

            Nombre: {
                required: true,
                minlength: 4
            }, 

            ctd1: {
                required: true,
                min: 1,
                max: 30,
                number: true
            },

            min1: {
                required: true,
                min: 1,
                number: true
            },

            max1: {
                required: true,
                min: 1,
                number: true
            },

        }, submitHandler: function(form) {
            alert('Apuesta Correcta')
            utils_.crearApuesta(form)
        }
    })
})