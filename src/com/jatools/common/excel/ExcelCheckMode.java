package com.jatools.common.excel;

public class ExcelCheckMode {
	private int checkWay;
	private int sheetIndex;
	private int columnIndex;
	private boolean allowEmpty;
	private ExcelColumnEnum typeCheck;
	private ExcelDbrefrenceCheck dbrefrence;
	private boolean convertToIdFlag;
	private ExcelSelfDefinedCheck selfDefinedCheck;

	public ExcelCheckMode(int sheetIndex, int columnIndex, boolean allowEmpty,
			ExcelColumnEnum typeCheck) {
		this.checkWay = 1;
		this.sheetIndex = sheetIndex;
		this.columnIndex = columnIndex;
		this.allowEmpty = allowEmpty;
		this.typeCheck = typeCheck;
	}

	public ExcelCheckMode(int sheetIndex, int columnIndex, boolean allowEmpty,
			ExcelDbrefrenceCheck dbrefrence, boolean convertToIdFlag) {
		this.checkWay = 2;
		this.sheetIndex = sheetIndex;
		this.columnIndex = columnIndex;
		this.allowEmpty = allowEmpty;
		this.dbrefrence = dbrefrence;
		this.convertToIdFlag = convertToIdFlag;
	}

	public ExcelCheckMode(int sheetIndex, int columnIndex,
			ExcelSelfDefinedCheck selfDefinedCheck) {
		this.checkWay = 3;
		this.sheetIndex = sheetIndex;
		this.columnIndex = columnIndex;
		this.selfDefinedCheck = selfDefinedCheck;
	}

	public int getCheckWay() {
		return checkWay;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public boolean isAllowEmpty() {
		return allowEmpty;
	}

	public ExcelColumnEnum getTypeCheck() {
		return typeCheck;
	}

	public ExcelDbrefrenceCheck getDbrefrence() {
		return dbrefrence;
	}

	public ExcelSelfDefinedCheck getSelfDefinedCheck() {
		return selfDefinedCheck;
	}

	public boolean isConvertToIdFlag() {
		return convertToIdFlag;
	}
}
