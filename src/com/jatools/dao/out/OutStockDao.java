package com.jatools.dao.out;

import com.jatools.common.Pager;
import com.jatools.vo.out.OutOrna;
import  com.jatools.vo.out.OutStockHead;
import com.jatools.vo.out.OutStockLine;
import java.util.List;
import java.util.Map;

public interface OutStockDao {
    Pager getStockHeadPage(Map<String, String> condition);
	OutOrna getOrna(String code, boolean ornaFlag);
	void deleteLines(String delIds);
	void updateHead(OutStockHead head);
	String insertHead(OutStockHead head);
	void updateLine(OutStockLine line);
	void insertLine(OutStockLine line);
	List<OutStockLine> getStockLines(String headId);
	OutStockHead getStockHead(String headId);
	void deleteHead(String billId);
	void deleteLinesByHeadId(String billId);
}