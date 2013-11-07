package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Size;
import com.jatools.vo.push.WearSize;

public interface WearSizeManager {
	/**
	 * 获取分页数据
	 */
	public Pager getWearSizePageData(Map<String, String> condition);

	/**
	 * 保存或修改
	 */
	public void saveOrUpdateWearSize(WearSize dn, String userId);

	/**
	 * 检查记录是否存在
	 */
	public List<WearSize> checkWearSizeRepeat(WearSize dn);

	/**
	 * 删除
	 */
	public void deleteWearSize(List<String> billIdList);

	/**
	 * 根据大类小类获取尺寸
	 */
	public List<Size> getSizeByItemIdAndOrnaId(String itemId, String ornaId);

}
