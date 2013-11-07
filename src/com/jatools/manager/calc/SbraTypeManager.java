package com.jatools.manager.calc;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraTypeHead;
import com.jatools.vo.calc.SbraTypeLine;

public interface SbraTypeManager {
	/**
	 * 获取配件类型分页数据
	 * @param condition
	 * @return
	 */
	Pager getSbraTypePageData(Map<String, String> condition);

	/**
	 * 保存配件类型
	 * @param head
	 * @return
	 */
	String saveSbraTypeHead(SbraTypeHead head);

	/**
	 * 更新配件类型
	 * @param head
	 */
	void updateSbraTypeHead(SbraTypeHead head);

	/**
	 * 删除配件类型行
	 * @param headid
	 */
	void deleteSbraTypeLineByHeadid(String headid);

	/**
	 * 保存配件类型行
	 * @param headid
	 */
	void saveSbraTypeLineList(List<SbraTypeLine> lineList);

	/**
	 * 获取头表数据
	 * @param headid
	 * @return
	 */
	public SbraTypeHead getSbraTypeHead(String headid);
	/**
	 * 获取行表数据
	 * @param headid
	 * @return
	 */
	public List<SbraTypeLine> getSbraTypeLineList(String headid);
	/**
	 * 删除配件类型头
	 * @param headid
	 */
	void deleteSbraTypeHead(String headid);
	/**
	 * 保存头行表
	 * @param lineList
	 * @param head
	 */
	void saveOrUpdateSbraType(List<SbraTypeLine> lineList, SbraTypeHead head);
	/**
	 * 获取所有未封存的配件类型
	 * @return
	 */
	List<SbraTypeHead> getSbraTypeHeadList();
	/**
	 * 获取配件类型下的材质
	 * @param headid
	 * @return
	 */
	public List<Quality> getSbraTypeLineSelectList(String headid);
}
