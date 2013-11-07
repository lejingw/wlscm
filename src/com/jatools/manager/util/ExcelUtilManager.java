package com.jatools.manager.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jatools.common.excel.ExcelDbrefrenceCheck;
import com.jatools.vo.util.ExcelRowData;


public interface ExcelUtilManager {

	public String saveExcelData(HSSFWorkbook workbook);

	public List<ExcelRowData> getExcelData(String seqId);

	public List<ExcelRowData> getCheckDbrefrenceResult(String seqId, int sheetIndex,
			int startCheckIndex, int columnIndex,
			ExcelDbrefrenceCheck dbrefrence);

	public void convertDbrefrenceToId(String seqId, int sheetIndex,
			int startCheckIndex, int columnIndex,
			ExcelDbrefrenceCheck dbrefrence);

	public void deleteTitleRows(String seqId, int startCheckIndex);

	public void printExcelData(String seqId);

}
