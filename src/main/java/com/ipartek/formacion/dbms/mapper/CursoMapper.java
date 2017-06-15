/**
 * 
 */
package com.ipartek.formacion.dbms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Curso;

/**
 * Mapea la tabla de BBDD de los Cursos
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
public class CursoMapper implements RowMapper<Curso> {

	private final static Logger LOGGER = LoggerFactory.getLogger(CursoMapper.class);
	
	@Override
	public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
		Curso curso = new Curso();
		curso.setCodigo(rs.getInt("codigo"));
		curso.setNomcurso(rs.getString("nomcurso"));
		curso.setCodcurso(rs.getString("codcurso"));
		LOGGER.info(curso.toString());
		return curso;
	}

}
