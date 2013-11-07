package com.jatools.manager.push;

import com.jatools.common.Pager;
import com.jatools.vo.push.PushStyle;

import java.util.List;
import java.util.Map;

public interface PushStyleManager {
	/**
	 * 获取分页数据
	 */
	public Pager getPushStylePageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdatePushStyle(PushStyle dn, String userId);
	/**
	 * 删除
	 */
	public void deletePushStyle(List<String> billIdList);

    PushStyle getPushStyleById(String id);
}
