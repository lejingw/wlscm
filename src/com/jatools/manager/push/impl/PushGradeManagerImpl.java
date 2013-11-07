package com.jatools.manager.push.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jatools.common.CommonUtil;
import com.jatools.common.ExcelUtil;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.excel.*;
import com.jatools.dao.push.PushGradeDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.PushGradeManager;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.push.PushGrade;
import com.jatools.vo.util.ExcelRowData;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PushGradeManagerImpl extends BaseManager implements PushGradeManager {
	private CommonDao commonDao;
	private PushGradeDao pushGradeDao;

	public void setPushGradeDao(PushGradeDao pushGradeDao) {
		this.pushGradeDao = pushGradeDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public Pager getPushGradePageData(Map<String, String> condition){
		return pushGradeDao.getPushGradePageData(condition);
	}
	
	/**
	 * 保存或修改
	 * 
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdatePushGrade(PushGrade dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			pushGradeDao.savePushGrade(dn);
		}else{
			pushGradeDao.updatePushGrade(dn);
		}
	}
	/**
	 * 检查记录是否存在
	 * @param dn
	 * @return
	 */
	public List<PushGrade> checkPushGradeRepeat(PushGrade dn){
		return pushGradeDao.checkPushGradeRepeat(dn);
	}
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deletePushGrade(List<String> billIdList){
		pushGradeDao.deletePushGrade(billIdList);
	}


    public void importExcel(HttpServletRequest req, HttpServletResponse res) {
        String fieldName = "excel_file";
        ExcelUtil excelUtil = new ExcelUtil(req, res, excelUtilManager);
        excelUtil.saveExcelData(fieldName);
        boolean successFlag = excelUtil.checkExcelData(new ExcelCheckMode[] {
                new ExcelCheckMode(0, 5, false, ExcelColumnEnum.FLOAT_COLUMN),
                new ExcelCheckMode(0, 0, new ExcelSelfDefinedCheck(){
                    @Override
                    public boolean check(int rowIndex, String value,
                                         ExcelRowData rowData, List<ExcelRowData> allData) {
                        String itemClass = getNullString(rowData.getCol0(), "");
                        String ornaClass = getNullString(rowData.getCol1(), "");
                        String analysis = getNullString(rowData.getCol2(), "");
                        String colorGradeId = getNullString(rowData.getCol3(), "");
                        String cleanId = getNullString(rowData.getCol4(), "");

                        for (ExcelRowData data : allData){
                            if(rowIndex != data.getRowIndex()){
                                // 大类
                                if(itemClass.equals(data.getCol0()) && ornaClass.equals(data.getCol1()) && analysis.equals(data.getCol2())
                                        && colorGradeId.equals(data.getCol3()) && cleanId.equals(data.getCol4())){
                                    return false;
                                }
                            }
                        }
                        return true;
                    }

                    @Override
                    public String getErrorMsg(int rowIndex, String value, ExcelRowData rowData, List<ExcelRowData> allData) {
                        String itemClass = getNullString(rowData.getCol0(), "");
                        String ornaClass = getNullString(rowData.getCol1(), "");
                        String analysis = getNullString(rowData.getCol2(), "");
                        String colorGradeId = getNullString(rowData.getCol3(), "");
                        String cleanId = getNullString(rowData.getCol4(), "");

                        for (ExcelRowData data : allData){
                            if(rowIndex != data.getRowIndex()){
                                // 大类
                                if(itemClass.equals(data.getCol0()) && ornaClass.equals(data.getCol1()) && analysis.equals(data.getCol2())
                                        && colorGradeId.equals(data.getCol3()) && cleanId.equals(data.getCol4())){
                                    return "第" + (rowIndex+1) + "行和第" + (data.getRowIndex()+1) + "数据重复";
                                }
                            }
                        }
                        return null;
                    }

                    private String getNullString(String str, String defaultStr){
                        if(com.jatools.web.util.StringUtil.isNotBlank(str)) return str;
                        return defaultStr;
                    }}),
                new ExcelCheckMode(0, 0, false, new ExcelDbrefrenceCheck("jas_bd_item_class", "item_class_id", "item_class_dsc"), true),
                new ExcelCheckMode(0, 1, false, new ExcelDbrefrenceCheck("jas_bd_orna_class", "orna_class_id", "orna_class_dsc"), true),
                new ExcelCheckMode(0, 2, false, new ExcelDbrefrenceCheck2("jas_bd_analysis_arange", "analysis_arange_id", "summarydescription",
                        " exists (select 1 from jas_bd_item_class ic where ic.ITEM_CLASS_ID=b.ITEM_CLASS_ID and ic.ITEM_CLASS_DSC =a.col0 )" +
                                " and exists (select 1 from jas_bd_orna_class oc where oc.ORNA_CLASS_ID=b.ORNA_CLASS_ID and oc.ORNA_CLASS_DSC =a.col1 )" +
                                " and b.archiveflag = 0",
                        " b.item_class_id = a.col0" +
                                " and b.orna_class_id = a.col1" +
                                " and b.archiveflag = 0"), true),
                new ExcelCheckMode(0, 3, false, new ExcelDbrefrenceCheck("jat_sys_dict_item", "item_key", "item_value", " b.entry_code = 'diacolorgrade' "), true),
                new ExcelCheckMode(0, 4, false, new ExcelDbrefrenceCheck("jat_sys_dict_item", "item_key", "item_value", " b.entry_code = 'diaclean' "), true)
        });
        if(successFlag){
            excelUtil.convertDbrefrenceToId();
            String userId = CommonUtil.getSessionUserId(req);
            pushGradeDao.savePushGradeFromExcel(excelUtil.getSeqId(), userId);

            try {
                res.getWriter().print( "{'isSuccess':'true'}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ExcelUtilManager excelUtilManager;

    public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
        this.excelUtilManager = excelUtilManager;
    }
}
