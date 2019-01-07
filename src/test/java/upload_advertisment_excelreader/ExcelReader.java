package upload_advertisment_excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private static List<Object[]> testData;
	private String fileName;
	boolean checkEmptyCell = true;
	static int activeSheet = 0;
	static Workbook wb;
	public static int getNumSheets = 0;

	public static int getNumberSheets() {
		getNumSheets = wb.getNumberOfSheets();
		System.out.println(getNumSheets);
		return getNumSheets;
	}

	public ExcelReader(String fileName, int activeSheet) {
		this.fileName = fileName;
		this.testData = new ArrayList<>();
		this.activeSheet = activeSheet;
		try {
			FileInputStream inputFile = new FileInputStream(new File(fileName));
			wb = new XSSFWorkbook(inputFile);
			// for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet dataSheet = wb.getSheetAt(activeSheet);
			activeSheet = wb.getActiveSheetIndex();
			Iterator<Row> getRow = dataSheet.iterator();
			getRow.next();
			while (getRow.hasNext()) {
				Row getCurrentRow = getRow.next();
				Iterator<Cell> cells = getCurrentRow.iterator();
				List<String> dataCell = new ArrayList<>();
				while (cells.hasNext()) {
					Cell currentCell = cells.next();
					// System.out.println(currentCell.getStringCellValue());
					dataCell.add(currentCell.getStringCellValue());
					if (currentCell.getStringCellValue().isEmpty()) {
						checkEmptyCell = false;
					}
				}

				if (checkEmptyCell == true) {
					testData.add(dataCell.toArray());
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Check the file location or if file exists and name is correct");
			e.printStackTrace();
		}

	}

	public static List<Object[]> getData() {
		return testData;
	}

	public static String getActiveSheet() {
		String nameSheet = wb.getSheetName(activeSheet);
		return nameSheet;

	}

}
