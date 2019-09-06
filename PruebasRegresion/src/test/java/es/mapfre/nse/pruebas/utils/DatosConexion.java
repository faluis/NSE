package es.mapfre.nse.pruebas.utils;

public class DatosConexion {

	private String url;	
	private Usuario usuarioEmisor;	
	private Usuario usuarioSubscriptor;
	private Usuario usuarioSubscriptorCentral;
	private Usuario usuarioCambioClave;	
	private Cliente clienteExistente;
	private Vivienda datosVivienda;
	
	public String getUrl() {
		return url;
	}
		
	public Cliente getClienteExistente() {
		return clienteExistente;
	}
	
	public Usuario getUsuarioEmisor() {
		return usuarioEmisor;
	}

	public Vivienda getDatosVivienda() {
		return datosVivienda;
	}

	public Usuario getUsuarioSubscriptor() {
		return usuarioSubscriptor;
	}

	public Usuario getUsuarioSubscriptorCentral() {
		return usuarioSubscriptorCentral;
	}

	public Usuario getUsuarioCambioClave() {
		return usuarioCambioClave;
	}

	
	
}
