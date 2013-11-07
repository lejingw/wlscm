package com.jatools.web.view.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.tag.LabelTagImportManager;
import com.jatools.vo.tag.LabelTagImportHead;
import com.jatools.vo.tag.LabelTagImportLine;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.cache.CacheSingletonIntf;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.UserCache;
import com.jatools.web.form.tag.LabelTagImportForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public class LabelTagImportController extends BaseMultiActionController {

private Logger logger = Logger.getLogger(LabelTagImportController.class);
	
	private static final String LIST_VM = "tag/labelTagImport_list";
	private static final String EDIT_VM = "tag/labelTagImport_edit";
	private static final String SHOW_VM = "tag/labelTagImport_show";
	private static final String PARAMS[] = {"billno", "orgId", "status", "startDate", "endDate", "createId"};
	
	private LabelTagImportManager labelTagImportManager;

	public void setLabelTagImportManager(LabelTagImportManager labelTagImportManager) {
		this.labelTagImportManager = labelTagImportManager;
	}

	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		LabelTagImportForm form = new LabelTagImportForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
			condition.put("userId", CommonUtil.getSessionUserId(req));
			Pager pager = labelTagImportManager.getLabelTagImportHeadData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
            CommonUtil.resetCondition(this, req);
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(LIST_VM, "form", form);
	}
	
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		String id = CommonUtil.getParameterNull(req, "headid");
		LabelTagImportForm form = new LabelTagImportForm();
		String vm_path = EDIT_VM;
		try {
			if(StringUtil.isNotBlank(id)){
				LabelTagImportHead labelTagImportHead = this.labelTagImportManager.getLabelTagImportHead(id);
				//List<LabelTagImportLine> lines = this.labelTagImportManager.getLabelTagImportLineList(id);
				Map<String, String> condition = CommonUtil.getConditionForPage(req, "searchOrnaCode", "headid");
				Pager pager = this.labelTagImportManager.getLabelTagImportLineData(condition);
				beforeView(pager);
				form.setImportHead(labelTagImportHead);
				form.setPager(pager);
				form.setCondition(condition);
				if(labelTagImportHead != null){
					String curOrgId = CommonUtil.getSessionOrgId(req);
					if(curOrgId.equals(labelTagImportHead.getOrgId())){
						vm_path = EDIT_VM;
					} else {
						vm_path = SHOW_VM;
					}
				}
			} else {
				form.setSuccessfulFlag(Boolean.FALSE);
				form.setMessage("加载失败");
			}
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取数据出错");
		}
		return new ModelAndView(vm_path, "form", form);
	}
	
	public ModelAndView importExcel(HttpServletRequest req, HttpServletResponse res) {
		String seqId = "";
		try {
			Map<String, String> result = this.getLinesFromExcel(req);
			seqId = result.get("seqId");
			String ornaDBs = (String)result.get("ornaDBs");
			if(StringUtil.isNotBlank(seqId)){
				LabelTagImportHead head = new LabelTagImportHead();
				String orgId = CommonUtil.getSessionOrgId(req);
				head.setOrgId(orgId);
				head.setStatus(DictConstant.BILL_STATUS_SAVE);
				head.setCreatedate(com.jatools.web.util.DateUtil.getCurrentDate18());
				head.setCreateid(CommonUtil.getSessionUserId(req));
				head.setUpdatedate(com.jatools.web.util.DateUtil.getCurrentDate18());
				head.setUpdateid(CommonUtil.getSessionUserId(req));
				this.labelTagImportManager.saveOrUpdateLabelTagImportHead(head, seqId, CommonUtil.getSessionUserId(req));
				res.getWriter().print("{'isSuccess':'true', 'headid':"+head.getHeadid()+", 'ornaDBs':'"+ornaDBs+"'}");
			} else {
				res.getWriter().print("{'isSuccess':'false', 'msg':'上传失败'}");
			}
		} catch (IOException e) {
			logger.error(e);
			return null;
		} catch (Exception e) {
			logger.error(e);
			try{
				if(StringUtil.isNotBlank(e.getMessage())){
					res.getWriter().print("{'isSuccess':'false', 'msg':'"+e.getMessage()+"'}");
				} else {
					res.getWriter().print("{'isSuccess':'false', 'msg':'上传失败'}");
				}
			}catch(Exception e1){
				logger.error(e1);
			}
			return null;
		}finally {
			if(StringUtil.isNotBlank(seqId)){
				this.labelTagImportManager.deleteTempLineBySeqId(seqId);
			}
		}
		return null;
	}
	
	
	private Map<String, String> getLinesFromExcel(HttpServletRequest req) throws Exception{
		Map<String, String> res = new HashMap<String, String>();
		List<LabelTagImportLine> lines = new ArrayList<LabelTagImportLine>();
		List<String> ornaList = new ArrayList<String>();// 饰品编码
		List<String> ornaDBs = new ArrayList<String>();// 饰品编码在同一个excel中重复的列表
		String seqId = "";
		FileItemStream fis;
		try {
			seqId = this.labelTagImportManager.getLineTempSeq();
			if (ServletFileUpload.isMultipartContent(req)) {
				DiskFileItemFactory dff = new DiskFileItemFactory(); //创建该对象
				//dff.setRepository(tmpDir);//指定上传文件的临时目录
				dff.setSizeThreshold(1024000); //指定在内存中缓存数据大小,单位为byte
				ServletFileUpload sfu = new ServletFileUpload(dff); //创建该对象
				sfu.setFileSizeMax(5000000); //指定单个上传文件的最大尺寸
				sfu.setSizeMax(10000000); //指定一次上传多个文件的总尺寸
				FileItemIterator fii = sfu.getItemIterator(req); //解析request 请求,并返回FileItemIterator集合
				while (fii.hasNext()) {
					fis = fii.next(); //从集合中获得一个文件流
					if (!fis.isFormField() && fis.getName().length() > 0 && "excel_file".equals(fis.getFieldName())) { //过滤掉表单中非文件域
						//String fileName = fis.getName();//获得上传文件的文件名
						Workbook wb = null;
						try {
							wb = new HSSFWorkbook(fis.openStream());
							//wb = WorkbookFactory.create(fis.openStream());
						} catch (IOException e) {
							e.printStackTrace();
						}
						Sheet sheet = wb.getSheetAt(0);   
						//logger.info("---------------------------------"+com.jatools.web.util.DateUtil.getCurrentDate18()+"---------------------------------");
						int rows = sheet.getPhysicalNumberOfRows();
						for(int i=1; i<rows; i++){
							Row row = sheet.getRow(i);
							if(row == null) continue;
						
							Cell cell = row.getCell(0);
							String ornaCode = this.getCellStringValue(cell);
							if(StringUtil.isNotBlank(ornaCode)){
								if(ornaList.contains(ornaCode)){
									if(!ornaDBs.contains(ornaCode)){
										ornaDBs.add(ornaCode);
									}
								} else {
									LabelTagImportLine line = new LabelTagImportLine();
									line.setOrnaCode(ornaCode);
									
									cell = row.getCell(1);
									String newOrnaDesc = this.getCellStringValue(cell);
									line.setNewOrnaName(newOrnaDesc);
									
									cell = row.getCell(2);
									Double newBasicPrice = this.getCellDoubleValue(cell);
									line.setNewBasicPrice(StringUtil.formatDouble(newBasicPrice, 4));
									
									cell = row.getCell(3);
									Double newAmount = this.getCellDoubleValue(cell);
									line.setNewAmount(StringUtil.formatDouble(newAmount, 4));
									
									cell = row.getCell(4);
									Double newSpecialWorkPrice = this.getCellDoubleValue(cell);
									line.setNewSpecialWorkPrice(StringUtil.formatDouble(newSpecialWorkPrice, 4));
									
									cell = row.getCell(5);
									String updateReason = this.getCellStringValue(cell);
									line.setUpdateReason(updateReason);
									
									cell = row.getCell(6);
									String groupNo = this.getCellStringValue(cell);
									if(StringUtil.isBlank(groupNo)){
										groupNo = "-1";
									}
									line.setGroupNo(groupNo);
									line.setSeqId(seqId);
									lines.add(line);
									ornaList.add(ornaCode);
								}
							}  
						} 
					}
					// 关闭资源
				}
			}
			if(CollectionUtils.isEmpty(ornaList)){
				throw new RuntimeException("尚未填写任何待导入的饰品。");
			}
			//logger.info("---------------------------------"+com.jatools.web.util.DateUtil.getCurrentDate18()+"---------------------------------");
			// 插入临时表
			this.labelTagImportManager.insertLinesTemp(lines);
			//logger.info("---------------------------------"+com.jatools.web.util.DateUtil.getCurrentDate18()+"---------------------------------");
			// 1、判断饰品是否在现有量中
			List<String> invalidOrnaCodes = this.labelTagImportManager.getInValidOrnaCodes(seqId);
			
			if(invalidOrnaCodes.size()>0){
				String invalidOrnas = this.listToString2(invalidOrnaCodes);
				this.labelTagImportManager.deleteTempLineBySeqId(seqId);
				throw new RuntimeException("请删除无效饰品\\n【"+invalidOrnas+"】\\n重新导入。");
			}
			
			// 2、判断饰品是否已经在其他单据中申请过，尚未结束
			List<String> existsOrnas = this.labelTagImportManager.getExistsOrnaCodes(seqId);
			if(CollectionUtils.isNotEmpty(existsOrnas)){
				String existsOrnaStr = this.listToString2(existsOrnas);
				this.labelTagImportManager.deleteTempLineBySeqId(seqId);
				throw new RuntimeException("请删除其他单据中已经存在的饰品\\n【"+existsOrnaStr+"】\\n重新导入。");
			}
		} catch (Exception e) {
			logger.error(e);
			if(StringUtil.isNotBlank(seqId)){
				this.labelTagImportManager.deleteTempLineBySeqId(seqId);
			}
			throw e;
		}
		res.put("seqId", seqId);
		res.put("ornaDBs", this.listToString2(ornaDBs));
		return res;
	}
	
	
	private String listToString2(List<String> list){
		StringBuffer ornas = new StringBuffer();
		int i=0;
		for(String str : list){
			if(ornas.length() > 0){
				ornas.append(",").append(str);
			} else {
				ornas.append(str);
			}
			if(i>50){
				ornas.append("...");
				break;
			}
			i++;
			if(i%3==0 && i != list.size()){
				ornas.append("\\n");
			}
		}
		return ornas.toString();
	}
	
	private Double getCellDoubleValue(Cell cell) throws Exception{
		if(cell == null) return null;
		String res = this.getCellStringValue(cell);
		if(StringUtil.isNotBlank(res)){
			try{
				return Double.valueOf(res);
			} catch(Exception e){
				int row = cell.getRowIndex()+1;
				int col = cell.getColumnIndex()+1;
				throw new Exception("第"+row+"行,第"+col+"列 数据格式错误.");
			}
		}
		return null;
	}
	
	private String getCellStringValue(Cell cell){
		String res = "";
		if(cell == null) return res;
		switch(cell.getCellType()){   
	        case Cell.CELL_TYPE_BOOLEAN:   
	            //得到Boolean对象的方法   
	        	res = cell.getBooleanCellValue()+"";   
	            break;   
	        case Cell.CELL_TYPE_NUMERIC:   
	            //先看是否是日期格式   
	            if(DateUtil.isCellDateFormatted(cell)){   
	                //读取日期格式   
	            	res = cell.getDateCellValue()+"";   
	            }else{   
	                //读取数字   
	            	res = cell.getNumericCellValue()+"";   
	            }   
	            break;   
	        case Cell.CELL_TYPE_FORMULA:   
	            //读取公式   
	        	res = cell.getCellFormula()+"";   
	            break;   
	        case Cell.CELL_TYPE_STRING:   
	            //读取String   
	            res = cell.getRichStringCellValue().toString()+"";   
	            break;                     
	    }   
		return res.trim();
	}
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, PARAMS);
		condition.put("userId", CommonUtil.getSessionUserId(req));
		Pager pager = labelTagImportManager.getLabelTagImportHeadData(condition);
		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("标签导入申请单");
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"orgId", "createdate"}, new Integer[]{4, 3});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"单据编号", "组织",  "创建人", "创建时间", "修改人", "修改时间", "状态"});
		//添加列name
		excelData.setColumnName(new String[]{"billno", "orgId", "createid", "createdate", "updateid", "updatedate", "status"});
		//设置对应的缓冲数据
		excelData.addSingletonDisplayColumns(new String[]{"orgId", "createid", "updateid"},
					new CacheSingletonIntf[]{OrgCache.getInstance(), UserCache.getInstance(), UserCache.getInstance()});
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"status"},
					new String[]{DictConstant.BILL_STATUS});
		
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
		return null;//返回null，不跳转至任何页面
	}

	private void beforeView(Pager pager){
		if(CollectionUtils.isNotEmpty(pager.getPageData())){
			for(Object obj : pager.getPageData()){
				LabelTagImportLine line = (LabelTagImportLine)obj;
				if(StringUtil.isNotBlank(line.getNewBasicPrice())){
					line.setNewBasicPrice(StringUtil.formatDouble(line.getNewBasicPrice(), 4));
				}
				if(StringUtil.isNotBlank(line.getNewAmount())){
					line.setNewAmount(StringUtil.formatDouble(line.getNewAmount(), 4));
				}
				if(StringUtil.isNotBlank(line.getNewSpecialWorkPrice())){
					line.setNewSpecialWorkPrice(StringUtil.formatDouble(line.getNewSpecialWorkPrice(), 4));
				}
			}
		}
	}
	
	
}
