package data_utils;

public class District {
	
	private int ID;
	private int Town_ID;
	private String name;
	
	public District()
	{
		this(-1, -1, "");
	}
	
	public District(int tID, int dID, String dName)
	{
		setID(dID);
		setName(dName);
		setTown_ID(tID);
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getTown_ID() {
		return Town_ID;
	}
	public void setTown_ID(int town_ID) {
		Town_ID = town_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
