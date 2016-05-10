package com.jd.survey.util;
public class ShopSurveyRelEntity implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8630054873191586761L;
	
	private Long id ;
	private String shortUrl ;
	private String longUrl ;
	private Long surveyId ;
	private String surveyName;
	private int status ;
	private Long managementId ;
	private Long shopId ;
	private String shopName ;
	private String flowId;
	private int amount;
	private int convertCount;
	private int redeemCodeCount;
	
	private Long insertDate;
	private Long updateDate;
	private String ext1 ;
	private String ext2 ;
	private String ext3 ;
	private String ext4 ;
	private String ext5 ;
	private String ext6 ;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	public Long getManagementId() {
		return managementId;
	}
	public void setManagementId(Long managementId) {
		this.managementId = managementId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	
	public String getExt6() {
		return ext6;
	}
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	public Long getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Long insertDate) {
		this.insertDate = insertDate;
	}
	public Long getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getConvertCount() {
		return convertCount;
	}
	public void setConvertCount(int convertCount) {
		this.convertCount = convertCount;
	}
	public int getRedeemCodeCount() {
		return redeemCodeCount;
	}
	public void setRedeemCodeCount(int redeemCodeCount) {
		this.redeemCodeCount = redeemCodeCount;
	}
	
}