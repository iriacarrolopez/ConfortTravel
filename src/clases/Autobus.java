package clases;

public class Autobus extends Transporte {
	private int num_Bus;
	private String compania;

	/**
	 * Constructor sin parametros
	 */
	public Autobus() {
		super();
	}

	/**
	 * Constructor con parametros
	 * @param num_Bus
	 * @param compania
	 * @param precio
	 * @param duracion
	 * @param plazas
	 */
	public Autobus(int num_Bus, String compania, float precio, float duracion, int plazas) {
		super(precio, duracion, plazas);
		this.num_Bus = num_Bus;
		this.compania = compania;
	}

	/**
	 * Getter del numero de autobus
	 * @return numero de bus
	 */
	public int getNum_Bus() {
		return num_Bus;
	}

	/**
	 * Setter del numero de bus
	 * @param num_Bus numero de bus nuevo
	 */
	public void setNum_Bus(int num_Bus) {
		this.num_Bus = num_Bus;
	}

	/**
	 * Getter de la compañia del autobus
	 * @return compañia
	 */
	public String getCompania() {
		return compania;
	}

	/**
	 * Setter de la compañia del autobus
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
		return "Autobus [Precio=" + getPrecio() + ", Duracion=" + getDuracion() + ", Plazas=" + getPlazas()
				+ ", num_Bus=" + num_Bus + ", compania=" + compania + "]";
	}

}
