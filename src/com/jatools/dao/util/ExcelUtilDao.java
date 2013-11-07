package com.jatools.dao.util;

import java.util.List;
import java.util.Map;

import com.jatools.vo.util.ExcelRowData;


public interface ExcelUtilDao {
	String getexcelKey();

	void saveExcelData(List<ExcelRowData> excelDataList);

	List<ExcelRowData> getExcelData(String seqId);

	List<ExcelRowData> getCheckDbrefrenceResult(String seqId,
			String sheetIndex, String startCheckIndex, String columnIndex,
			String tableName, String idFieldName, String nameFieldName,
			String queryCondition);
	
	void convertDbrefrenceToId(String seqId,
			String sheetIndex, String startCheckIndex, String columnIndex,
			String tableName, String idFieldName, String nameFieldName,
			String queryCondition);

	void deleteTitleRows(String seqId, int startCheckIndex);

	List<Map> printExcelData(String seqId);

}
