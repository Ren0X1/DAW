//JS
window.onload = function() {
    var botonT = document.getElementById('botonT')
    var botonB = document.getElementById('botonB')
    var input = document.getElementById('input')
    var pT = document.getElementById('pTraducido')
    var pB = document.getElementById('pBinario')

    botonT.onclick = function() {
        pT.innerHTML=""
		pB.innerHTML=""
        let value = input.value+""
        if (value!=null && value!="") {
            for (let i=0;i!=value.length;i++) {
                let n = value.charCodeAt(i).toString(2)
                pT.innerHTML+=n+" "
            }
        }
    }

    botonB.onclick = function() {
        pB.innerHTML=""
        let value = pT.innerHTML
        if (value!=null && value!="") {
            let array = value.split(" ")
            array.forEach(function(x) {
                pB.innerHTML+=String.fromCharCode(parseInt(x,2))
            })
        }
    }
}