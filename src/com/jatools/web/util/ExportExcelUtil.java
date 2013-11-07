package com.jatools.web.util;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.jatools.vo.util.ExcelData;

public class ExportExcelUtil {
	private ExcelData excelData;
	
	public void setExcelData(ExcelData excelData) {
		this.excelData = excelData;
	}
	
	public void export(HttpServletResponse response){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		createHeader(wb, sheet);
		createBody(wb, sheet);
		try {
			// 设置导出文件名
			String fileName = new String((DateUtil.formatSdf8(new Date()) + excelData.getTitle()) .getBytes("GBK"), "ISO8859-1");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xls" + "\"");
			wb.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			throw new RuntimeException("导出excel出错");
		}
	}

	private void createBody(HSSFWorkbook wb, HSSFSheet sheet) {
		int dataStartRow = 2;
		List<Map<String, Object>> dataList = excelData.getData();
		for(Map<String, Object> map : dataList){
			HSSFRow subrow = sheet.createRow(dataStartRow++);
			for(int i=0;i< excelData.getColumnName().length;i++){
				HSSFCell typeCell = subrow.createCell(i);
				Object object = map.get(excelData.getColumnName()[i]);
				if(null != object){
					if(object instanceof String){
						typeCell.setCellValue((String)object);
					}else if(object instanceof Double){
						typeCell.setCellValue((Double)object);
					}else if(object instanceof Integer){
						typeCell.setCellValue((Integer)object);
					}
				}
			}
		}
	}
	
	private void createHeader(HSSFWorkbook wb, HSSFSheet sheet) {
		HSSFFont fontHeader = getFontStyle(wb);
		HSSFCellStyle headerStyle = getHeaderStyle(wb);
		headerStyle.setFont(fontHeader);// 字体
		
		// 表头
		String[] header = excelData.getColumnName();
		for (short i = 0; i < header.length; i++) {
            if(excelData.getColumnWidthMap().containsKey(excelData.getColumnName()[i])){
            	sheet.setColumnWidth(i, 2048 * excelData.getColumnWidthMap().get(excelData.getColumnName()[i]));
            }else{
            	sheet.setColumnWidth(i, 2048 * 2);
            }
		}
		// 标题
		HSSFRow row = sheet.createRow((short) 0);
		HSSFCell cell = row.createCell(0);
//		Region region = new Region((short) 0, (short) 0, (short) 0, (short) (excelData.getColumnName().length - 1));
//		sheet.addMergedRegion(region);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelData.getColumnName().length - 1));
		cell.setCellValue(excelData.getTitle());

		row = sheet.createRow(1);
		String[] titles = excelData.getColumnTitle();
		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(titles[i]);
		}
	}

	private HSSFFont getFontStyle(HSSFWorkbook wb) {
		HSSFFont fontHeader = wb.createFont();
		fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		return fontHeader;
	}

	// 表头样式
	private HSSFCellStyle getHeaderStyle(HSSFWorkbook wb) {
		HSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		return headerStyle;
	}
}
