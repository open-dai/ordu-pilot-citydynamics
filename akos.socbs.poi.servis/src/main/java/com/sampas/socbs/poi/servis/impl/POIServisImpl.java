package com.sampas.socbs.poi.servis.impl;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import com.sampas.akos.common.exception.AkosException;
import com.sampas.akos.idariyapi.model.Il;
import com.sampas.akos.idariyapi.model.Ilce;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;
import com.sampas.akos.ortak.model.BagimsizKullanimGrup;
import com.sampas.akos.ortak.model.BagimsizKullanimSinif;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.akos.ortaksabit.servis.OrtakSabitServis;
import com.sampas.gis.ortak.basic.model.geometry.SmpCoordinate;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.model.Poi;
import com.sampas.gis.ortak.model.PoiTypes;
import com.sampas.gis.ortak.poi.search.servis.IClosestPOIFinder;
import com.sampas.gis.ortak.poi.search.servis.impl.ClosestPOIFinder;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.ortak.spatial.servis.IOrtakSpatialServis;
import com.sampas.ortak.spatial.servis.tools.IAppFeatureLayer;
import com.sampas.ortak.spatial.servis.tools.impl.OrtakSpatialLayers;
import com.sampas.socbs.base.feature.services.IFeatureFinderServices;
import com.sampas.socbs.base.model.MapReturnType;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.CoordinateSystemTransformers;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureID;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpPoint;
import com.sampas.socbs.core.maplayer.ILayerAttribute;
import com.sampas.socbs.core.tools.IFeatureIDTools;
import com.sampas.socbs.core.tools.IImageProcesses;
import com.sampas.socbs.core.tools.impl.FeatureIDTools;
import com.sampas.socbs.core.tools.impl.ImageProcesses;
import com.sampas.socbs.opendai.servis.IMapServis;
import com.sampas.socbs.poi.model.JointFeature;
import com.sampas.socbs.poi.servis.IPOIServis;
import com.sampas.socbs.poi.servis.metadata.impl.POIServisMetadata;
import com.sampas.socbs.poi.servis.returntypes.CoordinatesReturnType;
import com.sampas.socbs.poi.servis.returntypes.PoiReturnType;


public class POIServisImpl implements IPOIServis {
	
	private IMapServis mapServis;

	private OrtakSabitServis ortakSabitServis;
	
	private OrtakServis ortakServis;
	
	private IFeatureFinderServices featureFinderServices;

	private POIServisMetadata poiServisMetadata;
	
	private IFeatureIDTools featureIDTools = new FeatureIDTools();
	
	private ICoordinateSystemTransformers coordinateSystemTransformers = new CoordinateSystemTransformers();
	
	private static int[] imptPlaceAttCodes = {3, 1, 2};
	
	private IOrtakSpatialServis ortakSpatialServis;
	
	
	public POIServisImpl() {
		
		if (getPoiServisMetadata() == null) {
			
			setPoiServisMetadata(new POIServisMetadata());
		}
	}

	public MapReturnType getMapAsByteArr(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel) {

		MapReturnType mapReturnType = new MapReturnType();
		
		try {

			byte[] image = getMapServis().getMapAsByteArr(lng, lat, imageWidth, imageHeight, zoomLevel);

			if (image.length == 0) {
				
				mapReturnType = setMapError(1, "SMP-UYG010-HATA001; Harita imgesi sorgusu sonucu boş döndü imge yok !");
			} else {
				mapReturnType = setMapSuccess(image);
			}
			return (mapReturnType);
		} catch (Exception ex) {
			
			System.out.println("Error on creating map image ! ERROR : " + ex);
			
			mapReturnType = setMapError(2, "SMP-UYG010-HATA002; Harita imgesi oluşturma esnasında hata ile karşılaşıldı !");
		}
		return (mapReturnType);
	}
	
	private MapReturnType setMapSuccess(byte[] mapResult) {
		
		MapReturnType mapReturnType = new MapReturnType();
		
		mapReturnType.setErrorCode(0);
		
		mapReturnType.setErrorDescription("");
		
		mapReturnType.setReturnImage(mapResult);
		
		return mapReturnType;
	}
	
	private MapReturnType setMapError(int errorCode, String errorDescription) {
		
		MapReturnType mapReturnType = new MapReturnType();
		
		mapReturnType.setErrorCode(errorCode);
		
		mapReturnType.setErrorDescription(errorDescription);
		
		mapReturnType.setReturnImage(null);
		
		return mapReturnType;
	}

	public CoordinatesReturnType getCoordinateFromAddress(String ilAdi, String ilceAdi, String mahalleAdi, String caddeSokakAdi, String kapiNo) {
		
		return getCoordinateFromAddress(getKurumSabitFromCriterias(ilAdi.toUpperCase(), ilceAdi.toUpperCase()), mahalleAdi.toUpperCase(), caddeSokakAdi.toUpperCase(), kapiNo);
	}

	private CoordinatesReturnType setCoordinateReturnSuccess(double coordinateX, double coordinateY) {
		
		CoordinatesReturnType coordinatesReturnType = new CoordinatesReturnType();
		
		coordinatesReturnType.setErrorCode(0);
		
		coordinatesReturnType.setErrorDescription("");
		
		coordinatesReturnType.setCoordinateX(coordinateX);
		
		coordinatesReturnType.setCoordinateY(coordinateY);
		
		return coordinatesReturnType;
	}
	
