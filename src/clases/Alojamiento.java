package clases;

public class Alojamiento {
	
	private Integer id;
	private String nombre_comp;
	private TipoAlojamiento talojamiento;
	private int duracion;
	private Ciudad ciudad;
	private float precio;

	/**
	 * Constructor sin parametros
	 */
	public Alojamiento() {
	}

	/**
	 * Constructor con parametros de la clase alojamiento
	 * @param id
	 * @param nombre_comp
	 * @param talojamiento
	 * @param precio
	 * @param duracion
	 * @param ciudad
	 */
	public Alojamiento( int id, String nombre_comp, TipoAlojamiento talojamiento, float precio, int duracion, Ciudad ciudad) {
		super();
		this.id = id;
		this.nombre_comp = nombre_comp;
		this.talojamiento = talojamiento;
		this.precio = precio;
		this.duracion = duracion;
		this.ciudad = ciudad;
	}

	/**
	 * Getter del nombre
	 * @return nombre
	 */
	public String getNombre_comp() {
		return nombre_comp;
	}

	/**
	 * Setter del nombre
	 * @param nombre_comp nombre a cambiar
	 */
	public void setNombre_comp(String nombre_comp) {
		this.nombre_comp = nombre_comp;
	}

	/**
	 * Getter del tipo de alojamiento
	 * @return tipo de alojamiento
	 */
	public TipoAlojamiento getTalojamiento() {
		return talojamiento;
	}

	/**
	 * Setter del tipo de alojamiento
	 * @param talojamiento tipo de alojamiento a cambiar
	 */
	public void setTalojamiento(TipoAlojamiento talojamiento) {
		this.talojamiento = talojamiento;
	}

	/**
	 * Getter del precio
	 * @return precio
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Setter del precio
	 * @param precio precio a modificar
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Getter de la duracion
	 * @return duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Setter de la duracion
	 * @param duracion duracion a cambiar
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	/**
	 * Getter del id del destino
	 * @return id del destino
	 */
	public Integer getDestinoID() {
		return ciudad.getId();
	}

	/**
	 * Getter del nombre del destino
	 * @return nombre del destino
	 */
	public String getDestinoNombre() {
		return ciudad.getNombre();
	}

	/**
	 * Setter de la ciudad
	 * @param ciudad ciudad que queremos modificar
	 */
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Getter del id del alojamiento
	 * @return id del alojamiento
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter del id del alojamiento
	 * @param id id del alojamiento cambiado
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter de la ciudad
	 * @return ciudad
	 */
	public Ciudad getCiudad() {
		return ciudad;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Alojamiento [id=" + id + ", nombre_comp=" + nombre_comp + ", talojamiento=" + talojamiento
				+ ", duracion=" + duracion + ", ciudad=" + ciudad + ", precio=" + precio + "]";
	}

	

}