package com.sampas.ortak.spatial.history.servis;

import java.util.Date;
import java.util.List;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.ortak.spatial.history.model.BaseHistoryObject;
import com.sampas.socbs.core.data.IFeatureDataStore;


public interface IHistoryServisOld {

	List<BaseHistoryObject> readFeatureHistory(IHistoricalable historicalableObject,String degisiklikYapan,Date historyDateBegin,Date historyDateEnd,String degisiklikTipi);
	
	boolean saveFeatureHistory(IHistoricalable historyObject, String degisiklikYapan, Date degisiklikTarihi,String degisiklikTipi);
	
	void setFeatureDataStore(IFeatureDataStore featureDataStore);
	
}
