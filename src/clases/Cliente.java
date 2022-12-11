package clases;

import java.util.ArrayList;

public class Cliente extends Persona {
	private ArrayList<Viaje> viajes;

	public Cliente() {
		super();
	}

	public Cliente(ArrayList<Viaje> viajes) {
		super();
		this.viajes = viajes;
	}

	public Cliente(String dni, String nombre, String apellido, int telefono, String email, String direccion,
			String fechaNac, String nomUsuario, String contrasenia,TipoPersona tipo) {
		super(dni, nombre, apellido, telefono, email, direccion, fechaNac, nomUsuario, contrasenia,tipo);
		this.viajes = new ArrayList<>();
	}

	public Cliente(String dni, String nombre, String contrasenia, String email,TipoPersona tipo) {
		super(dni, nombre, contrasenia, email, tipo);
		this.viajes = new ArrayList<>();
	}

	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(ArrayList<Viaje> viajes) {
		this.viajes = viajes;
	}

	@Override
	public String toString() {
		return "Cliente [Dni=" + getDni() + ", Nombre=" + getNombre() + ", Apellido=" + getApellido() + ", Telefono="
				+ getTelefono() + ", Email=" + getEmail() + ", Direccion=" + getDireccion() + ", FechaNac="
				+ getFechaNac() + ", NomUsuario=" + getNomUsuario() + ", Contrasenia=" + getContrasenia() + ", viajes="
				+ viajes + "]";
	}
}
