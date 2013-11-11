package data_utils;

public class Town {
	
	private int ID;
	private int City_ID;
	private String name;
	
	public Town()
	{
		this(-1, -1, "");
	}
	
	public Town(int cID, int tID, String tName)
	{
		setCity_ID(cID);
		setID(tID);
		setName(tName);
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getCity_ID() {
		return City_ID;
	}
	public void setCity_ID(int city_ID) {
		City_ID = city_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
