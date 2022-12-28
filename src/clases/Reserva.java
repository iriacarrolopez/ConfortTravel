package clases;

public class Reserva {
	
	private Integer id;
	private Ciudad origen;
	private Ciudad destino;
	private String fechaIni;
	private String fechaFin;
	private TipoAlquiler alquilerTransporte;
	private TipoAlojamiento tipoAlojamiento;
	private TipoExcursion excursion;
	private TipoActividad actividades;
	private Persona dni;
	public Reserva() {
		
	}

	public Reserva(Integer id, Ciudad origen, Ciudad destino, String fechaIni, String fechaFin,
			TipoAlquiler alquilerTransporte, TipoAlojamiento tipoAlojamiento, TipoExcursion excursion,
			TipoActividad actividades) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.alquilerTransporte = alquilerTransporte;
		this.tipoAlojamiento = tipoAlojamiento;
		this.excursion = excursion;
		this.actividades = actividades;
	}

	public Reserva(Integer id, Ciudad origen, Ciudad destino, String fechaIni, String fechaFin,
			TipoAlquiler alquilerTransporte, TipoAlojamiento tipoAlojamiento, TipoExcursion excursion,
			TipoActividad actividades, Persona dni) {
		super();
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.alquilerTransporte = alquilerTransporte;
		this.tipoAlojamiento = tipoAlojamiento;
		this.excursion = excursion;
		this.actividades = actividades;
		this.dni = dni;
	}

	public Persona getDni() {
		return dni;
	}

	public void setDni(Persona dni) {
		this.dni = dni;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ciudad getOrigen() {
		return origen;
	}

	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}

	public Ciudad getDestino() {
		return destino;
	}

	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public TipoAlquiler getAlquilerTransporte() {
		return alquilerTransporte;
	}

	public void setAlquilerTransporte(TipoAlquiler alquilerTransporte) {
		this.alquilerTransporte = alquilerTransporte;
	}

	public TipoAlojamiento getTipoAlojamiento() {
		return tipoAlojamiento;
	}

	public void setTipoAlojamiento(TipoAlojamiento tipoAlojamiento) {
		this.tipoAlojamiento = tipoAlojamiento;
	}

	public TipoExcursion getExcursion() {
		return excursion;
	}

	public void setExcursion(TipoExcursion excursion) {
		this.excursion = excursion;
	}

	public TipoActividad getActividades() {
		return actividades;
	}

	public void setActividades(TipoActividad actividades) {
		this.actividades = actividades;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", origen=" + origen + ", destino=" + destino + ", fechaIni=" + fechaIni
				+ ", fechaFin=" + fechaFin + ", alquilerTransporte=" + alquilerTransporte + ", tipoAlojamiento="
				+ tipoAlojamiento + ", excursion=" + excursion + ", actividades=" + actividades + ", dni=" + dni + "]";
	}

	
	

	
}
