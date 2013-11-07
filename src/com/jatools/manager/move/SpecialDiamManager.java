package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.SpecialDiam;

public interface SpecialDiamManager {

	/**
	 * 获取分页数据
	 */
	public Pager getSpecialDiamPageData(Map<String, String> condition);
	public Pager getSpecialDiamOrgPageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateSpecialDiam(SpecialDiam dn, String userId);
	/**
	 * 检查记录是否存在
	 */
	public List<SpecialDiam> checkSpecialDiamRepeat(SpecialDiam dn);
	/**
	 * 删除
	 */
	public void deleteSpecialDiam(List<String> billIdList);
	/**
	 * 导入excel
	 */
	public void saveSpecialDiamFromExcel(String seqId, String userId);
	public void saveOrUpdateSpecialDiamOrg(String orgId, String orgType, String userId);
	public void deleteSpecialDiamOrg(String orgId);
}
