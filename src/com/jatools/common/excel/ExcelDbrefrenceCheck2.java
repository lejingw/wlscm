package com.jatools.common.excel;

public class ExcelDbrefrenceCheck2 extends ExcelDbrefrenceCheck {
	private String toIdCondition;


	public ExcelDbrefrenceCheck2(String tableName, String idFieldName,
                                 String nameFieldName, String toIdCondition) {
		super(tableName, idFieldName, nameFieldName);
        this.toIdCondition = toIdCondition;
	}
	public ExcelDbrefrenceCheck2(String tableName, String idFieldName,
                                 String nameFieldName, String queryCondition, String toIdCondition) {
		super(tableName, idFieldName, nameFieldName, queryCondition);
        this.toIdCondition = toIdCondition;
	}


    public String getToIdCondition() {
        return toIdCondition;
    }

    public void setToIdCondition(String toIdCondition) {
        this.toIdCondition = toIdCondition;
    }
}
