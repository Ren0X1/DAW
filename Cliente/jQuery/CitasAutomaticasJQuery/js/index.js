//JS
$(function() {
    var MENU = $('#contextMenu')
        MENU.mostrado = false
        MENU.borrar = false
        MENU.texto = null

    var FlotarIzquierda = $('.Izquierda')
    var FlotarDerecha = $('.Derecha')
    var Resaltar = $('.Resaltar')
    var Borrar = $('.Borrar')

    let hideMenu = function() { 
        MENU.hide() 
        MENU.mostrado=false
    }

    let aleatorio = function() {
        return (Math.random()<0.5)
    }

    let cambiarClases = function() {
        if (MENU.borrar) {
            Borrar.removeClass('tachado')
            FlotarIzquierda.addClass('tachado')
            FlotarDerecha.addClass('tachado')
            Resaltar.addClass('tachado')
        } else {
            Borrar.addClass('tachado')
            FlotarIzquierda.removeClass('tachado')
            FlotarDerecha.removeClass('tachado')
            Resaltar.removeClass('tachado')
        }
    }

    $('.pq').each(function() {
        if (aleatorio()) {
            $(this).addClass('derecha')
        } else {
            $(this).addClass('izquierda')
        }
    })

    $('body').on('contextmenu', function(e) {
        e.preventDefault()
        MENU.texto = document.getSelection()
        if(
            MENU.texto!="" && 
            MENU.texto!==null && 
            MENU.texto!==undefined || 
            e.target.className=='izquierda' || 
            e.target.className=='derecha'
        ) {
            if (MENU.mostrado){ 
                hideMenu()
            } else {
                MENU.show()
                MENU.mostrado = true
                if (e.target.classList.length != 0) {
                    MENU.borrar = true
                    cambiarClases()
                } else {
                    MENU.borrar = false
                    cambiarClases()
                }
                MENU.css('left',e.pageX + "px")
                MENU.css('top',e.pageY + "px")
            }
        }
    }).on('click', function() {
        hideMenu()
    })

    FlotarIzquierda.on('click', function() {
        if (MENU.mostrado && !MENU.borrar) {
            let range = MENU.texto.getRangeAt(0)
            let newSpan = $("<span>").addClass('izquierda')
            range.surroundContents(newSpan[0])
            $(newSpan).before(newSpan.text())
        }
    })

    FlotarDerecha.on('click', function() {
        if (MENU.mostrado && !MENU.borrar) {
            let range = MENU.texto.getRangeAt(0)
            let newSpan = $("<span>").addClass('derecha')
            range.surroundContents(newSpan[0])
            $(newSpan).before(newSpan.text())
        }
    })

    Resaltar.on('click', function() {
        if (MENU.mostrado && !MENU.borrar) {
            let range = MENU.texto.getRangeAt(0)
            let newSpan = $("<span>").addClass('resaltar')
            range.surroundContents(newSpan[0])
        }
    })

    Borrar.on('click', function() {
        if(MENU.mostrado && MENU.borrar) {
            let spam = MENU.texto.baseNode.parentElement
            $(spam).remove()
        }
    })
})