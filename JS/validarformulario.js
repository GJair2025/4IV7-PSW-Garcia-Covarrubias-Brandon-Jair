/*
Crear una función que se encargue de validar
que el usuario debe escribir 5
*/

function validar(formulario){

    if(formulario.nombre.value.length < 5){
        alert("Escribe más de 5 caracteres en el campo nombre");
        formulario.nombre.focus();
    return false;
    }


    /*para ingresar solo letras*/

    var checkOK = "qwertyuiopasdfghjklñzxcvbnm"
    + "QWERTYUIOPASDFGHJKLÑZXCVBNM";

    var chekStr = formulario.nombre.value;

    var allvalid = true;

    for(var i = 0; i < checkStr.length; i++){
        var ch = chekStr.charAt(i);
        for(var j = 0; j < checkOK.length; j++)
        if(ch == checkOK.charAt(j))
            break;
        if(j == checkOK.length){
            allvalid = false;
            break;
        }
    }

    if(!allvalid){
        alert("Escribe solo letras en el campo nombre");
        formulario.nombre.focus();
        return false;
    }

    /*
    Validar la entrada de un correo
    */

    var txt = formulario.email.value;

    //Patron
    var b = /^[^@\s]+@[^@\.\s]+(\.[^@\.\s]+)+$/;

    alert("Email " + (b.test(txt)?"":" no ")+" valido");

    return b.test(txt);
}
