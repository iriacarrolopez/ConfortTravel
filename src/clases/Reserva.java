package clases;

public class Reserva {
	
	private Integer id;
	private Origen origen;
	private Destino destino;
	private String fechaIni;
	private String fechaFin;
	private String alquilerTransporte;
	private TipoAlojamiento tipoAlojamiento;
	private TipoExcursion excursion;
	private String actividades;
	
	public Reserva() {
		
	}

	public Reserva(int id, Origen origen, Destino destino, String fechaIni, String fechaFin, String alquilerTransporte,
			TipoAlojamiento tipoAlojamiento, TipoExcursion excursion, String actividades) {
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


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Origen getOrigen() {
		return origen;
	}


	public void setOrigen(Origen origen) {
		this.origen = origen;
	}


	public Destino getDestino() {
		return destino;
	}


	public void setDestino(Destino destino) {
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


	public String getAlquilerTransporte() {
		return alquilerTransporte;
	}


	public void setAlquilerTransporte(String alquilerTransporte) {
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


	public String getActividades() {
		return actividades;
	}


	public void setActividades(String actividades) {
		this.actividades = actividades;
	}


	@Override
	public String toString() {
		return "Reserva [origen=" + origen + ", destino=" + destino + ", fechaIni=" + fechaIni + ", fechaFin="
				+ fechaFin + ", alquilerTransporte=" + alquilerTransporte + ", tipoAlojamiento=" + tipoAlojamiento
				+ ", excursion=" + excursion + ", actividades=" + actividades + "]";
	}
	
	

}
