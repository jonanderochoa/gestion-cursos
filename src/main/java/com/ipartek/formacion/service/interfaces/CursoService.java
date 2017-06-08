/**
 * 
 */
package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Curso;

/**
 * Interfaz que contiene las funcionalidades de la entidad Curso
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
public interface CursoService {

	/**
	 * Crea un nuevo curso con el objeto curso que le entregamos
	 * @param curso que le entregamos
	 * @return curso que devuelve
	 */
	public Curso create(Curso curso);
	
	/**
	 * Devuelve una lista con todos los curso
	 * @return List<Curso>
	 */
	public List<Curso> getAll();
	
	/**
	 * Devuelve el curso que contiene un codigo especifico
	 * @param codigo del curso que queremos ver
	 * @return curso que devuelve
	 */
	public Curso getById(int codigo);
	
	/**
	 * Sustituye el curso por el que le entregamos
	 * @param curso que le entregamos
	 * @return curso que devuelve
	 */
	public Curso update(Curso curso);
	
	/**
	 * Elimina el curso con ese codigo
	 * @param codigo del elemento que desea eliminar
	 */
	public void delete(int codigo);
}
