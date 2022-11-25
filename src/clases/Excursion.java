package clases;
/*
 * nuevo
 */

public class Excursion {
	private String nombre;
	private  TipoExcursion tipo;
	private Destino lugar;
	private Float duracion;
	private Integer numPersonas;
	
	public Excursion(String nombre, TipoExcursion tipo, Destino lugar, Float duracion, Integer numPersonas) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.lugar = lugar;
		this.duracion = duracion;
		this.numPersonas = numPersonas;
	}
	public Excursion() {
	
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoExcursion getTipo() {
		return tipo;
	}
	public void setTipo(TipoExcursion tipo) {
		this.tipo = tipo;
	}
	public Destino getLugar() {
		return lugar;
	}
	public void setLugar(Destino lugar) {
		this.lugar = lugar;
	}
	public Float getDuracion() {
		return duracion;
	}
	public void setDuracion(Float duracion) {
		this.duracion = duracion;
	}
	public Integer getNumPersonas() {
		return numPersonas;
	}
	public void setNumPersonas(Integer numPersonas) {
		this.numPersonas = numPersonas;
	}
	@Override
	public String toString() {
		return "Excursion: Nombre=" + nombre + ", Tipo de excursion =" + tipo + ", Lugar=" + lugar + ", Duracion=" + duracion
				+ ", Numero de personas maximas=" + numPersonas ;
	}
	

}
