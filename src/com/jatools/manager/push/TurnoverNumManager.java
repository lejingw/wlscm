package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.TurnoverNum;

public interface TurnoverNumManager {
	/**
	 * 获取分页数据
	 */
	public Pager getTurnoverNumPageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateTurnoverNum(TurnoverNum dn, String userId);
	/**
	 * 检查记录是否存在
	 */
	public List<TurnoverNum> checkTurnoverNumRepeat(TurnoverNum dn);
	/**
	 * 删除
	 */
	public void deleteTurnoverNum(List<String> billIdList);
	/**
	 * 复制
	 */
	public void copyTurnoverNum(String srcStartDate, String srcEndDate, String targetStartDate, String targetEndDate, String userId);
	/**
	 * 导入excel
	 */
	public void saveTurnoverNumFromExcel(String seqId, String userId);

}
