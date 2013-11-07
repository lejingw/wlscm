package com.jatools.web.view.stock;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Pager;
import com.jatools.manager.pur.HandoverManager;
import com.jatools.vo.pur.HandoverChild;
import com.jatools.vo.pur.HandoverHead;
import com.jatools.vo.pur.HandoverLine;
import com.jatools.vo.util.HandoverChildConverter;
import com.jatools.web.form.pur.HandoverForm;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

public abstract class BaseHandoverController extends BaseMultiActionController {

	protected void beforeVeiew(Pager pager){
		if(null != pager){
			if(CollectionUtils.isNotEmpty(pager.getPageData())){
				for(Object obj : pager.getPageData()){
					this.beforeVeiew((HandoverHead)obj);
				}
			}
		}
	}
	
	protected void beforeVeiew(HandoverHead head){
		if(null != head){
			if(StringUtil.isNotBlank(head.getSumCharge())){
				head.setSumCharge(Double.valueOf(head.getSumCharge())+"");
			} else {
				head.setSumCharge("0");
			}
			if(StringUtil.isNotBlank(head.getSumMoney())){
                DecimalFormat df = new DecimalFormat("0.######");
                Double d = new Double(head.getSumMoney());
                head.setSumMoney(df.format(d));
			} else {
				head.setSumMoney("0");
			}
			if(StringUtil.isNotBlank(head.getSumNum())){
				head.setSumNum(Double.valueOf(head.getSumNum())+"");
			} else {
				head.setSumNum("0");
			}
		}
	}
	
	protected void beforeView(List<HandoverLine> lines){
		if(CollectionUtils.isNotEmpty(lines)){
			for(HandoverLine line : lines ){
				if(StringUtil.isNotBlank(line.getHangNum())){
					line.setHangNum(Double.valueOf(line.getHangNum())+"");
				} else {
					line.setHangNum("0");
				}
				if(StringUtil.isNotBlank(line.getHangPrice())){
					line.setHangPrice(Double.valueOf(line.getHangPrice())+"");
				} else {
					line.setHangPrice("0");
				}
				if(StringUtil.isNotBlank(line.getYesNum())){
					line.setYesNum(Double.valueOf(line.getYesNum())+"");
				} else {
					line.setYesNum("0");
				}
				if(StringUtil.isNotBlank(line.getYesMoney())){
					line.setYesMoney(Double.valueOf(line.getYesMoney())+"");
				} else {
					line.setYesMoney("0");
				}
				
				if(StringUtil.isNotBlank(line.getYesMoney())){
					line.setYesMoney(Double.valueOf(line.getYesMoney())+"");
				} else {
					line.setYesMoney("0");
				}
				if(StringUtil.isNotBlank(line.getNoNumLast())){
					line.setNoNumLast(Double.valueOf(line.getNoNumLast())+"");
				} else {
					line.setNoNumLast("0");
				}
				if(StringUtil.isNotBlank(line.getNoNumNow())){
					line.setNoNumNow(Double.valueOf(line.getNoNumNow())+"");
				} else {
					line.setNoNumNow("0");
				}
				
				if(StringUtil.isNotBlank(line.getDiffNum())){
					line.setDiffNum(Double.valueOf(line.getDiffNum())+"");
				} else {
					line.setDiffNum("0");
				}
				if(StringUtil.isNotBlank(line.getDiffMoney())){
					line.setDiffMoney(Double.valueOf(line.getDiffMoney())+"");
				} else {
					line.setDiffMoney("0");
				}
				if(StringUtil.isNotBlank(line.getDiffMoney())){
					line.setDiffMoney(StringUtil.formatDouble(line.getDiffMoney(), 4));
				}
				if(StringUtil.isNotBlank(line.getDiffNum())){
					line.setDiffNum(StringUtil.formatDouble(line.getDiffNum(), 4));
				}
				if(StringUtil.isNotBlank(line.getChildList())){
					List<HandoverChild> childList = HandoverChildConverter.JSONArrayTOHandoverChildList(line.getChildList());
					String children = HandoverChildConverter.handoverChildListTOJSONArray(childList);
					line.setChildList(children);
				}
			}
		}
	}
	
	//[{"childid":"61","lineid":"225","alaysisId":"2448","stoneNum":".3","unitPrice":"3344.99"}]
	
	
	public ModelAndView doSearchLineRec(HandoverManager handoverManager, String vmpath,  Logger logger, HttpServletRequest req, HttpServletResponse res) {
		HandoverForm form = new HandoverForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPage(req, "billid");
			Pager pager = handoverManager.getHandoverLineRecData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(vmpath, "form", form);
	}
	
	public ModelAndView doSearchChildRec(HandoverManager handoverManager, String vmpath,  Logger logger, HttpServletRequest req, HttpServletResponse res) {
		HandoverForm form = new HandoverForm();
		try {
			Map<String, String> condition = CommonUtil.getConditionForPage(req, "lineid");
			Pager pager = handoverManager.getHandoverChildRecData(condition);
			form.setPager(pager);
			form.setCondition(condition);
		} catch (Exception e) {
			logger.error(e);
			form.setSuccessfulFlag(false);
			form.setMessage("获取列表数据出错");
		}
		return new ModelAndView(vmpath, "form", form);
	}
}
