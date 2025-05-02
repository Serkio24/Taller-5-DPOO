package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class RestauranteTest {
	
	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	private ProductoMenu producto1;
	private ProductoMenu producto2;
	private ArrayList<ProductoMenu> productosCombo;
	private Combo combo1;
	private Pedido pedido1;
	private Restaurante restaurante;
	
	@BeforeEach
	void setup()
	{
		ingrediente1 = new Ingrediente("lechuga",1000);	
		ingrediente2 = new Ingrediente("cebolla grille",2500);	
		producto1 = new ProductoMenu("corral", 14000);
		producto2 = new ProductoMenu("gaseosa", 5000);
		productosCombo = new ArrayList<>();
		productosCombo.add(producto1);
		productosCombo.add(producto2);
		combo1 = new Combo("Combo N25", 0.15, productosCombo);
		restaurante = new Restaurante();
	}
	
	@AfterEach
	void tearDown()
	{
		restaurante = new Restaurante();
	}
		
	@Test
	void testIniciarPedidoSinPedidoEnCurso() throws Exception
	{
		restaurante.iniciarPedido("HuTao", "Funeraria El Camino");
	}
	
	@Test
	void testIniciarPedidoHayPedidoEnCurso() throws YaHayUnPedidoEnCursoException
	{
		
		restaurante.iniciarPedido("HuTao", "Funeraria El Camino");
		try
		{
			restaurante.iniciarPedido("Zhongli", "Liyue");
		}
		catch (YaHayUnPedidoEnCursoException exception) {
			assertTrue(exception.getMessage().contains("HuTao"));
			assertTrue(exception.getMessage().contains("Zhongli"));
		}

    }
	
	
	@Test
	void testCerrarYGuardarPedidoConPedido() throws Exception
	{
		restaurante.iniciarPedido("9S", "Bunker");
		String nombreArchivo = (".datafactura_" + (restaurante.getPedidoEnCurso().getIdPedido())).toString();
		restaurante.cerrarYGuardarPedido();
		File archivo = new File(nombreArchivo+".txt");
		assertTrue(archivo.exists(), "La factura no fue generada correctamente");	
	}
	

	//@Test
	//void testCerrarYGuardarPedidoSinPedido() throws Exception
	//{
		//try
		//{
			//restaurante.cerrarYGuardarPedido();
			
		//}
		
		//catch (NoHayPedidoEnCursoException e) {
			//assertEquals("Actualmente no hay un pedido en curso", e.getMessage());
		//}
	//}
	@Test
	void testGetPedidoEnCursoSinIniciar() {
	    assertNull(restaurante.getPedidoEnCurso(), "No debería haber un pedido en curso");
	}

	@Test
	void testGetPedidoEnCursoConPedido() throws Exception {
	    restaurante.iniciarPedido("Nahida", "Sumeru");

	    Pedido pedido = restaurante.getPedidoEnCurso();

	    assertNotNull(pedido, "Debería haber un pedido en curso");
	    assertEquals("Nahida", pedido.getNombreCliente());
	}
	
	@Test 
	void testGetPedidosDespuesDeCerrarPedido() throws Exception {
	    restaurante.iniciarPedido("Yae", "Inazuma");

	    restaurante.cerrarYGuardarPedido();

	    ArrayList<Pedido> pedidos = restaurante.getPedidos();

	    assertEquals(0, pedidos.size(), "Debería haber 1 pedido cerrado");
	}
	
	@Test 
	void testCargarInformacionRestaurante() throws Exception
	{
		File archivoIngredientes = new File("./data/ingredientes.txt");
	    File archivoMenu = new File("./data/menu.txt");
	    File archivoCombos = new File("./data/combos.txt");

	    restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
	    
	    ArrayList<Ingrediente> ingredientes = restaurante.getIngredientes();
	    assertFalse(ingredientes.isEmpty(), "Los ingredientes no fueron cargados");
	    assertEquals("lechuga", ingredientes.get(0).getNombre(), "Los ingredientes no fueron cargados correctamente");
	    assertEquals(1000, ingredientes.get(0).getCostoAdicional(), "Los ingredientes no fueron cargados correctamente");
	    
	    ArrayList<ProductoMenu> menu = restaurante.getMenuBase();
	    assertFalse(menu.isEmpty(), "El menú no fue cargado");
	    assertEquals("corral", menu.get(0).getNombre(), "El menu no se cargo correctamente");
	    assertEquals(14000, menu.get(0).getPrecio(), "El menu no se cargo correctamente");
	    
	    ArrayList<Combo> combos = restaurante.getMenuCombos();
	    assertFalse(combos.isEmpty(), "Los combos no fueron cargados");
	    assertEquals("combo corral", combos.get(0).getNombre(), "El primer combo no es 'combo corral'");
	}
	

	

}
