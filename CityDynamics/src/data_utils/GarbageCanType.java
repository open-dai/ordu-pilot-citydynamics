package data_utils;

public class GarbageCanType {
	
	private int typeID;
	private String description;
	private boolean isChecked;
	
	public GarbageCanType(int t, String d)
	{
		setTypeID(t);
		setDescription(d);
		setChecked(false);
	}
	
	public int getTypeID() {
		return typeID;
	}
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}
