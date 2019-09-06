package es.mapfre.nse.paginas.subscripcion.operaciones;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaBusquedaSubscripciones extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formGestionSuscripciones\"]/div[1]/div/div[3]/span";	
	private static String ID_INPUT_FECHA_MECANIZACION_DESDE = "formGestionSuscripciones:fechaMecaDesdeInputDate";
	private static String ID_INPUT_FECHA_MECANIZACION_HASTA = "formGestionSuscripciones:fechaMecaHastaInputDate";
	private static String ID_BOTON_BUSCAR = "formGestionSuscripciones:btnBuscar";
	private static String ID_BOTON_CONTROLES_TECNICOS = "formGestionSuscripciones:btnControlesTec";
	private static String ID_BOTON_ACEPTAR_CONTROLES_TECNICOS = "formControlesTecnicos:btnAceptarNI";	
	private static String ID_BOTON_VER_HISTORIAL = "formGestionSuscripciones:btnVerHistorial";
	private static String ID_BOTON_ACEPTAR_VER_HISTORIAL = "formHistorialSuscripcion:btnAceptarNI";
	private static String ID_BOTON_DESASIGNAR = "formGestionSuscripciones:btnDesasignar";
	private static String ID_BOTON_AUTORIZACION_DIRECTA = "formGestionSuscripciones:btnSusDirecta";
	private static String ID_COMBO_STADO = "formGestionSuscripciones:estadoSuscripcion";
	private static String ID_INPUT_TEXTO = "formSuscripcionDirecta:textoSuscripcionTextArea_ifr";
	private static String ID_BOTON_AUTORIZACION_DIRECTA_INTERNA = "formSuscripcionDirecta:btnAutorizarSuscripcionDirecta";
	private static String ID_BOTON_AUTORIZACION_COMANDO_DIRECTO = "formGestionSuscripciones:j_id2471:panelAvisoAutorizacionSolicitudComandoEstAS";
	private static String ID_BOTON_AUTORIZACION_COMANDO_CONSULTA = "formObjAseg:j_id1061:panelAvisoAutorizacionSolicitudComandoEstAS";
	private static String ID_BOTON_AUTORIZACION_CONSULTA = "formGestionSuscripciones:btnSusConsulta";
	private static String ID_BOTON_AUTORIZACION_MODIFICACION = "formGestionSuscripciones:btnSusModificacion";
	private static String ID_BOTON_OK_POPUP = "PopUpForm:modalAvisosKoButton";
	private static String ID_BOTON_AUTORIZACION_SIGUIENTE = "formDG:btnSiguiente";
	private static String ID_BOTON_MODIFICACION_SIGUIENTE = "formProductos:btnSiguiente";
	//*[@id="formProductos:btnSiguiente"]
	private static String ID_BOTON_AUTORIZACION_CONSULTA_ACEPTAR = "formObjAseg:btnSuscribir";
	
	
	private static String ID_LABEL_NUM_RESULTADOS = "tSuscripcionesTotalRecordsNumber";
	private static String ID_RADIO_RESULTADO_BASE = "tSuscripciones_radio_grupo_-1_fila_";

	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	public void setFechaMecanizacionDesde(String fecha) {
		waitForLoading(10000);
		rellenarCampo(ID_INPUT_FECHA_MECANIZACION_DESDE, fecha);
	}

	public void setFechaMecanizacionHasta(String fecha) {
		waitForLoading(10000);
		rellenarCampo(ID_INPUT_FECHA_MECANIZACION_HASTA, fecha);
	}
	
	public void pulsarBotonBuscar() {
		pulsarBotonConEspera(ID_BOTON_BUSCAR);
	}

	public int getResultados() {
		return Integer.parseInt(findElementById(ID_LABEL_NUM_RESULTADOS).getText());
	}
	
	public void pulsarBotonControlesTecnicos() {
		pulsarBotonConEspera(ID_BOTON_CONTROLES_TECNICOS);
	}
	
	public void pulsarBotonAceptarControlesTecnicos() {
		pulsarBotonConEspera(ID_BOTON_ACEPTAR_CONTROLES_TECNICOS);
	}
	
	public void pulsarBotonVerHistorial() {
		pulsarBotonConEspera(ID_BOTON_VER_HISTORIAL);
	}
	
	public void pulsarBotonAceptarVerHistorial() {
		pulsarBotonConEspera(ID_BOTON_ACEPTAR_VER_HISTORIAL);
	}

	public void seleccionarResultadoBusqueda(int i) {
		findElementById(ID_RADIO_RESULTADO_BASE + i).click();
		waitForLoading(10000);
	}
	
	public void pulsarBotonDesasignar() {
		pulsarBotonConEspera(ID_BOTON_DESASIGNAR);
	}
	
	public void pulsarBotonAutorizarDirecta() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_DIRECTA);
	}
	
	public void setTextoAutorizacion(String textoSuscripcion) {
		waitForLoading(10000);
		
		WebElement textoSus = findElementById(ID_INPUT_TEXTO);
		textoSus.sendKeys(textoSuscripcion);
		textoSus.sendKeys(Keys.TAB);		
		
		waitForLoading(10000);
	}
	
	public void pulsarBotonAutorizarDirectaInterna() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_DIRECTA_INTERNA);
	}
	
	public void pulsarBotonComandoDirecto() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_COMANDO_DIRECTO);
	}
	
	public void pulsarBotonComandoConsulta() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_COMANDO_CONSULTA);
	}
	
	public void seleccionarValorCombo(String estado) {
		Select dropdown = new Select (findElementById(ID_COMBO_STADO));
		dropdown.selectByValue(estado);
	}

	public void pulsarBotonAutorizarConsulta() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_CONSULTA);
	}
	
	public void pulsarBotonModificarConsulta() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_MODIFICACION);
	}
	
	public void pulsarBotonOkPopup() {
		pulsarBotonConEspera(ID_BOTON_OK_POPUP);
	}
	
	public void pulsarBotonAutorizarSiguiente() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_SIGUIENTE);
	}
	
	public void pulsarBotonModificarSiguiente() {
		pulsarBotonConEspera(ID_BOTON_MODIFICACION_SIGUIENTE);
	}
	
	public void pulsarBotonAutorizarConsultaAceptar() {
		pulsarBotonConEspera(ID_BOTON_AUTORIZACION_CONSULTA_ACEPTAR);
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
}
