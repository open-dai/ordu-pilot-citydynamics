package com.sampas.akos.common.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.sampas.akos.common.model.AkosReadEnv;

public class JdbcConnection {
	private static JdbcConnection instance;
	private String akosYol = null;
	
	public static JdbcConnection getInstance() {
		if (instance == null) { 
			instance = new JdbcConnection();
		}
		return instance;
	}
	public static void setInstance(JdbcConnection instance) {
		JdbcConnection.instance = instance;
	}
	
	public void ortamDegiskenOku() {
		try {
			Properties p3 = AkosReadEnv.getEnvVars();
			akosYol = p3.getProperty("AKOS_YOL");
			//System.out.println("YOL BUDUR : "+akosYol);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {

		try {
			
			ortamDegiskenOku();
			String yol = akosYol + "/common/jdbcConn.properties";
			File file = new File(yol);
			InputStream conf = new FileInputStream(file);
			
			Properties prop = null;
			prop = new Properties();
			prop.load(conf);
			
			System.out.println("GELEN DB : "+prop.getProperty("url")+" = "+prop.getProperty("username")+" = "+prop.getProperty("password"));
			
			Class.forName(prop.getProperty("driver"));
			return DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("username"), prop.getProperty("password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void closeConnection() {
		JdbcConnection.getInstance().closeConnection();
		
	}
	
}
