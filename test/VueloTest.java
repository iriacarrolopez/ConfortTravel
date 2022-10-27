import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Vuelo;

public class VueloTest {

	private Vuelo vuelo;
	private String ID_Vuelo = "";
	private String nombre_comp = "";
	private String compania = "IBERIA";
	
	@Before
	public void setUp() throws Exception {
		vuelo = new Vuelo(ID_Vuelo, nombre_comp , compania, 60.00f, 80.0f, 135);
	}

	@After
	public void tearDown() throws Exception {
		vuelo = null;
	}
	
	@Test
	public void testVuelo() {
		assertNotNull(vuelo);
		assertEquals(ID_Vuelo, vuelo.getID_vuelos());
		assertEquals(nombre_comp, vuelo.getNombre_comp());
		assertEquals(compania, vuelo.getCompania());
	}

	@Test
	public void testToString() {
		String toString = "Vuelo [ID_Vuelo=" + vuelo.getID_vuelos() +"Nombre_comp=" + vuelo.getNombre_comp() +"Compañia=" + vuelo.getCompania() +" Precio=" + 60.00f + ", Duracion=" + 80.0f + ", Plazas="
				+ 135 + "]";
		assertEquals(toString, vuelo.toString());
	}

	@Test
	public void testGetID_Vuelo() {
		assertEquals(ID_Vuelo, vuelo.getID_vuelos());
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

