package es.mapfre.nse.paginas.calculorapido;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaBusquedaProductos extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formSelecProdCR\"]/div[2]/span[3]/table/tbody/tr/td/span";
	
	private static String ID_INPUT_PRODUCTO_COMERCIAL = "formSelecProdCR:prodComercial";
	private static String ID_BTN_SIGUIENTE = "formSelecProdCR:btnSiguiente";
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	public void buscarPorProductoComercial(String nombreProducto) {
		WebElement inputBusqueda = findElementById(ID_INPUT_PRODUCTO_COMERCIAL);
		inputBusqueda.sendKeys(nombreProducto);
		inputBusqueda.sendKeys(Keys.ENTER);
		
		waitForCondition(10000);
	}
	
	public PaginaCapturaInformacion pulsarSiguiente() {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		findElementById(ID_BTN_SIGUIENTE).click();
		
		PaginaCapturaInformacion paginaSiguiente = new PaginaCapturaInformacion();
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
