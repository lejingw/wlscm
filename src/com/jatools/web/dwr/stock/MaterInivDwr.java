package com.jatools.web.dwr.stock;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.constant.GlobalConstant;
import com.jatools.manager.calc.PriceHeadManager;
import com.jatools.manager.stock.MaterInivManager;
import com.jatools.manager.util.CommonManager;
import com.jatools.vo.bd.Tag;
import com.jatools.vo.calc.PriceHead;
import com.jatools.vo.stock.MaterIniv;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class MaterInivDwr {
	private Logger logger = Logger.getLogger(MaterInivDwr.class);
	private CommonManager commonManager;
	private MaterInivManager materInivManager;
	private PriceHeadManager priceHeadManager;
	
	public void setPriceHeadManager(PriceHeadManager priceHeadManager) {
		this.priceHeadManager = priceHeadManager;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}
	public void setMaterInivManager(MaterInivManager materInivManager) {
		this.materInivManager = materInivManager;
	}
	
	public String saveOrUpdateMater(MaterIniv head, HttpServletRequest req){
		HttpSession session = req.getSession();
		String id = head.getId();
		if(StringUtil.isEmpty(head.getId())){
			if(head.getNuclearBillId()!=null&&!"".equals(head.getNuclearBillId())){
				PriceHead ph = priceHeadManager.getPriceHead(head.getNuclearBillId());
				if(ph==null){
					return "0_核价单已被删除,保存失败";
				}else if(!"1".equals(ph.getState())){
					return "0_核价单状态已被修改,保存失败";
				}
				head.setOrnaCode(ph.getWlCode());
				head.setOrnaBarcode(ph.getOrnaBarCode());
			}else{
				if("3".equals(head.getAllowInivType())&&"156".equals(head.getItemClassId()))
					head.setOrnaCode(commonManager.getOrnaCodeD0(head.getAvgNum()));//平均分数
				else{
					//自动生成的编码第一位为9
					String inivCode = this.priceHeadManager.getEmpInivCalcCode(CommonUtil.getSessionUserId(req), "1");
					head.setOrnaCode(commonManager.getOrnaCode(inivCode, head.getItemClassId(), head.getOrnaClassId()));
				}
				head.setOrnaBarcode(commonManager.getBarCode(head.getItemClassId(), head.getOrnaClassId()));
				
			}
			head.setInivDate(DateUtil.getCurrentDate10());
			head.setInivOrg(CommonUtil.getSessionOrgId(session));
			head.setCreatedate(DateUtil.getCurrentDate18());
			head.setCreateuserid(CommonUtil.getSessionUserId(req));
			head.setNo(commonManager.getBillno("RK"));
			head.setState(DictConstant.BILL_STATUS_SAVE);
			head.setSrcBillId(head.getHandoverBillId());
			head.setSrcBillCode(GlobalConstant.BILL_CODE_JIAOJIEDAN);
			head.setStockId("1");//饰品库
			id = this.materInivManager.saveMaterIniv(head);
		}else{
			MaterIniv old = this.materInivManager.getMaterInivById(head.getId());
			if(old==null){
				return "0_单据已被删除,请返回列表页面";
			}else if("1".equals(old.getState())){
				head.setUpdatedate(DateUtil.getCurrentDate18());
				if(head.getNuclearBillId()!=null&&!"".equals(head.getNuclearBillId())
						&&!old.getNuclearBillId().equals(head.getNuclearBillId())){
					PriceHead ph = priceHeadManager.getPriceHead(head.getNuclearBillId());
					if(ph==null){
						return "0_核价单已被删除,保存失败";
					}else if(!"1".equals(ph.getState())){
						return "0_核价单状态已被修改,保存失败";
					}
					head.setOrnaCode(ph.getWlCode());
					head.setOrnaBarcode(ph.getOrnaBarCode());
				}
				if("3".equals(head.getAllowInivType())&&"156".equals(head.getItemClassId())&&
						!head.getAvgNum().equals(old.getAvgNum()))
					head.setOrnaCode(commonManager.getOrnaCodeD0(head.getAvgNum()));//平均分数
				this.materInivManager.updateMaterIniv(head);
			}else
				return "0_单据状态已被修改,请刷新页面";
		}
		return "1_操作成功_"+id+"_"+head.getOrnaCode();
	}
	/**
	 * 删除入库单
	 * @param id
	 * @return
	 */
	public String deleteMaterIniv(String id){
		try {
			if(StringUtil.isEmpty(id)){
				return "不能获取入库单ID";
			}
			MaterIniv old = this.materInivManager.getMaterInivById(id);
			if(old==null){
				return "单据已被删除,请刷新页面";
			}else if(!"1".equals(old.getState()))
				return "0_单据状态已被修改,请刷新页面";
			materInivManager.deleteMaterIniv(id);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}

	/**
	 * 取交接单行表中的成本单价
	 * @param handoverBillId
	 * @param itemClassId
	 * @return
	 */
	public String getHangPriceByIniv(String handoverBillId,String itemClassId , HttpServletRequest req){
		if("".equals(handoverBillId)||"".equals(itemClassId))
			return null;
		String price= materInivManager.getHangPriceByIniv(handoverBillId, itemClassId);
		return price;
	}
	
	/**
	 * 标签打印
	 * @param req
	 * @param res
	 * @return
	 */
	public String printLabels(String id,HttpServletRequest req, HttpServletResponse res){
		if(null==id)
			return null;
		String[] ids = id.split(",");
		List<Tag> tagList = materInivManager.printLabels(ids);
		JSONArray array = new JSONArray();
		for(Tag tag : tagList){
			array.add(tag);
		}
		JSONObject obj = new JSONObject();
		obj.put("lines", array.toString());
		return obj.toString();
	}

    public String getFashionGoldPrice(String itemClassId){
        try {
            Double res = this.materInivManager.getFashionGoldPrice(itemClassId);
            if(res != null) {
                return StringUtil.formatDouble(res, 6);
            }
            return null;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
