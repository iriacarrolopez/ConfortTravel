package clases;

import java.util.ArrayList;

public class Administrador extends Persona {
	protected ArrayList<String>funcionesDelAdmin;

	public Administrador(String dni, String nombre, String apellido, int telefono, String email, String direccion,
			String fechaNac, ArrayList<String> funcionesDelAdmin) {
		super(dni, nombre, apellido, telefono, email, direccion, fechaNac);
		this.funcionesDelAdmin = funcionesDelAdmin;
	}

	public ArrayList<String> getFuncionesDelAdmin() {
		return funcionesDelAdmin;
	}

	public void setFuncionesDelAdmin(ArrayList<String> funcionesDelAdmin) {
		this.funcionesDelAdmin = funcionesDelAdmin;
	}

	@Override
	public String toString() {
		return "Administrador Dni del administrador=" + dni + ", nombre=" + nombre + ", funcionesDelAdmin=" + funcionesDelAdmin + " ";
	}
	
	
	

}
