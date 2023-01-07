package clases;
/*
 * nuevo
 */

public class Excursion {
	private Integer id;
	private String nombre;
	private  TipoExcursion tipo;
	private Ciudad lugar;
	private Float precio;
	private Integer numPersonas;
	private Integer duracion;
	
	/**
	 * Constructor con parametros 
	 * @param id
	 * @param nombre
	 * @param tipo
	 * @param lugar
	 * @param precio
	 * @param duracion
	 * @param numPersonas
	 */
	public Excursion(Integer id,String nombre, TipoExcursion tipo, Ciudad lugar, Float precio,Integer duracion, Integer numPersonas) {
		this.id =id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.lugar = lugar;
		this.precio = precio;
		this.duracion=duracion;
		this.numPersonas = numPersonas;
	}
	
	/**
	 * Constructor sin parametros
	 */
	public Excursion() {
	
	}
	
	/**
	 * Getter del id de la excursion
	 * @return id de la excursion
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Setter del id de la excursion
	 * @param id id nuevo
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Getter del nombre de la excursion
	 * @return nombre de la excursion
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Setter del nombre de la excursion
	 * @param nombre nombre nuevo
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Getter del tipo de excursion
	 * @return tipo de excursion
	 */
	public TipoExcursion getTipo() {
		return tipo;
	}
	
	/**
	 * Setter del tipo de excursion
	 * @param tipo tipo de excursion nuevo
	 */
	public void setTipo(TipoExcursion tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Getter del lugar de la excursion
	 * @return lugar de la excursion
	 */
	public Ciudad getLugar() {
		return lugar;
	}
	
	/**
	 * Getter del nombre del lugar de la excursion
	 * @return nombre del lugar
	 */
	public String getLugarNombre() {
		return lugar.getNombre();
	}

	/**
	 * Setter del lugar de la excursion
	 * @param lugar lugar nuevo
	 */
	public void setLugar(Ciudad lugar) {
		this.lugar = lugar;
	}
	
	/**
	 * Getter del numero de personas que realizan la excursion
	 * @return numero de personas
	 */
	public Integer getNumPersonas() {
		return numPersonas;
	}
	
	/**
	 * Setter del numero de personas que realizan la excursion
	 * @param numPersonas nuevo numero de personas
	 */
	public void setNumPersonas(Integer numPersonas) {
		this.numPersonas = numPersonas;
	}
	
	/**
	 * Getter del precio de la excursion
	 * @return precio
	 */
	public Float getPrecio() {
		return precio;
	}
	
	/**
	 * Setter del precio de la excursion
	 * @param precio nuevo precio de la excursion
	 */
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	/**
	 * Getter de la duracion de la excursion
	 * @return duracion de la excursion
	 */
	public Integer getDuracion() {
		return duracion;
	}
	
	/**
	 * Setter de la duracion de la excursion
	 * @param duracion nueva duracion de la excursion
	 */
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	
	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Excursion [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", lugar=" + lugar + ", precio="
				+ precio + ", numPersonas=" + numPersonas + ", duracion=" + duracion + "]";
	}

}
