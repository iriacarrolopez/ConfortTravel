
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Origen;

public class OrigenTest {
	private Origen origen;
	private int idOrigen;
	private String nombreOrigen;
	
	
	@Before
	public void setUp() throws Exception {
		origen = new Origen (idOrigen, nombreOrigen);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOrigen() {
		assertNotNull(origen);
		assertEquals(idOrigen, origen.getId(),0);
		assertEquals(nombreOrigen, origen.getNombre());
	}

	@Test
	public void testGetId() {
		assertEquals(idOrigen, origen.getId(),0);
	}

	@Test
	public void testSetId() {
		Integer idn = 3;
		origen.setId(idn);
		assertEquals(idn, origen.getId(),0);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals(nombreOrigen, origen.getNombre());
	}

	@Test
	public void testSetNombre() {
		String nomOrig = "Beijing";
		origen.setNombre(nomOrig);
		assertEquals(nomOrig, origen.getNombre());
	}

	@Test
	public void testToString() {
		String toString = "Origen [id=" + idOrigen + ", nombre=" + nombreOrigen + "]"; 
		assertEquals(toString, origen.toString());
	}

}
