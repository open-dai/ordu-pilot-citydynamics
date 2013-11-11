package data_utils;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

public class GarbageCan {

	private LatLng point;
	private int ID;
	private String desc;
	private GarbageCanType type;
	
	public GarbageCan(LatLng p, String d, GarbageCanType t)
	{
		setDesc(d);
		setPoint(p);
		setType(t);
	}
	
	public LatLng getPoint() {
		return point;
	}
	public void setPoint(LatLng point) {
		this.point = point;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public GarbageCanType getType() {
		return type;
	}
	public void setType(GarbageCanType type) {
		this.type = type;
	}
	
	
	public float getColorValue()
	{
		float result = 0;
		
		switch (type.getTypeID()) {
		case 1:
			result = BitmapDescriptorFactory.HUE_AZURE;
			break;
		case 2:
			result = BitmapDescriptorFactory.HUE_BLUE;
			break;
		case 3:
			result = BitmapDescriptorFactory.HUE_CYAN;
			break;
		case 4:
			result = BitmapDescriptorFactory.HUE_GREEN;
			break;
		case 5:
			result = BitmapDescriptorFactory.HUE_MAGENTA;
			break;
		case 6:
			result = BitmapDescriptorFactory.HUE_ORANGE;
			break;
		case 7:
			result = BitmapDescriptorFactory.HUE_RED;
			break;
		case 8:
			result = BitmapDescriptorFactory.HUE_ROSE;
			break;
		case 9:
			result = BitmapDescriptorFactory.HUE_VIOLET;
			break;
		case 10:
			result = BitmapDescriptorFactory.HUE_YELLOW;
			break;
		case 11:
			result = BitmapDescriptorFactory.HUE_AZURE;
			break;
		case 12:
			result = BitmapDescriptorFactory.HUE_BLUE;
			break;
		case 13:
			result = BitmapDescriptorFactory.HUE_CYAN;
			break;
		case 14:
			result = BitmapDescriptorFactory.HUE_GREEN;
			break;
		case 15:
			result = BitmapDescriptorFactory.HUE_MAGENTA;
			break;

		default:
			result = BitmapDescriptorFactory.HUE_ORANGE;
			break;
		}
		return result;
		
	}
	
	
}
