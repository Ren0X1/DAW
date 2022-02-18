//JS
window.onload = function() {
    var pregunta = window.confirm('Quieres decirme tu nombre?')
    if (pregunta) {
        var nombre = window.prompt('Dime cual es tu nombre')
        if (nombre!=null) {
            if (nombre.length!=0) {
                var date = new Date()
                var momento
                if (date.getHours() >= 6 && date.getHours() < 14) {
                    momento = 'mañanas'
                } else if (date.getHours() >= 14 && date.getHours() <= 20) {
                    momento = 'tardes'
                } else if (date.getHours() > 20 && date.getHours() < 6) {
                    momento = 'noches'
                }
                document.write('Hola '+nombre+" buenas "+momento)
            } else {
                document.write('Adios, Desconocido')    
            }
        } else {
            document.write('Adios, Desconocido')    
        }
    } else {
        document.write('Adios, Desconocido')
    }
}