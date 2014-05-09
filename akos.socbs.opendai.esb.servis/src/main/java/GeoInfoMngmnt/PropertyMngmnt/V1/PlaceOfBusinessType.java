package GeoInfoMngmnt.PropertyMngmnt.V1;

public class PlaceOfBusinessType {

	private String companyName;
	private String title;
	private String responsibleNameSurName;
	private HourType openningTime;
	private HourType closingTime;
	private EmailAddressType email;
	private PhoneNumberType phoneNumber;
	private AddressType adress;
	private String sector;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HourType getOpenningTime() {
		return openningTime;
	}
	public void setOpenningTime(HourType openningTime) {
		this.openningTime = openningTime;
	}
	public HourType getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(HourType closingTime) {
		this.closingTime = closingTime;
	}
	public String getResponsibleNameSurName() {
		return responsibleNameSurName;
	}
	public void setResponsibleNameSurName(String responsibleNameSurName) {
		this.responsibleNameSurName = responsibleNameSurName;
	}
	public EmailAddressType getEmail() {
		return email;
	}
	public void setEmail(EmailAddressType email) {
		this.email = email;
	}
	public PhoneNumberType getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(PhoneNumberType phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public AddressType getAdress() {
		return adress;
	}
	public void setAdress(AddressType adress) {
		this.adress = adress;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	
}