	private CoordinatesReturnType setCoordinateReturnError(int errorCode, String errorDescription) {
		
		CoordinatesReturnType coordinatesReturnType = new CoordinatesReturnType();
		
		coordinatesReturnType.setErrorCode(errorCode);
		
		coordinatesReturnType.setErrorDescription(errorDescription);
		
		coordinatesReturnType.setCoordinateX(0);
		
		coordinatesReturnType.setCoordinateY(0);
		
		return coordinatesReturnType;
	}
	
	public int getMaxZoomLevel() {
		return getMapServis().getMaxZoomLevel();
	}

	public MapReturnType getMapFromAddress(String ilAdi, String ilceAdi, String mahalleAdi, String caddeSokakAdi, String kapiNo, int imageWidth, int imageHeight, int zoomLevel) {

		return getMapFromAddress(getKurumSabitFromCriterias(ilAdi.toUpperCase(), ilceAdi.toUpperCase()), mahalleAdi.toUpperCase(), caddeSokakAdi.toUpperCase(), kapiNo, imageWidth, imageHeight, zoomLevel);
	}

	public String[] getPoiTypes() {

		try {
			PoiTypes poiTypes = getPoiServisMetadata().getPoiTypes();

			int poiTypeCount = poiTypes.getPoiTypes().size();

			String[] poiTypesStrArr = new String[poiTypeCount];

			for (int i = 0; i < poiTypeCount; i++) {

				poiTypesStrArr[i] = poiTypes.getPoiTypes().get(i);
			}
			return (poiTypesStrArr);
		} catch (Exception ex) {

			System.out.println("Error on returning POI types ! ERROR : " + ex);
			
			return null;
		}
	}

	public PoiReturnType getPoiListFromType(String ilAdi, String ilceAdi, String poiType, String poiNameClue) {
		
		return getPoiListFromType(getKurumSabitFromCriterias(ilAdi.toUpperCase(), ilceAdi.toUpperCase()), poiType, poiNameClue.toUpperCase());
	}

	
	
	
	private PoiReturnType setPoiReturnSuccess(List<Poi> poiList) {
		
		PoiReturnType poiReturnType = new PoiReturnType();
		
		poiReturnType.setErrorCode(0);
		
		poiReturnType.setErrorDescription("");
		
		poiReturnType.setReturnPoiList(poiList);
		
		return poiReturnType;
	}
	
	private PoiReturnType setPoiReturnError(int errorCode, String errorDescription) {
		
		PoiReturnType poiReturnType = new PoiReturnType();
		
		poiReturnType.setErrorCode(errorCode);
		
		poiReturnType.setErrorDescription(errorDescription);
		
		poiReturnType.setReturnPoiList(null);
		
		return poiReturnType;
	}

	@SuppressWarnings("unchecked")
	private KurumSabit getKurumSabitFromCriterias(String ilAdi, String ilceAdi) {

		Il efIl = new Il();
		
		efIl.setAciklama(ilAdi.toUpperCase());

		Il il = getOrtakSabitServis().readIlByCriteria(efIl);

		KurumSabit kurumSabit = null;
		
		if (il != null && il.getIlceler() != null && il.getIlceler().size() > 0) {

			Ilce ilce = null;

			for (Ilce ilceIter : il.getIlceler()) {
				
				if (ilceIter.getAciklama().equals(ilceAdi.toUpperCase())) {
					
					ilce = ilceIter;
					
					break;
				}
			}
			List<KurumSabit> kurumSabitler = new ArrayList<KurumSabit>();

			if (ilce != null && ilce.getAciklama() != null && ilce.getAciklama() != "") {

				Collection<KurumSabit> kurumSabitlerCln = ilce.getKurumSabitler();
				
				Iterator<KurumSabit> kurumSbtIterator = kurumSabitlerCln.iterator();

				KurumSabit tempKurumSabit = null;
				
				while (kurumSbtIterator.hasNext()) {
					
					tempKurumSabit = kurumSbtIterator.next();
					
					kurumSabitler.add(tempKurumSabit);
				}
			} else {

				System.out.println("Error on finding parameters for County name !");
				
				return null;
			}
			if (kurumSabitler != null && kurumSabitler.size() > 0) {

				for (int i = 0; i < kurumSabitler.size(); i++) {
					
					if (kurumSabitler.get(i).getKurumAd().equals("")) {
						
						kurumSabit = kurumSabitler.get(i);
					}
				}
				if (kurumSabit == null) {
					
					kurumSabit = kurumSabitler.get(0);
				}
				return (kurumSabit);
			} else {

				System.out.println("Error City or County does not exist !");
			}
		} else {

			System.out.println("Error on finding parameters from County name !");
		}
		return kurumSabit;
	}
	
	public PoiReturnType getClosestPoiFromReferanceCoordinate(String ilAdi, String ilceAdi, String poiType, double lat, double lng) {
		
		return getClosestPoiFromReferanceCoordinate(getKurumSabitFromCriterias(ilAdi.toUpperCase(), ilceAdi.toUpperCase()), poiType, lat, lng);
	}
	
