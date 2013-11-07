package com.jatools.web.dwr.calc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.jatools.common.CommonUtil;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.calc.MaterAttrUpdateManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.calc.MaterAttrUpdate;
import com.jatools.vo.stock.MaterActive;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.calc.PriceHeadController;

public class MaterAttrUpdateDwr {
	private MaterAttrUpdateManager materAttrUpdateManager;
	private Logger logger = Logger.getLogger(MaterAttrUpdate.class);
	
	public void setMaterAttrUpdateManager(
			MaterAttrUpdateManager materAttrUpdateManager) {
		this.materAttrUpdateManager = materAttrUpdateManager;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	/**
	 * 删除入库单
	 * @param headid
	 * @return
	 */
	public String deleteMaterAttrUpdate(String id){
		try {
			if(StringUtil.isEmpty(id)){
				return "不能获取属性修改单ID";
			}
			MaterAttrUpdate old = this.materAttrUpdateManager.getMaterAttrUpdateById(id);
			if(old==null){
				return "单据已被删除,请刷新页面";
			}else if(!"1".equals(old.getOrnastate()))
				return "0_单据状态已被修改,请刷新页面";
			materAttrUpdateManager.deleteMaterAttrUpdate(id);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}
	
	/**
	 * 保存修改操作
	 * @param head
	 * @param req
	 * @return
	 */
	public String saveOrUpdateMaterAttrUpdate(MaterAttrUpdate head ,HttpServletRequest req){
		try {
			return this.materAttrUpdateManager.saveUpdateMater(head, req);
		} catch (Exception e) {
			logger.error(e);
			return "2_保存出错";
		}
	}
	
	public MaterActive getMaterCode(String code,String type,HttpServletRequest req){
		Map<String,String> conditionMap = new HashMap<String, String>();
		conditionMap.put("code", code);
		conditionMap.put("type", type);
		MaterActive ma = this.materAttrUpdateManager.getMaterByCode(conditionMap);
		return ma;
	}
}
