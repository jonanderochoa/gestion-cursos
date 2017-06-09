/**
 * 
 */
package com.ipartek.formacion.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

/**
 * Controlador que se comunica entre la vista y la capa service de cursos
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
@Controller
@RequestMapping(value="/cursos")
public class CursoController {

	@Inject
	private CursoService cS;
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);
	//Que datos carga y a que vista
	ModelAndView mav = null;
	//Accede al objeto del servlet-context
	@Resource(name = "cursoValidator")
	private Validator validator = null;
	
	/**
	 * Metodo que alimneta al metodo init() del servlet de spring. Por lo que vamos a cargar 
	 * al validador de Spring
	 * @param binder Recibe el binder al que carga el validador
	 */
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	
	/**
	 * Metodo que añade un nuevo curso
	 * @param model Modelo al que se le añade el curso y la vista
	 * @return Devuelve el String donde verlo
	 */
	@RequestMapping(value="/addCurso")
	public String addCurso(Model model){
		LOGGER.info("addCursoController");
		//Añadimos un objeto en blanco pero no nulo
		model.addAttribute("curso", new Curso());
		return "curso";
	}
	
	
	/**
	 * Metodo que muestra todos los cursos
	 * @return mav Un ModelAndView con la lista de cursos y la vista
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(){
		LOGGER.info("getAllController");
		//Vista a la que se dirige
		mav = new ModelAndView("cursos");
		//carga la lista
		List<Curso> cursos = cS.getAll();
		//Añade un objeto al mav con la lista de cursos y la llama listadoUsuarios
		mav.addObject("listadocursos", cursos);
		return mav;
	}
	
	/**
	 * Metodo encargado de mostrar el curso con el codigo entregado
	 * @param codigo Codigo del curso que queremos ver
	 * @return mav Devuelve un ModelAndView con el curso y la vista en la que mostrarlo
	 */
	@RequestMapping(value = "/{codigo}")
	public ModelAndView getById(@PathVariable("codigo") int codigo){
		LOGGER.info("getByIdController");
		mav = new ModelAndView("curso");
		Curso curso = cS.getById(codigo);
		mav.addObject("curso", curso);
		return mav;
	}
	/**
	 * Guarda o actualiza un curso validandolo con Spring
	 * @param curso Recibe un curso que creara un nuevo valor o modificara uno existente
	 * @param bindingResult Devuelve el resultado de la validacion
	 * @return Devuelve la cadena a donde redirigirse
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCurso(@ModelAttribute("curso") @Validated Curso curso, BindingResult bindingResult){
		String destino = "";
		if(bindingResult.hasErrors()){ //Si hay errores
			LOGGER.info("cursos tiene errores");
			destino = "curso";
		}else{
			destino = "redirect:/cursos";
			if(curso.getCodigo() > Curso.CODIGO_NULO){
				LOGGER.info("update");
				cS.update(curso);
			}else{
				LOGGER.info("create");
				cS.create(curso);
			}
		}
		return destino;
	}
	

	/**
	 * Metodo encargado de eliminar un curso
	 * @param codigo Codigo del curso que queremos eliminar
	 * @return Nos manda a la lista de cursos
	 */
	@RequestMapping(value="/deleteCurso/{codigo}")
	public String delete(@PathVariable("codigo") int codigo){
		LOGGER.info("deleteController");
		cS.delete(codigo);
		return "redirect:/cursos";
	}
}
