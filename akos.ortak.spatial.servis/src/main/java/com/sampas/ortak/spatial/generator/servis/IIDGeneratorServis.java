package com.sampas.ortak.spatial.generator.servis;

import com.sampas.ortak.spatial.generator.model.GeneratedID;


public interface IIDGeneratorServis {

	public Long getIDForNewObject(Class<?> objectClass);
	
	public GeneratedID readGeneratedID(Class<?> objectClass);
	
	public boolean saveGeneratedID(GeneratedID generatedID);
	
}
