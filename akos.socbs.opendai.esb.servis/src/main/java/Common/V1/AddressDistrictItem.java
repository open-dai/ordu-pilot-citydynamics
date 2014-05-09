package Common.V1;

public class AddressDistrictItem {
	
	private Long id;
	private String mahalle_adi;
	private Long ilce_id;

	public void setMahalle_adi(String mahalle_adi) {
		this.mahalle_adi = mahalle_adi;
	}

	public String getMahalle_adi() {
		return mahalle_adi;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setIlce_id(Long ilce_id) {
		this.ilce_id = ilce_id;
	}

	public Long getIlce_id() {
		return ilce_id;
	}
}