	@SuppressWarnings("unused")
	private byte[] addPinToMap(byte[] orjinalMap) {

		try {
		
			InputStream inputStream = new ByteArrayInputStream(orjinalMap);
			
			BufferedImage pureMap = javax.imageio.ImageIO.read(inputStream);
			
			BufferedImage pinImage = ImageIO.read(this.getClass().getResourceAsStream("pinIcon20.png"));
			
			BufferedImage scaledImage = new BufferedImage(pureMap.getWidth(), pureMap.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
            Graphics2D graphics2D = scaledImage.createGraphics();
            
            graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            
            graphics2D.drawImage(pureMap, 0, 0, null);
            
            graphics2D.drawImage(pinImage, pureMap.getWidth()/2 - (pinImage.getWidth()/2), pureMap.getHeight()/2- (pinImage.getHeight()/2), null);
            
			IImageProcesses imageProcesses = new ImageProcesses();

			byte[] resultbyteArrImage = imageProcesses.bufferedImageToByteArr(scaledImage);
			
			return resultbyteArrImage;
			
		} catch (Exception ex) {

			System.out.println("Error on adding pin to map !" + ex);
			
			return null;
		}
	}
	
	public byte[] getMisMapFromAddress(Adres address, int imageWidth, int imageHeight, int zoomLevel) {
		
		try {
			
			ICoordinate addressCoordinate = getCoordinateFromAddress(address);
			
			byte[] image = getMapServis().getMapAsByteArr(addressCoordinate.getX(), addressCoordinate.getY(), imageWidth, imageHeight, zoomLevel);
			
			return image;
			
		} catch (Exception ex) {

			System.out.println("Error on creating image from related address ! ERROR: " + ex);
			
			return null;
		}
	}
	
	private ICoordinate getCoordinateFromAddress(Adres address) {
		
		try {
			
			IFeature addressFeature = getMapServis().getAddressFeatureLayer().getFeaturesbyFID(featureIDTools.createFeatureIDWithFTypeAndID(getMapServis().getAddressFeatureLayer().getName(), address.getId()));
			
			return (addressFeature.getDefaultGeometry().getCoordinate());
		} catch (Exception ex) {

			System.out.println("Error on finding address coordinate ! ERROR: " + ex);			
		}
		return null;
	}
	
	public IGisOrtakServis getGisOrtakServis() {
		return getOrtakSpatialServis().getGisOrtakServis();
	}

	public IOrtakSpatialServis getOrtakSpatialServis() {
		return ortakSpatialServis;
	}
	
	public void setOrtakSpatialServis(IOrtakSpatialServis ortakSpatialServis) {
		this.ortakSpatialServis = ortakSpatialServis;
	}
	
	public IFeatureFinderServices getFeatureFinderServices() {
		return featureFinderServices;
	}

	public void setFeatureFinderServices(IFeatureFinderServices featureFinderServices) {
		this.featureFinderServices = featureFinderServices;
	}

	public IMapServis getMapServis() {
		return mapServis;
	}

	public void setMapServis(IMapServis mapServis) {
		this.mapServis = mapServis;
	}

	public OrtakSabitServis getOrtakSabitServis() {
		return ortakSabitServis;
	}

	public void setOrtakSabitServis(OrtakSabitServis ortakSabitServis) {
		this.ortakSabitServis = ortakSabitServis;
	}
	
	public OrtakServis getOrtakServis() {
		return ortakServis;
	}

	public void setOrtakServis(OrtakServis ortakServis) {
		this.ortakServis = ortakServis;
	}
	
	public POIServisMetadata getPoiServisMetadata() {
		return poiServisMetadata;
	}

	public void setPoiServisMetadata(POIServisMetadata poiServisMetadata) {
		this.poiServisMetadata = poiServisMetadata;
	}

	public PoiReturnType getClosestPoiFromReferanceCoordinate(KurumSabit kurumSabit, String poiType, double lat, double lng) {
		
		PoiReturnType poiReturnType = new PoiReturnType();
		
		if (kurumSabit != null) {	
			
			try {
				
				IClosestPOIFinder poiFinder = new ClosestPOIFinder();
				
				ICoordinateSystem coordinateSystem = new SmpCoordinateSystem("EPSG:4326");
				
				IPoint referancePoint = new SmpPoint(lat, lng, coordinateSystem);
				
				HashMap<ILayerAttribute, Object> attributes = new HashMap<ILayerAttribute, Object>();
				
				ILayerAttribute attributeOne = getMapServis().getImportantPlaceFeatureLayer().getAttributeAt(imptPlaceAttCodes[0]);
				
				ILayerAttribute attributeTwo = getMapServis().getImportantPlaceFeatureLayer().getAttributeAt(imptPlaceAttCodes[1]);
				
				ILayerAttribute attributeThree = getMapServis().getImportantPlaceFeatureLayer().getAttributeAt(imptPlaceAttCodes[2]);
				
				Long kullanimDetayID = getPoiServisMetadata().getPoiKullanimDetayID(poiType);
				
				Long kullanimGrupID = getPoiServisMetadata().getPoiKullanimGrupID(poiType);
				
				Long kullanimSinifID = getPoiServisMetadata().getPoiKullanimSinifID(poiType);
				
				List<ILayerAttribute> attributeList = new ArrayList<ILayerAttribute>();
				
				if (kullanimSinifID > 0) {
					
					attributes.put(attributeOne, kullanimSinifID);
					
					attributeList.add(attributeOne);
				}
				if (kullanimDetayID > 0) {
					
					attributes.put(attributeTwo, kullanimDetayID);
					
					attributeList.add(attributeTwo);
				}
				if (kullanimGrupID > 0) {
					
					attributes.put(attributeThree, kullanimGrupID);
					
					attributeList.add(attributeThree);
				}
				if (getMapServis().getImportantPlaceFeatureLayer().getCoordinateSystem() == null) {
					
					getMapServis().getImportantPlaceFeatureLayer().setCoordinateSystem(new SmpCoordinateSystem(getMapServis().getImportantPlaceFeatureLayer().getCoordinateSystem().getEPSGCode()));
				}
				
				
				IFeature closestImptPlaceFeature = poiFinder.findClosestPOI(getMapServis().getImportantPlaceFeatureLayer(), referancePoint, attributes, attributeList);
				
				OnemliYer searchClue = new OnemliYer();
				
				searchClue.setId(Long.parseLong(closestImptPlaceFeature.getAttribute(getPoiServisMetadata().getImptPlcUIDAttName()).toString()));
				
				List<OnemliYer> findedOnemliYerler = getGisOrtakServis().readOnemliyerByCriteria(searchClue, kurumSabit);
				
				OnemliYer findedImptPlc = null;
				
//				Adres imptPlcAddress = null;
//				
//				String addressStr = "";
				
				if (findedOnemliYerler.size() > 0) {
					
					findedImptPlc = findedOnemliYerler.get(0);
					
//					imptPlcAddress = getGisOrtakServis().readAdresByOnemliYer(findedImptPlc, kurumSabit);
					
//					if (imptPlcAddress != null) {
//						
//						imptPlcAddress = getGisOrtakServis().readAdresByOnemliYer(findedImptPlc, kurumSabit);
//						
//						addressStr = "" + getAddressStrFromImpPlc(kurumSabit, findedImptPlc);
						
						Poi closestPoi = new Poi();
						
						closestPoi.setPoiID((int)((long)findedImptPlc.getId()));
						
						closestPoi.setPoiName(findedImptPlc.getAciklama());
						
//						closestPoi.setAddress(addressStr);
						
						Double[] closestImptPlcCoordinate = {closestImptPlaceFeature.getDefaultGeometry().getCoordinate().getX(), closestImptPlaceFeature.getDefaultGeometry().getCoordinate().getY()};
						
						IPoint tmpPoint = new SmpPoint(closestImptPlcCoordinate[0], closestImptPlcCoordinate[1], getMapServis().getImportantPlaceFeatureLayer().getCoordinateSystem());
						
						if (!getMapServis().getImportantPlaceFeatureLayer().getCoordinateSystem().getEPSGCode().equals(coordinateSystem) ) {
							
							tmpPoint = coordinateSystemTransformers.SmpGeometryCoordinateTransformer(tmpPoint, getMapServis().getImportantPlaceFeatureLayer().getCoordinateSystem(), coordinateSystem);
						}
						Double[] resultCoordinates = {tmpPoint.getX(), tmpPoint.getY()};
						
						closestPoi.setCoordinate(resultCoordinates);
						
						closestPoi.setImptPlaceID(findedImptPlc.getId());
						
						List<Poi> tempPoiList = new ArrayList<Poi>();
						
						tempPoiList.add(closestPoi);
						
						poiReturnType = setPoiReturnSuccess(tempPoiList);
						
						return poiReturnType;
//					}
				}
			} catch (Exception ex) {
				
				System.out.println("Error on finding closest poi ! ERROR: " + ex);
				
				poiReturnType = setPoiReturnError(14, "SMP-UYG010-HATA014; En yakın önemli yer aramasında hata meydana geldi !");
			}
		}
		System.out.println("No poi found for related important place !");
		
		poiReturnType = setPoiReturnError(15, "SMP-UYG010-HATA015; İlgili arama için önemli yer kaydı bulunamadı !");
		
		return poiReturnType;
	}

	public CoordinatesReturnType getCoordinateFromAddress(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo) {

		CoordinatesReturnType coordinatesReturnType = new CoordinatesReturnType();
		
		try {
				
			if (kurumSabit != null) {
				
				Adres adres = getGisOrtakServis().readAdresByCriteries(kurumSabit, mahalleAdi.toUpperCase(), caddeSokakAdi.toUpperCase(), kapiNo);
				
				if (adres != null) {
	
					IFeature adresFeature = null;
					
					try {

						IFeatureID  featureId = featureIDTools.createFeatureIDWithFTypeAndID(getMapServis().getAddressFeatureLayer().getName(), adres.getId());
						
						adresFeature = getMapServis().getAddressFeatureLayer().getFeaturesbyFID(featureId);
					} catch (Exception ex) {
						
						System.out.println("Error on finding related feature from address object ! ERROR : " + ex);
						
						coordinatesReturnType = setCoordinateReturnError(3, "SMP-UYG010-HATA003; Adrese karşılık gelen mekan bilgisi kaydı yok !");
					}
					
					if (adresFeature != null && adresFeature.getDefaultGeometry() != null)  {
						
						ICoordinateSystemTransformers coordinateTransformers = new CoordinateSystemTransformers();

						IFeature transformedFeature = coordinateTransformers.FeatureCoordinateTransformer(adresFeature, getMapServis().getAddressFeatureLayer().getCoordinateSystem(), getPoiServisMetadata().getDataSimulatedTargetCoordinateSystem());
						
						double[] coordinates = new double[2];
						
						coordinates[0] = transformedFeature.getDefaultGeometry().getCoordinate().getX();
						
						coordinates[1] = transformedFeature.getDefaultGeometry().getCoordinate().getY();
						
						coordinatesReturnType = setCoordinateReturnSuccess(coordinates[0], coordinates[1]);
					} else {
						
						System.out.println("Address coordinate could not find !");
						
						coordinatesReturnType = setCoordinateReturnError(4, "SMP-UYG010-HATA004; Bulunan adresin koordinat bilgisi mevcut değil !");
					}
				} else {
					
					System.out.println("No Coordinate Found for Entered Address !");
					
					coordinatesReturnType = setCoordinateReturnError(5, "SMP-UYG010-HATA005; Girilen adrese karşılık gelen koordinatlar bulunamadı !");
				}
			} else {
				
				System.out.println("Error on finding parameters from County name !");
				
				coordinatesReturnType = setCoordinateReturnError(6, "SMP-UYG010-HATA006; İl ve/veya ilçe Girişi Kayıtlı Değil !");
			}
		} catch (Exception ex) {

			System.out.println("Error on finding coordinate from address ! ERROR : " + ex);
			
			coordinatesReturnType = setCoordinateReturnError(7, "SMP-UYG010-HATA007; Girilen koordinata denk gelen bir adres yok !");
		}
		return coordinatesReturnType;
	}

	public MapReturnType getMapFromAddress(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo, int imgeGenislik, int imgeYukseklik, int yakinlasmaSeviyesi) {

		MapReturnType mapReturnType = new MapReturnType();
		
		try {

			CoordinatesReturnType coordinateReturnType = getCoordinateFromAddress(kurumSabit, mahalleAdi.toUpperCase(), caddeSokakAdi.toUpperCase(), kapiNo);

			if (coordinateReturnType.getErrorDescription().equals("")) {
				
				mapReturnType = getMapAsByteArr(coordinateReturnType.getCoordinateX(), coordinateReturnType.getCoordinateY(), imgeGenislik, imgeYukseklik, yakinlasmaSeviyesi);
			} else {
				
				mapReturnType = setMapError(coordinateReturnType.getErrorCode(), coordinateReturnType.getErrorDescription());
			}
		} catch (Exception ex) {

			System.out.println("Error on generating map from address ! ERROR : " + ex);
			
			mapReturnType = setMapError(8, "SMP-UYG010-HATA008; Adresten harita üretimi sırasında hata meydane geldi !");
		}
		return mapReturnType;
	}

	public PoiReturnType getPoiListFromType(KurumSabit kurumSabit, String poiType, String poiNameClue) {
		
		PoiReturnType poiReturnType = new PoiReturnType();
		
		Long kullanimDetayID = getPoiServisMetadata().getPoiKullanimDetayID(poiType);
		
		BagimsizKullanimDetay kullanimDetay = new BagimsizKullanimDetay();
		
		kullanimDetay.setId(kullanimDetayID);
		
		Long kullanimGrupID = getPoiServisMetadata().getPoiKullanimGrupID(poiType);
		
		BagimsizKullanimGrup kullanimGrup = new BagimsizKullanimGrup();
		
		kullanimGrup.setId(kullanimGrupID);
		
		Long kullanimSinifID = getPoiServisMetadata().getPoiKullanimSinifID(poiType);
		
		BagimsizKullanimSinif kullanimSinif = new BagimsizKullanimSinif();
		
		kullanimSinif.setId(kullanimSinifID);
		
		OnemliYer searchClue = new OnemliYer();
		
		searchClue.setAciklama(poiNameClue.toUpperCase());
		
		searchClue.setKullanimDetayi(kullanimDetay);
		
		searchClue.setKullanimGrubu(kullanimGrup);
		
		searchClue.setKullanimSinifi(kullanimSinif);
		
		List<OnemliYer> findedOnemliYerler = getGisOrtakServis().readOnemliyerByCriteria(searchClue, kurumSabit);
		
		List<Adres> imptPlcAddrs = new ArrayList<Adres>();
		
		List<IFeature> imptPlcFeatures = new ArrayList<IFeature>();
		
	
		if (getMapServis().getAddressFeatureLayer()==null)
		{
			System.out.println("getPoiListFromType : 1_3_NULL BU 1");
		}
		
		if (kurumSabit != null) {

			try {
				System.out.println("getPoiListFromType : 1_1");
				for (int i = 0; i < findedOnemliYerler.size(); i++) {
					kurumSabit.setKurumKodu("63000");
					try {
						System.out.println("getPoiListFromType : 1_2_"+i+" == "+kurumSabit.getId()+" = "+kurumSabit.getKurumKodu()+" == "+findedOnemliYerler.get(i).getId());
						Adres address = new Adres();
						address = getGisOrtakServis().readAdresByOnemliYer(findedOnemliYerler.get(i), kurumSabit);
						
						if (address != null) {
							System.out.println("getPoiListFromType : 1_3_"+ address.getId());
							imptPlcAddrs.add(address);					
							imptPlcFeatures.add(getMapServis().getAddressFeatureLayer().getFeaturesbyFID(featureIDTools.createFeatureIDWithFTypeAndID(getMapServis().getAddressFeatureLayer().getName(), address.getId())));
							
						} else {
							
							poiReturnType = setPoiReturnError(16, "SMP-UYG010-HATA016; Önemli yere ait adres bilgisi bulunamadı !");
							
						}
						
					} catch (Exception ex) {
						
//						System.out.println("Error on finding features for important places from imptPlace ! ERROR : " + ex);
						
						poiReturnType = setPoiReturnError(9, "SMP-UYG010-HATA009; Önemli yere ait mekan bilgisi bulunamadı !");
					}
				}
				
			} catch (Exception ex) {
				
				System.out.println("Error on finding related feature from address object ! ERROR : " + ex);
				
				poiReturnType = setPoiReturnError(10, "SMP-UYG010-HATA010; Adres ile ilişkili mekan bilgisi bulunamadı !");
			}
			
			
			
			if (findedOnemliYerler.size() == imptPlcAddrs.size() && findedOnemliYerler.size() == imptPlcFeatures.size()) {
				List<Poi> pois = new ArrayList<Poi>();
				
				Poi tempPoi;
				
				for (int i = 0; i < findedOnemliYerler.size(); i++) {
					
					tempPoi = new Poi();
					
					tempPoi.setPoiID((int)((long)findedOnemliYerler.get(i).getId()));
					
					tempPoi.setPoiName(findedOnemliYerler.get(i).getAciklama());
					
					tempPoi.setImptPlaceID(findedOnemliYerler.get(i).getId());
					
//					String addressStr = "" + getAddressStrFromImpPlc(kurumSabit, findedOnemliYerler.get(i));
//					
//					tempPoi.setAddress(addressStr);
					
					try {
						
						if (imptPlcFeatures.get(i) != null && imptPlcFeatures.get(i).getDefaultGeometry() != null)  {
							
							ICoordinateSystemTransformers coordinateTransformers = new CoordinateSystemTransformers();
							
							//IFeature transformedFeature = coordinateTransformers.FeatureCoordinateTransformer(imptPlcFeatures.get(i), webMapMetadata.getDataSimulatedLayerCoordinateSystem(), webMapMetadata.getDataSimulatedTargetCoordinateSystem());
		
							IFeature transformedFeature = coordinateTransformers.FeatureCoordinateTransformer(imptPlcFeatures.get(i), getMapServis().getAddressFeatureLayer().getCoordinateSystem(), getPoiServisMetadata().getDataSimulatedTargetCoordinateSystem());
							
							Double[] poiCoordinates = new Double[2];
							
							poiCoordinates[0] = transformedFeature.getDefaultGeometry().getCoordinate().getX();
							
							poiCoordinates[1] = transformedFeature.getDefaultGeometry().getCoordinate().getY();
							
							tempPoi.setCoordinate(poiCoordinates);
						} else {
							
							System.out.println("Poi coordinate could not find !");
							
							poiReturnType = setPoiReturnError(11, "SMP-UYG010-HATA011; Önemli yer koordinat bilgisi bulunamadı !");
						}
					} catch (Exception ex) {
						
						System.out.println("Error on Poi coordinate could not find ! ERROR: " + ex);
	
						poiReturnType = setPoiReturnError(12, "SMP-UYG010-HATA012; Önemli yer koordinat bilgisi bulma işlemi sırasında hata olustu !");
					}
					pois.add(tempPoi);
				}
				poiReturnType = setPoiReturnSuccess(pois);
			} else {
				
				System.out.println("Finded important place and finded address or finded feature count not equal ! ");
				
				poiReturnType = setPoiReturnError(13, "SMP-UYG010-HATA013; Veritabanı ile mekansal veri kaynakları arasında tutarsızlık mevcut !");			
			}
		} else {
		
			poiReturnType = setPoiReturnError(17, "SMP-UYG010-HATA017; Girilen il ve/veya ilçe'ye ait kurum bilgisi bulunamadı !");		
		}
		return poiReturnType;
	}
	
	public String getAddressStrFromPoiAndCompConst(KurumSabit kurumSabit, Poi poi) {
		
		if (poi != null && poi.getImptPlaceID() != null && kurumSabit != null) {
			
			try {
				
				OnemliYer searchOnemliYer = new OnemliYer();
				
				searchOnemliYer.setId(poi.getImptPlaceID());
				
				List<OnemliYer> resultImpPlaceList = getGisOrtakServis().readOnemliyerByCriteria(searchOnemliYer, kurumSabit);
				
				if (resultImpPlaceList != null && resultImpPlaceList.size() > 0) {
				
					Adres imptPlcAddress = getGisOrtakServis().readAdresByOnemliYer(resultImpPlaceList.get(0), kurumSabit);
					
					List<MahalleCaddeSokak> mahalleCaddeSokakList = this.ortakServis.readAllMahalleCaddeSokakByAdres(imptPlcAddress, kurumSabit);
					
					if (mahalleCaddeSokakList != null && mahalleCaddeSokakList.size() > 0) {
						//TODO: this makes response time to much !!!
						if (mahalleCaddeSokakList.get(0) != null) {
							
							List<MahalleCaddeSokak> tempMahalleCaddeSokak = this.ortakServis.readAllMahalleCaddeSokakByMahalleCaddeSokak(mahalleCaddeSokakList.get(0), kurumSabit);
							
							if (tempMahalleCaddeSokak != null && tempMahalleCaddeSokak.size() > 0) {
								
								String addressStr = tempMahalleCaddeSokak.get(0).getMahalle().getAciklama() + " " + tempMahalleCaddeSokak.get(0).getCaddeSokak().getAciklama() + " NO:" + imptPlcAddress.getKapiNo();
								
								return (addressStr);
							}
						}
					}
				} else {
					//TODO:
				}
			} catch (Exception ex) {
				
				System.err.println("Error on searching for Address for Important place 'getAddressStrFromImpPlc' function !");
				
				ex.printStackTrace();
				
				return null;
			}
		}
		return null;
	}

	public MapReturnType getMapFromBuildingId(Long buildingId, int imgWidth, int imgHeight, int ratio) {
		
		MapReturnType mapReturnType = new MapReturnType();
		
		try {
			
			IFeature buildingFeature;
			
			IFeatureID  featureId = featureIDTools.createFeatureIDWithFTypeAndID(getMapServis().getBuildingFeatureLayer().getName(), buildingId);
				
			buildingFeature = getMapServis().getBuildingFeatureLayer().getFeaturesbyFID(featureId);

			if (buildingFeature != null && buildingFeature.getDefaultGeometry() != null)  {
						
				mapReturnType = getMapAsByteArr(buildingFeature, imgWidth, imgHeight, ratio, true, "#FF0000");
			} else {
				
				System.out.println("Building feature or feature geometry is null in function getMapFromBuildingId !");
			}
		} catch (Exception ex) {

			System.out.println("Error on generating map from building id ! ERROR : " + ex);
			
			mapReturnType = setMapError(18, "SMP-UYG010-HATA018; Binadan harita üretimi sırasında hata meydane geldi !");
		}
		return mapReturnType;
	}
	
	public CoordinatesReturnType getCoordinateFromBuildingId(Long buildingId, KurumSabit compConst) {

		CoordinatesReturnType coordinatesReturnType = new CoordinatesReturnType();
		
		try {
				
			if (compConst != null) {
				
				if (buildingId != null && buildingId > 0) {
	
					IFeature buildingFeature = null;
					
					try {

						IFeatureID  featureId = featureIDTools.createFeatureIDWithFTypeAndID(getMapServis().getBuildingFeatureLayer().getName(), buildingId);
						
						buildingFeature = getMapServis().getBuildingFeatureLayer().getFeaturesbyFID(featureId);
					} catch (Exception ex) {
						
						System.out.println("Error on finding related feature from building object ! ERROR : " + ex);
						
						coordinatesReturnType = setCoordinateReturnError(19, "SMP-UYG010-HATA019; Binaya karşılık gelen mekan bilgisi kaydı yok !");
					}
					if (buildingFeature != null && buildingFeature.getDefaultGeometry() != null)  {
						
						ICoordinateSystemTransformers coordinateTransformers = new CoordinateSystemTransformers();

						IFeature transformedFeature = coordinateTransformers.FeatureCoordinateTransformer(buildingFeature, getMapServis().getBuildingFeatureLayer().getCoordinateSystem(), getPoiServisMetadata().getDataSimulatedTargetCoordinateSystem());
						
						double[] coordinates = new double[2];
						
						coordinates[0] = transformedFeature.getDefaultGeometry().getCoordinate().getX();
						
						coordinates[1] = transformedFeature.getDefaultGeometry().getCoordinate().getY();
						
						coordinatesReturnType = setCoordinateReturnSuccess(coordinates[0], coordinates[1]);
					} else {
						
						System.out.println("Building coordinate could not found !!!");
						
						coordinatesReturnType = setCoordinateReturnError(20, "SMP-UYG010-HATA020; Bulunan binanin koordinat bilgisi mevcut değil !");
					}
				} else {
					
					System.out.println("No Coordinate Found for Entered Building !");
					
					coordinatesReturnType = setCoordinateReturnError(21, "SMP-UYG010-HATA021; Girilen binaya karşılık gelen koordinatlar bulunamadı !");
				}
			} else {
				
				System.out.println("Company constant can not be null !");
				
				coordinatesReturnType = setCoordinateReturnError(22, "SMP-UYG010-HATA022; Kurum ID bos birakilamaz !");
			}
		} catch (Exception ex) {

			System.out.println("Error on finding coordinate from building ! ERROR : " + ex);
			
			coordinatesReturnType = setCoordinateReturnError(23, "SMP-UYG010-HATA023; Girilen koordinata denk gelen bir bina yok !");
		}
		return coordinatesReturnType;
	}

	@SuppressWarnings("unused")
	private KurumSabit getDefaultCompanyConst() {
	
		try {
			
			List<KurumSabit> companyConts = getOrtakServis().readAllKurumSabitByCriteria(new KurumSabit());
			
			if (companyConts != null && companyConts.size() > 0) {
				
				return companyConts.get(0);
			}
		} catch(Exception ex) {
			
			System.out.println("Erron on finding default company const !");
		}
		return null;
		
	}
	
	public MapReturnType getMapFromBuildingIdAndLayers(Long buildingId, int imgWidth, int imgHeight, int ratio, int[] layers) {
		
		MapReturnType mapReturnType = new MapReturnType();
		
		try {
			
			IFeature buildingFeature;
			
			IFeatureID  featureId = featureIDTools.createFeatureIDWithFTypeAndID(getMapServis().getBuildingFeatureLayer().getName(), buildingId);
				
			buildingFeature = getMapServis().getBuildingFeatureLayer().getFeaturesbyFID(featureId);
			
			if (buildingFeature != null && buildingFeature.getDefaultGeometry() != null)  {
				
				mapReturnType = getMapAsByteArr(buildingFeature, imgWidth, imgHeight, ratio, true, "#FF0000", layers);
			} else {
				
				System.out.println("Building feature or feature geometry is null in function getMapFromBuildingId !");
			}
		} catch (Exception ex) {

			System.out.println("Error on generating map from address ! ERROR : " + ex);
			
			mapReturnType = setMapError(18, "SMP-UYG010-HATA018; Binadan harita üretimi sırasında hata meydane geldi !");
		}
		return mapReturnType;
	}
	
	public MapReturnType getMapAsByteArr(double lng, double lat, int imageWidth, int imageHeight, int zoomLevel, int[] layers) {

		MapReturnType mapReturnType = new MapReturnType();
		
		try {

			byte[] image = getMapServis().getMapAsByteArr(lng, lat, imageWidth, imageHeight, zoomLevel, layers);

			if (image.length == 0) {
				
				mapReturnType = setMapError(1, "SMP-UYG010-HATA001; Harita imgesi sorgusu sonucu boş döndü imge yok !");
			} else {
			
				mapReturnType = setMapSuccess(image);
			}
			return (mapReturnType);
		} catch (Exception ex) {
			
			System.out.println("Error on creating map image ! ERROR : " + ex);
			
			mapReturnType = setMapError(2, "SMP-UYG010-HATA002; Harita imgesi oluşturma esnasında hata ile karşılaşıldı !");
		}
		return (mapReturnType);
	}

	public MapReturnType getMapAsByteArr(IFeature feature, int imageWidth, int imageHeight, int ratio, boolean drawFeature, String colorHex) {

		MapReturnType mapReturnType = new MapReturnType();
		
		try {

			byte[] image = getMapServis().getMapAsByteArr(feature, imageWidth, imageHeight, ratio, drawFeature, colorHex);

			if (image.length == 0) {
				
				mapReturnType = setMapError(1, "SMP-UYG010-HATA003; Harita imgesi sorgusu sonucu boş döndü imge yok !");
			} else {
			
				mapReturnType = setMapSuccess(image);
			}
			return (mapReturnType);
		} catch (Exception ex) {
			
			System.out.println("Error on creating map image ! ERROR : " + ex);
			
			mapReturnType = setMapError(2, "SMP-UYG010-HATA004; Harita imgesi oluşturma esnasında hata ile karşılaşıldı !");
		}
		return (mapReturnType);
	}
	
	public MapReturnType getMapAsByteArr(IFeature feature, int imageWidth, int imageHeight, int ratio, boolean drawFeature, String colorHex, int[] layers) {

		MapReturnType mapReturnType = new MapReturnType();
		
		try {

			byte[] image = getMapServis().getMapAsByteArr(feature, imageWidth, imageHeight, ratio, drawFeature, colorHex, layers);

			if (image.length == 0) {
				
				mapReturnType = setMapError(1, "SMP-UYG010-HATA005; Harita imgesi sorgusu sonucu boş döndü imge yok !");
			} else {
			
				mapReturnType = setMapSuccess(image);
			}
			return (mapReturnType);
		} catch (Exception ex) {
			
			System.out.println("Error on creating map image ! ERROR : " + ex);
			
			mapReturnType = setMapError(2, "SMP-UYG010-HATA006; Harita imgesi oluşturma esnasında hata ile karşılaşıldı !");
		}
		return (mapReturnType);
	}

	public List<JointFeature> getJointFeatures(List<Long> featureIdList, String type) throws AkosException {
		
		try {
			
			if (type == null || type.length() == 0) {
				
				throw new AkosException("ERROR: Type can not be null !!!", null);
			} else if (featureIdList == null || featureIdList.size() == 0) {
				
				throw new AkosException("ERROR: Feature Id List can not be null !!!", null);
			} else {
				//Types must define
				if (!type.equals(OrtakSpatialLayers.DISTRICT_LAYER_PROP_NAME) &&
					!type.equals(OrtakSpatialLayers.BUILDING_LAYER_PROP_NAME) &&
					!type.equals(OrtakSpatialLayers.BUILDAREA_LAYER_PROP_NAME) &&
					!type.equals(OrtakSpatialLayers.STREET_LAYER_PROP_NAME) &&
					!type.equals(OrtakSpatialLayers.PARCEL_LAYER_PROP_NAME) &&
					!type.equals(OrtakSpatialLayers.ADDRESS_LAYER_PROP_NAME) ) {
					
					throw new AkosException("ERROR: Type " + type + "is not a valid type !!!", null);
				} else {
					
					IAppFeatureLayer featureLayer = getOrtakSpatialServis().getAppLayerFromName(type);
					
					List<JointFeature> resultJoinedFeaturelist = new ArrayList<JointFeature>();
					
					List<IFeatureID> searchFeatureIDList = new ArrayList<IFeatureID>();
					
					for (Long tempID : featureIdList) {
						
						searchFeatureIDList.add(featureIDTools.createFeatureIDWithFTypeAndID(featureLayer.getLayerSystemName(), tempID));
					}
					IFeatureCollection resultFeatures = featureLayer.getFeatureLayer().getFeaturesbyFIDs(searchFeatureIDList);
					
					if (resultFeatures == null || resultFeatures.getFeaturesCount() == 0) {
						//No any related feature found
						return resultJoinedFeaturelist;
					} else {
						
						ICoordinateSystem targetCS = new SmpCoordinateSystem("EPSG:4326");
						
						if (!featureLayer.getFeatureLayer().getCoordinateSystem().getEPSGCode().equals(targetCS)) {
						
							resultFeatures = coordinateSystemTransformers.FeaturesCoordinateTransformer(resultFeatures, featureLayer.getFeatureLayer().getCoordinateSystem(), targetCS);
						} 
						IFeature tempFeature;
						
						SmpCoordinate tempSmpCoordinate = null;
						
						for (int featureCntr = 0; featureCntr < resultFeatures.getFeaturesCount(); featureCntr++) {
							
							tempFeature = resultFeatures.getFeatureAt(featureCntr);
							
							if (tempFeature.getDefaultGeometry() != null) {
								
								tempSmpCoordinate = new SmpCoordinate(tempFeature.getDefaultGeometry().getCoordinate().getX(), tempFeature.getDefaultGeometry().getCoordinate().getY());
							}
							resultJoinedFeaturelist.add(new JointFeature(featureIDTools.getFeatureIDFromStr(tempFeature.getID()), type, tempSmpCoordinate));
						}
						return resultJoinedFeaturelist;
					}
				}
			}
		} catch(Exception ex) {
			
			throw new AkosException("ERROR: unknown error for getJointFeatures function !!!", null);
		}
	}

}