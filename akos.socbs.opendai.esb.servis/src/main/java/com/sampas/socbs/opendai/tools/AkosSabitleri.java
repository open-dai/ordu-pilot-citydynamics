package com.sampas.socbs.opendai.tools;


import com.sampas.akos.common.model.AkosReadEnv;

public class AkosSabitleri {
	private final static String	DEFAULT_AKOS_YOL				= "AKOS_YOL";
	private final static String	DEFAULT_PROPERTIES_PATH			= "/common/common.properties";
	private final static String	SECURITY_PROPERTIES_PATH		= "/security/akosSecurity.properties";
	private final static String	LDAP_PROPERTIES_PATH			= "/security/akosLdapRoot.properties";
	private final static String	DYS_PROPERTIES_PATH				= "/dys/akosSecurity.properties";
	private final static String	ISB_PROPERTIES_PATH				= "/isb/isb.properties";
	private final static String	WEB_HIBERNATE_PROPERTIES_PATH	= "/common/webHibernate.properties";

	private static String		cssYol;
	private static String		vedopYol;
	private static String		sicilEkleGercekURL;
	private static String		sicilEkleTuzelURL;
	private static String		mailTemplateYol;
	private static String		sicilGuncelleGercekURL;
	private static String		sicilGuncelleTuzelURL;
	private static String		cezaKontrolDuzenlemeAktifmi;
	private static String		sec_authTestUserName;
	private static String		sec_authTestPassword;
	private static String		sec_authTestKurumId;
	private static String		alfrescoRepoLocation;
	private static String		alfrescoRepoUsername;
	private static String		alfrescoRepoPassword;
	private static String		ucmRepoLocation;
	private static String		ucmRepoUsername;
	private static String		ucmRepoPassword;
	private static String		nviURL;
	private static String		nviUsername;
	private static String		nviPassword;
	private static String		ldapContextSourceBase;
	private static String		ldapProviderUrl;
	private static String		securityAuthChangePasswordUrlJdbc;
	private static String		securityAuthChangePasswordUrlLdap;
	private static String		securityAuthPasswordExpireDays;
	private static String		dysWebServiceSecurityUser;
	private static String		bilgiYonServicePath;
	private static String		smsCepNumarasi;
	private static String		isbUygulamaYol;
	private static String		ldapBelediyeContainer;
	private static String		securityAuthGuestPageUrl;
	private static String		hesapKartiRaporURL;

	private static String		useAlfresco;
	private static String		dysFileServerMappingDrive;
	private static String		mapPopupServerBaseUrl;
	private static String		vezneSicilKontrolSuresi;

	private static String		spacerResim, r1c1Resim, r1c2Resim, r1c5Resim,
			r2c2Resim, r3c1Resim, r4c1Resim, r5c1Resim, r5c3Resim, r5c4Resim;

	private static String		sifreEncoding;

	private static String		dbConnectionUrl, dbConnectionUsername,
			dbConnectionPassword;

	private static String		kurumId;

	public static String readMyProperty(String filePath, String propertyName) {
		return AkosReadEnv.getMyProperty(DEFAULT_AKOS_YOL, filePath,
				propertyName);
	}

	public static String readMyPropertyWithFilePath(String filePath,
			String propertyName) {
		return AkosReadEnv.getMyPropertyWithFilePath(DEFAULT_AKOS_YOL,
				filePath, propertyName);
	}

