import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Transporte;

public class TransporteTest {
	private Transporte transporte;
	private float precio=2.1f;
	private float nuevoP= 1.2f;
	private float duracion =20.0f;
	private float nuevoD=12.0f;
	private Integer plazas =20;
	private Integer nuevoPl =30;
	

	@Before
	public void setUp() throws Exception {
		transporte = new Transporte(precio, duracion, plazas);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TranspTest() {
		assertNotNull(transporte);
		assertEquals(precio, transporte.getPrecio(),0.0f);
		assertEquals(duracion, transporte.getDuracion(),0.0f);
		assertEquals(plazas, transporte.getPlazas(),0);
		
	}
	@Test
	public void testgetPrecio() {
		assertEquals(precio, transporte.getPrecio(),0.0f);
		
	}
	@Test
	public void testsetPrecio() {
		assertEquals(precio, transporte.getPrecio(),0.0f);
		transporte.setPrecio(nuevoP);
		assertEquals(nuevoP, transporte.getPrecio(),0.0f);
		
	}
	@Test
	public void testgetDuracion() {
		assertEquals(duracion, transporte.getDuracion(),0.0f);
		
		
	}
	@Test
	public void testsetDuracion() {
		
		assertEquals(duracion, transporte.getDuracion(),0.0f);
		transporte.setDuracion(nuevoD);
		assertEquals(nuevoD, transporte.getDuracion(),0.0f);
	}
	@Test
	public void testgetPlazas() {
		assertEquals(plazas, transporte.getPlazas(),0);
		
	}
	@Test
	public void testsetPlazas() {
		assertEquals(plazas, transporte.getPlazas(),0);
		transporte.setPlazas(nuevoPl);
		assertEquals(nuevoPl, transporte.getPlazas(),0);
		
	}
	@Test
	public void testtoString() {
		String toString = "Transporte [precio=" + precio + ", duracion=" + duracion + ", plazas=" + plazas + "]";
		assertEquals(toString, transporte.toString());
		
	}
	

}
