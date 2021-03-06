/*
 *    Geotools2 - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2002-2006, Geotools Project Managment Committee (PMC)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */
package com.sampas.socbs.core.data.arcsde.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Represents a set of ArcSDE database connection parameters. Instances of this
 * class are used to validate ArcSDE connection params as in
 * <code>DataSourceFactory.canProcess(java.util.Map)</code> and serves as keys
 * for maintaining single <code>SdeConnectionPool</code>'s by each set of
 * connection properties
 * 
 * @author Gabriel Roldan
 * @source $URL:
 *         http://svn.geotools.org/geotools/trunk/gt/modules/plugin/arcsde/datastore/src/main/java/org/geotools/arcsde/pool/ArcSDEConnectionConfig.java $
 * @version $Id: ArcSDEConnectionConfig.java 29625 2008-03-14 13:55:02Z groldan $
 */
@SuppressWarnings("unchecked")
public class ArcSDEConnectionConfig {
    /**
     * Shared package's logger
     */
    private static final Logger LOGGER = org.geotools.util.logging.Logging
            .getLogger("org.geotools.arcsde.pool");

    /**
     * message of the exception thrown if a mandatory parameter is not supplied
     */
    private static final String NULL_ARGUMENTS_MSG = "Illegal arguments. At least one of them was null. Check to pass "
            + "correct values to dbtype, server, port, database, user and password parameters";

    /** DOCUMENT ME! */
    private static final String ILLEGAL_ARGUMENT_MSG = " is not valid for parameter ";

    /** must equals to <code>"arcsde"</code> */
    public static final String DBTYPE_PARAM = "dbtype";

    /** constant to pass "arcsde" as DBTYPE_PARAM */
    public static final String DBTYPE_PARAM_VALUE = "arcsde";

    /** namespace URI assigned to datastore instance */
    public static final String NAMESPACE_PARAM = "namespace";

    /** ArcSDE server parameter name */
    public static final String SERVER_NAME_PARAM = "server";

    /** ArcSDE server port parameter name */
    public static final String PORT_NUMBER_PARAM = "port";

    /** ArcSDE databse name parameter name */
    public static final String INSTANCE_NAME_PARAM = "instance";

    /** ArcSDE database user name parameter name */
    public static final String USER_NAME_PARAM = "user";

    /** ArcSDE database user password parameter name */
    public static final String PASSWORD_PARAM = "password";

    /** DOCUMENT ME! */
    public static final String MIN_CONNECTIONS_PARAM = "pool.minConnections";

    /** DOCUMENT ME! */
    public static final String MAX_CONNECTIONS_PARAM = "pool.maxConnections";

    /** DOCUMENT ME! */
    public static final String CONNECTION_TIMEOUT_PARAM = "pool.timeOut";

    /**
     * parameter name who's value represents the feature class for wich an
     * <code>SdeDataSource</code> will be created
     * 
     * @task TODO: should this constant be moved to the SdeDataSource class?
     *       since SdeConnectionConfig thoes not validates the table param
     */
    protected static final String TABLE_NAME_PARAM = "table";

    /** namespace URI assigned to datastore */
    String namespaceUri;

    /** name or IP of the ArcSDE server to connect to */
    String serverName;

    /** port number where the ArcSDE instance listens for connections */
    Integer portNumber;

    /** name of the ArcSDE database to connect to */
    String databaseName;

    /** database user name to connect as */
    String userName;

    /** database user password */
    String userPassword;

    /** DOCUMENT ME! */
    Integer minConnections = null;

    /** DOCUMENT ME! */
    Integer maxConnections = null;

    /** DOCUMENT ME! */
    Integer connTimeOut = null;

    /**
     * DOCUMENT ME!
     * 
     * @param params
     * 
     * @throws NullPointerException
     *             if at least one mandatory parameter is
     * @throws IllegalArgumentException
     *             if at least one mandatory parameter is present but has no a
     *             "valid" value.
     */
    public ArcSDEConnectionConfig(Map params) throws NullPointerException, IllegalArgumentException {
        init(params);
    }

