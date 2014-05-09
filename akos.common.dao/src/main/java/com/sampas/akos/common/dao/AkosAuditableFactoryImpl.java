package com.sampas.akos.common.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.sql.NUMBER;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Expression;
import com.sampas.akos.common.model.BaseObject;
import com.sampas.akos.common.util.AkosResourceReader;

public class AkosAuditableFactoryImpl extends BaseDaoSupport implements AkosAuditableFactory{

	private final static String CREATE_ENTITY = "CREATE_ENTITY";
	private final static String UPDATE_ENTITY = "UPDATE_ENTITY";
	private final static String DELETE_ENTITY = "DELETE_ENTITY";
	
	@Override
	public void saveDBObject(Object entity) {
		Session session = super.getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
	}
	
	public boolean checkDirty(Object entity, Object dbObject){
		boolean changed = false;
		if(dbObject!=null){
			try {
				Method[] methods = entity.getClass().getMethods();
				String methodPart = "";
				Class[] par = new Class[1];
				deneme:for (Method method : methods) {
					// method ismi get ile basliyorsa
					if ((!method.getName().equals("getClass")) && ( method.getName().startsWith("get") || method.getName().startsWith("is"))) {
						if (method.getName().startsWith("get")) 
							methodPart = method.getName().substring(3);
						else 
							methodPart = method.getName().substring(2);
						par[0] = method.getReturnType();
						Object[] screenRecord = {entity.getClass().getMethod(method.getName(), null).invoke(entity, null)};						
						
						Object[] dbRecord = { 
								dbObject.getClass().getMethod(method.getName(), null).invoke(dbObject, null) 
							};
						
						if(par[0].getName()!="" && screenRecord[0]!=null && dbRecord[0]!=null){
							if(par[0].getName().equals("java.util.Date")){
								Date date1 = ((Date)screenRecord[0]);
								int d1 = date1.getDay();
								int m1 = date1.getMonth();
								int y1 = date1.getYear();
								int h1 = date1.getHours();
								int mi1 = date1.getMinutes();
								int ms1 = date1.getSeconds();
								
								Date date2 = ((Date)dbRecord[0]);
								int d2 = date2.getDay();
								int m2 = date2.getMonth();
								int y2 = date2.getYear();
								int h2 = date2.getHours();
								int mi2 = date2.getMinutes();
								int ms2 = date2.getSeconds();
								
								if(d1==d2   && m1==m2 && 
								   y1==y2   && h1==h2 && 
								   mi1==mi2	&& ms1==ms2){
								}
							}
							else if(!screenRecord[0].equals(dbRecord[0])){
								changed=true;
								continue;
							}
						}
	   				}
				}		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return changed;
	}
	
	public Object getDBObject(Object entity, Serializable id) {
		Session session = super.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(entity.getClass(), "en");
		criteria.add(Expression
				.eq("en.id", id));
		return criteria.list().get(0);
	}
	

//	public String getLoggedUsername(){
//		String username;
//		if(SecurityContextHolder.getContext().getAuthentication()!=null && !SecurityContextHolder.getContext().getAuthentication().equals(null))
//			username = SecurityContextHolder.getContext().getAuthentication().getName();
//		else 
//			username = "GUEST";
//		return username;
//	}
	
	
	public void entityToLogEntity(Object entity, Object logEntity, String operation) throws Exception{
		Method[] methods = entity.getClass().getMethods();
		Class[] par = new Class[1];
		String methodPart = "";
		logEntity.getClass().getMethod("setIslemTipi", java.lang.String.class).invoke(logEntity, operation);
		logEntity.getClass().getMethod("setIslemZamani", java.util.Date.class).invoke(logEntity, Calendar.getInstance().getTime());
		
		for (Method method : methods) {
			par[0] = method.getReturnType();
			
			if (!method.getName().equals("getClass") && 
					(method.getName().startsWith("get") || method.getName().startsWith("is"))) {
				if (method.getName().startsWith("get")) 
					methodPart = method.getName().substring(3);
				
				if (method.getName().startsWith("is")) 
					methodPart = method.getName().substring(2);
				Method curMethod = entity.getClass().getMethod(method.getName(), null);
				Object[] input = 
					{curMethod.invoke(entity, null)};
				if(method.getName().equals("getId"))
					logEntity.getClass().getMethod("setParentId", par).invoke(logEntity, input);
				else 
	       			logEntity.getClass().getMethod("set" + methodPart, par).invoke(logEntity, input);
			}	
		}
	}
	
	public Integer auditEntity(Object entity, String operation){
		//Nesne null degil ve nesne update edilmek isteniyorsa
		if(entity!=null){
			if(operation.equals(UPDATE_ENTITY)){
			//Nesnenin tarihsel bilgisi tutuluyorsa(AuditProperties tablosunda)
				if(getHistorical(entity.getClass().getSimpleName())){
					Class entityClass = entity.getClass();
					Class logEntityClass = null;
					
					String logClassName = entityClass.getSimpleName() + "Log";
					String logEntityPackageName = entityClass.getName().substring(0, entityClass.getName().lastIndexOf("."));
					try {
						logEntityClass = Class.forName(logEntityPackageName+"."+logClassName);
						Object logEntity = logEntityClass.newInstance();
						entityToLogEntity(entity, logEntity, operation);
		   				saveDBObject(logEntity);
					} 
					catch (Exception e) {
						System.out.println("Hata:");
						e.printStackTrace();
						return 0;
					}
				}
    		}else 
    			if(operation.equals(CREATE_ENTITY) || operation.equals(DELETE_ENTITY)){
	    			Class entityClass = entity.getClass();
					Class logEntityClass = null;
					
					String logClassName = entityClass.getSimpleName() + "Log";
					String logEntityPackageName = entityClass.getName().substring(0, entityClass.getName().lastIndexOf("."));
					try {
						logEntityClass = Class.forName(logEntityPackageName+"."+logClassName);
						Object logEntity = logEntityClass.newInstance();
						entityToLogEntity(entity, logEntity, operation);
		   				saveDBObject(logEntity);
					} 
					catch (Exception e) {
						System.out.println("Hata:");
						e.printStackTrace();
						return 0;
					}
    		}
		}
		 return 1;
	}
	
	public boolean getHistorical(String a) {
		boolean historical = false;
		String result = AkosResourceReader.getHistoricalEntites().get(a);
		if(result!=null && result.equals("true")){
			return true;
		}
		else return false;
//		Session session = super.getHibernateTemplate().getSessionFactory().openSession();
//		List<Object> list = session.createCriteria(com.sampas.akos.trace.model.AuditProperties.class).add(Expression.eq("entityName", a)).add(Expression.eq("historical", new Long("1"))).list();
//		int h = (list.size());
//		if(h>0){
//			if( h == 1 ){
//				historical = true;
//			}else{ 
//				historical = false;
//			}
//		}
//		return historical;
	}

	public BaseObject getLogInstance(String className) {
		// TODO Auto-generated method stub
		return null;
	}
}
