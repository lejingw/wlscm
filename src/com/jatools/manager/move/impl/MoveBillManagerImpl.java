package com.jatools.manager.move.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jatools.common.Pager;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.dao.bd.BdCommonDao;
import com.jatools.dao.move.MoveBillDao;
import com.jatools.dao.move.MoveCmdDao;
import com.jatools.dao.stock.MaterActiveDao;
import com.jatools.dao.stock.MaterTransDao;
import com.jatools.dao.util.CommonDao;
import com.jatools.manager.BaseManager;
import com.jatools.manager.move.MoveBillManager;
import com.jatools.vo.bd.Style;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.move.MoveBillHead;
import com.jatools.vo.move.MoveBillLine;
import com.jatools.vo.stock.MaterActive;
import com.jatools.web.cache.StyleThemeCache;
import com.jatools.web.util.BdCommon;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.DictUtil;
import com.jatools.ws.remote.WorkflowService;
import com.opensymphony.oscache.util.StringUtil;

public class MoveBillManagerImpl extends BaseManager implements MoveBillManager {
	private MoveBillDao moveBillDao;
	private MaterTransDao materTransDao;
	private MaterActiveDao materActiveDao;
	private CommonDao commonDao;
	private BdCommonDao bdCommonDao;
	private WorkflowService workflowService;
	private MoveCmdDao moveCmdDao;
	
