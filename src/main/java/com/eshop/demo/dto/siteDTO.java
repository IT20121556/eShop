package com.eshop.demo.dto;

public class siteDTO {
	String footer;
	String header;
	int siteId;
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public siteDTO(String footer, String header, int siteId) {
	
		this.footer = footer;
		this.header = header;
		this.siteId = siteId;
	}
	
	
	public siteDTO() {

	}

}
