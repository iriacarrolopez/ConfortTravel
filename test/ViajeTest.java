import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Alojamiento;
import clases.TipoAlojamiento;
import clases.Transporte;
import clases.Viaje;

public class ViajeTest {
	private Viaje viaje;
	
	/*FECHAS*/
	private static SimpleDateFormat sdf = new SimpleDateFormat("");
	private Date fechaInic;
	private Date fechaFin;
	
	private String destino="Madrid ";
	private float precio=2.3f;
	
	private Alojamiento alojamiento;
	private String nombre_comp = "RIU";
	private TipoAlojamiento talojamiento = TipoAlojamiento.HOTEL;
	private int duracionA = 3;
	private String destinoA = "Sevilla";
	private float precioA = 85.9f;
	
	private Transporte transporte;
	private float preciot= 2.0f;
	private float duraciont=20.0f;

	@Before
	public void setUp() throws Exception {
		alojamiento = new Alojamiento(nombre_comp, talojamiento, precioA, duracionA, destinoA);
		transporte = new Transporte(preciot,duraciont, 30);
		//preguntar
		//viaje = new Viaje(null,null,sdf.format(fechaInic) ,sdf.format(fechaFin),destino,precio);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ViajeTest() {
		assertEquals(transporte, viaje.getTransporte());
		assertEquals(alojamiento, viaje.getAlojamiento());
		
		fail("Not yet implemented");
	}

}
