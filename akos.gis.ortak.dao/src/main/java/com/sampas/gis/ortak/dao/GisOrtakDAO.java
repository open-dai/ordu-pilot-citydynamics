package com.sampas.gis.ortak.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.PropertyMapping;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.gis.ortak.metadata.model.KurumUygMD_DB_Map;
import com.sampas.gis.ortak.model.Fonksiyon;
import com.sampas.gis.ortak.model.KadastroParselGecerliFonksiyon;
import com.sampas.gis.ortak.model.OcxFormTable;
import com.sampas.gis.ortak.model.OnemliYer;
import com.sampas.gis.ortak.model.Plan;
import com.sampas.gis.ortak.model.PlanNot;
import com.sampas.gis.ortak.model.PlanTurleri;
import com.sampas.gis.ortak.tools.impl.EntityMetaData;


@SuppressWarnings({"unchecked", "unused"})
public class GisOrtakDAO extends BaseDaoSupport implements IGisOrtakDAO {

	public List<OnemliYer> readOnemliyerByCriteria(OnemliYer onemliYer, KurumSabit kurumSabit) {

		if (onemliYer != null) {
			
			Criteria criteria = super.getSession().createCriteria(
					OnemliYer.class, "oy");
			criteria.createAlias("oy.bagimsiz", "bgm");
			criteria.createAlias("bgm.adres", "adr");
			criteria.createAlias("adr.mahalleCaddeSokak", "mc");
			criteria.createAlias("mc.caddeSokak", "cs");
			criteria.createAlias("mc.mahalle", "mh");
			
			if (kurumSabit != null && kurumSabit.getId() != null) {
				criteria.add(Restrictions.eq("adr.kurumSabit.id", kurumSabit.getId()));
				criteria.add(Restrictions.eq("cs.kurumSabit.id", kurumSabit.getId()));
				criteria.add(Restrictions.eq("mh.kurumSabit.id", kurumSabit.getId()));
			}
			
			if (onemliYer.getId()!=null && onemliYer.getId() > 0) {
				criteria.add(Restrictions.eq("oy.id", onemliYer.getId()));
			}
			
			if (onemliYer.getBagimsiz() != null
					&& onemliYer.getBagimsiz().getId() > 0) {
				criteria.add(Restrictions.eq("oy.bagimsiz.id", onemliYer
						.getBagimsiz().getId()));
			}
			
			if (onemliYer.getKullanimSinifi() != null
					&& onemliYer.getKullanimSinifi().getId() != null
					&& onemliYer.getKullanimSinifi().getId() > 0) {
				criteria.add(Restrictions.eq("oy.kullanimSinifi.id", onemliYer
						.getKullanimSinifi().getId()));
			}
			
			if (onemliYer.getKullanimGrubu() != null
					&& onemliYer.getKullanimGrubu().getId() != null
					&& onemliYer.getKullanimGrubu().getId() > 0) {
				criteria.add(Restrictions.eq("oy.kullanimGrubu.id", onemliYer
						.getKullanimGrubu().getId()));
			}
			
			if (onemliYer.getKullanimDetayi() != null
					&& onemliYer.getKullanimDetayi().getId() != null
					&& onemliYer.getKullanimDetayi().getId() > 0) {
				criteria.add(Restrictions.eq("oy.kullanimDetayi.id", onemliYer
						.getKullanimDetayi().getId()));
			}
			
			if (onemliYer.getAciklama() != null
					&& onemliYer.getAciklama().trim() != ""
					&& onemliYer.getAciklama().trim() != "*") {
				criteria.add(Restrictions.ilike("oy.aciklama", "%"+onemliYer
						.getAciklama()
						+ "%"));
			}
			criteria.addOrder(Order.asc("oy.aciklama"));
			return criteria.list();
		} else {
			
			return null;
		}

	}

	public List<Fonksiyon> readFonksiyonlarByCriteria(KadastroParsel parsel, PlanTurleri planTuru) {

		if (parsel != null && parsel.getId() > 0) {

			List<Object> paramList = new ArrayList<Object>();

			String hql = "Select kpgf.fonksiyon FROM KadastroParselGecerliFonksiyon kpgf WHERE  kpgf.id>0 ";
			
			if (parsel.getId() > 0) {
				hql += " AND kpgf.kadastroParsel.id=?";
				paramList.add(parsel.getId());
			}

			if (planTuru != null && planTuru.getId() > 0) {
				if (planTuru.getId() > 0) {
					hql += " AND kpgf.planTuru.id=?";
					paramList.add(planTuru.getId());
				}
			}

			return((List<Fonksiyon>) super.readDBObjectByHQL(hql, paramList.toArray()));
			
		} else {
			return null;

		}
	}
	
	public Fonksiyon readFonksiyonByCriteria(KadastroParsel parsel, PlanTurleri planTuru) {

		List<Fonksiyon> fonksiyonList = readFonksiyonlarByCriteria(parsel, planTuru);
		
		if (fonksiyonList != null && fonksiyonList.size() > 0) {
			
			return fonksiyonList.get(0);
		} else {
			
			return null;
		}
	}

