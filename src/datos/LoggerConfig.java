package datos;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoggerConfig {
	private static final String PROPERTIES_FILE = "config\\logger.properties";
    private static Properties p;

    static {
        p = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            p.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return p.getProperty(key);
    }
}
