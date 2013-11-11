package data_utils;

import android.text.format.Time;

import com.google.android.maps.GeoPoint;

public class SpatialData {
	
	GeoPoint point;
	int ID;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public GeoPoint getPoint() {
		return point;
	}

	public void setPoint(GeoPoint point) {
		this.point = point;
	}

	String title;
	
	
	public SpatialData(GeoPoint p, String t)
	{
		setPoint(p);
		setTitle(t);
	}
	
	public void setTitle(String t)
	{
		title = t;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public double getLatitude()
	{
		return point.getLatitudeE6() / 1000000.0;
	}
	
	public double getLongitude()
	{
		return point.getLongitudeE6() / 1000000.0;
	}

}
