package clases;

public class Vuelo {
	private String ID_vuelo;
	private String nombre_comp;
	private int plazas;
	
	
	
	public Vuelo(String iD_vuelos, String nombre_comp, int plazas) {
		super();
		ID_vuelo = iD_vuelos;
		this.nombre_comp = nombre_comp;
		this.plazas = plazas;
	}
	public Vuelo() {
		super();
		ID_vuelo = " ";
		this.nombre_comp = " ";
		this.plazas = 0;
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
	public int getPlazas() {
		return plazas;
	}
	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}
	
	@Override
	public String toString() {
		return "Vuelos ID_vuelos=" + ID_vuelo + ", nombre_comp=" + nombre_comp + ", plazas=" + plazas + " ";
	}
	

}
