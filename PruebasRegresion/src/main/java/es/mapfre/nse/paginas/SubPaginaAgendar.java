package es.mapfre.nse.paginas;

public class SubPaginaAgendar extends PaginaBase {

	private static String ID_BTN_CANCELAR = "formSGC:btnCancelarSGC";
	
	
	@Override
	protected String getXpathTitulo() {
		return "//*[@id=\"formSGC\"]/div[1]/span";
	}
	
	public void pulsarBotonCancelar() {
		try {
			waitForLoading(10000);
			pulsarBotonConEspera(ID_BTN_CANCELAR);
		} catch (Exception e) {
			// un reintento
			waitForLoading(10000);
			pulsarBotonConEspera(ID_BTN_CANCELAR);
		}
	}

	@Override
	protected String getIdTipoOperacion() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
