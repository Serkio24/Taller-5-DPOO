package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	
	private ProductoMenu productoMenu1;

	@BeforeEach
	void setup() throws Exception
	{
		productoMenu1 = new ProductoMenu("Hamburguesa", 25000);
	}
	
	@Test
	void testGetNombre()
	{
		assertEquals("Hamburguesa", productoMenu1.getNombre(), "El nombre no es el esperado");		
	}
	
	@Test 
	void testGetPrecio()
	{
		assertEquals(25000, productoMenu1.getPrecio(), "El precio no es el esperado");
	}
	
	@Test
	void testGenerarTextoFactura()
	{
		assertEquals("Producto: Hamburguesa\nPrecio: 25000\n", productoMenu1.generarTextoFactura(), "La factura no es la esperada");
	}
	
}
