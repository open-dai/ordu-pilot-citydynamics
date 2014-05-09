package com.sampas.ortak.spatial.analysis.servis.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.sampas.akos.common.exception.AkosException;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.ortak.servis.OrtakServis;
import com.sampas.ortak.spatial.analysis.servis.IAnalysisServis;
import com.sampas.ortak.spatial.servis.IOrtakSpatialServis;
import com.sampas.ortak.spatial.servis.tools.IAppFeatureLayer;
import com.sampas.socbs.base.spatial.query.services.impl.SmpDwithinQuery;
import com.sampas.socbs.base.spatial.query.services.impl.SmpEqualsQuery;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.feature.IFilterBuilder;
import com.sampas.socbs.core.dataset.feature.impl.SmpFilterBuilder;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.maplayer.impl.SmpLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayerAttribute;
import com.sampas.socbs.core.tools.impl.FeatureIDTools;


public class AnalysisServis implements IAnalysisServis {

	private IOrtakSpatialServis ortakSpatialServis;

	private OrtakServis ortakServis;

	private FeatureIDTools featureIDTools = new FeatureIDTools();

	private final static String DEFAULT_DISTANCE_UNIT = "m";

	private List<IAppFeatureLayer> layerList;

	
	public List<BaseObject> readBaseObjectListWithDWithinFunctionByBaseObjectAndAttributes(BaseObject refBaseObject, Map<String, List<Object>> attributesMap, Double distance, Class<?> returnBaseObjectClass) throws AkosException {

		IAppFeatureLayer appFeatureLayerForRefDbObject = null;

		String refDbObjectClassName = refBaseObject.getClass().getName();

		for (IAppFeatureLayer tempAppFeatureLayer : getLayerList()) {

			if (tempAppFeatureLayer.getLayerRelatedBaseObjectName().equals(refDbObjectClassName)) {

				appFeatureLayerForRefDbObject = tempAppFeatureLayer;

				break;
			}
		}
		if (appFeatureLayerForRefDbObject == null) {

			throw new AkosException("com.sampas.ortak.spatial.analysis.servis.impl.AnalysisServis.relatedLayerNotFound", new Object[] { refDbObjectClassName });
		}
		Method getIdMethod = null;

		for (Method tmpGetIdMethod : refBaseObject.getClass().getMethods()) {

			if (tmpGetIdMethod.getName().equals("getId")) {

				getIdMethod = tmpGetIdMethod;

				break;
			}
		}
		Long refDbObjectId = null;

		if (getIdMethod == null) {

			throw new AkosException("com.sampas.ortak.spatial.analysis.servis.impl.AnalysisServis.idPropertyNotFoundForObject", new Object[] { refDbObjectClassName });
		} else {

			try {

				refDbObjectId = (Long) getIdMethod.invoke(refBaseObject);
			} catch (Exception ex) {

				System.out.println("Error on reflecting ID attribute from BaseObject ! ERROR: " + ex.getMessage());
			}
		}
		if (refDbObjectId != null && refDbObjectId.longValue() > 0) {

			IFeature refBaseObjectFeature = appFeatureLayerForRefDbObject.getFeatureLayer().getFeaturesbyFID(featureIDTools.createFeatureIDWithFTypeAndID(appFeatureLayerForRefDbObject.getFeatureLayer().getName(), refDbObjectId));

			if (refBaseObjectFeature != null && refBaseObjectFeature.getDefaultGeometry() != null) {

				return readBaseObjectListWithDWithinFunctionByGeometryAndAttributes(refBaseObjectFeature.getDefaultGeometry(), attributesMap, distance, returnBaseObjectClass);

			} else {

				throw new AkosException("com.sampas.ortak.spatial.analysis.servis.impl.AnalysisServis.baseObjectGeometryIsEmty", new Object[] { refDbObjectClassName + " ID :" + refDbObjectId.toString() });
			}
		}
		return null;
	}

	public List<BaseObject> readBaseObjectListWithDWithinFunctionByGeometryAndAttributes(IGeometry geometry, Map<String, List<Object>> attributesMap, Double distance, Class<?> returnBaseObjectClass) throws AkosException {

		IAppFeatureLayer appFeatureLayerForReturnDbObject = null;

		String returnDbObjectClassName = returnBaseObjectClass.getName();

		for (IAppFeatureLayer tempAppFeatureLayer : getLayerList()) {

			if (tempAppFeatureLayer.getLayerRelatedBaseObjectName().equals(returnDbObjectClassName)) {

				appFeatureLayerForReturnDbObject = tempAppFeatureLayer;

				break;
			}
		}
		if (appFeatureLayerForReturnDbObject == null) {

			throw new AkosException("com.sampas.ortak.spatial.analysis.servis.impl.AnalysisServis.relatedLayerNotFound", new Object[] { returnDbObjectClassName });
		}
		IFilterBuilder filterBuilder = new SmpFilterBuilder();

		SmpDwithinQuery smpDwithinQuery = new SmpDwithinQuery();

		IFilter dwithinFilter = smpDwithinQuery.dwithinFilter(appFeatureLayerForReturnDbObject.getFeatureLayer(), geometry, distance, DEFAULT_DISTANCE_UNIT);

		if (attributesMap != null) {

			for (String tmpAttributeKey : attributesMap.keySet()) {

				List<IFilter> attributeFilterList = new ArrayList<IFilter>();

				List<Object> attributeValues = attributesMap.get(tmpAttributeKey);

				SmpLayerAttribute layetAttribute = new SmpLayerAttribute();

				layetAttribute.setAttributeColumnName(tmpAttributeKey);

				if (attributeValues != null && attributeValues.size() > 0) {

					for (Object attributeValue : attributeValues) {

						SmpEqualsQuery smpEqualsQuery = new SmpEqualsQuery();

						IFilter attributeFilter = smpEqualsQuery.equalsFilter((SmpLayer) appFeatureLayerForReturnDbObject.getFeatureLayer(), layetAttribute, attributeValue);

						attributeFilterList.add(attributeFilter);
					}

					IFilter filterForAttributte = filterBuilder.addOrFilterList(attributeFilterList);

					dwithinFilter = filterBuilder.addAndFilter(dwithinFilter, filterForAttributte);
				}
			}
		}
		IFeatureCollection resultFeatures = appFeatureLayerForReturnDbObject.getFeatureLayer().getFeaturesByBRectAndFilter(null, dwithinFilter);

		List<Long> returnBaseObjectIdList = new ArrayList<Long>();

		if (resultFeatures != null && resultFeatures.getFeaturesCount() > 0) {

			for (int i = 0; i < resultFeatures.getFeaturesCount(); i++) {

				if (i>999) break;
					
				IFeature tmpFeature = resultFeatures.getFeatureAt(i);

				returnBaseObjectIdList.add(getFeatureIDTools().getFeatureIDFromStr(tmpFeature.getID()));
			}
			return getOrtakServis().readAllObjectByIdList(returnBaseObjectClass, returnBaseObjectIdList);
		}
		return null;
	}

	
	// TODO: Getters and Setters Separator

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

	public FeatureIDTools getFeatureIDTools() {
		return featureIDTools;
	}

	public void setFeatureIDTools(FeatureIDTools featureIDTools) {
		this.featureIDTools = featureIDTools;
	}

	public List<IAppFeatureLayer> getLayerList() {

		if (layerList == null) {
			
			layerList = getOrtakSpatialServis().getLayers().getAppLayers();
		}
		return layerList;
	}

}
