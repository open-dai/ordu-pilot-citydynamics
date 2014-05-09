package com.sampas.ortak.spatial.history.model;



@SuppressWarnings("serial")
public class BinaParselHst extends BaseHistoryObject {

	/**
	 * @uml.property  name="binaId"
	 */
	private Long binaId;

	/**
	 * Getter of the property <tt>binaId</tt>
	 * @return  Returns the binaId.
	 * @uml.property  name="binaId"
	 */
	public Long getBinaId() {
		return binaId;
	}

	/**
	 * Setter of the property <tt>binaId</tt>
	 * @param binaId  The binaId to set.
	 * @uml.property  name="binaId"
	 */
	public void setBinaId(Long binaId) {
		this.binaId = binaId;
	}
	
	/**
	 * @uml.property  name="kadastroParselId"
	 */
	private Long kadastroParselId;

	/**
	 * Getter of the property <tt>kadastroParselId</tt>
	 * @return  Returns the kadastroParselId.
	 * @uml.property  name="kadastroParselId"
	 */
	public Long getKadastroParselId() {
		return kadastroParselId;
	}
	
	/**
	 * Setter of the property <tt>kadastroParselId</tt>
	 * @param kadastroParselId  The kadastroParselId to set.
	 * @uml.property  name="kadastroParselId"
	 */
	public void setKadastroParselId(Long kadastroParselId) {
		this.kadastroParselId = kadastroParselId;
	}
	
	/**
	 * @uml.property  name="aktifEh"
	 */
	private String aktifEh = "";

	/**
	 * Getter of the property <tt>aktifEh</tt>
	 * @return  Returns the aktifEh.
	 * @uml.property  name="aktifEh"
	 */
	public String getAktifEh() {
		return aktifEh;
	}

	/**
	 * Setter of the property <tt>aktifEh</tt>
	 * @param aktifEh  The aktifEh to set.
	 * @uml.property  name="aktifEh"
	 */
	public void setAktifEh(String aktifEh) {
		this.aktifEh = aktifEh;
	}
	
}
