function decrementar(k) {
    var v = k
    if (v.nextElementSibling.value!=v.nextElementSibling.min) {
        v.nextElementSibling.value--
    }
}

function aumentar(k) {
    var v = k
    if (v.previousElementSibling.value!=v.previousElementSibling.max) {
        v.previousElementSibling.value++
    }
}