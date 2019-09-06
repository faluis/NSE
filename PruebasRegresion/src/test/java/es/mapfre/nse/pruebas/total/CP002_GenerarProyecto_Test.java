package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;
import es.mapfre.nse.paginas.calculorapido.PaginaBusquedaProductos;
import es.mapfre.nse.paginas.calculorapido.PaginaCapturaInformacion;

/**
 * Proyecto desde cálculo rápido con cliente existente y descuento campaña comercial 
 */
public class CP002_GenerarProyecto_Test extends NseBaseTest {

	public static String CODIGO_PRUEBA = "CP002";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void generarProyectoHogarTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaBusquedaProductos paginaBusquedaProductos = menu.irOpcion_CalculoRapido();
    	
    	assertTrue("No se ha cargado la página de Búsqueda de Productos Comerciales" ,paginaBusquedaProductos.getTitulo().equals("Búsqueda de Productos Comerciales"));
    	
    	paginaBusquedaProductos.buscarPorProductoComercial("HOGAR PLATINO");    	
    	PaginaCapturaInformacion paginaCapturaInformacion = paginaBusquedaProductos.pulsarSiguiente();
    	
    	assertTrue("No se ha cargado la página de Captura de Información", paginaCapturaInformacion.getTitulo().equals("Captura de información"));
    	
    	rellenarCliente(paginaCapturaInformacion);    	
    	
    	paginaCapturaInformacion.setCodigoPostal("28020");
    	paginaCapturaInformacion.setAnoConstruccion("1995");
    	paginaCapturaInformacion.setM2Vivienda("80");
    	paginaCapturaInformacion.pulsarAceptarInformacionObjetoAsegurado();
    	    	
    	paginaCapturaInformacion.pulsarCalcular();
    	
    	paginaCapturaInformacion.pulsarDesplegarSeccionBonificaciones();
    	paginaCapturaInformacion.seleccionarDescuentoTecnicoSubscripcionFijo(1);
    	paginaCapturaInformacion.setDescuentoTecnicoSubscripcionFijoPorcentajeInicial("5");
    	
    	paginaCapturaInformacion.pulsarCalcular();
    	paginaCapturaInformacion.pulsarCalcular();
    	paginaCapturaInformacion.seleccionarPagoAnual();
    	
    	paginaCapturaInformacion.seleccionarCheckReutilizarDatos();
    	
    	SubPaginaGenerarDocumentacion subPaginaDocumentacion = paginaCapturaInformacion.pulsarGenerarProyecto();
    	
    	// recuperar codigo de referencia
    	String numeroReferencia = subPaginaDocumentacion.obtenerNumeroReferencia();
    	System.out.println(numeroReferencia);
    	
    	subPaginaDocumentacion.pulsarAceptar();
    	subPaginaDocumentacion.pulsarAceptarDocumentacionCorrecta();
	}
	
	private void rellenarCliente(PaginaCapturaInformacion paginaCapturaInformacion) {
		paginaCapturaInformacion.pulsarGestionClientes();
		
		paginaCapturaInformacion.setGestionClientesNombre(getDatoTest("cliente.nombre"));		
		paginaCapturaInformacion.setGestionClientesApelido1(getDatoTest("cliente.apellido1"));
		paginaCapturaInformacion.setGestionClientesApelido2(getDatoTest("cliente.apellido2"));
		
		paginaCapturaInformacion.pulsarGestionClientesBuscar();
		paginaCapturaInformacion.pulsarGestionClientesSeleccionar();		
	}
	
}
