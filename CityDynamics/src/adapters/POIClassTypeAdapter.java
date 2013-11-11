package adapters;

import java.util.Vector;

import data_utils.POIClassType;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class POIClassTypeAdapter extends ArrayAdapter<POIClassType>{

	Context context;
    int layoutResourceId;   
    Vector<POIClassType> data = null;
   
    public POIClassTypeAdapter(Context context, int layoutResourceId, Vector<POIClassType> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        int holder;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
           

            holder = data.get(position).getId();
            row.setTag(holder);
        }
        else
        {
            holder = Integer.parseInt(row.getTag().toString());
        }
        
        return row;
    }
}
