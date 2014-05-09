package Common.V1;

public class AddressTownItem {
	
	private Long id;
	private String aciklama;
	private Long il_id;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setIl_id(Long il_id) {
		this.il_id = il_id;
	}

	public Long getIl_id() {
		return il_id;
	}
}
