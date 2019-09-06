package es.mapfre.nse.pruebas.regresion.gestion.operaciones;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.contratacion.PaginaFigurasParticipantes;
import es.mapfre.nse.paginas.contratacion.PaginaInformacionGeneral;
import es.mapfre.nse.paginas.contratacion.PaginaObjetosAsegurados;
import es.mapfre.nse.paginas.gestion.operaciones.PaginaBusquedaOperaciones;

public class CP118_ConsultarOperacionIncompletaTest extends NseBaseTest {

	public static String CODIGO_PRUEBA = "CP118";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void consultarOperacionIncompeta() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaBusquedaOperaciones paginaBusquedaOperaciones = menu.irOpcion_GestionOperaciones();
    	
    	assertTrue("No se ha cargado la página de Gestión de Operaciones" ,paginaBusquedaOperaciones.getTitulo().equals("Gestión de Operaciones"));
    	
    	paginaBusquedaOperaciones.setDocumentoTomador(getDatoTest("cliente.nif"));
    	paginaBusquedaOperaciones.pulsarBotonBusquedaAvanzada();
    	
    	paginaBusquedaOperaciones.busquedaAvanzada_SeleccionarTipoOperacion("OPERACIÓN INCOMPLETA");
    	paginaBusquedaOperaciones.busquedaAvanzada_pulsarBotonBuscar();
    	paginaBusquedaOperaciones.pulsarBotonBuscar();
    	
    	int numeroResultados = paginaBusquedaOperaciones.getNumeroResultados();
    	assertTrue("La busqueda no ha devuelto resultados", numeroResultados > 0);

    	// Si solo hay un resultado el primer elemento se selecciona automaticamente
    	if(numeroResultados>1) 
    		paginaBusquedaOperaciones.seleccionarResultadoBusqueda(0);
    	
    	
    	paginaBusquedaOperaciones.pulsarBotonConsultar();
    	PaginaInformacionGeneral paginaInformacionGeneral = new PaginaInformacionGeneral();
    	paginaInformacionGeneral.init(getDriver());
    	paginaInformacionGeneral.waitForLoad();
 
       	// pulsar siguiente 
    	PaginaObjetosAsegurados paginaObjetosAsegurados = paginaInformacionGeneral.pulsarSiguiente();
    	
    	PaginaFigurasParticipantes paginaFigurasParticipantes = paginaObjetosAsegurados.pulsarSiguiente();
    	paginaFigurasParticipantes.pulsarSalirOperacionConsulta();
	}
	
}
