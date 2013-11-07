package com.jatools.dao.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.move.SpecialDiam;

public interface SpecialDiamDao {

	/**
	 * 获取分页数据
	 */
	public Pager getSpecialDiamPageData(Map<String, String> condition);
	public Pager getSpecialDiamOrgPageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param dn
	 */
	public void saveSpecialDiam(SpecialDiam dn);
	/**
	 * 修改
	 * @param dn
	 */
	public void updateSpecialDiam(SpecialDiam dn);

	/**
	 * 检查记录是否存在
	 * @param dn
	 */
	public List<SpecialDiam> checkSpecialDiamRepeat(SpecialDiam dn);
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deleteSpecialDiam(List<String> billIdList);
	public void saveSpecialDiamFromExcel(String seqId, String userId);
	public boolean isExisteSpecialDiamOrg(String orgId);
	public void saveSpecialDiamOrg(String orgId, String orgType, String userId);
	public void updateSpecialDiamOrg(String orgId, String orgType, String userId);
	public void deleteSpecialDiamOrg(String orgId);
}
