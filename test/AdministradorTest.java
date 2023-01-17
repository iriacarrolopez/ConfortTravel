import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Administrador;

public class AdministradorTest {

	private Administrador administrador;
	private ArrayList<String> funcionesAdministrador = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		administrador = new Administrador(funcionesAdministrador);
	}

	@After
	public void tearDown() throws Exception {
		administrador = null;
	}

	@Test
	public void testAdministrador() {
		assertNotNull(administrador);
		assertEquals(funcionesAdministrador, administrador.getFuncionesDelAdmin());
	}

	@Test
	public void testToString() {
		String toString = "Administrador [Dni=" + "11111111A" + ", Nombre=" + "Nombre" + ", Apellido=" + "Apellido"
				+ ", Telefono=" + 1111111111 + ", Email=" + "email@gmail.com" + ", Direccion=" + "direccion"
				+ ", FechaNac=" + "01-01-2000" + ", NomUsuario=" + "Nombre Usuario" + ", Contrasenia=" + "contrasenia"
				+ ", funcionesDelAdmin=" + funcionesAdministrador + "]";
		
	}

	@Test
	public void testAdministradorArrayListOfString() {
		assertEquals(0, funcionesAdministrador.size());
	}

	@Test
	public void testGetFuncionesDelAdmin() {
		assertEquals(funcionesAdministrador, administrador.getFuncionesDelAdmin());
	}

	@Test
	public void testSetFuncionesDelAdmin() {
		ArrayList<String> nuevasFunciones = new ArrayList<>();
		administrador.setFuncionesDelAdmin(nuevasFunciones);
		assertEquals(0, nuevasFunciones.size());
	}

}
