package listeners;

import helpers.MapMarker;

import java.util.Vector;

import com.google.android.gms.maps.GoogleMap;

import data_utils.DataProvider;
import data_utils.GarbageCan;
import data_utils.POIData;
import android.content.Context;
import android.sampas.citydynamics.POIMain;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Switch;

public class GCTypeOnItemClickListener implements OnItemClickListener, OnItemSelectedListener, OnClickListener{
	
	MapMarker marker;
	DataProvider provider;
	Vector<Boolean> checkList;
	Vector<GarbageCan> allData;
	GoogleMap map; 
	Context context;
	
	public GCTypeOnItemClickListener(Context c, MapMarker m, DataProvider p, Vector<GarbageCan> d, GoogleMap gMap)
	{
		marker = m;
		provider = p;
		allData = d;
		map = gMap;
		context = c;
		checkList = new Vector<Boolean>(16);
		for(Boolean b : checkList)
		{
			b = false;
		}
	}
	
	
	@Override
	public void onItemSelected(AdapterView<?> adapter, View v, int pos, long id) {
//		CheckBox chkItem = (CheckBox) v;
//		String chkId = chkItem.getText().toString().split("-")[0].trim();
//		
//		checkList.setElementAt(!checkList.get(Integer.parseInt(chkId)), Integer.parseInt(chkId));
//		Vector<POIData> filtered = provider.getPOIDataByFilter(checkList, allData);
//		marker.clearAllMarkers();
//		marker.addPOIMarkersToMap(filtered, map);
//		Toast.makeText(context, "ItemSelected - " + chkId, Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
//		CheckBox chkItem = (CheckBox) v;
//		String chkId = chkItem.getText().toString().split("-")[0].trim();
//		
//		checkList.setElementAt(!checkList.get(Integer.parseInt(chkId)), Integer.parseInt(chkId));
//		Vector<POIData> filtered = provider.getPOIDataByFilter(checkList, allData);
//		marker.clearAllMarkers();
//		marker.addPOIMarkersToMap(filtered, map);
//		Toast.makeText(context, "ItemSelected - " + chkId, Toast.LENGTH_SHORT).show();
		
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	
}
