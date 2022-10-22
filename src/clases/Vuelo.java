package clases;

public class Vuelo extends Transporte{
	private String ID_vuelo;
	private String nombre_comp;
	
	public Vuelo() {
		super();
		ID_vuelo = " ";
		this.nombre_comp = " ";
	}

	public Vuelo(float precio, float duracion, int plazas, String iD_vuelo, String nombre_comp) {
		super(precio, duracion, plazas);
		ID_vuelo = iD_vuelo;
		this.nombre_comp = nombre_comp;
	}

	public String getID_vuelos() {
		return ID_vuelo;
	}
	public void setID_vuelos(String iD_vuelos) {
		ID_vuelo = iD_vuelos;
	}
	public String getNombre_comp() {
		return nombre_comp;
	}
	public void setNombre_comp(String nombre_comp) {
		this.nombre_comp = nombre_comp;
	}
	@Override
	public String toString() {
		return "Vuelo [Precio=" + getPrecio() + ", Duracion=" + getDuracion() + ", Plazas=" + getPlazas()+ ", ID_vuelo=" + ID_vuelo
				+ ", nombre_comp=" + nombre_comp + "]";
	}
}