	public void setMoveCmdDao(MoveCmdDao moveCmdDao) {
		this.moveCmdDao = moveCmdDao;
	}
	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	public void setMaterTransDao(MaterTransDao materTransDao) {
		this.materTransDao = materTransDao;
	}
	public void setMaterActiveDao(MaterActiveDao materActiveDao) {
		this.materActiveDao = materActiveDao;
	}
	public void setMoveBillDao(MoveBillDao moveBillDao) {
		this.moveBillDao = moveBillDao;
	}
	public void setBdCommonDao(BdCommonDao bdCommonDao) {
		this.bdCommonDao = bdCommonDao;
	}
	/**
	 * 获取调拨单分页数据
	 * @param condition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Pager getMoveBillPageData(Map<String, String> condition, String orgId, String userid) {
		return moveBillDao.getMoveBillPageData(condition, orgId, userid);
	}
	/**
	 * 获取现有量表信息
	 * 
	 * @param ornaCode
	 * @param ornaFlag
	 * @return
	 */
	public MoveBillLine getMaterActiveInfo(String ornaCode, String orgId) {
		MoveBillLine line = moveBillDao.getMaterActiveInfo(ornaCode);
		if(null == line){
			throw new RuntimeException("不能获取饰品信息");
		}
		if(!orgId.equals(line.getOrgId())){
			throw new RuntimeException("饰品所在网点不为当前选择的调出组织");
		}
		return line;
	}
	/**
	 * 检查饰品状态是否有效
	 * @param ornaCodeList
	 * @return
	 */
	public List<String> checkOrnaStatusAvail(List<String> ornaCodeList) {
		if(null == ornaCodeList || ornaCodeList.size()<1)
			return null;
		return moveBillDao.checkOrnaStatusAvail(ornaCodeList);
	}
	/**
	 * 保存或修改调拨单
	 * @param moveHead
	 * @param newOrnaCodeList 添加的行记录
	 * @param deleteOrnaCodeList 删除的行记录
	 * @return
	 */
	public void saveMoveBill(MoveBillHead moveHead, List<String> newOrnaCodeList, List<String> deleteOrnaCodeList, String userid) {
		try {
			String headid = moveHead.getHeadid();
			if (StringUtil.isEmpty(headid)) {
				moveHead.setBillno(commonDao.getBillno(GlobalConstant.BILL_CODE_DIAOBODAN));
				headid = moveBillDao.saveMoveBillHead(moveHead, userid);
			} else {
				asertStatus("jat_move_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
				moveBillDao.updateMoveBillHead(moveHead, userid);
				moveBillDao.deleteMoveBillLineByOrnaCode(deleteOrnaCodeList, headid, userid);
				//现有量状态改为有效
				for(String ornaCode : deleteOrnaCodeList){
					materActiveDao.markMaterActiveValid(ornaCode);
				}
			}
			//现有量状态改为保留
			for(String ornaCode : newOrnaCodeList){
				materActiveDao.markMaterActiveUsed(ornaCode, moveHead.getInOrgId());
			}
			moveBillDao.saveMoveBillLine(newOrnaCodeList, headid, moveHead.getBillType(), moveHead.getJmFlag(), moveHead.getInOrgId(), userid);
			moveBillDao.updateMoveBillSumNum(headid, userid);
			
//			if(DictConstant.BILL_STATUS_REVIEWING.equals(moveHead.getStatus())){
//				workflowService.enterReview(moveHead, userid);
//			}
		} catch (Exception e) {
			throw new RuntimeException("保存失败");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private void asertMoveCmd(String moveCmdId, String userid){
		try{
			asertStatus("jat_move_cmd_head", "headid", moveCmdId, "status", DictConstant.BILL_STATUS_REVIEWED);
		} catch(Exception e){
			throw new RuntimeException("调拨指令单状态必须为审批完成");
		}
		this.moveCmdDao.updateMoveCmdStatus(moveCmdId, userid, DictConstant.BILL_STATUS_CLOSED_PART);
	}
	
	private String getBillCodeByBillType(String billType, String jmFlag) {
		String billCode = null;
//		if(DictConstant.YES_OR_NO_YES.equals(jmFlag)){
//			if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_DIAOBODAN_JM;
//			else if(DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_TUIHUODAN_JM;
//			else if(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_TUICANDAN_JM;
//			else if(DictConstant.MOVE_BILL_TYPE_YIKUDAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_YIKUDAN_JM;
//			else if(DictConstant.MOVE_BILL_TYPE_GUIZUDIAOBODAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_GUIZUDIAOBODAN_JM;
//			else
//				throw new RuntimeException("单据类别错误");
//		}else{
//			if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_DIAOBODAN;
//			else if(DictConstant.MOVE_BILL_TYPE_TUIHUODAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_TUIHUODAN;
//			else if(DictConstant.MOVE_BILL_TYPE_TUICANDAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_TUICANDAN;
//			else if(DictConstant.MOVE_BILL_TYPE_YIKUDAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_YIKUDAN;
//			else if(DictConstant.MOVE_BILL_TYPE_GUIZUDIAOBODAN.equals(billType))
//				billCode = GlobalConstant.BILL_CODE_GUIZUDIAOBODAN;
//			else
//				throw new RuntimeException("单据类别错误");
//		}
		return billCode;
	}
	/**
	 * 删除调拨单
	 * @param headidList
	 * @return
	 */
	public void deleteMoveBill(List<String> headidList, String userid) {
		if(null != headidList && headidList.size()>0){
			for(String headid : headidList){
				asertStatus("jat_move_head", "headid", headid, "status", DictConstant.BILL_STATUS_SAVE);
				MoveBillHead head = this.moveBillDao.getMoveBillHead(headid);
				if(com.jatools.web.util.StringUtil.isNotBlank(head.getSrcBillId()) && GlobalConstant.BILL_CODE_MOVE_CMD.equals(head.getSrcBillCode())){
					// 调拨指令单生成过来 修改调拨指令单 状态为 审批完成
					try{
//						asertStatus("jat_move_cmd_head", "headid", head.getSrcBillId(), "status", DictConstant.BILL_STATUS_CLOSED);
					} catch(Exception e){
						throw new RuntimeException("调拨指令单状态必须为已引用");
					}
					this.moveCmdDao.updateMoveCmdStatus(head.getSrcBillId(), userid, DictConstant.BILL_STATUS_REVIEWED);
				} else {
					List<MoveBillLine> lineList = moveBillDao.getMoveBillLine(headid, null);
					//现有量状态改为有效
					for(MoveBillLine line : lineList){
						materActiveDao.markMaterActiveValid(line.getOrnaCode());
					}
				}
				moveBillDao.deleteMoveBillLine(headid);
				moveBillDao.deleteMoveBillHead(headid);
			}
		}
	}
	/**
	 * 生成结算单
	 * @param headidList
	 * @return
	 */
	public void generateEstimate(List<String> headidList, String billType, String userid) {
        synchronized (this) {
            if(null != headidList && headidList.size()>0){
                for(String headid : headidList){
//                    asertStatus("jat_move_head", "headid", headid, "status", DictConstant.BILL_STATUS_NO_ESTIMATE);
//                    if(DictConstant.MOVE_BILL_TYPE_DIAOBODAN.equals(billType)){
//                        String result = moveBillDao.generateSaleEstimate(headid, userid);
//                        if(!DictConstant.CALL_PROCEDURE_SUCCESS_FLAG.equals(result)){
//                            throw new RuntimeException("调用加盟系统接口,生成加盟销售结算单失败<br>["+result+"]");
//                        }
//                    }else{
//                        String result = moveBillDao.generateReturnEstimate(headid, billType, userid);
//                        if(!DictConstant.CALL_PROCEDURE_SUCCESS_FLAG.equals(result)){
//                            throw new RuntimeException(result);
//                        }
//                    }

                }
            }
        }
	}
	/**
	 * 撤销调拨单
	 * @param headidList
	 * @param userid
	 */
	public void cancelMoveBill(List<String> headidList, String userid){
		if(null != headidList && headidList.size()>0){
			for(String headid : headidList){
//				asertStatus("jat_move_head", "headid", headid, "status", DictConstant.BILL_STATUS_NO_ESTIMATE);
//				moveBillDao.updateMoveBillStatus(headid, userid, DictConstant.BILL_STATUS_SAVE);
			}
		}
	}
	/**
	 * 获取调拨单行
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getMoveBillLine(String headid, String printLabelType) {
		return moveBillDao.getMoveBillLine(headid, printLabelType);
	}
	/**
	 * 获取调拨单头
	 * @param headid
	 * @return
	 */
	public MoveBillHead getMoveBillHead(String headid){
		return moveBillDao.getMoveBillHead(headid);
	}
	/**
	 * 检查是否配置调拨网络
	 * @param outOrgId
	 * @param inOrgId
	 * @return
	 */
	public boolean checkMoveNetAvail(String outOrgId, String inOrgId){
		Long count = moveBillDao.getMoveNetCount(outOrgId, inOrgId);
		return count > 0;
	}
	/**
	 * 移库单、柜组调拨单接收
	 * @param headid
	 * @param userid
	 */
	public void receiveMoveBill(String headid, String userid){
		MoveBillHead head = moveBillDao.getMoveBillHead(headid);
		moveBillDao.receiveMoveBillHead(headid, userid);//头改为关闭状态，设置接收人、接收时间
		moveBillDao.receiveMoveBillLine(headid, userid);//行改为已接收状态
//		if(DictConstant.MOVE_BILL_TYPE_YIKUDAN.equals(head.getBillType())){
//			String billCode = DictConstant.YES_OR_NO_YES.equals(head.getJmFlag()) ? GlobalConstant.BILL_CODE_YIKUDAN_JM
//					: GlobalConstant.BILL_CODE_YIKUDAN;
//			//写事物处理表，出库
//			materTransDao.insertFromMoveBill(billCode, headid, userid, true);
//			//写事物处理表，入库
//			materTransDao.insertFromMoveBill(billCode, headid, userid, false);
//			//改现有量仓库
//			materActiveDao.updateStockFromMoveBill(head.getInStockId(), headid, userid);
//		}else if(DictConstant.MOVE_BILL_TYPE_GUIZUDIAOBODAN.equals(head.getBillType())){
//			//改现有量柜组
//			materActiveDao.updateGroupsFromMoveBill(head.getInGroup(), headid, userid);
//		}else{
//			throw new RuntimeException("单据类别错误");
//		}
	}
	/**
	 * 获取调拨统计数据
	 * @param headid
	 * @return
	 */
	public List<MoveBillLine> getMoveBillLineStatForPrint(String headid){
		return moveBillDao.getMoveBillLineStatForPrint(headid);
	}
	/**
	 * 打印标签
	 * @param ornaCodeList
	 * @return
	 */
	public List<Tag> getTagInfoForPrintLabels(String headid, String[] ornaCodeList){
		List<Tag> tagList = new ArrayList<Tag>();
		for(String ornaCode : ornaCodeList){
			if(StringUtil.isEmpty(ornaCode))
				continue;
			MaterActive materActive = moveBillDao.getMaterActiveByOrnaCode(headid, ornaCode);
			
			Tag t = new Tag();//打印实体
			t.setLabelType(materActive.getTagType());
			t.setOrnaCode(materActive.getOrnaCode());
			t.setOrnaBarCode(materActive.getOrnaBarcode());
			t.setOrnaName(materActive.getOrnaDsc());
			//获取款式大类编码
			Style style = bdCommonDao.getStyleById(materActive.getStyleId());
			String styleItemCode = bdCommonDao.getStyleItemCodeById(style.getStyleitemclass());
			t.setStyleName(style.getStyleName());
			
			t.setStyleItemCode(styleItemCode);
			if(null!=materActive.getPosAmount()&&!"".equals(materActive.getPosAmount()))
				t.setPosAmount(Double.parseDouble(materActive.getPosAmount()));
			if(null!=materActive.getMainWeight()&&!"".equals(materActive.getMainWeight()))
				t.setMainWeight(Double.parseDouble(materActive.getMainWeight()));
			if(!StringUtil.isEmpty(materActive.getPartWeight()))
				t.setPartStoneWeight(Double.parseDouble(materActive.getPartWeight()));
			if(!StringUtil.isEmpty(materActive.getAllQty()))
				t.setAllQty(Double.parseDouble(materActive.getAllQty()));
			if(!StringUtil.isEmpty(materActive.getSpecialWeight()))
				t.setSpecialWeight(Double.parseDouble(materActive.getSpecialWeight()));
			t.setSizeName(bdCommonDao.getSizeNameById(materActive.getSizeId()));
			t.setQuality(BdCommon.getQualityName(materActive.getQualityId()));
			t.setMainShape(DictUtil.getValue("diashape",materActive.getMainShapeId()));
			t.setPartStoneContent(DictUtil.getValue("secstone",materActive.getPartContent()));
			t.setCleanName(DictUtil.getValue("diaclean",materActive.getCleanId()));
			t.setColor(DictUtil.getValue("diacolor", materActive.getColorId()));
			t.setColorGrade(DictUtil.getValue("diacolorgrade", materActive.getMainColorGradeId()));
			t.setAnalysisArange(bdCommonDao.getAnalysisNameById(materActive.getAlaysisId()));
			
			t.setThemeName(StyleThemeCache.getInstance().getNameById(style.getThemeId()));//主题名称
			
			t.setFactoryStyle(materActive.getFactoryStyleId());
			t.setVenderCode(BdCommon.geVendorSupplierCode(materActive.getSupplierId()));
			
			if(null!=materActive.getStringWorkPrice()&&!"".equals(materActive.getStringWorkPrice()))
				t.setStringWorkPrice(Double.parseDouble(materActive.getStringWorkPrice()));
			
			if(materActive.getItemClassId().equals("146")&&//2012.07.13 修改
					(materActive.getOrnaClassId().equals("59")||materActive.getOrnaClassId().equals("65"))){
				
			}else{
				if(null!=materActive.getSpecialWorkPrice()&&!"".equals(materActive.getSpecialWorkPrice())){	
					t.setSpecialWorkPrice(materActive.getSpecialWorkPrice()+"");
				}else{
					if(null!=materActive.getSpecialWorkPrice()&&!"".equals(materActive.getSpecialWorkPrice())){	
						t.setSpecialWorkPrice(materActive.getSpecialWorkPrice()+"");
					}else{//
						if("117".equals(materActive.getSupplierId())){//供应商为明牌
							t.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_fare_xs"));
						}else{
							t.setSpecialWorkPrice(BdCommon.getParameterValue("verdor_charge_price_xs"));
						}
						
					}
				}
			}
			
			t.setItemClassName(BdCommon.getItemClassDesc(materActive.getItemClassId()));
			t.setOrnaClassName(BdCommon.getOrnaClassDesc(materActive.getOrnaClassId()));
			t.setGrian(materActive.getStoneNowNum());
			t.setIsMutiPart(materActive.getIsMutiPart());
			t.setIdent(materActive.getIdentId());
			t.setHRDCert(materActive.getHrdCert());
			t.setGIACert(materActive.getGiaCert());
			t.setIGICert(materActive.getIgiCert());
			t.setAGSCert(materActive.getAgsCert());
//			t.setLuckDsc(m.getLuckyDsc());//吉祥语标签(只有吉祥语标签需要附值)
			t.setBornImg(materActive.getZodiac());
			tagList.add(t);
		}
		return tagList;
	}
//	public String getBillno(String string){
//		String str = commonDao.getBillno(string);
//		long start = System.currentTimeMillis();
//		int count = 0;
//		for(int i=0;i<100000;i++){
//			for(int j=0;j<10000;j++){
//				count += i + j;
//			}
//		}
//		System.out.print(count + "\t\t\t cost:\t:" + (System.currentTimeMillis() - start) + "\t\t");
//		return str;
//	}
}