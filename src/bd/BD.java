package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import clases.Destino;
import clases.Persona;



public class BD {
	
	/**
	 * MÃ©todo que realiza la conexiÃ³n con la base de datos
	 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
	 * @return Devuelve la conexiÃ³n a la base de datos
	 */
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
					
		} catch (ClassNotFoundException ex) {

			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	/**
	 * Método que cierra la conexión con la base de datos
	 * @param con
	 */
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
				System.out.println("Cerrando la Base de Datos");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Método que crea las tablas en la base de datos
	 * @param con Conexión con la base de datos
	 */
	public static void crearTablas(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Persona (dni String, nom String, cont String, email String, tipo String)";
		String sql1 = "CREATE TABLE IF NOT EXISTS Destino (id String, nom String)";
		try {
			Statement st = con.createStatement();
			
			st.executeUpdate(sql);
			st.executeUpdate(sql1);
			System.out.println("--Se ha creado la tabla Persona");
			st.close();
		} catch (SQLException e) {
			System.err.println(String.format("* Error al crear la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Método que inserta a una persona en la base de datos
	 * @param con Conexión con la base de datos
	 * @param nom Nombre de la persona
	 * @param dni Dni de la persona
	 * @param cont Contraseña de la persona
	 * @param email Email de la persona
	 * @param tipo Tipo de persona
	 */
	public static void insertarPersona(Connection con, String nom, String dni, String cont, String email, String tipo) {
		String sql = "INSERT INTO Persona VALUES('"+dni+"','"+nom+"','"+cont+"','"+email+"','"+tipo+"')";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			
			System.out.println("--Se ha insertado a la tabla Persona");
			
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(String.format("* Error al insertar el archivo a la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que busca a una persona en la base de datos
	 * @param con Conexión con la base de datos
	 * @param dni Dni de la persona
	 * @return Devuelve la persona que buscamos
	 */
	public static boolean buscarPersona(Connection con, String dni) {
		String sql = "SELECT * FROM Persona WHERE dni='"+dni+"'";
		boolean personaEnc = false;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				personaEnc = true;
				System.out.println("--Se ha encontrado la Persona");
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(String.format("* Error al buscar la perdsona en la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return personaEnc;
	}
	
	/**
	 * Método que obtiene los datos de las personas
	 * @return Devuelve una lista con todas las personas
	 */
	public static List<Persona> obtenerDatos() {
		List<Persona> listaPersona = new ArrayList<>();
		String sql = "SELECT * FROM Persona WHERE  dni>=0 ";
		
		
		try(Connection con=DriverManager.getConnection("jdbc:sqlite:"+"confortTravel.db")) {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			Persona p;
			while(rs.next()) {
				p= new Persona();
				p.setDni(rs.getString("dni"));
				p.setNombre( rs.getString("nom"));
				p.setContrasenia(rs.getString("cont"));  
				p.setDireccion(rs.getString("email")); 
				p.setTipo(rs.getString("tipo")); 
				
				listaPersona.add(p);
				
				
				
			}
			rs.close();
			System.out.println(String.format("- Se han recuperado %d personas...", listaPersona.size()));			
		} catch (Exception e) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return listaPersona;
	}
	
	/**
	 * Método que obtiene los datos de una persona
	 * @param con Conexión con la base de datos
	 * @param dni Dni de la persona
	 * @return Devuelve la persona
	 */
	public static Persona obtenerDatosPersona(Connection con, String dni) {
		String sql = "SELECT * FROM Persona WHERE dni='"+dni+"'";
		Persona p = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				String d = rs.getString("dni");
				String n = rs.getString("nom");
				String c = rs.getString("cont");
				String e = rs.getString("email");
				String t = rs.getString("tipo");
				
				p = new Persona(d, n, c, e, t);
				
			}
			System.out.println(String.format("- Obtengo la persona:",p));		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(String.format("* Error al obtener los  datos de la persona en la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
		return p;
	}
	/**
	 * Eliminar la persona 
	 * @param con abrir la conexion con la BBDD
	 * @param dni de la persona
	 */
	public static  void eliminarPersona( Connection con, String dni) {
		
		try (Statement st = con.createStatement();){
			String sql = "DELETE FROM Persona WHERE dni='"+dni+"';";
			int result = st.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado la persona con el dni'"+dni+"'", result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(String.format("* Error al eliminar la persona de la BBDD: %s", e.getMessage()));
			e.printStackTrace();
		}
	
	}
	public static void obtenerTodosLosDestinos(Connection con,ArrayList<Destino> ldestino) {
		String sql=" ";
		
		
		try {
			sql="SELECT * FROM Destino";
			Statement st = con.createStatement();
			ResultSet	rs = st.executeQuery(sql);
			while(rs.next()) {
				String id= rs.getString("dni");
				String nombre =rs.getString("nombre");
				Destino d = new Destino(id, nombre);
				ldestino.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
