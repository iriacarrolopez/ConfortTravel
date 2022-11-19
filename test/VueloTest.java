import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Vuelo;

public class VueloTest {

	private Vuelo vuelo;
	private String ID_vuelo = "v001";
	private String compania = "IBERIA";

	@Before
	public void setUp() throws Exception {
		vuelo = new Vuelo(ID_vuelo, compania, 60.00f, 80.0f, 135);
	}

	@After
	public void tearDown() throws Exception {
		vuelo = null;
	}

	@Test
	public void testVuelo() {
		assertNotNull(vuelo);
		assertEquals(ID_vuelo, vuelo.getID_vuelos());
		assertEquals(compania, vuelo.getCompania());
	}

	@Test
	public void testToString() {
		String toString = "Vuelo [Precio=" + 60.00f + ", Duracion=" + 80.00f + ", Plazas=" + 135 + ", ID_vuelo="
				+ ID_vuelo + ", Compañia=" + compania + "]";
		;
		assertEquals(toString, vuelo.toString());
	}

	@Test
	public void testGetID_vuelos() {
		assertEquals(ID_vuelo, vuelo.getID_vuelos());
	}

	@Test
	public void testSetID_Vuelos() {
		String nuevoID_Vuelo = "v002";
		vuelo.setID_vuelos(nuevoID_Vuelo);
		assertEquals(nuevoID_Vuelo, vuelo.getID_vuelos());
	}

	@Test
	public void testGetCompania() {
		assertEquals(compania, vuelo.getCompania());
	}

	@Test
	public void testSetCompania() {
		String nuevaCompania = "AIR-EUROPA";
		vuelo.setCompania(nuevaCompania);
		assertEquals(nuevaCompania, vuelo.getCompania());
	}

}
