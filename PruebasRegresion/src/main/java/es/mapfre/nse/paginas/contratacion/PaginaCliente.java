package es.mapfre.nse.paginas.contratacion;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaCliente extends PaginaBase {
    
	public static String XPATH_BTN_SIGUIENTE = "//*[@id=\"formClientes:btnSiguiente\"]";
	public static String XPATH_TITULO = "//*[@id=\"formClientes\"]/div[2]/span[3]/table/tbody/tr/td[1]/span";
		
	public PaginaProductos pulsarSiguiente() {
		findElementByXpath(XPATH_BTN_SIGUIENTE).click();
		
		PaginaProductos paginaSiguiente = new PaginaProductos();
		paginaSiguiente.init(getDriver());
		paginaSiguiente.waitForLoad();
		
		return paginaSiguiente;
	}
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
