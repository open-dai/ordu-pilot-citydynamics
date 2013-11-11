package android.sampas.citydynamics;

import java.util.Vector;

import listeners.POIMapClickListener;
import listeners.POITypeOnItemClickListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import data_utils.DataProvider;
import data_utils.POIClassType;
import data_utils.POIData;

import helpers.AddressFinder;
import helpers.MapMarker;
import adapters.MyCustomAdapter;
import adapters.POIClassTypeAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class POIMain extends Activity{
	
	Button btnSearch;
	EditText txtAddress;
	GoogleMap mapPOI;
	ListView lstPOIType;
	DataProvider provider;
	//ArrayAdapter<String> adapter;
	MyCustomAdapter adapter;
	Vector<POIData> poiDatas;
	int capacity;
	MapMarker mMarker;
	POITypeOnItemClickListener poiListener;
	
	private void init()
	{
		btnSearch = (Button) findViewById(R.id.btnSearchPOI);
		txtAddress = (EditText) findViewById(R.id.txtAddressPOI);
		mapPOI = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapPOI)).getMap();
		mapPOI.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		lstPOIType = (ListView) findViewById(R.id.lstPOIType);
		provider = new DataProvider();
		
		
		
		capacity = 100;
		poiDatas = provider.generatePOIData(capacity);
		mMarker = new MapMarker(capacity, mapPOI);
		mMarker.addPOIMarkersToMap(poiDatas, mapPOI);
		
		fillPOITypeAdapter();
		lstPOIType.setAdapter(adapter);
		lstPOIType.setItemsCanFocus(true);
		mapPOI.setOnMapClickListener(new POIMapClickListener(mapPOI));
		mapPOI.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.983373, 37.876836), 14));

		
		//Toast.makeText(this, String.valueOf(poiDatas.size()), Toast.LENGTH_LONG).show();

		poiListener = new POITypeOnItemClickListener(this, mMarker, provider, poiDatas, mapPOI);
		
		btnSearch = (Button) findViewById(R.id.btnSearchPOI);
		txtAddress = (EditText) findViewById(R.id.txtAddressPOI);
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddressFinder finder = new AddressFinder();
				TextView txtAdd = (EditText) findViewById(R.id.txtAddressPOI);
				
				LatLng ll = finder.getLatLongFromAddress(txtAdd.getText().toString().replace(" ", "+"));
				
				mapPOI.addMarker(new MarkerOptions().position(ll).title(txtAdd.getText().toString()));
				mapPOI.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
			}
		});
		
		//lstPOIType.setOnItemClickListener(poiListener);
		//lstPOIType.setOnItemSelectedListener(poiListener);
		
//		lstPOIType.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				
//				Toast.makeText(POIMain.this, "Seçildi", Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		lstPOIType.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int pos, long id) 
			{
				Toast.makeText(POIMain.this, "ItemClick", Toast.LENGTH_SHORT).show();
			}
		});
		
//		lstPOIType.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				Toast.makeText(POIMain.this, "Dokunuldu", Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		});
		
		
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) { // for solving android.os.networkonmainthreadexception
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poi_map);
		init();
	}
	
	private void fillPOITypeAdapter()
	{
		
		Vector<POIClassType> POIArray = provider.getPOIClasses();
		//Vector<POIClassType> POIArray = provider.getPOIClassTypeFromService();
		Vector<String> PoiStrArray = ConvertToStringVector(POIArray);
		//adapter = new ArrayAdapter<String>(this, R.layout.check_list_item, PoiStrArray);
		adapter = new MyCustomAdapter(this, R.layout.poi_class_info, POIArray, mMarker, poiDatas);
		//adapter = new POIClassTypeAdapter(this, R.layout.check_list_item, POIArray);
		
		//MyCustomAdapter
	}
	
	private Vector<String> ConvertToStringVector(Vector<POIClassType> array)
	{
		Vector<String> strArray = new Vector<String>(array.capacity());
		for(POIClassType poi : array)
		{
			strArray.add(String.format("%d - %s",poi.getId(), poi.getAciklama()));
		}
		
		return strArray;
	}
	
	
	
}
