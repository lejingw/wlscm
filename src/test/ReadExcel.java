package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jatools.manager.util.ExcelUtilManager;

public class ReadExcel {
	private ExcelUtilManager excelUtilManager;
	
	public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
		this.excelUtilManager = excelUtilManager;
	}
	
	private String saveExcelData(HSSFWorkbook workbook) {
		return excelUtilManager.saveExcelData(workbook);
	/*
	 * statistics_grade
		List<ExcelRowData> excelDataList = new ArrayList<ExcelRowData>();
		for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			int startRowIndex = sheet.getFirstRowNum();
			int lastRowIndex = sheet.getLastRowNum();
			for (int rowIndex = startRowIndex; rowIndex < lastRowIndex+1; rowIndex++) {
				HSSFRow row = sheet.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				ExcelRowData excelRow = new ExcelRowData(sheetIndex, rowIndex);
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				for (int cellIndex = firstCellIndex; cellIndex < lastCellIndex + 1; cellIndex++) {
					HSSFCell cell = row.getCell(cellIndex);
					if(cell == null){
						continue;
					}
					String value = getCellStringValue(cell);
					excelRow.setColValue(cellIndex, value);
				}
				excelDataList.add(excelRow);
				//System.out.println(excelRow.toString());
			}
		}
		excelUtilManager.saveExcelData(excelDataList);
		return null;
	 */
	}

	/*private String getCellStringValue(Cell cell) {
		if (cell == null)
			return "";
		String res = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			// 得到Boolean对象的方法
			res = cell.getBooleanCellValue() + "";
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 先看是否是日期格式
			if (DateUtil.isCellDateFormatted(cell)) {
				// 读取日期格式
				res = cell.getDateCellValue() + "";
			} else {
				// 读取数字
				res = cell.getNumericCellValue() + "";
			}
			break;
		case Cell.CELL_TYPE_FORMULA:
			// 读取公式
			res = cell.getCellFormula() + "";
			break;
		case Cell.CELL_TYPE_STRING:
			// 读取String
			res = cell.getRichStringCellValue().toString() + "";
			break;
		}
		return res.trim();
	}*/
	public static void main(String[] args) throws Exception {
//		String fileName = "test.xls";
//		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileName));
//		ReadExcel temp = new ReadExcel();
//		temp.saveExcelData(workbook);
		Pattern pattern = Pattern.compile("^-?\\d+$");
		String s = "-11";
		System.out.println(pattern.matcher(s).matches());
		
		Pattern pattern2 = Pattern.compile("^-?\\d+\\.?\\d*$");
		String s2 = "1.1";
		System.out.println(pattern2.matcher(s2).matches());
		
		String s3 = "2012-01-01";
		Pattern pattern3 = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
		System.out.println(pattern3.matcher(s3).matches());
	}
}
