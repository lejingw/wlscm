package com.jatools.dao.move;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.util.SelectorOption;

public interface MoveNetDao {

	/**
	 * 获取调拨网络分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveNetPageData(Map<String, String> condition);

	/**
	 * 根据调入组织id获取调出组织
	 */
	public List<SelectorOption> getOrgs(String orgid, String outflag);

	/**
	 * 保存调拨网络
	 * @param inOrgId
	 * @param outOrgIdList
	 */
	public void saveMoveNet(String inOrgId, List<String> outOrgIdList, String outflag, String userid);
	/**
	 * 删除调拨网络
	 * @param netIdList
	 */
	public void deleteMoveNet(List<String> netIdList);
	/**
	 * 根据组织删除调拨网络
	 * @param netIdList
	 */
	public void deleteMoveNetByOrgid(String orgid, String outflag);
	/**
	 * 根据调出组织获取调入组织
	 * @param outOrgId
	 * @return
	 */
	public List<Org> getInOrgByMoveNet(String outOrgId);
	/**
	 * 根据调出组织获取调入加盟组织
	 * @param outOrgId
	 * @return
	 */
	public List<Org> getInJmOrgByMoveNet(String outOrgId);
	/**
	 * 装箱单根据调出组织获取调入加盟组织
	 */
	public List<Org> getInJmOrgFromPack(String outOrgId);
	/**
	 * 判断是否总部组织
	 * @param outOrgId
	 * @return
	 */
	public boolean isHqOrg(String outOrgId);
}
