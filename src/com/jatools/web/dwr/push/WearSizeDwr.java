package com.jatools.web.dwr.push;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jatools.common.CommonUtil;
import com.jatools.manager.push.WearSizeManager;
import com.jatools.manager.push.WearSizeManager;
import com.jatools.vo.bd.Size;
import com.jatools.vo.push.WearSize;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.StringUtil;

public class WearSizeDwr {
	private WearSizeManager wearSizeManager;
	

	public void setWearSizeManager(WearSizeManager wearSizeManager) {
		this.wearSizeManager = wearSizeManager;
	}

	public String saveOrUpdateWearSize(WearSize dn, HttpServletRequest req){
		String userId = CommonUtil.getSessionUserId(req);
		List<WearSize> list = wearSizeManager.checkWearSizeRepeat(dn);
		double sumRate = 0D;
		if(list.size()>0){
			for(WearSize ps:list){
				if(Double.valueOf(ps.getStartSizeNum()) - Double.valueOf(dn.getStartSizeNum())<=0 && Double.valueOf(ps.getEndSizeNum()) - Double.valueOf(dn.getStartSizeNum())>=0){
					return "相同的大类+小类+佩戴对象下存在尺寸范围["+ps.getStartSizeName()+"-"+ps.getEndSizeName()+"]与当前的范围交叉";
				}
				if(Double.valueOf(ps.getStartSizeNum()) - Double.valueOf(dn.getEndSizeNum())<=0 && Double.valueOf(ps.getEndSizeNum()) - Double.valueOf(dn.getEndSizeNum())>=0){
					return "相同的大类+小类+佩戴对象下存在尺寸范围["+ps.getStartSizeName()+"-"+ps.getEndSizeName()+"]与当前的范围交叉";
				}
				sumRate += Double.parseDouble(ps.getRate());
			}
		}
		if(sumRate + Double.parseDouble(dn.getRate()) > 100){
			return "相同的大类+小类+佩戴对象下分配的比例超过100%";
		}
		wearSizeManager.saveOrUpdateWearSize(dn, userId);
		return null;
	}
	
	public String deleteWearSize(List<String> billIdList){
		wearSizeManager.deleteWearSize(billIdList);
		return null;
	}
	/**
	 * 大小类的尺寸
	 * @param itemId
	 * @param ornaId
	 * @return
	 */
	public List<SelectorOption> getSizeByItemIdAndOrnaId(String itemId,String ornaId){
		if(StringUtil.isEmpty(itemId)||StringUtil.isEmpty(ornaId))
			return null;
		List<Size> list = wearSizeManager.getSizeByItemIdAndOrnaId(itemId, ornaId);
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(Size bc : list){
			sltList.add(new SelectorOption(bc.getSizeId() + "," + bc.getSizeNum(), bc.getSizeName()));
		}
		return sltList;
	}

}
