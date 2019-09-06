package es.mapfre.nse.paginas;

import es.mapfre.nse.paginas.calculorapido.PaginaBusquedaProductos;
import es.mapfre.nse.paginas.contratacion.PaginaCliente;
import es.mapfre.nse.paginas.gestion.operaciones.PaginaBusquedaOperaciones;
import es.mapfre.nse.paginas.gestion.solicitudes.PaginaBusquedaSolicitudes;
import es.mapfre.nse.paginas.multiprecio.PaginaOfertaMultiprecio;
import es.mapfre.nse.paginas.subscripcion.anulaciones.PaginaBusquedaAnulaciones;
import es.mapfre.nse.paginas.subscripcion.operaciones.PaginaBusquedaSubscripciones;

public class MenuPrincipal extends PaginaBase{
		
	/**
	 * Nueva Produccion
	 */
	
	private static String XPATH_OPT_GESTION_OPERACIONES  = "//*[@id=\"2\"]/a";
	private static String XPATH_OPT_GESTION_SOLICITUDES  = "//*[@id=\"1\"]/a";
	
	private static String XPATH_OPT_OFERTA_CONTRATACION  = "//*[@id=\"3\"]/a";
	private static String XPATH_OPT_CALCULO_RAPIDO       = "//*[@id=\"4\"]/a";
	private static String XPATH_OPT_OFERTA_MULTIPRECIO   = "//*[@id=\"16\"]/a";
	
	private static String XPATH_OPT_SUBSCRIPCION_OPERACIONES   = "//*[@id=\"7\"]/a";
	private static String XPATH_OPT_SUBSCRIPCION_ANULACIONES   = "//*[@id=\"8\"]/a";

	public PaginaCliente irOpcion_OfertaContratacion() {
		return (PaginaCliente) irOpcion(XPATH_OPT_OFERTA_CONTRATACION, new PaginaCliente());
	}

	public PaginaBusquedaProductos irOpcion_CalculoRapido() {
		return (PaginaBusquedaProductos) irOpcion(XPATH_OPT_CALCULO_RAPIDO, new PaginaBusquedaProductos());
	}
	
	public PaginaOfertaMultiprecio irOpcion_OfertaMultiprecio() {
		return (PaginaOfertaMultiprecio) irOpcion(XPATH_OPT_OFERTA_MULTIPRECIO, new PaginaOfertaMultiprecio());		
	}
	
	/**
	 * GESTION DE OPERACIONES
	 */
	
	public PaginaBusquedaOperaciones irOpcion_GestionOperaciones() {
		return (PaginaBusquedaOperaciones) irOpcion(XPATH_OPT_GESTION_OPERACIONES, new PaginaBusquedaOperaciones());		
	}
	
	public PaginaBusquedaSolicitudes irOpcion_GestionSolicitudes() {
		return (PaginaBusquedaSolicitudes) irOpcion(XPATH_OPT_GESTION_SOLICITUDES, new PaginaBusquedaSolicitudes());		
	}
	
	/**
	 * Gestion de Subscripciones
	 */
	public PaginaBusquedaSubscripciones irOpcion_SubscripcionOperaciones() {
		return (PaginaBusquedaSubscripciones) irOpcion(XPATH_OPT_SUBSCRIPCION_OPERACIONES, new PaginaBusquedaSubscripciones());		
	}
	
	/**
	 * Gestion de Anulaciones
	 */
	public PaginaBusquedaAnulaciones irOpcion_SubscripcionAnulaciones() {
		return (PaginaBusquedaAnulaciones) irOpcion(XPATH_OPT_SUBSCRIPCION_ANULACIONES, new PaginaBusquedaAnulaciones());		
	}
	
	protected PaginaBase irOpcion(String xpathOpcion, PaginaBase paginaDestino) {
		findElementByXpath(xpathOpcion).click();
		
		getDriver().switchTo().frame(FRAME_OPERACIONES);
		paginaDestino.init(getDriver());
		paginaDestino.waitForLoad();
		return paginaDestino;
	}
	
	@Override
	protected String getXpathTitulo() {
		return null;
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
