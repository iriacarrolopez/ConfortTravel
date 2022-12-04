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

import javax.swing.plaf.multi.MultiPopupMenuUI;

import clases.Alojamiento;
import clases.Excursion;
import clases.Ciudad;
import clases.Persona;
import clases.Reserva;
import clases.TipoAlojamiento;
import clases.TipoAlquiler;
import clases.TipoExcursion;

public class BD {
	private static Logger logger = null;

	/**
	 * Método que realiza la conexión con la base de datos
	 * 
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexión a la base de datos
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
			log(Level.SEVERE, "Error en conexi�n de base de datos " + nombreBD, e);

		}

		return con;
	}

	/**
	 * M�todo que cierra la conexi�n con la base de datos
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
	 * M�todo que crea las tablas en la base de datos
	 * 
	 * @param con Conexi�n con la base de datos
	 */
	public static void crearTablas(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Persona (dni String, nom String, cont String, email String, tipo String)";
		String sql2 = "CREATE TABLE IF NOT EXISTS Alojamiento (id Integer, nombre_comp String, talojamiento String, precio Float, duracion Integer, destino Integer)";
		String sql3 = "CREATE TABLE IF NOT EXISTS Excursion (id Integer, nombre String, tipo String,lugar String, precio Float,duracion Integer, numPersonas Integer)";
		String sql4 = "CREATE TABLE IF NOT EXISTS Reserva (id Integer, idOrigen Integer, idDestino Integer, fechaInicio String, fechaFin String, alquilerTransporte String, tipoAlojamiento String, excursion String, actividades String)";
		String sql5 = "CREATE TABLE IF NOT EXISTS Ciudad (id Integer, nom String)";
		try {
			Statement st = con.createStatement();

			st.executeUpdate(sql);
			st.executeUpdate(sql2);
			st.executeUpdate(sql3);
			st.executeUpdate(sql4);
			st.executeUpdate(sql5);
			log(Level.INFO, "Creacion de las tablas", null);
			System.out.println("--Se ha creado la tabla Destino");
			System.out.println("--Se ha creado la tabla Persona");
			System.out.println("--Se ha creado la tabla Alojamiento");
			System.out.println("--Se ha creado la tabla Excursion");
			
			st.close();
		} catch (SQLException e) {
			System.err.println(String.format("* Error al crear la BBDD: %s", e.getMessage()));
			log(Level.SEVERE, "Error al crea las tablas de la BBDD", e);

		}
	}

