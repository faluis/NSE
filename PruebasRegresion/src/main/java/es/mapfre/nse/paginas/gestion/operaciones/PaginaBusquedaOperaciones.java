package es.mapfre.nse.paginas.gestion.operaciones;

//import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Keys;

import es.mapfre.nse.paginas.PaginaBase;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;

public class PaginaBusquedaOperaciones extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formGestion\"]/div[1]/div/div[3]/span";	
	private static String ID_TIPO_OPERACION = "formGestion:operARealizar";	
	
	private static String ID_INPUT_FECHA_MECANIZACION_DESDE = "formGestion:fechaMecaDesdeInputDate";
	private static String ID_INPUT_FECHA_MECANIZACION_HASTA = "formGestion:fechaMecaHastaInputDate";
	
	private static String ID_INPUT_FECHA_MECANIZACION_DESDE_AVANZADA="formBusqAmpliada:fechaMecaDesdeInputDate";
	private static String ID_INPUT_FECHA_MECANIZACION_HASTA_AVANZADA="formBusqAmpliada:fechaMecaHastaInputDate";
	
	private static String ID_INPUT_DOCUMENTO_TOMADOR = "formGestion:docClienteTomador";

	private static String ID_BTN_BUSCAR = "formGestion:btnBuscar";
	private static String ID_BTN_BUSQUEDA_AVANZADA = "formGestion:btnBusquedaAmp";
	private static String ID_BTN_ELIMINAR = "formGestion:btnEliminar";
	
	private static String ID_PARCIAL_DIALOGO_CONFIRMAR_ELIMINAR_S = ":panelAvisoEliminarComandoEstAS";
	private static String ID_PARCIAL_DIALOGO_CONFIRMAR_ELIMINAR_N = ":panelAvisoEliminarComandoEstAN";
	
	private static String ID_LBL_RESULTADOS = "tOperacionesTotalRecords";
	
	private String ID_RADIO_RESULTADO_BASE = "tOperaciones_radio_grupo_-1_fila_";
	private String ID_BTN_GESTION_DOCUMENTOS = "formGestion:btnGDocumentos";
	private String ID_BTN_GESTION_DOCUMENTOS_MP = "formGestion:btnGDocumentosMP";
	
	private String ID_BTN_DUPLICAR = "formGestion:btnCopiar";
	private String ID_BTN_DUPLICAR_MP = "formGestion:btnCopiarMP";
	
	private String ID_BTN_RESTABLECER = "formGestion:btnRestablecer";
	private String ID_PARCIAL_BTN_RESTABLECER_CONFIRMAR_S = ":panelAvisoRestablecerComandoEstAS";
	private String ID_PARCIAL_BTN_RESTABLECER_CONFIRMAR_N = ":panelAvisoRestablecerCN";
	
	private String ID_BTN_RESTABLECER_OK = "formGestion:panelRestablecidaOKcerrarA";
	
	private String ID_BTN_CONSULTAR = "formGestion:btnConsultar";

		
	/**
	 * DIALOGO BUSQUEDA AVANZADA 
	 */
	private static String ID_COMBO_BUSQUEDA_AVANZADA_TIPO_OPERACION = "formBusqAmpliada:tipOperacion";
	private static String ID_COMBO_BUSQUEDA_AVANZADA_ESTADO_OPERACION = "formBusqAmpliada:estado";
	private static String ID_BTN_BUSQUEDA_AVANZADA_BUSCAR = "formBusqAmpliada:btnBuscar";
	private static String ID_BTN_BUSQUEDA_AVANZADA_OPERACIONES = "formBusqAmpliada:operaciones";
	
	public void setFechaMecanizacionDesde(String fechaDesde) {
		rellenarCampo(ID_INPUT_FECHA_MECANIZACION_DESDE, fechaDesde).sendKeys(Keys.TAB);
		waitForLoading(10000);
	}
	
	public void setFechaMecanizacionHasta(String fechaHasta) {
		rellenarCampo(ID_INPUT_FECHA_MECANIZACION_HASTA, fechaHasta);
	}
	
	public void setFechaMecanizacionDesdeAvanzada(String fechaDesdeAvanzada) {
		rellenarCampo(ID_INPUT_FECHA_MECANIZACION_DESDE_AVANZADA, fechaDesdeAvanzada).sendKeys(Keys.TAB);
		waitForLoading(10000);
	}
	
	public void setFechaMecanizacionHastaAvanzada(String fechaHastaAvanzada) {
		rellenarCampo(ID_INPUT_FECHA_MECANIZACION_HASTA_AVANZADA, fechaHastaAvanzada);
	}
	
	public void setDocumentoTomador(String documentoTomador) {
		rellenarCampo(ID_INPUT_DOCUMENTO_TOMADOR, documentoTomador);
	}
	
	public void pulsarBotonBuscar() {
		pulsarBotonConEspera(ID_BTN_BUSCAR);
	}
		
	public void pulsarBotonBusquedaAvanzada() {
		pulsarBotonConEspera(ID_BTN_BUSQUEDA_AVANZADA);
	}
	
	public void pulsarBotonEliminar() {
		pulsarBotonConEspera(ID_BTN_ELIMINAR);
	}
	
	public void pulsarBotonConfirmarEliminar(boolean eliminar) {
		if(eliminar) 
			pulsarBotonIdParcial(ID_PARCIAL_DIALOGO_CONFIRMAR_ELIMINAR_S);
		else
			pulsarBotonIdParcial(ID_PARCIAL_DIALOGO_CONFIRMAR_ELIMINAR_N);
	}
	
	public int getNumeroResultados() {
		String txtLblResultados = findElementById(ID_LBL_RESULTADOS).getText();
		String[] split = txtLblResultados.split(":");
		return Integer.parseInt(split[1].trim());
	}
	
	public void busquedaAvanzada_SeleccionarTipoOperacion(String tipoOperacion) {		
		waitForCondition(10000, () -> recuperarCombo(ID_COMBO_BUSQUEDA_AVANZADA_TIPO_OPERACION));
		seleccionarComboPorTextoVisible(ID_COMBO_BUSQUEDA_AVANZADA_TIPO_OPERACION, tipoOperacion);
		
		waitForLoading(10000);
	}
	
	public void busquedaAvanzada_EstadoOperacion(String estadoOperacion) {		
		waitForCondition(10000, () -> recuperarCombo(ID_COMBO_BUSQUEDA_AVANZADA_ESTADO_OPERACION));
		seleccionarComboPorTextoVisible(ID_COMBO_BUSQUEDA_AVANZADA_ESTADO_OPERACION, estadoOperacion);
		
		waitForLoading(10000);
	}
	
	
	public void busquedaAvanzada_PropietarioOperacion(String propietarioOperacion) {		
		waitForCondition(10000, () -> recuperarCombo(ID_BTN_BUSQUEDA_AVANZADA_OPERACIONES));
		seleccionarComboPorTextoVisible(ID_BTN_BUSQUEDA_AVANZADA_OPERACIONES, propietarioOperacion);
		
		waitForLoading(10000);
	}
	
	
	private Boolean recuperarCombo(String idCombo) {
		try {
			findElementById(idCombo);	
		} catch (Exception e) {
			return false;
		}		
		return true;
	}

	public void busquedaAvanzada_pulsarBotonBuscar() {
		pulsarBotonConEspera(ID_BTN_BUSQUEDA_AVANZADA_BUSCAR);
	}
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	@Override
	protected String getIdTipoOperacion() {
		return ID_TIPO_OPERACION;
	}
	
	public void seleccionarResultadoBusqueda(int i) {
		findElementById(ID_RADIO_RESULTADO_BASE + i).click();
		waitForLoading(10000);
	}
	
