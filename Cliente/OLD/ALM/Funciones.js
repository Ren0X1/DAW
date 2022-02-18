var Lib = {}

Lib.objetoProvincias={}
Lib.ClientesP={}
Lib.ClientesN={}

Lib.$ = function(selector) {
    return document.querySelector(selector)
}

Lib.$1 = function(selector) {
    return document.getElementById(selector)
}

Lib.sleep = function(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

Lib.metodoCargaXml = function(xmlObject) {
    Lib.objetoProvincias = xmlObject
    Lib.cargarLista()
}

Lib.cargarLista = async function () {
    let select = Lib.$('#listaProvincias')
    let provincias = await Lib.cargaDatos(Lib.objetoProvincias.getElementsByTagName("provincia"), "nombre")

    Object.keys(provincias).forEach(function(key) {
        let nombre = provincias[key]
        let opt = document.createElement('option');
        opt.value = nombre;
        opt.innerHTML = nombre;
        select.appendChild(opt);
    })
}

Lib.cargaPueblos = async function(k) {
    let select = Lib.$1('listaPueblos')
    select.setAttribute("disabled","disabled")
    Lib.removeOptions(select)
    let localidades = await Lib.cargaDatosPueblo(Lib.objetoProvincias.getElementsByTagName("provincia"), "localidades")
    localidades[k].forEach(function(v) {
        let nombre = v
        let opt = document.createElement('option');
        opt.value = nombre;
        opt.innerHTML = nombre;
        select.appendChild(opt);
    })
    select.removeAttribute("disabled")
}

Lib.removeOptions = function(selectElement) {
    var i, L = selectElement.options.length - 1;
    for(i = L; i >= 0; i--) {
        selectElement.remove(i);
    }
}

Lib.cargaDatos = async function(HTMLCol, Etiqueta) {
    var datos={}
    for(let i=0;i< HTMLCol.length;i++){
        var nodo=HTMLCol[i].getElementsByTagName(Etiqueta)[0].innerHTML
        datos[i]=nodo
    }
    return datos
}

Lib.cargaDatosPueblo = async function(HTMLCol, Etiqueta) {
    var datos = {}
    for(let i=0;i< HTMLCol.length;i++){
        let x = HTMLCol[i].getElementsByTagName(Etiqueta)[0]
        var array = []
        for (let j=0;j!=x.getElementsByTagName("localidad").length;j++) {
            var nodo=x.getElementsByTagName("localidad")[j]
            array.push(nodo.innerHTML)
        }
        datos[i] = array
    }
    return datos
}

Lib.cargaFichero = function(input,provincias) {
	var files = input.files;
	if (files) {
		var file=files[0];
		var reader = new FileReader();
		reader.onload = function (e) {
			parser = new DOMParser();
			xmlDoc = parser.parseFromString(e.target.result,"text/xml");
			Lib.metodoCargaXml(xmlDoc);
		}
		//reader.readAsDataURL(file);
		reader.readAsText(file);
	}	
}

Lib.descargaArchivo = function() {
	if(window.XMLHttpRequest) {
		peticion_http = new XMLHttpRequest();
	} else if(window.ActiveXObject) {
		peticion_http = new ActiveXObject("Microsoft.XMLHTTP");
	}
	peticion_http.onreadystatechange = muestraContenido;
	peticion_http.open('GET', 'http://localhost/provinciasypoblaciones.xml', true);
	peticion_http.send(null);
	function muestraContenido() {
		if(peticion_http.readyState == 4) {
			if(peticion_http.status == 200) {
				alert(peticion_http.responseXML);
			}
		}
	}
}

Lib.Cliente = function(nombre,apellidos,edad,sexo,nif,email,provincia,localidad) {
    var self = {}
    self.nombre	       = nombre;
    self.apellidos     = apellidos;
    self.edad		   = edad;
    self.sexo		   = sexo;
    self.nif		   = nif;
    self.email	       = email;
    self.provincia     = provincia;
    self.localidad     = localidad;
    return self
}

Lib.compruebaForm = function(evento) {
    evento.preventDefault()

    let nombre = Lib.$1('nomcli').value
    let apellidos = Lib.$1('apecli').value
    let edad = Lib.$1('edcli').value
    let radio = document.getElementsByName("sexo")
    var sexo = ""
    if (radio[0].checked) {
        sexo="H"
    } else {
        sexo="M"
    }
    let nif = Lib.$1('nifcli').value
    let email = Lib.$1('mail').value
    let provincia = Lib.$1('listaProvincias').value
    let localidad = Lib.$1('listaPueblos').value
    //COMPROBAR Y ACTUALIZAR NIF FALTA
    let cliente = Lib.Cliente(nombre,apellidos,edad,sexo,nif,email,provincia,localidad)

    Lib.ClientesN[nif] = cliente
    //alert(evento)
}

Lib.Listar = function(bol,sexo) {
    if (bol) { //JSON
        let lista = Lib.$1('qs')
        lista.innerHTML=""
        Object.keys(Lib.ClientesN).forEach(function(key) {
            if (sexo==false) {
                lista.innerHTML+=JSON.stringify(Lib.ClientesN[key])+"<br>"
            } else if (sexo=="H") {
                if (Lib.ClientesN[key].sexo=="H") {
                    lista.innerHTML+=JSON.stringify(Lib.ClientesN[key])+"<br>"
                }
            } else if (sexo=="M") {
                if (Lib.ClientesN[key].sexo=="M") {
                    lista.innerHTML+=JSON.stringify(Lib.ClientesN[key])+"<br>"
                }
            }
        })
    } else { //QS
        let lista = Lib.$1('qs')
        lista.innerHTML=""
        Object.keys(Lib.ClientesN).forEach(function(key) {
            let cliente = Lib.ClientesN[key]
            if (sexo==false) {
                lista.innerHTML+=
                "nombre="+cliente.nombre+"&"+
                "apellidos="+cliente.apellidos+"&"+
                "edad="+cliente.edad+"&"+
                "sexo="+cliente.sexo+"&"+
                "nif="+cliente.nif+"&"+
                "email="+cliente.email+"&"+
                "provincia="+cliente.provincia+"&"+
                "localidad="+cliente.localidad
                +"<br>"
            } else if (sexo=="H") {
                if (cliente.sexo=="H") {
                    lista.innerHTML+=
                    "nombre="+cliente.nombre+"&"+
                    "apellidos="+cliente.apellidos+"&"+
                    "edad="+cliente.edad+"&"+
                    "sexo="+cliente.sexo+"&"+
                    "nif="+cliente.nif+"&"+
                    "email="+cliente.email+"&"+
                    "provincia="+cliente.provincia+"&"+
                    "localidad="+cliente.localidad
                    +"<br>"
                }
            } else if (sexo=="M") {
                if (cliente.sexo=="M") {
                    lista.innerHTML+=
                    "nombre="+cliente.nombre+"&"+
                    "apellidos="+cliente.apellidos+"&"+
                    "edad="+cliente.edad+"&"+
                    "sexo="+cliente.sexo+"&"+
                    "nif="+cliente.nif+"&"+
                    "email="+cliente.email+"&"+
                    "provincia="+cliente.provincia+"&"+
                    "localidad="+cliente.localidad
                    +"<br>"
                }
            }
        })
    }
}

Lib.ListarP = async function() {
    let provincias = await Lib.cargaDatos(Lib.objetoProvincias.getElementsByTagName("provincia"), "nombre")
    let lista = Lib.$1('listados')
    lista.innerHTML=""
    var x = true
    Object.keys(provincias).forEach(function(key1) {
        Object.keys(Lib.ClientesN).forEach(function(key) {
            let cliente = Lib.ClientesN[key]
            if (cliente.provincia==provincias[key1]) {
                if (x) {
                    lista.innerHTML+="<p>Provincia: "+provincias[key1]+"</p>"
                    let tabla = Lib.createTable(provincias[key1],lista)
                    let tablerow1 = Lib.createTR(tabla)
                    Lib.createTH(tablerow1,cliente)
                    x=false
                }
                let tab = Lib.$1(provincias[key1])
                let tablerow = Lib.createTR(tab)
                Lib.createTD(tablerow,cliente)
            }
        })
        x=true
    })
}

Lib.createTD = function(tablerow, v) {
    Object.keys(v).forEach(function(k) {
        let j = document.createElement('td')
        j.innerHTML = v[k]
        tablerow.appendChild(j)
    })
}

Lib.createTH = function(tablerow, v) {
    Object.keys(v).forEach(function(k) {
        let j = document.createElement('th')
        j.innerHTML = k.toUpperCase()
        tablerow.appendChild(j)
    })
}

Lib.createTR = function(tabla) {
    let tablerow = document.createElement('tr')
    tabla.appendChild(tablerow)
    return tablerow
}

Lib.createTable = function(id, padre) {
    let tabla = document.createElement('table')
    tabla.id = id
    padre.appendChild(tabla)
    return tabla
}