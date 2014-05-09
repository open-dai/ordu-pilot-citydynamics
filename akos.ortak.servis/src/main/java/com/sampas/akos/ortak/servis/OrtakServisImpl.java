package com.sampas.akos.ortak.servis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sampas.akos.ortak.dao.OrtakDAO;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;
import com.sampas.akos.ortak.model.BagimsizKullanimGrup;
import com.sampas.akos.ortak.model.BagimsizKullanimSinif;
import com.sampas.akos.ortak.model.BagimsizTapu;
import com.sampas.akos.ortak.model.BilgiTipi;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.BinaBelge;
import com.sampas.akos.ortak.model.BinaCatiTur;
import com.sampas.akos.ortak.model.BinaNitelik;
import com.sampas.akos.ortak.model.BinaOrtakKullanim;
import com.sampas.akos.ortak.model.BinaParsel;
import com.sampas.akos.ortak.model.BinaRisk;
import com.sampas.akos.ortak.model.BinaTesisat;
import com.sampas.akos.ortak.model.Bolge;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.CaddeSokakTur;
import com.sampas.akos.ortak.model.DosemeTur;
import com.sampas.akos.ortak.model.GelismislikDurum;
import com.sampas.akos.ortak.model.HareketKod;
import com.sampas.akos.ortak.model.InsaatSinif;
import com.sampas.akos.ortak.model.InsaatTur;
import com.sampas.akos.ortak.model.IsitmaTur;
import com.sampas.akos.ortak.model.IslemMenu;
import com.sampas.akos.ortak.model.KadastroHareketGrup;
import com.sampas.akos.ortak.model.KadastroHareketKod;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KadastroParselTipi;
import com.sampas.akos.ortak.model.KonutDurum;
import com.sampas.akos.ortak.model.KonutSahiplikDurum;
import com.sampas.akos.ortak.model.KonutTip;
import com.sampas.akos.ortak.model.Kullanicilar;
import com.sampas.akos.ortak.model.KullanimSekli;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.Mahalle;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.akos.ortak.model.OrtakKullanimTur;
import com.sampas.akos.ortak.model.SicakSuTeminTur;
import com.sampas.akos.ortak.model.Site;
import com.sampas.akos.ortak.model.SysAudit;
import com.sampas.akos.ortak.model.Tapu;
import com.sampas.akos.ortak.model.TapuMahalle;
import com.sampas.akos.ortak.model.TasiyiciSistem;
import com.sampas.akos.ortak.model.UretimKaynak;
import com.sampas.akos.ortak.model.YakitTur;
import com.sampas.akos.ortak.model.YapiAda;
import com.sampas.akos.ortak.model.YapiCepheTur;

@SuppressWarnings("unchecked")
public class OrtakServisImpl implements OrtakServis {

	private OrtakDAO ortakDAO;

	public OrtakDAO getOrtakDAO() {
		return ortakDAO;
	}

	public void setOrtakDAO(OrtakDAO ortakDAO) {
		this.ortakDAO = ortakDAO;
	}
	
	
	public List<BinaRisk> readBinaRiskBilgiByBinaId(Long p_binano)
	{
		return ortakDAO.readBinaRiskBilgiByBinaId(p_binano);
	}
	
	public  List<BinaBelge> readBelgelerByBinaNo(Long p_binano)
	{
		return ortakDAO.readBelgelerByBinaNo(p_binano);
	}
	
	public Kullanicilar login(Long p_kurumKodu,String p_username,String p_password)
	{
		return ortakDAO.login(p_kurumKodu,p_username,p_password);
	}
	
	public List<Bina> readAllBinaByCriteriaNew (
			KurumSabit kurumSabit,Bina bina, Adres adres,KadastroParsel kadastroParsel,
			MahalleCaddeSokak mahalleCaddeSokak) {
		return ortakDAO.readAllBinaByCriteriaNew(kurumSabit,bina,adres,kadastroParsel,mahalleCaddeSokak);
	}
	
	
	
	public List<Bina> readAllBina(KurumSabit kurumsabit) 
	{
		return ortakDAO.readAllBina(kurumsabit);
	}
	
	public List<Adres> readAllKadastroParselByAdresNew(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak) 
			{
				return ortakDAO.readAllKadastroParselByAdresNew(kurumSabit, adres,mahalleCaddeSokak);
			}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokak(
			KurumSabit kurumSabit, MahalleCaddeSokak mahalleCaddeSokak) {
		return ortakDAO.readAllMahalleCaddeSokak(kurumSabit, mahalleCaddeSokak);
	}

	public List<KadastroParsel> readAllKadastroParselByParselMah(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel) {
		return ortakDAO.readAllKadastroParselByParselMah(kurumSabit,
				kadastroParsel);
	}

	public List<Number> readRevisions(Class c, Object obj) {
		return ortakDAO.readRevisions(c, obj);
	}

	public Long readNumberOfRevisionsOfEntity(Class c, Long id) {
		return ortakDAO.readNumberOfRevisionsOfEntity(c, id);
	}

	public List readRevisionsOfEntity(Class c, Long id, int startIndex,
			int maxResults) {
		return ortakDAO.readRevisionsOfEntity(c, id, startIndex, maxResults);
	}

	public List<?> readRevisionsOfEntityWithRevisionId(Class c, int revisionId,
			Long id) {
		return ortakDAO.readRevisionsOfEntityWithRevisionId(c, revisionId, id);
	}

