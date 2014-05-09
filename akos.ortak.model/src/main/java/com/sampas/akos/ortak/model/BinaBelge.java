package com.sampas.akos.ortak.model;

import java.util.Date;

import com.sampas.akos.common.model.BaseObject;

public class BinaBelge extends BaseObject {

		private Long belgeId;
		private Long arsivId;
		private Date belgeTarihi;
		private Long belgeTuru;
		private Long konuKodu;
		private String dysId;
		private Long belgeTipi;		
		private String aciklama;
		private String konuAciklama;
		private String konuOnemDerecesi;		
		private String turAciklama;
		private String tipAciklama;
		private String dosyaUrl;
		
		public Long getArsivId() {
			return arsivId;
		}
		public void setArsivId(Long arsivId) {
			this.arsivId = arsivId;
		}
		public Date getBelgeTarihi() {
			return belgeTarihi;
		}
		public void setBelgeTarihi(Date belgeTarihi) {
			this.belgeTarihi = belgeTarihi;
		}
		public Long getBelgeTuru() {
			return belgeTuru;
		}
		public void setBelgeTuru(Long belgeTuru) {
			this.belgeTuru = belgeTuru;
		}
		public Long getKonuKodu() {
			return konuKodu;
		}
		public void setKonuKodu(Long konuKodu) {
			this.konuKodu = konuKodu;
		}
		public String getDysId() {
			return dysId;
		}
		public void setDysId(String dysId) {
			this.dysId = dysId;
		}
		public Long getBelgeTipi() {
			return belgeTipi;
		}
		public void setBelgeTipi(Long belgeTipi) {
			this.belgeTipi = belgeTipi;
		}
		public String getAciklama() {
			return aciklama;
		}
		public void setAciklama(String aciklama) {
			this.aciklama = aciklama;
		}
		public String getKonuAciklama() {
			return konuAciklama;
		}
		public void setKonuAciklama(String konuAciklama) {
			this.konuAciklama = konuAciklama;
		}
		public String getKonuOnemDerecesi() {
			return konuOnemDerecesi;
		}
		public void setKonuOnemDerecesi(String konuOnemDerecesi) {
			this.konuOnemDerecesi = konuOnemDerecesi;
		}
		public String getTurAciklama() {
			return turAciklama;
		}
		public void setTurAciklama(String turAciklama) {
			this.turAciklama = turAciklama;
		}
		public String getTipAciklama() {
			return tipAciklama;
		}
		public void setTipAciklama(String tipAciklama) {
			this.tipAciklama = tipAciklama;
		}
		public Long getBelgeId() {
			return belgeId;
		}
		public void setBelgeId(Long belgeId) {
			this.belgeId = belgeId;
		}
		public String getDosyaUrl() {
			return dosyaUrl;
		}
		public void setDosyaUrl(String dosyaUrl) {
			this.dosyaUrl = dosyaUrl;
		}
		
}
