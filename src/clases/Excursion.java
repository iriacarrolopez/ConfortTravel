package clases;
/*
 * nuevo
 */

public class Excursion {
	private Integer id;
	private String nombre;
	private  TipoExcursion tipo;
	private Destino lugar;
	private Float precio;
	private Integer numPersonas;
	private Integer duracion;
	
	public Excursion(Integer id,String nombre, TipoExcursion tipo, Destino lugar, Float precio,Integer duracion, Integer numPersonas) {
		super();
		this.id =id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.lugar = lugar;
		this.precio = precio;
		this.duracion=duracion;
		this.numPersonas = numPersonas;
	}
	public Excursion() {
	
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getLugarNombre() {
		return lugar.getNombre();
	}

	public void setLugar(Destino lugar) {
		this.lugar = lugar;
	}
	
	public Integer getNumPersonas() {
		return numPersonas;
	}
	public void setNumPersonas(Integer numPersonas) {
		this.numPersonas = numPersonas;
	}
	
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Integer getDuracion() {
		return duracion;
	}
	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}
	@Override
	public String toString() {
		return "Excursion [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", lugar=" + lugar + ", precio="
				+ precio + ", numPersonas=" + numPersonas + ", duracion=" + duracion + "]";
	}
	

}
