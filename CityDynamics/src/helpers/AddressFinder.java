package helpers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;

import android.util.Log;


public class AddressFinder {

	private double lng = 0;
	private double lat = 0;
	
	public double getLng() {
		return lng;
	}


	public void setLng(double lng) {
		this.lng = lng;
	}


	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}

	

	public LatLng getLatLongFromAddress(String youraddress) {
		
        HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?address=" +youraddress+"&sensor=false");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONArray array = null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
            array = (JSONArray)jsonObject.get("results");
            int asd = array.length();
            lng = array.getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lng");

            lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lat");

            Log.i("latitude",String.valueOf(lat));
            Log.i("longitude",String.valueOf(lng));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
        	lat = 0;
        	lng = 0;
            e.printStackTrace();
        }
        
        LatLng ll = new LatLng(lat,  lng);
        
        return ll;

    }
}
