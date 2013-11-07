package com.jatools.web.dwr.push;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jatools.common.CommonUtil;
import com.jatools.manager.push.TurnoverNumManager;
import com.jatools.vo.push.TurnoverNum;

public class TurnoverNumDwr {
	private TurnoverNumManager turnoverNumManager;
	
	public void setTurnoverNumManager(TurnoverNumManager turnoverNumManager) {
		this.turnoverNumManager = turnoverNumManager;
	}

	public String saveOrUpdateTurnoverNum(TurnoverNum dn, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		String result = checkDate(dn);
		if(null != result){
			return result;
		}
		turnoverNumManager.saveOrUpdateTurnoverNum(dn, userId);
		return null;
	}

	private String checkDate(TurnoverNum dn) {
		List<TurnoverNum> list = turnoverNumManager.checkTurnoverNumRepeat(dn);
		if(null != list && list.size()>0){
			for(TurnoverNum num : list){
				if(num.getStartDate().compareTo(dn.getStartDate())<=0 && num.getEndDate().compareTo(dn.getStartDate())>=0){
					return "相同的组织+大类+小类+分析范围+款式大类下存在周转范围["+num.getStartDate()+"-"+num.getEndDate()+"]与当前的范围交叉";
				}
				if(num.getStartDate().compareTo(dn.getEndDate())<=0 && num.getEndDate().compareTo(dn.getEndDate())>=0){
					return "相同的组织+大类+小类+分析范围+款式大类下存在周转范围["+num.getStartDate()+"-"+num.getEndDate()+"]与当前的范围交叉";
				}
			}
		}
		return null;
	}
	
	public String deleteTurnoverNum(List<String> billIdList){
		turnoverNumManager.deleteTurnoverNum(billIdList);
		return null;
	}
	/**
	 * 复制
	 */
	public String copyTurnoverNum(String srcStartDate, String srcEndDate, String targetStartDate, String targetEndDate, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		turnoverNumManager.copyTurnoverNum(srcStartDate, srcEndDate, targetStartDate, targetEndDate, userId);
		return null;
	}
}
