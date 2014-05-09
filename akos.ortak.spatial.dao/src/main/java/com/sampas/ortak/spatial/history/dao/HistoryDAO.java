package com.sampas.ortak.spatial.history.dao;

import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.ortak.spatial.history.model.AdresHst;
import com.sampas.ortak.spatial.history.model.BagimsizHst;
import com.sampas.ortak.spatial.history.model.BinaHst;
import com.sampas.ortak.spatial.history.model.CaddeSokakHst;
import com.sampas.ortak.spatial.history.model.MahalleCaddeSokakHst;


@SuppressWarnings("unchecked")
public class HistoryDAO extends BaseDaoSupport implements IHistoryDAO {

	public boolean saveStreetHst(CaddeSokakHst streetHst) {
		
		if (streetHst != null) {
			
			super.saveOrUpdate(streetHst);
			
			return true;
		}
		return false;
	}
	
	public boolean saveStreetHstCollection(Collection<CaddeSokakHst> streetHstCollection) {
		
		if (streetHstCollection != null && streetHstCollection.size() > 0) {
			
			super.saveOrUpdate(streetHstCollection);
			
			return true;
		}
		return false;
	}

	public boolean deleteStreetHst(CaddeSokakHst streetHst) {
		
		if (streetHst != null) {
			
			super.deleteDBObject(streetHst);
			
			return true;
		} 
		return false;
	}
	
	public boolean deleteStreetHstCollection(Collection<CaddeSokakHst> streetHstCollection) {
		
		if (streetHstCollection != null && streetHstCollection.size() > 0) {
			
			super.deleteDBObject(streetHstCollection);
			
			return true;
		} 
		return false;
	}
	
	public boolean saveDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst) {

		if (districtStreetHst != null ) {
			
			super.saveOrUpdate(districtStreetHst);
			
			return true;
		}
		return false;	
	}

	public boolean saveDistrictStreetHstCollection(Collection<MahalleCaddeSokakHst> districtStreetHstCollection) {

		if (districtStreetHstCollection != null && districtStreetHstCollection.size() > 0) {
			
			super.saveOrUpdate(districtStreetHstCollection);
			
			return true;
		}
		return false;
	}
	
	public boolean deleteDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst) {
		
		if (districtStreetHst != null) {
			
			super.deleteDBObject(districtStreetHst);
			
			return true;
		} 
		return false;
	}
	
	public boolean deleteDistrictStreetHstCollection(Collection<MahalleCaddeSokakHst> districtStreetHstCollection) {
		
		if (districtStreetHstCollection != null && districtStreetHstCollection.size() > 0) {
			
			super.deleteDBObject(districtStreetHstCollection);
			
			return true;
		} 
		return false;
	}
	
	public boolean saveAddressHst(AdresHst addressHst) {

		if (addressHst != null) {
			
			super.saveOrUpdate(addressHst);
			
			return true;
		}
		return false;
	}

	public boolean saveAddressHstCollection(Collection<AdresHst> addressHstCollection) {

		if (addressHstCollection != null && addressHstCollection.size() > 0) {
			
			super.saveOrUpdate(addressHstCollection);
			
			return true;
		}
		return false;
	}

	public boolean deleteAddressHst(AdresHst addressHst) {
		
		if (addressHst != null) {
			
			super.deleteDBObject(addressHst);
			
			return true;
		} 
		return false;
	}
	
	public boolean deleteAddressHstCollection(Collection<AdresHst> addressHstCollection) {
		
		if (addressHstCollection != null && addressHstCollection.size() > 0) {
			
			super.deleteDBObject(addressHstCollection);
			
			return true;
		} 
		return false;
	}

	public <T> List<T> readAllObjectHistoryByCriteria(Class entity, Long processId, Long taskId) {
		
		Criteria criteria=getSession().createCriteria(entity);
		
		if (processId!=null && processId.longValue()>0){
			
			criteria.add(Restrictions.eq("surecId", processId));
			
			if (taskId!=null && taskId.longValue()>0){
				
				criteria.add(Restrictions.eq("taskId", taskId));
			}
			return criteria.list();
		}

		return null;
	}

	public void deleteAllObjectHistoryByCriteria(Class entity, Long processId,
			Long taskId) {
		
		List<Object> historyObjectList=  readAllObjectHistoryByCriteria(entity, processId, taskId);

		if (historyObjectList!=null && historyObjectList.size()>0)
			super.deleteDBObject(historyObjectList);
		
	}

	public boolean saveBuildingHst(BinaHst buildingHst) {
		
		if (buildingHst != null) {
			
			super.saveOrUpdate(buildingHst);
			
			return true;
		}
		return false;
	}

	public boolean saveDetachedHstList(List<BagimsizHst> detachedHstList) {
		
		if (detachedHstList!=null && detachedHstList.size()>0){
			
			super.saveOrUpdate(detachedHstList);
		}
		return false;
	}
	
}
