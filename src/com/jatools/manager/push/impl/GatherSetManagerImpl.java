package com.jatools.manager.push.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.push.GatherOrderDao;
import com.jatools.dao.push.GatherSetDao;
import com.jatools.dao.push.SalableRateDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.push.GatherSetManager;
import com.jatools.vo.push.GatherOrderItem;
import com.jatools.vo.push.GatherSetGrade;
import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.vo.push.GatherSetSize;
import com.jatools.vo.push.GatherSetStyle;
import com.jatools.vo.push.SalableRate;
import com.jatools.vo.push.StyleWearSizeRate;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.ItemClassCache;
import com.jatools.web.cache.OrnaClassCache;
import com.jatools.web.cache.StyleItemClassCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class GatherSetManagerImpl extends BaseManager implements
		GatherSetManager {
	private CommonDao commonDao;
	private GatherSetDao gatherSetDao;
	private GatherOrderDao gatherOrderDao;
	private SalableRateDao salableRateDao;
//	private DisplayStandardDao displayStandardDao;
//
//	public void setDisplayStandardDao(DisplayStandardDao displayStandardDao) {
//		this.displayStandardDao = displayStandardDao;
//	}

	public void setSalableRateDao(SalableRateDao salableRateDao) {
		this.salableRateDao = salableRateDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setGatherSetDao(GatherSetDao gatherSetDao) {
		this.gatherSetDao = gatherSetDao;
	}

	public void setGatherOrderDao(GatherOrderDao gatherOrderDao) {
		this.gatherOrderDao = gatherOrderDao;
	}

	public CommonDao getCommonDao() {
		return this.commonDao;
	}
	
	public Pager<GatherSetHead> getGatherSetPageData(Map<String, String> condition){
		return gatherSetDao.getGatherSetPageData(condition);
	}
	public GatherSetHead getGatherSetHead(String headid){
		return gatherSetDao.getGatherSetHead(headid);
	}
	/**
	 * 保存或修改头
	 * @param head
	 * @param userid
	 */
	public void saveOrUpdateGatherSetHead(GatherSetHead head, String userid, String orgid){
		head.setUpdateDate(DateUtil.getCurrentDate18());
		head.setUpdateId(userid);
		if(StringUtil.isEmpty(head.getHeadid())){
			head.setBillNo(commonDao.getBillno(GlobalConstant.BILL_CODE_PUSH_GATHER_BILL));
			head.setOrgId(orgid);
			head.setCreateDate(DateUtil.getCurrentDate18());
			head.setCreateId(userid);
			SalableRate salableRate = salableRateDao.getSalableRateByRegionId(head.getRegionId());
			if(null == salableRate){
				throw new RuntimeException("当前区域未设置畅销款比例基础数据");
			}
			head.setSaleDisRate(salableRate.getSaleDisRate());
			head.setSaleTurnRate(salableRate.getSaleTurnRate());
			head.setStatus(DictConstant.BILL_STATUS_SAVE);
			gatherSetDao.saveGatherSetHead(head);
		}else{
			asertStatus("jat_pl_gather_set_head", "headid", head.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
			gatherSetDao.updateGatherSetHead(head);
		}
	}
	public List<GatherSetLine> getGatherSetLineList(String headid){
		return gatherSetDao.getGatherSetLineList(headid);
	}
	/**
	 * 修改类别下单数量
	 * @param lineid
	 * @param newOrderNum
	 */
	public String updateLineOrderNum(String lineid, String newOrderNum, String userid){
		GatherSetLine line = gatherSetDao.getGatherSetLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
		line.setOrderNum(newOrderNum);
		gatherSetDao.updateLineOrderNum(lineid, newOrderNum);

		String realSalableOrderNum = getRealSalableOrderNum(line);
		//重新计算畅销款下单总数
		calcSalableNum(line, realSalableOrderNum);
		try {
			//计算畅销款、非畅销款下单数量
			calcSalableStyleNum(line);
			//计算品质下单数量
			calcSalableGradeNum(line);
			//计算尺寸下单数量
			calcSalableSizeNum(line);
			generateDetail(lineid, userid);
		} catch (Exception e) {
			String salableStyleTotalRate = gatherSetDao.getSalableStyleTotalRate(lineid);
			String unsalableStyleTotalRate = gatherSetDao.getUnsalableStyleTotalRate(lineid);
			if(Double.parseDouble(salableStyleTotalRate)>0 || Double.parseDouble(unsalableStyleTotalRate) > 0){
				return e.getMessage();
			}
		}
		return null;
	}

	/**
	 * 计算畅销款下单数量
	 */
	private String getRealSalableOrderNum(GatherSetLine line) {
		//计算畅销款下单数量=(行总库存+行总在途+行下单量-行铺货量）*80%+行铺货量*30%-行畅销总库存-行畅销总在途
		String realSalableOrderNum = gatherOrderDao.getRealSalableOrderNum(line.getLineid());//获取当前类别下实际需要的畅销款下单数量
		return new Double(realSalableOrderNum).intValue()+"";
	}

	public void saveGatherSetLine(GatherSetLine line, String userid){
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
		if(!gatherSetDao.checkGatherSetLine(line)){
			throw new RuntimeException("此类别已经存在");
		}
		line.setCreateDate(DateUtil.getCurrentDate18());
		line.setCreateId(userid);
		line.setStatus(DictConstant.BILL_STATUS_SAVE);
		String lineid = gatherSetDao.saveGatherSetLine(line);
		//保存品质行
		gatherSetDao.saveGatherSetGradeFromBasic(lineid, userid);
		//保存尺寸行
		gatherSetDao.saveGatherSetSizeFromBasic(lineid, userid);
		
		calcSalableGradeNum(line);
		calcSalableSizeNum(line);
	}

	public void deleteGatherSetLine(String lineid){
		GatherSetLine line = gatherSetDao.getGatherSetLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
		gatherOrderDao.deleteGatherOrderLineBySetLineId(lineid);
		gatherSetDao.deleteGatherSetSizeByLineId(lineid);
		gatherSetDao.deleteGatherSetGradeByLineId(lineid);
		gatherSetDao.deleteGatherSetStyleByLineId(lineid);
		gatherSetDao.deleteGatherSetLine(lineid);
	}
	/**
	 * 获取材质
	 */
	public List<SelectorOption> getQualitySelectOption(String lineid){
		return gatherSetDao.getQualitySelectOption(lineid);
	}

	/**
	 * 获取款式行
	 */
	public List<GatherSetStyle> getGatherSetStyle(String lineid){
		return gatherSetDao.getGatherSetStyle(lineid);
	}
	/**
	 * 获取选择款式分页数据
	 * @param saleable
	 * @param start
	 * @param limit
	 * @return
	 */
	public Pager getSelectStylePageData(boolean saleable, String lineid, String start, String limit){
		return gatherSetDao.getSelectStylePageData(saleable, lineid, start, limit);
	}
	/**
	 * 保存畅销款比例
	 */
	public GatherSetLine saveGatherSetStyle(String lineid, boolean salable, List<GatherSetStyle> addLineList, List<GatherSetStyle> updateLineList, List<String> deleteIdList, String userid){
		GatherSetLine line = gatherSetDao.getGatherSetLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
		if(null != addLineList && addLineList.size()>0){
			for(GatherSetStyle gs:addLineList){
				gs.setSalableFlag(salable?"1":"0");
				gs.setLineid(lineid);
				gs.setCreateDate(DateUtil.getCurrentDate18());
				gs.setCreateId(userid);
				gs.setOrderNum("0");
				gs.setStatus(DictConstant.BILL_STATUS_SAVE);
			}
			gatherSetDao.saveGatherSetStyle(addLineList);
		}
		if(null != updateLineList && updateLineList.size()>0){
			gatherSetDao.updateGatherSetStyle(updateLineList);
		}
		if(null != deleteIdList && deleteIdList.size()>0){
			for(String listId:deleteIdList){
				gatherSetDao.deleteGatherSetStyle(listId);
			}
		}
		String realSalableOrderNum = getRealSalableOrderNum(line);
		//重新计算畅销款下单总数
		calcSalableNum(line, realSalableOrderNum);
		//计算畅销款、非畅销款下单数量
		calcSalableStyleNum(line);
		return line;
	}
	/**
	 * 重新计算畅销款下单总数
	 * @param line
	 * @param realSalableOrderNum
	 */
	private void calcSalableNum(GatherSetLine line, String realSalableOrderNum){
		//如果B≤0，则该类别畅销款不下单；
		//如果B＞0且B＜该类别数量上限（人为规定），则下B的数量；
		//如果B＞该类别数量上限，则按数量上限，全部补充畅销款。
		if(Integer.parseInt(realSalableOrderNum)<=0){
			line.setSalableNum("0");
			line.setUnsalableNum(line.getOrderNum());
		}else if(Integer.parseInt(realSalableOrderNum)<=Integer.parseInt(line.getOrderNum())){
			line.setSalableNum(realSalableOrderNum);
			line.setUnsalableNum(""+(Integer.parseInt(line.getOrderNum()) - Integer.parseInt(realSalableOrderNum)));
		}else{
			line.setSalableNum(line.getOrderNum());
			line.setUnsalableNum("0");
		}
		line.setRealSalableOrderNum(realSalableOrderNum);
		/**
		 * 更新畅销款、非畅销款下单数量
		 */
		gatherSetDao.updateGatherSetLineSalableNum(line);
	}
	/**
	 * 计算畅销款、非畅销款下单数量
	 * @param line
	 * @return
	 */
	private void calcSalableStyleNum(GatherSetLine line){
		List<GatherSetStyle> styleList = gatherSetDao.getGatherSetStyle(line.getLineid());
		List<GatherSetStyle> saleStyleList = new ArrayList<GatherSetStyle>();
		List<GatherSetStyle> unsaleStyleList = new ArrayList<GatherSetStyle>();
		double saleTotalRate = 0D;
		double unsaleTotalRate = 0D;
		for(GatherSetStyle st:styleList){
			if(Double.parseDouble(st.getRate())>0){
				if("1".equals(st.getSalableFlag())){
					saleStyleList.add(st);
					saleTotalRate += Double.parseDouble(st.getRate());
				}else{
					unsaleStyleList.add(st);
					unsaleTotalRate += Double.parseDouble(st.getRate());
				}
			}
		}
		//畅销款全部分配
		if(saleTotalRate > 99.999999 && StringUtil.isNotEmpty(line.getSalableNum())){
			calcStyleOrderNum(saleStyleList, line.getSalableNum());
			gatherSetDao.updateGatherSetStyleOrderNum(saleStyleList);
		}
		//非畅销款全部分配
		if(unsaleTotalRate > 99.999999 && StringUtil.isNotEmpty(line.getUnsalableNum())){
			calcStyleOrderNum(unsaleStyleList, line.getUnsalableNum());
			gatherSetDao.updateGatherSetStyleOrderNum(unsaleStyleList);
		}
	}
	/**
	 * 获取比例非0的款式的总库存
	 * @param saleStyleList
	 */
	private int getStyleStockNum(List<GatherSetStyle> saleStyleList){
		int totalStockNum = 0;
		for(GatherSetStyle style : saleStyleList){
			if(Double.parseDouble(style.getRate())<=0)
				continue;
			totalStockNum += Integer.parseInt(style.getStockNum());
		}
		return totalStockNum;
	}
	private void calcStyleOrderNum(List<GatherSetStyle> saleStyleList, String salableNum) {
		int totalStockNum = getStyleStockNum(saleStyleList);
		int middleIndex = saleStyleList.size()/2;
		int leftNum = Integer.parseInt(salableNum);//剩余分解数量
		int totalNum = Integer.parseInt(salableNum) + totalStockNum;
		//规则((非)畅销款下单总量+比例非0的款式的总库存)*该款式所占的比例-该款式的库存
		for(int i=0,j=saleStyleList.size();i<j;i++){
			GatherSetStyle st = saleStyleList.get(i);
			if(i<middleIndex){
				int orderNum = calc(Double.parseDouble(st.getRate()), totalNum, true)-Integer.parseInt(st.getStockNum());
				st.setOrderNum(""+orderNum);
				leftNum -= orderNum;
			}else if(i == (j-1)){
				st.setOrderNum(""+leftNum);
			}else{
				int orderNum = calc(Double.parseDouble(st.getRate()), totalNum, false)-Integer.parseInt(st.getStockNum());
				st.setOrderNum(""+orderNum);
				leftNum -= orderNum;
			}
		}
		int leftOrderNum = 0;//下单数量为负数的总和
		for(int i=0;i<saleStyleList.size();i++){
			GatherSetStyle st = saleStyleList.get(i);
			if(Integer.parseInt(st.getOrderNum())<0){
				leftOrderNum += -1*Integer.parseInt(st.getOrderNum());
				st.setOrderNum("0");
			}
		}
		int i = 0;
		int j = saleStyleList.size() - 1;
		while (i < leftOrderNum) {
			GatherSetStyle st = saleStyleList.get(j);
			j--;
			if (Integer.parseInt(st.getOrderNum()) > 0) {
				st.setOrderNum("" + (Integer.parseInt(st.getOrderNum()) - 1));
				i++;
			}
			if (j < 0) {
				j = saleStyleList.size() - 1;
			}
		}
	}
	private int calc(double rate, int totalNum, boolean forwardFlag){
		double tmp = rate * totalNum / 10;
		int tail = new Double(tmp).intValue()%10;
		if (tail == 0) {
			return new Double(tmp / 10).intValue();
		} else {
			if (forwardFlag) {
				return new Double(tmp / 10).intValue() + 1;
			} else {
				return new Double(tmp / 10).intValue();
			}
		}
	}

	public List<GatherSetGrade> getGatherSetGrade(String lineid){
		return gatherSetDao.getGatherSetGrade(lineid);
	}

	public List<GatherSetSize> getGatherSetSize(String lineid){
		return gatherSetDao.getGatherSetSize(lineid);
	}
	/**
	 * 保存品质比例
	 */
	public void saveGatherSetGrade(String lineid, List<GatherSetGrade> addLineList, List<GatherSetGrade> updateLineList, List<String> deleteIdList, String userid){
		GatherSetLine line = gatherSetDao.getGatherSetLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
		if(null != addLineList && addLineList.size()>0){
			for(GatherSetGrade gs:addLineList){
				gs.setLineid(lineid);
				gs.setCreateDate(DateUtil.getCurrentDate18());
				gs.setCreateId(userid);
				gs.setStatus(DictConstant.BILL_STATUS_SAVE);
				gatherSetDao.saveGatherSetGrade(gs);
			}
		}
		if(null != updateLineList && updateLineList.size()>0){
			for(GatherSetGrade gs:updateLineList){
				gatherSetDao.updateGatherSetGrade(gs);
			}
		}
		if(null != deleteIdList && deleteIdList.size()>0){
			for(String listId:deleteIdList){
				gatherSetDao.deleteGatherSetGrade(listId);
			}
		}
		//计算品质下单数量
		calcSalableGradeNum(line);
	}

	private void calcSalableGradeNum(GatherSetLine line) {
		List<GatherSetGrade> gradeList = gatherSetDao.getGatherSetGrade(line.getLineid());
		double sizeTotalRate = 0D;
		for(GatherSetGrade st:gradeList){
			sizeTotalRate += Double.parseDouble(st.getRate());
		}
		//畅销款全部分配
		if(sizeTotalRate > 99.999999){
			calcGradeOrderNum(gradeList, line.getOrderNum(), line.getLineid());
		}else{
			for(GatherSetGrade sy:gradeList){
				sy.setOrderNum("0");
			}
		}
		gatherSetDao.updateGatherSetGradeOrderNum(gradeList);
	}
	private void calcGradeOrderNum(List<GatherSetGrade> saleStyleList, String orderNum, String lineid) {
		int leftNum = Integer.parseInt(orderNum);
		//（总库存+总在途+总下单）*rate-该品质总在途-改品质总在途
		List<Map<String, String>> list = gatherSetDao.getGradeStockTotalNum(lineid);
		for(int i=0,j=saleStyleList.size();i<j;i++){
			GatherSetGrade st = saleStyleList.get(i);
			if(i == (j-1)){
				st.setOrderNum(""+leftNum);
			}else{
				int num = setGradeOrderNum(list, st, i, saleStyleList.size());
				leftNum -= num;
			}
		}
		int leftOrderNum = 0;//下单数量为负数的总和
		for(int i=0;i<saleStyleList.size();i++){
			GatherSetGrade st = saleStyleList.get(i);
			if(Integer.parseInt(st.getOrderNum())<0){
				leftOrderNum += -1*Integer.parseInt(st.getOrderNum());
				st.setOrderNum("0");
			}
		}
		int i = 0;
		int j = saleStyleList.size() - 1;
		while (i < leftOrderNum) {
			GatherSetGrade st = saleStyleList.get(j);
			j--;
			if (Integer.parseInt(st.getOrderNum()) > 0) {
				st.setOrderNum("" + (Integer.parseInt(st.getOrderNum()) - 1));
				i++;
			}
			if (j < 0) {
				j = saleStyleList.size() - 1;
			}
		}
	}
	private int setGradeOrderNum(List<Map<String, String>> list, GatherSetGrade st, int index, int count){
		int middle = count/2;
		int stockNum = 0;
		int num = 0;
		for(Map<String, String> map : list){
			if(st.getListid().equals(map.get("listid"))){
				if(index<middle){
					num = new Double(map.get("orderNum")).intValue();
				}else{					
					num = new Double(map.get("orderNum")).intValue() + 1;
				}
				break;
			}
		}
		st.setOrderNum(""+num);
		st.setStockNum(""+stockNum);
		return num;
	}
	/**
	 * 保存尺寸比例
	 */
	public void saveGatherSetSize(String lineid, List<GatherSetSize> addLineList, List<GatherSetSize> updateLineList, List<String> deleteIdList, String userid){
		GatherSetLine line = gatherSetDao.getGatherSetLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
		if(null != addLineList && addLineList.size()>0){
			for(GatherSetSize gs:addLineList){
				gs.setLineid(lineid);
				gs.setCreateDate(DateUtil.getCurrentDate18());
				gs.setCreateId(userid);
				gs.setStatus(DictConstant.BILL_STATUS_SAVE);
				gatherSetDao.saveGatherSetSize(gs);
			}
		}
		if(null != updateLineList && updateLineList.size()>0){
			for(GatherSetSize gs:updateLineList){
				gatherSetDao.updateGatherSetSize(gs);
			}
		}
		if(null != deleteIdList && deleteIdList.size()>0){
			for(String listId:deleteIdList){
				gatherSetDao.deleteGatherSetSize(listId);
			}
		}
		//计算尺寸下单数量
		calcSalableSizeNum(line);
	}

	private void calcSalableSizeNum(GatherSetLine line) {
		List<GatherSetSize> sizeList = gatherSetDao.getGatherSetSize(line.getLineid());
		if(null != sizeList && sizeList.size()>0){
			double sizeTotalRate = 0D;
			for(GatherSetSize st:sizeList){
				sizeTotalRate += Double.parseDouble(st.getRate());
			}
			//畅销款全部分配
			if(sizeTotalRate > 99.999999){
				calcSizeOrderNum(sizeList, line.getOrderNum(), line.getLineid());
			}else{
				for(GatherSetSize sy:sizeList){
					sy.setOrderNum("0");
				}
			}
			gatherSetDao.updateGatherSetSizeOrderNum(sizeList);
		}
	}
	private void calcSizeOrderNum(List<GatherSetSize> saleStyleList, String orderNum, String lineid) {
		int leftNum = Integer.parseInt(orderNum);
		//（总库存+总在途+总下单）*rate-该品质总在途-改品质总在途
		List<Map<String, String>> list = gatherSetDao.getSizeStockTotalNum(lineid);
		for(int i=0,j=saleStyleList.size();i<j;i++){
			GatherSetSize st = saleStyleList.get(i);
			if(i == (j-1)){
				st.setOrderNum(""+leftNum);
			}else{
				int num = setSizeOrderNum(list, st, i, saleStyleList.size());
				leftNum -= num;
			}
		}
		int leftOrderNum = 0;//下单数量为负数的总和
		for(int i=0;i<saleStyleList.size();i++){
			GatherSetSize st = saleStyleList.get(i);
			if(Integer.parseInt(st.getOrderNum())<0){
				leftOrderNum += -1*Integer.parseInt(st.getOrderNum());
				st.setOrderNum("0");
			}
		}
		int i = 0;
		int j = saleStyleList.size() - 1;
		while (i < leftOrderNum) {
			GatherSetSize st = saleStyleList.get(j);
			j--;
			if (Integer.parseInt(st.getOrderNum()) > 0) {
				st.setOrderNum("" + (Integer.parseInt(st.getOrderNum()) - 1));
				i++;
			}
			if (j < 0) {
				j = saleStyleList.size() - 1;
			}
		}
	}
	private int setSizeOrderNum(List<Map<String, String>> list, GatherSetSize st, int index, int count){
		int middle = count/2;
		int stockNum = 0;
		int num = 0;
		for(Map<String, String> map : list){
			if(st.getListid().equals(map.get("listid"))){
				if(index<middle){
					num = new Double(map.get("orderNum")).intValue();
				}else{					
					num = new Double(map.get("orderNum")).intValue() + 1;
				}
				break;
			}
		}
		st.setOrderNum(""+num);
		st.setStockNum(""+stockNum);
		return num;
	}
	/**
	 * 选择尺寸
	 */
	public List<GatherSetGrade> getSelectGradeData(String lineid){
		return gatherSetDao.getSelectGradeData(lineid);
	}
	/**
	 * 选择尺寸
	 */
	public List<GatherSetSize> getSelectSizeData(String lineid){
		return gatherSetDao.getSelectSizeData(lineid);
	}

	public void checkGenerateDetailStyle(String lineid){
		GatherSetLine line = gatherSetDao.getGatherSetLine(lineid);
		if(Double.parseDouble(line.getSalableNum())>0){
			String salableStyleTotalRate = gatherSetDao.getSalableStyleTotalRate(lineid);
			if(Double.parseDouble(salableStyleTotalRate)<99.999999){
				throw new RuntimeException("畅销款比例之和必须等于100%");
			}
		}
		if(Double.parseDouble(line.getUnsalableNum())>0){
			String salableStyleTotalRate = gatherSetDao.getUnsalableStyleTotalRate(lineid);
			if(Double.parseDouble(salableStyleTotalRate)<99.999999){
				throw new RuntimeException("非畅销款比例之和必须等于100%");
			}
		}
	}

	public void checkGenerateDetailGrade(String lineid){
		String salableStyleTotalRate = gatherSetDao.getGradeTotalRate(lineid);
		if(Double.parseDouble(salableStyleTotalRate)<99.999999){
			throw new RuntimeException("品质比例之和必须等于100%");
		}
	}

	public void checkGenerateDetailSize(String lineid){
		List<GatherSetSize> sizeList = gatherSetDao.getGatherSetSize(lineid);
		if(null != sizeList && sizeList.size()>0){
			String salableStyleTotalRate = gatherSetDao.getSizeTotalRate(lineid);
			if(Double.parseDouble(salableStyleTotalRate)<99.999999){
				throw new RuntimeException("尺寸比例之和必须等于100%");
			}
		}
	}
	/**
	 * 生成总单
	 */
	public void createGatherOrder(String headid, String userid) {
		asertStatus("jat_pl_gather_set_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
        String isLoveStyle = getBillStatus("jat_pl_gather_set_head", "headid", headid, "is_love_style");
        if(isLoveStyle.equals("1")){ // 情侣类生成总单
            gatherOrderDao.deleteGatherOrderLineByHeadid(headid);
            gatherOrderDao.createOrderLineByHeadid(headid, userid);
        } else {
            List<GatherSetLine> lineList = gatherSetDao.getGatherSetLineList(headid);
            for (GatherSetLine line : lineList) {
                generateDetail(line.getLineid(), userid);
            }
        }
        gatherSetDao.updateGatherSetHeadStatus(headid, DictConstant.BILL_STATUS_CREATE_GATHER_ORDER);
//		//生成畅销款陈列标准
//		displayStandardDao.createDisplayStandard(headid, userid);
	}
	private String appendMsg(GatherSetLine line, String tmp, String msg){
		if(null == tmp){
			tmp = ItemClassCache.getInstance().getNameById(
					line.getItemClassId())
					+ "、"
					+ OrnaClassCache.getInstance().getNameById(
							line.getOrnaClassId())
					+ "、"
					+ line.getAnalysisName()
					+ "、"
					+ line.getStyleItemClassName() + " 行";
		}
		tmp += "<br>" + msg;
		return tmp;
	}
	/**
	 * 生成类别下的明细
	 * @param lineid
	 */
	public void generateDetail(String lineid, String userid){
		GatherSetLine line = gatherSetDao.getGatherSetLine(lineid);
		asertStatus("jat_pl_gather_set_head", "headid", line.getHeadid(), "status", DictConstant.BILL_STATUS_SAVE);
		String msg = null;
		try {checkGenerateDetailStyle(lineid);}	catch (Exception e) {msg = appendMsg(line, msg, e.getMessage());}
		try {checkGenerateDetailGrade(lineid);}	catch (Exception e) {msg = appendMsg(line, msg, e.getMessage());}
		try {checkGenerateDetailSize(lineid);}	catch (Exception e) {msg = appendMsg(line, msg, e.getMessage());}
		if(null != msg){
			throw new RuntimeException(msg);
		}
		
		gatherOrderDao.deleteGatherOrderLineBySetLineId(lineid);
		List<GatherSetStyle> 	styleList	= gatherSetDao.getGatherSetStyle(line.getLineid());
		List<GatherSetGrade> 	gradeList	= gatherSetDao.getGatherSetGrade(line.getLineid());
		List<GatherSetSize> 	sizeList	= gatherSetDao.getGatherSetSize(line.getLineid());
		Map<String, GatherOrderItem> salableItemMap	= dispatchGatherOrderLine(line, styleList, gradeList, sizeList);
		saveDispatchGatherOrderLine(salableItemMap, userid);
	}

	private void saveDispatchGatherOrderLine(Map<String, GatherOrderItem> itemMap, String userid) {
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		for (String key : itemMap.keySet()) {
			GatherOrderItem item = itemMap.get(key);
			if (item.isWearSizeFlag()) {
				for (StyleWearSizeRate sw : item.getWearSizeList()) {
					Map<String, String> map = createGatherOrderLineData(item.getStyleLineId(), item.getGradeLineId(), true, null, sw.getSizeId(), sw.getOrderNum(), userid);
					mapList.add(map);
				}
			} else {
				Map<String, String> map = createGatherOrderLineData(item.getStyleLineId(), item.getGradeLineId(), false, item.getSizeLineId(), null, item.getOrderNum(), userid);
				mapList.add(map);
			}
		}
		gatherOrderDao.saveGatherOrderLineByItem(mapList);
	}

	private Map<String, String> createGatherOrderLineData(String styleLineId, String gradeLineId, boolean wearSizeFlag, String sizeLineId, String sizeId, int orderNum, String userid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("styleLineId", styleLineId);
		map.put("gradeLineId", gradeLineId);
		map.put("wearSizeFlag", wearSizeFlag ? "1" : "0");
		map.put("sizeLineId", sizeLineId);
		map.put("sizeId", sizeId);
		map.put("orderNum", orderNum + "");
		map.put("userid", userid);
		map.put("date", DateUtil.getCurrentDate18());
		return map;
	}
	
	private Map<String, GatherOrderItem> dispatchGatherOrderLine(GatherSetLine line, List<GatherSetStyle> styleList, List<GatherSetGrade> gradeList, List<GatherSetSize> sizeList) {
		Map<String, GatherOrderItem> itemMap = new HashMap<String, GatherOrderItem>();
		int totalOrderNum	= Integer.parseInt(line.getOrderNum());
		int leftOrderNum	= totalOrderNum;
		//如果配置了尺寸比例
		if(sizeList.size()>0){
			for(GatherSetStyle style : styleList){
				for(GatherSetGrade grade : gradeList){
					for(GatherSetSize size : sizeList){
						int orderNum = calc(Double.parseDouble(style.getRealRate()) * Double.parseDouble(grade.getRealRate()) * Double.parseDouble(size.getRealRate()) / 10000, totalOrderNum, false);
						leftOrderNum -= orderNum;
						String key = getKey(style.getListid(), grade.getListid(), size.getListid());
						itemMap.put(key, new GatherOrderItem(style.getListid(), grade.getListid(), false, size.getListid(), orderNum));
					}
				}
			}
			for(int i=0;i<leftOrderNum;i++){
				String styleListid = getRightStyleListid(itemMap, styleList);
				String gradeListid = getRightGradeListid(itemMap, gradeList);
				String sizeListid = getRightSizeListid(itemMap, sizeList);
				String key = getKey(styleListid, gradeListid, sizeListid);
				GatherOrderItem item = itemMap.get(key);
				if(null != item){
					item.addOrderNum();
				}
			}
		}else{
			//检查没有配置佩戴对象尺寸比例、或佩戴对象尺寸比例配置没有满足100%款式
			List<String> styleNameList = gatherSetDao.getUnSetStyleWearSizeRate(line.getLineid());
			if(styleNameList.size()>0){
				throw new RuntimeException("以下款式没有配置佩戴对象尺寸比例、或佩戴对象尺寸比例配置没有满足100%:<br>" + styleNameList);
			}
			//计算款式、品质应下单数量
			for(GatherSetStyle style : styleList){
				for(GatherSetGrade grade : gradeList){
					int orderNum = calc(Double.parseDouble(style.getRealRate()) * Double.parseDouble(grade.getRealRate()) / 100, totalOrderNum, false);
					leftOrderNum -= orderNum;
					String key = getKey(style.getListid(), grade.getListid(), null);
					itemMap.put(key, new GatherOrderItem(style.getListid(), grade.getListid(), true, null, orderNum));
				}
			}
			//调整款式、品质应下单数量（与对未除尽的情况）
			for(int i=0;i<leftOrderNum;i++){
				String styleListid = getRightStyleListid(itemMap, styleList);
				String gradeListid = getRightGradeListid(itemMap, gradeList);
				String key = getKey(styleListid, gradeListid, null);
				GatherOrderItem item = itemMap.get(key);
				if(null != item){
					item.addOrderNum();
				}
			}
			List<StyleWearSizeRate> styleWearSizeRateList = gatherSetDao.getStyleWearSizeRate(line.getLineid());
			for(String key : itemMap.keySet()){
				GatherOrderItem item = itemMap.get(key);
				List<StyleWearSizeRate> wearSizeList = getWearSizeRateList(styleWearSizeRateList, item.getStyleLineId());
				calcStyleWearSizeOrderNum(wearSizeList, item.getOrderNum());
				item.setWearSizeList(wearSizeList);
			}
		}

        if(sizeList.size() >0 && "161".equals(line.getStyleItemClassId())) {
            for(GatherSetStyle style : styleList){
                List<String> sizeIds = gatherSetDao.getSizeByStyleId(style.getStyleId());
                for(GatherSetGrade grade : gradeList){
                    Integer sum = 0;
                    for(GatherSetSize size : sizeList){
                        String key = getKey(style.getListid(), grade.getListid(), size.getListid());
                        if(!sizeIds.contains(size.getSizeId())) {
                            sum += itemMap.get(key).getOrderNum();
                            itemMap.remove(key);
                        }
                    }
                    while(sum > 0) {
                        for(GatherSetSize size : sizeList){
                            String key = getKey(style.getListid(), grade.getListid(), size.getListid());
                            if(itemMap.containsKey(key) && sum > 0) {
                                GatherOrderItem item = itemMap.get(key);
                                item.addOrderNum();
                                sum--;
                            }
                        }
                    }
                }
            }
        }

		Map<String, GatherOrderItem> map = new HashMap<String, GatherOrderItem>();
		Iterator<String> iter = itemMap.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			GatherOrderItem item = itemMap.get(key);
			if(item.getOrderNum()>0){
				map.put(key, item);
			}
		}
		return map;
	}
	private String getRightStyleListid(Map<String, GatherOrderItem> itemMap, List<GatherSetStyle> styleList){
		for(GatherSetStyle style : styleList){
			int orderNum = Integer.parseInt(style.getOrderNum());
			int sum = 0;
			Iterator<String> iter = itemMap.keySet().iterator();
			while(iter.hasNext()){
				GatherOrderItem item = itemMap.get(iter.next());
				if(item.getStyleLineId().equals(style.getListid())){
					sum += item.getOrderNum();
				}
			}
			if(orderNum>sum){
				return style.getListid();
			}
		}
		return null;
	}
	private String getRightGradeListid(Map<String, GatherOrderItem> itemMap, List<GatherSetGrade> gradeList){
		for(GatherSetGrade grade : gradeList){
			int orderNum = Integer.parseInt(grade.getOrderNum());
			int sum = 0;
			Iterator<String> iter = itemMap.keySet().iterator();
			while(iter.hasNext()){
				GatherOrderItem item = itemMap.get(iter.next());
				if(item.getGradeLineId().equals(grade.getListid())){
					sum += item.getOrderNum();
				}
			}
			if(orderNum>sum){
				return grade.getListid();
			}
		}
		return null;
	}
	private String getRightSizeListid(Map<String, GatherOrderItem> itemMap, List<GatherSetSize> sizeList){
		for(GatherSetSize size : sizeList){
			int orderNum = Integer.parseInt(size.getOrderNum());
			int sum = 0;
			Iterator<String> iter = itemMap.keySet().iterator();
			while(iter.hasNext()){
				GatherOrderItem item = itemMap.get(iter.next());
				if(item.getSizeLineId().equals(size.getListid())){
					sum += item.getOrderNum();
				}
			}
			if(orderNum>sum){
				return size.getListid();
			}
		}
		return null;
	}

	private List<StyleWearSizeRate> getWearSizeRateList(List<StyleWearSizeRate> styleWearSizeRateList, String styleLineId){
		List<StyleWearSizeRate> wearSizeRateList = new ArrayList<StyleWearSizeRate>();
		for(StyleWearSizeRate sw : styleWearSizeRateList){
			if(styleLineId.equals(sw.getStyleLineId())){
				wearSizeRateList.add(sw);
			}
		}
		return wearSizeRateList;
	}
	

	private void calcStyleWearSizeOrderNum(List<StyleWearSizeRate> saleStyleList, int totalNum) {
		int middle = saleStyleList.size()/2;
		int leftNum = totalNum;
		for(int i=0,j=saleStyleList.size();i<j;i++){
			StyleWearSizeRate st = saleStyleList.get(i);
			if(i<middle){
				int orderNum = calc(Double.parseDouble(st.getRate()), totalNum, true);
				st.setOrderNum(orderNum);
				leftNum -= orderNum;
			}else if(i == (j-1)){
				st.setOrderNum(leftNum);
			}else{
				int orderNum = calc(Double.parseDouble(st.getRate()), totalNum, false);
				st.setOrderNum(orderNum);
				leftNum -= orderNum;
			}
		}
	}
	
	private String getKey(String styleLineId, String gradeLineId, String sizeLineId){
		return styleLineId+"$"+gradeLineId+"$"+sizeLineId;
	}
	/**
	 * 修改畅销款铺货比例
	 * a)	删除所有类别中已生成的明细下单数据
	 * b)	清除所有类别中畅销款、非畅销款的下单数量
	 * c)	若此类别下的畅销款配置比例之和为100%，则重新计算畅销款、非畅销款的各个明细款式下单数量
	 * d)	若此类别下已生成明细，则重新生成
	 */
	public String updateSalableRate(String headid, String saleDisRate, String saleTurnRate, String userid){
		asertStatus("jat_pl_gather_set_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
		GatherSetHead head = gatherSetDao.getGatherSetHead(headid);
		head.setSaleDisRate(saleDisRate);
		head.setSaleTurnRate(saleTurnRate);
		gatherSetDao.updateGatherSetHead(head);
		
		List<GatherSetLine> lineList = gatherSetDao.getGatherSetLineList(headid);
		String msg = null;
		for(GatherSetLine line : lineList){
			String lineid = line.getLineid();
			
			String realSalableOrderNum = getRealSalableOrderNum(line);
			//重新计算畅销款下单总数
			calcSalableNum(line, realSalableOrderNum);
			//计算畅销款、非畅销款下单数量
			calcSalableStyleNum(line);
			//计算品质下单数量
			calcSalableGradeNum(line);
			//计算尺寸下单数量
			calcSalableSizeNum(line);
			try {
				generateDetail(lineid, userid);
			} catch (Exception e) {
				if(null == msg){
					msg = e.getMessage();
				}else{
					msg += "<br>" + e.getMessage();
				}
			}
		}
		return msg;
	}
	public void deleteGatherSet(String headid, String userid){
		asertStatus("jat_pl_gather_set_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
		gatherOrderDao.deleteGatherOrderLineByHeadid(headid);//删除明细行
		gatherSetDao.deleteGatherSetStyleByHeadId(headid);//删除款式行
		gatherSetDao.deleteGatherSetSizeByHeadId(headid);//删除尺寸行
		gatherSetDao.deleteGatherSetGradeByHeadId(headid);//删除品质行
		gatherSetDao.deleteGatherSetLineByHeadId(headid);//删除类别行
		gatherSetDao.deleteGatherSetHead(headid);//删除头
	}
	public List<SelectorOption> getItemClassForSlt(String headid){
		return gatherSetDao.getItemClassForSlt(headid);
	}
	public GatherSetLine getGatherSetLine(String lineid){
		return gatherSetDao.getGatherSetLine(lineid);
	}
}
