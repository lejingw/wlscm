package com.jatools.manager.out;

import java.util.List;
import java.util.Map;

import com.jatools.common.Pager;
import com.jatools.vo.out.OutOrna;
import com.jatools.vo.out.OutStockHead;
import com.jatools.vo.out.OutStockLine;

public interface OutStockManager {
	Pager getStockHeadPage(Map<String, String> condition);
	public OutOrna getOrnaInfo(String code, boolean ornaFlag);
	void saveOrUpdateOutStock(OutStockHead head, List<OutStockLine> lines,String[] delIds);
	OutStockHead getStockHead(String headId);
	List<OutStockLine> getStockLines(String headId);
	void delOutStock(String billId);
}
