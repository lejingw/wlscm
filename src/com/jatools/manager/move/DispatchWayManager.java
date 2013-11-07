package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.DispatchWay;

public interface DispatchWayManager {

	Pager getDispatchStylePageData(Map<String, String> condition);

	Pager getDispatchPricePageData(Map<String, String> condition);
	
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateDispatchStyle(DispatchWay dn, String userId);
	/**
	 * 检查记录是否存在
	 */
	public List<DispatchWay> checkDispatchStyleRepeat(DispatchWay dn);
	/**
	 * 删除
	 */
	public void deleteDispatchStyle(List<String> billIdList);
	/**
	 * 导入excel
	 */
	public void saveDispatchStyleFromExcel(String seqId, String userId);
	//-----------------------------------
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateDispatchPrice(DispatchWay dn, String userId);
	/**
	 * 检查记录是否存在
	 */
	public List<DispatchWay> checkDispatchPriceRepeat(DispatchWay dn);
	/**
	 * 删除
	 */
	public void deleteDispatchPrice(List<String> billIdList);
	/**
	 * 导入excel
	 */
	public void saveDispatchPriceFromExcel(String seqId, String userId);


    Pager getDispatchStyleReportPageData(Map<String, String> condition);

    Pager getDispatchPriceReportPageData(Map<String, String> condition);
}
