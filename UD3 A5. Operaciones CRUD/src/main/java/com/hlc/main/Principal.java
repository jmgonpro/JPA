package com.hlc.main;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import com.hlc.dominio.Libro;
import com.hlc.utiles.Menu;
import com.hlc.utiles.Teclado;


public class Principal {

	private static Menu menu = new Menu("\nLibros", new String[] { "Nuevo libro", "Modificar libro", "Mostrar libro", "Eliminar libro", "Mostrar todos los libros", "Total de libros", "Salir" });
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("libros");
		EntityManager em = emf.createEntityManager();
		
		int opcion = 0;
		
		do {
			
			opcion = menu.gestionar();
			
			switch (opcion) {
				case 1:
					try {
						int isbn = Teclado.leerEntero("\nIntroduce el isbn del libro:");
						Libro libroInsert = new Libro(isbn);
						
						if (em.find(Libro.class, libroInsert.getIsbn()) != null) {
							throw new NullPointerException("\nIsbn introducido ya en la base de datos");
						}
						
						libroInsert = new Libro(isbn, Teclado.leerCadena("\nIntroduce el título del libro:"), 
											Teclado.leerCadena("\nIntroduce el autor del libro:"), Teclado.leerDecimal("\nIntroduce el precio del libro:"));
							
						em.getTransaction().begin();
						System.out.println();
						em.persist(libroInsert);
						em.getTransaction().commit();
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 2:
					try {
						Libro libroUpdate = em.find(Libro.class, Teclado.leerEntero("\nIntroduce el isbn del libro:"));	
						
						if (libroUpdate == null) {
							throw new NullPointerException("\nEl isbn introducido no almacenado en la base de datos");
						}
						
						em.getTransaction().begin();
						libroUpdate.setTitulo(Teclado.leerCadena("\nIntroduce el título del libro:"));
						libroUpdate.setAutor(Teclado.leerCadena("\nIntroduce el autor del libro:"));
						libroUpdate.setPrecio(Teclado.leerDecimal("\nIntroduce el precio del libro:"));
						System.out.println();
						em.merge(libroUpdate);
						em.getTransaction().commit();
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 3:
					try {
						Libro libroPorIsbn = em.find(Libro.class, Teclado.leerEntero("\nIntroduce el isbn del libro:"));
						
						if (libroPorIsbn == null) {
							throw new NullPointerException("\nEl isbn introducido no almacenado en la base de datos");
						}
						
						System.out.println("\n" + libroPorIsbn);
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 4:
					try {
						Libro libroDelete = em.find(Libro.class, Teclado.leerEntero("\nIntroduce el isbn del libro:"));	
						
						if (libroDelete == null) {
							throw new NullPointerException("\nEl isbn introducido no almacenado en la base de datos");
						}
						
						em.getTransaction().begin();
						System.out.println();
						em.remove(libroDelete);
						em.getTransaction().commit();
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				case 5:
					Query query = em.createQuery("SELECT l FROM Libro l");
					List<Libro> lista = query.getResultList();
					
					for (Libro libro : lista) {
						System.out.println("\n" + libro);
					}
					break;
				case 6:
					Query total = em.createQuery("SELECT COUNT(*) FROM Libro");
					System.out.println();
					long resultado = (Long) total.getSingleResult();
					System.out.println("\nTotal de libros: " + resultado);
					break;
				default:
					System.out.println("\nSaliendo..");
					return;
			}
			
		} while (opcion != 7);

	}

}
