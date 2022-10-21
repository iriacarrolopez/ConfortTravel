package clases;



public class Alojamiento {
	private String nombre_comp;
	private TipoAlojamiento talojamiento;
	
	public Alojamiento(String nombre_comp, TipoAlojamiento talojamiento) {
		super();
		this.nombre_comp = nombre_comp;
		this.talojamiento = TipoAlojamiento.HOTEL;
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

	@Override
	public String toString() {
		return "Alojamiento = nombre_comp=" + nombre_comp + "]";
	}
	
	
	
	
}