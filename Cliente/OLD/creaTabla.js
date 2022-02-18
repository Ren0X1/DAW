const creaTabla = (param, datos) => {
    
	if (param==null) {//Opciones por defecto
		param = {
			borrar: false,
            titulo: "Tabla sin titulo",
            donde: false,
            dragdrop: false
		}
	}
	
    const createTR = (tabla,v) => {
        let tablerow = v.cloneNode(true)
        tabla.appendChild(tablerow)
        return tablerow
    }

    let tabla = document.createElement('table')

    if(param.dragdrop) {
        //FALTA TERMINAR
        tabla.addEventListener('dragover', function(e) {
            e.preventDefault()
        })
    
        tabla.addEventListener('drop', function(e) {
            let data = e.dataTransfer.getData("tag")
            if(data=='TD') {
                let ele = e.target.parentNode
                let NEWtablerow = createTR(tabla,ele)
            }
            else if (data=='TR') {
                let NEWtablerow = createTR(tabla,e.target)
            }
        })
    }

    tabla.setAttribute('id', 'otraTabla')
    tabla.setAttribute('border', 1)
    tabla.setAttribute('summary', 'Descripción de la tabla y su contenido')

    let caption = document.createElement('caption')
    let titulo = document.createTextNode(param.titulo)

    caption.appendChild(titulo)
    tabla.appendChild(caption)

    let thead = document.createElement('thead')

    tabla.appendChild(thead)
    thead.insertRow(0)

    let filas=Object.keys(datos)
    let columnas= Object.keys(datos[filas[0]])

    cabecera = document.createElement('th')
    thead.rows[0].appendChild(cabecera)

    for(col of columnas) {
        let cabecera = document.createElement('th')
        cabecera.innerHTML = col
        thead.rows[0].appendChild(cabecera)
    }
    let tbody = document.createElement('tbody')
    tabla.appendChild(tbody)

    let f=0
    let c=0

    for(fila of filas) {
        tbody.insertRow(f)
        tbody.rows[f].addEventListener('dragstart', function(e) {
            e.dataTransfer.setData("tag", e.target.tagName)
        })
        tbody.rows[f].insertCell(c)
        tbody.rows[f].cells[c].innerHTML = fila
        c=1
        for(col of columnas) {
            tbody.rows[f].insertCell(c)
            tbody.rows[f].cells[c].innerHTML = datos[fila][col]
            c++;
        }
        if(param.borrar) {
            tbody.rows[f].insertCell(c)
            tbody.rows[f].cells[c].appendChild(creaBoton(datos))
        }
        f++
        c=0
    }
	if (param.donde==false) {
		document.body.appendChild(tabla)
	} else {
		document.getElementById(param.donde).appendChild(tabla)
	}
}