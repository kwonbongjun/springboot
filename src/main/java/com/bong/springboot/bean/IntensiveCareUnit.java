package com.bong.springboot.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="INTENSIVE_CARE_UNIT")
public class IntensiveCareUnit {
	String addr;
	String asmGrd;
	String asmItmCd;
	String clCd;
	String clCdNm;
	String sgguCd;
	String sgguCdNm;
	String sidoCd;
	String sidoCdNm;
	String yadmNm;
	String ykiho;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAsmGrd() {
		return asmGrd;
	}
	public void setAsmGrd(String asmGrd) {
		this.asmGrd = asmGrd;
	}
	public String getAsmItmCd() {
		return asmItmCd;
	}
	public void setAsmItmCd(String asmItmCd) {
		this.asmItmCd = asmItmCd;
	}
	public String getClCd() {
		return clCd;
	}
	public void setClCd(String clCd) {
		this.clCd = clCd;
	}
	public String getClCdNm() {
		return clCdNm;
	}
	public void setClCdNm(String clCdNm) {
		this.clCdNm = clCdNm;
	}
	public String getSgguCd() {
		return sgguCd;
	}
	public void setSgguCd(String sgguCd) {
		this.sgguCd = sgguCd;
	}
	public String getSgguCdNm() {
		return sgguCdNm;
	}
	public void setSgguCdNm(String sgguCdNm) {
		this.sgguCdNm = sgguCdNm;
	}
	public String getSidoCd() {
		return sidoCd;
	}
	public void setSidoCd(String sidoCd) {
		this.sidoCd = sidoCd;
	}
	public String getSidoCdNm() {
		return sidoCdNm;
	}
	public void setSidoCdNm(String sidoCdNm) {
		this.sidoCdNm = sidoCdNm;
	}
	public String getYadmNm() {
		return yadmNm;
	}
	public void setYadmNm(String yadmNm) {
		this.yadmNm = yadmNm;
	}
	public String getYkiho() {
		return ykiho;
	}
	public void setYkiho(String ykiho) {
		this.ykiho = ykiho;
	}
}
