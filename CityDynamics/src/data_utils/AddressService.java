package data_utils;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

import helpers.ServiceCommunicator;

public class AddressService {
	
	private ServiceCommunicator service;
	
	public void init()
	{
		
	}
	
	public Vector<City> getCityByID()
	{
		Vector<City> result = null;
		
		service = new ServiceCommunicator("queryListTheAddressCity", null);
		SoapObject response = service.getResultFromService();
		
		int count = response.getPropertyCount();
		
		result = new Vector<City>(count + 1);
		
		City cDefault = new City(-1, "Lütfen seçiniz...");
		result.add(cDefault);
		
		for(int i = 0; i < count; i++)
		{
			SoapObject data = (SoapObject)response.getProperty(i);
			City city = new City(Integer.parseInt(data.getPropertyAsString("id")), 
								 data.getPropertyAsString("description"));
			result.add(city);
		}
		
		return result;
	}
	
	public Vector<Town> getTownByCityID(SoapParameter[] params)
	{
		Vector<Town> result = null;
		
		service = new ServiceCommunicator("queryListTheAddressTowns", params); 
		SoapObject response = service.getResultFromService();
		
		int count = response.getPropertyCount();
		
		result = new Vector<Town>(count + 1);
		Town t = new Town(-1, -1, "Lütfen seçiniz...");
		result.add(t);
		
		for(int i = 0; i < count; i++)
		{
			SoapObject data = (SoapObject)response.getProperty(i);
			t = new Town(Integer.parseInt(data.getPropertyAsString("topID")), 
						 Integer.parseInt(data.getPropertyAsString("id")),
						 data.getPropertyAsString("description"));
			
			result.add(t);
		}
		
		return result;
	}
	
	public Vector<District> getDistrictByTownID(SoapParameter[] params)
	{
		Vector<District> result = null;
		
		service = new ServiceCommunicator("queryListTheAddressDistrict", params);
		SoapObject response = service.getResultFromService();
		
		int count = response.getPropertyCount();
		result = new Vector<District>(count + 1);
		
		District dist = new District(-1, -1, "Lütfen seçiniz...");
		result.add(dist);
		
		for(int i = 0; i < count; i++)
		{
			SoapObject data = (SoapObject)response.getProperty(i);
			dist = new District(Integer.parseInt(data.getPropertyAsString("topID")), 
								Integer.parseInt(data.getPropertyAsString("Id")), 
								data.getPropertyAsString("description"));
			
			result.add(dist);
		}
		
		return result;
	}
	
	public Vector<Street> getStreetByDistrict(SoapParameter[] params)
	{
		Vector<Street> result = null;
		
		service = new ServiceCommunicator("queryListTheAddressStreet", params);
		SoapObject response = service.getResultFromService();
		
		int count = response.getPropertyCount();
		result = new Vector<Street>(count + 1);
		Street st = new Street(-1, -1, "Lütfen seçiniz...");
		result.add(st);
		
		for(int i = 0; i < count; i++)
		{
			SoapObject data = (SoapObject) response.getProperty(i);
			st = new Street(Integer.parseInt(data.getPropertyAsString("topID")),
							Integer.parseInt(data.getPropertyAsString("Id")), 
							data.getPropertyAsString("description"));
			result.add(st);
		}
		
		return result;
	}
	
	

}