	public List<?> readAllObjectsByAciklama(Class obj, String aciklama,
			int maxResultCount) {
		return ortakDAO.readAllObjectsByAciklama(obj, aciklama, maxResultCount);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			KurumSabit kurumSabit, String aciklama, int maxResultCount,
			String sorguTipi) {
		return ortakDAO.readAllMahalleCaddeSokakByCriteria(kurumSabit,
				aciklama, maxResultCount, sorguTipi);
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(Adres adres,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllBagimsizByCriteria(adres, kurumSabit);
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(Bina bina,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllBagimsizByCriteria(bina, kurumSabit);
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		return ortakDAO.readAllBagimsizByCriteria(kadastroParsel, kurumSabit);
	}

	public List<Bagimsiz> readAllBagimsizByBina(Bina bina, KurumSabit kurumSabit) {
		return ortakDAO.readAllBagimsizByBina(bina, kurumSabit);
	}

	public List<Bina> readAllBinaByCriteria(Adres adres, KurumSabit kurumSabit) {
		return ortakDAO.readAllBinaByCriteria(adres, kurumSabit);
	}

	public List<Bina> readAllBinaByCriteria(KadastroParsel kadastroParsel,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllBinaByCriteria(kadastroParsel, kurumSabit);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllMahalleCaddeSokakByCriteria(mahalleCaddeSokak,
				adres, kurumSabit);
	}

	public KadastroParsel readKadastroParselByCriteria(Adres adres,
			KurumSabit kurumSabit) {
		return ortakDAO.readKadastroParselByCriteria(adres, kurumSabit);
	}

	public List<Site> readAllSiteByCriteria(Site site, KurumSabit kurumSabit,
			int maxOkunacakKayitSayisi) {
		return ortakDAO.readAllSiteByCriteria(site, kurumSabit,
				maxOkunacakKayitSayisi);
	}

	public List<YapiAda> readAllYapiAdaByCriteria(YapiAda yapiAda,
			KurumSabit kurumSabit, int maxOkunacakKayitSayisi) {
		return ortakDAO.readAllYapiAdaByCriteria(yapiAda, kurumSabit,
				maxOkunacakKayitSayisi);
	}

	public List<Adres> readAllAdresByAdresAndMahalleCaddeSokak(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak) {
		return ortakDAO.readAllAdresByAdresAndMahalleCaddeSokak(kurumSabit,
				adres, mahalleCaddeSokak);
	}

	public List<Adres> readAllAdresByAdresList(KurumSabit kurumSabit,
			List<Adres> addressList) {
		return ortakDAO.readAllAdresByAdresList(kurumSabit, addressList);
	}

	public List<Adres> readAllAdresByAdresListNonLazy(KurumSabit kurumSabit,
			List<Adres> addressList) {
		List<Adres> resultList = ortakDAO.readAllAdresByAdresList(kurumSabit,
				addressList);
		/**
		 * to break the lazy initialization
		 */
		if (resultList != null && resultList.size() > 0) {
			for (Adres adres : resultList) {
				if (adres.getMahalleCaddeSokak() != null) {
					if (adres.getMahalleCaddeSokak().getMahalle() != null)
						adres.getMahalleCaddeSokak().getMahalle().getAciklama();
					if (adres.getMahalleCaddeSokak().getCaddeSokak() != null) {
						adres.getMahalleCaddeSokak().getCaddeSokak()
								.getAciklama();
					}
				}
				if (adres.getBina() != null) {
					adres.getBina().getApartmanBlokAd();
					adres.getBina().getBinaNo();
					if (adres.getBina().getSite() != null) {
						adres.getBina().getSite().getAciklama();
					}
					adres.getBina().getAktifEh();
				}
				if (adres.getKadastroParsel() != null) {
					adres.getKadastroParsel().getPafta();
					adres.getKadastroParsel().getParsel();
					adres.getKadastroParsel().getAda();
					adres.getKadastroParsel().getAlan();
					adres.getKadastroParsel().getAktifEh();
					adres.getKadastroParsel().getKapanisTarih();
				}
			}
		}
		return resultList;
	}

	public List<CaddeSokak> readAllCaddeSokakByCriteria(KurumSabit kurumsabit,
			Mahalle mahalle) {
		return ortakDAO.readAllCaddeSokakByCriteria(kurumsabit, mahalle);
	}

	public List<CaddeSokak> readAllCaddeSokakByCriteria(CaddeSokak caddeSokak,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllCaddeSokakByCriteria(caddeSokak, kurumSabit);
	}

	public List<KadastroParsel> readAllKadastroParselByParsel(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel) {
		return ortakDAO.readAllKadastroParselByParsel(kurumSabit,
				kadastroParsel);
	}

	public List<KadastroParsel> readAllKadastroParselByParselList(
			KurumSabit kurumSabit, List<KadastroParsel> kadastroParselList,
			Integer maxResultCount) {

		return ortakDAO.readAllKadastroParselByParselList(kurumSabit,
				kadastroParselList, maxResultCount);
	}

	public List<Mahalle> readAllMahalle(KurumSabit kurumsabit) {
		return ortakDAO.readAllMahalle(kurumsabit);
	}

	public MahalleCaddeSokak readMahalleCaddeSokakByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, KurumSabit kurumSabit) {
		return ortakDAO.readMahalleCaddeSokakByCriteria(mahalleCaddeSokak,
				kurumSabit);
	}

	public List<Bina> readAllBinaByCriteria(Bina bina, KurumSabit kurumsabit) {
		return ortakDAO.readAllBinaByCriteria(bina, kurumsabit);
	}

	public Long readMaxIdForObject(Class entityClass) {
		return ortakDAO.readMaxIdForObject(entityClass);
	}

	public List<KadastroParsel> readAllKadastroParselByParselAndMahalle(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel,
			Mahalle mahalle) {
		return ortakDAO.readAllKadastroParselByParselAndMahalle(kurumSabit,
				kadastroParsel, mahalle);
	}

	public List<Adres> readAllAdresByCriteria(KurumSabit kurumSabit,
			Adres adres, MahalleCaddeSokak mahalleCaddeSokak, Bina bina) {
		return ortakDAO.readAllAdresByCriteria(kurumSabit, adres,
				mahalleCaddeSokak, bina);
	}

	public List<Adres> readAllAdresByKadastroParsel(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		return ortakDAO
				.readAllAdresByKadastroParsel(kadastroParsel, kurumSabit);
	}

	public List<Adres> readAllAdresByBagimsiz(Bagimsiz bagimsiz,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllAdresByBagimsiz(bagimsiz, kurumSabit);
	}

	public Bina readBinaByAdres(Adres adres, KurumSabit kurumSabit) {
		return ortakDAO.readBinaByAdres(adres, kurumSabit);
	}

	public Bina readBinaByBagimsiz(Bagimsiz bagimsiz, KurumSabit kurumSabit) {
		return ortakDAO.readBinaByBagimsiz(bagimsiz, kurumSabit);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByAdres(Adres adres,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllMahalleCaddeSokakByAdres(adres, kurumSabit);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByAdresler(
			List<Adres> adresler, KurumSabit kurumSabit, int maxResultCount) {
		return ortakDAO.readAllMahalleCaddeSokakByAdresler(adresler,
				kurumSabit, maxResultCount);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByBagimsiz(
			Bagimsiz bagimsiz, KurumSabit kurumSabit) {
		return ortakDAO
				.readAllMahalleCaddeSokakByBagimsiz(bagimsiz, kurumSabit);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByBina(Bina bina,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllMahalleCaddeSokakByBina(bina, kurumSabit);
	}

	public List<Adres> readAllAdresByBina(Bina bina, KurumSabit kurumSabit) {
		return ortakDAO.readAllAdresByBina(bina, kurumSabit);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByMahalleCaddeSokak(
			MahalleCaddeSokak mahalleCaddeSokak, KurumSabit kurumSabit) {
		// sorgulama yapabilmek icin kurumSabitin id si ve mahalleCaddeSokak
		// nesnesinin en az bir propertisinin belirtilmis olmasi gerekir
		if (kurumSabit == null
				|| kurumSabit.getId() == null
				|| mahalleCaddeSokak == null
				|| (mahalleCaddeSokak.getId() == null
						&& mahalleCaddeSokak.getTuikCaddeSokakKod() == null
						&& (mahalleCaddeSokak.getMahalle() == null || (mahalleCaddeSokak
								.getMahalle().getId() == null && (mahalleCaddeSokak
								.getMahalle().getAciklama() == null || mahalleCaddeSokak
								.getMahalle().getAciklama().trim().equals("")))) && (mahalleCaddeSokak
						.getCaddeSokak() == null || (mahalleCaddeSokak
						.getCaddeSokak().getId() == null && (mahalleCaddeSokak
						.getCaddeSokak() == null || (mahalleCaddeSokak
						.getCaddeSokak().getAciklama() == null || mahalleCaddeSokak
						.getCaddeSokak().getAciklama().trim().equals(""))))))) {
			return null;
		}
		return ortakDAO.readAllMahalleCaddeSokakByMahalleCaddeSokak(
				mahalleCaddeSokak, kurumSabit);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByKadastroParsel(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		return ortakDAO.readAllMahalleCaddeSokakByKadastroParsel(
				kadastroParsel, kurumSabit);
	}

	public List<Mahalle> readAllMahalleByCriteria(Mahalle mahalle,
			KurumSabit kurumSabit) {
		if (kurumSabit == null || kurumSabit.getId() == null) {
			return null;
		}
		return ortakDAO.readAllMahalleByCriteria(mahalle, kurumSabit);
	}

	public List<BagimsizKullanimSinif> readAllBagimsizKullanimSinif() {
		return ortakDAO.readAllBagimsizKullanimSinif();
	}

	public void saveOrUpdateBagimsizKullanimSinifList(
			List<BagimsizKullanimSinif> bagimsizKullanimSinifList) {
		ortakDAO.saveOrUpdateBagimsizKullanimSinifList(bagimsizKullanimSinifList);
	}

	public void deleteBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		ortakDAO.deleteBagimsizKullanimSinif(bagimsizKullanimSinif);
	}

	public void deleteBagimsizKullanimGrup(
			BagimsizKullanimGrup bagimsizKullanimGrup) {
		ortakDAO.deleteBagimsizKullanimGrup(bagimsizKullanimGrup);
	}

	public void saveOrUpdateBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		ortakDAO.saveOrUpdateBagimsizKullanimSinif(bagimsizKullanimSinif);
	}

	public List<BagimsizKullanimGrup> readAllBagimsizKullanimGrupByKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		return ortakDAO
				.readAllBagimsizKullanimGrupByKullanimSinif(bagimsizKullanimSinif);
	}

	public BagimsizKullanimGrup readAllBagimsizKullanimGrupByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup) {
		return ortakDAO
				.readAllBagimsizKullanimGrupByCriteria(bagimsizKullanimGrup);
	}

	public void deleteBagimsizKullanimDetay(
			BagimsizKullanimDetay bagimsizKullanimDetay) {
		ortakDAO.deleteBagimsizKullanimDetay(bagimsizKullanimDetay);
	}

	public void saveOrUpdateBagimsizKullanimGrup(
			BagimsizKullanimGrup bagimsizKullanimGrup) {
		ortakDAO.saveOrUpdateBagimsizKullanimGrup(bagimsizKullanimGrup);
	}

	public BagimsizKullanimDetay readBagimsizKullanimDetayByCriteria(
			BagimsizKullanimDetay bagimsizKullanimDetay) {
		return ortakDAO
				.readBagimsizKullanimDetayByCriteria(bagimsizKullanimDetay);
	}

	public List<BagimsizKullanimDetay> readAllBagimsizKullanimDetayByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup) {
		return ortakDAO
				.readAllBagimsizKullanimDetayByCriteria(bagimsizKullanimGrup);
	}

	public void saveOrUpdateBagimsizKullanimDetay(
			BagimsizKullanimDetay bagimsizKullanimDetay) {
		ortakDAO.saveOrUpdateBagimsizKullanimDetay(bagimsizKullanimDetay);
	}

	public List<BagimsizKullanimGrup> readAllBagimsizKullanimGrup() {
		return ortakDAO.readAllBagimsizKullanimGrup();
	}

	public void saveOrUpdateBagimsizKullanimDetayList(
			List<BagimsizKullanimDetay> bagimsizKullanimDetayList) {
		ortakDAO.saveOrUpdateBagimsizKullanimDetayList(bagimsizKullanimDetayList);
	}

	public void saveOrUpdateBagimsizKullanimGrupList(
			List<BagimsizKullanimGrup> bagimsizKullanimGrupList) {
		ortakDAO.saveOrUpdateBagimsizKullanimGrupList(bagimsizKullanimGrupList);
	}

	public Long readLastIdFromDbObject(Class entityClass) {
		Long id = ortakDAO.readLastIdFromDbObject(entityClass);
		if (id == null) {
			return 0L;
		}
		return id;
	}

	public List<CaddeSokakTur> readAllCaddeSokakTur() {
		return ortakDAO.readAllCaddeSokakTur();
	}

	public void saveOrUpdateCaddeSokak(CaddeSokak caddeSokak) {
		ortakDAO.saveOrUpdateCaddeSokak(caddeSokak);
	}

	public void deleteMahalleCaddeSokak(MahalleCaddeSokak mahalleCaddeSokak) {
		ortakDAO.deleteMahalleCaddeSokak(mahalleCaddeSokak);
	}

	public void deleteCaddeSokak(CaddeSokak caddeSokak) {
		ortakDAO.deleteCaddeSokak(caddeSokak);
	}

	public void saveOrUpdateMahalleCaddeList(
			List<MahalleCaddeSokak> mahalleCaddeList) {
		ortakDAO.saveOrUpdateMahalleCaddeList(mahalleCaddeList);
	}

	public void saveOrUpdateMahalleCaddeSokak(
			MahalleCaddeSokak mahalleCaddeSokak) {
		ortakDAO.saveOrUpdateMahalleCaddeSokak(mahalleCaddeSokak);
	}

	public List<Adres> readAllAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit, int startRow, int maxResults) {
		return ortakDAO.readAllAdresByCriteria(mahalleCaddeSokak, adres,
				kurumSabit, startRow, maxResults);
	}

	public Long readTotalNumberAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit) {
		return ortakDAO.readTotalNumberAdresByCriteria(mahalleCaddeSokak,
				adres, kurumSabit);
	}

