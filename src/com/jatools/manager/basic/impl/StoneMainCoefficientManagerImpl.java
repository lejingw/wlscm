package com.jatools.manager.basic.impl;

import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.basic.StoneMainCoefficientDao;
import com.jatools.manager.basic.StoneMainCoefficientManager;
import com.jatools.vo.basic.StoneMainCoefficient;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class StoneMainCoefficientManagerImpl implements StoneMainCoefficientManager {

	private StoneMainCoefficientDao stoneMainCoefficientDao;
	
	public void setStoneMainCoefficientDao(StoneMainCoefficientDao stoneMainCoefficientDao) {
		this.stoneMainCoefficientDao = stoneMainCoefficientDao;
	}

	@Override
	public Pager getStoneMainCoefficientPageData(Map<String, String> condition) {
		return this.stoneMainCoefficientDao.getStoneMainCoefficientPageData(condition);
	}

	@Override
	public void saveOrUpdateStoneMainCoefficient(StoneMainCoefficient stoneMainCoefficient, String userId) {
		int str = this.stoneMainCoefficientDao.checkWeightStr(stoneMainCoefficient);
		if(str > 0){
			throw new RuntimeException("保存失败， 重量始有交叉");
		}
		int end = this.stoneMainCoefficientDao.checkWeightEnd(stoneMainCoefficient);
		if(end > 0){
			throw new RuntimeException("保存失败， 重量止有交叉");
		}
		stoneMainCoefficient.setUpdateDate(DateUtil.getCurrentDate18());
		stoneMainCoefficient.setUpdateId(userId);
		if(StringUtil.isNotBlank(stoneMainCoefficient.getId())){
			this.stoneMainCoefficientDao.updateStoneMainCoefficient(stoneMainCoefficient);
		} else {
			stoneMainCoefficient.setCreateDate(DateUtil.getCurrentDate18());
			stoneMainCoefficient.setCreateId(userId);
			this.stoneMainCoefficientDao.saveStoneMainCoefficient(stoneMainCoefficient);
		}
	}

	@Override
	public StoneMainCoefficient getStoneMainCoefficient(String id) {
		return this.stoneMainCoefficientDao.getStoneMainCoefficient(id);
	}

	@Override
	public void deleteStoneMainCoefficient(String ids) {
		if(StringUtil.isNotBlank(ids)) {
			String idArr[] = ids.split(";");
			for (String id : idArr){
				if(StringUtil.isNotBlank(id)){
					this.stoneMainCoefficientDao.deleteStoneMainCoefficient(id);
				}
			}
		}
	}

}