	public static String getCssYol() {
		if (cssYol == null) {
			cssYol = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"url.common.cssYol");
		}
		return cssYol;
	}

	public static String getVedopYol() {
		if (vedopYol == null) {
			vedopYol = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"url.ortaksabit.vedopYol");
		}
		return vedopYol;
	}

	public static String getSicilEkleGercekURL() {
		if (sicilEkleGercekURL == null) {
			sicilEkleGercekURL = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"url.ortaksabit.sicilEkleGercek");
		}
		return sicilEkleGercekURL;
	}

	public static String getSicilEkleTuzelURL() {
		if (sicilEkleTuzelURL == null) {
			sicilEkleTuzelURL = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"url.ortaksabit.sicilEkleTuzel");
		}
		return sicilEkleTuzelURL;
	}

	public static String getMailTemplateYol() {
		if (mailTemplateYol == null) {
			try {
				mailTemplateYol = AkosReadEnv.getEnvVars().getProperty(
						"AKOS_YOL")
						+ readMyProperty(DEFAULT_PROPERTIES_PATH,
								"url.messaging.mailTemplate");
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				System.out
						.println(DEFAULT_PROPERTIES_PATH
								+ " altinda \"url.messaging.mailTemplate\" propertisi bulunamadi");
			}
		}
		return mailTemplateYol;
	}

	public static String getSicilGuncelleGercekURL() {
		if (sicilGuncelleGercekURL == null) {
			sicilGuncelleGercekURL = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"url.ortaksabit.sicilGuncelleGercek");
		}
		return sicilGuncelleGercekURL;
	}

	public static void setSicilGuncelleGercekURL(String sicilGuncelleGercekURL) {
		AkosSabitleri.sicilGuncelleGercekURL = sicilGuncelleGercekURL;
	}

	public static String getSicilGuncelleTuzelURL() {
		if (sicilGuncelleTuzelURL == null) {
			sicilGuncelleTuzelURL = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"url.ortaksabit.sicilGuncelleTuzel");
		}
		return sicilGuncelleTuzelURL;
	}

	public static void setSicilGuncelleTuzelURL(String sicilGuncelleTuzelURL) {
		AkosSabitleri.sicilGuncelleTuzelURL = sicilGuncelleTuzelURL;
	}

	public static String getSec_authTestUserName() {
		if (sec_authTestUserName == null) {
			sec_authTestUserName = readMyProperty(SECURITY_PROPERTIES_PATH,
					"security.auth.test.user");
		}
		return sec_authTestUserName;
	}

	public static String getSec_authTestPassword() {
		if (sec_authTestPassword == null) {
			sec_authTestPassword = readMyProperty(SECURITY_PROPERTIES_PATH,
					"security.auth.test.password");
		}
		return sec_authTestPassword;
	}

	public static String getSec_authTestKurumId() {
		if (sec_authTestKurumId == null) {
			sec_authTestKurumId = readMyProperty(SECURITY_PROPERTIES_PATH,
					"security.auth.test.kurumId");
		}
		return sec_authTestKurumId;
	}

	public static String getAlfrescoRepoLocation() {
		if (alfrescoRepoLocation == null) {
			alfrescoRepoLocation = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"alfresco.repository.location");
		}
		return alfrescoRepoLocation;
	}

	public static String getAlfrescoRepoUsername() {
		if (alfrescoRepoUsername == null) {
			alfrescoRepoUsername = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"alfresco.repository.username");
		}
		return alfrescoRepoUsername;
	}

	public static String getAlfrescoRepoPassword() {
		if (alfrescoRepoPassword == null) {
			alfrescoRepoPassword = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"alfresco.repository.password");
		}
		return alfrescoRepoPassword;
	}

	public static String getUcmRepoLocation() {
		if (ucmRepoLocation == null) {
			ucmRepoLocation = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"ucm.repository.location");
		}
		return ucmRepoLocation;
	}

	public static String getUcmRepoUsername() {
		if (ucmRepoUsername == null) {
			ucmRepoUsername = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"ucm.repository.username");
		}
		return ucmRepoUsername;
	}

	public static String getUcmRepoPassword() {
		if (ucmRepoPassword == null) {
			ucmRepoPassword = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"ucm.repository.password");
		}
		return ucmRepoPassword;
	}

	public static String getNviURL() {
		if (nviURL == null) {
			nviURL = readMyProperty(DEFAULT_PROPERTIES_PATH, "nvi.url");
		}
		return nviURL;
	}

	public static String getNviUsername() {
		if (nviUsername == null) {
			nviUsername = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"nvi.username");
		}
		return nviUsername;
	}

	public static String getNviPassword() {
		if (nviPassword == null) {
			nviPassword = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"nvi.password");
		}
		return nviPassword;
	}

	public static String getCezaKontrolDuzenlemeAktifmi() {
		if (cezaKontrolDuzenlemeAktifmi == null) {
			cezaKontrolDuzenlemeAktifmi = readMyProperty(
					DEFAULT_PROPERTIES_PATH,
					"sabit.tahsilat.cezaKontrolDuzenlemeAktifmi");
		}
		return cezaKontrolDuzenlemeAktifmi;
	}

	public static String getLdapContextSourceBase(String kurumId) {
		if (ldapContextSourceBase == null) {
			ldapContextSourceBase = readMyPropertyWithFilePath(
					LDAP_PROPERTIES_PATH, "ldap.context.source.base." + kurumId);
		}
		return ldapContextSourceBase;
	}

	public static String getLdapProviderUrl(String kurumId) {
		if (ldapProviderUrl == null) {
			ldapProviderUrl = readMyPropertyWithFilePath(LDAP_PROPERTIES_PATH,
					"ldap.provider.url." + kurumId);
		}
		return ldapProviderUrl;
	}

	public static String getSecurityAuthChangePasswordUrlJdbc() {
		if (securityAuthChangePasswordUrlJdbc == null) {
			securityAuthChangePasswordUrlJdbc = readMyPropertyWithFilePath(
					SECURITY_PROPERTIES_PATH,
					"security.auth.change.password.url.jdbc");
		}
		return securityAuthChangePasswordUrlJdbc;
	}

	public static String getSecurityAuthChangePasswordUrlLdap() {
		if (securityAuthChangePasswordUrlLdap == null) {
			securityAuthChangePasswordUrlLdap = readMyPropertyWithFilePath(
					SECURITY_PROPERTIES_PATH,
					"security.auth.change.password.url.ldap");
		}
		return securityAuthChangePasswordUrlLdap;
	}

	public static String getSecurityAuthPasswordExpireDays() {
		if (securityAuthPasswordExpireDays == null) {
			securityAuthPasswordExpireDays = readMyPropertyWithFilePath(
					SECURITY_PROPERTIES_PATH,
					"security.auth.password.expire.days");
		}
		return securityAuthPasswordExpireDays;
	}

	public static String getDysWebServiceSecurityUser(String user) {
		if (dysWebServiceSecurityUser == null) {
			dysWebServiceSecurityUser = readMyPropertyWithFilePath(
					DYS_PROPERTIES_PATH, user);
		}
		return dysWebServiceSecurityUser;
	}

	public static String getBilgiYonServicePath() {
		if (bilgiYonServicePath == null) {
			bilgiYonServicePath = readMyPropertyWithFilePath(
					ISB_PROPERTIES_PATH, "bilgiYonService.path");
		}
		return bilgiYonServicePath;
	}

	public static String getSmsCepNumarasi(String actorId) {
		if (smsCepNumarasi == null) {
			smsCepNumarasi = readMyPropertyWithFilePath(ISB_PROPERTIES_PATH,
					"sms.cep.numarasi." + actorId);
		}
		return smsCepNumarasi;
	}

	public static String getIsbUygulamaYol() {
		if (isbUygulamaYol == null) {
			isbUygulamaYol = readMyPropertyWithFilePath(ISB_PROPERTIES_PATH,
					"uygulamaYol");
		}
		return isbUygulamaYol;
	}

	public static String getLdapBelediyeContainer() {
		if (ldapBelediyeContainer == null) {
			ldapBelediyeContainer = readMyPropertyWithFilePath(
					LDAP_PROPERTIES_PATH, "ldap.belediye.container");
		}
		return ldapBelediyeContainer;
	}

	public static String getSecurityAuthGuestPageUrl() {
		if (securityAuthGuestPageUrl == null) {
			securityAuthGuestPageUrl = readMyPropertyWithFilePath(
					SECURITY_PROPERTIES_PATH, "security.auth.guest.page.url");
		}
		return securityAuthGuestPageUrl;
	}

	public static String getHesapKartiRaporURL() {
		if (hesapKartiRaporURL == null)
			hesapKartiRaporURL = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"url.tahsilat.hesapKartiRapor");
		return hesapKartiRaporURL;
	}

	/* Dys File Server properties */
	public static String getUseAlfresco() {
		if (useAlfresco == null)
			useAlfresco = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"dysFileServer.useAlfresco");
		return useAlfresco;
	}

	public static String getDysFileServerMappingDrive() {
		if (dysFileServerMappingDrive == null)
			dysFileServerMappingDrive = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"dysFileServer.dysFileServerMappingDrive");
		return dysFileServerMappingDrive;
	}

	public static String getMapPopupServerBaseUrl() {
		if (mapPopupServerBaseUrl == null)
			mapPopupServerBaseUrl = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mappopupserver.baseUrl");
		return mapPopupServerBaseUrl;
	}

	public static String getVezneSicilKontrolSuresi() {
		if (vezneSicilKontrolSuresi == null)
			vezneSicilKontrolSuresi = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"vezneSicilKilitKaldirma.kontrolSuresi");
		return vezneSicilKontrolSuresi;
	}

	public static String getDEFAULT_PROPERTIES_PATH() {
		return DEFAULT_PROPERTIES_PATH;
	}

	public static String getSpacerResim() {
		if (spacerResim == null)
			spacerResim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.spacer");
		return spacerResim;
	}

	public static String getR1c1Resim() {
		if (r1c1Resim == null)
			r1c1Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r1_c1");
		return r1c1Resim;
	}

	public static String getR1c2Resim() {
		if (r1c2Resim == null)
			r1c2Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r1_c2");
		return r1c2Resim;
	}

	public static String getR1c5Resim() {
		if (r1c5Resim == null)
			r1c5Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r1_c5");
		return r1c5Resim;
	}

	public static String getR2c2Resim() {
		if (r2c2Resim == null)
			r2c2Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r2_c2");
		return r2c2Resim;
	}

	public static String getR3c1Resim() {
		if (r3c1Resim == null)
			r3c1Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r3_c1");
		return r3c1Resim;
	}

	public static String getR4c1Resim() {
		if (r4c1Resim == null)
			r4c1Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r4_c1");
		return r4c1Resim;
	}

	public static String getR5c1Resim() {
		if (r5c1Resim == null)
			r5c1Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r5_c1");
		return r5c1Resim;
	}

	public static String getR5c3Resim() {
		if (r5c3Resim == null)
			r5c3Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r5_c3");
		return r5c3Resim;
	}

	public static String getR5c4Resim() {
		if (r5c4Resim == null)
			r5c4Resim = readMyProperty(DEFAULT_PROPERTIES_PATH,
					"mailResim.index_r5_c4");
		return r5c4Resim;
	}

	public static String getSifreEncoding() {
		if (sifreEncoding == null)
			sifreEncoding = readMyProperty(SECURITY_PROPERTIES_PATH,
					"sifreleme.encoding");
		return sifreEncoding;
	}

	public static String getDbConnectionUrl() {
		if (dbConnectionUrl == null)
			dbConnectionUrl = readMyProperty(WEB_HIBERNATE_PROPERTIES_PATH,
					"hibernate.connection.url");
		return dbConnectionUrl;
	}

	public static String getDbConnectionUsername() {
		if (dbConnectionUsername == null)
			dbConnectionUsername = readMyProperty(
					WEB_HIBERNATE_PROPERTIES_PATH,
					"hibernate.connection.username");
		return dbConnectionUsername;
	}

	public static String getDbConnectionPassword() {
		if (dbConnectionPassword == null)
			dbConnectionPassword = readMyProperty(
					WEB_HIBERNATE_PROPERTIES_PATH,
					"hibernate.connection.password");
		return dbConnectionPassword;
	}

	public static String getKurumId() {
		if (kurumId == null)
			kurumId = readMyProperty(SECURITY_PROPERTIES_PATH,
					"security.auth.test.kurumId");
		return kurumId;
	}

}
