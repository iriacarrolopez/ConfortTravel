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
import clases.Destino;
import clases.Excursion;
import clases.Origen;
import clases.Persona;
import clases.Reserva;
import clases.TipoAlojamiento;
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
		String sql1 = "CREATE TABLE IF NOT EXISTS Destino (id Integer, nom String)";
		String sql2 = "CREATE TABLE IF NOT EXISTS Alojamiento (id Integer, nombre_comp String, talojamiento String, precio Float, duracion Integer, destino Integer)";
		String sql3 = "CREATE TABLE IF NOT EXISTS Excursion (id Integer, nombre String, tipo String,lugar String, precio Float,duracion Integer, numPersonas Integer)";
		String sql4 = "CREATE TABLE IF NOT EXISTS Reserva (id Integer, origen String, destino String, fechaInicio String, fechaFin String, alquilerTransporte String, tipoAlojamiento String, excursion String, actividades String)";
		String sql5 = "CREATE TABLE IF NOT EXISTS Origen (id Integer, nom String)";
		try {
			Statement st = con.createStatement();

			st.executeUpdate(sql);
			st.executeUpdate(sql1);
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
	
	
	/***
	 * M�todo que obtiene los destinos
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
				Destino d = obtenerDestino(con, rs.getInt("id"));
				al.setDestino(d);
				
				
				
				
				
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
				/*al.setId(rs.getInt("id"));
				al.setNombre_comp(rs.getString("nombre_comp"));
				al.setTalojamiento(TipoAlojamiento.valueOf(rs.getString("talojamiento")));
				al.setPrecio(rs.getFloat("precio"));
				al.setDuracion(rs.getInt("duracion"));
				Destino d = obtenerDestino(con, rs.getInt("id"));
				al.setDestino(d);
				
				r.setId(rs.getInt("id"));
				Origen o = obtenerOrigen(con, rs.getInt("id"));
				r.setOrigen(o);
				Destino d = obtenerDestino(con, rs.getInt("id"));
				r.setDestino(d);
				r.setFechaIni(rs.getString("fechaInicio"));
				r.setFechaFin(rs.getString("fechaFin"));
				r.setAlquilerTransporte(rs.getString("alquilerTransporte"));
				r.setTipoAlojamiento(rs.getString("tipoAlojamiento"));
				r.setExcursion(rs.getString("excursion");
				r.setActividades(rs.getString("actividades"));*/
				
				int dni = rs.getInt("id");
				Origen o = null;
				Destino d = null;
				//Origen o = rs.getString("origen");
				//Destino d = rs.getString("destino");
				String fechaIni = rs.getString("fechaInicio");
				String fechaFin = rs.getString("fechaFin");
				String alquilerTransporte = rs.getString("alquilerTransporte");
				String tipoA = rs.getString("tipoAlojamiento");
				TipoAlojamiento ta = TipoAlojamiento.valueOf(tipoA);
				String ex = rs.getString("excursiones");
				TipoExcursion te = TipoExcursion.valueOf(ex);
				String act = rs.getString("actividades");
				
				Reserva r = new Reserva(dni, o, d, fechaIni, fechaFin, alquilerTransporte, ta, te, act);
				
				listaReservas.add(r);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d alojamientos", listaReservas.size()));
			log(Level.INFO, "Se han encontrado los siguientes alojamientos:" + listaReservas.size(), null);
		} catch (SQLException e) {
			log( Level.SEVERE, "Error al obtener de base de datos: " + sql, e );
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaReservas;
	}
	/**
	 * Obtener un alojamiento mediante el id del alojamiento
	 * @param id del alojamienti
	 * @return devuelve el alojamiento con la id introducida 
	 */
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
				Destino d = obtenerDestino(con, rs.getInt("id"));
			
				
				al = new Alojamiento(i,nom,tipo,p,dur,d);
				
				
				
				
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
	public static Destino obtenerDestino(Connection con, int id) {
		
		String sql = "SELECT * FROM Destino WHERE id ='" + id + "'";
		Destino d = null;
		log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
		try {
			log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String nom = rs.getString("nom");
				
				d = new Destino(id,nom);
			}
			log(Level.INFO, "Se ha obtenido: " + d, null);
			System.out.println(String.format("- Obtengo el destino:", d));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("*Error al obtener lod datosw del destino en la BBDD: %s", e.getMessage()));
		}
		return d;
	}
	
	public static Origen obtenerOrigen(Connection con, int id) {
		String sql = "SELECT * FROM Origen WHERE id ='" + id + "'";
		Origen o = null;
		log(Level.INFO, "Lamzada consulta a base de datos: " + sql, null);
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				String nom = rs.getString("nom");
				
				o = new Origen(id, nom);
			}log(Level.INFO, "Se ha obtenido: " + o, null);
			System.out.println(String.format("- Obtengo el destino:", o));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("*Error al obtener lod datosw del destino en la BBDD: %s", e.getMessage()));
		}
		
		return o;
	}
	
	//IRIA
	/*public static TipoAlojamiento obtenerTipoAlojamiento(Connection con, int id) {
		String sql = "SELECT * FROM TipoAlojamiento WHERE id ='" + id + "'";
		TipoAlojamiento ta = null;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				String nom = rs.getString("nom");
				
				//ta = new TipoAlojamiento();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

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
	/*
	 * nuevo
	 */
	
	/**
	 * METODO QUE INSERTA ORIGEN
	 * @param con CONEXION
	 * @param id DEL ORIGEN
	 * @param nombre DEL ORIGEN
	 */
	public static void insertarOrigen(Connection con, Integer id, String nombre) {
		String sql = "INSERT INTO Origen VALUES(" + id + ",'" + nombre + "');";
		try {
			
			Statement stmt = con.createStatement();

			log(Level.INFO, "Lanzada actualizaci�n a base de datos: " + sql, null);
			int resultado = stmt.executeUpdate(sql);
			log(Level.INFO, "A�adida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Origen");

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
	 * Eliminar un origen
	 * 
	 * @param con abrir la conexion con la BBDD
	 * @param id de la persona 
	 */
	
	public static void eliminarOrigen(Connection con, int id) {
		try (Statement st = con.createStatement();) {
			String sql = "DELETE FROM Origen WHERE id='" + id + "';";
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);
			System.out.println(String.format("- Se ha borrado el origen con id '" + id + "'", result));
			log(Level.INFO, "Se ha eliminado de la base de datos: " + result, null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
			System.err.println(String.format("* Error al eliminar el origen de la BBDD: %s", e.getMessage()));
		}
	}
	
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
	/**
	 * Elimina un alojamiento pasando como parametro el id del alojamiento
	 * @param con Conexion con la base de datos
	 * @param id del alojamiento que queremos eliminar
	 */
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
	
	/*
	 * EXCURSION
	 */
	/**
	 * Insertar una nueva excursion
	 * @param con Conexion con la base de datos
	 * @param id de la excursion 
	 * @param nombre de la excursion 
	 * @param tipo de  excursion 
	 * @param lugar donde se realiza la excursion
	 * @param precio de la excursion
	 * @param duracion de la excursion 
	 * @param numPersonas .Maximo numero de personas que pueden participar en la excursion
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
	/**
	 * Eliminar una excursion ,pasando el id de la excurison
	 * @param con Conexion con la BBDD
	 * @param id de la excursion
	 */
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
	/**
	 * Obtener los datos de una sola excursion , pasando por parametro el id de ella.
	 * @param con Conexion con la base de datos
	 * @param id de la excursion
	 * @return  devuelve los datos de la excursion del  id pasado por  parametro
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
				Destino des = obtenerDestino(con, rs.getInt("id"));
				//String e = rs.getString("lugar");
				
				Float precio = rs.getFloat("precio");
				Integer dur = rs.getInt("duracion");
				Integer num = rs.getInt("numPersonas");
				
				excursion = new Excursion(d,n,tipo,des,precio,dur,num);

				

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
	/**
	 * OBTENER TODAS LAS EXCURSIONES
	 * @return devuelve una lista con todas las excursiones
	 */
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
				Destino des = obtenerDestino(con,rs.getInt("id"));
				ex.setLugar(des);
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
