package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdDatabaseRasterLayer {

	private long oid;
	private String dbName;
	private String dbPass;
	private int dbPort;
	private String dbURL;
	private String dbUser;
	private String rasterName;
	private int rasterType;
	private List<MdRasterThemeStyle> mdRasterThemeStyles;
	private String dbSchema;
	private String dbType;
	

	public MdDatabaseRasterLayer(){

	}

	public void finalize() throws Throwable {

	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public int getDbPort() {
		return dbPort;
	}

	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getRasterName() {
		return rasterName;
	}

	public void setRasterName(String rasterName) {
		this.rasterName = rasterName;
	}

	public int getRasterType() {
		return rasterType;
	}

	public void setRasterType(int rasterType) {
		this.rasterType = rasterType;
	}

	public List<MdRasterThemeStyle> getMdRasterThemeStyles() {
		return mdRasterThemeStyles;
	}

	public void setMdRasterThemeStyles(List<MdRasterThemeStyle> mdRasterThemeStyles) {
		this.mdRasterThemeStyles = mdRasterThemeStyles;
	}

	public String getDbSchema() {
		return dbSchema;
	}

	public void setDbSchema(String dbSchema) {
		this.dbSchema = dbSchema;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

}