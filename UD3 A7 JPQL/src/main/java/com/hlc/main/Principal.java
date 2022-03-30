package com.hlc.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.hlc.dominio.Pelicula;
import com.hlc.service.PeliculaService;
import com.hlc.utiles.Menu;
import com.hlc.utiles.Teclado;

public class Principal {

	private static Menu menu = new Menu("\nPel�culas", new String[] { "Nueva pel�cula", "Modificar pel�cula", "Mostrar pel�cula", "Eliminar pel�cula", "Mostrar todas las pel�culas", "Pel�culas por director", "Pel�culas anteriores seg�n a�o", "Salir" });
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("peliculas");
		EntityManager em = emf.createEntityManager();
		PeliculaService peliculaService = new PeliculaService();
		peliculaService.setEmf(emf);
		
		int opcion = 0;
		
		do {
			
			opcion = menu.gestionar();
			
			switch (opcion) {
				case 1:
					try {
						String titulo = Teclado.leerCadena("\nIntroduce el t�tulo de la pel�cula:").toUpperCase();
						Pelicula insert = new Pelicula(titulo);
						
						if (em.find(Pelicula.class, insert.getTitulo()) != null) {
							throw new NullPointerException("\nLa pel�cula introducida ya est� almacenada");
						}
						
						insert = new Pelicula(titulo, Teclado.leerEntero("\nIntroduce el a�o de la pel�cula:"), 
				  				  Teclado.leerEntero("\nIntroduce la duraci�n de la pel�cula:"), Teclado.leerCadena("\nIntroduce el pa�s de la pel�cula:").toUpperCase(),
				  				  Teclado.leerCadena("\nIntroduce el director de la pel�cula").toUpperCase());
						peliculaService.createFilm(insert);
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 2:
					try {
						Pelicula update = peliculaService.findFilm(Teclado.leerCadena("\nIntroduce el t�tulo de la pel�cula:").toUpperCase());
						update.setAnno(Teclado.leerEntero("\nIntroduce el a�o de la pel�cula:"));
						update.setDuracion(Teclado.leerEntero("\nIntroduce la duraci�n de la pel�cula:"));
						update.setPais(Teclado.leerCadena("\nIntroduce el pa�s de la pel�cula:").toUpperCase());
						update.setDirector(Teclado.leerCadena("\nIntroduce el director de la pel�cula").toUpperCase());
						peliculaService.updateFilm(update);
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println("\n" + peliculaService.findFilm(Teclado.leerCadena("\nIntroduce el t�tulo de la pel�cula:").toUpperCase()));
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 4:
					try {
						peliculaService.deleteFilm(peliculaService.findFilm(Teclado.leerCadena("\nIntroduce el t�tulo de la pel�cula:").toUpperCase()));
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 5:
					peliculaService.listFilms();
					break;
				case 6:
					peliculaService.listDirectorFilms(Teclado.leerCadena("\nIntroduce el director de la pel�cula:").toUpperCase());
					break;
				case 7:
					peliculaService.listFilmsYear(Teclado.leerEntero("\nIntroduce el a�o de la pel�cula:"));
					break;
				default:
					System.out.println("\nSaliendo..");
					return;
			}
			
		} while (opcion != 8);

	}

}
