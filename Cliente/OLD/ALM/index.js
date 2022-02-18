//JS
window.onload = function() {
    var botonAlta = Lib.$('#alta')
    var divForm = Lib.$1('fcli')
    var display = false
    var botonExa = Lib.$('#fichProv')
    var listaProv = Lib.$('#listaProvincias')
    var listar = Lib.$('#lista')
    var listarP = Lib.$('#listaP')

    botonAlta.onclick = function() {
        let style = divForm.style
        if (!display) {
            style.display = 'block'
            display = true
        } else {
            style.display = 'none'
            display = false
        }
    }

    botonExa.onchange = async function() {
        await Lib.sleep(500)
        Lib.cargaFichero(botonExa)
    }

    listaProv.onchange = function() {
        Lib.cargaPueblos(this.selectedIndex-1)
    }

    listar.onclick = function() {
        let radio = document.getElementsByName("td")
        let radio2 = document.getElementsByName("lc")

        if (radio[0].checked) {
            if (radio2[0].checked) {
                Lib.Listar(true, "M")
            } else if (radio2[1].checked) {
                Lib.Listar(true , "H")
            } else {
                Lib.Listar(true , false)
            }
        } else {
            if (radio2[0].checked) {
                Lib.Listar(false, "M")
            } else if (radio2[1].checked) {
                Lib.Listar(false, "H")
            } else {
                Lib.Listar(false, false)
            }
        }
    }

    listarP.onclick = function() {
        Lib.ListarP()
    }
}