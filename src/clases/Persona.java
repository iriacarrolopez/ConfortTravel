package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
//N
public class Persona implements Comparable<Persona>{
	private String dni;
	private String nombre;
	private String apellido;
	private int telefono; // Cambiar a String
	private String email;
	private String direccion;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Date fechaNac; // Cambiar a date
	private String nomUsuario;
	private String contrasenia;
	//private String tipo;
	private TipoPersona tipo;


	/**
	 * Constructor sin parametros
	 */
	public Persona() {

	}

	/**
	 * Constructor con parametros
	 * @param dni
	 * @param nombre
	 * @param contrasenia
	 * @param email
	 * @param tipo
	 */
	public Persona(String dni, String nombre, String contrasenia, String email, TipoPersona tipo) {
		this.dni = dni;
		// Comprobar si el dni es correcto
		String erdni = "[0-9]{8}[A-Z]";
		boolean correctoDni = Pattern.matches(erdni, "12345678H");
		if (correctoDni)
			System.out.println("El DNI es correcto");
		else
			System.out.println("El DNI no es correcto");
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.email = email;
		this.tipo =tipo;
	}

	/**
	 * Constructor con parametros
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param email
	 * @param direccion
	 * @param fechaNac
	 * @param nomUsuario
	 * @param contrasenia
	 * @param tipo
	 */
	public Persona(String dni, String nombre, String apellido, int telefono, String email, String direccion,
			String fechaNac, String nomUsuario, String contrasenia,TipoPersona tipo) {
		this.dni = dni;
		// Comprobar si el dni es correcto
		String erdni = "[0-9]{8}[A-Z]";
		boolean correctoDni = Pattern.matches(erdni, "12345678H");
		if (correctoDni)
			System.out.println("El DNI es correcto");
		else
			System.out.println("El DNI no es correcto");
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		try {
			this.fechaNac = sdf.parse(fechaNac);
		} catch (ParseException e) {
			this.fechaNac = new Date(System.currentTimeMillis());
		}
		this.nomUsuario = nomUsuario;
		this.contrasenia = contrasenia;
		this.tipo = tipo;
	}

	/**
	 * Getter del dni de la persona
	 * @return dni de la persona
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Setter del dni de la persona
	 * @param dni nuevo dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Getter del nombre de la persona
	 * @return nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre de la persona
	 * @param nombre nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter del apellido de la persona
	 * @return apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Setter del apellido de la persona
	 * @param apellido nuevo apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Getter del telefono de la persona
	 * @return telefono de la persona
	 */
	public int getTelefono() {
		return telefono;
	}

	/**
	 * Setter del telefono de la persona
	 * @param telefono nuevo telefono
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	/**
	 * Getter del email de la persona
	 * @return email de la persona
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter del email de la persona
	 * @param email nuevo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter de la direccion de la persona
	 * @return direccion de la persona
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Setter de la direccion de la persona
	 * @param direccion nueva direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Getter de la fecha de nacimiento de la persona
	 * @return fecha de nacimiento 
	 */
	public Date getFechaNac() {
		return fechaNac;
	}

	/**
	 * Setter de la fecha de nacimiento de la persona
	 * @param fechaNac nueva fecha de nacimiento
	 */
	public void setFechaNac(String fechaNac) {
		try {
			this.fechaNac = sdf.parse(fechaNac);
		} catch (ParseException e) {
			this.fechaNac = new Date(System.currentTimeMillis());
		}
	}

	/**
	 * Getter del nombre de usuario de la persona
	 * @return nombre de usuario de la persona
	 */
	public String getNomUsuario() {
		return nomUsuario;
	}

	/**
	 * Setter del nombre de usuario de la persona
	 * @param nomUsuario nuevo nombre de usuario
	 */
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	/**
	 * Getter de la contrasenia de la persona
	 * @return contraseña de la persona
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Setter de la contraseña de la persona
	 * @param contrasenia nueva contraseña
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Getter del tipo de persona
	 * @return tipo de persona
	 */
	public TipoPersona getTipo() {
		return tipo;
	}

	/**
	 * Setter del tipo de persona
	 * @param tipo de persona
	 */
	public void setTipo(TipoPersona tipo) {
		this.tipo = tipo;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", email=" + email + ",  contrasenia=" + contrasenia
				+ ", tipo=" + tipo + "]";
	}
//N
	/**
	 * Metodo que compara si dos personas tienen el mismo dni
	 */
	@Override
	public int compareTo(Persona o) {
		// TODO Auto-generated method stub
		return this.dni.compareTo(o.dni);
	}

}
