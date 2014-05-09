package com.sampas.akos.ortak.dao;

import java.util.Collection;
import java.util.List;

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
public interface OrtakDAO {
	
	public abstract List<BinaRisk> readBinaRiskBilgiByBinaId(Long p_binano);
	public abstract List<BinaBelge> readBelgelerByBinaNo(Long p_binano);
	
	public abstract Kullanicilar login(Long p_kurumKodu,String p_username,String p_password);
	
	public abstract List<Bina> readAllBina(KurumSabit kurumsabit) ;	
	
	public abstract List<Bina> readAllBinaByCriteriaNew (
			KurumSabit kurumSabit,Bina bina, Adres adres,KadastroParsel kadastroParsel,
			MahalleCaddeSokak mahalleCaddeSokak) ;
	
	public abstract List<Adres> readAllKadastroParselByAdresNew(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak) ;

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokak(KurumSabit kurumSabit,
			MahalleCaddeSokak mahalleCaddeSoka );
	
	public abstract List<KadastroParsel> readAllKadastroParselByParselMah(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel) ;
	
	public abstract Long checkSuAboneTahsisByAboneNo(Long pKurumKodu, Long aboneNo);
    public abstract String ServerStatusCheck() ;

	public abstract void saveOrUpdateKullanimSekli(
			List<KullanimSekli> kullanimSekliList);

	public abstract void deleteKullanimSekli(KullanimSekli kullanimSekli);

	public abstract void saveOrUpdateKullanimSekliByCriteria(
			KullanimSekli kullanimSekli);

	public abstract List<KullanimSekli> readAllKullanimSekli();

	public abstract KullanimSekli readKullanimSekliByCriteria(
			KullanimSekli kullanimSekli);

	public abstract Long readNumberOfRevisionsOfEntity(Class c, Long id);

	public abstract List readRevisionsOfEntity(Class c, Long id,
			int startIndex, int maxResults);

	public abstract List<?> readRevisionsOfEntityWithRevisionId(Class c,
			int revisionId, Long id);

	public abstract List<Number> readRevisions(Class c, Object obj);

