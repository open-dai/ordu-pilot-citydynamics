package com.sampas.gis.ortak.gisdatabase.tools.servis.tests;

import oracle.jdbc.pool.OracleDataSource;
import org.geotools.data.jdbc.JDBCDataStoreConfig;

import com.sampas.gis.ortak.gisdatabase.creator.servis.IGisDatabaseCreator;
import com.sampas.gis.ortak.gisdatabase.creator.servis.impl.GisDatabaseCreator;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.oracle.impl.OracleDataStore;

import junit.framework.TestCase;

public class GisDatabaseCreatorJTests extends TestCase {
	
	private IFeatureDataStore dataStore;
	
	public void globalDataStore() {
		
		try {
			
			OracleDataSource oracleDataSource = new OracleDataSource();
			
//			oracleDataSource.setURL("jdbc:oracle:thin:@192.168.34.14:1521:SAMPAS10T");
//			oracleDataSource.setDatabaseName("BEYOGLU");
//			oracleDataSource.setUser("BEYOGLU");
//			oracleDataSource.setPassword("BEYOGLU");
//			oracleDataSource.setConnectionCacheName("Cache");
//			oracleDataSource.setConnectionCachingEnabled(true);
//			JDBCDataStoreConfig dataStoreConfig = new JDBCDataStoreConfig("smpboglu", "BEYOGLU", 100);
			
			oracleDataSource.setURL("jdbc:oracle:thin:@venus.sampas.com.tr:1521:sampas");
			oracleDataSource.setDatabaseName("AKOS_SOA_TEST");
			oracleDataSource.setUser("AKOS_ENTEGRATION");
			oracleDataSource.setPassword("AKOS_ENTEGRATION");
			oracleDataSource.setConnectionCacheName("Cache");
			oracleDataSource.setConnectionCachingEnabled(true);
			JDBCDataStoreConfig dataStoreConfig = new JDBCDataStoreConfig("smp", "AKOS_ENTEGRATION", 100);
			
			this.dataStore = new OracleDataStore(oracleDataSource, dataStoreConfig);
			
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating Datastore ERROR: " + ex);
		}
	}
	
	public void testGisDatabaseCreator() {
		
		globalDataStore();
	
		IGisDatabaseCreator databaseCreator = new GisDatabaseCreator();
		
		databaseCreator.creatGISDatabaseComponents(dataStore);
		
	}

}
