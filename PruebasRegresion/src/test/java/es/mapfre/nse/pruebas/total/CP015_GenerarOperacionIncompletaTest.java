package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.contratacion.PaginaCliente;
import es.mapfre.nse.paginas.contratacion.PaginaFigurasParticipantes;
import es.mapfre.nse.paginas.contratacion.PaginaInformacionGeneral;
import es.mapfre.nse.paginas.contratacion.PaginaObjetosAsegurados;
import es.mapfre.nse.paginas.contratacion.PaginaProductos;

/**
 * Proyecto desde oferta contratación con cliente existente y descuento suscripción fijo
 */
public class CP015_GenerarOperacionIncompletaTest extends NseBaseTest{
	
	public static String CODIGO_PRUEBA = "CP015";
	
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
    	
    	paginaFigurasParticipantes.pulsarSalirOperacion(true);
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
    	
    	paginaFigurasParticipantes.pulsarSalirOperacion(true);
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
