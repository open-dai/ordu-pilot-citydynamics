package com.sampas.gis.ortak.gisdatabase.tools.servis.tests;

import java.util.Iterator;
import oracle.jdbc.pool.OracleDataSource;
import org.geotools.data.DataStore;
import org.geotools.data.FeatureSource;
import org.geotools.data.jdbc.JDBCDataStoreConfig;
import org.geotools.data.oracle.OracleDataStore;
import org.geotools.feature.Feature;
import org.geotools.feature.FeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import junit.framework.TestCase;

public class FeatureReaderJTests extends TestCase {

	public void testReadFromDataStore() {

		try {
	
			OracleDataSource oracleDataSource = new OracleDataSource();
			oracleDataSource.setURL("jdbc:oracle:thin:@192.168.34.14:1521:SAMPAS10T");
			oracleDataSource.setDatabaseName("BEYOGLU");
			oracleDataSource.setUser("BEYOGLU");
			oracleDataSource.setPassword("BEYOGLU");
			oracleDataSource.setConnectionCacheName("Catch");
			oracleDataSource.setConnectionCachingEnabled(true);
			
			JDBCDataStoreConfig dataStoreConfig = new JDBCDataStoreConfig("smpboglu", "BEYOGLU", 100);
			
			DataStore dataStore = new OracleDataStore(oracleDataSource, dataStoreConfig);
			
			FeatureSource featureSource = dataStore.getFeatureSource("GRF_MAHALLE");
			
			FeatureCollection features = featureSource.getFeatures();
			
			Iterator<?> featureIterator = features.iterator();
			
			IFeatureCollection smpFeatures = new SmpFeatureCollection();
			
			//int cntr = 0;
			
			try {
				while (featureIterator.hasNext()) {
							
					smpFeatures.addFeature(new SmpFeature((Feature)featureIterator.next()));
					//System.out.println("Counter : " + cntr + " : " + smpFeatures.getFeatureAt(cntr).getAttribute("MAHALLE_ADI"));
					//cntr++;
				}
			}
			finally {
				features.close(featureIterator);
			}

			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : FeatureReaderJTests JUnit Test");
			System.out.println("Feature Count : " + smpFeatures.getFeaturesCount());
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			 
			//Test criteria	
			assertNotSame(0, smpFeatures);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
