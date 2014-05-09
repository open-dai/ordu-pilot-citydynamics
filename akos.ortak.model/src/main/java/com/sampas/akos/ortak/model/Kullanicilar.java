package com.sampas.akos.ortak.model;



import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;
import java.util.Date;

import org.hibernate.envers.Audited;


@SuppressWarnings("serial")
@Audited
public class Kullanicilar extends BaseObject {

	private Long id;
	

	
	private String kullaniciKodu;
	private String kullaniciAdi;
	private String kullaniciSoyadi;
	private String kullaniciSifresi;
	private String kullaniciTuru;
	private Long sicilKodu;
	private Long roleId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getKullaniciSoyadi() {
		return kullaniciSoyadi;
	}
	public void setKullaniciSoyadi(String kullaniciSoyadi) {
		this.kullaniciSoyadi = kullaniciSoyadi;
	}
	public String getKullaniciKodu() {
		return kullaniciKodu;
	}
	public void setKullaniciKodu(String kullaniciKodu) {
		this.kullaniciKodu = kullaniciKodu;
	}
	public String getKullaniciAdi() {
		return kullaniciAdi;
	}
	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}
	public String getKullaniciSifresi() {
		return kullaniciSifresi;
	}
	public void setKullaniciSifresi(String kullaniciSifresi) {
		this.kullaniciSifresi = kullaniciSifresi;
	}
	public String getKullaniciTuru() {
		return kullaniciTuru;
	}
	public void setKullaniciTuru(String kullaniciTuru) {
		this.kullaniciTuru = kullaniciTuru;
	}
	public Long getSicilKodu() {
		return sicilKodu;
	}
	public void setSicilKodu(Long sicilKodu) {
		this.sicilKodu = sicilKodu;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	

	
}
