package clases;

import java.util.ArrayList;

public class Cliente extends Persona {
	private ArrayList<Viaje> viajes;

	/**
	 * Constructor sin parametros con llamada a clase padre
	 */
	public Cliente() {
		super();
	}

	/**
	 * Constructor con parametros con llamada a clase padre
	 * @param viajes arraylist de los viajes que tinene el cliente
	 */
	public Cliente(ArrayList<Viaje> viajes) {
		super();
		this.viajes = viajes;
	}

	/**
	 * Constructor con parametros con llamada a clase padre
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
	public Cliente(String dni, String nombre, String apellido, int telefono, String email, String direccion,
			String fechaNac, String nomUsuario, String contrasenia,TipoPersona tipo) {
		super(dni, nombre, apellido, telefono, email, direccion, fechaNac, nomUsuario, contrasenia,tipo);
		this.viajes = new ArrayList<>();
	}

	/**
	 * Constructor con parametros con llamada a clase padre
	 * @param dni
	 * @param nombre
	 * @param contrasenia
	 * @param email
	 * @param tipo
	 */
	public Cliente(String dni, String nombre, String contrasenia, String email,TipoPersona tipo) {
		super(dni, nombre, contrasenia, email, tipo);
		this.viajes = new ArrayList<>();
	}

	/**
	 * Getter del arraylist de viajes del cliente
	 * @return arraylist de viajes del cliente
	 */
	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	/**
	 * Setter del arraylist de viajes del cliente
	 * @param viajes
	 */
	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Cliente [Dni=" + getDni() + ", Nombre=" + getNombre() + ", Apellido=" + getApellido() + ", Telefono="
				+ getTelefono() + ", Email=" + getEmail() + ", Direccion=" + getDireccion() + ", FechaNac="
				+ getFechaNac() + ", NomUsuario=" + getNomUsuario() + ", Contrasenia=" + getContrasenia() + ", viajes="
				+ viajes + "]";
	}
}
