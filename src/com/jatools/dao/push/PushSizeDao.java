package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.PushSize;

public interface PushSizeDao {
	/**
	 * 获取分页数据
	 */
	public Pager getPushSizePageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param dn
	 */
	public void savePushSize(PushSize dn);
	/**
	 * 修改
	 * @param dn
	 */
	public void updatePushSize(PushSize dn);

	/**
	 * 检查记录是否存在
	 * @param dn
	 */
	public List<PushSize> checkPushSizeRepeat(PushSize dn);
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deletePushSize(List<String> billIdList);
}
