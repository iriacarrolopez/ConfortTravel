
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Destino;

public class DestinoTest {
	private Destino destino;
	private int idDestino;
	private String nombreDestino;
	
	
	@Before
	public void setUp() throws Exception {
		destino = new Destino (9, "Nueva Delhi");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDestino() {
		assertNotNull(destino);
		assertEquals(idDestino, destino.getId(),0);
		assertEquals(nombreDestino, destino.getNombre());
	}

	@Test
	public void testGetId() {
		assertEquals(idDestino, destino.getId(),0);
	}

	@Test
	public void testSetId() {
		Integer idn = 4;
		destino.setId(idn);
		assertEquals(idn, destino.getId(),0);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals(nombreDestino, destino.getNombre());
	}

	@Test
	public void testSetNombre() {
		String nomDest = "Sidney";
		destino.setNombre(nomDest);
		assertEquals(nomDest, destino.getNombre());
	}

	@Test
	public void testToString() {
		String toString = "Destino [idDestino=" + idDestino + ", nombre=" + nombreDestino + "]"; 
		assertEquals(toString, destino.toString());
	}

}

