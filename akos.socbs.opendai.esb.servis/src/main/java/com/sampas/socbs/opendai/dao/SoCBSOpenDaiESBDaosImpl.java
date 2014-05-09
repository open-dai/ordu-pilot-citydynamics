package com.sampas.socbs.opendai.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Common.V1.AddressCityItem;
import Common.V1.AddressDistrictItem;
import Common.V1.AddressStreetItem;
import Common.V1.AddressTownItem;
import Common.V1.ComponentType;
import GeoInfoMngmnt.CityDynamics.V1.CanPoiItem;
import GeoInfoMngmnt.CityDynamics.V1.CanType;
import GeoInfoMngmnt.CityDynamics.V1.FindClosestGCRequest;
import GeoInfoMngmnt.CityDynamics.V1.FindClosestGCResponce;
import GeoInfoMngmnt.CityDynamics.V1.GcCollectScheduleItem;
import GeoInfoMngmnt.CityDynamics.V1.GetGCHoursForCanRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetGCHoursForCanResponce;
import GeoInfoMngmnt.CityDynamics.V1.GetInformationForCanRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetInformationForCanResponce;
import GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateRequest;
import GeoInfoMngmnt.CityDynamics.V1.GetMapByCoordinateResponse;
import GeoInfoMngmnt.CityDynamics.V1.ListPOITypesRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListPOITypesResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByAreaRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByAreaResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByTypeRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListPOIsByTypeResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressCityResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressDistrictRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressDistrictResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressStreetRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressStreetResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressTownsRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheAddressTownsResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheCansByStreetRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheCansByStreetResponce;
import GeoInfoMngmnt.CityDynamics.V1.ListTheStreetsRequest;
import GeoInfoMngmnt.CityDynamics.V1.ListTheStreetsResponce;
import GeoInfoMngmnt.CityDynamics.V1.QueryBusinessOfficeRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryBusinessOfficeResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryCanTypeResponce;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIByAddressRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIByAddressResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPATOIResponse;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPOIRequest;
import GeoInfoMngmnt.CityDynamics.V1.QueryClosestPOIResponse;
import GeoInfoMngmnt.PropertyMngmnt.V1.CityType;
import GeoInfoMngmnt.PropertyMngmnt.V1.CountyType;
import GeoInfoMngmnt.PropertyMngmnt.V1.DistrictType;
import GeoInfoMngmnt.PropertyMngmnt.V1.HourType;
import GeoInfoMngmnt.PropertyMngmnt.V1.StreetType;

import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.socbs.base.model.MapReturnType;

public class SoCBSOpenDaiESBDaosImpl  extends BaseDaoSupport  {

	
	public QueryClosestPATOIResponse queryClosestPATOI(QueryClosestPATOIRequest queryClosestPATOIRequest)
	{
		return null;
	}
	
	public QueryClosestPATOIByAddressResponse queryClosestPATOIByAddress(QueryClosestPATOIByAddressRequest queryClosestPATOIByAddressRequest)
	{
		return null;
	}


	public GetMapByCoordinateResponse getMapByCoordinate(GetMapByCoordinateRequest getMapByCoordinateRequest)
	{
		return null;
	}

	public QueryClosestPOIResponse queryClosestPOI(QueryClosestPOIRequest queryClosestPOIRequest)
	{
		return null;
	}

	public FindClosestGCResponce findClosestGC(FindClosestGCRequest findClosestGCRequest) 
	{
		
		
		return null;
	}
	
