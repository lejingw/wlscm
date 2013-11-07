package com.jatools.manager.push;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.vo.push.ExcludeOrna;

public interface ExcludeOrnaManager {
	/**
	 * 获取分页数据
	 */
	public Pager getExcludeOrnaPageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveExcludeOrna(String ornaCode, String userId);
	/**
	 * 保存或修改
	 */
	public void saveExcludeOrna(List<String> ornaCodeList, String userId);
	/**
	 * 检查记录是否存在
	 */
	public Integer checkExcludeOrnaRepeat(String ornaCode);
	/**
	 * 删除
	 */
	public void deleteExcludeOrna(List<String> billIdList);
	/**
	 * 根据编码获取现有量信息
	 */
	public ExcludeOrna getMaterActiveByCode(String code, boolean ornaFlag);
	public Pager queryExcludeOrnaPageData(Map<String, String> condition);

}
