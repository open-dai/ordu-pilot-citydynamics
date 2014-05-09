package com.sampas.socbs.geolsa.model;

import java.util.List;

public class MdSmpGISApp {

	private long oid;
	private String appDescriptor;
	private String appName;
	private List<MdAppLayer> mdAppLayers;

	public MdSmpGISApp(){

	}

	public void finalize() throws Throwable {

	}

	public String getAppDescriptor() {
		return appDescriptor;
	}

	public void setAppDescriptor(String appDescriptor) {
		this.appDescriptor = appDescriptor;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public List<MdAppLayer> getMdAppLayers() {
		return mdAppLayers;
	}

	public void setMdAppLayers(List<MdAppLayer> mdAppLayers) {
		this.mdAppLayers = mdAppLayers;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}
	
	public MdAppLayer getFeatureLayerFromGivenMdApplicationLayers(List<MdAppLayer> givenMdAppLayers,long layerId){
	
		MdAppLayer mdAppLayer = null;
		Long id = null;

		try {

			//getMdAppLayers().get(i).getOid()
			for (int i = 0; i < givenMdAppLayers.size(); i++) {


				id = givenMdAppLayers.get(i).getOid();

				if(id == layerId){

					mdAppLayer = givenMdAppLayers.get(i);

					if(mdAppLayer.getMdLayer() != null){

						if(mdAppLayer.getMdLayer().getMdRasterLayer() != null){

							String wfsLayer = mdAppLayer.getMdLayer().getMdRasterLayer().getRasterWfsEquvalent();
							/* If There is no equivalent wfs layer*/
							if(wfsLayer == null || wfsLayer.equals("")){
								System.err.println("INFO: SMPDATATABLEROWUSEROBJECT takeLayer() NO EQUALENT WFS LAYER FOUND");
								return null;
							}

							for (int j = 0; j < givenMdAppLayers.size();j++) {

								mdAppLayer = givenMdAppLayers.get(j);

								if(mdAppLayer.getMdLayer() != null && 
										mdAppLayer.getMdLayer().getMdFeatureLayer() != null && 
											mdAppLayer.getMdLayer().getMdFeatureLayer().getMdRemoteFeatureLayer()!= null){

									if(mdAppLayer.getMdLayer().getLayerName().equals(wfsLayer)){
										return mdAppLayer;
									}

								}
							}

						}
						else if(mdAppLayer.getMdLayer().getMdFeatureLayer() != null){

							if(mdAppLayer.getMdLayer().getMdFeatureLayer().getMdRemoteFeatureLayer() != null){
								return mdAppLayer;
							}

						}

					}

				}
			}
			return null;

		}
		catch (Exception e) {
			System.err.println("ERROR : SMPDATATABLEROWUSEROBJECT metadata handle exception");
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}