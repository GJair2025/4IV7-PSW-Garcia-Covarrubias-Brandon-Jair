/*
Vamnos a crear una función que se encargue de validar
 a través de una expresión regular la estrada de datos
 dentro del campo de texto  */

function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;

    if(teclado == 8) return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);

    return patron.test(prueba);
}

function interes(){

    var valor = document.formulario.cantidad.value;
    var resultado = parseInt(valor);
    var interes = resultado*0.02;
    var total = resultado + interes;
}