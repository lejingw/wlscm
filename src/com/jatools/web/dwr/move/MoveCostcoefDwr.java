package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.manager.move.MoveCostcoefManager;
import com.jatools.vo.move.MoveCostcoef;
import com.jatools.vo.util.SelectorOption;

public class MoveCostcoefDwr {
	private static Logger logger = Logger.getLogger(MoveCostcoefDwr.class);
	private MoveCostcoefManager moveCostcoefManager;
	public void setMoveCostcoefManager(MoveCostcoefManager moveCostcoefManager) {
		this.moveCostcoefManager = moveCostcoefManager;
	}
	
	/**
	 * 保存、修改
	 * @param coef
	 * @param session
	 * @return
	 */
	public String saveMoveCostcoef(MoveCostcoef coef, HttpSession session){
		try {
			String userid = CommonUtil.getSessionUserId(session);
			moveCostcoefManager.saveOrUpdateMoveCostcoef(coef, userid);
			return null;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * 根据大类获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassByItemClassIdForSlt(String itemClassId, String coefId){
		List<SelectorOption> list = moveCostcoefManager.getOrnaClassByItemClassIdForSlt(itemClassId, coefId);
		return list;
	}

	/**
	 * 删除调拨成本系数
	 * @param headid
	 * @return
	 */
	public String deleteMoveCostcoef(String[] coefIdArr){
		try {
			if(null == coefIdArr || coefIdArr.length<1){
				return "不能获取调拨销价系数id";
			}
			moveCostcoefManager.deleteMoveCostcoef(coefIdArr);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}
}
