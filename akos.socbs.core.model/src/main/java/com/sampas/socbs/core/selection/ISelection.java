package com.sampas.socbs.core.selection;

import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.display.IDisplay;
import com.sampas.socbs.core.renderer.IRenderer;
import com.sampas.socbs.core.tools.ITrackCancel;

public interface ISelection {
	
	/*
	 * Indicates if the selection can be cleared.
	 */
	public Boolean canClear();
	/*
	 * Indicates if the selection can be copied.
	 */
	public Boolean canCopy();
	/*
	 * Indicates if the selection can be cut.
	 */
	public Boolean canCut();
	/*
	 * Indicates if paste is supported.
	 */
	public Boolean canPaste();
	/*
	 * Indicates if there's something to select.
	 */
	public Boolean canSelectAll();
	/*
	 * Clears all items in the selection.
	 */
	public void clear();
	/*
	 * Copy the selected items to the clipboard.
	 */
	public void copy();
	/*
	 * Cut the selected items to the clipboard.
	 */
	public void cut();
	/*
	 * Draw the selected items.
	 */
	public void draw (IDisplay display,ITrackCancel cancelTracker);
	/*
	 * Paste clipboard contents.
	 */
	public void paste();
	/*
	 * 	Selects all items.
	 */
	public void selectAll();

	public void setFeatureCollection(IFeatureCollection featureCollection);

	public IFeatureCollection getFeatureCollection();
	
	public void setSelectedRender(IRenderer selectedRender);

	public IRenderer getSelectedRender();
}
