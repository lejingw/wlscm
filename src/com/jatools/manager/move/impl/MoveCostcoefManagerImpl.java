package com.jatools.manager.move.impl;

import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.move.MoveCostcoefDao;
import com.jatools.manager.move.MoveCostcoefManager;
import com.jatools.vo.move.MoveCostcoef;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class MoveCostcoefManagerImpl implements MoveCostcoefManager {
	private MoveCostcoefDao moveCostcoefDao;
	
	public void setMoveCostcoefDao(MoveCostcoefDao moveCostcoefDao) {
		this.moveCostcoefDao = moveCostcoefDao;
	}
	/**
	 * 获取调拨销价系数分页数据
	 * @param condition
	 * @return
	 */
	public Pager getMoveCostCoefPageData(Map<String, String> condition){
		return  moveCostcoefDao.getMoveCostCoefPageData(condition);
	}
	/**
	 * 保存或修改调拨销价系数
	 * @param head
	 * @param lineList
	 */
	public void saveOrUpdateMoveCostcoef(MoveCostcoef coef, String userid){
		Integer count = moveCostcoefDao.checkRepeat(coef.getItemClassId(), coef.getOrnaClassId(), coef.getCoefId());
		if(count>0){
			throw new RuntimeException("当前大类、小类记录记录已经存在");
		}
		String coefId = coef.getCoefId();
		coef.setUpdateDate(DateUtil.getCurrentDate18());
		coef.setUpdateId(userid);
		if(StringUtil.isEmpty(coefId)){
			coef.setCreateDate(DateUtil.getCurrentDate18());
			coef.setCreateId(userid);
			coef.setStatus(DictConstant.BILL_STATUS_SAVE);
			coefId = moveCostcoefDao.saveMoveCostcoef(coef);
		}else{
			moveCostcoefDao.updateMoveCostcoef(coef);
		}
	}
	/**
	 * 获取记录对象
	 * @param coefId
	 * @return
	 */
	public MoveCostcoef getMoveCostcoef(String coefId){
		return moveCostcoefDao.getMoveCostcoef(coefId);
	}
	/**
	 * 根据大类获取小类
	 * @param itemClassId
	 * @return
	 */
	public List<SelectorOption> getOrnaClassByItemClassIdForSlt(String itemClassId, String coefId){
		return moveCostcoefDao.getOrnaClassByItemClassIdForSlt(itemClassId, coefId);
	}
	/**
	 * 删除调拨成本系数
	 * @param coefIdArr
	 * @return
	 */
	public void deleteMoveCostcoef(String[] coefIdArr){
		for(String coefId : coefIdArr){
			if(StringUtil.isNotEmpty(coefId)){
				moveCostcoefDao.deleteMoveCostcoef(coefId);
			}
		}
	}
}
