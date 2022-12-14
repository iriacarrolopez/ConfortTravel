package bd;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.TreeMap;
import java.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;
import java.util.zip.CheckedOutputStream;

import javax.swing.plaf.multi.MultiPopupMenuUI;

import clases.Alojamiento;
import clases.Excursion;
import clases.Ciudad;
import clases.Cliente;
import clases.Persona;
import clases.Reserva;
import clases.TipoActividad;
import clases.TipoAlojamiento;
import clases.TipoAlquiler;
import clases.TipoExcursion;
import clases.TipoPersona;

public class BD {
	private static Logger logger = null;
	private static Properties properties;
	private Connection con = null;

	/**
	 * MÃ©todo que realiza la conexiÃ³n con la base de datos
	 * 
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexiÃ³n a la base de datos
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		// LEER EL FICHERO DE PROPIEDADES
		try (FileReader reader = new FileReader("conf/ConfortTravel.properties");) {

			// Lectura del fichero properties
			properties = new Properties();
			properties.load(reader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(String.format("No se pudo leer el fichero de propiedades %s", e.getMessage()));

			e.printStackTrace();
		}
		try (FileWriter writer = new FileWriter("ConfortTravel.properties");) {
			properties.store(writer, "Fichero de propiedades");

			System.out.println("El fichero de propiedades se ha guardado correctamente");
		} catch (IOException ioe2) {
			System.err.println(String.format("No se pudo leer el fichero de propiedades %s", ioe2.getMessage()));
		}
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			log(Level.INFO, "Conectada base de datos " + nombreBD, null);
		} catch (ClassNotFoundException ex) {

			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en conexiï¿½n de base de datos " + nombreBD, e);

		}

		return con;

	}

	/*
	 * NUEVO
	 */
	/**
	 * Obtener reservas por dni
	 * 
	 * @param dni_cliente que reciva
	 * @return un mapa
	 */
	public static TreeMap<String, ArrayList<Reserva>> obtenerTreeMapReservasPorDni(String dni_cliente) {
		TreeMap<String, ArrayList<Reserva>> tmR = new TreeMap<>();
		ArrayList<Reserva> aR = new ArrayList<>();

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {

			String sql1 = "SELECT * FROM Reserva WHERE dni='" + dni_cliente + "';";
			Statement st = con.createStatement();
			ResultSet rs1 = st.executeQuery(sql1);
			while (rs1.next()) {
				Reserva r = new Reserva();
				r.setId(rs1.getInt("id"));
				Ciudad co = obtenerCiudad(con, rs1.getInt("idOrigen"));
				r.setOrigen(co);
				Ciudad cd = obtenerCiudad(con, rs1.getInt("idDestino"));
				r.setDestino(cd);
				r.setFechaIni(rs1.getString("fechaInicio"));
				r.setFechaFin(rs1.getString("fechaFin"));
				r.setAlquilerTransporte(TipoAlquiler.valueOf(rs1.getString("alquilerTransporte")));
				r.setTipoAlojamiento(TipoAlojamiento.valueOf(rs1.getString("tipoAlojamiento")));
				r.setExcursion(TipoExcursion.valueOf(rs1.getString("excursion")));
				r.setActividades(TipoActividad.valueOf(rs1.getString("actividades")));
				r.setDni(rs1.getString("dni"));
				r.setPrecio(rs1.getFloat("precio"));
				aR.add(r);
				System.out.println(aR);
				log(Level.INFO, "El array de reservas contiene" + aR, null);
				tmR.put(dni_cliente, aR);
				System.out.println(tmR);
				log(Level.INFO, "El mapa contiene :" + tmR, null);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log(Level.WARNING, "Error al cargar el mapa", e);
		}
		return tmR;

	}
	/**
	 * Metodo que devuelve un array de clientes
	 * @param tipo Si es administrador o cliente
	 * @return arraylist de clientes
	 */
	public static ArrayList<Persona> ObtenerClientes(String tipo) {
		ArrayList<Persona> lp = new ArrayList<>();
		String sql = "SELECT * FROM Persona WHERE tipo ='" + tipo + "';";

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (tipo == "CLIENTE") {
					// CREATE TABLE IF NOT EXISTS Persona (dni String, nom String, cont String,
					// email String, tipo String)";
					Persona p = new Persona();
					p.setDni(rs.getString("dni"));
					p.setNombre(rs.getString("nom"));
					p.setContrasenia(rs.getString("cont"));

					p.setEmail(rs.getString("email"));
					p.setTipo(TipoPersona.valueOf(rs.getString("tipo")));
					lp.add(p);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lp;

	}

	/**
	 * Metodo que cierra la conexion con la base de datos
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
	 * Metodo que crea las tablas en la base de datos
	 * 
	 * @param con Conexion con la base de datos
	 */
	/* CAMBIO EN EL FORMATO DE CREAR LAS TABLAS PARA HACER LA FOREIGN KEY */
	public static void crearTablas(Connection con) {
		String sql1 = "CREATE TABLE IF NOT EXISTS Persona (Dni  VARCHAR(10) PRIMARY KEY NOT NULL, nom VARCHAR(20), cont VARCHAR(20), email VARCHAR(20), tipo VARCHAR(20));";
		log(Level.INFO, "Statement" + sql1, null);
		String sql2 = "CREATE TABLE IF NOT EXISTS Alojamiento (id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre_comp VARCHAR(20), talojamiento VARCHAR(20), precio FLOAT(3), duracion INTEGER(3), destino INTEGER(2),FOREIGN KEY(destino) REFERENCES Ciudad(id) ON DELETE CASCADE);";
		log(Level.INFO, "Statement" + sql2, null);
		String sql3 = "CREATE TABLE IF NOT EXISTS Excursion (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre VARCHAR(20), tipo VARCHAR(20),lugar VARCHAR(20), precio FLOAT(3),duracion INTEGER(3), numPersonas INTEGER(4));";
		log(Level.INFO, "Statement" + sql3, null);
		String sql4 = "CREATE TABLE IF NOT EXISTS Reserva (id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, idOrigen INTEGER(2), idDestino INTEGER(2), fechaInicio VARCHAR(20), fechaFin VARCHAR(20), alquilerTransporte VARCHAR(20), tipoAlojamiento VARCHAR(20), excursion VARCHAR(20), actividades VARCHAR(20), dni VARCHAR(10), precio FLOAT(6), FOREIGN KEY(dni) REFERENCES Persona(Dni) ON DELETE CASCADE);";
		log(Level.INFO, "Statement" + sql4, null);
		String sql5 = "CREATE TABLE IF NOT EXISTS Ciudad (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nom VARCHAR(20));";
		log(Level.INFO, "Statement" + sql5, null);
		try {
			Statement st = con.createStatement();

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

			// st.close();
		} catch (SQLException e) {
			System.err.println(String.format("* Error al crear la BBDD: %s", e.getMessage()));
			log(Level.SEVERE, "Error al crea las tablas de la BBDD", e);

		}
	}

	/**
	 * 
	 * Metodo que inserta a una persona en la base de datos
	 * 
	 * @param con   Conexion con la base de datos
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

			log(Level.INFO, "Lanzada actualizaciï¿½n a base de datos: " + sql, null);
			int resultado = st.executeUpdate(sql);
			log(Level.INFO, "Aï¿½adida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Persona");

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserciï¿½n de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que inserta un alojamiento
	 * 
	 * @param con      Conexion con la base de datos
	 * @param id       del alojamiento
	 * @param nombre   del alojamiento
	 * @param tipo     de alojamiento
	 * @param precio   del alojamiento
	 * @param duracion de la estancia
	 * @param destino  del alojamiento
	 */
	public static void insertarAlojamiento(Connection con, Integer id, String nombre, String tipo, float precio,
			Integer duracion, Integer destino) {
		String sql = "INSERT INTO Alojamiento VALUES(" + id + ",'" + nombre + "','" + tipo + "'," + precio + ","
				+ duracion + "," + destino + ");";
		try {
			Statement stmt = con.createStatement();
			log(Level.INFO, "Lanzada actualizaciï¿½n a base de datos: " + sql, null);
			int resultado = stmt.executeUpdate(sql);
			log(Level.INFO, "Aï¿½adida " + resultado + " fila a base de datos\t" + sql, null);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserciï¿½n de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	/**
	 * Mï¿½todo que busca a una persona en la base de datos
	 * 
	 * @param con Conexiï¿½n con la base de datos
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
			log(Level.SEVERE, "Error en bï¿½squeda de base de datos: " + sql, e);
			System.err.println(String.format("* Error al buscar la perdsona en la BBDD: %s", e.getMessage()));

		}
		return personaEnc;
	}

	/**
	 * Mï¿½todo que obtiene los datos de las personas
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
				p.setTipo(TipoPersona.valueOf(rs.getString("tipo")));

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

	/**
	 * MÃ©todo que devuelve el nombre de la ciudad por el id
	 * 
	 * @param con conexion con la base de datos
	 * @param id  id de la ciudad
	 * @return
	 */
	public static Ciudad getCiudad(Connection con, int id) {
		Ciudad c = null;
		String sql = "SELECT * FROM Ciudad WHERE id=" + id;
		try {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				c = new Ciudad(rs.getInt("id"), rs.getString("nom"));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error en bï¿½squeda de base de datos: " + sql, e);
			System.err.println(String.format("* Error al buscar la perdsona en la BBDD: %s", e.getMessage()));

		}
		return c;
	}

	/***
	 * Mï¿½todo que obtiene los destinos
	 * 
	 * @return Devuelve un ArrayList con todos los destinos
	 */

	public static ArrayList<Ciudad> obtenerTodasCiudades() {
		ArrayList<Ciudad> ciudades = new ArrayList<>();
		String sql = "SELECT * FROM Ciudad";

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
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
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}

		return ciudades;
	}

	/***
	 * 
	 * Mï¿½todo que obtiene los alojamientos
	 * 
	 * @return Devuelve un ArrayList con todos los alojamientos
	 */
	public static ArrayList<Alojamiento> obtenerAlojamientos() {
		ArrayList<Alojamiento> listaAlojamiento = new ArrayList<>();
		String sql = "SELECT * FROM Alojamiento WHERE id>=0";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
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
				//Ciudad c = obtenerCiudad(con, rs.getInt("id"));
				Ciudad c = getCiudadByID(rs.getInt("id"));
				al.setCiudad(c);

				listaAlojamiento.add(al);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d alojamientos", listaAlojamiento.size()));
			log(Level.INFO, "Se han encontrado los siguientes alojamientos:" + listaAlojamiento.size(), null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaAlojamiento;
	}

	// C
	/**
	 * Metodo que devuelve todas las reservas
	 * @return arraylist con todas las reservas
	 */
	public static ArrayList<Reserva> obtenerReservas() {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String sql = "SELECT * FROM Reserva WHERE id>=0";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				Integer dni = rs.getInt("id");
				Integer o = rs.getInt("idOrigen");
				Integer d = rs.getInt("idDestino");
				Ciudad co = getCiudad(con, o);
				Ciudad cd = getCiudad(con, d);
				String fechaIni = rs.getString("fechaInicio");
				String fechaFin = rs.getString("fechaFin");
				String at = rs.getString("alquilerTransporte");
				TipoAlquiler alquilerTransporte = TipoAlquiler.valueOf(at);
				String tipoA = rs.getString("tipoAlojamiento");
				TipoAlojamiento ta = TipoAlojamiento.valueOf(tipoA);
				String ex = rs.getString("excursion");
				TipoExcursion te = TipoExcursion.valueOf(ex);
				String act = rs.getString("actividades");
				TipoActividad tact = TipoActividad.valueOf(act);
				String dni_c = rs.getString("dni");
				Float precio = rs.getFloat("precio");

				Reserva r = new Reserva(dni, co, cd, fechaIni, fechaFin, alquilerTransporte, ta, te, tact, dni_c,
						precio);

				listaReservas.add(r);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d reservas", listaReservas.size()));
			log(Level.INFO, "Se han encontrado las siguientes reservas:" + listaReservas.size(), null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaReservas;
	}

	/***
	 * 
	 * Método para obtener las reservas en una persona
	 * 
	 * @param dni dni de la persona que queremos las reservas
	 * @return arraylist de todas las reservas de esa persona
	 */
	public static ArrayList<Reserva> obtenerReservasDni(String dni) {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String sql = "SELECT * FROM Reserva WHERE dni='" + dni + "';";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos,RESERVAS: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				Integer id = rs.getInt("id");
				int o = rs.getInt("idOrigen");
				int d = rs.getInt("idDestino");
				Ciudad co = getCiudad(con, o);
				Ciudad cd = getCiudad(con, d);
				String fechaIni = rs.getString("fechaInicio");
				String fechaFin = rs.getString("fechaFin");
				String at = rs.getString("alquilerTransporte");
				TipoAlquiler alquilerTransporte = TipoAlquiler.valueOf(at);
				String tipoA = rs.getString("tipoAlojamiento");
				TipoAlojamiento ta = TipoAlojamiento.valueOf(tipoA);
				String ex = rs.getString("excursion");
				TipoExcursion te = TipoExcursion.valueOf(ex);
				String act = rs.getString("actividades");
				TipoActividad tact = TipoActividad.valueOf(act);
				String dniCliente = rs.getString("dni");
				Float precio = rs.getFloat("precio");

				Reserva r = new Reserva(id, co, cd, fechaIni, fechaFin, alquilerTransporte, ta, te, tact, dniCliente,
						precio);

				listaReservas.add(r);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d reservas", listaReservas.size()));
			log(Level.INFO, "Se han encontrado las siguientes reservas:" + listaReservas.size(), null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaReservas;
	}
	/**
	 * Metodo para obtener todas las reservas por un id
	 * @param id id de la reserva
	 * @return arraylist de las reservas asociadas a ese id
	 */
	public static ArrayList<Reserva> obtenerReservasPorId(Integer id) {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String sql = "SELECT FROM Reserva WHERE id=" + id + ";";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int idReserva = rs.getInt("id");
				int idOrigen = rs.getInt("idOrigen");
				int idDestino = rs.getInt("idDestino");
				Ciudad cOrigen = getCiudad(con, idOrigen);
				Ciudad cDestino = getCiudad(con, idDestino);
				String fechaIni = rs.getString("fechaInicio");
				String fechaFin = rs.getString("fechaFin");
				String alquiler = rs.getString("alquilerTransporte");
				TipoAlquiler tipoAlquiler = TipoAlquiler.valueOf(alquiler);
				String alojamiento = rs.getString("tipoAlojamiento");
				TipoAlojamiento tipoAlojamiento = TipoAlojamiento.valueOf(alojamiento);
				String actividad = rs.getString("actividades");
				TipoActividad tipoActividad = TipoActividad.valueOf(actividad);
				String excursion = rs.getString("excursion");
				TipoExcursion tipoExcursion = TipoExcursion.valueOf(excursion);
				String dni = rs.getString("dni");
				Float precio = rs.getFloat("precio");

				Reserva r = new Reserva(idReserva, cOrigen, cDestino, fechaIni, fechaFin, tipoAlquiler, tipoAlojamiento,
						tipoExcursion, tipoActividad, dni, precio);
				listaReservas.add(r);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado % reservas", listaReservas.size()));
			log(Level.INFO, "Se han encontrado las siguientes reservas:" + listaReservas.size(), null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaReservas;
	}
	 
	public static ArrayList<Excursion> obtenerExcursionesPorDestino(Ciudad destino) {
		ArrayList<Excursion> listaExcursiones = new ArrayList<>();
		String sql = "SELECT * FROM Excursion WHERE lugar='" + destino.getNombre() + "';";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: "+sql,null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String nombreTipo = rs.getString("tipo");
				TipoExcursion tipo = TipoExcursion.valueOf(nombreTipo);
				Float precio = rs.getFloat("precio");
				int numPersonas = rs.getInt("numPersonas");
				int duracion = rs.getInt("duracion");
				Excursion e = new Excursion(id,nombre,tipo,destino,precio,numPersonas, duracion);
				listaExcursiones.add(e);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d excursiones", listaExcursiones.size()));
			log(Level.INFO, "Se han encontrado las siguientes excursiones:" + listaExcursiones.size(), null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaExcursiones;
	}
	/**
	 * Metodo que devuelve un arraylist asociado al id de un destino
	 * @param id id del destino de la reserva
	 * @return arraylist de todas lasr reservas de ese destino
	 */
	public static ArrayList<Reserva> obtenerReservasPorDestino(Integer id) {
		ArrayList<Reserva> listaReservas = new ArrayList<>();
		String sql = "SELECT * FROM Reserva WHERE idDestino=" + id + ";";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {

				int idReserva = rs.getInt("id");
				int o = rs.getInt("idOrigen");
				int d = rs.getInt("idDestino");
				Ciudad co = getCiudad(con, o);
				Ciudad cd = getCiudad(con, d);
				String fechaIni = rs.getString("fechaInicio");
				String fechaFin = rs.getString("fechaFin");
				String at = rs.getString("alquilerTransporte");
				TipoAlquiler alquilerTransporte = TipoAlquiler.valueOf(at);
				String tipoA = rs.getString("tipoAlojamiento");
				TipoAlojamiento ta = TipoAlojamiento.valueOf(tipoA);
				String ex = rs.getString("excursion");
				TipoExcursion te = TipoExcursion.valueOf(ex);
				String act = rs.getString("actividades");
				TipoActividad tact = TipoActividad.valueOf(act);
				String dni = rs.getString("dni");
				Float precio = rs.getFloat("precio");

				Reserva r = new Reserva(idReserva, co, cd, fechaIni, fechaFin, alquilerTransporte, ta, te, tact, dni,
						precio);

				listaReservas.add(r);
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d reservas", listaReservas.size()));
			log(Level.INFO, "Se han encontrado las siguientes reservas:" + listaReservas.size(), null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaReservas;
	}
	/**
	 * Metodo que devuelve un alojamiento 
	 * @param id id del alojamiento
	 * @return alojamiento
	 */
	public static Alojamiento obtenerAlojamientosPorid(Integer id) {

		String sql = "SELECT * FROM Alojamiento WHERE id=" + id + ";";
		Alojamiento al = null;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				Integer i = rs.getInt("id");// al.setId(rs.getInt("id"));
				String nom = rs.getString("nombre_comp");// al.setNombre_comp(rs.getString("nombre_comp"));
				TipoAlojamiento tipo = TipoAlojamiento.valueOf(rs.getString("talojamiento"));// .setTalojamiento(TipoAlojamiento.valueOf(rs.getString("talojamiento")));
				Float p = rs.getFloat("precio");// .setPrecio(rs.getFloat("precio"));
				Integer dur = rs.getInt("duracion");// .setDuracion(rs.getInt("duracion"));
				Ciudad c = obtenerCiudad(con, rs.getInt("id"));

				al = new Alojamiento(i, nom, tipo, p, dur, c);

			}
			rs.close();

			log(Level.INFO, "Se ha encontrado el alojamiento :" + al, null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return al;
	}
	/**
	 * Metodo que devuelve una ciudad
	 * @param id id de la ciudad
	 * @return ciudad
	 */
	public static Ciudad obtenerDestinosPorId(Integer id) {
		String sql = "SELECT * FROM Ciudad WHERE id=" + id + ";";
		Ciudad c = null;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Integer i = rs.getInt("id");

				String nom = rs.getString("nom");

				c = new Ciudad(i, nom);
				System.out.println(c);
			}
			rs.close();
			log(Level.INFO, "Se ha encontrado el destino" + c, null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Mï¿½todo que obtiene los datos de una persona
	 * 
	 * @param con Conexiï¿½n con la base de datos
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
				TipoPersona tipo = TipoPersona.valueOf(rs.getString("tipo"));

				p = new Persona(d, n, c, e, tipo);

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
	 * Mï¿½todo que obtiene los datos de un destino
	 * 
	 * @param con Conexiï¿½n con la base de datos
	 * @param id  Id del destino
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
				int i = rs.getInt("id");
				String nom = rs.getString("nom");

				c = new Ciudad(i, nom);
			}
			log(Level.INFO, "Se ha obtenido: " + c, null);
			System.out.println(String.format("- Obtengo el destino:", c));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err
					.println(String.format("*Error al obtener lod datosw del destino en la BBDD: %s", e.getMessage()));
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
	 * MÃ©todo que elimina la reserva por el id introducido
	 * 
	 * @param con conexiÃ³n con la base de datos
	 * @param id  id de la reserva que deseamos eliminar
	 */
	public static void eliminarReserva(int id) {
		String sql = "DELETE FROM Reserva WHERE id=" + id;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);

			System.out.println(String.format("- Se han borrado la reserva con el id'" + id + "'", result));
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
	 * 
	 * @param con    CONEXION
	 * @param id     DEL DESTINO
	 * @param nombre DEL DESTINO
	 */
	public static void insertarDestino(Connection con, Integer id, String nombre) {
		String sql = "INSERT INTO Ciudad VALUES(" + id + ",'" + nombre + "');";
		try {

			Statement stmt = con.createStatement();

			log(Level.INFO, "Lanzada actualizaciï¿½n a base de datos: " + sql, null);
			int resultado = stmt.executeUpdate(sql);
			log(Level.INFO, "Aï¿½adida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Ciudad");

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserciï¿½n de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	// N
	/**
	 * Metodo que inserta una reserva en la bd
	 * @param id id de la reserva
	 * @param idOrigen id de la ciudad origen
	 * @param idDestino id de la ciudad destino
	 * @param fechaIni fecha del inicio del viaje
	 * @param fechaFin fecha del fin del viaje
	 * @param alquilerTransporte alquiler del transporte
	 * @param tipoAlojamiento tipo de alojamiento que se desea
	 * @param excursion excursion a realizar
	 * @param actividades actividad a realizar
	 * @param dni dni del cliente que hace la reserva
	 * @param precio precio de la reserva
	 */
	public static void insertarReserva( Integer id, Integer idOrigen, Integer idDestino, String fechaIni,
			String fechaFin, String alquilerTransporte, String tipoAlojamiento, String excursion, String actividades,
			String dni, Float precio) {
		String sql = "INSERT INTO Reserva VALUES(" + id + "," + idOrigen + "," + idDestino + ",'" + fechaIni + "','"
				+ fechaFin + "','" + alquilerTransporte + "','" + tipoAlojamiento + "','" + excursion + "','"
				+ actividades + "','" + dni + "'," + precio + ");";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")){

			Statement stmt = con.createStatement();

			log(Level.INFO, "Lanzada actualización a base de datos: " + sql, null);
			int resultado = stmt.executeUpdate(sql);
			log(Level.INFO, "Añadida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Destino");

			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserción de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	/***
	 * 
	 * Eliminar un destino
	 * 
	 * @param con abrir la conexion con la BBDD
	 * 
	 */
	// N
	public static void eliminarDestino(Connection con, int id) {
		try (Statement st = con.createStatement();) {
			String sql = "DELETE FROM Ciudad WHERE id='" + id + "';";
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);
			System.out.println(String.format("- Se ha borrado el destino con id '" + id + "'", result));
			log(Level.INFO, "Se ha eliminado de la base de datos: " + result, null);
		} catch (SQLException e) {
			log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
			System.err.println(String.format("* Error al eliminar el destino de la BBDD: %s", e.getMessage()));
		}
	}

	// N
	/**
	 * Metodo que elimina un alojamiento de la bd
	 * @param con conexion con la bd
	 * @param id id del alojamiento a eliminar
	 */
	public static void eliminarAlojamiento(Connection con,Integer id) {
		try (Statement st = con.createStatement();) {
			String sql = "DELETE FROM Alojamiento WHERE id = "+id+" ;";
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);
			log(Level.INFO, "Se ha eliminado de la base de datos: " + result, null);

		} catch (SQLException e) {
			log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
			System.err.println(String.format("* Error al eliminar el alojamiento de la BBDD: %s", e.getMessage()));
		}
	}

	/**
	 * ACTUALIZAR EL PRECIO POR DIA DEL ALOJAMIENTO
	 * 
	 * @param id       EL IDENTIFICADOR DE ALOJAMIENTO
	 * @param precio   EL PRECIO
	 * @param duracion LA DURACION(DIAS)
	 */
	public static void UpdatePrecioPorDuracion(Integer id, Float precio, Integer duracion) {
		String sql = "UPDATE Alojamiento SET precio=?, duracion=? WHERE id =?";
		PreparedStatement pstmt;

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, precio);
			pstmt.setInt(2, duracion);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();

			log(Level.INFO, "Se ha actualiza la sentencia:" + sql, null);
			pstmt.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al actualizar de base de datos: " + sql, e);
			System.err.println(String.format("* Error alactualizar  datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}

	}

	/*
	 * String sql4 = "CREATE TABLE IF NOT EXISTS Reserva ( id Integer, idOrigen
	 * Integer, idDestino Integer, fechaInicio String, fechaFin String,
	 * alquilerTransporte String, tipoAlojamiento String, excursion String,
	 * actividades String)";
	 */
	/**
	 * Metodo que modifica las reservas
	 * @param id id de la reserva
	 * @param tipoAlquiler alquiler modificado
	 * @param tipoAlojamiento alojamiento modificado
	 * @param tipoExcursion excursion modificada
	 * @param tipoActividad actividad modificada
	 * @param precio precio de la reserva
	 */
	public static void uptadeReservas(Integer id, String tipoAlquiler, String tipoAlojamiento, String tipoExcursion,
			String tipoActividad, float precio) {
		String sql = "UPDATE Reserva SET alquilerTransporte=?, tipoAlojamiento=?, excursion=?, actividades=? WHERE id=?";
		PreparedStatement pstmt;

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tipoAlquiler);
			pstmt.setString(2, tipoAlojamiento);
			pstmt.setString(3, tipoExcursion);
			pstmt.setString(4, tipoActividad);
			pstmt.setInt(5, id);
			pstmt.setFloat(6, precio);
			pstmt.executeUpdate();

			log(Level.INFO, "Se ha actualiza la sentencia:" + sql, null);
			pstmt.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al actualizar de base de datos: " + sql, e);
			System.err.println(String.format("* Error alactualizar  datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que modifica el nombre del destino
	 * @param id id de la ciudad destino
	 * @param nombre nombre modificado
	 */
	public static void UpdateNombreDestino(Integer id, String nombre) {
		String sql = "UPDATE Ciudad SET nom=? WHERE id=?";
		PreparedStatement pstmt;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(2, id);
			pstmt.setString(1, nombre);
			pstmt.executeUpdate();

			log(Level.INFO, " Id destino :" + id, null);
			log(Level.INFO, " Nombre nuevo destino :" + nombre, null);

			log(Level.INFO, "Se ha actualizado la sentencia:" + sql, null);
			pstmt.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al actualizar la base de datos: " + sql, e);
			System.err.println(String.format("*Error al actualizar datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	/*
	 * EXCURSION
	 */
	/**
	 * Metodo que inserta una excursion en la bd
	 * @param con conexion con la bd
	 * @param id id de la excursion
	 * @param nombre nombre de la excursion
	 * @param tipo tipo de excursion
	 * @param lugar lugar de la excursion
	 * @param precio precio de la excursion
	 * @param duracion duracion de la excursion
	 * @param numPersonas personas que realizaran la excursion
	 */
	public static void insertarExcursion(Connection con, Integer id, String nombre, String tipo, String lugar,
			Float precio, Integer duracion, Integer numPersonas) {
		String sql = "INSERT INTO Excursion VALUES(" + id + ",'" + nombre + "','" + tipo + "','" + lugar + "'," + precio
				+ "," + duracion + "," + numPersonas + ");";
		try {
			Statement st = con.createStatement();

			log(Level.INFO, "Lanzada actualizaciï¿½n a base de datos: " + sql, null);
			int resultado = st.executeUpdate(sql);
			log(Level.INFO, "Aï¿½adida " + resultado + " fila a base de datos\t" + sql, null);
			System.out.println("--Se ha insertado a la tabla Excursion");

			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log(Level.SEVERE, "Error en inserciï¿½n de base de datos\t" + sql, e);
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	// N
	/**
	 * Metodo que elimina una excursion
	 * @param con conexion con la bd
	 * @param id id de la excursion
	 */
	public static void EliminarExcursion(Connection con, int id) {

		try (Statement st = con.createStatement();) {
			String sql = "DELETE FROM Excursion WHERE id='" + id + "';";
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			int result = st.executeUpdate(sql);
			log(Level.INFO, "Se ha eliminado de la base de datos: " + result, null);
			System.out.println(String.format("- Se ha borrado la excursion con id '" + id + "'", result));
		} catch (SQLException e) {
			log(Level.SEVERE, "Error la eliminacion de base de datos: " + e, null);
			System.err.println(String.format("* Error al eliminar la excursion de la BBDD: %s", e.getMessage()));
		}
	}

	/*
	 * nuevo
	 */
	/**
	 * Metodo que devuelve los datos de una excursion
	 * @param id id de la excurion a devolver
	 * @return excursion
	 */
	public static Excursion obtenerDatosExcursion(Integer id) {
		String sql = "SELECT * FROM Excursion WHERE dni=" + id + "";
		Excursion excursion = null;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				Integer d = rs.getInt("id");
				String n = rs.getString("nombre");
				TipoExcursion tipo = TipoExcursion.valueOf(rs.getString("tipo"));
				Ciudad c = obtenerCiudad(con, rs.getInt("id"));
				// String e = rs.getString("lugar");

				Float precio = rs.getFloat("precio");
				Integer dur = rs.getInt("duracion");
				Integer num = rs.getInt("numPersonas");

				excursion = new Excursion(d, n, tipo, c, precio, dur, num);

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
	 * Metodo que devuelve un arraylist de excursiones
	 * @return arraylist de excursiones
	 */
	public static ArrayList<Excursion> obtenerExcursiones() {
		ArrayList<Excursion> listaExcursion = new ArrayList<>();
		String sql = "SELECT * FROM Excursion WHERE id>=0";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			Excursion ex;
			while (rs.next()) {
				ex = new Excursion();
				ex.setId(rs.getInt("id"));
				ex.setNombre(rs.getString("nombre"));
				ex.setTipo(TipoExcursion.valueOf(rs.getString("tipo")));
				Ciudad c = obtenerCiudad(con, rs.getInt("id"));
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
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaExcursion;
	}

	// n
	/**
	 * Metodo que devuelve una ciudad por su nombre
	 * @param nombre nombre de la ciudad
	 * @return todos los datos de una ciudad
	 */
	public static Ciudad getCiudadByNombre(String nombre) {
		Ciudad c = null;
		String sql = "SELECT * FROM Ciudad WHERE nom = ? LIMIT 1;";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				c = new Ciudad();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("nom"));

			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return c;

	}
	//n
	/**
	 * Metodo que devuelve una ciudad por su id
	 * @param id id de la ciudad
	 * @return todos los datos de una ciudad
	 */
	public static Ciudad getCiudadByID(int id) {
		Ciudad c = null;
		String sql = "SELECT nom FROM Ciudad WHERE id = ? ;";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				c = new Ciudad();
				
				c.setNombre(rs.getString("nom"));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	/**
	 * Metodo que devuelve el id del destino y recibe el nombre del mismo
	 * @param nombre nombre del destino
	 * @return id del destino
	 */
	public static int obtenerIdDestino(String nombre) {
		int id = 0;
		String sql = "SELECT * FROM Ciudad WHERE nom = " + nombre + ";";
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			log(Level.INFO, "Lanzada consulta a base de datos: " + sql, null);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			id = rs.getInt(id);
			rs.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al obtener de base de datos: " + sql, e);
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * ACTUALIZA EL NUMERO DE PERSONAS DE LAS EXCURSIONES
	 * 
	 * @param con CONEXION CON LA BASE DE DATOS
	 * @param id  EL ID DE LA EXCURSION
	 * @param num El numero de personas maximo de personas
	 */
	public static void UpdateNumeroPersonasEnExcursion(Integer id, Integer numPersonas) {
		String sql = "UPDATE Excursion SET numPersonas =? WHERE id =?";
		PreparedStatement pstmt;

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numPersonas);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();

			log(Level.INFO, "Se ha actualiza la sentencia:" + sql, null);
			pstmt.close();
		} catch (SQLException e) {
			log(Level.SEVERE, "Error al actualizar de base de datos: " + sql, e);
			System.err.println(String.format("* Error alactualizar  datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}

	}
	
	/**
	 * Metodo que devuelve el mayor codigo de todas las reservas
	 * @return mayor codigo de las reservas
	 */
	public static int obtenerMayorCodigoReserva() {
		String sql = "SELECT MAX(id) FROM Reserva";
		int max = 0;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				max = rs.getInt(1);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
	}
	
	/**
	 * Metodo que devuelve todas las reservas que ha hecho un cliente
	 * @param dni dni del cliente que ha hecho las reservas
	 * @return lista con todas las reservas de un cliente
	 */
	public static List<Reserva> obtenerReservasPorCliente(String dni){
		String sql = "SELECT * FROM Reserva WHERE dni='"+dni+"'";
		List<Reserva> reservasCliente = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + "confortTravel.db")) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int o = rs.getInt("idOrigen");
				int d = rs.getInt("idDestino");
				Ciudad co = getCiudad(con, o);
				Ciudad cd = getCiudad(con, d);
				String fechaIni = rs.getString("fechaInicio");
				String fechaFin = rs.getString("fechaFin");
				String at = rs.getString("alquilerTransporte");
				TipoAlquiler alquilerTransporte = TipoAlquiler.valueOf(at);
				String tipoA = rs.getString("tipoAlojamiento");
				TipoAlojamiento ta = TipoAlojamiento.valueOf(tipoA);
				String ex = rs.getString("excursion");
				TipoExcursion te = TipoExcursion.valueOf(ex);
				String act = rs.getString("actividades");
				TipoActividad tact = TipoActividad.valueOf(act);
				String dniReserva = rs.getString("dni");
				Float precio = rs.getFloat("precio");
				
				Reserva r = new Reserva(id, co, cd, fechaIni, fechaFin, alquilerTransporte, ta, te, tact, dniReserva, o);
				
				reservasCliente.add(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reservasCliente;
	}

	/*
	 * LOGGER
	 */


	/**
	 * Metodo local para loggear (si no se asigna un logger externo, se asigna uno local)
	 * @param level tipo de logger
	 * @param msg mensage del logger
	 * @param excepcion excepcion del logger
	 */
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
