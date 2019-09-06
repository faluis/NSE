package es.mapfre.nse.paginas.contratacion;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaProductos extends PaginaBase {
	
	public static String XPATH_TITULO = "//*[@id=\"formProductos\"]/div[3]/span[3]/table/tbody/tr/td[2]/span";
	
	private static String ID_BOTON_FILTRO_HOGAR = "formProductos:btnGestionProdHogar";
	private static String XPATH_BOTON_FILTRO_AUTO = "//*[@id=\"formProductos:btnGestionProdAutos\"]";
	private static String ID_BOTON_BUSQUEDA = "formProductos:btnBuscar";
	
	private static String XPATH_INPUT_PRODUCTO_COMERCIAL = "//*[@id=\"formProductos:prodComercial\"]";
	
	public static String ID_COMBO_TIPO_OPERACION = "formProductos:tipoOperacion";
	
	public static String ID_BTN_SIGUIENTE = "formProductos:btnSiguiente";
	
	public void pulsarFiltroHogar() {
		findElementById(ID_BOTON_FILTRO_HOGAR).click();	
	}
	
	public void pulsarFiltroAuto() {
		findElementByXpath(XPATH_BOTON_FILTRO_AUTO).click();
	}
	
	public void pulsarBusqueda() {
		findElementById(ID_BOTON_BUSQUEDA).click();
	}
	
	public void buscarPorProductoComercial(String nombreProducto) {
		WebElement inputBusqueda = findElementByXpath(XPATH_INPUT_PRODUCTO_COMERCIAL);
		inputBusqueda.sendKeys(nombreProducto);
		inputBusqueda.sendKeys(Keys.ENTER);
		
		waitForCondition(10000);
	}
	
	public void seleccionarTipoOperacion(String tipoOperacion) {
		seleccionarComboPorTextoVisible(ID_COMBO_TIPO_OPERACION, tipoOperacion);
	}
	
	public String getTitulo() {
		return findElementByXpath(XPATH_TITULO).getText();
	}

	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	public PaginaInformacionGeneral pulsarSiguiente() {
		findElementById(ID_BTN_SIGUIENTE).click();
		
		PaginaInformacionGeneral paginaSiguiente = new PaginaInformacionGeneral();
		paginaSiguiente.init(getDriver());
		
		paginaSiguiente.waitForLoad();
		
		return paginaSiguiente;
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	protected boolean waitCondition() {
//		String elemento = "//*[@id=\"tProductosComerciales_celda_0-1\"]/span";
//		return findElementByXpath(elemento).getText().equals("HOGAR FAMILIAR");
//	}
}
