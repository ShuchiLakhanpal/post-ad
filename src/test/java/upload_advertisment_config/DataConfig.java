package upload_advertisment_config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class DataConfig {
	
	protected static Properties dataProp;
	
	private DataConfig() {
		Path pathData = Paths.get("src","test", "resources", "config", "logIn.properties");
		try {
			InputStream inputData = new FileInputStream(pathData.toFile());
			dataProp = new Properties();
			dataProp.load(inputData);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getDataProp(String key) {
		if(dataProp == null) {
			new DataConfig();
		}
		return dataProp.getProperty(key);
	}

}
