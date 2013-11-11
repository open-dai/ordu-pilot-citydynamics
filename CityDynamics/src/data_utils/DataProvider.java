package data_utils;

import java.util.Random;
import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import com.google.android.gms.maps.model.LatLng;

import android.text.format.Time;

import helpers.ServiceCommunicator;

public class DataProvider {

	public final static int GROUP_TYPE_ALL = 1;
	public final static int GROUP_TYPE_BY_TOPID = 2;

	private Vector<POIClassType> poiSinif;
	private Random rand;

	public DataProvider() {
		init();
	}

	private void init() {
		poiSinif = new Vector<POIClassType>(16);
		generatePOIClassType(poiSinif);
		rand = new Random();

	}

	private void generatePOIClassType(Vector<POIClassType> vector) {

		POIClassType poi16 = new POIClassType("BILINMEYEN", 0);
		POIClassType poi1 = new POIClassType("ALTYAPI TESÝSLERÝ", 1);
		POIClassType poi2 = new POIClassType("DINI TESISLER", 2);
		POIClassType poi3 = new POIClassType("EGITIM KURUMLARI", 3);
		POIClassType poi4 = new POIClassType("GENEL  KULLANIM", 4);
		POIClassType poi5 = new POIClassType("KÜLTÜREL TESISLER", 5);
		POIClassType poi6 = new POIClassType("REHBER", 6);
		POIClassType poi7 = new POIClassType("RESMI KURUMLAR", 7);
		POIClassType poi8 = new POIClassType("SAGLIK KURUMLARI", 8);
		POIClassType poi9 = new POIClassType("SANAYI VE ÜRETIM ALANLARI", 9);
		POIClassType poi10 = new POIClassType("SOSYAL AMAÇLI KURUMLAR", 10);
		POIClassType poi11 = new POIClassType(
				"TICARET ALANLARI, SATIS NOKTALARI, ISYERLERI", 11);
		POIClassType poi12 = new POIClassType("TURIZM", 12);
		POIClassType poi13 = new POIClassType("ULASIM", 13);
		POIClassType poi14 = new POIClassType(
				"YESIL ALAN,SPOR VE DINLENME YERLERI", 14);
		POIClassType poi15 = new POIClassType("MESKEN KONUT", 15);

		vector.add(poi16);
		vector.add(poi1);
		vector.add(poi2);
		vector.add(poi3);
		vector.add(poi4);
		vector.add(poi5);
		vector.add(poi6);
		vector.add(poi7);
		vector.add(poi8);
		vector.add(poi9);
		vector.add(poi10);
		vector.add(poi11);
		vector.add(poi12);
		vector.add(poi13);
		vector.add(poi14);
		vector.add(poi15);

	}

	public String getPOIDescriptionByID(int ID) {
		String aciklama = "Bulunamadý";

		for (POIClassType poi : poiSinif) {
			if (poi.getId() == ID) {
				aciklama = poi.getAciklama();
				break;
			}
		}

		return aciklama;
	}

	public String getPOIDescriptionByID(int ID, int index) {
		String aciklama = getPOIDescriptionByID(ID);
		aciklama += " - " + index;

		return aciklama;
	}

	public Vector<POIClassType> getPOIClasses() {
		return poiSinif;
	}

	public Vector<POIData> generatePOIData(int count) {
		Vector<POIData> datalar = new Vector<POIData>(count);
		int lat = 40960924;
		int lng = 37869528;

		for (int i = 0; i < count; i++) {
			int latAdd = rand.nextInt(24000);
			int lngAdd = rand.nextInt(70000);
			int type = rand.nextInt(15);
			String typeDesc = getPOIDescriptionByID(type, i);

			POIData data = new POIData(new LatLng((lat + latAdd) / 1000000.0, (lng + lngAdd)/1000000.0),
					type, typeDesc);

			datalar.add(data);
		}

		return datalar;
	}

	public Vector<POIData> getPOIDataByFilter(Vector<Boolean> checked,
			Vector<POIData> allData) {
		Vector<POIData> result = new Vector<POIData>(allData.capacity());

		for (POIData data : allData) {
			if (checked.get(data.getType())) {
				result.add(data);
			}
		}

		return result;
	}

	public Vector<POIClassType> getPOIClassTypeFromService() {
		Vector<POIClassType> result = null;

		ServiceCommunicator service = new ServiceCommunicator(
				"queryListPoiClassType", null);
		SoapObject response = service.getResultFromService();

		result = new Vector<POIClassType>(response.getPropertyCount());

		for (int i = 0; i < response.getPropertyCount(); i++) {
			SoapObject data = (SoapObject) response.getProperty(i);
			int typeID = Integer.parseInt(data.getPropertyAsString("id"));
			String desc = data.getPropertyAsString("description");

			POIClassType type = new POIClassType(desc, typeID);
			result.add(type);
		}

		return result;
	}

	public Vector<POIGroupType> getPOIGroupType(int functionType,
			SoapParameter[] params) {

		Vector<POIGroupType> result = null;

		switch (functionType) {
		case GROUP_TYPE_ALL:
			result = getAllPOIGroupType();
			break;
		case GROUP_TYPE_BY_TOPID:
			result = getPOIGroupTypeByTopID(params);
		default:
			break;
		}

		return result;
	}

