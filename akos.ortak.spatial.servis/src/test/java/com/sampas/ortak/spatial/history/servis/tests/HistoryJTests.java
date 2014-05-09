package com.sampas.ortak.spatial.history.servis.tests;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.ortak.spatial.history.model.BaseHistoryObject;
import com.sampas.ortak.spatial.history.servis.IHistoryServisOld;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.test.tools.TestServerMetaData;
import com.sampas.socbs.geolsa.servis.IGeolsaServis;


public class HistoryJTests extends BaseTestCase {
	
	IGisOrtakServis gisOrtakServis;
	
	IHistoryServisOld historyServiceOld;
	
	OrtakServis ortakService;
	
	IGeolsaServis geolsaServis;
	
	@SuppressWarnings("unused")
	private static IFeatureDataStore dataStore;
	
    private TestServerMetaData testServerMetaData=new TestServerMetaData();
	
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
		
		setComplete();		
		ortakService=(OrtakServis)applicationContext.getBean("ortakServis");
		gisOrtakServis = (IGisOrtakServis)applicationContext.getBean("gisOrtakServis");
		historyServiceOld=(IHistoryServisOld)applicationContext.getBean("historyServiceOld");
		historyServiceOld.setFeatureDataStore(testServerMetaData.getTestDataStore());
		historyServiceOld.setFeatureDataStore(testServerMetaData.getTestDataStore());
		this.dataStore=testServerMetaData.getTestDataStore();
	}
	
	public void testWriteHistoricalableObjectOld() {
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		OnemliYer onemliYer=new OnemliYer();
		onemliYer.setId(47L);
		onemliYer.setAciklama("FAZLI APARTMANI");
		IHistoricalable hstObject=(IHistoricalable)gisOrtakServis.readAdresByOnemliYer(onemliYer, kurumSabit);
		boolean IsSuccess=historyServiceOld.saveFeatureHistory(hstObject, "admin",Calendar.getInstance().getTime(),"I");
		assertTrue(IsSuccess);
	
	}
	
	public void testReadHistoriesOld(){
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		OnemliYer onemliYer=new OnemliYer();
		onemliYer.setId(47L);
		onemliYer.setAciklama("FAZLI APARTMANI");
		IHistoricalable hstObject=(IHistoricalable)gisOrtakServis.readAdresByOnemliYer(onemliYer, kurumSabit);
		
		//Date beginDate=null;
		//Date endDate=null;
		
		List<BaseHistoryObject> historyLists=historyServiceOld.readFeatureHistory(hstObject,"admin",null,null,"I");
		
		if (historyLists!=null && historyLists.size()>0){
			for (Iterator<?> iterator = historyLists.iterator(); iterator
					.hasNext();) {
				BaseHistoryObject baseHistoryObject = (BaseHistoryObject) iterator
						.next();
				System.out.println(baseHistoryObject.getId()+"--"+baseHistoryObject.getDegisiklikYapan());
				
			}
		}
	}
	
}
