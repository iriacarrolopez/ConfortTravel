package clases;

public class Destino extends Alojamiento {
	private Integer id;
	private String nombre;

	public Destino(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public Destino() {
		super();
		
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return "Destino id=" + id + ", nombre=" + nombre + " ";
	}

}
