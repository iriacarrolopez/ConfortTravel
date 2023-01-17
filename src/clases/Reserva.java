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
	private float precio;
	private String dni;
	
	/**
	 * Constructor sin parametros
	 */
	public Reserva() {
		
	}

	/**
	 * Constructor con parametros
	 * @param id
	 * @param origen
	 * @param destino
	 * @param fechaIni
	 * @param fechaFin
	 * @param alquilerTransporte
	 * @param tipoAlojamiento
	 * @param excursion
	 * @param actividades
	 * @param dni
	 * @param precio
	 */
	public Reserva(Integer id, Ciudad origen, Ciudad destino, String fechaIni, String fechaFin,
			TipoAlquiler alquilerTransporte, TipoAlojamiento tipoAlojamiento, TipoExcursion excursion,
			TipoActividad actividades,String dni, float precio) {
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
		this.precio = precio;
	}

	/**
	 * Getter del dni de la persona que hace la reserva
	 * @return dni de la persona
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Setter del dni de la persona que hace la reserva
	 * @param dni nuevo dni 
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Getter del id de la reserva
	 * @return id de la reserva
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter del id de la reserva
	 * @param id nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter de la ciudad origen de la reserva
	 * @return ciudad origen
	 */
	public Ciudad getOrigen() {
		return origen;
	}

	/**
	 * Setter de la ciudad origen de la reserva
	 * @param origen nueva ciudad origen
	 */
	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}

	/**
	 * Getter de la ciudad destino de la reserva
	 * @return ciudad destino
	 */
	public Ciudad getDestino() {
		return destino;
	}

	/**
	 * Setter de la ciudad destino de la reserva
	 * @param destino nueva ciudad destino
	 */
	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}

	/**
	 * Getter de la fecha inicio de la reserva
	 * @return fecha inicio
	 */
	public String getFechaIni() {
		return fechaIni;
	}

	/**
	 * Setter de la fecha inicio de la reserva
	 * @param fechaIni nueva fecha de inicio
	 */
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * Getter de la fecha fin de la reserva
	 * @return fecha fin de la reserva
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Setter de la fecha fin de la reserva
	 * @param fechaFin nueva fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Getter del alquiler de transporte de la reserva
	 * @return tipo de alquiler de transporte
	 */
	public TipoAlquiler getAlquilerTransporte() {
		return alquilerTransporte;
	}

	/**
	 * Setter del alquiler de transporte de la reserva
	 * @param alquilerTransporte nuevo alquiler de transporte
	 */
	public void setAlquilerTransporte(TipoAlquiler alquilerTransporte) {
		this.alquilerTransporte = alquilerTransporte;
	}

	/**
	 * Getter del tipo de alojamiento de la reserva
	 * @return tipo de alojamiento de la reserva
	 */
	public TipoAlojamiento getTipoAlojamiento() {
		return tipoAlojamiento;
	}

	/**
	 * Setter del tipo de alojamiento de la reserva
	 * @param tipoAlojamiento nuevo tipo de alojamiento
	 */
	public void setTipoAlojamiento(TipoAlojamiento tipoAlojamiento) {
		this.tipoAlojamiento = tipoAlojamiento;
	}

	/**
	 * Getter de la excursion de la reserva
	 * @return excursion
	 */
	public TipoExcursion getExcursion() {
		return excursion;
	}

	/**
	 * Setter de la excursion de la reserva
	 * @param excursion nueva excursion
	 */
	public void setExcursion(TipoExcursion excursion) {
		this.excursion = excursion;
	}

	/**
	 * Getter de las actividades de la reserva
	 * @return tipo de actividad
	 */
	public TipoActividad getActividades() {
		return actividades;
	}

	/**
	 * Setter de las actividades de la reserva
	 * @param actividades nueva actividad
	 */
	public void setActividades(TipoActividad actividades) {
		this.actividades = actividades;
	}
	
	/**
	 * Getter del precio de la reserva
	 * @return precio de la reserva
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Setter del precio de la reserva
	 * @param precio nuevo precio 
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Metodo toString con el formato deseado para salir por pantalla
	 */
	@Override
	public String toString() {
		return "Reserva [id=" + id + ", origen=" + origen + ", destino=" + destino + ", fechaIni=" + fechaIni
				+ ", fechaFin=" + fechaFin + ", alquilerTransporte=" + alquilerTransporte + ", tipoAlojamiento="
				+ tipoAlojamiento + ", excursion=" + excursion + ", actividades=" + actividades + ", precio=" + precio
				+ ", dni=" + dni + "]";
	}
	
	/**
	 * Método que calcula el precio total de la reserva
	 * @return prectio total de la reserva
	 */
	public float getPrecioTotal() {
		float precioTotal = 1000;
		
		
		if(alquilerTransporte != TipoAlquiler.NINGUNO) {
			precioTotal = precioTotal + 300;
		}
		if(tipoAlojamiento != TipoAlojamiento.NINGUNO) {
			precioTotal = precioTotal + 500;
		}
		if(excursion != TipoExcursion.NINGUNA_EXCURSION) {
			precioTotal = precioTotal + 100;
		}
		if(actividades != TipoActividad.NINGUNA) {
			precioTotal = precioTotal + 250;
		}
		
		return precioTotal;
	}
	
}
