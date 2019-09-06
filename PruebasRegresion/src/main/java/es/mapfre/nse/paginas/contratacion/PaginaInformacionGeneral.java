package es.mapfre.nse.paginas.contratacion;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaInformacionGeneral extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formDG\"]/div[3]/span[3]/table/tbody/tr/td[3]/span";
	
	public static String ID_BTN_SIGUIENTE = "formDG:btnSiguiente";
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	public PaginaObjetosAsegurados pulsarSiguiente() {
		findElementById(ID_BTN_SIGUIENTE).click();
		
		PaginaObjetosAsegurados paginaSiguiente = new PaginaObjetosAsegurados();
		paginaSiguiente.init(getDriver());
		
		paginaSiguiente.waitForLoad();
		
		return paginaSiguiente;
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
}