    /**
     * DOCUMENT ME!
     * 
     * @param dbType
     * @param serverName
     *            DOCUMENT ME!
     * @param portNumber
     *            DOCUMENT ME!
     * @param databaseName
     *            DOCUMENT ME!
     * @param userName
     *            DOCUMENT ME!
     * @param userPassword
     *            DOCUMENT ME!
     * 
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public ArcSDEConnectionConfig(String dbType, String serverName, String portNumber,
            String databaseName, String userName, String userPassword) throws NullPointerException,
            IllegalArgumentException {
        Map params = new HashMap();
        params.put(DBTYPE_PARAM, dbType);
        params.put(SERVER_NAME_PARAM, serverName);
        params.put(PORT_NUMBER_PARAM, portNumber);
        params.put(INSTANCE_NAME_PARAM, databaseName);
        params.put(USER_NAME_PARAM, userName);
        params.put(PASSWORD_PARAM, userPassword);
        init(params);
    }

    /**
     * DOCUMENT ME!
     * 
     * @param params
     *            DOCUMENT ME!
     * 
     * @throws NumberFormatException
     *             DOCUMENT ME!
     * @throws IllegalArgumentException
     *             DOCUMENT ME!
     */
    private void init(Map params) throws NumberFormatException, IllegalArgumentException {
        String dbtype = (String) params.get(DBTYPE_PARAM);
        String server = (String) params.get(SERVER_NAME_PARAM);
        String port = String.valueOf(params.get(PORT_NUMBER_PARAM));
        String instance = (String) params.get(INSTANCE_NAME_PARAM);
        String user = (String) params.get(USER_NAME_PARAM);
        String pwd = (String) params.get(PASSWORD_PARAM);
        Integer _port = checkParams(dbtype, server, port, instance, user, pwd);
        this.serverName = server;
        this.portNumber = _port;
        this.databaseName = instance;
        this.userName = user;
        this.userPassword = pwd;
        setUpOptionalParams(params);
    }

    /**
     * DOCUMENT ME!
     * 
     * @param params
     *            DOCUMENT ME!
     * 
     * @throws IllegalArgumentException
     *             DOCUMENT ME!
     */
    private void setUpOptionalParams(Map params) throws IllegalArgumentException {
        String exceptionMsg = null;
        Object ns = params.get(NAMESPACE_PARAM);

        this.namespaceUri = ns == null ? null : String.valueOf(ns);

        this.minConnections = getInt(params.get(MIN_CONNECTIONS_PARAM),
                ArcSDEConnectionPool.DEFAULT_CONNECTIONS);
        this.maxConnections = getInt(params.get(MAX_CONNECTIONS_PARAM),
                ArcSDEConnectionPool.DEFAULT_MAX_CONNECTIONS);
        this.connTimeOut = getInt(params.get(CONNECTION_TIMEOUT_PARAM),
                ArcSDEConnectionPool.DEFAULT_MAX_WAIT_TIME);

        if (this.minConnections.intValue() <= 0) {
            exceptionMsg = MIN_CONNECTIONS_PARAM + " must be a positive integer";
        }

        if (this.maxConnections.intValue() <= 0) {
            exceptionMsg = MAX_CONNECTIONS_PARAM + " must be a positive integer";
        }

        if (this.connTimeOut.intValue() <= 0) {
            exceptionMsg = CONNECTION_TIMEOUT_PARAM + " must be a positive integer";
        }

        if (this.minConnections.intValue() > this.maxConnections.intValue()) {
            exceptionMsg = MIN_CONNECTIONS_PARAM + " must be lower than " + MAX_CONNECTIONS_PARAM;
        }

        if (exceptionMsg != null) {
            throw new IllegalArgumentException(exceptionMsg);
        }
    }

