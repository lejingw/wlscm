package com.jatools.web.view.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jatools.common.ExcelUtil;
import com.jatools.common.constant.DictConstant;
import com.jatools.common.excel.*;
import com.jatools.manager.util.ExcelUtilManager;
import com.jatools.vo.util.ExcelRowData;
import com.jatools.web.cache.*;
import org.springframework.web.servlet.ModelAndView;

import com.jatools.common.CommonUtil;
import com.jatools.common.Global;
import com.jatools.common.Pager;
import com.jatools.manager.report.ReportManager;
import com.jatools.vo.util.ExcelData;
import com.jatools.web.form.report.ReportForm;
import com.jatools.web.util.ExportExcelUtil;
import com.jatools.web.util.StringUtil;
import com.jatools.web.view.BaseMultiActionController;

@SuppressWarnings("rawtypes")
public class ReportController extends BaseMultiActionController {
	private String listKey;
	private ReportManager reportManager;
	

	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	public String getSessionKey(){
		return super.getSessionKey() + listKey;
	}
	@Override
	public ModelAndView doPerform(HttpServletRequest req, HttpServletResponse res) {
		String reportType = CommonUtil.getParameterEmpty(req, "reportType");
		listKey = reportType;
		ReportForm form = new ReportForm();
		form.setReportType(reportType);
		try {
			
			if("1".equals(reportType)){   //库存汇总查询
				return stockgroup(req, form);
			}else if("36".equals(reportType)){   //加盟库存明细查询
				return stockgroup(req, form);
			}else if("2".equals(reportType)){   //库存明细查询
				return stocklist(req, form);
			}else if("32".equals(reportType)){   //加盟库存明细查询
				return stocklist(req, form);
			}else if("3".equals(reportType)){   //进销存
				return invoicing(req, form);
			}else if("4".equals(reportType)){   //门店满足率
				return orgmeet(req, form);
			}else if("5".equals(reportType)){   //总部满足率
				return orgallmeet(req, form);
			}else if("6".equals(reportType)){   //未关闭交接单统计
				return handnoclose(req, form);
			}else if("7".equals(reportType)){   //其它未关闭交接单统计
				return otherhandnoclose(req, form);
			}else if("8".equals(reportType)){   //下单款式查询
				return orderstyle(req, form);
			}else if("9".equals(reportType)){   //款式带分析范围
				return styleyesanaly(req, form);
			}else if("10".equals(reportType)){   //款式不带分析范围
				return stylenoanaly(req, form);
			}else if("11".equals(reportType)){   //饰品流转记录
				return ornaflowrecord(req, form);
			}else if("12".equals(reportType)){   //销售汇总查询
				return salegroup(req, form);
			}else if("13".equals(reportType)){   //销售明细查询
				return salelist(req, form);
			}else if("14".equals(reportType)){   //代销商品查询
				return saleconsi(req, form);
			}else if("15".equals(reportType)){   //采购入库物料统计
				return inivgroup(req, form);
			}else if("16".equals(reportType)){   //装箱单查询
				return packagefind(req, form);
			}else if("17".equals(reportType)){   //饰品编码盘点信息查询
				return inventory(req, form);
			//}else if("18".equals(reportType)){   //入库信息统计--暂时不用
			//	return handiniv(req, form);
			}else if("19".equals(reportType)){   //饰品库数据
				return matergroup(req, form);
			}else if("20".equals(reportType)){   //门店总单在途库存查询
				return orderinway(req, form);
			}else if("21".equals(reportType)){   //商品部库存明细查询
				return stocklist(req, form);
			}else if("22".equals(reportType)){   //商品部销售明细查询
				return salelist(req, form);
			}else if("23".equals(reportType)){   //库存统计
				return Matersum(req, form);
			}else if("24".equals(reportType)){   //调拔查询
				return Movelist(req, form);
			}else if("25".equals(reportType)){   //安全库存
				return SafeStock(req, form);
			}else if("26".equals(reportType)){   //核价查询
				return CalcList(req, form);
			}else if("27".equals(reportType)){   //裸石查询
				return LuosiList(req, form);
			}else if("28".equals(reportType)){   //进销存(无金额)
				return invoicing(req, form);
			}else if("29".equals(reportType)){   //门店总单在途库存查询（截止昨天）
				return orderinway(req, form);
			}else if("30".equals(reportType)){   //手工调配在途量
				return zhaitulist(req, form);
			}else if("31".equals(reportType)){   //销售查询
				return caiWuXiaoShou(req, form);
			}else if("33".equals(reportType)){   //销售查询
				return purchaseAnalysis(req, form);
			}else if("34".equals(reportType)){   //委外代销查询
				return weiWaiDaiXiao(req, form);
			}else if("35".equals(reportType)){   //
				return saleconsicb(req, form);
			}else if("37".equals(reportType)){   //
				return caiWuWeiJieShuan(req, form);//未结算单据查询
			}else if("38".equals(reportType)){   //
                return orgmeet_pu(req, form);//推式 满足率查询
            }else if("39".equals(reportType)){   //推式下单在途库存查询
                return orderinway(req, form);
            }else if("40".equals(reportType)){   //饰品调拨类型查询报表
                return ornaMoveType(req, form);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	public ModelAndView exportExcel(HttpServletRequest req, HttpServletResponse res) {

		String reportType = CommonUtil.getParameterEmpty(req, "reportType");
		ReportForm form = new ReportForm();
		form.setReportType(reportType);
		try {
			
			if("1".equals(reportType)){   //库存汇总查询
				stockgroupToExcel(req, res, form);			
			}else if("36".equals(reportType)){   //加盟库存汇总查询
				stockgroupToExcel(req, res, form);				
			}else if("2".equals(reportType)){   //库存明细查询
				stocklistToExcel(req, res, form);				
			}else if("32".equals(reportType)){   //加盟库存明细查询
				stocklistToExcel(req, res, form);	
			}else if("3".equals(reportType)){   //进销存
				invoicingToExcel(req,res, form);		     
			}else if("4".equals(reportType)){   //门店满足率
				orgmeetToExcel(req, res,form);
			}else if("5".equals(reportType)){   //总部满足率
				orgallmeetToExcel(req, res,form);
			}else if("6".equals(reportType)){   //未关闭交接单统计
				handnocloseToExcel(req,res, form);
			}else if("7".equals(reportType)){   //其它未关闭交接单统计
				otherhandnocloseToExcel(req, res,form);
			}else if("8".equals(reportType)){   //下单款式查询
				orderstyleToExcel(req, res,form);
			}else if("9".equals(reportType)){   //款式带分析范围
				styleyesanalyToExcel(req,res, form);
			}else if("10".equals(reportType)){   //款式不带分析范围
				stylenoanalyToExcel(req,res, form);
			}else if("11".equals(reportType)){   //饰品流转记录
				ornaflowrecordToExcel(req,res, form);
			}else if("12".equals(reportType)){   //销售汇总查询
				salegroupToExcel(req,res, form);
			}else if("13".equals(reportType)){   //销售明细查询
				salelistToExcel(req,res, form);
			}else if("14".equals(reportType)){   //代销商品查询
				saleconsiToExcel(req,res, form);
			}else if("15".equals(reportType)){   //采购入库物料统计
				inivgroupToExcel(req, res,form);
			}else if("16".equals(reportType)){   //装箱单查询
				packagefindToExcel(req, res,form);
			}else if("17".equals(reportType)){   //饰品编码盘点信息查询
				inventoryToExcel(req, res,form);
			//}else if("18".equals(reportType)){   //入库信息统计--暂时不用
			//	handiniv(req, res,form);
			}else if("19".equals(reportType)){   //饰品库数据
				matergroupToExcel(req,res, form);
			}else if("20".equals(reportType)){   //门店总单在途库存查询
				orderinwayToExcel(req,res, form);
			}else if("21".equals(reportType)){   //商品部库存明细查询
				stocklistToExcel(req, res, form);	
			}else if("22".equals(reportType)){   //商品部销售明细查询
				salelistToExcel(req,res, form);
			}else if("23".equals(reportType)){   //库存统计
				MatersumToExcel(req,res, form);
			}else if("24".equals(reportType)){   //调拔查询
				MovelistToExcel(req,res, form);
			}else if("25".equals(reportType)){   //调拔查询
				SafeStockToExcel(req,res, form);
			}else if("26".equals(reportType)){   //核价查询
				CalcListToExcel(req,res, form);
			}else if("27".equals(reportType)){   //裸石查询
				LuosiListToExcel(req,res, form);
			}else if("28".equals(reportType)){   //进销存(无金额)
				invoicingToExcel(req,res, form);		 
			}else if("29".equals(reportType)){   //门店总单在途库存查询（截止昨天）
				orderinwayToExcel(req,res, form);
			}else if("30".equals(reportType)){   //手工调配在途量
				zhaitulisToExcel(req, res, form);
			}else if("31".equals(reportType)){   //销售查询
				caiWuXiaoShouToExcel(req, res, form);
			}else if("33".equals(reportType)){   //销售查询
				purchaseAnalysisToExcel(req, res, form);
			}else if("34".equals(reportType)){   //委外代销查询
				weiWaiDaiXiaoToExcel(req, res, form);
			}else if("35".equals(reportType)){   //
				saleconsiToExcelcb(req, res, form);
			}else if("37".equals(reportType)){   //未结算单据查询
				caiWuWeiJieShuanToExcel(req, res, form);
			}else if("38".equals(reportType)){   //未结算单据查询
				orgmeetToExcel_pu(req, res, form);
			}else if("39".equals(reportType)){   //推式下单在途库存查询
                orderinwayToExcel(req, res, form);
            }else if("40".equals(reportType)){   //推式下单在途库存查询
                ornaMoveTypeExcel(req, res, form);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    //库存组织查询公用SQL
	private StringBuffer getStockgroupSql(HttpServletRequest req, ReportForm form) {
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","OrgId", "stockid", "state","articleTypeId", "itemClassId", "ornaClassId", "analysisId",
				"styleItemClassId", "styleMiddleClassId","styleOrnaClassId", "styleId", "themeid", "diagrade", "maincolorgradeid", "cleanid","shapeid", "stockage", "handoverno", "isconsign","sid");
		form.setCondition(condition);
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
	
		StringBuffer sql = new StringBuffer();
		
		sql.append("select so.org_name,ic.item_class_dsc,oc.orna_class_dsc,aa.analysis_arange_code,co.item_value || cl.item_value pingz,count(1) sumnum,sum(ma.all_qty) sumqty");
        sql.append("  from ic_mater_active ma,jas_bd_item_class ic,jas_bd_orna_class oc,jas_bd_style bs,jas_bd_analysis_arange aa, jas_sm_org so,(select t.item_key, t.item_value from jat_sys_dict_item t  where t.entry_code = 'gradelevel') co, (select t.item_key, t.item_value  from jat_sys_dict_item t where t.entry_code = 'gradelevel') cl");       
        sql.append(" where ma.item_class_id = ic.item_class_id   and ma.orna_class_id = oc.orna_class_id   and ma.style_id = bs.styleid   and ma.org_id = so.org_id   and ma.alaysis_id = aa.analysis_arange_id   and ma.color_grade_grade_id = co.item_key(+)   and ma.clean_grade_id = cl.item_key(+) ");
        
        if (form.getReportType().equals("36")){
			sql.append(" and so.ISFRANCHISE = 1 ");
		}
        
        //默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
    	    	sql.append(" and 1=0 ");   	       
    	 }
        
        //组织,必选项否则不查询数据
       if(StringUtil.isNotEmpty(condition.get("OrgId"))){
    	    
			sql.append(" and ma.org_id  in (" + condition.get("OrgId") + ")");
		} 
       //仓库
       if(StringUtil.isNotEmpty(condition.get("stockid"))){
    	    
			sql.append(" and ma.stock_id  = '" + condition.get("stockid") + "'");
		}
       //状态
       if(StringUtil.isNotEmpty(condition.get("state"))){
    	   
			sql.append(" and ma.state  = '" + condition.get("state") + "'");
		}
       //商品类别
       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
    	   
		   sql.append(" and ic.article_type_id = '" + condition.get("articleTypeId") + "'");
		}
       //大类
       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
    	    
			sql.append(" and ma.item_class_id  = '" + condition.get("itemClassId") + "'");
		}
       //小类
       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
    	    
			sql.append(" and ma.orna_class_id  = '" + condition.get("ornaClassId") + "'");
		}
       //分析范围
       if(StringUtil.isNotEmpty(condition.get("analysisId"))){
    	    
			sql.append(" and ma.alaysis_id  = '" + condition.get("analysisId") + "'");
		}
        //款式大类
       if(StringUtil.isNotEmpty(condition.get("styleItemClassId"))){
    	    
			sql.append(" and bs.styleitemclass  = '" + condition.get("styleItemClassId") + "'");
		}
        //款式中类
       if(StringUtil.isNotEmpty(condition.get("styleMiddleClassId"))){
    	    
			sql.append(" and bs.stylemiddleclass  = '" + condition.get("styleMiddleClassId") + "'");
		}
       //款式小类
       if(StringUtil.isNotEmpty(condition.get("styleOrnaClassId"))){
    	    
			sql.append(" and bs.styleornaclass = '" + condition.get("styleOrnaClassId") + "'");
		}      
       //款式
       if(StringUtil.isNotEmpty(condition.get("styleId"))){
    	    
			sql.append(" and ma.style_id = '" + condition.get("styleId") + "'");
		}       
       //主题名称
       if(StringUtil.isNotEmpty(condition.get("themeid"))){
    	   
			sql.append(" and bs.theme_id = '" + condition.get("themeid") + "'");
		}       
       //钻石品质
       if(StringUtil.isNotEmpty(condition.get("diagrade"))){
    	    
			sql.append(" and co.item_value || cl.item_value = '" + condition.get("diagrade") + "'");
		}
       //钻石色级
       if(StringUtil.isNotEmpty(condition.get("maincolorgradeid"))){
    	   
			sql.append(" and ma.main_color_grade_id  = '" + condition.get("maincolorgradeid") + "'");
		}
       //钻石净度
       if(StringUtil.isNotEmpty(condition.get("cleanid"))){
    	    
			sql.append(" and ma.clean_id = '" + condition.get("cleanid") + "'");
		}
       //钻石形状
       if(StringUtil.isNotEmpty(condition.get("shapeid"))){
    	    
			sql.append(" and ma.main_shape_id = '" + condition.get("shapeid") + "'");
		}
       //库龄
       if(StringUtil.isNotEmpty(condition.get("stockage"))){
    	    
    	    if (condition.get("stockage").equals("0")) { //0-30天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<30");
    	    }else if (condition.get("stockage").equals("1")){ //30-60天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=30");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<60");
    	    }else if (condition.get("stockage").equals("2")){ //60-90天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=60");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<90");
    	    }else if (condition.get("stockage").equals("3")){ //90-180天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=90");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<180");
    	    }else if (condition.get("stockage").equals("4")){ //180-360天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=180");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<360");
    	    }else if (condition.get("stockage").equals("5")){ //>360天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(ma.TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=360");
    	    }
		}
       //交接单号
       if(StringUtil.isNotEmpty(condition.get("handoverno"))){
    	    
			sql.append(" and exists (select hh.billid from jat_pur_handover_head hh  where hh.billid=ma.handover_bill_id  and  hh.billno='" + condition.get("handoverno") + "'  )");
		}
       //是否代销
       if(StringUtil.isNotEmpty(condition.get("isconsign"))){
    	    
			sql.append(" and ma.is_consign = '" + condition.get("isconsign") + "'");
		}
     
        //group by order by 
        sql.append(" group by so.org_id,so.org_name,ic.item_class_dsc,ic.sort,oc.orna_class_dsc,oc.sort,aa.analysis_arange_code,co.item_value || cl.item_value ");

        sql.append(" order by so.org_id,ic.sort, oc.sort");
        
		return sql;
	}
    //库存汇总查询
	private ModelAndView stockgroup(HttpServletRequest req, ReportForm form) throws Exception{
		StringBuffer sql = getStockgroupSql(req, form);
		String tofrom="";
		if (form.getReportType().equals("1")){
			tofrom="report/stockgroup";
		}else if (form.getReportType().equals("36")){
			tofrom="report/stockgroupjm";
		}
	    
		String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
		List<Map> listc=reportManager.executeQurey(count_sql);
		String totalCount =listc.get(0).get("NUMS").toString();
	
	    String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
		List<Map> list = reportManager.executeQurey(list_sql);
			
		form.setList(list);
		form.setTotalCount(totalCount);	 
		
		String sum_sql="select sum(sumnum) totalnum,sum(sumqty) totalqty from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) ";
		List<Map> listsum = reportManager.executeQurey(sum_sql);				
		form.setListSum(listsum);
		
		
		
		return new ModelAndView(tofrom, "form", form);
	}


	
    //库存汇总查询导出excel
	private void stockgroupToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

		StringBuffer sql = getStockgroupSql(req, form);
		//List<Map> dataList = reportManager.executeQurey(sql.toString());
		Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("库存汇总查询");		
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"ORG_NAME"}, new Integer[]{4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"组织", "大类", "小类", "分析范围", "件数", "重量", "品质"});
		//添加列name
		excelData.setColumnName(new String[]{"ORG_NAME", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE", "SUMNUM", "SUMQTY", "PINGZ"});
	    
	
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
	}
	
	
	//y库存明细得到sql
	private StringBuffer getStocklistSql(HttpServletRequest req, ReportForm form) {
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, "Finds","OrgId", "stockid", "state","articleTypeId", "itemClassId", "ornaClassId", "analysisId",
				"styleItemClassId", "styleMiddleClassId","styleOrnaClassId", "styleId", "themeid", "diagrade", "maincolorgradeid", "cleanid","shapeid", "stockage", "handoverno", 
				"isconsign","ornaCode","weightStr","weightEnd","priceStr","priceEnd","checkNo","sizeId","isStyle","sid","isLock",
				"privateType");
		form.setCondition(condition);
		
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		
		StringBuffer sql = new StringBuffer("select * FROM jav_report_mater_active ma  where 1=1 ");
		//2012.08.30
		if (form.getReportType().equals("32")){
			sql.append(" and ma.ISFRANCHISE =1 ");
		}
		sql.append(condition.get("_q_sql")==null?"":condition.get("_q_sql"));
		//默认不显示数据，查询即显示
	       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
	   	    	sql.append(" and 1=0 ");   	       
	   	    } 
		
		
        //组织,必选项否则不查询数据
       if(StringUtil.isNotEmpty(condition.get("OrgId"))){
    	    
			sql.append(" and org_id  in (" + condition.get("OrgId") + ")");
		} 
       //仓库
       if(StringUtil.isNotEmpty(condition.get("stockid"))){
    	    
			sql.append(" and stock_id  = '" + condition.get("stockid") + "'");
		}
       //状态
       if(StringUtil.isNotEmpty(condition.get("state"))){
    	    
			sql.append(" and state  = '" + condition.get("state") + "'");
		}
       //商品类别
       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
    	   
			sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
		}
       //大类
       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
    	   
			sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
		}
       //小类
       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
    	   
			sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
		}
       //分析范围
       if(StringUtil.isNotEmpty(condition.get("analysisId"))){
    	   
			sql.append(" and alaysis_id  = '" + condition.get("analysisId") + "'");
		}
        //款式大类
       if(StringUtil.isNotEmpty(condition.get("styleItemClassId"))){
    	   
			sql.append(" and styleitemclass  = '" + condition.get("styleItemClassId") + "'");
		}
        //款式中类
       if(StringUtil.isNotEmpty(condition.get("styleMiddleClassId"))){
    	   
			sql.append(" and stylemiddleclass  = '" + condition.get("styleMiddleClassId") + "'");
		}
       //款式小类
       if(StringUtil.isNotEmpty(condition.get("styleOrnaClassId"))){
    	   
			sql.append(" and styleornaclass = '" + condition.get("styleOrnaClassId") + "'");
		}      
       //款式
       if(StringUtil.isNotEmpty(condition.get("styleId"))){
    	   
			sql.append(" and style_id = '" + condition.get("styleId") + "'");
		}       
       //主题名称
       if(StringUtil.isNotEmpty(condition.get("themeid"))){
    	   
			sql.append(" and theme_id = '" + condition.get("themeid") + "'");
		}       
       //钻石品质
       if(StringUtil.isNotEmpty(condition.get("diagrade"))){
    	   
			sql.append(" and  pingz = '" + condition.get("diagrade") + "'");
		}
       //钻石色级
       if(StringUtil.isNotEmpty(condition.get("maincolorgradeid"))){
    	   
			sql.append(" and main_color_grade_id  = '" + condition.get("maincolorgradeid") + "'");
		}
       //钻石净度
       if(StringUtil.isNotEmpty(condition.get("cleanid"))){
    	   
			sql.append(" and clean_id = '" + condition.get("cleanid") + "'");
		}
       //钻石形状
       if(StringUtil.isNotEmpty(condition.get("shapeid"))){
    	    
			sql.append(" and main_shape_id = '" + condition.get("shapeid") + "'");
		}
       //库龄
       if(StringUtil.isNotEmpty(condition.get("stockage"))){
    	   
    	    if (condition.get("stockage").equals("0")) { //0-30天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<30");
    	    }else if (condition.get("stockage").equals("1")){ //30-60天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=30");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<60");
    	    }else if (condition.get("stockage").equals("2")){ //60-90天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=60");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<90");
    	    }else if (condition.get("stockage").equals("3")){ //90-180天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=90");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<180");
    	    }else if (condition.get("stockage").equals("4")){ //180-360天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=180");
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')<360");
    	    }else if (condition.get("stockage").equals("5")){ //>360天
			    sql.append("  and to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd') - to_date(substr(TOSHOPDATE, 1, 10), 'yyyy-mm-dd')>=360");
    	    }
		}
       //交接单号
       if(StringUtil.isNotEmpty(condition.get("handoverno"))){
    	   
			sql.append(" and exists (select hh.billid from jat_pur_handover_head hh  where hh.billid=handover_bill_id  and  hh.billno='" + condition.get("handoverno") + "'  )");
		}
       //是否代销
       if(StringUtil.isNotEmpty(condition.get("isconsign"))){
    	   
			sql.append(" and is_consign = '" + condition.get("isconsign") + "'");
		}
      
       //饰品编码
       if(StringUtil.isNotEmpty(condition.get("ornaCode"))){    	   
			//找该编码同款式数据
			if(StringUtil.isNotEmpty(condition.get("isStyle"))){
				sql.append(" and exists (select 1 from ic_mater_active ic where ic.orna_code = '" + condition.get("ornaCode") + "' and ic.style_id=ma.style_id )");
			} else {
				sql.append(" and orna_code = '" + condition.get("ornaCode") + "'");
			}
		}
   
		
       //重量始
       if(StringUtil.isNotEmpty(condition.get("weightStr"))){
    	   
			sql.append(" and all_qty >= '" + condition.get("weightStr") + "'   ");
		}
       //重量止
       if(StringUtil.isNotEmpty(condition.get("weightEnd"))){
    	   
			sql.append(" and all_qty <= '" + condition.get("weightEnd") + "'  ");
		}
       //价格始
       if(StringUtil.isNotEmpty(condition.get("priceStr"))){
    	   
			sql.append(" and pos_amount >= '" + condition.get("priceStr") + "'   ");
		}
       //价格止
       if(StringUtil.isNotEmpty(condition.get("priceEnd"))){
    	   
			sql.append("  and pos_amount <= '" + condition.get("priceEnd") + "'  ");
		}
       //证书号
       if(StringUtil.isNotEmpty(condition.get("checkNo"))){    	   
			sql.append(" and (IDENT_ID = '" + condition.get("checkNo") + "'  or HRD_CERT = '" + condition.get("checkNo") + "'  or GIA_CERT = '" + condition.get("checkNo") + "' or IGI_CERT = '" + condition.get("checkNo") + "' or AGS_CERT = '" + condition.get("checkNo") + "')");
		}
       
       //
      //尺寸
       if(StringUtil.isNotEmpty(condition.get("sizeId"))){
    	   
			sql.append("  and SIZE_ID = '" + condition.get("sizeId") + "'  ");
		}
       //是否锁定
		if(StringUtil.isNotEmpty(condition.get("isLock"))&&StringUtil.equals("on", condition.get("isLock"))){
			sql.append(" and exists (select 1 from ic_mater_active ic where ic.orna_code = ma.ORNA_CODE and ic.IS_LOCK=1 ) ");
		}
       
		//是否高工艺
       if(StringUtil.isNotEmpty(condition.get("privateType"))){
    	    
			sql.append(" and private_type = '" + condition.get("privateType") + "'");
		}
	       
       sql.append(" order by org_id,itemsort,ornasort");
		return sql;
	}
    //库存明细查询
	private ModelAndView stocklist(HttpServletRequest req, ReportForm form) throws Exception{
		String tofrom="";
		if (form.getReportType().equals("2")){
			tofrom="report/stocklist";
		}else if (form.getReportType().equals("21")){
			tofrom="report/stocklistsp";
		}else if (form.getReportType().equals("32")){
			tofrom="report/stocklistjm";
		}
		if("".equals(CommonUtil.getParameterEmpty(req, "firstFlag"))){
			form.setTotalCount("0");
			String userid = CommonUtil.getSessionUserId(req);
			form.setStart(Global.PAGE_DEFAULT_START);
			form.setLimit(Global.PAGE_DEFAULT_LIMIT);
			req.getSession().removeAttribute("query_condition_" + userid + "_KCMX01");
		}else{
			StringBuffer sql = getStocklistSql(req, form);
			String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
			List<Map> listc=reportManager.executeQurey(count_sql);
			String totalCount =listc.get(0).get("NUMS").toString();
			
	        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
			List<Map> list = reportManager.executeQurey(list_sql);
			
			form.setList(list);
			form.setTotalCount(totalCount);	 
	        
			String sum_sql="select sum(NOW_QTY)as SUM_NOW_QTY,sum(ALL_QTY) as SUM_ALL_QTY,sum(BASIC_PRICE) as SUM_BASIC_PRICE,sum(POS_AMOUNT) as SUM_POS_AMOUNT,SUM(STONE_TOTAL_NUM) AS SUM_STONE_TOTAL_NUM,SUM(STONE_NOW_NUM) AS SUM_STONE_NOW_NUM,SUM(SPECIAL_WORK_PRICE) AS SUM_SPECIAL_WORK_PRICE,SUM(STRING_WORK_PRICE) AS SUM_STRING_WORK_PRICE,SUM(SPECIAL_WEIGHT) AS SUM_SPECIAL_WEIGHT,SUM(PART_WEIGHT) AS SUM_PART_WEIGHT,SUM(MAIN_NUM) AS SUM_MAIN_NUM,SUM(PART_NUM) AS SUM_PART_NUM,ROUND(sum(REAL_TOTAL_COST),2) as SUM_REAL_TOTAL_COST from ("+sql.toString()+" ) ";
			List<Map> listsum = reportManager.executeQurey(sum_sql);				
			form.setListSum(listsum);
		}
		return new ModelAndView(tofrom, "form", form);
	}


    //库存明细查询导出excel
	private void stocklistToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

		StringBuffer sql = getStocklistSql(req, form);
		//List<Map> dataList = reportManager.executeQurey(sql.toString());
		Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("库存明细查询");		
		excelData.setPager(pager);//直接利用分页pager对象
	
		if (form.getReportType().equals("2")){
		   //添加列标题
		   excelData.setColumnTitle(new String[]{"组织","仓库","饰品编码", "饰品名称", "柜组", "现有量", "总重量", "大类", "小类", "分析范围","款式大类","款式中类","款式小类", "款式", "托架材质", "尺寸", "标签类型", "计量单位", "价格属性组", "饰品状态", "网点金额", "门店颜色", "公司颜色", "主石形状", "钻石颜色", "主石重量","配石重量", "配石内容", "钻石净度", "主石色级", "品质", "裸石总粒数", "裸石现有粒数", "特殊工费", "红绳工费", "常用摘要", "生肖", "鉴定证书号", "特殊重量", "切工", "切工台宽比", "切工亭深比", "对称性", "抛光", "荧光", "腰围", "底尖", "HRD国际证书", "GIA国际证书", "IGI国际证书", "托架颜色", "佩戴对象", "是否多粒", "主石粒数", "配石粒数", "旧品责任人", "单据编号", "单据名称", "到店日期", "入库日期","工厂饰品编码","是否高工艺"});
		   //添加列name
		   excelData.setColumnName(new String[]{"ORG_NAME", "STOCK", "ORNA_CODE","ORNA_DSC","GROUPS",  "NOW_QTY", "ALL_QTY", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE","ITEM_CLASS_NAME","MIDDLE_CLASS_NAME", "ORNA_CLASS_NAME",  "STYLENAME", "QUALITY_DSC", "SIZE_NAME", "TAG_TYPE", "UNITNAME", "PRICE_ATTR_GROUP", "STATES",  "POS_AMOUNT", "STORE_COLOR", "COM_STORE", "SHAPENAME", "COLORNAME", "MAIN_WEIGHT","PART_WEIGHT", "PART_CONTENT", "CLEANNAME", "COLORGRADE", "PINGZ", "STONE_TOTAL_NUM", "STONE_NOW_NUM", "SPECIAL_WORK_PRICE", "STRING_WORK_PRICE", "SUMMARY_NAME", "ZODIAC", "IDENT_ID", "SPECIAL_WEIGHT", "CUTNAME", "CUT_WIDE_SCALE", "CUT_DEEP_SCALE", "SYMMNAME", "POLINAME", "FLUONAME", "WAISNAME", "VERTNAME", "HRD_CERT", "GIA_CERT", "IGI_CERT", "BRACKET_COLOR_DSC", "WEAR_NAME", "ISMUTIPART", "MAIN_NUM", "PART_NUM", "EMPNAME", "BILL_NO", "BILL_NAME", "TOSHOPDATE", "STORAGEDATE","FACTORY_ORNA_CODE","PRIVATE_TYPE"});
		} else if (form.getReportType().equals("21")){
		   //添加列标题
		   excelData.setColumnTitle(new String[]{"组织","仓库","饰品编码", "饰品名称", "柜组", "现有量", "总重量", "大类", "小类", "分析范围","款式大类","款式中类","款式小类", "款式", "托架材质", "尺寸", "标签类型", "计量单位", "价格属性组", "饰品状态", "基础价", "网点金额", "暂估成本","源成本","供应商","门店颜色", "公司颜色", "主石形状", "钻石颜色", "主石重量","配石重量", "配石内容", "钻石净度", "主石色级", "品质", "裸石总粒数", "裸石现有粒数", "特殊工费", "红绳工费", "常用摘要", "生肖", "鉴定证书号", "特殊重量", "切工", "切工台宽比", "切工亭深比", "对称性", "抛光", "荧光", "腰围", "底尖", "HRD国际证书", "GIA国际证书", "IGI国际证书", "托架颜色", "佩戴对象", "是否多粒", "主石粒数", "配石粒数", "旧品责任人", "单据编号", "单据名称", "到店日期", "入库日期","工厂饰品编码","是否高工艺"});
		   //添加列name
		   excelData.setColumnName(new String[]{"ORG_NAME", "STOCK", "ORNA_CODE","ORNA_DSC","GROUPS",  "NOW_QTY", "ALL_QTY", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE","ITEM_CLASS_NAME","MIDDLE_CLASS_NAME", "ORNA_CLASS_NAME", "STYLENAME", "QUALITY_DSC", "SIZE_NAME", "TAG_TYPE", "UNITNAME", "PRICE_ATTR_GROUP", "STATES", "BASIC_PRICE", "POS_AMOUNT","REAL_TOTAL_COST","INIV_COST", "CUSTNAME", "STORE_COLOR", "COM_STORE", "SHAPENAME", "COLORNAME","MAIN_WEIGHT", "PART_WEIGHT", "PART_CONTENT", "CLEANNAME", "COLORGRADE", "PINGZ", "STONE_TOTAL_NUM", "STONE_NOW_NUM", "SPECIAL_WORK_PRICE", "STRING_WORK_PRICE", "SUMMARY_NAME", "ZODIAC", "IDENT_ID", "SPECIAL_WEIGHT", "CUTNAME", "CUT_WIDE_SCALE", "CUT_DEEP_SCALE", "SYMMNAME", "POLINAME", "FLUONAME", "WAISNAME", "VERTNAME", "HRD_CERT", "GIA_CERT", "IGI_CERT", "BRACKET_COLOR_DSC", "WEAR_NAME", "ISMUTIPART", "MAIN_NUM", "PART_NUM", "EMPNAME", "BILL_NO", "BILL_NAME", "TOSHOPDATE", "STORAGEDATE","FACTORY_ORNA_CODE","PRIVATE_TYPE"});
		}else if(form.getReportType().equals("32")){
		   //添加列标题
		   excelData.setColumnTitle(new String[]{"组织","仓库","饰品编码", "饰品名称", "柜组", "现有量", "总重量", "大类", "小类", "分析范围","款式大类","款式中类","款式小类", "款式", "托架材质", "尺寸", "标签类型", "计量单位", "价格属性组", "饰品状态", "网点金额", "门店颜色", "公司颜色", "主石形状", "钻石颜色", "主石重量","配石重量", "配石内容", "钻石净度", "主石色级", "品质", "裸石总粒数", "裸石现有粒数", "特殊工费", "红绳工费", "常用摘要", "生肖", "鉴定证书号", "特殊重量", "切工", "切工台宽比", "切工亭深比", "对称性", "抛光", "荧光", "腰围", "底尖", "HRD国际证书", "GIA国际证书", "IGI国际证书", "托架颜色", "佩戴对象", "是否多粒", "主石粒数", "配石粒数", "旧品责任人", "单据编号", "单据名称", "到店日期", "入库日期","工厂饰品编码","是否高工艺"});
		   //添加列name
		   excelData.setColumnName(new String[]{"ORG_NAME", "STOCK", "ORNA_CODE","ORNA_DSC","GROUPS",  "NOW_QTY", "ALL_QTY", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE","ITEM_CLASS_NAME","MIDDLE_CLASS_NAME", "ORNA_CLASS_NAME",  "STYLENAME", "QUALITY_DSC", "SIZE_NAME", "TAG_TYPE", "UNITNAME", "PRICE_ATTR_GROUP", "STATES",  "POS_AMOUNT", "STORE_COLOR", "COM_STORE", "SHAPENAME", "COLORNAME", "MAIN_WEIGHT","PART_WEIGHT", "PART_CONTENT", "CLEANNAME", "COLORGRADE", "PINGZ", "STONE_TOTAL_NUM", "STONE_NOW_NUM", "SPECIAL_WORK_PRICE", "STRING_WORK_PRICE", "SUMMARY_NAME", "ZODIAC", "IDENT_ID", "SPECIAL_WEIGHT", "CUTNAME", "CUT_WIDE_SCALE", "CUT_DEEP_SCALE", "SYMMNAME", "POLINAME", "FLUONAME", "WAISNAME", "VERTNAME", "HRD_CERT", "GIA_CERT", "IGI_CERT", "BRACKET_COLOR_DSC", "WEAR_NAME", "ISMUTIPART", "MAIN_NUM", "PART_NUM", "EMPNAME", "BILL_NO", "BILL_NAME", "TOSHOPDATE", "STORAGEDATE","FACTORY_ORNA_CODE","PRIVATE_TYPE"});
		} 
		
		//设置对应的数据字典
		excelData.addDictDisplayColumns(new String[]{"PRIVATE_TYPE"}, new String[]{"yesorno"});
				
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
	}
	
	public StringBuffer getZhaitulistSql(HttpServletRequest req, ReportForm form) {
		StringBuffer sql = new StringBuffer("select * from jav_report_zhaitu zt where 1=1 ");
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","outOrgId","inOrgId","itemClassId","ornaClassId","analysisId");
		form.setCondition(condition);
		 
		//默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
    	    	sql.append(" and 1=0 ");   	
    	 }
		
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		//调出组织
       if(StringUtil.isNotEmpty(condition.get("outOrgId"))){
			sql.append(" and OUT_ORG_ID  in(" + condition.get("outOrgId") + ")");
		}
       //调入组织
       if(StringUtil.isNotEmpty(condition.get("inOrgId"))){
			sql.append(" and IN_ORG_ID  in (" + condition.get("inOrgId") + ")");
		}
		//大类
       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			sql.append(" and ITEM_CLASS_ID  = '" + condition.get("itemClassId") + "'");
		}
       //小类
       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			sql.append(" and ORNA_CLASS_ID  = '" + condition.get("ornaClassId") + "'");
		}
       //分析范围
       if(StringUtil.isNotEmpty(condition.get("analysisId"))){
			sql.append(" and ANALYSIS_ID  = '" + condition.get("analysisId") + "'");
		}
		
		return sql;
	}
	//手工调配在途量
	public ModelAndView zhaitulist(HttpServletRequest req, ReportForm form) throws Exception{
		
		StringBuffer sql = getZhaitulistSql(req, form);
		
		String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
		List<Map> listc=reportManager.executeQurey(count_sql);
		String totalCount =listc.get(0).get("NUMS").toString();
		List<Map> list = reportManager.executeQurey("select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit());
	
		form.setList(list);
		form.setTotalCount(totalCount);
		String tofrom = "report/zhaitul";
		 
		return new ModelAndView(tofrom, "form", form);
	}
	
	//手工调配在途量查询导出excel
	private void zhaitulisToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

		Map<String, String> condition = CommonUtil.getConditionForPage(req, "");
		form.setCondition(condition);
		
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		StringBuffer sql = getZhaitulistSql(req, form);
		Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("手工调配在途量查询");		
		excelData.setPager(pager);//直接利用分页pager对象
	
	   //添加列标题
	   excelData.setColumnTitle(new String[]{"调出组织","调入组织","大类","小类","分析范围","款式大类","款式中类","款式小类","款式","计量单位","尺寸","成色","色级","净度","分配未到店数量"});
	   //添加列name
	   excelData.setColumnName(new String[]{"OUT_ORG_NAME","IN_ORG_NAME","ITEM_CLASS_NAME","ORNA_CLASS_NAME","ANALYSIS_NAME","STYLE_ITEM_CLASS","STYLE_MIDDLE_CLASS","STYLE_ORNA_CLASS","STYLE_NAME","UNIT_ID","SIZE_ID","QUALITY_ID","MAIN_COLOR_GRADE_ID","CLEAN_ID","COUNTS"});
		
	
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
	}
	
	public StringBuffer getCaiWuXiaoShouSql(HttpServletRequest req, ReportForm form) {
		StringBuffer sql = new StringBuffer(
			"select a.orna_code ,\n" +
			"       a.org_name ,\n" + 
			"       a.item_class_dsc ,\n" + 
			"       a.orna_class_dsc ,\n" + 
			"       a.trans_date ,\n" + 
			"       a.trans_qty ,\n" + 
			"       a.unitname ,\n" + 
			"       substr(a.storagedate, 1, 10) STORAGEDATE,\n" + 
			"       round(a.real_total_cost / a.trans_qty, 2) TOTALCOST,\n" + 
			"       a.real_total_cost \n" + 
			
			"  from jav_report_sale_list a\n" + 
			" where a.trans_flag=0 and a.trans_qty <>0 \n" 
			);
		
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this,req,"firstFlag");
		form.setCondition(condition);
		 
		//默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("firstFlag"))){ 
    	    	sql.append(" and 1=0 ");   	    
    	 }
        sql.append(condition.get("_q_sql")==null?"":condition.get("_q_sql"));
        
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		
		sql.append(" order by a.org_name, a.item_class_dsc, a.orna_class_dsc, a.trans_date");
		
		return sql;
	}
	
	//销售查询
	public ModelAndView caiWuXiaoShou(HttpServletRequest req, ReportForm form) throws Exception{
		
		if("".equals(CommonUtil.getParameterEmpty(req, "firstFlag"))){
			form.setTotalCount("0");
			String userid = CommonUtil.getSessionUserId(req);
			form.setStart(Global.PAGE_DEFAULT_START);
			form.setLimit(Global.PAGE_DEFAULT_LIMIT);
	    	req.getSession().removeAttribute("query_condition_" + userid + "_CWXS");   
		}else{
			StringBuffer sql = getCaiWuXiaoShouSql(req, form);
			
			String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
			List<Map> listc=reportManager.executeQurey(count_sql);
			String totalCount =listc.get(0).get("NUMS").toString();
			List<Map> list = reportManager.executeQurey("select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit());
		
			form.setList(list);
			form.setTotalCount(totalCount);
		}
		String tofrom = "report/caiWuXiaoShou";
		return new ModelAndView(tofrom, "form", form);
	}
	//销售查询导出excel
	private void caiWuXiaoShouToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

		Map<String, String> condition = CommonUtil.getConditionForPage(req, "");
		form.setCondition(condition);
		
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		StringBuffer sql = getCaiWuXiaoShouSql(req, form);
		Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("销售查询");		
		excelData.setPager(pager);//直接利用分页pager对象
	
	   //添加列标题
	   excelData.setColumnTitle(new String[]{"饰品编码","销售组织","大类","小类","销售时间","重量","计量单位","入库时间","成本单价","成本总额"});
	   //添加列name
	   excelData.setColumnName(new String[]{"ORNA_CODE","ORG_NAME","ITEM_CLASS_DSC","ORNA_CLASS_DSC","TRANS_DATE","TRANS_QTY","UNITNAME","STORAGEDATE","TOTALCOST","REAL_TOTAL_COST"});
		
	
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
	}
	
	//进销存查询sql
	private StringBuffer getInvoicingSql(HttpServletRequest req, ReportForm form) {
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","OrgId", "stockid","isTax", "startDate", "endDate");
		form.setCondition(condition);
		
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		
		StringBuffer sql = new StringBuffer("select org_name,stock,item_class_dsc,orna_class_dsc,");
		 sql.append(" start_qty + lastin_qty - lastout_qty first_qty, start_money + lastin_money - lastout_money first_money, ");
		 sql.append(" inivcg_qty,inivcg_money,inivww_qty,inivww_money,movein_qty,movein_money,moveout_qty,moveout_money,  ");
		 sql.append(" salein_qty,salein_money,saleout_qty,saleout_money,changein_qty,changein_money,changeout_qty,changeout_money,");
		 sql.append(" wwout_qty,wwout_money,dxout_qty,dxout_money,otherin_qty,otherin_money,otherout_qty,otherout_money, ");
		 sql.append(" inivcg_qty+inivww_qty+movein_qty+salein_qty+changein_qty+otherin_qty   sumin_qty,");
		 sql.append(" inivcg_money+inivww_money+movein_money+salein_money+changein_money+otherin_money  sumin_money,");       
		 sql.append(" moveout_qty+saleout_qty+changeout_qty+wwout_qty+dxout_qty+otherout_qty   sumout_qty, ");      
		 sql.append(" moveout_money+saleout_money+changeout_money+wwout_money+dxout_money+otherout_money  sumout_money, ");
		 sql.append(" start_qty + lastin_qty - lastout_qty+( inivcg_qty+inivww_qty+movein_qty+salein_qty+changein_qty+otherin_qty)-(moveout_qty+saleout_qty+changeout_qty+wwout_qty+dxout_qty+otherout_qty)   end_qty,bangc_qty, ");      
		 sql.append(" start_money + lastin_money - lastout_money+(inivcg_money+inivww_money+movein_money+salein_money+changein_money+otherin_money)-(moveout_money+saleout_money+changeout_money+wwout_money+dxout_money+otherout_money)  end_money ");
		 sql.append(" from (select so.org_name,decode(mt.stock_id, 1, '饰品库', 2, '旧品库') stock,ic.item_class_dsc,oc.orna_class_dsc,");
		 sql.append(" sum(decode(mt.trans_source_bill, 'QC', mt.trans_qty, 0)) start_qty, round(sum(decode(mt.trans_source_bill, 'QC', mt.trans_cost, 0))/"+condition.get("isTax")+",2) start_money,");
		 sql.append(" sum(case when trans_source_bill <> 'QC' and substr(mt.create_date,1,10) < '"+condition.get("startDate")+"' and mt.trans_flag = 1 then  mt.trans_qty  else 0 end) lastin_qty,");
		 sql.append(" round(sum(case when trans_source_bill <> 'QC' and substr(mt.create_date,1,10) < '"+condition.get("startDate")+"' and mt.trans_flag = 1 then  mt.trans_cost else 0 end)/"+condition.get("isTax")+",2) lastin_money,");
		 sql.append(" sum(case when trans_source_bill <> 'QC' and substr(mt.create_date,1,10) < '"+condition.get("startDate")+"' and mt.trans_flag = -1 then mt.trans_qty else 0   end) lastout_qty,");
		 sql.append(" round(sum(case when trans_source_bill <> 'QC' and substr(mt.create_date,1,10) < '"+condition.get("startDate")+"' and mt.trans_flag = -1 then mt.trans_cost else 0 end)/"+condition.get("isTax")+",2) lastout_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'JJ' and  mt.trans_source_type = '0' and mt.trans_flag = 1 then  mt.trans_qty else  0   end) inivcg_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'JJ' and  mt.trans_source_type = '0' and mt.trans_flag = 1 then  mt.trans_cost  else 0 end)/"+condition.get("isTax")+",2) inivcg_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'JJ' and  mt.trans_source_type = '1' and mt.trans_flag = 1 then  mt.trans_qty  else 0   end) inivww_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'JJ' and  mt.trans_source_type = '1' and mt.trans_flag = 1 then  mt.trans_cost else 0  end)/"+condition.get("isTax")+",2) inivww_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('TB', 'TH', 'TC') and  mt.trans_flag = 1 then  mt.trans_qty  else 0          end) movein_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('TB', 'TH', 'TC') and  mt.trans_flag = 1 then  mt.trans_cost else 0    end)/"+condition.get("isTax")+",2) movein_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('TB', 'TH', 'TC') and  mt.trans_flag = -1 then mt.trans_qty  else  0   end) moveout_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('TB', 'TH', 'TC') and  mt.trans_flag = -1 then mt.trans_cost  else  0 end)/"+condition.get("isTax")+",2) moveout_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'XS' and mt.trans_flag = 1 then  mt.trans_qty  else 0    end) salein_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'XS' and mt.trans_flag = 1 then  mt.trans_cost else  0  end)/"+condition.get("isTax")+",2) salein_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'XS' and mt.trans_flag = -1 then  mt.trans_qty  else  0   end) saleout_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'XS' and mt.trans_flag = -1 then mt.trans_cost  else  0 end)/"+condition.get("isTax")+",2) saleout_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('CF','XT','LT','CS') then  mt.trans_qty*mt.trans_flag  else 0    end) bangc_qty,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('CF','XT','LT','CS') and mt.trans_flag = 1 then  mt.trans_qty  else 0    end) changein_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('CF','XT','LT','CS') and mt.trans_flag = 1 then  mt.trans_cost  else 0   end)/"+condition.get("isTax")+",2) changein_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('CF','XT','LT','CS') and mt.trans_flag = -1 then mt.trans_qty else 0    end) changeout_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill in ('CF','XT','LT','CS') and mt.trans_flag = -1 then mt.trans_cost else 0  end)/"+condition.get("isTax")+",2) changeout_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'WW' and mt.trans_flag = -1 then mt.trans_qty  else  0    end) wwout_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'WW' and mt.trans_flag = -1 then mt.trans_cost else 0  end)/"+condition.get("isTax")+",2) wwout_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'DX' and mt.trans_flag = -1 then mt.trans_qty else 0  end) dxout_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill = 'DX' and mt.trans_flag = -1 then mt.trans_cost  else 0  end)/"+condition.get("isTax")+",2) dxout_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill not in  ('QC', 'JJ', 'TB', 'TH', 'TC', 'XS', 'XT', 'WW', 'DX','CF','LT','CS') and   mt.trans_flag = 1 then  mt.trans_qty else   0  end) otherin_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill not in  ('QC', 'JJ', 'TB', 'TH', 'TC', 'XS', 'XT', 'WW', 'DX','CF','LT','CS') and   mt.trans_flag = 1 then  mt.trans_cost else 0 end)/"+condition.get("isTax")+",2) otherin_money,");
		 sql.append(" sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill not in  ('QC', 'JJ', 'TB', 'TH', 'TC', 'XS', 'XT', 'WW', 'DX','CF','LT','CS') and  mt.trans_flag = -1 then  mt.trans_qty else 0    end) otherout_qty,");
		 sql.append(" round(sum(case when substr(mt.create_date,1,10) >= '"+condition.get("startDate")+"' and  substr(mt.create_date,1,10) <= '"+condition.get("endDate")+"' and mt.trans_source_bill not in  ('QC', 'JJ', 'TB', 'TH', 'TC', 'XS', 'XT', 'WW', 'DX','CF','LT','CS') and  mt.trans_flag = -1 then  mt.trans_cost else 0  end)/"+condition.get("isTax")+",2) otherout_money");
		 sql.append(" from jat_mater_trans   mt, jas_sm_org  so,jas_bd_item_class ic,jas_bd_orna_class oc where mt.org_id = so.org_id  and mt.item_class_id = ic.item_class_id   and mt.orna_class_id = oc.orna_class_id");
		// sql.append("");
		
		
			//默认不显示数据，查询即显示
	       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
	   	    	sql.append(" and 1=0 ");   	       
	   	    } 
		 
       //组织,必选项否则不查询数据
       if(StringUtil.isNotEmpty(condition.get("OrgId"))){
    	    
			sql.append(" and mt.org_id in (" + condition.get("OrgId") + ")");
		}
       //仓库
       if(StringUtil.isNotEmpty(condition.get("stockid"))){
    	   
			sql.append(" and mt.stock_id  = '" + condition.get("stockid") + "'");
		}
       //
       if(StringUtil.isNotEmpty(condition.get("startDate")) && StringUtil.isNotEmpty(condition.get("endDate"))){    	   
			sql.append(" and 1=1 ");
		} else {
			sql.append(" and 1=0 ");
			
		}
 
        //group by order by 
        sql.append("  group by so.org_name,decode(mt.stock_id, 1, '饰品库', 2, '旧品库'),ic.item_class_dsc,oc.orna_class_dsc)");
        
        sql.append("  order by  org_name,stock,item_class_dsc,orna_class_dsc ");
	   // System.out.println(sql);
		return sql;
	}
	
	//进销存查询
		private ModelAndView invoicing(HttpServletRequest req, ReportForm form) throws Exception{
			
			
			StringBuffer sql = getInvoicingSql(req, form);
			String tofrom="";
			if (form.getReportType().equals("3")){
				tofrom="report/invoicing";
			}else if (form.getReportType().equals("28")){
				tofrom="report/invoicingno";
			}
			
			String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
			List<Map> listc=reportManager.executeQurey(count_sql);
			String totalCount =listc.get(0).get("NUMS").toString();
			
	        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
			List<Map> list = reportManager.executeQurey(list_sql);
		
			form.setList(list);
			form.setTotalCount(totalCount);
		 
			return new ModelAndView(tofrom, "form", form);
		}
		
		
	    //进销存查询导出excel
		private void invoicingToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

			StringBuffer sql = getInvoicingSql(req, form);
			//List<Map> dataList = reportManager.executeQurey(sql.toString());
			Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

			//----------------------------------------------------------导出excel开始---------------------------------------
			//excel数据对象
			ExcelData excelData = new ExcelData();	
			excelData.setPager(pager);//直接利用分页pager对象
			if (form.getReportType().equals("3")){
				excelData.setTitle("进销存查询");
				//添加列标题
				excelData.setColumnTitle(new String[]{"组织", "仓库", "大类", "小类", "期初数量", "期初金额", "采购入数量", "采购入金额", "委外入数量", "委外入金额","调拔入数量", "调拔入金额","以旧换新入数量", "以旧换新入金额","转换入库数量", "转换入库金额","其它入库数量", "其它入库金额","入库小计数量", "入库小计金额","销售出数量", "销售出金额","委外出数量", "委外出金额","调拔出数量", "调拔出金额","转换出库数量", "转换出库金额","代销退回数量", "代销退回金额","其它出库数量", "其它出库金额","出库小计数量", "出库小计金额","磅差","期末数量", "期末金额"});
				//添加列name
				excelData.setColumnName(new String[]{"ORG_NAME", "STOCK", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "FIRST_QTY", "FIRST_MONEY", "INIVCG_QTY", "INIVCG_MONEY", "INIVWW_QTY", "INIVWW_MONEY", "MOVEIN_QTY", "MOVEIN_MONEY", "SALEIN_QTY", "SALEIN_MONEY", "CHANGEIN_QTY", "CHANGEIN_MONEY", "OTHERIN_QTY", "OTHERIN_MONEY", "SUMIN_QTY", "SUMIN_MONEY", "SALEOUT_QTY", "SALEOUT_MONEY", "WWOUT_QTY", "WWOUT_MONEY", "MOVEOUT_QTY", "MOVEOUT_MONEY", "CHANGEOUT_QTY", "CHANGEOUT_MONEY", "DXOUT_QTY", "DXOUT_MONEY", "OTHEROUT_QTY", "OTHEROUT_MONEY", "SUMOUT_QTY", "SUMOUT_MONEY","BANGC_QTY","END_QTY", "END_MONEY"});
			    
			
			}else if (form.getReportType().equals("28")){
				excelData.setTitle("进销存查询(无金额)");	
				//添加列标题
				excelData.setColumnTitle(new String[]{"组织", "仓库", "大类", "小类", "期初数量",  "采购入数量",  "委外入数量", "调拔入数量","以旧换新入数量", "转换入库数量", "其它入库数量", "入库小计数量", "销售出数量", "委外出数量", "调拔出数量", "转换出库数量", "代销退回数量", "其它出库数量", "出库小计数量", "磅差","期末数量"});
				//添加列name
				excelData.setColumnName(new String[]{"ORG_NAME", "STOCK", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "FIRST_QTY",  "INIVCG_QTY",  "INIVWW_QTY",  "MOVEIN_QTY",  "SALEIN_QTY",  "CHANGEIN_QTY",  "OTHERIN_QTY",  "SUMIN_QTY",  "SALEOUT_QTY",  "WWOUT_QTY",  "MOVEOUT_QTY",  "CHANGEOUT_QTY",  "DXOUT_QTY",  "OTHEROUT_QTY",  "SUMOUT_QTY", "BANGC_QTY","END_QTY"});
			    
			
			}
		
			
			ExportExcelUtil util = new ExportExcelUtil();
			util.setExcelData(excelData);//传入excel数据对象
			util.export(res);//需要传入HttpServletResponse，以便下载excel
			//----------------------------------------------------------导出excel结束---------------------------------------
		}
		
		//门店满足查询sql
		private StringBuffer getorgmeetSql(HttpServletRequest req, ReportForm form) {

			Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","OrgId","CycleTypeId", "startDate", "endDate");
			form.setCondition(condition);
			
			StringBuffer sql = new StringBuffer("select org_name,demand_date,order_end_date,demand_nums,get_nums,round(get_nums/demand_nums,4)*100||'%' meets from jav_report_order_org  where 1=1");
			
			form.setStart(condition.get("start"));
			form.setLimit(condition.get("limit"));
			
			//默认不显示数据，查询即显示
		       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
		   	    	sql.append(" and 1=0 ");   	       
		   	    }  
		       //组织,必选项否则不查询数据
		   if(StringUtil.isNotEmpty(condition.get("OrgId"))){
		    	 sql.append(" and org_id  in (" + condition.get("OrgId") + ")");
			}
		       //周期类型
		    if(StringUtil.isNotEmpty(condition.get("CycleTypeId"))){
		    	 sql.append(" and CYCLE_TYPE_ID  = '" + condition.get("CycleTypeId") + "'");
			}
		       //时间始
		    if(StringUtil.isNotEmpty(condition.get("startDate"))){
		    	 sql.append(" and order_end_date>= '" + condition.get("startDate") +"'");
			}
		    //时间止
		    if(StringUtil.isNotEmpty(condition.get("endDate"))){
		    	 sql.append(" and order_end_date<='"+condition.get("endDate")+"'");
			}
		   
		    sql.append(" order by org_id,demand_date");  
		    
			return sql;
		}
		
		//门店满足查询
			private ModelAndView orgmeet(HttpServletRequest req, ReportForm form) throws Exception{
			
		
				StringBuffer sql = getorgmeetSql(req, form);
		  
	
		    	String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
				
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);		
				
				String sum_sql="select org_name SUM_ORG_NAME, round(sum(get_nums) / sum(demand_nums), 4) * 100 || '%' summeets  from  ("+sql.toString()+" ) group by org_name ";
				List<Map> listsum = reportManager.executeQurey(sum_sql);				
				form.setListSum(listsum);
				
				//String total_sql="select round(sum(get_nums) / sum(demand_nums), 4) * 100 || '%' totalmeets  from  ("+sql.toString()+" )  ";
				String total_sql="select round(sum(get_nums/demand_nums) / count(1), 4) * 100 || '%' totalmeets  from  ("+sql.toString()+" )  ";
				
				List<Map> listtotal = reportManager.executeQurey(total_sql);				
				form.setListTotal(listtotal);
				
				return new ModelAndView("report/orgmeet", "form", form);
			}
			
			
		    //门店满足查询导出excel
			private void orgmeetToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getorgmeetSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("门店满足查询");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"组织", "下单日期", "最后到货日期", "下单数量", "到货数量", "满足率"});
				//添加列name
				excelData.setColumnName(new String[]{"ORG_NAME", "DEMAND_DATE", "ORDER_END_DATE", "DEMAND_NUMS", "GET_NUMS", "MEETS"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			private StringBuffer getorgallmeetSql(HttpServletRequest req, ReportForm form) {
				Map<String, String> condition = CommonUtil.getConditionForPage(req,"Finds", "CycleTypeId", "startDate", "endDate");
				form.setCondition(condition);
				
				StringBuffer sql = new StringBuffer("select demand_date,order_end_date,cycle_type_code,demand_nums,get_nums,round(get_nums/demand_nums,4)*100||'%' meets from jav_report_order_orgall  where 1=1");
				

				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    }  
			       //周期类型
			     if(StringUtil.isNotEmpty(condition.get("CycleTypeId"))){
			    	   sql.append(" and CYCLE_TYPE_ID  = '" + condition.get("CycleTypeId") + "'");
					}
			       //时间
			       if(StringUtil.isNotEmpty(condition.get("startDate"))){
			    	   sql.append(" and order_end_date>= '" + condition.get("startDate") +"'");
					}
			       //时间
			       if(StringUtil.isNotEmpty(condition.get("endDate"))){
			    	   sql.append(" and order_end_date<= '" + condition.get("endDate") +"'");
					}
			       sql.append(" order by demand_date");
			
			
				return sql;
			}
			//总部满足查询
			private ModelAndView orgallmeet(HttpServletRequest req, ReportForm form) throws Exception{
			
				StringBuffer sql = getorgallmeetSql(req, form);
				
		    	String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
				
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			    
				String sum_sql="select  round(sum(get_nums) / sum(demand_nums), 4) * 100 || '%' summeets  from  ("+sql.toString()+" )  ";
				List<Map> listsum = reportManager.executeQurey(sum_sql);				
				form.setListSum(listsum);
				
				form.setList(list);
				form.setTotalCount(totalCount);
		
				return new ModelAndView("report/orgmeetall", "form", form);
			}
			
			 //门店满足查询导出excel
			private void orgallmeetToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getorgallmeetSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("总部满足查询");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"下单日期", "最后到货日期", "周期类型","下单数量", "到货数量", "满足率"});
				//添加列name
				excelData.setColumnName(new String[]{"DEMAND_DATE", "ORDER_END_DATE", "CYCLE_TYPE_CODE","DEMAND_NUMS", "GET_NUMS", "MEETS"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			
			private StringBuffer gethandnocloseSql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","billNo", "startDate", "endDate");
				form.setCondition(condition);
				
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				StringBuffer sql = new StringBuffer("select * from jav_report_hand_noclose  where 1=1");
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    }  
		       //交接单号
		       if(StringUtil.isNotEmpty(condition.get("billNo"))){
		    	  sql.append(" and billno  = '" + condition.get("billNo") + "'");
				}
		   
		       
		       //时间始
			    if(StringUtil.isNotEmpty(condition.get("startDate"))){
			    	 sql.append(" and dodate>= '" + condition.get("startDate") +"'");
				}
			    //时间止
			    if(StringUtil.isNotEmpty(condition.get("endDate"))){
			    	 sql.append(" and dodate<='"+condition.get("endDate")+"'");
				}
		   
		       sql.append(" order by dodate");
		       
				return sql;
			}
			
			
			//未关闭交接单统计
			private ModelAndView handnoclose(HttpServletRequest req, ReportForm form) throws Exception{
				
				StringBuffer sql = gethandnocloseSql(req, form);
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
			
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);
		       		        
	
				return new ModelAndView("report/handnoclose", "form", form);
			}
			
			 //未关闭交接单统计导出excel
			private void handnocloseToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = gethandnocloseSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("未关闭交接单统计");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"交接单号", "创建日期", "单据状态", "入库件数", "入库重量", "单据来源"});
				//添加列name
				excelData.setColumnName(new String[]{"BILLNO", "DODATE", "STATE", "INIVNUM", "INIVWEI", "SRC_BILLNAME"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			private StringBuffer getotherhandnocloseSql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","billNo", "startDate", "endDate");
				form.setCondition(condition);
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				StringBuffer sql = new StringBuffer("select * from jav_report_handother_noclose  where 1=1");
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    }  
		       //交接单号
		       if(StringUtil.isNotEmpty(condition.get("billNo"))){
		    	  sql.append(" and billno  = '" + condition.get("billNo") + "'");
				}
		       //时间
		       if(StringUtil.isNotEmpty(condition.get("startDate"))){
		    	  sql.append(" and dodate>= '" + condition.get("startDate") +"'");
				}
		       //时间
		       if(StringUtil.isNotEmpty(condition.get("endDate"))){
		    	  sql.append(" and dodate<= '" + condition.get("endDate") +"'");
				}
		   
		       sql.append("  order by dodate");
				return sql;
			}
			
			//其它未关闭交接单统计
			private ModelAndView otherhandnoclose(HttpServletRequest req, ReportForm form) throws Exception{
				
				StringBuffer sql = getotherhandnocloseSql(req, form);
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
			
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);
		       
	
				return new ModelAndView("report/otherhandnoclose", "form", form);
			}
			
			 //其它未关闭交接单统计导出excel
			private void otherhandnocloseToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getotherhandnocloseSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("其它未关闭交接单统计");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"交接单号", "创建日期", "单据状态", "入库件数", "入库重量", "单据来源"});
				//添加列name
				excelData.setColumnName(new String[]{"BILLNO", "DODATE", "STATE", "INIVNUM", "INIVWEI", "SRC_BILLNAME"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			
			private StringBuffer getorderstyleSql(HttpServletRequest req, ReportForm form) {
				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId", "itemClassId","ornaClassId", "analysisId", "OrgId");
				form.setCondition(condition);
				
				StringBuffer sql = new StringBuffer("select ic.article_type_id,se.itemclassid,se.ornaclassid,se.analysis_arange_id,ic.item_class_dsc,oc.orna_class_dsc,aa.ANALYSIS_ARANGE_CODE,si.item_class_name,pc.CYCLE_TYPE_CODE,count(1) stylenum ");
				sql.append("  from (select distinct  t.STYLEID,t.ANALYSIS_ARANGE_ID,t.ITEMCLASSID,t.ORNACLASSID,t.STYLEITEMCLASS,t.STYLEMIDDLECLASS,t.STYLEORNACLASS,t.PERIOD from jas_sum_pl_article_set t )  se,jas_bd_item_class ic,jas_bd_orna_class  oc,jas_bd_analysis_arange  aa,jas_bd_style_item_class si,jas_pl_cycle_type   pc where se.itemclassid = ic.item_class_id  and se.ornaclassid = oc.orna_class_id" );
				sql.append("  and se.analysis_arange_id = aa.analysis_arange_id   and se.styleitemclass = si.item_class_id and se.period=pc.CYCLE_TYPE_ID");
			

				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    }  
				//商品类别
			    if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	  sql.append(" and ic.article_type_id = '" + condition.get("articleTypeId") + "'");
				}
			    //大类
			   if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
				   sql.append(" and se.itemclassid  = '" + condition.get("itemClassId") + "'");
				}
			   //小类
			  if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
				  sql.append(" and se.ornaclassid  = '" + condition.get("ornaClassId") + "'");
			  }
		
			  //分析范围
			  if(StringUtil.isNotEmpty(condition.get("analysisId"))){
				  sql.append(" and se.analysis_arange_id  = '" + condition.get("analysisId") + "'");
				}
			   //组织
		       if(StringUtil.isNotEmpty(condition.get("OrgId"))){
		    	   sql.append(" and exists  (select 1 from jas_PL_BD_STYLE_ORG PB where PB.STYLEID=SE.STYLEID AND  PB.org_id  in (" + condition.get("OrgId") + "))");
				}
		       
		   	   sql.append(" group by ic.article_type_id,se.itemclassid,se.ornaclassid,se.analysis_arange_id,ic.item_class_dsc,oc.orna_class_dsc,aa.ANALYSIS_ARANGE_CODE, si.item_class_name,pc.CYCLE_TYPE_CODE");			
			
		       sql.append(" order by se.itemclassid,se.ornaclassid");
		       
		       
				return sql;
			}
			//下单款式查询
			private ModelAndView orderstyle(HttpServletRequest req, ReportForm form) throws Exception{
				
				StringBuffer sql = getorderstyleSql(req, form);
		  	        
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);
		
				
		
				return new ModelAndView("report/orderstyle", "form", form);
			}
			
			private void orderstyleToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getorderstyleSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("下订款式查询");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围", "款式大类", "周期类型","款式数量"});
				//添加列name
				excelData.setColumnName(new String[]{"ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE", "ITEM_CLASS_NAME","CYCLE_TYPE_CODE", "STYLENUM"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			
			private StringBuffer getstyleyesanalySql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","ornaClassId","analysisId", "startDate", "endDate", "isMotif");
				form.setCondition(condition);
				
				String startDate="";
				String endDate="";
				//时间
			    if(StringUtil.isNotEmpty(condition.get("startDate"))){
			    	startDate=condition.get("startDate");
				}else {
					startDate="2010-01-01";
				}
			    if(StringUtil.isNotEmpty(condition.get("endDate"))){
			    	endDate=condition.get("endDate");
				}else {
					endDate="2110-01-01";
				}
				String wsql="";
				String asql="  and exists (select 1 from ic_mater_active ic   where ss.styleid = ic.style_id and ss.alaysis_id=ic.alaysis_id)";
				//商品类别
			    if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			   	    asql+=" and ss.article_type_id = '" + condition.get("articleTypeId") + "'";
			    	wsql+=" and bb.article_type_id = '" + condition.get("articleTypeId") + "'";
				}	
			    //大类
			   if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
				   asql+=" and ss.item_class_id  = '" + condition.get("itemClassId") + "'";
				   wsql+=" and bb.item_class_id  = '" + condition.get("itemClassId") + "'";
				}
			   //小类
			  if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
				  asql+=" and ss.orna_class_id  = '" + condition.get("ornaClassId") + "'";
				  wsql+=" and bb.orna_class_id  = '" + condition.get("ornaClassId") + "'";
			   }
			  //比较范围
				if(StringUtil.isNotEmpty(condition.get("isMotif"))){
					asql+=" and ss.ismotif  = '" + condition.get("isMotif") + "'";
					wsql+=" and bb.ismotif  = '" + condition.get("isMotif") + "'";
				} 
			 //分析范围
			   if(StringUtil.isNotEmpty(condition.get("analysisId"))){
				   asql+=" and ss.alaysis_id  = '" + condition.get("analysisId") + "'";
				   wsql+=" and aa.analysis_arange_id  = '" + condition.get("analysisId") + "'";
				}
			
			
				StringBuffer sql = new StringBuffer("select aa.analysis_arange_id,aa.ANALYSIS_ARANGE_CODE,bb.*,m1.storenum,nvl(s1.salenum,0) salenum,  ");
				sql.append("(select sum(ss.sale_style)  from JAV_REPORT_SALE_STYLE ss where  ss.trans_date >= '"+startDate+"' and ss.trans_date <= '"+endDate+"' "+asql+") allsalenum");
				sql.append(" from (select mc.alaysis_id,mc.style_id,count(1)  storenum from ic_mater_active mc group by mc.style_id,mc.alaysis_id ) m1  ");
				sql.append(" inner join jas_bd_analysis_arange aa on aa.ANALYSIS_ARANGE_ID = m1.alaysis_id ");
				sql.append(" inner join  jav_report_style_noanaly bb on m1.style_id=bb.styleid ");
				sql.append(" left join (select alaysis_id,styleid,sum(ss.sale_style) salenum from JAV_REPORT_SALE_STYLE ss where  ss.trans_date >= '"+startDate+"'  and ss.trans_date <= '"+endDate+"' group by styleid,alaysis_id) s1 on s1.styleid = bb.styleid and  s1.alaysis_id = aa.analysis_arange_id ");
			    sql.append("  where  m1.STORENUM>0 ");
			
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				//默认不显示数据
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    } 
			    sql.append(wsql); 
			       
		
				sql.append(" order by nvl(s1.salenum,0) ");
				
				return sql;
			}
			//款式带分析范围
			private ModelAndView styleyesanaly(HttpServletRequest req, ReportForm form) throws Exception{
			

				StringBuffer sql = getstyleyesanalySql(req, form);   
	
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				String sum_sql="select sum(STORENUM)as SUM_STORENUM,sum(SALENUM) as SUM_SALENUM from ("+sql.toString()+" ) ";
				List<Map> listsum = reportManager.executeQurey(sum_sql);				
				form.setListSum(listsum);
				
				
				form.setList(list);
				form.setTotalCount(totalCount);
	
				return new ModelAndView("report/styleyesanaly", "form", form);
			}
			
			private void styleyesanalyToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getstyleyesanalySql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("款式分析--带分析范围");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类", "款式", "创建时间", "主题名称", "是否封存", "是否淘汰", "是否常规下单", "周期类型", "款式等级", "库存件数", "销售件数", "总销售件数"});
				//添加列name
				excelData.setColumnName(new String[]{"ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE", "ITEM_CLASS_NAME", "MIDDLE_CLASS_NAME", "ORNA_CLASS_NAME", "STYLENAME", "CREATEDATE", "THEME_NAME", "ISARCHIVE", "ISOUT", "ISGENERAL","CYCLE_TYPE_CODE", "ARTICLE_LEVEL_NAME", "STORENUM", "SALENUM", "ALLSALENUM"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			
			private StringBuffer getstylenoanalySql(HttpServletRequest req, ReportForm form) {
				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","ornaClassId", "startDate", "endDate", "isMotif");
				form.setCondition(condition);
				String startDate="";
				String endDate="";
				//时间
			    if(StringUtil.isNotEmpty(condition.get("startDate"))){
			    	startDate=condition.get("startDate");
				}else {
					startDate="2010-01-01";
				}
			    if(StringUtil.isNotEmpty(condition.get("endDate"))){
			    	endDate=condition.get("endDate");
				}else {
					endDate="2110-01-01";
				}
				String wsql="";
				String asql="  and exists (select 1 from ic_mater_active ic   where ss.styleid = ic.style_id)";
				//商品类别
			    if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	asql+=" and ss.article_type_id = '" + condition.get("articleTypeId") + "'";
			    	wsql+=" and bb.article_type_id = '" + condition.get("articleTypeId") + "'";
				}	
			    //大类
			   if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
				   asql+=" and ss.item_class_id  = '" + condition.get("itemClassId") + "'";
				   wsql+=" and bb.item_class_id  = '" + condition.get("itemClassId") + "'";
				}
			   //小类
			  if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
				  asql+=" and ss.orna_class_id  = '" + condition.get("ornaClassId") + "'";
				  wsql+=" and bb.orna_class_id  = '" + condition.get("ornaClassId") + "'";
			   }
			  //比较范围
				if(StringUtil.isNotEmpty(condition.get("isMotif"))){
					asql+=" and ss.ismotif  = '" + condition.get("isMotif") + "'";
					wsql+=" and bb.ismotif  = '" + condition.get("isMotif") + "'";
				} 
					
				StringBuffer sql = new StringBuffer("select bb.*,m1.storenum,nvl(s1.salenum,0) salenum,  ");
				sql.append("(select sum(ss.sale_style)  from JAV_REPORT_SALE_STYLE ss where  ss.trans_date >= '"+startDate+"' and ss.trans_date <= '"+endDate+"' "+asql+") allsalenum");
				sql.append(" from jav_report_style_noanaly bb ");
				sql.append(" inner join (select mc.style_id,count(1)  storenum from ic_mater_active mc group by mc.style_id ) m1 on m1.style_id=bb.styleid ");
				sql.append(" left join (select styleid,sum(ss.sale_style) salenum from JAV_REPORT_SALE_STYLE ss where  ss.trans_date >= '"+startDate+"'  and ss.trans_date <= '"+endDate+"' group by styleid) s1 on s1.styleid = bb.styleid  ");
				sql.append("  where  m1.STORENUM>0 ");

				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				//默认不显示数据，查询即显示
			     if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    sql.append(" and 1=0 ");   	       
			   	 } 
				
			    sql.append(wsql);  		
				
				sql.append(" order by nvl(s1.salenum,0)");
				
				return sql;
			}
			
			//款式不带分析范围
			private ModelAndView stylenoanaly(HttpServletRequest req, ReportForm form) throws Exception{
				
				StringBuffer sql = getstylenoanalySql(req, form);
		
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);
				
				String sum_sql="select sum(STORENUM)as SUM_STORENUM,sum(SALENUM) as SUM_SALENUM from ("+sql.toString()+" ) ";
				List<Map> listsum = reportManager.executeQurey(sum_sql);				
				form.setListSum(listsum);
			
				return new ModelAndView("report/stylenoanaly", "form", form);
			}
			
			private void stylenoanalyToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getstylenoanalySql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("款式分析--不带分析范围");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"大类", "小类",  "款式大类", "款式中类", "款式小类", "款式", "创建时间", "主题名称", "是否封存", "是否淘汰", "是否常规下单", "周期类型", "款式等级", "库存件数", "销售件数", "总销售件数"});
				//添加列name
				excelData.setColumnName(new String[]{"ITEM_CLASS_DSC", "ORNA_CLASS_DSC",  "ITEM_CLASS_NAME", "MIDDLE_CLASS_NAME", "ORNA_CLASS_NAME", "STYLENAME", "CREATEDATE", "THEME_NAME", "ISARCHIVE", "ISOUT","ISGENERAL", "CYCLE_TYPE_CODE", "ARTICLE_LEVEL_NAME", "STORENUM", "SALENUM", "ALLSALENUM"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			private StringBuffer getornaflowrecordSql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "ornaNo");
				form.setCondition(condition);
						
				StringBuffer sql = new StringBuffer("select * from jav_report_orna_trans  where 1=1");
				
			    //编码
			   if(StringUtil.isNotEmpty(condition.get("ornaNo"))){
				    
					sql.append(" and orna_code  = '" + condition.get("ornaNo") + "'");
				} else {
					sql.append(" and 1=0 ");
					
				}	
			    
		        //order by 
		        sql.append("  order by trans_id ");
	
			   
				return sql;
			}
			//饰品流转记录
			private ModelAndView ornaflowrecord(HttpServletRequest req, ReportForm form) throws Exception{			
				
				StringBuffer sql = getornaflowrecordSql(req, form);
				List<Map> list = reportManager.executeQurey(sql.toString());
				form.setList(list);
				return new ModelAndView("report/ornaflowrecord", "form", form);
			}
			
			private void ornaflowrecordToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getornaflowrecordSql(req, form);
				List<Map> dataList = reportManager.executeQurey(sql.toString());
				//Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("饰品流转记录查询");		
				excelData.setDataList(dataList);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"组织","仓库", "业务类型",  "单据类型", "单据编号", "业务日期", "创建日期", "商品编码","事务数量", "大类", "小类", "款式名称"});
				//添加列name
				excelData.setColumnName(new String[]{"ORG_NAME","STOCK", "TRANS_FLAG",  "BILL_NAME", "TRANS_SOURCE_NO", "TRANS_DATE", "CREATE_DATE", "ORNA_CODE","TRANS_QTY", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "STYLENAME"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			private StringBuffer getsalegroupSql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","UserId","OrgId","articleTypeId","articleTypeId","itemClassId","ornaClassId","analysisId","styleItemClassId","styleMiddleClassId","styleOrnaClassId","styleId","themeid","diagrade","maincolorgradeid","cleanid","shapeid","startDate","endDate","handoverno","transFlag","sid");
				form.setCondition(condition);
				
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				StringBuffer sql = new StringBuffer("select ORG_NAME,ITEM_CLASS_DSC,ORNA_CLASS_DSC,ANALYSIS_ARANGE_CODE,PINGZ,QUALITY_DSC,TRANS_NAME,sum(decode(trans_flag, 0, 1, 1, -1)) as TRANS_NUM,sum(total_weight*decode(trans_flag, 0, 1, 1, -1)) as TRANS_QTY,sum(RECEIVED_AMOUNT) as RECEIVED_AMOUNT  from jav_report_sale_list where 1=1 ");
				
				//只能查询有权限的组织数据
				sql.append(" and exists (select 1 from jas_bd_emp_org  where empid='"+condition.get("UserId")+"' and orgid=org_id) "); 
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    } 
				
				  //组织,必选项否则不查询数据
			       if(StringUtil.isNotEmpty(condition.get("OrgId"))){			    	    
						sql.append(" and org_id  in (" + condition.get("OrgId") + ")");
					} 
			   
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
						sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	   
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //小类
			       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			    	   
						sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
					}
			       //分析范围
			       if(StringUtil.isNotEmpty(condition.get("analysisId"))){
			    	   
						sql.append(" and alaysis_id   = '" + condition.get("analysisId") + "'");
					}
			        //款式大类
			       if(StringUtil.isNotEmpty(condition.get("styleItemClassId"))){
			    	   
						sql.append(" and styleitemclass  = '" + condition.get("styleItemClassId") + "'");
					}
			        //款式中类
			       if(StringUtil.isNotEmpty(condition.get("styleMiddleClassId"))){
			    	   
						sql.append(" and stylemiddleclass  = '" + condition.get("styleMiddleClassId") + "'");
					}
			       //款式小类
			       if(StringUtil.isNotEmpty(condition.get("styleOrnaClassId"))){
			    	   
						sql.append(" and styleornaclass = '" + condition.get("styleOrnaClassId") + "'");
					}      
			       //款式
			       if(StringUtil.isNotEmpty(condition.get("styleId"))){
			    	   
						sql.append(" and styleid = '" + condition.get("styleId") + "'");
					}       
			       //主题名称
			       if(StringUtil.isNotEmpty(condition.get("themeid"))){
			    	   
						sql.append(" and theme_id = '" + condition.get("themeid") + "'");
					}       
			       //钻石品质
			       if(StringUtil.isNotEmpty(condition.get("diagrade"))){
			    	   
						sql.append(" and  pingz = '" + condition.get("diagrade") + "'");
					}
			       //钻石色级
			       if(StringUtil.isNotEmpty(condition.get("maincolorgradeid"))){
			    	   
						sql.append(" and main_color_grade_id  = '" + condition.get("maincolorgradeid") + "'");
					}
			       //钻石净度
			       if(StringUtil.isNotEmpty(condition.get("cleanid"))){
			    	   
						sql.append(" and clean_id = '" + condition.get("cleanid") + "'");
					}
			       //钻石形状
			       if(StringUtil.isNotEmpty(condition.get("shapeid"))){
			    	   
						sql.append(" and main_shape_id = '" + condition.get("shapeid") + "'");
					}
			       //交接单号
			       if(StringUtil.isNotEmpty(condition.get("handoverno"))){
			    	   
						sql.append(" and exists (select hh.billid from jat_pur_handover_head hh  where hh.billid=handover_bill_id  and  hh.billno='" + condition.get("handoverno") + "'  )");
					}
			       
			 	  
				     //时间始
				    if(StringUtil.isNotEmpty(condition.get("startDate"))){
				    	 sql.append(" and trans_date>= '" + condition.get("startDate") +"'");
					}
				    //时间止
				    if(StringUtil.isNotEmpty(condition.get("endDate"))){
				    	 sql.append(" and trans_date<='"+condition.get("endDate")+"'");
					}
				
			   
			       //销售退回
					if(StringUtil.isNotEmpty(condition.get("transFlag"))){
						
						sql.append(" and trans_flag  = '" + condition.get("transFlag") + "'");
					} 
					
		
			    
		        
				sql.append("   group by ORG_NAME,ITEM_CLASS_DSC,ORNA_CLASS_DSC,ANALYSIS_ARANGE_CODE,PINGZ,QUALITY_DSC,TRANS_NAME order by org_name,item_class_dsc,orna_class_dsc ");  
				
				return sql;
			}
			//销售汇总查询
			private ModelAndView salegroup(HttpServletRequest req, ReportForm form) throws Exception{
				
				StringBuffer sql = getsalegroupSql(req, form);
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);	
			
				String sum_sql="select sum(TRANS_NUM) as SUM_TRANS_NUM,sum(TRANS_QTY) as SUM_TRANS_QTY,SUM(RECEIVED_AMOUNT) AS SUM_RECEIVED_AMOUNT from ("+sql.toString()+" ) ";
				List<Map> listsum = reportManager.executeQurey(sum_sql);				
				form.setListSum(listsum);
				
				return new ModelAndView("report/salegroup", "form", form);
			}
			
			
			private void salegroupToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getsalegroupSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("销售汇总查询");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"组织","大类", "小类",  "分析范围", "钻石品质", "钻石材质", "销售/退回件数", "销售/退回重量", "销售/退回金额", "销售"});
				//添加列name
				excelData.setColumnName(new String[]{"ORG_NAME","ITEM_CLASS_DSC", "ORNA_CLASS_DSC",  "ANALYSIS_ARANGE_CODE", "PINGZ", "QUALITY_DSC", "TRANS_NUM", "TRANS_QTY", "RECEIVED_AMOUNT", "TRANS_NAME"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			
			
			private StringBuffer getsalelistSql(HttpServletRequest req, ReportForm form) {
				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","UserId","OrgId","articleTypeId","articleTypeId","itemClassId","ornaClassId","analysisId","styleItemClassId","styleMiddleClassId","styleOrnaClassId","styleId","themeid",
						"diagrade","maincolorgradeid","cleanid","shapeid","startDate","endDate","handoverno","transFlag","sid", "privateType", "saleno");
				form.setCondition(condition);

				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				StringBuffer sql = new StringBuffer(
						"select z.*,\n" +
								"       (select mi.iniv_cost\n" + 
								"          from jat_mater_iniv mi\n" + 
								"         where mi.orna_code = z.ORNA_CODE) iniv_cost\n" + 
								"  from jav_report_sale_list z where 1=1 ");

				//只能查询有权限的组织数据
				sql.append(" and exists (select 1 from jas_bd_emp_org  where empid='"+condition.get("UserId")+"' and orgid=org_id) "); 
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    } 
				  //组织,必选项否则不查询数据
			       if(StringUtil.isNotEmpty(condition.get("OrgId"))){
			    	    
						sql.append(" and org_id  in (" + condition.get("OrgId") + ")");
					} 
			   
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
						sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	   
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //小类
			       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			    	   
						sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
					}
			       //分析范围
			       if(StringUtil.isNotEmpty(condition.get("analysisId"))){
			    	   
						sql.append(" and alaysis_id   = '" + condition.get("analysisId") + "'");
					}
			        //款式大类
			       if(StringUtil.isNotEmpty(condition.get("styleItemClassId"))){
			    	   
						sql.append(" and styleitemclass  = '" + condition.get("styleItemClassId") + "'");
					}
			        //款式中类
			       if(StringUtil.isNotEmpty(condition.get("styleMiddleClassId"))){
			    	   
						sql.append(" and stylemiddleclass  = '" + condition.get("styleMiddleClassId") + "'");
					}
			       //款式小类
			       if(StringUtil.isNotEmpty(condition.get("styleOrnaClassId"))){
			    	   
						sql.append(" and styleornaclass = '" + condition.get("styleOrnaClassId") + "'");
					}      
			       //款式
			       if(StringUtil.isNotEmpty(condition.get("styleId"))){
			    	   
						sql.append(" and styleid = '" + condition.get("styleId") + "'");
					}       
			       //主题名称
			       if(StringUtil.isNotEmpty(condition.get("themeid"))){
			    	   
						sql.append(" and theme_id = '" + condition.get("themeid") + "'");
					}       
			       //钻石品质
			       if(StringUtil.isNotEmpty(condition.get("diagrade"))){
			    	   
						sql.append(" and  pingz = '" + condition.get("diagrade") + "'");
					}
			       //钻石色级
			       if(StringUtil.isNotEmpty(condition.get("maincolorgradeid"))){
			    	   
						sql.append(" and main_color_grade_id  = '" + condition.get("maincolorgradeid") + "'");
					}
			       //钻石净度
			       if(StringUtil.isNotEmpty(condition.get("cleanid"))){
			    	   
						sql.append(" and clean_id = '" + condition.get("cleanid") + "'");
					}
			       //钻石形状
			       if(StringUtil.isNotEmpty(condition.get("shapeid"))){
			    	   
						sql.append(" and main_shape_id = '" + condition.get("shapeid") + "'");
					}
			       //交接单号
			       if(StringUtil.isNotEmpty(condition.get("handoverno"))){
			    	   
						sql.append(" and exists (select hh.billid from jat_pur_handover_head hh  where hh.billid=handover_bill_id  and  hh.billno='" + condition.get("handoverno") + "'  )");
					}
			   			       
			       
				     //时间始
				    if(StringUtil.isNotEmpty(condition.get("startDate"))){
				    	 sql.append(" and trans_date>= '" + condition.get("startDate") +"'");
					}
				    //时间止
				    if(StringUtil.isNotEmpty(condition.get("endDate"))){
				    	 sql.append(" and trans_date<='"+condition.get("endDate")+"'");
					}
			   
			       //销售退回
					if(StringUtil.isNotEmpty(condition.get("transFlag"))){
						
						sql.append(" and trans_flag  = '" + condition.get("transFlag") + "'");
					} 
					// 是否高工艺
					if(StringUtil.isNotEmpty(condition.get("privateType"))){
						
						sql.append(" and private_type  = '" + condition.get("privateType") + "'");
					}
                    //销售单号
                    if(StringUtil.isNotEmpty(condition.get("saleno"))){

                        sql.append(" and saleno  = '" + condition.get("saleno") + "'");
                    }
                sql.append(" order by trans_date");
					
				
				return sql;
			}
			//销售明细查询
			private ModelAndView salelist(HttpServletRequest req, ReportForm form) throws Exception{				
				
			
			 	StringBuffer sql = getsalelistSql(req, form);
				
					String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
					List<Map> listc=reportManager.executeQurey(count_sql);
					String totalCount =listc.get(0).get("NUMS").toString();
			        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
					List<Map> list = reportManager.executeQurey(list_sql);
				
					
					
					String sum_sql="select sum(TOTAL_WEIGHT) as SUM_TOTAL_WEIGHT,sum(MAIN_WEIGHT) as SUM_MAIN_WEIGHT,SUM(PART_WEIGHT) AS SUM_PART_WEIGHT,SUM(RECEIVABLE_AMOUNT) AS SUM_RECEIVABLE_AMOUNT,SUM(RECEIVED_AMOUNT) AS SUM_RECEIVED_AMOUNT,SUM(REAL_TOTAL_COST) AS SUM_REAL_TOTAL_COST,SUM(SALE_NUM) AS SUM_SALE_NUM,SUM(BACK_NUM) AS SUM_BACK_NUM from ("+sql.toString()+" ) ";
					List<Map> listsum = reportManager.executeQurey(sum_sql);				
					form.setListSum(listsum);
					
					
					form.setList(list);
					form.setTotalCount(totalCount);
					String tofrom="";
					if (form.getReportType().equals("13"))
					   tofrom="report/salelist";
					else if (form.getReportType().equals("22"))
					   tofrom="report/salelistsp";
					return new ModelAndView(tofrom, "form", form);
			
			}
			
			
			private void salelistToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getsalelistSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("销售明细查询");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				if (form.getReportType().equals("13")){
					//添加列标题
				   excelData.setColumnTitle(new String[]{"销售","组织","大类", "小类",  "分析范围", "款式大类", "款式中类", "款式小类", "款式名称", "主题名称", "钻石品质", "材质", "颜色", "净度", "总重", "主石重", "配石重", "手寸", "入库日期", "到店日期", "销售方式", "标价", "特殊工费", "成交价", "折扣", "商品编码", "销售日期", "销售年月","销售件数","退回件数","销售单号","销售人员","价格属性组", "是否高工艺"});
				   //添加列name
				   excelData.setColumnName(new String[]{"TRANS_NAME","ORG_NAME","ITEM_CLASS_DSC", "ORNA_CLASS_DSC",  "ANALYSIS_ARANGE_CODE", "STYLEITEMNAME", "STYLEMIDDLENAME", "STYLEORNANAME", "STYLENAME", "THEME_NAME", "PINGZ", "QUALITY_DSC", "COLORGRADE","CLEANNAME",  "TOTAL_WEIGHT", "MAIN_WEIGHT", "PART_WEIGHT", "SIZE_NAME", "STORAGEDATE", "TOSHOPDATE", "UNITNAME", "RECEIVABLE_AMOUNT","SPECIAL_WORK_PRICE", "RECEIVED_AMOUNT", "DEDUCT_AMOUNT", "ORNA_CODE", "TRANS_DATE", "TRANS_YM", "SALE_NUM", "BACK_NUM","SALENO","SALEUSER","PRICE_ATTR_GROUP", "PRIVATE_TYPE"});
			    } else if (form.getReportType().equals("22")) {
			    	//添加列标题
					excelData.setColumnTitle(new String[]{"销售","组织","大类", "小类",  "分析范围", "款式大类", "款式中类", "款式小类", "款式名称", "主题名称", "钻石品质", "材质", "颜色", "净度", "总重", "主石重", "配石重", "手寸", "入库日期", "到店日期", "销售方式", "标价", "特殊工费", "成交价", "折扣", "商品编码", "暂估成本", "原成本" ,"供应商", "销售日期", "销售年月","销售件数","退回件数","销售单号","销售人员","价格属性组", "是否高工艺"});
					//添加列name
					excelData.setColumnName(new String[]{"TRANS_NAME","ORG_NAME","ITEM_CLASS_DSC", "ORNA_CLASS_DSC",  "ANALYSIS_ARANGE_CODE", "STYLEITEMNAME", "STYLEMIDDLENAME", "STYLEORNANAME", "STYLENAME", "THEME_NAME", "PINGZ", "QUALITY_DSC", "COLORGRADE","CLEANNAME",  "TOTAL_WEIGHT", "MAIN_WEIGHT", "PART_WEIGHT", "SIZE_NAME", "STORAGEDATE", "TOSHOPDATE", "UNITNAME", "RECEIVABLE_AMOUNT","SPECIAL_WORK_PRICE", "RECEIVED_AMOUNT", "DEDUCT_AMOUNT", "ORNA_CODE", "REAL_TOTAL_COST","INIV_COST","CUSTNAME","TRANS_DATE", "TRANS_YM","SALE_NUM", "BACK_NUM","SALENO","SALEUSER","PRICE_ATTR_GROUP", "PRIVATE_TYPE"});
			   }
				//设置对应的数据字典
				excelData.addDictDisplayColumns(new String[]{"PRIVATE_TYPE"}, new String[]{"yesorno"});
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			private StringBuffer getsaleconsiSql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","startDate","endDate","handoverno","vendorId", "factoryStyleId", "factoryOrnaCode");
				
			    form.setCondition(condition);
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));

				StringBuffer sql = new StringBuffer("select * FROM jav_report_sale_list WHERE NVL(IS_CONSIGN,0)=1 AND TRANS_FLAG=0  ");
				
				
				
				//默认不显示数据，查询即显示
               if(!StringUtil.isNotEmpty(condition.get("Finds"))){
                    sql.append(" and 1=0 ");
                }
               //时间始
                if(StringUtil.isNotEmpty(condition.get("startDate"))){
                     sql.append(" and trans_date>= '" + condition.get("startDate") +"'");
                }
                //时间止
                if(StringUtil.isNotEmpty(condition.get("endDate"))){
                     sql.append(" and trans_date<='"+condition.get("endDate")+"'");
                }


               //交接单号
               if(StringUtil.isNotEmpty(condition.get("handoverno"))){

                    sql.append(" and exists (select hh.billid from jat_pur_handover_head hh  where hh.billid=handover_bill_id  and  hh.billno='" + condition.get("handoverno") + "'  )");
                }
			    //供应商
			   if(StringUtil.isNotEmpty(condition.get("vendorId"))){
					sql.append(" and SUPPLIER_ID  = '" + condition.get("vendorId") + "'");
				}
                //工厂款号
                if(StringUtil.isNotEmpty(condition.get("factoryStyleId"))){
                    sql.append(" and FACTORY_STYLE_ID  = '" + condition.get("factoryStyleId") + "'");
                }
                //工厂编码
                if(StringUtil.isNotEmpty(condition.get("factoryOrnaCode"))){
                    sql.append(" and FACTORY_ORNA_CODE  = '" + condition.get("factoryOrnaCode") + "'");
                }
			   sql.append("  order by trans_date ");
				return sql;
			}
			
			//代销明细查询
			private ModelAndView saleconsi(HttpServletRequest req, ReportForm form) throws Exception{
				
				StringBuffer sql = getsaleconsiSql(req, form);
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				String sum_sql="select sum(TOTAL_WEIGHT) as SUM_TOTAL_WEIGHT,sum(MAIN_WEIGHT) as SUM_MAIN_WEIGHT,SUM(PART_WEIGHT) AS SUM_PART_WEIGHT,SUM(RECEIVABLE_AMOUNT) AS SUM_RECEIVABLE_AMOUNT,SUM(RECEIVED_AMOUNT) AS SUM_RECEIVED_AMOUNT,SUM(REAL_TOTAL_COST) AS SUM_REAL_TOTAL_COST from ("+sql.toString()+" ) ";
				List<Map> listsum = reportManager.executeQurey(sum_sql);				
				form.setListSum(listsum);
				
				form.setList(list);
				form.setTotalCount(totalCount);
			
				return new ModelAndView("report/saleconsi", "form", form);
			}
			
			private void saleconsiToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getsaleconsiSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("代销明细查询");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"供应商","组织","大类", "小类",  "分析范围", "款式大类", "款式中类", "款式小类", "款式名称", "主题名称", "钻石品质", "材质", "颜色", "净度", "总重", "主石重", "配石重", "手寸", "入库日期", "到店日期", "销售方式", "标价", "成交价", "折扣", "商品编码", "销售日期", "销售年月", "工厂款号", "工厂编码"});
				//添加列name
				excelData.setColumnName(new String[]{"CUSTNAME","ORG_NAME","ITEM_CLASS_DSC", "ORNA_CLASS_DSC",  "ANALYSIS_ARANGE_CODE", "STYLEITEMNAME", "STYLEMIDDLENAME", "STYLEORNANAME", "STYLENAME", "THEME_NAME", "PINGZ", "QUALITY_DSC","COLORGRADE", "CLEANNAME",  "TOTAL_WEIGHT","MAIN_WEIGHT", "PART_WEIGHT", "SIZE_NAME", "STORAGEDATE", "TOSHOPDATE", "UNITNAME", "RECEIVABLE_AMOUNT", "RECEIVED_AMOUNT", "DEDUCT_AMOUNT", "ORNA_CODE", "TRANS_DATE", "TRANS_YM", "FACTORY_STYLE_ID", "FACTORY_ORNA_CODE"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			

			private StringBuffer getsaleconsiSqlcb(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","startDate","endDate","handoverno","vendorId", "costStart", "costEnd", "factoryStyleId", "factoryOrnaCode");
				
			    form.setCondition(condition);
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));

				StringBuffer sql = new StringBuffer("select s.*, round( decode(s.UNITNAME,'件',s.REAL_TOTAL_COST,s.REAL_TOTAL_COST/s.TRANS_QTY), 6 ) TRANS_QTY1 FROM jav_report_sale_list s WHERE NVL(s.IS_CONSIGN,0)=1 AND s.TRANS_FLAG=0  ");
				
				
				
				//默认不显示数据，查询即显示
               if(!StringUtil.isNotEmpty(condition.get("Finds"))){
                    sql.append(" and 1=0 ");
                }
               //时间始
                if(StringUtil.isNotEmpty(condition.get("startDate"))){
                     sql.append(" and s.trans_date>= '" + condition.get("startDate") +"'");
                }
                //时间止
                if(StringUtil.isNotEmpty(condition.get("endDate"))){
                     sql.append(" and s.trans_date<='"+condition.get("endDate")+"'");
                }


               //交接单号
               if(StringUtil.isNotEmpty(condition.get("handoverno"))){

                    sql.append(" and exists (select hh.billid from jat_pur_handover_head hh  where hh.billid=s.handover_bill_id  and  hh.billno='" + condition.get("handoverno") + "'  )");
                }
			    //供应商
			   if(StringUtil.isNotEmpty(condition.get("vendorId"))){
					sql.append(" and s.SUPPLIER_ID  = '" + condition.get("vendorId") + "'");
				}
                //工厂款号
                if(StringUtil.isNotEmpty(condition.get("factoryStyleId"))){
                    sql.append(" and s.FACTORY_STYLE_ID  = '" + condition.get("factoryStyleId") + "'");
                }
                //工厂编码
                if(StringUtil.isNotEmpty(condition.get("factoryOrnaCode"))){
                    sql.append(" and s.FACTORY_ORNA_CODE  = '" + condition.get("factoryOrnaCode") + "'");
                }
                //成本始
                if(StringUtil.isNotEmpty(condition.get("costStart"))){
                    sql.append(" and s.REAL_TOTAL_COST  >= " + condition.get("costStart") + "");
                }
                //成本止
                if(StringUtil.isNotEmpty(condition.get("costEnd"))){
                    sql.append(" and s.REAL_TOTAL_COST  <= " + condition.get("costEnd") + "");
                }
			   sql.append("  order by s.trans_date ");
				return sql;
			}
			
			//代销明细查询
			private ModelAndView saleconsicb(HttpServletRequest req, ReportForm form) throws Exception{
				
				StringBuffer sql = getsaleconsiSqlcb(req, form);
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				String sum_sql="select sum(TOTAL_WEIGHT) as SUM_TOTAL_WEIGHT,sum(MAIN_WEIGHT) as SUM_MAIN_WEIGHT,SUM(PART_WEIGHT) AS SUM_PART_WEIGHT,SUM(RECEIVABLE_AMOUNT) AS SUM_RECEIVABLE_AMOUNT,SUM(RECEIVED_AMOUNT) AS SUM_RECEIVED_AMOUNT,SUM(REAL_TOTAL_COST) AS SUM_REAL_TOTAL_COST, " +
						" sum(REAL_TOTAL_COST) as SUM_REAL_TOTAL_COST, sum(TRANS_QTY1) as SUM_TRANS_QTY1 from ("+sql.toString()+" ) ";
				List<Map> listsum = reportManager.executeQurey(sum_sql);				
				form.setListSum(listsum);
				
				form.setList(list);
				form.setTotalCount(totalCount);
			
				return new ModelAndView("report/saleconsicb", "form", form);
			}
			
			private void saleconsiToExcelcb(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getsaleconsiSqlcb(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("代销明细查询(含成本)");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"供应商","组织","大类", "小类",  "分析范围", "款式大类", "款式中类", "款式小类", "款式名称", "主题名称", "钻石品质", "材质", "颜色", "净度", "总重", "主石重", "配石重", "手寸", "入库日期", "到店日期", "销售方式", "标价", "成本金额", "成本单价", "成交价", "折扣", "商品编码", "销售日期", "销售年月", "工厂款号", "工厂编码"});
				//添加列name
				excelData.setColumnName(new String[]{"CUSTNAME","ORG_NAME","ITEM_CLASS_DSC", "ORNA_CLASS_DSC",  "ANALYSIS_ARANGE_CODE", "STYLEITEMNAME", "STYLEMIDDLENAME", "STYLEORNANAME", "STYLENAME", "THEME_NAME", "PINGZ", "QUALITY_DSC","COLORGRADE", "CLEANNAME",  "TOTAL_WEIGHT","MAIN_WEIGHT", "PART_WEIGHT", "SIZE_NAME", "STORAGEDATE", "TOSHOPDATE", "UNITNAME", "RECEIVABLE_AMOUNT","REAL_TOTAL_COST" ,"TRANS_QTY1", "RECEIVED_AMOUNT", "DEDUCT_AMOUNT", "ORNA_CODE", "TRANS_DATE", "TRANS_YM", "FACTORY_STYLE_ID", "FACTORY_ORNA_CODE"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			private StringBuffer getinivgroupSql(HttpServletRequest req, ReportForm form) {
				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","ornaClassId","startDate","endDate","vendorId");
				form.setCondition(condition);				
				

				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
				StringBuffer sql = new StringBuffer("select bc.custname,ai.article_type_dsc,ic.item_class_dsc,oc.orna_class_dsc,sum(mi.quantity) quantity,sum(mi.all_qty) all_qty,sum(mi.total_cost) total_cost");
				sql.append(" from jat_mater_iniv mi,jat_pur_handover_head hh,jas_bd_cust bc,jas_bd_articletype ai,jas_bd_item_class  ic,jas_bd_orna_class  oc   ");
				sql.append(" where mi.state = 11  and mi.handover_bill_id=hh.billid    and hh.source_type='CM'  and mi.vender_id = bc.custid   and mi.article_type_id = ai.article_type_id   and mi.item_class_id = ic.item_class_id   and mi.orna_class_id = oc.orna_class_id");
			
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    } 
				  
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
						sql.append(" and ic.article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	   
						sql.append(" and ic.item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //小类
			       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			    	   
						sql.append(" and oc.orna_class_id  = '" + condition.get("ornaClassId") + "'");
					}
			       
			       //时间始
				    if(StringUtil.isNotEmpty(condition.get("startDate"))){
				    	 sql.append(" and mi.iniv_date>= '" + condition.get("startDate") +"'");
					}
				    //时间止
				    if(StringUtil.isNotEmpty(condition.get("endDate"))){
				    	 sql.append(" and mi.iniv_date<='"+condition.get("endDate")+"'");
					}
				    
			    
			       
			       //供应商
				   if(StringUtil.isNotEmpty(condition.get("vendorId"))){
					   
						sql.append(" and mi.vender_id = '" + condition.get("vendorId") + "'");
					}
				   
	
				    
				    
		        //group by 
		        sql.append("   group by bc.custname, ai.article_type_dsc,ic.item_class_dsc,oc.orna_class_dsc  ");
		       //order by 
		        sql.append("  order by bc.custname, ai.article_type_dsc,ic.item_class_dsc, oc.orna_class_dsc ");
		        
				return sql;
			}
			//采购入库物料统计
			private ModelAndView inivgroup(HttpServletRequest req, ReportForm form) throws Exception{
				
				
				StringBuffer sql = getinivgroupSql(req, form);
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);
				
		
				return new ModelAndView("report/inivgroup", "form", form);
			}
			
			private void inivgroupToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getinivgroupSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("采购入库物料统计");		
				excelData.setPager(pager);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"供应商","商品类别","大类", "小类",  "数量", "重量", "采购成本"});
				//添加列name
				excelData.setColumnName(new String[]{"CUSTNAME","ARTICLE_TYPE_DSC","ITEM_CLASS_DSC", "ORNA_CLASS_DSC",  "QUANTITY", "ALL_QTY", "TOTAL_COST"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			private StringBuffer getpackagefindSql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","startDate","endDate","expressMode","doType","expressNo");
				form.setCondition(condition);
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				

				StringBuffer sql = new StringBuffer("select oo.org_name outorg_name,io.org_name inorg_name,bh.billno,bh.express_no,bh.sum_cost, bh.sum_count,es.item_value express_mode,decode(bh.bill_type,1, '饰品装箱', 2, '维修装箱', 3, '交接装箱')  dotype,su.empname cempname,bh.create_date,ru.empname rempname,bh.receive_date,hs.item_value status ");
				sql.append("  from jat_move_pack_head bh,jas_sm_org oo,jas_sm_org  io,jas_bd_emp   su,jas_bd_emp   ru, (select t.item_key,t.item_value from jat_sys_dict_item t where t.entry_code='status') hs,");
				sql.append(" (select t.item_key,t.item_value from jat_sys_dict_item t where t.entry_code='movepackexpressmode') es where  bh.out_org_id = oo.org_id   and bh.in_org_id = io.org_id");
				sql.append("  and bh.create_id=su.empid  and bh.receive_emp_id=ru.empid   and bh.status=hs.item_key   and bh.express_mode=es.item_key");
				
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    } 
				  
				  
			    if(StringUtil.isNotEmpty(condition.get("startDate"))){
			    	   
						sql.append(" and substr(bh.create_date,1,10)>= '" + condition.get("startDate")+"'");
					}
			    
			    if(StringUtil.isNotEmpty(condition.get("endDate"))){
			    	   
					sql.append(" and substr(bh.create_date,1,10)<='"+condition.get("endDate")+"'");
				}
			    //发送方式
			   if(StringUtil.isNotEmpty(condition.get("expressMode"))){
				   
					sql.append(" and bh.express_mode  = '" + condition.get("expressMode") + "'");
				}
			  //单据类型
			   if(StringUtil.isNotEmpty(condition.get("doType"))){
				   
					sql.append(" and bh.bill_type  = '" + condition.get("doType") + "'");
				}
			 //快递单号
			   if(StringUtil.isNotEmpty(condition.get("expressNo"))){
				   
					sql.append(" and bh.express_no = '" + condition.get("expressNo") + "'");
				}
		        
			   sql.append(" order by bh.create_date desc");
			   
				return sql;
			}
			//装箱单查询
			private ModelAndView packagefind(HttpServletRequest req, ReportForm form) throws Exception{
				
				
				StringBuffer sql = getpackagefindSql(req, form);
		        
				String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);
				
				
				return new ModelAndView("report/packagefind", "form", form);
			}
			private void packagefindToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getpackagefindSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("装箱单查询");		
				excelData.setPager(pager);//直接利用分页pager对象
				excelData.addColumnWidth(new String[]{"OUTORG_NAME","INORG_NAME"}, new Integer[]{4,4});//宽度默认值为2
				//添加列标题
				excelData.setColumnTitle(new String[]{"调出组织","调入组织","单据编号", "快递单号",  "总成本", "总件数", "创建人", "创建日期", "接收人", "接收日期", "状态", "发运方式"});
				//添加列name
				excelData.setColumnName(new String[]{"OUTORG_NAME","INORG_NAME","BILLNO", "EXPRESS_NO",  "SUM_COST", "SUM_COUNT", "CEMPNAME", "CREATE_DATE", "REMPNAME", "RECEIVE_DATE", "STATUS", "EXPRESS_MODE"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			private StringBuffer getinventorySql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "ornaNo");
				form.setCondition(condition);
				
				StringBuffer sql = new StringBuffer("select * from jav_report_orna_inventory  where 1=1");
				
				
				
			    //编码
			   if(StringUtil.isNotEmpty(condition.get("ornaNo"))){
				   
					sql.append(" and orna_code  = '" + condition.get("ornaNo") + "'");
				} else {
					sql.append(" and 1=0 ");
					
				}
			   
		
		        //order by 
		        sql.append("  order by create_date ");
				
				
				return sql;
			}
			//饰品编码盘点信息查询
			private ModelAndView inventory(HttpServletRequest req, ReportForm form) throws Exception{
				
		        
				StringBuffer sql = getinventorySql(req, form);
				List<Map> list = reportManager.executeQurey(sql.toString());
				form.setList(list);
				return new ModelAndView("report/inventory", "form", form);
			}
			
			private void inventoryToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getinventorySql(req, form);
				List<Map> dataList = reportManager.executeQurey(sql.toString());
				//Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("饰品编码盘点信息查询");		
				excelData.setDataList(dataList);//直接利用分页pager对象
			
				//添加列标题
				excelData.setColumnTitle(new String[]{"饰品编码","组织", "盘点单号",  "大类", "小类", "计量单位", "重量", "金额", "盘点时间", "盘点人"});
				//添加列name
				excelData.setColumnName(new String[]{"ORNA_CODE","ORG_NAME", "BILLNO",  "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "UNITNAME", "ALL_QTY", "POS_MONEY", "CREATE_DATE", "EMPNAME"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			
			//交接入库信息统计
			@SuppressWarnings("unused")
			private ModelAndView handiniv(HttpServletRequest req, ReportForm form) throws Exception{
				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","vendorId","startDate","endDate","handoverno");
				form.setCondition(condition);
				
				StringBuffer sql = new StringBuffer("select * from jav_report_handiniv  where 1=1");
				
				
				
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    } 
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
						sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	   
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			    
			       //时间始
				    if(StringUtil.isNotEmpty(condition.get("startDate"))){
				    	 sql.append(" and dodate>= '" + condition.get("startDate") +"'");
					}
				    //时间止
				    if(StringUtil.isNotEmpty(condition.get("endDate"))){
				    	 sql.append(" and dodate<='"+condition.get("endDate")+"'");
					}
			   
			       
			       //供应商
				   if(StringUtil.isNotEmpty(condition.get("vendorId"))){
					   
						sql.append(" and verdor_id = '" + condition.get("vendorId") + "'");
					}
				   //交接单号
				   if(StringUtil.isNotEmpty(condition.get("handoverno"))){
					   
						sql.append(" and billno = '" + condition.get("handoverno") + "'");
					}
			
		        //order by 
		        sql.append("  order by DODATE ");
				List<Map> list = reportManager.executeQurey(sql.toString());
				form.setList(list);
				return new ModelAndView("report/handiniv", "form", form);
			}
			
			
			private StringBuffer getmatergroupSql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","ornaClassId","analysisId", "OrgId");
				form.setCondition(condition);
				
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));
				
                StringBuffer sql = new StringBuffer("select * from jav_report_mater_group  where 1=1");
				
				
            	
				//默认不显示数据，查询即显示
			       if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			   	    	sql.append(" and 1=0 ");   	       
			   	    } 

				//商品类别
			    if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
						sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
				}
			    //大类
			   if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
				   
					sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
				}
			   //小类
			  if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
				  
					sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
			  }
			  //分析范围
			  if(StringUtil.isNotEmpty(condition.get("analysisId"))){
				  
					sql.append(" and alaysis_id  = '" + condition.get("analysisId") + "'");
				}
		  		   
		       //组织
				if(StringUtil.isNotEmpty(condition.get("OrgId"))){
					
					sql.append(" and org_id  = '" + condition.get("OrgId") + "'");
				} 
				sql.append(" order by org_id,item_class_id,orna_class_id");
				
				
				return sql;
			}
			//饰品库数据
			private ModelAndView matergroup(HttpServletRequest req, ReportForm form) throws Exception{
				
				
				StringBuffer sql = getmatergroupSql(req, form);
		    	String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
				List<Map> listc=reportManager.executeQurey(count_sql);
				String totalCount =listc.get(0).get("NUMS").toString();
				
		        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
				List<Map> list = reportManager.executeQurey(list_sql);
			
				form.setList(list);
				form.setTotalCount(totalCount);
		
				
				return new ModelAndView("report/matergroup", "form", form);
			}
			
			private void matergroupToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

				StringBuffer sql = getmatergroupSql(req, form);
				//List<Map> dataList = reportManager.executeQurey(sql.toString());
				Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

				//----------------------------------------------------------导出excel开始---------------------------------------
				//excel数据对象
				ExcelData excelData = new ExcelData();
				excelData.setTitle("饰品库数据");		
				excelData.setPager(pager);//直接利用分页pager对象
				excelData.addColumnWidth(new String[]{"ORG_NAME"}, new Integer[]{4});//宽度默认值为2
				
				//添加列标题
				excelData.setColumnTitle(new String[]{"组织","大类","小类", "分析范围",  "件数", "重量", "金额"});
				//添加列name
				excelData.setColumnName(new String[]{"ORG_NAME","ITEM_CLASS_DSC","ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE",  "ALL_COUNT", "ALL_QTY", "POS_AMOUNT"});
			    
			
				ExportExcelUtil util = new ExportExcelUtil();
				util.setExcelData(excelData);//传入excel数据对象
				util.export(res);//需要传入HttpServletResponse，以便下载excel
				//----------------------------------------------------------导出excel结束---------------------------------------
			}
			
			
			
			
			//门店总单在途查询sql
			private StringBuffer getorderinwaySql(HttpServletRequest req, ReportForm form) {

				Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","OrgId","CycleTypeId","articleTypeId","itemClassId","startDate", "orderType");
				form.setCondition(condition);
				String tableName="";
				if (form.getReportType().equals("20")){
					tableName="jav_report_order_inway";
				}else if (form.getReportType().equals("29")){
					tableName="JAV_REPORT_ORDER_INWAY_DATA";
				}else if (form.getReportType().equals("39")){
                    tableName="jav_report_order_inway_pu";
                }
				
				StringBuffer sql = new StringBuffer("select * from "+tableName+"  where 1=1");
				
				form.setStart(condition.get("start"));
				form.setLimit(condition.get("limit"));

				//默认不显示数据，查询即显示
               if(!StringUtil.isNotEmpty(condition.get("Finds"))){
                    sql.append(" and 1=0 ");
                }
               //组织,必选项否则不查询数据
               if(!"39".equals(form.getReportType())) {
                   if(StringUtil.isNotEmpty(condition.get("OrgId"))){
                       sql.append(" and org_id  in (" + condition.get("OrgId") + ")");
                   }
               }
			   //周期类型
			    if(StringUtil.isNotEmpty(condition.get("CycleTypeId"))){
			    	 sql.append(" and CYCLE_TYPE_ID  = '" + condition.get("CycleTypeId") + "'");
				}
				//商品类别
			    if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
						sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
				}
			    //大类
			   if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
				   
					sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
				}
			   
			   
			   //订单结束日期
			   if(StringUtil.isNotEmpty(condition.get("startDate"))){
				   
					sql.append(" and ORDER_END_DATE  = '" + condition.get("startDate") + "'");
				}

                if(form.getReportType().equals("29")) {
                    if(StringUtil.isBlank(condition.get("orderType"))) {
                        condition.put("orderType", "HQ");
                    }
                    sql.append(" and SRC_BILL_CODE  = '" + condition.get("orderType") + "'");
                }
			   
			    sql.append(" order by org_id,item_class_dsc");  
			    
				return sql;
			}
			
			//门店总单在途查询
				private ModelAndView orderinway(HttpServletRequest req, ReportForm form) throws Exception{
				
			
					StringBuffer sql = getorderinwaySql(req, form);
					
					
			    	String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
					List<Map> listc=reportManager.executeQurey(count_sql);
					String totalCount =listc.get(0).get("NUMS").toString();
					
			        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
					List<Map> list = reportManager.executeQurey(list_sql);
				
					form.setList(list);
					form.setTotalCount(totalCount);				
					
					return new ModelAndView("report/orderinway", "form", form);
				}
				
				
			    //门店总单在途查询toexcel
				private void orderinwayToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

					StringBuffer sql = getorderinwaySql(req, form); 
					//List<Map> dataList = reportManager.executeQurey(sql.toString());
					Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

					//----------------------------------------------------------导出excel开始---------------------------------------
					//excel数据对象
					ExcelData excelData = new ExcelData();
					if (form.getReportType().equals("20")){
						excelData.setTitle("门店总单在途查询");	
					}else if (form.getReportType().equals("29")){
						excelData.setTitle("门店总单在途查询(截止昨天)");	
					}else if (form.getReportType().equals("39")){
                        excelData.setTitle("推式下单在途查询");
                    }
					excelData.setPager(pager);//直接利用分页pager
				
					//添加列标题
					excelData.setColumnTitle(new String[]{"组织", "周期类型", "购物结束日期", "大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类", "款式", "计量单位", "尺寸", "成色", "色级", "净度", "下单量", "在途量","分配未到店"});
					//添加列name  
					excelData.setColumnName(new String[]{"ORG_NAME", "CYCLE_TYPE_CODE","ORDER_END_DATE", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE", "STYLEITEMNAME", "STYLEMIDDLENAME", "STYLEORNANAME", "STYLENAME", "UNITNAME", "SIZENAME", "QUALITY_DSC", "COLORNAME", "GRADENAME", "ORDERNUM", "INWAY","NOGET"});
				    
				
					ExportExcelUtil util = new ExportExcelUtil();
					util.setExcelData(excelData);//传入excel数据对象
					util.export(res);//需要传入HttpServletResponse，以便下载excel
					//----------------------------------------------------------导出excel结束---------------------------------------
				}
				
				
				 //库存组织查询公用SQL
				private StringBuffer getMatersumSql(HttpServletRequest req, ReportForm form) {
					Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","OrgId", "stockid", "articleTypeId", "itemClassId");
					form.setCondition(condition);
					form.setStart(condition.get("start"));
					form.setLimit(condition.get("limit"));
				
					StringBuffer sql = new StringBuffer();
					
					sql.append("select * from jav_report_mater_check where 1=1  ");
			        
			       
			        //默认不显示数据，查询即显示
			        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			    	    	sql.append(" and 1=0 ");   	       
			    	 }
			        
			        //组织,必选项否则不查询数据
			       if(StringUtil.isNotEmpty(condition.get("OrgId"))){
			    	    
						sql.append(" and org_id  in (" + condition.get("OrgId") + ")");
					} 
			       //仓库
			       if(StringUtil.isNotEmpty(condition.get("stockid"))){
			    	    
						sql.append(" and stock_id  = '" + condition.get("stockid") + "'");
					}
			     
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
					   sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	    
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //小类
			       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			    	    
						sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
					}
			
			       sql.append(" order by org_id,stock_id,item_class_id,orna_class_id");
			        
					return sql;
				}
			    //库存汇总查询
				private ModelAndView Matersum(HttpServletRequest req, ReportForm form) throws Exception{
					StringBuffer sql = getMatersumSql(req, form);
				    
					String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
					List<Map> listc=reportManager.executeQurey(count_sql);
					String totalCount =listc.get(0).get("NUMS").toString();
				
				    String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
					List<Map> list = reportManager.executeQurey(list_sql);
						
					form.setList(list);
					form.setTotalCount(totalCount);	 
					
					String sum_sql="select sum(now_qty) totalqty,Sum(real_total_cost) totalcost,sum(trans_qty) transqty,Sum(trans_cost) transcost from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) ";
					List<Map> listsum = reportManager.executeQurey(sum_sql);				
					form.setListSum(listsum);
					
					
					
					return new ModelAndView("report/matersum", "form", form);
				}


				
			    //库存统计查询导出excel
				private void MatersumToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

					StringBuffer sql = getMatersumSql(req, form);
					//List<Map> dataList = reportManager.executeQurey(sql.toString());
					Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

					//----------------------------------------------------------导出excel开始---------------------------------------
					//excel数据对象
					ExcelData excelData = new ExcelData();
					excelData.setTitle("库存统计");		
					excelData.setPager(pager);//直接利用分页pager对象
					excelData.addColumnWidth(new String[]{"ORG_NAME"}, new Integer[]{4});//宽度默认值为2
					//添加列标题
					excelData.setColumnTitle(new String[]{"组织", "大类", "小类","库存数量", "库存金额","事务数量", "事务金额"});
					//添加列name
					excelData.setColumnName(new String[]{"ORG_NAME", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "NOW_QTY", "REAL_TOTAL_COST", "TRANS_QTY", "TRANS_COST"});
				    
				
					ExportExcelUtil util = new ExportExcelUtil();
					util.setExcelData(excelData);//传入excel数据对象
					util.export(res);//需要传入HttpServletResponse，以便下载excel
					//----------------------------------------------------------导出excel结束---------------------------------------
				}
				
				
				 //调拔查询公用SQL
				private StringBuffer getMovelistSql(HttpServletRequest req, ReportForm form) {
					Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","OutOrgId", "Outstockid","InOrgId", "Instockid", "articleTypeId", "itemClassId","ornaCode","startDate","endDate");
					form.setCondition(condition);
					form.setStart(condition.get("start"));
					form.setLimit(condition.get("limit"));
				
					StringBuffer sql = new StringBuffer();
					
					sql.append("select * from jav_report_move_list where 1=1  ");
			        
			       
			        //默认不显示数据，查询即显示
			        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			    	    	sql.append(" and 1=0 ");   	       
			    	 }
			        
			        //组织,必选项否则不查询数据
			       if(StringUtil.isNotEmpty(condition.get("OutOrgId"))){
			    	    
						sql.append(" and out_org_id  in (" + condition.get("OutOrgId") + ")");
					} 
			       //仓库
			       if(StringUtil.isNotEmpty(condition.get("Outstockid"))){
			    	    
						sql.append(" and out_stock_id  = '" + condition.get("Outstockid") + "'");
					}
			       //组织,必选项否则不查询数据
			       if(StringUtil.isNotEmpty(condition.get("InOrgId"))){
			    	    
						sql.append(" and in_org_id  in (" + condition.get("InOrgId") + ")");
					} 
			       //仓库
			       if(StringUtil.isNotEmpty(condition.get("Instockid"))){
			    	    
						sql.append(" and in_stock_id  = '" + condition.get("Instockid") + "'");
					}
			     
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
					   sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	    
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //饰品编码
			       if(StringUtil.isNotEmpty(condition.get("ornaCode"))){
			    	    
						sql.append(" and orna_code  = '" + condition.get("ornaCode") + "'");
					}
			       
			       //时间始
				    if(StringUtil.isNotEmpty(condition.get("startDate"))){
				    	 sql.append(" and dodate>= '" + condition.get("startDate") +"'");
					}
				    //时间止
				    if(StringUtil.isNotEmpty(condition.get("endDate"))){
				    	 sql.append(" and dodate<='"+condition.get("endDate")+"'");
					}
				    
			  
			
			       sql.append(" order by dodate desc");
			        
					return sql;
				}
			    //调拔查询
				private ModelAndView Movelist(HttpServletRequest req, ReportForm form) throws Exception{
					StringBuffer sql = getMovelistSql(req, form);
				    
					String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
					List<Map> listc=reportManager.executeQurey(count_sql);
					String totalCount =listc.get(0).get("NUMS").toString();
				
				    String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
					List<Map> list = reportManager.executeQurey(list_sql);
						
					form.setList(list);
					form.setTotalCount(totalCount);	 
					
					String sum_sql="select sum(all_weight) totalweight from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) ";
					List<Map> listsum = reportManager.executeQurey(sum_sql);				
					form.setListSum(listsum);
					
					
					
					return new ModelAndView("report/movelist", "form", form);
				}


				
			    //调拔查询导出excel
				private void MovelistToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

					StringBuffer sql = getMovelistSql(req, form);
					//List<Map> dataList = reportManager.executeQurey(sql.toString());
					Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

					//----------------------------------------------------------导出excel开始---------------------------------------
					//excel数据对象
					ExcelData excelData = new ExcelData();
					excelData.setTitle("调拔查询");		
					excelData.setPager(pager);//直接利用分页pager对象
					excelData.addColumnWidth(new String[]{"OUT_ORG_NAME"}, new Integer[]{4});//宽度默认值为2
					excelData.addColumnWidth(new String[]{"IN_ORG_NAME"}, new Integer[]{4});//宽度默认值为2
					//添加列标题
					excelData.setColumnTitle(new String[]{"调出组织", "调出仓库", "调入组织","调入仓库", "调拨单号", "饰品编码", "饰品名称", "大类", "小类", "重量", "状态"});
					//添加列name
					excelData.setColumnName(new String[]{"OUT_ORG_NAME", "OUT_STOCK_NAME","IN_ORG_NAME","IN_STOCK_NAME","BILLNO","ORNA_CODE","ORNA_DSC","ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ALL_WEIGHT", "STATUS"});
				    
				
					ExportExcelUtil util = new ExportExcelUtil();
					util.setExcelData(excelData);//传入excel数据对象
					util.export(res);//需要传入HttpServletResponse，以便下载excel
					//----------------------------------------------------------导出excel结束---------------------------------------
				}
				
				
				

				 //安全库存公用SQL
				private StringBuffer getSafeStockSql(HttpServletRequest req, ReportForm form) {
					Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","ornaClassId","analysisId", "styleId");
					form.setCondition(condition);
					form.setStart(condition.get("start"));
					form.setLimit(condition.get("limit"));
				
					StringBuffer sql = new StringBuffer();
					
					sql.append("select * from jav_report_stock_safe where 1=1  ");
			        
			       
			        //默认不显示数据，查询即显示
			        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			    	    	sql.append(" and 1=0 ");   	       
			    	 }
			  
			     
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
					   sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	    
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //小类
			       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			    	    
						sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
					}
			       //分析范围
			       if(StringUtil.isNotEmpty(condition.get("analysisId"))){
			    	    
						sql.append(" and alaysis_id  = '" + condition.get("analysisId") + "'");
					}
			       //款式
			       if(StringUtil.isNotEmpty(condition.get("styleId"))){
			    	    
						sql.append(" and style_id  = '" + condition.get("styleId") + "'");
					}
			  
			
			     
			        
					return sql;
				}
			
			    //安全库存
				private ModelAndView SafeStock(HttpServletRequest req, ReportForm form) throws Exception{
					StringBuffer sql = getSafeStockSql(req, form);
				    
					String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
					List<Map> listc=reportManager.executeQurey(count_sql);
					String totalCount =listc.get(0).get("NUMS").toString();
				
				    String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
					List<Map> list = reportManager.executeQurey(list_sql);
						
					form.setList(list);
					form.setTotalCount(totalCount);	 
					
					String sum_sql="select sum(safe_num) totalsafenum,sum(all_count) totalallcount from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) ";
					List<Map> listsum = reportManager.executeQurey(sum_sql);				
					form.setListSum(listsum);
					
					
					
					return new ModelAndView("report/SafeStock", "form", form);
				}


				
			    //安全库存导出excel
				private void SafeStockToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

					StringBuffer sql = getSafeStockSql(req, form);
					//List<Map> dataList = reportManager.executeQurey(sql.toString());
					Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

					//----------------------------------------------------------导出excel开始---------------------------------------
					//excel数据对象
					ExcelData excelData = new ExcelData();
					excelData.setTitle("安全库存");		
					excelData.setPager(pager);//直接利用分页pager对象
				
					//添加列标题
					excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围","款式", "库存标准", "现库存件数"});
					//添加列name
					excelData.setColumnName(new String[]{"ITEM_CLASS_DSC", "ORNA_CLASS_DSC","ANALYSIS_ARANGE_CODE","STYLENAME","SAFE_NUM","ALL_COUNT"});
				    
				
					ExportExcelUtil util = new ExportExcelUtil();
					util.setExcelData(excelData);//传入excel数据对象
					util.export(res);//需要传入HttpServletResponse，以便下载excel
					//----------------------------------------------------------导出excel结束---------------------------------------
				}
				

				
				 //核价查询公用SQL
				private StringBuffer getCalcListSql(HttpServletRequest req, ReportForm form) {
					Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","ornaClassId","startDate", "endDate");
					form.setCondition(condition);
					form.setStart(condition.get("start"));
					form.setLimit(condition.get("limit"));
				
					StringBuffer sql = new StringBuffer();
					
					sql.append("select * from jav_report_calclist where 1=1  ");
			        
			       
			        //默认不显示数据，查询即显示
			        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			    	    	sql.append(" and 1=0 ");   	       
			    	 }
			  
			     
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){			    	   
					   sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	    
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //小类
			       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			    	    
						sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
					}
			       //时间始
			       if(StringUtil.isNotEmpty(condition.get("startDate"))){
			    	    
						sql.append(" and createdate  >= '" + condition.get("startDate") + "'");
					}
			       //时间止
			       if(StringUtil.isNotEmpty(condition.get("endDate"))){
			    	    
						sql.append(" and createdate  <= '" + condition.get("endDate") + "'");
					}
			  
			
			       sql.append(" order by createdate desc, wl_code desc");
			        
					return sql;
				}
			    //核价查询
				private ModelAndView CalcList(HttpServletRequest req, ReportForm form) throws Exception{
					StringBuffer sql = getCalcListSql(req, form);
				    
					String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
					List<Map> listc=reportManager.executeQurey(count_sql);
					String totalCount =listc.get(0).get("NUMS").toString();
				
				    String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
					List<Map> list = reportManager.executeQurey(list_sql);
						
					form.setList(list);
					form.setTotalCount(totalCount);	 
					
					String sum_sql="select sum(all_weight) totalweight,sum(basic_price) totalprice,sum(ALL_WIDTH_TAX_PRICE) totaltaxprice from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) ";
					List<Map> listsum = reportManager.executeQurey(sum_sql);				
					form.setListSum(listsum);
					
					
					
					return new ModelAndView("report/CalcList", "form", form);
				}


				
			    //核价查询导出excel
				private void CalcListToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

					StringBuffer sql = getCalcListSql(req, form);
					//List<Map> dataList = reportManager.executeQurey(sql.toString());
					Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

					//----------------------------------------------------------导出excel开始---------------------------------------
					//excel数据对象
					ExcelData excelData = new ExcelData();
					excelData.setTitle("核价查询");		
					excelData.setPager(pager);//直接利用分页pager对象
				
					//添加列标题
					excelData.setColumnTitle(new String[]{"饰品编码","大类", "小类", "核价日期","主石重量", "总重量", "基础价","含税总成本","系数"});
					//添加列name
					excelData.setColumnName(new String[]{"WL_CODE","ITEM_CLASS_DSC", "ORNA_CLASS_DSC","CREATEDATE","MAIN_WEIGHT","ALL_WEIGHT","BASIC_PRICE","ALL_WIDTH_TAX_PRICE","COEF"});
				    
				
					ExportExcelUtil util = new ExportExcelUtil();
					util.setExcelData(excelData);//传入excel数据对象
					util.export(res);//需要传入HttpServletResponse，以便下载excel
					//----------------------------------------------------------导出excel结束---------------------------------------
				}
				
				 //裸石查询公用SQL
				private StringBuffer getLuosiListSql(HttpServletRequest req, ReportForm form) {
					Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds","articleTypeId","itemClassId","ornaClassId","analysisId", "styleId");
					form.setCondition(condition);
					form.setStart(condition.get("start"));
					form.setLimit(condition.get("limit"));
				
					StringBuffer sql = new StringBuffer();
					
					sql.append("select * from jav_report_stock_safe where 1=1  ");
			        
			       
			        //默认不显示数据，查询即显示
			        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
			    	    	sql.append(" and 1=0 ");   	       
			    	 }
			  
			     
			       //商品类别
			       if(StringUtil.isNotEmpty(condition.get("articleTypeId"))){
			    	   
					   sql.append(" and article_type_id = '" + condition.get("articleTypeId") + "'");
					}
			       //大类
			       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			    	    
						sql.append(" and item_class_id  = '" + condition.get("itemClassId") + "'");
					}
			       //小类
			       if(StringUtil.isNotEmpty(condition.get("ornaClassId"))){
			    	    
						sql.append(" and orna_class_id  = '" + condition.get("ornaClassId") + "'");
					}
			       //分析范围
			       if(StringUtil.isNotEmpty(condition.get("analysisId"))){
			    	    
						sql.append(" and alaysis_id  = '" + condition.get("analysisId") + "'");
					}
			       //款式
			       if(StringUtil.isNotEmpty(condition.get("styleId"))){
			    	    
						sql.append(" and style_id  = '" + condition.get("styleId") + "'");
					}
			  
			
			     
			        
					return sql;
				}
			    //裸石查询
				private ModelAndView LuosiList(HttpServletRequest req, ReportForm form) throws Exception{
					StringBuffer sql = getLuosiListSql(req, form);
				    
					String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
					List<Map> listc=reportManager.executeQurey(count_sql);
					String totalCount =listc.get(0).get("NUMS").toString();
				
				    String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
					List<Map> list = reportManager.executeQurey(list_sql);
						
					form.setList(list);
					form.setTotalCount(totalCount);	 
					
					String sum_sql="select sum(safe_num) totalsafenum, sum(all_count) totalallcount from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) ";
					List<Map> listsum = reportManager.executeQurey(sum_sql);				
					form.setListSum(listsum);
					
					
					
					return new ModelAndView("report/SafeStock", "form", form);
				}


				
			    //裸石查询导出excel
				private void LuosiListToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

					StringBuffer sql = getLuosiListSql(req, form);
					//List<Map> dataList = reportManager.executeQurey(sql.toString());
					Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

					//----------------------------------------------------------导出excel开始---------------------------------------
					//excel数据对象
					ExcelData excelData = new ExcelData();
					excelData.setTitle("安全库存");		
					excelData.setPager(pager);//直接利用分页pager对象
				
					//添加列标题
					excelData.setColumnTitle(new String[]{"大类", "小类", "分析范围","款式", "库存标准", "现库存件数"});
					//添加列name
					excelData.setColumnName(new String[]{"ITEM_CLASS_DSC", "ORNA_CLASS_DSC","ANALYSIS_ARANGE_CODE","STYLENAME","SAFE_NUM","ALL_COUNT"});
				    
				
					ExportExcelUtil util = new ExportExcelUtil();
					util.setExcelData(excelData);//传入excel数据对象
					util.export(res);//需要传入HttpServletResponse，以便下载excel
					//----------------------------------------------------------导出excel结束---------------------------------------
				}


	private StringBuffer getPurchaseAnalysisSql(HttpServletRequest req, ReportForm form) {
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds", "orgId", "itemClassId", "transDateStart", "transDateEnd");
		form.setCondition(condition);
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		StringBuffer sql = new StringBuffer();
		sql.append("select a.orna_code, a.org_name, a.item_class_dsc, a.orna_class_dsc, a.trans_date, " +
				"a.trans_qty, a.unitname, substr(a.storagedate, 1, 10) as storagedate," +
				"case when nvl(a.trans_qty, 0) = 0 then 0 else round(a.real_total_cost / a.trans_qty, 2) end as real_unit_cost, a.real_total_cost " +
				"from jav_report_sale_list a where  a.trans_flag = 0 ");
		//默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("Finds"))){ 
    	    	sql.append(" and 1=0 ");   	       
    	 }
        //组织,必选项否则不查询数据
       if(StringUtil.isNotEmpty(condition.get("orgId"))){
			sql.append(" and a.org_id  in (" + condition.get("orgId") + ")");
		}
       //大类
       if(StringUtil.isNotEmpty(condition.get("itemClassId"))){
			sql.append(" and a.item_class_id  in (" + condition.get("itemClassId") + ")");
		}
       
       if(StringUtil.isNotEmpty(condition.get("transDateStart"))){
			sql.append(" and substr(a.trans_date, 1, 10)  >= '" + condition.get("transDateStart") + "'");
		}
       if(StringUtil.isNotEmpty(condition.get("transDateEnd"))){
			sql.append(" and substr(a.trans_date, 1, 10)  <= '" + condition.get("transDateEnd") + "'");
		}
       sql.append(" order by a.org_name, a.item_class_dsc, a.orna_class_dsc, a.trans_date");
        
		return sql;
	}
			    
	private ModelAndView purchaseAnalysis(HttpServletRequest req, ReportForm form) throws Exception{
		StringBuffer sql = getPurchaseAnalysisSql(req, form);
		String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
		List<Map> listc=reportManager.executeQurey(count_sql);
		String totalCount =listc.get(0).get("NUMS").toString();
	
	    String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
		List<Map> list = reportManager.executeQurey(list_sql);
		
		form.setList(list);
		form.setTotalCount(totalCount);
		
		return new ModelAndView("report/purchaseAnalysis", "form", form);
	}
	
	private void purchaseAnalysisToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{

		StringBuffer sql = getPurchaseAnalysisSql(req, form);
		Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("库存统计");		
		excelData.setPager(pager);//直接利用分页pager对象
		excelData.addColumnWidth(new String[]{"ORG_NAME"}, new Integer[]{4});//宽度默认值为2
		//添加列标题
		excelData.setColumnTitle(new String[]{"饰品编码", "销售组织", "大类", "小类", "销售时间", "重量", "计量单位", "入库时间", "成本单价", "成本总额"});
		//添加列name
		excelData.setColumnName(new String[]{"ORNA_CODE", "ORG_NAME", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "TRANS_DATE", "TRANS_QTY", "UNITNAME", "STORAGEDATE", "REAL_UNIT_COST", "REAL_TOTAL_COST"});
	
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
		//----------------------------------------------------------导出excel结束---------------------------------------
	}
	
	private StringBuffer getWeiWaiDaiXiaoSql(HttpServletRequest req, ReportForm form) {
		StringBuffer sql = new StringBuffer("select * from jav_report_dai_xiao where 1=1");
		
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this,req,"firstFlag");
		form.setCondition(condition);
		 
		//默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("firstFlag"))){ 
    	    	sql.append(" and 1=0 ");   	    
    	 }
        sql.append(condition.get("_q_sql")==null?"":condition.get("_q_sql"));
        
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		
		//sql.append(" order by a.org_name, a.item_class_dsc, a.orna_class_dsc, a.trans_date");
		
		return sql;
	}
			    
	private ModelAndView weiWaiDaiXiao(HttpServletRequest req, ReportForm form) throws Exception{
		if("".equals(CommonUtil.getParameterEmpty(req, "firstFlag"))){
			form.setTotalCount("0");
			String userid = CommonUtil.getSessionUserId(req);
			form.setStart(Global.PAGE_DEFAULT_START);
			form.setLimit(Global.PAGE_DEFAULT_LIMIT);
	    	req.getSession().removeAttribute("query_condition_" + userid + "_CWXS");   
		}else{
			StringBuffer sql = getWeiWaiDaiXiaoSql(req, form);
			
			String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
			List<Map> listc=reportManager.executeQurey(count_sql);
			String totalCount =listc.get(0).get("NUMS").toString();
			List<Map> list = reportManager.executeQurey("select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit());
		
			form.setList(list);
			form.setTotalCount(totalCount);
		}
		String tofrom = "report/weiWaiDaiXiao";
		return new ModelAndView(tofrom, "form", form);
	}
	
	private void weiWaiDaiXiaoToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "");
		form.setCondition(condition);
		
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		StringBuffer sql = getWeiWaiDaiXiaoSql(req, form);
		Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("委外代销");		
		excelData.setPager(pager);//直接利用分页pager对象
	
	   //添加列标题
	   excelData.setColumnTitle(new String[]{"饰品编码","分析范围","品质","色级","净度","形状","颜色","供应商","大类","发料重量","发料粒数","核销粒数","退料粒数","剩余粒数"});
	   //添加列name
	   excelData.setColumnName(new String[]{"ORNA_CODE","ANALYSIS_ARANGE_CODE","CLEAN_GREAD_ID","MAIN_COLOR_GREAD_ID","CLEAN_ID",
			   "MAIN_SHAPE_ID","COLOR_ID","CUST_NAME","ITEM_CLASS_NAME","CHECK_WEIGHT",
			   "GRAINS","CHECK_NUMS","EXIT_NUMS","SHENG_NUMS"});
		
	
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
	}
	
	private StringBuffer getCaiWuWeiJieShuanSql(HttpServletRequest req, ReportForm form) {
		StringBuffer sql = new StringBuffer("select * from jav_report_cash_find where 1=1");
		
		Map<String, String> condition = CommonUtil.getConditionForPageSession(this,req,"firstFlag");
		form.setCondition(condition);
		 
		//默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("firstFlag"))){ 
    	    	sql.append(" and 1=0 ");   	    
    	 }
        sql.append(condition.get("_q_sql")==null?"":condition.get("_q_sql"));
        
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		
		//sql.append(" order by a.org_name, a.item_class_dsc, a.orna_class_dsc, a.trans_date");
		
		return sql;
	}
			    
	private ModelAndView caiWuWeiJieShuan(HttpServletRequest req, ReportForm form) throws Exception{
		if("".equals(CommonUtil.getParameterEmpty(req, "firstFlag"))){
			form.setTotalCount("0");
			String userid = CommonUtil.getSessionUserId(req);
			form.setStart(Global.PAGE_DEFAULT_START);
			form.setLimit(Global.PAGE_DEFAULT_LIMIT);
	    	req.getSession().removeAttribute("query_condition_" + userid + "_CWXS");   
		}else{
			StringBuffer sql = getCaiWuWeiJieShuanSql(req, form);
			
			String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
			List<Map> listc=reportManager.executeQurey(count_sql);
			String totalCount =listc.get(0).get("NUMS").toString();
			List<Map> list = reportManager.executeQurey("select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit());
		
			form.setList(list);
			form.setTotalCount(totalCount);
		}
		String tofrom = "report/caiWuWeiJieShuan";
		return new ModelAndView(tofrom, "form", form);
	}
	
	private void caiWuWeiJieShuanToExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{
		Map<String, String> condition = CommonUtil.getConditionForPage(req, "");
		form.setCondition(condition);
		
		form.setStart(condition.get("start"));
		form.setLimit(condition.get("limit"));
		StringBuffer sql = getCaiWuWeiJieShuanSql(req, form);
		Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

		//----------------------------------------------------------导出excel开始---------------------------------------
		//excel数据对象
		ExcelData excelData = new ExcelData();
		excelData.setTitle("未结算单据查询");		
		excelData.setPager(pager);//直接利用分页pager对象
	
	   //添加列标题
	   excelData.setColumnTitle(new String[]{"交接单未结算数量","委外发料未结算数量","对公销售未结算数量","退料单未结算数量","对公销售单未出票数量","交接单未收票数量"});
	   //添加列name
	   excelData.setColumnName(new String[]{"HAND_CASH","SEND_CASH","SALE_CASH","EXIT_CASH","OUT_CASH","IN_CASH"});
		
	
		ExportExcelUtil util = new ExportExcelUtil();
		util.setExcelData(excelData);//传入excel数据对象
		util.export(res);//需要传入HttpServletResponse，以便下载excel
	}

    // ----------------------------------------------------- 推式 ----------------------------------------------

    //门店满足查询sql
    private StringBuffer getorgmeetSql_pu(HttpServletRequest req, ReportForm form) {

        Map<String, String> condition = CommonUtil.getConditionForPage(req, "Finds", "CycleTypeId", "startDate", "endDate");
        form.setCondition(condition);

        StringBuffer sql = new StringBuffer("select demand_date,order_end_date,demand_nums,get_nums,round(get_nums/demand_nums,4)*100||'%' meets from jav_report_order_orgall_pu  where 1=1");

        form.setStart(condition.get("start"));
        form.setLimit(condition.get("limit"));

        //默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("Finds"))){
            sql.append(" and 1=0 ");
        }
        //周期类型
        if(StringUtil.isNotEmpty(condition.get("CycleTypeId"))){
            sql.append(" and CYCLE_TYPE_ID  = '" + condition.get("CycleTypeId") + "'");
        }
        //时间始
        if(StringUtil.isNotEmpty(condition.get("startDate"))){
            sql.append(" and order_end_date>= '" + condition.get("startDate") +"'");
        }
        //时间止
        if(StringUtil.isNotEmpty(condition.get("endDate"))){
            sql.append(" and order_end_date<='"+condition.get("endDate")+"'");
        }

        sql.append(" order by demand_date");

        return sql;
    }

    //门店满足查询
    private ModelAndView orgmeet_pu(HttpServletRequest req, ReportForm form) throws Exception{
        StringBuffer sql = getorgmeetSql_pu(req, form);

        String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
        List<Map> listc=reportManager.executeQurey(count_sql);
        String totalCount =listc.get(0).get("NUMS").toString();

        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
        List<Map> list = reportManager.executeQurey(list_sql);

        form.setList(list);
        form.setTotalCount(totalCount);

        String total_sql="select sum(get_nums) as get_nums, sum(demand_nums) as demand_nums, round(sum(get_nums/demand_nums) / count(1), 4) * 100 || '%' totalmeets  from  ("+sql.toString()+" )  ";

        List<Map> listtotal = reportManager.executeQurey(total_sql);
        form.setListTotal(listtotal);

        return new ModelAndView("report/orgmeet_pu", "form", form);
    }


    //门店满足查询导出excel
    private void orgmeetToExcel_pu(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{
        StringBuffer sql = getorgmeetSql_pu(req, form);
        //List<Map> dataList = reportManager.executeQurey(sql.toString());
        Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());

        //----------------------------------------------------------导出excel开始---------------------------------------
        //excel数据对象
        ExcelData excelData = new ExcelData();
        excelData.setTitle("门店满足查询");
        excelData.setPager(pager);//直接利用分页pager对象

        //添加列标题
        excelData.setColumnTitle(new String[]{"下单日期", "最后到货日期", "下单数量", "到货数量", "满足率"});
        //添加列name
        excelData.setColumnName(new String[]{"DEMAND_DATE", "ORDER_END_DATE", "DEMAND_NUMS", "GET_NUMS", "MEETS"});

        ExportExcelUtil util = new ExportExcelUtil();
        util.setExcelData(excelData);//传入excel数据对象
        util.export(res);//需要传入HttpServletResponse，以便下载excel
        //----------------------------------------------------------导出excel结束---------------------------------------
    }

    /** ==============================================================================
     *   --------------------------------------- 饰品调拨类型查询报表 开始 ---------------------------------------------
     *   ==============================================================================
     */

    private StringBuffer ornaMoveTypeSQL(HttpServletRequest req, ReportForm form) {
        String params[] = new String[] {"firstFlag"};
        Map<String, String> condition = CommonUtil.getConditionForPageSession(this, req, params);
        form.setCondition(condition);
        StringBuffer sql = new StringBuffer("SELECT mm.orna_code, ic.ITEM_CLASS_DSC, oc.ORNA_CLASS_DSC, aa.ANALYSIS_ARANGE_CODE, sic.ITEM_CLASS_NAME STYLEITEMCLASSNAME, " +
                "smc.MIDDLE_CLASS_NAME STYLEMIDDLECLASSNAME, soc.ORNA_CLASS_NAME STYLEORNACLASSNAME, to_char(ma.style_id) style_id, q.QUALITY_DSC, s.SIZE_NAME, ma.state, ma.pos_amount, " +
                "to_char(ma.clean_id) clean_id, to_char(ma.main_color_grade_id) main_color_grade_id, to_char(ma.color_grade_grade_id) color_grade_grade_id, to_char(ma.clean_grade_id) clean_grade_id, " +
                "ma.SPECIAL_WORK_PRICE, ma.TOSHOPDATE, ma.STORAGEDATE, bs.STYLENAME, " +
                "bs.ISBIGGRAPH, decode(ma.private_type, 0, '否', 1, '是') private_type, decode(mm.move_type, 1, '手工调拔', 2, '配货调拔') move_type " +
                "FROM (select ml.orna_code, mh.move_type, ROW_NUMBER() OVER(partition by ml.orna_code ORDER BY ml.create_date DESC) RN from jat_move_head mh " +
                "inner join jat_move_line ml on mh.headid = ml.headid where ml.status = 11 and mh.out_org_id = 49901 and mh.bill_type = 1) mm, ic_mater_active ma, " +
                "jas_bd_style bs, jas_bd_item_class ic, jas_bd_orna_class oc, jas_bd_analysis_arange aa, jas_bd_style_item_class sic, jas_bd_style_middle_class smc, jas_bd_style_orna_class soc, " +
                "jas_bd_quality q, jas_bd_size s WHERE mm.RN = 1 and mm.orna_code = ma.orna_code and ma.style_id = bs.STYLEID and ma.item_class_id = ic.ITEM_CLASS_ID " +
                "and ma.orna_class_id=oc.ORNA_CLASS_ID and ma.alaysis_id=aa.ANALYSIS_ARANGE_ID and bs.STYLEITEMCLASS=sic.ITEM_CLASS_ID and bs.STYLEMIDDLECLASS=smc.MIDDLE_CLASS_ID " +
                "and bs.STYLEORNACLASS=soc.ORNA_CLASS_ID and ma.quality_id=q.QUALITY_ID and ma.size_id=s.SIZE_ID and exists (select 1 from tmp_orna_move_type t where t.orna_code = mm.orna_code) ");
        form.setStart(condition.get("start"));
        form.setLimit(condition.get("limit"));
        //默认不显示数据，查询即显示
        if(!StringUtil.isNotEmpty(condition.get("firstFlag"))){
            sql.append(" and 1=0 ");
        }
        sql.append(condition.get("_q_sql")==null?"":condition.get("_q_sql"));
        return sql;
    }

    //门店满足查询
    private ModelAndView ornaMoveType(HttpServletRequest req, ReportForm form) throws Exception{
        StringBuffer sql = ornaMoveTypeSQL(req, form);
        String count_sql="select count(1) NUMS  from ("+sql.toString()+")";
        List<Map> listc=reportManager.executeQurey(count_sql);
        String totalCount =listc.get(0).get("NUMS").toString();
        String list_sql="select * from (select rownum rownum_ , AA.* from ("+sql.toString()+" ) AA ) where rownum_ >="+form.getStart()+"  and rownum <= "+form.getLimit();
        List<Map> list = reportManager.executeQurey(list_sql);
        form.setList(list);
        form.setTotalCount(totalCount);
        String total_sql="select sum(pos_amount) as pos_amount, sum(SPECIAL_WORK_PRICE) as SPECIAL_WORK_PRICE  from  ("+sql.toString()+" )  ";
        List<Map> listtotal = reportManager.executeQurey(total_sql);
        form.setListTotal(listtotal);
        return new ModelAndView("report/orna_move_type", "form", form);
    }


    //门店满足查询导出excel
    private void ornaMoveTypeExcel(HttpServletRequest req, HttpServletResponse res, ReportForm form) throws Exception{
        StringBuffer sql = ornaMoveTypeSQL(req, form);
        //List<Map> dataList = reportManager.executeQurey(sql.toString());
        Pager pager = reportManager.executeQueryPageData(sql.toString(), form.getStart(), form.getLimit());
        //excel数据对象
        ExcelData excelData = new ExcelData();
        excelData.setTitle("饰品调拨类型查询");
        excelData.setPager(pager);//直接利用分页pager对象
        //添加列标题
        excelData.setColumnTitle(new String[]{"饰品编码", "大类", "小类", "分析范围", "款式大类", "款式中类", "款式小类",
                "款式", "材质", "尺寸", "网店金额", "净度", "色级", "色级品质", "净度品质", "特殊工费", "到店日期", "入库日期", "状态"});
        //添加列name
        excelData.setColumnName(new String[]{"ORNA_CODE", "ITEM_CLASS_DSC", "ORNA_CLASS_DSC", "ANALYSIS_ARANGE_CODE", "STYLEITEMCLASSNAME", "STYLEMIDDLECLASSNAME", "STYLEORNACLASSNAME",
                "STYLENAME", "QUALITY_DSC", "SIZE_NAME", "POS_AMOUNT", "CLEAN_ID", "MAIN_COLOR_GRADE_ID", "COLOR_GRADE_GRADE_ID", "CLEAN_GRADE_ID", "SPECIAL_WORK_PRICE", "TOSHOPDATE", "STORAGEDATE", "STATE"});
        ExportExcelUtil util = new ExportExcelUtil();
        //设置对应的缓存数据
        excelData.addSingletonDisplayColumns(new String[]{},
                new CacheSingletonIntf[]{});
        //设置对应的数据字典
        excelData.addDictDisplayColumns(new String[]{"STATE", "MAIN_COLOR_GRADE_ID", "CLEAN_ID" , "COLOR_GRADE_GRADE_ID", "CLEAN_GRADE_ID"},
                new String[]{DictConstant.BILL_STATUS, DictConstant.DIA_COLORGRADE, DictConstant.DIA_CLEAN, DictConstant.GRADE_LEVEL, DictConstant.GRADE_LEVEL});

        util.setExcelData(excelData);//传入excel数据对象
        util.export(res);//需要传入HttpServletResponse，以便下载excel
    }

    public ModelAndView importOrnaMoveType(HttpServletRequest req, HttpServletResponse res) {
        return this.reportManager.importOrnaMoveType(req, res);
    }

    public ModelAndView importOrnaMoveType2(HttpServletRequest req, HttpServletResponse res) {
        String fieldName = "excel_file";
        ExcelUtil excelUtil = new ExcelUtil(req, res, excelUtilManager);
        excelUtil.saveExcelData(fieldName);
        boolean successFlag = excelUtil.checkExcelData(new ExcelCheckMode[] {
                new ExcelCheckMode(0, 0, new ExcelSelfDefinedCheck(){
                    @Override
                    public boolean check(int rowIndex, String value,
                                         ExcelRowData rowData, List<ExcelRowData> allData) {
                        String ornaCode = getNullString(rowData.getCol0(), "");
                        for (ExcelRowData data : allData){
                            if(rowIndex != data.getRowIndex()){
                                if(ornaCode.equals(data.getCol0()) ){
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                    @Override
                    public String getErrorMsg(int rowIndex, String value,
                                              ExcelRowData rowData, List<ExcelRowData> allData) {
                        String ornaCode = getNullString(rowData.getCol0(), "");
                        for (ExcelRowData data : allData){
                            if(rowIndex != data.getRowIndex()){
                                if(ornaCode.equals(data.getCol0()) ){
                                    return "第" + (rowIndex+1) + "行和第" + (data.getRowIndex()+1) + "行 饰品编码重复";
                                }
                            }
                        }
                        return null;
                    }
                    private String getNullString(String str, String defaultStr){
                        if(StringUtil.isNotBlank(str)) return str;
                        return defaultStr;
                    }}),
                new ExcelCheckMode(0, 0, false, ExcelColumnEnum.STRING_COLUMN)
        });
        if(successFlag){
            excelUtil.convertDbrefrenceToId();
            reportManager.saveOrnaMoveType(excelUtil.getSeqId());
            write(res, "{'isSuccess':'true'}");
        }
        return null;
    }

    private ExcelUtilManager excelUtilManager;

    public void setExcelUtilManager(ExcelUtilManager excelUtilManager) {
        this.excelUtilManager = excelUtilManager;
    }

    /** ==============================================================================
     *   --------------------------------------- 饰品调拨类型查询报表 结束 ---------------------------------------------
     *   ==============================================================================
     */
}