	public Vector<POIGroupType> getAllPOIGroupType() {
		Vector<POIGroupType> result = null;

		ServiceCommunicator service = new ServiceCommunicator(
				"queryListPoiGroupType", null);
		SoapObject response = service.getResultFromService();

		result = new Vector<POIGroupType>(response.getPropertyCount());

		for (int i = 0; i < response.getPropertyCount(); i++) {
			SoapObject data = (SoapObject) response.getProperty(i);
			POIGroupType type = new POIGroupType(Integer.parseInt(data
					.getPropertyAsString("topid")), Integer.parseInt(data
					.getPropertyAsString("id")),
					data.getPropertyAsString("description"));
			result.add(type);
		}

		return result;
	}

	public Vector<POIGroupType> getPOIGroupTypeByTopID(SoapParameter[] params) {
		Vector<POIGroupType> result = null;

		ServiceCommunicator service = new ServiceCommunicator("queryListPoiGroupType", params);
		SoapObject response = service.getResultFromService();

		result = new Vector<POIGroupType>(response.getPropertyCount());

		for (int i = 0; i < response.getPropertyCount(); i++) 
		{
			SoapObject data = (SoapObject) response.getProperty(i);
			POIGroupType type = new POIGroupType(Integer.parseInt(data.getPropertyAsString("topid")), 
												Integer.parseInt(data.getPropertyAsString("id")),
												data.getPropertyAsString("description"));
			result.add(type);
		}

		return result;
	}
	
	public Vector<GarbageCanType> getGCType()
	{
//		Vector<GarbageCanType> typeList = null;
//		
//		ServiceCommunicator service = new ServiceCommunicator("QueryCanType", null);
//		SoapObject response = service.getResultFromService();
//		
//		typeList = new Vector<GarbageCanType>(response.getPropertyCount());
//		
//		for(int i = 0; i < response.getPropertyCount(); i++)
//		{
//			SoapObject data = (SoapObject) response.getProperty(i);
//			GarbageCanType t = new GarbageCanType(Integer.parseInt(data.getPropertyAsString("kodu")), 
//												  data.getPropertyAsString("adi"));
//			
//			typeList.add(t);
//		}
		
		
		Vector<GarbageCanType> typeList = new Vector<GarbageCanType>(10);
		typeList.add(new GarbageCanType(1, "ÇÖP BÝDONU"));
		typeList.add(new GarbageCanType(2, "TIBBÝ ATIK BÝDONU"));
		typeList.add(new GarbageCanType(3, "PLASTÝK ÇÖP KOVASI"));
		typeList.add(new GarbageCanType(4, "DEMÝR ÇÖP KOVASI"));
		typeList.add(new GarbageCanType(5, "PLASTÝK ÇÖP KOVALARI"));
		typeList.add(new GarbageCanType(6, "DEMÝR ÇÖP KOVALARI"));
		typeList.add(new GarbageCanType(7, "BAHÇE TÝPÝ ÇÖP KUTUSU"));
		typeList.add(new GarbageCanType(8, "GERÝ DÖNÜÞÜM KONTEYNERÝ"));
		typeList.add(new GarbageCanType(9, "BÝLÝNMEYEN"));

		
//			private int typeID;
//	private String description;
//	private boolean isChecked;
//	
		return typeList;
		
	}
	
	public Vector<GarbageCan> generateGCData(int count) {
		Vector<GarbageCan> datalar = new Vector<GarbageCan>(count);
		int lat = 40960924;
		int lng = 37869528;

		for (int i = 0; i < count; i++) {
			int latAdd = rand.nextInt(24000);
			int lngAdd = rand.nextInt(70000);
			int type = rand.nextInt(8) + 1;
			String typeDesc = getGCTypeDescriptionByID(type);
			String desc = getRandomTimeStr();

			GarbageCan data = new GarbageCan(new LatLng((lat + latAdd) / 1000000.0, (lng + lngAdd)/1000000.0),
					desc, new GarbageCanType(type, typeDesc));

			datalar.add(data);
		}

		return datalar;
	}
	
	private String getGCTypeDescriptionByID(int typeID)
	{
		String result = "";
		
		switch (typeID) {
		case 1:
			result = "ÇÖP BÝDONU";
			break;
		case 2:
			result = "TIBBÝ ATIK BÝDONU";
			break;
		case 3:
			result = "PLASTÝK ÇÖP KOVASI";
			break;
		case 4:
			result = "DEMÝR ÇÖP KOVASI";
			break;
		case 5:
			result = "PLASTÝK ÇÖP KOVALARI";
			break;
		case 6:
			result = "DEMÝR ÇÖP KOVALARI";
			break;
		case 7:
			result = "BAHÇE TÝPÝ ÇÖP KUTUSU";
			break;
		case 8:
			result = "GERÝ DÖNÜÞÜM KONTEYNERÝ";
			break;

		default:
			result = "BÝLÝNMEYEN";
			break;
		}
		
		return result;
	}
	
	private String getRandomTimeStr()
	{
		
		int hour, minute;
		hour = rand.nextInt(24);
		String hourStr, minuteStr;
		
		minute = rand.nextInt(60);
		
		hourStr = hour > 9 ? String.valueOf(hour) : "0" + String.valueOf(hour);
		minuteStr = minute > 9 ? String.valueOf(minute) : "0" + String.valueOf(minute);
		
		String result = String.format("Bir sonraki çöp toplama saati\n\r %s:%s", hourStr, minuteStr);
		
		return result;		
	}
}
