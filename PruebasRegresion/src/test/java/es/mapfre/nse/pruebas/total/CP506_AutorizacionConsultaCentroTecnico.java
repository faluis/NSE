package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTestSubscriptor;
import es.mapfre.nse.paginas.subscripcion.anulaciones.PaginaBusquedaAnulaciones;

public class CP506_AutorizacionConsultaCentroTecnico extends NseBaseTestSubscriptor {

	public static String CODIGO_PRUEBA = "CP506";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}

	
	@Test
	public void consultarHistorialSubscripcionTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
       
    	PaginaBusquedaAnulaciones paginaBusquedaAnulaciones = menu.irOpcion_SubscripcionAnulaciones();

    	assertTrue("No se ha cargado la página de Gestión de Anulaciones" , paginaBusquedaAnulaciones.getTitulo().equals("Suscripción de Solicitud de Anulación"));
    	
    	/*	Seleccionamos en el combo el valor ASIGNADA SUSCRIPTOR
    	 * 	Los posibles valores son: "ASIGNADA SUSCRIPTOR"
    	 * 
    	 */
    	
		paginaBusquedaAnulaciones.seleccionarValorCombo("11");
    
    	
//    	Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_YEAR, -30);
//		
//    	Date fechaDesde = new Date(cal.getTimeInMillis());
//    	Date fechaHasta = new Date();
//    	
//    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
//    	
//    	paginaBusquedaAnulaciones.setFechaMecanizacionDesde(sdf.format(fechaDesde));
//    	paginaBusquedaAnulaciones.setFechaMecanizacionHasta(sdf.format(fechaHasta));
    	
    	paginaBusquedaAnulaciones.pulsarBotonBuscar();
    	
    	int numeroResultados = paginaBusquedaAnulaciones.getResultados();
    	assertTrue("Cero resultados obtenidos", numeroResultados>0);

    	// Si solo hay un resultado el primer elemento se selecciona automaticamente
    	if(numeroResultados>1) 
    		paginaBusquedaAnulaciones.seleccionarResultadoBusqueda(0);
    	
    	paginaBusquedaAnulaciones.pulsarBotonAutorizarConsulta();
    	
    	paginaBusquedaAnulaciones.pulsarBotonAutorizarConsultaAceptar();
    	
    	paginaBusquedaAnulaciones.pulsarBotonAutorizarDirectaInterna();	
    	
    	paginaBusquedaAnulaciones.pulsarBotonComandoConsulta();
	}
	
}
