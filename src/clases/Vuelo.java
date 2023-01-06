package clases;

public class Vuelo extends Transporte {
	private String ID_vuelo;
	private String compania;

	/**
	 * Constructor sin parametros
	 */
	public Vuelo() {
		ID_vuelo = " ";
		this.compania = " ";
	}

	/**
	 * Constructor con parametros
	 * @param iD_vuelo
	 * @param compania
	 * @param precio
	 * @param duracion
	 * @param plazas
	 */
	public Vuelo(String iD_vuelo, String compania, float precio, float duracion, int plazas) {
		super(precio, duracion, plazas);
		ID_vuelo = iD_vuelo;
		this.compania = compania;
	}

	/**
	 * Getter del id del vuelo 
	 * @return id del vuelo
	 */
	public String getID_vuelos() {
		return ID_vuelo;
	}

	/**
	 * Setter del id del vuelo
	 * @param iD_vuelos nuevo id
	 */
	public void setID_vuelos(String iD_vuelos) {
		ID_vuelo = iD_vuelos;
	}

	/**
	 * Getter de la compañia del vuelo
	 * @return compañia del vuelo
	 */
	public String getCompania() {
		return compania;
	}

	/**
	 * Setter de la compañia del vuelo
	 * @param compania
	 */
	public void setCompania(String compania) {
		this.compania = compania;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Vuelo [Precio=" + getPrecio() + ", Duracion=" + getDuracion() + ", Plazas=" + getPlazas()
				+ ", ID_vuelo=" + ID_vuelo + ", Compañia=" + getCompania() + "]";
	}
}
