package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.io.FileReader;

import java.util.logging.*;

import clases.Alojamiento;
import clases.Destino;
import clases.Persona;
import clases.TipoAlojamiento;

public class BD {
	private static Logger logger = null;

	/**
	 * MÃ©todo que realiza la conexiÃ³n con la base de datos
	 * 
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexiÃ³n a la base de datos
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			log(Level.INFO, "Conectada base de datos " + nombreBD, null);
		} catch (ClassNotFoundException ex) {

			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en conexión de base de datos " + nombreBD, e);

		}

		return con;
	}

	/**
	 * Método que cierra la conexión con la base de datos
	 * 
	 * @param con
	 */
	public static void closeBD(Connection con) {
		if (con != null) {
			try {
				con.close();
				System.out.println("Cerrando la Base de Datos");
				log(Level.INFO, "Cierre de base de datos", null);
			} catch (SQLException e) {

				log(Level.SEVERE, "Error en cierre de base de datos", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método que crea las tablas en la base de datos
	 * 
	 * @param con Conexión con la base de datos
	 */
	public static void crearTablas(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Persona (dni String, nom String, cont String, email String, tipo String)";
		String sql1 = "CREATE TABLE IF NOT EXISTS Destino (id Integer, nom String)";
		String sql2 = "CREATE TABLE IF NOT EXISTS Alojamiento (id Integer, nombre_comp String, talojamiento String, precio Float, duracion Integer, destino Integer)";
		try {
			Statement st = con.createStatement();

			st.executeUpdate(sql);
			st.executeUpdate(sql1);
			st.executeUpdate(sql2);
			log(Level.INFO, "Creacion de las tablas", null);
			System.out.println("--Se ha creado la tabla Destino");
			System.out.println("--Se ha creado la tabla Persona");
			System.out.println("--Se ha creado la tabla Alojamiento");
			st.close();
		} catch (SQLException e) {
			System.err.println(String.format("* Error al crear la BBDD: %s", e.getMessage()));
			log(Level.SEVERE, "Error al crea las tablas de la BBDD", e);

		}
	}

	/**
	 * 
	 * Método que inserta a una persona en la base de datos
	 * 
	 * @param con   Conexión con la base de datos
	 * @param nom   Nombre de la persona
	 * @param dni   Dni de la persona
	 * @param cont  Contraseña de la persona
	 * @param email Email de la persona
	 * @param tipo  Tipo de persona
	 */
	public static void insertarPersona(Connection con, String nom, String dni, String cont, String email, String tipo) {
		String sql = "INSERT INTO Persona VALUES('" + dni + "','" + nom + "','" + cont + "','" + email + "','" + tipo
				+ "')";
		try {
			Statement st = con.createStatement();

			log(Level.INFO, "Lanzada actualización a base de datos: " + sql, null);
			int resultado = st.executeUpdate(sql);
			log(Level.INFO, "Añadida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Persona");

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserción de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que busca a una persona en la base de datos
	 * 
	 * @param con Conexión con la base de datos
	 * @param dni Dni de la persona
	 * @return Devuelve la persona que buscamos
	 */
	public static boolean buscarPersona(Connection con, String dni) {
		String sql = "SELECT * FROM Persona WHERE dni='" + dni + "'";
		boolean personaEnc = false;
		try {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				personaEnc = true;
				System.out.println("--Se ha encontrado la Persona");
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en búsqueda de base de datos: " + sql, e);
			System.err.println(String.format("* Error al buscar la perdsona en la BBDD: %s", e.getMessage()));

		}
		return personaEnc;
	}

	/**
	 * Método que obtiene los datos de las personas
	 * 
	 * @return Devuelve una lista con todas las personas
	 */
	public static List<Persona> obtenerPersonas() {
		List<Persona> listaPersona = new ArrayList<>();
		String sql = "SELECT * FROM Persona WHERE  dni>=0 ";

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql);

			Persona p;
			while (rs.next()) {
				p = new Persona();
				p.setDni(rs.getString("dni"));
				p.setNombre(rs.getString("nom"));
				p.setContrasenia(rs.getString("cont"));
				p.setDireccion(rs.getString("email"));
				p.setTipo(rs.getString("tipo"));

				listaPersona.add(p);

			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d personas...", listaPersona.size()));
			log(Level.INFO, "Se ha encontrado las siguientes  personas:" + listaPersona.size(), null);
		} catch (Exception e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaPersona;
	}
	
	/***
	 * Método que obtiene los destinos
	 * 
	 * @return Devuelve un ArrayList con todos los destinos
	 */
	
	public static ArrayList<Destino> obtenerDestinos() {
		ArrayList<Destino> destinos = new ArrayList<>();
		String sql = "SELECT * FROM destino";

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log( Level.INFO, "Lanzada consulta a base de datos: " + sql, null );
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Destino d = new Destino();
				d.setId(rs.getInt("id"));
				d.setNombre(rs.getString("nom"));
				destinos.add(d);
				
				
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d destinos...", destinos.size()));
			log(Level.INFO, "Se han encontrado los siguientes destinos:" + destinos.size(), null);
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al obtener de base de datos: " + sql, e );
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}

		return destinos;
	}
	
	/***
	 * 
	 * Método que obtiene los alojamientos
	 * 
	 * @return Devuelve un ArrayList con todos los alojamientos
	 */
	public static ArrayList<Alojamiento> obtenerAlojamientos() {
		ArrayList<Alojamiento> listaAlojamiento = new ArrayList<>();
		String sql = "SELECT * FROM Alojamiento WHERE id>=0";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")){
			log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			Alojamiento al; 
			while (rs.next()) {
				al = new Alojamiento();
				al.setId(rs.getInt("id"));
				Destino d = obtenerDestino(con, rs.getInt("id"));
				al.setDestino(d);
				al.setDuracion(rs.getInt("duracion"));
				al.setNombre_comp(rs.getString("nombre_comp"));
				al.setPrecio(rs.getFloat("precio"));
				String t = rs.getString("tAlojamiento");
				al.setTalojamiento(TipoAlojamiento.valueOf(t));
				listaAlojamiento.add(al);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d alojamientos", listaAlojamiento.size()));
			log(Level.INFO, "Se han encontrado los siguientes alojamientos:" + listaAlojamiento.size(), null);
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al obtener de base de datos: " + sql, e );
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaAlojamiento;
	}
	/**
	 * Método que obtiene los datos de una persona
	 * 
	 * @param con Conexión con la base de datos
	 * @param dni Dni de la persona
	 * @return Devuelve la persona
	 */
	public static Persona obtenerDatosPersona(Connection con, String dni) {
		String sql = "SELECT * FROM Persona WHERE dni='" + dni + "'";
		Persona p = null;
		try {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String d = rs.getString("dni");
				String n = rs.getString("nom");
				String c = rs.getString("cont");
				String e = rs.getString("email");
				String t = rs.getString("tipo");

				p = new Persona(d, n, c, e, t);

			}
			log(Level.INFO, "Se ha obten: " + p, null);
			System.out.println(String.format("- Obtengo la persona:", p));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(
					String.format("* Error al obtener los  datos de la persona en la BBDD: %s", e.getMessage()));

		}
		return p;
	}
	
	/***
	 * 
	 * Método que obtiene los datos de un destino
	 * 
	 * @param con Conexión con la base de datos
	 * @param id Id del destino
	 * @return Devuelve el destino con dicho id
	 */
	public static Destino obtenerDestino(Connection con, int id) {
		
		String sql = "SELECT * FROM Destino WHERE id ='" + id + "'";
		Destino d = null;
		log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
		try {
			log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String nombre = rs.getString("nombre");
				d = new Destino(id,nombre);
			}
			log(Level.INFO, "Se ha obtenido: " + d, null);
			System.out.println(String.format("- Obtengo el destino:", d));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("*Error al obtener lod datosw del destino en la BBDD: %s", e.getMessage()));
		}
		return d;
	}

	/**
	 * Eliminar la persona
	 * 
	 * @param con abrir la conexion con la BBDD
	 * @param dni de la persona
	 */
	public static void eliminarPersona(Connection con, String dni) {

		try (Statement st = con.createStatement();) {
			String sql = "DELETE FROM Persona WHERE dni='" + dni + "';";
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);

			System.out.println(String.format("- Se han borrado la persona con el dni'" + dni + "'", result));
			log(Level.INFO, "Se ha eliminiado de la base de datos : " + result, null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
			System.err.println(String.format("* Error al eliminar la persona de la BBDD: %s", e.getMessage()));

		}

	}
	
	/***
	 * 
	 * Eliminar un destino
	 * 
	 * @param con abrir la conexion con la BBDD
	 * @param id de la persona 
	 */
	
	public static void eliminarDestino(Connection con, int id) {
		try (Statement st = con.createStatement();) {
			String sql = "DELETE FROM Destino WHERE id='" + id + "';";
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);
			System.out.println(String.format("- Se ha borrado el destino con id '" + id + "'", result));
			log(Level.INFO, "Se ha eliminado de la base de datos: " + result, null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
			System.err.println(String.format("* Error al eliminar el destino de la BBDD: %s", e.getMessage()));
		}
	}
	
	public static void eliminarAlojamiento(Connection con, int id) {
		try (Statement st = con.createStatement();) {
			String sql = "DELETE FROM Alojamiento WHERE id='" + id + "';";
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);
			System.out.println(String.format("- Se ha borrado el alojamiento con id '" + id + "'", result));
			log(Level.INFO, "Se ha eliminado de la base de datos: " + result, null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
			System.err.println(String.format("* Error al eliminar el alojamiento de la BBDD: %s", e.getMessage()));
		}	}
	/*
	 * LOGGER
	 */

	// Método local para loggear (si no se asigna un logger externo, se asigna uno
	// local)
	private static void log(Level level, String msg, Throwable excepcion) {
		if (logger == null) { // Logger por defecto local:
			logger = Logger.getLogger("BD-local"); // Nombre del logger
			logger.setLevel(Level.ALL); // Loguea todos los niveles
			try {
				logger.addHandler(new FileHandler("loggerBD.xml", true)); // Y saca el log a fichero xml
			} catch (Exception e) {
				logger.log(Level.SEVERE, "No se pudo crear fichero de log", e);
			}
		}
		if (excepcion == null)
			logger.log(level, msg);
		else
			logger.log(level, msg, excepcion);
	}
}
