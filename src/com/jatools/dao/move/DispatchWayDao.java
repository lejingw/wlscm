package com.jatools.dao.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.DispatchWay;


public interface DispatchWayDao {


	Pager getDispatchStylePageData(Map<String, String> condition);

	Pager getDispatchPricePageData(Map<String, String> condition);
	
	/**
	 * 保存
	 */
	public void saveDispatchStyle(DispatchWay dn);
	/**
	 * 修改
	 */
	public void updateDispatchStyle(DispatchWay dn);

	/**
	 * 检查记录是否存在
	 */
	public List<DispatchWay> checkDispatchStyleRepeat(DispatchWay dn);
	/**
	 * 删除
	 */
	public void deleteDispatchStyle(List<String> billIdList);
	public void saveDispatchStyleFromExcel(String seqId, String userId);
	//-------------------------------------

	/**
	 * 保存
	 */
	public void saveDispatchPrice(DispatchWay dn);
	/**
	 * 修改
	 */
	public void updateDispatchPrice(DispatchWay dn);

	/**
	 * 检查记录是否存在
	 */
	public List<DispatchWay> checkDispatchPriceRepeat(DispatchWay dn);
	/**
	 * 删除
	 */
	public void deleteDispatchPrice(List<String> billIdList);
	public void saveDispatchPriceFromExcel(String seqId, String userId);


    Pager getDispatchStyleReportPageData(Map<String, String> condition);

    Pager getDispatchPriceReportPageData(Map<String, String> condition);
}
