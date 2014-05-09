package com.sampas.socbs.core.gisdatabase.tools.servis.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.geotools.data.DataStore;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureSource;
import org.geotools.data.FeatureStore;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.geotools.feature.AttributeType;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.FeatureType;
import org.geotools.feature.FeatureTypeBuilder;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.identity.FeatureId;
import com.sampas.socbs.core.dataset.feature.IAttributeType;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.dataset.feature.IFeatureType;
import com.sampas.socbs.core.dataset.feature.IFeatureTypeName;
import com.sampas.socbs.core.dataset.feature.impl.SmpAttributeType;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeature;
import com.sampas.socbs.core.dataset.feature.impl.SmpFeatureCollection;
import com.sampas.socbs.core.gisdatabase.tools.servis.IGisDatabaseServis;


@SuppressWarnings("unchecked")
public class GisDatabaseServis implements IGisDatabaseServis {
	
	private DataStore dataStore;
	
	
	public GisDatabaseServis(DataStore dataStore) {
		
		if (dataStore != null) {
			
			this.dataStore = dataStore;
		}
	}
	
	public boolean createFeatureType(IFeatureTypeName newFeatureTypeName, List<IAttributeType> attributeTypes) {
		
		boolean existingSchema = false;
		
		try {
			
			if (this.dataStore != null && (newFeatureTypeName != null && newFeatureTypeName.getFeatureTypeName() != "") && (attributeTypes != null && attributeTypes.size() != 0)) {
				
				try {
					
					this.dataStore.getSchema(newFeatureTypeName.getFeatureTypeName());
					
					existingSchema = true;
				} catch (Exception ex) {
					
					existingSchema = false;
				}
				if (!existingSchema) {
					
					AttributeType[] attributeTypeArray = new AttributeType[attributeTypes.size()];
					
					for (int i = 0; i < attributeTypes.size(); i++) {
						
						attributeTypeArray[i] = ((SmpAttributeType)attributeTypes.get(i)).getGeoToolsAttributeType();
					}
//	//-----------------------------------------------------------------------------------------------------------------
//					//Manual test section
//					AttributeType geometry = AttributeTypeFactory.newAttributeType("GEOMETRY", Polygon.class);
//					AttributeType districtNo = AttributeTypeFactory.newAttributeType("MAHALLE_ADI", String.class);
//					AttributeType districtCode = AttributeTypeFactory.newAttributeType("MAHALLE_KODU", Integer.class);
//					AttributeType date = AttributeTypeFactory.newAttributeType("TARIH", Date.class);
//					FeatureType district = FeatureTypeBuilder.newFeatureType(new AttributeType[] {geometry, districtNo, districtCode, date}, "SOA_GRF_MAHALLE_TEST");
//					dataStore.createSchema(district);
//	//-----------------------------------------------------------------------------------------------------------------
					FeatureType featureType = FeatureTypeBuilder.newFeatureType(attributeTypeArray, newFeatureTypeName.getFeatureTypeName());
					
					this.dataStore.createSchema(featureType);
					
					return true;
				
				} else {
					
					System.out.println("Feature Type : " + newFeatureTypeName.getFeatureTypeName() + " is still created ! Can't create with same feature type name !");
				}
			} else {
				
				System.out.println("Error on input parameters ! Be sure parameters are not null or empty");
			}
		} catch (Exception ex) {
			
			System.out.println("Error on creating feature type to feature store ERROR : " + ex);
		}
		return false;
	}
	
	public IFeatureCollection readFeatures(IFeatureTypeName featureTypeName) {
		
		try {
			
			FeatureSource featureStore = this.dataStore.getFeatureSource(featureTypeName.getFeatureTypeName());
			
			FeatureCollection geoFeatures = featureStore.getFeatures();
			
			IFeatureCollection features = new SmpFeatureCollection(geoFeatures);
			
			return (features);
			
		} catch (Exception ex) {
			
			System.out.println("Error on reading features from feature store ERROR : " + ex);
			
			return null;
		}
	}
	
	public boolean writeFeatures(IFeatureCollection features) {

		if (features != null && features.getFeaturesCount() > 0) {
			
			DefaultTransaction transaction = new DefaultTransaction();
			
			try {
				
				IFeatureType featureType = features.getFeatureAt(0).getFeatureType();
				
				FeatureStore featureStore = (FeatureStore)this.dataStore.getFeatureSource(featureType.getFeatureType());

				featureStore.setTransaction(transaction);
					
				if (((SmpFeatureCollection)features) != null &&((SmpFeatureCollection)features).getGeoToolsFeatureCollection() != null) {
					
					featureStore.addFeatures(((SmpFeatureCollection)features).getGeoToolsFeatureCollection());
				} else if (((SmpFeatureCollection)features).getFeaturesCount() > 0) {
					
					FeatureCollection tempFeatures = FeatureCollections.newCollection("insert");
					
					for (int featureCntr = 0; featureCntr < ((SmpFeatureCollection)features).getFeaturesCount(); featureCntr++) {
						
						tempFeatures.add(((SmpFeature)(((SmpFeatureCollection)features).getFeatureAt(featureCntr))).getGeoToolFeature());
					}
					featureStore.addFeatures(tempFeatures);
				} else {
					transaction.close();
					
					return false;	
				}
		        transaction.commit();
		        
		        transaction.close();
		        
		        return true;
			} catch (Exception ex) {
				
				System.out.println("Error on updating features to feature store ERROR : " + ex);
				
				ex.printStackTrace();
				
				try {
					
					transaction.rollback();
					
					transaction.close();
				} catch (Exception exc) {
					
					System.out.println("Error on rolling back update transaction ! ERROR : " + exc);
				}
			}
		}
		return false;
	}
	
