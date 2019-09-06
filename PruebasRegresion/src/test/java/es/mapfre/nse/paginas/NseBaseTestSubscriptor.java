package es.mapfre.nse.paginas;

public abstract class NseBaseTestSubscriptor extends NseBaseTest {

	protected String getIdUsuario() {
		return getDatosConexion().getUsuarioSubscriptor().getId();
	}
	
	protected String getPasswordUsuario() {
		return getDatosConexion().getUsuarioSubscriptor().getPassword();
	}
	
}
