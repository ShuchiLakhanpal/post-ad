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

	private static List<List<Object[]>> testData;
	public static List<Object[]> sheetData;
	private String fileName;
	boolean checkEmptyCell = true;
	public static Workbook wb;
	static int activeSheet = 0;

	public ExcelReader(String fileName) {
		this.fileName = fileName;
		this.testData = new ArrayList<List<Object[]>>();
		try {
			FileInputStream inputFile = new FileInputStream(new File(fileName));
			wb = new XSSFWorkbook(inputFile);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				sheetData = new ArrayList<>();
				Sheet dataSheet = wb.getSheetAt(i);
				Iterator<Row> getRow = dataSheet.iterator();

				getRow.next();
				while (getRow.hasNext()) {
					Row getCurrentRow = getRow.next();
					//	System.out.println(dataSheet.getLastRowNum());
					Iterator<Cell> cells = getCurrentRow.iterator();
					List<String> dataCell = new ArrayList<>();
					while (cells.hasNext()) {
						Cell currentCell = cells.next();

						dataCell.add(currentCell.getStringCellValue());
						if (currentCell.getStringCellValue().isEmpty()) {
							checkEmptyCell = false;
							//getRow.next();
						}
					}

					if (checkEmptyCell == true) {
						sheetData.add(dataCell.toArray());
					}
				}
				testData.add(sheetData);

			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Check the file location or if file exists and name is correct");
			e.printStackTrace();
		}

	}

	public static List<Object[]> getData(int currSheet) {
		List<Object[]> list = testData.get(currSheet);
		return list;
	}

	public static String getActiveSheet() {
		String nameSheet = wb.getSheetName(activeSheet);
		return nameSheet;

	}

}
