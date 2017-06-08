/**
 * 
 */
package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Curso;

/**
 * Clase encargada de validar Curso
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
public class CursoValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		// Si paramClass es un ejercicio lo devuelve
		return Curso.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
