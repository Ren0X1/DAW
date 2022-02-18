//JS
window.onload = () => {
    let botonCargarProv = $('#prov')
    Lib.misDatos = this.localStorage
    botonCargarProv.onclick = () => {
        let item = Lib.misDatos.getItem('PROVJSON')
        if (item === null || item === undefined) {
            let datos = "provincia=0"
            Lib.enviarPeticionFetch("POST","CargaProvMuni",Lib.cargarProvincias,Lib.errorCargar,datos)
        } else {
            Lib.cargarProvincias(item)
        }
    }

    Lib.cargarProvincias = (datos) => {
        Lib.misDatos.setItem('PROVJSON', datos)
        Lib.limpiar(divprov)
        Lib.creaTabla({
            borrar: false,
            titulo: "Provincias",
            donde: "divprov",   
        }, JSON.parse(Lib.misDatos.getItem('PROVJSON')))

        Lib.addListener('tr')
    }

    Lib.cargarPueblos = (datos,pueblo) => {
        Lib.misDatos.setItem("MUNI_"+pueblo, datos)
        Lib.limpiar(divmuni)
        Lib.creaTabla({
            borrar: false,
            titulo: "Municipios",
            donde: "divmuni",
        }, JSON.parse(Lib.misDatos.getItem("MUNI_"+pueblo)))

        Lib.addListener('tr')
    }

    this.addEventListener('keydown', function (e) {
        let key = e.key
        if (key==='Control') {
            Lib.onCtrl = true
        }
    })

    this.addEventListener('keyup', function (e) {
        let key = e.key
        if (key==='Control') {
            Lib.onCtrl = false
        }
    })
}////////////////////////////////