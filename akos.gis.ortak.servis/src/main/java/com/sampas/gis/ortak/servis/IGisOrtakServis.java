package com.sampas.gis.ortak.servis;

import java.util.List;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.gis.ortak.metadata.model.KurumUygMD_DB_Map;
import com.sampas.gis.ortak.model.Fonksiyon;
import com.sampas.gis.ortak.model.KadastroParselGecerliFonksiyon;
import com.sampas.gis.ortak.model.OcxFormTable;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.model.Plan;
import com.sampas.gis.ortak.model.PlanNot;
import com.sampas.gis.ortak.model.PlanTurleri;
import com.sampas.gis.ortak.tools.impl.EntityMetaData;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.maplayer.IFeatureLayer;


public interface IGisOrtakServis {

	public abstract List<OnemliYer> readOnemliyerByCriteria(OnemliYer onemliYer, KurumSabit kurumSabit);
	
	public abstract Fonksiyon readFonksiyonByCriteria(KadastroParsel parsel,PlanTurleri planTuru);
	
	public abstract Plan readPlanByCriteria(Fonksiyon fonksiyon, KurumSabit kurumSabit);
	
	public abstract Bina readBinaByOnemliYer(OnemliYer onemliYer,KurumSabit kurumSabit);
	
	public abstract Bagimsiz readBagimsizByOnemliYer(OnemliYer onemliYer, KurumSabit kurumSabit);
	
	public abstract OnemliYer readOnemliYerByBagimsiz(Bagimsiz bagimsiz);
	
	public abstract Adres readAdresByOnemliYer(OnemliYer onemliYer,KurumSabit kurumSabit);
	
	public abstract Adres readAdresByCriteries(KurumSabit kurumSabit,String mahalleAdi,String caddeSokakAdi,String kapiNo);
	
	public abstract List<Adres> readAdresListByCriteries(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo);
	
	public abstract EntityMetaData readEntityMetaData(String objectClassName);
	
	public abstract String readAcikAdresByOnemliyer(OnemliYer onemliYer,KurumSabit kurumSabit);
	
	public abstract List<PlanNot> readPlanNotByPlan(Plan plan,KurumSabit kurumSabit);
	
	public abstract Plan readPlanByCriteria(KadastroParsel kadastroParsel,KurumSabit kurumSabit);
	
	public abstract String readPlanBBKararTarihiAndDurumuByCriteria(Plan plan,KurumSabit kurumSabit);
	
	public abstract String readPlanBKararTarihiAndDurumuByCriteria(Plan plan,KurumSabit kurumSabit);
	
	public abstract boolean readHmaxByPlanID(Plan plan,KadastroParsel kadastroParsel);
	
	public abstract String readAcikAdresByBagimsiz(Bagimsiz bagimsiz, KurumSabit kurumSabit);
	
	public abstract boolean saveAddressList(List<Adres> addressList);
	
	public abstract List<OcxFormTable> readAllOcxFormTableByKatmanAdi(String katmanAdi);
	
	public abstract List<Fonksiyon> readFonksiyonlarByCriteria(KadastroParsel parsel, PlanTurleri planTuru);
	
	public abstract List<KadastroParselGecerliFonksiyon> readKadastroParselGecerliFonksiyonByCriteria(KadastroParsel parsel, KurumSabit kurumSabit);
	
	public abstract List<KurumUygMD_DB_Map> readAllKurumUygMD_DB_MapByCriteria( KurumUygMD_DB_Map kurumUygMDDBMap, int startIndex, int maxResults );
	
	public abstract void saveOrUpdateKurumUygMD_DB_Map ( KurumUygMD_DB_Map kurumUygMDDBMap );
	
	public abstract void deleteKurumUygMD_DB_Map ( KurumUygMD_DB_Map kurumUygMDDBMap );
	
	public abstract Long readKurumUygMD_DB_MapKayitSayisiByCriteria( KurumUygMD_DB_Map kurumUygMDDBMap );
	
	public abstract Adres findClosestAddressFromCoordinate(IFeatureLayer addressLayer, double lon, double lat, Long kurumSabitID);
	
	public abstract List<Adres> getAddressListFromCoordinateAndOffset(IFeatureLayer addressLayer, Double lon, Double lat, Double offset, Long kurumSabitID);

	public abstract List<Bina> getBuildingListFromCoordinateAndOffset(IFeatureLayer buildingLayer, Double lon, Double lat, Double offset, Long kurumSabitID);
	
	public abstract List<Plan> getPlanListFromParcelWithSpatialQuery(IFeatureLayer planLayer, IFeature parcel);
	
	public abstract Plan readPlanByID(Long planID);
	
	public abstract String checkParcelRestrictions(KadastroParsel parsel);
	
	
}