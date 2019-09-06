package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTestSubscriptor;
import es.mapfre.nse.paginas.subscripcion.anulaciones.PaginaBusquedaAnulaciones;

public class CP501_BuscarFechaMecanizacion extends NseBaseTestSubscriptor {

	public static String CODIGO_PRUEBA = "CP501";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void buscarOperacionesHoyTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
  
    	PaginaBusquedaAnulaciones paginaBusquedaAnulaciones = menu.irOpcion_SubscripcionAnulaciones();
 
    	assertTrue("No se ha cargado la página de Gestión de Anulaciones" , paginaBusquedaAnulaciones.getTitulo().equals("Suscripción de Solicitud de Anulación"));
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -15);
		
    	Date fechaDesde = new Date(cal.getTimeInMillis());
    	Date fechaHasta = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
    	
    	paginaBusquedaAnulaciones.setFechaMecanizacionDesde(sdf.format(fechaDesde));
    	paginaBusquedaAnulaciones.setFechaMecanizacionHasta(sdf.format(fechaHasta));
    	
    	paginaBusquedaAnulaciones.pulsarBotonBuscar();
    	
    	assertTrue("Cero resultados obtenidos", paginaBusquedaAnulaciones.getResultados()>0);
	}

}
