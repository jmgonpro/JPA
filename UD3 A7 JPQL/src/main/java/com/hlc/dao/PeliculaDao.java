package com.hlc.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.hlc.dominio.Pelicula;

public class PeliculaDao extends Dao<Pelicula, String> {
	
	public PeliculaDao(EntityManager em) {
		super(em);
	}

	@Override
	public Pelicula find(String titulo) {
		EntityManager em = this.getEntityManager();
		return em.find(Pelicula.class, titulo);
	}
	
	public List<Pelicula> listFilms() {
		EntityManager em = this.getEntityManager();
		TypedQuery<Pelicula> query = em.createNamedQuery("listFilms", Pelicula.class);
        return (List<Pelicula>) query.getResultList();
	}
	
	public List<Pelicula> listFilmsDirector(String director) {
		EntityManager em = this.getEntityManager();
		TypedQuery<Pelicula> query = em.createNamedQuery("filmsDirector", Pelicula.class).setParameter("director", director);
        return (List<Pelicula>) query.getResultList();
	}
	
	public List<Pelicula> listFilmsYear(int anno) {
		EntityManager em = this.getEntityManager();
		TypedQuery<Pelicula> query = em.createNamedQuery("filmsYear", Pelicula.class).setParameter("anno", anno);
        return (List<Pelicula>) query.getResultList();
	}

}
