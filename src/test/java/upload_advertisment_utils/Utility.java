package upload_advertisment_utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
	public static String getDate(String testCaseName) {
		SimpleDateFormat  format = new SimpleDateFormat("ddMMyyyy_HHmmss");
		Date date = new Date();
		return testCaseName + "/"+ format.format(date)+ ".png";
		
	}

}
