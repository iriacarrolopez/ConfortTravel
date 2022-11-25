package clases;

public class Alojamiento {
	
	private Integer id;
	private String nombre_comp;
	private TipoAlojamiento talojamiento;
	private int duracion;
	private Destino destino;
	private float precio;

	public Alojamiento() {
	}

	/*
	 * cambiar el destino por una clase destino
	 */
	public Alojamiento( int id, String nombre_comp, TipoAlojamiento talojamiento, float precio, int duracion, Destino destino) {
		super();
		this.id = id;
		this.nombre_comp = nombre_comp;
		this.talojamiento = talojamiento;
		this.precio = precio;
		this.duracion = duracion;
		this.destino = destino;
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
	return destino.getId();
}

	public String getDestinoNombre() {
		return destino.getNombre();
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Destino getDestino() {
		return destino;
	}

	@Override
	public String toString() {
		return "Alojamiento [id=" + id + ", nombre_comp=" + nombre_comp + ", talojamiento=" + talojamiento
				+ ", duracion=" + duracion + ", destino=" + destino + ", precio=" + precio + "]";
	}

	

}