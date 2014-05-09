package GeoInfoMngmnt.CityDynamics.V1;

import GeoInfoMngmnt.PropertyMngmnt.V1.DistrictType;
import GeoInfoMngmnt.PropertyMngmnt.V1.StreetType;

public class CanPoiItem {
	
private Long id;
private Long companyId;
private Long itemType;
private Long itemCount;
private DistrictType distirictType;
private StreetType streetType;
private String comment;
private GcCollectScheduleItem[] gcCollectScheduleItem;

private Double x;
private Double y;

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getCompanyId() {
	return companyId;
}
public void setCompanyId(Long companyId) {
	this.companyId = companyId;
}
public Long getItemType() {
	return itemType;
}
public void setItemType(Long itemType) {
	this.itemType = itemType;
}
public DistrictType getDistirictType() {
	return distirictType;
}
public void setDistirictType(DistrictType distirictType) {
	this.distirictType = distirictType;
}
public StreetType getStreetType() {
	return streetType;
}
public void setStreetType(StreetType streetType) {
	this.streetType = streetType;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public Double getX() {
	return x;
}
public void setX(Double x) {
	this.x = x;
}
public Double getY() {
	return y;
}
public void setY(Double y) {
	this.y = y;
}
public Long getItemCount() {
	return itemCount;
}
public void setItemCount(Long itemCount) {
	this.itemCount = itemCount;
}
public GcCollectScheduleItem[] getGcCollectScheduleItem() {
	return gcCollectScheduleItem;
}
public void setGcCollectScheduleItem(GcCollectScheduleItem[] gcCollectScheduleItem) {
	this.gcCollectScheduleItem = gcCollectScheduleItem;
}


}
