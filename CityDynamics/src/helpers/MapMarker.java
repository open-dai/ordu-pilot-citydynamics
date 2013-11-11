package helpers;

import java.util.Vector;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import data_utils.GarbageCan;
import data_utils.GarbageCanType;
import data_utils.POIClassType;
import data_utils.POIData;
import data_utils.SpatialData;


public class MapMarker {
	Vector<Marker> markerList;
	Vector<Marker> gcMarkerList;
	GoogleMap map;
	
	public MapMarker(int capacity, GoogleMap m)
	{
		markerList = new Vector<Marker>(capacity);
		gcMarkerList = new Vector<Marker>(capacity);
		map = m;
	}
	public MapMarker()
	{
		 
	}
	
	public void addMarkersToMap(Vector<SpatialData> datas, GoogleMap map)
	{
		
		for(SpatialData data : datas)
		{
			map.addMarker(new MarkerOptions()
								.position(new LatLng(
												data.getLatitude(), 
												data.getLongitude()
												)
										).title(data.getTitle())
								);
		}
	}
	
	public void addPOIMarkersToMap(Vector<POIData> datas, GoogleMap map)
	{
		
		for(POIData data : datas)
		{
			Marker m = map.addMarker(new MarkerOptions()
								.position(data.getPoint())
								.title(data.getDesc()).icon(BitmapDescriptorFactory.defaultMarker(data.getColorValue()))
							);
			markerList.add(m);
		}
	}
	
	public void clearAllMarkers()
	{
		for(Marker m : markerList)
		{
			m.remove();
		}
		
		markerList.removeAllElements();
	}
	
	public void arrangePOIMarkers(Vector<POIData> allData, Vector<POIClassType> typeList)
	{
		Vector<POIData> filtered = new Vector<POIData>(allData.size());
		
		for(int i = 0; i < allData.size(); i++)
		{
			POIData data = allData.get(i);
			if(isValidType(data.getType(), typeList))
			{
				filtered.add(data);
			}
		}
		
		clearAllMarkers();
		addPOIMarkersToMap(filtered, map);		
	}
	
	private boolean isValidType(int typeID, Vector<POIClassType> typeList)
	{
		boolean result = false;
		for(POIClassType t : typeList)
		{
			if(t.getId() == typeID)
			{
				if(t.isChecked())
				{
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**********************************************************/
	
	public void addGCMarkersToMap(Vector<GarbageCan> datas)
	{
		
		for(GarbageCan data : datas)
		{
			Marker m = map.addMarker(new MarkerOptions()
								.position(data.getPoint())
								.title(data.getDesc()).icon(BitmapDescriptorFactory.defaultMarker(data.getColorValue()))
							);
			markerList.add(m);
		}
	}
	
	public void clearAllGCMarkers()
	{
		for(Marker m : gcMarkerList)
		{
			m.remove();
		}
		
		gcMarkerList.removeAllElements();
	}
	
	public void arrangeGCMarkers(Vector<GarbageCan> allData, Vector<GarbageCanType> typeList)
	{
		Vector<GarbageCan> filtered = new Vector<GarbageCan>(allData.size());
		
		for(int i = 0; i < allData.size(); i++)
		{
			GarbageCan data = allData.get(i);
			if(isValidGCType(data.getType().getTypeID(), typeList))
			{
				filtered.add(data);
			}
		}
		
		clearAllMarkers();
		addGCMarkersToMap(filtered);		
	}
	
	private boolean isValidGCType(int typeID, Vector<GarbageCanType> typeList)
	{
		boolean result = false;
		for(GarbageCanType t : typeList)
		{
			if(t.getTypeID() == typeID)
			{
				if(t.isChecked())
				{
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	

}
