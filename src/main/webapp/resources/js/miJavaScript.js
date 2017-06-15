
/**
 * Javascript que se encarga de la consumir el servicio web del tiempo
 */
$(document).ready(function() {
	//Al pulsar el boton del form
	$("#busca").on("click", (function(){
		//Oculta los ultimos 10 cursos
		$("#diezUltimos").slideUp();
	    //Guarda el valor del input
		var busqueda = $("#busqueda").val();
		//Construimos la url con el parámentro
		const url ="http://localhost:8080/gestioncursos/api/buscador/"+busqueda;
		  $.ajax({
		  url : url,
		  dataType : "json",
		  success : function(data) {
			  $("#resultado tr").remove();
			  for(var i = 0; i < data.length; i++){
				  var row = "<tr><td>"+data[i].codcurso+"</td><td>"+data[i].nomcurso+"</td></tr>";
				  $("#resultado").append(row);
			  }
			  
		  }
		  });
	}));
});



               
  


