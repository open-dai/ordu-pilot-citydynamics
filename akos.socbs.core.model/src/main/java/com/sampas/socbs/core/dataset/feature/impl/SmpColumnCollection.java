package com.sampas.socbs.core.dataset.feature.impl;

import java.util.ArrayList;
import java.util.List;

import com.sampas.socbs.core.dataset.table.IColumn;
import com.sampas.socbs.core.dataset.table.IColumnCollection;


@SuppressWarnings("unchecked")
public class SmpColumnCollection implements IColumnCollection {
	
	List<IColumn> columnList = new ArrayList<IColumn>();  
	
	
	public SmpColumnCollection(List fields){
		
		
	}

	public int findColumn(String columnName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public IColumn getColumnAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getColumnCollectionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
