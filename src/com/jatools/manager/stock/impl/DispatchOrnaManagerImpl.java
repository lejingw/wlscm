package com.jatools.manager.stock.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.web.util.BdCommon;
import org.apache.log4j.Logger;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.common.constant.StockConstant;
import com.jatools.dao.move.MoveBillDao;
import com.jatools.dao.pur.PurGatherDao;
import com.jatools.dao.pur.PurchaseDao;
import com.jatools.dao.stock.DispatchOrnaDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.stock.DispatchOrnaManager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchRealCost;
import com.jatools.vo.stock.DispatchTemp;
import com.jatools.vo.stock.GatherHead;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class DispatchOrnaManagerImpl extends BaseManager implements DispatchOrnaManager {
	private Logger logger = Logger.getLogger(DispatchOrnaManagerImpl.class);
	private DispatchOrnaDao dispatchOrnaDao;
	private PurGatherDao purGatherDao;
	private PurchaseDao purchaseDao;
	private CommonDao commonDao;
	private MaterActiveDao materActiveDao;
	private MoveBillDao moveBillDao;
	public void setMoveBillDao(MoveBillDao moveBillDao) {
		this.moveBillDao = moveBillDao;
	}
	public void setPurGatherDao(PurGatherDao purGatherDao) {
		this.purGatherDao = purGatherDao;
	}
	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setDispatchOrnaDao(DispatchOrnaDao dispatchOrnaDao) {
		this.dispatchOrnaDao = dispatchOrnaDao;
	}
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	/**
	 * 订单配货，总部总单列表
	 * @param conditon
	 * @return
	 */
	public Pager getGatherHeadPageData(Map<String, String> conditon){
		return dispatchOrnaDao.getGatherHeadPageData(conditon);
	}
	
	/**
	 * 获取配货参数
	 * @param gheadid
	 * @return
	 */
	public List<DispatchCondition> getDispatchCondition(String gheadid){
		return dispatchOrnaDao.getDispatchCondition(gheadid, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
	}
	

	/**
	 * 订单配货页面，获取总部总单部分数据
	 * @param gheadid
	 * @return
	 */
	public GatherHead getDispatchGatherHead(String gheadid){
		return dispatchOrnaDao.getDispatchGatherHead(gheadid);
	}
	

	/**
	 * 根据id，获取配货参数
	 * @param conditionId
	 * @return
	 */
	public DispatchCondition getDispatchConditionById(String conditionId){
		return dispatchOrnaDao.getDispatchConditionById(conditionId);
	}
	

	/**
	 * 获取总部总单行中的所有大类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineItemClassForSlt(String gheadid){
		return dispatchOrnaDao.getGatherLineItemClassForSlt(gheadid);
	}
	
	/**
	 * 根据大类
	 * 获取总部总单行中的所有小类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineOrnaClassForSlt(String gheadid, String itemClassId){
		return dispatchOrnaDao.getGatherLineOrnaClassForSlt(gheadid, itemClassId);
	}
	/**
	 * 检查配货参数是否已经存在
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public boolean checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId){
		return dispatchOrnaDao.checkDispatchCondition(gheadid, itemClassId, ornaClassId, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
	}
	/**
	 * 创建配货参数
	 */
	public synchronized String saveDispatchCondition(String gheadid, String itemClassId, String ornaClassId, String userid){
		boolean flag = dispatchOrnaDao.checkDispatchCondition(gheadid, itemClassId, ornaClassId, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
		if(!flag){
			throw new RuntimeException("该配货参数已经被使用");
		}
		DispatchCondition disCondition = new DispatchCondition();
		disCondition.setCreateDate(DateUtil.getCurrentDate18());
		disCondition.setCreateId(userid);
		disCondition.setGheadid(gheadid);
		disCondition.setItemClassId(itemClassId);
		disCondition.setOrnaClassId(ornaClassId);
		disCondition.setUpdateDate(DateUtil.getCurrentDate18());
		disCondition.setUpdateId(userid);
		disCondition.setStatus(DictConstant.BILL_STATUS_SAVE);
		disCondition.setSrcBillCode(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
		dispatchOrnaDao.createDispatchTempData(gheadid, itemClassId, ornaClassId, userid);
		return dispatchOrnaDao.saveDispatchCondition(disCondition);
	}
	/**
	 * 从减库临时数据中获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getSubtempDispatchOrnaInfo(String conditionId, String code, boolean ornaFlag){
		return  dispatchOrnaDao.getSubtempDispatchOrnaInfo(conditionId, code, ornaFlag, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
	}
	/**
	 * 获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getDispatchOrnaInfo(String code, boolean ornaFlag){
		return dispatchOrnaDao.getDispatchOrnaInfo(code, ornaFlag);
	}
	/**
	 * 获取配货记录信息
	 * @param ornaLogId
	 * @return
	 */
	public DispatchOrnaLog getDispatchOrnaLogById(String ornaLogId){
		return dispatchOrnaDao.getDispatchOrnaLogById(ornaLogId);
	}
	/**
	 * 获取配货记录信息
	 * @param ornaCode
	 * @return
	 */
	public DispatchOrnaLog getDispatchOrnaLogByOrnaCode(String ornaCode){
		return dispatchOrnaDao.getDispatchOrnaLogByOrnaCode(ornaCode, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
	}
	/**
	 * 获取配货参数下的所有分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchOrg(String conditionId){
		return dispatchOrnaDao.getDispatchOrg(conditionId);
	}
	/**
	 * 根据饰品属性进行配货
	 */
	public List<DispatchTemp> getMatchDispatchTempList(DispatchOrnaInfo info, boolean styleFlag, String styleFlag2, boolean colorGradeFlag, boolean cleanFlag, String inOrgId, String conditionId){
		String sql = dispatchOrnaByAttribute(info, styleFlag, styleFlag2, colorGradeFlag, cleanFlag, inOrgId, conditionId);
		logger.debug("==============配货sql:");
		logger.debug(sql);
		logger.debug("====================");
		return dispatchOrnaDao.getMatchDispatchTemp(sql);
	}
	/**
	 * 根据饰品属性进行配货
	 */
	public DispatchTemp getMatchDispatchTemp(DispatchOrnaInfo info, boolean styleFlag, String styleFlag2, boolean colorGradeFlag, boolean cleanFlag, String inOrgId, String conditionId) {
		List<DispatchTemp> list = getMatchDispatchTempList(info, styleFlag, styleFlag2, colorGradeFlag, cleanFlag, inOrgId, conditionId);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 获取匹配临时数据的sql
	 * @param info
	 * @return
	 */
	private String dispatchOrnaByAttribute(DispatchOrnaInfo info, boolean styleFlag, String styleFlag2, boolean colorGradeFlag, boolean cleanFlag, String inOrgId, String conditionId){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.id as \"id\", a.gather_head_id as \"gheadid\", a.gather_line_id as \"glineid\", a.order_head_id as \"oheadid\", a.order_line_id as \"olineid\", a.org_id as \"orgId\", a.user_id as \"userId\", a.item_class_id as \"itemClassId\", a.orna_class_id as \"ornaClassId\", a.style_id as \"styleId\", a.quality_id as \"qualityId\", a.bracketcolor_id as \"bracketcolorId\", a.analyse_beg as \"analyseBeg\", a.analyse_end as \"analyseEnd\", a.size_beg as \"sizeBeg\", a.size_end as \"sizeEnd\", a.color_grade_id as \"colorGradeId\", a.clean_id as \"cleanId\", a.order_num as \"orderNum\", a.order_end_date as \"orderEndDate\", a.analyse_id as \"analyseId\", a.style_item_class_id as \"styleItemClassId\", a.style_middle_class_id as \"styleMiddleClassId\", a.style_orna_class_id as \"styleOrnaClassId\", a.color_grade_grade_id as \"colorGradeGradeId\", a.clean_grade_id as \"cleanGradeId\", a.size_id as \"sizeId\", a.unit_id as \"unitId\", a2.summarydescription as \"analyseName\", a3.stylename as \"styleName\", a4.size_name as \"sizeName\",");
		sql.append("(select count(1) from jat_pl_dispatch_orna_log e where e.order_line_id=a.order_line_id and (e.status = '2' or e.status= '3' or e.status = '4')) as \"dispatched\", (select count(1) from jat_pl_dispatch_orna_log e where e.order_line_id=a.order_line_id and e.status = '1') as \"dispatching\"");
		sql.append(" from jat_pl_dispatch_temp a left join jas_bd_analysis_arange a2 on a.analyse_id = a2.analysis_arange_id left join jas_bd_style a3 on a.style_id = a3.styleid left join jas_bd_size a4 on a.size_id = a4.size_id");
		sql.append("$ where a.item_class_id = " + info.getItemClassId() + " and a.orna_class_id = " + info.getOrnaClassId());
		String basicPrice = StringUtil.isNotBlank(info.getBasicPrice())?info.getBasicPrice():"0";
        String priceRate = BdCommon.getDispatchPriceRate();
        sql.append("  and nvl(" + basicPrice + ",0) >=decode(a.GUJIAQSZ, 0,  nvl(" + basicPrice + ",0) , round(a.GUJIAQSZ*(1-" + priceRate + ")))\n" +
                "   and nvl(" + basicPrice + ",0) <=decode(a.GUJIAQSZ, 0,  nvl(" + basicPrice + ",0) , round(a.GUJIAJZZ*(1+" + priceRate + "))) ");
		if (StockConstant.ANALYSIS_UNIT_NAME_JZ.equals(info.getAnalyzeUnitName())) {
			String allQty = info.getAllQty();
			if (allQty == null)
				allQty = "0";
			sql.append(" and " + allQty + " >= a.analyse_beg and " + allQty + " < a.analyse_end");
		} else if (StockConstant.ANALYSIS_UNIT_NAME_CT.equals(info.getAnalyzeUnitName())) {
			String mainWeight = info.getMainWeight();
			if (mainWeight == null)
				mainWeight = "0";
			sql.append(" and " + mainWeight + " >= a.analyse_beg and " + mainWeight + " < a.analyse_end");
		} else if (StockConstant.ANALYSIS_UNIT_NAME_XJ.equals(info.getAnalyzeUnitName())) {
			String basePrice = info.getBasicPrice();
			if (basePrice == null)
				basePrice = "0";
			sql.append(" and " + basePrice + " >= a.analyse_beg and " + basePrice + " < a.analyse_end");
		}else{
			throw new RuntimeException("获取饰品分析单位失败");
		}
		if(StringUtil.isNotEmpty(info.getSizeName())){
			sql.append(" and a.size_beg <= " + info.getSizeName() + " and a.size_end >" + info.getSizeName());
		}
		if(StringUtil.isNotEmpty(info.getQualityId())){
//			sql.append(" and a.quality_id = " + info.getQualityId());
			//Ag≥92.50%  Ag≥99.00%          75 76
			//Au≥99.00%  Au≥99.90%          79 80
			sql.append(" and decode(a.quality_id, 75, -1, 76, -1, 79, -2, 80, -2, a.quality_id) = decode(" + info.getQualityId() + ", 75, -1, 76, -1, 79, -2, 80, -2, " + info.getQualityId() + ")");
		}
		sql.append(" and exists(select 1 from jat_pl_dispatch_condition f where f.gather_head_id = a.gather_head_id and f.src_bill_code = 'HQ' and f.item_class_id = a.item_class_id and f.orna_class_id = a.orna_class_id and f.create_id = a.user_id and f.id = " + conditionId + ")");
		if(!"-1".equals(inOrgId)){
			sql.append(" and a.org_id = " + inOrgId);
		}
		//款式
		if(styleFlag){
			sql.append(" and a.style_id = " + info.getStyleId());
		}else{
			if("1".equals(styleFlag2)){
				sql.append(" and a.style_item_class_id = " + info.getStyleItemClassId());
			}else if("2".equals(styleFlag2)){
				sql.append(" and a.style_middle_class_id = " + info.getStyleMiddleClassId());
			}if("3".equals(styleFlag2)){
				sql.append(" and a.style_orna_class_id = " + info.getStyleOrnaClassId());
			}
		}
		//色级
		if(colorGradeFlag&&StringUtil.isNotEmpty(info.getColorGradeId())){
			//将临时数据的色级根据规则放大进行匹配，而不是配货饰品放大
			sql.append(" and (nvl(a.color_grade_id,0)=0 or exists(select 1 from jas_pl_color_match_rule b where b.diam_color_grade_basic_id = a.color_grade_id and b.match_color_grade_basic_id = "+info.getColorGradeId()+"))");
		}
		String orderbySql = "";
		//净度
		int offset = sql.indexOf("$");
		if(cleanFlag&&StringUtil.isNotEmpty(info.getCleanId())){
			//将临时数据的净度根据规则放大进行匹配，而不是配货饰品放大
			sql.append("and (nvl(a.clean_id,0)=0 or exists(select 1 from jas_pl_clean_match_rule c where c.clean_basic_id = a.clean_id and nvl(c.analysis_arange_id, a.analyse_id) = a.analyse_id and c.match_clean_basic_id = "+info.getCleanId()+"))");
			sql.replace(offset, offset + 1, " left join jas_pl_clean_match_rule d on d.clean_basic_id = a.clean_id and nvl(d.analysis_arange_id, a.analyse_id) = a.analyse_id and d.match_clean_basic_id = "+info.getCleanId());
			orderbySql += ", nvl(d.analysis_arange_id, -1) asc";
		}else{
			sql.replace(offset, offset+1, "");
		}
		sql.append(" and a.order_num>(select count(1) from jat_pl_dispatch_orna_log e where e.order_line_id=a.order_line_id and e.status<>0)");
		sql.append(" order by");
		if(!styleFlag){
			sql.append(" decode(a.style_id, " + info.getStyleId() + ", 1, 0) desc,");
		}
		if(!colorGradeFlag){
			sql.append(" decode((select 1 from dual where nvl(a.color_grade_id, 0) = 0 or exists (select 1 from jas_pl_color_match_rule bb where bb.diam_color_grade_basic_id = a.color_grade_id and bb.match_color_grade_basic_id = " + info.getColorGradeId() + ")), 1, 1, 0) desc,");
		}
		if(!cleanFlag){
			sql.append(" decode((select 1 from dual where nvl(a.clean_id, 0) = 0 or exists (select 1 from jas_pl_clean_match_rule c where c.clean_basic_id = a.clean_id and nvl(c.analysis_arange_id, a.analyse_id) = a.analyse_id and c.match_clean_basic_id = " + info.getColorGradeId() + ")), 1, 1,0) desc,");
		}
		sql.append(" item_class_id").append(orderbySql).append(", a.analyse_beg, a.style_id, a.quality_id, a.color_grade_id, a.clean_id").toString();
		return sql.toString();
	}

	/**
	 * 保存配货记录
	 * @param dispatchOrnaLog
	 */
	public String saveDispatchOrnaLog(DispatchOrnaLog dispatchOrnaLog){
		dispatchOrnaLog.setSrcBillCode(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
		String ornaLogId = dispatchOrnaDao.saveDispatchOrnaLog(dispatchOrnaLog);
		//更新匹配减库记录临时表中的配货记录id段
		if(null != dispatchOrnaLog.getSubtempId()){
			dispatchOrnaDao.updateSubtempOrna(dispatchOrnaLog.getSubtempId(), ornaLogId);
		}
		//修改饰品现有量状态为901保留
//		materActiveDao.markMaterActiveUsed(dispatchOrnaLog.getOrnaCode(), GlobalConstant.BILL_CODE_DISPATCH, ornaLogId);
		materActiveDao.markMaterActiveUsedByBillDistatch(dispatchOrnaLog.getOrnaCode(), GlobalConstant.BILL_CODE_DISPATCH, dispatchOrnaLog.getGatherHeadId());
		return ornaLogId;
	}
	/**
	 * 根据配货参数id,获取正配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchingPageData(Map<String, String> condition){
		return dispatchOrnaDao.getDispatchingPageData(condition);
	}
	/**
	 * 根据配货参数id,获取已配货分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchedPageData(Map<String, String> condition){
		return dispatchOrnaDao.getDispatchedPageData(condition);
	}

	/**
	 * 根据总部总单id，获取对应所有的门店
	 * @param gheadid
	 * @return
	 */
	public List<Org> getPurchaseGatherOrg(String gheadid){
		return dispatchOrnaDao.getPurchaseGatherOrg(gheadid);
	}
	
	/**
	 * 已配货，获取大类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @return
	 */
	public List<ItemClass> getPurchaseGatherItemClass(String gheadid, String orgId){
		return dispatchOrnaDao.getPurchaseGatherItemClass(gheadid, orgId);
	}
	/**
	 * 已配货，获取小类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @param itemClassId 大类id
	 * @return
	 */
	public List<OrnaClass> getPurchaseGatherOrnaClass(String gheadid, String orgId, String itemClassId){
		return dispatchOrnaDao.getPurchaseGatherOrnaClass(gheadid, orgId, itemClassId);
	}

	/**
	 * 根据总部总单id，获取总部总单行分页数据
	 * @param condition
	 * @return
	 */
	public Pager getHqlinePageData(Map<String, String> condition){
		return dispatchOrnaDao.getHqlinePageData(condition);
	}
	/**
	 * 总部总单，获取所有大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getHqlineItemClass(String gheadid){
		return dispatchOrnaDao.getHqlineItemClass(gheadid);
	}
	/**
	 * 总部总单，根据大类获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getHqlineOrnaClass(String gheadid, String itemClassId){
		return dispatchOrnaDao.getHqlineOrnaClass(gheadid, itemClassId);
	}
	/**
	 * 获取正分配饰品信息
	 * @param condition
	 * @return
	 */
	public Pager getShowDispatchingPageData(Map<String, String> condition){
		return dispatchOrnaDao.getShowDispatchingPageData(condition);
	}
	/**
	 * 获取已分配饰品信息
	 * @param condition
	 * @return
	 */
	public Pager getShowDispatchedPageData(Map<String, String> condition){
		return dispatchOrnaDao.getShowDispatchedPageData(condition);
	}
	/**
	 * 根据配货记录id，逻辑删除
	 * @param ornaLogId
	 */
	public void deleteDispatchOrnaLog(String ornaLogId, String userid){
		DispatchOrnaLog ornaLog = dispatchOrnaDao.getDispatchOrnaLogById(ornaLogId);
		dispatchOrnaDao.deleteDispatchOrnaLog(ornaLogId, userid);//逻辑删除，将状态改为0
		materActiveDao.markMaterActiveValid(ornaLog.getOrnaCode());//改饰品状态为有效
		if(DictConstant.BILL_STATUS_MARK.equals(ornaLog.getStatus())){//已配货
			//重新计算总部总单行的已分配数量
//			dispatchOrnaDao.updateGatherLineDispatchNum(ornaLog.getGatherLineId());//根据status=2
			//根据配货记录id更新订单配货日志统计表
			dispatchOrnaDao.updateDispatchLogStatByOrnaLogId(ornaLogId, userid);
		}
	}
	/**
	 * 获取正配数量
	 */
	public int getDispatchingNum(String orderLineId){
		return dispatchOrnaDao.getDispatchingNum(orderLineId);
	}
	/**
	 * 获取已配数量
	 */
	public int getDispatchedNum(String useOrderLineIdFlag, String lineId){
		return dispatchOrnaDao.getDispatchedNum(useOrderLineIdFlag, lineId);
	}
	/**
	 * 记账
	 * @param userid
	 * @param conditionId
	 */
	public void markBill(String conditionId, String userid){
		//将保存状态的饰品记录改为记账状态
		dispatchOrnaDao.markDispatchOrnaLog(conditionId, userid);
		//更新总部总单行的已配数量
//		dispatchOrnaDao.updateGatherLineDispatchNumByConditionId(conditionId);
		//根据配货参数id，删除临时数据
		dispatchOrnaDao.deleteDispatchTempData(conditionId);
		//删除配货参数
		dispatchOrnaDao.deleteDispatchCondition(conditionId, userid);
		//根据配货参数id更新订单配货日志统计表
		updateDispatchLogStateByConditionId(conditionId, userid);
	}
	/**
	 * 根据配货参数id更新订单配货日志统计表
	 * @param conditionId
	 * @param userid
	 */
	private void updateDispatchLogStateByConditionId(String conditionId, String userid) {
		//删除配货参数id对应的订单配货日志统计表
		dispatchOrnaDao.deleteDispatchLogStat(conditionId);
		//重新统计配货参数id对应的订单配货日志统计表
		dispatchOrnaDao.createDispatchLogStat(conditionId, userid);
	}
	/**
	 * 正配货变更
	 * @param ornaLogId
	 * @param newOrderLineId
	 * @param userid
	 */
	public void exchangeDispatching(String ornaLogId, String newOrderLineId, String userid){
		//更新配货饰品记录
		dispatchOrnaDao.exchangeDispatching(ornaLogId, newOrderLineId, userid);
	}

	/**
	 * 生成调拨单获取大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getMoveItemClass(String gheadid){
		return dispatchOrnaDao.getMoveItemClass(gheadid);
	}
	/**
	 * 生成调拨单获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getMoveOrnaClass(String gheadid){
		return dispatchOrnaDao.getMoveOrnaClass(gheadid);
	}
	/**
	 * 获取调拨单分配店
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveOrgForSlt(String gheadid){
		return dispatchOrnaDao.getMoveOrgForSlt(gheadid);
	}
	/**
	 * 获取调拨单配货员
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveDispatchUserForSlt(String gheadid){
		return dispatchOrnaDao.getMoveDispatchUserForSlt(gheadid);
	}
	/**
	 * 未生成调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getUnmovebill(String gheadid){
		return dispatchOrnaDao.getUnmovebill(gheadid);
	}
	/**
	 * 调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getMovebill(String gheadid){
		return dispatchOrnaDao.getMovebill(gheadid);
	}
	/**
	 * 获取真实总成本
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @return
	 */
	public List<DispatchRealCost> getDispatchRealCost(String gheadid, String orgIds, String itemClassIds, String ornaClassIds, String srcBillCode){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("gheadid", gheadid);
		condition.put("orgIds", orgIds);
		condition.put("itemClassIds", itemClassIds);
		condition.put("ornaClassIds", ornaClassIds);
		condition.put("srcBillCode", srcBillCode);
		return dispatchOrnaDao.getDispatchRealCost(condition);
	}
	/**
	 * 生成调拨单
	 * @param gheadid
	 * @param orgIds
	 * @param itemClassIds
	 * @param ornaClassIds
	 * @param dispatchUserIds
	 * @param userid
	 */
	public synchronized List<MoveBillHead> createMoveBill(String gheadid, String orgIds, String itemClassIds, String ornaClassIds, String dispatchUserIds, String outOrgId, String userid){
		String[] orgIdArr = orgIds.split("[,]");
		String gatherHeadBillno = dispatchOrnaDao.getGatherHeadBillno(gheadid);
		List<MoveBillHead> moveBillList = new ArrayList<MoveBillHead>();
		String moveHeadIds = "$";
		for(String inOrgId : orgIdArr){
			List<String> newOrnaCodeList = dispatchOrnaDao.getOrnaClodeFromMove(gheadid, inOrgId, itemClassIds, ornaClassIds, dispatchUserIds);
			if(newOrnaCodeList.size()<1){
				continue;
			}
			Org org = OrgCache.getInstance().getOrgById(inOrgId);
			//保存调拨单头
			MoveBillHead moveHead = saveMoveBillHead(gheadid, outOrgId, userid, gatherHeadBillno, inOrgId, org.getJmFlag());
			moveBillDao.saveMoveBillLine(newOrnaCodeList, moveHead.getHeadid(), moveHead.getBillType(), org.getJmFlag(), moveHead.getInOrgId(), userid);
			moveBillDao.updateMoveBillSumNum(moveHead.getHeadid(), userid);
			moveBillDao.updateNorececount(moveHead.getHeadid(), null, userid);
			moveHead.setSumCount("" + newOrnaCodeList.size());
			moveHead.setValues();
			moveBillList.add(moveHead);
			
			moveHeadIds += "," + moveHead.getHeadid();
		}
		//状态改为已调拨3
		dispatchOrnaDao.updateDispatchOrnaStatusToMoved(gheadid, orgIds, itemClassIds, ornaClassIds, dispatchUserIds, userid);
		if(!"$".equals(moveHeadIds)){
			moveHeadIds = moveHeadIds.substring(2);
			//修改现拥有量表中的引用单据编码和单据编号段
			materActiveDao.updateBillCodeFromDispatch(moveHeadIds);
		}
		return moveBillList;
	}
	/**
	 * 保存调拨单头
	 * @param gheadid
	 * @param outOrgId
	 * @param userid
	 * @param gatherHeadBillno
	 * @param inOrgId
	 * @return
	 */
	private MoveBillHead saveMoveBillHead(String gheadid, String outOrgId, String userid, String gatherHeadBillno, String inOrgId, String jmFlag) {
		MoveBillHead moveHead = new MoveBillHead();
		moveHead.setBillno(commonDao.getBillno(DictConstant.YES_OR_NO_YES.equals(jmFlag)?GlobalConstant.BILL_CODE_DIAOBODAN_JM:GlobalConstant.BILL_CODE_DIAOBODAN));
		moveHead.setMoveType(DictConstant.MOVE_TYPE_DISPATCH);//配货调拨
		moveHead.setSrcBillCode(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);//总部要货汇总单
		moveHead.setSrcBillId(gheadid);//总部要货汇总单id
		moveHead.setSrcBillNo(gatherHeadBillno);//要货汇总单单号
		moveHead.setDodate(DateUtil.getCurrentDate10());
		moveHead.setOutOrgId(outOrgId);
		moveHead.setInOrgId(inOrgId);
		moveHead.setOutStockId(DictConstant.INVCODE_ORNA);//设置为饰品库
		moveHead.setInStockId(DictConstant.INVCODE_ORNA);//设置为饰品库
		moveHead.setMemo("订单配货生成调拨单");
		moveHead.setCreateDate(DateUtil.getCurrentDate18());
		moveHead.setCreateId(userid);
		moveHead.setUpdateDate(DateUtil.getCurrentDate18());
		moveHead.setUpdateId(userid);
		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
			moveHead.setStatus(DictConstant.BILL_STATUS_NO_ESTIMATE);//加盟调拨单的状态为未生成结算单
		}else{
			moveHead.setStatus(DictConstant.BILL_STATUS_REVIEWED);//审批完成
		}
		moveHead.setBillType(DictConstant.MOVE_BILL_TYPE_DIAOBODAN);
		moveHead.setJmFlag(jmFlag);
		
		String headid = moveBillDao.saveMoveBillHead(moveHead, userid);
		moveHead.setHeadid(headid);
		return moveHead;
	}
	/**
	 * 减库生成采购单(明细)
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	public synchronized void subPurchase(String gheadid, String userid){
		String status = super.getBillStatus("jas_pl_headquarter_gather_head", "headquarter_gather_head_id", gheadid, "state");
		if(DictConstant.PLAN_BILL_STATUS_PURCHASED.equals(status)){
			throw new RuntimeException("当前总部汇总单已生成采购单，不能再次生成采购单");
		}
		//根据总部总单行到库存表中获取，匹配的饰品，并放入配货临时表
		dispatchOrnaDao.createDispatchSubtemp(gheadid, userid);//生成减库配货临时数据
		//取总部总单行要货数量减去配货临时表中相应条件匹配的数量，且大于0的作为采购数量，获取采购总单行数据
		List<PurGatherLine> list = purGatherDao.getSubPurGatherLineData(gheadid);
		//不需要生成采购单
		if(list.size()<1){
			return ;
		}
		dispatchOrnaDao.updateHqGatherHeadStatus(gheadid, DictConstant.PLAN_BILL_STATUS_PURCHASED, PurConstant.HQ_GATHER_HEAD_DOTYPE_SUB, userid);
		//判断 若果存在这样的行记录，则根据总部总单生成采购总单头，保存采购总单行
		PurGatherHead purGatherHead = getPurGatherHeadData(gheadid, userid);
		String purGatherHeadId = purGatherDao.savePurGatherHead(purGatherHead);
		purGatherHead.setHeadid(purGatherHeadId);
		savePurGatherLines(userid, list, purGatherHead);
		//根据采购总单，生成采购单头、采购单行
		List<String> vendorIdList = purchaseDao.getVendorIdList(purGatherHead.getHeadid());
		savePurchaseHeads(vendorIdList, userid, purGatherHead, PurConstant.BILL_TYPE_MINGXI);
		purchaseDao.savePurchaseLineFromSub(purGatherHead.getHeadid(), userid);//保存采购单行表
	}
	/**
	 * 手工减库生成采购单(明细)
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	public synchronized void subPurchase2(String gheadid, String userid){
		String status = super.getBillStatus("jas_pl_headquarter_gather_head", "headquarter_gather_head_id", gheadid, "state");
		if(DictConstant.PLAN_BILL_STATUS_PURCHASED.equals(status)){
			throw new RuntimeException("当前总部汇总单已生成采购单，不能再次生成采购单");
		}
//		dispatchOrnaDao.createDispatchSubtemp(gheadid, userid);//生成减库配货临时数据
//		List<PurGatherLine> list = purGatherDao.getSubPurGatherLineData(gheadid);
		//更新配货记录表中，满足手工减库条件的饰品记录的标志位，设置为2（DISPATCH_FLAG 1正常配货0减库配货2手工减库配货）
		dispatchOrnaDao.updateDispatchOrnaDispatchFlagToManualSub(gheadid, userid);
		//取总部总单行要货数量减去配货记录表中相应条件匹配的数量，且大于0的作为采购数量，获取采购总单行数据
		List<PurGatherLine> list = purGatherDao.getSubPurGatherLineData2(gheadid);
		//不需要生成采购单
		if(list.size()<1){
			return ;
		}
		dispatchOrnaDao.updateHqGatherHeadStatus(gheadid, DictConstant.PLAN_BILL_STATUS_PURCHASED, PurConstant.HQ_GATHER_HEAD_DOTYPE_MANUALSUB, userid);
		//判断 若果存在这样的行记录，则根据总部总单生成采购总单头，保存采购总单行
		PurGatherHead purGatherHead = getPurGatherHeadData(gheadid, userid);
		String purGatherHeadId = purGatherDao.savePurGatherHead(purGatherHead);
		purGatherHead.setHeadid(purGatherHeadId);
		savePurGatherLines(userid, list, purGatherHead);
		//根据采购总单，生成采购单头、采购单行
		List<String> vendorIdList = purchaseDao.getVendorIdList(purGatherHead.getHeadid());
		savePurchaseHeads(vendorIdList, userid, purGatherHead, PurConstant.BILL_TYPE_MINGXI);
		purchaseDao.savePurchaseLineFromSub(purGatherHead.getHeadid(), userid);//保存采购单行表
	}
	/**
	 * 不减库生成采购单（明细和非明细均可）
	 * @param gheadid
	 * @param userid
	 */
	public synchronized void unsubPurchase(String gheadid, String userid){
		//根据总部总单生成采购总单头，保存采购总单行
		PurGatherHead purGatherHead = getPurGatherHeadData(gheadid, userid);
		//非明细
		if(PurConstant.ORDER_KIND_FEIMINGXI.equals(purGatherHead.getOrderKind())){
			//查看是否已经生成采购总单，同时获取采购总单头对象，包括id
			//如果未生成采购总单，则生成采购总单头行、生成采购单头行，否则进行修改
			String purGatherHeadId = dispatchOrnaDao.getPurGatherHeadId(gheadid);
			if(null == purGatherHeadId) {
				unsubPurchase(gheadid, userid, purGatherHead, PurConstant.BILL_TYPE_FEIMINGXI);
			} else {
				purGatherHead.setHeadid(purGatherHeadId);
				//如果已生成采购总单，则更新采购总单头行、生成采购单头行
				//修改采购总单头的修改人、修改时间(非明细中新添加的行记录)
				dispatchOrnaDao.updateUpdateIdAndDate(purGatherHeadId, userid);
				//更新已有的采购总单行的采购数量(非明细中新添加的行记录)
				dispatchOrnaDao.updatePurGatherNumOrderFMX(purGatherHeadId, userid);
				//创建新的采购总单行(非明细中新添加的行记录)
				dispatchOrnaDao.savePurGatherLineFMX(gheadid, purGatherHeadId, userid);
				//获取新的供应商(非明细中新添加的行记录)
				List<String> vendorIdList = dispatchOrnaDao.getVendorIdListFMX(purGatherHeadId);
				//创建新的采购单头(非明细中新添加的行记录)
				savePurchaseHeads(vendorIdList, userid, purGatherHead, PurConstant.BILL_TYPE_FEIMINGXI);
				//更新已有的采购单行的采购数量和当前增量(非明细中新添加的行记录)
				dispatchOrnaDao.updatePurchaseLineNumOrderFMX(purGatherHeadId, userid);
				//创建新的采购单行(非明细中新添加的行记录)
				dispatchOrnaDao.savePurchaseLineFMX(purGatherHeadId, userid);
			}
		}else{
			String status = super.getBillStatus("jas_pl_headquarter_gather_head", "headquarter_gather_head_id", gheadid, "state");
			if(DictConstant.PLAN_BILL_STATUS_PURCHASED.equals(status)){
				throw new RuntimeException("当前总部汇总单已生成采购单，不能再次生成采购单");
			}
			unsubPurchase(gheadid, userid, purGatherHead, PurConstant.BILL_TYPE_MINGXI);
		}
	}
	/**
	 * 不减库生成采购单
	 * @param gheadid
	 * @param userid
	 */
	private void unsubPurchase(String gheadid, String userid, PurGatherHead purGatherHead, String billType){
		//保存采购总单头
		String purGatherHeadId = purGatherDao.savePurGatherHead(purGatherHead);
		purGatherHead.setHeadid(purGatherHeadId);
		
		//根据总部总单行获取采购总单行数据
		List<PurGatherLine> list = purGatherDao.getUnsubPurGatherLineData(gheadid);
		//保存采购总单行
		savePurGatherLines(userid, list, purGatherHead);
		dispatchOrnaDao.updateHqGatherHeadStatus(gheadid, DictConstant.PLAN_BILL_STATUS_PURCHASED, PurConstant.HQ_GATHER_HEAD_DOTYPE_UNSUB, userid);
		//保存采购单头
		List<String> vendorIdList = purchaseDao.getVendorIdList(purGatherHead.getHeadid());
		savePurchaseHeads(vendorIdList, userid, purGatherHead, billType);
		//保存采购单行
		purchaseDao.savePurchaseLineFromSub(purGatherHead.getHeadid(), userid);
	}
	/**
	 * 获取采购总单数据
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	private PurGatherHead getPurGatherHeadData(String gheadid, String userid) {
		PurGatherHead purGatherHead = purGatherDao.getPurGatherHeadByGatherHeadId(gheadid);
		String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_CAIGOUZONGDAN);
		purGatherHead.setBillno(billno);
		purGatherHead.setDodate(DateUtil.getCurrentDate10());
		purGatherHead.setCreateDate(DateUtil.getCurrentDate18());
		purGatherHead.setCreateId(userid);
		purGatherHead.setUpdateDate(DateUtil.getCurrentDate18());
		purGatherHead.setUpdateId(userid);
		purGatherHead.setStatus(DictConstant.BILL_STATUS_MARK);
		purGatherHead.setSrcBillCode(GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
		return purGatherHead;
	}
	/**
	 * 保存采购总单行
	 * @param userid
	 * @param list
	 * @param purGatherHead
	 */
	private void savePurGatherLines(String userid, List<PurGatherLine> list, PurGatherHead purGatherHead) {
		for(PurGatherLine line : list){
			line.setHeadid(purGatherHead.getHeadid());
			line.setCreateId(userid);
			line.setCreateDate(DateUtil.getCurrentDate18());
			line.setUpdateId(userid);
			line.setUpdateDate(DateUtil.getCurrentDate18());
			line.setStatus(DictConstant.BILL_STATUS_SAVE);
		}
		purGatherDao.savePurGatherLine(list);
	}
	/**
	 * 保存采购单头
	 * @param userid
	 * @param purGatherHead
	 */
	private void savePurchaseHeads(List<String> vendorIdList, String userid, PurGatherHead purGatherHead, String billType) {
		List<PurchaseHead> purchaseHeadList = new ArrayList<PurchaseHead>();
		for(String vendorId : vendorIdList){
			PurchaseHead head = new PurchaseHead();
			head.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_CAIGOUDAN));
			head.setSrcBillCode(GlobalConstant.BILL_CODE_CAIGOUZONGDAN);
			head.setSrcBillId(purGatherHead.getHeadid());
			head.setBillType(billType);
			head.setBizType(null);
			head.setDodate(DateUtil.getCurrentDate10());
			head.setOrgId(purGatherHead.getOrgId());
			head.setVendorId(vendorId);
			head.setOrderCalenderId(purGatherHead.getOrderCalenderId());
			head.setOrderEndDate(purGatherHead.getOrderEndDate());
			head.setMemo(null);
			head.setCreateId(userid);
			head.setCreateDate(DateUtil.getCurrentDate18());
			head.setUpdateId(userid);
			head.setUpdateDate(DateUtil.getCurrentDate18());
			head.setStatus(DictConstant.BILL_STATUS_MARK);
			head.setManualFlag(PurConstant.PURCHASE_HEAD_MANUAL_NO);//自动生成
			purchaseHeadList.add(head);
		}
		//保存采购单头表
		purchaseDao.savePurchaseHeadList(purchaseHeadList);
	}
	/**
	 * 改总部状态为已到店
	 * @param gheadid
	 * @param userid
	 */
	public int updateHqOrnaStatus(String gheadid, String userid){
		int count = materActiveDao.updateHqOrnaStatus(gheadid, userid, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
		purchaseDao.updateHqOrnaStatus(gheadid, userid, GlobalConstant.BILL_CODE_GATHER_BILL_JIHUA);
		return count;
	}
	/**
	 * 获取减库饰品
	 * @param condition
	 * @return
	 */
	public Pager getDispatchSubTempOrnaPageData(Map<String, String> condition){
		condition.put("srcBillCode", GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
		return dispatchOrnaDao.getDispatchSubTempOrnaPageData(condition);
	}
	/**
	 * 总部总单 匹配率
	 * @return
	 */
	public String getHqlineDispatchRate(String gheadid, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
		return dispatchOrnaDao.getHqlineDispatchRate(gheadid, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
	}
	/**
	 * 已配货 匹配率
	 * @return
	 */
	public String getDispatchedDispatchRate(String gheadid, String orgId, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
		return dispatchOrnaDao.getDispatchedDispatchRate(gheadid, orgId, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
	}
	/**
	 * 获取要货总单状态
	 */
	public String getHeadquarterStatus(String gheadid){
		return dispatchOrnaDao.getHeadquarterStatus(gheadid);
	}
	/**
	 * 导出总部总单行
	 * @return
	 */
	public List<Map> getHqlineForExcel(Map<String, String> condition){
		return dispatchOrnaDao.getHqlineForExcel(condition);
	}
}
