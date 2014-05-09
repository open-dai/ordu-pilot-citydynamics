package com.sampas.gis.ortak.servis.impl;

import java.util.ArrayList;
import java.util.List;
import com.sampas.akos.idariyapi.model.Il;
import com.sampas.akos.idariyapi.model.Ilce;
import com.sampas.akos.idariyapi.servis.IdariYapiServis;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.gis.ortak.dao.IGisOrtakDAO;
import com.sampas.gis.ortak.metadata.model.KurumUygMD_DB_Map;
import com.sampas.gis.ortak.model.Fonksiyon;
import com.sampas.gis.ortak.model.KadastroParselGecerliFonksiyon;
import com.sampas.gis.ortak.model.OcxFormTable;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.model.Plan;
import com.sampas.gis.ortak.model.PlanNot;
import com.sampas.gis.ortak.model.PlanTurleri;
import com.sampas.gis.ortak.poi.search.servis.IClosestFeatureFinder;
import com.sampas.gis.ortak.poi.search.servis.impl.ClosestFeatureFinderImpl;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.gis.ortak.tools.impl.EntityMetaData;
import com.sampas.socbs.base.spatial.query.services.IIntersectsQuery;
import com.sampas.socbs.base.spatial.query.services.impl.SmpIntersectsQuery;
import com.sampas.socbs.base.spatial.query.services.returntypes.IGeomFiltersReturnType;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureID;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.maplayer.IFeatureLayer;
import com.sampas.socbs.core.tools.IFeatureIDTools;
import com.sampas.socbs.core.tools.impl.FeatureIDTools;


public class GisOrtakServis implements IGisOrtakServis {
	
	private IGisOrtakDAO gisOrtakDAO;
	
	private IdariYapiServis idariYapiServis;
	
	private OrtakServis ortakServis;
	
	private static IFeatureIDTools featureIDTools = new FeatureIDTools();
	
	
	public IdariYapiServis getIdariYapiServis() {
		return idariYapiServis;
	}

	public void setIdariYapiServis(IdariYapiServis idariYapiServis) {
		this.idariYapiServis = idariYapiServis;
	}

	public IGisOrtakDAO getGisOrtakDAO() {
		return gisOrtakDAO;
	}

	public void setGisOrtakDAO(IGisOrtakDAO gisOrtakDAO) {
		this.gisOrtakDAO = gisOrtakDAO;
	}
	
	public OrtakServis getOrtakServis() {
		return ortakServis;
	}

	public void setOrtakServis(OrtakServis ortakServis) {
		this.ortakServis = ortakServis;
	}
	
	public List<OnemliYer> readOnemliyerByCriteria(OnemliYer onemliYer, KurumSabit kurumSabit) {
		
		return (gisOrtakDAO.readOnemliyerByCriteria(onemliYer, kurumSabit));
	}
	
	public Fonksiyon readFonksiyonByCriteria(KadastroParsel parsel, PlanTurleri planTuru) {
		return (gisOrtakDAO.readFonksiyonByCriteria(parsel, planTuru));
	}

	public Plan readPlanByCriteria(Fonksiyon fonksiyon, KurumSabit kurumSabit) {
		return gisOrtakDAO.readPlanByCriteria(fonksiyon, kurumSabit);
	}

	public Bina readBinaByOnemliYer(OnemliYer onemliYer,KurumSabit kurumSabit) {
		
		return gisOrtakDAO.readBinaByOnemliYer(onemliYer, kurumSabit);
	}

	public Bagimsiz readBagimsizByOnemliYer(OnemliYer onemliYer, KurumSabit kurumSabit) {
		return gisOrtakDAO.readBagimsizByOnemliYer(onemliYer, kurumSabit);
	}

	public OnemliYer readOnemliYerByBagimsiz(Bagimsiz bagimsiz) {
		return gisOrtakDAO.readOnemliYerByBagimsiz(bagimsiz);
	}

	public Adres readAdresByOnemliYer(OnemliYer onemliYer,KurumSabit kurumSabit) {
		return gisOrtakDAO.readAdresByOnemliYer(onemliYer,kurumSabit);
	}

	public Adres readAdresByCriteries(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo) {
		return gisOrtakDAO.readAdresByCriteries(kurumSabit, mahalleAdi, caddeSokakAdi, kapiNo);
	}
	
	public List<Adres> readAdresListByCriteries(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo) {
		return gisOrtakDAO.readAdresListByCriteries(kurumSabit, mahalleAdi, caddeSokakAdi, kapiNo);
	}

	public EntityMetaData readEntityMetaData(String objectClassName) {
		
		return gisOrtakDAO.readEntityMetaData(objectClassName);
	}

	public String readAcikAdresByOnemliyer(OnemliYer onemliYer, KurumSabit kurumSabit) {
		
		String acikAdres = gisOrtakDAO.readAcikAdresByOnemliyer(onemliYer, kurumSabit);
		
		Adres adres = gisOrtakDAO.readAdresByOnemliYer(onemliYer, kurumSabit);
		
		Ilce ilce = idariYapiServis.readIlceByCriteria(adres, kurumSabit);
		
		Il il = idariYapiServis.readIlByIlceCriteria(ilce);
		
		acikAdres += " " + ilce.getAciklama() + " / " + il.getAciklama();

		return acikAdres;
	}

