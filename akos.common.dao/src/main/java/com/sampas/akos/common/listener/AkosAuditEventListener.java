package com.sampas.akos.common.listener;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.envers.event.AuditEventListener;
import org.hibernate.event.PostCollectionRecreateEvent;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PreCollectionRemoveEvent;
import org.hibernate.event.PreCollectionUpdateEvent;

import com.sampas.akos.common.context.SpringApplicationContextProvider;
import com.sampas.akos.common.dao.AuditSupport;
import com.sampas.akos.common.model.BaseObject;

public class AkosAuditEventListener extends AuditEventListener {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6466276546140290534L;

	@Override
	public void onPostDelete(PostDeleteEvent arg0) {
//		if (AuditSupport.isAuditEnabled(arg0.getEntity().getClass().getName()))
//			super.onPostDelete(arg0);
	}

	@Override
	public void onPostInsert(PostInsertEvent arg0) {
		// if (AuditSupport.dataStoreOnInsert())
		// super.onPostInsert(arg0);
	}

	@Override
	public void onPostRecreateCollection(PostCollectionRecreateEvent event) {
		// super.onPostRecreateCollection(event);
	}

	@Override
	public void onPostUpdate(PostUpdateEvent arg0) {
//		if (AuditSupport.isAuditEnabled(arg0.getEntity().getClass().getName())) {
//			Session session = ((SessionFactory) SpringApplicationContextProvider
//					.getApplicationContext().getBean("sessionFactory"))
//					.openSession();
//			Object obj = session.get(arg0.getEntity().getClass(), arg0.getId());
//
//			Object[] oldStateValues = arg0.getPersister().getPropertyValues(
//					obj, arg0.getSession().getEntityMode());
//			boolean degisiklik = false;
//			for (int i = 0; i < oldStateValues.length; i++) {
//				if (isChanged(oldStateValues[i], arg0.getState()[i])) {
//					degisiklik = true;
//					break;
//				}
//			}
//			session.close();
//			if (degisiklik) {
//				for (int i = 0; i < oldStateValues.length; i++) {
//					arg0.getState()[i] = oldStateValues[i];
//				}
//				super.onPostUpdate(arg0);
//			}
//		}
	}

	@Override
	public void onPreRemoveCollection(PreCollectionRemoveEvent event) {
		// super.onPreRemoveCollection(event);
	}

	@Override
	public void onPreUpdateCollection(PreCollectionUpdateEvent event) {
		// super.onPreUpdateCollection(event);

	}

	public boolean isChanged(Object oldValue, Object newValue) {
		if (oldValue == null && newValue == null) {
			return false;
		} else if (oldValue != null && newValue != null) {
			// her ikiside null dan farkli, degerlerin esitligi kontrol edilir
			if (oldValue instanceof Timestamp || oldValue instanceof Date) {
				long oldTime = 0L;
				long newTime = 0L;
				if (oldValue instanceof Timestamp) {
					oldTime = ((Timestamp) oldValue).getTime();
				} else {
					oldTime = ((Date) oldValue).getTime();
				}
				if (newValue instanceof Timestamp) {
					newTime = ((Timestamp) newValue).getTime();
				} else {
					newTime = ((Date) newValue).getTime();
				}
				// 1 saniyeden az onemsenmez
				if (oldTime - newTime < -1000 || oldTime - newTime > 1000) {
					return true;
				}
			} else if (oldValue instanceof BaseObject) {
//				if (!((BaseObject) oldValue).getId().equals(
//						((BaseObject) newValue).getId())) {
//					return true;
//				}
			} else if (!oldValue.equals(newValue)) {
				return true;
			}
		} else {
			// ikisinden sadece biri null
			if (oldValue instanceof String || newValue instanceof String) {
				if ((oldValue != null && !oldValue.toString().trim().equals(""))
						|| (newValue != null && !newValue.toString().trim()
								.equals(""))) {
					// kontrol edilen eski veya yeni eleman string ve bos degilse
					// degisiklik vardir
					return true;
				}
			} else {
				// String degil ve elemanlardan biri null dan farkli, degisiklik var
				return true;
			}
		}
		return false;
	}

}