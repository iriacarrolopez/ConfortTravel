import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Alojamiento;
import clases.TipoAlojamiento;
import clases.Transporte;
import clases.Viaje;

public class ViajeTest {
	private Viaje viaje;
	private DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	/*FECHAS*/
	private LocalDate fechaInic = LocalDate.parse("20-09-2022", formatter);
	private LocalDate nuevoFI;
	private LocalDate nuevoFF;
	private LocalDate fechaFin = LocalDate.parse("25-09-2022", formatter);
	
	//private String destino="Madrid ";
	//private float precioViaje = 2.3f;
	
	private Alojamiento alojamiento;
	private String nombre_comp = "RIU";
	private TipoAlojamiento talojamiento = TipoAlojamiento.HOTEL;
	private int duracionA = 3;
	private String destinoA = "Sevilla";
	private float precioA = 85.9f;
	private Alojamiento nuevoAl;
	
	private Transporte transporte;
	private float preciot= 2.0f;
	private float duraciont=20.0f;
	private int plazas =30;
	

	@Before
	public void setUp() throws Exception {
		alojamiento = new Alojamiento(nombre_comp, talojamiento, precioA, duracionA, destinoA);
		transporte = new Transporte(preciot,duraciont, plazas);
		viaje= new Viaje(transporte, alojamiento, fechaInic, fechaFin);
		
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Test() {
		assertNotNull(viaje);
		assertEquals(transporte, viaje.getTransporte());
		assertEquals(alojamiento, viaje.getAlojamiento());
		assertEquals(fechaInic,viaje.getFechaInic());
		assertEquals(fechaFin,viaje.getFechaFin());
		
	}
	@Test
	public void testgetTransporte() {
		assertEquals(transporte,viaje.getTransporte());
		
	}
	@Test
	public void testsetTransporte() {
		Transporte nuevoTransp = new Transporte(2.0f, 20.1f,20);
		assertEquals(transporte,viaje.getTransporte());
		viaje.setTransporte(nuevoTransp);
		assertEquals(nuevoTransp,viaje.getTransporte());
	}
	@Test
	public void testgetAlojamiento(){
		assertEquals(alojamiento, viaje.getAlojamiento());
		
	}
	@Test
	public void testsetAjomiento() {
		
		nuevoAl = new Alojamiento("IBERIA", talojamiento, 340.8f, 30, "Madrid");
		assertEquals(alojamiento, viaje.getAlojamiento());
		viaje.setAlojamiento(nuevoAl);
		assertEquals(nuevoAl, viaje.getAlojamiento());
		
	}
	@Test
	public void testgetFechaInic(){
		assertEquals(fechaInic, viaje.getFechaInic());
		
	}
	@Test
	public void testsetFechaInic(){
		nuevoFI= LocalDate.parse("21-10-2022", formatter);
		viaje.setFechaInic(nuevoFI);
		assertEquals(nuevoFI, viaje.getFechaInic());
		
		
	}
	@Test
	public void testgetFechaFin(){
		
		assertEquals(fechaFin, viaje.getFechaFin());
		
	}
	@Test
	public void testsetFechaFin(){
		nuevoFF= LocalDate.parse("30-10-2022", formatter);
		viaje.setFechaFin(nuevoFF);
		assertEquals(nuevoFF, viaje.getFechaFin());
		
	}
	
	
	@Test
	public  void testToString() {
		String toString = "Viaje [transporte=" + transporte + ", alojamiento=" + alojamiento + ", fechaInic=" + fechaInic
				+ ", fechaFin=" + fechaFin + ", destino=" + "Sevilla" + ", precio=" + 87.9f + "]";
		assertEquals(toString, viaje.toString());
	}
}