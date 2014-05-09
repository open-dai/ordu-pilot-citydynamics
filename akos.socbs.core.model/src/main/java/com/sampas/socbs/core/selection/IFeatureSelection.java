package com.sampas.socbs.core.selection;

import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFilter;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IColor;

public interface IFeatureSelection extends ISelection{

	/*
	 * Adds a feature to the selection set.
	 */
	public void add(IFeature Feature);
	/*
	 * Clears the selection.
	 */
	public void clear();
	/*
	 * Combination method for the selection.
	 */
	public void setCombinationMethod(SmpSelectionResultEnum Method);
	/*
	 * Combination method for the selection.
	 */
	public SmpSelectionResultEnum getCombinationMethod();
	/*
	 * Selects features based upon the specifed criteria and combination method.
	 */
	public void selectFeatures (IFilter filter,SmpSelectionResultEnum method,boolean justOne);
	/*
	 * 	Fires the features layer update event. Required when SelectionSet changes.
	 */
	public void selectionChanged();
	/*
	 * Selection color. (used when SetSelectionSymbol = FALSE).
	 */
	public void setSelectionColorByRef(IColor Color);
	/*
	 * 	The selected set of features.
	 */
	public ISelectionSet getSelectionSet();
	/*
	 * 	The selected set of features.
	 */
	public void setSelectionSet(ISelectionSet selectionSet);
	/*
	 * Selection symbol.
	 */
	public ISymbol getSelectionSymbol();
	/*
	 * Selection symbol.
	 */
	public void setSelectionSymbol(ISymbol symbol);
	/*
	 * Indicates if the selected set of features is drawn using the SelectionSymbol.
	 */
	public  boolean useSelectionSymbol();
	/*
	 * Indicates if the selected set of features is drawn using the SelectionSymbol.
	 */
	public  void setUseSelectionSymbol(boolean useSelectionSymbol);

}