    /**
     * DOCUMENT ME!
     * 
     * @param value
     *            DOCUMENT ME!
     * @param defaultValue
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    private static final Integer getInt(Object value, int defaultValue) {
        if (value == null) {
            return new Integer(defaultValue);
        }

        String sVal = String.valueOf(value);

        try {
            return new Integer(sVal);
        } catch (NumberFormatException ex) {
            return new Integer(defaultValue);
        }
    }

    /**
     * DOCUMENT ME!
     * 
     * @param dbType
     *            DOCUMENT ME!
     * @param serverName
     *            DOCUMENT ME!
     * @param portNumber
     *            DOCUMENT ME!
     * @param databaseName
     *            DOCUMENT ME!
     * @param userName
     *            DOCUMENT ME!
     * @param userPassword
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     * 
     * @throws IllegalArgumentException
     *             DOCUMENT ME!
     * @throws NullPointerException
     *             DOCUMENT ME!
     */
    private static Integer checkParams(String dbType, String serverName, String portNumber,
            String databaseName, String userName, String userPassword)
            throws IllegalArgumentException, NullPointerException {
        // check if dbtype is 'arcsde'
        if (!(DBTYPE_PARAM_VALUE.equals(dbType))) {
            throw new IllegalArgumentException("parameter dbtype must be " + DBTYPE_PARAM_VALUE);
        }

        // check for nullity
        if ((serverName == null) || (portNumber == null) || (userName == null)
                || (userPassword == null)) {
            throw new NullPointerException(NULL_ARGUMENTS_MSG);
        }

        if (serverName.length() == 0) {
            throwIllegal(SERVER_NAME_PARAM, serverName);
        }

        if (databaseName == null || databaseName.length() == 0) {
            LOGGER.fine("No database name specified");
        }

        if (userName.length() == 0) {
            throwIllegal(USER_NAME_PARAM, userName);
        }

        if (userPassword.length() == 0) {
            throwIllegal(PASSWORD_PARAM, userPassword);
        }

        Integer port = null;

        try {
            port = new Integer(portNumber);
        } catch (NumberFormatException ex) {
            throwIllegal(PORT_NUMBER_PARAM, portNumber);
        }

        return port;
    }

    /**
     * DOCUMENT ME!
     * 
     * @param paramName
     *            DOCUMENT ME!
     * @param paramValue
     *            DOCUMENT ME!
     * 
     * @throws IllegalArgumentException
     *             DOCUMENT ME!
     */
    private static void throwIllegal(String paramName, String paramValue) throws IllegalArgumentException {
        throw new IllegalArgumentException("'" + paramValue + "'" + ILLEGAL_ARGUMENT_MSG
                + paramName);
    }

    public String getNamespaceUri() {
        return namespaceUri;
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public String getUserName() {
        return userName;
    }

    /**
     * accessor method for retrieving the user password of the ArcSDE connection
     * properties holded here
     * 
     * @return the ArcSDE user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    //@Override
    public int hashCode() {
        int hash = 37;
        hash *= getServerName().hashCode();
        hash *= getPortNumber().hashCode();
        hash *= getUserName().hashCode();
        return hash;
    }

    /**
     * checks for equality over another <code>ArcSDEConnectionConfig</code>,
     * taking in count the values of database name, user name, and port number.
     * 
     * @param o
     *            DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    //@Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof ArcSDEConnectionConfig)) {
            return false;
        }

        ArcSDEConnectionConfig config = (ArcSDEConnectionConfig) o;

        return config.getServerName().equals(getServerName())
                && config.getPortNumber().equals(getPortNumber())
                && config.getUserName().equals(getUserName());
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public Integer getConnTimeOut() {
        return connTimeOut;
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public Integer getMaxConnections() {
        return maxConnections;
    }

    /**
     * DOCUMENT ME!
     * 
     * @return DOCUMENT ME!
     */
    public Integer getMinConnections() {
        return minConnections;
    }

    /**
     * @return a human friendly description of this parameter holder contents
     *         (password is masked), mostly usefull for stack traces
     */
    //@Override
    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getName() + "[");
        sb.append("dbtype=");
        sb.append(ArcSDEConnectionConfig.DBTYPE_PARAM_VALUE);
        sb.append(", server=");
        sb.append(this.serverName);
        sb.append(", port=");
        sb.append(this.portNumber);
        sb.append(", instance=");
        sb.append(this.databaseName);
        sb.append(", user=");
        sb.append(this.userName);
        // hidding password as the result of this method
        // is probably going to end up in a stack trace
        sb.append(", password=*****");
        sb.append(", minConnections=");
        sb.append(this.minConnections);
        sb.append(", maxConnections=");
        sb.append(this.maxConnections);
        sb.append(", connTimeOut=");
        sb.append(this.connTimeOut);
        sb.append("]");

        return sb.toString();
    }
}