	public List<KurumSabit> readAllKurumSabitByCriteria(KurumSabit kurumSabit) {
		return ortakDAO.readAllKurumSabitByCriteria(kurumSabit);
	}

	public List<BagimsizKullanimDetay> readAllBagimsizKullanimDetay() {
		return ortakDAO.readAllBagimsizKullanimDetay();
	}

	public BagimsizKullanimDetay readBagimsizKullanimDetayByCriteria(
			Bagimsiz bagimsiz) {
		return ortakDAO.readBagimsizKullanimDetayByCriteria(bagimsiz);
	}

	public BagimsizKullanimSinif readBagimsizKullanimSinifByCriteria(Bina bina) {
		return ortakDAO.readBagimsizKullanimSinifByCriteria(bina);
	}

	public KurumSabit readKurumSabitByCriteria(KurumSabit kurumSabit) {
		return ortakDAO.readKurumSabitByCriteria(kurumSabit);
	}

	public List<Object> readAllMahalleCaddeSokakKadastroParselAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllMahalleCaddeSokakKadastroParselAdresByCriteria(
				mahalleCaddeSokak, adres, kurumSabit);
	}

	public List<Mahalle> readAllMahalleByKurum(KurumSabit kurumSabit) {
		return ortakDAO.readAllMahalleByKurum(kurumSabit);
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			Mahalle mahalle, KurumSabit kurumSabit) {
		return ortakDAO.readAllMahalleCaddeSokakByCriteria(mahalle, kurumSabit);
	}

	public Bagimsiz readAllBagimsizByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			Bagimsiz bagimsiz, KurumSabit kurumSabit) {
		return ortakDAO.readAllBagimsizByCriteria(mahalleCaddeSokak, adres,
				bagimsiz, kurumSabit);
	}

	public Long readMahalleCaddeSokakKayitSayisiByCriteria(
			KurumSabit kurumSabit, String aciklama, String sorguTipi) {
		return ortakDAO.readMahalleCaddeSokakKayitSayisiByCriteria(kurumSabit,
				aciklama, sorguTipi);
	}

	public List<IslemMenu> readAllIslemMenu() {
		return ortakDAO.readAllIslemMenu();
	}

	public List<CaddeSokak> readAllCaddeSokakByCriteria(KurumSabit kurumsabit) {
		return ortakDAO.readAllCaddeSokakByCriteria(kurumsabit);
	}

	public List<?> readAllObjectsByKurumSabit(Class entityClass,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllObjectsByKurumSabit(entityClass, kurumSabit);
	}

	public List<KadastroParsel> readAllKadastroParselByAdresAndMahalleCaddeSokak(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak) {

		return ortakDAO.readAllKadastroParselByAdresAndMahalleCaddeSokak(
				kurumSabit, adres, mahalleCaddeSokak);
	}

	public KadastroParsel readAllKadastroParselByAdres(KurumSabit kurumSabit,
			Adres adres) {

		return ortakDAO.readAllKadastroParselByAdres(kurumSabit, adres);
	}

	public Collection<?> readAllObjects(Class entityClass) {
		return ortakDAO.readAllObjects(entityClass);
	}

	public void saveOrUpdateAdres(Adres adres) {
		ortakDAO.saveOrUpdateAdres(adres);
	}

	public void saveOrUpdateAdresList(List<Adres> adresList) {
		ortakDAO.saveOrUpdateAdresList(adresList);
	}

	public void saveOrUpdateCaddeSokakList(List<CaddeSokak> caddeSokakList) {
		ortakDAO.saveOrUpdateCaddeSokakList(caddeSokakList);
	}

	public void readLazyObject(Object obj) {
		ortakDAO.readLazyObject(obj);
	}

	public List<Mahalle> readAllMahalleByCriteria(KurumSabit kurumSabit,
			String aciklama, int maxResultCount) {
		return ortakDAO.readAllMahalleByCriteria(kurumSabit, aciklama,
				maxResultCount);
	}

	public Long readMahalleSayisiByCriteria(KurumSabit kurumSabit,
			String aciklama) {
		return ortakDAO.readMahalleSayisiByCriteria(kurumSabit, aciklama);
	}

	public List<Adres> readAllAdresByAdresSorgulama(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi) {
		List<Object[]> temp = ortakDAO.readAllAdresByAdresSorgulama(
				mahalleCaddeSokak, adres, kurumSabit, baslangicKaydi,
				maksimumKayitSayisi);
		List<Adres> adresler = new ArrayList<Adres>();
		if (temp != null) {
			for (Object[] item : temp) {
				Long adresId = (Long) item[0];
				Long binaId = (Long) item[1];
				Long mahalleCaddeSokakId = (Long) item[2];
				Long mahalleId = (Long) item[3];
				Long caddeSokakId = (Long) item[4];
				String mahalleAciklama = (String) item[5];
				String caddeSokakAciklama = (String) item[6];
				Long kapiNo = (Long) item[7];
				String altKapiNo = (String) item[8];
				String aktifEh = (String) item[9];
				String adresTur = (String) item[10];
				String aciklama = (String) item[11];
				Bina bina = new Bina();
				bina.setId(binaId);
				Mahalle mahalle = new Mahalle();
				mahalle.setId(mahalleId);
				mahalle.setAciklama(mahalleAciklama);
				CaddeSokak caddeSokak = new CaddeSokak();
				caddeSokak.setId(caddeSokakId);
				caddeSokak.setAciklama(caddeSokakAciklama);
				MahalleCaddeSokak yeniMahalleCaddeSokak = new MahalleCaddeSokak();
				yeniMahalleCaddeSokak.setId(mahalleCaddeSokakId);
				yeniMahalleCaddeSokak.setMahalle(mahalle);
				yeniMahalleCaddeSokak.setCaddeSokak(caddeSokak);
				Adres yeniAdres = new Adres();
				yeniAdres.setId(adresId);
				yeniAdres.setBina(bina);
				yeniAdres.setMahalleCaddeSokak(yeniMahalleCaddeSokak);
				yeniAdres.setKurumSabit(kurumSabit);
				yeniAdres.setAciklama(aciklama);
				yeniAdres.setAdresTur(adresTur);
				yeniAdres.setAktifEh(aktifEh);
				yeniAdres.setAltKapiNo(altKapiNo);
				yeniAdres.setKapiNo(kapiNo);
				adresler.add(yeniAdres);
			}
		}
		return adresler;
	}

	public Long readBagimsizKayitSayisiByCriteria(Adres adres,
			KurumSabit kurumSabit) {
		return ortakDAO.readBagimsizKayitSayisiByCriteria(adres, kurumSabit);
	}

	public List<Bagimsiz> readAllBagimsizByAdresSorgulama(Adres adres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi) {
		List<Object[]> temp = ortakDAO.readAllBagimsizByAdresSorgulama(adres,
				kurumSabit, baslangicKaydi, maksimumKayitSayisi);
		List<Bagimsiz> bagimsizlar = new ArrayList<Bagimsiz>();
		if (temp != null) {
			for (Object[] item : temp) {
				Long bagimsizId = (Long) item[0];
				Long daireNo = (Long) item[1];
				String altDaireNo = (String) item[2];
				String katNo = (String) item[3];
				Long tuikSiraNo = (Long) item[4];
				Long bagimsizNo = (Long) item[5];
				Double yuzolcum = (Double) item[6];
				Date acilisTarih = (Date) item[7];
				Date kapanisTarih = (Date) item[8];
				String aktifEh = (String) item[9];
				String aciklama = (String) item[10];
				String bagimsizKullanimDetayAciklama = (String) item[11];
				Bagimsiz yeniBagimsiz = new Bagimsiz();
				yeniBagimsiz.setId(bagimsizId);
				yeniBagimsiz.setAciklama(aciklama);
				yeniBagimsiz.setAcilisTarih(acilisTarih);
				yeniBagimsiz.setAktifEh(aktifEh);
				yeniBagimsiz.setAltDaireNo(altDaireNo);
				yeniBagimsiz.setBagimsizNo(bagimsizNo);
				yeniBagimsiz.setDaireNo(daireNo);
				yeniBagimsiz.setKapanisTarih(kapanisTarih);
				yeniBagimsiz.setKatNo(katNo);
				yeniBagimsiz.setTuikSiraNo(tuikSiraNo);
				yeniBagimsiz.setYuzolcum(yuzolcum);
				BagimsizKullanimDetay bagimsizKullanimDetay = new BagimsizKullanimDetay();
				bagimsizKullanimDetay
						.setAciklama(bagimsizKullanimDetayAciklama);
				yeniBagimsiz.setBagimsizKullanimDetay(bagimsizKullanimDetay);
				bagimsizlar.add(yeniBagimsiz);
			}
		}
		return bagimsizlar;
	}

	public List<Mahalle> readAllMahalleByAdresSorgulama(KurumSabit kurumSabit) {
		List<Object[]> temp = ortakDAO
				.readAllMahalleByAdresSorgulama(kurumSabit);
		List<Mahalle> mahalleler = new ArrayList<Mahalle>();
		if (temp != null) {
			for (Object[] item : temp) {
				Long id = (Long) item[0];
				String aciklama = (String) item[1];
				Mahalle yeniMahalle = new Mahalle();
				yeniMahalle.setId(id);
				yeniMahalle.setAciklama(aciklama);
				yeniMahalle.setKurumSabit(kurumSabit);
				mahalleler.add(yeniMahalle);
			}
		}
		return mahalleler;
	}

	public List<Mahalle> readAllMahalleByMahalleList(KurumSabit kurumSabit,
			List<Mahalle> districtList, Integer maxResultCount) {
		return ortakDAO.readAllMahalleByMahalleList(kurumSabit, districtList,
				maxResultCount);
	}

	public List<CaddeSokak> readAllCaddeSokakByAdresSorgulama(
			KurumSabit kurumSabit, Mahalle mahalle) {
		List<Object[]> temp = ortakDAO.readAllCaddeSokakByAdresSorgulama(
				kurumSabit, mahalle);
		List<CaddeSokak> caddeSokaklar = new ArrayList<CaddeSokak>();
		if (temp != null) {
			for (Object[] item : temp) {
				Long id = (Long) item[0];
				String aciklama = (String) item[1];
				CaddeSokak yeniCaddeSokak = new CaddeSokak();
				yeniCaddeSokak.setId(id);
				yeniCaddeSokak.setAciklama(aciklama);
				yeniCaddeSokak.setKurumSabit(kurumSabit);
				caddeSokaklar.add(yeniCaddeSokak);
			}
		}
		return caddeSokaklar;
	}

	public List<CaddeSokak> readAllCaddeSokakByCaddeSokakList(
			KurumSabit kurumSabit, List<CaddeSokak> streetList,
			Integer maxResultCount) {
		return ortakDAO.readAllCaddeSokakByCaddeSokakList(kurumSabit,
				streetList, maxResultCount);
	}

	public List<Bina> readAllBinaByAdresSorgulama(Adres adres,
			KurumSabit kurumSabit) {
		List<Object[]> temp = ortakDAO.readAllBinaByAdresSorgulama(adres,
				kurumSabit);
		List<Bina> binalar = new ArrayList<Bina>();
		if (temp != null) {
			for (Object[] item : temp) {
				Long binaId = (Long) item[0];
				Long siteId = (Long) item[1];
				Long binaNo = (Long) item[2];
				String aciklama = (String) item[3];
				String apartmanBlokAd = (String) item[4];
				Long zoneBinaNo = (Long) item[5];
				String aktifEh = (String) item[6];
				Date acilisTarih = (Date) item[7];
				Date kapanisTarih = (Date) item[8];
				String bagimsizKullanimSinifAciklama = (String) item[9];
				String insaatSinifAciklama = (String) item[10];
				String insaatTurAciklama = (String) item[11];
				Bina bina = new Bina();
				bina.setId(binaId);
				bina.setAcilisTarih(acilisTarih);
				bina.setAktifEh(aktifEh);
				bina.setApartmanBlokAd(apartmanBlokAd);
				bina.setBinaNo(binaNo);
				bina.setKapanisTarih(kapanisTarih);
				bina.setZoneNo(zoneBinaNo);
				BagimsizKullanimSinif bagimsizKullanimSinif = new BagimsizKullanimSinif();
				bagimsizKullanimSinif
						.setAciklama(bagimsizKullanimSinifAciklama);
				bina.setBagimsizKullanimSinif(bagimsizKullanimSinif);
				InsaatSinif insaatSinif = new InsaatSinif();
				insaatSinif.setAciklama(insaatSinifAciklama);
				bina.setInsaatSinif(insaatSinif);
				InsaatTur insaatTur = new InsaatTur();
				insaatTur.setAciklama(insaatTurAciklama);
				bina.setInsaatTur(insaatTur);
				if (siteId != null) {
					Site site = new Site();
					site.setId(siteId);
					site.setAciklama(aciklama);
					bina.setSite(site);
				}
				binalar.add(bina);
			}
		}
		return binalar;
	}

	public Object readPropertyByObject(Long id, String property, Class entity) {
		Object item = null;
		if (id != null && property != null && !property.trim().equals("")
				&& entity != null) {
			try {
				item = ortakDAO.readPropertyByObject(id, property, entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	public Object readObjectById(Class entity, Long id) {
		Object item = null;
		if (id != null && entity != null) {
			try {
				item = ortakDAO.readObjectById(entity, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return item;
	}

	public void saveOrUpdateBagimsizList(List<Bagimsiz> bagimsizList) {
		this.ortakDAO.saveOrUpdateBagimsizList(bagimsizList);
	}

	public void saveOrUpdateBagimsiz(Bagimsiz bagimsiz) {
		this.ortakDAO.saveOrUpdateBagimsiz(bagimsiz);
	}

	public void saveOrUpdateMahalle(Mahalle mahalle) {
		this.ortakDAO.saveOrUpdateMahalle(mahalle);
	}

	public void saveOrUpdateKadastroParselTipi(
			KadastroParselTipi kadastroParselTipi) {
		this.ortakDAO.saveOrUpdateKadastroParselTipi(kadastroParselTipi);
	}

	public void saveOrUpdateKadastroParsel(KadastroParsel kadastroParsel) {
		this.ortakDAO.saveOrUpdateKadastroParsel(kadastroParsel);
	}

	public void saveOrUpdateTapuMahalle(TapuMahalle tapuMahalle) {
		this.ortakDAO.saveOrUpdateTapuMahalle(tapuMahalle);
	}

	public void saveOrUpdateKadastroHareketGrup(
			KadastroHareketGrup kadastroHareketGrup) {
		this.ortakDAO.saveOrUpdateKadastroHareketGrup(kadastroHareketGrup);
	}

	public void saveOrUpdateKadastroHareketKod(
			KadastroHareketKod kadastroHareketKod) {
		this.ortakDAO.saveOrUpdateKadastroHareketKod(kadastroHareketKod);
	}

	public void saveOrUpdateBilgiTipi(BilgiTipi bilgiTipi) {
		this.ortakDAO.saveOrUpdateBilgiTipi(bilgiTipi);
	}

	public void saveOrUpdateBinaCatiTur(BinaCatiTur binaCatiTur) {
		this.ortakDAO.saveOrUpdateBinaCatiTur(binaCatiTur);
	}

	public void saveOrUpdateDosemeTur(DosemeTur dosemeTur) {
		this.ortakDAO.saveOrUpdateDosemeTur(dosemeTur);
	}

	public void saveOrUpdateIsitmaTur(IsitmaTur isitmaTur) {
		this.ortakDAO.saveOrUpdateIsitmaTur(isitmaTur);
	}

	public void saveOrUpdateSicakSuTeminTur(SicakSuTeminTur sicakSuTeminTur) {
		this.ortakDAO.saveOrUpdateSicakSuTeminTur(sicakSuTeminTur);
	}

	public void saveOrUpdateTasiyiciSistem(TasiyiciSistem tasiyiciSistem) {
		this.ortakDAO.saveOrUpdateTasiyiciSistem(tasiyiciSistem);
	}

	public void saveOrUpdateUretimKaynak(UretimKaynak uretimKaynak) {
		this.ortakDAO.saveOrUpdateUretimKaynak(uretimKaynak);
	}

	public void saveOrUpdateYakitTur(YakitTur yakitTur) {
		this.ortakDAO.saveOrUpdateYakitTur(yakitTur);
	}

	public void saveOrUpdateYapiCepheTur(YapiCepheTur yapiCepheTur) {
		this.ortakDAO.saveOrUpdateYapiCepheTur(yapiCepheTur);
	}

	public void saveOrUpdateBina(Bina bina) {
		this.ortakDAO.saveOrUpdateBina(bina);
	}

	public void saveOrUpdateBinaParsel(BinaParsel binaParsel) {
		this.ortakDAO.saveOrUpdateBinaParsel(binaParsel);
	}

	public void saveOrUpdateKonutDurum(KonutDurum konutDurum) {
		this.ortakDAO.saveOrUpdateKonutDurum(konutDurum);
	}

	public void saveOrUpdateKonutSahiplikDurum(
			KonutSahiplikDurum konutSahiplikDurum) {
		this.ortakDAO.saveOrUpdateKonutSahiplikDurum(konutSahiplikDurum);
	}

	public void saveOrUpdateKonutTip(KonutTip konutTip) {
		this.ortakDAO.saveOrUpdateKonutTip(konutTip);
	}

	public void saveOrUpdateGelismislikDurum(GelismislikDurum gelismislikDurum) {
		this.ortakDAO.saveOrUpdateGelismislikDurum(gelismislikDurum);
	}

	public void saveOrUpdateKullanimSekli(KullanimSekli kullanimSekli) {
		this.ortakDAO.saveOrUpdateKullanimSekli(kullanimSekli);
	}

	public List<GelismislikDurum> readAllGelismislikDurumByCriteria(
			GelismislikDurum gelismislikDurum) {
		return this.ortakDAO
				.readAllGelismislikDurumByCriteria(gelismislikDurum);
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(KurumSabit kurumSabit,
			List<BagimsizKullanimSinif> bagimsizKullanimSinifList,
			List<BagimsizKullanimGrup> bagimsizKullanimGrupList,
			List<BagimsizKullanimDetay> bagimsizKullanimDetayList,
			Long maxResultCount) {
		return ortakDAO.readAllBagimsizByCriteria(kurumSabit,
				bagimsizKullanimSinifList, bagimsizKullanimGrupList,
				bagimsizKullanimDetayList, maxResultCount);
	}

	public List<KadastroParsel> readAllKadastroParselByKadastroSorgulama(
			KadastroParsel kadastroParsel, int baslangicKaydi,
			int maksimumKayitSayisi) {
		return ortakDAO.readAllKadastroParselByKadastroSorgulama(
				kadastroParsel, baslangicKaydi, maksimumKayitSayisi);
	}

	public List<Bagimsiz> readAllBagimsizByKadastroParselSorgulama(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel,
			int baslangicKaydi, int maksimumKayitSayisi) {
		List<Object[]> temp = ortakDAO
				.readAllBagimsizByKadastroParselSorgulama(kurumSabit,
						kadastroParsel, baslangicKaydi, maksimumKayitSayisi);
		List<Bagimsiz> bagimsizlar = new ArrayList<Bagimsiz>();
		if (temp != null) {
			for (Object[] item : temp) {
				Long bagimsizId = (Long) item[0];
				Long daireNo = (Long) item[1];
				String altDaireNo = (String) item[2];
				String katNo = (String) item[3];
				Long tuikSiraNo = (Long) item[4];
				Long bagimsizNo = (Long) item[5];
				Double yuzolcum = (Double) item[6];
				Date acilisTarih = (Date) item[7];
				Date kapanisTarih = (Date) item[8];
				String aktifEh = (String) item[9];
				String aciklama = (String) item[10];
				String bagimsizKullanimDetayAciklama = (String) item[11];
				Bagimsiz yeniBagimsiz = new Bagimsiz();
				yeniBagimsiz.setId(bagimsizId);
				yeniBagimsiz.setAciklama(aciklama);
				yeniBagimsiz.setAcilisTarih(acilisTarih);
				yeniBagimsiz.setAktifEh(aktifEh);
				yeniBagimsiz.setAltDaireNo(altDaireNo);
				yeniBagimsiz.setBagimsizNo(bagimsizNo);
				yeniBagimsiz.setDaireNo(daireNo);
				yeniBagimsiz.setKapanisTarih(kapanisTarih);
				yeniBagimsiz.setKatNo(katNo);
				yeniBagimsiz.setTuikSiraNo(tuikSiraNo);
				yeniBagimsiz.setYuzolcum(yuzolcum);
				BagimsizKullanimDetay bagimsizKullanimDetay = new BagimsizKullanimDetay();
				bagimsizKullanimDetay
						.setAciklama(bagimsizKullanimDetayAciklama);
				yeniBagimsiz.setBagimsizKullanimDetay(bagimsizKullanimDetay);
				bagimsizlar.add(yeniBagimsiz);
			}
		}
		return bagimsizlar;
	}

	public List<Bina> readAllBinaByKadastroSorgulama(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		List<Object[]> temp = ortakDAO.readAllBinaByKadastroSorgulama(
				kadastroParsel, kurumSabit);
		List<Bina> binalar = new ArrayList<Bina>();
		if (temp != null) {
			for (Object[] item : temp) {
				Long binaId = (Long) item[0];
				Long siteId = (Long) item[1];
				Long binaNo = (Long) item[2];
				String aciklama = (String) item[3];
				String apartmanBlokAd = (String) item[4];
				Long zoneBinaNo = (Long) item[5];
				String aktifEh = (String) item[6];
				Date acilisTarih = (Date) item[7];
				Date kapanisTarih = (Date) item[8];
				String bagimsizKullanimSinifAciklama = (String) item[9];
				String insaatSinifAciklama = (String) item[10];
				String insaatTurAciklama = (String) item[11];
				Bina bina = new Bina();
				bina.setId(binaId);
				bina.setAcilisTarih(acilisTarih);
				bina.setAktifEh(aktifEh);
				bina.setApartmanBlokAd(apartmanBlokAd);
				bina.setBinaNo(binaNo);
				bina.setKapanisTarih(kapanisTarih);
				bina.setZoneNo(zoneBinaNo);
				BagimsizKullanimSinif bagimsizKullanimSinif = new BagimsizKullanimSinif();
				bagimsizKullanimSinif
						.setAciklama(bagimsizKullanimSinifAciklama);
				bina.setBagimsizKullanimSinif(bagimsizKullanimSinif);
				InsaatSinif insaatSinif = new InsaatSinif();
				insaatSinif.setAciklama(insaatSinifAciklama);
				bina.setInsaatSinif(insaatSinif);
				InsaatTur insaatTur = new InsaatTur();
				insaatTur.setAciklama(insaatTurAciklama);
				bina.setInsaatTur(insaatTur);
				if (siteId != null) {
					Site site = new Site();
					site.setId(siteId);
					site.setAciklama(aciklama);
					bina.setSite(site);
				}
				binalar.add(bina);
			}
		}
		return binalar;
	}

	public Adres readAdresByKadastroSorgulama(Bagimsiz bagimsiz) {
		Object[] temp = ortakDAO.readAdresByKadastroSorgulama(bagimsiz);
		Adres adres = null;
		if (temp != null) {
			Long adresId = (Long) temp[0];
			Long binaId = (Long) temp[1];
			Long mahalleCaddeSokakId = (Long) temp[2];
			Long mahalleId = (Long) temp[3];
			Long caddeSokakId = (Long) temp[4];
			String mahalleAciklama = (String) temp[5];
			String caddeSokakAciklama = (String) temp[6];
			Long kapiNo = (Long) temp[7];
			String altKapiNo = (String) temp[8];
			Bina bina = new Bina();
			bina.setId(binaId);
			Mahalle mahalle = new Mahalle();
			mahalle.setId(mahalleId);
			mahalle.setAciklama(mahalleAciklama);
			CaddeSokak caddeSokak = new CaddeSokak();
			caddeSokak.setId(caddeSokakId);
			caddeSokak.setAciklama(caddeSokakAciklama);
			MahalleCaddeSokak yeniMahalleCaddeSokak = new MahalleCaddeSokak();
			yeniMahalleCaddeSokak.setId(mahalleCaddeSokakId);
			yeniMahalleCaddeSokak.setMahalle(mahalle);
			yeniMahalleCaddeSokak.setCaddeSokak(caddeSokak);
			adres = new Adres();
			adres.setId(adresId);
			adres.setBina(bina);
			adres.setMahalleCaddeSokak(yeniMahalleCaddeSokak);
			adres.setAltKapiNo(altKapiNo);
			adres.setKapiNo(kapiNo);
		}
		return adres;
	}

	public Bina readBinaByKadastroSorgulama(Bagimsiz bagimsiz) {
		Object[] temp = ortakDAO.readBinaByKadastroSorgulama(bagimsiz);
		Bina bina = null;
		if (temp != null) {
			Long binaId = (Long) temp[0];
			Long siteId = (Long) temp[1];
			Long binaNo = (Long) temp[2];
			String aciklama = (String) temp[3];
			String apartmanBlokAd = (String) temp[4];
			bina = new Bina();
			bina.setId(binaId);
			bina.setApartmanBlokAd(apartmanBlokAd);
			bina.setBinaNo(binaNo);
			if (siteId != null) {
				Site site = new Site();
				site.setId(siteId);
				site.setAciklama(aciklama);
				bina.setSite(site);
			}
		}
		return bina;
	}

	public <T> List<T> readAllObjectByIdList(Class entity, List<Long> idList) {
		return ortakDAO.readAllObjectByIdList(entity, idList);
	}

	public void saveOrUpdateBinaNitelik(BinaNitelik binaNitelik) {
		ortakDAO.saveOrUpdateBinaNitelik(binaNitelik);
	}

	public void saveOrUpdateBinaOrtakKullanim(
			BinaOrtakKullanim binaOrtakKullanim) {
		ortakDAO.saveOrUpdateBinaOrtakKullanim(binaOrtakKullanim);

	}

	public void saveOrUpdateBinaOrtakKullanimList(
			List<BinaOrtakKullanim> binaOrtakKullanimList) {
		ortakDAO.saveOrUpdateBinaOrtakKullanimList(binaOrtakKullanimList);

	}

	public void saveOrUpdateBinaParselList(List<BinaParsel> binaParselList) {
		ortakDAO.saveOrUpdateBinaParselList(binaParselList);

	}

	public void saveOrUpdateBinaTesisat(BinaTesisat binaTesisat) {
		ortakDAO.saveOrUpdateBinaTesisat(binaTesisat);
	}

	public void saveOrUpdateBinaTesisatList(List<BinaTesisat> binaTesisatList) {
		ortakDAO.saveOrUpdateBinaTesisatList(binaTesisatList);
	}

	public List<Bagimsiz> readAllBagimsizByBina(Bina bina) {
		return ortakDAO.readAllBagimsizByBina(bina);
	}

	public List<BinaOrtakKullanim> readAllBinaOrtakKullanimByBina(Bina bina) {
		return ortakDAO.readAllBinaOrtakKullanimByBina(bina);
	}

	public List<BinaParsel> readAllBinaParselByBina(Bina bina) {
		return ortakDAO.readAllBinaParselByBina(bina);
	}

	public List<BinaTesisat> readAllBinaTesisatTurByBina(Bina bina) {
		return ortakDAO.readAllBinaTesisatTurByBina(bina);
	}

	public List<BinaNitelik> readAllBinaNitelikByBina(Bina bina) {
		return ortakDAO.readAllBinaNitelikByBina(bina);
	}

	public void deleteDBObject(Object entity) {
		ortakDAO.deleteDBObject(entity);
	}

	public void deleteDBObjectList(Collection<?> liste) {
		ortakDAO.deleteDBObjectList(liste);

	}

	public void saveOrUpdateBinaNitelikList(List<BinaNitelik> binaNitelikList) {
		ortakDAO.saveOrUpdateBinaNitelikList(binaNitelikList);
	}

	public List<HareketKod> readAllHareketKod() {
		return ortakDAO.readAllHareketKod();
	}

	public Long readBinaParselSayisiByBina(Bina bina) {
		return ortakDAO.readBinaParselSayisiByBina(bina);
	}

	public List<Tapu> readAllTapuByKadastroSorgulama(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		List<Tapu> tapular = null;
		List<Object[]> temp = ortakDAO.readAllTapuByKadastroSorgulama(
				kadastroParsel, kurumSabit);
		if (temp != null) {
			tapular = new ArrayList<Tapu>(temp.size());
			for (Object[] item : temp) {
				Double arsaPay = (Double) item[0];
				Double hissePay = (Double) item[1];
				Double arsaPayda = (Double) item[2];
				Double hissePayda = (Double) item[3];
				Double yuzolcum = (Double) item[4];
				Date iktisapTarih = (Date) item[5];
				String edinSebep = (String) item[6];
				String kapanma = (String) item[7];
				Tapu tapu = new Tapu();
				tapu.setArsaPay(arsaPay);
				tapu.setArsaPayda(arsaPayda);
				tapu.setHissePay(hissePay);
				tapu.setHissePayda(hissePayda);
				tapu.setYuzolcum(yuzolcum);
				tapu.setIktisapTarih(iktisapTarih);
				tapu.setEdinSebep(edinSebep);
				tapu.setKapanma(kapanma);
				tapu.setKurumSabit(kurumSabit);
				tapular.add(tapu);
			}
		}
		return tapular;
	}

	public Long readBagimsizTapuSayisiByBagimsiz(Bagimsiz bagimsiz) {
		return ortakDAO.readBagimsizTapuSayisiByBagimsiz(bagimsiz);
	}

	public List<KadastroParsel> readKadastroParselByBinaParselSorgulama(
			Bina bina, KurumSabit kurumSabit) {
		List<KadastroParsel> kadastroParseller = null;
		List<Object[]> temp = ortakDAO.readKadastroParselByBinaParselSorgulama(
				bina, kurumSabit);
		if (temp != null) {
			kadastroParseller = new ArrayList<KadastroParsel>(temp.size());
			for (Object[] item : temp) {
				Long id = (Long) item[0];
				String pafta = (String) item[1];
				Long ada = (Long) item[2];
				Long parsel = (Long) item[3];
				String aktifEh = (String) item[4];
				Double alan = (Double) item[5];
				Date acilisTarih = (Date) item[6];
				Date kapanisTarih = (Date) item[7];
				KadastroParsel kadastroParsel = new KadastroParsel();
				kadastroParsel.setAcilisTarih(acilisTarih);
				kadastroParsel.setAda(ada);
				kadastroParsel.setAktifEh(aktifEh);
				kadastroParsel.setAlan(alan);
				kadastroParsel.setId(id);
				kadastroParsel.setKapanisTarih(kapanisTarih);
				kadastroParsel.setPafta(pafta);
				kadastroParsel.setParsel(parsel);
				kadastroParsel.setKurumSabit(kurumSabit);
				kadastroParseller.add(kadastroParsel);
			}
		}
		return kadastroParseller;
	}

	public List<BagimsizTapu> readBagimsizTapuByBagimsiz(Bagimsiz bagimsiz) {
		return ortakDAO.readBagimsizTapuByBagimsiz(bagimsiz);
	}

	public List<Tapu> readAllTapuByCriteria(KadastroParsel kadastroParsel,
			KurumSabit kurumSabit) {
		return ortakDAO.readAllTapuByCriteria(kadastroParsel, kurumSabit);
	}

	public Long readCaddeSokakTurKayitSayisiByCriteria(
			CaddeSokakTur caddeSokakTur) {
		return ortakDAO.readCaddeSokakTurKayitSayisiByCriteria(caddeSokakTur);
	}

	public List<CaddeSokakTur> readAllCaddeSokakTurByCriteria(
			CaddeSokakTur caddeSokakTur, int baslangicKaydi,
			int maksimumKayitSayisi) {
		return ortakDAO.readAllCaddeSokakTurByCriteria(caddeSokakTur,
				baslangicKaydi, maksimumKayitSayisi);
	}

	public List<GelismislikDurum> readAllGelismislikDurum() {
		return ortakDAO.readAllGelismislikDurum();
	}

	public void deleteGelismislikDurum(GelismislikDurum gelismislikDurum) {
		ortakDAO.deleteGelismislikDurum(gelismislikDurum);
	}

	public GelismislikDurum readGelismislikDurumByCriteria(Long id) {
		return ortakDAO.readGelismislikDurumByCriteria(id);
	}

	public void saveOrUpdateGelismislikDurumList(
			List<GelismislikDurum> gelismislikDurumList) {
		ortakDAO.saveOrUpdateGelismislikDurumList(gelismislikDurumList);
	}

	public Long readGelismislikDurumKayitSayisiByCriteria(
			GelismislikDurum gelismislikDurum) {
		return ortakDAO
				.readGelismislikDurumKayitSayisiByCriteria(gelismislikDurum);
	}

	public List<GelismislikDurum> readAllGelismislikDurumByCriteria(
			GelismislikDurum gelismislikDurum, int baslangicKaydi,
			int maksimumKayitSayisi) {
		return ortakDAO.readAllGelismislikDurumByCriteria(gelismislikDurum,
				baslangicKaydi, maksimumKayitSayisi);
	}

	public List<KullanimSekli> readAllKullanimSekli() {
		return ortakDAO.readAllKullanimSekli();
	}

	public KullanimSekli readKullanimSekliByCriteria(KullanimSekli kullanimSekli) {
		return ortakDAO.readKullanimSekliByCriteria(kullanimSekli);
	}

	public void saveOrUpdateKullanimSekliByCriteria(KullanimSekli kullanimSekli) {
		ortakDAO.saveOrUpdateKullanimSekliByCriteria(kullanimSekli);
	}

	public void deleteKullanimSekli(KullanimSekli kullanimSekli) {
		ortakDAO.deleteKullanimSekli(kullanimSekli);
	}

	public void saveOrUpdateKullanimSekliList(
			List<KullanimSekli> kullanimSekliList) {
		ortakDAO.saveOrUpdateKullanimSekli(kullanimSekliList);
	}

	public Long readTotalNumberKullanimSekliByCriteria(
			KullanimSekli kullanimSekli) {
		return ortakDAO.readTotalNumberKullanimSekliByCriteria(kullanimSekli);
	}

	public List<KullanimSekli> readAllKullanimSekliByCriteria(int startRow,
			int maxResults, KullanimSekli kullanimSekli) {
		return ortakDAO.readAllKullanimSekliByCriteria(startRow, maxResults,
				kullanimSekli);
	}

	public KonutSahiplikDurum readKonutSahiplikDurumByCriteria(
			KonutSahiplikDurum konutSahiplikDurum) {
		return ortakDAO.readKonutSahiplikDurumByCriteria(konutSahiplikDurum);
	}

	public List<KonutSahiplikDurum> readAllKonutSahiplikDurumByCriteria(
			int startRow, int maxResults, KonutSahiplikDurum konutSahiplikDurum) {
		return ortakDAO.readAllKonutSahiplikDurumByCriteria(startRow,
				maxResults, konutSahiplikDurum);
	}

	public Long readTotalNumberKonutSahiplikDurumByCriteria(
			KonutSahiplikDurum konutSahiplikDurum) {
		return ortakDAO
				.readTotalNumberKonutSahiplikDurumByCriteria(konutSahiplikDurum);
	}

	public Long readInsaatTurKayitSayisiByCriteria(InsaatTur insaatTur) {
		return ortakDAO.readInsaatTurKayitSayisiByCriteria(insaatTur);
	}

	public List<InsaatTur> readAllInsaatTurByCriteria(InsaatTur insaatTur,
			int baslangicKaydi, int maksimumKayitSayisi) {
		return ortakDAO.readAllInsaatTurByCriteria(insaatTur, baslangicKaydi,
				maksimumKayitSayisi);
	}

	public BagimsizKullanimSinif readBagimsizKullanimSinifByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		return ortakDAO
				.readBagimsizKullanimSinifByCriteria(bagimsizKullanimSinif);
	}

	public Long readBagimsizKullanimSinifKayitSayisiByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		return ortakDAO
				.readBagimsizKullanimSinifKayitSayisiByCriteria(bagimsizKullanimSinif);
	}

	public List<BagimsizKullanimSinif> readAllBagimsizKullanimSinifByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif, int baslangicKaydi,
			int maksimumKayitSayisi) {
		return ortakDAO.readAllBagimsizKullanimSinifByCriteria(
				bagimsizKullanimSinif, baslangicKaydi, maksimumKayitSayisi);
	}

	public Long readBagimsizKullanimGrupKayitSayisiByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif,
			BagimsizKullanimGrup bagimsizKullanimGrup) {
		return ortakDAO.readBagimsizKullanimGrupKayitSayisiByCriteria(
				bagimsizKullanimSinif, bagimsizKullanimGrup);
	}

	public List<BagimsizKullanimGrup> readAllBagimsizKullanimGrupByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif,
			BagimsizKullanimGrup bagimsizKullanimGrup, int baslangicKaydi,
			int maksimumKayitSayisi) {
		return ortakDAO.readAllBagimsizKullanimGrupByCriteria(
				bagimsizKullanimSinif, bagimsizKullanimGrup, baslangicKaydi,
				maksimumKayitSayisi);
	}

	public Long readBagimsizKullanimDetayKayitSayisiByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup,
			BagimsizKullanimDetay bagimsizKullanimDetay) {
		return ortakDAO.readBagimsizKullanimDetayKayitSayisiByCriteria(
				bagimsizKullanimGrup, bagimsizKullanimDetay);
	}

	public List<BagimsizKullanimDetay> readAllBagimsizKullanimDetayByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup,
			BagimsizKullanimDetay bagimsizKullanimDetay, int baslangicKaydi,
			int maksimumKayitSayisi) {
		return ortakDAO.readAllBagimsizKullanimDetayByCriteria(
				bagimsizKullanimGrup, bagimsizKullanimDetay, baslangicKaydi,
				maksimumKayitSayisi);
	}

	public List readDeletedRevisionsOfEntity(Class c, Long sicil,
			int startIndex, int maxResults) {
		return ortakDAO.readDeletedRevisionsOfEntity(c, sicil, startIndex,
				maxResults);
	}

	public Long readNumberOfDeletedRevisionsOfEntity(Class c, Long sicil) {
		return ortakDAO.readNumberOfDeletedRevisionsOfEntity(c, sicil);
	}

	public boolean readGelismislikDurumKaydiVarMi(
			GelismislikDurum gelismislikDurum) {
		return ortakDAO.readGelismislikDurumKaydiVarMi(gelismislikDurum);
	}

	public Long readSysAuditKayitSayisiByCriteria(SysAudit sysAudit) {
		return ortakDAO.readSysAuditKayitSayisiByCriteria(sysAudit);
	}

	public List<SysAudit> readAllSysAuditByCriteria(SysAudit sysAudit,
			int baslangicKaydi, int maksimumKayitSayisi) {
		return ortakDAO.readAllSysAuditByCriteria(sysAudit, baslangicKaydi,
				maksimumKayitSayisi);
	}

	public void saveOrUpdateSysAuditList(List<SysAudit> sysAuditList) {
		ortakDAO.saveOrUpdateSysAuditList(sysAuditList);
	}

	public List<Adres> readAllAdresByCriteria(Adres paramAdres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi) {
		return ortakDAO.readAllAdresByCriteria(paramAdres, kurumSabit,
				baslangicKaydi, maksimumKayitSayisi);
	}

	public Long readAdresKayitSayisiByCriteria(Adres paramAdres,
			KurumSabit kurumSabit) {
		return ortakDAO.readAdresKayitSayisiByCriteria(paramAdres, kurumSabit);
	}

	public Long readBagimsizKayitSayisiByCriteria(Bagimsiz bagimsiz) {
		return ortakDAO.readBagimsizKayitSayisiByCriteria(bagimsiz);
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(Bagimsiz bagimsiz) {
		return ortakDAO.readAllBagimsizByCriteria(bagimsiz);
	}

	public List<BinaParsel> readAllBinaParselByCriteria(BinaParsel binaParsel) {
		return ortakDAO.readAllBinaParselByCriteria(binaParsel);
	}

	public List<BagimsizTapu> readAllBagimsizTapuByCriteria(
			BagimsizTapu bagimsizTapu) {
		return ortakDAO.readAllBagimsizTapuByCriteria(bagimsizTapu);
	}

	public List<KadastroParsel> readAllKadastroParselByCriteria(
			KadastroParsel paramKadastroParsel, int baslangicKaydi,
			int maksimumKayitSayisi) {
		return ortakDAO.readAllKadastroParselByCriteria(paramKadastroParsel,
				baslangicKaydi, maksimumKayitSayisi);
	}

	public Long readKadastroParselKayitSayisiByCriteria(
			KadastroParsel paramKadastroParsel) {
		return ortakDAO
				.readKadastroParselKayitSayisiByCriteria(paramKadastroParsel);
	}

	public List<Bina> readAllBinaByCriteria(Bina bina) {
		return ortakDAO.readAllBinaByCriteria(bina);
	}

	public List<Tapu> readAllTapuByCriteria(Tapu tapu) {
		return ortakDAO.readAllTapuByCriteria(tapu);
	}

	public List<IslemMenu> readAllIslemMenuByCriteria(IslemMenu islemMenu,
			int baslangicKaydi, int maksimumKayitSayisi) {
		return ortakDAO.readAllIslemMenuByCriteria(islemMenu, baslangicKaydi,
				maksimumKayitSayisi);
	}

	public Long readIslemMenuKayitSayisiByCriteria(IslemMenu islemMenu) {
		return ortakDAO.readIslemMenuKayitSayisiByCriteria(islemMenu);
	}

	public void saveOrUpdateIslemMenuList(List<IslemMenu> islemMenuList) {
		ortakDAO.saveOrUpdateIslemMenuList(islemMenuList);
	}

	public void deleteIslemMenu(IslemMenu islemMenu) {
		ortakDAO.deleteIslemMenu(islemMenu);
	}

	public BagimsizKullanimSinif readBagimsizKullanimSinifByBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		return ortakDAO
				.readBagimsizKullanimSinifByBagimsizKullanimSinif(bagimsizKullanimSinif);
	}

	public List<Bolge> readAllBolgeByCriteria(Bolge bolge) {
		return ortakDAO.readAllBolgeByCriteria(bolge);
	}

	public List<Mahalle> readAllAktifMahalle(KurumSabit kurumsabit) {

		return ortakDAO.readAllAktifMahalle(kurumsabit);
	}

	public List<Number> readKapiNoByCaddeSokak(KurumSabit kurumSabit,
			CaddeSokak caddeSokak) {
		return ortakDAO.readKapiNoByCaddeSokak(kurumSabit, caddeSokak);
	}

	public List<String> readAltKapiNoByCaddeSokakKapiNo(KurumSabit kurumSabit,
			CaddeSokak caddeSokak, Number kapi_no) {
		return ortakDAO.readAltKapiNoByCaddeSokakKapiNo(kurumSabit, caddeSokak,
				kapi_no);
	}

	public List<Object[]> readGenelSearch(KurumSabit kurumSabit, String keyword) {
		return ortakDAO.readGenelSearch(kurumSabit, keyword);
	}

	public List<Object[]> readAllDagitimAskiBagimsizByCriteria(
			KurumSabit kurumSabit, KadastroParsel parsel, Mahalle mahalle) {
		return ortakDAO.readAllDagitimAskiBagimsizByCriteria(kurumSabit,
				parsel, mahalle);
	}

	public List<Object[]> readAllDagitimAskiBagimsizByAdres(
			KurumSabit kurumSabit, MahalleCaddeSokak mahalleCaddeSokak,
			Adres adres) {
		return ortakDAO.readAllDagitimAskiBagimsizByAdres(kurumSabit,
				mahalleCaddeSokak, adres);
	}

	public List<Object[]> readDagitimAdaGrafik(Long oneri_ada_kodu) {

		return ortakDAO.readDagitimAdaGrafik(oneri_ada_kodu);
	}

	public boolean insertIstekSikayet(long parselKodu, long bagimsizNo,
			String adi, String soyadi, String mail, String tel,
			String konuKodu, String konuAciklama, String ipAdres, Date zaman) {
		return ortakDAO.insertIstekSikayet(parselKodu, bagimsizNo, adi, soyadi,
				mail, tel, konuKodu, konuAciklama, ipAdres, zaman);
	}

	public boolean insertImarDagitimLog(long binaKodu, long parselKodu,
			String ip) {
		return ortakDAO.insertImarDagitimLog(binaKodu, parselKodu, ip);
	}

	public List<Object[]> readAllDagitimAskiBagimsizlar(long binaId) {
		return ortakDAO.readAllDagitimAskiBagimsizlar(binaId);
	}

	public List<Object[]> readAllDagitimAskiBinaBilgiler(long binaId) {
		return ortakDAO.readAllDagitimAskiBinaBilgiler(binaId);
	}

	public List<Object[]> readAllDagitimAskiSatisBilgi(long binaId) {
		return ortakDAO.readAllDagitimAskiSatisBilgi(binaId);
	}

	public boolean saveAUAUrlLog(String appName, String urlTip, String clientIp) {
		return ortakDAO.saveAUAUrlLog(appName, urlTip, clientIp);
	}
}