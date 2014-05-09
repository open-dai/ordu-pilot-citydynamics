package com.sampas.ortak.spatial.transaction.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.ortak.spatial.transaction.dao.ITransactionDAO;
import com.sampas.ortak.spatial.transaction.model.AdresTrs;
import com.sampas.ortak.spatial.transaction.model.BagimsizTrs;
import com.sampas.ortak.spatial.transaction.model.BinaNitelikTrs;
import com.sampas.ortak.spatial.transaction.model.BinaOrtakKullanimTrs;
import com.sampas.ortak.spatial.transaction.model.BinaParselTrs;
import com.sampas.ortak.spatial.transaction.model.BinaTesisatTrs;
import com.sampas.ortak.spatial.transaction.model.BinaTrs;
import com.sampas.ortak.spatial.transaction.model.CaddeSokakTrs;
import com.sampas.ortak.spatial.transaction.model.MahalleCaddeSokakTrs;


@SuppressWarnings({"unchecked", "deprecation" })
public class TransactionDAO extends BaseDaoSupport implements ITransactionDAO {
	
	public boolean saveDistrictStreetTransaction(MahalleCaddeSokakTrs districtStreetTrs) {
		
		if (districtStreetTrs != null) {
			
			super.saveOrUpdate(districtStreetTrs);
			
			return true;
		}
		return false;
	}
	
	public boolean saveDistrictStreetTransactions(Collection<MahalleCaddeSokakTrs> districtStreetCrossTrsColl) {
		
		if (districtStreetCrossTrsColl != null) {
			
			super.saveOrUpdate(districtStreetCrossTrsColl);
			
			return true;
		}
		return false;
	}

