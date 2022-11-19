import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Alojamiento;
import clases.Destino;
import clases.TipoAlojamiento;

public class AlojamientoTest {

	private Alojamiento alojamiento;
	private String nombre_comp = "RIU";
	private TipoAlojamiento talojamiento = TipoAlojamiento.HOTEL;
	private int duracion = 3;
	private Destino destino;
	private float precio = 85.9f;

	@Before
	public void setUp() throws Exception {
		destino = new Destino(1, "Sevilla");
		alojamiento = new Alojamiento(nombre_comp, talojamiento, precio, duracion, destino);
	}

	@After
	public void tearDown() throws Exception {
		alojamiento = null;
	}

	@Test
	public void testAlojamiento() {
		assertNotNull(alojamiento);
		assertEquals(nombre_comp, alojamiento.getNombre_comp());
		assertEquals(talojamiento, alojamiento.getTalojamiento());
		assertEquals(precio, alojamiento.getPrecio(), 0.0f);
		assertEquals(duracion, alojamiento.getDuracion());
		assertEquals(destino, alojamiento.getDestinoNombre());
	}

	@Test
	public void testGetNombre_comp() {
		assertEquals(nombre_comp, alojamiento.getNombre_comp());
	}

	@Test
	public void testSetNombre_comp() {
		String nuevaComp = "Melia";
		alojamiento.setNombre_comp(nuevaComp);
		assertEquals(nuevaComp, alojamiento.getNombre_comp());
	}

	@Test
	public void testGetTalojamiento() {
		assertEquals(talojamiento, alojamiento.getTalojamiento());
	}

	@Test
	public void testSetTalojamiento() {
		TipoAlojamiento nuevoTipo = TipoAlojamiento.APARTAMENTO;
		alojamiento.setTalojamiento(nuevoTipo);
		assertEquals(nuevoTipo, alojamiento.getTalojamiento());
	}

	@Test
	public void testGetPrecio() {
		assertEquals(precio, alojamiento.getPrecio(), 0.0f);
	}

	@Test
	public void testSetPrecio() {
		precio = 90.5f;
		alojamiento.setPrecio(precio);
		assertEquals(precio, alojamiento.getPrecio(), 0.0f);
		;
	}

	@Test
	public void testGetDuracion() {
		assertEquals(duracion, alojamiento.getDuracion());
	}

	@Test
	public void testSetDuracion() {
		int nuevaDuracion = 5;
		alojamiento.setDuracion(nuevaDuracion);
		assertEquals(nuevaDuracion, alojamiento.getDuracion());
	}

	@Test
	public void testGetDestino() {
		assertEquals(destino, alojamiento.getDestinoNombre());
	}

	@Test
	public void testSetDestino() {
		Destino nuevoDestino = new Destino(2, "Barcelona");

		alojamiento.setDestino(nuevoDestino);
		assertEquals(nuevoDestino, alojamiento.getDestinoNombre());
	}

	@Test
	public void testToString() {
		String toString = "Alojamiento [nombre_comp=" + nombre_comp + ", talojamiento=" + talojamiento + ", duracion="
				+ duracion + ", destino=" + destino + ", precio=" + precio + "]";
		assertEquals(toString, alojamiento.toString());
	}

}
