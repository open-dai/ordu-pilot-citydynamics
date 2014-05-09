package com.sampas.ortak.spatial.transaction.servis.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sampas.akos.common.exception.AkosException;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;
import com.sampas.akos.ortak.model.BagimsizKullanimSinif;
import com.sampas.akos.ortak.model.BilgiTipi;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.BinaCatiTur;
import com.sampas.akos.ortak.model.BinaNitelik;
import com.sampas.akos.ortak.model.BinaOrtakKullanim;
import com.sampas.akos.ortak.model.BinaParsel;
import com.sampas.akos.ortak.model.BinaTesisat;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.CaddeSokakTur;
import com.sampas.akos.ortak.model.DosemeTur;
import com.sampas.akos.ortak.model.GelismislikDurum;
import com.sampas.akos.ortak.model.HareketKod;
import com.sampas.akos.ortak.model.InsaatSinif;
import com.sampas.akos.ortak.model.InsaatTur;
import com.sampas.akos.ortak.model.IsitmaTur;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KonutDurum;
import com.sampas.akos.ortak.model.KonutTip;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.Mahalle;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.akos.ortak.model.OrtakKullanimTur;
import com.sampas.akos.ortak.model.SicakSuTeminTur;
import com.sampas.akos.ortak.model.Site;
import com.sampas.akos.ortak.model.TasiyiciSistem;
import com.sampas.akos.ortak.model.TesisatTur;
import com.sampas.akos.ortak.model.UretimKaynak;
import com.sampas.akos.ortak.model.YakitTur;
import com.sampas.akos.ortak.model.YapiAda;
import com.sampas.akos.ortak.model.YapiCepheTur;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.gis.ortak.servis.IGisOrtakServis;
import com.sampas.ortak.spatial.generator.servis.IIDGeneratorServis;
import com.sampas.ortak.spatial.servis.IOrtakSpatialServis;
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
import com.sampas.ortak.spatial.transaction.servis.ITransactionServis;


public class TransactionServis implements ITransactionServis {

	private ITransactionDAO transactionDAO;
	
	private IGisOrtakServis gisOrtakServis;
	
	private IIDGeneratorServis idGeneratorServis;
	
	private IOrtakSpatialServis ortakSpatialServis;
	
	private OrtakServis ortakServis;

	
	public boolean saveStreetObjectTransaction(List<CaddeSokak> streets, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status) {
	
		List<CaddeSokakTrs> streetTrs = convertStreetOrjListToStreetTrsList(streets, processId, taskId, objectID, processUser, transactionDate, status);
	
		Object[] trsStreetObjects = {streetTrs};
		
		return this.ortakSpatialServis.saveObjects(trsStreetObjects);
	}
	
	public boolean saveDistrictStreetCrossObjectsTransaction(List<MahalleCaddeSokak> districtStreets, Long processId, Long taskId, Long objectID, String processUser,	Date transactionDate, String status) {
		
		List<MahalleCaddeSokakTrs> districtStreetCrossTrss = convertDistStreetOrjListToDistStreetTrsList(districtStreets, processId, taskId, objectID, processUser,	transactionDate, status);
		
		Object[] trsDistStreetCrsObjects = {districtStreetCrossTrss};
		
		return ortakSpatialServis.saveObjects(trsDistStreetCrsObjects);
	}

	public boolean saveStreetAndDistCrossObjectsTransaction(CaddeSokak street, List<MahalleCaddeSokak> districtStreets, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status) {
		
		List<CaddeSokak> streets = new ArrayList<CaddeSokak>();
		
		streets.add(street);
		
		List<CaddeSokakTrs> streetTrsList = convertStreetOrjListToStreetTrsList(streets, processId, taskId, objectID, processUser, transactionDate, status);
		
		if (streetTrsList != null && streetTrsList.size() > 0) {
	
			List<MahalleCaddeSokakTrs> districtStreetCrossTrss = convertDistStreetOrjListToDistStreetTrsList(districtStreets, processId, taskId, streetTrsList.get(0).getId(), processUser, transactionDate, status);
	
			Object[] trsObjects = {streetTrsList, districtStreetCrossTrss};
			
			return getOrtakSpatialServis().saveObjects(trsObjects);
		} else {
			
			return false;
		}
	}
	
	public boolean saveStreetTrs(CaddeSokakTrs streetTrs) {
		
		Object[] trsObjects = {streetTrs};
		
		return this.ortakSpatialServis.saveObjects(trsObjects);
	}
	
	public boolean saveDistrictStreetTrs(MahalleCaddeSokakTrs districtStreetTrs) {

		return transactionDAO.saveDistrictStreetTransaction(districtStreetTrs);
	}
	
	public boolean saveDistrictStreetTrsList(List<MahalleCaddeSokakTrs> districtStreetTrsList) {

		return transactionDAO.saveDistrictStreetTransactions(districtStreetTrsList);
	}
	
	public boolean saveAddressTrs(AdresTrs addressTrs) {
		
		return transactionDAO.saveAddressTransaction(addressTrs);
	}
	
	public boolean saveAddressTrsList(List<AdresTrs> addressTrslist){
		
		return transactionDAO.saveAddressTransactions(addressTrslist);
	}
	
	public List<AdresTrs> readAllAddressTransactions(AdresTrs addressTrs) {
		
		return (this.transactionDAO.readAllAddressTransactions(addressTrs));
	}

	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrs(MahalleCaddeSokakTrs districtStreetTrs) {
		
		return (this.transactionDAO.readAllAddressTrsListByDistrictStreetTrs(districtStreetTrs));
	}
	
	public List<AdresTrs> readAllAddressTrsListByDistrictStreetTrsList(List<MahalleCaddeSokakTrs> districtStreetTrsList) {
		
		return (this.transactionDAO.readAllAddressTrsListByDistrictStreetTrsList(districtStreetTrsList));
	}

	public List<MahalleCaddeSokakTrs> readAllDistrictStreetTransactions(MahalleCaddeSokakTrs distStrTrs) {
		
		return (this.transactionDAO.readAllDistrictStreetTransactions(distStrTrs));
	}
	
	public List<CaddeSokakTrs> readAllStreetTransactions(CaddeSokakTrs streetTrs) {
		
		return transactionDAO.readAllStreetTransactions(streetTrs);		
	}
	
	public boolean deleteAddressTransaction(AdresTrs addressTrs) {

		return (this.transactionDAO.deleteAddressTransaction(addressTrs));
	}
	
	public boolean deleteAddressTransactions(Collection<AdresTrs> addressTrsCollection) {
		
		return (this.transactionDAO.deleteAddressTransactions(addressTrsCollection));
	}
	
	public boolean deleteDistrictStreetTransaction(MahalleCaddeSokakTrs distStrTrs) {

		return (this.transactionDAO.deleteDistrictStreetTransaction(distStrTrs));
	}

	public boolean deleteDistrictStreetTransactions(Collection<MahalleCaddeSokakTrs> distStrTrsColl) {

		return (this.transactionDAO.deleteDistrictStreetTransactions(distStrTrsColl));
	}

	public List<CaddeSokakTrs> convertStreetOrjListToStreetTrsList(List<CaddeSokak> streetOrjList, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status) {

		if (streetOrjList != null && streetOrjList.size() > 0) {
		
			List<CaddeSokakTrs> resultStreetTrsList = new ArrayList<CaddeSokakTrs>();
			
			for (CaddeSokak tempStreetOrj : streetOrjList) {
				
				resultStreetTrsList.add(convertStreetOrjToStreetTrs(tempStreetOrj, processId, taskId, objectID, processUser, transactionDate, status));
			}
			return resultStreetTrsList;
		}
		return null;
	}

