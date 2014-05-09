package com.sampas.ortak.spatial.analysis.servis.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sampas.akos.common.exception.AkosException;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.test.BaseTestCase;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.ortak.spatial.analysis.servis.IAnalysisServis;


public class AnalysisServiceJTests extends BaseTestCase {

	private IAnalysisServis analysisServis;

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "com/sampas/akos/commonTestContext.xml" };
	}

	@Override
	protected String[] getXmlDataFileResource() {
		return null;
	}

	protected void onSetUpInTransaction() throws Exception {

		setComplete();

		setAnalysisServis((IAnalysisServis) applicationContext.getBean("analysisServis"));
	}

	public void testDWithinAnalysisForAddress() {

		Date beforeTestTime = new Date();

		Adres adres = new Adres();

		adres.setId(109087275L);

		List<BaseObject> resultListAsBaseObject;

		try {
			Map<String, List<Object>> additionalAttrbs = new HashMap<String, List<Object>>();

			List<Object> addressTypeValues = new ArrayList<Object>();

			addressTypeValues.add("T");

			additionalAttrbs.put("ADRES_TURU", addressTypeValues);

			List<Object> doorNoValues = new ArrayList<Object>();

			doorNoValues.add(1L);
			
			doorNoValues.add(2L);
			
			doorNoValues.add(3L);
			
			doorNoValues.add(4L);

			additionalAttrbs.put("KAPI_NO", doorNoValues);

			resultListAsBaseObject = getAnalysisServis().readBaseObjectListWithDWithinFunctionByBaseObjectAndAttributes(adres, additionalAttrbs, 1000d, Adres.class);

			if (resultListAsBaseObject != null) {
			
				List<Adres> resultListAsAdres = new ArrayList<Adres>();

				for (BaseObject baseObject : resultListAsBaseObject) {

					Adres tempAddress = (Adres) baseObject;

					resultListAsAdres.add(tempAddress);

					System.out.println(tempAddress.getId());
				}
				System.out.println("Total : " + resultListAsAdres.size());
			}
		} catch (AkosException ex) {

			ex.printStackTrace();
		}
		Date afterTestTime = new Date();

		System.out.println("\n--------------------------------------------------------");

		System.out.println("Test start time: " + beforeTestTime);

		System.out.println("Test end time: " + afterTestTime);

		System.out.println("\n--------------------------------------------------------");
	}

	public void testDWithinAnalysisForBuilding() {

		Date beforeTestTime = new Date();

		Adres adres = new Adres();

		adres.setId(109087275L);

		List<BaseObject> resultListAsBaseObject;

		try {

			resultListAsBaseObject = getAnalysisServis().readBaseObjectListWithDWithinFunctionByBaseObjectAndAttributes(adres, null, 50d, Bina.class);

			List<Bina> resultListAsBuilding = new ArrayList<Bina>();

			for (BaseObject baseObject : resultListAsBaseObject) {

				Bina tempBuilding = (Bina) baseObject;

				resultListAsBuilding.add(tempBuilding);

				System.out.println(tempBuilding.getId());
			}
		} catch (AkosException ex) {

			ex.printStackTrace();
		}
		Date afterTestTime = new Date();

		System.out.println("\n--------------------------------------------------------");

		System.out.println("Test start time: " + beforeTestTime);

		System.out.println("Test end time: " + afterTestTime);

		System.out.println("\n--------------------------------------------------------");
	}

	
	// TODO: Getters and Setters Separator

	public IAnalysisServis getAnalysisServis() {
		return analysisServis;
	}

	public void setAnalysisServis(IAnalysisServis analysisServis) {
		this.analysisServis = analysisServis;
	}

}
