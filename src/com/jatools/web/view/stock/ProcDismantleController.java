package com.jatools.web.view.stock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
/**
 * 拆石单
 * @author ren.ming
 * <br>
 * Created 2011-11-20
 */
public class ProcDismantleController extends BaseChangeController {
	
	private static final String LIST_VM = "stock/procDismantle_list";
	private static final String EDIT_VM = "stock/procDismantle_edit";
	private static final String DOTYPE = "2";
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