	public CaddeSokakTrs convertStreetOrjToStreetTrs(CaddeSokak streetOrj, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status) {
		
		try {
			
			if (streetOrj != null) {
			
				CaddeSokakTrs resultStreetTrs = new CaddeSokakTrs();
				
				resultStreetTrs.setId(getIdGeneratorServis().getIDForNewObject(resultStreetTrs.getClass()));
				
				if (streetOrj.getAciklama() != null) {
					
					resultStreetTrs.setAciklama(streetOrj.getAciklama());
				}
				if (streetOrj.getAcilisTarih() != null) {
					
					resultStreetTrs.setAcilisTarih(streetOrj.getAcilisTarih());
				}
				if (streetOrj.getAkosDegisimTarihi() != null) {
					
					resultStreetTrs.setAkosDegisimTarihi(streetOrj.getAkosDegisimTarihi());
				}
				if (streetOrj.getAkosDegisti() != null) {
					
					resultStreetTrs.setAkosDegisti(streetOrj.getAkosDegisti());
				}
				if (streetOrj.getAkosDegistiren() != null) {
					
					resultStreetTrs.setAkosDegistiren(streetOrj.getAkosDegistiren());
				}
				if (streetOrj.getAktifEh() != null) {
					
					resultStreetTrs.setAktifEh(streetOrj.getAktifEh());
				}
				if (streetOrj.getCaddeSokakTur() != null) {
					
					resultStreetTrs.setCaddeSokakTurId(streetOrj.getCaddeSokakTur().getId());
				}
				if (streetOrj.getCiftKapiBaslangicNo() != null) {
					
					resultStreetTrs.setCiftKapiBaslangicNo(streetOrj.getCiftKapiBaslangicNo());
				}
				if (streetOrj.getCiftKapiBitisNo() != null) {
					
					resultStreetTrs.setCiftKapiBitisNo(streetOrj.getCiftKapiBitisNo());
				}
				if (streetOrj.getCiftKapiBitisNo() != null) {
					
					resultStreetTrs.setCiftKapiBitisNo(streetOrj.getCiftKapiBitisNo());
				}
				if (streetOrj.getKapanisTarih() != null) {
					
					resultStreetTrs.setKapanisTarih(streetOrj.getKapanisTarih());
				}
				if (streetOrj.getKaydeden() != null) {
					
					resultStreetTrs.setKaydeden(streetOrj.getKaydeden());
				}
				if (streetOrj.getKayitTarih() != null) {
					
					resultStreetTrs.setKayitTarih(streetOrj.getKayitTarih());
				}
				if (streetOrj.getKurumSabit() != null) {
					
					resultStreetTrs.setKurumSabitId(streetOrj.getKurumSabit().getId());
				}
				if (streetOrj.getMeclisKararNo() != null) {
					
					resultStreetTrs.setMeclisKararNo(streetOrj.getMeclisKararNo());
				}
				if (streetOrj.getMeclisKararTarih() != null) {
					
					resultStreetTrs.setMeclisKararTarih(streetOrj.getMeclisKararTarih());
				}
				if (streetOrj.getTekKapiBaslangicNo() != null) {
					
					resultStreetTrs.setTekKapiBaslangicNo(streetOrj.getTekKapiBaslangicNo());
				}
				if (streetOrj.getTekKapiBitisNo() != null) {
					
					resultStreetTrs.setTekKapiBitisNo(streetOrj.getTekKapiBitisNo());
				}
				// Set transaction record need objects
				if (processId != null) {
					
					resultStreetTrs.setSurecId(processId);
				}
				if (taskId != null) {
					
					resultStreetTrs.setTaskId(taskId);
				}
				if (objectID != null && objectID > 0) {
					
					resultStreetTrs.setNesneId(objectID);
				}
				if (processUser != null) {
					
					resultStreetTrs.setIslemiYapan(processUser);
				}
				if (transactionDate != null) {
					
					resultStreetTrs.setIslemTarihi(transactionDate);
				}
				if (status != null) {
					
					resultStreetTrs.setOnayDurumu(status);
				}
				if (status != null) {
					
					resultStreetTrs.setOnayDurumu(status);
				}
				return resultStreetTrs;
			}
		} catch (Exception ex) {
			
			System.out.println("Error on converting Street Orj to Street Trs ! ERROR: " + ex);
		}
		return null;
	}
	
	public List<MahalleCaddeSokakTrs> convertDistStreetOrjListToDistStreetTrsList(List<MahalleCaddeSokak> districtStreetOrjList, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status) {
	
		if (districtStreetOrjList != null && districtStreetOrjList.size() > 0) {
			
			List<MahalleCaddeSokakTrs> resultDistrictStreetTrsList = new ArrayList<MahalleCaddeSokakTrs>();
			
			for (MahalleCaddeSokak tempDistrictStreetOrj : districtStreetOrjList) {
				
				resultDistrictStreetTrsList.add(convertDistStreetOrjToDistStreetTrs(tempDistrictStreetOrj, processId, taskId, objectID, processUser, transactionDate, status));
			}
			return resultDistrictStreetTrsList;
		}
		return null;
	}

	public MahalleCaddeSokakTrs convertDistStreetOrjToDistStreetTrs(MahalleCaddeSokak districtStreetOrj, Long processId, Long taskId, Long objectID, String processUser, Date transactionDate, String status) {
		
		try {
			
			if (districtStreetOrj != null) {
				
				MahalleCaddeSokakTrs resultStreetDistrictTrs = new MahalleCaddeSokakTrs();
				
				resultStreetDistrictTrs.setId(getIdGeneratorServis().getIDForNewObject(resultStreetDistrictTrs.getClass()));
				
				if (districtStreetOrj.getAkosDegisimTarihi() != null) {
					
					resultStreetDistrictTrs.setAkosDegisimTarihi(districtStreetOrj.getAkosDegisimTarihi());
				}
				if (districtStreetOrj.getAkosDegisti() != null) {
					
					resultStreetDistrictTrs.setAkosDegisti(districtStreetOrj.getAkosDegisti());
				}
				if (districtStreetOrj.getAkosDegistiren() != null) {
					
					resultStreetDistrictTrs.setAkosDegistiren(districtStreetOrj.getAkosDegistiren());
				}
				if (districtStreetOrj.getAktifEh() != null) {
					
					resultStreetDistrictTrs.setAktifEh(districtStreetOrj.getAktifEh());
				}
				if (districtStreetOrj.getCaddeSokak() != null) {
					
					resultStreetDistrictTrs.setNesneId(districtStreetOrj.getCaddeSokak().getId());
					//resultStreetDistrictTrs.setCaddeSokakId(districtStreetOrj.getCaddeSokak().getId());
				}
				if (districtStreetOrj.getCtvDerece() != null) {
					
					resultStreetDistrictTrs.setCtvDerece(districtStreetOrj.getCtvDerece());
				}
				if (districtStreetOrj.getCtvGrup() != null) {
					
					resultStreetDistrictTrs.setCtvGrup(districtStreetOrj.getCtvGrup());
				}
				if (districtStreetOrj.getGelismislikDurum() != null) {
					
					resultStreetDistrictTrs.setGelismislikDurumId(districtStreetOrj.getGelismislikDurum().getId());
				}
				if (districtStreetOrj.getKaydeden() != null) {
					
					resultStreetDistrictTrs.setKaydeden(districtStreetOrj.getKaydeden());
				}
				if (districtStreetOrj.getKayitTarih() != null) {
					
					resultStreetDistrictTrs.setKayitTarih(districtStreetOrj.getKayitTarih());
				}
				if (districtStreetOrj.getMahalle() != null) {
					
					resultStreetDistrictTrs.setMahalleId(districtStreetOrj.getMahalle().getId());;
				}
				if (districtStreetOrj.getTuikCaddeSokakKod() != null) {
					
					resultStreetDistrictTrs.setTuikCaddeSokakKod(districtStreetOrj.getTuikCaddeSokakKod());;
				}
				if (districtStreetOrj.getTuikCaddeSokakTanitimNo() != null) {
					
					resultStreetDistrictTrs.setTuikCaddeSokakTanitimNo(districtStreetOrj.getTuikCaddeSokakTanitimNo());;
				}
				// Set transaction record need objects
				if (processId != null) {
					
					resultStreetDistrictTrs.setSurecId(processId);
				}
				if (taskId != null) {
					
					resultStreetDistrictTrs.setTaskId(taskId);
				}
				if (objectID != null && objectID > 0) {
					
					resultStreetDistrictTrs.setCaddeSokakId(objectID);
					//resultStreetDistrictTrs.setNesneId(objectID);
				}
				if (processUser != null) {
					
					resultStreetDistrictTrs.setIslemiYapan(processUser);
				}
				if (transactionDate != null) {
					
					resultStreetDistrictTrs.setIslemTarihi(transactionDate);
				}
				if (status != null) {
					
					resultStreetDistrictTrs.setOnayDurumu(status);
				}
				return resultStreetDistrictTrs;
			}
		}catch (Exception ex) {
			
			System.out.println("Error on converting Street Orj to Street Trs ! ERROR: " + ex);
		}
		return null;
	}
	
