package data_utils;

public class Street {
	
	private int ID;
	private int District_ID;
	private String name;
	
	public Street()
	{
		this(-1, -1, "");
	}
	
	public Street(int dID, int sID, String sName)
	{
		setID(sID);
		setDistrict_ID(dID);
		setName(sName);
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getDistrict_ID() {
		return District_ID;
	}
	public void setDistrict_ID(int district_ID) {
		District_ID = district_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
