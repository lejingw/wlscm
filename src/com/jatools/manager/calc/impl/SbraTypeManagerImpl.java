package com.jatools.manager.calc.impl;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.dao.calc.SbraTypeDao;
import com.jatools.manager.calc.SbraTypeManager;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraTypeHead;
import com.jatools.vo.calc.SbraTypeLine;
import com.jatools.web.util.StringUtil;

public class SbraTypeManagerImpl implements SbraTypeManager {

	private SbraTypeDao sbraTypeDao;
	
	public SbraTypeDao getSbraTypeDao() {
		return sbraTypeDao;
	}

	public void setSbraTypeDao(SbraTypeDao sbraTypeDao) {
		this.sbraTypeDao = sbraTypeDao;
	}

	@Override
	public Pager getSbraTypePageData(Map<String, String> condition) {
		return sbraTypeDao.getSbraTypePageData(condition);
	}

	@Override
	public String saveSbraTypeHead(SbraTypeHead head) {
		return sbraTypeDao.saveSbraTypeHead(head);
	}

	@Override
	public void updateSbraTypeHead(SbraTypeHead head) {
		sbraTypeDao.updateSbraTypeHead(head);
	}

	@Override
	public void deleteSbraTypeLineByHeadid(String headid) {
		sbraTypeDao.deleteSbraTypeLineByHeadid(headid);
	}

	@Override
	public void saveSbraTypeLineList(List<SbraTypeLine> lineList) {
		sbraTypeDao.saveSbraTypeLineList(lineList);
	}

	@Override
	public SbraTypeHead getSbraTypeHead(String headid) {
		return sbraTypeDao.getSbraTypeHead(headid);
	}

	@Override
	public List<SbraTypeLine> getSbraTypeLineList(String headid) {
		return sbraTypeDao.getSbraTypeLineList(headid);
	}

	@Override
	public void deleteSbraTypeHead(String headid) {
		sbraTypeDao.deleteSbraTypeLineByHeadid(headid);
		sbraTypeDao.deleteSbraTypeHead(headid);
	}

	@Override
	public void saveOrUpdateSbraType(List<SbraTypeLine> lineList,
			SbraTypeHead head) {
		String hid = head.getId();
		if(StringUtil.isEmpty(hid)){
			hid =  sbraTypeDao.saveSbraTypeHead(head);
		}else{
			sbraTypeDao.updateSbraTypeHead(head);
			sbraTypeDao.deleteSbraTypeLineByHeadid(hid);	
		}
		for (SbraTypeLine line : lineList) {
			line.setSbraTypeId(hid);
		}
		sbraTypeDao.saveSbraTypeLineList(lineList);
	}

	@Override
	public List<SbraTypeHead> getSbraTypeHeadList() {
		// TODO Auto-generated method stub
		return sbraTypeDao.getSbraTypeHeadList();
	}
	
	/**
	 * 获取配件类型下的材质
	 * @param headid
	 * @return
	 */
	public List<Quality> getSbraTypeLineSelectList(String headid){
		return sbraTypeDao.getSbraTypeLineSelectList(headid);
	}
}
