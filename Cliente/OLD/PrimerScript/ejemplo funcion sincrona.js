boton.onclick = async function() {
    for (let i=0;i!=50+1;i++) {
        await sleep(50)
    } 
}
function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}