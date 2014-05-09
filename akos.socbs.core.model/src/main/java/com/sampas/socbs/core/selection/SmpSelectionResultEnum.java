package com.sampas.socbs.core.selection;

public enum SmpSelectionResultEnum {
	/*
	 * 0 Creates a new selection.
	*/
	smpSelectionResultNew,
	/*
	 * 1 	Adds to the current selection.
	 */
	smpSelectionResultAdd,
	/*
	 * 2 	Subtracts from the current selection.
	 */
	smpSelectionResultSubtract,
	/*
	 * 3 	Selects from the current selection.
	 */
	smpSelectionResultAnd,
	/*
	 * 4 	Performs an 'exclusive or' with the current selection.
	 */
	smpSelectionResultXOR 	
	
}
