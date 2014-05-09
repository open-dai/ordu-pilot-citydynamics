package com.sampas.akos.ortak.model;

import org.hibernate.envers.Audited;

import com.sampas.akos.common.model.BaseObject;


@SuppressWarnings("serial")
@Audited
public class KurumSabit extends BaseObject {

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
	 * @uml.property  name="kurumAd"
	 */
	private String kurumAd = "";

	/**
	 * Getter of the property <tt>kurumAd</tt>
	 * @return  Returns the kurumAd.
	 * @uml.property  name="kurumAd"
	 */
	public String getKurumAd() {
		return kurumAd;
	}

	/**
	 * Setter of the property <tt>kurumAd</tt>
	 * @param kurumAd  The kurumAd to set.
	 * @uml.property  name="kurumAd"
	 */
	public void setKurumAd(String kurumAd) {
		this.kurumAd = kurumAd;
	}

	/**
	 * @uml.property  name="numaratajKontrolEh"
	 */
	private String numaratajKontrolEh = "";

	/**
	 * Getter of the property <tt>numaratajKontrolEh</tt>
	 * @return  Returns the numaratajKontrolEh.
	 * @uml.property  name="numaratajKontrolEh"
	 */
	public String getNumaratajKontrolEh() {
		return numaratajKontrolEh;
	}

	/**
	 * Setter of the property <tt>numaratajKontrolEh</tt>
	 * @param numaratajKontrolEh  The numaratajKontrolEh to set.
	 * @uml.property  name="numaratajKontrolEh"
	 */
	public void setNumaratajKontrolEh(String numaratajKontrolEh) {
		this.numaratajKontrolEh = numaratajKontrolEh;
	}

	/**
	 * @uml.property  name="grafikEh"
	 */
	private String grafikEh = "";

	/**
	 * Getter of the property <tt>grafikEh</tt>
	 * @return  Returns the grafikEh.
	 * @uml.property  name="grafikEh"
	 */
	public String getGrafikEh() {
		return grafikEh;
	}

	/**
	 * Setter of the property <tt>grafikEh</tt>
	 * @param grafikEh  The grafikEh to set.
	 * @uml.property  name="grafikEh"
	 */
	public void setGrafikEh(String grafikEh) {
		this.grafikEh = grafikEh;
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
	 * @uml.property  name="kurumKodu"
	 */
	private String kurumKodu;

	/**
	 * Getter of the property <tt>kurumKodu</tt>
	 * @return  Returns the kurumKodu.
	 * @uml.property  name="kurumKodu"
	 */
	public String getKurumKodu() {
		return kurumKodu;
	}

	/**
	 * Setter of the property <tt>kurumKodu</tt>
	 * @param kurumKodu  The kurumKodu to set.
	 * @uml.property  name="kurumKodu"
	 */
	public void setKurumKodu(String kurumKodu) {
		this.kurumKodu = kurumKodu;
	}

}
