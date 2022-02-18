var $  = function (v) {return document.querySelector(v)}
var $1 = function (v) {return document.getElementById(v)}
var $2 = function (k,v) {return k.getElementsByTagName(v)}

var Lib = {}

Lib.onCtrl = false

Lib.errorCargar = function(error) {
    alert("Se ha producido un error recuperando los datos: "+error)
}

Lib.removeOptions = function(selectElement) {
    var i, L = selectElement.options.length - 1;
    for(i = L; i >= 0; i--) {
        selectElement.remove(i);
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

Lib.limpiar = function(v) {
    v.innerHTML=""
}

Lib.creaTabla = function(param, datos) {
    let tabla = document.createElement('table')

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
            tbody.rows[f].cells[c].appendChild(Lib.creaBoton(datos))
        }
        f++
        c=0
    }
    document.getElementById(param.donde).appendChild(tabla)
}

Lib.addListener = function(v) {
    let objetos = document.getElementsByTagName(v)

    function quitarBorde() {
        for (let i=0;i!=objetos.length;i++) {
            let style = objetos[i].style
            style.border = ''
            style.borderColor = ''
        }
    }

    for (let i=0;i!=objetos.length;i++) {
        if (objetos[i].getAttribute('listener') !== 'true') {
            objetos[i].addEventListener('dragstart', function(e) {
                e.dataTransfer.setData("id", objetos[i].firstElementChild.innerText);
            })

            objetos[i].addEventListener('click', function(e) {
                if (Lib.onCtrl==false) {
                    let padre = e.target.parentElement
                    quitarBorde()
                    if (padre.tagName=='TR') {
                        let style = padre.style
                        style.border = '3px solid'
                        style.borderColor = '#ad2323'
                        if (padre.parentElement.parentElement.caption.innerText=='Provincias') {
                            let td = padre.firstElementChild.innerText
                            let item = Lib.misDatos.getItem("MUNI_"+td)
                            if (item === null || item === undefined) {
                                let datos = "provincia="+td
                                Lib.enviarPeticionFetch("POST","CargaProvMuni",Lib.cargarPueblos,Lib.errorCargar,datos,td)
                            } else {
                                Lib.cargarPueblos(item,td)
                            }
                        }
                    }
                } else {
                    Lib.creaFormu(e.target.parentElement)
                }
            })
            objetos[i].setAttribute('listener', 'true');
        }
    }
}

Lib.creaFormu = function(elemento) {
    var ele
    if (elemento.parentElement != null) {
        ele = elemento
    } else {
        let x = document.getElementsByTagName('table')
        for (let i=0;i!=x.length;i++) {
            if (x[i].caption.innerText=='Provincias') {
                let j = x[i].getElementsByTagName('tr')
                for (let k=0;k!=j.length;k++) {
                    let td = j[k].firstElementChild.innerText
                    if (td==elemento) {
                        ele=j[k]
                    }
                }
            }
        }
    }

    let tabla = ele.parentElement.parentElement
    let contenedor = $1('divmiForm')
    contenedor.innerHTML=""
    let formu = document.createElement('form')
    formu.classList.add('nuevoForm')
    contenedor.style.display='block'
    contenedor.appendChild(formu)
    let field = document.createElement('fieldset')
    formu.appendChild(field)
    let legend = document.createElement('legend')
    legend.innerText = tabla.caption.innerText
    field.appendChild(legend)
    
    let labelFormu = tabla.getElementsByTagName('th')
    let datosFormu = ele.getElementsByTagName('td')

    for (let i=0; i<labelFormu.length; i++) {
        let label = document.createElement('label')
        label.innerText = labelFormu[i].innerText
        field.appendChild(label)

        let input = document.createElement('input')
        input.type = 'text'
        if (datosFormu[i]!=null) {
            input.value = datosFormu[i].innerText
        }
        input.setAttribute('required', 'required')
        field.appendChild(input)
    }

    let submit = document.createElement('input')
    submit.type = 'submit'
    submit.value = 'Confirmar'
    submit.addEventListener('click', Lib.addFilaFormu)
    formu.appendChild(submit)
    let reset = document.createElement('input')
    reset.type = 'reset'
    reset.value = 'Cancelar'
    formu.appendChild(reset)

    formu.addEventListener('dragover', function(e) {
        e.preventDefault()
    })

    formu.addEventListener('drop', function(e) {
        let data = e.dataTransfer.getData("id");
        Lib.creaFormu(data)
    })

}

Lib.addFilaFormu = function(e) {
    e.preventDefault()
    let formu = $('.nuevoForm')
    let self =  {}
    let label = formu.getElementsByTagName('label')
    let input = formu.getElementsByTagName('input')

    for (let i=0;i!=label.length;i++) {
        if (label[i].innerText!="") {
            self[label[i].innerText] = input[i].value
        }
    }

    function add(donde,pueblo) {
        let item = Lib.misDatos.getItem(donde)
        var k
        if (donde=='PROVJSON') {
            k = self.idprovincia
        } else {
            k = self.idpoblacion
        }
        let v = JSON.stringify(self).replace("'","")
        let valorrecor = item.substring(0,item.length-1)
        let valorFinal = valorrecor+",\""+k+"\":"+v+"}"

        if (donde=='PROVJSON') {
            Lib.cargarProvincias(valorFinal)
        } else {
            Lib.cargarPueblos(valorFinal,pueblo)
        }
    }

    if (self.latitud!=null) {
        let x = document.getElementsByTagName('table')
        var v = null
        for (let i=0;i!=x.length;i++) {
            if (x[i].caption.innerText=='Provincias') {
                let j = x[i].getElementsByTagName('tr')
                for (let k=0;k!=j.length;k++) {
                    if (j[k].style.border!="") {
                        let td = j[k].firstElementChild.innerText
                        v=td
                    }
                }
            }
        }
        add("MUNI_"+v,v)
    } else {
        add('PROVJSON')
    }
}

Lib.enviarPeticionFetch = async function(metodo,url,procesaRespuesta,procesaError,datos,x) {
	const options = {
		method: metodo,
		headers:{}
	}

	if (metodo=="POST") {
		options.body = datos
		options.headers["Content-type"]="application/x-www-form-urlencoded"
	} else {
		url = url+"?"+datos.asincrono
	}

	let fichero = await fetch(url, options)
	if (fichero.ok) {
		let respuesta = await fichero.text()
        if (x==null) {
            procesaRespuesta(respuesta)
        } else {
            procesaRespuesta(respuesta,x)
        }
	} else {
		procesaError(fichero.statusText)
	}
}