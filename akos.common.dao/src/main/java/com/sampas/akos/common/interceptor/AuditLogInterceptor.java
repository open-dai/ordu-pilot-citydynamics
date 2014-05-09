package com.sampas.akos.common.interceptor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sampas.akos.common.dao.AkosAuditableFactory;
import com.sampas.akos.common.model.Auditable;
import com.sampas.akos.common.model.BaseLogObject;
import com.sampas.akos.common.model.BaseObject;


public class AuditLogInterceptor extends EmptyInterceptor {
	
	private static final long serialVersionUID = 6803854387378916855L;

	private final static String CREATE_ENTITY = "CREATE_ENTITY";
	private final static String UPDATE_ENTITY = "UPDATE_ENTITY";
	private final static String DELETE_ENTITY = "DELETE_ENTITY";

	private int updates;
    private int creates;
    private int delete;
	
    private Set inserts    		= new HashSet();
    private Set flushDirties	= new HashSet();
    
    private SessionFactory sessionFactory;
	private AkosAuditableFactory akosAuditableFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public AkosAuditableFactory getAkosAuditableFactory() {
		return akosAuditableFactory;
	}

	public void setAkosAuditableFactory(AkosAuditableFactory akosAuditableFactory) {
		this.akosAuditableFactory = akosAuditableFactory;
	}
	
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		delete = delete+1;
		boolean changed = false;
		Object dbObject = null;
		String entityName = entity.getClass().getSimpleName();
		if(!entityName.endsWith("Log") && !entityName.equals("AuditLog") && !entityName.equals("AuditProperties")){
			if(akosAuditableFactory.getHistorical(entity.getClass().getSimpleName())){
				if((entity instanceof BaseObject) ){
					inserts.add(entity);
	       			Object entityId;
					try {
						entityId = entity.getClass().getMethod("getId", null).invoke(entity, null);
	       				akosAuditableFactory.auditEntity(entity, this.DELETE_ENTITY);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public boolean onDeleteReturn(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		updates = updates+1;
		//Eger update edilmek istenen kaydin dbdeki halinden farkliligi varsa changed=true olarak setlenir
		boolean changed = false;
		Object dbObject = null;
		String entityName = entity.getClass().getSimpleName();
		if(!entityName.endsWith("Log") && !entityName.equals("AuditLog") && !entityName.equals("AuditProperties")){
			if(akosAuditableFactory.getHistorical(entity.getClass().getSimpleName())){
				if(previousState==null){
					dbObject = akosAuditableFactory.getDBObject(entity, id);
				}
				if((entity instanceof BaseObject) ){
					changed = akosAuditableFactory.checkDirty(entity, dbObject);
					
					if(changed){
						flushDirties.add(entity);
		       			Object entityId;
						try {
							entityId = entity.getClass().getMethod("getId", null).invoke(entity, null);
		       				akosAuditableFactory.auditEntity(entity, this.UPDATE_ENTITY);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
		    	}
			}
		}
		return  false;			
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		creates = creates+1;
		boolean changed = false;
		Object dbObject = null;
		String entityName = entity.getClass().getSimpleName();
		if(!entityName.endsWith("Log") && !entityName.equals("AuditLog") && !entityName.equals("AuditProperties")){
			if(akosAuditableFactory.getHistorical(entity.getClass().getSimpleName())){
				if((entity instanceof BaseObject) ){
					inserts.add(entity);
	       			Object entityId;
					try {
						entityId = entity.getClass().getMethod("getId", null).invoke(entity, null);
	       				akosAuditableFactory.auditEntity(entity, this.CREATE_ENTITY);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	public void postFlush(Iterator entities) {
	}

	public void afterTransactionCompletion(Transaction tx) {
        if ( tx.wasCommitted() ) {        	
        }
        updates=0;
        creates=0;
        delete=0;
    }
	
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, org.hibernate.type.Type[] types) {
		return false;
	}

	public void afterTransactionBegin(Transaction tx) {
		super.afterTransactionBegin(tx);
	}

	public void beforeTransactionCompletion(Transaction tx) {
		super.beforeTransactionCompletion(tx);
	}

	public int[] findDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, org.hibernate.type.Type[] types) {		
		return super.findDirty(entity, id, currentState, previousState, propertyNames,
				types);
	}

	public Object getEntity(String entityName, Serializable id) {
		return super.getEntity(entityName, id);
	}

	public String getEntityName(Object object) {
		return super.getEntityName(object);
	}

	public Object instantiate(String entityName, EntityMode entityMode,
			Serializable id) {
		//log.info("################## instantiate OPERATION" + entityMode.toString());
		return super.instantiate(entityName, entityMode, id);
	}

	public Boolean isTransient(Object entity) {
		return super.isTransient(entity);
	}

	public void onCollectionRecreate(Object collection, Serializable key)
			throws CallbackException {
		super.onCollectionRecreate(collection, key);
	}

	public void onCollectionRemove(Object collection, Serializable key)
			throws CallbackException {
		super.onCollectionRemove(collection, key);
	}

	public void onCollectionUpdate(Object collection, Serializable key)
			throws CallbackException {
		super.onCollectionUpdate(collection, key);
	}

	public String onPrepareStatement(String sql) {
		return super.onPrepareStatement(sql);
	}

	public void preFlush(Iterator entities) {
		super.preFlush(entities);
	}

}
