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
//function loadXMLDoc(dname)
	function enviarPeticion(metodo,url,tipo,procesaRespuesta,datos)
		{
		 	//creamos el Objeto XMLHttpRequest...
			xmlhttp=creaPeticion();
			
			//Asociamos una funcion manejadora del evento onreadystatechange...
		 	/* 1 */
				xmlhttp.onreadystatechange=procesaRespuesta;
			/* 2 
				xhttp.addEventListener('readystatechange',procesaRespuesta); ***/
			if (metodo=="GET")
			{
				if (datos)
					url+='?'+datos;
				xmlhttp.open("GET",url,tipo);
				xmlhttp.send("");
			}
			else {
				xmlhttp.open("POST",url,tipo);
				xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                                //xmlhttp.setRequestHeader("Content-type","multipart/form-data");
                    //    xmlhttp.setRequestHeader("Content-Type",
                    //                    "application/x-www-form-urlencoded; charset=UTF-8");
                    //	xmlhttp.setRequestHeader("Content-type","application/json");
				xmlhttp.send(datos);
			}
			
			//return xmlhttp.response;
			
		  }
//----- gestor de eventos----------------*/
		
function procesaRespuesta2()
		  {
		  if ((xmlhttp.readyState==4 )&& (xmlhttp.status==200) )
			{// procesar el xml con js o aplicar XSL
			alert(xmlhttp.response);
		   		document.getElementById("res").innerHTML=xmlhttp.response;
				//donde.innerHTML +=xmlhttp.response;
				//return xmlhttp.response;//XML;
                                  cab.innerHTML= xmlhttp.getAllResponseHeaders();
			}
			else {
		 alert ("Error en la peticion State / status="+ xmlhttp.readyState + " /" +xmlhttp.status);
			}
		  }
		
//----------------------------------
		
		function displayResult(donde,xml,xsl)
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
		  document.getElementById(donde).innerHTML="";
		  document.getElementById(donde).appendChild(resultDocument);
		  
		  //document.getElementById(donde).innerHTML=resultDocument;
		  }
		}
/*------------------------------*/
function loadXMLDoc(dname)
		{
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
		 	xhttp=new XMLHttpRequest();
		 	xhttp.open("GET",dname,false);
			xhttp.send("");
			return xhttp.responseXML;
		  }
