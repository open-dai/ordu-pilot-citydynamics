package com.sampas.ortak.spatial.generator.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.akos.common.model.AkosReadEnv;
import com.sampas.ortak.spatial.generator.model.DBConnection;


public class DBConnectionDAO extends BaseDaoSupport implements IDBConnectionDAO {

	private DBConnection dbConnection = null;
	
	private String akosYol = null;
	
	public DBConnectionDAO() {
		
		if (dbConnection == null) {
			
			try {
				
				dbConnection = new DBConnection();
				
				readEnvVariables();
				
				String yol = akosYol + DBConnection.DB_CONN_FILE_PATH;
				
				File file = new File(yol);
				
				InputStream conf = new FileInputStream(file);
				
				Properties prop = null;
				
				prop = new Properties();
				
				prop.load(conf);
				
				this.dbConnection.setDialect(prop.getProperty(DBConnection.DB_CONN_DRIVER_CLASS_VAR_NAME));
				
				this.dbConnection.setDriverClass(prop.getProperty(DBConnection.DB_CONN_DRIVER_CLASS_VAR_NAME));
				
				String fullUrl = prop.getProperty(DBConnection.DB_CONN_FULLURL_CLASS_VAR_NAME);
				
				int atLoc = fullUrl.indexOf("@");
				
				String urlPortIns = fullUrl.substring(atLoc + 1);
				
				int dpoint1 = urlPortIns.indexOf(":");
				
				String host = urlPortIns.substring(0, dpoint1);
				
				String portIns = urlPortIns.substring(dpoint1 + 1);
				
				int dpoint2 = portIns.indexOf(":");
				
				String port = portIns.substring(0, dpoint2);
				
				String instance = portIns.substring(dpoint2 + 1);
				
				this.dbConnection.setInstance(instance);
				
				this.dbConnection.setPassword(prop.getProperty(DBConnection.DB_CONN_PASSWORD_CLASS_VAR_NAME));
				
				this.dbConnection.setPort(Integer.valueOf(port));
				
				this.dbConnection.setSchema(prop.getProperty(DBConnection.DB_CONN_SCHEMA_CLASS_VAR_NAME));
				
				this.dbConnection.setHost(host);
				
				this.dbConnection.setUser(prop.getProperty(DBConnection.DB_CONN_USERNAME_CLASS_VAR_NAME));
				
			} catch (Exception ex) {
				
				System.out.println("Error on parsing connection file ! ERROR : " + ex);
			}
		}
	}
	
	private void readEnvVariables() {
		
		try {
			
			Properties p3 = AkosReadEnv.getEnvVars();
			
			akosYol = p3.getProperty(DBConnection.DB_CONN_FILE_ENV_VAR_NAME);
			
		} catch (Throwable ex) {
			
			System.out.println("Error on reading Environment variable file name: " + DBConnection.DB_CONN_FILE_ENV_VAR_NAME + " ! ERROR : " + ex);
		}
	}
	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

}
