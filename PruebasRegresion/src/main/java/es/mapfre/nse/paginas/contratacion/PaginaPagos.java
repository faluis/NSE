package es.mapfre.nse.paginas.contratacion;

import es.mapfre.nse.paginas.PaginaBase;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;

public class PaginaPagos extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formPago\"]/div[3]/span[3]/table/tbody/tr/td[7]/span";
	
	private static String ID_COMBO_SELECCION_IBAN = "formPago:seleccionIBAN";
	
	private static String ID_BTN_GENERAR_PROYECTO = "formPago:btnAceptarE";
	
	private static String ID_PARCIAL_BOTON_SOLICITAR_AUTORIZACION_SI = "panelSolicitarAutorizacionSinComando";
	private static String ID_BOTON_TEXTO_AUTORIZACION_LISTA_DESORDENADA = "formSuscripcion:textoSuscripcionTextArea_insertunorderedlist";
	private static String ID_BOTON_TEXTO_AUTORIZACION_ACEPTAR = "formSuscripcion:btnAceptarSuscripcion";
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}

	public void seleccionarCuenta(int indiceCuenta) {
		seleccionarIndiceCombo(ID_COMBO_SELECCION_IBAN, indiceCuenta);
//		WebElement comboCuenta = findElementById(ID_COMBO_SELECCION_IBAN);
//		for (int i = 0; i < indiceCuenta; i++) comboCuenta.sendKeys(Keys.DOWN);
	}
	
	public SubPaginaGenerarDocumentacion pulsarGenerarProyecto() {
		pulsarBotonConEspera(ID_BTN_GENERAR_PROYECTO);
		return getSubPaginaGenerarDocumentacion();
	}
	
	public void pulsarDialogoSolicitarAutorizacionSi() {
		pulsarBotonIdParcial(ID_PARCIAL_BOTON_SOLICITAR_AUTORIZACION_SI);		
	}
	
	public void pulsarTextoAutorizacionListaDesordenada() {
		pulsarBotonConEspera(ID_BOTON_TEXTO_AUTORIZACION_LISTA_DESORDENADA);
	}
	
	public void pulsarTextoAutorizacionAceptar() {
		pulsarBotonConEspera(ID_BOTON_TEXTO_AUTORIZACION_ACEPTAR);
	}
	
	private static String ID_PANEL_TE_CUIDAMOS = "formPago:panelTeCuidamosCDiv";
	private static String ID_PANEL_TE_CUIDAMOS_BTN_NO = "formPago:panelTeCuidamosComandoEstCN";
	
	public void cerrarPanelMapfreTeCuidamos() {
		try {
			// Comprobar si se ha abierto el pop up para inscribir al asegurado en el programa de puntos
			if(!findElementById(ID_PANEL_TE_CUIDAMOS).getCssValue("top").equals("0px")) {
				// pulsar la opcion de no
				findElementById(ID_PANEL_TE_CUIDAMOS_BTN_NO).click();
			}
		} catch (Exception e) {
			System.out.println("INFO - NO SE HA ENCONTRADO EL DIALOGO DE TE CUIDAMOS");
		}
		waitForLoading(10000);
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
}
