package com.jatools.manager.report.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jatools.common.ExcelUtil;
import com.jatools.common.Pager;
import com.jatools.common.excel.ExcelCheckMode;
import com.jatools.common.excel.ExcelColumnEnum;
import com.jatools.common.excel.ExcelSelfDefinedCheck;
import com.jatools.dao.report.ReportDao;
import com.jatools.manager.report.ReportManager;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelRowData;
import com.jatools.web.util.StringUtil;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportManagerImpl implements ReportManager {
	private ReportDao reportDao;

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	/**
	 * 执行sql查询
	 */
	public List<Map> executeQurey(String sql){
		return reportDao.executeQurey(sql);
	}
	
	/**
	 * 执行sql查询获取分页数据
	 */
	public Pager executeQueryPageData(String sql, String start, String limit){
		return reportDao.executeQueryPageData(sql, start, limit);
	}

    @Override
    public void saveOrnaMoveType(String seqId) {
        this.reportDao.saveOrnaMoveType(seqId);
    }


    public ModelAndView importOrnaMoveType(HttpServletRequest req, HttpServletResponse res) {
        String fieldName = "excel_file";
        ExcelUtil excelUtil = new ExcelUtil(req, res, excelUtilManager);
        excelUtil.saveExcelData(fieldName);
        boolean successFlag = excelUtil.checkExcelData(new ExcelCheckMode[] {
                new ExcelCheckMode(0, 0, new ExcelSelfDefinedCheck(){
                    @Override
                    public boolean check(int rowIndex, String value,
                                         ExcelRowData rowData, List<ExcelRowData> allData) {
                        String ornaCode = getNullString(rowData.getCol0(), "");
                        for (ExcelRowData data : allData){
                            if(rowIndex != data.getRowIndex()){
                                if(ornaCode.equals(data.getCol0()) ){
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                    @Override
                    public String getErrorMsg(int rowIndex, String value,
                                              ExcelRowData rowData, List<ExcelRowData> allData) {
                        String ornaCode = getNullString(rowData.getCol0(), "");
                        for (ExcelRowData data : allData){
                            if(rowIndex != data.getRowIndex()){
                                if(ornaCode.equals(data.getCol0()) ){
                                    return "第" + (rowIndex+1) + "行和第" + (data.getRowIndex()+1) + "行 饰品编码重复";
                                }
                            }
                        }
                        return null;
                    }
                    private String getNullString(String str, String defaultStr){
                        if(StringUtil.isNotBlank(str)) return str;
                        return defaultStr;
                    }}),
                new ExcelCheckMode(0, 0, false, ExcelColumnEnum.STRING_COLUMN)
        });
        if(successFlag){
            excelUtil.convertDbrefrenceToId();
            reportDao.saveOrnaMoveType(excelUtil.getSeqId());
            try {
                res.getWriter().print("{'isSuccess':'true'}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private ExcelUtilManager excelUtilManager;

    public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
        this.excelUtilManager = excelUtilManager;
    }
}
