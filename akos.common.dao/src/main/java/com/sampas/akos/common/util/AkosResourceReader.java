package com.sampas.akos.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.sampas.akos.trace.model.AuditProperties;

public class AkosResourceReader implements ApplicationContextAware{
	private List resourceList;
	private ApplicationContext context;
	private SessionFactory sessionFactory;
	private static Map<String, String> historicalEntites = new HashMap<String, String>();

	public static Map<String, String> getHistoricalEntites() {
		return historicalEntites;
	}

	public static void setHistoricalEntites(Map<String, String> historicalEntites) {
		AkosResourceReader.historicalEntites = historicalEntites;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static Properties getPropertiesFromFile(String pathToPropFile) {
		Properties akos_properties = new Properties();
		try {
			akos_properties.load(new FileInputStream(pathToPropFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return akos_properties;
	}

	public static Properties getPropertiesFromURL(String urlOfPropFile) {
		Properties akos_properties = new Properties();
		URL prop_url = null;
		try {
			prop_url = new URL(urlOfPropFile);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		URLConnection prop_con = null;
		try {
			prop_con = prop_url.openConnection();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		InputStream in = null;
		try {
			in = prop_con.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			akos_properties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return akos_properties;
	}

	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;

		/***************** AuditProperties LISTESINI YAZMAK ICIN ****************/
		ArrayList<AuditProperties> auditProperties = new ArrayList<AuditProperties>();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(AuditProperties.class, "ap");
		List<AuditProperties> apList = criteria.list();
		boolean check = false;
		for (Object object : sessionFactory.getAllClassMetadata().values()) {
			if(	object.getClass().getName().equals("org.hibernate.persister.entity.UnionSubclassEntityPersister") || 
				object.getClass().getName().equals("org.hibernate.persister.entity.JoinedSubclassEntityPersister")  ){
			}
			else{
				SingleTableEntityPersister a = (SingleTableEntityPersister)object;
				String entityName = a.getName().substring(a.getName().lastIndexOf(".")+1);
				if(entityName.endsWith("Log"))
					continue;
				AuditProperties auditProperty = new AuditProperties();
				auditProperty.setEntityName(entityName);
				auditProperty.setHistorical(new Long(0L));
				auditProperty.setTraceable(new Long(0L));
				auditProperty.setChangeTime(Calendar.getInstance().getTime());
				for (AuditProperties dbAuditProperty : apList) {
					if(auditProperty.getEntityName().equals(dbAuditProperty.getEntityName())){
						 check = true;
						 continue;
					}
				}
				if (!check) {
					auditProperties.add(auditProperty);
				}
				check = false;
			}
		}
		for (AuditProperties auditProperty2 : auditProperties) {
			session.saveOrUpdate(auditProperty2);
			historicalEntites.put(auditProperty2.getEntityName(), "true");
		}
		tx.commit();
		session.close();
		/***************************************************************/

		/***************** RESOURCE LISTESI OKUMAK ICIN ****************/
		if (resourceList.size()>0) {
//		    for (Iterator iterator = resourceList.iterator(); iterator.hasNext();) {
//            	String property = (String) iterator.next();
            	/**
            	 * Denemek icin asagidaki adreste 'button.ara' ve 'button.kaydet' propertylerine sahip bir .properties dosyasi olmali.
            	 * http://localhost:8081/ortaksabitdil_tr.properties 
            	 */
            	//System.out.println((property + ": " + getPropertiesFromURL("http://localhost:8081/ortaksabitdil_tr.properties").get(property)));
//			}
			
        }
	}
}
