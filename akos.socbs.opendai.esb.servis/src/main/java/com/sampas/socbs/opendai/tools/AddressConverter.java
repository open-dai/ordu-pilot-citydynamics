package com.sampas.socbs.opendai.tools;

import com.sampas.akos.idariyapi.model.Il;
import com.sampas.akos.idariyapi.model.Ilce;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.gis.ortak.basic.model.SmpComponentType;
import com.sampas.gis.ortak.basic.model.world.SmpAddress;



public class AddressConverter {

	
	public static SmpAddress contertAdresToSmpAdres(Adres address, Il city, Ilce county) {
		
		if (address == null) {
			
			return null;
		} else {
			
			SmpAddress resultSmpAddress = new SmpAddress();
			
			if (address.getId() != null) {
				
				resultSmpAddress.setId(address.getId());
			}
			if (address.getBina() != null && address.getBina().getId() != null) {
				
				SmpComponentType buildingComponentType = new SmpComponentType();
				
				buildingComponentType.setId(address.getBina().getId());
				
				if (address.getBina().getApartmanBlokAd() != null) {
					
					buildingComponentType.setDescription(address.getBina().getApartmanBlokAd());
				} else {
					
					buildingComponentType.setDescription("Bilinmeyen");
				}
				resultSmpAddress.setBuilding(buildingComponentType);
			}
			if (address.getKapiNo() != null) {

				resultSmpAddress.setBuildingNumber(address.getKapiNo().toString());
			}
			if (address.getAltKapiNo() != null) {

				resultSmpAddress.setBuildingSubNumber(address.getAltKapiNo().toString());
			}
			if (address.getMahalleCaddeSokak() != null && address.getMahalleCaddeSokak().getCaddeSokak() != null) {
				
				SmpComponentType streetComponentType = new SmpComponentType();
				
				streetComponentType.setId(address.getMahalleCaddeSokak().getCaddeSokak().getId());
				
				if (address.getMahalleCaddeSokak().getCaddeSokak().getAciklama() != null) {
					
					streetComponentType.setDescription(address.getMahalleCaddeSokak().getCaddeSokak().getAciklama());
				}
				resultSmpAddress.setStreet(streetComponentType);
			}
			if (address.getMahalleCaddeSokak() != null && address.getMahalleCaddeSokak().getMahalle() != null) {
				
				SmpComponentType districtComponentType = new SmpComponentType();
				
				districtComponentType.setId(address.getMahalleCaddeSokak().getMahalle().getId());
				
				if (address.getMahalleCaddeSokak().getMahalle().getAciklama() != null) {
					
					districtComponentType.setDescription(address.getMahalleCaddeSokak().getMahalle().getAciklama());
				}
				resultSmpAddress.setDistrict(districtComponentType);
			}
			if (county != null) {
				
				SmpComponentType countyComponentType = new SmpComponentType();
				
				countyComponentType.setId(county.getId());
				
				if (county.getAciklama() != null) {
					
					countyComponentType.setDescription(county.getAciklama());
				}
				resultSmpAddress.setCounty(countyComponentType);
			}
			if (city != null) {
				
				SmpComponentType cityComponentType = new SmpComponentType();
				
				cityComponentType.setId(city.getId());
				
				if (city.getAciklama() != null) {
					
					cityComponentType.setDescription(city.getAciklama());
				}
				resultSmpAddress.setCity(cityComponentType);
			}
			//Generate a free format address
			String freeFormatAddress = "";
			
			if (resultSmpAddress.getDistrict() != null && resultSmpAddress.getDistrict().getDescription() != null) {
				
				freeFormatAddress += resultSmpAddress.getDistrict().getDescription() + " mh. ";
			}
			if (resultSmpAddress.getStreet() != null && resultSmpAddress.getStreet().getDescription() != null) {
				
				freeFormatAddress += resultSmpAddress.getStreet().getDescription() + " cd. ";
			}
			if (resultSmpAddress.getBuilding() != null && resultSmpAddress.getBuilding().getDescription() != null) {
				
				freeFormatAddress += resultSmpAddress.getBuilding().getDescription() + " apt. ";
			}
			if (resultSmpAddress.getBuildingNumber() != null) {
				
				freeFormatAddress += "No:" + resultSmpAddress.getBuildingNumber();
				
				if (resultSmpAddress.getBuildingSubNumber() != null) {
					
					freeFormatAddress += "/" + resultSmpAddress.getBuildingSubNumber() + " ";
				}
			}
			if (resultSmpAddress.getCounty() != null && resultSmpAddress.getCounty().getDescription() != null) {
				
				freeFormatAddress += resultSmpAddress.getCity().getDescription() + "/";
			}
			if (resultSmpAddress.getCity() != null && resultSmpAddress.getCity().getDescription() != null) {
				
				freeFormatAddress += resultSmpAddress.getCity().getDescription();
			}
			//TODO: il ilce gerekli
			resultSmpAddress.setFreeFormatAddress(freeFormatAddress);
			
			return resultSmpAddress;
		}
	}
	
}
