package es.mapfre.nse.pruebas.regresion.subscripcion.operaciones;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTestSubscriptor;
import es.mapfre.nse.paginas.subscripcion.operaciones.PaginaBusquedaSubscripciones;

public class CP407_AutorizacionConsultaCentroTecnico extends NseBaseTestSubscriptor {

	public static String CODIGO_PRUEBA = "C407";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}

	
	@Test
	public void consultarHistorialSubscripcionTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
       
    	PaginaBusquedaSubscripciones paginaBusquedaSubscripciones = menu.irOpcion_SubscripcionOperaciones();

    	assertTrue("No se ha cargado la p�gina de Gesti�n de Operaciones" , paginaBusquedaSubscripciones.getTitulo().equals("Suscripci�n Operaciones"));
    	
    	/*	Seleccionamos en el combo el valor ASIGNADA SUSCRIPTOR
    	 * 	Los posibles valores son: "ASIGNADA SUSCRIPTOR"
    	 * 
    	 */
    	
    	paginaBusquedaSubscripciones.seleccionarValorCombo("11");
    
    	
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
    	
    	paginaBusquedaSubscripciones.pulsarBotonBuscar();
    	
    	int numeroResultados = paginaBusquedaSubscripciones.getResultados();
    	assertTrue("Cero resultados obtenidos", numeroResultados>0);

    	// Si solo hay un resultado el primer elemento se selecciona automaticamente
    	if(numeroResultados>1) 
    		paginaBusquedaSubscripciones.seleccionarResultadoBusqueda(0);
    	
    	paginaBusquedaSubscripciones.pulsarBotonAutorizarConsulta();
    	
    	paginaBusquedaSubscripciones.pulsarBotonAutorizarSiguiente();
    	
    	paginaBusquedaSubscripciones.pulsarBotonAutorizarConsultaAceptar();
    	
    	paginaBusquedaSubscripciones.pulsarBotonAutorizarDirectaInterna();	
    	
    	paginaBusquedaSubscripciones.pulsarBotonComandoConsulta();
	}
	
}