	public List<CaddeSokak> convertStreetTrsListToStreetOrjList(List<CaddeSokakTrs> streetTrsList) {
		
		if (streetTrsList != null && streetTrsList.size() > 0) {

			List<CaddeSokak> resultStreetList = new ArrayList<CaddeSokak>();

			for (CaddeSokakTrs tempStreetTrs : streetTrsList) {
				
				resultStreetList.add(convertStreetTrsToStreetOrj(tempStreetTrs));
			}
			return resultStreetList;
		}
		return null;
	}
	
	public CaddeSokak convertStreetTrsToStreetOrj(CaddeSokakTrs streetTrs) {
		
		try {
			
			if (streetTrs != null && streetTrs.getKurumSabitId() != null && streetTrs.getKurumSabitId() > 0) {
				
				CaddeSokak resultStreetOrj = new CaddeSokak();
					
				KurumSabit companyConst = new KurumSabit();
				
				companyConst.setId(streetTrs.getKurumSabitId());
				
				try {
					
					companyConst = getOrtakServis().readAllKurumSabitByCriteria(companyConst).get(0);
					
					if (companyConst != null) {
						
						resultStreetOrj.setKurumSabit(companyConst);
					} else {
						
						return null;
					}
				} catch(Exception ex) {
					
					System.out.println("Error on getting Company Constraint ! ERROR: " + ex );
					
					return null;
				}
				resultStreetOrj.setId(getIdGeneratorServis().getIDForNewObject(resultStreetOrj.getClass()));
				
				if (streetTrs.getAciklama() != null && streetTrs.getAciklama() != "") {
					
					resultStreetOrj.setAciklama(streetTrs.getAciklama());
				}
				if (streetTrs.getAcilisTarih() != null) {
					
					resultStreetOrj.setAcilisTarih(streetTrs.getAcilisTarih());
				}
				if (streetTrs.getAkosDegisimTarihi() != null) {
					
					resultStreetOrj.setAkosDegisimTarihi(streetTrs.getAkosDegisimTarihi());
				}
				if (streetTrs.getAkosDegisti() != null) {
					
					resultStreetOrj.setAkosDegisti(streetTrs.getAkosDegisti());
				}
				if (streetTrs.getAkosDegistiren() != null) {
					
					resultStreetOrj.setAkosDegistiren(streetTrs.getAkosDegistiren());
				}
				if (streetTrs.getAktifEh() != null) {
					
					resultStreetOrj.setAktifEh(streetTrs.getAktifEh());
				}
				if (streetTrs.getCaddeSokakTurId() != null) {
					
					List<CaddeSokakTur> streetTypeList = getOrtakServis().readAllCaddeSokakTur();
					
					CaddeSokakTur streetType = null;
					
					for (CaddeSokakTur tempStreetType : streetTypeList) {
						
						if (tempStreetType.getId().equals(streetTrs.getCaddeSokakTurId())) {
							
							streetType = tempStreetType;
						}
					}
					resultStreetOrj.setCaddeSokakTur(streetType);
				}
				if (streetTrs.getCiftKapiBaslangicNo() != null) {
					
					resultStreetOrj.setCiftKapiBaslangicNo(streetTrs.getCiftKapiBaslangicNo());
				}
				if (streetTrs.getCiftKapiBitisNo() != null) {
					
					resultStreetOrj.setCiftKapiBitisNo(streetTrs.getCiftKapiBitisNo());
				}
				if (streetTrs.getKapanisTarih() != null) {
					
					resultStreetOrj.setKapanisTarih(streetTrs.getKapanisTarih());
				}
				if (streetTrs.getKaydeden() != null) {
					
					resultStreetOrj.setKaydeden(streetTrs.getKaydeden());
				}
				if (streetTrs.getKayitTarih() != null) {
					
					resultStreetOrj.setKayitTarih(streetTrs.getKayitTarih());
				}
				if (streetTrs.getMeclisKararNo() != null) {
					
					resultStreetOrj.setMeclisKararNo(streetTrs.getMeclisKararNo());
				}
				if (streetTrs.getMeclisKararTarih() != null) {
					
					resultStreetOrj.setMeclisKararTarih(streetTrs.getMeclisKararTarih());
				}
				if (streetTrs.getTekKapiBaslangicNo() != null) {
					
					resultStreetOrj.setTekKapiBaslangicNo(streetTrs.getTekKapiBaslangicNo());
				}
				if (streetTrs.getTekKapiBitisNo() != null) {
					
					resultStreetOrj.setTekKapiBitisNo(streetTrs.getTekKapiBitisNo());
				}
				return resultStreetOrj;
			}		
		} catch (Exception ex) {

			System.out.println("Error on converting Street Trs to Street ! ERROR: " + ex);
		}
		return null;
	}
	
	public List<MahalleCaddeSokak> convertDistStreetTrsListToDistStreetOrjList(List<MahalleCaddeSokakTrs> distStreetTrsList, KurumSabit compConst) {
			
		if (distStreetTrsList != null && distStreetTrsList.size() > 0) {
		
			List<MahalleCaddeSokak> resultDistStreetList = new ArrayList<MahalleCaddeSokak>();
			
			for (MahalleCaddeSokakTrs tempDistStreetTrs : distStreetTrsList) {
				
				resultDistStreetList.add(convertDistStreetTrsToDistStreetOrj(tempDistStreetTrs, compConst));
			}
			return resultDistStreetList;
		}
		return null;
	}
	
