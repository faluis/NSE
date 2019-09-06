package es.mapfre.nse.paginas.gestion.operaciones;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;

public abstract class GenerarDocumentoBaseTest extends NseBaseTest {

	public void generarDocumentoTest(String tipoOperacion) {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaBusquedaOperaciones paginaBusquedaOperaciones = menu.irOpcion_GestionOperaciones();
    	
    	assertTrue("No se ha cargado la página de Gestión de Operaciones" ,paginaBusquedaOperaciones.getTitulo().equals("Gestión de Operaciones"));
    	Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -7);
		
    	Date fechaDesde = new Date(cal.getTimeInMillis());
    	Date fechaHasta = new Date();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");
    	paginaBusquedaOperaciones.setFechaMecanizacionDesde(sdf.format(fechaDesde));
    	paginaBusquedaOperaciones.setFechaMecanizacionHasta(sdf.format(fechaHasta));
    	
    	paginaBusquedaOperaciones.pulsarBotonBusquedaAvanzada();
    	
    	paginaBusquedaOperaciones.busquedaAvanzada_PropietarioOperacion("AJENAS");    
    	
    	paginaBusquedaOperaciones.busquedaAvanzada_SeleccionarTipoOperacion(tipoOperacion);
    	paginaBusquedaOperaciones.busquedaAvanzada_pulsarBotonBuscar();
    	
    	int numeroResultados = paginaBusquedaOperaciones.getNumeroResultados();
    	assertTrue("La busqueda no ha devuelto resultados", numeroResultados > 0);

    	// Si solo hay un resultado el primer elemento se selecciona automaticamente
    	if(numeroResultados>1) paginaBusquedaOperaciones.seleccionarResultadoBusqueda(0);
    	
    
    	SubPaginaGenerarDocumentacion spGenerarDocumentacion= paginaBusquedaOperaciones.pulsarBotonDocumentacion();    	
    	
    	// si esta pulsado correo electronico desmarcarlo
    	if(spGenerarDocumentacion.estarMarcadoCheckEnviarPorEmail()) spGenerarDocumentacion.pulsarCheckEnviarPorEmail();
    	
    	// si no esta pulsado entregar en mano marcarlo
    	if(!spGenerarDocumentacion.estarMarcadoCheckEntregarEnMano()) spGenerarDocumentacion.pulsarCheckEntregarEnMano();
    	
    	spGenerarDocumentacion.pulsarAceptar();
    	
    	spGenerarDocumentacion.pulsarImprimirDocumento();
    	
    	spGenerarDocumentacion.cerrarDocumentoPDFGenerado();
    	
    	spGenerarDocumentacion.pulsarAceptarDocumentacionCorrecta();
	}
	
}
