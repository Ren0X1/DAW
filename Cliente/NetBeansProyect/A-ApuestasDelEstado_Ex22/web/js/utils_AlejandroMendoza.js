/*VARIABLES GLOBALES*/

let boleto;
let boletos__ = {}

var UTILS__ = (function() {

    class creaBoleto {
        constructor(nombre, apuestas) {
            this.nombre = nombre
            this.fecha = (new Date).toLocaleString()
            this.apuestas = apuestas
        }
    }

    class apuesta {
        constructor(nombre, ctd, numeros) {
            this[nombre] = {}
            this[nombre].numeros = numeros
            this[nombre].ctd = ctd
        }
    }

    function combinacion(etiqueta, ctd, min, max) {
        var combina = [];
        var i = 0;
        while (combina.length < ctd) {
            var num = getRandomInt(min, max);
            if (!combina.includes(num)) {
                combina[i] = num;
                i++
            }
        }
        combina.sort(function (a, b) {
            return a - b
        });
        return combina;
    }
    
    function getRandomInt(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    function cambiaPanel(id) {
        var marco = document.getElementById(id)
        jugar.style.display = comprobar.style.display = editar.style.display = listar.style.display = "none"
        marco.style.display = "block"
    }

    function pintarApuesta(array,x,nombreApuesta){
        let donde =	$(x);
        donde[0].innerHTML = "";
            
        donde[0].innerHTML = donde[0].innerHTML+"<br><img src='iconos/"+nombreApuesta+".jpg'>"+nombreApuesta+"<br>"+array.apuestas.length+"  APUESTAS<hr>";

        for (let i=0;i!=array.apuestas.length;i++) {
            donde[0].innerHTML = donde[0].innerHTML + (i+1) + "."
            Object.keys(array.apuestas[i]).forEach(function(key) {
                donde[0].innerHTML = donde[0].innerHTML+"- "+key+":"+array.apuestas[i][key].numeros
            })
            donde[0].innerHTML = donde[0].innerHTML + "<br>"
        }

        Object.keys(array).forEach(function(key) {
            if (
                key!='nombre' &&
                key!='fecha' &&
                key!='apuestas'
            ) {
                donde[0].innerHTML = donde[0].innerHTML + "<br>"+key+":"+array[key].numeros+"<hr>"
            }
        })

        donde[0].innerHTML = donde[0].innerHTML+"<br>"+array.fecha+"<br><br>";
    }

    function calcularApuestas() {
        let out = $('#salida')
        out.innerHTML = ""
        let lista = document.getElementById("listaApu")
        let n = $('#numApuestas')
        let totalApuestas = []
        let nApuestas  = n.val()
        let contructorApuestas = lista.value.replace("(","('" + lista[lista.selectedIndex].text +"',"); 
        
        boleto = new creaBoleto(lista[lista.selectedIndex].innerHTML,totalApuestas)
        
        for(let j=0;j!=nApuestas;j++) {
            eval(contructorApuestas)
        }

        pintarApuesta(boleto,"#salida",boleto.nombre)
    }

    function Apuesta(){
        let combinacion_ = undefined
        let args = arguments
        var apuesta_ = {}
        var apuestasFinales_ = {}

        for(let i=1;i!=args.length;i=i+5) {
            combinacion_ = combinacion(
                args[i],
                args[i+2],
                args[i+3],
                args[i+4]
            )
            
            apuesta_ = new apuesta(
                args[i],
                args[i+2],
                combinacion_
            )

            if(args[i+1]=='A') {
                apuestasFinales_[args[i]] = apuesta_[args[i]]
            } else {
                boleto[args[i]] = apuesta_[args[i]]
            }
            boleto.apuestas[boleto.apuestas.length] = apuestasFinales_
            return boleto
        }
    }
    
    function guardarPartida() {
        $.ajax({
            type: 'POST',
            dataType: 'JSON',
            async: true,
            url: "GuardaPanel",
            data:{'jugada': JSON.stringify(boleto)},
            success: function (data) {
                alert(data.guardado)
            }
        })
    }

    function leerBoletos() {
        $.getJSON("RecuperaPaneles", {
            fichero: "boletos.txt"
        }, function (data) {
            $.each(data, function(index,v) {
                boletos__[index] = JSON.parse(v)
            })
            cargaSelect('listaApuTipo',boletos__)
        })
    }

    function cargaSelect(id,datos) {
        let l = document.getElementById(id)
        
        Object.keys(datos).forEach(function(k) {
            l.add(new Option(datos[k].fecha,k))
        })
    }

    function altaApuesta() {
        $('#divTabla').hide()
        if ($('#divmiForm').css('display') == 'none') {
            $('#divmiForm').show()
        } else {
            $('#divmiForm').hide()
        }
    }

    function newCombination() {
        let field = document.getElementById('c1').cloneNode(true)
        let fields = $('fieldset')

        field.firstElementChild.innerText= " Combinación "+(fields.length)
        field.querySelectorAll("[type=radio]")[0].name="ab"+(fields.length-1)
        field.querySelectorAll("[type=radio]")[1].name="ab"+(fields.length-1)

        $('#insertar').append(field)
    }

    function borrar(v) {
        $(v).remove()
    }

    function crearApuesta(v) {
        let serial = $(v).serialize()
        let partes = serial.split("&")
        let cadenaFinal = "Apuesta({0})"
        let cadenaFinal_ = ""
        let cadenaFinal__ = ""
        for(let i=1;i!=partes.length;i++) {
            let x = partes[i].split("=")
            let fix = x[1]
            if (isNaN(fix)) {
                fix = '\''+x[1]+'\''
            }
            cadenaFinal_+=fix+","
        }
        cadenaFinal__ = cadenaFinal.replace('{0}', cadenaFinal_).replace(',)',')')
        let select = document.getElementById('listaApu')
        select.add(new Option(partes[0].split("=")[1],cadenaFinal__))
    }

    function mostrarTabla() {
        $('#divTabla').toggle()
        if ($('#divTabla').css('display') == 'block') {
            pintarTabla()
        }
    }

    function pintarTabla() {
        let obj = {}
        for(v of document.getElementById('listaApu').options) {
            obj[v.text] ={}
            let c =v.value.substring(9,v.value.length-1).replace(/[']/g,'').split(",")
            for(let i=0;i!=c.length;i=i+5) {
                obj[v.text][i] = {}
                obj[v.text][i].Nombre = c[i]
                obj[v.text][i].Cantidad_N = c[i+1]
                obj[v.text][i].min = c[i+2]
                obj[v.text][i].max = c[i+3]
                obj[v.text][i].Boleto_Apuesta = c[i+4]
            }
        }
        $('#divTabla').creaTabla21("Apuestas del estado", "apuesta", "Apuestas", obj, {borrar: false})
    }

    function editarDatos() {
        $('#divTabla').hide()
        if ($('#divmiForm').css('display') == 'none') {
            $('#divmiForm').show()
        } else {
            $('#divmiForm').hide()
        }
    }

    /**
    *  Metodos publicos
    */
    return {
        cambiarPanel: cambiaPanel,
        crearBoleto: creaBoleto,
        crearApuesta: apuesta,
        save: guardarPartida,
        mostrarTabla: mostrarTabla,
        editarDatos: editarDatos,
        crearApuesta: crearApuesta,
        load: leerBoletos,
        borrar: borrar,
        altaApuesta: altaApuesta,
        newCombo: newCombination,
        pintar: pintarApuesta,
        calcularApuestas: calcularApuestas
    }
})();