	public MahalleCaddeSokak convertDistStreetTrsToDistStreetOrj(MahalleCaddeSokakTrs distStreetTrs, KurumSabit compConst) {
		//TODO:CHECK
		try {
			
			if (distStreetTrs != null) {
				
				MahalleCaddeSokak resultDistStreet = new MahalleCaddeSokak();
				
				resultDistStreet.setId(getIdGeneratorServis().getIDForNewObject(resultDistStreet.getClass()));
				
				if (distStreetTrs.getAkosDegisimTarihi() != null) {
					
					resultDistStreet.setAkosDegisimTarihi(distStreetTrs.getAkosDegisimTarihi());
				}
				if (distStreetTrs.getAkosDegisti() != null) {
					
					resultDistStreet.setAkosDegisti(distStreetTrs.getAkosDegisti());
				}
				if (distStreetTrs.getAkosDegistiren() != null) {
					
					resultDistStreet.setAkosDegistiren(distStreetTrs.getAkosDegistiren());
				}
				if (distStreetTrs.getAktifEh() != null) {
					
					resultDistStreet.setAktifEh(distStreetTrs.getAktifEh());
				}
				if (distStreetTrs.getAktifEh() != null) {
					
					resultDistStreet.setAktifEh(distStreetTrs.getAktifEh());
				}
				if (distStreetTrs.getCaddeSokakId() != null) {
					
					CaddeSokak tempStreet = new CaddeSokak();
					
					tempStreet.setId(distStreetTrs.getCaddeSokakId());
					
					try{
						
						tempStreet = getOrtakServis().readAllCaddeSokakByCriteria(tempStreet, compConst).get(0);
						
						if (tempStreet != null) {
							
							resultDistStreet.setCaddeSokak(tempStreet);
						}
					} catch(Exception ex) {
						
						System.out.println("Error on getting Company Constraint ! ERROR: " + ex );
					}
				}
				if (distStreetTrs.getCtvDerece() != null) {
					
					resultDistStreet.setCtvDerece(distStreetTrs.getCtvDerece());
				}
				if (distStreetTrs.getCtvGrup() != null) {
					
					resultDistStreet.setCtvGrup(distStreetTrs.getCtvGrup());
				}
				if (distStreetTrs.getGelismislikDurumId() != null) {
					
					try {
						
						GelismislikDurum developedStatue = new GelismislikDurum();
						
						developedStatue.setId(distStreetTrs.getGelismislikDurumId());
						
						developedStatue = getOrtakServis().readAllGelismislikDurumByCriteria(developedStatue).get(0);
						
						resultDistStreet.setGelismislikDurum(developedStatue);
					} catch (Exception ex) {
						
						System.out.println("Error on getting Developed Statue ! ERROR: " + ex);
					}
				}
				if (distStreetTrs.getKaydeden() != null) {
					
					resultDistStreet.setKaydeden(distStreetTrs.getKaydeden());
				}
				if (distStreetTrs.getKayitTarih() != null) {
					
					resultDistStreet.setKayitTarih(distStreetTrs.getKayitTarih());
				}
				if (distStreetTrs.getMahalleId() != null) {
					
					Mahalle tempDistrict = new Mahalle();
					
					tempDistrict.setId(distStreetTrs.getMahalleId());
					
					try {
						
						tempDistrict = getOrtakServis().readAllMahalleByCriteria(tempDistrict, compConst).get(0);
						
						if (tempDistrict != null) {
						
							resultDistStreet.setMahalle(tempDistrict);
						}
					} catch (Exception ex) {
						
						System.out.println("Error on getting DistrictStreet ! ERROR : " + ex);
					}
				}
				if (distStreetTrs.getTuikCaddeSokakKod() != null) {
					
					resultDistStreet.setTuikCaddeSokakKod(distStreetTrs.getTuikCaddeSokakKod());
				}
				if (distStreetTrs.getTuikCaddeSokakTanitimNo() != null) {
					
					resultDistStreet.setTuikCaddeSokakTanitimNo(distStreetTrs.getTuikCaddeSokakTanitimNo());
				}
				if (distStreetTrs.getTuikCaddeSokakTanitimNo() != null) {
					
					resultDistStreet.setTuikCaddeSokakTanitimNo(distStreetTrs.getTuikCaddeSokakTanitimNo());
				}
				return resultDistStreet;
			}
		} catch (Exception ex) {
	
			System.out.println("Error on converting DistrictStreetTrs to  DistrictStreet Orj ! ERROR: " + ex);
		}
		return null;
	}
	
	public List<Adres> convertAddressTrsListToAddressOrjList(List<AdresTrs> addressTrsList) {
		
		if (addressTrsList != null && addressTrsList.size() > 0) {
			
			List<Adres> resultAddressList = new ArrayList<Adres>();
			
			for (AdresTrs tempAddresstTrs : addressTrsList) {
				
				resultAddressList.add(convertAddressTrsToAddressOrj(tempAddresstTrs));
			}
			return resultAddressList;
		}
		return null;
	}
	
	public Adres convertAddressTrsToAddressOrj(AdresTrs addressTrs) {

		try {
			
			if (addressTrs != null && addressTrs.getKurumSabitId() != null && addressTrs.getKurumSabitId() > 0) {
				
				Adres resultAddress = new Adres();

				KurumSabit companyConst = new KurumSabit();
				
				companyConst.setId(addressTrs.getKurumSabitId());
				
				try {
				
					companyConst = getOrtakServis().readAllKurumSabitByCriteria(companyConst).get(0);
					
					if (companyConst != null) {
						
						resultAddress.setKurumSabit(companyConst);
					} else {
						
						return null;
					}
				} catch (Exception ex) {
					
					System.out.println("Error on getting Company Contstraint ! ERROR: " + ex);
					
					return null;
				}
				resultAddress.setId(getIdGeneratorServis().getIDForNewObject(resultAddress.getClass()));
				
				if (addressTrs.getAciklama() != null) {
					
					resultAddress.setAciklama(addressTrs.getAciklama());
				}
				if (addressTrs.getAdresNo() != null) {
					
					resultAddress.setAdresNo(addressTrs.getAdresNo());
				}
				if (addressTrs.getAdresTur() != null) {
					
					resultAddress.setAdresTur(addressTrs.getAdresTur());						
				}
				if (addressTrs.getAkosDegisimTarihi() != null) {
					
					resultAddress.setAkosDegisimTarihi(addressTrs.getAkosDegisimTarihi());
				}
				if (addressTrs.getAkosDegisti() != null) {
					
					resultAddress.setAkosDegisti(addressTrs.getAkosDegisti());
				}
				if (addressTrs.getAkosDegistiren() != null) {
					
					resultAddress.setAkosDegistiren(addressTrs.getAkosDegistiren());
				}
				if (addressTrs.getAktifEh() != null) {
					
					resultAddress.setAktifEh(addressTrs.getAktifEh());
				}
				if (addressTrs.getKapiNo() != null) {
					
					resultAddress.setKapiNo(addressTrs.getKapiNo());
				}
				if (addressTrs.getAltKapiNo() != null) {
					
					resultAddress.setAltKapiNo(addressTrs.getAltKapiNo());
				}
				if (addressTrs.getKaydeden() != null) {
					
					resultAddress.setKaydeden(addressTrs.getKaydeden());
				}
				if (addressTrs.getKayitTarih() != null) {
					
					resultAddress.setKayitTarih(addressTrs.getKayitTarih());
				}
				if (addressTrs.getMahalleCaddeId() != null) {

					MahalleCaddeSokak distStreetOrj = new MahalleCaddeSokak();
					
					distStreetOrj.setId(addressTrs.getMahalleCaddeId());
					
					distStreetOrj = getOrtakServis().readMahalleCaddeSokakByCriteria(distStreetOrj, companyConst);
					
					resultAddress.setMahalleCaddeSokak(distStreetOrj);
				}
				if (addressTrs.getPdaEh() != null) {
					
					resultAddress.setPdaEh(addressTrs.getPdaEh());
				}
				if (addressTrs.getPdaIslemTarihi() != null) {
					
					resultAddress.setPdaIslemTarihi(addressTrs.getPdaIslemTarihi());
				}
				if (addressTrs.getPdaUserName() != null) {
					
					resultAddress.setPdaUserName(addressTrs.getPdaUserName());
				}
				return resultAddress;
			}
		} catch (Exception ex) {
	
			System.out.println("Error on converting Address Trs to  Address Orj ! ERROR: " + ex);
		}
		return null;
	}
	
	
	//TODO: Getter Setter Separator
	
	public IGisOrtakServis getGisOrtakServis() {
		return gisOrtakServis;
	}

	public void setGisOrtakServis(IGisOrtakServis gisOrtakServis) {
		this.gisOrtakServis = gisOrtakServis;
	}

