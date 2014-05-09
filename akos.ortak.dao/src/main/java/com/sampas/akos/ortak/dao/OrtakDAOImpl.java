package com.sampas.akos.ortak.dao;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.order.PropertyAuditOrder;
import org.hibernate.envers.query.projection.PropertyAuditProjection;
import org.hibernate.envers.query.property.EntityPropertyName;
import org.hibernate.envers.query.property.RevisionPropertyPropertyName;
import org.hibernate.type.Type;
import org.springframework.jdbc.object.SqlQuery;

import com.sampas.akos.common.constant.AkosSorgulamaTipleri;
import com.sampas.akos.common.dao.BaseDaoSupport;
import com.sampas.akos.ortak.model.Adres;
import com.sampas.akos.ortak.model.Bagimsiz;
import com.sampas.akos.ortak.model.BagimsizKullanimDetay;
import com.sampas.akos.ortak.model.BagimsizKullanimGrup;
import com.sampas.akos.ortak.model.BagimsizKullanimSinif;
import com.sampas.akos.ortak.model.BagimsizTapu;
import com.sampas.akos.ortak.model.BilgiTipi;
import com.sampas.akos.ortak.model.Bina;
import com.sampas.akos.ortak.model.BinaBelge;
import com.sampas.akos.ortak.model.BinaCatiTur;
import com.sampas.akos.ortak.model.BinaNitelik;
import com.sampas.akos.ortak.model.BinaOrtakKullanim;
import com.sampas.akos.ortak.model.BinaParsel;
import com.sampas.akos.ortak.model.BinaRisk;
import com.sampas.akos.ortak.model.BinaShort;
import com.sampas.akos.ortak.model.BinaTesisat;
import com.sampas.akos.ortak.model.Bolge;
import com.sampas.akos.ortak.model.CaddeSokak;
import com.sampas.akos.ortak.model.CaddeSokakTur;
import com.sampas.akos.ortak.model.DosemeTur;
import com.sampas.akos.ortak.model.GelismislikDurum;
import com.sampas.akos.ortak.model.Grafik;
import com.sampas.akos.ortak.model.HareketKod;
import com.sampas.akos.ortak.model.InsaatTur;
import com.sampas.akos.ortak.model.IsitmaTur;
import com.sampas.akos.ortak.model.IslemMenu;
import com.sampas.akos.ortak.model.KadastroHareketGrup;
import com.sampas.akos.ortak.model.KadastroHareketKod;
import com.sampas.akos.ortak.model.KadastroParsel;
import com.sampas.akos.ortak.model.KadastroParselTipi;
import com.sampas.akos.ortak.model.KonutDurum;
import com.sampas.akos.ortak.model.KonutSahiplikDurum;
import com.sampas.akos.ortak.model.KonutTip;
import com.sampas.akos.ortak.model.Kullanicilar;
import com.sampas.akos.ortak.model.KullanimSekli;
import com.sampas.akos.ortak.model.KurumSabit;
import com.sampas.akos.ortak.model.Mahalle;
import com.sampas.akos.ortak.model.MahalleCaddeSokak;
import com.sampas.akos.ortak.model.SicakSuTeminTur;
import com.sampas.akos.ortak.model.Site;
import com.sampas.akos.ortak.model.SysAudit;
import com.sampas.akos.ortak.model.Tapu;
import com.sampas.akos.ortak.model.TapuMahalle;
import com.sampas.akos.ortak.model.TasiyiciSistem;
import com.sampas.akos.ortak.model.UretimKaynak;
import com.sampas.akos.ortak.model.YakitTur;
import com.sampas.akos.ortak.model.YapiAda;
import com.sampas.akos.ortak.model.YapiCepheTur;
import com.sybase.jdbc3.tds.ReusableLanguageToken;

@SuppressWarnings("unchecked")
public class OrtakDAOImpl extends BaseDaoSupport implements OrtakDAO {

	private AuditReader reader;

	public AuditReader getAuditReader() {
		reader = AuditReaderFactory.get(this.getSession());

		return reader;
	}
	  
	 
	 public List<BinaRisk> readBinaRiskBilgiByBinaId(Long p_binano)
		{
			List<BinaRisk> belgeler = new ArrayList<BinaRisk>();
		       
	        String procedure = "{CALL num_bina_risk_sorgula_list (?,?)}";   
	        try {   
	            CallableStatement cstmt = this.getSession().connection().prepareCall(procedure);   
	            cstmt.registerOutParameter(2,OracleTypes.CURSOR);
	            cstmt.setLong(1,p_binano)                                                           ;            
	            cstmt.execute();
	            
	            ResultSet rs = (ResultSet) cstmt.getObject(2);
	            
	            while (rs.next())
	            {
	            	BinaRisk _binaBelge =  new BinaRisk();
	            	_binaBelge.setYapiRisk(rs.getString(1));
	            	_binaBelge.setYapiRiskAciklama(rs.getString(2));	            	
	            	belgeler.add(_binaBelge);
	            }
	            rs.close();
	            cstmt.close();
	        } catch (SQLException e) {     
	            e.printStackTrace();   
	        }   		
			
			
			return belgeler;
		}	
	 
	
	public Kullanicilar login(Long p_kurumKodu,String p_username,String p_password)
	{
		Kullanicilar kullanicilar = null;
	       
        String procedure = "{CALL SRV_SABIT_SERVIS.readkullaniciforlogin (?,?,?,?)}";   
        try {   
            CallableStatement cstmt = this.getSession().connection().prepareCall(procedure);   
            cstmt.registerOutParameter(4,OracleTypes.CURSOR);
            cstmt.setLong(1,p_kurumKodu)                                                           ;            
            cstmt.setString(2,p_username)                                                           ;   
            cstmt.setString(3,p_password);   
            cstmt.execute();
            
            ResultSet rs = (ResultSet) cstmt.getObject(4);
            if (rs.next())
            {
           	 //K.KULLANICI_KODU,K.KULLANICI_ADI,K.KULLANICI_SOYADI, K.AKOSCEP_YETKI_EH, K.KURUM_ID AS KURUM_KODU            	
            	kullanicilar = new Kullanicilar();
            	kullanicilar.setKullaniciKodu(rs.getString(1));
            	kullanicilar.setKullaniciAdi(rs.getString(2));            	
            	kullanicilar.setKullaniciSoyadi(rs.getString(3));
            }
            rs.close();
            cstmt.close();
            
        } catch (SQLException e) {     
            e.printStackTrace();   
        }   		
		
		
		return kullanicilar;
	}
	
	public List<BinaBelge> readBelgelerByBinaNo(Long p_binano)
	{
		List<BinaBelge> belgeler = new ArrayList<BinaBelge>();
	       
        String procedure = "{CALL ARS_BELGELER_LISTBYBINANO (?,?)}";   
        try {   
            CallableStatement cstmt = this.getSession().connection().prepareCall(procedure);   
            cstmt.registerOutParameter(2,OracleTypes.CURSOR);
            cstmt.setLong(1,p_binano)                                                           ;            
            cstmt.execute();
            
            ResultSet rs = (ResultSet) cstmt.getObject(2);
            
            while (rs.next())
            {
            	BinaBelge _binaBelge =  new BinaBelge();
            	_binaBelge.setBelgeId(rs.getLong(1));
            	_binaBelge.setArsivId(rs.getLong(2));
            	_binaBelge.setBelgeTarihi(rs.getDate(3));
            	_binaBelge.setBelgeTuru(rs.getLong(4));
            	_binaBelge.setKonuKodu(rs.getLong(5));
            	_binaBelge.setDysId(rs.getString(6));
            	_binaBelge.setBelgeTipi(rs.getLong(7));
            	_binaBelge.setAciklama(rs.getString(8));
            	_binaBelge.setKonuAciklama(rs.getString(9));
            	_binaBelge.setKonuOnemDerecesi(rs.getString(10));
            	_binaBelge.setTurAciklama(rs.getString(11));            	
            	_binaBelge.setTipAciklama(rs.getString(12));        
            	_binaBelge.setDosyaUrl(rs.getString(13));
            	belgeler.add(_binaBelge);
            }
            rs.close();
            cstmt.close();
        } catch (SQLException e) {     
            e.printStackTrace();   
        }   		
		
		
		return belgeler;
	}	
	
	
	public List<Bina> readAllBinaByCriteriaNew (
			KurumSabit kurumSabit,Bina bina, Adres adres,KadastroParsel kadastroParsel,
			MahalleCaddeSokak mahalleCaddeSokak) {

			if (kurumSabit != null && kurumSabit.getId() != null) {

				List<Object> paramList = new ArrayList<Object>();

				String hql = "Select Distinct bn from Adres as adr inner join adr.bina as bn inner join adr.kadastroParsel as kp "
				+ "  WHERE kp.kurumSabit.id=? AND adr.kurumSabit.id=? and bn.kurumSabit.id=? ";				
				
//				String hql = "SELECT adr FROM Adres as adr inner join adr.kadastroParsel AS kp inner join adr.mahalleCaddeSokak as mcs inner join adr.bina AS bn  "
//						+ "  WHERE kp.kurumSabit.id=? AND adr.kurumSabit.id=? and bn.kurumSabit.id=?";

				paramList.add(kurumSabit.getId());				
				paramList.add(kurumSabit.getId());
				paramList.add(kurumSabit.getId());

				if (mahalleCaddeSokak != null) {
					if (mahalleCaddeSokak.getMahalle() != null
							&& mahalleCaddeSokak.getMahalle().getId() != null) {

						hql += " AND mcs.mahalle.id=?";
						paramList.add(mahalleCaddeSokak.getMahalle().getId());
					}

					if (mahalleCaddeSokak.getCaddeSokak() != null
							&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {

						hql += " AND mcs.caddeSokak.id=?";
						paramList.add(mahalleCaddeSokak.getCaddeSokak().getId());
					}

				}

				if (adres != null) {

					if (adres.getKapiNo() != null) {
						hql += " AND adr.kapiNo=?";
						paramList.add(adres.getKapiNo());
					}
				}

				if (kadastroParsel != null) {
					
					if (kadastroParsel.getParsel() != null) {
						if (!kadastroParsel.getParsel().toString().trim().equals("")) {
							
							hql += " AND adr.kadastroParsel.parsel=?";
							paramList.add(kadastroParsel.getParsel());							
							
						}
					}
					if (kadastroParsel.getPafta() != null) {
						if (!kadastroParsel.getPafta().trim().equals("")) {

							hql += " AND adr.kadastroParsel.pafta=?";
							paramList.add(kadastroParsel.getPafta().trim());							
							

						}
					}
					if (kadastroParsel.getAda() != null) {
						if (!kadastroParsel.getAda().toString().trim().equals("")) {
							hql += " AND adr.kadastroParsel.ada=?";
							paramList.add(kadastroParsel.getPafta());							
						}
					}
					
				}
				
				
				//hql += " ORDER BY kp.id ";

				
				
				List<Bina> findList = (List<Bina>) super.readDBObjectByHQL(hql,
						paramList.toArray());

				if (findList != null && findList.size() > 0) {
					return findList;
				}
				else
					return null;
				

			} else {

				return null;
			}
		}

	
	
	
	
	
	public List<Adres> readAllKadastroParselByAdresNew(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak) {

		if (kurumSabit != null && kurumSabit.getId() != null) {

			List<Object> paramList = new ArrayList<Object>();

			String hql = "SELECT adr FROM Adres as adr inner join adr.kadastroParsel AS kp inner join adr.mahalleCaddeSokak as mcs   "
					+ "  WHERE kp.kurumSabit.id=? AND adr.kurumSabit.id=? ";

			paramList.add(kurumSabit.getId());
			paramList.add(kurumSabit.getId());

			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getMahalle() != null
						&& mahalleCaddeSokak.getMahalle().getId() != null) {

					hql += " AND mcs.mahalle.id=?";
					paramList.add(mahalleCaddeSokak.getMahalle().getId());
				}

				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {

					hql += " AND mcs.caddeSokak.id=?";
					paramList.add(mahalleCaddeSokak.getCaddeSokak().getId());
				}

			}

			if (adres != null) {
				if (adres.getId() != null) {

					hql += " AND adr.id=?";
					paramList.add(adres.getId());
				}

				if (adres.getKapiNo() != null) {

					hql += " AND adr.kapiNo=?";
					paramList.add(adres.getKapiNo());
				}

				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {

					hql += " AND adr.altKapiNo=?";
					paramList.add(adres.getAltKapiNo().trim());
				}

			}

			hql += " ORDER BY kp.id ";

			
			
			List<Adres> findList = (List<Adres>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (findList != null && findList.size() > 0) {
				return findList;
			}
			else
				return null;
			

		} else {

			return null;
		}
	}
	
	
	
	
	public List<Mahalle> readAllMahalle(KurumSabit kurumsabit) {

		if (kurumsabit != null && kurumsabit.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT m FROM Mahalle m where m.id > 0 ";
			hql += " AND m.kurumSabit.id=? Order by m.aciklama asc ";
			paramList.add(kurumsabit.getId());

			List<Mahalle> list = (List<Mahalle>) super.readDBObjectByHQL(hql,paramList.toArray());

			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} else {
			return null;
		}
	}
	
	
	
	public List<Bagimsiz> readAllCaddeSokakByKurum(Adres adres,
			KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null && adres != null
				&& adres.getId() != null) {
			StringBuilder hql = new StringBuilder();
			List<Object> list = new ArrayList<Object>();
			hql.append("SELECT bgm FROM Bagimsiz As bgm ");
			hql.append("INNER JOIN bgm.adres AS adr ");
			hql.append("WHERE adr.id=? ");
			list.add(adres.getId());
			hql.append("AND adr.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
			List<Bagimsiz> tempList = (List<Bagimsiz>) super.readDBObjectByHQL(
					hql.toString(), list.toArray());
			return tempList;
		} else {
			return null;
		}
	}	
	
	public List<KadastroParsel> readAllKadastroParselByParselMah(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel) {
/*
		if (kurumSabit != null && kurumSabit.getId() != null
				&& kadastroParsel != null) {

			if (kadastroParsel.getPafta() == null
					&& kadastroParsel.getParsel() == null
					&& kadastroParsel.getAda() == null
					&& kadastroParsel.getMahalle()==null
					) {
				return null;
			}
*/
			Criteria criteria = super.getSession().createCriteria(
					KadastroParsel.class, "parcel");
			
			criteria.add(Restrictions.eq("parcel.kurumSabit.id", kurumSabit.getId()));
			
			if (kadastroParsel.getParsel() != null) {
				if (!kadastroParsel.getParsel().toString().trim().equals("")) {
					criteria.add(Restrictions.eq("parcel.parsel", kadastroParsel
							.getParsel()));
				}
			}
			if (kadastroParsel.getPafta() != null) {
				if (!kadastroParsel.getPafta().trim().equals("")) {
					criteria.add(Restrictions.eq("parcel.pafta",
							kadastroParsel.getPafta()).ignoreCase());
				}
			}
			if (kadastroParsel.getAda() != null) {
				if (!kadastroParsel.getAda().toString().trim().equals("")) {
					criteria.add(Restrictions.eq("parcel.ada", kadastroParsel
							.getAda()));
				}
			}
			
	
			/*
			if (kadastroParsel.getId() != null) {
				if (!kadastroParsel.getId().toString().trim().equals("")) {				
					criteria.add(Restrictions.eq("parcel.id", kadastroParsel
							.getId()));
				}
			}
			*/
			
			if( kadastroParsel.getMahalle() != null ){
				if( kadastroParsel.getMahalle().getId() != null ){
					if (!kadastroParsel.getMahalle().getId().toString().trim().equals("")) {
						criteria.add(Restrictions.eq("parcel.mahalle.id", kadastroParsel.getMahalle().getId()));
					}
				}
			}
		
			return criteria.list();
		
		//return null;
	}
	
	
	
	public Long readNumberOfRevisionsOfEntity(Class c, Long id) {
		PropertyAuditProjection auditProjection = new PropertyAuditProjection(
				new EntityPropertyName("id"), "count", false);
		int sayi = (Integer) getAuditReader().createQuery()
				.forRevisionsOfEntity(c, true, false).add(
						AuditEntity.id().eq(id)).getResultList().size();
		return new Long(sayi);
	}

	public List readRevisionsOfEntity(Class c, Long id, int startIndex,
			int maxResults) {

		AuditQuery query = AuditReaderFactory.get(this.getSession())
				.createQuery().forRevisionsOfEntity(c, false, true);
		query.add(AuditEntity.id().eq(id));
		query.setFirstResult(startIndex);
		PropertyAuditOrder pao = new PropertyAuditOrder(
				new RevisionPropertyPropertyName("kayitTarih"), true);
		query.addOrder(pao);
		List<Object[]> rersults = query.getResultList();

		return rersults;
	}

	public Long readNumberOfDeletedRevisionsOfEntity(Class c, Long sicil) {
		AuditQuery query = getAuditReader().createQuery().forRevisionsOfEntity(
				c, false, true);
		query.add(AuditEntity.revisionType().eq(RevisionType.DEL));
		if (sicil != null) {
			query.add(AuditEntity.relatedId("mukellef").eq(sicil));
		}
		int sayi = query.getResultList().size();
		return new Long(sayi);
	}

