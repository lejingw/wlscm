package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jatools.web.util.StringUtil;

public class ReadRight {

	private static HSSFWorkbook readFile(String filename) throws IOException {
		return new HSSFWorkbook(new FileInputStream(filename));
	}

	private static String getValue(HSSFCell cell) {
		if (null == cell) {
			return null;
		}
		String value = null;
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			if(StringUtil.isNotEmpty("" + cell.getNumericCellValue())){
				value = "" + new Double(cell.getNumericCellValue()).intValue();
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		default:
		}
		return value;
	}

	public static void main(String[] args) throws Exception {
		String fileName = "doc/SCM模块功能.xls";
		List<RightMapping> list = new ArrayList<RightMapping>();
		{
			HSSFWorkbook wb = ReadRight.readFile(fileName);

			HSSFSheet sheet = wb.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
System.out.println(rows);
			String preToolbarCode = null;
			String preToolbarName = null;
			String preModelCode = null;
			int sort = 0;
			for (int i = 1; i <= rows; i++) {
				HSSFRow row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				HSSFCell cell4 = row.getCell(4);
				HSSFCell cell5 = row.getCell(5);
				HSSFCell cell6 = row.getCell(6);
				HSSFCell cell7 = row.getCell(7);
				HSSFCell cell8 = row.getCell(8);
				HSSFCell cell9 = row.getCell(9);
				HSSFCell cell10 = row.getCell(10);
				
				String toolbarCode = getValue(cell4);
				String toolbarName = getValue(cell5);
				String itemCode = getValue(cell6);
				String itemName = getValue(cell7);
				String reviewBillCode = getValue(cell8);
				String modelCode = getValue(cell9);
				String funcId = getValue(cell10);
if("BD".equals(reviewBillCode))
	System.out.println("--------");
				if (StringUtil.isEmpty(toolbarCode)) {
					if (StringUtil.isEmpty(preToolbarCode)) {
						continue;
					} else {
						toolbarCode = preToolbarCode;
						toolbarName = preToolbarName;
					}
				}
				if (StringUtil.isNotEmpty(toolbarCode)) {
					preToolbarCode = toolbarCode;
					preToolbarName = toolbarName;
					//如果不是功能行，则为审批行或者工具条行
					if (StringUtil.isEmpty(itemCode)) {
						sort = 0;
						//如果为工具条行
						if(StringUtil.isEmpty(reviewBillCode)){
							//如果已经配置模块编码
							if(StringUtil.isNotBlank(modelCode)){
								preModelCode = modelCode;
							}else{
								preModelCode = null;
							}
							continue;
						}else{
							//如果已经配置模块编码
							if(StringUtil.isNotBlank(modelCode)){
								preModelCode = modelCode;
							}else{
								preModelCode = null;
							}
						}
					}
				}
				if(StringUtil.isBlank(modelCode) && StringUtil.isNotEmpty(preModelCode)){
					modelCode = preModelCode;
				}
				RightMapping rightMapping = new RightMapping(toolbarCode,
						toolbarName, itemCode, itemName, reviewBillCode, ""
								+ (sort++), modelCode, funcId);
				list.add(rightMapping);
			}
		}
		for (RightMapping rm : list) {
			System.out.println(rm.toString());
		}

		{
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet();

			HSSFRow titleRow = sheet.createRow(0);
			setCellValue(titleRow.createCell(0), "TOOLBAR_CODE");
			setCellValue(titleRow.createCell(1), "TOOLBAR_NAME");
			setCellValue(titleRow.createCell(2), "ITEM_CODE");
			setCellValue(titleRow.createCell(3), "ITEM_NAME");
			setCellValue(titleRow.createCell(4), "MODEL_CODE");
			setCellValue(titleRow.createCell(5), "FUNC_ID");
			setCellValue(titleRow.createCell(6), "REVIEW_BILL_CODE");
			setCellValue(titleRow.createCell(7), "SORT");

			for (int k = 0; k < list.size(); k++) {
				HSSFRow row = sheet.createRow(k + 1);
				setCellValue(row.createCell(0), list.get(k).getToolbarCode());
				setCellValue(row.createCell(1), list.get(k).getToolbarName());
				setCellValue(row.createCell(2), list.get(k).getItemCode());
				setCellValue(row.createCell(3), list.get(k).getItemName());
				setCellValue(row.createCell(4), list.get(k).getModelCode());
				setCellValue(row.createCell(5), list.get(k).getFuncId());
				setCellValue(row.createCell(6), list.get(k).getReviewBillCode());
				setCellValue(row.createCell(7), list.get(k).getSort());
			}

			String fileName2 = "doc/库存与门户权限编码映射.xls";
			FileOutputStream stream = new FileOutputStream(fileName2);
			wb.write(stream);
			stream.close();
		}
	}

	private static void setCellValue(HSSFCell cell, String value) {
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}
}

class RightMapping {
	private String toolbarCode;
	private String toolbarName;
	private String itemCode;
	private String itemName;
	private String reviewBillCode;
	private String sort;
	private String modelCode;
	private String funcId;

	public RightMapping() {
	}

	public RightMapping(String toolbarCode, String toolbarName,
			String itemCode, String itemName, String reviewBillCode,
			String sort, String modelCode, String funcId) {
		this.toolbarCode = toolbarCode;
		this.toolbarName = toolbarName;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.reviewBillCode = reviewBillCode;
		this.sort = sort;
		this.modelCode = modelCode;
		this.funcId = funcId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getToolbarCode() {
		return toolbarCode;
	}

	public void setToolbarCode(String toolbarCode) {
		this.toolbarCode = toolbarCode;
	}

	public String getToolbarName() {
		return toolbarName;
	}

	public void setToolbarName(String toolbarName) {
		this.toolbarName = toolbarName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getReviewBillCode() {
		return reviewBillCode;
	}

	public void setReviewBillCode(String reviewBillCode) {
		this.reviewBillCode = reviewBillCode;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	@Override
	public String toString() {
		return "RightMapping [toolbarCode=" + toolbarCode + ", toolbarName="
				+ toolbarName + ", itemCode=" + itemCode + ", itemName="
				+ itemName + ", reviewBillCode=" + reviewBillCode + ", sort="
				+ sort + ", modelCode=" + modelCode + ", funcId=" + funcId
				+ "]";
	}

}
