package clases;

import java.util.Date;

public class Viaje {

	private Transporte transporte;
	private Alojamiento alojamiento;
	private Date fechaInic;
	private Date fechaFin;
	private String destino;
	private float precio;
	
	public Viaje() {
		
	}

	public Viaje(Transporte transporte, Alojamiento alojamiento, Date fechaInic, Date fechaFin) {
		super();
		this.transporte = transporte;
		this.alojamiento = alojamiento;
		this.fechaInic = fechaInic;
		this.fechaFin = fechaFin;
		this.destino = alojamiento.getDestino();
		this.precio = (alojamiento.getPrecio()+(transporte.getPrecio()));
	}

	public Transporte getTransporte() {
		return transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	public Date getFechaInic() {
		return fechaInic;
	}

	public void setFechaInic(Date fechaInic) {
		this.fechaInic = fechaInic;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Viaje [transporte=" + transporte + ", alojamiento=" + alojamiento + ", fechaInic=" + fechaInic
				+ ", fechaFin=" + fechaFin + ", destino=" + destino + ", precio=" + precio + "]";
	}
	
	
}
