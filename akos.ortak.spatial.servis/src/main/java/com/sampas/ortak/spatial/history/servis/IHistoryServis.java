package com.sampas.ortak.spatial.history.servis;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.ortak.spatial.history.model.AdresHst;
import com.sampas.ortak.spatial.history.model.BagimsizHst;
import com.sampas.ortak.spatial.history.model.BinaHst;
import com.sampas.ortak.spatial.history.model.CaddeSokakHst;
import com.sampas.ortak.spatial.history.model.MahalleCaddeSokakHst;

@SuppressWarnings("unchecked")
public interface IHistoryServis {

	public boolean saveStreetOrjToHistory(CaddeSokak streetOrj,  Long processId, Long taskId,String recordOwner, Date changeDate, String processType);
	
	public boolean saveStreetOrjListToHistory(List<CaddeSokak> streetOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);

	public boolean deleteStreetHst(CaddeSokakHst streetHst);
	
	public boolean deleteStreetHstCollection(Collection<CaddeSokakHst> streetHstCollection);
	
	public boolean saveDistrictStreetOrjToHistory(MahalleCaddeSokak districtStreetOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);
	
	public boolean saveDistrictStreetOrjListToHistory(List<MahalleCaddeSokak> districtStreetListOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);
	
	public boolean deleteDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst);
	
	public boolean deleteDistrictStreetHstCollection(Collection<MahalleCaddeSokakHst> districtStreetHstCollection);
	
	public boolean saveAddressOrjToHistory(Adres addressOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst);
	
	public boolean saveAddressOrjListToHistory(List<Adres> addressOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst);
	
	public boolean deleteAddressHst(AdresHst addressHst);
	
	public boolean deleteAddressHstCollection(Collection<AdresHst> addressHstCollection);

	public boolean saveStreetHst(CaddeSokakHst streetHst);
	
	public boolean saveStreetHstList(List<CaddeSokakHst> streetHstList);
	
	public boolean saveDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst);
	
	public boolean saveDistrictStreetHstList(List<MahalleCaddeSokakHst> districtStreetHstList);
	
	public boolean saveAddressHst(AdresHst addressHst);
	
	public boolean saveAddressHstList(List<AdresHst> addressHstList);
	
	public CaddeSokakHst convertStreetOrjToStreetHst(CaddeSokak streetOrj, Long processId, Long taskId,String recordOwner, Date changeDate, String processType);
	
	public MahalleCaddeSokakHst convertDistrictStreetOrjToDistrictStreetHst(MahalleCaddeSokak districtStreetOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);
	
	public AdresHst convertAddressOrjToAddressHst(Adres addressOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst);
	
	public List<CaddeSokakHst> convertStreetOrjListToStreetHstList(List<CaddeSokak> streetOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);

	public List<MahalleCaddeSokakHst> convertDistrictStreetOrjListToDistrictStreetHstList(List<MahalleCaddeSokak> districtStreetOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);
	
	public List<AdresHst> convertAddressOrjListToAddressHstList(List<Adres> addressOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst);

	public abstract <T> List<T> readAllObjectHistoryByCriteria(Class entity,Long processId,Long taskId);
	
	public abstract void deleteAllObjectHistoryByCriteria(Class entity,Long processId,Long taskId);
	
	public BinaHst convertBuildingOrjToHst(Bina building,Long processId, Long taskId,String recordOwner, Date changeDate, String processType);
	
	public boolean saveBuildingHst(BinaHst buildingHst);
	
	public BagimsizHst convertDetachedOrjToDetachedHst(Bagimsiz detachedOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);
	
	public List<BagimsizHst> convertDetachedOrjListToDetachedHstList(List<Bagimsiz> detachedOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType);
	
	public boolean saveDetachedHstList(List<BagimsizHst> detachedHstList);
	
}	
