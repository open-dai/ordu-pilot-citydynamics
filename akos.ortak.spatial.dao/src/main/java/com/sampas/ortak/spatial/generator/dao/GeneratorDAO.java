package com.sampas.ortak.spatial.generator.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.ortak.spatial.generator.model.GeneratedID;


@SuppressWarnings("deprecation")
public class GeneratorDAO extends BaseDaoSupport implements IGeneratorDAO {

	private static String DEFAULT_GET_MAX_COLUMN_NAME = "id";
	
	private static Long DEFAULT_GEN_FIRST_ID = 0L;
	
	private long globalCntr = 1L;
	
	public Long getIDForNewObject(Class<?> objectClass) {

		Long maxValueInDB = this.getMaxIdFromDbObject(objectClass);
		
		GeneratedID generatedID = this.readGeneratedID(objectClass);
		
		Long maxValueInGenerateds = generatedID.getLastGenerated();
		
		if (maxValueInDB == null) {
			
			maxValueInDB = globalCntr;
			
			globalCntr++;
		}
		if (maxValueInGenerateds == null) {
			
			maxValueInGenerateds = 1L;
		}
		if (maxValueInDB > maxValueInGenerateds) {
			
			generatedID.setLastGenerated(maxValueInDB + 1);
			
			this.saveGeneratedID(generatedID);
			
			return (generatedID.getLastGenerated());
		} else {
			
			generatedID.setLastGenerated(maxValueInGenerateds + 1);
			
			this.saveGeneratedID(generatedID);
			
			return (generatedID.getLastGenerated());
		}
	}
	
	@SuppressWarnings({ "unchecked", "static-access"})
	public GeneratedID readGeneratedID(Class<?> objectClass) {
		
		if (objectClass != null) {
			
			Criteria criteria = super.getSession().createCriteria(GeneratedID.class, "gID");
			
			criteria.add(Expression.eq("gID.className", objectClass.getName()));
			
			List<GeneratedID> generatedIDs = criteria.list();
			
			if (generatedIDs.size() > 0) {
				
				return (generatedIDs.get(0));
			} else {
				
				//if there is no GeneratedId record try to create it
				GeneratedID generatedID = new GeneratedID();
				
				generatedID.setClassName(objectClass.getName());
				
				generatedID.setLastGenerated(this.DEFAULT_GEN_FIRST_ID);
				
				this.saveGeneratedID(generatedID);

				return (generatedID);
			}
		} 
		else {
			
			return null;
		}
	}
	
	public boolean saveGeneratedID(GeneratedID generatedID) {
		
		if (generatedID != null) {
			//must use try and exception handling together 
			super.saveOrUpdate(generatedID);
			
			return true;
		} else {
			
			return false;
		}
	}
	
	//This function also stay in BaseDaoSupport, will be remove after service tests passed but problem is "id" selected as default column
	private Long getMaxIdFromDbObject(Class<?> objectClass) {
		
		Criteria criteria = this.getSession().createCriteria(objectClass);
		
		criteria.setProjection(Projections.projectionList().add(Projections.max(DEFAULT_GET_MAX_COLUMN_NAME)));
		
		Long result = (Long) criteria.list().get(0);
		
		return result;
	}
	
}
