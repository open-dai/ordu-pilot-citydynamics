package com.sampas.ortak.spatial.history.servis.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.ortak.spatial.generator.servis.IIDGeneratorServis;
import com.sampas.ortak.spatial.history.dao.IHistoryDAO;
import com.sampas.ortak.spatial.history.model.AdresHst;
import com.sampas.ortak.spatial.history.model.BagimsizHst;
import com.sampas.ortak.spatial.history.model.BinaHst;
import com.sampas.ortak.spatial.history.model.CaddeSokakHst;
import com.sampas.ortak.spatial.history.model.MahalleCaddeSokakHst;
import com.sampas.ortak.spatial.history.servis.IHistoryServis;


@SuppressWarnings("unchecked")
public class HistoryServis implements IHistoryServis {

	private IHistoryDAO historyDAO;
	
	private IIDGeneratorServis idGeneratorServis;
	
	private OrtakServis ortakServis;
	
	
	public boolean saveStreetOrjToHistory(CaddeSokak streetOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType) {
		
		if (streetOrj != null) {
			
			CaddeSokakHst streetHst = convertStreetOrjToStreetHst(streetOrj,processId,taskId, recordOwner, changeDate, processType);
			
			if (getHistoryDAO().saveStreetHst(streetHst)) {
				
				return true;
			}
		}
		return false;
	}
	
	public boolean saveStreetOrjListToHistory(List<CaddeSokak> streetOrjList,  Long processId, Long taskId,String recordOwner, Date changeDate, String processType) {
		
		if (streetOrjList != null && streetOrjList.size() > 0) {
			
			List<CaddeSokakHst> resultStreetHstList = new ArrayList<CaddeSokakHst>();
			
			for (CaddeSokak tempStreetOrj : streetOrjList) {
				
				resultStreetHstList.add(convertStreetOrjToStreetHst(tempStreetOrj,processId,taskId, recordOwner, changeDate, processType));
			}
			if (resultStreetHstList != null && resultStreetHstList.size() > 0) {
				
				if (getHistoryDAO().saveStreetHstCollection(resultStreetHstList)) {
					
					return true;
				} else {
					
					return false;
				}
			}
		} else {
			
			return false;
		}
		return false;
	}

	public boolean deleteStreetHst(CaddeSokakHst streetHst) {
		
		if (streetHst != null) {
			
			return this.historyDAO.deleteStreetHst(streetHst);
		}
		return false;
	}
	
	public boolean deleteStreetHstCollection(Collection<CaddeSokakHst> streetHstCollection) {
		
		if (streetHstCollection != null && streetHstCollection.size() > 0) {
			
			return this.historyDAO.deleteStreetHstCollection(streetHstCollection);
		}
		return false;
	}
	
	public boolean saveDistrictStreetOrjToHistory(MahalleCaddeSokak districtStreetOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType) {

		if (districtStreetOrj != null) {
			
			MahalleCaddeSokakHst districtStreetHst = convertDistrictStreetOrjToDistrictStreetHst(districtStreetOrj,processId,taskId, recordOwner, changeDate, processType);
			
			if (getHistoryDAO().saveDistrictStreetHst(districtStreetHst)) {
				
				return true;
			}
		}
		return false;
	}

	public boolean saveDistrictStreetOrjListToHistory(List<MahalleCaddeSokak> districtStreetListOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType) {

		if (districtStreetListOrj != null && districtStreetListOrj.size() > 0) {
			
			List<MahalleCaddeSokakHst> resultDistrictStreetHstList = new ArrayList<MahalleCaddeSokakHst>();
			
			for (MahalleCaddeSokak tempDistrictStreetOrj : districtStreetListOrj) {
				
				resultDistrictStreetHstList.add(convertDistrictStreetOrjToDistrictStreetHst(tempDistrictStreetOrj,processId,taskId, recordOwner, changeDate, processType));
			}
			if (resultDistrictStreetHstList != null && resultDistrictStreetHstList.size() > 0) {
				
				if (getHistoryDAO().saveDistrictStreetHstCollection(resultDistrictStreetHstList)) {
					
					return true;
				} else {
			
					return false;
				}
			}
		} else {
			
			return false;
		}
		return false;
	}
	
