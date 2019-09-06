package es.mapfre.nse.pruebas.regresion.gestion.solicitudes;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.gestion.solicitudes.PaginaBusquedaSolicitudes;

public class CP303_ConsultarHistorial extends NseBaseTest {

	public static String CODIGO_PRUEBA = "CP301";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}

	@Test
	public void consultarHistorialTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaBusquedaSolicitudes paginaBusquedaSolicitudes = menu.irOpcion_GestionSolicitudes();
    	
    	assertTrue("No se ha cargado la página de Gestión de Solicitudes" , paginaBusquedaSolicitudes.getTitulo().equals("Gestión de Respuestas"));
    	
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);
		
    	Date fechaDesde = new Date(cal.getTimeInMillis());
    	Date fechaHasta = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
    	
    	paginaBusquedaSolicitudes.setFechaMecanizacionDesde(sdf.format(fechaDesde));
    	paginaBusquedaSolicitudes.setFechaMecanizacionHasta(sdf.format(fechaHasta));
    	
    	paginaBusquedaSolicitudes.pulsarBotonBuscar();
    	
    	int numeroResultados = paginaBusquedaSolicitudes.getResultados();
    	assertTrue("Cero resultados obtenidos", numeroResultados>0);
    	
    	// Si solo hay un resultado el primer elemento se selecciona automaticamente
    	if(numeroResultados>1) 
    		paginaBusquedaSolicitudes.seleccionarResultadoBusqueda(0);
    	
    	paginaBusquedaSolicitudes.pulsarBotonVerHistorial();
    	
    	paginaBusquedaSolicitudes.pulsarBotonAceptarVerHistorial();	
	}
	
}
