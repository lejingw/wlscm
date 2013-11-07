package com.jatools.common.excel;

import java.util.List;

import com.jatools.vo.util.ExcelRowData;

public interface ExcelSelfDefinedCheck {
	/**
	 * 检查字段有效性
	 * @param value 字段值
	 * @param rowData
	 * @param allData
	 * @return true检查通过，false检查不通过会调用getErrorMsg获取提示信息
	 */
	public boolean check(int rowIndex, String value, ExcelRowData rowData,
			List<ExcelRowData> allData);
	/**
	 * 检查不通过时的提示信息
	 * @param value
	 * @param rowData
	 * @param allData
	 * @return
	 */
	public String getErrorMsg(int rowIndex, String value, ExcelRowData rowData,
			List<ExcelRowData> allData);
}
