package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;
import es.mapfre.nse.paginas.contratacion.PaginaCliente;
import es.mapfre.nse.paginas.contratacion.PaginaCoberturas;
import es.mapfre.nse.paginas.contratacion.PaginaFigurasParticipantes;
import es.mapfre.nse.paginas.contratacion.PaginaInformacionGeneral;
import es.mapfre.nse.paginas.contratacion.PaginaObjetosAsegurados;
import es.mapfre.nse.paginas.contratacion.PaginaPagos;
import es.mapfre.nse.paginas.contratacion.PaginaProductos;

/**
 * Propuesta con cliente existente y descuento y comercial libre
 */
public class CP008_GenerarPropuestaClienteExistenteTest extends NseBaseTest{
	
	public static String CODIGO_PRUEBA = "CP008";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void generarPropuestaHogar() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaCliente paginaDatosPersonales = menu.irOpcion_OfertaContratacion();
    	assertTrue(paginaDatosPersonales.getTitulo().equals("Información de Cliente"));
    	
    	PaginaProductos paginaProductos = paginaDatosPersonales.pulsarSiguiente();
    	
    	assertTrue(paginaProductos.getTitulo().equals("Búsqueda de Productos Comerciales"));
    	
    	paginaProductos.buscarPorProductoComercial("HOGAR PLATINO");
    	
    	// seleccionar propuesta
    	paginaProductos.seleccionarTipoOperacion("PROPUESTA");
    	
    	PaginaInformacionGeneral paginaInformacionGeneral = paginaProductos.pulsarSiguiente();
    	
    	assertTrue(paginaInformacionGeneral.getTitulo().equals("Información General"));
    	
    	PaginaObjetosAsegurados paginaObjetosAsegurados = paginaInformacionGeneral.pulsarSiguiente();
    	    	
    	assertTrue(paginaObjetosAsegurados.getTitulo().equals("Inf. Objetos Asegurados"));
    	
    	rellenarDatosPaginaObjetosAseguradosHogar(paginaObjetosAsegurados);    	
    	PaginaFigurasParticipantes paginaFigurasParticipantes = paginaObjetosAsegurados.pulsarSiguiente();
    	
    	assertTrue(paginaFigurasParticipantes.getTitulo().equals("Figuras Participantes"));
    	
    	rellenarFigurasParticipantes(paginaFigurasParticipantes);
    	PaginaCoberturas paginaCoberturas = paginaFigurasParticipantes.pulsarSiguiente();
    	
    	assertTrue(paginaCoberturas.getTitulo().equals("Coberturas / Garantías y Descuentos / Recargos"));
    	
    	paginaCoberturas.irSeccionDescuentos();
    	
    	paginaCoberturas.setOpcionTecnicoSubscripcionFijo(1);
    	paginaCoberturas.setDescuentoTecnicoSubscripcionFijo("5");
    	    	
    	paginaCoberturas.pulsarCalcular();
    	PaginaPagos paginaPagos = paginaCoberturas.pulsarSiguiente();
    			
    	assertTrue("No se ha navegado correctamente a la pagina de pagos",paginaPagos.getTitulo().equals("Pagos"));
    	
    	paginaPagos.seleccionarCuenta(1);
    	
    	// GENERAR Proyecto
    	SubPaginaGenerarDocumentacion subPaginaDocumentacion = paginaPagos.pulsarGenerarProyecto();
    	
    	// cancelar agendar
    	paginaPagos.getSubPaginaAgendar().pulsarBotonCancelar();
    	
    	// recuperar codigo de referencia
    	String numeroReferencia = subPaginaDocumentacion.obtenerNumeroReferencia();
    	System.out.println(numeroReferencia);
    	
    	subPaginaDocumentacion.pulsarAceptar();
    	
    	subPaginaDocumentacion.pulsarImprimirDocumento();
    	subPaginaDocumentacion.cerrarDocumentoPDFGenerado();
    	
    	subPaginaDocumentacion.pulsarAceptarDocumentacionCorrecta();
	}
		
	private void rellenarFigurasParticipantes(PaginaFigurasParticipantes paginaFigurasParticipantes) {
		paginaFigurasParticipantes.pulsarGestionClientes();
		
		paginaFigurasParticipantes.setGestionClientesNombre(getDatoTest("cliente.nombre"));
		paginaFigurasParticipantes.setGestionClientesApelido1(getDatoTest("cliente.apellido1"));
		paginaFigurasParticipantes.setGestionClientesApelido2(getDatoTest("cliente.apellido2"));
		
		paginaFigurasParticipantes.pulsarGestionClientesBuscar();
		paginaFigurasParticipantes.pulsarGestionClientesSeleccionar();		
	}

	private void rellenarDatosPaginaObjetosAseguradosHogar(PaginaObjetosAsegurados paginaObjetosAsegurados) {
		paginaObjetosAsegurados.waitForCondition(1000);
		paginaObjetosAsegurados.pulsarReferenciasCatastrales();
				
		paginaObjetosAsegurados.setModalRefCatastrales_CodigoPostal("28020");
		paginaObjetosAsegurados.setModalRefCatastrales_Calle("DULCINEA (CALLE)");
		paginaObjetosAsegurados.setModalRefCatastrales_Numero("31");
		paginaObjetosAsegurados.setModalRefCatastrales_Piso("2");
		paginaObjetosAsegurados.setModalRefCatastrales_Puerta("D");
		
		paginaObjetosAsegurados.pulsarModalRefCatastrales_Buscar();
		paginaObjetosAsegurados.pulsarModalRefCatastrales_Aceptar();
	}
	
}
