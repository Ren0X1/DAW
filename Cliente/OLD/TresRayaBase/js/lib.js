var $ = (v)=> {
    return document.querySelector(v)
}

var Lib = {}

Lib.turno = true // True Rojas, False Azules

Lib.CargarTablero = function(tablero) {
    let length = (e)=>{
        return e.getElementsByTagName('img').length
    }

    for(let v=0;v!=tablero.length;v++) {
        tablero[v].addEventListener('dragover', function(e) {
            e.preventDefault()
        })

        tablero[v].addEventListener('drop', function(e) {
            e.preventDefault()
            console.log(length(e.target))
            if (length(e.target)<1 && e.target.tagName=='TD') {
                let data = e.dataTransfer.getData("id");
                e.target.appendChild(document.getElementById(data));
            }
        })
    }
}

Lib.CargarFichas = function(fichas) {
    for(let v=0;v!=fichas.length;v++) {
        fichas[v].addEventListener('dragstart', function(e) {
            e.dataTransfer.setData("id", e.target.id);
        })
    }
}

Lib.toogle = function() {
    let fichasR = document.getElementsByClassName('rojo')
    let fichasA = document.getElementsByClassName('azul')


}