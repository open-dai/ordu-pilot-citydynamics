package com.sampas.ortak.spatial.generator.servis.impl;

import com.sampas.ortak.spatial.generator.dao.IGeneratorDAO;
import com.sampas.ortak.spatial.generator.model.GeneratedID;
import com.sampas.ortak.spatial.generator.servis.IIDGeneratorServis;


public class IDGeneratorServis implements IIDGeneratorServis {

	IGeneratorDAO generatorDAO;
	
	public Long getIDForNewObject(Class<?> objectClass) {
		
		return (this.generatorDAO.getIDForNewObject(objectClass));
	}
	
	public GeneratedID readGeneratedID(Class<?> objectClass) {
		
		return (this.generatorDAO.readGeneratedID(objectClass));
	}
	
	public boolean saveGeneratedID(GeneratedID generatedID) {
		
		return (this.generatorDAO.saveGeneratedID(generatedID));
	}
	
	public IGeneratorDAO getGeneratorDAO() {
		return generatorDAO;
	}

	public void setGeneratorDAO(IGeneratorDAO generatorDAO) {
		this.generatorDAO = generatorDAO;
	}
}
