package es.mapfre.nse.paginas.contratacion;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaCoberturas extends PaginaBase {

	private static String XPATH_TITULO = "//*[@id=\"contenedorPrincipal\"]/div[3]/span[3]/table/tbody/tr/td[6]/span";
	
	public static String ID_BTN_CALCULAR = "formCoberturasGarantias:btnCalcular";
	public static String ID_BTN_SIGUIENTE = "formCoberturasGarantias:btnSiguiente";
	
	private static String ID_TAB_SECCION_DESCUENTOS = "formCoberturasGarantias:pestaniaBoniReca_lbl";
	
	private static String ID_COMBO_DESCUENTO_COMERCIAL_CAMPANA = "formCoberturasGarantias:combo_2";
	private static String ID_INPUT_DESCUENTO_COMERCIAL_CAMPANA = "formCoberturasGarantias:pctInicial_2";
	
	private static String ID_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO = "formCoberturasGarantias:combo_4";
	private static String ID_INPUT_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO = "formCoberturasGarantias:pctInicial_4";
	
	private static String ID_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_TEMPORAL = "formCoberturasGarantias:combo_5";
	private static String ID_INPUT_DESCUENTO_TECNICO_SUBSCRIPCION_TEMPORAL = "formCoberturasGarantias:pctInicial_5";
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}

	public void pulsarCalcular() {
		pulsarBotonConEspera(ID_BTN_CALCULAR);	
	}
	
	public void irSeccionDescuentos() {
		pulsarBotonConEspera(ID_TAB_SECCION_DESCUENTOS);	
	}
	
	public void setOpcionDescuentoComercialCampana(int indiceCombo) {
		seleccionarIndiceCombo(ID_COMBO_DESCUENTO_COMERCIAL_CAMPANA, indiceCombo);
	}
	
	public void setDescuentoComercialCampana(String porcentajeInicial) {
		rellenarCampo(ID_INPUT_DESCUENTO_COMERCIAL_CAMPANA, porcentajeInicial);
	}
	
	public void setOpcionTecnicoSubscripcionFijo(int indiceCombo) {
		waitForCondition(10000, () -> existeElemento(ID_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO)); 
		seleccionarIndiceCombo(ID_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO, indiceCombo);
	}
	
	public void setDescuentoTecnicoSubscripcionFijo(String porcentajeInicial) {
		rellenarCampo(ID_INPUT_DESCUENTO_TECNICO_SUBSCRIPCION_FIJO, porcentajeInicial);
	}
	
	public void setOpcionDescuentoTecnicoSubscripcionTemporal(int indiceCombo) {
		waitForCondition(10000, () -> existeElemento(ID_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_TEMPORAL));
		seleccionarIndiceCombo(ID_COMBO_DESCUENTO_TECNICO_SUBSCRIPCION_TEMPORAL, indiceCombo);
	}
	
	public void setDescuentoTecnicoSubscripcionTemporal(String porcentajeInicial) {
		rellenarCampo(ID_INPUT_DESCUENTO_TECNICO_SUBSCRIPCION_TEMPORAL, porcentajeInicial);
	}
	
	public PaginaPagos pulsarSiguiente() {
		try {
			waitForLoading(10000);
			pulsarBotonConEspera(ID_BTN_SIGUIENTE);
		} catch (Exception e) {
			//hacer un reintento
			waitForLoading(10000);
			pulsarBotonConEspera(ID_BTN_SIGUIENTE);
		}		
		
		PaginaPagos paginaSiguiente = new PaginaPagos();
		paginaSiguiente.init(getDriver());
		
		paginaSiguiente.waitForLoad();
		
		return paginaSiguiente;
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
