package com.ipartek.formacion;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private CursoService cS;
	//Que datos carga y a que vista
	ModelAndView mav = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	
	/**
	 * Metodo que muestra todos los cursos
	 * @return mav Un ModelAndView con la lista de cursos y la vista
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		LOGGER.info("DiezUltimosController");
		//Vista a la que se dirige
		mav = new ModelAndView("home");
		//carga la lista
		List<Curso> cursos = cS.diezUltimos();
		//Añade un objeto al mav con la lista de cursos y la llama listadoUsuarios
		model.addAttribute("listadocursos", cursos);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
	/**
	 * Controla el ofuscamiento
	 * @return
	 */
	@RequestMapping(value="login.html")
	public String loginPage(){
		//Redirige a login.jsp
		return "login";
	}
	
	
	/**
	 * ModelMap recibe el mensaje de error
	 * @param model
	 * @return
	 */
	@RequestMapping(value="Access_Denied")
	public String accesoDenegado(ModelMap model){
		model.addAttribute("user", getPrincipal());
		return "login";
	}

	/**
	 * Validamos de user y password desde XML(contexto)
	 * @return
	 */
	private String getPrincipal() {
		String username= null;
		//Cogemos del contexto de seguridad los datos de autentificacion de los usuarios(principal)
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails){
			username= ((UserDetails) principal).getUsername();
		}else{
			username= principal.toString();
		}
		return username;
	}
	
	
	/**
	 * Logout es un enlace que pondremos en el header
	 * @param request Peticion
	 * @param response Respuesta
	 * @return Devuelve
	 */
	@RequestMapping(value="logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		//Cogemos la autentificacion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login.html?logout";//Se recomienda que la pagina de logout sea la de login
	}
	
}
