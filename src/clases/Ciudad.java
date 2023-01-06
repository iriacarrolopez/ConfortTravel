package clases;

public class Ciudad {
	
	private int id;
	private String nombre;
	
	/**
	 * Constructor sin parametros
	 */
	public Ciudad() {
		
	}
	
	/**
	 * Constructor con parametros
	 * @param id 
	 * @param nombre
	 */
	public Ciudad(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * Getter del id de la ciudad
	 * @return id de la ciudad
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter del id
	 * @param id id nuevo
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter del nombre de la ciudad
	 * @return nombre de la ciudad
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre de la ciudad 
	 * @param nombre nombre nuevo
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return nombre;
	}
	
	

}
