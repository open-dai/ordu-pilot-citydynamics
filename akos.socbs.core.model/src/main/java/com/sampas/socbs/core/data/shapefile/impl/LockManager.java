/*
 *    GeoTools - OpenSource mapping toolkit
 *    http://geotools.org
 *    (C) 2003-2006, GeoTools Project Managment Committee (PMC)
 *    (C) 2002, Centre for Computational Geography
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
 */
package com.sampas.socbs.core.data.shapefile.impl;

/**
 * DOCUMENT ME!
 *
 * @author Tommaso Nolli
 * @source $URL: http://svn.geotools.org/tags/2.4.4/modules/plugin/shapefile/src/main/java/org/geotools/index/LockManager.java $
 */
public class LockManager {
    private static final int EXCLUSIVE_LOCK_TIMEOUT = 20;
    private static final int SHARED_LOCK_TIMEOUT = 10;
    public static final short READ = 1;
    public static final short WRITE = 2;
    private LockIndex exclusiveLock;
    private int leases;

    public LockManager() {
    }

    public synchronized void release(LockIndex lock) {
        LockImpl li = (LockImpl) lock;

        if (li.getType() == LockIndex.EXCLUSIVE) {
            this.exclusiveLock = null;
        } else {
            this.leases--;
        }

        this.notify();
    }

    /**
     * DOCUMENT ME!
     *
     *
     * @throws LockTimeoutException DOCUMENT ME!
     */
    public synchronized LockIndex aquireExclusive() throws LockTimeoutException {
        int cnt = 0;

        while (((this.exclusiveLock != null) || (this.leases > 0))
                && (cnt < EXCLUSIVE_LOCK_TIMEOUT)) {
            cnt++;

            try {
                this.wait(500);
            } catch (InterruptedException e) {
                throw new LockTimeoutException(e);
            }
        }

        if ((this.exclusiveLock != null) || (this.leases > 0)) {
            throw new LockTimeoutException("Timeout aquiring exclusive lock");
        }

        this.exclusiveLock = new LockImpl(LockIndex.EXCLUSIVE);

        return this.exclusiveLock;
    }

    /**
     * DOCUMENT ME!
     *
     *
     * @throws LockTimeoutException DOCUMENT ME!
     */
    public synchronized LockIndex aquireShared() throws LockTimeoutException {
        int cnt = 0;

        while ((this.exclusiveLock != null) && (cnt < SHARED_LOCK_TIMEOUT)) {
            cnt++;

            try {
                this.wait(500);
            } catch (InterruptedException e) {
                throw new LockTimeoutException(e);
            }
        }

        if (this.exclusiveLock != null) {
            throw new LockTimeoutException("Timeout aquiring shared lock");
        }

        this.leases++;

        return new LockImpl(LockIndex.SHARED);
    }

    /**
     * DOCUMENT ME!
     *
     * @author Tommaso Nolli
     */
    private class LockImpl implements LockIndex {
        private short type;

        /**
         * DOCUMENT ME!
         *
         * @param type
         */
        public LockImpl(short type) {
            this.type = type;
        }

        /**
         * @see org.geotools.index.Lock#getType()
         */
        public short getType() {
            return this.type;
        }
    }
}