	public List<CaddeSokakTrs> readAllStreetTransactions(CaddeSokakTrs streetTrs) {
		
		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from CaddeSokakTrs as trs where trs.id > 0";
		
		if (streetTrs.getAciklama() != null && streetTrs.getAciklama() != "") {
			
			hql += " AND trs.aciklama=?";
			
			params.add(streetTrs.getAciklama());
		}
		if (streetTrs.getCaddeSokakTurId() != null && streetTrs.getCaddeSokakTurId() > 0) {
			
			hql += " AND trs.caddeSokakTurId=?";
			
			params.add(streetTrs.getCaddeSokakTurId());
		}
		if (streetTrs.getCiftKapiBaslangicNo() != null && streetTrs.getCiftKapiBaslangicNo() > 0) {
			
			hql += " AND trs.ciftKapiBaslangicNo=?";
			
			params.add(streetTrs.getCiftKapiBaslangicNo());
		}
		if (streetTrs.getCiftKapiBitisNo() != null && streetTrs.getCiftKapiBitisNo() > 0) {
			
			hql += " AND trs.ciftKapiBitisNo=?";
			
			params.add(streetTrs.getCiftKapiBitisNo());
		}
		if (streetTrs.getIslemiYapan() != null && streetTrs.getIslemiYapan() != "") {
			
			hql += " AND trs.islemiYapan=?";
			
			params.add(streetTrs.getIslemiYapan());
		}
		if (streetTrs.getIslemTarihi() != null) {
			
			hql += " AND trs.islemTarihi=?";
			
			params.add(streetTrs.getIslemTarihi());
		}
		if (streetTrs.getKaydeden() != null && streetTrs.getKaydeden() != "") {
			
			hql += " AND trs.kaydeden=?";
			
			params.add(streetTrs.getKaydeden());
		}
		if (streetTrs.getKayitTarih() != null) {
			
			hql += " AND trs.kayitTarih=?";
			
			params.add(streetTrs.getKayitTarih());
		}
		if (streetTrs.getKurumSabitId() != null && streetTrs.getKurumSabitId() > 0) {
			
			hql += " AND trs.kurumSabitId=?";
			
			params.add(streetTrs.getKurumSabitId());
		}
		if (streetTrs.getMeclisKararNo() != null && streetTrs.getMeclisKararNo() != "") {
			
			hql += " AND trs.meclisKararNo=?";
			
			params.add(streetTrs.getMeclisKararNo());
		}
		if (streetTrs.getMeclisKararTarih() != null) {
			
			hql += " AND trs.meclisKararTarih=?";
			
			params.add(streetTrs.getMeclisKararTarih());
		}
		if (streetTrs.getNesneId() != null && streetTrs.getNesneId() > 0) {
			
			hql += " AND trs.nesneId=?";
			
			params.add(streetTrs.getNesneId());
		}
		if (streetTrs.getOnayDurumu() != null && streetTrs.getOnayDurumu() != "") {
			
			hql += " AND trs.onayDurumu=?";
			
			params.add(streetTrs.getOnayDurumu());
		}
		if (streetTrs.getSurecId() != null && streetTrs.getSurecId() > 0) {
			
			hql += " AND trs.surecId=?";
			
			params.add(streetTrs.getSurecId());
		}
		if (streetTrs.getTaskId() != null && streetTrs.getTaskId() > 0) {
			
			hql += " AND trs.taskId=?";
			
			params.add(streetTrs.getTaskId());
		}
		if (streetTrs.getTekKapiBaslangicNo() != null && streetTrs.getTekKapiBaslangicNo() > 0) {
			
			hql += " AND trs.tekKapiBaslangicNo=?";
			
			params.add(streetTrs.getTekKapiBaslangicNo());
		}
		if (streetTrs.getTekKapiBitisNo() != null && streetTrs.getTekKapiBitisNo() > 0) {
			
			hql += " AND trs.tekKapiBitisNo=?";
			
			params.add(streetTrs.getTekKapiBitisNo());
		}
		try {
			
			return ((List<CaddeSokakTrs>)super.readDBObjectByHQL(hql,params.toArray()));
		} catch (Exception ex) {
		
			System.out.println("Error on searching streetTRS ! ERROR: " + ex);
			
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<MahalleCaddeSokakTrs> readAllDistrictStreetTransactions(MahalleCaddeSokakTrs distStrTrs) {
		 
		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from MahalleCaddeSokakTrs as trs where trs.id > 0";
		
		if (distStrTrs.getId() != null && distStrTrs.getId() > 0) {
			
			hql += " AND trs.id=?";
			
			params.add(distStrTrs.getId());
		}
		if (distStrTrs.getCaddeSokakId() != null && distStrTrs.getCaddeSokakId() > 0) {
			
			hql += " AND trs.caddeSokakId=?";
			
			params.add(distStrTrs.getCaddeSokakId());
		}
		if (distStrTrs.getCtvDerece() != null && distStrTrs.getCtvDerece() > 0) {
			
			hql += " AND trs.ctvDerece=?";
			
			params.add(distStrTrs.getCtvDerece());
		}
		if (distStrTrs.getCtvGrup() != null && distStrTrs.getCtvGrup() > 0) {
			
			hql += " AND trs.ctvGrup=?";
			
			params.add(distStrTrs.getCtvGrup());
		}
		if (distStrTrs.getIslemiYapan() != null && distStrTrs.getIslemiYapan() != "") {
			
			hql += " AND trs.islemiYapan=?";
			
			params.add(distStrTrs.getIslemiYapan());
		}
		if (distStrTrs.getIslemTarihi() != null) {
			
			hql += " AND trs.islemTarihi=?";
			
			params.add(distStrTrs.getIslemTarihi());
		}
		if (distStrTrs.getKaydeden() != null && distStrTrs.getKaydeden() != "") {
			
			hql += " AND trs.kaydeden=?";
			
			params.add(distStrTrs.getKaydeden());
		}
		if (distStrTrs.getKayitTarih() != null) {
			
			hql += " AND trs.kayitTarih=?";
			
			params.add(distStrTrs.getKayitTarih());
		}
		if (distStrTrs.getMahalleId() != null && distStrTrs.getMahalleId() > 0) {
			
			hql += " AND trs.mahalleId=?";
			
			params.add(distStrTrs.getMahalleId());
		}
		if (distStrTrs.getNesneId() != null && distStrTrs.getNesneId() > 0) {
			
			hql += " AND trs.nesneId=?";
			
			params.add(distStrTrs.getNesneId());
		}
		if (distStrTrs.getOnayDurumu() != null && distStrTrs.getOnayDurumu() != "") {
			
			hql += " AND trs.onayDurumu=?";
			
			params.add(distStrTrs.getOnayDurumu());
		}
		if (distStrTrs.getSurecId() != null && distStrTrs.getSurecId() > 0) {
			
			hql += " AND trs.surecId=?";
			
			params.add(distStrTrs.getSurecId());
		}
		if (distStrTrs.getTaskId() != null && distStrTrs.getTaskId() > 0) {
			
			hql += " AND trs.taskId=?";
			
			params.add(distStrTrs.getTaskId());
		}
		if (distStrTrs.getTuikCaddeSokakKod() != null && distStrTrs.getTuikCaddeSokakKod() > 0) {
			
			hql += " AND trs.tuikCaddeSokakKod=?";
			
			params.add(distStrTrs.getTuikCaddeSokakKod());
		}
		if (distStrTrs.getTuikCaddeSokakTanitimNo() != null && distStrTrs.getTuikCaddeSokakTanitimNo() > 0) {
			
			hql += " AND trs.tuikCaddeSokakTanitimNo=?";
			
			params.add(distStrTrs.getTuikCaddeSokakTanitimNo());
		}
		try {
			
			return ((List<MahalleCaddeSokakTrs>)super.readDBObjectByHQL(hql,params.toArray()));
		} catch (Exception ex) {
		
			System.out.println("Error on searching districtStreetTRS ! ERROR: " + ex);
			
			ex.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteDistrictStreetTransaction(MahalleCaddeSokakTrs distStrTrs) {
		
		if (distStrTrs != null) {
			
			super.deleteDBObject(distStrTrs);
			
			return true;
		}		
		return false;
	}
	
	public boolean deleteDistrictStreetTransactions(Collection<MahalleCaddeSokakTrs> distStrTrsColl) {
		
		if (distStrTrsColl != null) {
			
			super.deleteDBObject(distStrTrsColl);
			
			return true;
		}		
		return false;
	}

	public boolean saveAddressTransaction(AdresTrs adresTrs) {
		
		if (adresTrs != null) {
			
			super.saveOrUpdate(adresTrs);
			
			return true;
		}
		return false;
	}
	
	public boolean saveAddressTransactions(Collection<AdresTrs> addressTrsColl) {
		
		if (addressTrsColl != null) {
			
			super.saveOrUpdate(addressTrsColl);
			
			return true;
		}
		return false;
	}

	public boolean deleteAddressTransaction(AdresTrs adresTrs) {
		
		if (adresTrs != null) {
			
			super.deleteDBObject(adresTrs);
			
			return true;
		}		
		return false;
	}
	
	public boolean deleteAddressTransactions(Collection<AdresTrs> adresTrsCollection) {
		
		if (adresTrsCollection != null && adresTrsCollection.size() > 0) {
			
			super.deleteDBObject(adresTrsCollection);
			
			return true;
		}		
		return false;
	}

	public List<AdresTrs> readAllAddressTransactions(AdresTrs adresTrs) {
		
		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from AdresTrs as trs where trs.id > 0";
		
		if (adresTrs.getId() != null && adresTrs.getId() > 0) {
			
			hql += " AND trs.id=?";
			
			params.add(adresTrs.getId());
		}
		if (adresTrs.getIslemiYapan() != null && adresTrs.getIslemiYapan() != "") {
			
			hql += " AND trs.islemiYapan=?";
			
			params.add(adresTrs.getIslemiYapan());
		}
		if (adresTrs.getIslemTarihi() != null) {
			
			hql += " AND trs.islemTarihi=?";
			
			params.add(adresTrs.getIslemTarihi());
		}
		if (adresTrs.getKaydeden() != null && adresTrs.getKaydeden() != "") {
			
			hql += " AND trs.kaydeden=?";
			
			params.add(adresTrs.getKaydeden());
		}
		if (adresTrs.getKayitTarih() != null) {
			
			hql += " AND trs.kayitTarih=?";
			
			params.add(adresTrs.getKayitTarih());
		}
		if (adresTrs.getNesneId() != null && adresTrs.getNesneId() > 0) {
			
			hql += " AND trs.nesneId=?";
			
			params.add(adresTrs.getNesneId());
		}
		if (adresTrs.getOnayDurumu() != null && adresTrs.getOnayDurumu() != "") {
			
			hql += " AND trs.onayDurumu=?";
			
			params.add(adresTrs.getOnayDurumu());
		}
		if (adresTrs.getSurecId() != null && adresTrs.getSurecId() > 0) {
			
			hql += " AND trs.surecId=?";
			
			params.add(adresTrs.getSurecId());
		}
		if (adresTrs.getTaskId() != null && adresTrs.getTaskId() > 0) {
			
			hql += " AND trs.taskId=?";
			
			params.add(adresTrs.getTaskId());
		}
		if (adresTrs.getAdresNo() != null && adresTrs.getAdresNo() > 0) {
			
			hql += " AND trs.adresNo=?";
			
			params.add(adresTrs.getAdresNo());
		}
		if (adresTrs.getBinaId() != null && adresTrs.getBinaId() > 0) {
			
			hql += " AND trs.binaId=?";
			
			params.add(adresTrs.getBinaId());
		}
		if (adresTrs.getKapiNo() != null && adresTrs.getKapiNo() > 0) {
			
			hql += " AND trs.kapiNo=?";
			
			params.add(adresTrs.getKapiNo());
		}
		if (adresTrs.getMahalleCaddeId() != null && adresTrs.getMahalleCaddeId() > 0) {
			
			hql += " AND trs.mahalleCaddeId=?";
			
			params.add(adresTrs.getMahalleCaddeId());
		}
		if (adresTrs.getKadastroParselId() != null && adresTrs.getKadastroParselId() > 0) {
			
			hql += " AND trs.parselId=?";
			
			params.add(adresTrs.getKadastroParselId());
		}
		try {
			
			return ((List<AdresTrs>)super.readDBObjectByHQL(hql,params.toArray()));
		} catch (Exception ex) {
		
			System.out.println("Error on searching addressTRS ! ERROR: " + ex);
			
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrs(MahalleCaddeSokakTrs districtStreetTrs) {

		if (districtStreetTrs != null) {

			List<Object> params = new ArrayList<Object>();
			
			String hql = "SELECT adrTrs FROM AdresTrs AS adrTrs, MahalleCaddeSokakTrs AS mcsTrs WHERE adrTrs.mahalleCaddeId = mcsTrs.id";
			
			if (districtStreetTrs.getId() != null && districtStreetTrs.getId() > 0) {
				
				hql += " AND mcsTrs.id=?";
				
				params.add(districtStreetTrs.getId());
			}
			if (districtStreetTrs.getCaddeSokakId() != null && districtStreetTrs.getCaddeSokakId() > 1) {
				
				hql += " AND mcsTrs.caddeSokakId=?";
				
				params.add(districtStreetTrs.getCaddeSokakId());
			}
			if (districtStreetTrs.getMahalleId() != null && districtStreetTrs.getMahalleId() > 1) {
				
				hql += " AND mcsTrs.mahalleId=?";
				
				params.add(districtStreetTrs.getMahalleId());
			}
			if (districtStreetTrs.getNesneId() != null && districtStreetTrs.getNesneId() > 1) {
				
				hql += " AND mcsTrs.nesneId=?";
				
				params.add(districtStreetTrs.getNesneId());
			}
			if (districtStreetTrs.getOnayDurumu() != null) {
				
				hql += " AND mcsTrs.onayDurumu=?";
				
				params.add(districtStreetTrs.getOnayDurumu());
			}
			if (districtStreetTrs.getSurecId() != null && districtStreetTrs.getSurecId() > 1) {
				
				hql += " AND mcsTrs.surecId=?";
				
				params.add(districtStreetTrs.getSurecId());
			}
			if (districtStreetTrs.getTaskId() != null && districtStreetTrs.getTaskId() > 1) {
				
				hql += " AND mcsTrs.taskId=?";
				
				params.add(districtStreetTrs.getTaskId());
			}
			if (districtStreetTrs.getTuikCaddeSokakKod() != null && districtStreetTrs.getTuikCaddeSokakKod() > 1) {
				
				hql += " AND mcsTrs.tuikCaddeSokakKod=?";
				
				params.add(districtStreetTrs.getTuikCaddeSokakKod());
			}
			if (districtStreetTrs.getTuikCaddeSokakTanitimNo() != null && districtStreetTrs.getTuikCaddeSokakTanitimNo() > 1) {
				
				hql += " AND mcsTrs.tuikCaddeSokakTanitimNo=?";
				
				params.add(districtStreetTrs.getTuikCaddeSokakTanitimNo());
			}
			List<AdresTrs> list = (List<AdresTrs>) super.readDBObjectByHQL(hql, params.toArray());

			if (list != null && list.size() > 0) {
				
				return list;
				
			} else {
				
				return null;
			}
		} else {
			
			return null;
		}
	}

	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrsList(List<MahalleCaddeSokakTrs> districtStreetTrsList) {
		
		if (districtStreetTrsList != null && districtStreetTrsList.size() > 0) {
			
			List<AdresTrs> resultAddressTrsList = new ArrayList<AdresTrs>();
			
			for (MahalleCaddeSokakTrs tempDistrictStreetTrs : districtStreetTrsList) {
				
				List<AdresTrs> tempAddressTrsList = readAllAddressTrsListByDistrictStreetTrs(tempDistrictStreetTrs);
				
				if (tempAddressTrsList != null && tempAddressTrsList.size() > 0) {
				
					resultAddressTrsList.addAll(tempAddressTrsList);
				}
			}
			return resultAddressTrsList;
		}
		return null;
	}

	public List<BinaTrs> readAllBuildingTransactions(BinaTrs buildingTrs) {

		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from BinaTrs as trs where trs.id > 0";
		
		if (buildingTrs.getKurumSabitId() != null && buildingTrs.getKurumSabitId() > 0) {
			
			hql += " AND trs.kurumSabitId=?";
			
			params.add(buildingTrs.getKurumSabitId());
		
			if (buildingTrs.getNesneId() != null && buildingTrs.getNesneId() > 0) {
				
				hql += " AND trs.nesneId=?";
				
				params.add(buildingTrs.getNesneId());
			}
			if (buildingTrs.getOnayDurumu() != null && buildingTrs.getOnayDurumu() != "") {
				
				hql += " AND trs.onayDurumu=?";
				
				params.add(buildingTrs.getOnayDurumu());
			}
			if (buildingTrs.getSurecId() != null && buildingTrs.getSurecId() > 0) {
				
				hql += " AND trs.surecId=?";
				
				params.add(buildingTrs.getSurecId());
			}
			if (buildingTrs.getTaskId() != null && buildingTrs.getTaskId() > 0) {
				
				hql += " AND trs.taskId=?";
				
				params.add(buildingTrs.getTaskId());
			}
		
			try {
				
				return ((List<BinaTrs>)super.readDBObjectByHQL(hql,params.toArray()));
			} catch (Exception ex) {
			
				System.out.println("Error on searching buildingTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		return null;
		
		
	}

	public List<BinaNitelikTrs> readAllBuildingAttributesByCriteria(
			BinaTrs buildingTrs) {
		
		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from BinaNitelikTrs as trs where trs.id > 0";
		
		if (buildingTrs!=null && buildingTrs.getId()!=null &&  buildingTrs.getKurumSabitId() != null && buildingTrs.getKurumSabitId() > 0) {
			
			hql+=" AND trs.binaId=?";
			
		    params.add(buildingTrs.getId());
			
			if (buildingTrs.getSurecId() != null && buildingTrs.getSurecId() > 0) {
				
				hql += " AND trs.surecId=?";
				
				params.add(buildingTrs.getSurecId());
			}
				try {
				
				return ((List<BinaNitelikTrs>)super.readDBObjectByHQL(hql,params.toArray()));
			} catch (Exception ex) {
			
				System.out.println("Error on searching buildingAttributeTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		return null;
	}

	public List<BinaTesisatTrs> readAllBuildingFittingsByCriteria(
			BinaTrs buildingTrs) {
		
		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from BinaTesisatTrs as trs where trs.id > 0";
		
		if (buildingTrs!=null && buildingTrs.getId()!=null &&  buildingTrs.getKurumSabitId() != null && buildingTrs.getKurumSabitId() > 0) {
			
			hql+=" AND trs.binaId=?";
			
		    params.add(buildingTrs.getId());
			
			if (buildingTrs.getSurecId() != null && buildingTrs.getSurecId() > 0) {
				
				hql += " AND trs.surecId=?";
				
				params.add(buildingTrs.getSurecId());
			}
				try {
				
				return ((List<BinaTesisatTrs>)super.readDBObjectByHQL(hql,params.toArray()));
			} catch (Exception ex) {
			
				System.out.println("Error on searching buildingFittingTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		return null;
	}

	public List<BinaOrtakKullanimTrs> readAllBuildingCommonUsagesByCriteria(
			BinaTrs buildingTrs) {

		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from BinaOrtakKullanimTrs as trs where trs.id > 0";
		
		if (buildingTrs!=null && buildingTrs.getId()!=null &&  buildingTrs.getKurumSabitId() != null && buildingTrs.getKurumSabitId() > 0) {
			
			hql+=" AND trs.binaId=?";
			
		    params.add(buildingTrs.getId());
			
			if (buildingTrs.getSurecId() != null && buildingTrs.getSurecId() > 0) {
				
				hql += " AND trs.surecId=?";
				
				params.add(buildingTrs.getSurecId());
			}
				try {
				
				return ((List<BinaOrtakKullanimTrs>)super.readDBObjectByHQL(hql,params.toArray()));
			} catch (Exception ex) {
			
				System.out.println("Error on searching buildingCommonUsagesTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		return null;
	}

	public List<BagimsizTrs> readAllBuildingDetachedsByCriteria(
			BinaTrs buildingTrs) {

		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from BagimsizTrs as trs where trs.id > 0";
		
		if (buildingTrs!=null && buildingTrs.getId()!=null &&  buildingTrs.getKurumSabitId() != null && buildingTrs.getKurumSabitId() > 0) {
			
			hql+=" AND trs.bina=?";
			
		    params.add(buildingTrs.getId());
			
			if (buildingTrs.getSurecId() != null && buildingTrs.getSurecId() > 0) {
				
				hql += " AND trs.surecId=?";
				
				params.add(buildingTrs.getSurecId());
			}
				try {
				
				return ((List<BagimsizTrs>)super.readDBObjectByHQL(hql,params.toArray()));
			} catch (Exception ex) {
			
				System.out.println("Error on searching buildingCommonUsagesTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		
		return null;
		
	}

	public List<BinaParselTrs> readAllBuildingParcelByCriteria(	BinaTrs buildingTrs) {

		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from BinaParselTrs as trs where trs.id > 0";
		
		if (buildingTrs!=null && buildingTrs.getId()!=null &&  buildingTrs.getKurumSabitId() != null && buildingTrs.getKurumSabitId() > 0) {
			
			hql+=" AND trs.binaId=?";
			
		    params.add(buildingTrs.getId());
			
			if (buildingTrs.getSurecId() != null && buildingTrs.getSurecId() > 0) {
				
				hql += " AND trs.surecId=?";
				
				params.add(buildingTrs.getSurecId());
			}
				try {
				
				return ((List<BinaParselTrs>)super.readDBObjectByHQL(hql,params.toArray()));
			} catch (Exception ex) {
			
				System.out.println("Error on searching buildingParcelTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		
		return null;
		
	}

	public List<AdresTrs> readAllBuildingAddressByCriteria(BinaTrs buildingTrs) {

		List<Object> params = new ArrayList<Object>();
		
		String hql = "Select trs from AdresTrs as trs where trs.id > 0";
		
		if (buildingTrs!=null && buildingTrs.getId()!=null &&  buildingTrs.getKurumSabitId() != null && buildingTrs.getKurumSabitId() > 0) {
			
			hql+=" AND trs.binaId=?";
			
		    params.add(buildingTrs.getId());
			
			if (buildingTrs.getSurecId() != null && buildingTrs.getSurecId() > 0) {
				
				hql += " AND trs.surecId=?";
				
				params.add(buildingTrs.getSurecId());
			}
				try {
				
				return ((List<AdresTrs>)super.readDBObjectByHQL(hql,params.toArray()));
			} catch (Exception ex) {
			
				System.out.println("Error on searching buildingAddressTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		
		return null;
	}

	public boolean saveBuildingTransaction(BinaTrs buildingTrs) {
		
		if (buildingTrs != null) {
			
			super.saveDBObject(buildingTrs);
			
			return true;
		}		
		return false;
	
	}

	public boolean saveBuildingTransactions(Collection<BinaTrs> buildingTrsList) {

		if (buildingTrsList != null && buildingTrsList.size() > 0) {
			
			super.saveDBObject(buildingTrsList);
			
			return true;
		}		
		return false;
	}
	
}