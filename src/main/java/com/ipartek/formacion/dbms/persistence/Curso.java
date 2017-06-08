/**
 * 
 */
package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;

/**
 * Clase de persistencia
 * @author Jon Ander Ochoa Ruiz
 * 8 de jun. de 2017
 */
public class Curso implements Serializable, Comparable<Curso> {

	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private int codigo;
	private String nomcurso;
	private String codcurso;

	public Curso() {
		super();
		this.codigo = CODIGO_NULO;
		this.nomcurso = "";
		this.codcurso = "";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomcurso == null) ? 0 : nomcurso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (nomcurso == null) {
			if (other.nomcurso != null)
				return false;
		} else if (!nomcurso.equals(other.nomcurso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nomcurso=" + nomcurso + ", codcurso=" + codcurso + "]";
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomcurso() {
		return nomcurso;
	}

	public void setNomcurso(String nomcurso) {
		this.nomcurso = nomcurso;
	}
	
	public String getCodcurso() {
		return codcurso;
	}

	public void setCodcurso(String codcurso) {
		this.codcurso = codcurso;
	}
	
	@Override
	public int compareTo(Curso o) {
		return this.getCodcurso().compareToIgnoreCase(o.getCodcurso());
	}
}
