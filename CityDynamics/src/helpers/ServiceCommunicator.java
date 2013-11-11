package helpers;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import data_utils.SoapParameter;


public class ServiceCommunicator {

	private EnvelopeBuilder envBuilder;
	private final String nameSpace = "http://V1.CityDynamics.GeoInfoMngmnt.sampas.com/";
	private String method;
	private String soapAction;
	private String serviceURL = "http://services.sampas.com.tr:5586/services/CityDynamicsService";
	private SoapParameter[] parameters;
	
	
	private void init()
	{
		envBuilder = new EnvelopeBuilder();
		soapAction = nameSpace + this.method;
	}
	
	public ServiceCommunicator(String method, SoapParameter[] params)
	{		
		this.method = method;
		this.parameters = params;
		init();
	}
	
	public SoapObject getResultFromService()
	{
		SoapObject response = null;
		
		SoapSerializationEnvelope envelope = envBuilder.create(nameSpace, method, parameters);
		
		try
		{
			HttpTransportSE httpTransport = new HttpTransportSE(serviceURL);
			httpTransport.call(soapAction, envelope);
			
			response = (SoapObject)envelope.bodyIn;
		}
		catch(Exception e)
		{
			response = null;
		}
		
		return response;
	}
	
	
	
}
