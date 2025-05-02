package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;



public class ProductoAjustadoTest {
	
	private ProductoMenu productoMenu1;
	private ProductoAjustado productoAjustado1;
	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	
	@BeforeEach
	void setup() throws Exception
	{
		productoMenu1 = new ProductoMenu("Hamburguesa", 25000);
		productoAjustado1 = new ProductoAjustado(productoMenu1);
		ingrediente1 = new Ingrediente("Queso", 1500);
		ingrediente2 = new Ingrediente("Cebolla", 1000);
		productoAjustado1.agregados.add(ingrediente1);
		productoAjustado1.eliminados.add(ingrediente2);
	}
	
	@Test
	void testGetNombre()
	{
		assertEquals("Hamburguesa", productoAjustado1.getNombre(), "El nombre no es el esperado");
	}
	
	@Test
	void testGetPrecio()
	{
		assertEquals(25000, productoAjustado1.getPrecio(), "El precio no es el esperado");
	}
	
	@Test
	void testGenerarTextoFactura()
	{
		assertEquals("Producto: Hamburguesa\nAgregado: Queso Coste adicional: 1500\nEliminado: Cebolla\n26500\n", productoAjustado1.generarTextoFactura(), "El contenido de la factura no es el esperado");
	}
}
