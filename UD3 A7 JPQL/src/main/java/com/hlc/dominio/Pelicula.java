package com.hlc.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "peliculas")
@NamedQueries({
	@NamedQuery(name = "listFilms", query = "SELECT p FROM Pelicula p"),
	@NamedQuery(name = "filmsDirector", query = "SELECT p FROM Pelicula p WHERE p.director = :director ORDER BY p.anno DESC"),
	@NamedQuery(name = "filmsYear", query = "SELECT p FROM Pelicula p WHERE p.anno < :anno ORDER BY p.director")})
public class Pelicula {
	@Id
	private String titulo;
	private int anno;
	private int duracion;
	private String pais;
	private String director;
	
	public Pelicula(String titulo, int anno, int duracion, String pais, String director) {
		this.titulo = titulo;
		this.anno = anno;
		this.duracion = duracion;
		this.pais = pais;
		this.director = director;
	}

	public Pelicula(String titulo) {
		this.titulo = titulo;
	}
	
	public Pelicula() {
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Pelicula other = (Pelicula) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", anno=" + anno + ", duracion=" + duracion + ", pais=" + pais
				+ ", director=" + director + "]";
	}
	
}