	public List<PlanNot> readPlanNotByPlan(Plan plan, KurumSabit kurumSabit) {
		
		return gisOrtakDAO.readPlanNotByPlan(plan, kurumSabit);
	}

	public Plan readPlanByCriteria(KadastroParsel kadastroParsel,
			KurumSabit kurumSabit) {
		
		return gisOrtakDAO.readPlanByCriteria(kadastroParsel, kurumSabit);
	}

	public String readPlanBBKararTarihiAndDurumuByCriteria(Plan plan,KurumSabit kurumSabit)
	{
		return gisOrtakDAO.readPlanBBKararTarihiAndDurumuByCriteria(plan, kurumSabit);		
	}
	public String readPlanBKararTarihiAndDurumuByCriteria(Plan plan,KurumSabit kurumSabit)
	{
		return gisOrtakDAO.readPlanBKararTarihiAndDurumuByCriteria(plan, kurumSabit);
	}
	
	public boolean readHmaxByPlanID(Plan plan,KadastroParsel kadastroParsel)
	{
		return gisOrtakDAO.readHmaxByPlanID(plan,kadastroParsel);
	}
	public String readAcikAdresByBagimsiz(Bagimsiz bagimsiz,
			KurumSabit kurumSabit) {
		
		return gisOrtakDAO.readAcikAdresByBagimsiz(bagimsiz, kurumSabit);
	}

	public boolean saveAddressList(List<Adres> addressList) {
		
		return gisOrtakDAO.saveAddressList(addressList);
	}

	public List<OcxFormTable> readAllOcxFormTableByKatmanAdi(String katmanAdi)
	{
		return gisOrtakDAO.readAllOcxFormTableByKatmanAdi(katmanAdi);
	}

	public List<Fonksiyon> readFonksiyonlarByCriteria(KadastroParsel parsel, PlanTurleri planTuru) {

		return this.gisOrtakDAO.readFonksiyonlarByCriteria(parsel, planTuru);
	}
	
	public List<KadastroParselGecerliFonksiyon> readKadastroParselGecerliFonksiyonByCriteria(KadastroParsel parsel, KurumSabit kurumSabit) {
		
		return this.gisOrtakDAO.readKadastroParselGecerliFonksiyonByCriteria(parsel, kurumSabit);
	}
	
	public List<KurumUygMD_DB_Map> readAllKurumUygMD_DB_MapByCriteria( KurumUygMD_DB_Map kurumUygMDDBMap, int startIndex, int maxResults ){
		
		return this.gisOrtakDAO.readAllKurumUygMD_DB_MapByCriteria(kurumUygMDDBMap, startIndex, maxResults);
	}
	
	public void saveOrUpdateKurumUygMD_DB_Map ( KurumUygMD_DB_Map kurumUygMDDBMap ){
		
		gisOrtakDAO.saveOrUpdateKurumUygMD_DB_Map(kurumUygMDDBMap);
	}
	
	public void deleteKurumUygMD_DB_Map ( KurumUygMD_DB_Map kurumUygMDDBMap ){
		
		gisOrtakDAO.deleteKurumUygMD_DB_Map(kurumUygMDDBMap);
	}
	
	public Long readKurumUygMD_DB_MapKayitSayisiByCriteria( KurumUygMD_DB_Map kurumUygMDDBMap ){
		
		return gisOrtakDAO.readKurumUygMD_DB_MapKayitSayisiByCriteria(kurumUygMDDBMap);
	}
	
	public Adres findClosestAddressFromCoordinate(IFeatureLayer addressLayer, double lon, double lat, Long corporateID) {
		
		try {
		
			return getAddressListFromCoordinateAndOffset(addressLayer, lon, lat, 0.01, corporateID).get(0);
		} catch (Exception ex) {
			
			System.err.println("Error on getting address from feature !!!");
			
			ex.printStackTrace();
			
			return null;
		}
	}