	/**
	 * 
	 * M�todo que inserta a una persona en la base de datos
	 * 
	 * @param con   Conexi�n con la base de datos
	 * @param nom   Nombre de la persona
	 * @param dni   Dni de la persona
	 * @param cont  Contrase�a de la persona
	 * @param email Email de la persona
	 * @param tipo  Tipo de persona
	 */
	public static void insertarPersona(Connection con, String nom, String dni, String cont, String email, String tipo) {
		String sql = "INSERT INTO Persona VALUES('" + dni + "','" + nom + "','" + cont + "','" + email + "','" + tipo
				+ "')";
		try {
			Statement st = con.createStatement();

			log(Level.INFO, "Lanzada actualizaci�n a base de datos: " + sql, null);
			int resultado = st.executeUpdate(sql);
			log(Level.INFO, "A�adida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Persona");

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserci�n de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que inserta un alojamiento
	 * @param con Conexion con la base de datos
	 * @param id del alojamiento
	 * @param nombre del alojamiento
	 * @param tipo de alojamiento
	 * @param precio del alojamiento
	 * @param duracion de la estancia
	 * @param destino del alojamiento
	 */
	public static void insertarAlojamiento(Connection con ,Integer id, String nombre , String tipo,float precio,Integer duracion,Integer destino) {
			String sql = "INSERT INTO Alojamiento VALUES("+id+",'"+nombre+"','"+tipo+"',"+precio+","+duracion+","+destino+");";
			try {
				Statement stmt = con.createStatement();
				log(Level.INFO,"Lanzada actualizaci�n a base de datos: " + sql, null);
				int resultado = stmt.executeUpdate(sql);
				log(Level.INFO, "A�adida " + resultado + " fila a base de datos\t" + sql, null);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
							log(Level.SEVERE, "Error en inserci�n de base de datos\t" + sql, e);
							System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
							e.printStackTrace();
			}
		}
	
	/**
	 * M�todo que busca a una persona en la base de datos
	 * 
	 * @param con Conexi�n con la base de datos
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
			log(Level.SEVERE, "Error en b�squeda de base de datos: " + sql, e);
			System.err.println(String.format("* Error al buscar la perdsona en la BBDD: %s", e.getMessage()));

		}
		return personaEnc;
	}

	/**
	 * M�todo que obtiene los datos de las personas
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
	//IRIA
	/**
	 * Método que devuelve el nombre de la ciudad por el id
	 * @param con conexion con la base de datos
	 * @param id id de la ciudad
	 * @return
	 */
	public static Ciudad getCiudad(Connection con, int id) {
		Ciudad c = null;
		String sql = "SELECT * FROM Ciudad WHERE id="+id;
		try {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				c = new Ciudad(rs.getInt("id"),rs.getString("nom"));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en b�squeda de base de datos: " + sql, e);
			System.err.println(String.format("* Error al buscar la perdsona en la BBDD: %s", e.getMessage()));

		}
		return c;
	}
	
	
	/***
	 * M�todo que obtiene los destinos
	 * 
	 * @return Devuelve un ArrayList con todos los destinos
	 */
	
	public static ArrayList<Ciudad> obtenerTodasCiudades() {
		ArrayList<Ciudad> ciudades = new ArrayList<>();
		String sql = "SELECT * FROM Ciudad";

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log( Level.INFO, "Lanzada consulta a base de datos: " + sql, null );
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Ciudad c = new Ciudad();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("nom"));
				ciudades.add(c);
				
				
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d destinos...", ciudades.size()));
			log(Level.INFO, "Se han encontrado los siguientes destinos:" + ciudades.size(), null);
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al obtener de base de datos: " + sql, e );
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}

		return ciudades;
	}
	
