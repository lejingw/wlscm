package com.jatools.dao.push;

import com.jatools.common.Pager;
import com.jatools.vo.push.PushStyle;

import java.util.List;
import java.util.Map;

public interface PushStyleDao {
	/**
	 * 获取分页数据
	 */
	public Pager getPushStylePageData(Map<String, String> condition);

	/**
	 * 保存
	 * 
	 * @param dn
	 */
	public void savePushStyle(PushStyle dn);

	/**
	 * 修改
	 * 
	 * @param dn
	 */
	public void updatePushStyle(PushStyle dn);

	/**
	 * 检查记录是否存在
	 * 
	 * @param dn
	 */
	public boolean checkPushStyleExists(PushStyle dn);

	/**
	 * 删除
	 * 
	 * @param billIdList
	 */
	public void deletePushStyle(List<String> billIdList);


    PushStyle getPushStyleById(String id);
}
