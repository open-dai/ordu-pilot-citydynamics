package com.sampas.gis.ortak.servis.tests;

import java.util.List;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.Mahalle;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;
import com.sampas.akos.ortak.model.BagimsizKullanimGrup;
import com.sampas.akos.ortak.model.BagimsizKullanimSinif;
import com.sampas.gis.ortak.model.Fonksiyon;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.model.Plan;
import com.sampas.gis.ortak.model.PlanTurleri;
import com.sampas.gis.ortak.servis.IGisOrtakServis;


public class GisServisJTests extends BaseTestCase {

	@Override
	protected String[] getXmlDataFileResource() {
		// TODO Auto-generated method stub
		return new String[]{};
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/akos/commonTestContext.xml" };
	}
	
	IGisOrtakServis gisOrtakServis;
	
	OrtakServis ortakService;
	
	protected void onSetUpInTransaction() throws Exception {
		
		// System.setProperty("javax.net.debug", "ssl,data");
		setComplete();		
		
		gisOrtakServis = (IGisOrtakServis) applicationContext.getBean("gisOrtakServis");
		ortakService=(OrtakServis)applicationContext.getBean("ortakServis");
	}
	
	public void testSearchOnemliYer(){
		
		System.out.println("---------------------------");
		System.out.println("Önemli Yer Arama Testi Başlatıldı");
		System.out.println("---------------------------");

		OnemliYer onemliYer=new OnemliYer();
		BagimsizKullanimSinif kullSinif=new BagimsizKullanimSinif();
		//kullSinif.setId(11L);
		onemliYer.setKullanimSinifi(kullSinif);
		
		BagimsizKullanimGrup kullGrup=new BagimsizKullanimGrup();
		//kullGrup.setId(75L);
		onemliYer.setKullanimGrubu(kullGrup);
		
		BagimsizKullanimDetay kullDetay=new BagimsizKullanimDetay();
		//kullDetay.setId(204L);
		onemliYer.setKullanimDetayi(kullDetay);
		//onemliYer.setId(11674);
		onemliYer.setAciklama("Galata");
		
		KurumSabit ks=new KurumSabit();
		ks.setId(1L);
		
		List<OnemliYer> onemliYerler = gisOrtakServis.readOnemliyerByCriteria(onemliYer, ks) ;
		if (onemliYerler!=null && onemliYerler.size()>0){
			
			for (OnemliYer onemliy : onemliYerler) {
				
				System.out.println(onemliy.getId()+"-"+ onemliy.getAciklama());
			}
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Önemli Yer Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchKadastroParsel(){
		
		KadastroParsel parsel=new KadastroParsel();

		System.out.println("---------------------------");
		System.out.println("Parsel Arama Testi Başlatıldı");
		System.out.println("---------------------------");
		
		parsel.setPafta("44");
		//parsel.setAda(44L);
		List<KadastroParsel> parseller=ortakService.readAllKadastroParselByParsel(null, parsel);
		if (parseller!=null && parseller.size()>0){
			
			for (KadastroParsel prsl : parseller) {
				
				System.out.println(prsl.getId()+"-"+ prsl.getAda());
			}
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Parsel Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchAdres(){
		
		System.out.println("---------------------------");
		System.out.println("Adres Arama Testi Başladı");
		System.out.println("---------------------------");
		Adres adres=new Adres();
		adres.setKapiNo(1L);
		
		MahalleCaddeSokak mahcaddeSokak=new MahalleCaddeSokak();
		Mahalle mahalle=new Mahalle();
		mahalle.setId(144L);
		mahcaddeSokak.setMahalle(mahalle);
		
		CaddeSokak caddeSokak=new CaddeSokak();
		caddeSokak.setId(36858L);
		mahcaddeSokak.setCaddeSokak(caddeSokak);
		
		List<Adres> adresler=ortakService.readAllAdresByAdresAndMahalleCaddeSokak(null, adres, mahcaddeSokak);
		
		if (adresler!=null && adresler.size()>0){
			
			for (Adres adrs : adresler) {
				
				System.out.println(adrs.getId()+"-"+ adrs.getAciklama());
			}
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Adres Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testGetParselFonksiyon(){
		
		System.out.println("---------------------------");
		System.out.println("Parsel Geçerli Fonksiyon Testi Başladı");
		System.out.println("---------------------------");

		KadastroParsel parsel=new KadastroParsel();
		parsel.setId(4L);
	
		PlanTurleri planTuru=new PlanTurleri();
		planTuru.setId(2L);
		
		Fonksiyon fonksiyon = gisOrtakServis.readFonksiyonByCriteria(parsel, planTuru);
		
		if (fonksiyon!=null){
			System.out.println(fonksiyon.getId()+"-"+fonksiyon.getAciklama());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun fonksiyon verisi yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Parsel Geçerli Fonksiyon  Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchCaddeSokakByMahalle(){
		System.out.println("---------------------------");
		System.out.println("Cadde Sokak Arama Testi Başlatıldı");
		System.out.println("---------------------------");

		Mahalle mahalle=new Mahalle();
		mahalle.setId(157L);
		
		List<CaddeSokak> caddeSokaklar=ortakService.readAllCaddeSokakByCriteria(null, mahalle);
		if (caddeSokaklar!=null && caddeSokaklar.size()>0){
			
			for (CaddeSokak caddeSok : caddeSokaklar) {
				
				System.out.println(caddeSok.getAciklama());
			}
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Cadde Sokak Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchBinaByBina(){
		
		System.out.println("---------------------------");
		System.out.println("Bina Testi Başlatıldı");
		System.out.println("---------------------------");

		Bina bina=new Bina();
		bina.setId(122113171L);
		KurumSabit ks=new KurumSabit();
		ks.setId(1L);
		List<Bina> binalar=ortakService.readAllBinaByCriteria(bina,ks);
		if (binalar!=null && binalar.size()>0){
			
			for (Bina bn : binalar) {
				
				System.out.println(bn.getId()+" - "+ bn.getApartmanBlokAd());
			}
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Bina Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchPlanByFonksiyon(){
		
		System.out.println("---------------------------");
		System.out.println("Plan Arama testi Başlatıldı");
		System.out.println("---------------------------");

		Fonksiyon fonksiyon=new Fonksiyon();
		fonksiyon.setId(3140L);
		
		KurumSabit kurumSabit = new KurumSabit();
		kurumSabit.setId(34009L);
		
		Plan plan = gisOrtakServis.readPlanByCriteria(fonksiyon, kurumSabit);
		
		//List<PlanNot> notlar=gisOrtakServis.readPlanNotByPlan(plan, kurumSabit);
		
		if (plan!=null ){
				
			System.out.println(plan.getAdi());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Plan Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
public void testSearchPlanByFonksiyonByParsel(){
		
		System.out.println("---------------------------");
		System.out.println("Plan Arama testi Başlatıldı");
		System.out.println("---------------------------");
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		
		KadastroParsel parsel=new KadastroParsel();
		parsel.setId(118L);
		
		Plan plan = gisOrtakServis.readPlanByCriteria(parsel,kurumSabit);
		
		
		if (plan!=null ){
				
			System.out.println(plan.getAdi());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Plan Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	
	public void testSearchKadastroParselandMahalle(){
		
		KadastroParsel parsel=new KadastroParsel();

		System.out.println("---------------------------");
		System.out.println("Parsel Arama Testi Başlatıldı");
		System.out.println("---------------------------");
		
		
		//parsel.setPafta("80");
		parsel.setAda(415L);
		Mahalle mah=new Mahalle();
		mah.setId(181L);
		List<KadastroParsel> parseller=ortakService.readAllKadastroParselByParselAndMahalle(null, parsel,mah);
		if (parseller!=null && parseller.size()>0){
			
			for (KadastroParsel prsl : parseller) {
				
				System.out.println(prsl.getId()+"-"+ prsl.getAda());
			}
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Parsel Arama Testi Bitti");
		System.out.println("---------------------------");
	}

	public void testSearchAdresByBina(){
		
		System.out.println("---------------------------");
		System.out.println("Adres Arama Testi Başladı");
		System.out.println("---------------------------");
		Adres adres=new Adres();
		adres.setKapiNo(1L);
		
		MahalleCaddeSokak mahcaddeSokak=new MahalleCaddeSokak();
		Mahalle mahalle=new Mahalle();
		mahalle.setId(144L);
		mahcaddeSokak.setMahalle(mahalle);
		
		CaddeSokak caddeSokak=new CaddeSokak();
		caddeSokak.setId(36858L);
		mahcaddeSokak.setCaddeSokak(caddeSokak);
		
		Bina bina=new Bina();
		
		bina.setApartmanBlokAd("Murat%");
		bina.setId(109028606L);
		KurumSabit kurum=new KurumSabit();
		kurum.setId(1L);
		
		//List<Adres> adresler=ortakService.readAllAdresByCriteria(null, adres, mahcaddeSokak,bina);
		List<Adres> adresler=ortakService.readAllAdresByBina(bina,kurum);
	
		if (adresler!=null && adresler.size()>0){
			
			for (Adres adrs : adresler) {
				
				System.out.println(adrs.getId()+"-"+ adrs.getAciklama());
			}
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Adres Arama Testi Bitti");
		System.out.println("---------------------------");
	}

	public void testSearchBinaByOnemliYer(){
		
		System.out.println("---------------------------");
		System.out.println("Önemli Yer ile Bina Arama Testi Başladı");
		System.out.println("---------------------------");
		OnemliYer onemliYer=new OnemliYer();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		Bagimsiz bagimsiz=new Bagimsiz();
		bagimsiz.setId(354271L);
		
		onemliYer.setBagimsiz(bagimsiz);
		
		Bina  bina = gisOrtakServis.readBinaByOnemliYer(onemliYer,kurumSabit);
		
		if (bina!=null){
			
			System.out.println(bina.getId()+"-"+ bina.getApartmanBlokAd());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Önemli Yer ile Bina Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchBinaByAdres(){
		
		System.out.println("---------------------------");
		System.out.println("Adres ile Bina Arama Testi Başladı");
		System.out.println("---------------------------");
		Adres adres=new Adres();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		adres.setId(109079725L); 
		Bina  bina=ortakService.readBinaByAdres(adres,kurumSabit);
		
		if (bina!=null){
			
			System.out.println(bina.getId()+"-"+ bina.getApartmanBlokAd());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Adres ile Bina Arama Testi Bitti");
		System.out.println("---------------------------");
	}	
	
	public void testSearchBinaByBagimsiz(){
		System.out.println("---------------------------");
		System.out.println("Bagimsiz ile Bina Arama Testi Başladı");
		System.out.println("---------------------------");
		Bagimsiz bagimsiz=new Bagimsiz();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		bagimsiz.setId(354271L); 
		Bina  bina=ortakService.readBinaByBagimsiz(bagimsiz,kurumSabit);
		
		if (bina!=null){
				
			System.out.println(bina.getId()+"-"+ bina.getApartmanBlokAd());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Bagimsiz ile Bina Arama Testi Bitti");
		System.out.println("---------------------------");
	}

	public void testSearchOnemliYerByBagimsiz(){
		
		System.out.println("---------------------------");
		System.out.println("Bagimsiz ile Önemli Yer Arama Testi Başladı");
		System.out.println("---------------------------");
		Bagimsiz bagimsiz=new Bagimsiz();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		bagimsiz.setId(402414L); 
		OnemliYer  onemliYer = gisOrtakServis.readOnemliYerByBagimsiz(bagimsiz);
		
		if (onemliYer!=null){
			
			System.out.println(onemliYer.getId()+"-"+ onemliYer.getAciklama());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Bagimsiz ile Önemli Yer Arama Testi Bitti");
		System.out.println("---------------------------");
	}

	public void testSearchMahalleCaddeSokakByAdres(){
		
		System.out.println("---------------------------");
		System.out.println("Adres ile Mahalle Cadde/Sokak Arama Testi Başladı");
		System.out.println("---------------------------");
		Adres adres=new Adres();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		adres.setId(109079725L); 
		List<MahalleCaddeSokak>  mcsList=ortakService.readAllMahalleCaddeSokakByAdres(adres, kurumSabit);
		
		if (mcsList!=null){
			
			for (MahalleCaddeSokak mahalleCaddeSokak : mcsList) {
			
				if (mahalleCaddeSokak.getMahalle()!=null){
					System.out.println("Mahalle :" + mahalleCaddeSokak.getMahalle().getAciklama()+  " Cadde :"+mahalleCaddeSokak.getCaddeSokak().getAciklama());
				}
			}	
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Adres ile Mahalle Cadde/Sokak Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchMahalleCaddeSokakByKadastroParsel(){
		
		System.out.println("---------------------------");
		System.out.println("Kadastro Parsel ile Mahalle Cadde/Sokak Arama Testi Başladı");
		System.out.println("---------------------------");
		KadastroParsel parsel=new KadastroParsel();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		parsel.setId(109023832L); 
		List<MahalleCaddeSokak>  mcsList=ortakService.readAllMahalleCaddeSokakByKadastroParsel(parsel, kurumSabit);
		
		if (mcsList!=null){
			
			for (MahalleCaddeSokak mahalleCaddeSokak : mcsList) {
			
				if (mahalleCaddeSokak.getMahalle()!=null){
					System.out.println("Mahalle :" + mahalleCaddeSokak.getMahalle().getAciklama()+  " Cadde :"+mahalleCaddeSokak.getCaddeSokak().getAciklama());
				}
			}	
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Kadastro Parsel ile Mahalle Cadde/Sokak Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchMahalleCaddeSokakByBagimsiz(){
		
		System.out.println("---------------------------");
		System.out.println("Bağımsız ile Mahalle Cadde/Sokak Arama Testi Başladı");
		System.out.println("---------------------------");
		Bagimsiz bagimsiz=new Bagimsiz();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		bagimsiz.setId(354271L); 
		List<MahalleCaddeSokak>  mcsList=ortakService.readAllMahalleCaddeSokakByBagimsiz(bagimsiz, kurumSabit);
		
		if (mcsList!=null){
			
			for (MahalleCaddeSokak mahalleCaddeSokak : mcsList) {
			
				if (mahalleCaddeSokak.getMahalle()!=null){
					System.out.println("Mahalle :" + mahalleCaddeSokak.getMahalle().getAciklama()+  " Cadde :"+mahalleCaddeSokak.getCaddeSokak().getAciklama());
				}
			}	
		}
		else
		{
			
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Adres ile Mahalle Cadde/Sokak Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchMahalleCaddeSokakByBina(){
		
		System.out.println("---------------------------");
		System.out.println("Bina ile Mahalle Cadde/Sokak Arama Testi Başladı");
		System.out.println("---------------------------");
		Bina bina=new Bina();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		bina.setId(109028606L); 
		List<MahalleCaddeSokak>  mcsList=ortakService.readAllMahalleCaddeSokakByBina(bina, kurumSabit);
		
		if (mcsList!=null){
			
			for (MahalleCaddeSokak mahalleCaddeSokak : mcsList) {
			
				if (mahalleCaddeSokak.getMahalle()!=null){
					System.out.println("Mahalle :" + mahalleCaddeSokak.getMahalle().getAciklama()+  " Cadde :"+mahalleCaddeSokak.getCaddeSokak().getAciklama());
				}
			}	
		}
		else
		{
			
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Adres ile Mahalle Cadde/Sokak Arama Testi Bitti");
		System.out.println("---------------------------");
	}
	
	public void testSearchMahalleCaddeSokakByMahalleCaddeSokak(){
		
		System.out.println("---------------------------");
		System.out.println("Mahalle Cadde/Sokak ile Mahalle Cadde/Sokak Arama Testi Başladı");
		System.out.println("---------------------------");
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		MahalleCaddeSokak mahalleCaddeSokak=new MahalleCaddeSokak();
		CaddeSokak cadde=new CaddeSokak();
		cadde.setId(35976L);
		mahalleCaddeSokak.setCaddeSokak(cadde);
		List<MahalleCaddeSokak>  mcsList=ortakService.readAllMahalleCaddeSokakByMahalleCaddeSokak(mahalleCaddeSokak, kurumSabit);
		
		if (mcsList!=null){
			
			for (MahalleCaddeSokak mcs : mcsList) {
			
				if (mcs.getMahalle()!=null){
					System.out.println("Mahalle :" + mcs.getMahalle().getAciklama()+  " Cadde :"+mcs.getCaddeSokak().getAciklama());
				}
			}	
		}
		else
		{
			
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Mahalle Cadde/Sokak ile Mahalle Cadde/Sokak Arama Testi Başladı");
		System.out.println("---------------------------");
	}
	
	public void testSearchAdresByOnemliYer(){
		
		System.out.println("---------------------------");
		System.out.println("Önemli Yer ile Adres Arama Testi Başladı");
		System.out.println("---------------------------");
		OnemliYer onemliYer=new OnemliYer();
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		
		onemliYer.setId(12596L);
		onemliYer.setAciklama("NİL ECZANESİ");
		Adres  adres = gisOrtakServis.readAdresByOnemliYer(onemliYer, kurumSabit);
		
		if (adres!=null){
			
			List<MahalleCaddeSokak> mcsList=ortakService.readAllMahalleCaddeSokakByAdres(adres, kurumSabit);
			MahalleCaddeSokak mcs=mcsList.get(0);
			
			System.out.println(adres.getId()+"-"+ adres.getAciklama());
			System.out.println(mcs.getMahalle().getAciklama()+"/"+mcs.getCaddeSokak().getAciklama());	
		}
		else
		{
			
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Önemli Yer ile Adres Arama Testi Bitti");
		System.out.println("---------------------------");
	}
		
	public void testSearchAdresByIlIlceMahalleCaddeSokakKapiNo(){
		
		System.out.println("---------------------------");
		System.out.println("Adres Arama Testi Başladı");
		System.out.println("---------------------------");
		
		KurumSabit ks = new KurumSabit();
		ks.setId(1L);
		Adres adres = gisOrtakServis.readAdresByCriteries(ks, "fetihtepe", "çinar sokak", "16");
		
		if (adres!=null){
			
			System.out.println(adres.getId()+"-"+ adres.getKapiNo());
		}
		else
		{
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Adres Arama Testi Bitti");
		System.out.println("---------------------------");
		
	}
	
	public void testSearchAcikAdresByOnemliYer(){
		
		System.out.println("---------------------------");
		System.out.println("Önemli Yer ile Açık Adres Arama Testi Başladı");
		System.out.println("---------------------------");
		OnemliYer onemliYer=new OnemliYer();
		KurumSabit kurumSabit=new KurumSabit();
		
		kurumSabit.setId(1L);

		onemliYer.setId(111L);
		String  acikAdres = gisOrtakServis.readAcikAdresByOnemliyer(onemliYer, kurumSabit);
		
		if (acikAdres!=null && !acikAdres.equals("") ){
			
			System.out.println(acikAdres);
		}
		else
		{
			
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Önemli Yer ile Açık Adres Arama Testi Bitti");
		System.out.println("---------------------------");
	}	
	
	
public void testSearchAcikAdresByBagimsiz(){
		
		System.out.println("---------------------------");
		System.out.println("Bağımsız Yer ile Açık Adres Arama Testi Başladı");
		System.out.println("---------------------------");
		Bagimsiz bagimsiz=new Bagimsiz();
		KurumSabit kurumSabit=new KurumSabit();
		
		kurumSabit.setId(1L);

		bagimsiz.setId(301555L);
		String  acikAdres =gisOrtakServis.readAcikAdresByBagimsiz(bagimsiz, kurumSabit);
		
		if (acikAdres!=null && !acikAdres.equals("") ){
			
			System.out.println(acikAdres);
		}
		else
		{
			
			System.out.println("Aradığınız kriterlere uygun veri yok!");
		}
		System.out.println("---------------------------");
		System.out.println("Önemli Yer ile Açık Adres Arama Testi Bitti");
		System.out.println("---------------------------");
	}	
	
}
