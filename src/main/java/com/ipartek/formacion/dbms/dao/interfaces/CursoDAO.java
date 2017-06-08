/**
 * 
 */
package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Curso;

/**
 * Esta interfaz genera los metodos necesarios de acceso a la BBDD
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
public interface CursoDAO extends DAOSetter {
	
	/**
	 * Crea un nuevo curso pasandoselo como parametro
	 * @param curso curso que crear
	 * @return curso nuevo
	 */
	public Curso create(Curso curso);
	
	/**
	 * Muestra todos los cursos
	 * @return List<Curso> Devuelve la lista de cursos
	 */
	public List<Curso> getAll();
	
	/**
	 * Muestra el curso con el codigo entregado como parametro
	 * @param codigo Codigo del curso que queremos ver
	 * @return Curso con ese codigo
	 */
	public Curso getById(int codigo);
	
	/**
	 * Actualizamos un curso
	 * @param curso Curso que queremos modificar
	 * @return Curso modificado
	 */
	public Curso update(Curso curso);
	
	/**
	 * Elimina el curso seleccionado por su codigo
	 * @param codigo Codigo del curso que queremos eliminar
	 */
	public void delete(int codigo);
}
