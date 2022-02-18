// JavaScript Document
function allowDrop(ev) {
	ev.preventDefault();
}

function drag(ev) {
	ev.dataTransfer.setData("fila", ev.target.rowIndex);
	ev.dataTransfer.setDragImage(document.getElementById("ccd"), 20, 20);
	document.getElementById("ccd").title = listaCds[ev.target.rowIndex - 1].Titulo

}


function dragend() {
	document.getElementById("ccd").style.display = "none";
}

function arrastra(ev) {
	ev.dataTransfer.setData("fila", ev.target.parentNode.parentNode.firstChild.textContent);

}

function drop(ev) {
	document.getElementById("ccd").style.display = "none";
	ev.preventDefault();
	var data = ev.dataTransfer.getData("fila");
	var cds = document.getElementById("cd");
	var cd = listaCds[data - 1].Titulo + "/" + listaCds[data - 1].Artista;
	var precio = listaCds[data - 1].Precio;


	var color = listaCds[data - 1].Color;

	linea = new lineap(cd, precio, 1, color);
	var fila = Object.keys(detallePedido).length;
	while (detallePedido[fila] !== undefined)
		fila++;
	detallePedido[fila] = linea;

	tabla2("Carro de la << Compra >>", "carro", "carrito", detallePedido, {
		borrar: true,
		subtotal: {
			c1: 2,
			c2: 3
		}
	});

	window.localStorage.setItem("detallePedido", JSON.stringify(detallePedido));
	document.body.style.cursor = "default";
}

function creaBotonn() {
	var botones = document.getElementsByTagName("button");
	if (botones) {
		var boton = document.createElement("button");
		var clic = document.createAttribute("onclick");
		var ntexto = document.createTextNode("eliminar");

		boton.appendChild(ntexto);
		return boton;
	} else return botones[0].cloneNode(true);
}