	public Plan readPlanByCriteria(Fonksiyon fonksiyon, KurumSabit kurumSabit) {

		List<Object> paramList = new ArrayList<Object>();
		
		String hql = "Select f.plan FROM Fonksiyon f inner join f.plan p  WHERE  f.id=? ";
		hql += " AND f.kurumSabit.id=? AND p.kurumSabit.id=? ";

		if (fonksiyon != null && fonksiyon.getId() > 0) {
			
			paramList.add(fonksiyon.getId());
			
			paramList.add(kurumSabit.getId());
			paramList.add(kurumSabit.getId());
			
			List<Plan> planList = (List<Plan>) super.readDBObjectByHQL(hql,
					paramList.toArray());
			if (planList.size() > 0) {
				return planList.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Bina readBinaByOnemliYer(OnemliYer onemliYer, KurumSabit kurumSabit) {

		if (kurumSabit != null && onemliYer != null) {

			if (onemliYer.getBagimsiz() == null
					|| onemliYer.getBagimsiz().getId() == null
					|| onemliYer.getBagimsiz().getId() <= 0) {
				onemliYer.setBagimsiz(readBagimsizByOnemliYer(onemliYer, kurumSabit));
			}
			if (onemliYer.getBagimsiz() != null) {

				List<Object> paramList = new ArrayList<Object>();
				String hql = "SELECT b FROM Bina AS b INNER JOIN b.adresler AS adr INNER JOIN adr.bagimsizlar AS bgm "
						+ " WHERE b.kurumSabit.id=? AND bgm.id=? AND adr.kurumSabit.id=? ";

				paramList.add(kurumSabit.getId());
				paramList.add(onemliYer.getBagimsiz().getId());
				paramList.add(kurumSabit.getId());
				
				List<Bina> binaList = (List<Bina>) super.readDBObjectByHQL(hql,
						paramList.toArray());

				if (binaList != null && binaList.size() > 0) {
					return binaList.get(0);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public Bagimsiz readBagimsizByOnemliYer(OnemliYer onemliYer, KurumSabit kurumSabit) {
		
		if (onemliYer != null && onemliYer.getId() > 0) {

			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT bgm FROM OnemliYer as oy INNER JOIN oy.bagimsiz as bgm INNER JOIN bgm.adres AS adr "
					+ " WHERE  oy.id=? ";

			paramList.add(onemliYer.getId());
			
			hql += " AND adr.kurumSabit.id=? ";
			paramList.add( kurumSabit.getId() );
			
			List<Bagimsiz> bagimsizList = (List<Bagimsiz>) super
					.readDBObjectByHQL(hql, paramList.toArray());

			if (bagimsizList != null && bagimsizList.size() > 0) {
				return bagimsizList.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public OnemliYer readOnemliYerByBagimsiz(Bagimsiz bagimsiz) {
		
		if (bagimsiz != null && bagimsiz.getId() > 0) {

			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT oy FROM OnemliYer as oy INNER JOIN oy.bagimsiz as bgm  "
					+ " WHERE  bgm.id=? ";

			paramList.add(bagimsiz.getId());

			List<OnemliYer> onemliYerList = (List<OnemliYer>) super
					.readDBObjectByHQL(hql, paramList.toArray());

			if (onemliYerList != null && onemliYerList.size() > 0) {
				return onemliYerList.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Adres readAdresByOnemliYer(OnemliYer onemliYer, KurumSabit kurumSabit) {
		
		
		if (kurumSabit != null && kurumSabit.getId() > 0) {
			
			List<Object> paramList = new ArrayList<Object>();
			
			String hql = "SELECT adr FROM OnemliYer as oy INNER JOIN oy.bagimsiz as bgm inner join bgm.adres as adr  " + " WHERE adr.kurumSabit.id=?";

			paramList.add(kurumSabit.getId());

			if (onemliYer != null && onemliYer.getId()!=null && onemliYer.getId() > 0) {
				hql += "AND oy.id=? ";
				paramList.add(onemliYer.getId());
			}
			/*
			if (onemliYer.getAciklama() != null	&& !onemliYer.getAciklama().trim().equals("")) {
				
				hql += "AND upper(oy.aciklama) like upper('%' || ? || '%')";
				
				paramList.add(onemliYer.getAciklama());
			}
			*/
			
			List<Adres> adresList = (List<Adres>) super.readDBObjectByHQL(hql, paramList.toArray());
			System.out.println("readAdresByOnemliYer : 1");
			if (adresList != null && adresList.size() > 0) {
				System.out.println("readAdresByOnemliYer : 2 = "+adresList.get(0).getAciklama()+" = "+adresList.get(0).getId());
				return adresList.get(0);
			} else {
				System.out.println("readAdresByOnemliYer : 3");
				return null;
			}
		} else {
			System.out.println("readAdresByOnemliYer : 4");
			return null;
		}
	}

	public Adres readAdresByCriteries(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo) {
		
		if (kurumSabit != null && kurumSabit.getId() > 0 && mahalleAdi != null && mahalleAdi != "" && caddeSokakAdi != null && caddeSokakAdi != "") {
			
			try {
				
				Criteria criteria = super.getSession().createCriteria(MahalleCaddeSokak.class, "mcs");
				
				criteria.createAlias("mcs.mahalle", "mh");
				
				criteria.createAlias("mh.kurumSabit", "mhks");
				
				criteria.createAlias("mcs.caddeSokak", "cd");
				
				criteria.add(Restrictions.ilike("mh.aciklama", mahalleAdi.trim()+ "%"));
				
				criteria.add(Restrictions.ilike("cd.aciklama", caddeSokakAdi.trim()+ "%"));
				
				criteria.add(Restrictions.eq("mhks.id", kurumSabit.getId()));
				
				List<MahalleCaddeSokak> mahalleCaddeSokakList = criteria.list();
				
				if (mahalleCaddeSokakList != null && mahalleCaddeSokakList.size() > 0) {
				
					if (mahalleCaddeSokakList.size() == 1) {
						
						Criteria criteriaForAddressSearch = super.getSession().createCriteria(Adres.class, "adr");
						
						Long kapiNoAdNumeric = 0L;
						
						try {
							
							kapiNoAdNumeric = Long.parseLong(kapiNo);
							
							criteriaForAddressSearch.add(Restrictions.eq("adr.kapiNo", kapiNoAdNumeric));
						} catch (Exception ex) {
							// Parse Hatası olursa kapiNo = 0L
							System.err.println("Kapıno (" + kapiNo + ") Long tipine parse edilemiyor...");
						}
						criteriaForAddressSearch.add(Restrictions.eq("adr.mahalleCaddeSokak", mahalleCaddeSokakList.get(0)));
						
						List<Adres> tempAddressList = criteriaForAddressSearch.list();
						
						if (tempAddressList != null && tempAddressList.size() > 0) {
							
							return tempAddressList.get(0);
						} else {
							
							System.out.println("More than one address found ! in 'readAdresByCriteries' function");
						}
					} else {
						
						System.out.println("More than one districtStreet found ! in 'readAdresByCriteries' function");
					}
				} else {
				
					System.out.println("No any districtStreet found ! in 'readAdresByCriteries' function");
				}
			} catch (Exception ex) {
				
				System.err.println("Error on searching for address in 'readAdresByCriteries' function !");
			}
		}
		return null;
	}

	public List<Adres> readAdresListByCriteries(KurumSabit kurumSabit, String mahalleAdi, String caddeSokakAdi, String kapiNo) {
		
		if (kurumSabit != null && kurumSabit.getId() > 0 && mahalleAdi != null && mahalleAdi != "" && caddeSokakAdi != null && caddeSokakAdi != "") {
			
			try {
				
				Criteria criteria = super.getSession().createCriteria(MahalleCaddeSokak.class, "mcs");
				
				criteria.createAlias("mcs.mahalle", "mh");
				
				criteria.createAlias("mh.kurumSabit", "mhks");
				
				criteria.createAlias("mcs.caddeSokak", "cd");
				
				criteria.add(Restrictions.ilike("mh.aciklama", mahalleAdi.trim()+ "%"));
				
				criteria.add(Restrictions.ilike("cd.aciklama", caddeSokakAdi.trim()+ "%"));
				
				criteria.add(Restrictions.eq("mhks.id", kurumSabit.getId()));
				
				List<MahalleCaddeSokak> mahalleCaddeSokakList = criteria.list();
				
				if (mahalleCaddeSokakList != null && mahalleCaddeSokakList.size() > 0) {
				
					if (mahalleCaddeSokakList.size() == 1) {
						
						Criteria criteriaForAddressSearch = super.getSession().createCriteria(Adres.class, "adr");
						
						Long kapiNoAdNumeric = 0L;
						
						try {
							
							kapiNoAdNumeric = Long.parseLong(kapiNo);
							
							criteriaForAddressSearch.add(Restrictions.eq("adr.kapiNo", kapiNoAdNumeric));
						} catch (Exception ex) {
							// Parse Hatası olursa kapiNo = 0L
							System.err.println("Kapıno (" + kapiNo + ") Long tipine parse edilemiyor...");
						}
						criteriaForAddressSearch.add(Restrictions.eq("adr.mahalleCaddeSokak", mahalleCaddeSokakList.get(0)));
						
						List<Adres> tempAddressList = criteriaForAddressSearch.list();
						
						if (tempAddressList != null && tempAddressList.size() > 0) {
							
							return tempAddressList;
						} else {
							
							System.out.println("More than one address found ! in 'readAdresByCriteries' function");
						}
					} else {
						
						System.out.println("More than one districtStreet found ! in 'readAdresByCriteries' function");
					}
				} else {
				
					System.out.println("No any districtStreet found ! in 'readAdresByCriteries' function");
				}
			} catch (Exception ex) {
				
				System.err.println("Error on searching for address in 'readAdresByCriteries' function !");
			}
		}
		return null;
	}
	
	public EntityMetaData readEntityMetaData(String objectClassName) {
		
		EntityMetaData toReturn=null;
		
		SessionFactory sessionFactory = getHibernateTemplate().getSessionFactory();
		
		Map<?,?> metadata = sessionFactory.getAllClassMetadata();
		
		for (Iterator<?> i = metadata.values().iterator(); i.hasNext();) {
			Object classMetaData = i.next();
			EntityPersister persister = (EntityPersister) classMetaData;
			String className = persister.getEntityName();
			
			if (className.equals(objectClassName)) {
				toReturn=new EntityMetaData();
			
				try {
					
					toReturn.setEntityClass(Class.forName(className));
				} catch (ClassNotFoundException e) {
					
					System.err.println("Class named '"+className +"' is unidentified!");
				}
				
				String[] propertyNames = persister.getPropertyNames();
				
				if (propertyNames != null && propertyNames.length > 0) {
					
					SingleTableEntityPersister tableMetadata = (SingleTableEntityPersister) classMetaData;
				    
					HashMap<String, String> propColumnMapping = new HashMap<String, String>();
					
					if (persister.hasIdentifierProperty()){
				    	
						PropertyMapping propMap=(PropertyMapping)classMetaData;
						String pkeyColumnName= propMap.toColumns(persister.getIdentifierPropertyName())[0];
						propColumnMapping.put(persister.getIdentifierPropertyName(), pkeyColumnName);
						
						String tableNameWithSchema=tableMetadata.getTableName();
						int indexOfDot= tableNameWithSchema.indexOf('.');
						String tableName=tableNameWithSchema.substring(indexOfDot+1);
						toReturn.setDbTableName(tableName);
						
						for (int j = 0; j < propertyNames.length; j++) {
	
							String[] columnNames = tableMetadata
									.getPropertyColumnNames(propertyNames[j]);
	
							String columnName = "";
	
							if (columnNames != null && columnNames.length > 0) {
								columnName = columnNames[0];
							}
							
							propColumnMapping.put(propertyNames[j], columnName);
						}
						
						toReturn.setPropertyColumnMappings(propColumnMapping);
				    }
				}
			}
		}
		return toReturn;
	}
	
	public String readAcikAdresByOnemliyer(OnemliYer onemliYer, KurumSabit kurumSabit) {
		
		if (onemliYer!=null && onemliYer.getId()!=null && onemliYer.getId()>0 && kurumSabit!=null && kurumSabit.getId()!=null && kurumSabit.getId()>0){
		
			Long adresId=null;
			
			String mahalleAdi = "",caddeSokakAdi = "",kapiNo = "",altKapiNo = "",adresAciklama = "",ilAdi = "",ilceAdi = "",siteApartmanAdi = "";
			
			List<Object> paramList = new ArrayList<Object>();
			/**
			 * Mahalle ve CaddeSokak adi okuma..
			 */
			String hql = "SELECT mcs FROM MahalleCaddeSokak as mcs,OnemliYer as oy  INNER JOIN mcs.adresler mcs_adr  INNER join mcs_adr.bagimsizlar as mcs_adr_bgm INNER JOIN oy.bagimsiz as oy_bgm   "
					+ " INNER JOIN oy.kurumSabit as ks INNER JOIN mcs.mahalle as mah INNER JOIN mcs.caddeSokak as cad "
					+ " WHERE mcs_adr_bgm.id=oy_bgm.id AND ks.id=? AND oy.id=?";

			paramList.add(kurumSabit.getId());
			
			paramList.add(onemliYer.getId());
			
			List<MahalleCaddeSokak> mcsList = (List<MahalleCaddeSokak>) super.readDBObjectByHQL(hql, paramList.toArray());
			
			if (mcsList != null && mcsList.size() > 0) {
				
				MahalleCaddeSokak mcs=mcsList.get(0);
	
				mahalleAdi=mcs.getMahalle().getAciklama();
				
				caddeSokakAdi=mcs.getCaddeSokak().getAciklama();
			}
			/**
			 * Adres Okuma..
			 */
		 	hql = "SELECT adr FROM Adres as adr, OnemliYer as oy INNER JOIN oy.bagimsiz as oy_bgm  INNER JOIN adr.bagimsizlar adr_bgm "
				+ " INNER JOIN oy.kurumSabit as ks "
				+ " WHERE adr_bgm.id=oy_bgm.id AND ks.id=? AND oy.id=?";
		 
			List<Adres> adresList = (List<Adres>) super.readDBObjectByHQL(hql, paramList.toArray());
			
			if (adresList != null && adresList.size() > 0) {
				
				Adres adr=adresList.get(0);
				
				adresId=adr.getId();
				
				if (adr.getAciklama()!=null) {
					
					adresAciklama=adr.getAciklama();
				}
				if (adr.getKapiNo()!=null) {
					
					kapiNo=adr.getKapiNo().toString();
				}
				if (adr.getAltKapiNo()!=null) {
					
					altKapiNo=adr.getAltKapiNo();
				}
			}
			/**
			 * Bina Okuma..
			 */
		 	hql = "SELECT bina FROM Bina as bina INNER JOIN bina.adresler bn_adr "
				+ " INNER JOIN bn_adr.kurumSabit as ks "
				+ " WHERE ks.id=? AND bn_adr.id=?";
		
		 	paramList.clear();
		 	
		 	paramList.add(kurumSabit.getId());
		 	
		 	paramList.add(adresId);
		 	
			List<Bina> binaList = (List<Bina>) super.readDBObjectByHQL(hql, paramList.toArray());
			
			if (binaList != null && binaList.size() > 0) {
				
				Bina bina=binaList.get(0);
				
				if (bina.getApartmanBlokAd()!=null) {
					
					siteApartmanAdi=bina.getApartmanBlokAd();
				}
			}
			return "Mahalle: " + mahalleAdi+ " Cadde-Sokak: " + caddeSokakAdi + " Apartman:" + (siteApartmanAdi.equals("")?"-":siteApartmanAdi) + " No:" +kapiNo+ (altKapiNo.equals("")?"":"/")+altKapiNo; 
		}
		return null;
	}

	public String readAcikAdresByBagimsiz(Bagimsiz bagimsiz, KurumSabit kurumSabit) {
		
//		if (bagimsiz!=null && bagimsiz.getId()!=null && bagimsiz.getId()>0 && kurumSabit!=null && kurumSabit.getId()!=null && kurumSabit.getId()>0){
//			
//			bagimsiz.getAdres();
//			Adres adres;
//			adres.getAktifEh();
//			Criteria criteria = super.getSession().createCriteria(Adres.class, "adr");
//			if (bagimsiz.getAdres()!=null) {
//				if (bagimsiz.getAdres().getId()!=null) {
//					criteria.add(Restrictions.eq("adr.id", bagimsiz.getAdres().getId()));
//				}
//				if (bagimsiz.getAdres().getAciklama()!=null && !bagimsiz.getAdres().getAciklama().trim().equals("")) {
//					criteria.add(Restrictions.like("adr.aciklama", bagimsiz.getAdres().getAciklama()));
//				}
//				if (bagimsiz.getAdres().getKapiNo()!=null) {
//					criteria.add(Restrictions.eq("adr.kapiNo", bagimsiz.getAdres().getKapiNo()));
//				}
//				if (bagimsiz.getAdres().getAltKapiNo()!=null && !bagimsiz.getAdres().getAltKapiNo().trim().equals("")) {
//					criteria.add(Restrictions.like("adr.kapiNo", bagimsiz.getAdres().getAltKapiNo()));
//				}
//					criteria.add(Restrictions.eq("adr.aktifEh","E"));
//			}
//			
//			
//			
//			return "Mahalle: " + (mahalleAdi.equals("")?"---":mahalleAdi) + " Cadde/Sokak: " + (caddeSokakAdi.equals("")?"---":caddeSokakAdi) + " Apartman: " + (siteApartmanAdi.equals("")?"---":siteApartmanAdi) + " No: " + (kapiNo.equals("")?"-":kapiNo) + "/" +(altKapiNo.equals("")?"-":altKapiNo); 

//			Long adresId=null;
//			
//			String mahalleAdi = "",caddeSokakAdi = "",kapiNo = "",altKapiNo = "",adresAciklama = "",ilAdi = "",ilceAdi = "",siteApartmanAdi = "";
//			
//			List<Object> paramList = new ArrayList<Object>();
//			/**
//			 * Mahalle ve CaddeSokak adi okuma..
//			 */
//			String hql = "SELECT DISTINCT mcs FROM MahalleCaddeSokak as mcs  INNER JOIN mcs.adresler mcs_adr  INNER join mcs_adr.bagimsizlar as mcs_adr_bgm    "
//					+ "  INNER JOIN mcs.mahalle as mah INNER JOIN mcs.caddeSokak as cad "
//					+ " WHERE mcs_adr.kurumSabit.id=? AND mcs_adr_bgm.id=?";
//
//			paramList.add(kurumSabit.getId());
//			
//			paramList.add(bagimsiz.getId());
//			
//			List<MahalleCaddeSokak> mcsList = (List<MahalleCaddeSokak>) super.readDBObjectByHQL(hql, paramList.toArray());
//			
//			if (mcsList != null && mcsList.size() > 0) {
//				
//				MahalleCaddeSokak mcs=mcsList.get(0);
//	
//				mahalleAdi=mcs.getMahalle().getAciklama();
//				
//				caddeSokakAdi=mcs.getCaddeSokak().getAciklama();
//			}
//			/**
//			 * Adres Okuma..
//			 */
//		 	hql = "SELECT adr FROM Adres as adr INNER JOIN adr.bagimsizlar adr_bgm "
//				+ " INNER JOIN adr.kurumSabit as ks "
//				+ " WHERE  ks.id=? AND adr_bgm.id=? ";
//		 
//			List<Adres> adresList = (List<Adres>) super.readDBObjectByHQL(hql, paramList.toArray());
//			
//			if (adresList != null && adresList.size() > 0) {
//				
//				Adres adr=adresList.get(0);
//				
//				adresId=adr.getId();
//				
//				if (adr.getAciklama()!=null) {
//					
//					adresAciklama=adr.getAciklama();
//				}				
//				if (adr.getKapiNo()!=null) {
//				
//					kapiNo=adr.getKapiNo().toString();
//				}
//				if (adr.getAltKapiNo()!=null) {
//				
//					altKapiNo=adr.getAltKapiNo();
//				}
//			}
//			/**
//			 * Bina Okuma..
//			 */
//		 	hql = "SELECT bina FROM Bina as bina INNER JOIN bina.adresler bn_adr "
//				+ " INNER JOIN bn_adr.kurumSabit as ks "
//				+ " WHERE ks.id=? AND bn_adr.id=?";
//		
//		 	paramList.clear();
//		 	
//		 	paramList.add(kurumSabit.getId());
//		 	
//		 	paramList.add(adresId);
//		 	
//			List<Bina> binaList = (List<Bina>) super.readDBObjectByHQL(hql, paramList.toArray());
//			
//			if (binaList != null && binaList.size() > 0) {
//				
//				Bina bina=binaList.get(0);
//				
//				if (bina.getApartmanBlokAd()!=null) {
//					
//					siteApartmanAdi=bina.getApartmanBlokAd();
//				}
//			}
//			return "Mahalle: " + (mahalleAdi.equals("")?"---":mahalleAdi) + " Cadde/Sokak: " + (caddeSokakAdi.equals("")?"---":caddeSokakAdi) + " Apartman: " + (siteApartmanAdi.equals("")?"---":siteApartmanAdi) + " No: " + (kapiNo.equals("")?"-":kapiNo) + "/" +(altKapiNo.equals("")?"-":altKapiNo); 
//		}
		return null;

	}
	
	public List<PlanNot> readPlanNotByPlan(Plan plan, KurumSabit kurumSabit) {
		
		List<Object> paramList = new ArrayList<Object>();
		
		if (kurumSabit!=null && kurumSabit.getId()!=null && kurumSabit.getId()>0 && plan != null) {
			
			Criteria criteria = super.getSession().createCriteria(PlanNot.class, "planNote");
			criteria.createAlias("planNote.plan", "plan");
			
			criteria.add(Restrictions.eq("plan.kurumSabit.id", kurumSabit.getId()));
			
			if (plan.getId()!=null && plan.getId().longValue()>0) {
				criteria.add(Restrictions.eq("plan.id", plan.getId()));
			}
			if (plan.getAdi()!=null && !plan.getAdi().trim().equals("")) {
				criteria.add(Restrictions.like("plan.adi","%"+plan.getAdi()+"%"));
			}
			if (plan.getPlanNo()!=null && !plan.getPlanNo().trim().equals("")) {
				criteria.add(Restrictions.like("plan.planNo","%"+plan.getPlanNo()+"%"));
			}
			
			criteria.addOrder(Order.asc("planNote.sira"));
			
			return criteria.list();

		}
		
		return null;
	}

	public Plan readPlanByCriteria(KadastroParsel kadastroParsel,
			KurumSabit kurumSabit) {
		
		List<Object> paramList = new ArrayList<Object>();
		
		String hql = "Select f.plan FROM KadastroParselGecerliFonksiyon kpgf   inner join  kpgf.fonksiyon f inner join kpgf.kadastroParsel p WHERE  kpgf.kurumSabit.id=? and p.id=? ";

		if (kurumSabit != null && kurumSabit.getId()!=null && kurumSabit.getId() > 0 && kadastroParsel!=null && kadastroParsel.getId()!=null && kadastroParsel.getId()>0) {
			paramList.add(kurumSabit.getId());
			paramList.add(kadastroParsel.getId());
			List<Plan> planList = (List<Plan>) super.readDBObjectByHQL(hql,
					paramList.toArray());
			if (planList.size() > 0) {
				return planList.get(0);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public String readPlanBBKararTarihiAndDurumuByCriteria(Plan plan,
			KurumSabit kurumSabit)
	{	
		if (kurumSabit != null && kurumSabit.getId()!=null && kurumSabit.getId() > 0) {
			String hql = "select P.BB_MECLIS_KARAR_NO2,P.BB_MECLIS_KARAR_TARIHI2 from PLN_PLAN p "+
			"where P.KURUM_ID="+kurumSabit.getId()+" and P.ID='"+plan.getId()+"' AND P.BB_MECLIS_KARAR_DURUMU='K'";
			List<Object> planList=getSession().createSQLQuery(hql).list();		
			
			if (planList.size() > 0) {				
				Object[] temp= (Object[]) planList.get(0);
				String sayi=temp[0]==null?"":""+temp[0];
				Date date=(Date) temp[1];
				SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
				String dateStr=date!=null?sdfSource.format(date):"";
				return ""+sayi+"d"+dateStr;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public String readPlanBKararTarihiAndDurumuByCriteria(Plan plan,KurumSabit kurumSabit)
	{	

		if (kurumSabit != null && kurumSabit.getId()!=null && kurumSabit.getId() > 0 ) {
			String hql = "select P.MECLIS_KARAR_NO,P.MECLIS_KARAR_TARIHI from PLN_PLAN p "+
			"where P.KURUM_ID="+kurumSabit.getId()+" and P.ID='"+plan.getId()+"' AND P.KARAR_DURUMU='K'";
			
			List<Object> planList=getSession().createSQLQuery(hql).list();
			

			if (planList.size() > 0) {
				Object[] temp= (Object[]) planList.get(0);
				String sayi=temp[0]==null?"":""+temp[0];
				Date date=(Date) temp[1];
				SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
				String dateStr=date!=null?sdfSource.format(date):"";
				return ""+sayi+"d"+dateStr;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public boolean readHmaxByPlanID(Plan plan,KadastroParsel parsel)
	{
		String hql = "select f.HMAX from pln_fonksiyon f inner join  num_kadastro_parsel_fonk pk on PK.FONKSIYON_ID=F.ID "+
"where f.PLAN_ID="+plan.getId()+" and PK.PARSEL_ID="+parsel.getId()+" and rownum=1";
		List<BigDecimal> planList=getSession().createSQLQuery(hql).list();
		if (planList.size() > 0) {  
			if(planList.get(0)!=null){				
				if(planList.get(0)==BigDecimal.ZERO) 
				{
					return true;
				} 
			}
			return false;
		} else {
			return false;
		}
			

	}
	public boolean saveAddressList(List<Adres> addressList) {

		if (addressList != null && addressList.size() > 0) {
			
			try {
				
				super.saveOrUpdate(addressList);
				
				return true;
			} catch (Exception ex) {

				System.err.println("Error on saving address list ! ERROR: " + ex.getMessage());
				
				return false;
			}
		}
		return false;
	}
	
	public List<Adres> readAllAdresByID(Long id, KurumSabit kurumSabit) {
		
		Criteria criteria = getSession().createCriteria(Adres.class, "adr");
		
		if (id != null && id > 0 && kurumSabit != null && kurumSabit.getId() > 0) {
			
			criteria.add(Restrictions.eq("adr.id", id));

			criteria.add(Restrictions.eq("adr.kurumSabit.id", kurumSabit.getId()));
			
			try {
				
				return criteria.list();
			} catch (Exception ex) {
			
				System.out.println("Error on searching addressTRS ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		return null;
	}

	public List<OcxFormTable> readAllOcxFormTableByKatmanAdi(String katmanAdi) {
		
		/*Criteria criteria = getSession().createCriteria(OcxFormTable.class, "ocx");
		criteria.add(Restrictions.eq("adr.katmanAdi", katmanAdi));
		try {
			return criteria.list();
		} catch (Exception ex) {
			System.out.println("Error on searching OcxFormTable ! ERROR: " + ex);
			ex.printStackTrace();
		}
		return null;*/
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(new String(katmanAdi));
		String hql = "select o from OcxFormTable o where o.katmanAdi=?";
		List<OcxFormTable> tempList = (List<OcxFormTable>) super.readDBObjectByHQL(hql,  paramList.toArray());
		return tempList;
	}
	
	public List<KadastroParselGecerliFonksiyon> readKadastroParselGecerliFonksiyonByCriteria(KadastroParsel parsel, KurumSabit kurumSabit) {

		if (parsel == null) {
			
			return null;
		}
		try {
			
			Criteria criteria = super.getSession().createCriteria(KadastroParselGecerliFonksiyon.class, "kpgf");
			
			criteria.createAlias("kpgf.kadastroParsel", "kp");
			
			criteria.add(Restrictions.eq("kpgf.kadastroParsel.id", parsel.getId()));
			criteria.add(Restrictions.eq("kpgf.kurumSabit.id", kurumSabit.getId()));
			criteria.add(Restrictions.eq("kp.kurumSabit.id", kurumSabit.getId()));
			
			return criteria.list();
		} catch (Exception ex) {
			
			System.err.println("Error on searching for KadastroParselGecerliFonksiyon in 'readKadastroParselGecerliFonksiyonByCriteria' function !");
			
			ex.printStackTrace();
			
			return null;
		}
	}
	
	public List<KurumUygMD_DB_Map> readAllKurumUygMD_DB_MapByCriteria( KurumUygMD_DB_Map kurumUygMDDBMap, 
			int startIndex, int maxResults ) {

		Criteria criteria = super.getSession().createCriteria(
				KurumUygMD_DB_Map.class, "kumd");
		criteria.setFirstResult(startIndex);
		if( maxResults >= 0 )
			criteria.setMaxResults(maxResults);
		
		if (kurumUygMDDBMap != null) {

			if (kurumUygMDDBMap.getId() != null)
				criteria.add(Restrictions
						.eq("kumd.id", kurumUygMDDBMap.getId()));

			if (kurumUygMDDBMap.getKurumId() != null)
				criteria.add(Restrictions.eq("kumd.kurumId", kurumUygMDDBMap
						.getKurumId()));

			if (kurumUygMDDBMap.getApplicationName() != null
					&& !kurumUygMDDBMap.getApplicationName().equals(""))
				criteria.add(Restrictions.ilike("kumd.applicationName", "%"
						+ kurumUygMDDBMap.getApplicationName() + "%"));

			return criteria.list();

		}

		return null;

	}

	public Long readKurumUygMD_DB_MapKayitSayisiByCriteria( KurumUygMD_DB_Map kurumUygMDDBMap ) {

		Criteria criteria = getSession().createCriteria(
				KurumUygMD_DB_Map.class, "kumd");

		if (kurumUygMDDBMap != null) {

			if (kurumUygMDDBMap.getId() != null)
				criteria.add(Restrictions
						.eq("kumd.id", kurumUygMDDBMap.getId()));

			if (kurumUygMDDBMap.getKurumId() != null)
				criteria.add(Restrictions.eq("kumd.kurumId", kurumUygMDDBMap
						.getKurumId()));

			if (kurumUygMDDBMap.getApplicationName() != null
					&& !kurumUygMDDBMap.getApplicationName().equals(""))
				criteria.add(Restrictions.ilike("kumd.applicationName", "%"
						+ kurumUygMDDBMap.getApplicationName() + "%"));

		}

		return super.kayitSayisiOku(criteria);

	}

	public void saveOrUpdateKurumUygMD_DB_Map(KurumUygMD_DB_Map kurumUygMDDBMap) {
		
		super.saveOrUpdate(kurumUygMDDBMap);
	}

	public void deleteKurumUygMD_DB_Map(KurumUygMD_DB_Map kurumUygMDDBMap) {
		
		super.deleteDBObject(kurumUygMDDBMap);
	}
	
	public List<Bina> readAllBinaByID(Long id, KurumSabit kurumSabit) {
		
		Criteria criteria = getSession().createCriteria(Bina.class, "bld");
		
		if (id != null && id > 0 && kurumSabit != null && kurumSabit.getId() > 0) {
			
			criteria.add(Restrictions.eq("bld.id", id));

			criteria.add(Restrictions.eq("bld.kurumSabit.id", kurumSabit.getId()));
			
			try {
				
				return criteria.list();
			} catch (Exception ex) {
			
				System.out.println("Error on searching building ! ERROR: " + ex);
				
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public Plan readPlanByID(Long planID) {
		
		Criteria criteria = super.getSession().createCriteria(Plan.class, "pln");
		
		criteria.add(Restrictions.eq("pln.id", planID));
		
		criteria.add(Restrictions.eq("pln.aktifMi", Long.parseLong("1")));
		
		List<Plan> resultPlanList = criteria.list();
		
		if (resultPlanList != null && resultPlanList.size() == 1) {
			
			return resultPlanList.get(0);
		} else {
			
			return null;
		}
	}
	public String checkParcelRestrictions(KadastroParsel parsel)
	{
		try{
		String returnMessage="";
		//returnMessage[0]="";
		//returnMessage[1]="";
		if(parsel!=null){
		String hql = "SELECT A.ACIKLAMA, A.TARIH , A.SAYI,A.ETKI_DURUMU "+ 
					 "FROM PLN_PLAN_KISIT a,NUM_KADASTRO_PARSEL b "+
					 "WHERE B.ID="+parsel.getId()+" and a.AKTIF_EH = 'E' AND SDO_RELATE(a.GEOMETRY,b.GEOMETRY,'mask=ANYINTERACT querytype=WINDOW') = 'TRUE'";
		
		List<Object> planList=getSession().createSQLQuery(hql).list();	

		if (planList.size() > 0) {
			int i=1;
			for (Object object : planList) {
				Object[] temp= (Object[]) object;
				String etki=""+temp[3];
				if(etki.equals("E"))
				{
					returnMessage="E";
				}else
				{
					returnMessage+=returnMessage.equals("E")?"E":"U";
				}
				String Aciklama=""+temp[0];
				returnMessage+=i+".<br/>"+"&nbsp;&nbsp;&nbsp;<b>A\u00e7\u0131klama:</b>"+Aciklama;
				Date date=(Date) temp[1];
				SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
				String dateStr=date!=null?sdfSource.format(date):"";
				if(dateStr!=""){
					String Tarih=sdfSource.format(date);
					returnMessage+="<br/>&nbsp;&nbsp;&nbsp;<b>Tarih:</b>"+Tarih;
				}
				String Sayi=""+(temp[2]==null?"":temp[2]);
				if(!Sayi.equals(""))
				{
					returnMessage+="<br/>&nbsp;&nbsp;&nbsp;<b>Say\u0131:</b>"+Sayi;	
				}
				returnMessage+="<br/>";
				i++;
			}		
			return returnMessage;
		} else {
			return null;
		}
		}
		return null;
		}catch (Exception ex) {
			System.out.println("Error on checkParcelRestrictions function ! ERROR: " + ex);			
			ex.printStackTrace();
			return null;
			
		}
		
	}
	
}	
