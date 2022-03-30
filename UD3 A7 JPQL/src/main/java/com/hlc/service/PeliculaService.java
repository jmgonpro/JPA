package com.hlc.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import com.hlc.dao.PeliculaDao;
import com.hlc.dominio.Pelicula;

public class PeliculaService {
	private EntityManagerFactory emf;

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public void createFilm(Pelicula pelicula) {
    	EntityManager em = emf.createEntityManager();
    	PeliculaDao peliculaDao = new PeliculaDao(em);
    	
    	em.getTransaction().begin();
		peliculaDao.create(pelicula);
		em.getTransaction().commit();
    }
    
    public Pelicula findFilm(String titulo) {
    	EntityManager em = emf.createEntityManager();
    	PeliculaDao peliculaDao = new PeliculaDao(em);
    	
    	if (peliculaDao.find(titulo) == null) {
			throw new NullPointerException("\nLa película introducida no esta almacenada");
		}
    	
    	return peliculaDao.find(titulo);
    }
    
    public void updateFilm(Pelicula pelicula) {
    	EntityManager em = emf.createEntityManager();
    	PeliculaDao peliculaDao = new PeliculaDao(em);
    	
    	if (peliculaDao.find(pelicula.getTitulo()) == null) {
			throw new NullPointerException("\nLa película introducida no está almacenada");
		}
    	
    	em.getTransaction().begin();
		peliculaDao.update(pelicula);
		em.getTransaction().commit();
    }
    
    public void deleteFilm(Pelicula pelicula) {
    	EntityManager em = emf.createEntityManager();
    	PeliculaDao peliculaDao = new PeliculaDao(em);
    	
    	if (pelicula == null) {
			throw new NullPointerException("\nLa película introducida no está almacenada");
		}
    	
    	em.getTransaction().begin();
		peliculaDao.delete(pelicula);
		em.getTransaction().commit();
    }
    
    public void listFilms() {
		EntityManager em = emf.createEntityManager();
		PeliculaDao peliculaDao = new PeliculaDao(em);
		List<Pelicula> lista = peliculaDao.listFilms();
		
		for (Pelicula pelicula : lista) {
			System.out.println("\n" + pelicula);
		}
	}
    
    public void listDirectorFilms(String director) {
		EntityManager em = emf.createEntityManager();
		PeliculaDao peliculaDao = new PeliculaDao(em);
		List<Pelicula> lista = peliculaDao.listFilmsDirector(director);
		
		for (Pelicula pelicula : lista) {
			System.out.println("\n" + pelicula);
		}
	}
    
    public void listFilmsYear(int anno) {
		EntityManager em = emf.createEntityManager();
		PeliculaDao peliculaDao = new PeliculaDao(em);
		List<Pelicula> lista = peliculaDao.listFilmsYear(anno);
		
		for (Pelicula pelicula : lista) {
			System.out.println("\n" + pelicula);
		}
	}
    
}
