// JavaScript Document

var xmlhttp; //peticion
//------------------------------
function creaPeticion() {

	if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else { // code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //versiones
	}

	return xmlhttp;
}

//------------------------------
//function loadXMLDoc(dname)
function enviarPeticion(metodo, url, tipo, procesaRespuesta, datos) {
	//creamos el Objeto XMLHttpRequest...
	xmlhttp = creaPeticion();

	//Asociamos una funcion manejadora del evento onreadystatechange...
	/* 1 */
	xmlhttp.onreadystatechange = procesaRespuesta;
	/* 2 
		xhttp.addEventListener('readystatechange',procesaRespuesta); ***/
	if (metodo == "GET") {
		if (datos)
			url += '?' + datos;
		xmlhttp.open("GET", url, tipo);
		xmlhttp.send("");
	} else {
		xmlhttp.open("POST", url, tipo);
		//xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		//    request.setRequestHeader("Content-Type",
		//                    "application/x-www-form-urlencoded; charset=UTF-8");*/
		xmlhttp.setRequestHeader("Content-type", "application/json");
		xmlhttp.send(datos);
	}

	//return xmlhttp.response;

}
//----- gestor de eventos----------------*/

function procesaRespuesta2() {
	if ((xmlhttp.readyState == 4) && (xmlhttp.status == 200)) { // procesar el xml con js o aplicar XSL
		alert(xmlhttp.response);
		document.getElementById("res").innerHTML += xmlhttp.response;
		//donde.innerHTML +=xmlhttp.response;
		//return xmlhttp.response;//XML;
	} else {
		alert("Error en la peticion State / status=" + xmlhttp.readyState + " /" + xmlhttp.status);
	}
}

//----------------------------------

function displayResult(donde, xml, xsl) {

	// code for IE
	if (window.ActiveXObject) {
		ex = xml.transformNode(xsl);
		document.getElementById(donde).innerHTML = ex;
	}
	// code for Mozilla, Firefox, Opera, etc.
	else if (document.implementation && document.implementation.createDocument) {
		xsltProcessor = new XSLTProcessor();
		xsltProcessor.importStylesheet(xsl);
		resultDocument = xsltProcessor.transformToFragment(xml, document);
		document.getElementById(donde).innerHTML = "";
		document.getElementById(donde).appendChild(resultDocument);

		//document.getElementById(donde).innerHTML=resultDocument;
	}
}
/*------------------------------*/
function loadXMLDoc(dname) {
	/*if (window.ActiveXObject)
		  {
		  	//xhttp=new ActiveXObject("Msxml2.XMLHTTP.3.0");
		  	var xml = new ActiveXObject("Microsoft.XMLDOM")
	        xml.async = false;
    	    xml.load(dname);
			return xml;
		  }
		else
		  {*/
	xhttp = new XMLHttpRequest();
	xhttp.open("GET", dname, false);
	xhttp.send("");
	return xhttp.responseXML;
}