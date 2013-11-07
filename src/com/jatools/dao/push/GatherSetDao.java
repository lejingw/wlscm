package com.jatools.dao.push;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.push.GatherSetGrade;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.vo.push.GatherSetSize;
import com.jatools.vo.push.GatherSetStyle;
import com.jatools.vo.util.SelectorOption;

public interface GatherSetDao {

	public Pager<GatherSetHead> getGatherSetPageData(Map<String, String> condition);

	public void saveGatherSetHead(GatherSetHead head);
	
	public void updateGatherSetHead(GatherSetHead head);

	public GatherSetHead getGatherSetHead(String headid);
	/**
	 * 获取当前的畅销款铺货占比
	 * @return
	public String getCurrentSaleDisRate();
	 */
	/**
	 * 获取当前的畅销款周转占比
	 * @return
	public String getCurrentSaleTurnRate();
	 */

	public List<GatherSetLine> getGatherSetLineList(String headid);
	
	public GatherSetLine getGatherSetLine(String lineid);

	/**
	 * 修改类别下单数量
	 * @param lineid
	 * @param newOrderNum
	 */
	public void updateLineOrderNum(String lineid, String newOrderNum);

	public boolean checkGatherSetLine(GatherSetLine line);
	public String saveGatherSetLine(GatherSetLine line);

	public void deleteGatherSetLine(String lineid);
	
	public void deleteGatherSetLineByHeadId(String headid);

	/**
	 * 获取材质
	 */
	public List<SelectorOption> getQualitySelectOption(String lineid);
	/**
	 * 获取款式行
	 */
	public List<GatherSetStyle> getGatherSetStyle(String lineid);
	/**
	 * 获取选择款式分页数据
	 * @param saleable
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pager getSelectStylePageData(boolean saleable, String lineid, String start, String limit);

	public void saveGatherSetStyle(List<GatherSetStyle> addLineList);

	public void updateGatherSetStyle(List<GatherSetStyle> updateLineList);

	public void deleteGatherSetStyle(String listId);
	public void deleteGatherSetStyleByLineId(String headid);
	public void deleteGatherSetStyleByHeadId(String headid);

	public List<GatherSetGrade> getGatherSetGrade(String lineid);

	public List<GatherSetSize> getGatherSetSize(String lineid);

	public void saveGatherSetGrade(GatherSetGrade grade);
	public void saveGatherSetSize(GatherSetSize size);
	public void updateGatherSetGrade(GatherSetGrade grade);
	public void updateGatherSetSize(GatherSetSize size);
	public void deleteGatherSetGrade(String listid);
	public void deleteGatherSetGradeByLineId(String lineid);
	public void deleteGatherSetGradeByHeadId(String headid);
	public void deleteGatherSetSize(String listid);
	public void deleteGatherSetSizeByHeadId(String headid);
	public void deleteGatherSetSizeByLineId(String lineid);
	/**
	 * 选择品质
	 */
	public List<GatherSetGrade> getSelectGradeData(String lineid);
	/**
	 * 选择尺寸
	 */
	public List<GatherSetSize> getSelectSizeData(String lineid);
	/**
	 * 更新畅销款、非畅销款下单数量
	 */
	public void updateGatherSetLineSalableNum(GatherSetLine line);

	/**
	 * 保存款式分配数量
	 * @param saleStyleList
	 */
	public void updateGatherSetStyleOrderNum(List<GatherSetStyle> saleStyleList);

	/**
	 * 保存品质分配数量
	 * @param sizeList
	 */
	public void updateGatherSetGradeOrderNum(List<GatherSetGrade> gradeList);
	/**
	 * 保存尺寸分配数量
	 * @param sizeList
	 */
	public void updateGatherSetSizeOrderNum(List<GatherSetSize> sizeList);

	public String getSalableStyleTotalRate(String lineid);

	public String getUnsalableStyleTotalRate(String lineid);

	public String getGradeTotalRate(String lineid);

	public String getSizeTotalRate(String lineid);

	public void updateGatherSetHeadStatus(String headid, String status);

	public void deleteGatherSetHead(String headid);

	public List<SelectorOption> getItemClassForSlt(String headid);
	/**
	 * 获取款式佩戴对象尺寸比例
	 * @param lineid
	 * @return
	 */
	public List getStyleWearSizeRate(String lineid);
	/**
	 * 检查没有配置佩戴对象尺寸比例、或佩戴对象尺寸比例配置没有满足100%款式
	 * @param lineid
	 * @return
	 */
	public List<String> getUnSetStyleWearSizeRate(String lineid);
	/**
	 * 保存下单类别时，带出所有品质基础数据
	 * @param lineid
	 */
	public void saveGatherSetGradeFromBasic(String lineid, String userid);
	/**
	 * 保存下单类别时，带出所有尺寸基础数据
	 * @param lineid
	 */
	public void saveGatherSetSizeFromBasic(String lineid, String userid);

	public List<Map<String, String>> getGradeStockTotalNum(String lineid);
	public List<Map<String, String>> getSizeStockTotalNum(String lineid);

    List<String> getSizeByStyleId(String styleId);
}
