package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Size;
import com.jatools.vo.push.PushSize;

public interface PushSizeManager {
	/**
	 * 获取分页数据
	 */
	public Pager getPushSizePageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdatePushSize(PushSize dn, String userId);
	/**
	 * 检查记录是否存在
	 */
	public List<PushSize> checkPushSizeRepeat(PushSize dn);
	/**
	 * 删除
	 */
	public void deletePushSize(List<String> billIdList);
	/**
	 * 根据大类小类获取尺寸
	 * @param itemId
	 * @param ornaId
	 * @return
	 */
	public List<Size> getSizeByItemIdAndOrnaId(String itemId, String ornaId);

}
