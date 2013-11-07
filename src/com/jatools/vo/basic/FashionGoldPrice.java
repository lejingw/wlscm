package com.jatools.vo.basic;

public class FashionGoldPrice {
    private String id;
    private String itemClassId;
    private String goldPrice;
	private String status;// number,

	private String memo;// varchar2(1000),
	private String createDate;// varchar2(20),
	private String createId;// number,
	private String updateDate;// varchar2(20),
	private String updateId;// number

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemClassId() {
        return itemClassId;
    }

    public void setItemClassId(String itemClassId) {
        this.itemClassId = itemClassId;
    }

    public String getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(String goldPrice) {
        this.goldPrice = goldPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }
}
