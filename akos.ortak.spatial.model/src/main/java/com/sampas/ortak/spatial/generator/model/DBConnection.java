package com.sampas.ortak.spatial.generator.model;



public class DBConnection {

	private String driverClass;
	
	private String host;
	
	private int port;
	
	private String instance;
	
	private String schema;
	
	private String user;
	
	private String password;
	
	private String dialect;
	
	
	public static String DB_CONN_DRIVER_CLASS_VAR_NAME = "hibernate.connection.driver_class";
	
	public static String DB_CONN_FULLURL_CLASS_VAR_NAME = "hibernate.connection.url";
	
	public static String DB_CONN_SCHEMA_CLASS_VAR_NAME = "hibernate.default_schema";
	
	public static String DB_CONN_USERNAME_CLASS_VAR_NAME = "hibernate.connection.username";
	
	public static String DB_CONN_PASSWORD_CLASS_VAR_NAME = "hibernate.connection.password";
	
	public static String DB_CONN_DIALECT_CLASS_VAR_NAME = "hibernate.dialect";
	
	public static String DB_CONN_FILE_ENV_VAR_NAME = "AKOS_YOL";
	
	public static String DB_CONN_FILE_PATH = "/common/webHibernate.properties";

	
	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
	
}
