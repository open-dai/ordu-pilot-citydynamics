package com.sampas.ortak.spatial.generator.dao;

import com.sampas.ortak.spatial.generator.model.GeneratedID;


public interface IGeneratorDAO {

	public Long getIDForNewObject(Class<?> objectClass);
	
	public GeneratedID readGeneratedID(Class<?> objectClass);
	
	public boolean saveGeneratedID(GeneratedID generatedID);

}
