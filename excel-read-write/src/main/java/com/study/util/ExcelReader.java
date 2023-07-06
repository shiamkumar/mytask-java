package com.study.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;

//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	private LinkedHashMap<String, Object[][]> data;

	private Workbook workbook;

	private POIFSFileSystem npoifs = null;

//	private OPCPackage opcPackage = null;

	private int columnCount = -1;

	private boolean isDateAsCalendar = false;

	File file = null;

	public ExcelReader(String fileName, int columnCount) throws IOException, FileNotFoundException {
		this.columnCount = columnCount;
		createWorkBook(fileName);
	}

	public ExcelReader(String fileName) throws IOException, FileNotFoundException {
		createWorkBook(fileName);
	}

	private Object[][] startRead(int columnCount) {
		if (workbook != null && workbook.getNumberOfSheets() == 0) {
			return null;
		}
		return startRead(0, getPhysicalNumberOfRows(), 0, columnCount, 0);
	}

	private Object[][] startRead(int fromRowNumber, int toRowNumber, int columnCount) {
		return startRead(fromRowNumber, toRowNumber, 0, columnCount, 0);
	}

	// 5
	private Object[][] startRead(int fromRowNumber, int toRowNumber, int fromColmnNumber, int columnCount,
			int sheetNumber) {
		Object[][] dataObjectLocal = null;
		// The HSSFSheet object
		Sheet sheetLocal = null;

		Iterator<Row> rowiterator = null;
		int rowCount = 0;
		int size = toRowNumber - fromRowNumber + 1;
		if (!isValidSheetNumber(sheetNumber) || size <= 0) {
			return null;
		}
		// Get the first sheet
		sheetLocal = workbook.getSheetAt(sheetNumber);
		// Instantiate the data object. Here no of rows is got from POI API
		// column count is the value passed
		/*
		 * * if (size > sheetLocal.getPhysicalNumberOfRows()) { size = *
		 * sheetLocal.getPhysicalNumberOfRows(); }
		 */
		dataObjectLocal = new Object[size][columnCount];
		rowiterator = sheetLocal.rowIterator();
		while (rowCount < fromRowNumber && rowiterator.hasNext()) {
			rowiterator.next();
			rowCount++;
		}
		int index = 0;
		// Iterate through the rows
		while (rowCount <= toRowNumber) {
			if (rowiterator.hasNext()) {
				// Get the row
				Row row = rowiterator.next();
				// int totalColmnsCount=row.getPhysicalNumberOfCells();
				/* * if(fromColmnNumber>totalColmnsCount){ index++; rowCount++; * continue; } */
				for (int i = fromColmnNumber; i < fromColmnNumber + columnCount; i++) {
					// Get the cell
					Cell cell = row.getCell(i);
					if (cell == null) {
						// This is a case where no data is set
						// System.out.println("No data got");
						// getLogger("UTIL").fine("No data got");
					} else {
						// If the data type is String, then set the value
						int columnNumberInDataArray = i - fromColmnNumber;
						if (cell.getCellType() == CellType.STRING) {
							dataObjectLocal[index][columnNumberInDataArray] = cell.getStringCellValue();
						} else if (cell.getCellType() == CellType.NUMERIC) {
							if (DateUtil.isCellDateFormatted(cell) && isDateAsCalendar) {
								/*
								 * Important * @auther A-3277 Sangeeth * * * This method will return the date as
								 * Calendar object in 1900 Date System. * The workbook's date system must be in
								 * 1900 other wise a 1462 days difference will reflect. * see below links *
								 * http://support.microsoft.com/kb/180162 *
								 * http://poi.apache.org/apidocs/org/apache/poi/ss/usermodel/DateUtil.html#
								 * isCellDateFormatted(org.apache.poi.ss.usermodel.Cell)
								 */
								dataObjectLocal[index][columnNumberInDataArray] = DateUtil
										.getJavaCalendarUTC(cell.getNumericCellValue(), false);
							} else {
								/*
								 * * Modified By IC, to avoid getting the result * in double value format (ie
								 * 1.2E+10) This will * give result 12000000000 instead of 1.2E+10
								 */
								// If the data type is numeric, then convert the
								// value to String // dataObject[index][i] = Double.toString(cell.getNumericCellValue());
								BigDecimal integerCellValue = new BigDecimal(cell.getNumericCellValue());
								dataObjectLocal[index][columnNumberInDataArray] = integerCellValue.toString();
								/* *IC's Modification Ends */
							}
						}
					}
				}
				index++;
				rowCount++;
			} else {
				break;
			}
		}
		return dataObjectLocal;
	}

	public int getPhysicalNumberOfRows() {
		return getPhysicalNumberOfRows(0);
	}

	public int getPhysicalNumberOfRows(int sheetNumber) {
		int physicalNumberOfRows = -1;
		if (isValidSheetNumber(sheetNumber)) {
			Sheet sheet = workbook.getSheetAt(sheetNumber);
			physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		}
		return physicalNumberOfRows;
	}

	private boolean isValidSheetNumber(int sheetNumber) {
		return (workbook != null && sheetNumber >= 0 && sheetNumber < workbook.getNumberOfSheets());
	}

	public int getPhysicalNumberOfRows(String sheetName) {
		int physicalNumberOfRows = -1;
		if (workbook != null) {
			physicalNumberOfRows = getPhysicalNumberOfRows(workbook.getSheetIndex(sheetName));
		}
		return physicalNumberOfRows;
	}

	public Object[][] getData() {
		if (columnCount < 0) {
			return null;
		}
		return startRead(columnCount);
	}

	public Object[][] getData(int fromRowNum, int toRowNum, int columnCount) {
		return startRead(fromRowNum, toRowNum, columnCount);
	}

	public Object[][] getData(int fromRowNum, int toRowNum, short columnCount) {
		return startRead(fromRowNum, toRowNum, columnCount);
	}

	public Object[][] getData(int fromRowNum, int toRowNum, int columnCount, int sheetNumber) {
		return startRead(fromRowNum, toRowNum, 0, columnCount, sheetNumber);
	}

	public Object[][] getData(int fromRowNum, int toRowNum, int columnCount, String sheetName) {
		return startRead(fromRowNum, toRowNum, 0, columnCount, workbook.getSheetIndex(sheetName));
	}

	public Object[][] getData(int fromRowNum, int toRowNum, int fromColumnNumber, int columnCount, String sheetName) {
		return startRead(fromRowNum, toRowNum, fromColumnNumber, columnCount, workbook.getSheetIndex(sheetName));
	}

	public Object[][] getData(int fromRowNum, int toRowNum, int fromColumnNumber, int columnCount, int sheetNumber) {
		return startRead(fromRowNum, toRowNum, fromColumnNumber, columnCount, sheetNumber);
	}

	public void closeFile() throws IOException {
		/* * if (workbook != null) { workbook = null; * * } */
		if (npoifs != null) {
			npoifs.close();
		}
//		if (opcPackage != null) {
//			opcPackage.close();
//		}
		if (data != null) {
			data.clear();
			data = null;
		}
	}

	public boolean isDateAsCalendar() {
		return isDateAsCalendar;
	}

	public void setDateAsCalendar(boolean isDateAsCalendar) {
		this.isDateAsCalendar = isDateAsCalendar;
	}

	private void createWorkBook(String fileName) throws IOException {
		// change made for solving issue with jira-id IRESPST-6992 File file = null;
		try {
			npoifs = null;
//			opcPackage = null;
			file = new File(fileName);
			npoifs = new POIFSFileSystem(file);
			// workbook = WorkbookFactory.create(new File(fileName));
			workbook = WorkbookFactory.create(npoifs);
		} catch (OfficeXmlFileException officeXmlFileException) {
			try {
//				opcPackage = OPCPackage.open(file.getAbsolutePath());
				workbook = WorkbookFactory.create(file);
			} catch (Exception e) {
//				if (log.isSevereEnabled()) {
//					log.severe(Log.getMessageWithRootCause(e));
//				}
				throw new IOException(e);
			}
		}
	}

	public int getPhysicalNumberOfColumns(String sheetName, int rowNumber) {
		int physicalNumberOfRows = -1;
		if (workbook != null) {
			physicalNumberOfRows = getPhysicalNumberOfColumns(workbook.getSheetIndex(sheetName), rowNumber);
		}
		return physicalNumberOfRows;
	}

	public int getPhysicalNumberOfColumns(int sheetNumber, int rowNumber) {
		int physicalNumberOfRows = -1;
		if (isValidSheetNumber(sheetNumber)) {
			Sheet sheet = workbook.getSheetAt(sheetNumber);
			Row row = sheet.getRow(rowNumber);
			if (row != null) {
				physicalNumberOfRows = row.getPhysicalNumberOfCells();
			}
		}
		return physicalNumberOfRows;
	}

}
