package com.jatools.web.view.pur;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.UserCache;
import com.jatools.web.cache.VendorCache;
import com.jatools.web.util.DateUtil;

public class ExportPurchaseBillToExcelUtil {
	private String picBasePath = null;
	private ExcelData excelData;
	private PurchaseHead head;
	private Vector<String> validUrlVector = new Vector<String>();
	private Vector<String> invalidUrlVector = new Vector<String>();
	
	public void setPicBasePath(String picBasePath) {
		this.picBasePath = picBasePath;
	}

	public void setHead(PurchaseHead head) {
		this.head = head;
	}

	public void setExcelData(ExcelData excelData) {
		this.excelData = excelData;
	}

	private synchronized boolean isAvailUrl(String url){
		if(invalidUrlVector.contains(url)){
			return false;
		}
		if(validUrlVector.contains(url)){
			return true;
		}
		HttpURLConnection con = null;
		try {
			HttpURLConnection.setFollowRedirects(false);
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setConnectTimeout(1000);
			con.setRequestMethod("HEAD");
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
				validUrlVector.add(url);
				return true;
			}else{
				invalidUrlVector.add(url);
				return false;
			}
		} catch (Exception e) {
			invalidUrlVector.add(url);
			return false;
		}finally{
			if(null != con){
				con.disconnect();
				con = null;
			}
		}
	}
	
	private int loadPicture(String path, HSSFWorkbook wb) {
		try {
			int pictureIndex;
			BufferedInputStream fis = null;
			ByteArrayOutputStream bos = null;
			try {
				fis = new BufferedInputStream(new URL(path).openStream());
				bos = new ByteArrayOutputStream();
//				int c;
//				while ((c = fis.read()) != -1) {
//					bos.write(c);
//				}
				byte[] b = new byte[40960];
				int length;
				while((length = fis.read(b)) != -1){
					bos.write(b, 0, length);
				}
				pictureIndex = wb.addPicture(bos.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG);
				fis.close();
				bos.close();
				fis = null;
				bos = null;
			} finally {
				if (fis != null)
					fis.close();
				if (bos != null)
					bos.close();
			}
			return pictureIndex;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
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
		if(null != head){
			HSSFRow subrow = sheet.createRow(2);
			for (short i = 0; i < 5; i++) {
				sheet.addMergedRegion(new CellRangeAddress(2, 2, (i*2), (i*2+1)));
			}
			
			HSSFCell typeCell = subrow.createCell(0);
			typeCell.setCellValue(head.getBillno());
			
			typeCell = subrow.createCell(2);
			typeCell.setCellValue(head.getCycleTypeName());
			
			typeCell = subrow.createCell(4);
			typeCell.setCellValue(head.getDodate());
			
			typeCell = subrow.createCell(6);
			typeCell.setCellValue(VendorCache.getInstance().getNameById(head.getVendorId()));
			
			typeCell = subrow.createCell(8);
			typeCell.setCellValue(UserCache.getInstance().getNameById(head.getPurEmpids()));
		}
		
		int dataStartRow = 6;
		List<Map<String, Object>> dataList = excelData.getData();
		for(Map<String, Object> map : dataList){
			HSSFRow subrow = sheet.createRow(dataStartRow);
			subrow.setHeight((short) 1600);
			for(int i=0;i< excelData.getColumnName().length;i++){
				HSSFCell typeCell = subrow.createCell(i);
				String columnName = excelData.getColumnName()[i];
				if("smallGraph".equals(columnName)){
					if(null != map.get(columnName)){
						String url = picBasePath + map.get(columnName);
						if(!isAvailUrl(url)){
//							System.out.println("===============连接无效：" + url);
							continue;
						}
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) i, dataStartRow, (short)i, dataStartRow);
						anchor.setAnchorType(2);
						HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
						patriarch.createPicture(anchor, loadPicture(url, wb));
					}
				}else{
					Object object = map.get(columnName);
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
			dataStartRow++;
		}
	}

	private void createHeader(HSSFWorkbook wb, HSSFSheet sheet) {
		HSSFFont font = getFont(wb);
		HSSFCellStyle titleStyle = getTitleStyle(wb, font);
		HSSFFont fontHeader = getFontStyle(wb);
		HSSFCellStyle headerStyle = getHeaderStyle(wb);
		headerStyle.setFont(fontHeader);// 字体

		// 表头
		String[] header = excelData.getColumnName();
		for (short i = 0; i < header.length; i++) {
            if(excelData.getColumnWidthMap().containsKey(excelData.getColumnName()[i])){
            	sheet.setColumnWidth(i, 1024 * excelData.getColumnWidthMap().get(excelData.getColumnName()[i]));
            }else{
            	sheet.setColumnWidth(i, 1024 * 2);
            }
		}
		// 标题
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (excelData.getColumnName().length - 1)));
		cell.setCellStyle(titleStyle);
		cell.setCellValue(excelData.getTitle());

		if(null != head){
			row = sheet.createRow(1);
			for (short i = 0; i < 5; i++) {
				sheet.addMergedRegion(new CellRangeAddress(1, 1, i*2, (i*2+1)));
			}
			String[] purchaseHeadTitle = new String[]{"订单编号","周期","采购日期","供应商","采购人员"};
			for (short i = 0; i < purchaseHeadTitle.length; i++) {
				cell = row.createCell(i*2);
				cell.setCellStyle(headerStyle);
				cell.setCellValue(purchaseHeadTitle[i]);
			}
		}
		
		row = sheet.createRow(5);
		String[] titles = excelData.getColumnTitle();
		for (int i = 0; i < titles.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(titles[i]);
		}
	}

	private HSSFFont getFont(HSSFWorkbook wb) {
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 16);// 字号
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		return font;
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

	// 标题样式
	private HSSFCellStyle getTitleStyle(HSSFWorkbook wb, HSSFFont font) {
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFont(font);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		return titleStyle;
	}
}
