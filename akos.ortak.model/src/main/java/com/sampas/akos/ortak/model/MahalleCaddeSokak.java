package com.sampas.akos.ortak.model;

import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.model.IVersionable;


@SuppressWarnings( "serial" )
@Audited
public class MahalleCaddeSokak extends BaseObject implements IVersionable ,ITransactionable, IHistoricalable {

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
	 * @uml.property  name="tuikCaddeSokakTanitimNo"
	 */
	private Double tuikCaddeSokakTanitimNo;

	/**
	 * Getter of the property <tt>tuikCaddeSokakTanitimNo</tt>
	 * @return  Returns the tuikCaddeSokakTanitimNo.
	 * @uml.property  name="tuikCaddeSokakTanitimNo"
	 */
	public Double getTuikCaddeSokakTanitimNo() {
		return tuikCaddeSokakTanitimNo;
	}

	/**
	 * Setter of the property <tt>tuikCaddeSokakTanitimNo</tt>
	 * @param tuikCaddeSokakTanitimNo  The tuikCaddeSokakTanitimNo to set.
	 * @uml.property  name="tuikCaddeSokakTanitimNo"
	 */
	public void setTuikCaddeSokakTanitimNo(Double tuikCaddeSokakTanitimNo) {
		this.tuikCaddeSokakTanitimNo = tuikCaddeSokakTanitimNo;
	}

	/**
	 * @uml.property  name="tuikCaddeSokakKod"
	 */
	private Long tuikCaddeSokakKod;

	/**
	 * Getter of the property <tt>tuikCaddeSokakKod</tt>
	 * @return  Returns the tuikCaddeSokakKod.
	 * @uml.property  name="tuikCaddeSokakKod"
	 */
	public Long getTuikCaddeSokakKod() {
		return tuikCaddeSokakKod;
	}

	/**
	 * Setter of the property <tt>tuikCaddeSokakKod</tt>
	 * @param tuikCaddeSokakKod  The tuikCaddeSokakKod to set.
	 * @uml.property  name="tuikCaddeSokakKod"
	 */
	public void setTuikCaddeSokakKod(Long tuikCaddeSokakKod) {
		this.tuikCaddeSokakKod = tuikCaddeSokakKod;
	}

	/**
	 * @uml.property  name="ctvGrup"
	 */
	private Long ctvGrup;

	/**
	 * Getter of the property <tt>ctvGrup</tt>
	 * @return  Returns the ctvGrup.
	 * @uml.property  name="ctvGrup"
	 */
	public Long getCtvGrup() {
		return ctvGrup;
	}

	/**
	 * Setter of the property <tt>ctvGrup</tt>
	 * @param ctvGrup  The ctvGrup to set.
	 * @uml.property  name="ctvGrup"
	 */
	public void setCtvGrup(Long ctvGrup) {
		this.ctvGrup = ctvGrup;
	}

	/**
	 * @uml.property  name="ctvDerece"
	 */
	private Long ctvDerece;

	/**
	 * Getter of the property <tt>ctvDerece</tt>
	 * @return  Returns the ctvDerece.
	 * @uml.property  name="ctvDerece"
	 */
	public Long getCtvDerece() {
		return ctvDerece;
	}

	/**
	 * Setter of the property <tt>ctvDerece</tt>
	 * @param ctvDerece  The ctvDerece to set.
	 * @uml.property  name="ctvDerece"
	 */
	public void setCtvDerece(Long ctvDerece) {
		this.ctvDerece = ctvDerece;
	}

	/**
	 * @uml.property  name="grafik"
	 */
	private Grafik grafik;

	/**
	 * Getter of the property <tt>grafik</tt>
	 * @return  Returns the grafik.
	 * @uml.property  name="grafik"
	 */
	public Grafik getGrafik() {
		return grafik;
	}

	/**
	 * Setter of the property <tt>grafik</tt>
	 * @param grafik  The grafik to set.
	 * @uml.property  name="grafik"
	 */
	public void setGrafik(Grafik grafik) {
		this.grafik = grafik;
	}

	/**
	 * @uml.property  name="mahalle"
	 * @uml.associationEnd  inverse="mahalleCaddeSokak:com.sampas.akos.ortak.model.Mahalle"
	 */
	private Mahalle mahalle;

	/**
	 * Getter of the property <tt>mahalle</tt>
	 * @return  Returns the mahalle.
	 * @uml.property  name="mahalle"
	 */
	public Mahalle getMahalle() {
		return mahalle;
	}

	/**
	 * Setter of the property <tt>mahalle</tt>
	 * @param mahalle  The mahalle to set.
	 * @uml.property  name="mahalle"
	 */
	public void setMahalle(Mahalle mahalle) {
		this.mahalle = mahalle;
	}

	/**
	 * @uml.property  name="caddeSokak"
	 * @uml.associationEnd  inverse="mahalleCaddeSokak:com.sampas.akos.ortak.model.CaddeSokak"
	 */
	private CaddeSokak caddeSokak;

	/**
	 * Getter of the property <tt>caddeSokak</tt>
	 * @return  Returns the caddeSokak.
	 * @uml.property  name="caddeSokak"
	 */
	public CaddeSokak getCaddeSokak() {
		return caddeSokak;
	}

	/**
	 * Setter of the property <tt>caddeSokak</tt>
	 * @param caddeSokak  The caddeSokak to set.
	 * @uml.property  name="caddeSokak"
	 */
	public void setCaddeSokak(CaddeSokak caddeSokak) {
		this.caddeSokak = caddeSokak;
	}

	/**
	 * @uml.property  name="aktifEh"
	 */
	private String aktifEh;

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
	
	/**
	 * @uml.property  name="gelismislikDurum"
	 */
	private GelismislikDurum gelismislikDurum;

	/**
	 * Getter of the property <tt>gelismislikDurum</tt>
	 * @return  Returns the gelismislikDurum.
	 * @uml.property  name="gelismislikDurum"
	 */
	public GelismislikDurum getGelismislikDurum() {
		return gelismislikDurum;
	}

	/**
	 * Setter of the property <tt>gelismislikDurum</tt>
	 * @param gelismislikDurum  The gelismislikDurum to set.
	 * @uml.property  name="gelismislikDurum"
	 */
	public void setGelismislikDurum(GelismislikDurum gelismislikDurum) {
		this.gelismislikDurum = gelismislikDurum;
	}

}