function borraFilaa(boton) {
	boton.parentNode.parentNode.parentNode.removeChild(boton.parentNode.parentNode);
}
(function ($) {
	$.extend({

		creaTabla222: function (titulo, id, donde, datos, estilo) {
			var tabla = document.createElement('table');
			tabla.setAttribute('id', id);
			tabla.setAttribute('border', 1);
			tabla.setAttribute('summary', 'Descripción de la tabla y su contenido');
			var caption = document.createElement('caption');
			var titulo = document.createTextNode(titulo);
			caption.appendChild(titulo);
			tabla.appendChild(caption);

			var thead = document.createElement('thead');
			tabla.appendChild(thead);
			thead.insertRow(0);
			var cabecera;

			try {
				calvesFilas = Object.keys(datos)
				nfilas = calvesFilas.length;

				clavesLinea = Object.keys(datos[calvesFilas["0"]]);

				ncolumnas = clavesLinea.length;
				var i = 0;
				cabecera = document.createElement('th');
				thead.rows[0].appendChild(cabecera);

				for (i = 1; i < ncolumnas; i++) {
					cabecera = document.createElement('th');
					cabecera.innerHTML = clavesLinea[i - 1];
					thead.rows[0].appendChild(cabecera);
				}
				if (estilo.subtotal !== undefined) {
					cabecera = document.createElement('th');
					cabecera.innerHTML = "Subtotal";
					thead.rows[0].appendChild(cabecera);
				}
				var tbody = document.createElement('tbody');
				tabla.appendChild(tbody);

				var suma = 0;
				var i = 0;
				for (clave in datos) {
					var j = 0;
					tbody.insertRow(clave);
					tbody.rows[clave].insertCell(j).innerHTML = clave;
					tbody.rows[clave].ondragstart = drag;

					for (j = 1; j < ncolumnas; j++) {
						tbody.rows[clave].insertCell(j);
						tbody.rows[clave].cells[j].innerHTML = datos[clave][clavesLinea[j - 1]];

					}
					console.log(datos[clave][clavesLinea[ncolumnas - 1]]);
					tbody.rows[clave].setAttribute("style", "color:" + datos[clave][clavesLinea[ncolumnas - 1]]);
					if (estilo.subtotal !== undefined) {
						console.log("ncolumnas:" + ncolumnas);
						tbody.rows[clave].insertCell(ncolumnas);

						tbody.rows[clave].cells[ncolumnas].innerHTML =
							parseFloat(tbody.rows[clave].cells[estilo.subtotal.c1].innerHTML) *
							parseInt(tbody.rows[clave].cells[estilo.subtotal.c2].innerHTML);

						suma += parseFloat(tbody.rows[clave].cells[ncolumnas].textContent);
					}
					if (estilo.borrar) {
						tbody.rows[clave].insertCell(ncolumnas + 1);
						tbody.rows[clave].cells[ncolumnas + 1].appendChild(creaBoton());
					}
					i++;
				}
			} catch (err) {
				txt = "There was an error on this page.\n\n";
				txt += "Error description: " + err.message + "\n\n";
				txt += "Click OK to continue.\n\n";
				alert(txt);
			}

			if (estilo.subtotal) {
				var tfoot = document.createElement('tfoot');
				tabla.appendChild(tfoot);
				tfoot.insertRow(0);
				var cabecera = document.createElement('th');
				cabecera.setAttribute('colspan', thead.rows[0].cells.length - 1);
				cabecera.innerHTML = 'Total:...';
				tfoot.rows[0].appendChild(cabecera);

				cabecera = document.createElement('td');
				cabecera.innerHTML = Math.round(suma) + ' €';
				tfoot.rows[0].appendChild(cabecera);
			}

			var donde = document.getElementById(donde);

			if (donde.childNodes.length > 0)
				donde.replaceChild(tabla, donde.childNodes[0]);
			else
				donde.appendChild(tabla);

		}
	});

})(jQuery);
$(function ($) {
	$.fn.creaTabla21 = tabla2;
	$.creaTabla21 = tabla2;
	$.fn.creaTabla21.defaultOptions = {
		titulo: 'tabla por Defecto...',
		id: 'miTabla',
		donde: 'body',
		datos: {
			1: {
				col1: "celda11",
				col2: "celda12"
			},
			2: {
				col1: "celda21",
				col2: "celda22"
			}
		},
		estilo: {
			borrar: false
		},
		filtro: false
	}

	$.creaTabla21.defaultOptions = {
		titulo: 'tabla por Defecto...',
		id: 'miTabla',
		donde: 'body',
		datos: {
			1: {
				col1: "celda11",
				col2: "celda12"
			},
			2: {
				col1: "celda21",
				col2: "celda22"
			}
		},
		estilo: {
			borrar: false
		},
		filtro: false
	}
});