	public abstract List<?> readAllObjectsByAciklama(Class obj,
			String aciklama, int maxResultCount);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			KurumSabit kurumSabit, String aciklama, int maxResultCount,
			String sorguTipi);

	public abstract List<Bagimsiz> readAllBagimsizByCriteria(Adres adres,
			KurumSabit kurumSabit);

	public abstract List<Bagimsiz> readAllBagimsizByCriteria(Bina bina,
			KurumSabit kurumSabit);

	public abstract List<Bagimsiz> readAllBagimsizByCriteria(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit);

	public abstract List<Bagimsiz> readAllBagimsizByBina(Bina bina,
			KurumSabit kurumSabit);

	public abstract List<Bina> readAllBinaByCriteria(Adres adres,
			KurumSabit kurumSabit);

	public abstract List<Bina> readAllBinaByCriteria(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit);

	public abstract KadastroParsel readKadastroParselByCriteria(Adres adres,
			KurumSabit kurumSabit);

	public abstract List<Site> readAllSiteByCriteria(Site site,
			KurumSabit kurumSabit, int maxOkunacakKayitSayisi);

	public abstract List<KadastroParsel> readAllKadastroParselByParsel(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel);

	public abstract List<KadastroParsel> readAllKadastroParselByParselList(
			KurumSabit kurumSabit, List<KadastroParsel> kadastroParselList,
			Integer maxResultCount);

	public abstract List<Adres> readAllAdresByAdresAndMahalleCaddeSokak(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak);

	public abstract List<Adres> readAllAdresByAdresList(KurumSabit kurumSabit,
			List<Adres> addressList);

	public abstract List<YapiAda> readAllYapiAdaByCriteria(YapiAda yapiAda,
			KurumSabit kurumSabit, int maxOkunacakKayitSayisi);

	public abstract List<CaddeSokak> readAllCaddeSokakByCriteria(
			KurumSabit kurumsabit, Mahalle mahalle);

	public abstract List<CaddeSokak> readAllCaddeSokakByCriteria(
			CaddeSokak caddeSokak, KurumSabit kurumSabit);

	public abstract List<Mahalle> readAllMahalle(KurumSabit kurumsabit);

	public abstract MahalleCaddeSokak readMahalleCaddeSokakByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, KurumSabit kurumSabit);

	public abstract List<Bina> readAllBinaByCriteria(Bina bina,
			KurumSabit kurumsabit);

	public abstract Long readMaxIdForObject(Class entityClass);

	public abstract List<KadastroParsel> readAllKadastroParselByParselAndMahalle(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel,
			Mahalle mahalle);

	public abstract List<Adres> readAllAdresByCriteria(KurumSabit kurumSabit,
			Adres adres, MahalleCaddeSokak mahalleCaddeSokak, Bina bina);

	public abstract List<Adres> readAllAdresByKadastroParsel(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit);

	public abstract List<Adres> readAllAdresByBagimsiz(Bagimsiz bagimsiz,
			KurumSabit kurumSabit);

	public abstract Bina readBinaByAdres(Adres adres, KurumSabit kurumSabit);

	public abstract Bina readBinaByBagimsiz(Bagimsiz bagimsiz,
			KurumSabit kurumSabit);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByBina(
			Bina bina, KurumSabit kurumSabit);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByAdres(
			Adres adres, KurumSabit kurumSabit);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByAdresler(
			List<Adres> adresler, KurumSabit kurumSabit, int maxResultCount);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByBagimsiz(
			Bagimsiz bagimsiz, KurumSabit kurumSabit);

	public abstract List<Adres> readAllAdresByBina(Bina bina,
			KurumSabit kurumSabit);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByMahalleCaddeSokak(
			MahalleCaddeSokak mahalleCaddeSokak, KurumSabit kurumSabit);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByKadastroParsel(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit);

	public abstract List<Mahalle> readAllMahalleByCriteria(Mahalle mahalle,
			KurumSabit kurumSabit);

	public abstract List<BagimsizKullanimSinif> readAllBagimsizKullanimSinif();

	public abstract void saveOrUpdateBagimsizKullanimSinifList(
			List<BagimsizKullanimSinif> bagimsizKullanimSinifList);

	public abstract void deleteBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif);

	public abstract void deleteBagimsizKullanimGrup(
			BagimsizKullanimGrup bagimsizKullanimGrup);

	public abstract void saveOrUpdateBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif);

	public abstract List<BagimsizKullanimGrup> readAllBagimsizKullanimGrupByKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif);

	public abstract BagimsizKullanimGrup readAllBagimsizKullanimGrupByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup);

	public abstract void deleteBagimsizKullanimDetay(
			BagimsizKullanimDetay bagimsizKullanimDetay);

	public abstract void saveOrUpdateBagimsizKullanimGrup(
			BagimsizKullanimGrup bagimsizKullanimGrup);

	public abstract BagimsizKullanimDetay readBagimsizKullanimDetayByCriteria(
			BagimsizKullanimDetay bagimsizKullanimDetay);

	public abstract List<BagimsizKullanimDetay> readAllBagimsizKullanimDetayByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup);

	public abstract void saveOrUpdateBagimsizKullanimDetay(
			BagimsizKullanimDetay bagimsizKullanimDetay);

	public abstract List<BagimsizKullanimGrup> readAllBagimsizKullanimGrup();

	public abstract void saveOrUpdateBagimsizKullanimDetayList(
			List<BagimsizKullanimDetay> bagimsizKullanimDetayList);

	public abstract void saveOrUpdateBagimsizKullanimGrupList(
			List<BagimsizKullanimGrup> bagimsizKullanimGrupList);

	public abstract Long readLastIdFromDbObject(Class entityClass);

	public abstract List<CaddeSokakTur> readAllCaddeSokakTur();

	public abstract void saveOrUpdateCaddeSokak(CaddeSokak caddeSokak);

	public abstract void deleteMahalleCaddeSokak(
			MahalleCaddeSokak mahalleCaddeSokak);

	public abstract void deleteCaddeSokak(CaddeSokak caddeSokak);

	public abstract void saveOrUpdateMahalleCaddeList(
			List<MahalleCaddeSokak> mahalleCaddeList);

	public abstract void saveOrUpdateMahalleCaddeSokak(
			MahalleCaddeSokak mahalleCaddeSokak);

	public abstract List<Adres> readAllAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit, int startRow, int maxResults);

	public abstract Long readTotalNumberAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit);

	public abstract List<KurumSabit> readAllKurumSabitByCriteria(
			KurumSabit kurumSabit);

	public abstract BagimsizKullanimDetay readBagimsizKullanimDetayByCriteria(
			Bagimsiz bagimsiz);

	public abstract BagimsizKullanimSinif readBagimsizKullanimSinifByCriteria(
			Bina bina);

	public abstract KurumSabit readKurumSabitByCriteria(KurumSabit kurumSabit);

	public abstract List<Object> readAllMahalleCaddeSokakKadastroParselAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit);

	public abstract List<Mahalle> readAllMahalleByKurum(KurumSabit kurumSabit);

	public abstract List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			Mahalle mahalle, KurumSabit kurumSabit);

	public abstract List<BagimsizKullanimDetay> readAllBagimsizKullanimDetay();

	public abstract Bagimsiz readAllBagimsizByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			Bagimsiz bagimsiz, KurumSabit kurumSabit);

	public abstract Long readMahalleCaddeSokakKayitSayisiByCriteria(
			KurumSabit kurumSabit, String aciklama, String sorguTipi);

	public abstract List<IslemMenu> readAllIslemMenu();

	public abstract List<CaddeSokak> readAllCaddeSokakByCriteria(
			KurumSabit kurumsabit);

	public abstract List<?> readAllObjectsByKurumSabit(Class entityClass,
			KurumSabit kurumSabit);

	public abstract List<KadastroParsel> readAllKadastroParselByAdresAndMahalleCaddeSokak(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak);

	public abstract KadastroParsel readAllKadastroParselByAdres(
			KurumSabit kurumSabit, Adres adres);

	public abstract Collection<?> readAllObjects(Class entityClass);

	public abstract void saveOrUpdateAdres(Adres adres);

	public abstract void saveOrUpdateAdresList(List<Adres> adresList);

	public abstract void saveOrUpdateCaddeSokakList(
			List<CaddeSokak> caddeSokakList);

	public abstract void readLazyObject(Object obj);

	public abstract List<Mahalle> readAllMahalleByCriteria(
			KurumSabit kurumSabit, String aciklama, int maxResultCount);

	public abstract Long readMahalleSayisiByCriteria(KurumSabit kurumSabit,
			String aciklama);

	public abstract List<Object[]> readAllAdresByAdresSorgulama(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi);

	public abstract Long readBagimsizKayitSayisiByCriteria(Adres adres,
			KurumSabit kurumSabit);

	public abstract List<Object[]> readAllBagimsizByAdresSorgulama(Adres adres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi);

	public abstract List<Object[]> readAllMahalleByAdresSorgulama(
			KurumSabit kurumSabit);

	public abstract List<Mahalle> readAllMahalleByMahalleList(
			KurumSabit kurumSabit, List<Mahalle> districtList,
			Integer maxResultCount);

	public abstract List<Object[]> readAllCaddeSokakByAdresSorgulama(
			KurumSabit kurumSabit, Mahalle mahalle);

	public abstract List<CaddeSokak> readAllCaddeSokakByCaddeSokakList(
			KurumSabit kurumSabit, List<CaddeSokak> streetList,
			Integer maxResultCount);

	public abstract List<Object[]> readAllBinaByAdresSorgulama(Adres adres,
			KurumSabit kurumSabit);

	public abstract Object readPropertyByObject(Long id, String property,
			Class entity);

	public abstract Object readObjectById(Class entity, Long id);

	public abstract void saveOrUpdateBagimsiz(Bagimsiz bagimsiz);

	public abstract void saveOrUpdateBagimsizList(List<Bagimsiz> bagimsizList);

	public abstract void saveOrUpdateMahalle(Mahalle mahalle);

	public abstract void saveOrUpdateKadastroParselTipi(
			KadastroParselTipi kadastroParselTipi);

	public abstract void saveOrUpdateKadastroParsel(
			KadastroParsel kadastroParsel);

	public abstract void saveOrUpdateTapuMahalle(TapuMahalle tapuMahalle);

	public abstract void saveOrUpdateKadastroHareketGrup(
			KadastroHareketGrup kadastroHareketGrup);

	public abstract void saveOrUpdateKadastroHareketKod(
			KadastroHareketKod kadastroHareketKod);

	public abstract void saveOrUpdateBilgiTipi(BilgiTipi bilgiTipi);

	public abstract void saveOrUpdateBinaCatiTur(BinaCatiTur binaCatiTur);

	public abstract void saveOrUpdateDosemeTur(DosemeTur dosemeTur);

	public abstract void saveOrUpdateIsitmaTur(IsitmaTur isitmaTur);

	public abstract void saveOrUpdateYakitTur(YakitTur yakitTur);

	public abstract void saveOrUpdateSicakSuTeminTur(
			SicakSuTeminTur sicakSuTeminTur);

	public abstract void saveOrUpdateTasiyiciSistem(
			TasiyiciSistem tasiyiciSistem);

	public abstract void saveOrUpdateUretimKaynak(UretimKaynak uretimKaynak);

	public abstract void saveOrUpdateYapiCepheTur(YapiCepheTur yapiCepheTur);

	public abstract void saveOrUpdateBina(Bina bina);

	public abstract void saveOrUpdateBinaParsel(BinaParsel binaParsel);

	public abstract void saveOrUpdateKonutDurum(KonutDurum konutDurum);

	public abstract void saveOrUpdateKonutSahiplikDurum(
			KonutSahiplikDurum konutSahiplikDurum);

	public abstract void saveOrUpdateKonutTip(KonutTip konutTip);

	public abstract void saveOrUpdateGelismislikDurum(
			GelismislikDurum gelismislikDurum);

	public abstract void saveOrUpdateKullanimSekli(KullanimSekli kullanimSekli);

	public abstract List<GelismislikDurum> readAllGelismislikDurumByCriteria(
			GelismislikDurum gelismislikDurum);

	public abstract List<Bagimsiz> readAllBagimsizByCriteria(
			KurumSabit kurumSabit,
			List<BagimsizKullanimSinif> bagimsizKullanimSinifList,
			List<BagimsizKullanimGrup> bagimsizKullanimGrupList,
			List<BagimsizKullanimDetay> bagimsizKullanimDetayList,
			Long maxResultCount);

	public abstract List<KadastroParsel> readAllKadastroParselByKadastroSorgulama(
			KadastroParsel kadastroParsel, int baslangicKaydi,
			int maksimumKayitSayisi);

	public abstract List<Object[]> readAllBagimsizByKadastroParselSorgulama(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel,
			int baslangicKaydi, int maksimumKayitSayisi);

	public abstract List<Object[]> readAllBinaByKadastroSorgulama(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit);

	public abstract Object[] readAdresByKadastroSorgulama(Bagimsiz bagimsiz);

	public abstract Object[] readBinaByKadastroSorgulama(Bagimsiz bagimsiz);

	public abstract <T> List<T> readAllObjectByIdList(Class entity,
			List<Long> idList);

	public abstract void saveOrUpdateBinaTesisat(BinaTesisat binaTesisat);

	public abstract void saveOrUpdateBinaTesisatList(
			List<BinaTesisat> binaTesisatList);

	public abstract void saveOrUpdateBinaParselList(
			List<BinaParsel> binaParselList);

	public abstract void saveOrUpdateBinaNitelik(BinaNitelik binaNitelik);

	public abstract void saveOrUpdateBinaOrtakKullanim(
			BinaOrtakKullanim binaOrtakKullanim);

	public abstract void saveOrUpdateBinaOrtakKullanimList(
			List<BinaOrtakKullanim> binaOrtakKullanimList);

	public abstract List<BinaTesisat> readAllBinaTesisatTurByBina(Bina bina);

	public abstract List<BinaOrtakKullanim> readAllBinaOrtakKullanimByBina(
			Bina bina);

	public abstract List<Bagimsiz> readAllBagimsizByBina(Bina bina);

	public abstract List<BinaParsel> readAllBinaParselByBina(Bina bina);

	public abstract List<BinaNitelik> readAllBinaNitelikByBina(Bina bina);

	public abstract void deleteDBObject(Object entity);

	public abstract void deleteDBObjectList(Collection<?> liste);

	public abstract void saveOrUpdateBinaNitelikList(
			List<BinaNitelik> binaNitelikList);

	public abstract List<HareketKod> readAllHareketKod();

	public abstract Long readBinaParselSayisiByBina(Bina bina);

	public abstract List<Object[]> readAllTapuByKadastroSorgulama(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit);

	public abstract Long readBagimsizTapuSayisiByBagimsiz(Bagimsiz bagimsiz);

	public abstract List<BagimsizTapu> readBagimsizTapuByBagimsiz(
			Bagimsiz bagimsiz);

	public abstract List<Object[]> readKadastroParselByBinaParselSorgulama(
			Bina bina, KurumSabit kurumSabit);

	public abstract List<Tapu> readAllTapuByCriteria(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit);

	public abstract Long readCaddeSokakTurKayitSayisiByCriteria(
			CaddeSokakTur caddeSokakTur);

	public abstract List<CaddeSokakTur> readAllCaddeSokakTurByCriteria(
			CaddeSokakTur caddeSokakTur, int baslangicKaydi,
			int maksimumKayitSayisi);

	public abstract List<GelismislikDurum> readAllGelismislikDurum();

	public abstract void deleteGelismislikDurum(
			GelismislikDurum gelismislikDurum);

	public abstract GelismislikDurum readGelismislikDurumByCriteria(Long id);

	public abstract void saveOrUpdateGelismislikDurumList(
			List<GelismislikDurum> gelismislikDurumList);

	public abstract Long readGelismislikDurumKayitSayisiByCriteria(
			GelismislikDurum gelismislikDurum);

	public abstract List<GelismislikDurum> readAllGelismislikDurumByCriteria(
			GelismislikDurum gelismislikDurum, int baslangicKaydi,
			int maksimumKayitSayisi);

	public abstract Long readTotalNumberKullanimSekliByCriteria(
			KullanimSekli kullanimSekli);

	public abstract List<KullanimSekli> readAllKullanimSekliByCriteria(
			int startRow, int maxResults, KullanimSekli KullanimSekli);

	public abstract KonutSahiplikDurum readKonutSahiplikDurumByCriteria(
			KonutSahiplikDurum konutSahiplikDurum);

	public abstract List<KonutSahiplikDurum> readAllKonutSahiplikDurumByCriteria(
			int startRow, int maxResults, KonutSahiplikDurum konutSahiplikDurum);

	public abstract Long readTotalNumberKonutSahiplikDurumByCriteria(
			KonutSahiplikDurum konutSahiplikDurum);

	public abstract Long readInsaatTurKayitSayisiByCriteria(InsaatTur insaatTur);

	public abstract List<InsaatTur> readAllInsaatTurByCriteria(
			InsaatTur insaatTur, int baslangicKaydi, int maksimumKayitSayisi);

	public abstract BagimsizKullanimSinif readBagimsizKullanimSinifByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif);

	public abstract Long readBagimsizKullanimSinifKayitSayisiByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif);

	public abstract List<BagimsizKullanimSinif> readAllBagimsizKullanimSinifByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif, int baslangicKaydi,
			int maksimumKayitSayisi);

	public abstract Long readBagimsizKullanimGrupKayitSayisiByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif,
			BagimsizKullanimGrup bagimsizKullanimGrup);

	public abstract List<BagimsizKullanimGrup> readAllBagimsizKullanimGrupByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif,
			BagimsizKullanimGrup bagimsizKullanimGrup, int baslangicKaydi,
			int maksimumKayitSayisi);

	public abstract Long readBagimsizKullanimDetayKayitSayisiByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup,
			BagimsizKullanimDetay bagimsizKullanimDetay);

	public abstract List<BagimsizKullanimDetay> readAllBagimsizKullanimDetayByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup,
			BagimsizKullanimDetay bagimsizKullanimDetay, int baslangicKaydi,
			int maksimumKayitSayisi);

	public abstract List readDeletedRevisionsOfEntity(Class c, Long sicil,
			int startIndex, int maxResults);

	public abstract Long readNumberOfDeletedRevisionsOfEntity(Class c,
			Long sicil);

	public abstract boolean readGelismislikDurumKaydiVarMi(
			GelismislikDurum gelismislikDurum);

	public abstract Long readSysAuditKayitSayisiByCriteria(SysAudit sysAudit);

	public abstract List<SysAudit> readAllSysAuditByCriteria(SysAudit sysAudit,
			int baslangicKaydi, int maksimumKayitSayisi);

	public abstract void saveOrUpdateSysAuditList(List<SysAudit> sysAuditList);

	public abstract List<Adres> readAllAdresByCriteria(Adres paramAdres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi);

	public abstract Long readAdresKayitSayisiByCriteria(Adres paramAdres,
			KurumSabit kurumSabit);

	public abstract Long readBagimsizKayitSayisiByCriteria(Bagimsiz bagimsiz);

	public abstract List<Bagimsiz> readAllBagimsizByCriteria(Bagimsiz bagimsiz);

	public abstract List<BinaParsel> readAllBinaParselByCriteria(
			BinaParsel binaParsel);

	public abstract List<BagimsizTapu> readAllBagimsizTapuByCriteria(
			BagimsizTapu bagimsizTapu);

	public abstract List<KadastroParsel> readAllKadastroParselByCriteria(
			KadastroParsel paramKadastroParsel, int baslangicKaydi,
			int maksimumKayitSayisi);

	public abstract Long readKadastroParselKayitSayisiByCriteria(
			KadastroParsel paramKadastroParsel);

	public abstract List<Bina> readAllBinaByCriteria(Bina bina);

	public abstract List<Tapu> readAllTapuByCriteria(Tapu tapu);

	public abstract List<IslemMenu> readAllIslemMenuByCriteria(
			IslemMenu islemMenu, int baslangicKaydi, int maksimumKayitSayisi);

	public abstract Long readIslemMenuKayitSayisiByCriteria(IslemMenu islemMenu);

	public abstract void saveOrUpdateIslemMenuList(List<IslemMenu> islemMenuList);

	public abstract void deleteIslemMenu(IslemMenu islemMenu);

	public abstract BagimsizKullanimSinif readBagimsizKullanimSinifByBagimsizKullanimSinif(BagimsizKullanimSinif bagimsizKullanimSinif);

	public abstract List<Bolge> readAllBolgeByCriteria(Bolge bolge);

	public abstract List<Mahalle> readAllAktifMahalle(KurumSabit kurumsabit);

	public abstract List<Number> readKapiNoByCaddeSokak(KurumSabit kurumSabit,CaddeSokak caddeSokak);

	public abstract List<String> readAltKapiNoByCaddeSokakKapiNo(KurumSabit kurumSabit,CaddeSokak caddeSokak,Number kapi_no);
	
	public abstract List<Object[]> readGenelSearch(KurumSabit kurumSabit,String keyword);
	
	public abstract List<Object[]> readAllDagitimAskiBagimsizByCriteria(KurumSabit kurumSabit,KadastroParsel parsel,Mahalle mahalle);
	
	public abstract List<Object[]> readAllDagitimAskiBagimsizByAdres(KurumSabit kurumSabit,MahalleCaddeSokak mahalleCaddeSokak,Adres adres);
	
	public abstract List<Object[]> readDagitimAdaGrafik(Long oneri_ada_kodu);
	
	public abstract boolean insertIstekSikayet(long parselKodu,
			long bagimsizNo,String adi,String soyadi,String mail,String tel,String konuKodu,
			String konuAciklama,String ipAdres,java.util.Date zaman);
	public abstract boolean insertImarDagitimLog(long binaKodu,long parselKodu,String ip);
	public abstract List<Object[]> readAllDagitimAskiBagimsizlar(long binaId);
	public abstract List<Object[]> readAllDagitimAskiBinaBilgiler(long binaId);	
	public abstract List<Object[]> readAllDagitimAskiSatisBilgi(long binaId);
	public abstract boolean saveAUAUrlLog(String appName,String urlTip,String clientIp);
}