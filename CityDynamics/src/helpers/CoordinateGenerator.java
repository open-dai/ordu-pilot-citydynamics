package helpers;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.sampas.citydynamics.R;
import android.widget.ArrayAdapter;
import com.google.android.maps.GeoPoint;

import data_utils.SpatialData;

public class CoordinateGenerator {
	
	public Context _context;
	Random rand;
	
	public CoordinateGenerator(Context context)
	{
		_context = context;
		rand = new Random();
	}
	
	
	public Vector<SpatialData> createSpatialDataVector(int capacity)
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

		adapter = new ArrayAdapter<SpatialData>(_context,
				R.layout.list_item, datalar);

		return adapter;
	}
	
	private ArrayAdapter<String> convertSpatialToStringAdapter(
			ArrayAdapter<SpatialData> geoAdp) {
		ArrayAdapter<String> strAdapter = new ArrayAdapter<String>(
				_context, R.layout.list_item);

		for (int i = 0; i < geoAdp.getCount(); i++) {
			strAdapter.add(String.format("Latitude: %f \nLongitude: %f", geoAdp
					.getItem(i).getLatitude(), geoAdp.getItem(i)
					.getLongitude()));
		}

		return strAdapter;

	}
}
