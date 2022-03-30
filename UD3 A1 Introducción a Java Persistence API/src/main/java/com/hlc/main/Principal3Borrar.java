package com.hlc.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hlc.dominio.Libro;

public class Principal3Borrar {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();
		
		Libro libro = em.find(Libro.class, "1A");
		
		em.getTransaction().begin();
		em.remove(libro);
		em.getTransaction().commit();

	}

}
