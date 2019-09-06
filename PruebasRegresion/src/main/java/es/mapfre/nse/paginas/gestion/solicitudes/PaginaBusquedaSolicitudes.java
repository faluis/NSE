	package es.mapfre.nse.paginas.gestion.solicitudes;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaBusquedaSolicitudes extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"formGestion\"]/div[1]/div/div[3]/span";
	private static String ID_INPUT_FECHA_MECANIZACION_DESDE = "formGestion:fechaMecaDesdeInputDate";
	private static String ID_INPUT_FECHA_MECANIZACION_HASTA = "formGestion:fechaMecaHastaInputDate";
	
	private static String ID_BOTON_BUSCAR = "formGestion:btnBuscar";
	private static String ID_BOTON_CONTROLES_TECNICOS = "formGestion:btnControlesTec";
	private static String ID_BOTON_ACEPTAR_CONTROLES_TECNICOS = "formControlesTecnicos:btnAceptarNI";	
	private static String ID_BOTON_VER_HISTORIAL = "formGestion:btnVerHistorial";
	private static String ID_BOTON_ACEPTAR_VER_HISTORIAL = "formHistorialSuscripcion:btnAceptarNI";
	
	private static String ID_LABEL_NUM_RESULTADOS = "tSolicitudesTotalRecordsNumber";
	
	private static String ID_RADIO_RESULTADO_BASE = "tSolicitudes_radio_grupo_-1_fila_";
	
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
	
	public int getResultados() {
		return Integer.parseInt(findElementById(ID_LABEL_NUM_RESULTADOS).getText());
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
