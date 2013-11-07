package com.jatools.dao.push;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.push.DistributeNum;

public interface DistributeNumDao {
	/**
	 * 获取分页数据
	 */
	public Pager getDistributeNumPageData(Map<String, String> condition);
	/**
	 * 保存
	 * @param dn
	 */
	public void saveDistributeNum(DistributeNum dn);
	/**
	 * 修改
	 * @param dn
	 */
	public void updateDistributeNum(DistributeNum dn);

	/**
	 * 检查记录是否存在
	 * @param dn
	 */
	public Integer checkDistributeNumRepeat(DistributeNum dn);
	/**
	 * 删除
	 * @param billIdList
	 */
	public void deleteDistributeNum(List<String> billIdList);
	/**
	 * 复制
	 */
	public void copyDistributeNum(String srcOrgId, String targetOrgIds, 	String userId);
	/**
	 * 复制时先删除原来的数据
	 */
	public void deleteDistributeNumByOrgs(String targetOrgIds, String userId);
	/**
	 * 导入excel
	 */
	public void saveDistributeNumFromExcel(String seqId, String userId);
}
