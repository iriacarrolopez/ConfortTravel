import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Autobus;

public class AutobusTest {

	private Autobus autobus;
	private int num_Bus = 001;
	private String compania = "ALSA";
	
	@Before
	public void setUp() throws Exception {
		autobus = new Autobus(num_Bus, compania, 20.00f, 60.0f, 75);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToString() {
		String toString = "Autobus [Precio=" + 20.00f + ", Duracion=" + 60.0f + ", Plazas="
				+ 75 + ", num_Bus=" + num_Bus + ", compania=" + compania+ "]";
		assertEquals(toString, autobus.toString());
	}

	@Test
	public void testGetNum_Bus() {
		assertEquals(num_Bus, autobus.getNum_Bus());
	}

	@Test
	public void testSetNum_Bus() {
		int nuevoNum_bus = 002;
		autobus.setNum_Bus(nuevoNum_bus);
		assertEquals(nuevoNum_bus, autobus.getNum_Bus());
	}

	@Test
	public void testGetCompania() {
		assertEquals(compania, autobus.getCompania());
	}

	@Test
	public void testSetCompania() {
		String nuevaCompania = "MONBUS";
		autobus.setCompania(nuevaCompania);
		assertEquals(nuevaCompania, autobus.getCompania());
	}

}
