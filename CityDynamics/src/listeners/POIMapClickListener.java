package listeners;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class POIMapClickListener implements OnMapClickListener{
	
	GoogleMap map;
	
	public POIMapClickListener(GoogleMap mMap)
	{
		map = mMap;
	}
	
	@Override
	public void onMapClick(LatLng ll) {
		map.addMarker(new MarkerOptions()
								.position(ll)
								.title(String.format("Lat: %f\nLng: %f", ll.latitude, ll.longitude))
								.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
				);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
	}

}
