/**
 * 
 */
package com.ipartek.formacion.controller.validator;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

/**
 * Clase encargada de validar Curso
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
public class CursoValidator implements Validator {

	@Value("${curso.codcurso.size.min}")
	private int codCursoTamMin;
	@Value("${curso.codcurso.size.max}")
	private int codCursoTamMax;
	@Value("${curso.nomcurso.size.min}")
	private int nomCursoTamMin;
	@Value("${curso.nomcurso.size.max}")
	private int nomCursoTamMax;
	@Inject
	private CursoService cS;
	
	@Override
	public boolean supports(Class<?> paramClass) {
		// Si paramClass es un ejercicio lo devuelve
		return Curso.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// Validamos que no sea nulo, no este en blanco
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codcurso", "form.codigoRequerido",
						"tiene que introducirse un código de curso");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomcurso", "form.nombreRequerido",
						"tienen que introducirse un nombre de curso");
		
		Curso curso = (Curso) obj;
		// Si el codigo del usuario(obj) es menos que nulo...
		if(curso.getCodigo() < Curso.CODIGO_NULO){
			errors.rejectValue("codigo", "form.codigoNegativo", new Object[]{ "'codigo" }, "no puede ser menor que "+ Curso.CODIGO_NULO);
		}
		// Valida la longitud del codigo de curso
		if (curso.getCodcurso().length() < codCursoTamMin || curso.getCodcurso().length() > codCursoTamMax) {
			errors.rejectValue("codcurso", "form.longitudCodigoIncorrecta", new Object[] { codCursoTamMin, codCursoTamMax },
					"El codigo de curso tiene que ocupar entre " + codCursoTamMin + " y " + codCursoTamMax + " caracteres.");
		}
		// Valida la longitud del nombre del curso
		if (curso.getNomcurso().length() < nomCursoTamMin || curso.getNomcurso().length() > nomCursoTamMax) {
			errors.rejectValue("nomcurso", "form.longitudNombreIncorrecta",	new Object[] { nomCursoTamMin, nomCursoTamMax }, 
					"El nombre del curso tienen que ocupar entre "+ nomCursoTamMin + " y " + nomCursoTamMax + " caracteres.");
		}
		
		
		//Valida que el curso sea valido
        Curso aux = cS.getByCodigo(curso.getCodcurso());
        if ((curso.getCodcurso() != null && aux != null)
                || (!curso.equals(aux) && curso.getCodcurso().equalsIgnoreCase(aux.getCodcurso()))) {
            errors.rejectValue("codcurso", "form.codigoExiste", new Object[] { curso.getCodcurso() },
                    "el codigo ya existe en la base de datos");
        }
	}

}
