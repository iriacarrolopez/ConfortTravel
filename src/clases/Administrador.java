package clases;

import java.util.ArrayList;

public class Administrador extends Persona {
	protected ArrayList<String> funcionesDelAdmin;

	/**
	 * Constructor con parametros con llamada a la clase padre
	 * @param funcionesDelAdmin arraylist con las funciones que puede realizar el administrador
	 */
	public Administrador(ArrayList<String> funcionesDelAdmin) {
		super();
		this.funcionesDelAdmin = funcionesDelAdmin;
	}

	/**
	 * Constructor sin parametros con llamada a la clase padre
	 */
	public Administrador() {
		super();
	}

	/**
	 * Constructor con parametros con llamada a la clase padre
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
	public Administrador(String dni, String nombre, String apellido, int telefono, String email, String direccion,
			String fechaNac, String nomUsuario, String contrasenia,TipoPersona tipo) {
		super(dni, nombre, apellido, telefono, email, direccion, fechaNac, nomUsuario, contrasenia,tipo);
	}

	/**
	 * Constructor con parametros con llamada a la clase padre
	 * @param dni
	 * @param nombre
	 * @param contrasenia
	 * @param email
	 * @param tipo
	 */
	public Administrador(String dni, String nombre, String contrasenia, String email, TipoPersona tipo) {
		super(dni, nombre, contrasenia, email, tipo);
	}

	/**
	 * Getter del arraylist de las funciones del admin
	 * @return arraylist de las funciones del admin
	 */
	public ArrayList<String> getFuncionesDelAdmin() {
		return funcionesDelAdmin;
	}

	/**
	 * Setter del arraylist de las funciones del admin
	 * @param funcionesDelAdmin
	 */
	public void setFuncionesDelAdmin(ArrayList<String> funcionesDelAdmin) {
		this.funcionesDelAdmin = funcionesDelAdmin;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Administrador [Dni=" + getDni() + ", Nombre=" + getNombre() + ", Apellido=" + getApellido()
				+ ", Telefono=" + getTelefono() + ", Email=" + getEmail() + ", Direccion=" + getDireccion()
				+ ", FechaNac=" + getFechaNac() + ", NomUsuario=" + getNomUsuario() + ", Contrasenia="
				+ getContrasenia() + ", funcionesDelAdmin=" + funcionesDelAdmin + "]";
	}
}
