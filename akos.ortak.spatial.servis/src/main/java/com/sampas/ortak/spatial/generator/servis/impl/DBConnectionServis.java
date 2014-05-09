package com.sampas.ortak.spatial.generator.servis.impl;

import com.sampas.ortak.spatial.generator.dao.IDBConnectionDAO;
import com.sampas.ortak.spatial.generator.model.DBConnection;
import com.sampas.ortak.spatial.generator.servis.IDBConnectionServis;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.OracleDataStoreSrv;
import com.sampas.socbs.core.data.provider.services.impl.PostGisDataStoreSrv;
import com.sampas.socbs.core.tools.impl.GlobalConsts;


public class DBConnectionServis implements IDBConnectionServis {

	IDBConnectionDAO dbConnectionDAO;
	
	public DBConnection getDbConnection() {
		
		return dbConnectionDAO.getDbConnection();
	}
	
	public IFeatureDataStore getFeatureDataStore() {
		
		System.out.println("ADIM 1:");
		
		if (this.dbConnectionDAO != null) {
			System.out.println("ADIM 2:");
			if (this.dbConnectionDAO.getDbConnection().getDialect().contains(GlobalConsts.ORACLE_DB_CLUE_NAME)) {
				System.out.println("ADIM 3:");
				return getOracleDBDataStore(this.dbConnectionDAO.getDbConnection());
			} else if (this.dbConnectionDAO.getDbConnection().getDialect().contains(GlobalConsts.POSTGIS_DB_CLUE_NAME)) {
				System.out.println("ADIM 4:");
				return getPostGISDBDataStore(this.dbConnectionDAO.getDbConnection());
			} else {
				System.out.println("ADIM 5:");
				return null;
			}
		} else {
			System.out.println("ADIM 6:");
			return null;
		}
		
	}
	
	private IFeatureDataStore getOracleDBDataStore(DBConnection dbConnection) {
		
		OracleDataStoreSrv oracleDataStoreCreatorSrv = new OracleDataStoreSrv(GlobalConsts.UNKNOWN_NAMESPACE, dbConnection.getHost(), dbConnection.getPort(), dbConnection.getInstance(), dbConnection.getUser(), dbConnection.getPassword(), dbConnection.getSchema(), GlobalConsts.ORACLE_DEFAULT_MAX_CONNECTION, GlobalConsts.ORACLE_DEFAULT_MIN_CONNECTION, GlobalConsts.ORACLE_DEFAULT_VALIDATA_CONNECTION);
		
		return(oracleDataStoreCreatorSrv.getOracleDataStore());

	}
	
	private IFeatureDataStore getPostGISDBDataStore(DBConnection dbConnection) {
		
		PostGisDataStoreSrv postgisDataStoreCreatorSrv = new PostGisDataStoreSrv(dbConnection.getHost(), dbConnection.getPort(), dbConnection.getInstance(), dbConnection.getSchema(), dbConnection.getUser(), dbConnection.getPassword(), GlobalConsts.POSTGIS_DEFAULT_WKT_ENABLED, GlobalConsts.POSTGIS_DEFAULT_LOOSEBOX);
		
		return(postgisDataStoreCreatorSrv.getPostGisDataStore());
	}

	public IDBConnectionDAO getDbConnectionDAO() {
		return dbConnectionDAO;
	}

	public void setDbConnectionDAO(IDBConnectionDAO dbConnectionDAO) {
		this.dbConnectionDAO = dbConnectionDAO;
	}

}