	public QueryCanTypeResponce queryCanType() 
	{
		
		QueryCanTypeResponce queryCanTypeResponce = new QueryCanTypeResponce();
	      System.out.println("GELDI");
		String sorgu = "SELECT T.* FROM TIM_COP_KONTEYNER_TURLERI T";
		String where ="";
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<CanType> _list= new ArrayList<CanType>();
			while (rs.next()) {
				CanType canType = new CanType();
				//canType.setCompanyId(rs.getLong("KURUM_ID"));
				canType.setName(rs.getString("ADI"));
				canType.setCode(rs.getLong("KODU"));
				
				_list.add(canType);
				//tempBean.setServiceId(rs.getLong("SERVICE_ID"));
			}
			
			queryCanTypeResponce.setCanTypeList(_list.toArray(new CanType[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
      return queryCanTypeResponce;
	}
	
	
	public GetGCHoursForCanResponce getGCHoursForCan(GetGCHoursForCanRequest getGCHoursForCanRequest) 
	{
		GetGCHoursForCanResponce getGCHoursForCanResponce = new GetGCHoursForCanResponce();

		String sorgu = "SELECT  T.KURUM_ID,  T.YILI,T.DONEM, T.GUN, T.MAHALLE_ID,T.CADDE_SOKAK_ID, T.BASLAMA_SAAT, T.BITIS_SAAT,T.ACIKLAMA FROM TIM_SEMT_COP_TOPLAMA T ";
		String where ="";
		
	      if (getGCHoursForCanRequest.getAddressDistrictItem()!=null )	    	  
	      {
		      if (getGCHoursForCanRequest.getAddressStreetItem().getMahalle_id()!=null )
		      {
		    	  if (getGCHoursForCanRequest.getAddressStreetItem().getMahalle_id().toString().trim()!="")
		    	  {
		    		  where += " WHERE  T.MAHALLE_ID = "+getGCHoursForCanRequest.getAddressStreetItem().getMahalle_id();
		    	  }
		      }
	      }
	      
	      if (getGCHoursForCanRequest.getAddressStreetItem()!=null)
	      {
	    	  if (getGCHoursForCanRequest.getAddressStreetItem().getCadde_sokak_id()!=null)	    	  
	    	  {	    	  
		    	  if (getGCHoursForCanRequest.getAddressStreetItem().getCadde_sokak_id().toString().trim()!="")
		    	  {
			    	  if (where.length()>1)
			    	  {
			    		  where+=" WHERE ";
			    	  } else
			    	  {
			    		  where+=" AND ";
			    	  }
			    	  where += "T.CADDE_SOKAK_ID = "+getGCHoursForCanRequest.getAddressStreetItem().getCadde_sokak_id();
		    	  }
	    	  }
	      }	
		
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<GcCollectScheduleItem> _list= new ArrayList<GcCollectScheduleItem>();
			while (rs.next()) {
				GcCollectScheduleItem gcCollectScheduleItem = new GcCollectScheduleItem();
				gcCollectScheduleItem.setComment(rs.getString("ACIKLAMA"));
				HourType startTime = new HourType();
				startTime.setValue(rs.getString("BASLAMA_SAAT"));

				HourType finishTime = new HourType();
				finishTime.setValue(rs.getString("BITIS_SAAT"));
				
				gcCollectScheduleItem.setStartTime(startTime);				
				gcCollectScheduleItem.setFinishTime(finishTime);
				
				DistrictType districtType = new DistrictType();
				districtType.setId(""+rs.getLong("MAHALLE_ID"));
				gcCollectScheduleItem.setDistrictType(districtType);
				
				StreetType streetType = new StreetType();
				streetType.setId(""+rs.getLong("CADDE_SOKAK_ID"));
				gcCollectScheduleItem.setStreetType(streetType);
				
				gcCollectScheduleItem.setPeriod(rs.getLong("DONEM"));
				gcCollectScheduleItem.setDay(rs.getLong("GUN"));
				gcCollectScheduleItem.setCompanyId(rs.getLong("KURUM_ID"));				
				gcCollectScheduleItem.setYear(rs.getLong("YILI"));
				
				
				_list.add(gcCollectScheduleItem);
				//tempBean.setServiceId(rs.getLong("SERVICE_ID"));
			}
			
			getGCHoursForCanResponce.setGcCollectScheduleItemList(_list.toArray(new GcCollectScheduleItem[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
        return getGCHoursForCanResponce;

	}
	
	public ListTheStreetsResponce listTheStreets(ListTheStreetsRequest listTheStreetsRequest) 
	{
		ListTheStreetsResponce listTheStreetsResponce = new ListTheStreetsResponce();
	      System.out.println("GELDI");
		String sorgu = "select OC.CADDE_SOKAK_ID, OS.CADDE_SOKAK_ADI,OC.MAHALLE_ID from ORT_MAHALLE_CADDE OC left join ORT_CADDE_SOKAK OS on OS.ID = OC.CADDE_SOKAK_ID ";
		String where ="";
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<StreetType> _list= new ArrayList<StreetType>();
			while (rs.next()) {
				StreetType streetType = new StreetType();
				streetType.setId(""+rs.getLong("CADDE_SOKAK_ID"));
				streetType.setWidth("0");
				streetType.setLength("0");
				Common.V1.ComponentType streetInfo = new ComponentType(""+rs.getLong("CADDE_SOKAK_ID"), rs.getString("CADDE_SOKAK_ADI"));
				streetType.setStreetInfo(streetInfo);
				_list.add(streetType);
				//tempBean.setServiceId(rs.getLong("SERVICE_ID"));
			}
			
			listTheStreetsResponce.setStreetTypeList(_list.toArray(new StreetType[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
        return listTheStreetsResponce;
	}
	
	public ListTheCansByStreetResponce listTheCansByStreet(ListTheCansByStreetRequest listTheCansByStreetRequest) 
	{
		ListTheCansByStreetResponce listTheCansByStreetResponce = new ListTheCansByStreetResponce();
	      System.out.println("GELDI");
	      String sorgu ="select * from TIM_COP ";
	      String where ="";
	      if (listTheCansByStreetRequest.getAddressDistrictItem()!=null)
	      {
	    	  
		      if (listTheCansByStreetRequest.getAddressDistrictItem().getId()!=null)
		      {
			      if (listTheCansByStreetRequest.getAddressDistrictItem().getId().toString()!="")
			      {
			    	  where += " WHERE  MAHALLE_ID = "+listTheCansByStreetRequest.getAddressDistrictItem().getId();
			      }
		      }
	      }
	      
	      if (listTheCansByStreetRequest.getAddressStreetItem()!=null)
	      {
		      if (listTheCansByStreetRequest.getAddressStreetItem().getCadde_sokak_id()!=null)
		      {	    	  
			      if (listTheCansByStreetRequest.getAddressStreetItem().getCadde_sokak_id().toString()!="")
			      {
			    	  if (where.length()>1)
			    	  {
			    		  where+=" WHERE ";
			    	  } else
			    	  {
			    		  where+=" AND ";
			    	  }
			    	  where += "CADDE_SOKAK_ID = "+listTheCansByStreetRequest.getAddressStreetItem().getCadde_sokak_id();
			      }
		      }
	      }
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<CanPoiItem> _list= new ArrayList<CanPoiItem>();
			while (rs.next()) {
				CanPoiItem canPoiItem = new CanPoiItem();
				canPoiItem.setId(rs.getLong("SIRA_NO"));
				canPoiItem.setItemCount(rs.getLong("ADEDI"));
				
				DistrictType districtType = new DistrictType();
				districtType.setId(""+rs.getLong("MAHALLE_ID"));
				canPoiItem.setDistirictType(districtType);
				
				StreetType streetType = new StreetType();
				streetType.setId(""+rs.getLong("CADDE_SOKAK_ID"));
				canPoiItem.setStreetType(streetType);				
				
				canPoiItem.setCompanyId(rs.getLong("KURUM_ID"));
				canPoiItem.setItemType(rs.getLong("TIPI"));				
				canPoiItem.setComment(rs.getString("ACIKLAMA"));				
				_list.add(canPoiItem);
				//tempBean.setServiceId(rs.getLong("SERVICE_ID"));
			}
			
			listTheCansByStreetResponce.setCanPoiItemList(_list.toArray(new CanPoiItem[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
      return listTheCansByStreetResponce;
	}	
	
	public GetInformationForCanResponce getInformationForCan(GetInformationForCanRequest getInformationForCanRequest) 
	{
		
		GetInformationForCanResponce getInformationForCanResponce = new GetInformationForCanResponce();
		System.out.println("GELDI 1.1");
	      String sorgu ="select * from TIM_COP ";
	      String where=  "";
	      
	      if (getInformationForCanRequest.getCanPoiItem()!=null && getInformationForCanRequest.getCanPoiItem().getId().toString().trim()!="")
	      {
	    	  where  =  " WHERE SIRA_NO = "+getInformationForCanRequest.getCanPoiItem().getId(); 
	      }
	      
	      
	      System.out.println("GELDI 1. = "+where);
	      
	      if (where.trim().length()>0)
	      {
	    	  System.out.println("GELDI 1.2");
	    	  sorgu = sorgu + where;
				try {
					CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
					ResultSet rs = callableStatement.executeQuery();
				//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
					
					if (rs.next()) {
						CanPoiItem canPoiItem = new CanPoiItem();
						canPoiItem.setId(rs.getLong("SIRA_NO"));
						canPoiItem.setItemCount(rs.getLong("ADEDI"));

						
						DistrictType districtType = new DistrictType();
						districtType.setId(""+rs.getLong("MAHALLE_ID"));
						canPoiItem.setDistirictType(districtType);
						
						StreetType streetType = new StreetType();
						streetType.setId(""+rs.getLong("CADDE_SOKAK_ID"));
						canPoiItem.setStreetType(streetType);				
						
						canPoiItem.setCompanyId(rs.getLong("KURUM_ID"));
						canPoiItem.setItemType(rs.getLong("TIPI"));				
						canPoiItem.setComment(rs.getString("ACIKLAMA"));				
						getInformationForCanResponce.setCanPoiItem(canPoiItem);
						//tempBean.setServiceId(rs.getLong("SERVICE_ID"));
					}
					
					
					
					rs.close();
					callableStatement.close();
					
					//return mblCompany;
		
				} catch (Exception e) {
					e.printStackTrace();
				}
	      } 
		
		System.out.println("GELDI 3");
    return getInformationForCanResponce;
		
	}
	
	public ListPOITypesResponce listPOITypes(ListPOITypesRequest listPOITypesRequest) 
	{
		return null;
	}
	
	public ListPOIsByTypeResponce listPOIsByType(ListPOIsByTypeRequest listPOIsByTypeRequest) 
	{
		return null;
	}

	public ListPOIsByAreaResponce listPOIsByArea(ListPOIsByAreaRequest listPOIsByAreaRequest) 
	{

		return null;
	}

	public ListTheAddressCityResponce listTheAddressCity() 
	{
		ListTheAddressCityResponce listTheAddressCityResponce = new ListTheAddressCityResponce();
	      System.out.println("GELDI");
		String sorgu = "select ID,ACIKLAMA from ORT_ILLER";
		String where ="";
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<CityType> _list= new ArrayList<CityType>();
			while (rs.next()) {
				CityType addressCityItem = new CityType();
				addressCityItem.setId(""+rs.getLong("ID"));
				ComponentType info = new ComponentType();
				info.setId(""+rs.getLong("ID"));
				info.setDescription(rs.getString("ACIKLAMA"));				
				addressCityItem.setInfo(info);				
				
				_list.add(addressCityItem);
			}
			
			listTheAddressCityResponce.setAddressCityItemList(_list.toArray(new CityType[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
		return listTheAddressCityResponce;
	}	
	
	
	public ListTheAddressTownsResponce listTheAddressTowns(ListTheAddressTownsRequest listTheAddressTownsRequest) 
	{
		ListTheAddressTownsResponce listTheAddressTownsResponce = new ListTheAddressTownsResponce();
	      System.out.println("GELDI");
	      
		String sorgu = "select ILCE_NO,ACIKLAMA,IL_NO from ORT_ILCE";
		String where ="";
		
		 if (listTheAddressTownsRequest!=null)
		 {
			if (listTheAddressTownsRequest.getAddressCityItem()!=null)
			{
				if (listTheAddressTownsRequest.getAddressCityItem().getId()!=null)
				{					
					if ( listTheAddressTownsRequest.getAddressCityItem().getId().toString().trim()!="")
					{	
					
						where =" WHERE IL_NO = "+listTheAddressTownsRequest.getAddressCityItem().getId();
					}
				}
			}
		 }
		
		
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<CountyType> _list= new ArrayList<CountyType>();
			while (rs.next()) {
				
				CountyType countyType = new CountyType();
				countyType.setId(""+rs.getLong("ILCE_NO"));
				ComponentType info = new ComponentType();
				info.setId(""+rs.getLong("ILCE_NO"));
				info.setDescription(rs.getString("ACIKLAMA"));				
				countyType.setInfo(info);				
				
				_list.add(countyType);
			}
			
			listTheAddressTownsResponce.setAddressTownItemList(_list.toArray(new CountyType[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
		return listTheAddressTownsResponce;
	}
	
	public ListTheAddressDistrictResponce listTheAddressDistrict(ListTheAddressDistrictRequest listTheAddressDistrictRequest) 
	{
		ListTheAddressDistrictResponce listTheAddressDistrictResponce = new ListTheAddressDistrictResponce();
	      System.out.println("GELDI");
		String sorgu = "select ID,MAHALLE_ADI from ORT_MAHALLE_KOYLER";
		String where ="";
		
		if (listTheAddressDistrictRequest!=null)
		{
			/*
			if (listTheAddressDistrictRequest.getAddressTownItem()!=null )
			{
				if (listTheAddressDistrictRequest.getAddressTownItem().getId()!=null)
				{
					if (listTheAddressDistrictRequest.getAddressTownItem().getId().toString().trim()!="")
					{				
						 where =" WHERE ILCE_NO = "+listTheAddressDistrictRequest.getAddressTownItem().getId();
					}
				}
			}
			*/
		}		
		
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<DistrictType> _list= new ArrayList<DistrictType>();
			while (rs.next()) {
				DistrictType districtType = new DistrictType();
				districtType.setId(""+rs.getLong("ID"));
				ComponentType info = new ComponentType();
				info.setId(""+rs.getLong("ID"));
				info.setDescription(rs.getString("MAHALLE_ADI"));
				districtType.setDistrictInfo(info);
				
				/*
				ComponentType townInfo = new ComponentType();
				townInfo.setId(""+rs.getLong("ILCE_ID"));
				districtType.setTownInfo(townInfo);	
				*/
				
				/*
				addressDistrictItem.setId(rs.getLong("ID"));
				addressDistrictItem.setIlce_id(rs.getLong("ILCE_ID"));
				addressDistrictItem.setMahalle_adi(rs.getString("MAHALLE_ADI"));				
				*/
				_list.add(districtType);
			}
			
			listTheAddressDistrictResponce.setAddressDistrictItem(_list.toArray(new DistrictType[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
		return listTheAddressDistrictResponce;
	}	
	
	public ListTheAddressStreetResponce listTheAddressStreet(ListTheAddressStreetRequest listTheAddressStreetRequest) 
	{
		ListTheAddressStreetResponce listTheAddressStreetResponce = new ListTheAddressStreetResponce();
	      System.out.println("GELDI");
		String sorgu = "select OC.CADDE_SOKAK_ID, OS.CADDE_SOKAK_ADI,OC.MAHALLE_ID from ORT_MAHALLE_CADDE OC left join ORT_CADDE_SOKAK OS on OS.ID = OC.CADDE_SOKAK_ID ";

		String where ="";
		if (listTheAddressStreetRequest!=null)
		{
			if (listTheAddressStreetRequest.getAddressDistrictItem()!=null)
			{	
				if (listTheAddressStreetRequest.getAddressDistrictItem().getId()!=null)
				{
					if (listTheAddressStreetRequest.getAddressDistrictItem().getId().toString().trim().length()>0)
					{
						where =" and OC.MAHALLE_ID = "+listTheAddressStreetRequest.getAddressDistrictItem().getId();
					}
				}
			}
		}
		
		
		
		sorgu = sorgu + where;
		
		
		try {
			CallableStatement callableStatement = super.getConnection().prepareCall(sorgu);
			ResultSet rs = callableStatement.executeQuery();
		//	MblCompany mblCompany = mobileDbConverter.companyConverter(rs);
			List<StreetType> _list= new ArrayList<StreetType>();
			while (rs.next()) {
				
				StreetType streetType = new StreetType();
				
				
				streetType.setId(""+rs.getLong("CADDE_SOKAK_ID"));
				ComponentType info = new ComponentType();
				info.setId(""+rs.getLong("CADDE_SOKAK_ID"));
				info.setDescription(rs.getString("CADDE_SOKAK_ADI"));
				streetType.setStreetInfo(info);					

				ComponentType distInfo = new ComponentType();
				distInfo.setId(""+rs.getLong("MAHALLE_ID"));
				streetType.setDistrictInfo(distInfo);					
				
				
				_list.add(streetType);
			}
			
			listTheAddressStreetResponce.setAddressStreetItemList(_list.toArray(new StreetType[_list.size()]));
			
			rs.close();
			callableStatement.close();
			
			//return mblCompany;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("GELDI 1");
		return listTheAddressStreetResponce;
	}	
/*
	public QueryBusinessOfficeResponse queryBusinessOffice(QueryBusinessOfficeRequest queryBusinessOfficeRequest)
	{
		return null;
	}
	*/
}
