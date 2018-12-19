package upload_advertisment_config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DriverConfig {
	
	protected static Properties properties;
	
	private DriverConfig() {
		Path pathEnv = Paths.get("src","test", "resources", "config", "driver_config.yml");
		try {
			InputStream inputEnvFile = new FileInputStream(pathEnv.toFile());
			properties = new Properties();
			properties.load(inputEnvFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		if(properties == null) {
			new DriverConfig();
		}

		return properties.getProperty(key);
	}
	

}
