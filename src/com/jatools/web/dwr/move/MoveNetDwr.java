package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.move.MoveNetManager;
import com.jatools.vo.util.SelectorOption;

public class MoveNetDwr {
	private static Logger logger = Logger.getLogger(MoveNetDwr.class);
	private MoveNetManager moveNetManager;

	public void setMoveNetManager(MoveNetManager moveNetManager) {
		this.moveNetManager = moveNetManager;
	}
	/**
	 * 保存调拨网络
	 * @return
	 */
	public String saveMoveNet(String inOrgId, List<String> outOrgIdList, String outflag, HttpSession session){
		moveNetManager.saveMoveNet(inOrgId, outOrgIdList, outflag, CommonUtil.getSessionUserId(session));
		return null;
	}
	/**
	 * 删除调拨网络
	 * @return
	 */
	public String deleteMoveNet(List<String> netIdList){
		moveNetManager.deleteMoveNet(netIdList);
		return null;
	}
//	/**
//	 * 获取调拨单调入组织
//	 * @return
//	 */
//	public List<SelectorOption> getInOrgByMoveNet(HttpSession session){
//		String outOrgId = CommonUtil.getSessionOrgId(session);
//		return moveNetManager.getInOrgByMoveNet(outOrgId);
//	}
}
