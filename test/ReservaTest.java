

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.print.DocFlavor.READER;
import javax.sound.sampled.ReverbType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Destino;
import clases.Origen;
import clases.Reserva;
import clases.TipoAlojamiento;
import clases.TipoExcursion;

public class ReservaTest {
	private Reserva reserva;
	private Destino destino;
	private Origen origen;
	private int IdReserva =1;
	private String actividad= "Esnorquel";
	private String alquiler = "coche";

	/* FECHAS */
	private String fechaInic = "12/12/2022";
	private String nuevoFI;
	private String nuevoFF;
	private String fechaFin = "17/12/2022";
	
	
/*
 *  Reserva(int id, Origen origen, Destino destino, String fechaIni, String fechaFin, String alquilerTransporte,
			TipoAlojamiento tipoAlojamiento, TipoExcursion excursion, String actividades) 
 */
	@Before
	public void setUp() throws Exception {
		//pensar el destino
		destino = new Destino ( 8 , "Estambul");
		origen = new Origen (5, "Roma");
		reserva = new Reserva(IdReserva , origen,destino,fechaInic,fechaFin,"coche",TipoAlojamiento.APARTAMENTO,TipoExcursion.ACUATICA,"Esnorquel");
			
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReserva() {
		assertNotNull(reserva);
		assertEquals(IdReserva, reserva.getId(),0);
		assertEquals(origen, reserva.getOrigen());
		assertEquals(destino, reserva.getDestino());
		assertEquals(fechaInic, reserva.getFechaIni());
		assertEquals(fechaFin,reserva.getFechaFin());
		assertEquals(alquiler, reserva.getAlquilerTransporte());
		assertEquals(TipoAlojamiento.APARTAMENTO, reserva.getTipoAlojamiento());
		assertEquals(TipoExcursion.ACUATICA,reserva.getExcursion());
		assertEquals(actividad, reserva.getActividades());
		
		;
	}



	@Test
	public void testGetId() {
	//comprobar que el id esta en la reserva
	assertEquals(IdReserva, reserva.getId(),0);
	}

	@Test
	public void testSetId() {
		Integer idn = 2;
		reserva.setId(idn);
		assertEquals(idn, reserva.getId(),0);
	}

	@Test
	public void testGetOrigen() {

	assertEquals(origen, reserva.getOrigen());
	}

	@Test
	public void testSetOrigen() {
		origen = new Origen(9,"Chicago");
		reserva.setOrigen(origen);
		assertEquals(origen, reserva.getOrigen());
		
	}
/*
 * AL COGER UN DESTINO , QUIERO QUE SALGA EL NOMBRE
 */
	@Test
	public void testGetDestino() {
		assertEquals(destino.getNombre(), reserva.getDestino().getNombre());
	}

	@Test
	public void testSetDestino() {
		destino = new Destino(5,"Buenos_aires");
		reserva.setDestino(destino);
		assertEquals(destino, reserva.getDestino());
	}

	@Test
	public void testGetFechaIni() {
		assertEquals(fechaInic,reserva.getFechaIni() );
	}

	@Test
	public void testSetFechaIni() {
		nuevoFI= "30/12/2022";
		reserva.setFechaIni(nuevoFI);
		assertEquals(nuevoFI, reserva.getFechaIni());
	}

	@Test
	public void testGetFechaFin() {
		assertEquals(fechaFin, reserva.getFechaFin());
	}

	@Test
	public void testSetFechaFin() {
		nuevoFF ="05/01/2023";
		reserva.setFechaFin(nuevoFF);
		assertEquals(nuevoFF, reserva.getFechaFin());
	}

	@Test
	public void testGetAlquilerTransporte() {

		assertEquals(alquiler, reserva.getAlquilerTransporte());
	}

	@Test
	public void testSetAlquilerTransporte() {
		String alq ="bicicleta";
		reserva.setAlquilerTransporte(alq);
		assertEquals(alq, reserva.getAlquilerTransporte());
	}

	@Test
	public void testGetTipoAlojamiento() {
	assertEquals(TipoAlojamiento.APARTAMENTO, reserva.getTipoAlojamiento());
	}

	@Test
	public void testSetTipoAlojamiento() {
		
	TipoAlojamiento tp = TipoAlojamiento.BUNGALO;
	reserva.setTipoAlojamiento(tp);
	assertEquals(tp, reserva.getTipoAlojamiento());
	}

	@Test
	public void testGetExcursion() {
	assertEquals(TipoExcursion.ACUATICA, reserva.getExcursion());
	}

	@Test
	public void testSetExcursion() {
		TipoExcursion te = TipoExcursion.SENDERISMO;
		reserva.setExcursion(te);
		assertEquals(te, reserva.getExcursion());
	}

	@Test
	public void testGetActividades() {
	
		assertEquals(actividad, reserva.getActividades());
	}

	@Test
	public void testSetActividades() {
		String actN ="submarinismo";
		reserva.setActividades(actN);
		assertEquals(actN, reserva.getActividades());
	}

	@Test
	public void testToString() {
		String toString ="Reserva [origen=" + origen + ", destino=" + destino + ", fechaIni=" + fechaInic + ", fechaFin="
				+ fechaFin + ", alquilerTransporte=" + alquiler + ", tipoAlojamiento=" +TipoAlojamiento.APARTAMENTO
				+ ", excursion=" + TipoExcursion.ACUATICA+ ", actividades=" + actividad + "]";
		assertEquals(toString, reserva.toString());
	}

}