	public List readDeletedRevisionsOfEntity(Class c, Long sicil,
			int startIndex, int maxResults) {

		AuditQuery query = AuditReaderFactory.get(this.getSession())
				.createQuery().forRevisionsOfEntity(c, false, true);
		query.add(AuditEntity.revisionType().eq(RevisionType.DEL));
		if (sicil != null) {
			query.add(AuditEntity.relatedId("mukellef").eq(sicil));
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(maxResults);
		PropertyAuditOrder pao = new PropertyAuditOrder(
				new RevisionPropertyPropertyName("kayitTarih"), true);
		query.addOrder(pao);
		List<Object[]> rersults = query.getResultList();

		return rersults;
	}

	public List<?> readRevisionsOfEntityWithRevisionId(Class c, int revisionId,
			Long id) {
		List<?> history = (List<?>) getAuditReader().createQuery()
				.forEntitiesAtRevision(c, revisionId).getResultList();
		return history;
	}

	public List<Number> readRevisions(Class c, Object obj) {
		List<Number> history = getAuditReader().getRevisions(c, obj);
		return history;
	}

	public List<?> readAllObjectsByAciklama(Class obj, String aciklama,
			int maxResultCount) {
		if (obj == null && aciklama != null && !aciklama.trim().equals("")) {
			return null;
		}

		Criteria criteria = this.getSession().createCriteria(obj);
		if (aciklama != null && !aciklama.trim().equals("")) {
			String aciklamaObj = null;
			if (aciklama.contains("*"))
				aciklamaObj = aciklama.toString().replaceAll("[*]", "%");
			else
				aciklamaObj = aciklama + "%";
			criteria.add(Restrictions.like("aciklama", aciklamaObj)
					.ignoreCase());
			criteria.addOrder(Order.asc("aciklama"));
		} else {
			return null;
		}
		criteria.setMaxResults(maxResultCount);
		return criteria.list();
	}
	public List<Number> readKapiNoByCaddeSokak(KurumSabit kurumSabit,CaddeSokak caddeSokak)
	{
		List<Number> returnData = null;
		if (kurumSabit != null && kurumSabit.getId() != null && caddeSokak != null
				&& caddeSokak.getId() != null) {
			String hql = "";
			hql+="select distinct adres0_.KAPI_NO from "+
            "NUM_ADRES adres0_,"+
            "ORT_MAHALLE_CADDE mahallecad1_,"+
            "ORT_MAHALLE_KOYLER mahalle2_,"+
            "ORT_CADDE_SOKAK caddesokak3_ "+
        "where adres0_.MAHALLE_CADDE_ID=mahallecad1_.ID "+
            "and mahallecad1_.MAHALLE_ID=mahalle2_.ID "+
            "and mahallecad1_.CADDE_SOKAK_ID=caddesokak3_.ID "+
            "and 1=1 "+
            "and adres0_.KURUM_ID="+kurumSabit.getId()+ 
            " and mahalle2_.KURUM_ID="+kurumSabit.getId()+
            " and caddesokak3_.KURUM_ID="+kurumSabit.getId()+
            " and caddesokak3_.ID="+caddeSokak.getId()+        
        " order by 1";
//			hql+="SELECT DISTINCT KAPI_NO FROM NUM_ADRES ";			
//			hql+=" WHERE MAHALLE_CADDE_ID="+caddeSokak.getId();			
//			hql+=" AND KURUM_ID="+kurumSabit.getId();
//			hql+=" order by 1";
			
			returnData=getSession().createSQLQuery(hql).list();
		}
			return returnData;		
	}
	public List<String> readAltKapiNoByCaddeSokakKapiNo(KurumSabit kurumSabit,CaddeSokak caddeSokak,Number kapi_no)
	{
		List<String> returnData = null;
		if (kurumSabit != null && kurumSabit.getId() != null && caddeSokak != null
				&& caddeSokak.getId() != null) {
			String hql = "";
			hql+="select distinct adres0_.ALT_KAPI_NO from "+
            "NUM_ADRES adres0_,"+
            "ORT_MAHALLE_CADDE mahallecad1_,"+
            "ORT_MAHALLE_KOYLER mahalle2_,"+
            "ORT_CADDE_SOKAK caddesokak3_ "+
        "where adres0_.MAHALLE_CADDE_ID=mahallecad1_.ID "+
            "and mahallecad1_.MAHALLE_ID=mahalle2_.ID "+
            "and mahallecad1_.CADDE_SOKAK_ID=caddesokak3_.ID "+
            "and 1=1 "+
            "and adres0_.KURUM_ID="+kurumSabit.getId()+ 
            " and mahalle2_.KURUM_ID="+kurumSabit.getId()+
            " and caddesokak3_.KURUM_ID="+kurumSabit.getId()+
            " and caddesokak3_.ID="+caddeSokak.getId()+
            " and adres0_.KAPI_NO="+kapi_no+
        " order by 1";		
//			hql+="SELECT DISTINCT ALT_KAPI_NO FROM NUM_ADRES ";			
//			hql+="WHERE MAHALLE_CADDE_ID="+caddeSokak.getId();	
//			hql+=" AND KURUM_ID="+kurumSabit.getId();			
//			hql+=" AND KAPI_NO="+kapi_no;
//			hql+=" order by 1";
			returnData=getSession().createSQLQuery(hql).list();
		}
			return returnData;	
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			KurumSabit kurumSabit, String aciklama, int maxResultCount,
			String sorguTipi) {
		// sorgulama yapabilmek icin kurumSabitId ve mahalleCaddeSokak
		// nesnesinin en az bir propertisinin ve sorgulama tipinin belirtilmis
		// olmasi gerekir
		if (kurumSabit == null || kurumSabit.getId() == null
				|| aciklama == null || aciklama.trim().equals("")
				|| sorguTipi == null || sorguTipi.trim().equals("")) {
			return null;
		}

		String aciklamaObj = null;
		if (aciklama.contains("*"))
			aciklamaObj = aciklama.replaceAll("[*]", "%");
		else
			aciklamaObj = "%" + aciklama + "%";

		Criteria criteria = getSession().createCriteria(
				MahalleCaddeSokak.class, "mcs");
		criteria.createAlias("mcs.mahalle", "mah");
		criteria.createAlias("mcs.caddeSokak", "cad");
		criteria.add(Restrictions.eq("mah.kurumSabit.id", kurumSabit.getId()));
		criteria.add(Restrictions.eq("cad.kurumSabit.id", kurumSabit.getId()));

		if (sorguTipi.equals(AkosSorgulamaTipleri.CADDE_SOKAK_TIP)) {
			criteria.add(Restrictions.like("cad.aciklama", aciklamaObj)
					.ignoreCase());
		} else if (sorguTipi.equals(AkosSorgulamaTipleri.MAHALLE_TIP)) {
			criteria.add(Restrictions.like("mah.aciklama", aciklamaObj)
					.ignoreCase());
		} else {
			criteria.add(Restrictions.or(Restrictions.like("mah.aciklama",
					aciklamaObj).ignoreCase(), Restrictions.like(
					"cad.aciklama", aciklamaObj).ignoreCase()));
		}
		criteria.addOrder(Order.asc("mah.aciklama")).addOrder(
				Order.asc("cad.aciklama"));
		criteria.setMaxResults(maxResultCount);

		return criteria.list();
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(Adres adres,
			KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null && adres != null
				&& adres.getId() != null) {
			StringBuilder hql = new StringBuilder();
			List<Object> list = new ArrayList<Object>();
			hql.append("SELECT bgm FROM Bagimsiz As bgm ");
			hql.append("INNER JOIN bgm.adres AS adr ");
			hql.append("WHERE adr.id=? ");
			list.add(adres.getId());
			hql.append("AND adr.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
			List<Bagimsiz> tempList = (List<Bagimsiz>) super.readDBObjectByHQL(
					hql.toString(), list.toArray());
			return tempList;
		} else {
			return null;
		}
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(Bina bina,
			KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null && bina != null
				&& bina.getId() != null) {
			String hql = "Select bgm from Bagimsiz bgm inner join bgm.bina bn inner join bgm.adres as adr"
					+ " Where  bn.id=? And bgm.aktifEh=? And bn.kurumSabit.id=? order by adr.kapiNo,bgm.katNo,bgm.daireNo ";
			List<Bagimsiz> tempList = (List<Bagimsiz>) super
					.readDBObjectByHQL(hql, new Object[] { bina.getId(), "E",
							kurumSabit.getId() });
			return tempList;
		} else {
			return null;
		}
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null
				&& kadastroParsel != null && kadastroParsel.getId() != null) {
			String hql = "Select bgm from Bagimsiz as bgm inner join bgm.adres as adr inner join adr.kadastroParsel as kp "
					+ " Where  kp.id=?  And bgm.aktifEh=? And kp.kurumSabit.id=? ";
			List<Bagimsiz> tempList = (List<Bagimsiz>) super.readDBObjectByHQL(
					hql, new Object[] { kadastroParsel.getId(), "E",
							kurumSabit.getId() });
			return tempList;
		} else {
			return null;
		}
	}

	public List<Bina> readAllBinaByCriteria(Adres adres, KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null && adres != null
				&& adres.getId() != null) {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT bn FROM Adres adr inner join adr.bina AS bn ");
			
			hql.append("WHERE adr.id=? ");
			hql.append("WHERE adr.kurumSabit.id=? ");
			hql.append("AND bn.kurumSabit.id=? ");
			
			List<Bina> tempList = (List<Bina>) super.readDBObjectByHQL(hql
					.toString(), new Object[] { adres.getId(), kurumSabit.getId(),
					kurumSabit.getId() });
			return tempList;
		} else {
			return null;
		}
	}

