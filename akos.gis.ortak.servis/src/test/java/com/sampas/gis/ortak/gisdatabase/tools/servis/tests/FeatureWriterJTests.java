package com.sampas.gis.ortak.gisdatabase.tools.servis.tests;

import java.sql.Date;
import oracle.jdbc.pool.OracleDataSource;
import org.geotools.data.DataStore;
import org.geotools.data.jdbc.JDBCDataStoreConfig;
import org.geotools.data.oracle.OracleDataStore;
import org.geotools.feature.AttributeType;
import org.geotools.feature.AttributeTypeFactory;
import org.geotools.feature.FeatureType;
import org.geotools.feature.FeatureTypeFactory;
import com.vividsolutions.jts.geom.Polygon;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
public class FeatureWriterJTests extends TestCase {

	public void testReadFromDataStore() {

		try {
	
			OracleDataSource oracleDataSource = new OracleDataSource();
			
//			oracleDataSource.setURL("jdbc:oracle:thin:@192.168.34.3:1521:sampas");
//			oracleDataSource.setDatabaseName("AKOS_SOA_TEST");
//			oracleDataSource.setUser("AKOS_SOA_TEST");
//			oracleDataSource.setPassword("AKOS_SOA_TEST");
//			oracleDataSource.setConnectionCacheName("Catch");
//			oracleDataSource.setConnectionCachingEnabled(true);
//			JDBCDataStoreConfig dataStoreConfig = new JDBCDataStoreConfig("smpboglu", "AKOS_SOA_TEST", 100);
			
			oracleDataSource.setURL("jdbc:oracle:thin:@192.168.34.14:1521:SAMPAS10T");
			oracleDataSource.setDatabaseName("BEYOGLU");
			oracleDataSource.setUser("BEYOGLU");
			oracleDataSource.setPassword("BEYOGLU");
			oracleDataSource.setConnectionCacheName("Catch");
			oracleDataSource.setConnectionCachingEnabled(true);
			JDBCDataStoreConfig dataStoreConfig = new JDBCDataStoreConfig("smpboglu", "BEYOGLU", 100);
			
			DataStore dataStore = new OracleDataStore(oracleDataSource, dataStoreConfig);
			
			AttributeType geometry = AttributeTypeFactory.newAttributeType("GEOMETRY", Polygon.class);
			AttributeType districtNo = AttributeTypeFactory.newAttributeType("MAHALLE_NO", Integer.class);
			AttributeType districtCode = AttributeTypeFactory.newAttributeType("MAHALLE_KODU", Integer.class);
			AttributeType date = AttributeTypeFactory.newAttributeType("TARIH", Date.class);
			//FeatureType district = dataStore.getSchema("SOA_GRF_MAHALLE_TEST");
			FeatureType district = FeatureTypeFactory.newFeatureType(new AttributeType[] {geometry, districtNo, districtCode, date}, "SOA_GRF_MAHALLE_TEST");
			dataStore.createSchema(district);
			
			//Table Modifications Not Supported yet
			//dataStore.updateSchema("SOA_GRF_MAHALLE_TEST", district);
			
		} catch (Exception ex) {
			
			System.out.println("ERROR : " + ex); 
		}
	}
	
}
