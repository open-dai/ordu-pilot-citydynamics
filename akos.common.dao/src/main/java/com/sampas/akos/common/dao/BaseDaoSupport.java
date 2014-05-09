package com.sampas.akos.common.dao;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.jdbc.driver.OracleConnection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.context.SecurityContextHolder;

public abstract class BaseDaoSupport extends HibernateDaoSupport {
	private Connection	connection;

	public void saveOrUpdate(Object entity) {
		setClientIdentifier();
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdate(Collection<?> liste) {
		setClientIdentifier();
		this.getHibernateTemplate().saveOrUpdateAll(liste);
	}

	@Deprecated
	public void saveDBObject(Object entity) {
		setClientIdentifier();
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Deprecated
	public void saveDBObject(Collection<?> liste) {
		setClientIdentifier();
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().saveOrUpdateAll(liste);
	}

	public Object saveDBObjectWithReturn(Object entity) {
		setClientIdentifier();
		return this.getHibernateTemplate().merge(entity);
	}

	public void mergeDBObject(Object entity) {
		setClientIdentifier();
		this.getHibernateTemplate().clear();
		this.getHibernateTemplate().merge(entity);
	}

	public void deleteDBObject(Object entity) {
		setClientIdentifier();
		this.getHibernateTemplate().delete(entity);
	}

	public void deleteDBObject(Collection<?> liste) {
		setClientIdentifier();
		this.getHibernateTemplate().deleteAll(liste);
	}

	public Object readDBObject(Class entityClass, Long primaryKey) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		criteria.add(Restrictions.idEq(primaryKey));
		criteria.setMaxResults(1);
		criteria.setCacheable(true);
		List result = criteria.list();
		if (result.size() == 1)
			return result.get(0);
		else
			return null;
	}

	public Collection<?> readDBObject(Class entityClass, HashMap<?, ?> map) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		Set keySet = map.keySet();

		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object value = map.get(key);

			if (value != null) {
				if ((value.getClass()).equals(Date.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else if ((value.getClass()).equals(String.class)) {
					if (!((String) value).trim().equals(""))
						criteria
								.add(Restrictions.like(key, value).ignoreCase());
				} else if ((value.getClass()).equals(Long.class)
						|| (value.getClass()).equals(Double.class)
						|| (value.getClass()).equals(Integer.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else {
					criteria.add(Restrictions.eq(key, value));
				}
			}
		}
		criteria.setCacheable(true);
		List<?> list = criteria.list();
		if (list != null && list.size() == 0)
			list = null;
		return list;
	}

	public Long readDBObjectCount(Class entityClass, HashMap<?, ?> map) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		Set keySet = map.keySet();

		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object value = map.get(key);

			if (value != null) {
				if ((value.getClass()).equals(Date.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else if ((value.getClass()).equals(String.class)) {
					if (!value.toString().trim().equals(""))
						criteria
								.add(Restrictions.like(key, value).ignoreCase());
				} else if ((value.getClass()).equals(Long.class)
						|| (value.getClass()).equals(Double.class)
						|| (value.getClass()).equals(Integer.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else {
					criteria.add(Restrictions.eq(key, value));
				}
			}
		}
		criteria.setCacheable(true);
		return kayitSayisiOku(criteria);
	}

	public Object readUniqueObject(Class entityClass, HashMap<?, ?> map) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		Set keySet = map.keySet();

		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object value = map.get(key);

			if (value != null) {
				if (value.getClass().equals(String.class)
						&& ((String) value).trim().equals(""))
					continue;

				if ((value.getClass()).equals(Date.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else if ((value.getClass()).equals(String.class)) {
					criteria.add(Restrictions.like(key, value).ignoreCase());
				} else if ((value.getClass()).equals(Long.class)
						|| (value.getClass()).equals(Double.class)
						|| (value.getClass()).equals(Integer.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else {
					criteria.add(Restrictions.eq(key, value));
				}
			}
		}
		criteria.setCacheable(true);
		return criteria.uniqueResult();
	}

	public Collection<?> readDBObject(Class entityClass) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		Order arg0 = Order.asc("id");
		criteria.addOrder(arg0);
		criteria.setCacheable(true);
		return criteria.list();
	}

	/**
	 * Belirtilen arama kriterlerine gore belirtilen nesne turunde sonuclari
	 * veritabanindan okur ve sonuclari Collection olarak doner.
	 * 
	 * @param entityClass
	 *            veritabanindan okunmak istenen nesnenin turu
	 * @param map
	 *            sorgulama kriterlerinin tutuldugu parametre ( Gonderilen mapin key i
	 *            sorgulama yapilacak alani, value dizisinin birinci elemani sorgulama
	 *            yapilmak istenen key in degerini, eger belirtilmis ise ikinci elemani
	 *            ise ilgili key icin datanin sirali getirilmesi icin kullanilir, eger asc
	 *            artan, degilse azalan siralama yapar )
	 * @param maxResultCount
	 *            sorgulama sonucu alinmasi gereken max rov countin belirtildigi alan (
	 *            Eger 0 verilmis ise herhangi bir sinir konulmaz )
	 * @return belirtilen kriterlere gore yapilan sorgulama sonucunu Collection tipinde
	 *         doner
	 */

	public Collection<?> readDBObjectAsOrder(Class entityClass,
			Map<String, Object[]> map, int maxResultCount) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		Set keySet = map.keySet();

		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object value = map.get(key)[0];
			Object order = map.get(key)[1];

			if (value != null) {
				if ((value.getClass()).equals(Date.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else if ((value.getClass()).equals(String.class)) {
					criteria.add(Restrictions.like(key, value).ignoreCase());
				} else if ((value.getClass()).equals(Long.class)
						|| (value.getClass()).equals(Double.class)
						|| (value.getClass()).equals(Integer.class)) {
					criteria.add(Restrictions.eq(key, value));
				} else {
					criteria.add(Restrictions.eq(key, value));
				}
			}

			if (order != null) {
				if (order.equals("asc")) {
					criteria.addOrder(Order.asc(key));
				} else {
					criteria.addOrder(Order.desc(key));
				}
			}
		}
		if (maxResultCount != 0)
			criteria.setMaxResults(maxResultCount);
		criteria.setCacheable(true);
		return criteria.list();
	}

	public Collection<?> readDBObjectPart(Class entityClass, int beginIndex,
			int maxResult) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		criteria.setFirstResult(beginIndex);
		// criteria.setMaxResults(maxResult);
		criteria.setCacheable(true);
		return criteria.list();
	}

	public Collection<?> readDBObjectByHQL(String queryString, Object[] values) {
		this.getHibernateTemplate().setCacheQueries(true);
		return this.getHibernateTemplate().find(queryString, values);
	}

	public Object readDBObjectByPk(Class entityClass, HashMap map) {
		Collection<Object> objects = (Collection<Object>) readDBObject(
				entityClass, map);
		if (objects.size() == 1)
			return objects.iterator().next();

		return null;

	}

	public Long readLastIdFromDbObject(Class entityClass) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		criteria.setProjection(Projections.projectionList().add(
				Projections.max("id")));
		Long result = (Long) criteria.list().get(0);
		if (result == null)
			return 0L;
		else
			return result;
	}

	public Collection<?> readDBObjectByHQL(String queryString, Object[] values,
			int baslangicKaydi, int maksimumKayitSayisi) {
		Query query = getSession().createQuery(queryString);
		int index = 0;
		for (Object value : values) {
			if (value.getClass().equals(Long.class)) {
				query.setLong(index, (Long) value);
			} else if (value.getClass().equals(Double.class)) {
				query.setDouble(index, (Double) value);
			} else if (value.getClass().equals(BigDecimal.class)) {
				query.setBigDecimal(index, (BigDecimal) value);
			} else if (value.getClass().equals(Integer.class)) {
				query.setInteger(index, (Integer) value);
			} else if (value.getClass().equals(Date.class)) {
				query.setDate(index, (Date) value);
			} else if (value.getClass().equals(String.class)) {
				query.setString(index, (String) value);
			} else if (value.getClass().equals(Boolean.class)) {
				query.setBoolean(index, (Boolean) value);
			} else {
				query.setEntity(index, value);
			}
			index++;
		}
		if (baslangicKaydi > 0) {
			query.setFirstResult(baslangicKaydi);
		}
		if (maksimumKayitSayisi > 0) {
			query.setMaxResults(maksimumKayitSayisi);
		}
		query.setCacheable(true);
		return query.list();
	}

	public Object readUniqueObjectByQuery(String queryString,
			List<Object> values, int baslangicKaydi, int maksimumKayitSayisi) {
		Query query = getSession().createQuery(queryString);
		int index = 0;
		for (Object value : values) {
			if (value.getClass().equals(Long.class)) {
				query.setLong(index, (Long) value);
			} else if (value.getClass().equals(Double.class)) {
				query.setDouble(index, (Double) value);
			} else if (value.getClass().equals(BigDecimal.class)) {
				query.setBigDecimal(index, (BigDecimal) value);
			} else if (value.getClass().equals(Integer.class)) {
				query.setInteger(index, (Integer) value);
			} else if (value.getClass().equals(Date.class)) {
				query.setDate(index, (Date) value);
			} else if (value.getClass().equals(Boolean.class)) {
				query.setBoolean(index, (Boolean) value);
			} else if (value.getClass().equals(String.class)) {
				query.setString(index, (String) value);
			}
			index++;
		}
		if (baslangicKaydi > 0) {
			query.setFirstResult(baslangicKaydi);
		}
		if (maksimumKayitSayisi > 0) {
			query.setMaxResults(maksimumKayitSayisi);
		}
		query.setCacheable(true);
		return query.uniqueResult();
	}

	public void readLazyObject(Object obj) {
		this.getHibernateTemplate().setCacheQueries(true);
		this.getHibernateTemplate().refresh(obj);
	}

	public Connection getJdbcConnection() {
		JdbcConnection jdbcConnection = new JdbcConnection();

		return jdbcConnection.getConnection();
	}

	public void closeConnection() {
		JdbcConnection.getInstance().closeConnection();

	}

	public Connection getConnection() {
		if (connection == null) {
			connection = getJdbcConnection();
		}
		try {
			if (connection.isClosed()) {
				connection = getJdbcConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection = getJdbcConnection();
		}
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Long kayitSayisiOku(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		Long kayitSayisi = (Long) criteria.uniqueResult();
		return kayitSayisi;
	}

	private void setClientIdentifier() {
		Proxy proxy = (Proxy) this.getSessionFactory().getCurrentSession()
				.connection();
		try {
			// DYS gibi domain disi uygulamalarda nullPointer hatası vermemesi için
			// düzenlendi
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				Proxy.getInvocationHandler(proxy).invoke(
						proxy,
						OracleConnection.class.getMethod("setClientIdentifier",
								new Class[] { String.class }),
						new Object[] { SecurityContextHolder.getContext()
								.getAuthentication().getName() });
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
