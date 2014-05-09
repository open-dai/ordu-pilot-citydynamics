package com.sampas.socbs.core.tools;

public interface ITrackCancel {
	
	/*
	 * Cancels the associated operation.
	 */
	public void cancel();
	/*
	 * Indicates whether mouse clicks should cancel the operation.
	 */
	public boolean getCancelOnClick();
	/*
	 * Indicates whether mouse clicks should cancel the operation.
	 */
	public void setCancelOnClick(boolean pCancelOnClick);
	/*
	 * Indicates whether the escape key and spacebar should cancel the operation.
	 */
	public boolean getCancelOnKeyPress();
	/*
	 * Indicates whether the escape key and spacebar should cancel the operation.
	 */
	public void setCancelOnKeyPress(boolean pCancelOnKeyPress);
	/*
	 * Called frequently while associated operation is progressing. A return value of false indicates that the operation should stop.
	 */
	public boolean progress();
	/*
	 * 
	 */
	public void reset();

}
