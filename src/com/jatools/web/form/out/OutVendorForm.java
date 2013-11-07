package com.jatools.web.form.out;

import java.util.List;

import com.jatools.vo.out.OutVendorHead;
import com.jatools.vo.out.OutVendorLine;
import com.jatools.vo.stock.MaterActive;
import com.jatools.vo.stock.MaterNoActive;
import com.jatools.web.form.BaseForm;

public class OutVendorForm extends BaseForm {
	private OutVendorHead head=new OutVendorHead();
	private String showAll;
	private List<OutVendorLine> list;
	private List<MaterActive> printList;	
	private List<MaterNoActive> printListNo;	

	public List<MaterActive> getPrintList() {
		return printList;
	}

	public void setPrintList(List<MaterActive> printList) {
		this.printList = printList;
	}

	public List<MaterNoActive> getPrintListNo() {
		return printListNo;
	}

	public void setPrintListNo(List<MaterNoActive> printListNo) {
		this.printListNo = printListNo;
	}

	public OutVendorHead getHead() {
		return head;
	}

	public void setHead(OutVendorHead head) {
		this.head = head;
	}

	public List<OutVendorLine> getList() {
		return list;
	}

	public void setList(List<OutVendorLine> list) {
		this.list = list;
	}

	public String getShowAll() {
		return showAll;
	}

	public void setShowAll(String showAll) {
		this.showAll = showAll;
	}

}
