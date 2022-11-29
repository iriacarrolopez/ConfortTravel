package clases;

public class Origen {
	
	private int id;
	private String nombre;
	
	public Origen() {
		
	}
	
	public Origen(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Origen [id=" + id + ", nombre=" + nombre + "]";
	}
	
	

}
