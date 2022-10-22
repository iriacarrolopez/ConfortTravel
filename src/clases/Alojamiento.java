package clases;



public class Alojamiento {
	private String nombre_comp;
	private TipoAlojamiento talojamiento;
	private int duracion;
	private String destino;
	private float precio;
	
	public Alojamiento() {
	}

	public Alojamiento(String nombre_comp, TipoAlojamiento talojamiento, float precio, int duracion, String destino) {
		super();
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
	
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	@Override
	public String toString() {
		return "Alojamiento [nombre_comp=" + nombre_comp + ", talojamiento=" + talojamiento + ", duracion=" + duracion
				+ ", destino=" + destino + ", precio=" + precio + "]";
	}
	
}