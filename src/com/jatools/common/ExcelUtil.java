package com.jatools.common;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


import com.jatools.common.excel.ExcelCheckMode;
import com.jatools.common.excel.ExcelColumnEnum;
import com.jatools.common.excel.ExcelDbrefrenceCheck;
import com.jatools.common.excel.ExcelSelfDefinedCheck;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelRowData;
import com.jatools.web.util.DateUtil;

public class ExcelUtil {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ExcelUtilManager excelUtilManager;
	private String seqId;
	private List<ExcelRowData> excelDataList;
	private int startCheckIndex = 1;
	private HSSFWorkbook workbook = new HSSFWorkbook();
	private ExcelCheckMode[] excelCheckModes;
	private Pattern numberPattern = Pattern.compile("^-?\\d+$");
	private Pattern floatPattern = Pattern.compile("^-?\\d+\\.?\\d*$");
	private Pattern datePattern = Pattern.compile("^[0-9]{4}-[0-9]{2}-[0-9]{2}$");
	
	public String getSeqId() {
		return seqId;
	}

	public ExcelUtil(HttpServletRequest request, HttpServletResponse response, ExcelUtilManager excelUtilManager) {
		this.request = request;
		this.response = response;
		this.excelUtilManager = excelUtilManager;
	}
	
	public void setStartCheckIndex(int startCheckIndex) {
		this.startCheckIndex = startCheckIndex;
	}

