package com.sampas.ortak.spatial.transaction.servis.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.geotools.feature.IllegalAttributeException;
import com.sampas.akos.common.model.ITransactionable;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.gis.ortak.tools.impl.EntityMetaData;
import com.sampas.ortak.spatial.transaction.dao.ITransactionDAOOld;
import com.sampas.ortak.spatial.transaction.model.BaseTransactionObject;
import com.sampas.socbs.base.database.services.IGISDBImportServices;
import com.sampas.socbs.base.database.services.impl.SmpGISDBImportServices;
import com.sampas.socbs.base.feature.services.IFeatureFinderServices;
import com.sampas.socbs.base.services.transformpersist.common.ITransformPersistExtraAttributes;
import com.sampas.socbs.base.services.transformpersist.common.ITransformPersistFeatureServices;
import com.sampas.socbs.base.services.transformpersist.common.impl.SmpTransformPersistExtraAttributes;
import com.sampas.socbs.base.services.transformpersist.common.impl.SmpTransformPersistFeatureServices;
import com.sampas.socbs.base.transaction.services.ITransactionExtraAttributes;
import com.sampas.socbs.base.transaction.services.impl.SmpTransactionExtraAttributes;
import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureTypeName;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;


public class TransactionServisOld {

	IGisOrtakServis gisOrtakServis = null;
	
	IFeatureDataStore featureDataStore = null;
	
	IGisDatabaseServis gisDatabaseServis = null;
	
	IFeatureFinderServices featureFinderServis;
	
	ITransactionDAOOld transactionDAOOld;
	
	public List<BaseTransactionObject> readObjectTransactions(ITransactionable transactionableObject, Long processId, Long taskId, Long id, String processUser, Date transactionDateBegin, Date transactionDateEnd, String status) {
		
		return transactionDAOOld.readObjectTransactions(transactionableObject, processId, taskId, id, processUser, transactionDateBegin, transactionDateEnd, status) ;
	}
	
	public boolean saveObjectTransaction(ITransactionable transactionObject, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status) {
		
		boolean isSuccess = false;
		
		try
		{
			Class<?> objectClass = transactionObject.getClass();
		
			EntityMetaData entityMetaData = gisOrtakServis.readEntityMetaData(objectClass.getName());
		
			String tableName = entityMetaData.getDbTableName();
			
			IFeatureTypeName featureTypeName = new SmpFeatureTypeName(tableName);
			
			Long id = transactionObject.getId();
			
			IFeature feature = featureFinderServis.getFeatureByFNameAndID(this.featureDataStore, featureTypeName, id.longValue());
			
//			java.sql.Date date = new java.sql.Date(transactionDate.getTime());//  versionDate.getYear(),versionDate.getMonth(),versionDate.getDay());
//			date.setHours(versionDate.getHours());
//			date.setMinutes(versionDate.getMinutes());
//			date.setSeconds(versionDate.getSeconds());
			
			ITransactionExtraAttributes transactionExtraAttributes = new SmpTransactionExtraAttributes(processId, taskId, processUser, transactionDate, status);
			
			ITransformPersistExtraAttributes transformPersistExtraAttributes = new SmpTransformPersistExtraAttributes();
			
			transformPersistExtraAttributes.setTransactionExtraAttributes(transactionExtraAttributes);
			
			ITransformPersistFeatureServices transformPersistFeatureService = new SmpTransformPersistFeatureServices(this.featureDataStore);
			
			isSuccess = transformPersistFeatureService.transformPersistFeature(feature, transformPersistExtraAttributes);
		}
		catch (Exception ex) {
			
			System.out.println("Error on runing transaction service ! ERROR : " + ex);
			
			isSuccess = false;
		}
		return isSuccess;
	}
	
	@SuppressWarnings("deprecation")
	public boolean saveFeatureTransactionFromShpFile(String shapeFilePath, HashMap<String, String> attributeMapper, ITransactionable transactionObject, String islemiYapan, Date transactionDate, Long surecId, Long taskId, String onayDurumu,KurumSabit kurumSabit) {
		
		Boolean IsSucceded = false;
		
		IGISDBImportServices dbImportService=new SmpGISDBImportServices(featureDataStore);
		
		EntityMetaData entityMetaData = gisOrtakServis.readEntityMetaData(transactionObject.getClass().getName());
		
		String tableName=entityMetaData.getDbTableName();
		
		IFeatureTypeName featureTypeName = new SmpFeatureTypeName(tableName + "_TRS");
		
		IFeatureCollection featureCollection=dbImportService.createFeatureCollectionFromShpFile(featureTypeName, shapeFilePath, attributeMapper);
		
		if (featureCollection!=null){
			
			try {
				
				for (int i = 0; i < featureCollection.getFeaturesCount(); i++) {
					
					IFeature feature = featureCollection.getFeatureAt(i);
	
					feature.setAttribute("KURUM_ID",new java.math.BigDecimal(kurumSabit.getId().longValue()));
					
					feature.setAttribute("SUREC_ID",new java.math.BigDecimal(surecId.longValue()));
					
					feature.setAttribute("TASK_ID",new java.math.BigDecimal(taskId.longValue()));
					
					feature.setAttribute("ISLEMI_YAPAN",islemiYapan);
					
					feature.setAttribute("ONAY_DURUMU",onayDurumu);
					
					if (transactionDate!=null){
					
						java.sql.Date islemTarihi=new java.sql.Date(transactionDate.getYear(),transactionDate.getMonth(),transactionDate.getDay());
						
						feature.setAttribute("ISLEM_TARIHI", islemTarihi);
					}	
				}
			} catch (IllegalAttributeException ex) {
				
				ex.printStackTrace();
			}
			IsSucceded= gisDatabaseServis.writeFeatures(featureCollection);
			
			return IsSucceded;
		}
		return dbImportService.importDBFromShpFile(featureTypeName, shapeFilePath, attributeMapper);
	}

	
	//TODO: Getters and Setters Separator
	
	public IGisOrtakServis getGisOrtakServis() {
		return gisOrtakServis;
	}

	public void setGisOrtakServis(IGisOrtakServis gisOrtakServis) {
		this.gisOrtakServis = gisOrtakServis;
	}

	public IFeatureDataStore getFeatureDataStore() {
		return featureDataStore;
	}

	public void setFeatureDataStore(IFeatureDataStore featureDataStore) {
		this.featureDataStore = featureDataStore;
	}

	public IGisDatabaseServis getGisDatabaseServis() {
		return gisDatabaseServis;
	}

	public void setGisDatabaseServis(IGisDatabaseServis gisDatabaseServis) {
		this.gisDatabaseServis = gisDatabaseServis;
	}

	public IFeatureFinderServices getFeatureFinderServis() {
		return featureFinderServis;
	}

	public void setFeatureFinderServis(IFeatureFinderServices featureFinderServis) {
		this.featureFinderServis = featureFinderServis;
	}

}
