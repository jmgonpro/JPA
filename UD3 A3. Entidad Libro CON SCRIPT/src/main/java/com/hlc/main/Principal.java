package com.hlc.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hlc.dominio.Libro;

public class Principal {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();
		
		Libro libro = em.find(Libro.class, "1A");
		
		System.out.println(libro.getIsbn());
		System.out.println(libro.getAutor());
		System.out.println(libro.getTitulo());

	}

}
