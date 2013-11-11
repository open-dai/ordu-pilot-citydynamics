package adapters;

import helpers.MapMarker;

import java.util.Vector;

import data_utils.GarbageCan;
import data_utils.GarbageCanType;
import android.content.Context;
import android.sampas.citydynamics.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomGCAdapter extends ArrayAdapter<GarbageCanType>{

	public Vector<GarbageCanType> typeList;
	private Context context;
	public MapMarker marker;
	public Vector<GarbageCan> allData;
	
	public MyCustomGCAdapter(Context context, int textViewResourceID, Vector<GarbageCanType> list, MapMarker mm, Vector<GarbageCan> dataList)
	{
		super(context, textViewResourceID, list);
		this.context = context;
		this.typeList = new Vector<GarbageCanType>();
		this.typeList.addAll(list);
		marker = mm;
		allData = dataList;
	}
	
	private class ViewHolder
	{
		TextView desc;
		CheckBox id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null;
		final MapMarker lMarker = marker;
		
		if(convertView == null)
		{
			LayoutInflater vi = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.poi_class_info, null);
			
			holder = new ViewHolder();
			holder.desc  = (TextView) convertView.findViewById(R.id.code);
			holder.id = (CheckBox) convertView.findViewById(R.id.checkBox1);
			
			convertView.setTag(holder);
			
			holder.id.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					GarbageCanType type = (GarbageCanType) cb.getTag();
					for(GarbageCanType t : typeList)
					{
						if(t.getTypeID()== type.getTypeID())
						{
							t.setChecked(cb.isChecked());
							break;
						}
					}
					
					//Toast.makeText(getContext(), String.format("%d - %d",  allData.size(), type.getId()), Toast.LENGTH_SHORT).show();
					//Toast.makeText(getContext(), lMarker.getClass().toString(), Toast.LENGTH_SHORT).show();
					marker.arrangeGCMarkers(allData, typeList);
					//Toast.makeText(getContext(), String.valueOf(type.getId()), Toast.LENGTH_SHORT).show();
					
				}
			});
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
			
		}
		
		GarbageCanType type = typeList.get(position);
		holder.desc.setText(type.getDescription());
		holder.id.setChecked(type.isChecked());
		
		holder.id.setTag(type);
		
		return convertView;
	}
	
	
}