	public List<Bina> readAllBinaByCriteria(KadastroParsel kadastroParsel,
			KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null
				&& kadastroParsel != null && kadastroParsel.getId() != null) {
			String hql = "Select Distinct bn from Adres adr inner join adr.bina bn inner join adr.kadastroParsel as kp "
					+ " Where  kp.id=? And bn.aktifEh=? ";
			hql += " And kp.kurumSabit.id=? And bn.kurumSabit.id=?";

			List<Bina> tempList = (List<Bina>) super.readDBObjectByHQL(hql,
					new Object[] { kadastroParsel.getId(), "E",
							kurumSabit.getId(), kurumSabit.getId() });
			return tempList;
		} else {
			return null;
		}
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null) {
			Criteria criteria = super.getSession().createCriteria(Adres.class,
					"adr").createCriteria("mahalleCaddeSokak", "mcs");
			criteria.createAlias("mcs.mahalle", "mh");
			criteria.createAlias("mcs.caddeSokak", "cd");
			criteria.add(Restrictions.eq("adr.kurumSabit.id", kurumSabit
					.getId()));
			criteria.add(Restrictions
					.eq("mh.kurumSabit.id", kurumSabit.getId()));
			criteria.add(Restrictions
					.eq("cd.kurumSabit.id", kurumSabit.getId()));

			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getId() != null) {
					criteria.add(Restrictions.eq("mcs.id", mahalleCaddeSokak
							.getId()));
				}
				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {
					criteria.add(Restrictions.eq("cd.id", mahalleCaddeSokak
							.getCaddeSokak().getId()));
				}
			
			}

			if (adres != null) {
				if (adres.getId() != null) {
					criteria.add(Restrictions.eq("adr.id", adres.getId()));
				}
				if (adres.getKapiNo() != null) {
					criteria.add(Restrictions.eq("adr.kapiNo", adres
							.getKapiNo()));
				}
				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {
					criteria.add(Restrictions.eq("adr.altKapiNo", adres
							.getAltKapiNo()));
				}
				if (!adres.getAktifEh().equals("")) {
					criteria.add(Restrictions.eq("adr.aktifEh", adres
							.getAktifEh()));
				}
			}
			List aliasEntitiesMap = criteria.setResultTransformer(
					CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();

			List<MahalleCaddeSokak> tempList = new ArrayList<MahalleCaddeSokak>();

			Iterator iterator = aliasEntitiesMap.iterator();

			while (iterator.hasNext()) {
				Map resultMap = (Map) iterator.next();
				MahalleCaddeSokak mcs = (MahalleCaddeSokak) resultMap
						.get("mcs");
				tempList.add(mcs);
			}

			return tempList;
		} else {
			return null;
		}
	}

	public List<Adres> readAllAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit, int startRow, int maxResults) {

		if (kurumSabit != null && kurumSabit.getId() != null) {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT adr FROM Adres adr ");
			hql.append("INNER JOIN adr.mahalleCaddeSokak mcs ");
			hql.append("INNER JOIN mcs.mahalle mh ");
			hql.append("INNER JOIN mcs.caddeSokak cd ");
			hql.append("WHERE 1=1 ");
			hql.append("AND adr.kurumSabit.id=:krmSbtid ");
			hql.append("AND mh.kurumSabit.id=:krmSbtid ");
			hql.append("AND cd.kurumSabit.id=:krmSbtid ");

			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getId() != null) {
					hql.append("AND mcs.id=:mcsid ");
				}
				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {
					hql.append("AND cd.id=:cdid ");
				}
			}

			if (adres != null) {
				if (adres.getId() != null) {
					hql.append("AND adr.id=:adrid ");
				}
				if (adres.getKapiNo() != null) {
					hql.append("AND adr.kapiNo=:adrkapiNo ");
				}
				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {
					hql.append("AND adr.altKapiNo=:adraltKapiNo ");
				}
				if (!adres.getAktifEh().equals("")) {
					hql.append("AND adr.aktifEh=:adraktifEh ");
				}
			}
			hql.append("ORDER BY mh.aciklama, cd.aciklama ASC ");
			// //////////////////////////////////////////
			Query q = super.getSession().createQuery(hql.toString());
			q.setParameter("krmSbtid", kurumSabit.getId());

			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getId() != null) {
					q.setParameter("mcsid", mahalleCaddeSokak.getId());
				}
				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {
					q.setParameter("cdid", mahalleCaddeSokak.getCaddeSokak()
							.getId());
				}
			}

			if (adres != null) {
				if (adres.getId() != null) {
					q.setParameter("adrid", adres.getId());
				}
				if (adres.getKapiNo() != null) {
					q.setParameter("adrkapiNo", adres.getKapiNo());
				}
				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {
					q.setParameter("adraltKapiNo", adres.getAltKapiNo());
				}
				if (!adres.getAktifEh().equals("")) {
					q.setParameter("adraktifEh", adres.getAktifEh());
				}
			}
			q.setFirstResult(startRow);
			if (maxResults >= 0)
				q.setMaxResults(maxResults);
			List<Adres> list = q.list();

			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} else {
			return null;
		}
	}

	public Long readTotalNumberAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null) {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT count(adr.id) FROM Adres AS adr ");
			hql.append("INNER JOIN adr.mahalleCaddeSokak AS mcs ");
			hql.append("LEFT OUTER JOIN mcs.mahalle mh ");
			hql.append("LEFT OUTER JOIN mcs.caddeSokak cd ");
			hql.append("WHERE 1=1 ");
			hql.append("AND adr.kurumSabit.id= :krmSbtid ");
			hql.append("AND mh.kurumSabit.id= :krmSbtid ");
			hql.append("AND cd.kurumSabit.id= :krmSbtid ");
			// count(eb.id)
			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getId() != null) {
					hql.append("AND mcs.id= :mcsid ");
				}
				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {
					hql.append("AND cd.id= :cdid ");
				}
			}

			if (adres != null) {
				if (adres.getId() != null) {
					hql.append("AND adr.id= :adrid ");
				}
				if (adres.getKapiNo() != null) {
					hql.append("AND adr.kapiNo= :adrkapiNo ");
				}
				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {
					hql.append("AND adr.altKapiNo= :adraltKapiNo ");
				}
				if (!adres.getAktifEh().equals("")) {
					hql.append("AND adr.aktifEh= :adraktifEh ");
				}
			}
			Query q = super.getSession().createQuery(hql.toString());
			q.setParameter("krmSbtid", kurumSabit.getId());

			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getId() != null) {
					q.setParameter("mcsid", mahalleCaddeSokak.getId());
				}
				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {
					q.setParameter("cdid", mahalleCaddeSokak.getCaddeSokak()
							.getId());
				}
			}

			if (adres != null) {
				if (adres.getId() != null) {
					q.setParameter("adrid", adres.getId());
				}
				if (adres.getKapiNo() != null) {
					q.setParameter("adrkapiNo", adres.getKapiNo());
				}
				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {
					q.setParameter("adraltKapiNo", adres.getAltKapiNo());
				}
				if (!adres.getAktifEh().equals("")) {
					q.setParameter("adraktifEh", adres.getAktifEh());
				}
			}
			List<Long> list = q.list();

			if (list != null && list.size() > 0)
				return list.get(0);
			return 0l;
		} else {
			return null;
		}
	}

	public KadastroParsel readKadastroParselByCriteria(Adres adres,
			KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null && adres != null
				&& adres.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT distinct kp FROM Adres adr INNER JOIN adr.kadastroParsel kp "
					+ " WHERE adr.id=? ";
			hql += " AND kp.kurumSabit.id=?  AND adr.kurumSabit.id= ? ";
			paramList.add(adres.getId());
			paramList.add(kurumSabit.getId());
			paramList.add(kurumSabit.getId());
			List findList = (List<KadastroParsel>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (findList != null && findList.size() > 0) {
				return (KadastroParsel) findList.get(0);
			}
		}
		return null;
	}

	public List<Adres> readAllAdresByKadastroParsel(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null
				&& kadastroParsel != null && kadastroParsel.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT adr FROM Adres AS adr inner join adr.kadastroParsel As kp "
					+ " WHERE kp.id=? ";
			hql += " AND kp.kurumSabit.id=?  AND adr.kurumSabit.id= ? ";
			paramList.add(kadastroParsel.getId());
			paramList.add(kurumSabit.getId());
			paramList.add(kurumSabit.getId());

			List<Adres> findList = (List<Adres>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (findList != null && findList.size() > 0) {
				return findList;
			}
		}
		return null;
	}

	public List<Site> readAllSiteByCriteria(Site site, KurumSabit kurumSabit,
			int maxOkunacakKayitSayisi) {

		if (kurumSabit != null && kurumSabit.getId() != null && site != null) {
			Criteria criteria = super.getSession().createCriteria(Site.class,
					"site");

			if (maxOkunacakKayitSayisi >= 0)
				criteria.setMaxResults(maxOkunacakKayitSayisi);

			criteria.createAlias("site.kurumSabit", "ks");
			criteria.add(Restrictions.eq("ks.id", kurumSabit.getId()));
			if (site.getId() != null) {
				criteria.add(Restrictions.eq("site.id", site.getId()));

			} else if (site.getAciklama() != null) {
				if (!site.getAciklama().equals("*"))
					criteria.add(Restrictions.like("site.aciklama", site
							.getAciklama()
							+ "%"));
				criteria.addOrder(Order.asc("aciklama"));
			} else {
				return null;
			}
			return criteria.list();
		} else {
			return null;
		}
	}

	public List<KadastroParsel> readAllKadastroParselByParsel(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel) {

		if (kurumSabit != null && kurumSabit.getId() != null
				&& kadastroParsel != null) {

			if (kadastroParsel.getPafta() == null
					&& kadastroParsel.getParsel() == null
					&& kadastroParsel.getAda() == null
					&& kadastroParsel.getAlan() == null
					&& kadastroParsel.getAktifEh() == null) {
				return null;
			}

			Criteria criteria = super.getSession().createCriteria(
					KadastroParsel.class, "parcel");
			
			criteria.add(Restrictions.eq("parcel.kurumSabit.id", kurumSabit.getId()));
			
			if (kadastroParsel.getParsel() != null) {
				criteria.add(Restrictions.eq("parcel.parsel", kadastroParsel
						.getParsel()));
			}
			if (kadastroParsel.getPafta() != null) {
				if (!kadastroParsel.getPafta().trim().equals("")) {
					criteria.add(Restrictions.eq("parcel.pafta",
							kadastroParsel.getPafta()).ignoreCase());
				}
			}
			if (kadastroParsel.getAda() != null) {
				criteria.add(Restrictions.eq("parcel.ada", kadastroParsel
						.getAda()));
			}
			if (kadastroParsel.getAlan() != null) {
				criteria.add(Restrictions.eq("parcel.alan", kadastroParsel
						.getAlan()));
			}
			if (kadastroParsel.getAktifEh() != null) {
				if (!kadastroParsel.getAktifEh().trim().equals("")) {
					criteria.add(Restrictions.eq("parcel.aktifEh",
							kadastroParsel.getAktifEh()).ignoreCase());
				}
			}
			if (kadastroParsel.getId() != null) {
				criteria.add(Restrictions.eq("parcel.id", kadastroParsel
						.getId()));
			}
			if( kadastroParsel.getBolge() != null ){
				if( kadastroParsel.getBolge().getBolgeKodu() != null ){
					criteria.add(Restrictions.eq("parcel.bolge.bolgeKodu", kadastroParsel.getBolge().getBolgeKodu()));
				}
			}
			return criteria.list();
		}
		return null;
	}

	public List<KadastroParsel> readAllKadastroParselByParselList(
			KurumSabit kurumSabit, List<KadastroParsel> kadastroParselList,
			Integer maxResultCount) {

		Criteria criteria = getSession().createCriteria(KadastroParsel.class,
				"parcel");
		List<Long> idList = new ArrayList<Long>();
		if (kadastroParselList != null && kadastroParselList.size() > 0) {
			for (KadastroParsel tmpParcel : kadastroParselList) {
				if (tmpParcel.getId() != null) {
					idList.add(tmpParcel.getId());
				}
			}
			if (idList != null && idList.size() > 0) {
				criteria.add(Restrictions.in("parcel.id", idList));
			}
		}
		if (maxResultCount != null) {
			criteria.setMaxResults(maxResultCount);
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<KadastroParsel> tempList = criteria.list();
		return tempList;
	}

	
	public List<Adres> readAllAdresByAdresAndMahalleCaddeSokak(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak) {

		if (kurumSabit != null) {

			Criteria criteriaForsearchAddresses = super.getSession()
					.createCriteria(Adres.class, "adr");

			criteriaForsearchAddresses.add(Restrictions.eq("adr.kurumSabit",
					kurumSabit));

			if (adres != null) {

				if (adres.getId() != null) {

					criteriaForsearchAddresses.add(Restrictions.eq("adr.id",
							adres.getId()));
				}

				if (adres.getAciklama() != null
						&& !adres.getAciklama().equals("")) {

					criteriaForsearchAddresses.add(Restrictions.ilike(
							"adr.aciklama", adres.getAciklama()));
				}
				if (adres.getAdresNo() != null) {

					criteriaForsearchAddresses.add(Restrictions.eq(
							"adr.adresNo", adres.getAdresNo()));
				}
				if (adres.getAdresTur() != null
						&& !adres.getAdresTur().equals("")) {

					criteriaForsearchAddresses.add(Restrictions.eq(
							"adr.adresTur", adres.getAdresTur()).ignoreCase());
				}
				if (adres.getAktifEh() != null
						&& !adres.getAktifEh().equals("")) {

					criteriaForsearchAddresses.add(Restrictions.eq(
							"adr.aktifEh", adres.getAktifEh()).ignoreCase());
				}
				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().equals("")) {

					criteriaForsearchAddresses
							.add(Restrictions.eq("adr.altKapiNo",
									adres.getAltKapiNo()).ignoreCase());
				}

				if (adres.getKapiNo() != null) {

					criteriaForsearchAddresses.add(Restrictions.eq(
							"adr.kapiNo", adres.getKapiNo()));
				}
			}

			if (mahalleCaddeSokak != null) {

				criteriaForsearchAddresses.createAlias("adr.mahalleCaddeSokak",
						"mcs");

				if (mahalleCaddeSokak.getId() != null) {
					criteriaForsearchAddresses.add(Restrictions.eq("mcs.id",
							mahalleCaddeSokak.getId()));
				}

				if (mahalleCaddeSokak.getMahalle() != null
						&& mahalleCaddeSokak.getMahalle().getId() != null) {

					criteriaForsearchAddresses.add(Restrictions.eq(
							"mcs.mahalle.id", mahalleCaddeSokak.getMahalle()
									.getId()));

				}

				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {

					criteriaForsearchAddresses.add(Restrictions.eq(
							"mcs.caddeSokak.id", mahalleCaddeSokak
									.getCaddeSokak().getId()));

				}

			}
			return criteriaForsearchAddresses.list();
		}
		return null;
	}

	public List<Adres> readAllAdresByAdresList(KurumSabit kurumSabit,
			List<Adres> addressList) {

		if (kurumSabit != null && kurumSabit.getId() != null
				&& addressList != null && addressList.size() > 0) {

			List<Long> idList = new ArrayList<Long>();
			for (Adres tmpAddress : addressList) {
				if (tmpAddress.getId() != null) {
					idList.add(tmpAddress.getId());
				}
			}

			if (idList.size() > 0) {
				Criteria addressCriteria = super.getSession().createCriteria(
						Adres.class, "adr");

				addressCriteria.add(Restrictions.in("adr.id", idList));

				return addressCriteria.list();
			}
		}
		return null;
	}

	public List<YapiAda> readAllYapiAdaByCriteria(YapiAda yapiAda,
			KurumSabit kurumSabit, int maxOkunacakKayitSayisi) {

		if (kurumSabit != null && kurumSabit.getId() != null && yapiAda != null) {
			Criteria criteria = super.getSession().createCriteria(
					YapiAda.class, "yapi");
			criteria.setMaxResults(maxOkunacakKayitSayisi);
			criteria.createAlias("yapi.kurumSabit", "ks");
			if (yapiAda.getId() != null) {
				criteria.add(Restrictions.eq("yapi.id", yapiAda.getId()));

			} else if (yapiAda.getAciklama() != null) {
				if (!yapiAda.getAciklama().equals("*"))
					criteria.add(Restrictions.like("yapi.aciklama", yapiAda
							.getAciklama()
							+ "%"));
				criteria.add(Restrictions.eq("ks.id", kurumSabit.getId()));
				criteria.addOrder(Order.asc("aciklama"));
			} else {
				return null;
			}
			return criteria.list();
		} else {
			return null;
		}
	}

	public List<CaddeSokak> readAllCaddeSokakByCriteria(KurumSabit kurumsabit,
			Mahalle mahalle) {

		if (kurumsabit != null && kurumsabit.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT DISTINCT mc.caddeSokak  FROM MahalleCaddeSokak mc  inner join  mc.mahalle m inner join mc.caddeSokak c left outer join c.caddeSokakTur where mc.caddeSokak.id>0";
			hql += " And m.kurumSabit.id=? And c.aktifEh='E' ";
			paramList.add(kurumsabit.getId());
			
			hql += " AND c.kurumSabit.id=? ";
			paramList.add(kurumsabit.getId());
			
			if (mahalle != null && mahalle.getId() > 0) {
				hql += " And m.id=?";
				paramList.add(mahalle.getId());
			}
			hql += " ORDER BY 2 ASC";
			List<CaddeSokak> list = (List<CaddeSokak>) super.readDBObjectByHQL(
					hql, paramList.toArray());

			if (list != null && list.size() > 0) {
				Collections.sort(list, new Comparator<CaddeSokak>() {
					public int compare(CaddeSokak o1, CaddeSokak o2) {
						return o1.getAciklama().compareTo(o2.getAciklama());
					}
				});
				return list;
			} else
				return null;
		} else {
			return null;
		}

	}

	public List<CaddeSokak> readAllCaddeSokakByCriteria(CaddeSokak caddeSokak,
			KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT cs FROM CaddeSokak cs inner join cs.caddeSokakTur inner join cs.kurumSabit ks where cs.id > 0";
			hql += " And cs.kurumSabit.id=?";

			paramList.add(kurumSabit.getId());

			if (caddeSokak != null && caddeSokak.getId() > 0) {
				hql += " And cs.id=?";
				paramList.add(caddeSokak.getId());
			}
			hql += " ORDER BY cs.aciklama";
			List<CaddeSokak> list = (List<CaddeSokak>) super.readDBObjectByHQL(
					hql, paramList.toArray());

			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} else {
			return null;
		}
	}

	public List<Mahalle> readAllMahalle1(KurumSabit kurumsabit) {

		if (kurumsabit != null && kurumsabit.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT m FROM Mahalle m where m.id > 0 ";
			hql += " AND m.kurumSabit.id=? Order by m.aciklama asc ";
			paramList.add(kurumsabit.getId());

			List<Mahalle> list = (List<Mahalle>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} else {
			return null;
		}
	}
	
	public List<Mahalle> readAllAktifMahalle(KurumSabit kurumsabit) {

		if (kurumsabit != null && kurumsabit.getId() != null) {
			
			List<Object> paramList = new ArrayList<Object>();
			
			String hql = "SELECT m FROM Mahalle m where m.id > 0 ";
			
			hql += " AND m.kurumSabit.id=? AND m.aktifEh=? Order by m.aciklama asc ";
			
			paramList.add(kurumsabit.getId());
			
			paramList.add("E");

			List<Mahalle> list = (List<Mahalle>) super.readDBObjectByHQL(hql, paramList.toArray());

			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} else {
			
			return null;
		}
	}

	public MahalleCaddeSokak readMahalleCaddeSokakByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, KurumSabit kurumSabit) {
		if (kurumSabit == null || kurumSabit.getId() == null)
			return null;

		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT mcs FROM MahalleCaddeSokak AS mcs ");
		hql.append("INNER JOIN mcs.mahalle AS mah ");
		hql.append("INNER JOIN mcs.caddeSokak AS cds ");
		hql.append("WHERE mah.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND cds.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		if (mahalleCaddeSokak != null) {
			if (mahalleCaddeSokak.getId() != null) {
				hql.append("AND mcs.id=? ");
				list.add(mahalleCaddeSokak.getId());
			}
			if (mahalleCaddeSokak.getMahalle() != null
					&& mahalleCaddeSokak.getMahalle().getId() != null) {
				hql.append("AND mah.id=? ");
				list.add(mahalleCaddeSokak.getMahalle().getId());
			}
			if (mahalleCaddeSokak.getCaddeSokak() != null
					&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {
				hql.append("AND cds.id=? ");
				list.add(mahalleCaddeSokak.getCaddeSokak().getId());
			}
		}

		List<MahalleCaddeSokak> tempList = getHibernateTemplate().find(
				hql.toString(), list.toArray());

		if (tempList != null && tempList.size() > 0)
			return tempList.get(0);
		return null;
	}

	
	public List<MahalleCaddeSokak> readAllMahalleCaddeSokak(KurumSabit kurumSabit,
			MahalleCaddeSokak mahalleCaddeSokak ) {
		if (kurumSabit == null || kurumSabit.getId() == null)
			return null;

		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT mcs FROM MahalleCaddeSokak AS mcs ");
		hql.append("INNER JOIN mcs.mahalle AS mah ");
		hql.append("INNER JOIN mcs.caddeSokak AS cds ");
		hql.append("WHERE mah.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND cds.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		/*
		if (mahalleCaddeSokak != null) {
			if (mahalleCaddeSokak.getMahalle() != null
					&& mahalleCaddeSokak.getMahalle().getId() != null) {
				hql.append("AND mah.id=? ");
				list.add(mahalleCaddeSokak.getMahalle().getId());
			}
		}
		*/
		
		List<MahalleCaddeSokak> tempList = getHibernateTemplate().find(
				hql.toString(), list.toArray());

		if (tempList != null && tempList.size() > 0)
			return tempList;
		
		return null;
	}	
	
	
	public List<Bina> readAllBina(KurumSabit kurumsabit) {

		if (kurumsabit != null && kurumsabit.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT b FROM Bina b where b.id > 0 ";
			hql += " AND b.kurumSabit.id=?";
			paramList.add(kurumsabit.getId());
			
			hql += " Order by b.id asc";

			List<Bina> list = (List<Bina>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} else {
			return null;
		}
	
	}	
	
	
	public List<Bina> readAllBinaByCriteria(Bina bina, KurumSabit kurumsabit) {

		if (kurumsabit != null && kurumsabit.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT b FROM Bina b where b.id > 0 ";
			hql += " AND b.kurumSabit.id=?";
			paramList.add(kurumsabit.getId());

			if (bina != null && bina.getId() > 0) {
				hql += " AND b.id=?";
				paramList.add(bina.getId());
			}
			hql += " Order by b.id asc";

			List<Bina> list = (List<Bina>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} else {
			return null;
		}
	}

	public Long readMaxIdForObject(Class entityClass) {
		return super.readLastIdFromDbObject(entityClass);
	}
	
	public boolean insertIstekSikayet(long parselKodu,
			long bagimsizNo,String adi,String soyadi,String mail,String tel,String konuKodu,
			String konuAciklama,String ipAdres,java.util.Date zaman)
	{
		try{//SUBBYS_NEW
			String sql="insert into BEYOGLU.knt_istek_sikayet "+
			"(PARSEL_KODU,BAGIMSIZ_NO,ADI,SOYADI,EMAIL,ILETISIM_TEL,KONU_KODU,KONU_ACIKLAMASI,IP_ADRES) "+
			"values "+ 
			"(" +parselKodu+","+bagimsizNo+",'"+adi+"','"+soyadi+"','"+mail+"','"+tel+"','"+
			konuKodu+"','"+konuAciklama+"','"+ipAdres+"'"+
			")";
			System.err.println(sql);
			int result= getSession().createSQLQuery(sql).executeUpdate();
			
			if(result>0)
			{
				return true;
			}
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage()+" "+e.getCause());
			return false;
		}
	}
	public boolean insertImarDagitimLog(long binaKodu,long parselKodu,String ip)
	{
		try{//SUBBYS_NEW
			String sql="insert into BEYOGLU.KNT_IMAR_DAGITIM_SORGU_LOG "+
			"(BINA_NO,PARSEL_NO,IP) "+
			"values "+ 
			"(" +binaKodu+","+parselKodu+",'"+ip+"')";
			System.err.println(sql);
			int result= getSession().createSQLQuery(sql).executeUpdate();
			
			if(result>0)
			{
				return true;
			}
			return true;
		}catch (Exception e) {
			System.err.println(e.getMessage()+" "+e.getCause());
			return false;
		}
	}
	public List<Object[]> readDagitimAdaGrafik(Long oneri_ada_kodu)
	{
		String sql="  SELECT "  + 
		"t.id,A.ADA_NO ||'/'||A.PARSEL_NO ,t.X,t.Y,ROUND(SDO_GEOM.SDO_AREA(a.GEOMETRY,0.001),2) "+
		"FROM "+
		"BEYOGLU.KNT_KENTSEL_DAGITIM_ADA a, "+		
		"TABLE(SDO_UTIL.GETVERTICES(a.GEOMETRY)) t "+
		"WHERE "+
		"A.ID="+oneri_ada_kodu+
		" order by t.id";	
		
		return (List<Object[]>) super.getSession().createSQLQuery(sql).list();
		
				
	}
	public List<Object[]> readAllDagitimAskiBagimsizByCriteria(KurumSabit kurumSabit,KadastroParsel parsel,Mahalle mahalle)
	{		
		if (kurumSabit != null && kurumSabit.getId() != null && parsel != null) {		
			String sql=
				"select "+
				"NUM_KADASTRO_PARSEL.ADA, "+
				"NUM_KADASTRO_PARSEL.PARSEL, "+
				"ORT_CADDE_SOKAK.CADDE_SOKAK_ADI, "+
				"NUM_BINA_ADRES.KAPI_NO || '' ||NUM_BINA_ADRES.ALT_KAPI_NO AS KAPI, "+   
				"NUM_BINA.ID as BINA_ID, "+
				"NUM_KADASTRO_PARSEL.ID AS PARSEL_KODU, "+  
				"dag.ADA_KODU AS ID "+
				"from NUM_BINA "+
				"left outer join  "+
				"( "+
				"select      BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO, BEYOGLU.KNT_KENTSEL_DAGITIM.ADA_KODU "+ 
				"from        BEYOGLU.KNT_KENTSEL_DAGITIM "+
				"GROUP BY    BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO, BEYOGLU.KNT_KENTSEL_DAGITIM.ADA_KODU "+
				") dag ON dag.BINA_NO = NUM_BINA.ID "+
				"inner join num_bina_adres on NUM_BINA_ADRES.ADRES_NO = NUM_BINA.ADRES_NO "+  
				"inner join NUM_KADASTRO_PARSEL on NUM_BINA.PARSEL_ID = NUM_KADASTRO_PARSEL.ID "+   
				"inner join NUM_KADASTRO_ADA on NUM_KADASTRO_ADA.ID = NUM_KADASTRO_PARSEL.ADA_KODU "+   
				"INNER JOIN ORT_MAHALLE_KOYLER  on ORT_MAHALLE_KOYLER.ID = NUM_KADASTRO_ADA.MAHALLE_ID "+   
				"INNER JOIN ORT_CADDE_SOKAK on ORT_CADDE_SOKAK.ID = NUM_BINA_ADRES.CADDE_SOKAK_ID "+ 
				"left outer join BEYOGLU.KNT_KENTSEL_DAGITIM_ADA on dag.ADA_KODU =BEYOGLU.KNT_KENTSEL_DAGITIM_ADA.ID "+ 
				"where NUM_BINA_ADRES.ADRES_TURU='A' and NUM_BINA_ADRES.AKTIF_EH='E' AND NUM_KADASTRO_ADA.ID IN (SELECT ADA_KODU FROM BEYOGLU.KNT_ADA) "+			
				"and NUM_BINA_ADRES.kurum_id = "+kurumSabit.getId() +" "+
				(mahalle!=null?("and ORT_MAHALLE_KOYLER.MAHALLE_KODU ="+mahalle.getId()+" "):"")+
				(parsel.getPafta()!=""?("and NUM_KADASTRO_PARSEL.PAFTA = '"+parsel.getPafta()+"' "):"")+
				(parsel.getAda()!=null?("and NUM_KADASTRO_PARSEL.ADA = "+parsel.getAda()+" "):"")+
				(parsel.getParsel()!=null?("and NUM_KADASTRO_PARSEL.PARSEL = "+parsel.getParsel()+" "):"");
			sql+=" order by NUM_KADASTRO_PARSEL.ADA,NUM_KADASTRO_PARSEL.PARSEL,ORT_CADDE_SOKAK.CADDE_SOKAK_ADI,KAPI";

			List<Object[]> findList = getSession().createSQLQuery(sql).list();			
			if (findList != null && findList.size() > 0) {
				return findList;
			}
		}
		return null;
	}
	public List<Object[]> readAllDagitimAskiSatisBilgi(long binaId)
	{
		if(binaId!=0)
		{
			String sql="select "+
			"ROUND(yy.BINATABANALANI,2), "+ 
			"ROUND(yy.EML_ARSA_RAYIC_DEGERI,2), "+
			"ROUND(yy.KOMISYON_ARSA_RAYIC_DEGERI,2), "+
			"CASE WHEN yy.ACIKLAMA = '4706 PARSELLER�' THEN ROUND((yy.BINATABANALANI * yy.KOMISYON_ARSA_RAYIC_DEGERI),2) Else ROUND(0,2)  End As TOPLAM_BEDEL "+
			"from "+ 
			"( "+ 
			"select "+     
			"    BEYOGLU.KNT_MAHALLE_CADDE_DEGER.MAHALLE_KODU "+ 
			",BEYOGLU.KNT_MAHALLE_CADDE_DEGER.CADDE_SOKAK_KODU "+ 
			"  ,BEYOGLU.KNT_MAHALLE_CADDE_DEGER.EMLAK_RAYIC_YILI "+ 
			", "+ 
			"( select       BEYOGLU.ORT_MAHALLE_CADDE_RAYIC.ARSA_RAYIC_DEGERI "+ 
			"from         BEYOGLU.ORT_MAHALLE_CADDE_RAYIC "+ 
			"where        BEYOGLU.ORT_MAHALLE_CADDE_RAYIC.MAHALLE_KODU = BEYOGLU.KNT_MAHALLE_CADDE_DEGER.MAHALLE_KODU "+  
			"        AND BEYOGLU.ORT_MAHALLE_CADDE_RAYIC.CADDE_SOKAK_KODU = BEYOGLU.KNT_MAHALLE_CADDE_DEGER.CADDE_SOKAK_KODU "+ 
			"       AND BEYOGLU.ORT_MAHALLE_CADDE_RAYIC.YILI = BEYOGLU.KNT_MAHALLE_CADDE_DEGER.EMLAK_RAYIC_YILI "+ 
			") AS EML_ARSA_RAYIC_DEGERI "+ 
			",BEYOGLU.KNT_MAHALLE_CADDE_DEGER.KOMISYON_ARSA_RAYIC_DEGERI "+ 
			",xx.BINATABANALANI "+
			",xx.ACIKLAMA "+  
			"from     BEYOGLU.KNT_MAHALLE_CADDE_DEGER "+ 
			"        INNER JOIN "+ 
			"       ( "+ 
			"         select  BEYOGLU.ORT_MAHALLE_KOYLER.MAHALLE_KODU,BEYOGLU.ORT_CADDE_SOKAK.CADDE_SOKAK_KODU, zz.BINATABANALANI,rr.ACIKLAMA "+ 
			"        from    BEYOGLU.NUM_BINA "+ 
			"       inner join BEYOGLU.NUM_BINA_ADRES on BEYOGLU.NUM_BINA_ADRES.ADRES_NO = BEYOGLU.NUM_BINA.ADRES_NO "+   
			"      inner join BEYOGLU.NUM_KADASTRO_PARSEL on BEYOGLU.NUM_BINA.PARSEL_KODU = BEYOGLU.NUM_KADASTRO_PARSEL.PARSEL_KODU "+  
			"     inner join BEYOGLU.NUM_KADASTRO_ADA on BEYOGLU.NUM_KADASTRO_ADA.ADA_KODU = BEYOGLU.NUM_KADASTRO_PARSEL.ADA_KODU "+   
			"    inner join BEYOGLU.ORT_MAHALLE_KOYLER  on BEYOGLU.ORT_MAHALLE_KOYLER.MAHALLE_KODU = BEYOGLU.NUM_KADASTRO_ADA.MAHALLE_KODU "+   
			"   inner join BEYOGLU.ORT_CADDE_SOKAK on BEYOGLU.ORT_CADDE_SOKAK.CADDE_SOKAK_KODU = BEYOGLU.NUM_BINA_ADRES.CADDE_SOKAK_KODU "+ 
			"  LEFT OUTER JOIN "+ 
			" ( "+ 
			"  select      BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO,ROUND(BEYOGLU.KNT_KENTSEL_DAGITIM.IMAR_PLAN_PARSEL_ALANI,2) AS BINATABANALANI "+  
			"  from        BEYOGLU.KNT_KENTSEL_DAGITIM "+ 
			"    GROUP BY    BINA_NO,IMAR_PLAN_PARSEL_ALANI "+ 
			") zz ON zz.BINA_NO = BEYOGLU.NUM_BINA_ADRES.BINA_NO "+
			"LEFT OUTER JOIN "+ 
			"( "+ 
			"  select      BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO "+ 
			"  ,BEYOGLU.KNT_KENTSEL_DAGITIM.ACIKLAMA "+ 
			"  from        BEYOGLU.KNT_KENTSEL_DAGITIM "+ 
			"    GROUP BY    BINA_NO,ACIKLAMA "+ 
			") rr ON rr.BINA_NO = BEYOGLU.NUM_BINA_ADRES.BINA_NO "+                             
			"where "+  
			"       BEYOGLU.NUM_BINA_ADRES.BINA_NO ="+binaId+"  and BEYOGLU.NUM_BINA_ADRES.BINA_GIRIS_TURU='A' and BEYOGLU.NUM_BINA_ADRES.AKTIF_EH='E' "+ 
			") xx ON    BEYOGLU.KNT_MAHALLE_CADDE_DEGER.MAHALLE_KODU = xx.MAHALLE_KODU "+  
			"      and BEYOGLU.KNT_MAHALLE_CADDE_DEGER.CADDE_SOKAK_KODU = xx.CADDE_SOKAK_KODU "+ 
			") yy";
			return getSession().createSQLQuery(sql).list();	
		}
		return null;
	}
	public List<Object[]> readAllDagitimAskiBinaBilgiler(long binaId)
	{
		if(binaId !=0)
		{
			String sql="select * from( "+ 
            "SELECT  ADA,PARSEL "+  
            "FROM    BEYOGLU.NUM_BINA "+  
            "WHERE   BEYOGLU.NUM_BINA.BINA_NO = "+binaId+" ) f, "+                    
            "( "+  
            "    select      ROUND(BEYOGLU.KNT_KENTSEL_DAGITIM.IMAR_PLAN_PARSEL_ALANI,2) AS BINATABANALANI "+  
            "    from        BEYOGLU.KNT_KENTSEL_DAGITIM "+ 
            "    WHERE       BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO = "+binaId+" "+ 
            "    GROUP BY    BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO, BEYOGLU.KNT_KENTSEL_DAGITIM.IMAR_PLAN_PARSEL_ALANI "+ 
            ") f1, "+                     
            "( "+ 
            "   select      ROUND(BEYOGLU.KNT_KENTSEL_DAGITIM.INSAAT_ALANI,2) AS INSSATALANI "+  
            "    from        BEYOGLU.KNT_KENTSEL_DAGITIM "+ 
            "    WHERE       BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO = "+binaId+" "+
            "    GROUP BY    BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO, BEYOGLU.KNT_KENTSEL_DAGITIM.INSAAT_ALANI "+ 
            ") f3, "+
            "  ( "+ 
            "   select ROUND(decode(max(rownum), 1, BEYOGLU.tapu_sayfa.yuzolcumu, 0 ),2) as TAPUALANI from BEYOGLU.tapu_sayfa "+
            "    where BEYOGLU.tapu_sayfa.parsel_id in ( "+  
            "        select num_bina.parsel_kodu from BEYOGLU.num_bina where BEYOGLU.num_bina.bina_no = "+binaId+") "+
            "    and BEYOGLU.tapu_sayfa.kapanma_kod = 0 "+
            "    group by BEYOGLU.tapu_sayfa.parsel_id, BEYOGLU.tapu_sayfa.yuzolcumu "+ 
            ") f4 "+
            "where rownum = 1";

			return getSession().createSQLQuery(sql).list();	
		}
		return null;
	}
	public List<Object[]> readAllDagitimAskiBagimsizlar(long binaId)
	{
		if(binaId !=0)
		{
			String sql="select "+     
            "ORT_CADDE_SOKAK.CADDE_SOKAK_ADI As CaddeSokak, "+ 
            "A.KAPI_NO || ' ' ||A.ALT_KAPI_NO AS Kapi, "+ 
            "NUM_BINA_DETAY.KAT_NO As KatNo,  "+
            "NUM_BINA_DETAY.DAIRE_NO As DaireNo,  "+
            "CASE WHEN NUM_BINA_DETAY.ISYERI_EH ='E' Then 'Isyeri' "+ 
            "     Else 'Konut' End As Fonksiyon "+  
            ",NUM_BINA_DETAY.BAGIMSIZ_NO "+ 
            "from    NUM_BINA_DETAY "+ 
            "        inner join NUM_BINA on NUM_BINA.BINA_NO = NUM_BINA_DETAY.BINA_ID "+ 
            "        inner join NUM_BINA_ADRES A on A.ADRES_NO = NUM_BINA_DETAY.ADRES_ID  "+
            "        inner join ORT_CADDE_SOKAK on ORT_CADDE_SOKAK.ID = A.CADDE_SOKAK_ID "+  
            "where  NUM_BINA_DETAY.AKTIF_EH ='E' and num_bina.ID= "+binaId+
            " order by Kapi,KatNo,DaireNo";
			
			return getSession().createSQLQuery(sql).list();	
		}
		return null;
	}
	public List<Object[]> readAllDagitimAskiBagimsizByAdres(KurumSabit kurumSabit,MahalleCaddeSokak mahalleCaddeSokak,Adres adres)
	{		
		if (kurumSabit != null && kurumSabit.getId() != null && mahalleCaddeSokak != null && adres!=null) {		

			String sql=
				"select "+
				"NUM_KADASTRO_PARSEL.ADA, "+
				"NUM_KADASTRO_PARSEL.PARSEL, "+
				"ORT_CADDE_SOKAK.CADDE_SOKAK_ADI, "+
				"NUM_BINA_ADRES.KAPI_NO || '' ||NUM_BINA_ADRES.ALT_KAPI_NO AS KAPI, "+   
				"NUM_BINA.ID as BINA_ID, "+
				"NUM_KADASTRO_PARSEL.ID AS PARSEL_KODU, "+  
				"dag.ADA_KODU AS ID "+
				"from NUM_BINA "+
				"left outer join  "+
				"( "+
				"select      BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO, BEYOGLU.KNT_KENTSEL_DAGITIM.ADA_KODU "+ 
				"from        BEYOGLU.KNT_KENTSEL_DAGITIM "+
				"GROUP BY    BEYOGLU.KNT_KENTSEL_DAGITIM.BINA_NO, BEYOGLU.KNT_KENTSEL_DAGITIM.ADA_KODU "+
				") dag ON dag.BINA_NO = NUM_BINA.ID "+
				"inner join num_bina_adres on NUM_BINA_ADRES.ADRES_NO = NUM_BINA.ADRES_NO "+  
				"inner join NUM_KADASTRO_PARSEL on NUM_BINA.PARSEL_ID = NUM_KADASTRO_PARSEL.ID "+   
				"inner join NUM_KADASTRO_ADA on NUM_KADASTRO_ADA.ID = NUM_KADASTRO_PARSEL.ADA_KODU "+   
				"INNER JOIN ORT_MAHALLE_KOYLER  on ORT_MAHALLE_KOYLER.ID = NUM_KADASTRO_ADA.MAHALLE_ID "+   
				"INNER JOIN ORT_CADDE_SOKAK on ORT_CADDE_SOKAK.ID = NUM_BINA_ADRES.CADDE_SOKAK_ID "+ 
				"left outer join BEYOGLU.KNT_KENTSEL_DAGITIM_ADA on dag.ADA_KODU =BEYOGLU.KNT_KENTSEL_DAGITIM_ADA.ID "+ 
				"where NUM_BINA_ADRES.ADRES_TURU='A' and NUM_BINA_ADRES.AKTIF_EH='E' AND NUM_KADASTRO_ADA.ID IN (SELECT ADA_KODU FROM BEYOGLU.KNT_ADA) "+			
				"and NUM_BINA_ADRES.kurum_id = "+kurumSabit.getId() +" "+	
				(mahalleCaddeSokak.getId()!=null?("and ORT_MAHALLE_KOYLER.MAHALLE_KODU ="+mahalleCaddeSokak.getId()+" "):"")+
				(mahalleCaddeSokak.getCaddeSokak().getId()!=null?("and ORT_CADDE_SOKAK.ID = "+mahalleCaddeSokak.getCaddeSokak().getId()+" "):"")+
				(adres.getKapiNo()!=null?("and NUM_BINA_ADRES.KAPI_NO = "+adres.getKapiNo()+" "):"")+
				(adres.getAltKapiNo()!=""?("and NUM_BINA_ADRES.ALT_KAPI_NO = '"+adres.getAltKapiNo()+"' "):"");

			sql+=" order by NUM_KADASTRO_PARSEL.ADA,NUM_KADASTRO_PARSEL.PARSEL,ORT_CADDE_SOKAK.CADDE_SOKAK_ADI,KAPI";
			List<Object[]> findList = getSession().createSQLQuery(sql).list();			
			if (findList != null && findList.size() > 0) {
				return findList;
			}
		}
		return null;
	}
	public List<KadastroParsel> readAllKadastroParselByParselAndMahalle(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel,
			Mahalle mahalle) {

		if (kurumSabit != null && kurumSabit.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "Select distinct kp FROM KadastroParsel kp ";
			if (mahalle != null && mahalle.getId() > 0) {
				hql += " inner join  kp.mahalle as m ";
			}
			hql += " WHERE  kp.id > 0 AND kp.kurumSabit.id=?  ";
			paramList.add(kurumSabit.getId());

			if (kadastroParsel != null) {
				if (kadastroParsel.getAda() != null
						&& kadastroParsel.getAda() > -1) {
					hql += " AND kp.ada=?";
					paramList.add(kadastroParsel.getAda());
				}
				if (kadastroParsel.getPafta() != null
						&& !kadastroParsel.getPafta().trim().equals("")) {
					hql += " AND kp.pafta=?";
					paramList.add(kadastroParsel.getPafta());
				}
				if (kadastroParsel.getParsel() != null
						&& kadastroParsel.getParsel() > -1) {
					hql += " AND kp.parsel=?";
					paramList.add(kadastroParsel.getParsel());
				}
				if (kadastroParsel.getId() != null
						&& kadastroParsel.getId() > 0) {
					hql += " AND kp.id=?";
					paramList.add(kadastroParsel.getId());
				}
				
				if( kadastroParsel.getBolge() != null ){
					if( kadastroParsel.getBolge().getBolgeKodu() != null ){
						hql += " AND kp.bolge.bolgeKodu=?";
						paramList.add(kadastroParsel.getBolge().getBolgeKodu());
					}
				}
			}

			if (mahalle != null && mahalle.getId() > 0) {
				hql += " AND m.id=?";
				paramList.add(mahalle.getId());
				
				hql += " AND m.kurumSabit.id=?";
				paramList.add(kurumSabit.getId());
			}

			List<KadastroParsel> list = (List<KadastroParsel>) super
					.readDBObjectByHQL(hql, paramList.toArray());

			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} else {
			return null;
		}

	}

	public List<Adres> readAllAdresByCriteria(KurumSabit kurumSabit,
			Adres adres, MahalleCaddeSokak mahalleCaddeSokak, Bina bina) {

		if (kurumSabit != null && kurumSabit.getId() != null && adres != null
				|| mahalleCaddeSokak != null || bina != null) {

			List<Object> paramList = new ArrayList<Object>();
			String hql = "Select distinct adr from Adres As adr INNER JOIN FETCH adr.mahalleCaddeSokak mcs  ";
			hql += " INNER JOIN FETCH mcs.caddeSokak cs ";
			hql += " INNER JOIN FETCH mcs.mahalle mh ";

			hql += " INNER JOIN FETCH adr.bina bn ";

			hql += " where adr.id > 0 AND adr.kurumSabit.id=? ";
			paramList.add(kurumSabit.getId());

			hql += " AND mh.kurumSabit.id=? ";
			paramList.add(kurumSabit.getId());

			hql += " AND cs.kurumSabit.id=? ";
			paramList.add(kurumSabit.getId());

			hql += " AND bn.kurumSabit.id=? ";
			paramList.add(kurumSabit.getId());

			if (adres != null) {
				if (adres.getKapiNo() != null && adres.getKapiNo() > 0) {
					hql += " AND adr.kapiNo=?";
					paramList.add(adres.getKapiNo());
				}
				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {
					hql += " AND adr.altKapiNo=?";
					paramList.add(adres.getAltKapiNo());
				}
			}
			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getMahalle() != null) {
					if (mahalleCaddeSokak.getMahalle().getId() != null
							&& mahalleCaddeSokak.getMahalle().getId() > 0) {
						hql += " AND mcs.mahalle.id=?";
						paramList.add(mahalleCaddeSokak.getMahalle().getId());
					}
				}
				if (mahalleCaddeSokak.getCaddeSokak() != null) {
					if (mahalleCaddeSokak.getCaddeSokak().getId() != null
							&& mahalleCaddeSokak.getCaddeSokak().getId() > 0) {
						hql += " AND mcs.caddeSokak.id=?";
						paramList
								.add(mahalleCaddeSokak.getCaddeSokak().getId());
					}
				}
			}

			if (bina != null) {

				if (bina.getId() != null && bina.getId() > 0) {
					hql += " AND bn.id=?";
					paramList.add(bina.getId());
				}
				if (bina.getApartmanBlokAd() != null
						&& !bina.getApartmanBlokAd().trim().equals("")) {
					hql += " AND lower(bn.apartmanBlokAd) LIKE lower(?)";
					paramList.add(bina.getApartmanBlokAd().trim().replaceAll(
							"%", "")
							+ "%");
				}

				if (bina.getSite() != null
						&& bina.getSite().getAciklama() != null
						&& bina.getSite().getAciklama().trim().length() > 0) {
					hql += " AND lower(bn.site.aciklama) LIKE lower(?)";
					paramList.add(bina.getSite().getAciklama().trim()
							.replaceAll("%", "")
							+ "%");
				}
			}

			List<Adres> list = (List<Adres>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (list != null && list.size() > 0)
				return list;
			else
				return null;
		} else {
			return null;
		}

	}

	public List<Adres> readAllAdresByBagimsiz(Bagimsiz bagimsiz,
			KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null
				&& bagimsiz != null && bagimsiz.getId() != null) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT adr FROM Bagimsiz as bgm INNER JOIN bgm.adres as adr "
					+ " WHERE bgm.id=? AND adr.kurumSabit.id= ? ";
			paramList.add(bagimsiz.getId());
			paramList.add(kurumSabit.getId());

			List<Adres> findList = (List<Adres>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (findList != null && findList.size() > 0) {
				return findList;
			}
		}
		return null;
	}

	public Bina readBinaByAdres(Adres adres, KurumSabit kurumSabit) {

		if (adres != null && adres.getId() != null && kurumSabit != null
				&& kurumSabit.getId() != null) {
			String hql = "Select bn from Adres adr inner join adr.bina as bn"
					+ " Where  adr.id=? And bn.kurumSabit.id=?";
			List<Bina> tempList = (List<Bina>) super.readDBObjectByHQL(hql,
					new Object[] { adres.getId(), kurumSabit.getId() });

			if (tempList != null && tempList.size() > 0) {
				return tempList.get(0);
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	public Bina readBinaByBagimsiz(Bagimsiz bagimsiz, KurumSabit kurumSabit) {
		if (bagimsiz != null && bagimsiz.getId() != null && kurumSabit != null
				&& kurumSabit.getId() > 0) {
			String hql = "Select bn from Bagimsiz bgm inner join bgm.bina as bn "
					+ " Where  bgm.id=? And bn.kurumSabit.id=? ";
			List<Bina> tempList = (List<Bina>) super.readDBObjectByHQL(hql,
					new Object[] { bagimsiz.getId(), kurumSabit.getId() });

			if (tempList != null && tempList.size() > 0) {
				return tempList.get(0);
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByAdres(Adres adres,
			KurumSabit kurumSabit) {

		if (adres != null && adres.getId() != null && kurumSabit != null
				&& kurumSabit.getId() > 0) {
			String hql = "Select mcs from Adres adr inner join adr.mahalleCaddeSokak as mcs inner join mcs.mahalle as m inner join mcs.caddeSokak as c "
					+ " Where  adr.id=? And m.kurumSabit.id=? And c.kurumSabit.id=? AND adr.kurumSabit.id=?  ";
			List<MahalleCaddeSokak> tempList = (List<MahalleCaddeSokak>) super
					.readDBObjectByHQL(hql, new Object[] { adres.getId(),
							kurumSabit.getId(), kurumSabit.getId(), kurumSabit.getId() });

			if (tempList != null && tempList.size() > 0) {
				return tempList;
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByAdresler(
			List<Adres> adresler, KurumSabit kurumSabit, int maxResultCount) {

		if (kurumSabit != null && kurumSabit.getId() != null) {
			Criteria criteria = super.getSession().createCriteria(Adres.class,
					"adr").createCriteria("mahalleCaddeSokak", "mcs");
			criteria.createAlias("mcs.mahalle", "mh");
			criteria.createAlias("mcs.caddeSokak", "cd");
			criteria.add(Restrictions.eq("adr.kurumSabit.id", kurumSabit
					.getId()));
			criteria.add(Restrictions
					.eq("mh.kurumSabit.id", kurumSabit.getId()));
			criteria.add(Restrictions
					.eq("cd.kurumSabit.id", kurumSabit.getId()));

			List<Long> idList = new ArrayList<Long>();

			if (adresler != null && adresler.size() > 0) {
				for (Adres tmpAdres : adresler) {
					if (tmpAdres.getId() != null) {
						idList.add(tmpAdres.getId());
					}
				}
				if (idList != null && idList.size() > 0) {
					criteria.add(Restrictions.in("adr.id", idList));
				}

			}
			criteria.setMaxResults(maxResultCount);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List aliasEntitiesMap = criteria.setResultTransformer(
					CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();

			List<MahalleCaddeSokak> tempList = new ArrayList<MahalleCaddeSokak>();

			Iterator iterator = aliasEntitiesMap.iterator();

			while (iterator.hasNext()) {
				Map resultMap = (Map) iterator.next();
				MahalleCaddeSokak mcs = (MahalleCaddeSokak) resultMap
						.get("mcs");
				tempList.add(mcs);
			}

			return tempList;
		} else {
			return null;
		}

	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByBagimsiz(
			Bagimsiz bagimsiz, KurumSabit kurumSabit) {

		if (bagimsiz != null && bagimsiz.getId() != null && kurumSabit != null
				&& kurumSabit.getId() > 0) {
			String hql = "Select distinct mcs from Bagimsiz bgm inner join bgm.adres adr inner join adr.mahalleCaddeSokak as mcs inner join mcs.mahalle as m inner join mcs.caddeSokak as c "
					+ " Where  bgm.id=? And m.kurumSabit.id=? And c.kurumSabit.id=? AND adr.kurumSabit.id=?  ";
			List<MahalleCaddeSokak> tempList = (List<MahalleCaddeSokak>) super
					.readDBObjectByHQL(hql, new Object[] { bagimsiz.getId(),
							kurumSabit.getId(), kurumSabit.getId(), kurumSabit.getId() });

			if (tempList != null && tempList.size() > 0) {
				return tempList;
			} else {
				return null;
			}

		} else {
			return null;
		}
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByBina(Bina bina,
			KurumSabit kurumSabit) {

		if (bina != null && bina.getId() != null && kurumSabit != null
				&& kurumSabit.getId() > 0) {
			String hql = "Select distinct mcs from Adres adr inner join adr.mahalleCaddeSokak as mcs inner join adr.bina as b inner join mcs.mahalle as m inner join mcs.caddeSokak as c "
					+ " Where b.id=? And m.kurumSabit.id=? And c.kurumSabit.id=? AND adr.kurumSabit.id=? AND b.kurumSabit.id=? ";

			List<MahalleCaddeSokak> tempList = (List<MahalleCaddeSokak>) super
					.readDBObjectByHQL(hql, new Object[] { bina.getId(),
							kurumSabit.getId(), kurumSabit.getId(), kurumSabit.getId(), kurumSabit.getId() });

			if (tempList != null && tempList.size() > 0) {
				return tempList;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	public List<Adres> readAllAdresByBina(Bina bina, KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null && bina != null
				&& bina.getId() != null && bina.getId() > 0) {
			List<Object> paramList = new ArrayList<Object>();
			String hql = "SELECT adr FROM Adres as adr inner join adr.bina as bn inner join adr.mahalleCaddeSokak as mcs "
					+ "inner join mcs.mahalle as m inner join mcs.caddeSokak as c inner join adr.kadastroParsel as kp"
					+ " WHERE  bn.id=? And adr.kurumSabit=? ";
			paramList.add(bina.getId());
			paramList.add(kurumSabit);
			List<Adres> findList = (List<Adres>) super.readDBObjectByHQL(hql,
					paramList.toArray());

			if (findList != null && findList.size() > 0) {
				return findList;
			}

		}
		return null;
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByMahalleCaddeSokak(
			MahalleCaddeSokak mahalleCaddeSokak, KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null) {
			Criteria criteria = super.getSession().createCriteria(
					MahalleCaddeSokak.class, "mcs");
			criteria.createAlias("mcs.mahalle", "mh");
			criteria.createAlias("mcs.caddeSokak", "cd");
			criteria.add(Restrictions
					.eq("mh.kurumSabit.id", kurumSabit.getId()));
			criteria.add(Restrictions
					.eq("cd.kurumSabit.id", kurumSabit.getId()));

			if (mahalleCaddeSokak.getId() != null) {
				criteria.add(Restrictions.eq("mcs.id", mahalleCaddeSokak
						.getId()));
			} else {
				if (mahalleCaddeSokak.getTuikCaddeSokakKod() != null) {
					criteria.add(Restrictions.eq("mcs.tuikCaddeSokakKod",
							mahalleCaddeSokak.getTuikCaddeSokakKod()));
				}

				if (mahalleCaddeSokak.getMahalle() != null) {
					if (mahalleCaddeSokak.getMahalle().getId() != null) {
						criteria.add(Restrictions.eq("mh.id", mahalleCaddeSokak
								.getMahalle().getId()));
					} else if (mahalleCaddeSokak.getMahalle().getAciklama() != null
							&& !mahalleCaddeSokak.getMahalle().getAciklama()
									.trim().equals("")) {
						criteria.add(Restrictions.ilike("mh.aciklama",
								mahalleCaddeSokak.getMahalle().getAciklama()
										.trim()));
					}
				}
				if (mahalleCaddeSokak.getCaddeSokak() != null) {
					if (mahalleCaddeSokak.getCaddeSokak().getId() != null) {
						criteria.add(Restrictions.eq("cd.id", mahalleCaddeSokak
								.getCaddeSokak().getId()));
					} else if (mahalleCaddeSokak.getCaddeSokak().getAciklama() != null
							&& !mahalleCaddeSokak.getCaddeSokak().getAciklama()
									.trim().equals("")) {
						criteria.add(Restrictions.ilike("cd.aciklama",
								mahalleCaddeSokak.getCaddeSokak().getAciklama()
										.trim()));
					}
				}
			}

			List<MahalleCaddeSokak> tempList = criteria.list();
			return tempList;
		} else {
			return null;
		}
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByKadastroParsel(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {

		if (kadastroParsel != null && kadastroParsel.getId() != null
				&& kurumSabit != null && kurumSabit.getId() > 0) {
			String hql = "Select distinct mcs from Adres as kpAdr inner join kpAdr.mahalleCaddeSokak as mcs inner join kpAdr.kadastroParsel as kp inner join mcs.mahalle as m inner join mcs.caddeSokak as c "
					+ " WHERE kp.id=? AND m.kurumSabit.id=? AND c.kurumSabit.id=? AND kpAdr.kurumSabit.id=? AND kp.kurumSabit.id=? ";
			List<MahalleCaddeSokak> tempList = (List<MahalleCaddeSokak>) super
					.readDBObjectByHQL(hql, new Object[] {
							kadastroParsel.getId(), kurumSabit.getId(),
							kurumSabit.getId(), kurumSabit.getId(), kurumSabit.getId() });

			if (tempList != null && tempList.size() > 0) {
				return tempList;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	public List<Mahalle> readAllMahalleByCriteria(Mahalle mahalle,
			KurumSabit kurumSabit) {

		if (kurumSabit != null && kurumSabit.getId() != null) {

			ArrayList<Object> paramList = new ArrayList<Object>();

			String hql = "SELECT m FROM Mahalle m WHERE m.kurumSabit.id = ? ";

			paramList.add(kurumSabit.getId());

			if (mahalle != null) {
				if (mahalle.getId() != null) {
					hql += "AND m.id=? ";
					paramList.add(mahalle.getId());
				} else {
					if (mahalle.getTuikMahalleKod() != null) {
						hql += "AND m.tuikMahalleKod = ? ";
						paramList.add(mahalle.getTuikMahalleKod());
					}
					if (mahalle.getAciklama() != null
							&& !mahalle.getAciklama().trim().equals("")) {
						hql += "AND m.aciklama like ? ";
						paramList.add(mahalle.getAciklama().trim()
								.toUpperCase()
								+ "%");
					}
				}
				List<Mahalle> list = (List<Mahalle>) this
						.getHibernateTemplate().find(hql, paramList.toArray());
				if (list != null && list.size() > 0) {
					return list;
				}
			}

			return null;
		} else {
			return null;
		}
	}

	public List<BagimsizKullanimSinif> readAllBagimsizKullanimSinif() {

		return (List<BagimsizKullanimSinif>) super
				.readDBObject(BagimsizKullanimSinif.class);
	}

	public void saveOrUpdateBagimsizKullanimSinifList(
			List<BagimsizKullanimSinif> bagimsizKullanimSinifList) {

		super.saveOrUpdate(bagimsizKullanimSinifList);
	}

	public void deleteBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {

		super.deleteDBObject(bagimsizKullanimSinif);
	}

	public void deleteBagimsizKullanimGrup(
			BagimsizKullanimGrup bagimsizKullanimGrup) {

		super.deleteDBObject(bagimsizKullanimGrup);
	}

	public void saveOrUpdateBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {

		super.saveOrUpdate(bagimsizKullanimSinif);
	}

	public List<BagimsizKullanimGrup> readAllBagimsizKullanimGrupByKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {

		List<BagimsizKullanimGrup> list;

		if (bagimsizKullanimSinif != null
				&& bagimsizKullanimSinif.getId() != null) {

			Criteria criteria = getSession().createCriteria(
					BagimsizKullanimGrup.class, "bkg");

			criteria.add(Restrictions.eq("bkg.bagimsizKullanimSinif.id",
					bagimsizKullanimSinif.getId()));

			list = criteria.list();

		} else {

			list = (List<BagimsizKullanimGrup>) super
					.readDBObject(BagimsizKullanimGrup.class);
		}

		return list;
	}

	public BagimsizKullanimGrup readAllBagimsizKullanimGrupByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup) {

		return (BagimsizKullanimGrup) super.readDBObject(
				BagimsizKullanimGrup.class, bagimsizKullanimGrup.getId());
	}

	public void deleteBagimsizKullanimDetay(
			BagimsizKullanimDetay bagimsizKullanimDetay) {

		super.deleteDBObject(bagimsizKullanimDetay);
	}

	public void saveOrUpdateBagimsizKullanimGrup(
			BagimsizKullanimGrup bagimsizKullanimGrup) {

		super.saveOrUpdate(bagimsizKullanimGrup);
	}

	public BagimsizKullanimDetay readBagimsizKullanimDetayByCriteria(
			BagimsizKullanimDetay bagimsizKullanimDetay) {

		return (BagimsizKullanimDetay) super.readDBObject(
				BagimsizKullanimDetay.class, bagimsizKullanimDetay.getId());
	}

	public List<BagimsizKullanimDetay> readAllBagimsizKullanimDetayByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup) {

		List<BagimsizKullanimDetay> list = null;

		if (bagimsizKullanimGrup != null
				&& bagimsizKullanimGrup.getId() != null) {

			Criteria criteria = getSession().createCriteria(
					BagimsizKullanimDetay.class, "bkd");

			criteria.add(Restrictions.eq("bkd.bagimsizKullanimGrup.id",
					bagimsizKullanimGrup.getId()));

			list = criteria.list();
		}
		return list;
	}

	public void saveOrUpdateBagimsizKullanimDetay(
			BagimsizKullanimDetay bagimsizKullanimDetay) {

		super.saveOrUpdate(bagimsizKullanimDetay);
	}

	public List<BagimsizKullanimGrup> readAllBagimsizKullanimGrup() {

		List<BagimsizKullanimGrup> readDBObject = (List<BagimsizKullanimGrup>) super
				.readDBObject(BagimsizKullanimGrup.class);

		return readDBObject;
	}

	public void saveOrUpdateBagimsizKullanimDetayList(
			List<BagimsizKullanimDetay> bagimsizKullanimDetayList) {

		super.saveOrUpdate(bagimsizKullanimDetayList);
	}

	public void saveOrUpdateBagimsizKullanimGrupList(
			List<BagimsizKullanimGrup> bagimsizKullanimGrupList) {

		super.saveOrUpdate(bagimsizKullanimGrupList);
	}

	public Long readLastIdFromDbObject(Class entityClass) {

		return super.readLastIdFromDbObject(entityClass);
	}

	public List<CaddeSokakTur> readAllCaddeSokakTur() {

		return (List<CaddeSokakTur>) super.readDBObject(CaddeSokakTur.class);
	}

	public void saveOrUpdateCaddeSokak(CaddeSokak caddeSokak) {

		super.saveOrUpdate(caddeSokak);
	}

	public void deleteMahalleCaddeSokak(MahalleCaddeSokak mahalleCaddeSokak) {

		super.deleteDBObject(mahalleCaddeSokak);
	}

	public void deleteCaddeSokak(CaddeSokak caddeSokak) {

		super.deleteDBObject(caddeSokak);
	}

	public void saveOrUpdateMahalleCaddeList(
			List<MahalleCaddeSokak> mahalleCaddeList) {

		super.saveOrUpdate(mahalleCaddeList);
	}

	public void saveOrUpdateMahalleCaddeSokak(
			MahalleCaddeSokak mahalleCaddeSokak) {

		super.saveOrUpdate(mahalleCaddeSokak);
	}

	public List<KurumSabit> readAllKurumSabitByCriteria(KurumSabit kurumSabit) {

		if (kurumSabit == null) {

			return null;
		}

		Criteria criteria = this.getSession().createCriteria(KurumSabit.class);

		if (kurumSabit.getKurumAd() != null
				&& !kurumSabit.getKurumAd().trim().equals("")) {

			criteria
					.add(Restrictions.ilike("kurumAd", kurumSabit.getKurumAd()));

			criteria.addOrder(Order.asc("kurumAd"));
		}

		if (kurumSabit.getId() != null && kurumSabit.getId() > 0) {

			criteria.add(Restrictions.eq("id", kurumSabit.getId()));
		}

		return criteria.list();
	}

	public BagimsizKullanimDetay readBagimsizKullanimDetayByCriteria(
			Bagimsiz bagimsiz) {

		if (bagimsiz != null && bagimsiz.getId() != null) {

			String hql = "Select distinct bkd from Bagimsiz bgm inner join bgm.bagimsizKullanimDetay  as bkd inner join bkd.bagimsizKullanimGrup as bkg "
					+ "  inner join bkg.bagimsizKullanimSinif  as bks Where  bgm=? ";

			List<BagimsizKullanimDetay> tempList = (List<BagimsizKullanimDetay>) super
					.readDBObjectByHQL(hql, new Object[] { bagimsiz });

			if (tempList != null && tempList.size() > 0)

				return tempList.get(0);
		}

		return new BagimsizKullanimDetay();
	}

	public BagimsizKullanimSinif readBagimsizKullanimSinifByCriteria(Bina bina) {

		if (bina != null && bina.getId() != null) {

			String hql = "select bks from Bina as bn inner join bn.bagimsizKullanimSinif as bks where  bn = ? ";

			List<BagimsizKullanimSinif> tempList = (List<BagimsizKullanimSinif>) super
					.readDBObjectByHQL(hql, new Object[] { bina });

			if (tempList != null && tempList.size() > 0)

				return tempList.get(0);
		}

		return new BagimsizKullanimSinif();
	}

	public KurumSabit readKurumSabitByCriteria(KurumSabit kurumSabit) {

		return (KurumSabit) super.readDBObject(KurumSabit.class, kurumSabit
				.getId());
	}

	public List<Object> readAllMahalleCaddeSokakKadastroParselAdresByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit) {

		List<Object> paramList = new ArrayList<Object>();

		String hql = "SELECT mcs, kp, adrslr1 FROM MahalleCaddeSokak as mcs, KadastroParsel as kp "
				+ " inner join mcs.mahalle as m inner join mcs.caddeSokak as cs "
				+ " inner join mcs.adresler as adrslr1 "
				+ " WHERE m.kurumSabit.id = cs.kurumSabit.id "
				+ " AND m.kurumSabit.id = adrslr1.kurumSabit.id "
				+ " AND m.kurumSabit.id = kp.kurumSabit.id "
				+ " AND adrslr1.id = kp.adresler.id "
				+ " AND m.kurumSabit.id = ? ";

		paramList.add(kurumSabit.getId());

		if (mahalleCaddeSokak != null && mahalleCaddeSokak.getId() != null) {

			hql += " AND mcs.id = ? ";

			paramList.add(mahalleCaddeSokak.getId());
		}
		if (adres != null) {

			if (adres.getKapiNo() != null) {

				hql += " AND adrslr1.kapiNo = ? ";

				paramList.add(adres.getKapiNo());
			}
			if (adres.getAltKapiNo() != null
					&& !adres.getAltKapiNo().trim().equals("")) {

				hql += " AND adrslr1.altKapiNo = ? ";

				paramList.add(adres.getAltKapiNo());
			}
		}

		List<Object> list = this.getHibernateTemplate().find(hql,
				paramList.toArray());

		if (list != null && list.size() > 0) {

			for (Object obj : list) {
				Hibernate
						.initialize((((MahalleCaddeSokak) (((Object[]) obj)[0])))
								.getMahalle());

				Hibernate
						.initialize((((MahalleCaddeSokak) (((Object[]) obj)[0])))
								.getCaddeSokak());
			}
			return list;
		}
		return null;
	}

	public List<Mahalle> readAllMahalleByKurum(KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null) {
			List<Mahalle> list = (List<Mahalle>) this
					.getHibernateTemplate()
					.find(
							"SELECT m FROM Mahalle m Where m.kurumSabit.id = ? order by m.aciklama asc",
							new Object[] { kurumSabit.getId() });
			if (list != null && list.size() > 0)
				return list;
			return null;
		} else {
			return null;
		}
	}

	public List<MahalleCaddeSokak> readAllMahalleCaddeSokakByCriteria(
			Mahalle mahalle, KurumSabit kurumSabit) {
		if (kurumSabit == null || kurumSabit.getId() == null || mahalle == null
				|| mahalle.getId() == null)
			return null;

		String hql = "SELECT mcs FROM MahalleCaddeSokak mcs "
				+ " WHERE  mcs.mahalle.id=? AND mcs.mahalle.kurumSabit.id=? "
				+ "AND mcs.caddeSokak.kurumSabit.id= ? order by mcs.caddeSokak.aciklama asc";
		List findList = this.getHibernateTemplate().find(
				hql,
				new Object[] { mahalle.getId(), kurumSabit.getId(),
						kurumSabit.getId() });
		if (findList != null && findList.size() > 0) {
			return findList;
		}
		return null;
	}

	public List<BagimsizKullanimDetay> readAllBagimsizKullanimDetay() {
		return (List<BagimsizKullanimDetay>) super
				.readDBObject(BagimsizKullanimDetay.class);
	}

	public Bagimsiz readAllBagimsizByCriteria(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			Bagimsiz bagimsiz, KurumSabit kurumSabit) {

		StringBuilder hql = new StringBuilder();

		List<Object> list = new ArrayList<Object>();

		hql
				.append("SELECT bgm FROM Bagimsiz as bgm inner join bgm.adres As adr inner join adr.mahalleCaddeSokak AS mcs ");
		hql.append("WHERE mcs.id=? ");
		list.add(mahalleCaddeSokak.getId());
		hql.append("AND adr.kurumSabit.id=? ");

		list.add(kurumSabit.getId());

		if (bagimsiz != null) {

			if (bagimsiz.getDaireNo() != null) {

				hql.append("AND bgm.daireNo=? ");

				list.add(bagimsiz.getDaireNo());
			}
			if (bagimsiz.getAltDaireNo() != null
					&& !bagimsiz.getAltDaireNo().trim().equals("")) {

				hql.append("AND bgm.altDaireNo=? ");

				list.add(bagimsiz.getAltDaireNo());
			}
		}

		if (adres != null) {

			if (adres.getKapiNo() != null) {

				hql.append("AND adr.kapiNo=? ");

				list.add(adres.getKapiNo());
			}
			if (adres.getAltKapiNo() != null
					&& !adres.getAltKapiNo().trim().equals("")) {

				hql.append("AND adr.altKapiNo=? ");

				list.add(adres.getAltKapiNo());
			}
		}

		List<Bagimsiz> tempList = getHibernateTemplate().find(hql.toString(),
				list.toArray());

		if (tempList != null && tempList.size() > 0) {
			return tempList.get(0);
		}

		return null;
	}

	public List<Bagimsiz> readAllBagimsizByBina(Bina bina, KurumSabit kurumSabit) {
		if (kurumSabit != null && kurumSabit.getId() != null && bina != null
				&& bina.getId() != null) {
			
			Criteria criteria = super.getSession().createCriteria(
					Bagimsiz.class, "bagimsiz");
			criteria.createAlias("bagimsiz.adres", "adr");
			criteria.createAlias("bagimsiz.bina", "bina");
			
			criteria.add(Restrictions.eq("bagimsiz.bina.id", bina.getId()));
			criteria.add(Restrictions.eq("adr.kurumSabit.id", kurumSabit.getId()));
			criteria.add(Restrictions.eq("bina.kurumSabit.id", kurumSabit.getId()));
			
			return criteria.list();
		}
		return null;
	}

	public Long readMahalleCaddeSokakKayitSayisiByCriteria(
			KurumSabit kurumSabit, String aciklama, String sorguTipi) {
		// sorgulama yapabilmek icin kurumSabitId ve mahalleCaddeSokak
		// nesnesinin en az bir propertisinin ve sorgulama tipinin belirtilmis
		// olmasi gerekir
		if (kurumSabit == null || kurumSabit.getId() == null
				|| aciklama == null || aciklama.trim().equals("")
				|| sorguTipi == null || sorguTipi.trim().equals("")) {
			return 0L;
		}

		String aciklamaObj = null;
		if (aciklama.contains("*"))
			aciklamaObj = aciklama.replaceAll("[*]", "%");
		else
			aciklamaObj = aciklama + "%";

		Criteria criteria = getSession().createCriteria(
				MahalleCaddeSokak.class, "mcs");
		criteria.createAlias("mcs.mahalle", "mah");
		criteria.createAlias("mcs.caddeSokak", "cad");
		criteria.add(Restrictions.eq("mah.kurumSabit.id", kurumSabit.getId()));
		criteria.add(Restrictions.eq("cad.kurumSabit.id", kurumSabit.getId()));

		if (sorguTipi.equals(AkosSorgulamaTipleri.CADDE_SOKAK_TIP)) {
			criteria.add(Restrictions.like("cad.aciklama", aciklamaObj)
					.ignoreCase());
		} else if (sorguTipi.equals(AkosSorgulamaTipleri.MAHALLE_TIP)) {
			criteria.add(Restrictions.like("mah.aciklama", aciklamaObj)
					.ignoreCase());
		} else {
			criteria.add(Restrictions.or(Restrictions.like("mah.aciklama",
					aciklamaObj).ignoreCase(), Restrictions.like(
					"cad.aciklama", aciklamaObj).ignoreCase()));
		}
		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}

	public List<IslemMenu> readAllIslemMenu() {
		Collection<?> result = super.readDBObjectByHQL(
				"SELECT im FROM IslemMenu im ORDER BY aciklama", null);
		if (result != null && result.size() > 0) {
			return (ArrayList<IslemMenu>) result;
		}
		return null;
	}

	public List<CaddeSokak> readAllCaddeSokakByCriteria(KurumSabit kurumsabit) {

		if (kurumsabit == null || kurumsabit.getId() < 0) {

			return null;
		} else {

			Criteria criteria = getSession().createCriteria(CaddeSokak.class,
					"cs");

			criteria.add(Restrictions.eq("cs.kurumSabit", kurumsabit));

			return criteria.list();
		}
	}

	public List<?> readAllObjectsByKurumSabit(Class entityClass,
			KurumSabit kurumSabit) {
		Criteria criteria = this.getSession().createCriteria(entityClass, "ec");
		criteria.add(Restrictions.eq("ec.kurumSabit.id", kurumSabit.getId()));
		return criteria.list();
	}

	public KadastroParsel readAllKadastroParselByAdres(KurumSabit kurumSabit,
			Adres adres) {

		if (kurumSabit != null && kurumSabit.getId() != null && adres != null
				&& adres.getId() != null) {

			Criteria parcelCriteria = super.getSession().createCriteria(
					KadastroParsel.class, "parcel");
			Collection<Adres> addressCollection = new ArrayList<Adres>();
			addressCollection.add(adres);
			parcelCriteria
					.add(Restrictions.eq("parcel.kurumSabit", kurumSabit));
			parcelCriteria.createAlias("parcel.adresler", "adr");
			parcelCriteria.add(Restrictions.like("adr.id", adres.getId()));

			List<KadastroParsel> parcelList = parcelCriteria.list();
			if (parcelList != null && parcelList.size() == 1) {
				return parcelList.get(0);
			}
		}
		return null;
	}

	public List<KadastroParsel> readAllKadastroParselByAdresAndMahalleCaddeSokak(
			KurumSabit kurumSabit, Adres adres,
			MahalleCaddeSokak mahalleCaddeSokak) {

		if (kurumSabit != null && kurumSabit.getId() != null) {

			List<Object> paramList = new ArrayList<Object>();

		
			String hql = "SELECT DISTINCT kp FROM Adres as adr inner join adr.kadastroParsel AS kp inner join adr.mahalleCaddeSokak as mcs   "
					+ "  WHERE kp.kurumSabit.id=? AND adr.kurumSabit.id=? ";

			paramList.add(kurumSabit.getId());
			paramList.add(kurumSabit.getId());

			if (mahalleCaddeSokak != null) {
				if (mahalleCaddeSokak.getMahalle() != null
						&& mahalleCaddeSokak.getMahalle().getId() != null) {

					hql += " AND mcs.mahalle.id=?";
					paramList.add(mahalleCaddeSokak.getMahalle().getId());
				}

				if (mahalleCaddeSokak.getCaddeSokak() != null
						&& mahalleCaddeSokak.getCaddeSokak().getId() != null) {

					hql += " AND mcs.caddeSokak.id=?";
					paramList.add(mahalleCaddeSokak.getCaddeSokak().getId());
				}

			}

			if (adres != null) {
				if (adres.getId() != null) {

					hql += " AND adr.id=?";
					paramList.add(adres.getId());
				}

				if (adres.getKapiNo() != null) {

					hql += " AND adr.kapiNo=?";
					paramList.add(adres.getKapiNo());
				}

				if (adres.getAltKapiNo() != null
						&& !adres.getAltKapiNo().trim().equals("")) {

					hql += " AND adr.altKapiNo=?";
					paramList.add(adres.getAltKapiNo().trim());
				}

			}

			hql += " ORDER BY kp.id ";

			List<KadastroParsel> findList = (List<KadastroParsel>) super
					.readDBObjectByHQL(hql, paramList.toArray());

			if (findList != null && findList.size() > 0) {

				return findList;
			} else {

				return null;
			}

		} else {

			return null;
		}
	}

	public Collection<?> readAllObjects(Class entityClass) {

		Criteria criteria = this.getSession().createCriteria(entityClass);

		return criteria.list();
	}

	public void saveOrUpdateAdres(Adres adres) {

		super.saveOrUpdate(adres);
	}

	public void saveOrUpdateAdresList(List<Adres> adresList) {

		super.saveOrUpdate(adresList);
	}

	public void saveOrUpdateCaddeSokakList(List<CaddeSokak> caddeSokakList) {

		super.saveOrUpdate(caddeSokakList);

	}

	public void readLazyObject(Object obj) {
		super.readLazyObject(obj);
	}

	public List<Mahalle> readAllMahalleByCriteria(KurumSabit kurumSabit,
			String aciklama, int maxResultCount) {

		if (kurumSabit == null || kurumSabit.getId() == null
				|| aciklama == null || aciklama.trim().equals("")) {

			return null;
		}
		String aciklamaObj = null;

		if (aciklama.contains("*"))

			aciklamaObj = aciklama.replaceAll("[*]", "%");
		else
			aciklamaObj = aciklama + "%";

		Criteria criteria = getSession().createCriteria(Mahalle.class, "mah");

		criteria.add(Restrictions.eq("mah.kurumSabit.id", kurumSabit.getId()));

		criteria.add(Restrictions.like("mah.aciklama", aciklamaObj)
				.ignoreCase());

		criteria.addOrder(Order.asc("mah.aciklama"));

		criteria.setMaxResults(maxResultCount);

		return criteria.list();
	}

	public Long readMahalleSayisiByCriteria(KurumSabit kurumSabit,
			String aciklama) {

		if (kurumSabit == null || kurumSabit.getId() == null
				|| aciklama == null || aciklama.trim().equals("")) {

			return 0L;
		}

		String aciklamaObj = null;

		if (aciklama.contains("*"))

			aciklamaObj = aciklama.replaceAll("[*]", "%");

		else
			aciklamaObj = aciklama + "%";

		Criteria criteria = getSession().createCriteria(Mahalle.class, "mah");
		criteria.add(Restrictions.eq("mah.kurumSabit.id", kurumSabit.getId()));
		criteria.add(Restrictions.like("mah.aciklama", aciklamaObj)
				.ignoreCase());

		criteria.setProjection(Projections.rowCount());

		return (Long) criteria.uniqueResult();
	}

	public Long readAdresKayitSayisiByCriteria(Adres paramAdres,
			KurumSabit kurumSabit) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		hql.append("SELECT count(adr.id) FROM Adres AS adr ");
		hql.append("INNER JOIN adr.mahalleCaddeSokak AS mcs ");
		hql.append("INNER JOIN mcs.mahalle AS mh ");
		hql.append("INNER JOIN mcs.caddeSokak AS cd ");

		hql.append("WHERE 1=1 ");

		if (kurumSabit != null && kurumSabit.getId() != null) {
			hql.append("AND adr.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
			hql.append("AND mh.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
			hql.append("AND cd.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
		}
		if (paramAdres != null) {
			if (paramAdres.getMahalleCaddeSokak() != null
					&& paramAdres.getMahalleCaddeSokak().getId() != null) {
				hql.append("AND adr.mahalleCaddeSokak.id=? ");
				list.add(paramAdres.getMahalleCaddeSokak().getId());
			}
			if (paramAdres.getKapiNo() != null) {
				hql.append("AND adr.kapiNo=? ");
				list.add(paramAdres.getKapiNo());
			}
			if (paramAdres.getAltKapiNo() != null
					&& !paramAdres.getAltKapiNo().trim().equals("")) {
				hql.append("AND adr.altKapiNo=? ");
				list.add(paramAdres.getAltKapiNo());
			}
			if (paramAdres.getAktifEh() != null
					&& !paramAdres.getAktifEh().trim().equals("")) {
				if (paramAdres.getAktifEh().equals("E"))
					hql.append("AND adr.aktifEh='E' ");
				else if (paramAdres.getAktifEh().equals("H"))
					hql.append("AND adr.aktifEh='H' ");
			}
		}

		return (Long) super.readUniqueObjectByQuery(hql.toString(), list, 0, 0);
	}

	public List<Adres> readAllAdresByCriteria(Adres paramAdres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		hql.append("SELECT adr FROM Adres AS adr ");
		hql.append("INNER JOIN adr.mahalleCaddeSokak AS mcs ");
		hql.append("INNER JOIN mcs.mahalle AS mh ");
		hql.append("INNER JOIN mcs.caddeSokak AS cd ");

		hql.append("WHERE 1=1 ");

		if (kurumSabit != null && kurumSabit.getId() != null) {
			hql.append("AND adr.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
			hql.append("AND mh.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
			hql.append("AND cd.kurumSabit.id=? ");
			list.add(kurumSabit.getId());
		}
		if (paramAdres != null) {
			if (paramAdres.getMahalleCaddeSokak() != null
					&& paramAdres.getMahalleCaddeSokak().getId() != null) {
				hql.append("AND adr.mahalleCaddeSokak.id=? ");
				list.add(paramAdres.getMahalleCaddeSokak().getId());
			}
			if (paramAdres.getKapiNo() != null) {
				hql.append("AND adr.kapiNo=? ");
				list.add(paramAdres.getKapiNo());
			}
			if (paramAdres.getAltKapiNo() != null
					&& !paramAdres.getAltKapiNo().trim().equals("")) {
				hql.append("AND adr.altKapiNo=? ");
				list.add(paramAdres.getAltKapiNo());
			}
			if (paramAdres.getAktifEh() != null
					&& !paramAdres.getAktifEh().trim().equals("")) {
				if (paramAdres.getAktifEh().equals("E"))
					hql.append("AND adr.aktifEh='E' ");
				else if (paramAdres.getAktifEh().equals("H"))
					hql.append("AND adr.aktifEh='H' ");
			}
		}

		hql.append("ORDER BY adr.kapiNo, adr.altKapiNo ");

		List<Adres> adresList = (List<Adres>) super.readDBObjectByHQL(hql
				.toString(), list.toArray(), baslangicKaydi,
				maksimumKayitSayisi);

		return adresList;
	}

	public List<Object[]> readAllAdresByAdresSorgulama(
			MahalleCaddeSokak mahalleCaddeSokak, Adres adres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT adr.id,adr.bina.id,mcs.id,mh.id,cd.id,mh.aciklama,");
		hql.append("cd.aciklama,adr.kapiNo,adr.altKapiNo,adr.aktifEh,");
		hql.append("adr.adresTur,adr.aciklama FROM Adres AS adr ");
		hql.append("INNER JOIN adr.mahalleCaddeSokak AS mcs ");
		hql.append("INNER JOIN mcs.mahalle AS mh ");
		hql.append("INNER JOIN mcs.caddeSokak AS cd ");
		hql.append("WHERE adr.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND mh.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND cd.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		if (mahalleCaddeSokak != null && mahalleCaddeSokak.getId() != null) {
			hql.append("AND mcs.id=? ");
			list.add(mahalleCaddeSokak.getId());
		}

		if (adres != null) {
			if (adres.getKapiNo() != null) {
				hql.append("AND adr.kapiNo=? ");
				list.add(adres.getKapiNo());
			}
			if (adres.getAltKapiNo() != null
					&& !adres.getAltKapiNo().trim().equals("")) {
				hql.append("AND adr.altKapiNo=? ");
				list.add(adres.getAltKapiNo());
			}
			if (adres.getAktifEh() != null
					&& !adres.getAktifEh().trim().equals("")) {
				hql.append("AND adr.aktifEh=? ");
				list.add(adres.getAktifEh());
			}
		}
		hql.append("ORDER BY mh.aciklama ASC, cd.aciklama ASC ");

		List<Object[]> tempList = (List<Object[]>) super.readDBObjectByHQL(hql
				.toString(), list.toArray(), baslangicKaydi,
				maksimumKayitSayisi);

		if (tempList != null && tempList.size() == 0)
			tempList = null;

		return tempList;
	}

	/*
	 * Parametrik yazilmis bir DAO'dur. Istenilen kriterlere benzer sekilde
	 * eklenilip kayitlar sorgulanabilir.
	 */
	public List<BagimsizTapu> readAllBagimsizTapuByCriteria(
			BagimsizTapu bagimsizTapu) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		hql.append("SELECT bt FROM BagimsizTapu AS bt ");
		hql.append("INNER JOIN bt.tapu AS tapu ");

		hql.append("WHERE 1=1 ");

		if (bagimsizTapu != null) {

			if (bagimsizTapu.getBagimsiz() != null) {

				if (bagimsizTapu.getBagimsiz().getId() != null) {
					hql.append("AND bt.bagimsiz.id=? ");
					list.add(bagimsizTapu.getBagimsiz().getId());
				}

			}

		}

		List<BagimsizTapu> result = getHibernateTemplate().find(hql.toString(),
				list.toArray());
		if (result != null && result.size() != 0) {
			for (BagimsizTapu temp : result)
				Hibernate.initialize(temp.getTapu());
		}
		return result;
	}

	/*
	 * Istenilen kriterler null kontrolu yapilarak eklenebilir. Kullanildigi
	 * yerlerde tapu parametresinin sadece sorgulanmak istenenen propertyleri
	 * setlenmelidir.
	 */
	public List<Tapu> readAllTapuByCriteria(Tapu tapu) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		hql.append("SELECT  tp FROM Tapu AS tp ");
		hql.append("LEFT JOIN tp.kadastroParsel AS kp ");
		hql.append("WHERE 1=1 ");

		if (tapu != null) {
			if (tapu.getId() != null) {
				hql.append("AND tp.id=? ");
				list.add(tapu.getId());
			}
			if (tapu.getKurumSabit() != null
					&& tapu.getKurumSabit().getId() != null) {
				hql.append("AND tp.kurumSabit.id=? ");
				list.add(tapu.getKurumSabit().getId());
			}
			if (tapu.getKadastroParsel() != null
					&& tapu.getKadastroParsel().getId() != null) {
				hql.append("AND kp.id=? ");
				list.add(tapu.getKadastroParsel().getId());
			}
		}

		return getHibernateTemplate().find(hql.toString(), list.toArray());
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(Bagimsiz bagimsiz) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		hql.append("SELECT bgm FROM Bagimsiz AS bgm ");
		hql.append("INNER JOIN bgm.adres AS adr ");
		hql.append("INNER JOIN bgm.bina AS bina ");
		hql.append("LEFT JOIN bgm.bagimsizKullanimDetay AS bkd ");
		hql.append("LEFT JOIN bkd.kullanimSekli AS ks ");

		hql.append("WHERE 1=1 ");

		if (bagimsiz != null) {

			if (bagimsiz.getBina() != null) {
				if (bagimsiz.getBina().getId() != null) {
					hql.append("AND bina.id=? ");
					list.add(bagimsiz.getBina().getId());
				}
				if (bagimsiz.getBina().getKurumSabit() != null
						&& bagimsiz.getBina().getKurumSabit().getId() != null) {
					hql.append("AND bina.kurumSabit.id=? ");
					list.add(bagimsiz.getBina().getKurumSabit().getId());
				}
			}

			if (bagimsiz.getId() != null) {
				hql.append("AND bgm.id=? ");
				list.add(bagimsiz.getId());
			}

			if (bagimsiz.getAdres() != null) {
				if (bagimsiz.getAdres().getId() != null) {
					hql.append("AND adr.id=? ");
					list.add(bagimsiz.getAdres().getId());
				}
				if (bagimsiz.getAdres().getKurumSabit() != null
						&& bagimsiz.getAdres().getKurumSabit() != null) {
					hql.append("AND adr.kurumSabit.id=? ");
					list.add(bagimsiz.getAdres().getKurumSabit().getId());
				}
			}

			if (bagimsiz.getAktifEh() != null
					&& !bagimsiz.getAktifEh().trim().equals("")) {
				if (bagimsiz.getAktifEh().trim().equals("E"))
					hql.append("AND bgm.aktifEh='E' ");
				else if (bagimsiz.getAktifEh().trim().equals("H"))
					hql.append("AND bgm.aktifEh='H' ");
			}
		}

		hql.append("ORDER BY bgm.daireNo, bgm.altDaireNo ");

		return getHibernateTemplate().find(hql.toString(), list.toArray());
	}

	public List<BinaParsel> readAllBinaParselByCriteria(BinaParsel binaParsel) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		hql.append("SELECT bp FROM BinaParsel AS bp ");
		hql.append("INNER JOIN bp.kadastroParsel AS kp ");
		hql.append("WHERE 1=1 ");

		if (binaParsel != null) {
			if (binaParsel.getBina() != null
					&& binaParsel.getBina().getId() != null) {
				hql.append("AND bp.bina.id=? ");
				list.add(binaParsel.getBina().getId());
			}
			if (binaParsel.getKadastroParsel() != null) {
				if (binaParsel.getKadastroParsel().getPafta() != null
						&& !binaParsel.getKadastroParsel().getPafta().trim()
								.equals("")) {
					hql.append("AND kp.pafta=? ");
					list.add(binaParsel.getKadastroParsel().getPafta());
				}
				if (binaParsel.getKadastroParsel().getAda() != null) {
					hql.append("AND kp.ada=? ");
					list.add(binaParsel.getKadastroParsel().getAda());
				}
				if (binaParsel.getKadastroParsel().getParsel() != null) {
					hql.append("AND kp.parsel=? ");
					list.add(binaParsel.getKadastroParsel().getParsel());
				}
			}
		}

		hql.append("ORDER BY kp.pafta, kp.ada, kp.parsel ");

		return getHibernateTemplate().find(hql.toString(), list.toArray());
	}

	public Long readBagimsizKayitSayisiByCriteria(Bagimsiz bagimsiz) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();

		hql.append("SELECT count(bgm.id) FROM Bagimsiz AS bgm ");
		hql.append("INNER JOIN bgm.adres AS adr ");
		hql.append("WHERE 1=1 ");

		if (bagimsiz != null) {

			if (bagimsiz.getAdres() != null) {
				if (bagimsiz.getAdres().getId() != null) {
					hql.append("AND adr.id=? ");
					list.add(bagimsiz.getAdres().getId());
				}
				if (bagimsiz.getAdres().getKurumSabit() != null
						&& bagimsiz.getAdres().getKurumSabit() != null) {
					hql.append("AND adr.kurumSabit.id=? ");
					list.add(bagimsiz.getAdres().getKurumSabit().getId());
				}
			}

			if (bagimsiz.getAktifEh() != null
					&& !bagimsiz.getAktifEh().trim().equals("")) {
				if (bagimsiz.getAktifEh().trim().equals("E"))
					hql.append("AND bgm.aktifEh='E' ");
				else if (bagimsiz.getAktifEh().trim().equals("H"))
					hql.append("AND bgm.aktifEh='H' ");
			}
		}

		return (Long) super.readUniqueObjectByQuery(hql.toString(), list, 0, 0);
	}

	public Long readBagimsizKayitSayisiByCriteria(Adres adres,
			KurumSabit kurumSabit) {
		if (adres == null || adres.getId() == null) {
			return 0L;
		}

		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT count(bgm.id) FROM Bagimsiz AS bgm ");
		hql.append("INNER JOIN bgm.adres AS adr ");
		hql.append("WHERE adr.id=? ");
		hql.append("AND bgm.aktifEh='E' ");
		list.add(adres.getId());
		hql.append("AND adr.kurumSabit.id=? ");
		list.add(kurumSabit.getId());

		return (Long) super.readUniqueObjectByQuery(hql.toString(), list, 0, 0);
	}

	public List<Object[]> readAllBagimsizByAdresSorgulama(Adres adres,
			KurumSabit kurumSabit, int baslangicKaydi, int maksimumKayitSayisi) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT bgm.id,bgm.daireNo,bgm.altDaireNo,bgm.katNo,");
		hql.append("bgm.tuikSiraNo,bgm.bagimsizNo,bgm.yuzolcum,");
		hql.append("bgm.acilisTarih,bgm.kapanisTarih,bgm.aktifEh,");
		hql.append("bgm.aciklama,bkd.aciklama FROM Bagimsiz AS bgm ");
		hql.append("INNER JOIN bgm.adres AS adr ");
		hql.append("LEFT OUTER JOIN bgm.bagimsizKullanimDetay AS bkd ");
		hql.append("WHERE adr.id=? ");
		list.add(adres.getId());
		hql.append("AND adr.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("ORDER BY bgm.id ASC ");

		List<Object[]> tempList = (List<Object[]>) super.readDBObjectByHQL(hql
				.toString(), list.toArray(), baslangicKaydi,
				maksimumKayitSayisi);
		if (tempList != null && tempList.size() == 0)
			tempList = null;

		return tempList;
	}

	public List<Object[]> readAllMahalleByAdresSorgulama(KurumSabit kurumSabit) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT m.id,m.aciklama FROM Mahalle AS m ");
		hql.append("WHERE m.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("ORDER BY m.aciklama ");

		List<Object[]> tempList = getHibernateTemplate().find(hql.toString(),
				list.toArray());
		if (tempList != null && tempList.size() == 0)
			tempList = null;

		return tempList;
	}

	public List<Mahalle> readAllMahalleByMahalleList(KurumSabit kurumSabit,
			List<Mahalle> districtList, Integer maxResultCount) {

		Criteria criteria = getSession().createCriteria(Mahalle.class, "mah");
		List<Long> idList = new ArrayList<Long>();
		if (districtList != null && districtList.size() > 0) {
			for (Mahalle tmpDistrict : districtList) {
				if (tmpDistrict.getId() != null) {
					idList.add(tmpDistrict.getId());
				}
			}
			if (idList != null && idList.size() > 0) {
				criteria.add(Restrictions.in("mah.id", idList));
			}
		}
		if (maxResultCount != null) {
			criteria.setMaxResults(maxResultCount);
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Mahalle> tempList = criteria.list();
		return tempList;
	}

	public List<Object[]> readAllCaddeSokakByAdresSorgulama(
			KurumSabit kurumSabit, Mahalle mahalle) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT cs.id,cs.aciklama FROM MahalleCaddeSokak AS mcs ");
		hql.append("INNER JOIN mcs.mahalle AS m ");
		hql.append("INNER JOIN mcs.caddeSokak AS cs ");
		hql.append("WHERE m.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND m.id=? ");
		list.add(mahalle.getId());
		hql.append("AND cs.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("ORDER BY cs.aciklama ");

		List<Object[]> tempList = getHibernateTemplate().find(hql.toString(),
				list.toArray());
		if (tempList != null && tempList.size() == 0)
			tempList = null;

		return tempList;
	}

	public List<CaddeSokak> readAllCaddeSokakByCaddeSokakList(
			KurumSabit kurumSabit, List<CaddeSokak> streetList,
			Integer maxResultCount) {

		Criteria criteria = getSession()
				.createCriteria(CaddeSokak.class, "cad");
		List<Long> idList = new ArrayList<Long>();
		if (streetList != null && streetList.size() > 0) {
			for (CaddeSokak tmpStreet : streetList) {
				if (tmpStreet.getId() != null) {
					idList.add(tmpStreet.getId());
				}
			}
			if (idList != null && idList.size() > 0) {
				criteria.add(Restrictions.in("cad.id", idList));
			}
		}
		if (maxResultCount != null) {
			criteria.setMaxResults(maxResultCount);
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<CaddeSokak> tempList = criteria.list();
		return tempList;
	}

	public List<Object[]> readAllBinaByAdresSorgulama(Adres adres,
			KurumSabit kurumSabit) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT b.id,s.id,b.binaNo,s.aciklama,b.apartmanBlokAd,");
		hql.append("b.zoneNo,b.aktifEh,b.acilisTarih,b.kapanisTarih,");
		hql.append("bks.aciklama,ins.aciklama,it.aciklama FROM Adres AS adr ");
		hql.append("INNER JOIN adr.bina AS b ");
		hql.append("LEFT OUTER JOIN b.bagimsizKullanimSinif AS bks ");
		hql.append("LEFT OUTER JOIN b.insaatSinif AS ins ");
		hql.append("LEFT OUTER JOIN b.insaatTur AS it ");
		hql.append("LEFT OUTER JOIN b.site AS s ");
		hql.append("WHERE adr.id=? ");
		list.add(adres.getId());
		hql.append("AND b.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND (s.kurumSabit.id=? OR s.id IS NULL) ");
		list.add(kurumSabit.getId());
		List<Object[]> tempList = getHibernateTemplate().find(hql.toString(),
				list.toArray());
		return tempList;
	}

	public Object readPropertyByObject(Long id, String property, Class entity) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>(1);
		hql.append("SELECT ob." + property + " FROM ");
		hql.append(entity.getName() + " AS ob ");
		hql.append("WHERE ob.id=? ");
		list.add(id);

		return super.readUniqueObjectByQuery(hql.toString(), list, 0, 0);
	}

	public Object readObjectById(Class entity, Long id) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>(1);
		hql.append("SELECT ob FROM ");
		hql.append(entity.getName() + " AS ob ");
		hql.append("WHERE ob.id=? ");
		list.add(id);

		return super.readUniqueObjectByQuery(hql.toString(), list, 0, 0);
	}

	public void saveOrUpdateBagimsiz(Bagimsiz bagimsiz) {
		super.saveOrUpdate(bagimsiz);
	}

	public void saveOrUpdateBagimsizList(List<Bagimsiz> bagimsizList) {
		super.saveOrUpdate(bagimsizList);
	}

	public void saveOrUpdateMahalle(Mahalle mahalle) {
		super.saveOrUpdate(mahalle);
	}

	public void saveOrUpdateKadastroParselTipi(
			KadastroParselTipi kadastroParselTipi) {
		super.saveOrUpdate(kadastroParselTipi);
	}

	public void saveOrUpdateKadastroParsel(KadastroParsel kadastroParsel) {
		super.saveOrUpdate(kadastroParsel);
	}

	public void saveOrUpdateTapuMahalle(TapuMahalle tapuMahalle) {
		super.saveOrUpdate(tapuMahalle);
	}

	public void saveOrUpdateKadastroHareketGrup(
			KadastroHareketGrup kadastroHareketGrup) {
		super.saveOrUpdate(kadastroHareketGrup);
	}

	public void saveOrUpdateKadastroHareketKod(
			KadastroHareketKod kadastroHareketKod) {
		super.saveOrUpdate(kadastroHareketKod);
	}

	public void saveOrUpdateBilgiTipi(BilgiTipi bilgiTipi) {
		super.saveOrUpdate(bilgiTipi);
	}

	public void saveOrUpdateBinaCatiTur(BinaCatiTur binaCatiTur) {
		super.saveOrUpdate(binaCatiTur);
	}

	public void saveOrUpdateDosemeTur(DosemeTur dosemeTur) {
		super.saveOrUpdate(dosemeTur);
	}

	public void saveOrUpdateIsitmaTur(IsitmaTur isitmaTur) {
		super.saveOrUpdate(isitmaTur);
	}

	public void saveOrUpdateSicakSuTeminTur(SicakSuTeminTur sicakSuTeminTur) {
		super.saveOrUpdate(sicakSuTeminTur);
	}

	public void saveOrUpdateTasiyiciSistem(TasiyiciSistem tasiyiciSistem) {
		super.saveOrUpdate(tasiyiciSistem);
	}

	public void saveOrUpdateUretimKaynak(UretimKaynak uretimKaynak) {
		super.saveOrUpdate(uretimKaynak);
	}

	public void saveOrUpdateYakitTur(YakitTur yakitTur) {
		super.saveOrUpdate(yakitTur);
	}

	public void saveOrUpdateYapiCepheTur(YapiCepheTur yapiCepheTur) {
		super.saveOrUpdate(yapiCepheTur);
	}

	public void saveOrUpdateBina(Bina bina) {
		super.saveOrUpdate(bina);
	}

	public void saveOrUpdateBinaParsel(BinaParsel binaParsel) {
		super.saveOrUpdate(binaParsel);
	}

	public void saveOrUpdateKonutDurum(KonutDurum konutDurum) {
		super.saveOrUpdate(konutDurum);
	}

	public void saveOrUpdateKonutSahiplikDurum(
			KonutSahiplikDurum konutSahiplikDurum) {
		super.saveOrUpdate(konutSahiplikDurum);
	}

	public void saveOrUpdateKonutTip(KonutTip konutTip) {
		super.saveOrUpdate(konutTip);
	}

	public void saveOrUpdateGelismislikDurum(GelismislikDurum gelismislikDurum) {
		super.saveOrUpdate(gelismislikDurum);
	}

	public void saveOrUpdateKullanimSekli(KullanimSekli kullanimSekli) {
		super.saveOrUpdate(kullanimSekli);
	}

	public List<GelismislikDurum> readAllGelismislikDurumByCriteria(
			GelismislikDurum gelismislikDurum) {

		if (gelismislikDurum != null) {

			Criteria criteria = this.getSession().createCriteria(
					GelismislikDurum.class, "gd");

			if (gelismislikDurum.getAciklama() != null) {

				criteria.add(Restrictions.eq("gd.aciklama", gelismislikDurum
						.getAciklama()));
			}
			if (gelismislikDurum.getId() != null) {

				criteria
						.add(Restrictions.eq("gd.id", gelismislikDurum.getId()));
			}
			if (gelismislikDurum.getKaydeden() != null) {

				criteria.add(Restrictions.eq("gd.kaydeden", gelismislikDurum
						.getKaydeden()));
			}
			if (gelismislikDurum.getKayitTarih() != null) {

				criteria.add(Restrictions.eq("gd.kayitTarihi", gelismislikDurum
						.getKayitTarih()));
			}
			return criteria.list();
		}
		return null;
	}

	public List<Bagimsiz> readAllBagimsizByCriteria(KurumSabit kurumSabit,
			List<BagimsizKullanimSinif> bagimsizKullanimSinifList,
			List<BagimsizKullanimGrup> bagimsizKullanimGrupList,
			List<BagimsizKullanimDetay> bagimsizKullanimDetayList,
			Long maxResultCount) {

		if (kurumSabit != null && kurumSabit.getId() != null) {

			Criteria criteria = getSession().createCriteria(Bagimsiz.class,
					"bgm");

			List<Long> bkSinifIdList = new ArrayList<Long>();

			List<Long> bkGrupIdList = new ArrayList<Long>();

			List<Long> bkDetayIdList = new ArrayList<Long>();

			if (bagimsizKullanimSinifList != null
					&& bagimsizKullanimSinifList.size() > 0) {

				for (BagimsizKullanimSinif bkSinif : bagimsizKullanimSinifList) {

					bkSinifIdList.add(bkSinif.getId());
				}
			}

			if (bagimsizKullanimGrupList != null
					&& bagimsizKullanimGrupList.size() > 0) {

				for (BagimsizKullanimGrup bkGrup : bagimsizKullanimGrupList) {

					bkGrupIdList.add(bkGrup.getId());
				}
			}

			if (bagimsizKullanimDetayList != null
					&& bagimsizKullanimDetayList.size() > 0) {

				for (BagimsizKullanimDetay bkDetay : bagimsizKullanimDetayList) {

					bkDetayIdList.add(bkDetay.getId());
				}
			}

			if (bkDetayIdList.size() > 0 || bkGrupIdList.size() > 0
					|| bkSinifIdList.size() > 0) {

				criteria.createAlias("bgm.bagimsizKullanimDetay", "bkd");

				if (bkGrupIdList.size() > 0 || bkSinifIdList.size() > 0) {

					criteria.createAlias("bkd.bagimsizKullanimGrup", "bkg");
				}

				if (bkSinifIdList.size() > 0) {

					criteria.createAlias("bkg.bagimsizKullanimSinif", "bks");
				}

				if (bkDetayIdList.size() > 0) {

					criteria.add(Restrictions.in("bkd.id", bkDetayIdList));
				}
				if (bkGrupIdList.size() > 0) {

					criteria.add(Restrictions.in("bkg.id", bkGrupIdList));
				}
				if (bkSinifIdList.size() > 0) {

					criteria.add(Restrictions.in("bks.id", bkSinifIdList));
				}

				criteria.createAlias("bgm.bina", "bn");

				criteria.add(Restrictions.eq("bn.kurumSabit.id", kurumSabit
						.getId()));

				criteria.add(Restrictions.eq("bgm.aktifEh", "E"));

				if (maxResultCount != null && maxResultCount.longValue() > 0) {

					criteria.setMaxResults((int) maxResultCount.longValue());
				}

				List<Bagimsiz> tempList = criteria.list();
				return tempList;

			}
		}
		return null;
	}

	public Long readKadastroParselKayitSayisiByCriteria(
			KadastroParsel paramKadastroParsel) {
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();

		hql.append("SELECT count( kp.id ) FROM KadastroParsel AS kp ");
		hql.append("LEFT JOIN kp.uretimKaynak AS uk ");

		hql.append("WHERE 1=1 ");

		if (paramKadastroParsel != null) {
			if (paramKadastroParsel.getKurumSabit() != null
					&& paramKadastroParsel.getKurumSabit().getId() != null) {
				hql.append("AND kp.kurumSabit.id=? ");
				list.add(paramKadastroParsel.getKurumSabit().getId());
			}
			if (paramKadastroParsel.getPafta() != null
					&& !paramKadastroParsel.getPafta().trim().equals("")) {
				hql.append("AND kp.pafta=? ");
				list.add(paramKadastroParsel.getPafta());
			}
			if (paramKadastroParsel.getAda() != null) {
				hql.append("AND kp.ada=? ");
				list.add(paramKadastroParsel.getAda());
			}
			if (paramKadastroParsel.getParsel() != null) {
				hql.append("AND kp.parsel=? ");
				list.add(paramKadastroParsel.getParsel());
			}
			if (paramKadastroParsel.getAktifEh() != null
					&& !paramKadastroParsel.getAktifEh().trim().equals("")) {
				if (paramKadastroParsel.getAktifEh().equals("E")) {
					hql.append("AND kp.aktifEh='E' ");
				} else if (paramKadastroParsel.getAktifEh().equals("H")) {
					hql.append("AND kp.aktifEh='H' ");
				}
			}

			if (paramKadastroParsel.getUretimKaynak() != null
					&& paramKadastroParsel.getUretimKaynak().getId() != null) {
				hql.append("AND uk.id=? ");
				list.add(paramKadastroParsel.getUretimKaynak().getId());
			}

		}

		return (Long) super.readUniqueObjectByQuery(hql.toString(), list, 0, 0);
	}

	public List<Bina> readAllBinaByCriteria(Bina bina) {
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();

		hql.append("SELECT bn FROM Bina AS bn ");
		hql.append("LEFT JOIN bn.kadastroParsel AS kp ");

		hql.append("WHERE 1=1 ");

		if (bina != null) {
			if (bina.getKadastroParsel() != null
					&& bina.getKadastroParsel().getId() != null) {
				hql.append("AND kp.id=? ");
				list.add(bina.getKadastroParsel().getId());
			}
			if (bina.getKurumSabit() != null
					&& bina.getKurumSabit().getId() != null) {
				hql.append("AND bn.kurumSabit.id=? ");
				list.add(bina.getKurumSabit().getId());
			}
		}

		List<Bina> tempList = getHibernateTemplate().find(hql.toString(),
				list.toArray());
		if (tempList != null && tempList.size() != 0) {
			for (Bina tempBina : tempList) {
				Hibernate.initialize(tempBina.getSite());
				Hibernate.initialize(tempBina.getBagimsizKullanimSinif());
				Hibernate.initialize(tempBina.getInsaatSinif());
				Hibernate.initialize(tempBina.getInsaatTur());
				Hibernate.initialize(tempBina.getAcilisHareketKod());
				Hibernate.initialize(tempBina.getKapanisHareketKod());
			}
		}
		return tempList;
	}

	public List<KadastroParsel> readAllKadastroParselByCriteria(
			KadastroParsel paramKadastroParsel, int baslangicKaydi,
			int maksimumKayitSayisi) {
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();

		hql.append("SELECT kp FROM KadastroParsel AS kp ");
		hql.append("LEFT JOIN kp.uretimKaynak AS uk ");
		hql.append("LEFT JOIN kp.tapuMahalle AS tm ");
		hql.append("LEFT JOIN kp.mahalle AS mah ");
		hql.append("LEFT JOIN kp.acilisHareketKod AS ahk ");
		hql.append("LEFT JOIN kp.kapanisHareketKod AS khk ");

		hql.append("WHERE 1=1 ");

		if (paramKadastroParsel != null) {
			if (paramKadastroParsel.getKurumSabit() != null
					&& paramKadastroParsel.getKurumSabit().getId() != null) {
				hql.append("AND kp.kurumSabit.id=? ");
				list.add(paramKadastroParsel.getKurumSabit().getId());
			}
			if (paramKadastroParsel.getPafta() != null
					&& !paramKadastroParsel.getPafta().trim().equals("")) {
				hql.append("AND kp.pafta=? ");
				list.add(paramKadastroParsel.getPafta());
			}
			if (paramKadastroParsel.getAda() != null) {
				hql.append("AND kp.ada=? ");
				list.add(paramKadastroParsel.getAda());
			}
			if (paramKadastroParsel.getParsel() != null) {
				hql.append("AND kp.parsel=? ");
				list.add(paramKadastroParsel.getParsel());
			}
			if (paramKadastroParsel.getAktifEh() != null
					&& !paramKadastroParsel.getAktifEh().trim().equals("")) {
				if (paramKadastroParsel.getAktifEh().equals("E")) {
					hql.append("AND kp.aktifEh='E' ");
				} else if (paramKadastroParsel.getAktifEh().equals("H")) {
					hql.append("AND kp.aktifEh='H' ");
				}
			}

			if (paramKadastroParsel.getUretimKaynak() != null
					&& paramKadastroParsel.getUretimKaynak().getId() != null) {
				hql.append("AND uk.id=? ");
				list.add(paramKadastroParsel.getUretimKaynak().getId());
			}

		}

		hql.append("ORDER BY kp.pafta, kp.ada, kp.parsel ");

		List<KadastroParsel> tempList = (List<KadastroParsel>) super
				.readDBObjectByHQL(hql.toString(), list.toArray(),
						baslangicKaydi, maksimumKayitSayisi);
		if (tempList != null && tempList.size() != 0) {
			for (KadastroParsel kadastroParsel : tempList) {
				Hibernate.initialize(kadastroParsel.getUretimKaynak());
				Hibernate.initialize(kadastroParsel.getTapuMahalle());
			}
		}
		return tempList;
	}

	public List<KadastroParsel> readAllKadastroParselByKadastroSorgulama(
			KadastroParsel kadastroParsel, int baslangicKaydi,
			int maksimumKayitSayisi) {
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT kp FROM KadastroParsel AS kp ");
		hql.append("WHERE kp.kurumSabit.id=? ");
		list.add(kadastroParsel.getKurumSabit().getId());
		if (kadastroParsel.getUretimKaynak() != null
				&& kadastroParsel.getUretimKaynak().getId() != null) {
			hql.append("AND kp.uretimKaynak.id=? ");
			list.add(kadastroParsel.getUretimKaynak().getId());
		}
		if (kadastroParsel.getPafta() != null
				&& !kadastroParsel.getPafta().trim().equals("")) {
			hql.append("AND kp.pafta=? ");
			list.add(kadastroParsel.getPafta());
		}
		if (kadastroParsel.getAda() != null) {
			hql.append("AND kp.ada=? ");
			list.add(kadastroParsel.getAda());
		}
		if (kadastroParsel.getParsel() != null) {
			hql.append("AND kp.parsel=? ");
			list.add(kadastroParsel.getParsel());
		}
		if (kadastroParsel.getAktifEh() != null
				&& !kadastroParsel.getAktifEh().trim().equals("")) {
			hql.append("AND kp.aktifEh=?");
			list.add(kadastroParsel.getAktifEh());
		}

		List<KadastroParsel> tempList = getHibernateTemplate().find(
				hql.toString(), list.toArray());
		return tempList;
	}

	public List<Object[]> readAllBagimsizByKadastroParselSorgulama(
			KurumSabit kurumSabit, KadastroParsel kadastroParsel,
			int baslangicKaydi, int maksimumKayitSayisi) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT bgm.id,bgm.daireNo,bgm.altDaireNo,bgm.katNo,");
		hql.append("bgm.tuikSiraNo,bgm.bagimsizNo,bgm.yuzolcum,");
		hql.append("bgm.acilisTarih,bgm.kapanisTarih,bgm.aktifEh,");
		hql.append("bgm.aciklama,bkd.aciklama FROM Bagimsiz AS bgm ");
		hql.append("INNER JOIN bgm.bina AS bina ");
		hql.append("LEFT OUTER JOIN bgm.bagimsizKullanimDetay AS bkd ");
		hql.append("WHERE bina.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND bina.kadastroParsel.id=? ");
		list.add(kadastroParsel.getId());
		hql.append("ORDER BY bgm.id ASC ");

		List<Object[]> tempList = (List<Object[]>) super.readDBObjectByHQL(hql
				.toString(), list.toArray(), baslangicKaydi,
				maksimumKayitSayisi);
		if (tempList != null && tempList.size() == 0)
			tempList = null;

		return tempList;
	}

	public List<Object[]> readAllBinaByKadastroSorgulama(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT b.id,s.id,b.binaNo,s.aciklama,b.apartmanBlokAd,");
		hql.append("b.zoneNo,b.aktifEh,b.acilisTarih,b.kapanisTarih,");
		hql.append("bks.aciklama,ins.aciklama,it.aciklama FROM Bina AS b ");
		hql.append("LEFT OUTER JOIN b.bagimsizKullanimSinif AS bks ");
		hql.append("LEFT OUTER JOIN b.insaatSinif AS ins ");
		hql.append("LEFT OUTER JOIN b.insaatTur AS it ");
		hql.append("LEFT OUTER JOIN b.site AS s ");
		hql.append("WHERE b.kadastroParsel.id=? ");
		list.add(kadastroParsel.getId());
		hql.append("AND b.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		hql.append("AND (s.kurumSabit.id=? OR s.id IS NULL) ");
		list.add(kurumSabit.getId());
		List<Object[]> tempList = getHibernateTemplate().find(hql.toString(),
				list.toArray());
		return tempList;
	}

	public Object[] readAdresByKadastroSorgulama(Bagimsiz bagimsiz) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT adr.id,adr.bina.id,mcs.id,mh.id,cd.id,mh.aciklama,");
		hql.append("cd.aciklama,adr.kapiNo,adr.altKapiNo FROM Bagimsiz AS bg ");
		hql.append("INNER JOIN bg.adres AS adr ");
		hql.append("INNER JOIN adr.mahalleCaddeSokak AS mcs ");
		hql.append("INNER JOIN mcs.mahalle AS mh ");
		hql.append("INNER JOIN mcs.caddeSokak AS cd ");
		hql.append("WHERE bg.id=? ");
		list.add(bagimsiz.getId());
		hql.append("ORDER BY mh.aciklama ASC, cd.aciklama ASC ");

		Object[] tempList = (Object[]) super.readUniqueObjectByQuery(hql
				.toString(), list, 0, 0);
		return tempList;
	}

	public Object[] readBinaByKadastroSorgulama(Bagimsiz bagimsiz) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT b.id,s.id,b.binaNo,s.aciklama,b.apartmanBlokAd ");
		hql.append("FROM Bagimsiz AS bgm ");
		hql.append("INNER JOIN bgm.bina AS b ");
		hql.append("LEFT OUTER JOIN b.site AS s ");
		hql.append("WHERE bgm.id=? ");
		list.add(bagimsiz.getId());
		Object[] tempList = (Object[]) super.readUniqueObjectByQuery(hql
				.toString(), list, 0, 0);
		return tempList;
	}

	public <T> List<T> readAllObjectByIdList(Class entity, List<Long> idList) {

		if (idList == null) {
			idList = new ArrayList<Long>();
		}

		if (idList.size() == 0)
			idList.add(0L);

		Criteria criteria = getSession().createCriteria(entity, "ent");

		criteria.add(Restrictions.in("ent.id", idList));

		return criteria.list();
	}

	public void saveOrUpdateBinaNitelik(BinaNitelik binaNitelik) {
		super.saveOrUpdate(binaNitelik);
	}

	public void saveOrUpdateBinaOrtakKullanim(
			BinaOrtakKullanim binaOrtakKullanim) {
		super.saveOrUpdate(binaOrtakKullanim);
	}

	public void saveOrUpdateBinaOrtakKullanimList(
			List<BinaOrtakKullanim> binaOrtakKullanimList) {
		super.saveOrUpdate(binaOrtakKullanimList);
	}

	public void saveOrUpdateBinaParselList(List<BinaParsel> binaParselList) {
		super.saveOrUpdate(binaParselList);
	}

	public void saveOrUpdateBinaTesisat(BinaTesisat binaTesisat) {
		super.saveOrUpdate(binaTesisat);
	}

	public void saveOrUpdateBinaTesisatList(List<BinaTesisat> binaTesisatList) {
		super.saveOrUpdate(binaTesisatList);
	}

	public List<Bagimsiz> readAllBagimsizByBina(Bina bina) {

		if (bina != null && bina.getId() != null && bina.getId() > 0) {

			Criteria criteria = getSession().createCriteria(Bagimsiz.class);

			criteria.add(Restrictions.eq("bina.id", bina.getId()));

			return criteria.list();
		}

		return null;
	}

	public List<BinaOrtakKullanim> readAllBinaOrtakKullanimByBina(Bina bina) {

		if (bina != null && bina.getId() != null && bina.getId() > 0) {

			Criteria criteria = getSession().createCriteria(
					BinaOrtakKullanim.class);

			criteria.add(Restrictions.eq("bina.id", bina.getId()));

			return criteria.list();
		}

		return null;
	}

	public List<BinaParsel> readAllBinaParselByBina(Bina bina) {

		if (bina != null && bina.getId() != null && bina.getId() > 0) {

			Criteria criteria = getSession().createCriteria(BinaParsel.class);

			criteria.add(Restrictions.eq("bina.id", bina.getId()));

			return criteria.list();
		}

		return null;
	}

	public List<BinaTesisat> readAllBinaTesisatTurByBina(Bina bina) {

		if (bina != null && bina.getId() != null && bina.getId() > 0) {

			Criteria criteria = getSession().createCriteria(BinaTesisat.class);

			criteria.add(Restrictions.eq("bina.id", bina.getId()));

			return criteria.list();
		}

		return null;
	}

	public List<BinaNitelik> readAllBinaNitelikByBina(Bina bina) {

		if (bina != null && bina.getId() != null && bina.getId() > 0) {

			Criteria criteria = getSession().createCriteria(BinaNitelik.class);

			criteria.add(Restrictions.eq("bina.id", bina.getId()));

			return criteria.list();
		}

		return null;
	}

	public void deleteDBObject(Object entity) {
		super.deleteDBObject(entity);
	}

	public void deleteDBObjectList(Collection<?> liste) {
		super.deleteDBObject(liste);
	}

	public void saveOrUpdateBinaNitelikList(List<BinaNitelik> binaNitelikList) {
		super.saveOrUpdate(binaNitelikList);
	}

	public List<HareketKod> readAllHareketKod() {

		Criteria criteria = getSession().createCriteria(HareketKod.class);

		criteria.addOrder(Order.asc("aciklama"));

		return criteria.list();

	}

	public Long readBinaParselSayisiByBina(Bina bina) {
		if (bina != null && bina.getId() != null) {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT count(bp.id) FROM BinaParsel AS bp ");
			hql.append("WHERE bp.bina.id=? ");

			List<Long> list = (List<Long>) super.readDBObjectByHQL(hql
					.toString(), new Object[] { bina.getId() });

			return list.get(0);
		}
		return null;
	}

	public List<Object[]> readAllTapuByKadastroSorgulama(
			KadastroParsel kadastroParsel, KurumSabit kurumSabit) {
		StringBuilder hql = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		hql.append("SELECT t.arsaPay,t.hissePay,t.arsaPayda,t.hissePayda,");
		hql.append("t.yuzolcum,t.iktisapTarih,t.edinSebep,t.kapanma ");
		hql.append("FROM Tapu AS t ");
		hql.append("WHERE t.kadastroParsel.id=? ");
		list.add(kadastroParsel.getId());
		hql.append("AND t.kurumSabit.id=? ");
		list.add(kurumSabit.getId());
		List<Object[]> tempList = getHibernateTemplate().find(hql.toString(),
				list.toArray());
		return tempList;
	}

	public Long readBagimsizTapuSayisiByBagimsiz(Bagimsiz bagimsiz) {
		if (bagimsiz != null && bagimsiz.getId() != null) {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT count(bt.id) FROM BagimsizTapu AS bt ");
			hql.append("WHERE bt.bagimsiz.id=? ");

			List<Long> list = (List<Long>) super.readDBObjectByHQL(hql
					.toString(), new Object[] { bagimsiz.getId() });
			return list.get(0);
		}
		return null;
	}

	public List<Object[]> readKadastroParselByBinaParselSorgulama(Bina bina,
			KurumSabit kurumSabit) {
		List<Object[]> tempList = null;
		if (bina != null && bina.getId() != null) {
			StringBuilder hql = new StringBuilder();
			List<Object> list = new ArrayList<Object>(2);
			hql.append("SELECT kp.id,kp.pafta,kp.ada,kp.parsel,");
			hql.append("kp.aktifEh,kp.alan,kp.acilisTarih,kp.kapanisTarih ");
			hql.append("FROM BinaParsel AS bp ");
			hql.append("INNER JOIN bp.kadastroParsel AS kp ");
			hql.append("WHERE bp.bina.id=? ");
			list.add(bina.getId());
			hql.append("AND kp.kurumSabit.id=? ");
			list.add(kurumSabit.getId());

			tempList = (List<Object[]>) super.readDBObjectByHQL(hql.toString(),
					list.toArray());
			if (tempList != null && tempList.size() == 0)
				tempList = null;
		}
		return tempList;
	}

	public List<BagimsizTapu> readBagimsizTapuByBagimsiz(Bagimsiz bagimsiz) {
		if (bagimsiz != null && bagimsiz.getId() != null) {
			StringBuilder hql = new StringBuilder();
			hql.append("SELECT bt FROM BagimsizTapu AS bt ");
			hql.append("WHERE bt.bagimsiz.id=? ");

			List<BagimsizTapu> list = (List<BagimsizTapu>) super
					.readDBObjectByHQL(hql.toString(), new Object[] { bagimsiz
							.getId() });
			if (list != null && list.size() > 0)
				return list;
		}
		return null;
	}

	public List<Tapu> readAllTapuByCriteria(KadastroParsel kadastroParsel,
			KurumSabit kurumSabit) {
		List<Object> paramList = new ArrayList<Object>();
		String hql = "";
		if (kurumSabit != null && kurumSabit.getId() != null) {
			hql = "Select tp From Tapu As tp inner join tp.kadastroParsel as kp "
					+ " Where tp.kurumSabit=? And kp.kurumSabit=? ";
			paramList.add(kurumSabit);
			paramList.add(kurumSabit);
			if (kadastroParsel != null && kadastroParsel.getId() != null) {
				hql += " And kp=?";
				paramList.add(kadastroParsel);
			}
			List list = (List<Tapu>) super.readDBObjectByHQL(hql, paramList
					.toArray());
			return list;
		} else
			return null;
	}

	public Long readCaddeSokakTurKayitSayisiByCriteria(
			CaddeSokakTur caddeSokakTur) {
		Criteria criteria = getSession().createCriteria(CaddeSokakTur.class,
				"m");
		if (caddeSokakTur != null && caddeSokakTur.getAciklama() != null
				&& !caddeSokakTur.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", caddeSokakTur
					.getAciklama().toUpperCase().trim()
					+ "%"));

		}
		return super.kayitSayisiOku(criteria);
	}

	public List<CaddeSokakTur> readAllCaddeSokakTurByCriteria(
			CaddeSokakTur caddeSokakTur, int baslangicKaydi,
			int maksimumKayitSayisi) {
		Criteria criteria = getSession().createCriteria(CaddeSokakTur.class,
				"caddeSokakTur");
		if (caddeSokakTur != null && caddeSokakTur.getAciklama() != null
				&& !caddeSokakTur.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", caddeSokakTur
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		criteria.setFirstResult(baslangicKaydi);
		criteria.setMaxResults(maksimumKayitSayisi);
		List<CaddeSokakTur> list = criteria.list();
		return list;
	}

	public List<GelismislikDurum> readAllGelismislikDurum() {
		return (List<GelismislikDurum>) super
				.readDBObject(GelismislikDurum.class);
	}

	public void deleteGelismislikDurum(GelismislikDurum gelismislikDurum) {
		super.deleteDBObject(gelismislikDurum);
	}

	public GelismislikDurum readGelismislikDurumByCriteria(Long id) {
		return (GelismislikDurum) super
				.readDBObject(GelismislikDurum.class, id);
	}

	public void saveOrUpdateGelismislikDurumList(
			List<GelismislikDurum> gelismislikDurumList) {
		super.saveDBObject(gelismislikDurumList);
	}

	public Long readGelismislikDurumKayitSayisiByCriteria(
			GelismislikDurum gelismislikDurum) {
		Criteria criteria = getSession().createCriteria(GelismislikDurum.class,
				"m");
		if (gelismislikDurum != null && gelismislikDurum.getAciklama() != null
				&& !gelismislikDurum.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", gelismislikDurum
					.getAciklama().toUpperCase().trim()
					+ "%"));

		}
		return super.kayitSayisiOku(criteria);
	}

	public List<GelismislikDurum> readAllGelismislikDurumByCriteria(
			GelismislikDurum gelismislikDurum, int baslangicKaydi,
			int maksimumKayitSayisi) {
		Criteria criteria = getSession().createCriteria(GelismislikDurum.class,
				"gelismislikDurum");
		if (gelismislikDurum != null && gelismislikDurum.getAciklama() != null
				&& !gelismislikDurum.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", gelismislikDurum
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		criteria.setFirstResult(baslangicKaydi);
		criteria.setMaxResults(maksimumKayitSayisi);
		List<GelismislikDurum> list = criteria.list();
		return list;
	}

	public List<KullanimSekli> readAllKullanimSekli() {
		if ((super.readDBObject(KullanimSekli.class)).size() != 0)
			return (ArrayList<KullanimSekli>) super
					.readDBObject(KullanimSekli.class);
		return null;
	}

	public KullanimSekli readKullanimSekliByCriteria(KullanimSekli kullanimSekli) {
		String queryString = "Select ks from KullanimSekli ks Where  ks.id= ? ";
		List<KullanimSekli> tempList = (List<KullanimSekli>) super
				.readDBObjectByHQL(queryString, new Object[] { kullanimSekli
						.getId() });
		if (tempList != null && tempList.size() != 0)
			return (KullanimSekli) tempList.get(0);
		return null;
	}

	public void saveOrUpdateKullanimSekliByCriteria(KullanimSekli kullanimSekli) {
		super.mergeDBObject(kullanimSekli);
	}

	public void deleteKullanimSekli(KullanimSekli kullanimSekli) {
		// TODO Auto-generated method stub
		super.deleteDBObject(kullanimSekli);
	}

	public void saveOrUpdateKullanimSekli(List<KullanimSekli> kullanimSekliList) {
		super.saveOrUpdate(kullanimSekliList);
	}

	public List<KullanimSekli> readAllKullanimSekliByCriteria(int startRow,
			int maxResults, KullanimSekli kullanimSekli) {
		Criteria criteria = getSession().createCriteria(KullanimSekli.class,
				"ks");
		if (kullanimSekli != null && kullanimSekli.getAciklama() != null
				&& !kullanimSekli.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", kullanimSekli
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		criteria.setFirstResult(startRow);
		criteria.setMaxResults(maxResults);
		List<KullanimSekli> list = criteria.list();
		return list;
	}

	public Long readTotalNumberKullanimSekliByCriteria(
			KullanimSekli kullanimSekli) {
		Criteria criteria = getSession().createCriteria(KullanimSekli.class,
				"ks");
		if (kullanimSekli != null && kullanimSekli.getAciklama() != null
				&& !kullanimSekli.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", kullanimSekli
					.getAciklama().toUpperCase().trim()
					+ "%"));

		}
		return super.kayitSayisiOku(criteria);
	}

	public KonutSahiplikDurum readKonutSahiplikDurumByCriteria(
			KonutSahiplikDurum konutSahiplikDurum) {
		String queryString = "Select ksd from KonutSahiplikDurum ksd Where  ksd.id= ? ";
		List<KonutSahiplikDurum> tempList = (List<KonutSahiplikDurum>) super
				.readDBObjectByHQL(queryString,
						new Object[] { konutSahiplikDurum.getId() });
		if (tempList != null && tempList.size() != 0)
			return (KonutSahiplikDurum) tempList.get(0);
		return null;
	}

	public List<KonutSahiplikDurum> readAllKonutSahiplikDurumByCriteria(
			int startRow, int maxResults, KonutSahiplikDurum konutSahiplikDurum) {
		Criteria criteria = getSession().createCriteria(
				KonutSahiplikDurum.class, "ksd");
		if (konutSahiplikDurum != null
				&& konutSahiplikDurum.getAciklama() != null
				&& !konutSahiplikDurum.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", konutSahiplikDurum
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		criteria.setFirstResult(startRow);
		criteria.setMaxResults(maxResults);
		List<KonutSahiplikDurum> list = criteria.list();
		return list;
	}

	public Long readTotalNumberKonutSahiplikDurumByCriteria(
			KonutSahiplikDurum konutSahiplikDurum) {
		Criteria criteria = getSession().createCriteria(
				KonutSahiplikDurum.class, "ksd");
		if (konutSahiplikDurum != null
				&& konutSahiplikDurum.getAciklama() != null
				&& !konutSahiplikDurum.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", konutSahiplikDurum
					.getAciklama().toUpperCase().trim()
					+ "%"));

		}
		return super.kayitSayisiOku(criteria);
	}

	public Long readInsaatTurKayitSayisiByCriteria(InsaatTur insaatTur) {
		Criteria criteria = getSession().createCriteria(InsaatTur.class, "m");
		if (insaatTur != null && insaatTur.getAciklama() != null
				&& !insaatTur.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", insaatTur.getAciklama()
					.toUpperCase().trim()
					+ "%"));

		}
		return super.kayitSayisiOku(criteria);
	}

	public List<InsaatTur> readAllInsaatTurByCriteria(InsaatTur insaatTur,
			int baslangicKaydi, int maksimumKayitSayisi) {
		Criteria criteria = getSession().createCriteria(InsaatTur.class,
				"insaatTur");
		if (insaatTur != null && insaatTur.getAciklama() != null
				&& !insaatTur.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", insaatTur.getAciklama()
					.toUpperCase().trim()
					+ "%"));
		}
		criteria.setFirstResult(baslangicKaydi);
		criteria.setMaxResults(maksimumKayitSayisi);
		List<InsaatTur> list = criteria.list();
		return list;
	}

	public BagimsizKullanimSinif readBagimsizKullanimSinifByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		if (bagimsizKullanimSinif != null
				&& bagimsizKullanimSinif.getId() != null) {
			Criteria criteria = getSession().createCriteria(
					BagimsizKullanimSinif.class, "bks");
			criteria.add(Restrictions.eq("id", bagimsizKullanimSinif.getId()));

			List<BagimsizKullanimSinif> tempList = criteria.list();
			if (tempList != null && tempList.size() != 0)
				return (BagimsizKullanimSinif) tempList.get(0);
		}
		return null;
	}

	public Long readBagimsizKullanimSinifKayitSayisiByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif) {
		Criteria criteria = getSession().createCriteria(
				BagimsizKullanimSinif.class, "bks");
		if (bagimsizKullanimSinif != null
				&& bagimsizKullanimSinif.getAciklama() != null
				&& !bagimsizKullanimSinif.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", bagimsizKullanimSinif
					.getAciklama().toUpperCase().trim()
					+ "%"));

		}
		return super.kayitSayisiOku(criteria);
	}

	public List<BagimsizKullanimSinif> readAllBagimsizKullanimSinifByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif, int baslangicKaydi,
			int maksimumKayitSayisi) {
		Criteria criteria = getSession().createCriteria(
				BagimsizKullanimSinif.class, "bks");
		if (bagimsizKullanimSinif != null
				&& bagimsizKullanimSinif.getAciklama() != null
				&& !bagimsizKullanimSinif.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("aciklama", bagimsizKullanimSinif
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		criteria.setFirstResult(baslangicKaydi);
		criteria.setMaxResults(maksimumKayitSayisi);
		List<BagimsizKullanimSinif> list = criteria.list();
		return list;
	}

	public Long readBagimsizKullanimGrupKayitSayisiByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif,
			BagimsizKullanimGrup bagimsizKullanimGrup) {

		Criteria criteria = getSession().createCriteria(
				BagimsizKullanimGrup.class, "bkg");

		if (bagimsizKullanimSinif != null
				&& bagimsizKullanimSinif.getId() != null) {
			criteria.createAlias("bkg.bagimsizKullanimSinif", "bks");
			criteria.add(Restrictions.eq("bks.id", bagimsizKullanimSinif
					.getId()));

		}
		if (bagimsizKullanimGrup != null
				&& bagimsizKullanimGrup.getAciklama() != null
				&& !bagimsizKullanimGrup.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("bkg.aciklama",
					bagimsizKullanimGrup.getAciklama().toUpperCase().trim()
							+ "%"));
		}
		return super.kayitSayisiOku(criteria);
	}

	public List<BagimsizKullanimGrup> readAllBagimsizKullanimGrupByCriteria(
			BagimsizKullanimSinif bagimsizKullanimSinif,
			BagimsizKullanimGrup bagimsizKullanimGrup, int baslangicKaydi,
			int maksimumKayitSayisi) {

		Criteria criteria = getSession().createCriteria(
				BagimsizKullanimGrup.class, "bkg");

		if (bagimsizKullanimSinif != null
				&& bagimsizKullanimSinif.getId() != null) {
			criteria.createAlias("bkg.bagimsizKullanimSinif", "bks");
			criteria.add(Restrictions.eq("bks.id", bagimsizKullanimSinif
					.getId()));

		}
		if (bagimsizKullanimGrup != null
				&& bagimsizKullanimGrup.getAciklama() != null
				&& !bagimsizKullanimGrup.getAciklama().equals("")) {
			criteria.add(Restrictions.ilike("bkg.aciklama",
					bagimsizKullanimGrup.getAciklama().toUpperCase().trim()
							+ "%"));
		}
		criteria.setFirstResult(baslangicKaydi);
		criteria.setMaxResults(maksimumKayitSayisi);
		List<BagimsizKullanimGrup> list = criteria.list();
		return list;
	}

	public Long readBagimsizKullanimDetayKayitSayisiByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup,
			BagimsizKullanimDetay bagimsizKullanimDetay) {

		Criteria criteria = getSession().createCriteria(
				BagimsizKullanimDetay.class, "bkd");

		if (bagimsizKullanimGrup != null
				&& bagimsizKullanimGrup.getId() != null) {
			criteria.createAlias("bkd.bagimsizKullanimGrup", "bkg");
			criteria.add(Restrictions
					.eq("bkg.id", bagimsizKullanimGrup.getId()));

		}
		if (bagimsizKullanimDetay != null) {
			if (bagimsizKullanimDetay.getAciklama() != null
					&& !bagimsizKullanimDetay.getAciklama().equals(""))
				criteria.add(Restrictions.ilike("bkd.aciklama",
						bagimsizKullanimDetay.getAciklama().toUpperCase()
								.trim()
								+ "%"));
			if (bagimsizKullanimDetay.getKullanimSekli() != null
					&& bagimsizKullanimDetay.getKullanimSekli().getId() != null) {
				criteria.createAlias("bkd.kullanimSekli", "ks");
				criteria.add(Restrictions.eq("ks.id", bagimsizKullanimDetay
						.getKullanimSekli().getId()));
			}

			if (bagimsizKullanimDetay.getKullanimTip() != null
					&& !bagimsizKullanimDetay.getKullanimTip().equals(""))
				criteria.add(Restrictions.ilike("bkd.kullanimTip",
						bagimsizKullanimDetay.getKullanimTip().toUpperCase()
								.trim()
								+ "%"));

			if (bagimsizKullanimDetay.getIsyeriEh() != null
					&& !bagimsizKullanimDetay.getIsyeriEh().equals(""))
				criteria.add(Restrictions.ilike("bkd.isyeriEh",
						bagimsizKullanimDetay.getIsyeriEh().toUpperCase()
								.trim()
								+ "%"));
		}

		return super.kayitSayisiOku(criteria);
	}

	public List<BagimsizKullanimDetay> readAllBagimsizKullanimDetayByCriteria(
			BagimsizKullanimGrup bagimsizKullanimGrup,
			BagimsizKullanimDetay bagimsizKullanimDetay, int baslangicKaydi,
			int maksimumKayitSayisi) {

		Criteria criteria = getSession().createCriteria(
				BagimsizKullanimDetay.class, "bkd");

		criteria.setFetchMode("kullanimSekli", FetchMode.JOIN);
		criteria.setFetchMode("bagimsizKullanimGrup", FetchMode.JOIN);

		if (bagimsizKullanimGrup != null
				&& bagimsizKullanimGrup.getId() != null) {
			criteria.createAlias("bkd.bagimsizKullanimGrup", "bkg");
			criteria.add(Restrictions
					.eq("bkg.id", bagimsizKullanimGrup.getId()));

		}
		if (bagimsizKullanimDetay != null) {
			if (bagimsizKullanimDetay.getAciklama() != null
					&& !bagimsizKullanimDetay.getAciklama().equals(""))
				criteria.add(Restrictions.ilike("bkd.aciklama",
						bagimsizKullanimDetay.getAciklama().toUpperCase()
								.trim()
								+ "%"));
			if (bagimsizKullanimDetay.getKullanimSekli() != null
					&& bagimsizKullanimDetay.getKullanimSekli().getId() != null) {
				criteria.createAlias("bkd.kullanimSekli", "ks");
				criteria.add(Restrictions.eq("ks.id", bagimsizKullanimDetay
						.getKullanimSekli().getId()));
			}

			if (bagimsizKullanimDetay.getKullanimTip() != null
					&& !bagimsizKullanimDetay.getKullanimTip().equals(""))
				criteria.add(Restrictions.ilike("bkd.kullanimTip",
						bagimsizKullanimDetay.getKullanimTip().toUpperCase()
								.trim()
								+ "%"));

			if (bagimsizKullanimDetay.getIsyeriEh() != null
					&& !bagimsizKullanimDetay.getIsyeriEh().equals(""))
				criteria.add(Restrictions.ilike("bkd.isyeriEh",
						bagimsizKullanimDetay.getIsyeriEh().toUpperCase()
								.trim()
								+ "%"));
		}

		criteria.setFirstResult(baslangicKaydi);
		criteria.setMaxResults(maksimumKayitSayisi);
		List<BagimsizKullanimDetay> list = criteria.list();
		return list;
	}

	public boolean readGelismislikDurumKaydiVarMi(
			GelismislikDurum gelismislikDurum) {
		if (gelismislikDurum == null || gelismislikDurum.getAciklama() == null) {
			return true;
		}
		Criteria criteria = getSession().createCriteria(GelismislikDurum.class,
				"gelismislikDurum");
		if (gelismislikDurum.getId() != null)
			criteria.add(Restrictions.ne("id", gelismislikDurum.getId()));
		criteria.add(Restrictions.ilike("aciklama", gelismislikDurum
				.getAciklama().toUpperCase().trim()));
		Long kayitSayisi = super.kayitSayisiOku(criteria);
		if (kayitSayisi > 0)
			return true;
		else
			return false;
	}

	public Long readSysAuditKayitSayisiByCriteria(SysAudit sysAudit) {
		Criteria criteria = getSession().createCriteria(SysAudit.class, "sa");
		if (sysAudit != null && sysAudit.getAciklama() != null
				&& !sysAudit.getAciklama().trim().equals("")) {
			criteria.add(Restrictions.ilike("sa.aciklama", sysAudit
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		return super.kayitSayisiOku(criteria);
	}

	public List<SysAudit> readAllSysAuditByCriteria(SysAudit sysAudit,
			int baslangicKaydi, int maksimumKayitSayisi) {
		Criteria criteria = getSession().createCriteria(SysAudit.class, "sa");
		if (sysAudit != null && sysAudit.getAciklama() != null
				&& !sysAudit.getAciklama().trim().equals("")) {
			criteria.add(Restrictions.ilike("sa.aciklama", sysAudit
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		criteria.add(Restrictions.eq("sa.kaydedilebilirEh", "E"));
		criteria.addOrder(Order.asc("sa.aciklama"));
		criteria.setFirstResult(baslangicKaydi);
		if (maksimumKayitSayisi > 0)
			criteria.setMaxResults(maksimumKayitSayisi);
		List<SysAudit> list = criteria.list();
		return list;
	}

	public void saveOrUpdateSysAuditList(List<SysAudit> sysAuditList) {
		super.saveOrUpdate(sysAuditList);
	}

	public List<IslemMenu> readAllIslemMenuByCriteria(IslemMenu islemMenu,
			int baslangicKaydi, int maksimumKayitSayisi) {
		Criteria criteria = getSession().createCriteria(IslemMenu.class, "im");
		if (islemMenu != null && islemMenu.getAciklama() != null
				&& !islemMenu.getAciklama().trim().equals("")) {
			criteria.add(Restrictions.ilike("im.aciklama", islemMenu
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		criteria.setFirstResult(baslangicKaydi);
		if (maksimumKayitSayisi > 0) {
			criteria.setMaxResults(maksimumKayitSayisi);
		}

		List<IslemMenu> list = criteria.list();

		return list;
	}

	public Long readIslemMenuKayitSayisiByCriteria(IslemMenu islemMenu) {
		Criteria criteria = getSession().createCriteria(IslemMenu.class, "im");
		if (islemMenu != null && islemMenu.getAciklama() != null
				&& !islemMenu.getAciklama().trim().equals("")) {
			criteria.add(Restrictions.ilike("im.aciklama", islemMenu
					.getAciklama().toUpperCase().trim()
					+ "%"));
		}
		return super.kayitSayisiOku(criteria);
	}

	public void saveOrUpdateIslemMenuList(List<IslemMenu> islemMenuList) {
		super.saveOrUpdate(islemMenuList);
	}

	public void deleteIslemMenu(IslemMenu islemMenu) {
		super.deleteDBObject(islemMenu);
	}

	public BagimsizKullanimSinif readBagimsizKullanimSinifByBagimsizKullanimSinif(
			BagimsizKullanimSinif bagimsizKullanimSinif) {

		return (BagimsizKullanimSinif) super.readDBObject(
				BagimsizKullanimSinif.class, bagimsizKullanimSinif.getId());
	}

	public List<Bolge> readAllBolgeByCriteria(Bolge bolge) {

		Criteria criteria = getSession().createCriteria(Bolge.class, "bolge");

		if (bolge != null) {
			if (bolge.getBolgeKodu() != null) {
				criteria.add(Restrictions.eq("bolge.bolgeKodu", bolge
						.getBolgeKodu()));
			}
			if (bolge.getAciklama() != null
					&& !bolge.getAciklama().trim().equals("")) {
				criteria.add(Restrictions.ilike("bolge.aciklama", bolge
						.getAciklama().toUpperCase().trim()
						+ "%"));
			}
			if (bolge.getKurumSabit() != null) {
				if (bolge.getKurumSabit().getId() != null) {
					criteria.add(Restrictions.eq("bolge.kurumSabit.id", bolge.getKurumSabit().getId()));
				}
			}
		}

		return criteria.list();
	}	
	public List<Object[]> readGenelSearch(KurumSabit kurumSabit,String keyword) {
	
		String sql="Select TIP,ID,ADI,ADRES from GENELSEARCH "+
					"where ADI LIKE '%"+keyword+"%' order by TIP,ADI";
		
		List<Object[]> temp= getSession().createSQLQuery(sql).list();		
		return temp;
		
	}
	public boolean saveAUAUrlLog(String appName,String urlTip,String clientIp)
	{
		try{
			String sql="INSERT INTO AUA_URL_LOG (APPNAME,URLTIP,IP) VALUES ('"+appName+"','"+urlTip+"','"+clientIp+"')";
			super.getSession().createSQLQuery(sql).executeUpdate();
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();

			return false;

		}

	}

	@Override
	public String ServerStatusCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long checkSuAboneTahsisByAboneNo(Long pKurumKodu, Long aboneNo) {
		// TODO Auto-generated method stub
		return null;
	}
}