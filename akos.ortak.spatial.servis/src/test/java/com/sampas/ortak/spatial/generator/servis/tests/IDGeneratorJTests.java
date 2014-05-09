package com.sampas.ortak.spatial.generator.servis.tests;

import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.ortak.spatial.generator.servis.impl.IDGeneratorServis;


public class IDGeneratorJTests extends BaseTestCase {
	
	private IDGeneratorServis idGeneratorServis;
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/akos/commonTestContext.xml" };
	}

	protected void onSetUpInTransaction() throws Exception {
		
		setComplete();
		
		idGeneratorServis = (IDGeneratorServis)applicationContext.getBean("idGeneratorServis");
	}
	
	@Override
	protected String[] getXmlDataFileResource() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void testGenerateNewIDforObject() {
		
		//Take new 2 id for street object and check: they are different
		Long firstID = idGeneratorServis.getIDForNewObject(CaddeSokak.class);
		Long secondID = idGeneratorServis.getIDForNewObject(CaddeSokak.class);
		
		assertNotSame(firstID, secondID);
		
	}
	
}
