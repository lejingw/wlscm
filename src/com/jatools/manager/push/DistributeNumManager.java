package com.jatools.manager.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.DistributeNum;

public interface DistributeNumManager {
	/**
	 * 获取分页数据
	 */
	public Pager getDistributeNumPageData(Map<String, String> condition);
	/**
	 * 保存或修改
	 */
	public void saveOrUpdateDistributeNum(DistributeNum dn, String userId);
	/**
	 * 检查记录是否存在
	 */
	public Integer checkDistributeNumRepeat(DistributeNum dn);
	/**
	 * 删除
	 */
	public void deleteDistributeNum(List<String> billIdList);
	/**
	 * 复制
	 */
	public void copyDistributeNum(String srcOrgId, String targetOrgIds, String userId);
	/**
	 * 导入excel
	 */
	public void saveDistributeNumFromExcel(String seqId, String userId);

}
