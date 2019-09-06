package es.mapfre.nse.paginas.calculorapido;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import es.mapfre.nse.paginas.PaginaBase;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;

public class PaginaCapturaInformacion extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formCapturaInfCR\"]/div[3]/span[3]/table/tbody/tr/td/span";
	
	private static String ID_BTN_GESTION_CLIENTES          = "formCapturaInfCR:btnGestionClientes";	
	private static String ID_INPUT_CODIGO_POSTAL           = "formCapturaInfCR:COD_POSTAL";
	private static String ID_INPUT_ANO_CONSTRUCCION        = "formCapturaInfCR:ANO_CONSTRUCCION";
	private static String ID_INPUT_M2_VIVIENDA             = "formCapturaInfCR:METROS_CONST_VIVIENDA";
	private static String ID_BTN_ACEPTAR_INF_OBJ_ASEGURADO = "formCapturaInfCR:btnAceptarCR";
	
	private static String ID_BTN_CALCULAR = "formCapturaInfCR:btnCalcularCR";
	private static String ID_BTN_GENERAR_PROYECTO = "formCapturaInfCR:btnProyecto";
	
	/**
	 * Descuentos/Recargos
	 */
	private static String ID_IMG_DESPLEGAR_BONIFICACIONES                           = "formCapturaInfCR:btnPanelBonificaciones";
	private static String ID_CMB_DESCUENTOS_COMERCIALES_CAMPANAS                    = "formCapturaInfCR:combo_2";
	private static String ID_CMB_DESCUENTOS_TECNICO_SUBSCRIPCION_FIJO               = "formCapturaInfCR:combo_4";
	private static String ID_INPUT_DESCUENTOS_TECNICO_SUBSCRIPCION_FIJO_PCT_INICIAL = "formCapturaInfCR:pctInicial_4";
	
	/**
	 * Pagos
	 */
	private static String ID_RADIO_PAGO_ANUAL = "formCapturaInfCR:planPagoSelecionado2:0";
	
	private static String ID_CHECK_REUTILIZAR_DATOS = "formCapturaInfCR:checkReutilizar";
	
	/**
	 * Formulario de gestion de clientes	  
	 */
	private static String ID_GESTION_CLIENTES_INPUT_NOMBRE        = "formClientes:nombre";
	private static String ID_GESTION_CLIENTES_INPUT_APELLIDO_1    = "formClientes:primerApellido";
	private static String ID_GESTION_CLIENTES_INPUT_APELLIDO_2    = "formClientes:segundoApellido";
	private static String ID_GESTION_CLIENTES_INPUT_NUM_DOC       = "formClientes:numDoc";	
	private static String ID_GESTION_CLIENTES_BTN_BUSCAR          = "formClientes:btnBuscar";
	private static String ID_GESTION_CLIENTES_BTN_SELECCIONAR     = "formClientes:btnAceptar";
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	/**
	 * GESTION DE CLIENTES
	 */
	
	public void pulsarGestionClientes() {
		if(estadoCargandoActivado()) waitForCondition(10000);
		pulsarBotonConEspera(ID_BTN_GESTION_CLIENTES);	
	}
	
	public void setGestionClientesNombre(String nombre) {
		findElementById(ID_GESTION_CLIENTES_INPUT_NOMBRE).sendKeys(nombre);
	}
	
	public void setGestionClientesApelido1(String primerApellido) {
		findElementById(ID_GESTION_CLIENTES_INPUT_APELLIDO_1).sendKeys(primerApellido);
	}
	
	public void setGestionClientesApelido2(String segundoApellido) {
		findElementById(ID_GESTION_CLIENTES_INPUT_APELLIDO_2).sendKeys(segundoApellido);
	}
	
	public void setGestionClientesNumeroDocumento(String numeroDocumento) {
		findElementById(ID_GESTION_CLIENTES_INPUT_NUM_DOC).sendKeys(numeroDocumento);
	}
	
	public void pulsarGestionClientesBuscar() {
		pulsarBotonConEspera(ID_GESTION_CLIENTES_BTN_BUSCAR);		
	}
	
	public void pulsarGestionClientesSeleccionar() {
		pulsarBotonConEspera(ID_GESTION_CLIENTES_BTN_SELECCIONAR);		
	}
	
	/**
	 * INFORMACION OBJETO ASEGURADO
	 */
	
	public void setCodigoPostal(String codigoPostal) {
		waitForLoading(10000);
		
		WebElement inputCodigoPostal = findElementById(ID_INPUT_CODIGO_POSTAL);
		inputCodigoPostal.sendKeys(codigoPostal);
		inputCodigoPostal.sendKeys(Keys.TAB);		
		
		waitForLoading(10000);
	}
	
	public void setAnoConstruccion(String ano) {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		WebElement inputCodigoPostal = findElementById(ID_INPUT_ANO_CONSTRUCCION);
		inputCodigoPostal.sendKeys(ano);
		inputCodigoPostal.sendKeys(Keys.TAB);		
	}
	
	public void setM2Vivienda(String m2Vivienda) {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		WebElement inputCodigoPostal = findElementById(ID_INPUT_M2_VIVIENDA);
		inputCodigoPostal.sendKeys(m2Vivienda);
		inputCodigoPostal.sendKeys(Keys.TAB);		
	}
	
	public void pulsarAceptarInformacionObjetoAsegurado() {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		pulsarBotonConEspera(ID_BTN_ACEPTAR_INF_OBJ_ASEGURADO);		
	}
	
	public void pulsarDesplegarSeccionBonificaciones() {
		mostrarElementoPorId(ID_IMG_DESPLEGAR_BONIFICACIONES);
		pulsarBotonConEspera(ID_IMG_DESPLEGAR_BONIFICACIONES);
	}
	
	public void seleccionarDescuentoComercial(int indiceDescuento) {
		seleccionarIndiceCombo(ID_CMB_DESCUENTOS_COMERCIALES_CAMPANAS, indiceDescuento);
	}
	
	public void seleccionarDescuentoTecnicoSubscripcionFijo(int indiceDescuento) {
		seleccionarIndiceCombo(ID_CMB_DESCUENTOS_TECNICO_SUBSCRIPCION_FIJO, indiceDescuento);
	}
	
	public void setDescuentoTecnicoSubscripcionFijoPorcentajeInicial(String porcentajeInicial) {
		rellenarCampo(ID_INPUT_DESCUENTOS_TECNICO_SUBSCRIPCION_FIJO_PCT_INICIAL, porcentajeInicial);
	}
	
	public void pulsarCalcular() {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		pulsarBotonConEspera(ID_BTN_CALCULAR);		
	}
	
	public void seleccionarPagoAnual() {
		WebElement radio = findElementById(ID_RADIO_PAGO_ANUAL);
		radio.click();
		waitForLoading(10000);
	}
	
	public void seleccionarCheckReutilizarDatos() {
		WebElement check = findElementById(ID_CHECK_REUTILIZAR_DATOS);
		check.click();
	}
	
	public SubPaginaGenerarDocumentacion pulsarGenerarProyecto() {
		pulsarBotonConEspera(ID_BTN_GENERAR_PROYECTO);	
		
		return getSubPaginaGenerarDocumentacion();
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