//-----------------------------------------
//------------- Tabla 2 ----------------------------------------------------------
function tabla2(titulo,id,donde,datos,estilo){ //{borrar:true, Subtotal:{c1:,c2}}
// Crear <table> y sus dos atributos
	var tabla = document.createElement('table');
	tabla.setAttribute('id', id);
	tabla.setAttribute('border', 1);
	tabla.setAttribute('summary', 'Descripción de la tabla y su contenido');
	// Crear <caption> y añadirlo a la <table>
	var caption = document.createElement('caption');
	var titulo = document.createTextNode(titulo);
	caption.appendChild(titulo);
	tabla.appendChild(caption);
	
	
	// Crear sección <thead>
	var thead = document.createElement('thead');
	tabla.appendChild(thead);
	// Añadir una fila a la sección <thead>
	thead.insertRow(0);
	// Añadir las tres columnas de la fila de <thead>
	var cabecera;
	
	try{
	calvesFilas = Object.keys(datos)	
	nfilas	 	= calvesFilas.length;
	
	clavesLinea = Object.keys(datos[calvesFilas["0"]]);// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//clavesLinea = Object.keys(datos["1"]);
	
	ncolumnas	= clavesLinea.length;	
	// cabecera oculta
	//var i =0;	
	//	cabecera = document.createElement('th');
		//cabecera.style.visibility="hidden";
		//cabecera.style.display="none";  Oculto
	//	thead.rows[0].appendChild(cabecera);
		
	for (i=0; i<ncolumnas ; i++)
	{
		cabecera = document.createElement('th');
		cabecera.innerHTML = clavesLinea[i];
		thead.rows[0].appendChild(cabecera);
	}
	// Insertamos la columna Subtotal...............
	//if(estilo.subtotal){
            if(estilo.subtotal!==undefined){
		cabecera = document.createElement('th');
		cabecera.innerHTML = "Subtotal";
		thead.rows[0].appendChild(cabecera);
	}
	
	// Crear sección <tbody>
	var tbody = document.createElement('tbody');
	tabla.appendChild(tbody);
		
	var suma=0;
//	for (i=0; i<calvesFilas.length ; i++)//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    var i =0;
	//for(clave in datos)
	for (i=0; i<nfilas ; i++)
	{
		// columnna oculta con el numero de fila
		//var j=0;
		//tbody.insertRow(clave); 
                tbody.insertRow(i); 
		//tbody.rows[clave].insertCell(j).innerHTML =clave;// reeemplazar i por clave->rowIndex
                //tbody.rows[clave].ondragstart=drag;////////////
		//tbody.rows[i].cells[j].width=0;
		//tbody.rows[i].cells[j].style.visibility="hidden";
		//tbody.rows[i].cells[j].style.display="none";Oculto
		
		for (j=0; j<ncolumnas ; j++)
			{
				tbody.rows[i].insertCell(j);
				//tbody.rows[i].cells[j].ondragstart=arrastra;
												//datos[i+1][clavesLinea[j]];	
				tbody.rows[i].cells[j].innerHTML = datos[calvesFilas[i]][clavesLinea[j]];
				//tbody.rows[clave].cells[j].innerHTML = datos[clave][clavesLinea[j]];
				
			}
			//coloreamos la fila;
			/*console.log(datos[clave][clavesLinea[ncolumnas-1]]);
			tbody.rows[clave].setAttribute("style","color:"+datos[clave][clavesLinea[ncolumnas-1]]);*/
			
			// Insertamos la columna Subtotal...............
			//if(estilo.subtotal){
                        if(estilo.subtotal!==undefined){
			console.log("ncolumnas:"+ncolumnas);
			tbody.rows[i].insertCell(ncolumnas);
						
			tbody.rows[i].cells[ncolumnas].innerHTML =
                                                    parseFloat(tbody.rows[i].cells[estilo.subtotal.c1].innerHTML)
						    *parseInt(tbody.rows[i].cells[estilo.subtotal.c2].innerHTML);
							/*	    parseFloat(tbody.rows[i].cells[3].innerHTML)
								   *parseInt(tbody.rows[i].cells[4].innerHTML);*/
								   
		 	suma+=parseFloat(tbody.rows[i].cells[ncolumnas].textContent);
			}
			// Insertamos la columna borrar...............
			if(estilo.borrar){
				tbody.rows[i].insertCell(ncolumnas+1);
				tbody.rows[i].cells[ncolumnas+1].appendChild(creaBoton());
			}
		//i++;	
	}
	}catch(err)
	  {
	  txt="There was an error on this page.\n\n";
	  txt+="Error description: " + err.message + "\n\n";
	  txt+="Click OK to continue.\n\n";
	  alert(txt);
	  }
	 
	 // tfoot.........................
	 // Crear sección <tfoot>
	 if(estilo.subtotal){
		var tfoot = document.createElement('tfoot');
		tabla.appendChild(tfoot);
		// Añadir una fila a la sección <thead>
		tfoot.insertRow(0);
		// Añadir las dos columnas de la fila de <thead>
		var cabecera = document.createElement('th');
		cabecera.setAttribute('colspan', thead.rows[0].cells.length-1);
		cabecera.innerHTML = 'Total:...';
		tfoot.rows[0].appendChild(cabecera);
		
		cabecera = document.createElement('td');
		cabecera.innerHTML =Math.round(suma)+ ' €';
		tfoot.rows[0].appendChild(cabecera);
	 }
	 
//  var donde= document.getElementById(arguments[arguments.length-1]);
  var donde= document.getElementById(donde);
  
   if (donde.childNodes.length>0)
	  	donde.replaceChild(tabla,donde.childNodes[0]);
	else		
		donde.appendChild(tabla);
	//document.body.appendChild(tabla);
}
