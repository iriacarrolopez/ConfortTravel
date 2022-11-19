package clases;

public class Transporte {

	private float precio;
	private float duracion;
	private int plazas;

	public Transporte() {
	}

	public Transporte(float precio, float duracion, int plazas) {
		this.precio = precio;
		this.duracion = duracion;
		this.plazas = plazas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	@Override
	public String toString() {
		return "Transporte [precio=" + precio + ", duracion=" + duracion + ", plazas=" + plazas + "]";
	}
}
