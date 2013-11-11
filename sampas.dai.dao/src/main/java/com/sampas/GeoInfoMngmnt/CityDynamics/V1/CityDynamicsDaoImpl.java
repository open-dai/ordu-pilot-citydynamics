package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import oracle.sql.STRUCT;

import com.sampas.Common.V1.AddressCityItem;
import com.sampas.Common.V1.AddressDistrictItem;
import com.sampas.Common.V1.AddressStreetItem;
import com.sampas.Common.V1.AddressTownItem;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.akos.kurumorganizasyon.model.Birim;
import com.sampas.common.model.AkosException;

public class CityDynamicsDaoImpl extends BaseDaoSupport implements
		CityDynamicsDao {

	public GetMapByCoordinateResponseType getMapByCoordinate(
			GetMapByCoordinateRequestType getMapByCoordinate) {
		return null;
	}

	public PatoiType[] queryClosestPatoi(
			QueryClosestPatoiRequestType queryClosestPatoi) {

		List<PatoiType> result = new ArrayList<PatoiType>();

		CoordinateType _koor = queryClosestPatoi.getMyCoordinate();
		double lat = Double.parseDouble(_koor.getLatitude());
		double lon = Double.parseDouble(_koor.getLongtitude());
		
		
		
/*
		try {

			Date _start = new Date();
			List<PatoiType> sonuc = (List<PatoiType>) getAkosJdbcSupport()
					.readDBObjectAsList("readQueryClosestPatoi");

			// List<PatoiType> result = new ArrayList<PatoiType>();
			for (PatoiType _item : sonuc) {

				double dist3 = DistanceFromCoordinates
						.getDistanceFromCoordinatesDeg(lat, lon, _item.getX(),
								_item.getY());
				if (dist3 < 6) {
					PoiType _poi = new PoiType();

					CoordinateType _coor = new CoordinateType();
					_coor.setLatitude(_item.getX().toString());
					_coor.setLongtitude(_item.getY().toString());
					_poi.setCoordinate(_coor);

					PatoiType poiItemPatoiType = new PatoiType();
					poiItemPatoiType.setAciklama(_item.getAciklama());
					poiItemPatoiType.setKurumId(_item.getKurumId());
					poiItemPatoiType.setPoi(_poi);
					poiItemPatoiType.setX(_item.getX());
					poiItemPatoiType.setY(_item.getY());
					System.out.println(" x2 : " + _item.getKurumId() + " y2 : "
							+ _item.getAciklama() + " = " + dist3);
					result.add(poiItemPatoiType);
				}

			}

			Date _finish = new Date();
			Date diff = new Date(_finish.getTime() - _start.getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(diff);
			int seconds = calendar.get(Calendar.SECOND);
			System.out.println("Bitis  : " + seconds);
			// getAkosJdbcSupport().readDBObjectAsList("readQueryClosestPatoi",
			// map);
			PatoiType[] _resultArray = new PatoiType[result.size()];
			for (int I = 0; I < result.size(); I++) {
				_resultArray[I] = result.get(I);
			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}
		*/

		 return null;
	}
/*
	public ArrayList<Coordinates> loadMultiPointGeometries(JGeometry geom) {
		ArrayList<Coordinates> locs = new ArrayList<Coordinates>();
		int numPoints = geom.getNumPoints();
		double[] ordinatesArray = geom.getOrdinatesArray();
		for(int j=0; j<numPoints; ++j) {
			locs.add(new Coordinates(ordinatesArray[2*j+1], ordinatesArray[2*j],0));
		}
		return locs;
	}

	public Coordinates loadSinglePointGeometry(JGeometry geom) {
		double[] pt = geom.getPoint();
		return new Coordinates(pt[1], pt[0], 0);
	}

	public Boolean PointInPolygon(ArrayList<Coordinates> _vertices,Coordinates point)
    {
        int j = _vertices.size() - 1;
        Boolean oddNodes = false;

        for (int i = 0; i < _vertices.size(); i++)
        {
            if (_vertices.get(i).getLongitude() < point.getLongitude() && _vertices.get(j).getLongitude() >= point.getLongitude() ||
            		_vertices.get(j).getLongitude() < point.getLongitude() && _vertices.get(i).getLongitude() >= point.getLongitude())
            {
                if (_vertices.get(i).getLatitude() +
                    (point.getLongitude() - _vertices.get(i).getLongitude())/(_vertices.get(j).getLongitude() - _vertices.get(i).getLongitude())*(_vertices.get(j).getLatitude() - _vertices.get(i).getLatitude()) < point.getLatitude())
                {
                    oddNodes = !oddNodes;
                }
            }
            j = i;
        }

        return oddNodes;
    }
*/

	public PatoiType[] queryClosestPatoiByAddress(
			QueryClosestPatoiByAddressRequestType queryClosestPatoiByAddressRequest) {
/*
		try {
			List<PoiType> result = new ArrayList<PoiType>();
			ArrayList<Coordinates> sinirList = new ArrayList<Coordinates>();

			if (queryClosestPatoiByAddressRequest.getQueryType()==0) // street level
			{
				CallableStatement callableStatement = super.getJdbcConnection().prepareCall("SELECT geometry FROM ORT_CADDE_SOKAK where ID = "+queryClosestPatoiByAddressRequest.getRecordID());

				 ResultSet rs = callableStatement.executeQuery();

				 STRUCT st = (oracle.sql.STRUCT) rs.getObject(0);
				 JGeometry j_geom = JGeometry.load(st);
				 sinirList = loadMultiPointGeometries(j_geom);

				 System.out.println(" Geldi ii :: "+sinirList.size());
			}

			if (sinirList.size()>0)
			{

			CallableStatement callableStatement1 = super.getJdbcConnection().prepareCall("SELECT geometry FROM ORT_CADDE_SOKAK where ID = "+queryClosestPatoiByAddressRequest.getRecordID());
			ResultSet rs1 = callableStatement1.executeQuery();
			 while (rs1.next()) {

				 STRUCT st1 = (oracle.sql.STRUCT) rs1.getObject(1);
				 JGeometry j_geom1 = JGeometry.load(st1);
				 Coordinates coor= loadSinglePointGeometry(j_geom1);

				 if (PointInPolygon(sinirList,coor))
				 {
					 //result.add(e);
				 }


			 }
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		*/

		return null;


	}

	public PoiType[] queryClosestPoi(QueryClosestPoiRequestType queryClosestPoi) {

		List<PoiType> result = new ArrayList<PoiType>();

		CoordinateType _koor = queryClosestPoi.getCoordinate();
		double lat = Double.parseDouble(_koor.getLatitude());
		double lon = Double.parseDouble(_koor.getLongtitude());
/*
		try {

			Date _start = new Date();
			List<PoiType> sonuc = (List<PoiType>) getAkosJdbcSupport()
					.readDBObjectAsList("readQueryClosestPoi");

			// List<PatoiType> result = new ArrayList<PatoiType>();
			for (PoiType _item : sonuc) {

				double dist3 = DistanceFromCoordinates
						.getDistanceFromCoordinatesDeg(lat, lon, _item.getX(),
								_item.getY());

				if (dist3 < 6) {
					PoiType _poi = new PoiType();

					CoordinateType _coor = new CoordinateType();
					_coor.setLatitude(_item.getX().toString());
					_coor.setLongtitude(_item.getY().toString());
					_poi.setCoordinate(_coor);
					_poi.setDescription(_item.getDescription());
					_poi.setName(_item.getName().toString());

					_poi.setPoiType(_item.getPoiType());

					System.out.println(" x3 : " + _item.getName() + " y3 : "
							+ _item.getDescription() + " = " + dist3);
					result.add(_poi);
				}

			}

			Date _finish = new Date();
			Date diff = new Date(_finish.getTime() - _start.getTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(diff);
			int seconds = calendar.get(Calendar.SECOND);
			System.out.println("Bitis  : " + seconds);
			// getAkosJdbcSupport().readDBObjectAsList("readQueryClosestPatoi",
			// map);
			PoiType[] _resultArray = new PoiType[result.size()];
			for (int I = 0; I < result.size(); I++) {
				_resultArray[I] = result.get(I);
			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}
*/
		return null;
	}

	public CanPoiItem[] FindClosestGC(
			QueryClosestGcRequestType queryClosestGcRequestType) {

		List<CanPoiItem> result = new ArrayList<CanPoiItem>();

		CoordinateType _koor = queryClosestGcRequestType.getCoordinate();
		double lat = Double.parseDouble(_koor.getLatitude());
		double lon = Double.parseDouble(_koor.getLongtitude());

		try {

			Date _start = new Date();
			List<CanPoiItem> sonuc = (List<CanPoiItem>) getAkosJdbcSupport()
					.readDBObjectAsList("readQueryClosestGc");

			// List<PatoiType> result = new ArrayList<PatoiType>();
			for (CanPoiItem _item : sonuc) {
/*
				double dist3 = DistanceFromCoordinates
						.getDistanceFromCoordinatesDeg(lat, lon, _item.getX(),
								_item.getY());

				if (dist3 < 6) {
					result.add(_item);
				}
*/
			}

			CanPoiItem[] _resultArray = new CanPoiItem[result.size()];
			for (int I = 0; I < result.size(); I++) {
				_resultArray[I] = result.get(I);
			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}

	}

	public CanType[] QueryCanType() {

		try {

			Date _start = new Date();
			List<CanType> sonuc = (List<CanType>) getAkosJdbcSupport()
					.readDBObjectAsList("readQueryCanTypes");

			CanType[] _resultArray = new CanType[sonuc.size()];
			for (int I = 0; I < sonuc.size(); I++) {
				_resultArray[I] = sonuc.get(I);
			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}

	}

	public GcCollectScheduleItem[] GetGCHoursForCan() {

		try {

			Date _start = new Date();
			List<GcCollectScheduleItem> sonuc = (List<GcCollectScheduleItem>) getAkosJdbcSupport()
					.readDBObjectAsList("readQueryGcCollectSchedule");

			GcCollectScheduleItem[] _resultArray = new GcCollectScheduleItem[sonuc
					.size()];
			for (int I = 0; I < sonuc.size(); I++) {
				_resultArray[I] = sonuc.get(I);
			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}
	}

	public PoiType[] ListTheStreets(QueryClosestPoiRequestType queryClosestPoi) {

		return null;
	}

	public PoiType[] ListTheCansByStreet(
			QueryClosestPoiRequestType queryClosestPoi) {

		return null;
	}

	public PoiType[] GetInformationForCan(
			QueryClosestPoiRequestType queryClosestPoi) {

		return null;
	}

	public PoiType[] ListPOITypes(QueryClosestPoiRequestType queryClosestPoi) {

		return null;
	}

	public PoiType[] ListPOIsByType(QueryClosestPoiRequestType queryClosestPoi) {

		return null;
	}

	public PoiType[] ListPOIsByArea(QueryClosestPoiRequestType queryClosestPoi) {

		return null;
	}

	public AddressCityItem[] ListTheAddressCity() {

		try {

			Date _start = new Date();
			List<AddressCityItem> sonuc = (List<AddressCityItem>) getAkosJdbcSupport()
					.readDBObjectAsList("getAddressCity");

			AddressCityItem[] _resultArray = new AddressCityItem[sonuc.size()];
			for (int I = 0; I < sonuc.size(); I++) {
				_resultArray[I] = sonuc.get(I);
			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}
	}

	public AddressTownItem[] ListTheAddressTowns(
			QueryAddressTownRequestType queryAddressTownRequestType) {

		try {

			List<AddressTownItem> sonuc = (List<AddressTownItem>) getAkosJdbcSupport()
					.readDBObjectAsList("getAddressTown");

			AddressTownItem[] _resultArray = new AddressTownItem[sonuc.size()];
			for (int I = 0; I < sonuc.size(); I++) {
				if (queryAddressTownRequestType.getCityCode() == sonuc.get(I)
						.getIl_id()) {
					_resultArray[I] = sonuc.get(I);
				}
			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}
	}

	public AddressDistrictItem[] ListTheAddressDistrict(
			QueryAddressDistrictRequestType queryAddressDistrictRequest) {
		try {

			List<AddressDistrictItem> sonuc = (List<AddressDistrictItem>) getAkosJdbcSupport()
					.readDBObjectAsList("getAddressDistrict");

			AddressDistrictItem[] _resultArray = new AddressDistrictItem[sonuc
					.size()];
			for (int I = 0; I < sonuc.size(); I++) {
				if (queryAddressDistrictRequest.getTownCode() == sonuc.get(I)
						.getIlce_id()) {
					_resultArray[I] = sonuc.get(I);
				}

			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}
	}

	public AddressStreetItem[] ListTheAddressStreet(
			QueryAddressStreetRequestType queryAddressStreetRequestType) {

		try {

			List<AddressStreetItem> sonuc = (List<AddressStreetItem>) getAkosJdbcSupport()
					.readDBObjectAsList("getAddressStreet");

			AddressStreetItem[] _resultArray = new AddressStreetItem[sonuc
					.size()];
			for (int I = 0; I < sonuc.size(); I++) {
				if (queryAddressStreetRequestType.getDistrictCode() == sonuc
						.get(I).getMahalle_id()) {
					_resultArray[I] = sonuc.get(I);
				}

			}

			return _resultArray;

		} catch (AkosException e) {
			System.out.println("Err : " + e.getMessage());
			return null;
		}
	}
}
