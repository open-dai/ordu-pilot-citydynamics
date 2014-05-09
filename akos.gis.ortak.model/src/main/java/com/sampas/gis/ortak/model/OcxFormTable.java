package com.sampas.gis.ortak.model;

import java.util.Date;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings("serial")
public class OcxFormTable extends BaseObject implements IVersionable,ITransactionable,IHistoricalable{

	/**
	 * @uml.property  name="id"
	 */
	private Long id;

	/**
	 * Getter of the property <tt>id</tt>
	 * @return  Returns the id.
	 * @uml.property  name="id"
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter of the property <tt>id</tt>
	 * @param id  The id to set.
	 * @uml.property  name="id"
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @uml.property  name="adi"
	 */
	private String formAdi = "";

	/**
	 * Getter of the property <tt>adi</tt>
	 * @return  Returns the adi.
	 * @uml.property  name="adi"
	 */
	public String getFormAdi() {
		return formAdi;
	}

	/**
	 * Setter of the property <tt>adi</tt>
	 * @param adi  The adi to set.
	 * @uml.property  name="adi"
	 */
	public void setFormAdi(String formAdi) {
		this.formAdi = formAdi;
	}

	/**
	 * @uml.property  name="planNo"
	 */
	private String formAciklamasi = "";

	/**
	 * Getter of the property <tt>planNo</tt>
	 * @return  Returns the planNo.
	 * @uml.property  name="planNo"
	 */
	public String getFormAciklamasi() {
		return formAciklamasi;
	}

	/**
	 * Setter of the property <tt>planNo</tt>
	 * @param planNo  The planNo to set.
	 * @uml.property  name="planNo"
	 */
	public void setFormAciklamasi(String formAciklamasi) {
		this.formAciklamasi = formAciklamasi;
	}

	/**
	 * @uml.property  name="onayTarihi"
	 */
	private Date kayitTarih;

	/**
	 * Getter of the property <tt>onayTarihi</tt>
	 * @return  Returns the onayTarihi.
	 * @uml.property  name="onayTarihi"
	 */
	public Date getKayitTarih() {
		return kayitTarih;
	}

	/**
	 * Setter of the property <tt>onayTarihi</tt>
	 * @param onayTarihi  The onayTarihi to set.
	 * @uml.property  name="onayTarihi"
	 */
	public void setKayitTarih(Date kayitTarih) {
	
		this.kayitTarih = kayitTarih;
	}
	
	private String kaydeden = "";

	/**
	 * Getter of the property <tt>planNo</tt>
	 * @return  Returns the planNo.
	 * @uml.property  name="planNo"
	 */
	public String getKaydeden() {
		return kaydeden;
	}

	/**
	 * Setter of the property <tt>planNo</tt>
	 * @param planNo  The planNo to set.
	 * @uml.property  name="planNo"
	 */
	public void setKaydeden(String kaydeden) {
		this.kaydeden = kaydeden;
	}
	
	private String katmanAdi = "";

	/**
	 * Getter of the property <tt>planNo</tt>
	 * @return  Returns the planNo.
	 * @uml.property  name="planNo"
	 */
	public String getKatmanAdi() {
		return katmanAdi;
	}

	/**
	 * Setter of the property <tt>planNo</tt>
	 * @param planNo  The planNo to set.
	 * @uml.property  name="planNo"
	 */
	public void setKatmanAdi(String katmanAdi) {
		this.katmanAdi = katmanAdi;
	}


	
}
