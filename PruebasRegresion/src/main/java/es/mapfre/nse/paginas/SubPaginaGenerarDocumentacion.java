package es.mapfre.nse.paginas;

import org.openqa.selenium.WebElement;

public class SubPaginaGenerarDocumentacion extends PaginaBase {

	/**
	 * GENERACION DE DOCUMENTACION 
	 */
	private static String XPATH_LABEL_NUMERO_REFERENCIA = "//*[@id=\"contenedorDocumentacion\"]/table/tbody/tr[1]/td[2]/span[2]";
	private static String ID_BTN_ACEPTAR_GENERAR_DOCUMENTACION = "formDocumentacion:btnAceptarNI";
	private static String ID_BTN_ACEPTAR_CTECNICOS ="formControlesTecnicosMP:btnAceptarNI";
	private static String ID_BTN_ACEPTAR_DOCUMENTACION_CORRECTA = "formDocumentacionCorrecta:btnAceptar";
	private static String ID_BTN_IMPRIMIR_DOCUMENTO = "formDocumentacionCorrecta:btnObtenerPdf";
	
	private static String ID_CHECK_DOCUMENTO_EMAIL = "formDocumentacion:tipoEnvioEmailCheck-0";
	private static String ID_CHECK_DOCUMENTO_EN_MANO = "formDocumentacion:tipoEnvioLocalCheck-0";
		
	@Override
	protected String getXpathTitulo() {
		return "";
	}
	
	
	public String obtenerNumeroReferencia() {
		WebElement labelReferencia = findElementByXpath(XPATH_LABEL_NUMERO_REFERENCIA);
		return labelReferencia.getText();
	}
	
	public void pulsarAceptar() {
		pulsarBotonConEspera(ID_BTN_ACEPTAR_GENERAR_DOCUMENTACION);
	}
	
	public void pulsarAceptarCTecnicos() {
		pulsarBotonConEspera(ID_BTN_ACEPTAR_CTECNICOS);
	}
	
	public void pulsarAceptarDocumentacionCorrecta() {
		pulsarBotonConEspera(ID_BTN_ACEPTAR_DOCUMENTACION_CORRECTA);
	}
	
	public void pulsarImprimirDocumento() {
		pulsarBotonConEspera(ID_BTN_IMPRIMIR_DOCUMENTO);
	}
	
	public void cerrarDocumentoPDFGenerado() {
		String[] handles = getDriver().getWindowHandles().toArray(new String[0]);
		getDriver().switchTo().window(handles[1]);		
		getDriver().close();
		getDriver().switchTo().window(handles[0]);
		
		waitForLoading(10000);
	}
	
	public boolean estarMarcadoCheckEnviarPorEmail() {
		WebElement checkEmail = findElementById(ID_CHECK_DOCUMENTO_EMAIL);
		return checkEmail.isSelected();
	}
	
	public void pulsarCheckEnviarPorEmail() {
		pulsarBotonConEspera(ID_CHECK_DOCUMENTO_EMAIL);
	}
	
	public boolean estarMarcadoCheckEntregarEnMano() {
		WebElement check = findElementById(ID_CHECK_DOCUMENTO_EN_MANO);
		return check.isSelected();
	}
	
	public void pulsarCheckEntregarEnMano() {
		pulsarBotonConEspera(ID_CHECK_DOCUMENTO_EN_MANO);
	}
	
	public static String ID_PARCIAL_BTN_GENERADOR_OFERTAS_NO = ":confirmarMostrarGeneradorOfertaDocCorrectaCN";
	
	public void pulsarBotonVerOfertasNO () {
		pulsarBotonIdParcial(ID_PARCIAL_BTN_GENERADOR_OFERTAS_NO);
	}


	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
