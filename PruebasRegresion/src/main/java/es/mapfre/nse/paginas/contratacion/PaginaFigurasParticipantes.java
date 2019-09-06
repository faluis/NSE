package es.mapfre.nse.paginas.contratacion;

import org.openqa.selenium.Keys;

import es.mapfre.nse.paginas.PaginaBase;

public class PaginaFigurasParticipantes extends PaginaBase {
	
	private static String XPATH_TITULO = "//*[@id=\"formFiguras\"]/div[3]/span[3]/table/tbody/tr/td[5]/span";	
	private static String ID_BTN_SIGUIENTE = "formFiguras:btnSiguiente";		
	private static String ID_BTN_GESTION_CLIENTES = "formFiguras:btnGestionClientesTomadorDP"; //*[@id="formFiguras:btnGestionClientesTomadorDP"]
	private static String ID_PANEL_TE_CUIDAMOS = "formFiguras:panelTeCuidamosCDiv";
	private static String ID_PANEL_TE_CUIDAMOS_BTN_NO = "formFiguras:panelTeCuidamosComandoEstCN";
	
	private static String ID_COMBO_CODIGO_LICENCIA_CONDUCIR = "formFiguras:codLicenciaPestTomador";
	private static String ID_INPUT_FECHA_EXPEDICION_LICENCIA_CONDUCIR = "formFiguras:fechaExpPestTomadorInputDate";
	private static String ID_BTN_HISTORIAL_SINIESTRALIDAD = "formFiguras:btnBuscarPolizaPestTomador";
	
	/**
	 * Formulario de gestion de clientes	  
	 */
	private static String ID_GESTION_CLIENTES_INPUT_NOMBRE        = "formClientes:nombre";
	private static String ID_GESTION_CLIENTES_INPUT_APELLIDO_1    = "formClientes:primerApellido";
	private static String ID_GESTION_CLIENTES_INPUT_APELLIDO_2    = "formClientes:segundoApellido";
	private static String ID_GESTION_CLIENTES_INPUT_NUM_DOC       = "formClientes:numDoc";	
	private static String ID_GESTION_CLIENTES_BTN_BUSCAR          = "formClientes:btnBuscar";
	private static String ID_GESTION_CLIENTES_BTN_SELECCIONAR     = "formClientes:btnAceptar";
	
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
	
	@Override
	protected String getXpathTitulo() {
		return XPATH_TITULO;
	}
	
	public void pulsarGestionClientes() {
		pulsarBotonConEspera(ID_BTN_GESTION_CLIENTES);		
	}
	
	public PaginaCoberturas pulsarSiguiente() {
		if(estadoCargandoActivado())
			waitForCondition(10000);
		
		pulsarBotonConEspera(ID_BTN_SIGUIENTE);	
		
		try {
			// Comprobar si se ha abierto el pop up para inscribir al asegurado en el programa de puntos
			if(!findElementById(ID_PANEL_TE_CUIDAMOS).getCssValue("top").equals("0px")) {
				// pulsar la opcion de no
				findElementById(ID_PANEL_TE_CUIDAMOS_BTN_NO).click();
			}
		} catch (Exception e) {
			System.out.println("INFO - NO SE HA ENCONTRADO EL DIALOGO DE TE CUIDAMOS");
		}
		
		
		PaginaCoberturas paginaSiguiente = new PaginaCoberturas();
		paginaSiguiente.init(getDriver());
		
		paginaSiguiente.waitForLoad();
		
		return paginaSiguiente;
	}
	
	public void pulsarConsultarHistorialSiniestralidad() {			
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

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
}