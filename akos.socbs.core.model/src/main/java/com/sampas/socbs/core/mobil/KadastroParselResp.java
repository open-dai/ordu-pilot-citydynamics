package com.sampas.socbs.core.mobil;

import java.util.Date;

public class KadastroParselResp {

	private int tur;	
	private Long id;
	private String aktifEh = "";
	private Long parselId;	
	private Long parsel;
	private Double alan;
	private Long ada;	
	private String pafta = "";	
	private Long kapiNo;
	private String altKapiNo;
	private Long binaNo;
	private Long binaId;
	private Long mahalleId;
	private Long caddesokakId;	
	private double lat=0.0;
	private double lon=0.0;
	private double alt=1.0;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getAktifEh() {
		return aktifEh;
	}

	public void setAktifEh(String aktifEh) {
		this.aktifEh = aktifEh;
	}



	public Long getParsel() {
		return parsel;
	}

	public void setParsel(Long parsel) {
		this.parsel = parsel;
	}


	public Double getAlan() {
		return alan;
	}

	public void setAlan(Double alan) {
		this.alan = alan;
	}



	public Long getAda() {
		return ada;
	}

	public void setAda(Long ada) {
		this.ada = ada;
	}



	public String getPafta() {
		return pafta;
	}

	public void setPafta(String pafta) {
		this.pafta = pafta;
	}

	public Long getKapiNo() {
		return kapiNo;
	}

	public void setKapiNo(Long kapiNo) {
		this.kapiNo = kapiNo;
	}

	public String getAltKapiNo() {
		return altKapiNo;
	}

	public void setAltKapiNo(String altKapiNo) {
		this.altKapiNo = altKapiNo;
	}

	public Long getBinaNo() {
		return binaNo;
	}

	public void setBinaNo(Long binaNo) {
		this.binaNo = binaNo;
	}

	public Long getBinaId() {
		return binaId;
	}

	public void setBinaId(Long binaId) {
		this.binaId = binaId;
	}

	public int getTur() {
		return tur;
	}

	public void setTur(int tur) {
		this.tur = tur;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getAlt() {
		return alt;
	}

	public void setAlt(double alt) {
		this.alt = alt;
	}

	public Long getMahalleId() {
		return mahalleId;
	}

	public void setMahalleId(Long mahalleId) {
		this.mahalleId = mahalleId;
	}

	public Long getCaddesokakId() {
		return caddesokakId;
	}

	public void setCaddesokakId(Long caddesokakId) {
		this.caddesokakId = caddesokakId;
	}

	public Long getParselId() {
		return parselId;
	}

	public void setParselId(Long parselId) {
		this.parselId = parselId;
	}


/*
	private MahalleResp mahalle;

	public MahalleResp getMahalle() {
		return mahalle;
	}

	public void setMahalle(MahalleResp mahalle) {
		this.mahalle = mahalle;
	}

	
	private KadastroParselTipi kadastroParselTipi;

	public KadastroParselTipi getKadastroParselTipi() {
		return kadastroParselTipi;
	}

	public void setKadastroParselTipi(KadastroParselTipi kadastroParselTipi) {
		this.kadastroParselTipi = kadastroParselTipi;
	}
	
	private Bolge bolge;

	public Bolge getBolge() {
		return bolge;
	}

	public void setBolge(Bolge bolge) {
		this.bolge = bolge;
	}
*/	
	

}
