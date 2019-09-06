package es.mapfre.nse.pruebas.total;

import org.junit.Test;

import es.mapfre.nse.paginas.gestion.operaciones.GenerarDocumentoBaseTest;

public class CP104_GenerarDocumentoProyectoTest extends GenerarDocumentoBaseTest {

	public static String CODIGO_PRUEBA = "CP103";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void generarDocumentoProyectoTest() {
		generarDocumentoTest("PROYECTO");
	}
	
}