	public boolean writeFeature(IFeature feature) {
		
		if (feature != null) {
			
			return this.writeFeature(feature);
		} else {
			return false;
		}
	}
	
	public boolean updateFeature(IFeature feature) {

		if (feature != null) {
			
			IFeatureCollection tempFeatures = new SmpFeatureCollection();
			
			tempFeatures.addFeature(feature);
			
			return this.updateFeatures(tempFeatures);
		}
		return false;
	}
	
	public boolean deleteFeature(IFeature feature) {

		if (feature != null) {
			
			IFeatureCollection tempFeatures = new SmpFeatureCollection();
			
			tempFeatures.addFeature(feature);
			
			return this.deleteFeatures(tempFeatures);
		}
		return false;
	}
	
	public boolean updateFeatures(IFeatureCollection features) {

		if (features != null && features.getFeaturesCount() > 0) {
		
			DefaultTransaction transaction = new DefaultTransaction();
			
			try {
				
				IFeatureType featureType = features.getFeatureAt(0).getFeatureType();
				
				FeatureStore featureStore = (FeatureStore)this.dataStore.getFeatureSource(featureType.getFeatureType());

				featureStore.setTransaction(transaction);
				
				FilterFactory2 filterFactory = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());		
				
				Set<FeatureId> fids = new HashSet<FeatureId>();		
				
				for (int ftrCntr = 0; ftrCntr < features.getFeaturesCount(); ftrCntr++) {
					
					fids.clear();
					
					fids.add(filterFactory.featureId(features.getFeatureAt(ftrCntr).getID()));
					
					featureStore.modifyFeatures(((SmpFeature)features.getFeatureAt(ftrCntr)).getFeatureType().getAttributeType("GEOMETRY"), features.getFeatureAt(ftrCntr).getAttribute("GEOMETRY"), filterFactory.id(fids));
				}
		        transaction.commit();
		        
		        transaction.close();
		        
		        return true;
			} catch (Exception ex) {
				
				System.out.println("Error on updating features to feature store ERROR : " + ex);
				
				ex.printStackTrace();
				
				try {
					
					transaction.rollback();
					
					transaction.close();
				} catch (Exception exc) {
					
					System.out.println("Error on rolling back update transaction ! ERROR : " + exc);
				}
			} 
		}
		return false;
	}
	
	public boolean deleteFeatures(IFeatureCollection features) {
		if (features != null && features.getFeaturesCount() > 0) {
			
			DefaultTransaction transaction = new DefaultTransaction();
			
			try {
				
				IFeatureType featureType = features.getFeatureAt(0).getFeatureType();
				
				FeatureStore featureStore = (FeatureStore)this.dataStore.getFeatureSource(featureType.getFeatureType());

				featureStore.setTransaction(transaction);
				
				FilterFactory2 filterFactory = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());		
				
				Set<FeatureId> fids = new HashSet<FeatureId>();		
				
				for (int ftrCntr = 0; ftrCntr < features.getFeaturesCount(); ftrCntr++) {
					
					fids.add(filterFactory.featureId(features.getFeatureAt(ftrCntr).getID()));
					
				}
				
				featureStore.removeFeatures(filterFactory.id(fids));
				
		        transaction.commit();
		        
		        transaction.close();
		        
		        return true;
			} catch (Exception ex) {
				
				System.out.println("Error on deleting features to feature store ERROR : " + ex);
				ex.printStackTrace();
				
				try {
					
					transaction.rollback();
				} catch (Exception exc) {
					
					System.out.println("Error on rolling back delete transaction ! ERROR : " + exc);
				}
			} finally {
				
				transaction.close();
			}
		}
		return false;
	}

}
//	public boolean writeFeatures(IFeatureTypeName newFeatureTypeName, IFeatureCollection features) {
	//
