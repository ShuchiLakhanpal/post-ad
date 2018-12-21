package upload_advertisment_excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private List<Object[]> testData;
	private String fileName;
	boolean checkEmptyCell = true;

	@SuppressWarnings("deprecation")
	public ExcelReader(String fileName) {
		this.fileName = fileName;
		this.testData = new ArrayList<>();
		try {
			FileInputStream inputFile = new FileInputStream(new File(fileName));
			Workbook wb = new XSSFWorkbook(inputFile);
			//Sheet dataSheet = wb.getSheet("BA");
			Sheet dataSheet = wb.getSheet("QA");
			Iterator<Row> getRow = dataSheet.iterator();

			getRow.next();
			while (getRow.hasNext()) {
				Row getCurrentRow = getRow.next();
				Iterator<Cell> cells = getCurrentRow.iterator();
				List<String> dataCell = new ArrayList<>();
				 System.out.println(getCurrentRow.getLastCellNum());
				while (cells.hasNext()) {
					Cell currentCell = cells.next();
					// CellType type = currentCell.getCellTypeEnum();

					// if(type == CellType.STRING ) {
					/*
					 * if(currentCell.getStringCellValue().isEmpty()) { checkEmptyCell = false; }
					 */
					dataCell.add(currentCell.getStringCellValue());
					if (currentCell.getStringCellValue().isEmpty()) {
						checkEmptyCell = false;

					} // else if(type == CellType.NUMERIC) {
					/*
					 * if(currentCell.getNumericCellValue() == 0) { checkEmptyCell = false; }
					 */
					// dataCell.add(currentCell.getNumericCellValue());
					// System.out.println(currentCell.getNumericCellValue());
				}

				if (checkEmptyCell == true) {
					testData.add(dataCell.toArray());
				}
			}
			// }
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Check the file location or if file exists and name is correct");
			e.printStackTrace();
		}
	}

	public List<Object[]> getData() {

		return testData;
	}

}
