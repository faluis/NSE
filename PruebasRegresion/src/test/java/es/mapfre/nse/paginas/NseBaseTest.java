package es.mapfre.nse.paginas;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import es.mapfre.nse.pruebas.utils.DatosConexion;
import es.mapfre.nse.pruebas.utils.Utilidades;

public abstract class NseBaseTest {

	private static String PREFIJO_URL = "http://";	
	
	private DatosConexion datosConexion = null;
	private WebDriver driver = null;
	
	private Map<String, String> datosTest = null;
	
	@Before
	public void init() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		datosConexion = Utilidades.getDatosConexion();
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		// Empezar con el navegador maximizado
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    	driver.get(PREFIJO_URL + getIdUsuario() + ":" + getPasswordUsuario() + "@" + getDatosConexion().getUrl());
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	protected DatosConexion getDatosConexion() {
		return datosConexion;
	}
	
	@After
	public void tearDown() {
		try {
			if(Utilidades.getConfiguracion().cerrarNavegador())
				driver.quit();
		} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	protected String getIdUsuario() {
		return datosConexion.getUsuarioEmisor().getId();
	}
	
	protected String getPasswordUsuario() {
		return datosConexion.getUsuarioEmisor().getPassword();
	}
	
	protected abstract String getCodigoPrueba();
	
	public Map<String, String> getDatosTest() {
		if(datosTest==null) {
			try {
				datosTest = Utilidades.getDatosTest(getCodigoPrueba());
			} catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
				System.out.println("WARNING - El Test no tiene datos de prueba");
			}
		}
		return datosTest;
	}
	
	public String getDatoTest(String idDato) {
		return getDatosTest().get(idDato);
	}
}
