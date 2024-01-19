package ventanas;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
	
	static boolean carga;
	static String dbpath;
	
	protected static final Logger logger = Logger.getLogger(Main.class.getName());
	/*
    public static void l (){
    	
        // Configurar el logger
        try {
            FileHandler fileHandler = new FileHandler(LoggerConfig.getProperty("log.filepath"));
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Configurar el nivel de registro
            Level logLevel = Level.parse(LoggerConfig.getProperty("log.level"));
            logger.setLevel(logLevel);
            fileHandler.setLevel(logLevel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	*/
	public static void main(String[] args) {
		
		//Prueba de properties
		logger.info(String.format("Iniciando la app."));
		
		Properties properties = new Properties();
		
		try {
			properties.load(new FileReader("config/config.properties"));
			carga = Boolean.parseBoolean(""+properties.get("CargaCSV"));
			dbpath = properties.getProperty("DBpath");
		}catch (Exception e) {			
			e.printStackTrace();
		}
		
		VentanaMenuPrincipal v = new VentanaMenuPrincipal();
		
	}

}