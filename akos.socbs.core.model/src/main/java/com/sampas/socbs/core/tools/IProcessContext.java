package com.sampas.socbs.core.tools;

/**
 * @version 1.0
 * @created 07-Kas-2008 13:35:44
 */
public interface IProcessContext {

	public void cancel();

	public void process();

	public boolean getCancelOnClick();

	public boolean getCancelOnKeyPress();

	public IProgressDisplayer getProgressDisplayer();

	public long getTimeInterval();

	public void reset();

	/**
	 * 
	 * @param cancelOnClick
	 */
	public void setCancelOnClick(boolean cancelOnClick);

	/**
	 * 
	 * @param cancelOnKeyPress
	 */
	public void setCancelOnKeyPress(boolean cancelOnKeyPress);

	/**
	 * 
	 * @param progressDisplayer
	 */
	public void setProgressDisplayer(IProgressDisplayer progressDisplayer);

	/**
	 * 
	 * @param interval
	 */
	public void setTimeInterval(long interval);

}