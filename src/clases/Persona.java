package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Persona {
	private String dni;
	private String nombre;
	private String apellido;
	private int telefono; //Cambiar a String
	private String email;
	private String direccion;
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
	private Date fechaNac; //Cambiar a date
	private String nomUsuario;
	private String contrasenia;
	public Persona() {
		
	}
	
	public Persona(String dni, String nombre, String contrasenia, String email) {
		this.dni = dni;
		//Comprobar si el dni es correcto
		String erdni = "[0-9]{8}[A-Z]";
		boolean correctoDni = Pattern.matches(erdni, "12345678H");
		if(correctoDni)
			System.out.println("El DNI es correcto");
		else
			System.out.println("El DNI no es correcto");
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.email = email;
	}
	
	public Persona(String dni, String nombre, String apellido, int telefono, String email, String direccion,
			String fechaNac, String nomUsuario, String contrasenia) {
		this.dni = dni;
		//Comprobar si el dni es correcto
		String erdni = "[0-9]{8}[A-Z]";
		boolean correctoDni = Pattern.matches(erdni, "12345678H");
		if(correctoDni)
			System.out.println("El DNI es correcto");
		else
			System.out.println("El DNI no es correcto");
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		try {
			this.fechaNac =sdf.parse(fechaNac);
		} catch (ParseException e) {
			this.fechaNac = new Date(System.currentTimeMillis());
		}
		this.nomUsuario = nomUsuario;
		this.contrasenia = contrasenia;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		try {
			this.fechaNac =sdf.parse(fechaNac);
		} catch (ParseException e) {
			this.fechaNac = new Date(System.currentTimeMillis());
		}
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	//No se si queremos incluir la contraseña en el toString
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", email=" + email + ", direccion=" + direccion + ", fechaNac=" + fechaNac + ", nomUsuario="
				+ nomUsuario + ", contrasenia=" + contrasenia + "]";
	}
	
}

