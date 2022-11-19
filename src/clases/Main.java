package clases;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import bd.BD;

public class Main {

	public static void main(String[] args) {

		// INITBD:Abrir la base de datos
		Connection con = BD.initBD("confortTravel.db");
		// CREATE DATABASE :Se crea la BBDD
		BD.crearTablas(con);

		System.out.println("--Se han insertado en la tabla persona:");
		// INSERT: Insertar datos en la BBDD

		BD.insertarPersona(con, "iria", "12345678A", "Hh1?", "iria@gmail.com", "ADMINISTRADOR");
		BD.insertarPersona(con, "iker", "23456789A", "12Jj?", "iker@gmail.com", "CLIENTE");
		BD.insertarPersona(con, "maitane", "23459989A", "13Jj?", "maitane2@gmail.com", "ADMINISTRADOR");
		BD.insertarPersona(con, "marta", "21111189A", "12Pj?", "marta@gmail.com", "CLIENTE");
		BD.insertarPersona(con, "jon", "23999789A", "12Jkk?", "jon@gmail.com", "ADMINISTRADOR");

		// SELECT :Buscar esa persona
		System.out.println("Buscar la persona cuyo DNI es 21111189A:");
		System.out.println(BD.buscarPersona(con, "21111189A"));

		// SELECT : Se obtienen datos de la BBDD
		System.out.println("VISUALIZANDO LAS PERSONAS QUE ESTAN EN LA BBDD:");
		List<Persona> listaPersonas = new ArrayList<>();
		listaPersonas = BD.obtenerDatos();
		visualizarPersonas(listaPersonas);

		// SELECT :Se obtienen datos de la BBDD
		System.out.println("--Obtener datos de la persona cuyo dni es 23999789A:");
		System.out.println(BD.obtenerDatosPersona(con, "23999789A"));

		// DELETE :Eliminar persona

		System.out.println("Eliminar de la base de datos el dni 12345678A:");
		BD.eliminarPersona(con, "12345678A");

	}

	private static void visualizarPersonas(List<Persona> personas) {
		if (!personas.isEmpty()) {
			for (Persona p : personas) {
				System.out.println(String.format(" - %s", p.toString()));
			}
		}
	}
}
