package es.mapfre.nse.paginas.multiprecio;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import es.mapfre.nse.paginas.PaginaBase;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;

public class PaginaOfertaMultiprecio extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"textoSubtitulo\"]";
	
	private static String ID_BTN_HOGAR = "formMultiprecio:btnGestionProdHogar";
	private static String ID_BTN_AUTO = "formMultiprecio:btnGestionProdAutos";
	private static String ID_BTN_SIGUIENTE = "formMultiprecio:btnSiguiente";
	
	private static String ID_INPUT_INF_OBJ_ASEGURADO_COD_POSTAL       = "formMultiprecio:COD_POSTAL";
	private static String ID_INPUT_INF_OBJ_ASEGURADO_ANO_CONSTRUCCION = "formMultiprecio:ANO_CONSTRUCCION";
	private static String ID_INPUT_INF_OBJ_ASEGURADO_M2_VIVIENDA      = "formMultiprecio:METROS_CONST_VIVIENDA";
	
	private static String ID_INPUT_INF_TOMADOR_NUM_DOC = "formMultiprecio:numDocSubPestTomador_DatosPers";
	private static String ID_BTN_INF_TOMADOR_NUM_DOC   = "formMultiprecio:btnBuscarClienteSubPestTomador_DatosPers";
	
	private static String ID_BTN_GENERAR_PROYECTO = "formMultiprecio:btnAceptarEmisionCT";
	
	private static String ID_BOTON_CALCULAR = "formMultiprecio:btnCalcular";
	
	/////////////// CONSTANTES PARA AUTO
	
	private static String ID_INPUT_INF_OBJ_ASEGURADO_MATRICULA = "formMultiprecio:NUM_MATRICULA_VISUAL";
	private static String ID_BTN_SELECCIONAR_VEHICULO = "formIdv:btnSeleccionar";	
	private static String ID_INPUT_INF_OBJ_ASEGURADO_COD_POSTAL_CIRCULACION = "formMultiprecio:COD_POSTAL_CIRCULACION";
	
	private static String ID_COMBO_CODIGO_LICENCIA_CONDUCIR = "formMultiprecio:codLicenciaPestTomador";
	private static String ID_INPUT_FECHA_EXPEDICION_LICENCIA_CONDUCIR = "formMultiprecio:fechaExpPestTomadorInputDate";
	private static String ID_BTN_HISTORIAL_SINIESTRALIDAD = "formMultiprecio:btnBuscarPolizaPestTomador";
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	public void pulsarBotonSiguiente() {
		pulsarBotonConEspera(ID_BTN_SIGUIENTE);
	}
	
	/**
	 * INFORMACION BASICA
	 */
	public void pulsarBotonOfertaHogar() {
		pulsarBotonConEspera(ID_BTN_HOGAR);
	}
	
	public void pulsarBotonOfertaAuto() {
		pulsarBotonConEspera(ID_BTN_AUTO);
	}
	
	/**
	 * INFORMACION OBJETO ASEGURADO
	 */
	
	public void setInfObtAsegurado_CodigoPostal(String codigoPostal) {

		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		WebElement input = findElementById(ID_INPUT_INF_OBJ_ASEGURADO_COD_POSTAL);
		input.sendKeys(codigoPostal);
		input.sendKeys(Keys.TAB);
		
		if(estadoCargandoActivado())
			waitForCondition(10000);
	}
	
	public void setInfObtAsegurado_AnoConstrucion(String ano) {
		WebElement input = findElementById(ID_INPUT_INF_OBJ_ASEGURADO_ANO_CONSTRUCCION);
		input.sendKeys(ano);
		input.sendKeys(Keys.TAB);
		
		if(estadoCargandoActivado())
			waitForCondition(10000);
	}
	
	public void setInfObtAsegurado_M2Vivienda(String m2Vivienda) {
		WebElement input = findElementById(ID_INPUT_INF_OBJ_ASEGURADO_M2_VIVIENDA);
		input.sendKeys(m2Vivienda);
		input.sendKeys(Keys.TAB);
		
		if(estadoCargandoActivado())
			waitForCondition(10000);
	}
	
	public void setInfObtAsegurado_Matricula(String matricula) {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		WebElement input = findElementById(ID_INPUT_INF_OBJ_ASEGURADO_MATRICULA);
		input.sendKeys(matricula);
		input.sendKeys(Keys.TAB);
		
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		pulsarBotonConEspera(ID_BTN_SELECCIONAR_VEHICULO);	
	}
	
	public void setInfObtAsegurado_CodigoPostalCirculacion(String codigoPostal) {
		WebElement input = findElementById(ID_INPUT_INF_OBJ_ASEGURADO_COD_POSTAL_CIRCULACION);
		input.sendKeys(codigoPostal);
		input.sendKeys(Keys.TAB);
		
		if(estadoCargandoActivado())
			waitForCondition(10000);
	}
	
	
	
	/**
	 * INFORMADOR TOMADOR
	 */
	
	public void setInfTomador_NumeroDocumento(String numeroDocumento) {
		WebElement input = findElementById(ID_INPUT_INF_TOMADOR_NUM_DOC);
		input.sendKeys(numeroDocumento);
		input.sendKeys(Keys.TAB);
		
		waitForLoading(10000);		
	}
	
	public void pulsarBotonBuscarTomador() {
		pulsarBotonConEspera(ID_BTN_INF_TOMADOR_NUM_DOC);
	}

	public SubPaginaGenerarDocumentacion pulsarGenerarProyecto() {
		esperarBotonActivo(ID_BTN_GENERAR_PROYECTO, 240);
		pulsarBotonConEspera(ID_BTN_GENERAR_PROYECTO);
		return getSubPaginaGenerarDocumentacion();
	}
	
	public void pulsarConsultarHistorialSiniestralidad() {
		mostrarElementoPorId(ID_BTN_HISTORIAL_SINIESTRALIDAD);
		pulsarBotonConEspera(ID_BTN_HISTORIAL_SINIESTRALIDAD);
	}
	
	public void seleccionarTipoCarnetConducir(String tipoCarnet) {
		seleccionarComboPorTextoVisible(ID_COMBO_CODIGO_LICENCIA_CONDUCIR, tipoCarnet).sendKeys(Keys.TAB);
		waitForCondition(10000);
	}
	
	public void setFechaExpedicionLicenciaConducir(String fechaExpedicion) {
		rellenarCampo(ID_INPUT_FECHA_EXPEDICION_LICENCIA_CONDUCIR, fechaExpedicion).sendKeys(Keys.TAB);
		waitForCondition(10000);
	}
	
	/**
	 * DESCUENTOS
	 */
	private static String ID_TAB_SECCION_DESCUENTOS = "formMultiprecio:pestaniaBoniReca_lbl";
	
	public void irSeccionDescuentos() {
		pulsarBotonConEspera(ID_TAB_SECCION_DESCUENTOS);
		
	}
	
	private static String XPATH_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO = "//*[contains(@id, 'formMultiprecio:combo_4_')]";
	private static String XPATH_INPUT_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO = "//*[contains(@id, 'formMultiprecio:pctInicial_4_')]";
	
	public void seleccionarDescuentoTecnicoSubscripcionFijo(int indice) {
		WebElement combo = findElementByXpath(XPATH_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO); //getDriver().findElement(By.xpath("//*[contains(@id, 'formMultiprecio:combo_4_')]"));	
		for (int i = 0; i < indice; i++) combo.sendKeys(Keys.DOWN);
		combo.sendKeys(Keys.TAB);
		waitForLoading(10000);
	}
	
	public void setDescuentoTecnicoSubscripcionFijo(String descuento) {
		WebElement campoNombre = findElementByXpath(XPATH_INPUT_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO);
		campoNombre.sendKeys(descuento);
		campoNombre.sendKeys(Keys.TAB);
		waitForCondition(10000);
	}
	
	public void pulsarCalcular() {
		pulsarBotonConEspera(ID_BOTON_CALCULAR);
		
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
