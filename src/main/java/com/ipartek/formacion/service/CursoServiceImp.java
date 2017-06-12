/**
 * 
 */
package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.CursoDAO;
import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

/**
 * Clase encargada de implementar las funcionalidades creadas en la interfaz CursoService
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
@Service
public class CursoServiceImp implements CursoService {

	@Inject
	private CursoDAO cursoDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoServiceImp.class);
	
	@Override
	public Curso create(Curso curso) {
		LOGGER.info("createService");
		return cursoDAO.create(curso);
	}

	@Override
	public List<Curso> getAll() {
		LOGGER.info("getAllService");
		return cursoDAO.getAll();
	}

	@Override
	public Curso getById(int codigo) {
		LOGGER.info("getByIdService");
		return cursoDAO.getById(codigo);
	}

	@Override
	public Curso update(Curso curso) {
		LOGGER.info("updateService");
		return cursoDAO.update(curso);
	}

	@Override
	public void delete(int codigo) {
		LOGGER.info("deleteService");
		cursoDAO.delete(codigo);
	}

	/* (non-Javadoc)
	 * @see com.ipartek.formacion.service.interfaces.CursoService#diezUltimos()
	 */
	@Override
	public List<Curso> diezUltimos() {
		LOGGER.info("diezUltimosService");
		return cursoDAO.diezUltimos();
	}

	/* (non-Javadoc)
	 * @see com.ipartek.formacion.service.interfaces.CursoService#getByCodigo(java.lang.String)
	 */
	@Override
	public Curso getByCodigo(String codigo) {
		LOGGER.info("getByCodigoService");
		return cursoDAO.getByCodigo(codigo);
	}

}
