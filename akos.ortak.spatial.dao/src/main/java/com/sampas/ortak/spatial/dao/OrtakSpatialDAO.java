package com.sampas.ortak.spatial.dao;

import java.util.ArrayList;
import java.util.List;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.ortak.spatial.dao.IOrtakSpatialDAO;


public class OrtakSpatialDAO extends BaseDaoSupport implements IOrtakSpatialDAO {

	public boolean saveObjects(Object[] objects) {
		
		if (objects != null && objects.length > 0) {
			
			for (Object saveObject : objects) {
			
				if (saveObject == null) {
					
					System.out.println("Error on saving object, one or more object(s) is null !");
					return false;
				}
			}
			try {
				
				for (Object saveObjectArr : objects) {
					
					try {
						
						List<?> listofObject = ((ArrayList<?>)saveObjectArr);
						
						for (Object saveObject : listofObject) {
							
							super.saveOrUpdate(saveObject);
						}
					} catch (Exception ex) {
						
						super.saveOrUpdate(saveObjectArr);
					}					
				}
			} catch (Exception ex) {
				
				System.out.println("Error on saving object! ERROR : " + ex);
				
				ex.printStackTrace();
				
				return false;
			}
			return true;
		}
		return false;
	}
}
