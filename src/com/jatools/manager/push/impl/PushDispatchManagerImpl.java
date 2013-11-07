package com.jatools.manager.push.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.common.constant.PurConstant;
import com.jatools.dao.move.MoveBillDao;
import com.jatools.dao.pur.PurGatherDao;
import com.jatools.dao.pur.PurchaseDao;
import com.jatools.dao.push.GatherOrderDao;
import com.jatools.dao.push.PushDispatchDao;
import com.jatools.dao.stock.DispatchOrnaDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.PushDispatchManager;
import com.jatools.vo.bd.ItemClass;
import com.jatools.vo.bd.Org;
import com.jatools.vo.bd.OrnaClass;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.pur.PurGatherHead;
import com.jatools.vo.pur.PurGatherLine;
import com.jatools.vo.pur.PurchaseHead;
import com.jatools.vo.push.GatherOrderHead;
import com.jatools.vo.stock.DispatchCondition;
import com.jatools.vo.stock.DispatchMoveBill;
import com.jatools.vo.stock.DispatchOrnaInfo;
import com.jatools.vo.stock.DispatchOrnaLog;
import com.jatools.vo.stock.DispatchStatistics;
import com.jatools.vo.stock.PushDispatchTemp;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.DictCache;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrgCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class PushDispatchManagerImpl extends BaseManager implements PushDispatchManager {
	private PushDispatchDao pushDispatchDao;
	private GatherOrderDao gatherOrderDao;
	private PurGatherDao purGatherDao;
	private PurchaseDao purchaseDao;
	private CommonDao commonDao;
	private DispatchOrnaDao dispatchOrnaDao;
	private MaterActiveDao materActiveDao;
	private MoveBillDao moveBillDao;
	
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setDispatchOrnaDao(DispatchOrnaDao dispatchOrnaDao) {
		this.dispatchOrnaDao = dispatchOrnaDao;
	}

	public void setPurGatherDao(PurGatherDao purGatherDao) {
		this.purGatherDao = purGatherDao;
	}

	public void setGatherOrderDao(GatherOrderDao gatherOrderDao) {
		this.gatherOrderDao = gatherOrderDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public PushDispatchDao getPushDispatchDao() {
		return pushDispatchDao;
	}

	public void setPushDispatchDao(PushDispatchDao pushDispatchDao) {
		this.pushDispatchDao = pushDispatchDao;
	}

	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}

	public void setMoveBillDao(MoveBillDao moveBillDao) {
		this.moveBillDao = moveBillDao;
	}

	/**
	 * 减库生成采购单(明细)
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	public synchronized void subPurchase(String gheadid, String userid){
		String status = super.getBillStatus("jat_pl_gather_set_head", "headid", gheadid, "status");
		String isLoveStyle = super.getBillStatus("jat_pl_gather_set_head", "headid", gheadid, "is_love_style");
        if("1".equals(isLoveStyle)) {
            throw new RuntimeException("情侣类总单不允许减库生成采购单");
        }
		if(DictConstant.BILL_STATUS_CREATE_PURCHASE.equals(status)){
			throw new RuntimeException("当前总部总单已生成采购单，不能再次生成采购单");
		}
		//根据总部总单行到库存表中获取，匹配的饰品，并放入配货临时表
		pushDispatchDao.createDispatchSubtemp(gheadid, userid);//生成减库配货临时数据
		//取总部总单行要货数量减去配货临时表中相应条件匹配的数量，且大于0的作为采购数量，获取采购总单行数据
		List<PurGatherLine> list = pushDispatchDao.getSubPurGatherLineData(gheadid);
		//不需要生成采购单
		if(list.size()<1){
			return ;
		}
		pushDispatchDao.updateHqGatherHeadStatus(gheadid, DictConstant.BILL_STATUS_CREATE_PURCHASE, PurConstant.HQ_GATHER_HEAD_DOTYPE_SUB, userid);
		//判断 若果存在这样的行记录，则根据总部总单生成采购总单头，保存采购总单行
		PurGatherHead purGatherHead = getPurGatherHeadData(gheadid, userid);
		String purGatherHeadId = purGatherDao.savePurGatherHead(purGatherHead);
		purGatherHead.setHeadid(purGatherHeadId);
		savePurGatherLines(userid, list, purGatherHead);
		//根据采购总单，生成采购单头、采购单行
		List<String> vendorIdList = purchaseDao.getVendorIdList(purGatherHead.getHeadid());
		savePurchaseHeads(vendorIdList, userid, purGatherHead);
		purchaseDao.savePurchaseLineFromSub(purGatherHead.getHeadid(), userid);//保存采购单行表
	}
	/**
	 * 手工减库生成采购单(明细)
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	public synchronized void subPurchase2(String gheadid, String userid){
		String status = super.getBillStatus("jat_pl_gather_set_head", "headid", gheadid, "status");
        String isLoveStyle = super.getBillStatus("jat_pl_gather_set_head", "headid", gheadid, "is_love_style");
        if("1".equals(isLoveStyle)) {
            throw new RuntimeException("情侣类总单不允许手工减库生成采购单");
        }
		if(DictConstant.BILL_STATUS_CREATE_PURCHASE.equals(status)){
			throw new RuntimeException("当前总部汇总单已生成采购单，不能再次生成采购单");
		}
		//更新配货记录表中，满足手工减库条件的饰品记录的标志位，设置为2（DISPATCH_FLAG 1正常配货0减库配货2手工减库配货）
		pushDispatchDao.updateDispatchOrnaDispatchFlagToManualSub(gheadid, userid);
		//取总部总单行要货数量减去配货记录表中相应条件匹配的数量，且大于0的作为采购数量，获取采购总单行数据
		List<PurGatherLine> list = purGatherDao.getSubPurGatherLineData2(gheadid);
		//不需要生成采购单
		if(list.size()<1){
			return ;
		}
		pushDispatchDao.updateHqGatherHeadStatus(gheadid, DictConstant.BILL_STATUS_CREATE_PURCHASE, PurConstant.HQ_GATHER_HEAD_DOTYPE_MANUALSUB, userid);
		//判断 若果存在这样的行记录，则根据总部总单生成采购总单头，保存采购总单行
		PurGatherHead purGatherHead = getPurGatherHeadData(gheadid, userid);
		String purGatherHeadId = purGatherDao.savePurGatherHead(purGatherHead);
		purGatherHead.setHeadid(purGatherHeadId);
		savePurGatherLines(userid, list, purGatherHead);
		//根据采购总单，生成采购单头、采购单行
		List<String> vendorIdList = purchaseDao.getVendorIdList(purGatherHead.getHeadid());
		savePurchaseHeads(vendorIdList, userid, purGatherHead);
		purchaseDao.savePurchaseLineFromSub(purGatherHead.getHeadid(), userid);//保存采购单行表
	}

	/**
	 * 不减库生成采购单（明细和非明细均可）
	 * @param gheadid
	 * @param userid
	 */
	public synchronized void unsubPurchase(String gheadid, String userid){
		String status = super.getBillStatus("jat_pl_gather_set_head", "headid", gheadid, "status");
        String isLoveStyle = super.getBillStatus("jat_pl_gather_set_head", "headid", gheadid, "is_love_style");
        if("1".equals(isLoveStyle)) {
            throw new RuntimeException("情侣类总单不允许不减库生成采购单");
        }
		if(DictConstant.BILL_STATUS_CREATE_PURCHASE.equals(status)){
			throw new RuntimeException("当前总部汇总单已生成采购单，不能再次生成采购单");
		}
		//根据总部总单生成采购总单头，保存采购总单行
		PurGatherHead purGatherHead = getPurGatherHeadData(gheadid, userid);
		unsubPurchase(gheadid, userid, purGatherHead);
	}
	/**
	 * 获取采购总单数据
	 * @param gheadid
	 * @param userid
	 * @return
	 */
	private PurGatherHead getPurGatherHeadData(String gheadid, String userid) {
		GatherOrderHead head = gatherOrderDao.getGatherOrderHead(gheadid);
		PurGatherHead purGatherHead = new PurGatherHead();
		purGatherHead.setGatherHeadId(gheadid);
		purGatherHead.setOrderType(DictConstant.ORDER_TYPE_TUISHIXIADAN);//推式下单
		purGatherHead.setOrderKind(DictConstant.ORDER_KIND_ZHENGCHANG);//推式下单 推式下单属于正常下单 modidied by renming 2012-10-10
		purGatherHead.setRegionId(head.getRegionId());
		purGatherHead.setOrderCalenderId(head.getCycleTypeId());
		purGatherHead.setOrderEndDate(head.getPurDateEnd());
		purGatherHead.setOrgId(head.getOrgId());
		
		String billno = commonDao.getBillno(GlobalConstant.BILL_CODE_CAIGOUZONGDAN);
		purGatherHead.setBillno(billno);
		purGatherHead.setDodate(DateUtil.getCurrentDate10());
		purGatherHead.setCreateDate(DateUtil.getCurrentDate18());
		purGatherHead.setCreateId(userid);
		purGatherHead.setUpdateDate(DateUtil.getCurrentDate18());
		purGatherHead.setUpdateId(userid);
		purGatherHead.setStatus(DictConstant.BILL_STATUS_MARK);
		purGatherHead.setSrcBillCode(GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
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
	private void savePurchaseHeads(List<String> vendorIdList, String userid, PurGatherHead purGatherHead) {
		List<PurchaseHead> purchaseHeadList = new ArrayList<PurchaseHead>();
		for(String vendorId : vendorIdList){
			PurchaseHead head = new PurchaseHead();
			head.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_CAIGOUDAN));
			head.setSrcBillCode(GlobalConstant.BILL_CODE_CAIGOUZONGDAN);
			head.setSrcBillId(purGatherHead.getHeadid());
			head.setBillType(PurConstant.BILL_TYPE_MINGXI);
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
	 * 不减库生成采购单
	 * @param gheadid
	 * @param userid
	 */
	private void unsubPurchase(String gheadid, String userid, PurGatherHead purGatherHead){
		//保存采购总单头
		String purGatherHeadId = purGatherDao.savePurGatherHead(purGatherHead);
		purGatherHead.setHeadid(purGatherHeadId);
		
		//根据总部总单行获取采购总单行数据
		List<PurGatherLine> list = pushDispatchDao.getUnsubPurGatherLineData(gheadid);
		//保存采购总单行
		savePurGatherLines(userid, list, purGatherHead);
		
		pushDispatchDao.updateHqGatherHeadStatus(gheadid, DictConstant.BILL_STATUS_CREATE_PURCHASE, PurConstant.HQ_GATHER_HEAD_DOTYPE_UNSUB, userid);
		//保存采购单头
		List<String> vendorIdList = purchaseDao.getVendorIdList(purGatherHead.getHeadid());
		savePurchaseHeads(vendorIdList, userid, purGatherHead);
		//保存采购单行
		purchaseDao.savePurchaseLineFromSub(purGatherHead.getHeadid(), userid);
	}

	public List<DispatchCondition> getConditionLine(String headid){
		return dispatchOrnaDao.getDispatchCondition(headid, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
	}
	
	//------------------------------------------------------------------------------------------------------
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
		return pushDispatchDao.getGatherLineItemClassForSlt(gheadid);
	}
	
	/**
	 * 根据大类
	 * 获取总部总单行中的所有小类
	 * 供生成配货参数和总部总单查询使用
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getGatherLineOrnaClassForSlt(String gheadid, String itemClassId){
		return pushDispatchDao.getGatherLineOrnaClassForSlt(gheadid, itemClassId);
	}
	/**
	 * 检查配货参数是否已经存在
	 * @param gheadid
	 * @param itemClassId
	 * @param ornaClassId
	 * @return
	 */
	public boolean checkDispatchCondition(String gheadid, String itemClassId, String ornaClassId){
		return dispatchOrnaDao.checkDispatchCondition(gheadid, itemClassId, ornaClassId, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
	}
	/**
	 * 创建配货参数
	 */
	public synchronized String saveDispatchCondition(String gheadid, String itemClassId, String ornaClassId, String userid){
		boolean flag = dispatchOrnaDao.checkDispatchCondition(gheadid, itemClassId, ornaClassId, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
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
		disCondition.setSrcBillCode(GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
//		dispatchOrnaDao.createDispatchTempData(gheadid, itemClassId, ornaClassId, userid);
		return dispatchOrnaDao.saveDispatchCondition(disCondition);
	}
	/**
	 * 从减库临时数据中获取配货饰品信息
	 * @param code
	 * @param ornaFlag 
	 * @return
	 */
	public DispatchOrnaInfo getSubtempDispatchOrnaInfo(String conditionId, String code, boolean ornaFlag){
		return  dispatchOrnaDao.getSubtempDispatchOrnaInfo(conditionId, code, ornaFlag, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
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
//	/**
//	 * 获取配货记录信息
//	 * @param ornaCode
//	 * @return
//	 */
//	public DispatchOrnaLog getDispatchOrnaLogById(String ornaLogId){
//		return dispatchOrnaDao.getDispatchOrnaLogById(ornaLogId);
//	}
	/**
	 * 获取配货记录信息
	 * @param ornaCode
	 * @return
	 */
	public DispatchOrnaLog getDispatchOrnaLogByOrnaCode(String ornaCode){
		return dispatchOrnaDao.getDispatchOrnaLogByOrnaCode(ornaCode, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
	}
	/**
	 * 获取正配货分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchingOrg(String conditionId){
		return pushDispatchDao.getDispatchingOrg(conditionId);
	}
	/**
	 * 根据饰品属性进行配货
	 */
	public PushDispatchTemp getMatchDispatchTemp(DispatchOrnaInfo info, String inOrgId, String conditionId) {
		List<PushDispatchTemp> list = dispatchOrnaByAttribute(info, inOrgId, conditionId, false);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	/**
	 * 变更列表
	 */
	public List<PushDispatchTemp> getMatchDispatchTempList(DispatchOrnaInfo info, String inOrgId, String conditionId) {
		return dispatchOrnaByAttribute(info, inOrgId, conditionId, true);
	}
	/**
	 * 获取匹配临时数据的sql
	 * @param info
	 * @return
	 */
	private List<PushDispatchTemp> dispatchOrnaByAttribute(DispatchOrnaInfo info, String inOrgId, String conditionId, boolean isList){
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("conditionId", conditionId);
		condition.put("itemClassId", info.getItemClassId());
		condition.put("ornaClassId", info.getOrnaClassId());
		condition.put("analysisId", info.getAnalyseId());
		condition.put("styleItemClassId", info.getStyleItemClassId());
		condition.put("styleId", info.getStyleId());
		condition.put("cleanId", info.getCleanId());
		condition.put("colorGradeId", info.getColorGradeId());
		condition.put("qualityId", info.getQualityId());
		condition.put("sizeId", info.getSizeId());
		condition.put("unitId", info.getUnitId());
		condition.put("styleId", info.getStyleId());
		condition.put("bracketColorId", info.getBracketcolorId());
		condition.put("ornaCode", info.getOrnaCode());
		condition.put("isList", isList?"1":"0");

		
		Map<String, String> otherInfo = pushDispatchDao.getPushDispatchData(condition);
		if(null == otherInfo || otherInfo.size()<1){
			throw new RuntimeException("类别["
					+ ItemClassCache.getInstance().getNameById(info.getItemClassId()) + "+"
					+ OrnaClassCache.getInstance().getNameById(info.getOrnaClassId()) + "+"
					+ info.getAnalyseName() + "+"
                    + info.getStyleName()
					+ "]不属于当前总单，不能配货");
		}
        String isLoveStyle = otherInfo.get("isLoveStyle");
        if("1".equals(isLoveStyle)) {// 情侣类
            return this.dispatchOrnaByAttribute(info, otherInfo, inOrgId, conditionId, isList);
        }
		otherInfo.put("styleId", info.getStyleId());
		otherInfo.put("colorGradeId", info.getColorGradeId());
		otherInfo.put("cleanId", info.getCleanId());
		otherInfo.put("colorGradeGradeId", info.getColorGradeGradeId());
		otherInfo.put("cleanGradeId", info.getCleanGradeId());
		otherInfo.put("date", DateUtil.getCurrentDate10());

		String stockNumWeights = DictCache.getInstance().getValue(DictConstant.PUSH_DISPATCH_WEIGHTS, DictConstant.PUSH_DISPATCH_WEIGHTS_STOCK_NUM);
		String gradeNumWeights = DictCache.getInstance().getValue(DictConstant.PUSH_DISPATCH_WEIGHTS, DictConstant.PUSH_DISPATCH_WEIGHTS_GRADE_NUM);
		String styleNumWeights = DictCache.getInstance().getValue(DictConstant.PUSH_DISPATCH_WEIGHTS, DictConstant.PUSH_DISPATCH_WEIGHTS_STYLE_NUM);
		otherInfo.put("stockNumWeights", DictConstant.PUSH_DISPATCH_WEIGHTS_STOCK_NUM.equals(stockNumWeights)?"9":stockNumWeights);//库存数量权重 默认取9
		otherInfo.put("gradeNumWeights", DictConstant.PUSH_DISPATCH_WEIGHTS_GRADE_NUM.equals(gradeNumWeights)?"5":gradeNumWeights);//品质数量权重 默认取5
		otherInfo.put("styleNumWeights", DictConstant.PUSH_DISPATCH_WEIGHTS_STYLE_NUM.equals(styleNumWeights)?"2":styleNumWeights);//款式数量权重 默认取2

        otherInfo.put("itemClassId", info.getItemClassId());
        otherInfo.put("ornaClassId", info.getOrnaClassId());
        otherInfo.put("styleItemClassId", info.getStyleItemClassId());
        otherInfo.put("analysisId", info.getAnalyseId());
        otherInfo.put("ornaCode", info.getOrnaCode());

		String[] tmpStr = new String[] { "headid", "lineid", "saleDisRate",
				"saleTurnRate", "styleId", "colorGradeId", "cleanId", "colorGradeGradeId", "cleanGradeId", "date",
				"stockNumWeights", "gradeNumWeights", "styleNumWeights" ,"itemClassId", "ornaClassId", "styleItemClassId",
                "ornaCode", "gatherLineId", "analysisId"};
		boolean salableFlag = "1".equals(otherInfo.get("salableFlag"));
		
		String sql = null;
		sql =		"select aaa.org_id          as \"orgId\",\n" +
					"       aaa.dispatched_num  as \"dispatchedNum\",\n" + 
					"       aaa.dispatching_num as \"dispatchingNum\",\n" + 
					"       aaa.match_degee     as \"matchDegee\",\n" +
					"       round(aaa.num_diff,4)        as \"numDiff\",\n" +
					"       round(aaa.grade_diff,4)      as \"gradeDiff\",\n" +
					"       aaa.gheadid         as \"gheadid\",\n" +
					"      $gatherLineId$         as \"glineid\",\n" +
					"       aaa.pur_date_end    as \"orderEndDate\"\n" + 
					"  from (select aa.*,\n" + 
					"               nvl(cc.dispatched_num, 0) as dispatched_num,\n" + 
					"               nvl(cc.dispatching_num, 0) as dispatching_num,\n";
		if(salableFlag){
			sql +=	"               (select $stockNumWeights$ * (case\n" + 
					"                         when (aa.salable_real_num + aa.salable_real_num2 +\n" + 
					"                              nvl(cc.salable_dispatch_num, 0)) <\n" + 
					"                              (aa.salable_dis_num * $saleDisRate$ / 100 +\n" + 
					"                              aa.salable_turn_num * $saleTurnRate$ / 100) then\n" + 
					"                          1\n" + 
					"                         else\n" + 
					"                          -1\n" + 
					"                       end) + $gradeNumWeights$ * (case\n" + 
					"                         when (aa.salable_grade_real_num + aa.salable_grade_real_num2 +\n" + 
					"                              nvl(cc.salable_grade_dispatch_num, 0)) <\n" + 
					"                              (aa.salable_dis_num * $saleDisRate$ / 100 +\n" + 
					"                              aa.salable_turn_num * $saleTurnRate$ / 100) *\n" + 
					"                              aa.salable_grade_rate then\n" + 
					"                          1\n" + 
					"                         else\n" + 
					"                          -1\n" + 
					"                       end) + $styleNumWeights$ * (case\n" + 
					"                         when (aa.style_num + aa.style_num2 + nvl(cc.style_dispatch_num, 0)) <\n" + 
					"                              (nvl(bb.min_count, 0) + nvl(dd.min_count, 0)) then\n" +
					"                          2\n" + 
					"                         when (nvl(bb.min_count, 0) + nvl(dd.min_count, 0)) <=\n" +
					"                              (aa.style_num + aa.style_num2 + nvl(cc.style_dispatch_num, 0)) and\n" + 
					"                              (aa.style_num + aa.style_num2 + nvl(cc.style_dispatch_num, 0)) <=\n" + 
					"                              (nvl(bb.max_count, 0) + nvl(dd.max_count, 0)) then\n" +
					"                          1\n" + 
					"                         else\n" + 
					"                          -1\n" + 
					"                       end)\n" + 
					"                  from dual) as match_degee,\n" + 
					"               case when (aa.salable_dis_num * $saleDisRate$ / 100 + aa.salable_turn_num * $saleTurnRate$ / 100) = 0 then 0 else \n" + 
					"                   (1 - ((aa.salable_real_num + aa.salable_real_num2 + nvl(cc.salable_dispatch_num, 0)) /\n" + 
					"                   (aa.salable_dis_num * $saleDisRate$ / 100 +\n" + 
					"                   aa.salable_turn_num * $saleTurnRate$ / 100))) \n" + 
					"               end as num_diff,\n" + 
					"               case when ((aa.salable_dis_num * $saleDisRate$ / 100 + aa.salable_turn_num * $saleTurnRate$ / 100) * aa.salable_grade_rate) = 0 then 0 else \n" + 
					"                   (1 - ((aa.salable_grade_real_num + aa.salable_grade_real_num2 + nvl(cc.salable_grade_dispatch_num, 0)) /\n" + 
					"                   ((aa.salable_dis_num * $saleDisRate$ / 100 +\n" + 
					"                   aa.salable_turn_num * $saleTurnRate$ / 100) * aa.salable_grade_rate))) \n" + 
					"               end as grade_diff\n";
		}else{
			sql +=	"               (select $stockNumWeights$ * (case\n" + 
					"                         when (aa.real_num + aa.real_num2 + nvl(cc.dispatch_num, 0) -\n" + 
					"                              aa.salable_real_num -\n" + 
					"                              aa.salable_real_num2 -\n" + 
					"                              nvl(cc.salable_dispatch_num, 0)) <\n" + 
					"                              (aa.salable_dis_num * (100 - $saleDisRate$) / 100 +\n" + 
					"                              aa.salable_turn_num * (100 - $saleTurnRate$) / 100) then\n" + 
					"                          1\n" + 
					"                         else\n" + 
					"                          -1\n" + 
					"                       end) + $gradeNumWeights$ * (case\n" + 
					"                         when (aa.grade_real_num + aa.grade_real_num2 +\n" + 
					"                              nvl(cc.grade_dispatch_num, 0) -\n" + 
					"                              aa.salable_grade_real_num -\n" + 
					"                              aa.salable_grade_real_num2 -\n" + 
					"                              nvl(cc.salable_grade_dispatch_num, 0)) <\n" + 
					"                              (aa.salable_dis_num * (100 - $saleDisRate$) / 100 +\n" + 
					"                              aa.salable_turn_num * (100 - $saleTurnRate$) / 100) *\n" + 
					"                              aa.salable_grade_rate then\n" + 
					"                          1\n" + 
					"                         else\n" + 
					"                          -1\n" + 
					"                       end) + $styleNumWeights$ * (case\n" + 
					"                         when (aa.style_num + aa.style_num2 + nvl(cc.style_dispatch_num, 0)) <\n" + 
					"                              nvl(dd.min_count, 0) then\n" + 
					"                          2\n" + 
					"                         when nvl(dd.min_count, 0) <=\n" + 
					"                              (aa.style_num + aa.style_num2 + nvl(cc.style_dispatch_num, 0)) and\n" + 
					"                              (aa.style_num + aa.style_num2 + nvl(cc.style_dispatch_num, 0)) <=\n" + 
					"                              nvl(dd.max_count, 0) then\n" + 
					"                          1\n" + 
					"                         else\n" + 
					"                          -1\n" + 
					"                       end)\n" + 
					"                  from dual) as match_degee,\n" + 
					"               case when (aa.salable_dis_num * (100 - $saleDisRate$) / 100 + aa.salable_turn_num * (100 - $saleDisRate$) / 100) = 0 then 0 else \n" + 
					"                   (1 - ((aa.real_num + aa.real_num2 + nvl(cc.dispatch_num, 0) -\n" + 
					"                   aa.salable_real_num - aa.salable_real_num2 - nvl(cc.salable_dispatch_num, 0)) /\n" + 
					"                   (aa.salable_dis_num * (100 - $saleDisRate$) / 100 +\n" + 
					"                   aa.salable_turn_num * (100 - $saleDisRate$) / 100))) \n" + 
					"               end as num_diff,\n" + 
					"               case when ((aa.salable_dis_num * (100 - $saleDisRate$) / 100 + aa.salable_turn_num * (100 - $saleDisRate$) / 100) * aa.salable_grade_rate) = 0 then 0 else \n" + 
					"                   (1 - ((aa.grade_real_num + aa.grade_real_num2 + nvl(cc.grade_dispatch_num, 0) -\n" + 
					"                   aa.salable_grade_real_num - aa.salable_grade_real_num2 -\n" + 
					"                   nvl(cc.salable_grade_dispatch_num, 0)) /\n" + 
					"                   ((aa.salable_dis_num * (100 - $saleDisRate$) / 100 +\n" + 
					"                   aa.salable_turn_num * (100 - $saleDisRate$) / 100) *\n" + 
					"                   aa.salable_grade_rate))) \n" +
					"               end as grade_diff\n";
		}
		sql +=		"          from (select b.org_id,\n" + 
					"                       (select nvl(sum(nvl(a1.stock_num, 0)), 0)\n" + 
					"                          from jat_pl_stock_statistics_grade a1\n" + 
					"                         where a1.org_id = b.org_id\n" + 
					"                           and exists\n" + 
					"                         (select 1\n" + 
					"                                  from jat_pl_gather_set_style b1\n" + 
					"                                 where b1.lineid = $lineid$\n" + 
					"                                   and b1.salable_flag = 1\n" + 
					"                                   and a1.style_id = b1.style_id)) as salable_real_num, --畅销款实际总库存\n" + 
					"                       (select nvl(sum(nvl(a1.onway_num, 0)), 0)\n" + 
					"                          from jat_pl_onway_statistics_grade a1\n" + 
					"                         where a1.org_id = b.org_id\n" + 
					"                           and exists\n" + 
					"                         (select 1\n" + 
					"                                  from jat_pl_gather_set_style b1\n" + 
					"                                 where b1.lineid = $lineid$\n" + 
					"                                   and b1.salable_flag = 1\n" + 
					"                                   and a1.style_id = b1.style_id)) as salable_real_num2, --畅销款实际总在途\n" + 
					"                       (select nvl(max(nvl(a2.distribute_num, 0)), 0)\n" + 
					"                          from jat_pl_basic_distribute_num a2, jat_pl_gather_set_line b2\n" + 
					"                        where a2.org_id = b.org_id\n" +
					"                          and b2.lineid = $lineid$\n" + 
					"                          and a2.item_class_id = b2.item_class_id\n" + 
					"                          and a2.orna_class_id = b2.orna_class_id\n" + 
					"                          and a2.analysis_id = b2.analysis_id\n" + 
					"                          and a2.style_item_class_id = b2.style_item_class_id) as salable_dis_num, --畅销款标准铺货量\n" + 
					"                       (select nvl(max(nvl(a3.turnover_num, 0)), 0)\n" + 
					"                          from jat_pl_basic_turnover_num a3, jat_pl_gather_set_line b3\n" + 
					"                         where a3.org_id = b.org_id\n" + 
					"                           and b3.lineid = $lineid$\n" + 
					"                           and a3.item_class_id = b3.item_class_id\n" + 
					"                           and a3.orna_class_id = b3.orna_class_id\n" + 
					"                           and a3.analysis_id = b3.analysis_id\n" + 
					"                           and a3.style_item_class_id = b3.style_item_class_id\n" + 
					"                           and a3.start_date <= '$date$'\n" + 
					"                           and '$date$' <= a3.end_date) as salable_turn_num, --畅销款标准周转量\n" + 
					"                       (select nvl(sum(nvl(a4.stock_num, 0)), 0)\n" + 
					"                          from jat_pl_stock_statistics_grade a4\n" + 
					"                         where a4.org_id = b.org_id\n" + 
					"                           and a4.color_grade_id $colorGradeId$\n" + 
					"                           and a4.clean_id $cleanId$\n" + 
					"                           and exists (select 1\n" + 
					"                                  from jat_pl_gather_set_style b3\n" + 
					"                                 where a4.style_id = b3.style_id\n" + 
					"                                   and b3.lineid = $lineid$\n" + 
					"                                   and b3.salable_flag = 1)) as salable_grade_real_num, --畅销款品质实际总库存\n" + 
					"                       (select nvl(sum(nvl(a4.onway_num, 0)), 0)\n" + 
					"                          from jat_pl_onway_statistics_grade a4\n" + 
					"                         where a4.org_id = b.org_id\n" + 
					"                           and a4.color_grade_id $colorGradeId$\n" + 
					"                           and a4.clean_id $cleanId$\n" + 
					"                           and exists (select 1\n" + 
					"                                  from jat_pl_gather_set_style b3\n" + 
					"                                 where a4.style_id = b3.style_id\n" + 
					"                                   and b3.lineid = $lineid$\n" + 
					"                                   and b3.salable_flag = 1)) as salable_grade_real_num2, --畅销款品质实际总在途\n" + 
					"                       (select nvl(sum(nvl(a5.rate, 0)), 0)\n" + 
					"                          from jat_pl_basic_grade a5, jat_pl_gather_set_line b5\n" + 
					"                         where b5.lineid = $lineid$\n" + 
					"                           and a5.item_class_id = b5.item_class_id\n" + 
					"                           and a5.orna_class_id = b5.orna_class_id\n" + 
					"                           and a5.analysis_id = b5.analysis_id\n" + 
					"                           and a5.color_grade_grade_id = $colorGradeGradeId$\n" + 
					"                           and a5.clean_grade_id = $cleanGradeId$) as salable_grade_rate, --畅销款品质标准占比\n" + 
					"                       (select nvl(sum(nvl(a6.stock_num, 0)), 0)\n" + 
					"                          from jat_pl_stock_statistics_grade a6\n" + 
					"                         where a6.org_id = b.org_id) as real_num, --实际总库存\n" + 
					"                       (select nvl(sum(nvl(a6.onway_num, 0)), 0)\n" + 
					"                          from jat_pl_onway_statistics_grade a6\n" + 
					"                         where a6.org_id = b.org_id) as real_num2, --实际总在途\n" + 
					"                       (select nvl(sum(nvl(a7.stock_num, 0)), 0)\n" + 
					"                          from jat_pl_stock_statistics_grade a7\n" + 
					"                         where a7.org_id = b.org_id\n" + 
					"                           and a7.color_grade_id $colorGradeId$\n" + 
					"                           and a7.clean_id $cleanId$) as grade_real_num, --品质实际总库存\n" + 
					"                       (select nvl(sum(nvl(a7.onway_num, 0)), 0)\n" + 
					"                          from jat_pl_onway_statistics_grade a7\n" + 
					"                         where a7.org_id = b.org_id\n" + 
					"                           and a7.color_grade_id $colorGradeId$\n" + 
					"                           and a7.clean_id $cleanId$) as grade_real_num2, --品质实际总在途\n" + 
					"                       (select nvl(sum(nvl(a8.stock_num, 0)), 0)\n" + 
					"                          from jat_pl_stock_statistics_grade a8\n" + 
					"                         where a8.org_id = b.org_id\n" + 
					"                           and a8.style_id = $styleId$) as style_num, --款式总库存\n" + 
					"                       (select nvl(sum(nvl(a8.onway_num, 0)), 0)\n" + 
					"                          from jat_pl_onway_statistics_grade a8\n" + 
					"                         where a8.org_id = b.org_id\n" + 
					"                           and a8.style_id = $styleId$) as style_num2, --款式总在途\n" + 
					"                       a.headid as gheadid,\n" + 
					"                       a.pur_date_end as pur_date_end\n" + 
					"                  from jat_pl_gather_set_head a, jas_pl_region_org b\n" + 
					"                 where a.headid = $headid$\n" + 
					"                   and a.region_id = b.region_id\n" +
                    "                   and (exists (select 1 from jat_pl_basic_distribute_num dm where dm.distribute_num > 0 and dm.org_id = b.org_id \n" +
                    "                           and dm.item_class_id=$itemClassId$\n" +
                    "                           and dm.orna_class_id=$ornaClassId$\n" +
                    "                           and dm.style_item_class_id=$styleItemClassId$\n" +
                    "                           and dm.analysis_id = $analysisId$\n"+
                    "                           and dm.status = 1)\n" +
                    "                       or exists (select 1 from  jat_pl_basic_turnover_num tm where tm.turnover_num > 0 and  tm.org_id = b.ORG_ID\n" +
                    "                           and tm.item_class_id=$itemClassId$\n" +
                    "                           and tm.orna_class_id=$ornaClassId$\n" +
                    "                           and tm.style_item_class_id=$styleItemClassId$\n" +
                "                               and tm.analysis_id = $analysisId$\n"+
                    "                           and tm.start_date <='$date$'\n" +
                    "                           and tm.end_date >='$date$'\n" +
                    "                           and tm.status = 1))\n" +
                    "                 ) aa\n" +
					"          left join (select b8.org_id, a8.min_count, a8.max_count\n" + 
					"                      from jat_pl_display_standand     a8,\n" + 
					"                           jat_pl_display_orgtype_line b8\n" + 
					"                     where a8.headid = b8.headid\n" + 
					"                       and a8.style_id = $styleId$\n" +
                    "                       and exists (select 1 from jat_pl_basic_salable bs \n" +
                    "                               where a8.item_class_id=bs.item_class_id\n" +
                    "                               and a8.orna_class_id = bs.orna_class_id\n" +
                    "                               and a8.style_item_class_id = bs.style_item_class_id\n" +
                    "                               and a8.style_id=bs.style_id\n" +
                    "                               and a8.analysis_id=bs.analysis_id)" +
					"                       and exists\n" + 
					"                     (select 1\n" + 
					"                              from jat_pl_gather_set_line c8\n" + 
					"                             where c8.lineid = $lineid$\n" + 
					"                               and a8.item_class_id = c8.item_class_id\n" + 
					"                               and a8.orna_class_id = c8.orna_class_id\n" + 
					"                               and a8.analysis_id = c8.analysis_id\n" + 
					"                               and a8.style_item_class_id =\n" + 
					"                                   c8.style_item_class_id)) bb\n" + 
					"            on aa.org_id = bb.org_id\n" + 
					"          left join (select a9.gather_head_id,\n" + 
					"                           a9.org_id,\n" + 
					"                           sum(decode(a9.status, 1, 1, 0)) as dispatching_num, --正配数量\n" + 
					"                           sum(decode(a9.status, 1, 0, 1)) as dispatched_num, --已配数量\n" + 
					"                           count(1) as dispatch_num, --总配货数量\n" + 
					"                           sum(decode(b9.salable_flag, 1, 1, 0)) as salable_dispatch_num, --畅销款配货数量\n" + 
					"                           sum(case\n" + 
					"                                 when a9.color_grade_id $colorGradeId$ and\n" + 
					"                                      a9.clean_id $cleanId$ then\n" + 
					"                                  1\n" + 
					"                                 else\n" + 
					"                                  0\n" + 
					"                               end) as grade_dispatch_num, --品质总配货数量\n" + 
					"                           sum(decode(b9.salable_flag,\n" + 
					"                                      case\n" + 
					"                                        when a9.color_grade_id $colorGradeId$ and\n" + 
					"                                             a9.clean_id $cleanId$ then\n" + 
					"                                         1\n" + 
					"                                        else\n" + 
					"                                         0\n" + 
					"                                      end,\n" + 
					"                                      1,\n" + 
					"                                      0)) as salable_grade_dispatch_num, --畅销款品质配货数量\n" + 
					"                           sum(decode(a9.style_id, $styleId$, 1, 0)) as style_dispatch_num --款式配货数量\n" + 
					"                      from jat_pl_dispatch_orna_log a9\n" + 
					"                           left join jat_pl_gather_set_style  b9\n" + 
					"                              on a9.style_id = b9.style_id\n" + 
					"                              and b9.lineid = $lineid$\n" + 
					"                     where a9.src_bill_code = 'PU'\n" + 
					"                       and a9.status in (1, 2, 3) --去除已到店4状态的\n" +
                    "                       and a9.orna_code <> '$ornaCode$' \n" +
					"                     group by a9.gather_head_id, a9.org_id) cc\n" + 
					"            on aa.gheadid = cc.gather_head_id\n" + 
					"           and aa.org_id = cc.org_id\n" + 
					"          left join (select b10.org_id, a10.min_count, a10.max_count\n" + 
					"                      from jat_pl_display_standand2    a10,\n" + 
					"                           jat_pl_display_orgtype_line b10\n" + 
					"                     where a10.headid = b10.headid\n" +
                    "                     and not exists (select 1 from jat_pl_basic_salable bs \n" +
                    "                               where a10.item_class_id=bs.item_class_id\n" +
                    "                               and a10.orna_class_id = bs.orna_class_id\n" +
                    "                               and a10.style_item_class_id = bs.style_item_class_id\n" +
                    "                               and a10.analysis_id=bs.analysis_id)\n"+
					"                       and exists\n" + 
					"                     (select 1\n" + 
					"                              from jat_pl_gather_set_line c10\n" + 
					"                             where c10.lineid = $lineid$\n" + 
					"                               and a10.item_class_id = c10.item_class_id\n" + 
					"                               and a10.orna_class_id = c10.orna_class_id\n" +
                    "                               and a10.style_item_class_id = c10.style_item_class_id\n" +
                    "                               and a10.analysis_id = c10.analysis_id\n"+
                    "             )) dd\n" +
					"            on aa.org_id = dd.org_id\n" +
                    "           where aa.salable_turn_num + aa.salable_dis_num > 0\n" +
                    ") aaa\n";
		if(!"-1".equals(inOrgId)){
			sql += " where aaa.org_id = " + inOrgId + "\n";
		}else{
			sql += " where 1=1";
		}
		sql += " and not exists (select 1 from jat_sys_dict_item bbb where bbb.entry_code = 'hqorgs' and bbb.item_key = aaa.org_id)";
		sql += " order by aaa.match_degee desc, aaa.num_diff desc, aaa.grade_diff desc, aaa.org_id";
		
		if(StringUtil.isNotEmpty(otherInfo.get("colorGradeId"))){
			otherInfo.put("colorGradeId", "= " + otherInfo.get("colorGradeId"));
		}else{
			otherInfo.put("colorGradeId", "is null");
		}
		if(StringUtil.isNotEmpty(otherInfo.get("cleanId"))){
			otherInfo.put("cleanId", "= " + otherInfo.get("cleanId"));
		}else{
			otherInfo.put("cleanId", "is null");
		}
		for(int i=0;i<tmpStr.length;i++){
			sql = sql.replace("$"+tmpStr[i]+"$", otherInfo.get(tmpStr[i]));
		}
		System.out.println("===配货SQL============================");
		System.out.println(sql);
		System.out.println();
		List<PushDispatchTemp> list = pushDispatchDao.getMatchDispatchTemp(sql);
		return list;
	}

    /**
     * 情侣类 配货
     * @param info
     * @param otherInfo
     * @param inOrgId
     * @param conditionId
     * @param isList
     * @return
     */
    private List<PushDispatchTemp> dispatchOrnaByAttribute(DispatchOrnaInfo info, Map<String, String> otherInfo, String inOrgId, String conditionId, boolean isList){
        otherInfo.put("styleId", info.getStyleId());
        otherInfo.put("ornaCode", info.getOrnaCode());
        otherInfo.put("inOrgId", "-1".equals(inOrgId)?"":inOrgId);
        otherInfo.put("isList", isList?"1":"0");
        List<PushDispatchTemp> list = pushDispatchDao.getMatchDispatchTemp(otherInfo);
        return list;
    }
	/**
	 * 保存配货记录
	 * @param dispatchOrnaLog
	 */
	public String saveDispatchOrnaLog(DispatchOrnaLog dispatchOrnaLog){
		dispatchOrnaLog.setSrcBillCode(GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
		String ornaLogId = dispatchOrnaDao.saveDispatchOrnaLog(dispatchOrnaLog);
		//更新匹配减库记录临时表中的配货记录id段
		if(null != dispatchOrnaLog.getSubtempId()){
			dispatchOrnaDao.updateSubtempOrna(dispatchOrnaLog.getSubtempId(), ornaLogId);
		}
		//修改饰品现有量状态为901保留
//		materActiveDao.markMaterActiveUsed(dispatchOrnaLog.getOrnaCode(), GlobalConstant.BILL_CODE_PUSH_GATHER_BILL, ornaLogId);
		materActiveDao.markMaterActiveUsedByPushDistatch(dispatchOrnaLog.getOrnaCode(), GlobalConstant.BILL_CODE_PUSH_GATHER_BILL, dispatchOrnaLog.getGatherHeadId());
		return ornaLogId;
	}
	/**
	 * 获取配货参数下的所有分配店
	 * @param conditionId
	 * @return
	 */
	public List<Org> getDispatchOrg(String conditionId){
		return pushDispatchDao.getDispatchOrg(conditionId);
	}
	/**
	 * 根据配货参数id,获取正配货临时分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchingPageData(Map<String, String> condition){
		return pushDispatchDao.getDispatchingPageData(condition);
	}
	/**
	 * 根据配货参数id,获取已配货分页数据
	 * @param condition
	 * @return
	 */
	public Pager getDispatchedPageData(Map<String, String> condition){
		return pushDispatchDao.getDispatchedPageData(condition);
	}

	/**
	 * 根据总部总单id，获取对应所有的门店
	 * @param gheadid
	 * @return
	 */
	public List<Org> getDispatchedOrg(String gheadid){
		return pushDispatchDao.getDispatchedOrg(gheadid);
	}
	
	/**
	 * 已配货，获取大类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @return
	 */
	public List<ItemClass> getPurchaseGatherItemClass(String gheadid, String orgId){
		return pushDispatchDao.getPurchaseGatherItemClass(gheadid, orgId);
	}
	/**
	 * 已配货，获取小类
	 * @param gheadid 总部总单id
	 * @param orgId 门店id
	 * @param itemClassId 大类id
	 * @return
	 */
	public List<OrnaClass> getPurchaseGatherOrnaClass(String gheadid, String orgId, String itemClassId){
		return pushDispatchDao.getPurchaseGatherOrnaClass(gheadid, orgId, itemClassId);
	}

	/**
	 * 根据总部总单id，获取总部总单行分页数据
	 * @param condition
	 * @return
	 */
	public Pager getHqlinePageData(Map<String, String> condition){
		return pushDispatchDao.getHqlinePageData(condition);
	}
	public Pager getHqlinePageData2(Map<String, String> condition){
		return pushDispatchDao.getHqlinePageData2(condition);
	}
	/**
	 * 总部总单，获取所有大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getHqlineItemClass(String gheadid){
		return pushDispatchDao.getHqlineItemClass(gheadid);
	}
	/**
	 * 总部总单，根据大类获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getHqlineOrnaClass(String gheadid, String itemClassId){
		return pushDispatchDao.getHqlineOrnaClass(gheadid, itemClassId);
	}
//	/**
//	 * 获取正分配饰品信息
//	 * @param condition
//	 * @return
//	 */
//	public Pager getShowDispatchingPageData(Map<String, String> condition){
//		return dispatchOrnaDao.getShowDispatchingPageData(condition);
//	}
//	/**
//	 * 获取已分配饰品信息
//	 * @param condition
//	 * @return
//	 */
//	public Pager getShowDispatchedPageData(Map<String, String> condition){
//		return dispatchOrnaDao.getShowDispatchedPageData(condition);
//	}
	/**
	 * 根据配货记录id，逻辑删除
	 * @param ornaLogId
	 */
	public void deleteDispatchOrnaLog(String ornaLogId, String userid){
		DispatchOrnaLog ornaLog = dispatchOrnaDao.getDispatchOrnaLogById(ornaLogId);
		dispatchOrnaDao.deleteDispatchOrnaLog(ornaLogId, userid);//逻辑删除，将状态改为0
		materActiveDao.markMaterActiveValid(ornaLog.getOrnaCode());//改饰品状态为有效
//		if(DictConstant.BILL_STATUS_MARK.equals(ornaLog.getStatus())){//已配货
			//重新计算总部总单行的已分配数量
//			dispatchOrnaDao.updateGatherLineDispatchNum(ornaLog.getGatherLineId());//根据status=2
			//根据配货记录id更新订单配货日志统计表
//			dispatchOrnaDao.updateDispatchLogStatByOrnaLogId(ornaLogId, userid);
//		}
	}
//	/**
//	 * 获取正配数量
//	 */
//	public int getDispatchingNum(String orderLineId){
//		return dispatchOrnaDao.getDispatchingNum(orderLineId);
//	}
//	/**
//	 * 获取已配数量
//	 */
//	public int getDispatchedNum(String useOrderLineIdFlag, String lineId){
//		return dispatchOrnaDao.getDispatchedNum(useOrderLineIdFlag, lineId);
//	}
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
//		dispatchOrnaDao.deleteDispatchTempData(conditionId);
		//删除配货参数
		dispatchOrnaDao.deleteDispatchCondition(conditionId, userid);
		//根据配货参数id更新订单配货日志统计表
//		updateDispatchLogStateByConditionId(conditionId, userid);
	}
//	/**
//	 * 根据配货参数id更新订单配货日志统计表
//	 * @param conditionId
//	 * @param userid
//	 */
//	private void updateDispatchLogStateByConditionId(String conditionId, String userid) {
//		//删除配货参数id对应的订单配货日志统计表
//		dispatchOrnaDao.deleteDispatchLogStat(conditionId);
//		//重新统计配货参数id对应的订单配货日志统计表
//		dispatchOrnaDao.createDispatchLogStat(conditionId, userid);
//	}
	/**
	 * 正配货变更
	 */
	public void exchangeDispatching(String ornaLogId, String newOrgId, String userid){
		//更新配货饰品记录
		pushDispatchDao.exchangeDispatching(ornaLogId, newOrgId, userid);
	}

	/**
	 * 生成调拨单获取大类
	 * @param gheadid
	 * @return
	 */
	public List<ItemClass> getMoveItemClass(String gheadid){
		return pushDispatchDao.getMoveItemClass(gheadid);
	}
	/**
	 * 生成调拨单获取小类
	 * @param gheadid
	 * @return
	 */
	public List<OrnaClass> getMoveOrnaClass(String gheadid){
		return pushDispatchDao.getMoveOrnaClass(gheadid);
	}
	/**
	 * 获取调拨单分配店
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveOrgForSlt(String gheadid){
		return pushDispatchDao.getMoveOrgForSlt(gheadid);
	}
	/**
	 * 获取调拨单配货员
	 * @param gheadid
	 * @return
	 */
	public List<SelectorOption> getMoveDispatchUserForSlt(String gheadid){
		return pushDispatchDao.getMoveDispatchUserForSlt(gheadid);
	}
	/**
	 * 未生成调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getUnmovebill(String gheadid){
		return pushDispatchDao.getUnmovebill(gheadid);
	}
	/**
	 * 调拨单查询
	 * @param gheadid
	 * @return
	 */
	public List<DispatchMoveBill> getMovebill(String gheadid){
		return pushDispatchDao.getMovebill(gheadid);
	}
//	/**
//	 * 获取真实总成本
//	 * @param gheadid
//	 * @param orgIds
//	 * @param itemClassIds
//	 * @param ornaClassIds
//	 * @return
//	 */
//	public List<DispatchRealCost> getDispatchRealCost(String gheadid, String orgIds, String itemClassIds, String ornaClassIds){
//		return dispatchOrnaDao.getDispatchRealCost(gheadid, orgIds, itemClassIds, ornaClassIds);
//	}
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
		String gatherHeadBillno = pushDispatchDao.getGatherHeadBillno(gheadid);
		List<MoveBillHead> moveBillList = new ArrayList<MoveBillHead>();
		String moveHeadIds = "$";
		for(String inOrgId : orgIdArr){
			List<String> newOrnaCodeList = pushDispatchDao.getOrnaClodeFromMove(gheadid, inOrgId, itemClassIds, ornaClassIds, dispatchUserIds);
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
		pushDispatchDao.updateDispatchOrnaStatusToMoved(gheadid, orgIds, itemClassIds, ornaClassIds, dispatchUserIds, userid);
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
		moveHead.setSrcBillCode(GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);//总部要货汇总单
		moveHead.setSrcBillId(gheadid);//总部要货汇总单id
		moveHead.setSrcBillNo(gatherHeadBillno);//要货汇总单单号
		moveHead.setDodate(DateUtil.getCurrentDate10());
		moveHead.setOutOrgId(outOrgId);
		moveHead.setInOrgId(inOrgId);
		moveHead.setOutStockId(DictConstant.INVCODE_ORNA);//设置为饰品库
		moveHead.setInStockId(DictConstant.INVCODE_ORNA);//设置为饰品库
		moveHead.setMemo("推式配货生成调拨单");
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
	 * 改总部状态为已到店
	 * @param gheadid
	 * @param userid
	 */
	public int updateHqOrnaStatus(String gheadid, String userid){
		int count = materActiveDao.updateHqOrnaStatus(gheadid, userid, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
		purchaseDao.updateHqOrnaStatus(gheadid, userid, GlobalConstant.BILL_CODE_PUSH_GATHER_BILL);
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
//	/**
//	 * 总部总单 匹配率
//	 * @return
//	 */
//	public String getHqlineDispatchRate(String gheadid, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
//		return dispatchOrnaDao.getHqlineDispatchRate(gheadid, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
//	}
//	/**
//	 * 已配货 匹配率
//	 * @return
//	 */
//	public String getDispatchedDispatchRate(String gheadid, String orgId, String itemClassId, String ornaClassId, String showUnfullOnly, String showUnperfectMatch){
//		return dispatchOrnaDao.getDispatchedDispatchRate(gheadid, orgId, itemClassId, ornaClassId, showUnfullOnly, showUnperfectMatch);
//	}
	/**
	 * 获取要货总单状态
	 */
	public String getHeadquarterStatus(String gheadid){
		return pushDispatchDao.getHeadquarterStatus(gheadid);
	}
	/**
	 * 导出总部总单行
	 * @return
	 */
	public List<Map> getHqlineForExcel(Map<String, String> condition){
		return pushDispatchDao.getHqlineForExcel(condition);
	}
	
	/**
	 * 获取统计数据
	 */
	public List<DispatchStatistics> getDispatchStatistics(String gheadid,
			String dispatchingFlag){
		return pushDispatchDao.getDispatchStatistics(gheadid, dispatchingFlag);
	}

    @Override
    public Pager getShowDispatchedPageData(Map<String, String> condition) {
        return pushDispatchDao.getShowDispatchedPageData(condition);
    }
}
