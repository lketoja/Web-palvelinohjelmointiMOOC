function haeNumero(tunnus) {
    return parseInt(document.getElementById(tunnus).value);
}

function asetaTulos(tulos) {
    document.getElementById("tulos").innerHTML = tulos;
}

function plus() {
    asetaTulos(haeNumero("eka") + haeNumero("toka"));
}

function kerto() {
    asetaTulos(haeNumero("eka") * haeNumero("toka"));
}

function miinus(){
    asetaTulos(haeNumero("eka") - haeNumero("toka"));
}
