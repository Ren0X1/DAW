//--------------------------------------------------------------------------------
// JavaScript Document
//---------------------------------------------------

		function creaBoton(datos)
		{
			var boton = document.createElement("button");
			
			var ntexto = document.createTextNode("eliminar");
							
				boton.appendChild(ntexto);
							
				boton.addEventListener('clic', 
						function(){borraFila(datos);},
								false);
				return boton;
		}
//--------------------------------------------------------------------------------
		function borraFila(datos){
			delete datos[boton.parentNode.parentNode.cells[0]];
			this.parentNode.parentNode.remove();	
		}
//--------------------------------------------------------------------------------

//------------- Tabla  -----------------------------------------------------------
	function creaTabla(param, datos){
		

	// Crear <table> y sus dos atributos
	var tabla = document.createElement('table');
	tabla.setAttribute('id', 'otraTabla');
	tabla.setAttribute('border', 1);
	tabla.setAttribute('summary', 'Descripción de la tabla y su contenido');

	// Crear <caption> y añadirlo a la <table>
	var caption = document.createElement('caption');
	var titulo = document.createTextNode(param.titulo);
	caption.appendChild(titulo);
	tabla.appendChild(caption);

	// Crear sección <thead>
	var thead = document.createElement('thead');
	tabla.appendChild(thead);

	// Añadir una fila a la sección <thead>
	thead.insertRow(0);

	var filas=Object.keys(datos);
	var columnas= Object.keys(datos[filas[0]]);

	// Añadir las columnas de la fila de <thead>    
		
	// columna extra...........................................	
	 cabecera = document.createElement('th');
	 thead.rows[0].appendChild(cabecera);	

	for(col of columnas){
	 var cabecera = document.createElement('th');
	 cabecera.innerHTML = col;
	 thead.rows[0].appendChild(cabecera);   
	   
	}    
		

	// La sección <tfoot> se crearía de forma similar a <thead>

	// Crear sección <tbody>
	var tbody = document.createElement('tbody');
	tabla.appendChild(tbody);

	// Añadir una fila a la sección <tbody>
	// Recorrer las filas del objeto-----------------------------
		
		var f=0;var c=0;
		for(fila of filas){
			tbody.insertRow(f);
			// columna oculta con la clave de la fila....
				tbody.rows[f].insertCell(c);
				tbody.rows[f].cells[c].innerHTML = fila;
			c=1;
			for(col of columnas){
				tbody.rows[f].insertCell(c);
				tbody.rows[f].cells[c].innerHTML = datos[fila][col];
				c++;
				
			}
			 if(param.borrar){
				tbody.rows[f].insertCell(c);
				 tbody.rows[f].cells[c].appendChild(creaBoton(datos));
			}
			f++;c=0;
		}
		// borrar...........
	   
		document.getElementById(param.donde).appendChild(tabla);
  
}
//---------------------------------------------------------------------------------

//--------------------------------------------------------------------------------		