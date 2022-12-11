import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.Persona;
import clases.TipoPersona;

public class PersonaTest {

	private Persona persona;
	private String dni = "11111111A";
	private String nombre = "Nombre";
	private String apellido = "Apellido";
	private int telefono = 111111111;
	private String email = "nombre@gmail.com";
	private String direccion = "direccion";
	private String fechaNacimiento = "01-01-2000";
	private String nomUsuario = "nombreUsuario";
	private String contrasenia = "contrasenia";
	// private TipoPersona tipo = TipoPersona.CLIENTE;
	 private String tipo = "CLIENTE";

	@Before
	public void setUp() throws Exception {
		persona = new Persona(dni, nombre, apellido, telefono, email, direccion, fechaNacimiento, nomUsuario,
				contrasenia,TipoPersona.valueOf(tipo));
	}

	@After
	public void tearDown() throws Exception {
		persona = null;
	}

	@Test
	public void testPersona() {
		assertNotNull(persona);
		assertEquals(dni, persona.getDni());
		assertEquals(nombre, persona.getNombre());
		assertEquals(apellido, persona.getApellido());
		assertEquals(telefono, persona.getTelefono());
		assertEquals(email, persona.getEmail());
		assertEquals(direccion, persona.getDireccion());
		// assertEquals(fechaNacimiento, persona.getFechaNac());
		assertEquals(nomUsuario, persona.getNomUsuario());
		assertEquals(contrasenia, persona.getContrasenia());
		// assertEquals(tipo, persona.getTipo());
	}

	@Test
	public void testGetDni() {
		assertEquals(dni, persona.getDni());
	}

	@Test
	public void testSetDni() {
		String nuevoDni = "22222222B";
		persona.setDni(nuevoDni);
		assertEquals(nuevoDni, persona.getDni());
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, persona.getNombre());
	}

	@Test
	public void testSetNombre() {
		String nuevoNombre = "Nuevo nombre";
		persona.setNombre(nuevoNombre);
		assertEquals(nuevoNombre, persona.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals(apellido, persona.getApellido());
	}

	@Test
	public void testSetApellido() {
		String nuevoApellido = "Nuevo apellido";
		persona.setApellido(nuevoApellido);
		assertEquals(nuevoApellido, persona.getApellido());
	}

	@Test
	public void testGetTelefono() {
		assertEquals(telefono, persona.getTelefono());
	}

	@Test
	public void testSetTelefono() {
		int nuevoTelefono = 222222222;
		persona.setTelefono(nuevoTelefono);
		assertEquals(nuevoTelefono, persona.getTelefono());
	}

	@Test
	public void testGetEmail() {
		assertEquals(email, persona.getEmail());
	}

	@Test
	public void testSetEmail() {
		String nuevoEmail = "nuevoEmail@gmail.com";
		persona.setEmail(nuevoEmail);
		assertEquals(nuevoEmail, persona.getEmail());
	}

	@Test
	public void testGetDireccion() {
		assertEquals(direccion, persona.getDireccion());
	}

	@Test
	public void testSetDireccion() {
		String nuevaDireccion = "nueva direccion";
		persona.setDireccion(nuevaDireccion);
		assertEquals(nuevaDireccion, persona.getDireccion());
	}

	/*
	 * @Test public void testGetFechaNac() { assertEquals(fechaNacimiento,
	 * persona.getFechaNac()); }
	 * 
	 * @Test public void testSetFechaNac() { String nuevaFechaNac = "02-02-2000";
	 * persona.setFechaNac(nuevaFechaNac); assertEquals(nuevaFechaNac,
	 * persona.getFechaNac()); }
	 */

	@Test
	public void testGetNomUsuario() {
		assertEquals(nomUsuario, persona.getNomUsuario());
	}

	@Test
	public void testSetNomUsuario() {
		String nuevoNomUsuario = "Nuevo nomUsuario";
		persona.setNomUsuario(nuevoNomUsuario);
		assertEquals(nuevoNomUsuario, persona.getNomUsuario());
	}

	@Test
	public void testGetContrasenia() {
		assertEquals(contrasenia, persona.getContrasenia());
	}

	@Test
	public void testSetContrasenia() {
		String nuevaContrasenia = "Nueva contrasenia";
		persona.setContrasenia(nuevaContrasenia);
		assertEquals(nuevaContrasenia, persona.getContrasenia());
	}

	
	 @Test public void testGetTipo() {
		 assertEquals(tipo, persona.getTipo().toString()); 
		 }
	

	@Test
	public void testSetTipo() {
		String nuevoTipo = "ADMINISTRADOR";
		persona.setTipo(TipoPersona.valueOf(nuevoTipo));
		assertEquals(nuevoTipo, persona.getTipo().toString());
	}

}
