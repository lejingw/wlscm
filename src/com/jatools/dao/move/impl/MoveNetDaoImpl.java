package com.jatools.dao.move.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.BaseDao;
import com.jatools.dao.move.MoveNetDao;
import com.jatools.vo.bd.Org;
import com.jatools.vo.move.MoveNet;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;

public class MoveNetDaoImpl extends BaseDao implements MoveNetDao {

	/**
	 * 获取调拨网络分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveNetPageData(Map<String, String> condition){
		return executeQueryForPager("MoveNet.getMoveNetPageData", "MoveNet.getMoveNetTotalCount", condition);
	}
	/**
	 * 根据调入组织id获取调出组织
	 * @return
	 */
	public List<SelectorOption> getOrgs(String orgid, String outflag){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("orgid", orgid);
		condition.put("outflag", outflag);
		return executeQueryForList("MoveNet.getOrgs", condition);
	}

	/**
	 * 保存调拨网络
	 */
	public void saveMoveNet(String inOrgId, List<String> outOrgIdList, String outflag, String userid){
		List<MoveNet> parameterList = new ArrayList<MoveNet>();
		for(String outOrgId : outOrgIdList){
			MoveNet moveNet = new MoveNet();
			if("1".equals(outflag)){				
				moveNet.setInOrgId(outOrgId);
				moveNet.setOutOrgId(inOrgId);
			}else{				
				moveNet.setInOrgId(inOrgId);
				moveNet.setOutOrgId(outOrgId);
			}
			moveNet.setCreateDate(DateUtil.getCurrentDate18());
			moveNet.setCreateId(userid);
			moveNet.setUpdateDate(DateUtil.getCurrentDate18());
			moveNet.setUpdateId(userid);
			moveNet.setStatus(DictConstant.BILL_STATUS_SAVE);
			parameterList.add(moveNet);
		}
		executeBatchInsert("MoveNet.saveMoveNet", parameterList);
	}
	/**
	 * 删除调拨网络
	 */
	public void deleteMoveNet(List<String> netIdList){
		executeBatchDelete("MoveNet.deleteMoveNet", netIdList);
	}
	/**
	 * 根据组织删除调拨网络
	 */
	public void deleteMoveNetByOrgid(String orgid, String outflag){
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgid", orgid);
		map.put("outflag", outflag);
		delete("MoveNet.deleteMoveNetByOrgid", map);
	}
	/**
	 * 根据调出组织获取调入组织
	 * @param outOrgId
	 * @return
	 */
	public List<Org> getInOrgByMoveNet(String outOrgId){
		return executeQueryForList("MoveNet.getInOrgByMoveNet", outOrgId);
	}
	/**
	 * 根据调出组织获取调入加盟组织
	 * @param outOrgId
	 * @return
	 */
	public List<Org> getInJmOrgByMoveNet(String outOrgId){
		return executeQueryForList("MoveNet.getInJmOrgByMoveNet", outOrgId);
	}
	/**
	 * 装箱单根据调出组织获取调入加盟组织
	 */
	public List<Org> getInJmOrgFromPack(String outOrgId){
		return executeQueryForList("MoveNet.getInJmOrgFromPack", outOrgId);
	}
	/**
	 * 判断是否总部组织
	 * @param outOrgId
	 * @return
	 */
	public boolean isHqOrg(String outOrgId){
		int count = (Integer) executeQueryForObject("MoveNet.isHqOrg", outOrgId);
		return count>0;
	}
}
