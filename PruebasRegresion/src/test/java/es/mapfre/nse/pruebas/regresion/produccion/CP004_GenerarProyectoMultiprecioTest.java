package es.mapfre.nse.pruebas.regresion.produccion;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import es.mapfre.nse.paginas.MenuPrincipal;
import es.mapfre.nse.paginas.NseBaseTest;
import es.mapfre.nse.paginas.SubPaginaGenerarDocumentacion;
import es.mapfre.nse.paginas.multiprecio.PaginaOfertaMultiprecio;

/**
 * Proyecto multiprecio con cliente existente y descuento campaña comercial
 */
public class CP004_GenerarProyectoMultiprecioTest extends NseBaseTest{
	
	public static String CODIGO_PRUEBA = "CP004";
	
	@Override
	protected String getCodigoPrueba() {
		return CODIGO_PRUEBA;
	}
	
	@Test
	public void generarOfertaMultiprecioAuto() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaOfertaMultiprecio paginaOfertaMultiprecio = menu.irOpcion_OfertaMultiprecio();
    	assertTrue(paginaOfertaMultiprecio.getTitulo().equals("Oferta multiprecio"));
    	    	
    	paginaOfertaMultiprecio.pulsarBotonOfertaAuto();
    	
    	rellenarInformacionObjetoAseguradoAuto(paginaOfertaMultiprecio);
    	rellenarInformacionTomadorAuto(paginaOfertaMultiprecio);
    	
    	paginaOfertaMultiprecio.irSeccionDescuentos();
    	paginaOfertaMultiprecio.seleccionarDescuentoTecnicoSubscripcionFijo(1);
    	paginaOfertaMultiprecio.setDescuentoTecnicoSubscripcionFijo("5");
    	
    	paginaOfertaMultiprecio.pulsarCalcular();
    	
    	SubPaginaGenerarDocumentacion subPaginaDocumentacion = paginaOfertaMultiprecio.pulsarGenerarProyecto();
    	
    	//subPaginaDocumentacion.pulsarAceptarCTecnicos();
    	
    	// cancelar agendar
    	paginaOfertaMultiprecio.getSubPaginaAgendar().pulsarBotonCancelar();
    	
    	subPaginaDocumentacion.pulsarAceptar();    	
    	
    	subPaginaDocumentacion.pulsarImprimirDocumento();
    	subPaginaDocumentacion.cerrarDocumentoPDFGenerado();
    	
    	subPaginaDocumentacion.pulsarAceptarDocumentacionCorrecta();
	}
	
	@Test
	public void generarProyectoMultiprecioHogar() {
		MenuPrincipal menu = new MenuPrincipal();
    	menu.init(getDriver());
    	
    	PaginaOfertaMultiprecio paginaOfertaMultiprecio = menu.irOpcion_OfertaMultiprecio();
    	assertTrue(paginaOfertaMultiprecio.getTitulo().equals("Oferta multiprecio"));
    	    	
    	paginaOfertaMultiprecio.pulsarBotonOfertaHogar();
    	
    	rellenarInformacionObjetoAsegurado(paginaOfertaMultiprecio);
    	rellenarInformacionTomador(paginaOfertaMultiprecio);
    	
    	paginaOfertaMultiprecio.irSeccionDescuentos();
    	paginaOfertaMultiprecio.seleccionarDescuentoTecnicoSubscripcionFijo(1);
    	paginaOfertaMultiprecio.setDescuentoTecnicoSubscripcionFijo("5");
    	
    	paginaOfertaMultiprecio.pulsarCalcular();
    	
    	SubPaginaGenerarDocumentacion subPaginaDocumentacion = paginaOfertaMultiprecio.pulsarGenerarProyecto();
    	
    	//subPaginaDocumentacion.pulsarAceptarCTecnicos();
    	
    	// cancelar agendar
    	paginaOfertaMultiprecio.getSubPaginaAgendar().pulsarBotonCancelar();
    	
    	subPaginaDocumentacion.pulsarAceptar();    	
    	
    	subPaginaDocumentacion.pulsarImprimirDocumento();
    	subPaginaDocumentacion.cerrarDocumentoPDFGenerado();
    	
    	subPaginaDocumentacion.pulsarAceptarDocumentacionCorrecta();
    	
    	try {
			subPaginaDocumentacion.pulsarBotonVerOfertasNO();
		} catch (Exception e) {
			System.out.println("No se ha visualizado el dialogo de mostrar ofertas");
		}
	}

	private void rellenarInformacionTomador(PaginaOfertaMultiprecio paginaOfertaMultiprecio) {		
		paginaOfertaMultiprecio.setInfTomador_NumeroDocumento(getDatoTest("cliente.nif"));
		paginaOfertaMultiprecio.pulsarBotonBuscarTomador();
		
		paginaOfertaMultiprecio.pulsarBotonSiguiente();	
	}
	
	private void rellenarInformacionTomadorAuto(PaginaOfertaMultiprecio paginaOfertaMultiprecio) {
		paginaOfertaMultiprecio.setInfTomador_NumeroDocumento(getDatoTest("cliente.nif"));
		paginaOfertaMultiprecio.pulsarBotonBuscarTomador();		
		
		// permiso de conducir
		paginaOfertaMultiprecio.seleccionarTipoCarnetConducir("B");
		paginaOfertaMultiprecio.setFechaExpedicionLicenciaConducir("02/11/2003");
		
		paginaOfertaMultiprecio.pulsarConsultarHistorialSiniestralidad();		
		
		paginaOfertaMultiprecio.pulsarBotonSiguiente();	
	}

	private void rellenarInformacionObjetoAsegurado(PaginaOfertaMultiprecio paginaOfertaMultiprecio) {
		paginaOfertaMultiprecio.setInfObtAsegurado_CodigoPostal("28020");		
		paginaOfertaMultiprecio.setInfObtAsegurado_AnoConstrucion("1995");
		paginaOfertaMultiprecio.setInfObtAsegurado_M2Vivienda("80");
		
		paginaOfertaMultiprecio.pulsarBotonSiguiente();		
	}
	
	private void rellenarInformacionObjetoAseguradoAuto(PaginaOfertaMultiprecio paginaOfertaMultiprecio) {
		paginaOfertaMultiprecio.setInfObtAsegurado_Matricula("3544-GTH");
		
		paginaOfertaMultiprecio.setInfObtAsegurado_CodigoPostalCirculacion("28020");
		paginaOfertaMultiprecio.pulsarBotonSiguiente();	
	}
	
}
