package clases;

public class Transporte {

	private float precio;
	private float duracion;
	private int plazas;

	/**
	 * Constructor sin parametros
	 */
	public Transporte() {
	}

	/**
	 * Constructor con parametros
	 * @param precio
	 * @param duracion
	 * @param plazas
	 */
	public Transporte(float precio, float duracion, int plazas) {
		this.precio = precio;
		this.duracion = duracion;
		this.plazas = plazas;
	}

	/**
	 * Getter del precio del transporte
	 * @return precio del transporte
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Setter del precio del transporte
	 * @param precio nuevo precio
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Getter de la duracion del transporte
	 * @return duracion del transporte
	 */
	public float getDuracion() {
		return duracion;
	}

	/**
	 * Setter de la duracion del transporte
	 * @param duracion nueva duracion
	 */
	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	/**
	 * Getter de las plazas del transporte
	 * @return plazas del transporte
	 */
	public int getPlazas() {
		return plazas;
	}

	/**
	 * Setter de las plazas del transporte
	 * @param plazas nuevas plazas
	 */
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Transporte [precio=" + precio + ", duracion=" + duracion + ", plazas=" + plazas + "]";
	}
}
