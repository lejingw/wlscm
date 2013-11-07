package com.jatools.common.excel;

public class ExcelDbrefrenceCheck {
	private String tableName;
	private String idFieldName;
	private String nameFieldName;
	private String queryCondition;
	
	public ExcelDbrefrenceCheck(String tableName, String idFieldName,
			String nameFieldName) {
		this.tableName = tableName;
		this.idFieldName = idFieldName;
		this.nameFieldName = nameFieldName;
	}
	public ExcelDbrefrenceCheck(String tableName, String idFieldName,
			String nameFieldName, String queryCondition) {
		this.tableName = tableName;
		this.idFieldName = idFieldName;
		this.nameFieldName = nameFieldName;
		this.queryCondition = queryCondition;
	}
	public String getTableName() {
		return tableName;
	}
	public String getIdFieldName() {
		return idFieldName;
	}
	public String getNameFieldName() {
		return nameFieldName;
	}
	public String getQueryCondition() {
		return queryCondition;
	}
	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}

}