function tabla2(opt) {
	document.onkeyup = function (evento) {
		var caracter = evento.key;
		evento.preventDefault();
		if (evento.altKey) {
			switch (caracter) {
				case 'F':
				case 'f':
					$(this).find(".filtro").toggle();

					evento.preventDefault();
					break;

			}
		}
	};
	var options = $.fn.creaTabla21.defaultOptions;
	for (opcion in opt)
		options[opcion] = opt[opcion];

	$(this).each(function (indice, nodo) {

		var tabla = document.createElement('table');
		tabla.setAttribute('id', options.id);
		tabla.setAttribute('border', 1);
		tabla.setAttribute('summary', 'Descripción de la tabla y su contenido');
		var caption = document.createElement('caption');
		var titulo = document.createTextNode(options.titulo);
		caption.appendChild(titulo);
		tabla.appendChild(caption);
		if (options.filtro) {
			var pfilter = document.createElement("p");
			pfilter.className = "filtro";
			var filter = document.createElement("input");
			var texto = document.createTextNode("Filtro(alt+f):");
			filter.id = "filtro";
			pfilter.appendChild(texto);
			pfilter.appendChild(filter);
			caption.appendChild(pfilter);
			filter.onkeyup = filtra;
			$(tabla).on("change", "input:checkbox",
				function () {
					$("#filtro").trigger("keyup")
				});
		}
		var thead = document.createElement('thead');
		tabla.appendChild(thead);
		thead.insertRow(0);
		var cabecera;

		try {
			calvesFilas = Object.keys(options.datos)
			nfilas = calvesFilas.length;

			clavesLinea = Object.keys(options.datos[calvesFilas["0"]]);

			ncolumnas = clavesLinea.length;

			var check;
			for (i = 0; i < ncolumnas; i++) {
				cabecera = document.createElement('th');
				cabecera.innerHTML = clavesLinea[i];
				cabecera.pos = i;

				check = document.createElement('input');
				check.type = "checkbox";
				check.checked = "checked";
				check.className = "filtro";
				cabecera.appendChild(check);
				thead.rows[0].appendChild(cabecera);
			}
			if (options.estilo.subtotal !== undefined) {
				cabecera = document.createElement('th');
				cabecera.innerHTML = "Subtotal";
				thead.rows[0].appendChild(cabecera);
			}
			var tbody = document.createElement('tbody');
			tabla.appendChild(tbody);

			var suma = 0;
			var i = 0;
			for (i = 0; i < nfilas; i++) {

				tbody.insertRow(i);

				for (j = 0; j < ncolumnas; j++) {
					tbody.rows[i].insertCell(j);
					tbody.rows[i].cells[j].innerHTML = options.datos[calvesFilas[i]][clavesLinea[j]];

				}
				if (options.estilo.subtotal !== undefined) {
					console.log("ncolumnas:" + ncolumnas);
					tbody.rows[i].insertCell(ncolumnas);

					tbody.rows[i].cells[ncolumnas].innerHTML =
						parseFloat(tbody.rows[i].cells[options.estilo.subtotal.c1].innerHTML) *
						parseInt(tbody.rows[i].cells[options.estilo.subtotal.c2].innerHTML);
					suma += parseFloat(tbody.rows[i].cells[ncolumnas].textContent);
				}
				if (options.estilo.borrar) {
					tbody.rows[i].insertCell(ncolumnas);
					tbody.rows[i].cells[ncolumnas].appendChild(creaBoton());
				}
			}
		} catch (err) {
			txt = "There was an error on this page.\n\n";
			txt += "Error description: " + err.message + "\n\n";
			txt += "Click OK to continue.\n\n";
			alert(txt);
		}
		if (options.estilo.subtotal) {
			var tfoot = document.createElement('tfoot');
			tabla.appendChild(tfoot);
			tfoot.insertRow(0);
			var cabecera = document.createElement('th');
			cabecera.setAttribute('colspan', thead.rows[0].cells.length - 1);
			cabecera.innerHTML = 'Total:...';
			tfoot.rows[0].appendChild(cabecera);

			cabecera = document.createElement('td');
			cabecera.innerHTML = Math.round(suma) + ' €';
			tfoot.rows[0].appendChild(cabecera);
		}
		for (var cab of tabla.tHead.rows[0].cells) {
			cab.addEventListener("click", sortTable2, true);

			cab.onclick = function (ev) {
				ev.stopPropagation();
			}
			cab.draggable = true;
			cab.ondragover = function (ev) {
				ev.preventDefault();
			}

			cab.ondragstart = function (event) {
				event.dataTransfer.setData("pos", event.target.pos);
			}
			cab.ondrop = function (event) {
				event.preventDefault();
				var posA = event.dataTransfer.getData("pos") * 1;
				var posB = event.target.pos;

				var tdsA = tabla.querySelectorAll("th:nth-child(" + (posA + 1) + ")" +
					",td:nth-child(" + (posA + 1) + ")");
				var tdsB = tabla.querySelectorAll("th:nth-child(" + (posB + 1) + ")" +
					",td:nth-child(" + (posB + 1) + ")");
				if (posA < posB) {
					for (let i = 0; i < tdsA.length; i++)
						tdsA[i].parentElement.insertBefore(tdsA[i], tdsB[i].nextElementSibling);
				} else {
					for (let i = 0; i < tdsA.length; i++)
						tdsA[i].parentElement.insertBefore(tdsA[i], tdsB[i]);
				}
				var tdsH = tabla.querySelectorAll("th");
				for (let i = 0; i < tdsH.length; i++)
					tdsH[i].pos = i;
			}
		}

		//tabla filas***************************
		for (var i = 1; i < tabla.rows.length; i++) {
			var fila = tabla.rows[i];
			fila.draggable = true;
			fila.ondragover = function (ev) {
				ev.preventDefault();
			}

			fila.ondragstart = function (event) {
				event.dataTransfer.setData("pos", hermanosMenores(event.target));
				var ofila = filaObjeto(fila, clavesLinea);
				event.dataTransfer.setData("fila", JSON.stringify(ofila));
				event.target.style.backgroundColor = "yellow";
			}
			fila.ondrop = function (event) {
				event.preventDefault();
				var posA = event.dataTransfer.getData("pos") * 1;
				var posB = hermanosMenores(event.currentTarget);

				if (posA < posB) {
					event.currentTarget.parentElement.insertBefore(
						event.currentTarget.parentElement.rows[posA],
						event.currentTarget.nextElementSibling);
				} else {
					event.currentTarget.parentElement.insertBefore(
						event.currentTarget.parentElement.rows[posA],
						event.currentTarget);
				}
				event.currentTarget.style.backgroundColor = "white";

				event.target.parentElement.parentElement.rows[event.dataTransfer.getData("pos") - 1]
					.style.backgroundColor = "white";
			}
		}
		if ($(this)[0].nodeType != 1)
			$("#" + options.donde).empty().append(tabla);
		else
			$(this).empty().append(tabla);

	});
	if ($(this)[0].nodeType != 1)
		return $("#" + options.donde)
	else
		return $(this);

}

