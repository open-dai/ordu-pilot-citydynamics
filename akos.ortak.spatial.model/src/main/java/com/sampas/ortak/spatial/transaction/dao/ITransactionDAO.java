package com.sampas.ortak.spatial.transaction.dao;

import java.util.Collection;
import java.util.List;
import com.sampas.ortak.spatial.transaction.model.AdresTrs;
import com.sampas.ortak.spatial.transaction.model.BagimsizTrs;
import com.sampas.ortak.spatial.transaction.model.BinaNitelikTrs;
import com.sampas.ortak.spatial.transaction.model.BinaOrtakKullanimTrs;
import com.sampas.ortak.spatial.transaction.model.BinaParselTrs;
import com.sampas.ortak.spatial.transaction.model.BinaTesisatTrs;
import com.sampas.ortak.spatial.transaction.model.BinaTrs;
import com.sampas.ortak.spatial.transaction.model.CaddeSokakTrs;
import com.sampas.ortak.spatial.transaction.model.MahalleCaddeSokakTrs;


public interface ITransactionDAO {
	
	public List<CaddeSokakTrs> readAllStreetTransactions(CaddeSokakTrs streetTrs);
	
	public List<MahalleCaddeSokakTrs> readAllDistrictStreetTransactions(MahalleCaddeSokakTrs distStrTrs);
	
	public boolean saveDistrictStreetTransaction(MahalleCaddeSokakTrs districtStreetCrossTrs);
	
	public boolean saveDistrictStreetTransactions(Collection<MahalleCaddeSokakTrs> districtStreetCrossTrsColl);
	
	public boolean deleteDistrictStreetTransaction(MahalleCaddeSokakTrs distStrTrs);
	
	public boolean deleteDistrictStreetTransactions(Collection<MahalleCaddeSokakTrs> distStrTrsList);
	
	public boolean saveAddressTransaction(AdresTrs adresTrs);
	
	public boolean saveAddressTransactions(Collection<AdresTrs> adresTrsColl);
	
	public boolean deleteAddressTransaction(AdresTrs adresTrs);
	
	public boolean deleteAddressTransactions(Collection<AdresTrs> adresTrsCollection);
	
	public List<AdresTrs> readAllAddressTransactions(AdresTrs adresTrs);
	
	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrs(MahalleCaddeSokakTrs districtStreetTrs);
	
	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrsList(List<MahalleCaddeSokakTrs> districtStreetTrsList);

	public List<BinaTrs> readAllBuildingTransactions(BinaTrs buildingTrs);
	
	public List<BinaNitelikTrs> readAllBuildingAttributesByCriteria(BinaTrs buildingTrs);
	
	public List<BinaTesisatTrs> readAllBuildingFittingsByCriteria(BinaTrs buildingTrs);
		
	public List<BinaOrtakKullanimTrs> readAllBuildingCommonUsagesByCriteria(BinaTrs buildingTrs);
	
	public List<BagimsizTrs> readAllBuildingDetachedsByCriteria(BinaTrs buildingTrs);
	
	public List<BinaParselTrs> readAllBuildingParcelByCriteria(BinaTrs buildingTrs);
	
	public List<AdresTrs> readAllBuildingAddressByCriteria(BinaTrs buildingTrs);
	
	public boolean saveBuildingTransaction(BinaTrs buildingTrs);
	
	public boolean saveBuildingTransactions(Collection<BinaTrs> buildingTrsList);
	
	
}
