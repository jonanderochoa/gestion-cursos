/**
 * 
 */
package com.ipartek.formacion.dbms.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.CursoDAO;
import com.ipartek.formacion.dbms.mapper.CursoMapper;
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
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoDAOImp.class);
	
	/**
	 * Al inyectar el codigo del setDataSource() va al root-context donde genera un objeto
	 * (bean) de la conexion dataSource y lo guarda en la variable que hemos creado mas arriba
	 * @param dataSource
	 */
	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Curso create(Curso curso) {
		LOGGER.info("create");
		final String SQL = "cursoCreate";
		//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		//Se le asigna el nombre del procedimiento almacenado 
		jdbcCall.withProcedureName(SQL);
		/*
		 * Le pasamos los valores de java a SQL en un mapa escepto codigo que lo recogemos
		 * una vez creado el curso en el procedimiento almacenado (ya que lo autogenera) 
		 */
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", curso.getCodigo())
				.addValue("pnomcurso", curso.getNomcurso())
				.addValue("pcodcurso", curso.getCodcurso());
		LOGGER.info(curso.toString());
		//jdbcCall.execute(in); Ejecuta la consulta dandole los parametros del mapa in y devuelve el out
		//Recogemos los datos que devuelve la consulta con otro mapa llamado out
		Map<String, Object> out = jdbcCall.execute(in);
		//Recogemos el codigo del procedimiento almacenado y lo pasamos a java
		curso.setCodigo((Integer)out.get("pcodigo"));
		return curso;
	}

	@Override
	public List<Curso> getAll() {
		LOGGER.info("getAll");
		final String SQL = "call cursogetAll()";
		List<Curso> cursos = null;
		try{
			cursos = jdbcTemplate.query(SQL, new CursoMapper());
			LOGGER.info(cursos.toString());
		}catch(EmptyResultDataAccessException e){
			cursos = null;
			LOGGER.error(e.getMessage());
		}
		return cursos;
	}

	@Override
	public Curso getById(int codigo) {
		LOGGER.info("getById");
		Curso curso = null;
		final String SQL = "call cursogetById(?);";
		try{
			curso = jdbcTemplate.queryForObject(SQL, new CursoMapper(), new Object[] { codigo });
			LOGGER.info(curso.toString());
		}catch(EmptyResultDataAccessException e){
			curso = null;
			LOGGER.error("No se ha encontrado usuario para codigo: "+codigo+" "+e.getMessage());
		}
		return curso;
	}

	@Override
	public Curso update(Curso curso) {
		LOGGER.info("update");
		final String SQL = "cursoUpdate";
		//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		this.jdbcCall = new SimpleJdbcCall(dataSource);//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		jdbcCall.withProcedureName(SQL);//Se le asigna el nombre del procedimiento almacenado 
		//Le pasamos los valores de java a SQL mediante un mapa
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", curso.getCodigo())
				.addValue("pnomcurso", curso.getNomcurso())
				.addValue("pcodcurso", curso.getCodcurso());
		LOGGER.info(curso.toString());
		//Ejecutamos la consulta dandole los parametros del mapa in
		jdbcCall.execute(in);
		return curso;
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("cursoDelete");
		//Se crea una instancia de SimpleJdbcCall pasandole la conexion
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		final String SQL = "cursoDelete";
		//Le pasamos la cadena con el nombre del procedimiento almacenado a la instancia de SimpleJdbcCall
		jdbcCall.withProcedureName(SQL);
		//Creamos un mapa con los parametros del procedimiento almacenado
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		//Ejecutamos la consulta dandole los parametros del mapa in
		jdbcCall.execute(in);
	}

	@Override
	public List<Curso> diezUltimos() {
		LOGGER.info("diezUltimosDAO");
		final String SQL = "call cursoDiezUltimos()";
		List<Curso> cursos = null;
		try{
			cursos = jdbcTemplate.query(SQL, new CursoMapper());
			LOGGER.info(cursos.toString());
		}catch(EmptyResultDataAccessException e){
			cursos = null;
			LOGGER.error(e.getMessage());
		}
		return cursos;
	}

	@Override
	public Curso getByCodigo(String codigo) {
		LOGGER.info("getByCodigoDAO");
		Curso curso = null;
		final String SQL = "call cursogetByCodigo(?);";
		try{
			curso = jdbcTemplate.queryForObject(SQL, new CursoMapper(), new Object[] { codigo });
			LOGGER.info(curso.toString());
		}catch(EmptyResultDataAccessException e){
			curso = null;
			LOGGER.error("No se ha encontrado usuario para el codigo: "+codigo+" "+e.getMessage());
		}
		return curso;
	}

	@Override
	public List<Curso> getByNombre(String nombreCurso) {
		LOGGER.info("getByNombreDAO");
		final String SQL = "call cursogetByName(?)";
		List<Curso> cursos = null;
		try{
			cursos = jdbcTemplate.query(SQL, new CursoMapper(), new Object[] { nombreCurso });
			LOGGER.info(cursos.toString());
		}catch(EmptyResultDataAccessException e){
			cursos = null;
			LOGGER.error(e.getMessage());
		}
		return cursos;
	}
}
