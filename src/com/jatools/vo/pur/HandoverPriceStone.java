package com.jatools.vo.pur;

/**
 * 供查询满足交接单的入库单的核价单数据使用
 * @author ren.ming
 *
 */
public class HandoverPriceStone {

	private String handoverId;
	private String vendorId;
	private String ornaCode;
	private Double stoneNum;
	public String getHandoverId() {
		return handoverId;
	}
	public void setHandoverId(String handoverId) {
		this.handoverId = handoverId;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getOrnaCode() {
		return ornaCode;
	}
	public void setOrnaCode(String ornaCode) {
		this.ornaCode = ornaCode;
	}
	public Double getStoneNum() {
		return stoneNum;
	}
	public void setStoneNum(Double stoneNum) {
		this.stoneNum = stoneNum;
	}
	
}
