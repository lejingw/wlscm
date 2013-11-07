package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.ExcludeOrna;

public interface ExcludeOrnaDao {
	/**
	 * 获取分页数据
	 */
	public Pager getExcludeOrnaPageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param dn
	 */
	public void saveExcludeOrna(ExcludeOrna dn);
	/**
	 * 检查记录是否存在
	 * @param dn
	 */
	public Integer checkExcludeOrnaRepeat(String ornaCode);
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deleteExcludeOrna(List<String> billIdList);
	/**
	 * 根据编码获取现有量信息
	 */
	public ExcludeOrna getMaterActiveByCode(String code, boolean ornaFlag);
	public Pager queryExcludeOrnaPageData(Map<String, String> condition);
}
