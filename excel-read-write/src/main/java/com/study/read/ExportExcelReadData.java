package com.study.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.study.util.ExcelReader;
import com.study.vo.CustomVO;

public class ExportExcelReadData {

	public static String fileName = "C:\\temp\\1050_Excel.xlsx";

	public static void getRowColumnCounts() throws IOException {
		File file = new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalRowNumber = sheet.getLastRowNum();
		int totalColumnCount = sheet.getRow(0).getLastCellNum();
		// System.out.println(toRowNumber + "|" + columnCount + "|");
		XLSReader(totalRowNumber, totalColumnCount);
	}

	public static void XLSReader(int totalRowNumber, int totalColumnCount) throws FileNotFoundException, IOException {
		// int totalColumnCount = 6;
		ExcelReader reader = new ExcelReader(fileName, totalColumnCount);
		Object[][] data = reader.getData();
		List<CustomVO> customVOs = new ArrayList<>();
		getExcelHeaders(data);
		getExcelDatas(data, customVOs, totalRowNumber, totalColumnCount);
	}

	private static void getExcelHeaders(Object[][] data) {
		int count = 0;
		String item = (String) data[0][count++];
		String subject = (String) data[0][count++];
		String subSubject = (String) data[0][count++];
		String ref = (String) data[0][count++];
		String remarks = (String) data[0][count++];
		String category = (String) data[0][count++];
		System.out.println("Headers:: " + item + "," + subject + "," + subSubject + "," + ref + "," + remarks + "," + category);
	}

	private static void getExcelDatas(Object[][] data, List<CustomVO> customVOs, int totalRowNumber,
			int totalColumnCount) throws IOException {
		// int totalRowNumber = 4;
		// int totalColumnCount = 6;
		for (int i = 0; i < totalRowNumber; i++) {
			for (int j = 0; j < totalColumnCount; j++) {
				CustomVO customVO = new CustomVO();
				customVO.setItem((String) data[i+1][j++]); 
				customVO.setSubject((String) data[i+1][j++]); 
				customVO.setSubSubject((String) data[i+1][j++]); 
				customVO.setRef((String) data[i+1][j++]); 
				customVO.setRemarks((String) data[i+1][j++]); 
				customVO.setCategory((String) data[i+1][j++]); 
				customVOs.add(customVO);
			}
		}
		System.out.println("Datas:: " + customVOs);
	}
}
