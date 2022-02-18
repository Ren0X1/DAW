// JavaScript Document

		var xmlhttp; //peticion
//------------------------------
function creaPeticion()
		{
		
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  	xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");//versiones
		  }
		  
		  return xmlhttp;
		}
			
//------------------------------

	function enviarPeticion(metodo,url,tipo,procesaRespuesta,datos)
		{
		 	// * 1 * creamos el Objeto XMLHttpRequest...
			xmlhttp=creaPeticion();
			
			//Asociamos una funcion manejadora del evento onreadystatechange...
		 	/* 1 */
				xmlhttp.onreadystatechange=procesaRespuesta;
				
			/* 2  abrimos la peticion */
				
			if (metodo=="GET")
			{
				if (datos)
					url+='?'+datos;
				xmlhttp.open("GET",url,tipo);
                                xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				xmlhttp.send("");
			}
			else {
				xmlhttp.open("POST",url,tipo);
				xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");

			//  xmlhttp.setRequestHeader("Content-type","application/json");
			
			/* 3  enviamos la peticion */
				xmlhttp.send(datos);
			}
			
			//return xmlhttp.response;
			
		  }
		
//-------------------------------------------------------------------------------
		
		function aplica_XSL_XML(donde,xml,xsl)
		{
		
		// code for IE
		if (window.ActiveXObject)
		  {
		  ex=xml.transformNode(xsl);
		  document.getElementById(donde).innerHTML=ex;
		  }
		// code for Mozilla, Firefox, Opera, etc.
		else if (document.implementation && document.implementation.createDocument)
		  {
		  xsltProcessor=new XSLTProcessor();
		  xsltProcessor.importStylesheet(xsl);
		  resultDocument = xsltProcessor.transformToFragment(xml,document);
		  
		  salida = document.getElementById(donde);
		  if (salida.hasChildNodes())
			  salida.replaceChild(resultDocument,salida.childNodes[0]);
		  else	  
			salida.appendChild(resultDocument);
		  
		  //document.getElementById(donde).innerHTML=resultDocument;
		  }
		}
/*----------------------------------------------------------------------*/
function loadXMLDoc(dname)
		{
		
		 	xhttp=new XMLHttpRequest();
		 	xhttp.open("GET",dname,false);
			xhttp.send("");
			return xhttp.responseXML;
		  }