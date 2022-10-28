package clases;

import java.util.ArrayList;

public class Administrador extends Persona {
	protected ArrayList<String>funcionesDelAdmin;

	
	public Administrador(ArrayList<String> funcionesDelAdmin) {
		super();
		this.funcionesDelAdmin = funcionesDelAdmin;
	}

	public Administrador() {
		super();
	}

	public Administrador(String dni, String nombre, String apellido, int telefono, String email, String direccion,
			String fechaNac, String nomUsuario, String contrasenia) {
		super(dni, nombre, apellido, telefono, email, direccion, fechaNac, nomUsuario, contrasenia);
	}

	public Administrador(String dni, String nombre, String contrasenia, String email) {
		super(dni, nombre, contrasenia, email, "ADMIN");
	}

	public ArrayList<String> getFuncionesDelAdmin() {
		return funcionesDelAdmin;
	}

	public void setFuncionesDelAdmin(ArrayList<String> funcionesDelAdmin) {
		this.funcionesDelAdmin = funcionesDelAdmin;
	}

	@Override
	public String toString() {
		return "Administrador [Dni=" + getDni() + ", Nombre=" + getNombre() + ", Apellido="
				+ getApellido() + ", Telefono=" + getTelefono() + ", Email=" + getEmail()
				+ ", Direccion=" + getDireccion() + ", FechaNac=" + getFechaNac() + ", NomUsuario="
				+ getNomUsuario() + ", Contrasenia=" + getContrasenia() + ", funcionesDelAdmin=" + funcionesDelAdmin + "]";
	}
}
