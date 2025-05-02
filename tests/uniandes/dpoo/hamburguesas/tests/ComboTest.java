package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ComboTest {

	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ArrayList<ProductoMenu> lista;
	private Combo combo1;
	
	@BeforeEach
	void setup() throws Exception
	{
		producto1 = new ProductoMenu("Hamburguesa", 15000);
		producto2 = new ProductoMenu("PapasPequeñas", 5000);
		lista = new ArrayList<>();
		lista.add(producto1);
		lista.add(producto2);
		combo1 = new Combo("Combo Ado", 0.2, lista);
	}
		
	@AfterEach
	void tearDown() throws Exception
	{
		lista.clear();
	}
	
	@Test
	void testGetNombre()
	{
		assertEquals("Combo Ado", combo1.getNombre(), "El nombre no es el esperado");
	}
	
	@Test
	void testGetPrecio()
	{
		assertEquals(16000, combo1.getPrecio(), "El precio no es el esperado");
	}
	
	@Test
	void testGenerarTextoFactura()
	{
		assertEquals("Combo Combo Ado\nDescuento: 0.2\nPrecio: 16000\n", combo1.generarTextoFactura(), "El contenido de la factura no es el esperado");
	}
	
}

