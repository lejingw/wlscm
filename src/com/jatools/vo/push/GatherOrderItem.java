package com.jatools.vo.push;

import java.util.List;

public class GatherOrderItem {
	private String styleLineId;
	private String gradeLineId;
	private boolean wearSizeFlag;
	private String sizeLineId;
	private List<StyleWearSizeRate> wearSizeList;
	private int orderNum;

	public GatherOrderItem(String styleLineId,
			String gradeLineId, boolean wearSizeFlag, String sizeLineId, int orderNum) {
		this.styleLineId = styleLineId;
		this.gradeLineId = gradeLineId;
		this.wearSizeFlag = wearSizeFlag;
		this.sizeLineId = sizeLineId;
		this.orderNum = orderNum;
	}

	public void addOrderNum() {
		this.orderNum++;
	}

	public String getStyleLineId() {
		return styleLineId;
	}

	public String getGradeLineId() {
		return gradeLineId;
	}

	public String getSizeLineId() {
		return sizeLineId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public List<StyleWearSizeRate> getWearSizeList() {
		return wearSizeList;
	}

	public void setWearSizeList(List<StyleWearSizeRate> wearSizeList) {
		this.wearSizeList = wearSizeList;
	}

	public boolean isWearSizeFlag() {
		return wearSizeFlag;
	}
}