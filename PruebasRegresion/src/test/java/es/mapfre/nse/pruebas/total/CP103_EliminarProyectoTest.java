package es.mapfre.nse.pruebas.total;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.gestion.operaciones.PaginaBusquedaOperaciones;

public class CP103_EliminarProyectoTest extends NseBaseTest {

	public static String CODIGO_PRUEBA = "CP103";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void eliminarProyectoTest() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaBusquedaOperaciones paginaBusquedaOperaciones = menu.irOpcion_GestionOperaciones();
    	
    	assertTrue("No se ha cargado la página de Gestión de Operaciones" ,paginaBusquedaOperaciones.getTitulo().equals("Gestión de Operaciones"));
    	
    	paginaBusquedaOperaciones.setDocumentoTomador(getDatoTest("cliente.nif"));
    	paginaBusquedaOperaciones.pulsarBotonBusquedaAvanzada();
    	
    	paginaBusquedaOperaciones.busquedaAvanzada_SeleccionarTipoOperacion("PROYECTO");
    	paginaBusquedaOperaciones.busquedaAvanzada_pulsarBotonBuscar();
    	paginaBusquedaOperaciones.pulsarBotonBuscar();
    	
    	int numeroResultados = paginaBusquedaOperaciones.getNumeroResultados();
    	assertTrue("La busqueda no ha devuelto resultados", numeroResultados > 0);

    	// Si solo hay un resultado el primer elemento se selecciona automaticamente
    	if(numeroResultados>1) 
    		paginaBusquedaOperaciones.seleccionarResultadoBusqueda(0);
    	
    	paginaBusquedaOperaciones.pulsarBotonEliminar();
    	paginaBusquedaOperaciones.pulsarBotonConfirmarEliminar(true);
	}
	
}
