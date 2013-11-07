package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.move.MoveNetDao;
import com.jatools.manager.move.MoveNetManager;
import com.jatools.vo.bd.Org;
import com.jatools.vo.util.SelectorOption;

public class MoveNetManagerImpl implements MoveNetManager {
	private MoveNetDao moveNetDao;

	public void setMoveNetDao(MoveNetDao moveNetDao) {
		this.moveNetDao = moveNetDao;
	}

	/**
	 * 获取调拨网络分页数据
	 * @return
	 */
	public Pager getMoveNetPageData(Map<String, String> condition){
		return moveNetDao.getMoveNetPageData(condition);
	}

	/**
	 * 根据组织id获取调出组织
	 * @return
	 */
	public List<SelectorOption> getOrgs(String orgid, String outflag){
		return moveNetDao.getOrgs(orgid, outflag);
	}

	/**
	 * 保存调拨网络
	 * @return
	 */
	public void saveMoveNet(String inOrgId, List<String> outOrgIdList, String outflag, String userid){
		moveNetDao.deleteMoveNetByOrgid(inOrgId, outflag);
		moveNetDao.saveMoveNet(inOrgId, outOrgIdList, outflag, userid);
	}
	/**
	 * 删除调拨网络
	 * @return
	 */
	public void deleteMoveNet(List<String> netIdList){
		moveNetDao.deleteMoveNet(netIdList);
	}
	/**
	 * 根据调出组织获取调入组织
	 * @return
	 */
	public List<Org> getInOrgByMoveNet(String outOrgId){
		return moveNetDao.getInOrgByMoveNet(outOrgId);
	}
	/**
	 * 根据调出组织获取调入加盟组织
	 * @return
	 */
	public List<Org> getInJmOrgByMoveNet(String outOrgId){
		return moveNetDao.getInJmOrgByMoveNet(outOrgId);
	}
	/**
	 * 装箱单根据调出组织获取调入加盟组织
	 */
	public List<Org> getInJmOrgFromPack(String outOrgId){
		boolean isHqOrgFlag = moveNetDao.isHqOrg(outOrgId);
		if(isHqOrgFlag){			
			return moveNetDao.getInJmOrgByMoveNet(outOrgId);
		}else{
			return moveNetDao.getInJmOrgFromPack(outOrgId);
		}
	}
}
