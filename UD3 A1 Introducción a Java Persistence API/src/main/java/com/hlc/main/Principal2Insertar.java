package com.hlc.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hlc.dominio.Libro;

public class Principal2Insertar {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
		EntityManager em = emf.createEntityManager();
		
		String textoFecha = "1/01/2020";
		SimpleDateFormat ffecha = new SimpleDateFormat("d/M/yyyy");
		Date fecha = null;
		
		try {
			fecha = ffecha.parse(textoFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Libro li = new Libro("2A", "JPA", "Ana", 3, fecha);
		
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();

	}

}
