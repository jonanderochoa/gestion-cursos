/**
 * 
 */
package com.ipartek.formacion.api.restfulservers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

/**
 * Este controlador se encarga de gestionar un servicio REST de lado del servidor.
 * Contiene los metodos necesarios para ver los cursos del buscador
 * @author Jon Ander Ochoa Ruiz
 * 13 de jun. de 2017
 */
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/buscador")
public class BuscadorRestController {

	@Inject
	CursoService cS;
	
	/**
	 * Metodo encargado de mostrar los cursos busqueda por codigo
	 * @param busqueda Introduce la cadena que desea buscar
	 * @return Devuelve el o los cursos que coinciden y el codigo http
	 */
	@RequestMapping(value = "/{busqueda}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Curso>> getByBusqueda(@PathVariable("busqueda") String busqueda){
		List<Curso> cursos = cS.getByNombre(busqueda);
		ResponseEntity<List<Curso>> response = null;
		if(cursos == null || cursos.isEmpty()){//204
			response = new ResponseEntity<List<Curso>>(HttpStatus.NO_CONTENT);
		}else{//200
			response = new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
		}
		return response;
	}
}
