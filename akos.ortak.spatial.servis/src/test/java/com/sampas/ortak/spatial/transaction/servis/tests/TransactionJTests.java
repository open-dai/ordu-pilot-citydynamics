package com.sampas.ortak.spatial.transaction.servis.tests;

import java.util.HashMap;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.ortak.spatial.transaction.servis.ITransactionServis;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


@SuppressWarnings("unused")
public class TransactionJTests extends BaseTestCase {

	IGisOrtakServis searchService;
	
	ITransactionServis transactionService;
	
	OrtakServis ortakService;
	
	private static IFeatureDataStore dataStore;
	
    TestServerMetaData testServerMetaData=new TestServerMetaData();
    
	@Override
	protected String[] getXmlDataFileResource() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/akos/commonTestContext.xml" };
	}
	
	@SuppressWarnings("static-access")
	protected void onSetUpInTransaction() throws Exception {
		// System.setProperty("javax.net.debug", "ssl,data");
		setComplete();		
		ortakService=(OrtakServis)applicationContext.getBean("ortakServis");
		searchService = (IGisOrtakServis) applicationContext.getBean("searchService");
		transactionService=(ITransactionServis)applicationContext.getBean("transactionServis");
		//transactionService.setFeatureDataStore(testServerMetaData.getTestDataStore());
		this.dataStore=testServerMetaData.getTestDataStore();
	}
	
	public void testWriteTransactionableObject(){
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		OnemliYer onemliYer=new OnemliYer();
		onemliYer.setId(47L);
		onemliYer.setAciklama("FAZLI APARTMANI");
		ITransactionable trsObject=(ITransactionable)searchService.readAdresByOnemliYer(onemliYer, kurumSabit);
		//boolean IsSuccess=transactionService.saveFeatureTransaction(trsObject, "admin",Calendar.getInstance().getTime(), 1L, 1L, "OK");
		//assertTrue(IsSuccess);
	}
	
	public void testWriteTransactionObjectFromShapeFile(){
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		
		String shpFilePath="C:\\Yeni Cadde Sokak\\yeniCaddeSokak.shp";
		HashMap<String, String> attributeMapper=new HashMap<String, String>();
		
		attributeMapper.put("CADDE_SOKAK_ADI", "CADDE_SOKA");
		attributeMapper.put("ESKI_CADDE_SOKAK_ADI", "ESKI_CADDE");
		attributeMapper.put("MECLIS_KARAR_NO", "MECLIS_KAR");
		attributeMapper.put("MECLIS_KARAR_TARIHI", "MECLIS__0");
		attributeMapper.put("TEK_KAPI_BASLANGIC_NO", "TEK_KAPI_B");
		attributeMapper.put("KAYIT_TARIHI", "KAYIT_TARI");
		attributeMapper.put("KAYDEDEN", "KAYDEDEN");
		attributeMapper.put("KURUM_ID", "KURUM_ID");
		attributeMapper.put("CADDE_SOKAK_TUR_ID", "CADDE_S_1");
		attributeMapper.put("TEK_KAPI_BITIS_NO", "TEK_KAP_2");
		attributeMapper.put("CIFT_KAPI_BASLANGIC_NO", "CIFT_KAPI_");
		attributeMapper.put("CIFT_KAPI_BITIS_NO", "CIFT_KA_3");
		attributeMapper.put("FID", "FID");
		attributeMapper.put("GEOMETRY", "the_geom");
		
		CaddeSokak cadde=new CaddeSokak();
		//boolean IsSucceded=transactionService.saveFeatureTransactionFromShpFile(shpFilePath, attributeMapper, cadde, "ADMIN", Calendar.getInstance().getTime(), 12L,1L, "IN_PRGS", kurumSabit);
		//assertTrue(IsSucceded);
	}
	
	public void testReadTransactions(){
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		OnemliYer onemliYer=new OnemliYer();
		onemliYer.setId(47L);
		onemliYer.setAciklama("FAZLI APARTMANI");
		ITransactionable trsObject=(ITransactionable)searchService.readAdresByOnemliYer(onemliYer, kurumSabit);
		
		//List<BaseTransactionObject> transactionLists=transactionService.readFeatureTransactions(trsObject, 1L, 1L, "admin", null,null, "OK",1L);
		
//		if (transactionLists!=null && transactionLists.size()>0){
//			for (Iterator<?> iterator = transactionLists.iterator(); iterator
//					.hasNext();) {
//				BaseTransactionObject baseTransactionObject = (BaseTransactionObject) iterator
//						.next();
//				System.out.println(baseTransactionObject.getId()+"--"+baseTransactionObject.getIslemiYapan());
//				
//			}
//		}
	}
	
}
