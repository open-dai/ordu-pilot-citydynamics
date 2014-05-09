package com.sampas.ortak.spatial.history.dao;

import java.util.Collection;
import java.util.List;
import com.sampas.ortak.spatial.history.model.AdresHst;
import com.sampas.ortak.spatial.history.model.BagimsizHst;
import com.sampas.ortak.spatial.history.model.BinaHst;
import com.sampas.ortak.spatial.history.model.CaddeSokakHst;
import com.sampas.ortak.spatial.history.model.MahalleCaddeSokakHst;

@SuppressWarnings("unchecked")
public interface IHistoryDAO {
	
	public boolean saveStreetHst(CaddeSokakHst streetHst);
	
	public boolean saveStreetHstCollection(Collection<CaddeSokakHst> streetHstCollection);
	
	public boolean deleteStreetHst(CaddeSokakHst streetHst);
	
	public boolean deleteStreetHstCollection(Collection<CaddeSokakHst> streetHstCollection);
	
	public boolean saveDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst);
	
	public boolean saveDistrictStreetHstCollection(Collection<MahalleCaddeSokakHst> districtStreetHstCollection);
	
	public boolean deleteDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst);
	
	public boolean deleteDistrictStreetHstCollection(Collection<MahalleCaddeSokakHst> districtStreetHstCollection);
	
	public boolean saveAddressHst(AdresHst addressHst);
	
	public boolean saveAddressHstCollection(Collection<AdresHst> addressHstCollection);
	
	public boolean deleteAddressHst(AdresHst addressHst);
	
	public boolean deleteAddressHstCollection(Collection<AdresHst> addressHstCollection);
	
	public abstract <T> List<T> readAllObjectHistoryByCriteria(Class entity,Long processId,Long taskId);
	
	public abstract void deleteAllObjectHistoryByCriteria(Class entity,Long processId,Long taskId);
	
	public boolean saveBuildingHst(BinaHst buildingHst);
	
	public boolean saveDetachedHstList(List<BagimsizHst> detachedHstList);
}
