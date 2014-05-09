package com.sampas.socbs.opendai.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.sampas.akos.ortak.model.Bina;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.geometry.ICoordinate;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IPoint;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;

public class GpsTools {

	public static double INCHTOCENTIMETER=2.54;
	public static double DEFAULT_DPI=96;
	public static double dpi=0;
	
	public static String getDateNow()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		//System.out.println(dateFormat.format(cal.getTime()));
		return dateFormat.format(cal.getTime());
	}
	
	
	public static Bina searchBuild(List<Bina> _list,Long BuildId)
	{
		//Bina bina = null;
		
		for(int I=0;I<_list.size();I++)
		{
			//System.out.println("IDL : "+_list.get(I).getId()+"--"+BuildId);
			
			if (_list.get(I).getId().equals(BuildId))
			{
				return _list.get(I);
			}
		}
		return null;
	}
	
	
	public static  double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      if (unit == 'K') {
	        dist = dist * 1.609344;
	      } else if (unit == 'N') {
	        dist = dist * 0.8684;
	        }
	      return (dist);
	    }


	    private static double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }

	    private static double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }
	    
	    public static double distance1(double lat1, double lat2, double lon1, double lon2,
	            double el1, double el2) {

	        final int R = 6371; // Radius of the earth

	        Double latDistance = deg2rad(lat2 - lat1);
	        Double lonDistance = deg2rad(lon2 - lon1);
	        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
	                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        double distance = R * c * 1000; // convert to meters

	        double height = el1 - el2;
	        distance = Math.pow(distance, 2) + Math.pow(height, 2);
	        return Math.sqrt(distance);
	    }

		public static Double coordinateCorrector(Double coordinateValue, int sensivity){

			try{
				coordinateValue = coordinateValue*Math.pow(10,sensivity);
				return (coordinateValue.longValue() / Math.pow(10,sensivity));
			}catch (Exception e) {
				// TODO: handle exception
			}
			return Double.MIN_VALUE;
		}
		
		public static IEnvelope getBBoxFromCoordinateWithOffset(double centerX,double centerY, double offset,Integer floatNumberAfterComma) {

			double minX;
			double minY;
			double maxX;
			double maxY;

			if(floatNumberAfterComma!=null){
				minX = coordinateCorrector(centerX - offset, floatNumberAfterComma);
				minY = coordinateCorrector(centerY - offset, floatNumberAfterComma);
				maxX = coordinateCorrector(centerX+ offset, floatNumberAfterComma);
				maxY = coordinateCorrector(centerY + offset, floatNumberAfterComma);
			}else{
				minX=centerX - offset;
				minY=centerY - offset;
				maxX=centerX + offset;
				maxY=centerY + offset;
			}
			return (new SmpBoundingRectangle(minX, minY, maxX, maxY));
		}	
				

		public static IEnvelope getCorrectedEnvelope(double minLon,double minLat,double maxLon,double maxLat,Integer floatNumberAfterComma) {

			double minX;
			double minY;
			double maxX;
			double maxY;

			if(floatNumberAfterComma!=null){
				minX = coordinateCorrector(minLon, floatNumberAfterComma);
				minY = coordinateCorrector(minLat, floatNumberAfterComma);
				maxX = coordinateCorrector(maxLon, floatNumberAfterComma);
				maxY = coordinateCorrector(maxLat, floatNumberAfterComma);
			}else{
				minX=minLon;
				minY=minLat;
				maxX=maxLon;
				maxY=maxLat;
			}
			return (new SmpBoundingRectangle(minX, minY, maxX, maxY));
		}
		
		
		
		public static IEnvelope getBBoxFromPointWithScale(IPoint point,IEnvelope displayEnvelope, double scale, int round) {

			if (point!=null && point.getCoordinateSystem()!=null) {
				if(point.getCoordinateSystem().getEPSGCode().equals((new SmpCoordinateSystem("EPSG:2320")).getEPSGCode()) 
						|| point.getCoordinateSystem().getEPSGCode().equals((new SmpCoordinateSystem("EPSG:2319")).getEPSGCode())
						|| point.getCoordinateSystem().getEPSGCode().equals((new SmpCoordinateSystem("EPSG:2321")).getEPSGCode())
						|| point.getCoordinateSystem().getEPSGCode().equals((new SmpCoordinateSystem("EPSG:2322")).getEPSGCode())
						|| point.getCoordinateSystem().getEPSGCode().equals((new SmpCoordinateSystem("EPSG:2323")).getEPSGCode())
						|| point.getCoordinateSystem().getEPSGCode().equals((new SmpCoordinateSystem("EPSG:2324")).getEPSGCode())
						|| point.getCoordinateSystem().getEPSGCode().equals((new SmpCoordinateSystem("EPSG:2325")).getEPSGCode())){

					if(displayEnvelope!=null && displayEnvelope.getWidth()!=0 && displayEnvelope.getHeight()!=0){

						try {

							double totalWidth = (displayEnvelope.getWidth() * INCHTOCENTIMETER)/ (100 * scale * getDpi());
							double totalHeight = (displayEnvelope.getHeight() * INCHTOCENTIMETER)/ (100 * scale * getDpi());

							return getCorrectedEnvelope ((point.getX()-(totalWidth/2)), 
									(point.getY()-(totalHeight/2)), 
									(point.getX()+(totalWidth/2)), 
									(point.getY()+(totalHeight/2)), round);

						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					}

				}
			}
			return null;
		}		
		public static  double getDpi() {

			if (dpi==0) {
				return DEFAULT_DPI;
			}
			return dpi;
		}
		
		/**
		 * @return the dpi
		 */
		public static void setDpi(double dpiValue) {
			dpi=dpiValue;
		}
}
