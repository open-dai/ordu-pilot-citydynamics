package data_utils;

public class POIGroupType {
	
	private String description;
	private int Id;
	private int topId;
	
	public POIGroupType(int top_Id, int group_Id, String desc)
	{
		setDescription(desc);
		setId(group_Id);
		setTopId(top_Id);
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getTopId() {
		return topId;
	}
	public void setTopId(int topId) {
		this.topId = topId;
	}

}
