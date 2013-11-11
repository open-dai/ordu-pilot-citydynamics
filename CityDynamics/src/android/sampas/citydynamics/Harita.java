package android.sampas.citydynamics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;

import data_utils.ClosestPOI;
import data_utils.SpatialData;

import helpers.GPSTracker;
import helpers.MapMarker;
import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Harita extends Activity{

	Random rand = new Random();
	GoogleMap mMap;
	Vector<SpatialData> datalar;
	MapMarker marker;
	GPSTracker tracker;
	Button btnSearch;
	EditText txtAddress;
	Geocoder gCoder;
	List<Address> adresler;
	
	private void init()
	{
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		datalar = createSpatialDataVector(20);
		marker = new MapMarker();
		tracker = new GPSTracker(this);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		txtAddress = (EditText) findViewById(R.id.txtAddress);
		gCoder = new Geocoder(this);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) { // for solving android.os.networkonmainthreadexception
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
		init();
		haritayiHazirla();
		
		AraClickListener arayici = new AraClickListener();
		
		btnSearch.setOnClickListener(arayici);
	}
	
	private class AraClickListener implements OnClickListener
	{
		@Override
		public void onClick(View v) {
			Button btnSearch = (Button) v;
			String address = txtAddress.getText().toString();
			
			try {
			    adresler = gCoder.getFromLocationName(address, 10);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				adresler = new Vector<Address>();
				e.printStackTrace(); 
			}
		}
	}
	
	
	private void haritayiHazirla()
	{
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		//marker.addMarkersToMap(datalar, mMap);

		//Location loc = tracker.getLocation();
		//LatLng ll = new LatLng(loc.getLatitude(), loc.getLongitude());
		
		//mMap.addMarker(new MarkerOptions().position(ll).title("Ben"));
		ClosestPOI poi = new ClosestPOI();
		Vector<SpatialData> datalar = poi.findClosestPOI();
		SpatialData data = datalar.firstElement();
		LatLng ll = new LatLng(data.getLatitude(), data.getLongitude());
		
		mMap.addMarker(new MarkerOptions().position(ll).title(data.getTitle()));
		
		mMap.setMyLocationEnabled(true);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 8));
	}
	
	private Vector<SpatialData> createSpatialDataVector(int capacity)
	{		
		Vector<SpatialData> dataVector = new Vector<SpatialData>(capacity);
		
			

		for (int i = 0; i < dataVector.capacity(); i++) {
			GeoPoint point = new GeoPoint(
					getRandomLatitude(),
					getRandomLongitude());
			
			SpatialData data = new SpatialData(point, "Nokta-" + i);
			dataVector.add(data);
		}
		
		return dataVector;
	}
	
	private int getRandomLatitude()
	{
		int sayi = 0;
		
		sayi =  (int) (rand.nextDouble() * 6000000);
		sayi += 36000000;
		
		return sayi;
	}
	
	private int getRandomLongitude()
	{
		int sayi = (int) (rand.nextDouble() * 19000000);
		sayi += 26000000;
		return sayi;
	}

}
