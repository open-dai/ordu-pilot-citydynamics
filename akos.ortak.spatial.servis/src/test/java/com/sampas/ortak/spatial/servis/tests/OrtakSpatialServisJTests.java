package com.sampas.ortak.spatial.servis.tests;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.Mahalle;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.ortak.spatial.servis.IOrtakSpatialServis;


public class OrtakSpatialServisJTests extends BaseTestCase {

	private IOrtakSpatialServis ortakSpatialServis = null;
	
	private OrtakServis ortakServis;
	
	protected void onSetUpInTransaction() throws Exception {
		
		setComplete();	
		
		ortakSpatialServis = (IOrtakSpatialServis)applicationContext.getBean("ortakSpatialServis");
		
		ortakServis = (OrtakServis)applicationContext.getBean("ortakServis");
	}
	
	public void objectIntersectionJTests() {
		
		CaddeSokak street = new CaddeSokak();
		
		street.setId(39483L);
		
		//boolean intResult = ortakSpatialServis.isStreetIntersectWithAnyDistrict(street);
		//TODO: must fix
		boolean intResult = false;
		
		System.out.println("Kesisme sonucu : " + intResult);
	}
	
	public IOrtakSpatialServis getOrtakSpatialServis() {
		return ortakSpatialServis;
	}

	public void setOrtakSpatialServis(IOrtakSpatialServis ortakSpatialServis) {
		this.ortakSpatialServis = ortakSpatialServis;
	}

	@Override
	protected String[] getXmlDataFileResource() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/akos/commonTestContext.xml" };
	}
	
	public void testSaveNewAddress() {
		
		KurumSabit kurumSabit=new KurumSabit();
		
		kurumSabit.setId(1L);
		
		Mahalle mahalle=new Mahalle();
		
		mahalle.setId(157L);
		
		mahalle=ortakServis.readAllMahalleByCriteria(mahalle, kurumSabit).get(0);

		CaddeSokak caddeSokak=new CaddeSokak();
		
		caddeSokak.setId(36157L);
		
		caddeSokak=ortakServis.readAllCaddeSokakByCriteria(caddeSokak, kurumSabit).get(0);

		MahalleCaddeSokak newDistrictStreet = new MahalleCaddeSokak();
		//newDistrictStreet.setId(6180202L);
		newDistrictStreet.setMahalle(mahalle);
		
		newDistrictStreet.setCaddeSokak(caddeSokak);
		
		Date now = new Date();
	//	newDistrictStreet.setId(9999L);
		newDistrictStreet.setKayitTarih(now);
		
		newDistrictStreet.setKaydeden("ADMIN");
		
		Adres newAddress1 = new Adres();
		
		newAddress1.setId(5654214L);
		
		newAddress1.setAktifEh("H");
		
		newAddress1.setKayitTarih(now);
		
		newAddress1.setKaydeden("ADMIN");
		
		Adres newAddress2 = new Adres();
		
		newAddress2.setId(5654215L);
		
		newAddress2.setAktifEh("H");
		
		newAddress2.setKayitTarih(now);
		
		newAddress2.setKaydeden("ADMIN");
		
		Set<Adres> newAddressCollection =new HashSet<Adres>();
		
		newAddressCollection.add(newAddress1);
		
		newAddressCollection.add(newAddress2);
		
		//newDistrictStreet.setAdresler(newAddressCollection);
		
		//ortakServis.saveOrUpdateAdres(newAddress1);
		
		ortakServis.saveOrUpdateMahalleCaddeSokak(newDistrictStreet);
		
		//assertSame(1, 0);
	}
	
public void testSaveAdresByItself(){
		
		Adres newAddress1 = new Adres();
		
		newAddress1.setId(333333344L);
		
		newAddress1.setAktifEh("H");
		
		newAddress1.setKayitTarih(new Date());
		
		newAddress1.setKaydeden("ADMIN");
		
		KurumSabit kurumSabit=new KurumSabit();
		
		kurumSabit.setId(1L);
		
		MahalleCaddeSokak mahalleCaddeSokak=new MahalleCaddeSokak();
		
		mahalleCaddeSokak.setId(4L);
		
		ortakServis.saveOrUpdateAdres(newAddress1);
	}
	
	public void testSaveMahalleCaddeSokakByItself(){
		
		KurumSabit kurumSabit=new KurumSabit();
		
		kurumSabit.setId(1L);
		
		Mahalle mahalle=new Mahalle();
		
		mahalle.setId(157L);
		
		mahalle=ortakServis.readAllMahalleByCriteria(mahalle, kurumSabit).get(0);

		CaddeSokak caddeSokak=new CaddeSokak();
		
		caddeSokak.setId(35979L);
		
		caddeSokak=ortakServis.readAllCaddeSokakByCriteria(caddeSokak, kurumSabit).get(0);
		
		MahalleCaddeSokak newDistrictStreet = new MahalleCaddeSokak();
		
		newDistrictStreet.setMahalle(mahalle);
		
		newDistrictStreet.setCaddeSokak(caddeSokak);
		
		Date now = new Date();
	    //newDistrictStreet.setId(9999L);
		newDistrictStreet.setKayitTarih(now);
		
		newDistrictStreet.setKaydeden("ADMIN");
		//ortakServis.saveOrUpdateAdres(newAddress1);
		ortakServis.saveOrUpdateMahalleCaddeSokak(newDistrictStreet);
	}
	
	public void testDeleteMahalleCaddeSokak(){
		
		MahalleCaddeSokak mahalleCaddeSokak=new MahalleCaddeSokak();
		
		mahalleCaddeSokak.setId(6180202L);
		
		KurumSabit kurumSabit=new KurumSabit();
		
		kurumSabit.setId(1L);
		
		MahalleCaddeSokak caddeSokak=ortakServis.readMahalleCaddeSokakByCriteria(mahalleCaddeSokak, kurumSabit);
	
		ortakServis.deleteMahalleCaddeSokak(caddeSokak);
	}
	
	public void testTEST() {
		
		ortakSpatialServis.isFeatureIntersectWithAnyBuildArea(null, null);
		
		System.out.println("Test OK !");
	}
	
}
