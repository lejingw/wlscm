package com.jatools.manager.util.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.excel.ExcelDbrefrenceCheck2;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


import com.jatools.common.ExcelUtil;
import com.jatools.common.excel.ExcelDbrefrenceCheck;
import com.jatools.dao.util.ExcelUtilDao;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelRowData;
import com.jatools.web.util.StringUtil;

public class ExcelUtilManagerImpl implements ExcelUtilManager{
	private ExcelUtilDao excelUtilDao;
	
	public void setExcelUtilDao(ExcelUtilDao excelUtilDao) {
		this.excelUtilDao = excelUtilDao;
	}

	public String saveExcelData(HSSFWorkbook workbook) {
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
		return saveExcelData(excelDataList);
	}
	public List<ExcelRowData> getExcelData(String seqId){
		return excelUtilDao.getExcelData(seqId);
	}

	public List<ExcelRowData> getCheckDbrefrenceResult(String seqId,
			int sheetIndex, int startCheckIndex, int columnIndex,
			ExcelDbrefrenceCheck dbrefrence) {
		return excelUtilDao.getCheckDbrefrenceResult(seqId, "" + sheetIndex, ""
				+ startCheckIndex, "" + columnIndex, dbrefrence.getTableName(),
				dbrefrence.getIdFieldName(), dbrefrence.getNameFieldName(), dbrefrence.getQueryCondition());
	}

	public void convertDbrefrenceToId(String seqId, int sheetIndex,
			int startCheckIndex, int columnIndex,
			ExcelDbrefrenceCheck dbrefrence){
        String condition = dbrefrence.getQueryCondition();
        if(dbrefrence instanceof ExcelDbrefrenceCheck2){
            ExcelDbrefrenceCheck2 dbrefrence2 = (ExcelDbrefrenceCheck2)dbrefrence;
            condition = dbrefrence2.getToIdCondition();
        }
		excelUtilDao.convertDbrefrenceToId(seqId, "" + sheetIndex, ""
				+ startCheckIndex, "" + columnIndex, dbrefrence.getTableName(),
				dbrefrence.getIdFieldName(), dbrefrence.getNameFieldName(), condition);
	}

	public void deleteTitleRows(String seqId, int startCheckIndex){
		excelUtilDao.deleteTitleRows(seqId, startCheckIndex);
	}
	public void printExcelData(String seqId){
		List<Map> list = excelUtilDao.printExcelData(seqId);
		for(Map map : list){
			for(int i=0;i<100;i++){
				System.out.print(map.get("COL"+i));
			}
			System.out.println();
		}
	}
	
	private String saveExcelData(List<ExcelRowData> excelDataList){
		if(null == excelDataList || excelDataList.size()<1)
			return null;
		String seqId = excelUtilDao.getexcelKey();
		for(ExcelRowData data:excelDataList){
			data.setSeqId(seqId);
		}
		excelUtilDao.saveExcelData(excelDataList);
		return seqId;
	}
	private String getCellStringValue(Cell cell) {
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
				if(null != cell.getDateCellValue()){
					res = com.jatools.web.util.DateUtil.formatSdf2(cell.getDateCellValue());
				}else{
					res = "";
				}
			} else {
				// 读取数字
				res = cell.getNumericCellValue() + "";
				if(res.endsWith(".0")){
					res = res.substring(0, res.length() - 2);
				}
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
		case Cell.CELL_TYPE_BLANK:
			// 读取String
			res = "";
			break;
		}
//		System.out.println("======================" + cell.getCellType());
		return res.trim();
	}
}