function sortTable(ev) {
	var th = ev.target;
	var table = th.parentElement.parentElement.parentElement;
	alert("click en -> " + th.textContent + " pos = " + th.pos +
		" Tabla  -> " + table.summary);
}

function sortTable2(ev) {
	ev.stopPropagation();
	var rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	var th = ev.target;
	var n = th.pos;
	var table = th.parentElement.parentElement.parentElement;

	switching = true;
	dir = "asc";
	if ((th.innerHTML.indexOf("asc") < 0) && (th.innerHTML.indexOf("dsc") < 0))
		th.childNodes[0].textContent = " " + dir + " " + th.childNodes[0].textContent
	while (switching) {
		switching = false;
		rows = table.rows;
		for (i = 1; i < (rows.length - 1); i++) {
			shouldSwitch = false;
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					shouldSwitch = true;
					th.childNodes[0].textContent = th.childNodes[0].textContent.replace("asc", "dsc");
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					shouldSwitch = true;
					th.childNodes[0].textContent = th.childNodes[0].textContent.replace("dsc", "asc");
					break;
				}
			}
		}
		if (shouldSwitch) {
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			switchcount++;
		} else {
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}




/*FILTRO*/
function filtra() {
	let filtro = this.value.toUpperCase()
	let table = $(this).parents("table")[0]
	let tr = table.getElementsByTagName("tr")
	let td = []
	$(tr[0]).find("input[type=checkbox]:checked").each(
		function (i) {
			td[i] = this.parentNode.cellIndex
		}
	)
	$(table.tBodies[0]).find("tr").filter(
		function () {
			let texto = ""
			for (index of td) {
				texto += this.cells[index].textContent
			}
			$(this).toggle(texto.toUpperCase().indexOf(filtro) > -1)
		}
	)
}

function hermanosMenores(nodo) {
	var nh = 0;
	while (nodo.previousSibling) {
		nodo = nodo.previousSibling;
		nh++;
	}
	return nh;
}

function creaBoton() {
	var boton = document.createElement("button");
	var clic = document.createAttribute("onclick");
	var ntexto = document.createTextNode("eliminar");

	boton.appendChild(ntexto);
	clic.nodeValue = "borraFila(this);";
	boton.setAttributeNode(clic);

	return boton;

}

function borraFila(boton) {
	boton.parentNode.parentNode.parentNode.removeChild(boton.parentNode.parentNode);
}

function filaObjeto(fila, claves) {
	var objeto = {};
	for (i in fila.cells)
		objeto[claves[i]] = fila.cells[i].textContent;

	objeto.rowIndex = fila.rowIndex;
	return objeto;

}

$(function ($) {
	$.fn.resalta = function (opt) {
		var options = $.extend({}, $.fn.resalta.defaultOptions, opt);

		return this.css({
			color: options.texto,
			backgroundColor: options.fondo
		});

	};
	$.fn.resalta.defaultOptions = {
		texto: 'red',
		fondo: 'yellow'
	}
});