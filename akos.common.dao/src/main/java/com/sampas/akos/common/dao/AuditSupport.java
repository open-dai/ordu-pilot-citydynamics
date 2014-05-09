package com.sampas.akos.common.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AuditSupport {
	// private static Map<String, String> auditPropertiesMap = null;

	public static boolean isAuditEnabled(String className) {
		Map<String, String> map = getAuditPropertiesMap();
		if (map != null) {
			if (map.containsKey(className)) {
				if (map.get(className).equalsIgnoreCase("E")) {
					return true;
				}
			}
		}
		return false;
	}

	// TODO kurum bilgisine gore duzenlenecek
	public static boolean dataStoreOnInsert() {
		JdbcConnection jdbcConnection = JdbcConnection.getInstance();
		Connection connection = null;
		boolean sonuc = false;
		try {
			connection = jdbcConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select yeni_kayit_izle_eh from sys_sabitleri");
			while (resultSet.next()) {
				String str = resultSet.getString("yeni_kayit_izle_eh");
				if (str == null || str.equals("") || str.equals("H"))
					sonuc = false;
				else if (str != null && str.equals("E")) {
					sonuc = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sonuc;
	}

	// TODO 1. kurum bilgisine gore duzenlenecek
	// 2. kaydedilebilir_ehe alani sorguya eklenecek
	public static Map<String, String> getAuditPropertiesMap() {
		Map<String, String> auditPropertiesMap = null;
		// Basedao..
		auditPropertiesMap = new HashMap<String, String>();
		JdbcConnection jdbcConnection = JdbcConnection.getInstance();
		Connection connection = null;
		try {
			connection = jdbcConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select sinif_adi,izlenebilir_eh from sys_audit");
			while (resultSet.next()) {
				auditPropertiesMap.put(resultSet.getString("sinif_adi"),
						resultSet.getString("izlenebilir_eh"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return auditPropertiesMap;
	}
}
