package com.jatools.dao.push.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.BaseDao;
import com.jatools.dao.push.GatherSetDao;
import com.jatools.vo.push.GatherSetGrade;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.vo.push.GatherSetSize;
import com.jatools.vo.push.GatherSetStyle;
import com.jatools.vo.push.StyleWearSizeRate;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class GatherSetDaoImpl extends BaseDao implements GatherSetDao {

	public Pager<GatherSetHead> getGatherSetPageData(
			Map<String, String> condition) {
		return executeQueryForPager("GatherSet.getGatherSetPageData",
				"GatherSet.getGatherSetTotalCount", condition);
	}

	public void saveGatherSetHead(GatherSetHead head) {
		executeInsert("GatherSet.saveGatherSetHead", head);
	}

	public void updateGatherSetHead(GatherSetHead head) {
		executeUpdate("GatherSet.updateGatherSetHead", head);
	}

	public GatherSetHead getGatherSetHead(String headid) {
		return (GatherSetHead) executeQueryForObject(
				"GatherSet.getGatherSetHead", headid);
	}

	/**
	 * 获取当前的畅销款铺货占比
	 * @return
	public String getCurrentSaleDisRate() {
		return (String) executeQueryForObject(
				"GatherSet.getCurrentSaleDisRate", null);
	}
	 */

	/**
	 * 获取当前的畅销款周转占比
	 * @return
	public String getCurrentSaleTurnRate() {
		return (String) executeQueryForObject(
				"GatherSet.getCurrentSaleTurnRate", null);
	}
	 */
	public List<GatherSetLine> getGatherSetLineList(String headid){
		return executeQueryForList("GatherSet.getGatherSetLineList", headid);
	}
	public GatherSetLine getGatherSetLine(String lineid){
		return (GatherSetLine) executeQueryForObject("GatherSet.getGatherSetLine", lineid);
	}
	/**
	 * 修改类别下单数量
	 * @param lineid
	 * @param newOrderNum
	 */
	public void updateLineOrderNum(String lineid, String newOrderNum){
		Map<String, String> map =  new HashMap<String, String>();
		map.put("newOrderNum", newOrderNum);
		map.put("lineid", lineid);
		executeUpdate("GatherSet.updateLineOrderNum", map);
	}

	public boolean checkGatherSetLine(GatherSetLine line){
		Integer count = (Integer) executeQueryForObject("GatherSet.checkGatherSetLine", line);
		return count<1;
	}
	public String saveGatherSetLine(GatherSetLine line){
		return (String) executeInsert("GatherSet.saveGatherSetLine", line);
	}

	public void deleteGatherSetLine(String lineid){
		delete("GatherSet.deleteGatherSetLine", lineid);
	}
	public void deleteGatherSetLineByHeadId(String headid){
		delete("GatherSet.deleteGatherSetLineByHeadId", headid);
	}
	/**
	 * 获取材质
	 */
	public List<SelectorOption> getQualitySelectOption(String lineid){
		return executeQueryForList("GatherSet.getQualitySelectOption", lineid);
	}
	/**
	 * 获取款式行
	 */
	public List<GatherSetStyle> getGatherSetStyle(String lineid){
		return executeQueryForList("GatherSet.getGatherSetStyle", lineid);
	}
	/**
	 * 获取选择款式分页数据
	 * @param saleable
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pager getSelectStylePageData(boolean saleable, String lineid, String start, String limit){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("start", start);
		condition.put("limit", limit);
		condition.put("lineid", lineid);
		condition.put("salableFlag", saleable?"1":"0");
		return executeQueryForPager("GatherSet.getSelectStylePageData", "GatherSet.getSelectStyleTotalCount", condition);
	}
	public void saveGatherSetStyle(List<GatherSetStyle> addLineList){
		executeBatchInsert("GatherSet.saveGatherSetStyle", addLineList);
	}

	public void updateGatherSetStyle(List<GatherSetStyle> updateLineList){
		executeBatchUpdate("GatherSet.updateGatherSetStyle", updateLineList);
	}

	public void deleteGatherSetStyle(String listId){
		delete("GatherSet.deleteGatherSetStyle", listId);
	}
	public void deleteGatherSetStyleByLineId(String headid){
		delete("GatherSet.deleteGatherSetStyleByLineId", headid);
	}
	public void deleteGatherSetStyleByHeadId(String headid){
		delete("GatherSet.deleteGatherSetStyleByHeadId", headid);
	}

	public List<GatherSetGrade> getGatherSetGrade(String lineid){
		return executeQueryForList("GatherSet.getGatherSetGrade", lineid);
	}

	public List<GatherSetSize> getGatherSetSize(String lineid){
		return executeQueryForList("GatherSet.getGatherSetSize", lineid);
	}
	
	public void saveGatherSetGrade(GatherSetGrade grade){
		executeInsert("GatherSet.saveGatherSetGrade", grade);
	}
	public void saveGatherSetSize(GatherSetSize size){
		executeInsert("GatherSet.saveGatherSetSize", size);
	}
	public void updateGatherSetGrade(GatherSetGrade grade){
		executeInsert("GatherSet.updateGatherSetGrade", grade);
	}
	public void updateGatherSetSize(GatherSetSize size){
		executeInsert("GatherSet.updateGatherSetSize", size);
	}
	public void deleteGatherSetGrade(String listid){
		delete("GatherSet.deleteGatherSetGrade", listid);
	}
	public void deleteGatherSetGradeByLineId(String headid){
		delete("GatherSet.deleteGatherSetGradeByLineId", headid);
	}
	public void deleteGatherSetGradeByHeadId(String headid){
		delete("GatherSet.deleteGatherSetGradeByHeadId", headid);
	}
	public void deleteGatherSetSize(String listid){
		delete("GatherSet.deleteGatherSetSize", listid);
	}
	public void deleteGatherSetSizeByHeadId(String headid){
		delete("GatherSet.deleteGatherSetSizeByHeadId", headid);
	}
	public void deleteGatherSetSizeByLineId(String lineid){
		delete("GatherSet.deleteGatherSetSizeByLineId", lineid);
	}
	/**
	 * 选择品质
	 */
	public List<GatherSetGrade> getSelectGradeData(String lineid){
		return executeQueryForList("GatherSet.getSelectGradeData", lineid);
	}
	/**
	 * 选择尺寸
	 */
	public List<GatherSetSize> getSelectSizeData(String lineid){
		return executeQueryForList("GatherSet.getSelectSizeData", lineid);
	}
	/**
	 * 更新畅销款、非畅销款下单数量
	 */
	public void updateGatherSetLineSalableNum(GatherSetLine line){
		executeUpdate("GatherSet.updateGatherSetLineSalableNum", line);
	}
	/**
	 * 保存分配数量
	 * @param saleStyleList
	 */
	public void updateGatherSetStyleOrderNum(List<GatherSetStyle> saleStyleList){
		executeBatchUpdate("GatherSet.updateGatherSetStyleOrderNum", saleStyleList);
	}
	/**
	 * 保存品质分配数量
	 * @param gradeList
	 */
	public void updateGatherSetGradeOrderNum(List<GatherSetGrade> gradeList){
		executeBatchUpdate("GatherSet.updateGatherSetGradeOrderNum", gradeList);
	}
	/**
	 * 保存尺寸分配数量
	 * @param sizeList
	 */
	public void updateGatherSetSizeOrderNum(List<GatherSetSize> sizeList){
		executeBatchUpdate("GatherSet.updateGatherSetSizeOrderNum", sizeList);
	}

	public String getSalableStyleTotalRate(String lineid){
		return (String) executeQueryForObject("GatherSet.getSalableStyleTotalRate", lineid);
	}

	public String getUnsalableStyleTotalRate(String lineid){
		return (String) executeQueryForObject("GatherSet.getUnsalableStyleTotalRate", lineid);
	}

	public String getGradeTotalRate(String lineid){
		return (String) executeQueryForObject("GatherSet.getGradeTotalRate", lineid);
	}

	public String getSizeTotalRate(String lineid){
		return (String) executeQueryForObject("GatherSet.getSizeTotalRate", lineid);
	}
	public void updateGatherSetHeadStatus(String headid, String status){
		Map<String, String> map = new HashMap<String, String>();
		map.put("headid", headid);
		map.put("status", status);
		executeUpdate("GatherSet.updateGatherSetHeadStatus", map);
	}
	public void deleteGatherSetHead(String headid){
		delete("GatherSet.deleteGatherSetHead", headid);
	}
	public List<SelectorOption> getItemClassForSlt(String headid){
		return (List<SelectorOption>)executeQueryForList("GatherSet.getItemClassForSlt", headid);
	}
	/**
	 * 获取款式佩戴对象尺寸比例
	 * @param lineid
	 * @return
	 */
	public List<StyleWearSizeRate> getStyleWearSizeRate(String lineid){
		return (List<StyleWearSizeRate>)executeQueryForList("GatherSet.getStyleWearSizeRate", lineid);
	}
	/**
	 * 检查没有配置佩戴对象尺寸比例、或佩戴对象尺寸比例配置没有满足100%款式
	 * @param lineid
	 * @return
	 */
	public List<String> getUnSetStyleWearSizeRate(String lineid){
		return (List<String>)executeQueryForList("GatherSet.getUnSetStyleWearSizeRate", lineid);
	}
	/**
	 * 保存下单类别时，带出所有品质基础数据
	 * @param lineid
	 */
	public void saveGatherSetGradeFromBasic(String lineid, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("lineid", lineid);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("GatherSet.saveGatherSetGradeFromBasic", map);
	}
	/**
	 * 保存下单类别时，带出所有尺寸基础数据
	 * @param lineid
	 */
	public void saveGatherSetSizeFromBasic(String lineid, String userid){
		Map<String, String> map = new HashMap<String, String>();
		map.put("lineid", lineid);
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		executeInsert("GatherSet.saveGatherSetSizeFromBasic", map);
	}

	public List<Map<String, String>> getGradeStockTotalNum(String lineid){
		return (List<Map<String, String>>)executeQueryForList("GatherSet.getGradeStockTotalNum", lineid);
	}
	public List<Map<String, String>> getSizeStockTotalNum(String lineid){
		return (List<Map<String, String>>)executeQueryForList("GatherSet.getSizeStockTotalNum", lineid);
	}

    @Override
    public List<String> getSizeByStyleId(String styleId) {
        return this.executeQueryForList("GatherSet.getSizeByStyleId", styleId);
    }
}