//			if (newFeatureTypeName != null && newFeatureTypeName.getFeatureTypeName() != "" && features != null && features.getFeaturesCount() > 0) {
//				
//				DefaultTransaction transaction = new DefaultTransaction();
//				
//				try {
//					
//					int matchedAttCount = 0;
//					
//					if (newFeatureTypeName != null && newFeatureTypeName.getFeatureTypeName() != "" && features != null && features.getFeaturesCount() != 0) {
//					
//						FeatureType baseFeatureType = this.dataStore.getSchema(newFeatureTypeName.getFeatureTypeName());
//						
//						for (int i = 0; i < baseFeatureType.getAttributeCount(); i++) {
//							
//							for (int j = 0; j < features.getFeatureAt(0).getNumberOfAttributes(); j++) {
//								
//								if(baseFeatureType.getAttributeType(i).getLocalName().equals(((SmpFeatureCollection)(features)).getFeatureAt(0).getFeatureType().getAttributeType(j).getLocalName()) && baseFeatureType.getAttributeType(i).getBinding().equals(((SmpFeatureCollection)(features)).getFeatureAt(0).getFeatureType().getAttributeType(j).getBinding())) {
//									
//									matchedAttCount++;
//								} 
//							}
//						}
//						
//						if (baseFeatureType.getAttributeCount() == (((SmpFeatureCollection)(features)).getFeatureAt(0).getFeatureType().getAttributeCount()) && baseFeatureType.getAttributeCount() == matchedAttCount) {
//							
//							FeatureStore featureStore = (FeatureStore)this.dataStore.getFeatureSource(newFeatureTypeName.getFeatureTypeName());
//							
//							featureStore.setTransaction(transaction);
//		//					FeatureSource featureSource = this.dataStore.getFeatureSource(newFeatureTypeName.getFeatureTypeName());
//		//					
//		//					FeatureStore featureStore = null;
//		//					
//		//					if( featureSource instanceof FeatureLocking ){
//		//						
//		//						featureStore = (FeatureStore) featureSource;
//		//					 }
//		//					 else {
//		//						 
//		//					     System.out.println("Not write access to layer !");
//		//					     return false;
//		//					 }
//							
//							DefaultFeatureCollection featureCol = new DefaultFeatureCollection(null, baseFeatureType);
//							
//							for (int i = 0; i < features.getFeaturesCount(); i++) {
//							
//								featureCol.add(((SmpFeature)features.getFeatureAt(i)).getGeoToolFeature());
//							}
//							
//							featureStore.addFeatures(featureCol);
//							
//					        transaction.commit();
//					        transaction.close();
//					        return true;
//							
//						} else {
//							
//							System.out.println("Error, attribute type counts must bu same with featureType attribute count !");				
//							return false;
//						}
//						
//		//				//Can change another good solution about features.getSchema
//		//				short mode = 0;
//		//				
//		//				try {
//		//					
//		//					features.getSchema();
//		//					
//		//				} catch (Exception ex) {
//		//					
//		//					mode = 1;
//		//				}
//		//				
//		//				if (mode == 0) {
//		//					
//		//					for (int i = 0; i < baseFeatureType.getAttributeCount(); i++) {
//		//						
//		//						for (int j = 0; j < features.getSchema().getAttributeCount(); j++) {
//		//							
//		//							if(baseFeatureType.getAttributeType(i).getLocalName().equals(((SmpFeatureType)(features.getSchema())).getGeoToolsFeatureType().getAttributeType(j).getLocalName()) && baseFeatureType.getAttributeType(i).getBinding().equals(((SmpFeatureType)(features.getSchema())).getGeoToolsFeatureType().getAttributeType(j).getBinding())) {
//		//								
//		//								matchedAttCount++;
//		//							} 
//		//						}
//		//					}
//		//					
//		//					if (baseFeatureType.getAttributeCount() == ((SmpFeatureType)(features.getSchema())).getGeoToolsFeatureType().getAttributeCount() && baseFeatureType.getAttributeCount() == matchedAttCount) {
//		//						
//		//						FeatureWriter writer = this.dataStore.getFeatureWriter(newFeatureTypeName.getFeatureTypeName(), transaction);
//		//						
//		//				        for (int i = 0; i < features.getFeaturesCount(); i++) {
//		//							
//		//				        	 Feature feature = writer.next();
//		//				        	 
//		//				        	 for (int j = 0; j < features.getFeatureAt(i).getNumberOfAttributes(); j++) {
//		//				        		 
//		//				        		 if (features.getFeatureAt(i).getAttribute(j) != null) {
//		//										
//		//									feature.setAttribute(j, features.getFeatureAt(i).getAttribute(j));
//		//				        		 }
//		//							 }
//		//				        	 
//		//				             System.out.println(feature);
//		//				             
//		//				             writer.write();			             
//		//						}
//		//				        writer.close();
//		//				        transaction.commit();
//		//				        
//		//				        return true;
//		//						
//		//					} else {
//		//						
//		//						System.out.println("Error, attribute type counts must bu same with featureType attribute count !");				
//		//						return false;
//		//					}
//		//					
//		//				} else if (mode == 1) {
//		//					
//		//				}
//						
//					} else {
//						
//						System.out.println("Error, feature type name or features can't be null or empty !");				
//						return false;
//					}	
//					
//				} catch (Exception ex) {
	//	
//					System.out.println("Error on writing features to feature store ERROR : " + ex);
//					ex.printStackTrace();
//					
//					try {
//						
//						transaction.rollback();
//						
//					} catch (Exception exc) {
//						System.out.println("Error on rolling back write transaction ! ERROR : " + exc);
//					}
//					
//					return false;
//				}
//			} else {
//				
//				return false;
//			}
//		}