//	public SubPaginaGenerarDocumentacion pulsarGestionDocumentos() {
//		pulsarBotonConEspera(ID_BTN_GESTION_DOCUMENTOS);
//		return getSubPaginaGenerarDocumentacion();
//	}
//	
//	public SubPaginaGenerarDocumentacion pulsarGestionDocumentosMP() {
//		pulsarBotonConEspera(ID_BTN_GESTION_DOCUMENTOS_MP);
//		return getSubPaginaGenerarDocumentacion();
//	}
//	
	public void pulsarBotonDuplicar() {
		pulsarBotonConEspera(ID_BTN_DUPLICAR);
	}
	
	public void pulsarBotonDuplicarMultiPrecio() {
		pulsarBotonConEspera(ID_BTN_DUPLICAR_MP);
	}

	public void pulsarBotonRestablecer(boolean confirmar) {
		pulsarBotonConEspera(ID_BTN_RESTABLECER);
		
		if(confirmar)
			pulsarBotonIdParcial(ID_PARCIAL_BTN_RESTABLECER_CONFIRMAR_S);
		else
			pulsarBotonIdParcial(ID_PARCIAL_BTN_RESTABLECER_CONFIRMAR_N);
	}
	
	public void pulsarBotonRestablerOk() {
		pulsarBotonConEspera(ID_BTN_RESTABLECER_OK);
	}
	
	public void pulsarBotonConsultar() {
		pulsarBotonConEspera(ID_BTN_CONSULTAR);
	}

	public String getOper() {
		return findElementById(getIdTipoOperacion()).getText();
	}
	
	public SubPaginaGenerarDocumentacion pulsarBotonDocumentacion() {

		if(getOper()=="") {
			pulsarBotonConEspera(ID_BTN_GESTION_DOCUMENTOS);
			return getSubPaginaGenerarDocumentacion();}
		else {
			pulsarBotonConEspera(ID_BTN_GESTION_DOCUMENTOS_MP);
			return getSubPaginaGenerarDocumentacion();}
	}

}

