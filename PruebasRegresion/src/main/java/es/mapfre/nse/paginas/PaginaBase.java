package es.mapfre.nse.paginas;

import java.util.function.Supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PaginaBase {

	public static String FRAME_OPERACIONES = "ARQOS_MC";	
	private static String ID_WAIT_LAYER = "waitMessageLayer2";
	
	private static String XPATH_ID_PARCIAL = "//*[contains(@id, '%s')]";
	
	private static String ID_PARCIAL_BOTON_CONTORLES_TECNICOS_ACEPTAR = "formControlesTecnicosformPago:btnAceptarNIformPago";
	
	private static String ID_PARCIAL_BOTON_SALIR = ":btnSalirOperacionSeleccionDirecta";
	private static String ID_PARCIAL_BOTON_SALIR_GUARDAR_SI = ":panelAvisoSalirOperacionComandoEstAS";
	private static String ID_PARCIAL_BOTON_SALIR_GUARDAR_NO = ":panelAvisoSalirOperacionComandoEstAN";
	
	private static String ID_PARCIAL_BOTON_SALIR_CONFIRMAR_SI = ":panelAvisoSalirOperacionSinNumeroComandoEstAS";
	//*[@id="formFiguras:j_id1798:panelAvisoSalirOperacionSinNumeroCN"]
	
	private static String ID_BOTON_ACEPTAR_POPUP_CAMBIO_TARIFA = "PopUpForm:modalAvisosKoButton";
	
	public void pulsarBotonWarningCambioTarifa() {
		pulsarBotonConEspera(ID_BOTON_ACEPTAR_POPUP_CAMBIO_TARIFA);
		waitForLoading(10000);
	}
	
	public void pulsarSalirOperacionConsulta() {
		waitForCondition(10000, () -> recuperarBotonSalir());
		pulsarBotonIdParcial(ID_PARCIAL_BOTON_SALIR);
		pulsarBotonIdParcial(ID_PARCIAL_BOTON_SALIR_CONFIRMAR_SI);
	}
	
	public void pulsarSalirOperacion(boolean guardar) {
		waitForCondition(10000, () -> recuperarBotonSalir());
		pulsarBotonIdParcial(ID_PARCIAL_BOTON_SALIR);
		if(guardar)
			pulsarBotonIdParcial(ID_PARCIAL_BOTON_SALIR_GUARDAR_SI);
		else
			pulsarBotonIdParcial(ID_PARCIAL_BOTON_SALIR_GUARDAR_NO);
	}
	
	private Boolean recuperarBotonSalir() {
		try {
			findElementByIdParcial(ID_PARCIAL_BOTON_SALIR);	
		} catch (Exception e) {
			return false;
		}		
		return true;
	}

	
	private WebDriver driver;

	private SubPaginaGenerarDocumentacion subPaginaGenerarDocumentacion = null;
	private SubPaginaAgendar subPaginaAgendar = null;
	
	public void init(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
		
	protected WebElement findElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	protected WebElement findElementById(String id) {
		return driver.findElement(By.id(id));
	}
	
	/**
	 * Encuentra un elemento que tiene un id parcial (por ejemplo un 
	 * id que tiene una parte fija y una variable se buscaria poniendo
	 * a este metodo la parte fija)
	 * @param id parcial por el que se desea buscar
	 * @return
	 */
	protected WebElement findElementByIdParcial(String id) {
		return findElementByXpath(String.format(XPATH_ID_PARCIAL, id));
	}
	
	protected WebElement findElementByLabel(String label) {
		return driver.findElement(By.linkText(label));
	}
		
	protected String getTituloHTML() {
		return driver.getTitle();
	}
	
	public WebElement rellenarCampo(String idCampo, String valor) {
		WebElement campoNombre = findElementById(idCampo);
		campoNombre.sendKeys(valor);
		return campoNombre;
	}
	
	public boolean estadoCargandoActivado() {
		getDriver().switchTo().parentFrame();		
		WebElement waitLayer = findElementById(ID_WAIT_LAYER);
		boolean salida = !waitLayer.getCssValue("display").equals("none");
		getDriver().switchTo().frame(FRAME_OPERACIONES);
		return salida;
	}
	
	protected abstract String getXpathTitulo();
	protected abstract String getIdTipoOperacion();
	
	public String getTipoOperacion() {
		return findElementByXpath(getIdTipoOperacion()).getText();
	}
	
	public String getTitulo() {
		return findElementByXpath(getXpathTitulo()).getText();
	}
	
	public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                    	try {
							findElementByXpath(getXpathTitulo());
						} catch (Exception e) {
							return false;
						}                    	
                        return true;
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(pageLoadCondition);
    }
	
	protected boolean waitCondition() {
		return !estadoCargandoActivado();
	}
	
	public void waitForCondition(int tiempoEspera, Supplier<Boolean> funcCondition) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return funcCondition.get();
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, tiempoEspera);
        wait.until(pageLoadCondition);
    }
	
	public void waitForCondition(int tiempoEspera) {
		waitForCondition(tiempoEspera, () -> waitCondition());       
    }
	
	
	protected void pulsarBotonConEspera(String idBoton) {
		WebElement boton = findElementById(idBoton);
		
		//WebDriverWait boton = new WebDriverWait(driver, 10);
		
		//WebElement element = boton.until(ExpectedConditions.elementToBeClickable(By.id(idBoton)));
		
		
		if(!boton.isDisplayed()) mostrarElementoPorId(idBoton);
		
		if(!boton.isEnabled())
			waitForCondition(10000, () -> !boton.isEnabled());
				
		boton.click();
		
		waitForLoading(10000);
	}
	
	protected void esperarBotonActivo(String idBoton, int segundosEspera){
		WebDriverWait wait = new WebDriverWait(driver, segundosEspera);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(idBoton))); 
	}
	
	protected void waitForLoading(int tiempoEspera) {
		if(estadoCargandoActivado())
			waitForCondition(tiempoEspera, () -> !estadoCargandoActivado());
	}
	
	protected void mostrarElementoPorId(String idElemento) {
		WebElement element = findElementById(idElemento);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	protected void seleccionarIndiceCombo(String idCombo, int indice) {
		WebElement combo = findElementById(idCombo);
		for (int i = 0; i < indice; i++) combo.sendKeys(Keys.DOWN);
		combo.sendKeys(Keys.TAB);
		waitForLoading(10000);
	}
	
	protected WebElement seleccionarComboPorTextoVisible(String idCombo, String textoVisible) {
		WebElement combo = findElementById(idCombo);
		Select selectObject = new Select(combo);
		selectObject.selectByVisibleText(textoVisible);
		
		return combo;
	}
	
	protected SubPaginaGenerarDocumentacion getSubPaginaGenerarDocumentacion() {
		if(subPaginaGenerarDocumentacion==null) {
			subPaginaGenerarDocumentacion = new SubPaginaGenerarDocumentacion();
			subPaginaGenerarDocumentacion.init(getDriver());		
		}
		
		return subPaginaGenerarDocumentacion;
	}
	
	public SubPaginaAgendar getSubPaginaAgendar() {
		if(subPaginaAgendar==null) {
			subPaginaAgendar = new SubPaginaAgendar();
			subPaginaAgendar.init(getDriver());		
			subPaginaAgendar.waitForLoad();
		}
		
		return subPaginaAgendar;
	}
	
	protected boolean existeElemento(String idElemento) {
		try {
			findElementById(idElemento);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * pulsa un Boton con espera mediante un id que tiene una parte fija y otra parte que
	 * se genera dinamicamente
	 * @param idParcial Parte fija del id del componente
	 */
	protected void pulsarBotonIdParcial(String idParcial) {
		WebElement boton = findElementByIdParcial(idParcial);
		
		if(!boton.isEnabled())
			waitForCondition(10000, () -> !boton.isEnabled());
		
		boton.click();
		
		waitForLoading(10000);
	}
	
	public void pulsarControlesTecnicosAceptar() {
		pulsarBotonIdParcial(ID_PARCIAL_BOTON_CONTORLES_TECNICOS_ACEPTAR);		
	}

}
