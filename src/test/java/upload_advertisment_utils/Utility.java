package upload_advertisment_utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import upload_advertisment_config.DriverConfig;
import upload_advertisment_config.SelectorsData;
import upload_advertisment_excelreader.ExcelReader;

public class Utility {

	public static String getDate(String testCaseName) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		return testCaseName + "_" + format.format(date) + ".png";

	}

	public static void uploadFiles(String pathFile) throws InterruptedException {
		File imgDirectory = new File(pathFile);
		if (imgDirectory.exists() && imgDirectory.isDirectory()) {
			// System.out.println(imgDirectory.isDirectory());
			String imgNames = "";
			File[] fileList = imgDirectory.listFiles();// get the array of files in the folder
			for (File getFileList : fileList) {
				if (getFileList.isFile()) {
					imgNames = imgNames + "\"" + getFileList.getName() + "\"" + " ";

					//System.out.println(imgDirectory + File.separator + imgNames);
				}
			}
			Thread.sleep(2000);
			try {
				Runtime.getRuntime().exec(DriverConfig.getProperty("getFolderPath") + " " + imgDirectory + File.separator);
				//System.out.println(imgDirectory + File.separator + imgNames);
				Thread.sleep(5000);
				Runtime.getRuntime().exec(DriverConfig.getProperty("addFilesPath") + " " + imgNames);
				//System.out.println(imgNames);
				Thread.sleep(3000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}