package com.sampas.socbs.core.dataset.feature.impl;

import java.util.List;

import org.geotools.factory.CommonFactoryFinder;
import org.geotools.factory.GeoTools;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;

import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.dataset.feature.IFilterBuilder;
import com.sampas.socbs.core.dataset.filter.impl.SmpFilter;

public class SmpFilterBuilder implements IFilterBuilder {

	public SmpFilterBuilder() {
				
	}
	/**
	 * Combines filters with "and"
	 * @param smpFilterBase
	 * @param smpFilterExtend
	 * @return
	 */
	public SmpFilter addAndFilter(SmpFilter smpFilterBase, SmpFilter smpFilterExtend) {
		
		try {
		
			FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );

			Filter filter = filterFactory2.and(smpFilterBase.getGeoToolsFilter(), smpFilterExtend.getGeoToolsFilter());
			
			SmpFilter smpFilterTotal = new SmpFilter(filter);
		
			return (smpFilterTotal);
		} catch (Exception ex) {
			
			return null;
		}
	}
	
	/**
	 * Combines filters with "or"
	 * @param smpFilterBase
	 * @param smpFilterExtend
	 * @return
	 * 
	 */
	public SmpFilter addOrFilter(SmpFilter smpFilterBase, SmpFilter smpFilterExtend) {
		
		try {
			
			FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );

			Filter filter = filterFactory2.or(smpFilterBase.getGeoToolsFilter(), smpFilterExtend.getGeoToolsFilter());
				
			SmpFilter smpFilterTotal = new SmpFilter(filter);
			
			return (smpFilterTotal);
		} catch (Exception ex) {
				
			return null;
		}
	}
	
	/**
	 * Makes filter as a "not" filter
	 * @param smpFilter
	 * @return
	 */
	public SmpFilter addNotToFilter(SmpFilter smpFilterBase) {
		
		try {
			
			FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );

			Filter filter = filterFactory2.not(smpFilterBase.getGeoToolsFilter());
				
			SmpFilter smpFilterTotal = new SmpFilter(filter);
			
			return (smpFilterTotal);
		} catch (Exception ex) {
				
			return null;
		}
	}
	
	public IFilter addAndFilter(IFilter smpFilterBase, IFilter smpFilterExtend) {
		
		try {
		
			FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );

			Filter filter = filterFactory2.and(((SmpFilter)smpFilterBase).getGeoToolsFilter(), ((SmpFilter)smpFilterExtend).getGeoToolsFilter());
			
			SmpFilter smpFilterTotal = new SmpFilter(filter);
		
			return (smpFilterTotal);
		} catch (Exception ex) {
			
			return null;
		}
	}
	
	public IFilter addOrFilter(IFilter smpFilterBase, IFilter smpFilterExtend) {
		
		try {
			
			FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );

			Filter filter = filterFactory2.or(((SmpFilter)smpFilterBase).getGeoToolsFilter(), ((SmpFilter)smpFilterExtend).getGeoToolsFilter());
				
			SmpFilter smpFilterTotal = new SmpFilter(filter);
			
			return (smpFilterTotal);
		} catch (Exception ex) {
				
			return null;
		}
	}
	
	public IFilter addNotToFilter(IFilter smpFilterBase) {
		
		try {
			
			FilterFactory2 filterFactory2 = CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );

			Filter filter = filterFactory2.not(((SmpFilter)smpFilterBase).getGeoToolsFilter());
				
			SmpFilter smpFilterTotal = new SmpFilter(filter);
			
			return (smpFilterTotal);
		} catch (Exception ex) {
				
			return null;
		}
	}
	
	public IFilter addAndFilterList(List<IFilter> smpFilters) {
		
		if (smpFilters.size() == 0) {
			return null;
		}
		
		if (smpFilters.size() == 1) {
			return smpFilters.get(0);
		}
		
		if (smpFilters.size() >= 2) {
			
			try {
				
				IFilter totalFilter = new SmpFilter();
				
				IFilterBuilder filterBuilder = new SmpFilterBuilder();
				
				totalFilter = filterBuilder.addAndFilter(smpFilters.get(0), smpFilters.get(1));
				
				for (int i = 2; i < smpFilters.size(); i++) {
					
					totalFilter = filterBuilder.addOrFilter(totalFilter, smpFilters.get(i));
				}
				
				return (totalFilter);
			} catch (Exception ex) {
				
				return null;
			}
		}
		return null;
	}
	
	public IFilter addOrFilterList(List<IFilter> smpFilters) {
		
		if (smpFilters.size() == 0) {
			return null;
		}
		
		if (smpFilters.size() == 1) {
			return smpFilters.get(0);
		}
		
		if (smpFilters.size() >= 2) {
			
			try {
				
				IFilter totalFilter = new SmpFilter();
				
				IFilterBuilder filterBuilder = new SmpFilterBuilder();
				
				totalFilter = filterBuilder.addOrFilter(smpFilters.get(0), smpFilters.get(1));
				
				for (int i = 2; i < smpFilters.size(); i++) {
					
					totalFilter = filterBuilder.addOrFilter(totalFilter, smpFilters.get(i));
				}
				
				return (totalFilter);
			} catch (Exception ex) {
				
				return null;
			}
		}
		return null;
	}
	
	
	
}
