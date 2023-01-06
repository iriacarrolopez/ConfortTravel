package clases;

import java.time.LocalDate;

public class Viaje {

	private Transporte transporte;
	private Alojamiento alojamiento;

	private LocalDate fechaInic;
	private LocalDate fechaFin;
	private String destino;

	private float precio;

	/**
	 * Constructor sin parametros
	 */
	public Viaje() {

	}

	/**
	 * Constructor con parametros
	 * @param transporte
	 * @param alojamiento
	 * @param fechaInic
	 * @param fechaFin
	 */
	public Viaje(Transporte transporte, Alojamiento alojamiento, LocalDate fechaInic, LocalDate fechaFin) {
		super();
		this.transporte = transporte;
		this.alojamiento = alojamiento;
		this.fechaInic = fechaInic;
		this.fechaFin = fechaFin;
		this.destino = alojamiento.getDestinoNombre();
		this.precio = (alojamiento.getPrecio() + (transporte.getPrecio()));
	}

	/**
	 * Getter del transporte del viaje
	 * @return transporte del viaje
	 */
	public Transporte getTransporte() {
		return transporte;
	}

	/**
	 * Setter del transporte del viaje
	 * @param transporte nuevo transporte
	 */
	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	/**
	 * Getter del alojamiento del viaje
	 * @return alojamiento del viaje
	 */
	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	/**
	 * Setter del alojamiento del viaje
	 * @param alojamiento nuevo alojamiento
	 */
	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	/**
	 * Getter de la fecha de inicio del viaje
	 * @return fecha de inicio del viaje
	 */
	public LocalDate getFechaInic() {
		return fechaInic;
	}

	/**
	 * Setter de la fecha de inicio del viaje
	 * @param fechaInic nueva fecha de inicio
	 */
	public void setFechaInic(LocalDate fechaInic) {
		this.fechaInic = fechaInic;
	}

	/**
	 * Getter de la fecha fin del viaje
	 * @return feha fin del viaje
	 */
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	/**
	 * Setter de la fecha fin del viaje
	 * @param fechaFin nueva fecha fin
	 */
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Getter de la ciudad destino del viaje
	 * @return ciudad destino del viaje
	 */
	public String getDestino() {
		return destino;
	}

	/**
	 * Setter de la ciudad destino del viaje
	 * @param destino nueva ciudad destino
	 */
	public void setDestino(String destino) {
		this.destino = destino;
	}

	/**
	 * Getter del precio del viaje
	 * @return precio del viaje
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Setter del precio del viaje
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
		return "Viaje [transporte=" + transporte + ", alojamiento=" + alojamiento + ", fechaInic=" + fechaInic
				+ ", fechaFin=" + fechaFin + ", destino=" + destino + ", precio=" + precio + "]";
	}

}
