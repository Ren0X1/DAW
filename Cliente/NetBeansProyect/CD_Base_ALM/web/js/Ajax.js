Lib.enviarPeticion = function(metodo,url,asincrono,procesaRespuesta,procesaError,datos) {
    var xhttp = new XMLHttpRequest()
	xhttp.onreadystatechange = function() {
		if (this.readyState=='4' && this.status=='200') {
			procesaRespuesta(this.responseText)
		} else if (this.readyState=='4' && this.status !='200'){
			procesaError(this.statusText)
		}
	}
	if (metodo=='GET') {
		xhttp.open(metodo, url+"?"+datos.asincrono)
		xhttp.send()
	} else {
		xhttp.open(metodo, url, asincrono)
		xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded")
		xhttp.send(datos)
	}
}

Lib.enviarPeticionFetch = async function(metodo,url,asincrono,procesaRespuesta,procesaError,datos) {
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
		procesaRespuesta(respuesta)
	} else {
		procesaError(fichero.statusText)
	}
}

Lib.loadDocXML = function(fichero,boolean,id) {
    var xhttp = new XMLHttpRequest()
    xhttp.onload=function(){
      Lib.CargarCD(id,xhttp.responseXML)
    }
    xhttp.open("GET", fichero, boolean)
    xhttp.send()
} 

Lib.displayResult = function(donde,xml,xsl,boton) {
	let catalogo=$1(donde)
	if(catalogo.childNodes.length!==0){
		catalogo.removeChild(catalogo.childNodes[0]);
		boton.value="Ver Catalogo"
	} else {	
		if (window.ActiveXObject) {
			ex=xml.transformNode(xsl)
			$1(donde).innerHTML=ex
		} else if (document.implementation && document.implementation.createDocument) {
			xsltProcessor=new XSLTProcessor()
			xsltProcessor.importStylesheet(xsl)
			resultDocument = xsltProcessor.transformToFragment(xml,document)
			$1(donde).appendChild(resultDocument)
			boton.value="Ocultar Catalogo"
		}
	}
}

Lib.aleatorio = function(inferior,superior) {
	numPosibilidades = superior-inferior
	aleat = Math.random()*numPosibilidades
	aleat = Math.floor(aleat)
	return parseInt(inferior)+aleat
} 

Lib.colorAleatorio = function() {
	hexadecimal = new Array("0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F")
	color_aleatorio = "#"
	var i=0
	for (i=0;i<6;i++) {
		pos = Math.ceil(Math.round(Math.random()*15))
		color_aleatorio += hexadecimal[pos]
	}
	return color_aleatorio
}