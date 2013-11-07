package com.jatools.web.form.stock;

import java.util.List;

import com.jatools.vo.stock.ProcPackageHead;
import com.jatools.vo.stock.ProcPackageNewline;
import com.jatools.vo.stock.ProcPackageOldline;
import com.jatools.web.form.BaseForm;


public class ProcPackageHeadForm extends BaseForm {
	private ProcPackageHead procPackageHead;
	/**
	 * 原饰品列表
	 */
	private List<ProcPackageOldline> oldLines;
	/**
	 * 新饰品列表
	 */
	private List<ProcPackageNewline> newLines;
	
	private String printData;
	public String getPrintData() {
		return printData;
	}
	public void setPrintData(String printData) {
		this.printData = printData;
	}

	public ProcPackageHead getProcPackageHead() {
		return procPackageHead;
	}

	public void setProcPackageHead(ProcPackageHead procPackageHead) {
		this.procPackageHead = procPackageHead;
	}

	public List<ProcPackageOldline> getOldLines() {
		return oldLines;
	}

	public void setOldLines(List<ProcPackageOldline> oldLines) {
		this.oldLines = oldLines;
	}

	public List<ProcPackageNewline> getNewLines() {
		return newLines;
	}

	public void setNewLines(List<ProcPackageNewline> newLines) {
		this.newLines = newLines;
	}
	
	
}
