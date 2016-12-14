 
	function validacion(){
	var email = document.getElementById("email").value;
	var contras1 = document.getElementById("contra1").value;
	var contras2 = document.getElementById("contra2").value;
	var nombre = document.getElementById("nomusu").value;
	var apellido = document.getElementById("apellidos").value;
	var ciudad = document.getElementById("prov").value;
	
	var expresion = /^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i;
	
	if (email.length == 0){
		alert("Por favor introduce tu dirección de correo electrónico");
		return false;
	}
	if (!expresion.test(email)){
		alert("Por favor introduce una dirección de correo electrónico válida");
		return false;
	}
	
	if (contras1.length == 0){
		alert("Por favor introduce tu contraseña");
		return false;
	}
	if (contras2.length == 0){
		alert("Por favor confirma tu contraseña");
		return false;
	}
	if(contras1 != contras2){
		alert("Las contraseñas no son iguales. Por favor, introduzca la misma contraseña en ambos campos");
		return false;
	}
	if (nombre.length == 0){
		alert("Por favor introduce tu nombre");
		return false;
	}
	if (apellido.length == 0){
		alert("Por favor introduce tus apellidos");
		return false;
	}
	if (ciudad.length == 0) {
        alert("Por favor introduce tu ciudad de residencia");
		return false;
    }
	//alert("Registro completado con éxito")
	return true;
	}
	
	
