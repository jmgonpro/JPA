package com.hlc.main;

import javax.persistence.Persistence;

import com.hlc.dominio.Libro;

public class Principal5Generar {

	public static void main(String[] args) {
		
		Persistence.generateSchema("biblioteca", null);

	}

}