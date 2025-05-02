package uniandes.dpoo.hamburguesas.tests;


import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ArrayList<Producto> lista;
	private Pedido pedido;
	
	@BeforeEach
	void setup()
	{
		producto1 = new ProductoMenu("corral", 14000);
		producto2 = new ProductoMenu("papas medianas", 5500);
		pedido = new Pedido("Furina", "Corte de Fontaine con 68");
	}

	@Test
	void testGetIdPedido()
	{
		assertEquals(2, pedido.getIdPedido(), "El id no es el esperado");
	}
	
	@Test
	void testGetNombreCliente()
	{
		assertEquals("Furina", pedido.getNombreCliente(), "El nombre del cliente no es el esperado");
	}
	
	@Test
	void TestAgregarProducto()
	{
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		
		assertTrue(pedido.productos.contains(producto1), "La lista no contiene el elemento");
		assertTrue(pedido.productos.contains(producto2), "La lista no contiene el elemento");
		assertEquals(2, pedido.productos.size(), "El tamaño no es el esperado");
	}
	
	@Test
	void TestGetPrecioTotalPedido()
	{
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		assertEquals(23205, pedido.getPrecioTotalPedido(), "El precio total no es el esperado");
	}
	
	@Test
	void TestGenerarTextoFactura()
	{
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		assertEquals("Cliente: Furina\nDirección: Corte de Fontaine con 68\n----------------\nProducto: corral\nPrecio: 14000\nProducto: papas medianas\nPrecio: 5500\n----------------\nPrecio Neto: 19500\nIVA: 3705\nPrecio Total: 23205\n", pedido.generarTextoFactura());
	}
	
	@Test
	void TestGuardarFactura() throws Exception
	{
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		
		String comparacion;
		File archivo =  File.createTempFile("Factura", ".csv");
		pedido.guardarFactura(archivo);
		
		StringBuffer sb = new StringBuffer();
		try(BufferedReader br = new BufferedReader(new FileReader(archivo)))
		{
			String linea;
			while((linea = br.readLine()) != null)
			{
				sb.append( linea + "\n");
			}
		}
		comparacion = sb.toString();
		String factura = pedido.generarTextoFactura();
		
		assertEquals(factura, comparacion, "Lo guardado no es lo esperado");
	}

}
