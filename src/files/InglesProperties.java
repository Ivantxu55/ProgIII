package files;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class InglesProperties {

	public static void main(String[] args) {
		
		Properties propiedades = new Properties();
		InputStream entrada = null;
		
		try {
			entrada = new FileInputStream("file\\Ingles.properties");
			propiedades.load(entrada);
			
			System.out.println(propiedades.getProperty("Nombre"));
			System.out.println(propiedades.getProperty("idiomas"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
