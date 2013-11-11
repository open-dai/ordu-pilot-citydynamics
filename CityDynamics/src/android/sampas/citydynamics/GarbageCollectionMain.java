package android.sampas.citydynamics;

import helpers.AddressFinder;
import helpers.MapMarker;

import java.util.Vector;

import listeners.GCTypeOnItemClickListener;
import listeners.POIMapClickListener;
import listeners.POITypeOnItemClickListener;

import com.google.android.gms.internal.d;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import data_utils.DataProvider;
import data_utils.GarbageCan;
import data_utils.GarbageCanType;
import data_utils.POIClassType;
import data_utils.POIData;
import adapters.MyCustomAdapter;
import adapters.MyCustomGCAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GarbageCollectionMain extends Activity{


	Button btnSearch;
	EditText txtAddress;
	GoogleMap mapGC;
	ListView lstGCType;
	DataProvider provider;
	//ArrayAdapter<String> adapter;
	MyCustomGCAdapter adapter;
	Vector<GarbageCan> gcDatas;
	int capacity;
	MapMarker mMarker;
	GCTypeOnItemClickListener poiListener;
	
	Spinner spnCity;
	Spinner spnTown;
	Spinner spnDistrict;
	Spinner spnStreet;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) { // for solving android.os.networkonmainthreadexception
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gc_map);
		
		init_2();
	}
	
	private void init_2()
	{
		//spinnerDoldur();
		haritayiHazirla();
		digerDoldur();
	}
	
	private void digerDoldur()
	{
		provider = new DataProvider();
		capacity = 50;
		gcDatas = provider.generateGCData(capacity);
		mMarker = new MapMarker(capacity, mapGC);
		poiListener = new GCTypeOnItemClickListener(this, mMarker, provider, gcDatas, mapGC);
		
		mMarker.addGCMarkersToMap(gcDatas);
		lstGCType = (ListView) findViewById(R.id.lstGCType);

		fillCanTypeAdapter();
		//fillPOITypeAdapter();
		lstGCType.setAdapter(adapter);
		lstGCType.setItemsCanFocus(true);
		mapGC.setOnMapClickListener(new POIMapClickListener(mapGC));
	}
	
//	private void spinnerDoldur()
//	{
//		spnCity = (Spinner) findViewById(R.id.spnCity);
//		spnTown = (Spinner) findViewById(R.id.spnTown);
//		spnDistrict = (Spinner) findViewById(R.id.spnDistrict);
//		spnStreet = (Spinner) findViewById(R.id.spnStreet);
//		
//		String[] arrCity = {"Ordu"};
//		String[] arrTown = {"Bahçelievler"};
//		String[] arrDistrict = {"Þahincili"};
//		String[] arrStreet = {"503. Sk"};
//		
//		ArrayAdapter<String> adpCity = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrCity);
//		ArrayAdapter<String> adpTown = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrTown);
//		ArrayAdapter<String> adpDistrict = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrDistrict);
//		ArrayAdapter<String> adpStreet = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrStreet);
//		
//		adpCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		adpTown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		adpDistrict.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		adpStreet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		
//		
//		
//		spnCity.setAdapter(adpCity);
//		spnTown.setAdapter(adpTown);
//		spnDistrict.setAdapter(adpDistrict);
//		spnStreet.setAdapter(adpStreet);
//	}
	
	private void haritayiHazirla()
	{
		btnSearch = (Button) findViewById(R.id.btnSearchPOI);
		txtAddress = (EditText) findViewById(R.id.txtAddressPOI);
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddressFinder finder = new AddressFinder();
				TextView txtAdd = (EditText) findViewById(R.id.txtAddressPOI);
				
				LatLng ll = finder.getLatLongFromAddress(txtAdd.getText().toString().replace(" ", "+"));
				
				mapGC.addMarker(new MarkerOptions().position(ll).title(txtAdd.getText().toString()));
				mapGC.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
			}
		});
		
		mapGC = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapGC)).getMap();
		mapGC.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		mapGC.setOnMapClickListener(new POIMapClickListener(mapGC));
		mapGC.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.973336, 37.890677), 14));
	}

	
	private void init()
	{


		lstGCType = (ListView) findViewById(R.id.lstGCType);
		provider = new DataProvider();
		//fillPOITypeAdapter();
		lstGCType.setAdapter(adapter);
		lstGCType.setItemsCanFocus(true);
		mapGC.setOnMapClickListener(new POIMapClickListener(mapGC));
		mapGC.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.983373, 37.876836), 14));
		capacity = 100;
		gcDatas = provider.generateGCData(capacity);
		mMarker = new MapMarker(capacity, mapGC);
		poiListener = new GCTypeOnItemClickListener(this, mMarker, provider, gcDatas, mapGC);
		//lstPOIType.setOnItemClickListener(poiListener);
		//lstPOIType.setOnItemSelectedListener(poiListener);

	}
	
	private void fillCanTypeAdapter()
	{
		
		//Vector<POIClassType> POIArray = provider.getPOIClasses();
		Vector<GarbageCanType> GCArray = provider.getGCType();
		//Vector<String> PoiStrArray = ConvertToStringVector(POIArray);
		//adapter = new ArrayAdapter<String>(this, R.layout.check_list_item, PoiStrArray);
		adapter = new MyCustomGCAdapter(this, R.layout.poi_class_info, GCArray, mMarker, gcDatas);
		//adapter = new POIClassTypeAdapter(this, R.layout.check_list_item, POIArray);
		
		//MyCustomAdapter
	}
	
}
