package com.sampas.ortak.spatial.history.dao;

import java.util.Date;
import java.util.List;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.ortak.spatial.history.model.BaseHistoryObject;


public interface IHistoryDAOOld {

	List<BaseHistoryObject> readFeatureHistory(IHistoricalable historicalableObject,String degisiklikYapan,Date historyDateBegin,Date historyDateEnd,String degisiklikTipi);
	
}
