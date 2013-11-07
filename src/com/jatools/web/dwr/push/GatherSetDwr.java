package com.jatools.web.dwr.push;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.push.GatherSetManager;
import com.jatools.vo.push.GatherSetGrade;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.vo.push.GatherSetSize;
import com.jatools.vo.push.GatherSetStyle;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.StringUtil;

public class GatherSetDwr {
	private GatherSetManager gatherSetManager;

	public void setGatherSetManager(GatherSetManager gatherSetManager) {
		this.gatherSetManager = gatherSetManager;
	}
	public List<SelectorOption> getItemClassForSlt(String headid){
		return gatherSetManager.getItemClassForSlt(headid);
	}
	/**
	 * 保存或修改头
	 */
	public String saveOrUpdateGatherSetHead(GatherSetHead head, HttpServletRequest req) {
		String userid = CommonUtil.getSessionUserId(req);
		String orgid = CommonUtil.getSessionOrgId(req);
		gatherSetManager.saveOrUpdateGatherSetHead(head, userid, orgid);
		return null;
	}
	/**
	 * 删除
	 */
	public String deleteGatherSet(String headid, HttpServletRequest req) {
		try {
			String userid = CommonUtil.getSessionUserId(req);
			gatherSetManager.deleteGatherSet(headid, userid);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	 * 修改类别下单数量
	 */
	public String[] updateLineOrderNum(String lineid, String newOrderNum, HttpServletRequest req) {
		try {
			String userid = CommonUtil.getSessionUserId(req);
			String msg = gatherSetManager.updateLineOrderNum(lineid, newOrderNum, userid);
			return new String[]{"1", StringUtil.trimToEmpty(msg)};
		} catch (Exception e) {
			return new String[]{"0", e.getMessage()};
		}
	}
	/**
	 * 保存行
	 */
	public String saveGatherSetLine(GatherSetLine line, HttpServletRequest req){
		String userid = CommonUtil.getSessionUserId(req);
		gatherSetManager.saveGatherSetLine(line, userid);
		return null;
	}
	/**
	 * 删除行
	 */
	public String deleteGatherSetLine(String lineid){
		gatherSetManager.deleteGatherSetLine(lineid);
		return null;
	}
	public GatherSetLine getGatherSetLine(String lineid){
		return gatherSetManager.getGatherSetLine(lineid);
	}
	/**
	 * 获取材质
	 */
	public List<SelectorOption> getQualitySelectOption(String lineid){
		return gatherSetManager.getQualitySelectOption(lineid);
	}
	/**
	 * 获取款式行
	 */
	public List<GatherSetStyle> getGatherSetStyle(String lineid){
		return gatherSetManager.getGatherSetStyle(lineid);
	}
	/**
	 * 获取选择款式分页数据
	 * @param saleable
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pager getSelectStylePageData(boolean saleable, String lineid, String start, String limit){
		return gatherSetManager.getSelectStylePageData(saleable, lineid, start, limit);
	}
	
	/**
	 * 保存畅销款比例
	 * @return
	 */
	public String[] saveGatherSetStyle(String lineid, boolean salable, List<GatherSetStyle> addLineArr, List<GatherSetStyle> updateLineArr, List<String> deleteIdList, HttpServletRequest req) {
		try {
			String userid = CommonUtil.getSessionUserId(req);
			GatherSetLine line = gatherSetManager.saveGatherSetStyle(lineid, salable, addLineArr, updateLineArr, deleteIdList, userid);
			return new String[]{"保存成功", line.getSalableNum(), line.getUnsalableNum(), line.getRealSalableOrderNum()};
		} catch (Exception e) {
			e.printStackTrace();
			return new String[]{e.getMessage()};
		}
	}
	/**
	 * 获取尺寸行
	 */
	public List<GatherSetGrade> getGatherSetGrade(String lineid){
		return gatherSetManager.getGatherSetGrade(lineid);
	}
	/**
	 * 获取尺寸行
	 */
	public List<GatherSetSize> getGatherSetSize(String lineid){
		return gatherSetManager.getGatherSetSize(lineid);
	}
	/**
	 * 保存品质比例
	 * @return
	 */
	public String saveGatherSetGrade(String lineid, List<GatherSetGrade> addLineArr, List<GatherSetGrade> updateLineArr, List<String> deleteIdList, HttpServletRequest req) {
		String userid = CommonUtil.getSessionUserId(req);
		gatherSetManager.saveGatherSetGrade(lineid, addLineArr, updateLineArr, deleteIdList, userid);
		return null;
	}
	/**
	 * 保存尺寸比例
	 * @return
	 */
	public String saveGatherSetSize(String lineid, List<GatherSetSize> addLineArr, List<GatherSetSize> updateLineArr, List<String> deleteIdList, HttpServletRequest req) {
		String userid = CommonUtil.getSessionUserId(req);
		gatherSetManager.saveGatherSetSize(lineid, addLineArr, updateLineArr, deleteIdList, userid);
		return null;
	}
	public List<GatherSetGrade> getSelectGradeData(String lineid){
		return gatherSetManager.getSelectGradeData(lineid);
	}
	public List<GatherSetSize> getSelectSizeData(String lineid){
		return gatherSetManager.getSelectSizeData(lineid);
	}
	/**
	 * 生成明细
	 * @param lineid
	 * @param req
	 * @return
	 */
	public String generateDetail(String lineid, HttpServletRequest req) {
		try {
			String userid = CommonUtil.getSessionUserId(req);
			//生成类别下的明细
			gatherSetManager.generateDetail(lineid, userid);
			return null;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	/**
	 * 生成总单
	 * @param headid
	 * @param req
	 * @return
	 */
	public String createGatherOrder(String headid, HttpServletRequest req) {
		try {
			String userid = CommonUtil.getSessionUserId(req);
			//检查畅销款比例是否都已经分配
			gatherSetManager.createGatherOrder(headid, userid);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	 * 修改畅销款铺货比例
	 */
	public String[] updateSalableRate(String headid, String saleDisRate, String saleTurnRate, HttpServletRequest req){
		try {
			String userid = CommonUtil.getSessionUserId(req);
			String msg = gatherSetManager.updateSalableRate(headid, saleDisRate, saleTurnRate, userid);
			return new String[]{"1", StringUtil.trimToEmpty(msg)};
		} catch (Exception e) {
			return new String[]{"1", e.getMessage()};
		}
	}
}
