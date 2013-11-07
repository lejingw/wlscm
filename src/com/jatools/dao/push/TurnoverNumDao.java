package com.jatools.dao.push;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.TurnoverNum;
import com.jatools.web.util.DateUtil;

public interface TurnoverNumDao {
	/**
	 * 获取分页数据
	 */
	public Pager getTurnoverNumPageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param dn
	 */
	public void saveTurnoverNum(TurnoverNum dn);
	/**
	 * 修改
	 * @param dn
	 */
	public void updateTurnoverNum(TurnoverNum dn);

	/**
	 * 检查记录是否存在
	 * @param dn
	 */
	public List<TurnoverNum> checkTurnoverNumRepeat(TurnoverNum dn);
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deleteTurnoverNum(List<String> billIdList);

	/**
	 * 复制
	 */
	public void copyTurnoverNum(String srcStartDate, String srcEndDate, String targetStartDate, String targetEndDate, String userId);
	/**
	 * 复制时先删除原来的数据
	 */
	public void deleteTurnoverNumByDate(String targetStartDate, String targetEndDate, String userId);
	public void saveTurnoverNumFromExcel(String seqId, String userId);
}
