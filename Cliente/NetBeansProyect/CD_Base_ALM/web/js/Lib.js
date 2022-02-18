var $  = function (v) {return document.querySelector(v)}
var $1 = function (v) {return document.getElementById(v)}
var $2 = function (k,v) {return k.getElementsByTagName(v)}

var Lib = {}

Lib.listaCD = {}
Lib.mostrado = false

Lib.tecla = function(e) {
    var evento = e || window.event
    var caracter = evento.key
} 

Lib.mueveCd = function(e) {
    let v = $1("ccd")
    v.style.display="block"
    v.style.zIndex=10
    v.style.left=evento.clientX+"px"
    v.style.top= evento.clientY+"px"
}

Lib.verCatalogo = function() {
    let catalogo = $1('catalogo')
    if (!Lib.mostrado) {
        catalogo.innerHTML=""
        Lib.creaTabla({
            borrar: false,
            titulo: "Catalogo de CD",
            donde: "catalogo",   
        }, Lib.listaCD)
        catalogo.style.display="block"
        Lib.mostrado = !Lib.mostrado
    } else {
        catalogo.style.display="none"
        Lib.mostrado = !Lib.mostrado
    }
}

Lib.errorCargar = function(error) {
    alert("Se ha producido un error recuperando los datos: "+error)
}

Lib.cargarCliPedidos = function(clientes) {
    Lib.CLIENTES = JSON.parse(clientes).clientes
    var lista = $1('cli2')
    Object.keys(Lib.CLIENTES).forEach(function(v) {
        lista.options.add(new Option(
            Lib.CLIENTES[v],
            Lib.CLIENTES[v])
        )
    })

    lista.addEventListener('change', function() {
        let cliente = this.value
        Lib.cargaListaPedidos(cliente)
    })
}

Lib.crearPedidos = function(pedidos) {
    Lib.PEDIDOS = JSON.parse(pedidos)
    var lista = $1('lp')
    Lib.removeOptions(lista)
    Object.keys(Lib.PEDIDOS).forEach(function(v) {
        lista.options.add(new Option(
            Lib.PEDIDOS[v].codigo+" - "+Lib.PEDIDOS[v].fecha,
            v)
        )
    })
}

Lib.removeOptions = function(selectElement) {
    var i, L = selectElement.options.length - 1;
    for(i = L; i >= 0; i--) {
        selectElement.remove(i);
    }
}

Lib.cargaListaPedidos = function(cli) {
    var datos = "pedido="+JSON.stringify({
        cpedido: "0",
        cliente: cli
    })

    Lib.enviarPeticionFetch("POST","RecuperaP",true,Lib.crearPedidos,Lib.errorCargar,datos)
}

Lib.cargarPedidoCliente = function(response) {

}
Lib.lineaCD = function(t,a,pa,pre,co) {
    let self = {}
    self.Titulo   = t
    self.Artista  = a
    self.Pais     = pa
    self.Precio   = pre
    self.Color    = co
    return self
}

Lib.xmlDoc = null
Lib.xslDoc = null

Lib.CargarCD = function(id,xmlDoc) {
    let lista = $1(id)
    Lib.xmlDoc = xmlDoc
    let tit = $2(Lib.xmlDoc,'title'), 
    aut = $2(Lib.xmlDoc,'artist'),
    cou = $2(Lib.xmlDoc,'country'), 
    pre = $2(Lib.xmlDoc,'price');

    for(let i=0;i!=tit.length;i++) {
        lista.options.add(new Option(
            tit[i].childNodes[0].nodeValue +" / "+
            aut[i].childNodes[0].nodeValue,
            pre[i].childNodes[0].nodeValue)
        )

        let linea = new Lib.lineaCD(
            tit[i].childNodes[0].nodeValue,
            aut[i].childNodes[0].nodeValue,
            cou[i].childNodes[0].nodeValue,
            pre[i].childNodes[0].nodeValue,
            Lib.colorAleatorio()
        )
        Lib.listaCD[i]=linea
    }
}

Lib.creaBoton = function(datos) {
    let boton = document.createElement("button");
    let ntexto = document.createTextNode("eliminar");       
    boton.appendChild(ntexto);      
    boton.addEventListener('clic', function() {
        Lib.borraFila(datos)
    }, false)
    return boton;
}

Lib.borraFila = function(datos) {
    delete datos[boton.parentNode.parentNode.cells[0]]
    this.parentNode.parentNode.remove()
}

Lib.creaTabla = (param, datos) => {
    
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