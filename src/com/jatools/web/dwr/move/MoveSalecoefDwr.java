package com.jatools.web.dwr.move;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.manager.move.MoveSalecoefManager;
import com.jatools.vo.move.MoveSalecoefHead;
import com.jatools.vo.move.MoveSalecoefLine;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class MoveSalecoefDwr {
	private static Logger logger = Logger.getLogger(MoveSalecoefDwr.class);
	private MoveSalecoefManager moveSalecoefManager;
	
	public void setMoveSalecoefManager(MoveSalecoefManager moveSalecoefManager) {
		this.moveSalecoefManager = moveSalecoefManager;
	}

	/**
	 * 保存或修改数据
	 * @param head
	 * @param lineList
	 */
	public String saveOrUpdateMoveSalecoef(MoveSalecoefHead head, List<MoveSalecoefLine> lineList, HttpServletRequest req){
		try {
			if(StringUtil.isEmpty(head.getHeadid())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateId(CommonUtil.getSessionUserId(req));
				head.setStatus(DictConstant.BILL_STATUS_SAVE);
			}
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(CommonUtil.getSessionUserId(req));
			moveSalecoefManager.saveOrUpdateMoveSalecoef(head, lineList);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}
	/**
	 * 删除调拨销价系数
	 * @param headid
	 * @return
	 */
	public String deleteMoveSalecoef(String[] headidArr){
		try {
			if(null == headidArr || headidArr.length<1){
				return "不能获取调拨销价系数id";
			}
			moveSalecoefManager.deleteMoveSalecoef(headidArr);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}
	/**
	 * 检查调入组织、大类、小类是否已经存在
	 * @param inOrgId
	 * @param itemClassId
	 * @param ornaClass
	 * @return
	 */
	public boolean checkRepeat(String inOrgId, String itemClassId, String ornaClassId, String styleItemIds, String headid){
		List<MoveSalecoefHead> list = moveSalecoefManager.checkRepeat(inOrgId, itemClassId, ornaClassId, styleItemIds, headid);
		if(list.size()>0)
			return false;
		return true;
	}
	
	public String copyData(String fromOrgId, String toOrgIds, HttpServletRequest req){
//		System.out.println(fromOrgId);
//		System.out.println(toOrgIdList);
		moveSalecoefManager.copyData(fromOrgId, toOrgIds, CommonUtil.getSessionUserId(req));
		return null;
	}
}
