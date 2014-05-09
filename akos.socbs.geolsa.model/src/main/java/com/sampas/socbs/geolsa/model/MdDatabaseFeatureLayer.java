package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdDatabaseFeatureLayer {

	private long oid;
	private String dbName;
	private String dbPass;
	private int dbPort;
	private String dbURL;
	private String dbUser;
	private String layerGeomClnName;
	private String layerTbName;
	private List<MdFeatureThemeStyle> mdFeatureThemeStyles;
	private String dbSchema;
	private String dbType;
	

	public MdDatabaseFeatureLayer(){

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

	public String getLayerGeomClnName() {
		return layerGeomClnName;
	}

	public void setLayerGeomClnName(String layerGeomClnName) {
		this.layerGeomClnName = layerGeomClnName;
	}

	public String getLayerTbName() {
		return layerTbName;
	}

	public void setLayerTbName(String layerTbName) {
		this.layerTbName = layerTbName;
	}

	public List<MdFeatureThemeStyle> getMdFeatureThemeStyles() {
		return mdFeatureThemeStyles;
	}

	public void setMdFeatureThemeStyles(List<MdFeatureThemeStyle> mdFeatureThemeStyles) {
		this.mdFeatureThemeStyles = mdFeatureThemeStyles;
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