// JavaScript Document
	function allowDrop(ev) {
   	 ev.preventDefault();
	}

	function drag(ev) {
 	  // ev.dataTransfer.setData("text", ev.target.id);
	   ev.dataTransfer.setData("fila", ev.target.rowIndex);
	   ev.dataTransfer.setDragImage(document.getElementById("ccd"), 20, 20);
           document.getElementById("ccd").title=listaCds[ev.target.rowIndex-1].Titulo
          
	}
	
	
	function dragend(){
		document.getElementById("ccd").style.display="none";
	}
	
	//-------------------------------------------------
	function arrastra(ev) {
 	   ev.dataTransfer.setData("fila", ev.target.parentNode.parentNode.firstChild.textContent);
	 
	}
	//------------------------------------------------

	function drop(ev) {
		document.getElementById("ccd").style.display="none";
  	  ev.preventDefault();
   	 var data = ev.dataTransfer.getData("fila");
	// alert("fila="+data);
   	// ev.target.appendChild(document.getElementById(data)); inserta fila listaCds[data]
     
	 	var cds=document.getElementById("cd");
		var cd =listaCds[data-1].Titulo+"/"+listaCds[data-1].Artista;
		var precio =listaCds[data-1].Precio;
			
		
		var color=listaCds[data-1].Color;
		
	  linea = new lineap(cd,precio,1,color);
	  detallePedido[Object.keys(detallePedido).length+1]=linea;
	  
	 /* tabla2("Carro de la << Compra >>","carro","carrito",detallePedido,
	  			{borrar:true,subtotal:true});*/
          tabla2("Carro de la << Compra >>","carro","carrito",detallePedido,
	  			//{borrar:true,subtotal:true});
                                {borrar:true,subtotal:{c1:2,c2:3}});                      

	  window.localStorage.setItem("detallePedido",JSON.stringify(detallePedido));	
	 document.body.style.cursor="default";
	}
//------------------------------------------------
		function creaBoton()
		{
		    var botones =document.getElementsByTagName("button");
			if(botones)
			{
		   		var boton = document.createElement("button");
				var clic = document.createAttribute("onclick");
				var ntexto = document.createTextNode("eliminar");
			
				boton.appendChild(ntexto);
			
					clic.nodeValue="borraFila(this);";
				boton.setAttributeNode(clic);
				return boton;
			}
			else return botones[0].cloneNode(true);
		}
//------------------------------------------------
		function borraFila(boton)
		{
		 boton.parentNode.parentNode.parentNode.removeChild(boton.parentNode.parentNode);		
		}
//---------------------------------------------------
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
	var i =0;	
		cabecera = document.createElement('th');
		//cabecera.style.visibility="hidden";
		//cabecera.style.display="none";  Oculto
		thead.rows[0].appendChild(cabecera);
		
	for (i=1; i<=ncolumnas ; i++)
	{
		cabecera = document.createElement('th');
		cabecera.innerHTML = clavesLinea[i-1];
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
	for(clave in datos)
	//for (i=1; i<=nfilas ; i++)
	{
		// columnna oculta con el numero de fila
		var j=0;
		tbody.insertRow(i);
		tbody.rows[i].insertCell(j).innerHTML =i;
                tbody.rows[i].ondragstart=drag;////////////
		//tbody.rows[i].cells[j].width=0;
		//tbody.rows[i].cells[j].style.visibility="hidden";
		//tbody.rows[i].cells[j].style.display="none";Oculto
		
		for (j=1; j<=ncolumnas ; j++)
			{
				tbody.rows[i].insertCell(j);
				//tbody.rows[i].cells[j].ondragstart=arrastra;
												//datos[i+1][clavesLinea[j]];	
				//tbody.rows[i].cells[j].innerHTML = datos[calvesFilas[clave]][clavesLinea[j]];
				tbody.rows[i].cells[j].innerHTML = datos[clave][clavesLinea[j-1]];
				
			}
			//coloreamos la fila;
			console.log(datos[clave][clavesLinea[ncolumnas-1]]);
			tbody.rows[i].setAttribute("style","color:"+datos[clave][clavesLinea[ncolumnas-1]]);
			
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
		i++;	
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
  /*var donde= document.getElementById(donde);
  
   if (donde.childNodes.length>0)
	  	donde.replaceChild(tabla,donde.childNodes[0]);
	else		
		donde.appendChild(tabla);
	//document.body.appendChild(tabla);*/
        $(this).html(tabla);
}
//------------------------------------------------
    (function($){  
        $.fn.extend({
           //creaTabla:function(titulo,id,donde,datos,estilo)
           creaTabla:tabla2
          
     
        });
    })(jQuery);  

