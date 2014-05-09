package com.sampas.ortak.spatial.history.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.akos.common.model.IHistoricalable;
import com.sampas.ortak.spatial.history.model.BaseHistoryObject;


public class HistoryDAOOld extends BaseDaoSupport implements IHistoryDAOOld {

	@SuppressWarnings("unchecked")
	public List<BaseHistoryObject> readFeatureHistory(
			IHistoricalable historicalableObject, String degisiklikYapan,
			Date historyDateBegin, Date historyDateEnd, String degisiklikTipi) {
		
		List<BaseHistoryObject> toReturn=null;
		
		if (historicalableObject!=null && historicalableObject.getId()!=null && historicalableObject.getId()>0){
			List<Object> params=new ArrayList<Object>();
			params.add(historicalableObject.getId());
			String objectClassName=historicalableObject.getClass().getSimpleName();
			String historicalableObjectClassName=objectClassName+"Hst";
			String hql="Select hst from " +historicalableObjectClassName + " as hst where hst.nesneId=?";  
			
			if (degisiklikYapan!=null && !degisiklikYapan.trim().equals("")){
				hql+=" AND hst.degisiklikYapan=?";
				params.add(degisiklikYapan);
			}
			
		
			if (historyDateBegin!=null){
				hql+=" AND hst.degisiklikTarihi>=?";
				params.add(historyDateBegin);
			}
			
			if (historyDateEnd!=null){
				hql+=" AND hst.degisiklikTarihi<=?";
				params.add(historyDateEnd);
			}
			
			if (degisiklikTipi!=null && !degisiklikTipi.trim().equals("")){
				hql+=" AND hst.degisiklikTipi<=?";
				params.add(degisiklikTipi);
			}
		
			try {
			
				toReturn=(List<BaseHistoryObject>)super.readDBObjectByHQL(hql,params.toArray());
			} catch (Exception e) {
			
				toReturn=null;
				e.printStackTrace();
			}
			
		}else{
			System.err.println("BaseTransactionObject TransactionObjectId value must greater than zero");
		}
		return toReturn;
	}
	
}
