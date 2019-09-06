package es.mapfre.nse.paginas.contratacion;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaObjetosAsegurados extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formObjAseg\"]/div[3]/span[3]/table/tbody/tr/td[4]/span";
	
	// //*[@id="formObjAseg:btnSiguiente"]
	public static String ID_BTN_SIGUIENTE = "formObjAseg:btnSiguiente";
		
	/**
	 * Identificadores para inputs de la pagina
	 */
	
	private static String ID_INPUT_CODIGO_POSTAL = "formObjAseg:COD_POSTAL";	
	private static String ID_INPUT_ANO_CONSTRUCCION = "formObjAseg:ANO_CONSTRUCCION";
	private static String ID_INPUT_M2_VIVIENDA = "formObjAseg:METROS_CONST_VIVIENDA";

	private static String ID_BOTON_REFERENCIAS_CATASTRALES = "formObjAseg:btnAbrirModalRefCatastrales";
	private static String ID_COMBO_ESTADO_GENERAL = "formObjAseg:COD_ESTADO_GENERAL";
	private static String ID_INPUT_FECHA_PERITACION = "formObjAseg:FEC_PERITACIONInputDate";
	private static String ID_COMBO_TIPO_PERITO = "formObjAseg:TIPO_PERSONA_INFORME";
	private static String ID_INPUT_NOMBRE_PERITO = "formObjAseg:TXT_NOM_APE_PERITADOR";
	
	public void pulsarReferenciasCatastrales() {
		pulsarBotonConEspera(ID_BOTON_REFERENCIAS_CATASTRALES);
	}
	
	/**
	 * AUTO
	 */
	private static String ID_INPUT_MATRICULA  = "formObjAseg:NUM_MATRICULA_VISUAL";
	private static String ID_INPUT_COD_POSTAL_CIRCULACION = "formObjAseg:COD_POSTAL_CIRCULACION";
	private static String ID_BTN_SELECCIONAR = "formIdv:btnSeleccionar";
	private static String ID_BTN_MATRICULA = "formCartera:btnAceptar";
	
	private static String ID_SECCION_PERITACION = "formObjAseg:pestOtrosDatos_lbl";
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	public void setCodigoPostal(String codigoPostal) {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		WebElement inputCodigoPostal = findElementById(ID_INPUT_CODIGO_POSTAL);
		inputCodigoPostal.sendKeys(codigoPostal);
		inputCodigoPostal.sendKeys(Keys.TAB);		
	}
	
	public void setAnoConstruccion(String ano) {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		WebElement inputAnoConstruccion = findElementById(ID_INPUT_ANO_CONSTRUCCION);
		inputAnoConstruccion.sendKeys(ano);
		inputAnoConstruccion.sendKeys(Keys.TAB);		
	}
	
	public void setM2Vivienda(String m2Vivienda) {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		findElementById(ID_INPUT_M2_VIVIENDA).sendKeys(m2Vivienda);
	}

	public PaginaFigurasParticipantes pulsarSiguiente() {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		findElementById(ID_BTN_SIGUIENTE).click();
		
		PaginaFigurasParticipantes paginaSiguiente = new PaginaFigurasParticipantes();
		paginaSiguiente.init(getDriver());
		
		paginaSiguiente.waitForLoad();
		
		return paginaSiguiente;
	}
	
	public void setMatricula(String matricula) {
		rellenarCampo(ID_INPUT_MATRICULA, matricula).sendKeys(Keys.TAB);
		waitForLoading(10000);
	}
	
	public void pulsarDialogoConfirmacionMatricula() {
		pulsarBotonConEspera(ID_BTN_SELECCIONAR);
	}
	
	public void seleccionarMatricula() {
		pulsarBotonConEspera(ID_BTN_MATRICULA);
	}
	
	public void setCodigoPostalCirculacion(String codigoPostalCirculacion) {
		rellenarCampo(ID_INPUT_COD_POSTAL_CIRCULACION, codigoPostalCirculacion);
	}

	/**
	 * MODAL REFERENCIAS CATASTRALES
	 */
	
	private static String ID_INPUT_MODAL_REF_CATASTRALES_COD_POSTAL = "formInformacionCatastral:codPostalCatastro";
	private static String ID_INPUT_MODAL_REF_CATASTRALES_CALLE = "formInformacionCatastral:nombreViaCatastrocomboboxField";
	
	private static String ID_INPUT_MODAL_REF_CATASTRALES_NUMERO = "formInformacionCatastral:numeroViaCatastro";
	private static String ID_INPUT_MODAL_REF_CATASTRALES_PISO = "formInformacionCatastral:pisoCatastro";
	private static String ID_INPUT_MODAL_REF_CATASTRALES_PUERTA = "formInformacionCatastral:puertaCatastro";
	private static String ID_BOTON_MODAL_REF_CATASTRALES_BUSCAR = "formInformacionCatastral:btnBuscarCatastro";
	private static String ID_BOTON_MODAL_REF_CATASTRALES_ACEPTAR = "formInformacionCatastral:btnAceptarCatastro";
	
	public void setModalRefCatastrales_CodigoPostal(String codigoPostal) {
		rellenarCampo(ID_INPUT_MODAL_REF_CATASTRALES_COD_POSTAL, codigoPostal).sendKeys(Keys.TAB);
		waitForLoading(10000);
	}
	
	public void setModalRefCatastrales_Calle(String calle) {
		WebElement inputCalle = rellenarCampo(ID_INPUT_MODAL_REF_CATASTRALES_CALLE, calle);
		waitForLoading(10000);
		inputCalle.sendKeys(Keys.ENTER);
		inputCalle.sendKeys(Keys.TAB);
	}
	
	public void setModalRefCatastrales_Numero(String numero) {
		waitForLoading(10000);
		rellenarCampo(ID_INPUT_MODAL_REF_CATASTRALES_NUMERO, numero).sendKeys(Keys.TAB);
		waitForLoading(10000);
	}
	
	public void setModalRefCatastrales_Piso(String piso) {
		rellenarCampo(ID_INPUT_MODAL_REF_CATASTRALES_PISO, piso).sendKeys(Keys.TAB);
	}
	
	public void setModalRefCatastrales_Puerta(String puerta) {
		rellenarCampo(ID_INPUT_MODAL_REF_CATASTRALES_PUERTA, puerta).sendKeys(Keys.TAB);
	}
	
	public void pulsarModalRefCatastrales_Buscar() {
		pulsarBotonConEspera(ID_BOTON_MODAL_REF_CATASTRALES_BUSCAR);
	}
	
	public void pulsarModalRefCatastrales_Aceptar() {
		pulsarBotonConEspera(ID_BOTON_MODAL_REF_CATASTRALES_ACEPTAR);
	}

	/**
	 * Peritos AUTO
	 */
	
	public void irSeccionPeritacion() {
		pulsarBotonConEspera(ID_SECCION_PERITACION);
	}
	
	public void selecionarEstadoConversacionVehiculo(String estadoVehiculo) {
		seleccionarComboPorTextoVisible(ID_COMBO_ESTADO_GENERAL, estadoVehiculo);
	}
	
	public void setFechaPeritacion(String fecha) {
		rellenarCampo(ID_INPUT_FECHA_PERITACION, fecha).sendKeys(Keys.TAB);
	}
	
	public void selecionarTipoPerito(String tipoPerito) {
		seleccionarComboPorTextoVisible(ID_COMBO_TIPO_PERITO, tipoPerito);
	}
	
	public void setNombrePerito(String nombrePerito) {
		rellenarCampo(ID_INPUT_NOMBRE_PERITO, nombrePerito).sendKeys(Keys.TAB);
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
