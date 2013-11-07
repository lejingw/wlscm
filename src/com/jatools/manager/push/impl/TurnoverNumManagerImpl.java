package com.jatools.manager.push.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.dao.push.TurnoverNumDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.TurnoverNumManager;
import com.jatools.vo.push.TurnoverNum;
import com.jatools.web.util.DateUtil;
import com.opensymphony.oscache.util.StringUtil;

public class TurnoverNumManagerImpl extends BaseManager implements TurnoverNumManager {
	private CommonDao commonDao;
	private TurnoverNumDao turnoverNumDao;

	public void setTurnoverNumDao(TurnoverNumDao turnoverNumDao) {
		this.turnoverNumDao = turnoverNumDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public Pager getTurnoverNumPageData(Map<String, String> condition){
		return turnoverNumDao.getTurnoverNumPageData(condition);
	}
	
	/**
	 * 保存或修改
	 * 
	 * @param dn
	 * @param userId
	 */
	public void saveOrUpdateTurnoverNum(TurnoverNum dn, String userId) {
		dn.setUpdateDate(DateUtil.getCurrentDate18());
		dn.setUpdateId(userId);
		if (StringUtil.isEmpty(dn.getBillId())) {
			dn.setCreateDate(DateUtil.getCurrentDate18());
			dn.setCreateId(userId);
			dn.setStatus(DictConstant.BILL_STATUS_SAVE);
			turnoverNumDao.saveTurnoverNum(dn);
		}else{
			turnoverNumDao.updateTurnoverNum(dn);
		}
	}
	/**
	 * 检查记录是否存在
	 * @param dn
	 * @return
	 */
	public List<TurnoverNum> checkTurnoverNumRepeat(TurnoverNum dn){
		return turnoverNumDao.checkTurnoverNumRepeat(dn);
	}
	/**
	 * 删除
	 * @param billId
	 */
	public void deleteTurnoverNum(List<String> billIdList){
		turnoverNumDao.deleteTurnoverNum(billIdList);
	}
	/**
	 * 复制
	 */
	public void copyTurnoverNum(String srcStartDate, String srcEndDate, String targetStartDate, String targetEndDate, String userId){
		turnoverNumDao.deleteTurnoverNumByDate(targetStartDate, targetEndDate, userId);
		turnoverNumDao.copyTurnoverNum(srcStartDate, srcEndDate, targetStartDate, targetEndDate, userId);
	}
	public void saveTurnoverNumFromExcel(String seqId, String userId){
		turnoverNumDao.saveTurnoverNumFromExcel(seqId, userId);
	}
}
