package data_utils;

public class SoapParameter {

	private String key;
	private Object value;
	
	public SoapParameter(String key, Object value)
	{
		setKey(key);
		setValue(value);
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	
	public void setValue(Object value)
	{
		this.value = value;
	}
	
	public String getKey()
	{
		return this.key;
	}
	
	public Object getValue()
	{
		return this.value;
	}
	
}
