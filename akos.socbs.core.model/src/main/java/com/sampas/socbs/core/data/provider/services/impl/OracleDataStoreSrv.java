package com.sampas.socbs.core.data.provider.services.impl;

import java.io.IOException;
import java.util.HashMap;
import javax.sql.DataSource;
import org.geotools.data.DataSourceException;
import org.geotools.data.jdbc.datasource.DataSourceUtil;
import org.geotools.data.jdbc.datasource.ManageableDataSource;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.oracle.impl.OracleDataStore;
import com.sampas.socbs.core.data.provider.services.IOracleDataStoreSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;


public class OracleDataStoreSrv implements IOracleDataStoreSrv {

	private OracleDataStore oracleDataStore = null;
	
	private static final String JDBC_PATH = "jdbc:oracle:thin:@";

	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	
	@SuppressWarnings("unchecked")
	public OracleDataStoreSrv(String namespace, String host, int port, String instance, String user, String passwd, String schema, int maxConn, int minConn, Boolean validateConn) {

		DataSource source;
		
		try {
			
			source = getDefaultDataSource(host, user, passwd, port, instance, maxConn, minConn, validateConn);
			
			this.oracleDataStore = new OracleDataStore(source, namespace, schema, new HashMap());
		} catch (DataSourceException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
    private static ManageableDataSource getDefaultDataSource(String host, String user, String passwd, int port, String instance, int maxActive, int minIdle, boolean validate) throws DataSourceException {
        
    	String dbUrl = null;
        
    	if( instance.startsWith("(") )
    		
            dbUrl = JDBC_PATH + instance;
        else if( instance.startsWith("/") )
        	
            dbUrl = JDBC_PATH + "//" + host + ":" + port + instance;
        else
        	
            dbUrl = JDBC_PATH + host + ":" + port + ":" + instance;
        return (DataSourceUtil.buildDefaultDataSource(dbUrl, JDBC_DRIVER, user, passwd, maxActive, minIdle, validate ? "select sysdate from dual" : null, false, 0));
    }
	
	public IFeatureDataStore getOracleDataStore() {
						
		return (this.oracleDataStore);
	}

	public IFeatureCollection getFeaturesFromOracleDSByLayerName(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}	

}
