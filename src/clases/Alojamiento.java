package clases;

public class Alojamiento {
	
	private Integer id;
	private String nombre_comp;
	private TipoAlojamiento talojamiento;
	private int duracion;
	private Ciudad ciudad;
	private float precio;

	public Alojamiento() {
	}

	/*
	 * cambiar el destino por una clase destino
	 */
	public Alojamiento( int id, String nombre_comp, TipoAlojamiento talojamiento, float precio, int duracion, Ciudad ciudad) {
		super();
		this.id = id;
		this.nombre_comp = nombre_comp;
		this.talojamiento = talojamiento;
		this.precio = precio;
		this.duracion = duracion;
		this.ciudad = ciudad;
	}

	public String getNombre_comp() {
		return nombre_comp;
	}

	public void setNombre_comp(String nombre_comp) {
		this.nombre_comp = nombre_comp;
	}

	public TipoAlojamiento getTalojamiento() {
		return talojamiento;
	}

	public void setTalojamiento(TipoAlojamiento talojamiento) {
		this.talojamiento = TipoAlojamiento.APARTAMENTO;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
public Integer getDestinoID() {
	return ciudad.getId();
}

	public String getDestinoNombre() {
		return ciudad.getNombre();
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	@Override
	public String toString() {
		return "Alojamiento [id=" + id + ", nombre_comp=" + nombre_comp + ", talojamiento=" + talojamiento
				+ ", duracion=" + duracion + ", ciudad=" + ciudad + ", precio=" + precio + "]";
	}

	

}