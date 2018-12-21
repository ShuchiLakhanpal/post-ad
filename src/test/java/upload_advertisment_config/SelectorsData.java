package upload_advertisment_config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SelectorsData {
	
	protected static Properties properties;
	
	private SelectorsData() {
		Path pathSelector = Paths.get("src", "test", "resources", "config", "course_config.yml");
		try {
				InputStream loadFile = new FileInputStream(pathSelector.toFile());
				properties = new Properties();
				properties.load(loadFile);
			
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
			new SelectorsData();
		}
		return properties.getProperty(key);
	}

}