	public ITransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(ITransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	public IIDGeneratorServis getIdGeneratorServis() {
		return idGeneratorServis;
	}

	public void setIdGeneratorServis(IIDGeneratorServis idGeneratorServis) {
		this.idGeneratorServis = idGeneratorServis;
	}

	
	//TODO: Getters and Setters Separator
	
	public IOrtakSpatialServis getOrtakSpatialServis() {
		return ortakSpatialServis;
	}

	public void setOrtakSpatialServis(IOrtakSpatialServis ortakSpatialServis) {
		this.ortakSpatialServis = ortakSpatialServis;
	}

	public OrtakServis getOrtakServis() {
		return ortakServis;
	}

	public void setOrtakServis(OrtakServis ortakServis) {
		this.ortakServis = ortakServis;
	}

	public List<BinaTrs> readAllBuildingTransactions(BinaTrs buildingTrs) {
		return transactionDAO.readAllBuildingTransactions(buildingTrs); 
	}

	public List<BinaNitelikTrs> readAllBuildingAttributesByCriteria(
			BinaTrs buildingTrs) {
		return transactionDAO.readAllBuildingAttributesByCriteria(buildingTrs);
	}

	public List<BinaTesisatTrs> readAllBuildingFittingsByCriteria(
			BinaTrs buildingTrs) {
		return transactionDAO.readAllBuildingFittingsByCriteria(buildingTrs);
	}

	public List<BinaOrtakKullanimTrs> readAllBuildingCommonUsagesByCriteria(
			BinaTrs buildingTrs) {
		return transactionDAO.readAllBuildingCommonUsagesByCriteria(buildingTrs);
	}

	public List<BagimsizTrs> readAllBuildingDetachedsByCriteria(
			BinaTrs buildingTrs) {
		return transactionDAO.readAllBuildingDetachedsByCriteria(buildingTrs);
	}

	public List<BinaParselTrs> readAllBuildingParcelByCriteria(
			BinaTrs buildingTrs) {
		return transactionDAO.readAllBuildingParcelByCriteria(buildingTrs);
	}

	public List<AdresTrs> readAllBuildingAddressByCriteria(BinaTrs buildingTrs) {
		return transactionDAO.readAllBuildingAddressByCriteria(buildingTrs);
	}

	public Bina convertBuildingTrsToBuildingOrj(BinaTrs buildingTrs) throws AkosException {
		
		Bina building=new Bina();
		
		Long binaNo=getIdGeneratorServis().getIDForNewObject(Bina.class);
		
		building.setId(binaNo);
		
		building.setBinaNo(binaNo);
		
		
		KurumSabit companyConst = new KurumSabit();
		
		companyConst.setId(buildingTrs.getKurumSabitId());

		companyConst = getOrtakServis().readAllKurumSabitByCriteria(companyConst).get(0);
		
		if (companyConst != null) {
			
			building.setKurumSabit(companyConst);
			
		} else {

			throw new AkosException(String.format("No Company defined for CompanyId={0}" , buildingTrs.getKurumSabitId()),null);
			
		}
				

		if (buildingTrs.getYapiAdaId()!=null && buildingTrs.getYapiAdaId().longValue()>0){
			
			YapiAda buidingIsland=(YapiAda)getOrtakServis().readObjectById(YapiAda.class, buildingTrs.getYapiAdaId());
			
			if (buidingIsland!=null){
				
				building.setYapiAda(buidingIsland);
			}else{
			
				throw new AkosException(String.format("No object found for class (YapiAda Id={0})",buildingTrs.getYapiAdaId()),null);
			}
		}

		
		if (buildingTrs.getSiteId()!=null && buildingTrs.getSiteId().longValue()>0){
			
			Site site=(Site)getOrtakServis().readObjectById(Site.class, buildingTrs.getSiteId());
			
			if (site!=null){
				
				building.setSite(site);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (Site Id={0})",buildingTrs.getSiteId()),null);
			}

		}
			
		
		building.setApartmanBlokAd(buildingTrs.getApartmanBlokAd());
		
		building.setPostaKodu(buildingTrs.getPostaKodu());
		
		if (buildingTrs.getInsaatTurId()!=null && buildingTrs.getInsaatTurId().longValue()>0){
			
			InsaatTur constructionType=(InsaatTur)getOrtakServis().readObjectById(InsaatTur.class, buildingTrs.getInsaatTurId());
			
			if (constructionType!=null){
				
				building.setInsaatTur(constructionType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (InsaatTur Id={0})",buildingTrs.getInsaatTurId()),null);
			}
			
		}
		
		if (buildingTrs.getInsaatSinifId()!=null && buildingTrs.getInsaatSinifId().longValue()>0){
			
			InsaatSinif constructionClass=(InsaatSinif)getOrtakServis().readObjectById(InsaatSinif.class, buildingTrs.getInsaatSinifId());
			
			if (constructionClass!=null){
				
				building.setInsaatSinif(constructionClass);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (InsaatSinif Id={0})",buildingTrs.getInsaatSinifId()),null);
			}

		}
		
		if (buildingTrs.getIsitmaTurId()!=null && buildingTrs.getIsitmaTurId().longValue()>0){
			
			IsitmaTur heatingType=(IsitmaTur)getOrtakServis().readObjectById(IsitmaTur.class, buildingTrs.getIsitmaTurId());
			
			if (heatingType!=null){
				
				building.setIsitmaTur(heatingType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (IsitmaTur Id={0})",buildingTrs.getIsitmaTurId()),null);
			}

		}
		
		
		if (buildingTrs.getIsitmaYakitTurId()!=null && buildingTrs.getIsitmaYakitTurId().longValue()>0){
			
			YakitTur heatingFuelType=(YakitTur)getOrtakServis().readObjectById(YakitTur.class, buildingTrs.getIsitmaYakitTurId());
			
			if (heatingFuelType!=null){
				
				building.setIsitmaYakitTur(heatingFuelType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (YakitTur Id={0})",buildingTrs.getIsitmaYakitTurId()),null);
			}

		}
		
		
		if (buildingTrs.getSicakSuTeminTurId()!=null && buildingTrs.getSicakSuTeminTurId().longValue()>0){
			
			SicakSuTeminTur waterHeatingType=(SicakSuTeminTur)getOrtakServis().readObjectById(SicakSuTeminTur.class, buildingTrs.getSicakSuTeminTurId());
			
			if (waterHeatingType!=null){
				
				building.setSicakSuTeminTur(waterHeatingType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (SicakSuTeminTur Id={0})",buildingTrs.getSicakSuTeminTurId()),null);
			}

		}
		
		
		if (buildingTrs.getSicakSuYakitId()!=null && buildingTrs.getSicakSuYakitId().longValue()>0){
			
			YakitTur waterHeatingFuelType=(YakitTur)getOrtakServis().readObjectById(YakitTur.class, buildingTrs.getSicakSuYakitId());
			
			if (waterHeatingFuelType!=null){
				
				building.setSicakSuYakit(waterHeatingFuelType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (YakitTur Id={0})",buildingTrs.getSicakSuYakitId()),null);
			}

		}
		
		if (buildingTrs.getTasiyiciSistemId()!=null && buildingTrs.getTasiyiciSistemId().longValue()>0){
			
			TasiyiciSistem carriageSystem=(TasiyiciSistem)getOrtakServis().readObjectById(TasiyiciSistem.class, buildingTrs.getTasiyiciSistemId());
			
			if (carriageSystem!=null){
				
				building.setTasiyiciSistem(carriageSystem);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (TasiyiciSistem Id={0})",buildingTrs.getTasiyiciSistemId()),null);
			}

		}
		
		if (buildingTrs.getDosemeTurId()!=null && buildingTrs.getDosemeTurId().longValue()>0){
			
			DosemeTur floorCoveringType=(DosemeTur)getOrtakServis().readObjectById(DosemeTur.class, buildingTrs.getDosemeTurId());
			
			if (floorCoveringType!=null){
				
				building.setDosemeTur(floorCoveringType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (DosemeTur Id={0})",buildingTrs.getDosemeTurId()),null);
			}
		}
		
		if (buildingTrs.getBagimsizKullanimSinifId()!=null && buildingTrs.getBagimsizKullanimSinifId().longValue()>0){
			
			BagimsizKullanimSinif detachedUsageClass=(BagimsizKullanimSinif)getOrtakServis().readObjectById(BagimsizKullanimSinif.class, buildingTrs.getBagimsizKullanimSinifId());
			
			if (detachedUsageClass!=null){
				
				building.setBagimsizKullanimSinif(detachedUsageClass);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (BagimsizKullanimSinif Id={0})",buildingTrs.getBagimsizKullanimSinifId()),null);
			}
		}
		
		
		if (buildingTrs.getYapiCepheTurId()!=null && buildingTrs.getYapiCepheTurId().longValue()>0){
			
			YapiCepheTur buildingFacadeType=(YapiCepheTur)getOrtakServis().readObjectById(YapiCepheTur.class, buildingTrs.getYapiCepheTurId());
			
			if (buildingFacadeType!=null){
				
				building.setYapiCepheTur(buildingFacadeType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (YapiCepheTur Id={0})",buildingTrs.getYapiCepheTurId()),null);
			}
		}
		
		building.setAsansorEh(buildingTrs.getAsansorEh());
		
		building.setKaloriferEh(buildingTrs.getKaloriferEh());
		
		building.setArsaAlan(buildingTrs.getArsaAlan());
		
		building.setZoneNo(buildingTrs.getZoneNo());
		
		if (buildingTrs.getBinaCatiTurId()!=null && buildingTrs.getBinaCatiTurId().longValue()>0){
			
			BinaCatiTur buildingRoofType=(BinaCatiTur)getOrtakServis().readObjectById(BinaCatiTur.class, buildingTrs.getBinaCatiTurId());
			
			if (buildingRoofType!=null){
				
				building.setBinaCatiTur(buildingRoofType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (BinaCatiTur Id={0})",buildingTrs.getBinaCatiTurId()),null);
			}
		}
		
		if (buildingTrs.getBilgiTipiId()!=null && buildingTrs.getBilgiTipiId().longValue()>0){
			
			BilgiTipi infoGatheringType=(BilgiTipi)getOrtakServis().readObjectById(BilgiTipi.class, buildingTrs.getBilgiTipiId());
			
			if (infoGatheringType!=null){
				
				building.setBilgiTipi(infoGatheringType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (BilgiTipi Id={0})",buildingTrs.getBilgiTipiId()),null);
			}
		}
		
		
		if (buildingTrs.getUretimKaynakId()!=null && buildingTrs.getUretimKaynakId().longValue()>0){
			
			UretimKaynak graphicDataProductionType=(UretimKaynak)getOrtakServis().readObjectById(UretimKaynak.class, buildingTrs.getUretimKaynakId());
			
			if (graphicDataProductionType!=null){
				
				building.setUretimKaynak(graphicDataProductionType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (UretimKaynak Id={0})",buildingTrs.getUretimKaynakId()),null);
			}
		}		
		
		building.setPdaEh(buildingTrs.getPdaEh());
		
		building.setPdaUserName(buildingTrs.getPdaUserName());
		
		building.setPdaIslemTarihi(buildingTrs.getPdaIslemTarihi());

		building.setAktifEh(buildingTrs.getAktifEh());
		
		building.setKayitTarih(buildingTrs.getKayitTarih());
		
		building.setKaydeden(buildingTrs.getKaydeden());
		
		building.setAcilisTarih(buildingTrs.getAcilisTarih());
		
		building.setKapanisTarih(buildingTrs.getKapanisTarih());
		
		if (buildingTrs.getAcilisHareketKodId()!=null && buildingTrs.getAcilisHareketKodId().longValue()>0){
			
			HareketKod openningOperationType=(HareketKod)getOrtakServis().readObjectById(HareketKod.class, buildingTrs.getAcilisHareketKodId());
			
			if (openningOperationType!=null){
				
				building.setAcilisHareketKod(openningOperationType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (HareketKod Id={0})",buildingTrs.getAcilisHareketKodId()),null);
			}
		}	
		
		if (buildingTrs.getKapanisHareketKodId()!=null && buildingTrs.getKapanisHareketKodId().longValue()>0){
			
			HareketKod closingOperationType=(HareketKod)getOrtakServis().readObjectById(HareketKod.class, buildingTrs.getKapanisHareketKodId());
			
			if (closingOperationType!=null){
				
				building.setKapanisHareketKod(closingOperationType);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (HareketKod Id={0})",buildingTrs.getKapanisHareketKodId()),null);
			}
		}	
		
		if (buildingTrs.getKadastroParselId()!=null && buildingTrs.getKadastroParselId().longValue()>0){
			
			KadastroParsel parcel=(KadastroParsel)getOrtakServis().readObjectById(KadastroParsel.class, buildingTrs.getKadastroParselId());
			
			if (parcel!=null){
				
				building.setKadastroParsel(parcel);
				
			}else{
				
				throw new AkosException(String.format("No object found for class (KadastroParsel Id={0})",buildingTrs.getKadastroParselId()),null);
			}
		}	
		
		return building;
	}

	public List<BinaOrtakKullanim> convertBuildingCommonUsageTrsListToOrjList(
			List<BinaOrtakKullanimTrs> buildingCommonUsageTrsList)
			throws AkosException {

		List<BinaOrtakKullanim> buildingCommonUsageList=new ArrayList<BinaOrtakKullanim>();
		
		for (BinaOrtakKullanimTrs buildingCommonUsageTrs : buildingCommonUsageTrsList) {
		
			long commonUsageId=getIdGeneratorServis().getIDForNewObject(BinaOrtakKullanim.class);
			
			BinaOrtakKullanim buildingCommonUsage=new BinaOrtakKullanim();
			
			buildingCommonUsage.setId(commonUsageId);
			
			if (buildingCommonUsageTrs.getOrtakKullanimTurId()!=null && buildingCommonUsageTrs.getOrtakKullanimTurId().longValue()>0){
				
				OrtakKullanimTur commonUsageType=(OrtakKullanimTur)getOrtakServis().readObjectById(OrtakKullanimTur.class, buildingCommonUsageTrs.getOrtakKullanimTurId());
				
				
				if (commonUsageType!=null){
					
					buildingCommonUsage.setOrtakKullanimTur(commonUsageType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (OrtakKullanimTur Id={0})",buildingCommonUsageTrs.getOrtakKullanimTurId()),null);
				}
			}	
			
			buildingCommonUsage.setKaydeden(buildingCommonUsageTrs.getKaydeden());
			
			buildingCommonUsage.setKayitTarih(buildingCommonUsageTrs.getKayitTarih());
			
			buildingCommonUsage.setAkosDegisti(buildingCommonUsageTrs.getAkosDegisti());
			
			if (buildingCommonUsageTrs.getAkosDegisimTarihi()!=null)
			{
				buildingCommonUsage.setAkosDegisimTarihi(buildingCommonUsageTrs.getAkosDegisimTarihi());
			}
			
			buildingCommonUsage.setPdaEh(buildingCommonUsageTrs.getPdaEh());
			
			buildingCommonUsage.setPdaIslemTarihi(buildingCommonUsageTrs.getPdaIslemTarihi());
			
			buildingCommonUsage.setPdaUserName(buildingCommonUsageTrs.getPdaUserName());
			
			buildingCommonUsage.setAlaniAdedi(buildingCommonUsageTrs.getAlaniAdedi());
			
			buildingCommonUsageList.add(buildingCommonUsage);
			
		}
		
		return buildingCommonUsageList;
	}

	public List<BinaTesisat> convertBuildingFittingTrsListToOrjList(
			List<BinaTesisatTrs> buildingFittingTrsList) throws AkosException {
		
		List<BinaTesisat> buildingFittingList=new ArrayList<BinaTesisat>();
		
		for (BinaTesisatTrs buildingFittingTrs : buildingFittingTrsList) {
			
			Long buildingFittingId=getIdGeneratorServis().getIDForNewObject(BinaTesisat.class);
			
			BinaTesisat buildingFitting=new BinaTesisat();
			
			buildingFitting.setId(buildingFittingId);
			
			if (buildingFittingTrs.getAkosDegisimTarihi()!=null)
			{
				buildingFitting.setAkosDegisimTarihi(buildingFittingTrs.getAkosDegisimTarihi());
			}
			
			buildingFitting.setAkosDegisimTarihi(buildingFittingTrs.getAkosDegisimTarihi());
			
			buildingFitting.setAkosDegisti(buildingFittingTrs.getAkosDegisti());
			
			buildingFitting.setAkosDegistiren(buildingFittingTrs.getAkosDegistiren());
			
			buildingFitting.setAktifEh(buildingFittingTrs.getAktifEh());
			
			buildingFitting.setKaydeden(buildingFittingTrs.getKaydeden());
			
			buildingFitting.setKayitTarih(buildingFittingTrs.getKayitTarih());
			
			buildingFitting.setPdaEh(buildingFittingTrs.getPdaEh());
			
			buildingFitting.setPdaIslemTarihi(buildingFittingTrs.getPdaIslemTarihi());
			
			buildingFitting.setPdaUserName(buildingFittingTrs.getPdaUserName());
			
			if (buildingFittingTrs.getTesisatTurId()!=null && buildingFittingTrs.getTesisatTurId().longValue()>0){
				
				TesisatTur fittingType=(TesisatTur)getOrtakServis().readObjectById(TesisatTur.class, buildingFittingTrs.getTesisatTurId());
				
				if (fittingType!=null){
					
					buildingFitting.setTesisatTur(fittingType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (TesisatTur Id={0})",buildingFittingTrs.getTesisatTurId()),null);
				}
			}
			
			buildingFittingList.add(buildingFitting);
		}
		
		return buildingFittingList;
	}

	public List<BinaParsel> convertBuildingParceTrsListToOrjList(
			List<BinaParselTrs> buildingParcelTrsList) throws AkosException {
		
		List<BinaParsel> buildingParcelList=new ArrayList<BinaParsel>();
		
		for (BinaParselTrs buildingParcelTrs : buildingParcelTrsList) {
		
			Long buildingParcelId=getIdGeneratorServis().getIDForNewObject(BinaParsel.class);
			
			BinaParsel buildingParcel=new BinaParsel();
			
			buildingParcel.setId(buildingParcelId);
			
			if (buildingParcelTrs.getAkosDegisimTarihi()!=null)
			{
				buildingParcel.setAkosDegisimTarihi(buildingParcelTrs.getAkosDegisimTarihi());
			}
			
			
			//buildingParcel.setAkosDegisimTarihi(buildingParcelTrs.getAkosDegisimTarihi());
			
			buildingParcel.setAkosDegisti(buildingParcelTrs.getAkosDegisti());
			
			buildingParcel.setAkosDegistiren(buildingParcelTrs.getAkosDegistiren());
			
			buildingParcel.setAktifEh(buildingParcelTrs.getAktifEh());
			
			buildingParcel.setKaydeden(buildingParcelTrs.getKaydeden());
			
			buildingParcel.setKayitTarih(buildingParcelTrs.getKayitTarih());
			
			if (buildingParcelTrs.getKadastroParselId()!=null && buildingParcelTrs.getKadastroParselId().longValue()>0){
				
				KadastroParsel parcel=(KadastroParsel)getOrtakServis().readObjectById(KadastroParsel.class, buildingParcelTrs.getKadastroParselId());
				
				if (parcel!=null){
					
					buildingParcel.setKadastroParsel(parcel);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (KadastroParsel Id={0})",buildingParcelTrs.getKadastroParselId()),null);
				}
			}
			
			buildingParcelList.add(buildingParcel);
			
		}

		return buildingParcelList;
	}

	public List<BinaNitelik> convertBuildingAttributesTrstListToOrjList(List<BinaNitelikTrs> buildingAttributesTrsList) throws AkosException {

		List<BinaNitelik> buildingAttributesList=new ArrayList<BinaNitelik>();
			
		for (BinaNitelikTrs buildingAttributesTrs : buildingAttributesTrsList) {
			
			BinaNitelik buildingAttributes=new BinaNitelik();
		
			Long buildingPropertiesId=getIdGeneratorServis().getIDForNewObject(BinaNitelik.class);
			
			buildingAttributes.setId(buildingPropertiesId);
			
			buildingAttributes.setAciklama(buildingAttributesTrs.getAciklama());
			
			buildingAttributes.setBenzerYapiSayisi(buildingAttributesTrs.getBenzerYapiSayisi());
			
			buildingAttributes.setBagimsizBolumSayisi(buildingAttributesTrs.getBagimsizBolumSayisi());
			
			buildingAttributes.setKonutDaireSayisi(buildingAttributesTrs.getKonutDaireSayisi());
			
			buildingAttributes.setYolKotuAltiKatSayisi(buildingAttributesTrs.getYolKotuAltiKatSayisi());
			
			buildingAttributes.setYolKotuUstuKatSayisi(buildingAttributesTrs.getYolKotuUstuKatSayisi());
			
			buildingAttributes.setIlaveKatSayisi(buildingAttributesTrs.getIlaveKatSayisi());
			
			buildingAttributes.setToplamKatSayisi(buildingAttributesTrs.getToplamKatSayisi());
			
			buildingAttributes.setYolKotuAltiYukseklik(buildingAttributesTrs.getYolKotuAltiYukseklik());
			
			buildingAttributes.setYolKotuUstuYukseklik(buildingAttributesTrs.getYolKotuUstuYukseklik());
			
			buildingAttributes.setInsaatAlani(buildingAttributesTrs.getIlaveYukseklik());
			
			buildingAttributes.setToplamMaliyeti(buildingAttributesTrs.getToplamMaliyeti());
			
			buildingAttributes.setIcmeSuTuru(buildingAttributesTrs.getIcmeSuTuru());
			
			buildingAttributes.setAtikSuTuru(buildingAttributesTrs.getAtikSuTuru());
			
			buildingAttributes.setBosEh(buildingAttributesTrs.getBosEh());
			
			buildingAttributes.setUlusalBinaNo(buildingAttributesTrs.getUlusalBinaNo());
			
			buildingAttributes.setTabanAlani(buildingAttributesTrs.getTabanAlani());
			
			buildingAttributes.setKayitTarih(buildingAttributesTrs.getKayitTarih());
			
			buildingAttributes.setKaydeden(buildingAttributesTrs.getKaydeden());
			
		//	buildingAttributes.setAkosDegisimTarihi(buildingAttributesTrs.getAkosDegisimTarihi());
			if (buildingAttributesTrs.getAkosDegisimTarihi()!=null)
			{
				buildingAttributes.setAkosDegisimTarihi(buildingAttributesTrs.getAkosDegisimTarihi());
			}
			
			buildingAttributes.setAkosDegisti(buildingAttributesTrs.getAkosDegisti());
			
			buildingAttributes.setAkosDegistiren(buildingAttributesTrs.getAkosDegistiren());
			
			buildingAttributes.setTarihiEserEh(buildingAttributesTrs.getTarihiEserEh());
			
			buildingAttributesList.add(buildingAttributes);
			
			
		}
		return buildingAttributesList;
	}

	public List<Bagimsiz> convertDetachedTrsListToOrjList(
			List<BagimsizTrs> detachedTrsList) throws AkosException {

		List<Bagimsiz> detachedList=new ArrayList<Bagimsiz>();
		
		for (BagimsizTrs detachedTrs : detachedTrsList) {
			
			Bagimsiz detached=new Bagimsiz();
			
			Long detachedId=getIdGeneratorServis().getIDForNewObject(Bagimsiz.class);
			
			detached.setId(detachedId);
			
			
			if (detachedTrs.getAdresId()!=null && detachedTrs.getAdresId().longValue()>0){
				
				Adres address=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getAdres()!=null){
						
						if (detachedTemp.getAdres().getId().equals(detachedTrs.getAdresId())){
							
							address=detachedTemp.getAdres();
							
							break;
							
						}
					}
					
				}
				
				if (address==null){
					
					address=(Adres)getOrtakServis().readObjectById(Adres.class, detachedTrs.getAdresId());
				}
				
				if (address!=null){
					
					detached.setAdres(address);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (Adres Id={0})",detachedTrs.getAdresId()),null);
				}
			}
			
			
			detached.setDaireNo(detachedTrs.getDaireNo());
			
			detached.setAltDaireNo(detachedTrs.getAltDaireNo());
			
			detached.setKatNo(detachedTrs.getKatNo());
			
			detached.setBagimsizNo(detachedId);
			
			detached.setBagimsizAd(detachedTrs.getBagimsizAd());
			
			detached.setAktifEh(detachedTrs.getAktifEh());
			
			if (detachedTrs.getBilgiTipi()!=null && detachedTrs.getBilgiTipi().longValue()>0){
				
				BilgiTipi infogatherType=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getBilgiTipi()!=null){
						
						if (detachedTemp.getBilgiTipi().getId().equals(detachedTrs.getBilgiTipi())){
							
							infogatherType=detachedTemp.getBilgiTipi();
							
							break;
							
						}
					}
					
				}
				
				if (infogatherType==null){
					
					infogatherType=(BilgiTipi)getOrtakServis().readObjectById(BilgiTipi.class, detachedTrs.getBilgiTipi());
				}
				
				if (infogatherType!=null){
					
					detached.setBilgiTipi(infogatherType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (BilgiTipi Id={0})",detachedTrs.getBilgiTipi()),null);
				}
			}
			
			
			if (detachedTrs.getIsitmaTur()!=null && detachedTrs.getIsitmaTur().longValue()>0){
				
				IsitmaTur heatingType=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getIsitmaTur()!=null){
						
						if (detachedTemp.getIsitmaTur().getId().equals(detachedTrs.getIsitmaTur())){
							
							heatingType=detachedTemp.getIsitmaTur();
							
							break;
						}
					}
					
				}
				
				if (heatingType==null){
					
					heatingType=(IsitmaTur)getOrtakServis().readObjectById(IsitmaTur.class, detachedTrs.getIsitmaTur());
				}
				
				if (heatingType!=null){
					
					detached.setIsitmaTur(heatingType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (IsitmaTur Id={0})",detachedTrs.getIsitmaTur()),null);
				}
			}
			
			
			if (detachedTrs.getKonutTip()!=null && detachedTrs.getKonutTip().longValue()>0){
				
				KonutTip residenceType=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getKonutTip()!=null){
						
						if (detachedTemp.getKonutTip().getId().equals(detachedTrs.getKonutTip())){
							
							residenceType=detachedTemp.getKonutTip();
							
							break;
						}
					}
				}
				
				if (residenceType==null){
					
					residenceType=(KonutTip)getOrtakServis().readObjectById(KonutTip.class, detachedTrs.getKonutTip());
				}
				
				if (residenceType!=null){
					
					detached.setKonutTip(residenceType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (KonutTip Id={0})",detachedTrs.getKonutTip()),null);
				}
			}
			
			
			if (detachedTrs.getKonutDurum()!=null && detachedTrs.getKonutDurum().longValue()>0){
				
				KonutDurum residenceCondition=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getKonutDurum()!=null){
						
						if (detachedTemp.getKonutDurum().getId().equals(detachedTrs.getKonutDurum())){
							
							residenceCondition=detachedTemp.getKonutDurum();
							
							break;
						}
					}
				}
				
				if (residenceCondition==null){
					
					residenceCondition=(KonutDurum)getOrtakServis().readObjectById(KonutDurum.class, detachedTrs.getKonutDurum());
				}
				
				if (residenceCondition!=null){
					
					detached.setKonutDurum(residenceCondition);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (KonutDurum Id={0})",detachedTrs.getKonutDurum()),null);
				}
			}
			
			
			if (detachedTrs.getYakitTur()!=null && detachedTrs.getYakitTur().longValue()>0){
				
				YakitTur fuelType=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getYakitTur()!=null){
						
						if (detachedTemp.getYakitTur().getId().equals(detachedTrs.getYakitTur())){
							
							fuelType=detachedTemp.getYakitTur();
							
							break;
						}
					}
				}
				
				if (fuelType==null){
					
					fuelType=(YakitTur)getOrtakServis().readObjectById(YakitTur.class, detachedTrs.getYakitTur());
				}
				
				if (fuelType!=null){
					
					detached.setYakitTur(fuelType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (YakitTur Id={0})",detachedTrs.getYakitTur()),null);
				}
			}
			
			
			
			if (detachedTrs.getBagimsizKullanimDetay()!=null && detachedTrs.getBagimsizKullanimDetay().longValue()>0){
				
				BagimsizKullanimDetay usageType=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getBagimsizKullanimDetay()!=null){
						
						if (detachedTemp.getBagimsizKullanimDetay().getId().equals(detachedTrs.getBagimsizKullanimDetay())){
							
							usageType=detachedTemp.getBagimsizKullanimDetay();
							
							break;
						}
					}
				}
				
				if (usageType==null){
					
					usageType=(BagimsizKullanimDetay)getOrtakServis().readObjectById(BagimsizKullanimDetay.class, detachedTrs.getBagimsizKullanimDetay());
				}
				
				if (usageType!=null){
					
					detached.setBagimsizKullanimDetay(usageType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (BagimsizKullanimDetay Id={0})",detachedTrs.getBagimsizKullanimDetay()),null);
				}
			}
			
			
			detached.setElektrikNo(detachedTrs.getElektrikNo());
			
			detached.setGazNo(detachedTrs.getGazNo());
			
			detached.setSuNo(detachedTrs.getSuNo());
			
			detached.setYuzolcum(detachedTrs.getYuzolcum());
			
			detached.setKaloriferEh(detachedTrs.getKaloriferEh());
			
			detached.setTuikSiraNo(detachedTrs.getTuikSiraNo());
			
			detachedTrs.setAktifEh(detachedTrs.getAktifEh());
			
			detached.setAciklama(detachedTrs.getAciklama());
			
			detached.setPdaUserName(detachedTrs.getPdaUserName());
			
			detached.setPdaIslemTarihi(detachedTrs.getPdaIslemTarihi());
			
			detached.setPdaEh(detachedTrs.getPdaEh());
			
			detached.setKayitTarih(detachedTrs.getKayitTarih());
			
			detached.setKaydeden(detachedTrs.getKaydeden());
			
			detached.setAcilisTarih(detachedTrs.getAcilisTarih());
			
			detached.setKapanisTarih(detachedTrs.getKapanisTarih());
			
			
			if (detachedTrs.getAcilisHareketKod()!=null && detachedTrs.getAcilisHareketKod().longValue()>0){
				
				HareketKod openningOperationType=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getAcilisHareketKod()!=null){
						
						if (detachedTemp.getAcilisHareketKod().getId().equals(detachedTrs.getAcilisHareketKod())){
							
							openningOperationType=detachedTemp.getAcilisHareketKod();
							
							break;
						}
					}
				}
				
				if (openningOperationType==null){
					
					openningOperationType=(HareketKod)getOrtakServis().readObjectById(HareketKod.class, detachedTrs.getAcilisHareketKod());
				}
				
				if (openningOperationType!=null){
					
					detached.setAcilisHareketKod(openningOperationType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (HareketKod Id={0})",detachedTrs.getAcilisHareketKod()),null);
				}
			}
			
			if (detachedTrs.getKapanisHareketKod()!=null && detachedTrs.getKapanisHareketKod().longValue()>0){
				
				HareketKod closingOperationType=null;
				
				for (Bagimsiz detachedTemp : detachedList) {
				
					if (detachedTemp.getKapanisHareketKod()!=null){
						
						if (detachedTemp.getKapanisHareketKod().getId().equals(detachedTrs.getKapanisHareketKod())){
							
							closingOperationType=detachedTemp.getKapanisHareketKod();
							
							break;
						}
					}
				}
				
				if (closingOperationType==null){
					
					closingOperationType=(HareketKod)getOrtakServis().readObjectById(HareketKod.class, detachedTrs.getKapanisHareketKod());
				}
				
				if (closingOperationType!=null){
					
					detached.setKapanisHareketKod(closingOperationType);
					
				}else{
					
					throw new AkosException(String.format("No object found for class (HareketKod Id={0})",detachedTrs.getKapanisHareketKod()),null);
				}
			}			
			
			detachedList.add(detached);
			
		}
		return detachedList;
	}

	public boolean saveBuildingTransaction(BinaTrs buildingTrs) {
		
		return transactionDAO.saveBuildingTransaction(buildingTrs);
	}

	public boolean saveBuildingTransactions(Collection<BinaTrs> buildingTrsList) {
		
		return transactionDAO.saveBuildingTransactions(buildingTrsList);
	}

}
	
