package com.jatools.web.dwr.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.manager.calc.SbraTypeManager;
import com.jatools.vo.bd.Quality;
import com.jatools.vo.calc.SbraTypeHead;
import com.jatools.vo.calc.SbraTypeLine;
import com.jatools.vo.util.SelectorOption;
import com.jatools.web.cache.QualityCache;
import com.jatools.web.util.DateUtil;
import com.jatools.web.util.StringUtil;

public class SbraTypeDwr {
	private Logger logger = Logger.getLogger(SbraTypeDwr.class);
	private SbraTypeManager sbraTypeManager;
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public void setSbraTypeManager(SbraTypeManager sbraTypeManager) {
		this.sbraTypeManager = sbraTypeManager;
	}
	
	/**
	 * 保存或修改数据
	 * @param head
	 * @param lineList
	 */
	public String saveOrUpdateSbraType(SbraTypeHead head, List<SbraTypeLine> lineList, HttpServletRequest req){
		try {
			if(StringUtil.isEmpty(head.getId())){
				head.setCreateDate(DateUtil.getCurrentDate18());
				head.setCreateUserId(CommonUtil.getSessionUserId(req));
			}else{
				SbraTypeHead old = sbraTypeManager.getSbraTypeHead(head.getId());
				head.setUpdateDate(DateUtil.getCurrentDate18());
				head.setUpdateUserId(CommonUtil.getSessionUserId(req));
				head.setCreateDate(old.getCreateDate());
				head.setCreateUserId(old.getCreateUserId());
			}
			sbraTypeManager.saveOrUpdateSbraType(lineList,head);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "保存出错";
		}
	}
	/**
	 * 删除配件类型
	 * @param headid
	 * @return
	 */
	public String deleteSbraType(String id){
		try {
			if(StringUtil.isEmpty(id)){
				return "不能获取配件类型id";
			}
			sbraTypeManager.deleteSbraTypeHead(id);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return "删除出错";
		}
	}
	
	/**
	 * 获取所有未封存的配件类型
	 * @return
	 */
	public List<SelectorOption> getAllQualityForSlt(){
		Map<String, String> qualityMap = QualityCache.getInstance().getAllQuality();
		if(null == qualityMap || qualityMap.size()<1)
			return null;
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		for(String key:qualityMap.keySet()){
			sltList.add(new SelectorOption(key, qualityMap.get(key)));
		}
		return sltList;
	}
	/**
	 * 全部托架类型数据
	 * @param index 1为工费，2为全部托架类型
	 * @return
	 */
	public List<SelectorOption> getSbraTypeHeadList(String index){
		List<SbraTypeHead> list = sbraTypeManager.getSbraTypeHeadList();
		
		List<SelectorOption> sltList = new ArrayList<SelectorOption>();
		if("2".equals(index)){
			for(SbraTypeHead head:list){
				if(!"工费".equals(head.getSbraType()))
					sltList.add(new SelectorOption(head.getId()+"_"+head.getCoefficient(), head.getSbraType()));
			}
		}else if("1".equals(index)){
			for(SbraTypeHead head:list){//工费系数
				if("工费".equals(head.getSbraType()))
					sltList.add(new SelectorOption(head.getId()+"_"+head.getCoefficient(), head.getSbraType()));
			}
		}
		return sltList;
	}
	/**
	 * 获取配件类型下的材质
	 * @param hid
	 * @return
	 */
	public List<SelectorOption> getSbraTypeLineSelectList(String hid){
		if(null==hid||"".equals(hid))
			return null;
		else{
			List<SelectorOption> sltList = new ArrayList<SelectorOption>();
			List<Quality> list = sbraTypeManager.getSbraTypeLineSelectList(hid);
			for(Quality line:list){
				sltList.add(new SelectorOption(line.getQualityId(),line.getQualityName()));
			}
			return sltList;
		}
	}
	
}