	public boolean deleteDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst) {
		
		if (districtStreetHst != null) {
			
			return this.historyDAO.deleteDistrictStreetHst(districtStreetHst);
		}
		return false;
	}
	
	public boolean deleteDistrictStreetHstCollection(Collection<MahalleCaddeSokakHst> districtStreetHstCollection) {
		
		if (districtStreetHstCollection != null && districtStreetHstCollection.size() > 0) {
			
			return this.historyDAO.deleteDistrictStreetHstCollection(districtStreetHstCollection);
		}
		return false;
	}
	
	public boolean saveAddressOrjToHistory(Adres addressOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst) {

		if (addressOrj != null) {
			
			AdresHst addressHst = convertAddressOrjToAddressHst(addressOrj,processId,taskId, recordOwner, changeDate, processType, compConst);
			
			if (getHistoryDAO().saveAddressHst(addressHst)) {
				
				return true;
			}
		}
		return false;
	}
	
	public boolean saveAddressOrjListToHistory(List<Adres> addressOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst) {

		if (addressOrjList != null && addressOrjList.size() > 0) {
			
			List<AdresHst> resultAddressHstList = new ArrayList<AdresHst>();
			
			for (Adres tempAddressOrj : addressOrjList) {
				
				resultAddressHstList.add(convertAddressOrjToAddressHst(tempAddressOrj,processId,taskId, recordOwner, changeDate, processType, compConst));
			}
			if (resultAddressHstList != null && resultAddressHstList.size() > 0) {
				
				if (getHistoryDAO().saveAddressHstCollection(resultAddressHstList)) {
					
					return true;
				} else {
					
					return false;
				}
			}
		} else {
			
			return false;
		}
		return false;
	}
	
	public boolean deleteAddressHst(AdresHst addressHst) {
		
		if (addressHst != null) {
			
			return this.historyDAO.deleteAddressHst(addressHst);
		}
		return false;
	}
	
	public boolean deleteAddressHstCollection(Collection<AdresHst> addressHstCollection) {
		
		if (addressHstCollection != null && addressHstCollection.size() > 0) {
			
			return this.historyDAO.deleteAddressHstCollection(addressHstCollection);
		}
		return false;
	}
	
	public CaddeSokakHst convertStreetOrjToStreetHst(CaddeSokak streetOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType) {
		
		if (streetOrj != null) {
			
			CaddeSokakHst resultStreetHst = new CaddeSokakHst();
			
			resultStreetHst.setId(getIdGeneratorServis().getIDForNewObject(resultStreetHst.getClass()));
			
			resultStreetHst.setSurecId(processId);
			
			resultStreetHst.setTaskId(taskId);
			
			if (streetOrj.getId() != null) {
				
				resultStreetHst.setNesneId(streetOrj.getId());
			}
			if (streetOrj.getAciklama() != null) {
			
				resultStreetHst.setAciklama(streetOrj.getAciklama());
			}
			if (streetOrj.getAcilisTarih() != null) {
				
				resultStreetHst.setAcilisTarih(streetOrj.getAcilisTarih());
			}
			if (streetOrj.getAkosDegisimTarihi() != null) {
				
				resultStreetHst.setAkosDegisimTarihi(streetOrj.getAkosDegisimTarihi());
			}
			if (streetOrj.getAkosDegisti() != null) {
				
				resultStreetHst.setAkosDegisti(streetOrj.getAkosDegisti());
			}
			if (streetOrj.getAkosDegistiren() != null) {
				
				resultStreetHst.setAkosDegistiren(streetOrj.getAkosDegistiren());
			}
			if (streetOrj.getAktifEh() != null) {
				
				resultStreetHst.setAktifEh(streetOrj.getAktifEh());
			}
			if (streetOrj.getCaddeSokakTur() != null) {
				
				resultStreetHst.setCaddeSokakTurId(streetOrj.getCaddeSokakTur().getId());
			} else {
				//TODO:In new model this attribute can't be null or empty so for now have to set it a default value.
				resultStreetHst.setCaddeSokakTurId(1L);
			}
			if (streetOrj.getCiftKapiBaslangicNo() != null) {
				
				resultStreetHst.setCiftKapiBaslangicNo(streetOrj.getCiftKapiBaslangicNo());
			}
			if (streetOrj.getCiftKapiBitisNo() != null) {
				
				resultStreetHst.setCiftKapiBitisNo(streetOrj.getCiftKapiBitisNo());
			}
			if (streetOrj.getKapanisTarih() != null) {
				
				resultStreetHst.setKapanisTarih(streetOrj.getKapanisTarih());
			}
			if (streetOrj.getKaydeden() != null) {
				
				resultStreetHst.setKaydeden(streetOrj.getKaydeden());
			}
			if (streetOrj.getKayitTarih() != null) {
				
				resultStreetHst.setKayitTarih(streetOrj.getKayitTarih());			
			}
			if (streetOrj.getKurumSabit() != null) {
				
				resultStreetHst.setKurumSabitId(streetOrj.getKurumSabit().getId());
			}
			if (streetOrj.getMeclisKararNo() != null) {
				
				resultStreetHst.setMeclisKararNo(streetOrj.getMeclisKararNo());
			}
			if (streetOrj.getMeclisKararTarih() != null) {
				
				resultStreetHst.setMeclisKararTarih(streetOrj.getMeclisKararTarih());
			}
			if (streetOrj.getTekKapiBaslangicNo() != null) {
				
				resultStreetHst.setTekKapiBaslangicNo(streetOrj.getTekKapiBaslangicNo());
			}
			if (streetOrj.getTekKapiBitisNo() != null) {
				
				resultStreetHst.setTekKapiBitisNo(streetOrj.getTekKapiBitisNo());
			}
			if (recordOwner != null) {
				
				resultStreetHst.setDegisiklikYapan(recordOwner);
			}
			if (changeDate != null) {
				
				resultStreetHst.setDegisiklikTarihi(changeDate);
			}
			if (processType != null) {
				
				resultStreetHst.setDegisiklikTipi(processType);
			}
			return resultStreetHst;
		} else {
			
			return null;
		}
	}
	
	public MahalleCaddeSokakHst convertDistrictStreetOrjToDistrictStreetHst(MahalleCaddeSokak districtStreetOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType) {
		
		if (districtStreetOrj != null) {
			
			MahalleCaddeSokakHst resultDistrictStreetHst = new MahalleCaddeSokakHst();
			
			resultDistrictStreetHst.setId(getIdGeneratorServis().getIDForNewObject(resultDistrictStreetHst.getClass()));
			
			resultDistrictStreetHst.setSurecId(processId);
			
			resultDistrictStreetHst.setTaskId(taskId);
			
			if (districtStreetOrj.getId() != null) {
				
				resultDistrictStreetHst.setNesneId(districtStreetOrj.getId());
			}
			if (districtStreetOrj.getAkosDegisimTarihi() != null) {
				
				resultDistrictStreetHst.setAkosDegisimTarihi(districtStreetOrj.getAkosDegisimTarihi());
			}
			if (districtStreetOrj.getAkosDegisti() != null) {
				
				resultDistrictStreetHst.setAkosDegisti(districtStreetOrj.getAkosDegisti());
			}
			if (districtStreetOrj.getAkosDegistiren() != null) {
				
				resultDistrictStreetHst.setAkosDegistiren(districtStreetOrj.getAkosDegistiren());
			}
			if (districtStreetOrj.getAktifEh() != null) {
				
				resultDistrictStreetHst.setAktifEh(districtStreetOrj.getAktifEh());
			}
			if (districtStreetOrj.getCaddeSokak() != null) {
				
				resultDistrictStreetHst.setCaddeSokakId(districtStreetOrj.getCaddeSokak().getId());
			}
			if (districtStreetOrj.getCtvDerece() != null) {
				
				resultDistrictStreetHst.setCtvDerece(districtStreetOrj.getCtvDerece());
			}
			if (districtStreetOrj.getCtvGrup() != null) {
				
				resultDistrictStreetHst.setCtvGrup(districtStreetOrj.getCtvGrup());
			}
			if (districtStreetOrj.getGelismislikDurum() != null) {
				
				resultDistrictStreetHst.setGelismislikDurumId(districtStreetOrj.getGelismislikDurum().getId());
			}
			if (districtStreetOrj.getKaydeden() != null) {
				
				resultDistrictStreetHst.setKaydeden(districtStreetOrj.getKaydeden());
			}
			if (districtStreetOrj.getKayitTarih() != null) {
				
				resultDistrictStreetHst.setKayitTarih(districtStreetOrj.getKayitTarih());
			}
			if (districtStreetOrj.getMahalle() != null) {
				
				resultDistrictStreetHst.setMahalleId(districtStreetOrj.getMahalle().getId());
			}
			if (districtStreetOrj.getTuikCaddeSokakKod() != null) {
				
				resultDistrictStreetHst.setTuikCaddeSokakKod(districtStreetOrj.getTuikCaddeSokakKod());
			}
			if (districtStreetOrj.getTuikCaddeSokakTanitimNo() != null) {
				
				resultDistrictStreetHst.setTuikCaddeSokakTanitimNo(districtStreetOrj.getTuikCaddeSokakTanitimNo());
			}
			if (recordOwner != null) {
				
				resultDistrictStreetHst.setDegisiklikYapan(recordOwner);
			}
			if (changeDate != null) {
				
				resultDistrictStreetHst.setDegisiklikTarihi(changeDate);
			}
			if (processType != null) {
				
				resultDistrictStreetHst.setDegisiklikTipi(processType);
			}
			return resultDistrictStreetHst;
		} else {
			
			return null;
		}
	}
	
	public AdresHst convertAddressOrjToAddressHst(Adres addressOrj, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst) {
		
		if (addressOrj != null) {
			
			AdresHst resultAddressHst = new AdresHst();
			
			resultAddressHst.setId(getIdGeneratorServis().getIDForNewObject(resultAddressHst.getClass()));
			
			resultAddressHst.setSurecId(processId);
			
			resultAddressHst.setTaskId(taskId);
			
			if (addressOrj.getId() != null) {
				
				resultAddressHst.setNesneId(addressOrj.getId());
			}
			if (addressOrj.getAciklama() != null) {
				
				resultAddressHst.setAciklama(addressOrj.getAciklama());
			}
			if (addressOrj.getAcilisTarihi() != null) {
				
				resultAddressHst.setAcilisTarihi(addressOrj.getAcilisTarihi());
			}
			if (addressOrj.getAdresNo() != null) {
				
				resultAddressHst.setAdresNo(addressOrj.getAdresNo());
			}
			if (addressOrj.getAdresTur() != null) {
				
				resultAddressHst.setAdresTur(addressOrj.getAdresTur());
			}
			if (addressOrj.getAkosDegisimTarihi() != null) {
				
				resultAddressHst.setAkosDegisimTarihi(addressOrj.getAkosDegisimTarihi());
			}
			if (addressOrj.getAkosDegisti() != null) {
				
				resultAddressHst.setAkosDegisti(addressOrj.getAkosDegisti());
			}
			if (addressOrj.getAkosDegistiren() != null) {
				
				resultAddressHst.setAkosDegistiren(addressOrj.getAkosDegistiren());
			}
			if (addressOrj.getAktifEh() != null) {
				
				resultAddressHst.setAktifEh(addressOrj.getAktifEh());
			}
			if (addressOrj.getAltKapiNo() != null) {
				
				resultAddressHst.setAltKapiNo(addressOrj.getAltKapiNo());
			}
			if (addressOrj.getBina() != null) {
				
				resultAddressHst.setBinaId(addressOrj.getBina().getId());
			}
			if (addressOrj.getKadastroParsel() != null) {
				
				resultAddressHst.setKadastroParselId(addressOrj.getKadastroParsel().getId());
			}
			if (addressOrj.getKapanisTarihi() != null) {
				
				resultAddressHst.setKapanisTarihi(addressOrj.getKapanisTarihi());
			}
			if (addressOrj.getKapiNo() != null) {
				
				resultAddressHst.setKapiNo(addressOrj.getKapiNo());
			}
			if (addressOrj.getKaydeden() != null) {
				
				resultAddressHst.setKaydeden(addressOrj.getKaydeden());
			}
			if (addressOrj.getKayitTarih() != null) {
				
				resultAddressHst.setKayitTarih(addressOrj.getKayitTarih());
			}
			if (addressOrj.getKurumSabit() != null) {
				
				resultAddressHst.setKurumSabitId(addressOrj.getKurumSabit().getId());
			}
			if (addressOrj.getMahalleCaddeSokak() != null) {
				
				resultAddressHst.setMahalleCaddeId(addressOrj.getMahalleCaddeSokak().getId());
			}
			if (addressOrj.getPdaEh() != null) {
				
				resultAddressHst.setPdaEh(addressOrj.getPdaEh());
			}
			if (addressOrj.getPdaIslemTarihi() != null) {
				
				resultAddressHst.setPdaIslemTarihi(addressOrj.getPdaIslemTarihi());
			}
			if (addressOrj.getPdaUserName() != null) {
				
				resultAddressHst.setPdaUserName(addressOrj.getPdaUserName());
			}
			if (recordOwner != null) {
				
				resultAddressHst.setDegisiklikYapan(recordOwner);
			}
			if (changeDate != null) {
				
				resultAddressHst.setDegisiklikTarihi(changeDate);
			}
			if (processType != null) {
				
				resultAddressHst.setDegisiklikTipi(processType);
			}
			//For complate address HST convertion need to add mahalleCaddeId and binaId and parselID
			
			List<MahalleCaddeSokak> districtStreetList = getOrtakServis().readAllMahalleCaddeSokakByAdres(addressOrj, compConst);
			
			if (districtStreetList != null && districtStreetList.size() > 0) {
				
				resultAddressHst.setMahalleCaddeId(districtStreetList.get(0).getId());
			}
			List<Bina> buildingList = getOrtakServis().readAllBinaByCriteria(addressOrj, compConst);
			
			if (buildingList != null && buildingList.size() > 0) {
				
				resultAddressHst.setBinaId(buildingList.get(0).getId());
			}
			List<KadastroParsel> parcelList = getOrtakServis().readAllKadastroParselByAdresAndMahalleCaddeSokak(compConst, addressOrj, null);
			
			if(parcelList != null && parcelList.size() > 0) {
				
				resultAddressHst.setKadastroParselId(parcelList.get(0).getId());
			}
			return resultAddressHst;
		} else {
			
			return null;
		}
	}
	
	public List<CaddeSokakHst> convertStreetOrjListToStreetHstList(List<CaddeSokak> streetOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType) {
		
		List<CaddeSokakHst> caddeSokakHstList=null;
		
		if (streetOrjList!=null){
		
			caddeSokakHstList=new ArrayList<CaddeSokakHst>();
			
			for (CaddeSokak tempStreetOrj : streetOrjList) {
			
				caddeSokakHstList.add(convertStreetOrjToStreetHst(tempStreetOrj,processId,taskId, recordOwner, changeDate, processType));
			}
		}			
		return caddeSokakHstList;
	}

	public boolean saveAddressHst(AdresHst addressHst) {

		if (getHistoryDAO().saveAddressHst(addressHst)) {
			
			return true;
		}
		return false;
	}
	
	public boolean saveAddressHstList(List<AdresHst> addressHstList) {
		
		if (getHistoryDAO().saveAddressHstCollection((addressHstList))) {
			
			return true;
		}
		return false;
	}
	
	public boolean saveDistrictStreetHst(MahalleCaddeSokakHst districtStreetHst) {
		
		if (getHistoryDAO().saveDistrictStreetHst(districtStreetHst)) {
			
			return true;
		}
		return false;
	}

	public boolean saveDistrictStreetHstList(List<MahalleCaddeSokakHst> districtStreetHstList) {

		if (getHistoryDAO().saveDistrictStreetHstCollection(districtStreetHstList)) {
			
			return true;
		}
		return false;
	}

	public boolean saveStreetHst(CaddeSokakHst streetHst) {
		
		if (getHistoryDAO().saveStreetHst(streetHst)) {
			
			return true;
		}
		return false;
	}

	public boolean saveStreetHstList(List<CaddeSokakHst> streetHstList) {

		if (getHistoryDAO().saveStreetHstCollection(streetHstList)) {
			
			return true;
		}
		return false;
	}

	public List<AdresHst> convertAddressOrjListToAddressHstList(List<Adres> addressOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType, KurumSabit compConst) {

		if (addressOrjList!=null){
			
			List<AdresHst> addressHstList=new ArrayList<AdresHst>();
			
			for (Adres addressOrj : addressOrjList) {
				
				addressHstList.add(convertAddressOrjToAddressHst(addressOrj,processId,taskId, recordOwner, changeDate, processType, compConst));
				
			}
			return addressHstList;
		}
		return null;
	}

	public List<MahalleCaddeSokakHst> convertDistrictStreetOrjListToDistrictStreetHstList(List<MahalleCaddeSokak> districtStreetOrjList, Long processId, Long taskId, String recordOwner, Date changeDate, String processType) {
		
		if (districtStreetOrjList!=null){
			
			List<MahalleCaddeSokakHst> districtStreetHstList=new ArrayList<MahalleCaddeSokakHst>();
			
			for (MahalleCaddeSokak districtStreetOrj : districtStreetOrjList) {
				
				districtStreetHstList.add(convertDistrictStreetOrjToDistrictStreetHst(districtStreetOrj,processId,taskId, recordOwner, changeDate, processType));
				
			}
			return districtStreetHstList;
		}
		return null;
	}
	
	//TODO: Getters and Setters Separator

	public IHistoryDAO getHistoryDAO() {
		return historyDAO;
	}

	public void setHistoryDAO(IHistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	public IIDGeneratorServis getIdGeneratorServis() {
		return idGeneratorServis;
	}
	
	public void setIdGeneratorServis(IIDGeneratorServis idGeneratorServis) {
		this.idGeneratorServis = idGeneratorServis;
	}

	public OrtakServis getOrtakServis() {
		return ortakServis;
	}

	public void setOrtakServis(OrtakServis ortakServis) {
		this.ortakServis = ortakServis;
	}

	public void deleteAllObjectHistoryByCriteria(Class entity, Long processId, Long taskId) {

		this.historyDAO.deleteAllObjectHistoryByCriteria(entity, processId, taskId);
	}

	public <T> List<T> readAllObjectHistoryByCriteria(Class entity, Long processId, Long taskId) {

		return historyDAO.readAllObjectHistoryByCriteria(entity, processId, taskId);
	}

	public BinaHst convertBuildingOrjToHst(Bina building, Long processId,
			Long taskId, String recordOwner, Date changeDate, String processType) {
		
		if (building!=null){

			BinaHst buildingHst=new BinaHst();
			
			buildingHst.setId(getIdGeneratorServis().getIDForNewObject(BinaHst.class));
			
			buildingHst.setNesneId(building.getId());
			
			buildingHst.setSurecId(processId);
			
			buildingHst.setTaskId(taskId);
			
			buildingHst.setDegisiklikYapan(recordOwner);
			
			buildingHst.setDegisiklikTarihi(changeDate);
			
			buildingHst.setDegisiklikTipi(processType);
			
			buildingHst.setKayitTarih(changeDate);
			
			buildingHst.setKaydeden(recordOwner);
			
			buildingHst.setBinaNo(building.getBinaNo());
			
			buildingHst.setApartmanBlokAd(building.getApartmanBlokAd());
			
			buildingHst.setAsansorEh(building.getAsansorEh());
			
			buildingHst.setArsaAlan(building.getArsaAlan());
			
			buildingHst.setPdaEh(building.getPdaEh());
			
			buildingHst.setAktifEh(building.getAktifEh());
			
			buildingHst.setAcilisTarih(building.getAcilisTarih());
			
			buildingHst.setKapanisTarih(building.getKapanisTarih());
			
			buildingHst.setKaloriferEh(building.getKaloriferEh());
			
			buildingHst.setPdaUserName(building.getPdaUserName());
			
			buildingHst.setPdaIslemTarihi(building.getPdaIslemTarihi());
			
			buildingHst.setPostaKodu(building.getPostaKodu());
			
			buildingHst.setZoneNo(building.getZoneNo());
			
			buildingHst.setKurumSabitId(building.getKurumSabit().getId());
			
			if (building.getYapiAda()!=null){
				
				buildingHst.setYapiAdaId(building.getYapiAda().getId());
			}
			
			
			if (building.getSite()!=null){
				
				buildingHst.setSiteId(building.getSite().getId());
			}
			

			if (building.getKadastroParsel()!=null){
				
				buildingHst.setKadastroParselId(building.getKadastroParsel().getId());
			}
			
			if (building.getAcilisHareketKod()!=null){
				
				buildingHst.setAcilisHareketKodId(building.getAcilisHareketKod().getId());
			}
			
			if (building.getKapanisHareketKod()!=null){
				
				buildingHst.setKapanisHareketKodId(building.getKapanisHareketKod().getId());
			}
			
			if (building.getUretimKaynak()!=null){
				
				buildingHst.setUretimKaynakId(building.getUretimKaynak().getId());
			}
			
			if (building.getBilgiTipi()!=null){
				
				buildingHst.setBilgiTipiId(building.getBilgiTipi().getId());
			}
			
			if (building.getBinaCatiTur()!=null){
				
				buildingHst.setBinaCatiTurId(building.getBinaCatiTur().getId());
			}
			
			if (building.getYapiCepheTur()!=null){
				
				buildingHst.setYapiCepheTurId(building.getYapiCepheTur().getId());
			}
			
			if (building.getDosemeTur()!=null){
				
				buildingHst.setDosemeTurId(building.getDosemeTur().getId());
			}
			
			if (building.getIsitmaTur()!=null){
				
				buildingHst.setIsitmaTurId(building.getInsaatTur().getId());
			}
			
			if (building.getIsitmaYakitTur()!=null){
				
				buildingHst.setIsitmaYakitTurId(building.getIsitmaYakitTur().getId());
			}
			
			if (building.getSicakSuTeminTur()!=null){
				
				buildingHst.setSicakSuTeminTurId(building.getSicakSuTeminTur().getId());
			}
			
			if (building.getSicakSuYakit()!=null){
				
				buildingHst.setSicakSuYakitId(building.getSicakSuYakit().getId());
			}
			
			if (building.getTasiyiciSistem()!=null){
				
				buildingHst.setTasiyiciSistemId(building.getTasiyiciSistem().getId());
			}
			
			if (building.getBagimsizKullanimSinif()!=null){
				
				buildingHst.setBagimsizKullanimSinifId(building.getBagimsizKullanimSinif().getId());
			}
			
			if (building.getInsaatTur()!=null){
				
				buildingHst.setInsaatTurId(building.getInsaatTur().getId());
			}
			
			if (building.getInsaatSinif()!=null){
				
				buildingHst.setInsaatSinifId(building.getInsaatSinif().getId());
			}
			
			if (building.getDuvarDolguMadde()!=null){
				
				buildingHst.setDuvarDolguId(building.getDuvarDolguMadde().getId());
			}
			
			return buildingHst;
		}
		
		return null;
	}

	public boolean saveBuildingHst(BinaHst buildingHst) {

		if (getHistoryDAO().saveBuildingHst(buildingHst)) {
			
			return true;
		}
		return false;
		
	}

	public List<BagimsizHst> convertDetachedOrjListToDetachedHstList(
			List<Bagimsiz> detachedOrjList, Long processId, Long taskId,
			String recordOwner, Date changeDate, String processType) {
		
		if (detachedOrjList!=null){
				
			List<BagimsizHst> detachedHstList=new ArrayList<BagimsizHst>();
			
			for (Bagimsiz detachedOrj : detachedOrjList) {
			
				if (detachedOrj!=null){
					
					detachedHstList.add(convertDetachedOrjToDetachedHst(detachedOrj, processId, taskId, recordOwner, changeDate, processType));
				}
			}
			
			return detachedHstList;
		}
		return null;
	}

	public BagimsizHst convertDetachedOrjToDetachedHst(Bagimsiz detachedOrj,
			Long processId, Long taskId, String recordOwner, Date changeDate,
			String processType) {

		if (detachedOrj!=null){
		
			BagimsizHst detachedHst=new BagimsizHst();
			
			detachedHst.setId(getIdGeneratorServis().getIDForNewObject(BagimsizHst.class));
			
			detachedHst.setNesneId(detachedOrj.getId());
			
			detachedHst.setSurecId(processId);
			
			detachedHst.setTaskId(taskId);
			
			detachedHst.setDegisiklikYapan(recordOwner);
			
			detachedHst.setDegisiklikTarihi(changeDate);
			
			detachedHst.setDegisiklikTipi(processType);
			
			detachedHst.setKayitTarih(changeDate);
			
			detachedHst.setKaydeden(recordOwner);
			
			detachedHst.setAktifEh(detachedOrj.getAktifEh());
			
			detachedHst.setKaloriferEh(detachedOrj.getKaloriferEh());
			
			detachedHst.setDaireNo(detachedOrj.getDaireNo());
			
			detachedHst.setAltDaireNo(detachedOrj.getAltDaireNo());
			
			detachedHst.setKatNo(detachedOrj.getKatNo());
			
			detachedHst.setBagimsizNo(detachedOrj.getBagimsizNo());
			
			detachedHst.setBagimsizAd(detachedOrj.getBagimsizAd());
			
			detachedHst.setElektrikNo(detachedOrj.getElektrikNo());
			
			detachedHst.setGazNo(detachedOrj.getGazNo());
			
			detachedHst.setSuNo(detachedOrj.getSuNo());
			
			detachedHst.setYuzolcum(detachedOrj.getYuzolcum());
			
			detachedHst.setAcilisTarih(detachedOrj.getAcilisTarih());
			
			detachedHst.setKapanisTarih(detachedOrj.getKapanisTarih());
			
			detachedHst.setTuikSiraNo(detachedOrj.getTuikSiraNo());
			
			detachedHst.setAciklama(detachedOrj.getAciklama());
			
			detachedHst.setPdaEh(detachedOrj.getPdaEh());
			
			detachedHst.setPdaUserName(detachedOrj.getPdaUserName());
			
			detachedHst.setPdaIslemTarihi(detachedOrj.getPdaIslemTarihi());
			
			detachedHst.setPdaBagimsizNo(detachedOrj.getPdaBagimsizNo());
			
			if (detachedOrj.getBilgiTipi()!=null){
				
				detachedHst.setBilgiTipi(detachedOrj.getBilgiTipi().getId());
			}
			
			if (detachedOrj.getIsitmaTur()!=null){

				detachedHst.setIsitmaTur(detachedOrj.getIsitmaTur().getId());
			}
			
			if (detachedOrj.getYakitTur()!=null){
				
				detachedHst.setYakitTur(detachedOrj.getYakitTur().getId());
			}
			
			if (detachedOrj.getKonutDurum()!=null){
				
				detachedHst.setKonutDurum(detachedOrj.getKonutDurum().getId());
			}
			
			if (detachedOrj.getKonutTip()!=null){
				
				detachedHst.setKonutTip(detachedOrj.getKonutTip().getId());
			}
			
			if (detachedOrj.getAdres()!=null){
				
				detachedHst.setAdresId(detachedOrj.getAdres().getId());
			}
			
			if (detachedOrj.getBagimsizKullanimDetay()!=null){
				
				detachedHst.setBagimsizKullanimDetay(detachedOrj.getBagimsizKullanimDetay().getId());
			}
			
			if (detachedOrj.getAcilisHareketKod()!=null){ 
				
				detachedHst.setAcilisHareketKod(detachedOrj.getAcilisHareketKod().getId());
			}
			
			if (detachedOrj.getKapanisHareketKod()!=null){
				
				detachedHst.setKapanisHareketKod(detachedOrj.getKapanisHareketKod().getId());
			}
			
			if (detachedOrj.getBina()!=null){
				
				detachedHst.setBina(detachedOrj.getBina().getId());
			}
			
			return detachedHst;
		}
		
		return null;
	}

	public boolean saveDetachedHstList(List<BagimsizHst> detachedHstList) {

		return historyDAO.saveDetachedHstList(detachedHstList);
	}
	
	
	
}
