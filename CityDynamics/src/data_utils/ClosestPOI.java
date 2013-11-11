package data_utils;

import java.util.Vector;

import com.google.android.maps.GeoPoint;


public class ClosestPOI {
	
	public Vector<SpatialData> findClosestPOI()
	{
		String description = "";
		double lat = 41024710.14827;
		double lng = 38157200.93734235;
		int poiTypeID = 4;
		
		DataProvider provider = new DataProvider();
		description = provider.getPOIDescriptionByID(poiTypeID);
		
		SpatialData data = new SpatialData(new GeoPoint((int)lat, (int)lng), description);
		Vector<SpatialData> result = new Vector<SpatialData>(1);
		result.add(data);
		
		return result;		
	}
	
	
//    <description>ENSARI APT</description>
//    <id>581211</id>
//    <latitude>482471.148273356</latitude>
//    <longitude>4115720.93734235</longitude>
//    <poitypeclassid>4</poitypeclassid>
//    <poitypedetailid>52</poitypedetailid>
//    <poitypegroupid>19</poitypegroupid>
}
