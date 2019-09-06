package es.mapfre.nse.pruebas.regresion.gestion.operaciones;

import org.junit.Test;

import es.mapfre.nse.paginas.gestion.operaciones.GenerarDocumentoBaseTest;

public class CP106_GenerarDocumentoPropuestaTest extends GenerarDocumentoBaseTest {

	public static String CODIGO_PRUEBA = "CP106";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void generarDocumentoPropuestaTest() {
		generarDocumentoTest("PROPUESTA");
	}
	
}
