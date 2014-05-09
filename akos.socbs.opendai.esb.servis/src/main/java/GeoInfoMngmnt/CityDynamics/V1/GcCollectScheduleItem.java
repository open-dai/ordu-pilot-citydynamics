package GeoInfoMngmnt.CityDynamics.V1;

import java.util.Date;

import Common.V1.ComponentType;
import GeoInfoMngmnt.PropertyMngmnt.V1.DistrictType;
import GeoInfoMngmnt.PropertyMngmnt.V1.HourType;
import GeoInfoMngmnt.PropertyMngmnt.V1.StreetType;

public class GcCollectScheduleItem {


	private Long companyId;
	private Long year;
	private Long period;
	private Long day;
	private DistrictType districtType;
	private StreetType streetType;
	private HourType startTime;
	private HourType finishTime;
	private String comment;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getPeriod() {
		return period;
	}
	public void setPeriod(Long period) {
		this.period = period;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public DistrictType getDistrictType() {
		return districtType;
	}
	public void setDistrictType(DistrictType districtType) {
		this.districtType = districtType;
	}
	public StreetType getStreetType() {
		return streetType;
	}
	public void setStreetType(StreetType streetType) {
		this.streetType = streetType;
	}
	public HourType getStartTime() {
		return startTime;
	}
	public void setStartTime(HourType startTime) {
		this.startTime = startTime;
	}
	public HourType getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(HourType finishTime) {
		this.finishTime = finishTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	}
