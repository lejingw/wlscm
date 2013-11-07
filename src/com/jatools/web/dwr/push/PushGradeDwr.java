package com.jatools.web.dwr.push;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.jatools.common.CommonUtil;
import com.jatools.manager.push.PushGradeManager;
import com.jatools.vo.push.PushGrade;

public class PushGradeDwr {
	private PushGradeManager pushGradeManager;
	
	public void setPushGradeManager(PushGradeManager pushGradeManager) {
		this.pushGradeManager = pushGradeManager;
	}

	public String saveOrUpdatePushGrade(PushGrade dn, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		List<PushGrade> list = pushGradeManager.checkPushGradeRepeat(dn);
		double sumRate = 0D;
		if(list.size()>0){
			for(PushGrade pg:list){
				if(pg.getColorGradeId().equals(dn.getColorGradeId())&& pg.getCleanId().equals(dn.getCleanId())){
					return "存在大类+小类+分析范围+色级+净度相同的记录";
				}
				sumRate += Double.parseDouble(pg.getRate());
			}
		}
		if(sumRate + Double.parseDouble(dn.getRate()) > 100){
			return "相同的大类+小类+分析范围下分配的比例超过100%";
		}
		pushGradeManager.saveOrUpdatePushGrade(dn, userId);
		return null;
	}
	
	public String deletePushGrade(List<String> billIdList){
		pushGradeManager.deletePushGrade(billIdList);
		return null;
	}
}