	public List<Adres> getAddressListFromCoordinateAndOffset(IFeatureLayer addressLayer, Double lon, Double lat, Double offset, Long corporateID) {
		
		try {
			
			if (addressLayer == null || corporateID == null) {
				
				System.err.println("Address layer not initialized or corporate ID is null !!!");
				
				return null;
			} else {
			
				KurumSabit corporateId = new KurumSabit();
				
				corporateId.setId(corporateID);
				
				List<KurumSabit> corporateIdList = getOrtakServis().readAllKurumSabitByCriteria(corporateId);
				
				if (corporateIdList == null || corporateIdList.size() == 0) {
					
					System.err.println("Corporate instance couldn't find for ID : " + corporateId);
					
					return null;
				} else {
				
					corporateId = corporateIdList.get(0);
					
					IClosestFeatureFinder featureFinder = new ClosestFeatureFinderImpl();
					//Longtitude and Latitude is WGS84
					IPoint referancePoint = new SmpPoint(lon, lat, new SmpCoordinateSystem("EPSG:4326"));
					
					IFeature resultFeature = featureFinder.findClosestFeature(addressLayer, referancePoint, offset);
					
					if (resultFeature == null) {
						
						return null;			
					} else {
						
						IFeatureIDTools featureIDTools = new FeatureIDTools();
						
						Long addressId = featureIDTools.getFeatureIDFromFID(new SmpFeatureID(resultFeature.getID()));
						
						List<Adres> addressList = getGisOrtakDAO().readAllAdresByID(addressId, corporateId);
						
						if (addressList == null || addressList.size() == 0) {
							
							return null;
						} else {
							
							return addressList;
						}
					}
				}
			}
		} catch (Exception ex) {
			
			System.err.println("Error on getting addresses from features !!!");
			
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	public List<Bina> getBuildingListFromCoordinateAndOffset(IFeatureLayer buildingLayer, Double lon, Double lat, Double offset, Long corporateID) {
		
		try {
			
			if (buildingLayer == null || corporateID == null) {
				
				System.err.println("Building layer not initialized or corporate ID is null !!!");
				
				return null;
			} else {
			
				KurumSabit corporateId = new KurumSabit();
				
				corporateId.setId(corporateID);
				
				List<KurumSabit> corporateIdList = getOrtakServis().readAllKurumSabitByCriteria(corporateId);
				
				if (corporateIdList == null || corporateIdList.size() == 0) {
					
					System.err.println("Corporate instance couldn't find for ID : " + corporateId);
					
					return null;
				} else {
				
					corporateId = corporateIdList.get(0);
					
					IClosestFeatureFinder featureFinder = new ClosestFeatureFinderImpl();
					//Longtitude and Latitude is WGS84
					IPoint referancePoint = new SmpPoint(lon, lat, new SmpCoordinateSystem("EPSG:4326"));
					
					IFeature resultFeature = featureFinder.findClosestFeature(buildingLayer, referancePoint, offset);
					
					if (resultFeature == null) {
						
						return null;			
					} else {
						
						IFeatureIDTools featureIDTools = new FeatureIDTools();
						
						Long buildingId = featureIDTools.getFeatureIDFromFID(new SmpFeatureID(resultFeature.getID()));
						
						List<Bina> buildingList = getGisOrtakDAO().readAllBinaByID(buildingId, corporateId);
						
						if (buildingList == null || buildingList.size() == 0) {
							
							return null;
						} else {
							
							return buildingList;
						}
					}
				}
			}
		} catch (Exception ex) {
			
			System.err.println("Error on getting addresses from features !!!");
			
			ex.printStackTrace();
			
			return null;
		}
	}

	public List<Plan> getPlanListFromParcelWithSpatialQuery(IFeatureLayer planLayer, IFeature parcel) {

		if (planLayer != null && parcel != null) {
			
//			IOverlapsQuery overlapsQuery = new SmpOverlapsQuery();
			IIntersectsQuery intersectsQuery = new SmpIntersectsQuery();
			
//			IGeomFiltersReturnType filterResult1 = overlapsQuery.overlaps(planLayer, parcel);
			
			IGeomFiltersReturnType filterResult = intersectsQuery.intersects(planLayer, parcel);
			
			if (!filterResult.getException().equals("")) {
				
				System.out.println("Error on executing filter query !!! ERROR : " + filterResult.getException());
			} else {

				List<Plan> resultPlanList = new ArrayList<Plan>();
				
				if (filterResult.getFeatures() != null && filterResult.getFeatures().getFeaturesCount() > 0) {
					
					for (int featureCntr = 0; featureCntr < filterResult.getFeatures().getFeaturesCount(); featureCntr++) {
						
						//TO-DO kesişen her plan için kesişim alanı bulunacak.
						//bulunan bu alan için orana göre işlem yapılacak.
						
						double intersectArea=intersectsQuery.intersectsArea(parcel.getDefaultGeometry(),filterResult.getFeatures().getFeatureAt(featureCntr).getDefaultGeometry());
						double rate=(intersectArea*100)/parcel.getArea();
						if(6<=rate){
							Plan newPlan = readPlanByID(featureIDTools.getFeatureIDFromStr(filterResult.getFeatures().getFeatureAt(featureCntr).getID()));
							if(newPlan!=null){
								resultPlanList.add(newPlan);
							}
						}else if(5<=rate && rate<6 && 50<=intersectArea){
							
							Plan newPlan = readPlanByID(featureIDTools.getFeatureIDFromStr(filterResult.getFeatures().getFeatureAt(featureCntr).getID()));
							
							if(newPlan!=null){
								resultPlanList.add(newPlan);
							}
						
						}
					}
					return resultPlanList;
				}//no need to implement else statement return null is enough
			}
		} //no need to implement else statement return null is enough
		return null;
	}
	
	public Plan readPlanByID(Long planID) {
		return gisOrtakDAO.readPlanByID(planID);
	}
	public String checkParcelRestrictions(KadastroParsel parsel)
	{
		return gisOrtakDAO.checkParcelRestrictions(parsel);
	}

}

























