package data_utils;

public class City {
	
	private int ID;
	private String name;
	
	public City()
	{
		this(-1, "");
	}
	
	public City(int cID, String cName)
	{
		setID(cID);
		setName(cName);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
