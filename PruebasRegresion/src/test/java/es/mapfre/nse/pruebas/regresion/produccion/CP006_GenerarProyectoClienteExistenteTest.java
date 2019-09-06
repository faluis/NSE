package es.mapfre.nse.pruebas.regresion.produccion;

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
 * Proyecto desde oferta contratación con cliente existente y descuento suscripción fijo
 */
public class CP006_GenerarProyectoClienteExistenteTest extends NseBaseTest{
	
	public static String CODIGO_PRUEBA = "CP006";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void generarProyectoAuto() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaCliente paginaDatosPersonales = menu.irOpcion_OfertaContratacion();
    	assertTrue(paginaDatosPersonales.getTitulo().equals("Información de Cliente"));
    	
    	PaginaProductos paginaProductos = paginaDatosPersonales.pulsarSiguiente();
    	
    	assertTrue(paginaProductos.getTitulo().equals("Búsqueda de Productos Comerciales"));
    	
    	paginaProductos.buscarPorProductoComercial("TÚ ELIGES TODO RIESGO");
    	
    	PaginaInformacionGeneral paginaInformacionGeneral = paginaProductos.pulsarSiguiente();
    	
    	assertTrue(paginaInformacionGeneral.getTitulo().equals("Información General"));
    	
    	PaginaObjetosAsegurados paginaObjetosAsegurados = paginaInformacionGeneral.pulsarSiguiente();
    	
    	assertTrue(paginaObjetosAsegurados.getTitulo().equals("Inf. Objetos Asegurados"));
    	
    	rellenarDatosPaginaObjetosAseguradosAuto(paginaObjetosAsegurados);    	
    	PaginaFigurasParticipantes paginaFigurasParticipantes = paginaObjetosAsegurados.pulsarSiguiente();
    	
    	assertTrue(paginaFigurasParticipantes.getTitulo().equals("Figuras Participantes"));
    	
    	rellenarFigurasParticipantesAuto(paginaFigurasParticipantes);
    	
    	
    	PaginaCoberturas paginaCoberturas = paginaFigurasParticipantes.pulsarSiguiente();
    	
    	assertTrue(paginaCoberturas.getTitulo().equals("Coberturas / Garantías y Descuentos / Recargos"));
    	
    	//TODO REVISAR
//    	paginaCoberturas.irSeccionDescuentos();
//    	
//    	paginaCoberturas.setOpcionTecnicoSubscripcionFijo(1);
//    	paginaCoberturas.setDescuentoTecnicoSubscripcionFijo("5");
    	    	
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
    	
    	// si esta pulsado correo electronico desmarcarlo
    	if(subPaginaDocumentacion.estarMarcadoCheckEnviarPorEmail()) subPaginaDocumentacion.pulsarCheckEnviarPorEmail();
    	
    	// si no esta pulsado entregar en mano marcarlo
    	if(!subPaginaDocumentacion.estarMarcadoCheckEntregarEnMano()) subPaginaDocumentacion.pulsarCheckEntregarEnMano();
    	
    	subPaginaDocumentacion.pulsarAceptar();    	
    	subPaginaDocumentacion.pulsarAceptarDocumentacionCorrecta();
    	
    	subPaginaDocumentacion.pulsarBotonVerOfertasNO();
	}
	
	@Test
	public void generarProyectoHogar() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaCliente paginaDatosPersonales = menu.irOpcion_OfertaContratacion();
    	assertTrue(paginaDatosPersonales.getTitulo().equals("Información de Cliente"));
    	
    	PaginaProductos paginaProductos = paginaDatosPersonales.pulsarSiguiente();
    	
    	assertTrue(paginaProductos.getTitulo().equals("Búsqueda de Productos Comerciales"));
    	
    	paginaProductos.buscarPorProductoComercial("HOGAR FAMILIAR");
    	
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
	
	private void rellenarFigurasParticipantesAuto(PaginaFigurasParticipantes paginaFigurasParticipantes) {
		paginaFigurasParticipantes.waitForCondition(1000);
		rellenarFigurasParticipantes(paginaFigurasParticipantes);
		paginaFigurasParticipantes.seleccionarTipoCarnetConducir("B");
		paginaFigurasParticipantes.setFechaExpedicionLicenciaConducir("02/11/2003");
		
		paginaFigurasParticipantes.pulsarConsultarHistorialSiniestralidad();		
	}

	private void rellenarDatosPaginaObjetosAseguradosHogar(PaginaObjetosAsegurados paginaObjetosAsegurados) {
		paginaObjetosAsegurados.setCodigoPostal("28020");
		paginaObjetosAsegurados.setAnoConstruccion("1995");
		paginaObjetosAsegurados.setM2Vivienda("80");
	}
	
	private void rellenarDatosPaginaObjetosAseguradosAuto(PaginaObjetosAsegurados paginaObjetosAsegurados) {
		paginaObjetosAsegurados.setMatricula("0820-BYT");
		paginaObjetosAsegurados.seleccionarMatricula();
		//paginaObjetosAsegurados.pulsarDialogoConfirmacionMatricula();
		paginaObjetosAsegurados.setCodigoPostalCirculacion("28010");
	}
}