	public void saveExcelData(String fieldName) {
		try {
			if (!ServletFileUpload.isMultipartContent(request)) {
				return ;
			}
			DiskFileItemFactory dff = new DiskFileItemFactory(); // 创建该对象
			//dff.setRepository(tmpDir);//指定上传文件的临时目录
			dff.setSizeThreshold(1024000); //指定在内存中缓存数据大小,单位为byte
			ServletFileUpload sfu = new ServletFileUpload(dff); // 创建该对象
			sfu.setFileSizeMax(5000000); //指定单个上传文件的最大尺寸
			sfu.setSizeMax(10000000); //指定一次上传多个文件的总尺寸
			FileItemIterator fii = sfu.getItemIterator(request);
			while (fii.hasNext()) {
				FileItemStream fis = fii.next(); //从集合中获得一个文件流
				if (!fis.isFormField() && fis.getName().length() > 0 && fieldName.equals(fis.getFieldName())) {
					//过滤掉表单中非文件域
					HSSFWorkbook wb = new HSSFWorkbook(fis.openStream());
					seqId = excelUtilManager.saveExcelData(wb);
					return ;
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ;
	}
	
	public boolean checkExcelData(ExcelCheckMode[] excelCheckModes) {
		this.excelCheckModes = excelCheckModes;
		excelDataList = excelUtilManager.getExcelData(seqId);
		for(ExcelCheckMode checkMode : excelCheckModes){
			if(1 == checkMode.getCheckWay()){
				//类型检查
				checkRowColumnType(checkMode);
			}else if(2 == checkMode.getCheckWay()){
				//数据库段
				checkRowDbrefrence(checkMode);
			}else if(3 == checkMode.getCheckWay()){
				//自定义检查
				checkRowSelfDefined(checkMode);
			}
		}
		
		if(workbook.getNumberOfSheets()>0){
			exportWorkbook();
			return false;
		}
		return true;
	}
	public void convertDbrefrenceToId() {
		excelUtilManager.deleteTitleRows(seqId, this.startCheckIndex);
		for(ExcelCheckMode checkMode : excelCheckModes){
			if(2 == checkMode.getCheckWay() && checkMode.isConvertToIdFlag()){
				excelUtilManager.convertDbrefrenceToId(seqId, checkMode.getSheetIndex(), startCheckIndex, checkMode.getColumnIndex(), checkMode.getDbrefrence());
			}
		}
//		excelUtilManager.printExcelData(seqId);
	}
	/**
	 * 自定义检查
	 * @param checkMode
	 */
	public void checkRowSelfDefined(ExcelCheckMode checkMode){
		for(ExcelRowData rowData : excelDataList){
			if(checkMode.getSheetIndex() != rowData.getSheetIndex())
				continue;
			if(startCheckIndex>rowData.getRowIndex())
				continue;
			String val = null;
			try {
				val = PropertyUtil.getProperty(rowData, "col" + checkMode.getColumnIndex());
			} catch (Exception e) {
				e.printStackTrace();
			}
			ExcelSelfDefinedCheck selfDefinedCheck = checkMode.getSelfDefinedCheck();
			if(!selfDefinedCheck.check(rowData.getRowIndex(), val, rowData, excelDataList)){
				String errorMsg = selfDefinedCheck.getErrorMsg(rowData.getRowIndex(), val, rowData, excelDataList);
				setCheckResultMessage(rowData.getSheetIndex(), rowData.getRowIndex(),  checkMode.getColumnIndex(), errorMsg, val != null);
			}
		}
	}
	/**
	 * 数据库段检查
	 * @param checkMode
	 */
	public void checkRowDbrefrence(ExcelCheckMode checkMode){
		ExcelDbrefrenceCheck dbrefrence = checkMode.getDbrefrence();
		List<ExcelRowData> checkResultList = excelUtilManager.getCheckDbrefrenceResult(seqId, checkMode.getSheetIndex(), startCheckIndex, checkMode.getColumnIndex(), dbrefrence);
		for(ExcelRowData rowData : checkResultList){
			String val = rowData.getResult();
			if(null == val){
				if(!checkMode.isAllowEmpty()){
					setCheckResultMessage(rowData.getSheetIndex(), rowData.getRowIndex(),  checkMode.getColumnIndex(), "不能为空", false);
				}
			}else{
				setCheckResultMessage(rowData.getSheetIndex(), rowData.getRowIndex(),  checkMode.getColumnIndex(), "当前值无效，不能找到对应id", true);
			}
		}
	}
	/**
	 * 类型检查
	 * @param checkMode
	 */
	public void checkRowColumnType(ExcelCheckMode checkMode){
		for(ExcelRowData rowData : excelDataList){
			if(checkMode.getSheetIndex() != rowData.getSheetIndex())
				continue;
			if(startCheckIndex>rowData.getRowIndex())
				continue;
			String val = null;
			try {
				val = PropertyUtil.getProperty(rowData, "col" + checkMode.getColumnIndex());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!checkMode.isAllowEmpty() && (null == val)){
				setCheckResultMessage(rowData.getSheetIndex(), rowData.getRowIndex(),  checkMode.getColumnIndex(), "不能为空", false);
			}
			if(null == val){
				continue;
			}
			if(ExcelColumnEnum.NUMBER_COLUMN.equals(checkMode.getTypeCheck())){
				checkNumberColumn(rowData.getSheetIndex(),  checkMode.getColumnIndex(), rowData.getRowIndex(), val);
			}else if(ExcelColumnEnum.FLOAT_COLUMN.equals(checkMode.getTypeCheck())){
				checkFloatColumn(rowData.getSheetIndex(),  checkMode.getColumnIndex(), rowData.getRowIndex(), val);
			}else if(ExcelColumnEnum.DATE_COLUMN.equals(checkMode.getTypeCheck())){
				checkDateColumn(rowData.getSheetIndex(),  checkMode.getColumnIndex(), rowData.getRowIndex(), val);
			}
		}
	}
	
	private void checkNumberColumn(int sheetIndex, int columnIndex, int rowIndex, String val) {
		if(!numberPattern.matcher(val).matches()){
			setCheckResultMessage(sheetIndex, rowIndex,  columnIndex, "必须为整数类型", true);
		}
	}
	private void checkFloatColumn(int sheetIndex, int columnIndex, int rowIndex, String val) {
		if(!floatPattern.matcher(val).matches()){
			setCheckResultMessage(sheetIndex, rowIndex,  columnIndex, "必须为数字类型", true);
		}
	}
	private void checkDateColumn(int sheetIndex, int columnIndex, int rowIndex, String val) {
		if(!datePattern.matcher(val).matches()){
			setCheckResultMessage(sheetIndex, rowIndex,  columnIndex, "日期格式 (yyyy-mm-dd)不正确", true);
		}
	}

	private void setCheckResultMessage(int sheetIndex, int rowIndex, int columnIndex, String msg, boolean commentFlag){
		if(workbook.getNumberOfSheets()<1){
			createWorkbook();			
		}
		HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
		HSSFRow row = sheet.getRow(rowIndex);
		
		HSSFCell cell = row.getCell(columnIndex);
		if(null == cell){
			cell = row.createCell(columnIndex);
		}
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.RED.index);
		cell.setCellStyle(style);
		if(commentFlag){
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6);
			HSSFPatriarch patr = sheet.createDrawingPatriarch();
			HSSFComment comment = patr.createCellComment(anchor);
			//设置注释内容
			comment.setString(new HSSFRichTextString(msg));
			comment.setAuthor("库存系统");
			cell.setCellComment(comment);
		}else{
			cell.setCellValue(msg);
		}
	}
	
	private void createWorkbook(){
		for(ExcelRowData rowData : excelDataList){
			HSSFSheet sheet = null;
			try {
				sheet = workbook.getSheetAt(rowData.getSheetIndex());
			} catch (IllegalArgumentException e1) {
				sheet = null;
			}
			if(sheet == null){
				sheet = workbook.createSheet("sheet" + rowData.getSheetIndex());
			}
			HSSFRow row = sheet.getRow(rowData.getRowIndex());
			if(row == null){
				row = sheet.createRow(rowData.getRowIndex());
			}
			for(int i=0;i<100;i++){
				try {
					String val = PropertyUtil.getProperty(rowData, "col" + i);
					if(null != val){					
						HSSFCell cell = row.createCell(i);
						cell.setCellValue(val);
					}
				} catch (Exception e) {
					e.printStackTrace();
//					System.out.println("获取数据出错");
				}
			}
		}
	}

	private void exportWorkbook(){
		try {
			// 设置导出文件名
			String fileName = new String(("检查结果"+DateUtil.formatSdf8(new Date())) .getBytes("GBK"), "ISO8859-1");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xls" + "\"");
			workbook.write(response.getOutputStream());
			response.getOutputStream().flush();
//			response.getOutputStream().close();
		} catch (Exception e) {
			throw new RuntimeException("导出excel出错");
		}
	}
	
//	private String getCellStringValue(Cell cell) {
//		if (cell == null)
//			return "";
//		String res = null;
//		switch (cell.getCellType()) {
//		case Cell.CELL_TYPE_BOOLEAN:
//			// 得到Boolean对象的方法
//			res = cell.getBooleanCellValue() + "";
//			break;
//		case Cell.CELL_TYPE_NUMERIC:
//			// 先看是否是日期格式
//			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
//				// 读取日期格式
//				res = cell.getDateCellValue() + "";
//			} else {
//				// 读取数字
//				res = cell.getNumericCellValue() + "";
//			}
//			break;
//		case Cell.CELL_TYPE_FORMULA:
//			// 读取公式
//			res = cell.getCellFormula() + "";
//			break;
//		case Cell.CELL_TYPE_STRING:
//			// 读取String
//			res = cell.getRichStringCellValue().toString() + "";
//			break;
//		}
//		return res.trim();
//	}
}
