package data_utils;

public class POIClassType {
	
	private String aciklama;
	private boolean isChecked = false;
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	private int id;
	
	public POIClassType(String aci, int id)
	{
		setAciklama(aci);
		setId(id);
		setChecked(false);
	}
	
	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
