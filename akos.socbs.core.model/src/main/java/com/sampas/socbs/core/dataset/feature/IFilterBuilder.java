package com.sampas.socbs.core.dataset.feature;

import java.util.List;

public interface IFilterBuilder {

	public IFilter addAndFilter(IFilter smpFilterBase, IFilter smpFilterExtend);
	
	public IFilter addOrFilter(IFilter smpFilterBase, IFilter smpFilterExtend);
	
	public IFilter addNotToFilter(IFilter smpFilter);
	
	public IFilter addAndFilterList(List<IFilter> smpFilters);
	
	public IFilter addOrFilterList(List<IFilter> smpFilters);
	
}
