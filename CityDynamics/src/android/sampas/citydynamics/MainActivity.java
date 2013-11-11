package android.sampas.citydynamics;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;

import data_utils.SpatialData;

import helpers.GPSTracker;
import helpers.MapMarker;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String currentLocationStr = "";
	GPSTracker tracker;
	Location curLocation;
	Random rand = new Random();
	Vector<SpatialData> datalar;
	
	Button btnGC;
	Button btnPOI;
	
	
	private void init()
	{
		btnGC = (Button) findViewById(R.id.btnGC);
		btnPOI = (Button) findViewById(R.id.btnPOI);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) { // for solving android.os.networkonmainthreadexception
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_window);
		init();
		
		
		btnGC.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent in = new Intent(MainActivity.this, GarbageCollectionMain.class);
				startActivity(in);
			}
		});
		
		btnPOI.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent ine = new Intent(MainActivity.this, POIMain.class);
				startActivity(ine);
			}
		});
		
		
//		Button btnEnlem = (Button) findViewById(R.id.btnLatitude);
//		btnEnlem.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent ine = new Intent(MainActivity.this, POIMain.class);
//				startActivity(ine);
//			}
//		});
	}
	
	private void sonHali()
	{
		tracker = new GPSTracker(this);
		Location loc = tracker.getLocation();
		datalar = createSpatialDataVector(20);
		
		ListView lstView = (ListView) findViewById(R.id.lstView);

		ArrayAdapter<String> adapter;

		ArrayAdapter<SpatialData> geoAdapter = createSpatialDataAdapter(datalar);

		adapter = convertSpatialToStringAdapter(geoAdapter);
		lstView.setAdapter(adapter);
		
		lstView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(MainActivity.this, 
								String.format("0- %s\n1- %s\n2- %d\n3- %d", 
										arg0.getClass().toString(),
										arg1.getClass().toString(),
										arg2, arg3), 
										Toast.LENGTH_SHORT).show();
				
			}
		});
		
		Button btnBilgi = (Button) findViewById(R.id.btnName);

		btnBilgi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

//				currentLocationStr = String.format(
//						"Latitude: %f \nLongitutde: %f",
//						curLocation.getLatitude(), curLocation.getLongitude());
//				Toast.makeText(MainActivity.this, currentLocationStr,
//						Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this, Harita.class);
				
				startActivity(intent);

			}
		});
		
		
//		Marker marker =  mMap.addMarker(new MarkerOptions()
//							.position(new LatLng(
//											loc.getLatitude(), 
//											loc.getLongitude()
//											)
//									).title("Ben")
//							);
//		
		
		
		//MapView map = (MapView) findViewById(R.id.map);
	}
	
	
	private Vector<SpatialData> createSpatialDataVector(int capacity)
	{		
		Vector<SpatialData> dataVector = new Vector<SpatialData>(capacity + 1);
					

		for (int i = 0; i < dataVector.capacity() - 1; i++) {
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
	
	
	private ArrayAdapter<SpatialData> createSpatialDataAdapter(Vector<SpatialData> datalar) {
		ArrayAdapter<SpatialData> adapter;

		//fillGeoPointArray(datalar);

		adapter = new ArrayAdapter<SpatialData>(getBaseContext(),
				R.layout.list_item, datalar);

		return adapter;
	}
	
	private ArrayAdapter<String> convertSpatialToStringAdapter(
			ArrayAdapter<SpatialData> geoAdp) {
		ArrayAdapter<String> strAdapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.list_item);

		for (int i = 0; i < geoAdp.getCount(); i++) {
			strAdapter.add(String.format("Latitude: %f \nLongitude: %f", geoAdp
					.getItem(i).getLatitude(), geoAdp.getItem(i)
					.getLongitude()));
		}

		return strAdapter;

	}
	
	
	/***********************************************************/
	

	private void ayriFonk()
	{
		tracker = new GPSTracker(MainActivity.this);

		ListView lstView = (ListView) findViewById(R.id.lstView);

		ArrayAdapter<String> adapter;

		ArrayAdapter<GeoPoint> geoAdapter = createDataAdapter(100);

		adapter = convertGeoPointToStringAdapter(geoAdapter);
		lstView.setAdapter(adapter);

		curLocation = tracker.getLocation();
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		LocationListener listener = new mylocationlistener();
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
		
		
		//LocationListener ll = new mylocationlistener();
		//lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);

		Button btnBilgi = (Button) findViewById(R.id.btnName);

		btnBilgi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				currentLocationStr = String.format(
						"Latitude: %f \nLongitutde: %f",
						curLocation.getLatitude(), curLocation.getLongitude());
				Toast.makeText(MainActivity.this, currentLocationStr,
						Toast.LENGTH_LONG).show();
			}
		});
	}
	
	
	private ArrayAdapter<GeoPoint> createDataAdapter(int capacity) {
		ArrayAdapter<GeoPoint> adapter;

		Vector<GeoPoint> datalar = new Vector<GeoPoint>(capacity);

		fillGeoPointArray(datalar);

		adapter = new ArrayAdapter<GeoPoint>(getBaseContext(),
				R.layout.list_item, datalar);

		return adapter;
	}

	private void fillGeoPointArray(Vector<GeoPoint> array) {
		Random rand = new Random();

		for (int i = 0; i < array.capacity(); i++) {
			GeoPoint point = new GeoPoint((int) (rand.nextDouble() * 180000000),
					(int) (rand.nextDouble() * 360000000));
			array.add(point);
		}
	}

	private ArrayAdapter<String> convertGeoPointToStringAdapter(
			ArrayAdapter<GeoPoint> geoAdp) {
		ArrayAdapter<String> strAdapter = new ArrayAdapter<String>(
				getBaseContext(), R.layout.list_item);

		for (int i = 0; i < geoAdp.getCount(); i++) {
			strAdapter.add(String.format("Latitude: %d \nLongitude: %d", geoAdp
					.getItem(i).getLatitudeE6(), geoAdp.getItem(i)
					.getLongitudeE6()));
		}

		return strAdapter;

	}

	private class mylocationlistener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			Date today = new Date();
			Timestamp currentTimeStamp = new Timestamp(today.getTime());
			if (location != null) {
				Log.d("LOCATION CHANGED", location.getLatitude() + "");
				Log.d("LOCATION CHANGED", location.getLongitude() + "");
				String str = "\n CurrentLocation: " + "\n Latitude: "
						+ location.getLatitude() + "\n Longitude: "
						+ location.getLongitude() + "\n Accuracy: "
						+ location.getAccuracy() + "\n CurrentTimeStamp "
						+ currentTimeStamp;
				Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG)
						.show();
				//tv.append(str);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			Toast.makeText(MainActivity.this, "Error onProviderDisabled",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onProviderEnabled(String provider) {
			Toast.makeText(MainActivity.this, "onProviderEnabled",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			Toast.makeText(MainActivity.this, "onStatusChanged",
					Toast.LENGTH_LONG).show();
		}
	}
}
