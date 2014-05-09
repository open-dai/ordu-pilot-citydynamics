package com.sampas.akos.ortak.model;

import com.sampas.akos.common.model.BaseObject;

public class BinaRisk extends BaseObject {

	private Long binaRiskNo;
	private Long binaNo;
	private String yapiRisk;
	private String yapiRiskAciklama;
	private Long yapiRiskKodu;
	private Long isyeriRuhsatDurum;
	public Long getBinaRiskNo() {
		return binaRiskNo;
	}
	public void setBinaRiskNo(Long binaRiskNo) {
		this.binaRiskNo = binaRiskNo;
	}
	public Long getBinaNo() {
		return binaNo;
	}
	public void setBinaNo(Long binaNo) {
		this.binaNo = binaNo;
	}
	public String getYapiRisk() {
		return yapiRisk;
	}
	public void setYapiRisk(String yapiRisk) {
		this.yapiRisk = yapiRisk;
	}
	public String getYapiRiskAciklama() {
		return yapiRiskAciklama;
	}
	public void setYapiRiskAciklama(String yapiRiskAciklama) {
		this.yapiRiskAciklama = yapiRiskAciklama;
	}
	public Long getYapiRiskKodu() {
		return yapiRiskKodu;
	}
	public void setYapiRiskKodu(Long yapiRiskKodu) {
		this.yapiRiskKodu = yapiRiskKodu;
	}
	public Long getIsyeriRuhsatDurum() {
		return isyeriRuhsatDurum;
	}
	public void setIsyeriRuhsatDurum(Long isyeriRuhsatDurum) {
		this.isyeriRuhsatDurum = isyeriRuhsatDurum;
	}	
	
}
