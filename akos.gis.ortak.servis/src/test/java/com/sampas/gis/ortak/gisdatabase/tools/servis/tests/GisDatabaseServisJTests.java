package com.sampas.gis.ortak.gisdatabase.tools.servis.tests;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.pool.OracleDataSource;
import org.geotools.data.DataStore;
import org.geotools.data.jdbc.JDBCDataStoreConfig;
import com.sampas.socbs.core.data.oracle.impl.OracleDataStore;
import com.sampas.socbs.core.dataset.feature.IAttributeType;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;
import com.sampas.socbs.core.dataset.feature.impl.SmpAttributeType;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureTypeName;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;
import com.sampas.socbs.core.gisdatabase.tools.servis.impl.GisDatabaseServis;
import com.vividsolutions.jts.geom.Polygon;
import junit.framework.TestCase;

public class GisDatabaseServisJTests extends TestCase {

	private DataStore dataStore;
	
	private DataStore writeDataStore;
	
	private IFeatureTypeName newFeatureTypeName;
	
	private IFeatureTypeName writeFeatureTypeName;
	
	private IGisDatabaseServis gisDatabaseServis;
	
	private IGisDatabaseServis writeGisDatabaseServis;

	public void globalDataStore() {
		
		try {
			
			OracleDataSource oracleDataSource = new OracleDataSource();
			
			oracleDataSource.setURL("jdbc:oracle:thin:@192.168.34.14:1521:SAMPAS10T");
			oracleDataSource.setDatabaseName("BEYOGLU");
			oracleDataSource.setUser("BEYOGLU");
			oracleDataSource.setPassword("BEYOGLU");
			oracleDataSource.setConnectionCacheName("Cache");
			oracleDataSource.setConnectionCachingEnabled(true);
			JDBCDataStoreConfig dataStoreConfig = new JDBCDataStoreConfig("smpboglu", "BEYOGLU", 100);
			
			this.dataStore = new OracleDataStore(oracleDataSource, dataStoreConfig);
			
			this.writeDataStore = new OracleDataStore(oracleDataSource, dataStoreConfig);
			
			this.gisDatabaseServis = new GisDatabaseServis(dataStore);
			
			this.writeGisDatabaseServis = new GisDatabaseServis(writeDataStore);
			
			this.newFeatureTypeName = new SmpFeatureTypeName("SOA_GRF_MAHALLE_TEST");
			
			this.writeFeatureTypeName = new SmpFeatureTypeName("SOA_GRF_MAHALLE_TEST2");
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating Datastore ERROR: " + ex);
		}
	}
	
	public void testFeatureDataStore() {
	
		try {
			
			globalDataStore();
			
			List<IAttributeType> attributeItems = new ArrayList<IAttributeType>();
			IAttributeType attributeTypeOne = new SmpAttributeType("GEOMETRY", Polygon.class);
			IAttributeType attributeTypeTwo = new SmpAttributeType("MAHALLE_ADI", String.class);
			IAttributeType attributeTypeThree = new SmpAttributeType("MAHALLE_KODU", Integer.class);
			//IAttributeType attributeTypeFour = new SmpAttributeType("KAYIT_TARIHI", Date.class);
			IAttributeType attributeTypeFour = new SmpAttributeType("KAYIT_TARIHI", Date.class);
			IAttributeType attributeTypeFive = new SmpAttributeType("MAHALLE_NO", Integer.class);
			IAttributeType attributeTypeSix = new SmpAttributeType("SILINME_TARIHI", Date.class);
			attributeItems.add(attributeTypeOne);
			attributeItems.add(attributeTypeTwo);
			attributeItems.add(attributeTypeThree);
			attributeItems.add(attributeTypeFour);
			attributeItems.add(attributeTypeFive);
			attributeItems.add(attributeTypeSix);
			
			boolean isSuccess = this.gisDatabaseServis.createFeatureType(newFeatureTypeName, attributeItems);
			
			boolean isWriteDSSuccess = this.writeGisDatabaseServis.createFeatureType(writeFeatureTypeName, attributeItems);
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : GisDatabaseServisJTests DataStore JUnit Test");
			System.out.println("Yeni tablo kayit basarili mi? : " + isSuccess);
			System.out.println("Yeni yazma tablosu kaydi basarili mi? : " + isWriteDSSuccess);
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			 
			//Test criteria	
			assertSame(true, isSuccess);
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating new featureType to FeatureStore ERROR: " + ex);
		}
	}	
	
	public void testFeatureReader() {
		
		try {
			
			globalDataStore();
			
			IFeatureCollection features = this.gisDatabaseServis.readFeatures(this.newFeatureTypeName);
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : GisDatabaseServisJTests Read Features JUnit Test");
			System.out.println("Elde edilen nesne sayisi : " + features.getFeaturesCount());
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			 
			//Test criteria	
			assertNotSame(0, features.getFeaturesCount());
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating new featureType to FeatureStore ERROR: " + ex);
		}
	}
	
	public void testFeatureWriter() {
		
		try {
			
			globalDataStore();
			
			IFeatureCollection features = this.gisDatabaseServis.readFeatures(this.newFeatureTypeName);
			
			boolean isSuccess = this.writeGisDatabaseServis.writeFeatures(features);
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : GisDatabaseServisJTests Write Features JUnit Test");
			System.out.println("Nesne yazma islemi bararili mi? : " + isSuccess);
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			 
			//Test criteria	
			assertSame(true, isSuccess);
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating new featureType to FeatureStore ERROR: " + ex);
		}
	}
	
	public void testFeatureUpdate() {
		
		try {
			
			globalDataStore();
			
			IFeatureCollection features = this.gisDatabaseServis.readFeatures(this.newFeatureTypeName);
			
			boolean isSuccess = this.writeGisDatabaseServis.writeFeatures(features);
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : GisDatabaseServisJTests Write Features JUnit Test");
			System.out.println("Nesne yazma islemi bararili mi? : " + isSuccess);
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
			 
			//Test criteria	
			assertSame(true, isSuccess);
			
		} catch (Exception ex) {
			
			System.out.println("Error on creating new featureType to FeatureStore ERROR: " + ex);
		}
	}
	
}
