/**
 * 
 */
package com.ipartek.formacion.dbms.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.CursoDAO;
import com.ipartek.formacion.dbms.persistence.Curso;

/**
 * Clase encargada de implementar los metodos de CRUD de la interfaz CursoDAO
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
@Repository("cursoDAOImp")
public class CursoDAOImp implements CursoDAO {

	//Para la conexion con la BBDD
	private DataSource dataSource;
	//Para la lectura (getAll y getById)
	private JdbcTemplate jdbctemplate;
	private SimpleJdbcCall jdbcCall;
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoDAOImp.class);
	
	@Override
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Curso create(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Curso> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso getById(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Curso update(Curso curso) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("deleteCurso");
		//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "";
		//Le pasamos la cadena con el nombre del procedimiento almacenado a la instancia de SimpleJdbcCall
		jdbcCall.withProcedureName(SQL);
		//Creamos un mapa con los parametros del procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		//Ejecutamos la consulta dandole los parametros del mapa in
		jdbcCall.execute(in);
	}
}