	/***
	 * 
	 * M�todo que obtiene los alojamientos
	 * 
	 * @return Devuelve un ArrayList con todos los alojamientos
	 */
	public static ArrayList<Alojamiento> obtenerAlojamientos() {
		ArrayList<Alojamiento> listaAlojamiento = new ArrayList<>();
		String sql = "SELECT * FROM Alojamiento WHERE id>=0";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")){
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			Alojamiento al; 
			while (rs.next()) {
				al = new Alojamiento();
				al.setId(rs.getInt("id"));
				al.setNombre_comp(rs.getString("nombre_comp"));
				al.setTalojamiento(TipoAlojamiento.valueOf(rs.getString("talojamiento")));
				al.setPrecio(rs.getFloat("precio"));
				al.setDuracion(rs.getInt("duracion"));
				Ciudad c = obtenerCiudad(con, rs.getInt("id"));
				al.setCiudad(c);
				
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
	
	//IRIA
	public static ArrayList<Reserva> obtenerReservas(){
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String sql = "SELECT * FROM Reserva WHERE id>=0";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")){
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
			
				int dni = rs.getInt("id");
				int o = rs.getInt("idOrigen");
				int d = rs.getInt("idDestino");
				Ciudad co = getCiudad(con,o);
				Ciudad cd = getCiudad(con,d);
				String fechaIni = rs.getString("fechaInicio");
				String fechaFin = rs.getString("fechaFin");
				String at = rs.getString("alquilerTransporte");
				TipoAlquiler alquilerTransporte = TipoAlquiler.valueOf(at);
				String tipoA = rs.getString("tipoAlojamiento");
				TipoAlojamiento ta = TipoAlojamiento.valueOf(tipoA);
				String ex = rs.getString("excursion");
				TipoExcursion te = TipoExcursion.valueOf(ex);
				String act = rs.getString("actividades");
				
				Reserva r = new Reserva(dni, co, cd, fechaIni, fechaFin, alquilerTransporte, ta, te, act);
				
				listaReservas.add(r);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d reservas", listaReservas.size()));
			log(Level.INFO, "Se han encontrado las siguientes reservas:" + listaReservas.size(), null);
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al obtener de base de datos: " + sql, e );
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaReservas;
	}
	
	public static Alojamiento obtenerAlojamientosPorid(Integer id) {
		
		String sql = "SELECT * FROM Alojamiento WHERE id="+id+";";
		Alojamiento al=null;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")){
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			 
			while (rs.next()) {
			Integer i =  rs.getInt("id");//	al.setId(rs.getInt("id"));
				String nom = rs.getString("nombre_comp");//al.setNombre_comp(rs.getString("nombre_comp"));
				TipoAlojamiento tipo = TipoAlojamiento.valueOf(rs.getString("talojamiento"));//.setTalojamiento(TipoAlojamiento.valueOf(rs.getString("talojamiento")));
				Float p = rs.getFloat("precio");//.setPrecio(rs.getFloat("precio"));
				Integer dur= rs.getInt("duracion");//.setDuracion(rs.getInt("duracion"));
				Ciudad c = obtenerCiudad(con, rs.getInt("id"));
			
				
				al = new Alojamiento(i,nom,tipo,p,dur,c);
				
				
				
				
			}
			rs.close();
			
			log(Level.INFO, "Se ha encontrado el alojamiento :" + al,null);
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al obtener de base de datos: " + sql, e );
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return al;
	}
	/**
	 * M�todo que obtiene los datos de una persona
	 * 
	 * @param con Conexi�n con la base de datos
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
	 * M�todo que obtiene los datos de un destino
	 * 
	 * @param con Conexi�n con la base de datos
	 * @param id Id del destino
	 * @return Devuelve el destino con dicho id
	 */
	public static Ciudad obtenerCiudad(Connection con, int id) {
		
		String sql = "SELECT * FROM Ciudad WHERE id ='" + id + "'";
		Ciudad c = null;
		log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
		try {
			log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String nom = rs.getString("nom");
				
				c = new Ciudad(id,nom);
			}
			log(Level.INFO, "Se ha obtenido: " + c, null);
			System.out.println(String.format("- Obtengo el destino:", c));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("*Error al obtener lod datosw del destino en la BBDD: %s", e.getMessage()));
		}
		return c;
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
	
	/**
	 * Método que elimina la reserva por el id introducido
	 * @param con conexión con la base de datos
	 * @param id id de la reserva que deseamos eliminar
	 */
	public static void eliminarReserva(Connection con, int id) {
		String sql = "DELETE FROM Reserva WHERE id="+id;
		try (Statement st = con.createStatement();){
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado la reserva con el id'" + id+ "'", result));
			log(Level.INFO, "Se ha eliminiado de la base de datos : " + result, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * nuevo
	 */
	
	/**
	 * METODO QUE INSERTA DESTINO
	 * @param con CONEXION
	 * @param id DEL DESTINO
	 * @param nombre DEL DESTINO
	 */
	public static void insertarDestino(Connection con, Integer id, String nombre) {
		String sql = "INSERT INTO Destino VALUES(" + id + ",'" + nombre + "');";
		try {
			
			Statement stmt = con.createStatement();

			log(Level.INFO, "Lanzada actualizaci�n a base de datos: " + sql, null);
			int resultado = stmt.executeUpdate(sql);
			log(Level.INFO, "A�adida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Destino");

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserci�n de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
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
	/**
	 * ACTUALIZAR EL PRECIO POR DIA DEL ALOJAMIENTO
	 * @param id EL IDENTIFICADOR DE ALOJAMIENTO
	 * @param precio EL PRECIO
	 * @param duracion LA DURACION(DIAS)
	 */
	public static void UpdatePrecioPorDuracion( Integer id,Float precio, Integer duracion) {
		String sql = "UPDATE Alojamiento SET precio=?, duracion=? WHERE id =?";
		PreparedStatement pstmt;
		
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")){
			pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1,precio);
			pstmt.setInt(2, duracion);
			pstmt.setInt(3,id);
			pstmt.executeUpdate();
		
			log(Level.INFO, "Se ha actualiza la sentencia:" + sql, null);
			pstmt.close();
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al actualizar de base de datos: " + sql, e );
			System.err.println(String.format("* Error alactualizar  datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	
	}
	
	public static void uptadeReservas(int id, Ciudad corigen, Ciudad cdestino, TipoAlojamiento tAlojamiento) {
		//String sql = "UPDATE Reserva SET "
	}
	
	/*
	 * EXCURSION
	 */
	public static void insertarExcursion(Connection con, Integer id,String nombre, String tipo, String lugar,Float precio, Integer duracion, Integer numPersonas) {
		String sql = "INSERT INTO Excursion VALUES(" + id + ",'" + nombre + "','" + tipo + "','" + lugar + "',"+precio+"," +duracion+ ","+numPersonas+");";
		try {
			Statement st = con.createStatement();

			log(Level.INFO, "Lanzada actualizaci�n a base de datos: " + sql, null);
			int resultado = st.executeUpdate(sql);
			log(Level.INFO, "A�adida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Excursion");

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserci�n de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	} 
	public static void EliminarExcursion(Connection con, int id) {
		
			try (Statement st = con.createStatement();) {
				String sql = "DELETE FROM Excursion WHERE id='" + id + "';";
				log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
				int result = st.executeUpdate(sql);
				System.out.println(String.format("- Se ha borrado la excursion con id '" + id + "'", result));
				log(Level.INFO, "Se ha eliminado de la base de datos: " + result, null);
			} catch (SQLException e) {
				log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
				System.err.println(String.format("* Error al eliminar la excursion de la BBDD: %s", e.getMessage()));
			}
	}
	/*
	 * nuevo
	 */
	public static Excursion obtenerDatosExcursion(Connection con, Integer id) {
		String sql = "SELECT * FROM Excursion WHERE dni=" + id+ "";
		Excursion excursion =null;
		try {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				Integer d = rs.getInt("id");
				String n = rs.getString("nombre");
				TipoExcursion tipo =TipoExcursion.valueOf( rs.getString("tipo"));
				Ciudad c = obtenerCiudad(con, rs.getInt("id"));
				//String e = rs.getString("lugar");
				
				Float precio = rs.getFloat("precio");
				Integer dur = rs.getInt("duracion");
				Integer num = rs.getInt("numPersonas");
				
				excursion = new Excursion(d,n,tipo,c,precio,dur,num);

				

			}
			log(Level.INFO, "Se ha obten: " + excursion, null);
			System.out.println(String.format("- Obtengo la persona:", excursion));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(
					String.format("* Error al obtener los  datos de la persona en la BBDD: %s", e.getMessage()));

		}
		return excursion;
	}
	public static ArrayList<Excursion> obtenerExcursiones() {
		ArrayList<Excursion> listaExcursion = new ArrayList<>();
		String sql = "SELECT * FROM Excursion WHERE id>=0";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")){
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			Excursion ex; 
			while (rs.next()) {
				ex = new Excursion();
				ex.setId(rs.getInt("id"));
				ex.setNombre(rs.getString("nombre"));
				ex.setTipo(TipoExcursion.valueOf(rs.getString("tipo")));
				Ciudad c = obtenerCiudad(con,rs.getInt("id"));
				ex.setLugar(c);
				ex.setPrecio(rs.getFloat("precio"));
				ex.setDuracion(rs.getInt("duracion"));
				ex.setNumPersonas(rs.getInt("numPersonas"));
				
				
				
				listaExcursion.add(ex);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d excursiones", listaExcursion.size()));
			log(Level.INFO, "Se han encontrado las siguientes excursiones:" + listaExcursion.size(), null);
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al obtener de base de datos: " + sql, e );
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaExcursion;
	}
	/**
	 * ACTUALIZA EL NUMERO DE PERSONAS DE LAS EXCURSIONES 
	 * @param con CONEXION CON LA BASE DE DATOS
	 * @param id EL ID DE LA EXCURSION
	 * @param num El numero de personas maximo de personas
	 */
	public static void UpdateNumeroPersonasEnExcursion( Integer id,Integer numPersonas) {
		String sql = "UPDATE Excursion SET numPersonas =? WHERE id =?";
		PreparedStatement pstmt;
		
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")){
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,numPersonas);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		
			log(Level.INFO, "Se ha actualiza la sentencia:" + sql, null);
			pstmt.close();
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al actualizar de base de datos: " + sql, e );
			System.err.println(String.format("* Error alactualizar  datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	
	}
	
	
	/*
	 * LOGGER
	 */

	// M�todo local para loggear (si no se asigna un logger externo, se asigna uno
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
