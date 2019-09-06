package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.gestion.operaciones.PaginaBusquedaOperaciones;

/**
 * Gestion de operaciones -> Restablecer
 */
public class CP102_RestablecerTest extends NseBaseTest {

	public static String CODIGO_PRUEBA = "CP102";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void restablecerTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaBusquedaOperaciones paginaBusquedaOperaciones = menu.irOpcion_GestionOperaciones();
    	
    	assertTrue("No se ha cargado la página de Gestión de Operaciones" ,paginaBusquedaOperaciones.getTitulo().equals("Gestión de Operaciones"));
    	    	
    	//paginaBusquedaOperaciones.setDocumentoTomador(getDatoTest("cliente.nif"));
    	paginaBusquedaOperaciones.pulsarBotonBusquedaAvanzada();
 
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);
		
    	Date fechaDesde = new Date(cal.getTimeInMillis());
    	Date fechaHasta = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
    	paginaBusquedaOperaciones.setFechaMecanizacionDesdeAvanzada(sdf.format(fechaDesde));
    	paginaBusquedaOperaciones.setFechaMecanizacionHastaAvanzada(sdf.format(fechaHasta));
    	
    	paginaBusquedaOperaciones.busquedaAvanzada_PropietarioOperacion("AJENAS");    
    	paginaBusquedaOperaciones.busquedaAvanzada_SeleccionarTipoOperacion("PROYECTO");
    	paginaBusquedaOperaciones.busquedaAvanzada_EstadoOperacion("BLOQUEADA");
    	paginaBusquedaOperaciones.busquedaAvanzada_pulsarBotonBuscar();
    	
    	int numeroResultados = paginaBusquedaOperaciones.getNumeroResultados();
    	assertTrue("La busqueda no ha devuelto resultados", numeroResultados > 0);
    	
    	// Si solo hay un resultado el primer elemento se selecciona automaticamente
    	if(numeroResultados>1) 
    		paginaBusquedaOperaciones.seleccionarResultadoBusqueda(0);
    	
    	paginaBusquedaOperaciones.pulsarBotonRestablecer(true);
    	
    	paginaBusquedaOperaciones.pulsarBotonRestablerOk();
	}
	
}
