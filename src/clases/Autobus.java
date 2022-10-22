package clases;

public class Autobus extends Transporte{
	private int num_Bus;
	private String compania;
	public Autobus() {
		super();
	}
	public Autobus(float precio, float duracion, int plazas) {
		super(precio, duracion, plazas);
		// TODO Auto-generated constructor stub
	}
	public Autobus(int num_Bus, String compania) {
		super();
		this.num_Bus = num_Bus;
		this.compania = compania;
	}
	public int getNum_Bus() {
		return num_Bus;
	}
	public void setNum_Bus(int num_Bus) {
		this.num_Bus = num_Bus;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	@Override
	public String toString() {
		return "Autobus [Precio=" + getPrecio() + ", Duracion=" + getDuracion() + ", Plazas="
				+ getPlazas() + ", num_Bus=" + num_Bus + ", compania=" + compania+ "]";
	}
	
	
}
