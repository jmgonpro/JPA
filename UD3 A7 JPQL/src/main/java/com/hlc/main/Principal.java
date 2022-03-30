package com.hlc.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.hlc.dominio.Pelicula;
import com.hlc.service.PeliculaService;
import com.hlc.utiles.Menu;
import com.hlc.utiles.Teclado;

public class Principal {

	private static Menu menu = new Menu("\nPelículas", new String[] { "Nueva película", "Modificar película", "Mostrar película", "Eliminar película", "Mostrar todas las películas", "Películas por director", "Películas anteriores según año", "Salir" });
	
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
						String titulo = Teclado.leerCadena("\nIntroduce el título de la película:").toUpperCase();
						Pelicula insert = new Pelicula(titulo);
						
						if (em.find(Pelicula.class, insert.getTitulo()) != null) {
							throw new NullPointerException("\nLa película introducida ya está almacenada");
						}
						
						insert = new Pelicula(titulo, Teclado.leerEntero("\nIntroduce el año de la película:"), 
				  				  Teclado.leerEntero("\nIntroduce la duración de la película:"), Teclado.leerCadena("\nIntroduce el país de la película:").toUpperCase(),
				  				  Teclado.leerCadena("\nIntroduce el director de la película").toUpperCase());
						peliculaService.createFilm(insert);
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 2:
					try {
						Pelicula update = peliculaService.findFilm(Teclado.leerCadena("\nIntroduce el título de la película:").toUpperCase());
						update.setAnno(Teclado.leerEntero("\nIntroduce el año de la película:"));
						update.setDuracion(Teclado.leerEntero("\nIntroduce la duración de la película:"));
						update.setPais(Teclado.leerCadena("\nIntroduce el país de la película:").toUpperCase());
						update.setDirector(Teclado.leerCadena("\nIntroduce el director de la película").toUpperCase());
						peliculaService.updateFilm(update);
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println("\n" + peliculaService.findFilm(Teclado.leerCadena("\nIntroduce el título de la película:").toUpperCase()));
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 4:
					try {
						peliculaService.deleteFilm(peliculaService.findFilm(Teclado.leerCadena("\nIntroduce el título de la película:").toUpperCase()));
					} catch (NullPointerException e) {
						System.err.println(e.getMessage());
					}
					break;
				case 5:
					peliculaService.listFilms();
					break;
				case 6:
					peliculaService.listDirectorFilms(Teclado.leerCadena("\nIntroduce el director de la película:").toUpperCase());
					break;
				case 7:
					peliculaService.listFilmsYear(Teclado.leerEntero("\nIntroduce el año de la película:"));
					break;
				default:
					System.out.println("\nSaliendo..");
					return;
			}
			
		} while (opcion != 8);

	}

}
