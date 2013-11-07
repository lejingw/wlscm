package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.WearSize;

public interface WearSizeDao {
	/**
	 * 获取分页数据
	 */
	public Pager getWearSizePageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param dn
	 */
	public void saveWearSize(WearSize dn);
	/**
	 * 修改
	 * @param dn
	 */
	public void updateWearSize(WearSize dn);

	/**
	 * 检查记录是否存在
	 * @param dn
	 */
	public List<WearSize> checkWearSizeRepeat(WearSize dn);
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deleteWearSize(List<String> billIdList);
}
