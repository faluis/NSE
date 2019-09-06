package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTestSubscriptor;
import es.mapfre.nse.paginas.subscripcion.operaciones.PaginaBusquedaSubscripciones;

public class CP401_BuscarFechaMecanizacion extends NseBaseTestSubscriptor {

	public static String CODIGO_PRUEBA = "CP401";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}

	
	@Test
	public void buscarOperacionesHoyTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaBusquedaSubscripciones paginaBusquedaSubscripciones = menu.irOpcion_SubscripcionOperaciones();

    	assertTrue("No se ha cargado la página de Gestión de Operaciones" , paginaBusquedaSubscripciones.getTitulo().equals("Suscripción Operaciones"));
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);
		
    	Date fechaDesde = new Date(cal.getTimeInMillis());
    	Date fechaHasta = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
    	
    	paginaBusquedaSubscripciones.setFechaMecanizacionDesde(sdf.format(fechaDesde));
    	paginaBusquedaSubscripciones.setFechaMecanizacionHasta(sdf.format(fechaHasta));
    	
    	paginaBusquedaSubscripciones.pulsarBotonBuscar();
    	
    	assertTrue("Cero resultados obtenidos", paginaBusquedaSubscripciones.getResultados()>0);
	}
	
}
