package com.jatools.manager.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.util.SelectorOption;

public interface MoveNetManager {

	/**
	 * 获取调拨网络分页数据
	 * @return
	 */
	public Pager getMoveNetPageData(Map<String, String> condition);

	/**
	 * 根据调入组织id获取调出组织
	 * @return
	 */
	public List<SelectorOption> getOrgs(String orgid, String outflag);

	/**
	 * 保存调拨网络
	 */
	public void saveMoveNet(String inOrgId, List<String> outOrgIdList, String outflag, String userid);
	/**
	 * 删除调拨网络
	 */
	public void deleteMoveNet(List<String> netIdList);

	/**
	 * 根据调出组织获取调入组织
	 * @return
	 */
	public List<Org> getInOrgByMoveNet(String outOrgId);
	/**
	 * 根据调出组织获取调入加盟组织
	 * @return
	 */
	public List<Org> getInJmOrgByMoveNet(String outOrgId);
	/**
	 * 装箱单根据调出组织获取调入加盟组织
	 */
	public List<Org> getInJmOrgFromPack(String outOrgId);
}
