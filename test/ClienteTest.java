import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Cliente;
import clases.Viaje;

public class ClienteTest {
	
	private Cliente cliente;
	private ArrayList<Viaje> viajes = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente(viajes);
	}

	@After
	public void tearDown() throws Exception {
		cliente = null;
	}

	@Test
	public void testCliente() {
		assertNotNull(cliente);
		assertEquals(viajes, cliente.getViajes());
	}
	
	@Test
	public void testToString() {
		String toString = "Cliente [Dni=" + "11111111A" + ", Nombre=" + "nombre" + ", Apellido=" + "apellido"
		+ ", Telefono=" + 111111111 + ", Email=" + "email@gmail.com" + ", Direccion="
		+ "direccion" + ", FechaNac=" + "01-01-2000" + ", NomUsuario=" + "nomUsuario"
		+ ", Contrasenia=" + "contrasenia" + ", viajes=" + viajes+ "]";
		assertEquals(toString, cliente.toString());
	}

	@Test
	public void testClienteArrayListOfViaje() {
		assertEquals(0, viajes.size());
	}

	@Test
	public void testGetViajes() {
		assertEquals(viajes, cliente.getViajes());
	}

	@Test
	public void testSetViajes() {
		ArrayList<Viaje> nuevosViajes = new ArrayList<>();
		cliente.setViajes(nuevosViajes);
		assertEquals(0, nuevosViajes.size());
	}

}
