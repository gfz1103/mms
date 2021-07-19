package com.buit.mms.crivalu.dto;

import com.buit.mq.dto.NotifyMessageDto;

public class CisWjzMessDto extends NotifyMessageDto{

	private Integer wjzdh;
	
	private Boolean isCritical;
	
	private String routingKey;
	
	private String brch;
	
	private String brxm;
	
	private String xb;
	
	private String officename;
	
	public CisWjzMessDto(){
		
	}
	
	public CisWjzMessDto(Integer wjzdh, Boolean isCritical, String routingKey, String brch,
			String brxm, String xb, String officename) {
		this.wjzdh = wjzdh;
		this.isCritical = isCritical;
		this.routingKey = routingKey;
		this.brch = brch;
		this.brxm = brxm;
		this.xb = xb;
		this.officename = officename;
	}

	public Integer getWjzdh() {
		return wjzdh;
	}

	public void setWjzdh(Integer wjzdh) {
		this.wjzdh = wjzdh;
	}

	public Boolean getIsCritical() {
		return isCritical;
	}

	public void setIsCritical(Boolean isCritical) {
		this.isCritical = isCritical;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getBrch() {
		return brch;
	}

	public void setBrch(String brch) {
		this.brch = brch;
	}

	public String getBrxm() {
		return brxm;
	}

	public void setBrxm(String brxm) {
		this.brxm = brxm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}
	
	
	
}
