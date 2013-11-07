package com.jatools.web.view.stock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * 原料提纯单
 * @author ren.ming
 * <br>
 * Created 2011-12-2
 */
public class ProcPurifyController extends BaseChangeController {

	private static final String LIST_VM = "stock/procPurify_list";
	private static final String EDIT_VM = "stock/procPurify_edit";
	private static final String DOTYPE = "1";
	
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		return doDasePerform(LIST_VM, DOTYPE, req, res);
	}
	
	/**
	 * 页面跳转
	 * @param req
	 * @param res
	 * @return
	 */
	public ModelAndView toEdit(HttpServletRequest req, HttpServletResponse res) {
		return toBaseEdit(EDIT_VM, req, res);
	}
	
	public ModelAndView delete(HttpServletRequest req, HttpServletResponse res){
		return baseDelete(DOTYPE, req, res, LIST_VM);
	}
	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {
		return baseExportExcel(DOTYPE, req, res);
	}
}
