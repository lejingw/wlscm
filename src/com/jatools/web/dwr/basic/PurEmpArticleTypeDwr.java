package com.jatools.web.dwr.basic;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.jatools.common.CommonUtil;
import com.jatools.manager.basic.PurEmpArticleTypeManager;

public class PurEmpArticleTypeDwr {
	private static Logger logger = Logger.getLogger(PurEmpArticleTypeDwr.class);
	private PurEmpArticleTypeManager purEmpArticleTypeManager;

	public void setPurEmpArticleTypeManager(PurEmpArticleTypeManager purEmpArticleTypeManager) {
		this.purEmpArticleTypeManager = purEmpArticleTypeManager;
	}
	/**
	 * 保存采购员商品类别
	 * @return
	 */
	public String savePurEmpArticleType(String inOrgId, List<String> outOrgIdList, String outflag, HttpSession session){
		purEmpArticleTypeManager.savePurEmpArticleType(inOrgId, outOrgIdList, outflag, CommonUtil.getSessionUserId(session));
		return null;
	}
	/**
	 * 删除采购员商品类别
	 * @return
	 */
	public String deletePurEmpArticleType(List<String> netIdList){
		purEmpArticleTypeManager.deletePurEmpArticleType(netIdList);
		return null;
	}
}
