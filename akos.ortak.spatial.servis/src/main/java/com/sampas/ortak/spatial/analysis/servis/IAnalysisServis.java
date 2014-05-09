package com.sampas.ortak.spatial.analysis.servis;

import java.util.List;
import java.util.Map;
import com.sampas.akos.common.exception.AkosException;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.socbs.core.geometry.IGeometry;


public interface IAnalysisServis {
	
	

	public List<BaseObject> readBaseObjectListWithDWithinFunctionByBaseObjectAndAttributes(BaseObject refBaseObject, Map<String, List<Object>> attributesMap, Double distance, Class<?> returnBaseObjectClass) throws AkosException;
	
	public List<BaseObject> readBaseObjectListWithDWithinFunctionByGeometryAndAttributes(IGeometry geometry, Map<String, List<Object>> attributesMap, Double distance, Class<?> returnBaseObjectClass) throws AkosException;
	
}
