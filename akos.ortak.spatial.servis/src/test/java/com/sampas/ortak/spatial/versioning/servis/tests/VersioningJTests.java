package com.sampas.ortak.spatial.versioning.servis.tests;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import com.sampas.akos.common.model.IVersionable;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.gis.ortak.tools.impl.EntityMetaData;
import com.sampas.ortak.spatial.history.model.CaddeSokakHst;
import com.sampas.ortak.spatial.versioning.servis.IVersioningServisOld;
import com.sampas.ortak.spatial.versiyon.model.BaseVersionObject;
import com.sampas.ortak.spatial.versiyon.model.OnemliYerVrs;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.test.tools.TestServerMetaData;


@SuppressWarnings("unchecked")
public class VersioningJTests extends BaseTestCase {
	
	@SuppressWarnings("unused")
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
	
	IGisOrtakServis searchService = null;
	
	IVersioningServisOld versioningService;
	
	OrtakServis ortakService;
	
	@SuppressWarnings("static-access")
	protected void onSetUpInTransaction() throws Exception {
		// System.setProperty("javax.net.debug", "ssl,data");
		setComplete();		
		ortakService=(OrtakServis)applicationContext.getBean("ortakServis");
		searchService = (IGisOrtakServis) applicationContext.getBean("searchService");
		versioningService=(IVersioningServisOld)applicationContext.getBean("versioningService");
		versioningService.setFeatureDataStore(testServerMetaData.getTestDataStore());
		this.dataStore=testServerMetaData.getTestDataStore();
	}
	
	public void testReadEntityMetaData(){
		
		EntityMetaData metadata=searchService.readEntityMetaData(CaddeSokakHst.class.getName());
		
		Class classOfObject=metadata.getEntityClass();
		if (classOfObject!=null){
			System.out.println("Entity Tip Adı :"+classOfObject.getName());
			
		}
		if (metadata.getPropertyColumnMappings()!=null){
			for (Iterator<String> i = metadata.getPropertyColumnMappings().keySet().iterator() ; i.hasNext();) {
				String propName=i.next();
				if (propName!=null){
					String columnName=metadata.getPropertyColumnMappings().get(propName);
					System.out.println(propName + "~~~~>"+columnName);
				}
					
			}
		}
		
 		Class typeOfClassa= OnemliYerVrs.class;
 		
 		try {
			@SuppressWarnings("unused")
			BaseVersionObject bvo=(BaseVersionObject) typeOfClassa.newInstance();
			Method[] methods=typeOfClassa.getMethods();
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().startsWith("set")){
					System.out
							.println(methods[i].getName());
					
					Class<?>[] methodParams=methods[i].getParameterTypes();
					Object sayi=10100L;
					Object paramValue= methodParams[0].cast(sayi);
					System.out.println(paramValue.getClass().getName());
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testWriteVersionableObject(){
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		OnemliYer onemliYer=new OnemliYer();
		onemliYer.setId(47L);
		onemliYer.setAciklama("FAZLI APARTMANI");
		IVersionable vrsObject=(IVersionable)searchService.readAdresByOnemliYer(onemliYer, kurumSabit);
		boolean IsSuccess=versioningService.saveFeatureVersion(vrsObject, "admin", Calendar.getInstance().getTime(), 123L);
		assertTrue(IsSuccess);
	}
	
	public void testReadVersions(){
		
		KurumSabit kurumSabit=new KurumSabit();
		kurumSabit.setId(1L);
		OnemliYer onemliYer=new OnemliYer();
		onemliYer.setId(47L);
		onemliYer.setAciklama("FAZLI APARTMANI");
		IVersionable vrsObject=(IVersionable)searchService.readAdresByOnemliYer(onemliYer, kurumSabit);
		
//		Date beginDate=new Date(2009,1,1);
//		Date endDate=new Date(2009,12,31);
		
		Date beginDate=null;
		Date endDate=null;
		
		List<BaseVersionObject> versionLists=versioningService.readFeatureVersions(vrsObject, "admin", 123L,beginDate,endDate);
		
		if (versionLists!=null && versionLists.size()>0){
			for (Iterator iterator = versionLists.iterator(); iterator
					.hasNext();) {
				BaseVersionObject baseVersionObject = (BaseVersionObject) iterator
						.next();
				System.out.println(baseVersionObject.getId()+"--"+baseVersionObject.getIslemiYapan());
			}
		}
	}
	
}
