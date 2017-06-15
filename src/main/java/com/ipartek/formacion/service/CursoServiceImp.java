/**
 * 
 */
package com.ipartek.formacion.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

	@Override
	public List<Curso> diezUltimos() {
		LOGGER.info("diezUltimosService");
		return cursoDAO.diezUltimos();
	}

	@Override
	public Curso getByCodigo(String codigo) {
		LOGGER.info("getByCodigoService");
		return cursoDAO.getByCodigo(codigo);
	}

	@Override
	public List<Curso> getByNombre(String nombreCurso) {
		LOGGER.info("getByNombreService");
		return cursoDAO.getByNombre(nombreCurso);
	}
	
	/**
	 * Importación de la BBDD
	 */
	public int cargarCSV() {
        String separador = ";";
        BufferedReader br = null; 
          try {
             br =new BufferedReader(new FileReader("C:\\Desarrollo\\git\\gestion-cursos\\src\\main\\resources\\database\\csv\\cursos.csv"));
             String line = br.readLine();
             line = br.readLine();
             Curso curso = new Curso();
             while (null!=line) {
                String [] fields = line.split(separador);
                LOGGER.info(line.toString());
                try {
                    if(fields.length >8 && (null!=fields[1] || null!=fields[8])){
                        curso.setNomcurso(fields[1]);
                        curso.setCodcurso(fields[8]);
                        cursoDAO.create(curso);
                    }
                    line = br.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
             }
             
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
             if (null!=br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.getMessage();
                }
             }
          }
        return 0;
    }


}
