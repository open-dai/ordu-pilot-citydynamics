package com.sampas.ortak.spatial.transaction.servis;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sampas.akos.common.exception.AkosException;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.BinaNitelik;
import com.sampas.akos.ortak.model.BinaOrtakKullanim;
import com.sampas.akos.ortak.model.BinaParsel;
import com.sampas.akos.ortak.model.BinaTesisat;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.ortak.spatial.transaction.model.AdresTrs;
import com.sampas.ortak.spatial.transaction.model.BagimsizTrs;
import com.sampas.ortak.spatial.transaction.model.BinaNitelikTrs;
import com.sampas.ortak.spatial.transaction.model.BinaOrtakKullanimTrs;
import com.sampas.ortak.spatial.transaction.model.BinaParselTrs;
import com.sampas.ortak.spatial.transaction.model.BinaTesisatTrs;
import com.sampas.ortak.spatial.transaction.model.BinaTrs;
import com.sampas.ortak.spatial.transaction.model.CaddeSokakTrs;
import com.sampas.ortak.spatial.transaction.model.MahalleCaddeSokakTrs;


public interface ITransactionServis {
	
	public boolean saveStreetObjectTransaction(List<CaddeSokak> streets, Long processId, Long taskId, Long objectID, String changeType, Date transactionDate, String status);
	
	public boolean saveDistrictStreetCrossObjectsTransaction(List<MahalleCaddeSokak> districtStreet, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status);	
	
	public boolean saveStreetAndDistCrossObjectsTransaction(CaddeSokak street, List<MahalleCaddeSokak> districtStreets, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status);
	
	public boolean saveStreetTrs(CaddeSokakTrs streetTrs);
	
	public boolean saveDistrictStreetTrs(MahalleCaddeSokakTrs districtStreetTrs);
	
	public boolean saveDistrictStreetTrsList(List<MahalleCaddeSokakTrs> districtStreetTrsList);
	
	public boolean saveAddressTrs(AdresTrs addressTrs);
	
	public boolean saveAddressTrsList(List<AdresTrs> addressTrslist);
	
	public List<CaddeSokakTrs> readAllStreetTransactions(CaddeSokakTrs streetTrs);
	
	public List<MahalleCaddeSokakTrs> readAllDistrictStreetTransactions(MahalleCaddeSokakTrs distStrTrs);
	
	public List<AdresTrs> readAllAddressTransactions(AdresTrs addressTrs);
	
	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrs(MahalleCaddeSokakTrs districtStreetTrs);
	
	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrsList(List<MahalleCaddeSokakTrs> districtStreetTrsList);
	
	public boolean deleteDistrictStreetTransaction(MahalleCaddeSokakTrs distStrTrs);
	
	public boolean deleteDistrictStreetTransactions(Collection<MahalleCaddeSokakTrs> distStrTrsColl);
	
	public boolean deleteAddressTransaction(AdresTrs addressTrs);
	
	public boolean deleteAddressTransactions(Collection<AdresTrs> addressTrsCollection);
	
	public List<CaddeSokak> convertStreetTrsListToStreetOrjList(List<CaddeSokakTrs> streetTrsList);
	
	public CaddeSokak convertStreetTrsToStreetOrj(CaddeSokakTrs streetTrs);
	
	public List<MahalleCaddeSokak> convertDistStreetTrsListToDistStreetOrjList(List<MahalleCaddeSokakTrs> distStreetTrsList, KurumSabit compConst);
	
	public MahalleCaddeSokak convertDistStreetTrsToDistStreetOrj(MahalleCaddeSokakTrs distStreetTrs, KurumSabit compConst);
	
	public List<Adres> convertAddressTrsListToAddressOrjList(List<AdresTrs> addressTrsList);
	
	public Adres convertAddressTrsToAddressOrj(AdresTrs addressTrs);
	
	public List<CaddeSokakTrs> convertStreetOrjListToStreetTrsList(List<CaddeSokak> streetOrjList, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status);
	
	public CaddeSokakTrs convertStreetOrjToStreetTrs(CaddeSokak streetOrj, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status);
		
	public List<MahalleCaddeSokakTrs> convertDistStreetOrjListToDistStreetTrsList(List<MahalleCaddeSokak> districtStreetOrjList, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status);
	
	public MahalleCaddeSokakTrs convertDistStreetOrjToDistStreetTrs(MahalleCaddeSokak districtStreetOrj, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status);
	
	public List<BinaTrs> readAllBuildingTransactions(BinaTrs buildingTrs);
	
	public List<BinaNitelikTrs> readAllBuildingAttributesByCriteria(BinaTrs buildingTrs);
	
	public List<BinaTesisatTrs> readAllBuildingFittingsByCriteria(BinaTrs buildingTrs);

	public List<BinaOrtakKullanimTrs> readAllBuildingCommonUsagesByCriteria(BinaTrs buildingTrs);
	
	public List<BagimsizTrs> readAllBuildingDetachedsByCriteria(BinaTrs buildingTrs);
	
	public List<BinaParselTrs> readAllBuildingParcelByCriteria(BinaTrs buildingTrs);
	
	public List<AdresTrs> readAllBuildingAddressByCriteria(BinaTrs buildingTrs);
	
	public Bina convertBuildingTrsToBuildingOrj(BinaTrs buildingTrs) throws AkosException;
	
	public List<BinaTesisat> convertBuildingFittingTrsListToOrjList(List<BinaTesisatTrs> buildingFittingTrsList) throws AkosException;
	
	public List<BinaOrtakKullanim> convertBuildingCommonUsageTrsListToOrjList(List<BinaOrtakKullanimTrs> buildingCommonUsageTrsList) throws AkosException;

	public List<BinaNitelik> convertBuildingAttributesTrstListToOrjList(List<BinaNitelikTrs> buildingAttributesTrsList) throws AkosException;
	
	public List<Bagimsiz> convertDetachedTrsListToOrjList(List<BagimsizTrs> detachedTrsList) throws AkosException;
	
	public List<BinaParsel> convertBuildingParceTrsListToOrjList(List<BinaParselTrs> buildingParcelTrsList) throws AkosException;

	public boolean saveBuildingTransaction(BinaTrs buildingTrs);
	
	public boolean saveBuildingTransactions(Collection<BinaTrs> buildingTrsList);
}
