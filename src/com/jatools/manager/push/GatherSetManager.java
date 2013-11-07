package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.vo.push.GatherSetGrade;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.vo.push.GatherSetSize;
import com.jatools.vo.push.GatherSetStyle;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public interface GatherSetManager {
	public GatherSetHead getGatherSetHead(String headid);

	public Pager<GatherSetHead> getGatherSetPageData(
			Map<String, String> condition);

	/**
	 * 保存或修改头
	 * 
	 * @param head
	 * @param userid
	 */
	void saveOrUpdateGatherSetHead(GatherSetHead head, String userid,
			String orgid);

	public List<GatherSetLine> getGatherSetLineList(String headid);

	/**
	 * 修改类别下单数量
	 * 
	 * @param lineid
	 * @param newOrderNum
	 */
	public String updateLineOrderNum(String lineid, String newOrderNum, String userid);

	public void saveGatherSetLine(GatherSetLine line, String userid);

	public void deleteGatherSetLine(String lineid);

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

	/**
	 * 保存畅销款比例
	 * 
	 * @param lineid
	 * @param salable
	 * @param addLineArr
	 * @param updateLineArr
	 * @param deleteIdList
	 */
	public GatherSetLine saveGatherSetStyle(String lineid, boolean salable,
			List<GatherSetStyle> addLineArr,
			List<GatherSetStyle> updateLineArr, List<String> deleteIdList,
			String userid);
	
	public List<GatherSetGrade> getGatherSetGrade(String lineid);

	public List<GatherSetSize> getGatherSetSize(String lineid);
	/**
	 * 保存品质比例
	 */
	public void saveGatherSetGrade(String lineid, List<GatherSetGrade> addLineList, List<GatherSetGrade> updateLineList, List<String> deleteIdList, String userid);
	/**
	 * 保存尺寸比例
	 */
	public void saveGatherSetSize(String lineid, List<GatherSetSize> addLineList, List<GatherSetSize> updateLineList, List<String> deleteIdList, String userid);
	/**
	 * 选择品质
	 */
	public List<GatherSetGrade> getSelectGradeData(String lineid);
	/**
	 * 选择尺寸
	 */
	public List<GatherSetSize> getSelectSizeData(String lineid);

	public void checkGenerateDetailStyle(String lineid);

	public void checkGenerateDetailGrade(String lineid);
	/**
	 * 生成类别下的明细
	 * @param lineid
	 */
	public void generateDetail(String lineid, String userid);
	
	public void createGatherOrder(String headid, String userid);
	/**
	 * 修改畅销款铺货比例
	 * a)	删除所有类别中已生成的明细下单数据
	 * b)	清除所有类别中畅销款、非畅销款的下单数量
	 * c)	若此类别下的畅销款配置比例之和为100%，则重新计算畅销款、非畅销款的各个明细款式下单数量
	 * d)	若此类别下已生成明细，则重新生成
	 */
	public String updateSalableRate(String headid, String saleDisRate, String saleTurnRate, String userid);

	public void deleteGatherSet(String headid, String userid);

	public List<SelectorOption> getItemClassForSlt(String headid);

	public GatherSetLine getGatherSetLine(String lineid);
}
