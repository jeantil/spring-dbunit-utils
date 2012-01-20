/**
 * Copyright (c) 2012, Jean Helou
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * The name of the author may not be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE REGENTS AND CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.byjean.test.utils.parallel.jpa.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;

import javax.persistence.spi.PersistenceUnitInfo;
import java.util.Map;

/**
 * Parameter object for the Hibernate persistence cache.
 * Retains:
 * <ul>
 *  <li>A name</li>
 *  <li>The persistence information ( javax.persistence.spi.PersistenceUnitInfo )</li>
 *  <li>The Hibernate property map</li>
 *  <li>The Hibernate configuration ( org.hibernate.cfg.Configuration )</li>
 *  <li>The Hibernate settings (org.hibernate.cfg.Settings)</li>
 *  <li>The Hibernate sessionFactoty ( org.hibernate.SessionFactory )</li>
 * </ul>
 *
 * @author Jean Helou - <a href="http://twitter.com/jeanhelou">@jeanhelou</a>
 */
public class HibernatePersistenceCacheUnit {

    private final String name;

    private PersistenceUnitInfo persistenceUnitInfo;
    private Map<String, Object> propertyMap;

    private Configuration hibernateConfiguration;

    private Settings settings;

    private SessionFactory sessionFactory;

    public HibernatePersistenceCacheUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public PersistenceUnitInfo getPersistenceUnitInfo() {
        return persistenceUnitInfo;
    }

    public void setPersistenceUnitInfo(PersistenceUnitInfo persistenceUnitInfo) {
        this.persistenceUnitInfo = persistenceUnitInfo;
    }

    public Map<String, Object> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, Object> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Configuration getHibernateConfiguration() {
        return hibernateConfiguration;
    }

    public void setHibernateConfiguration(Configuration hibernateConfiguration) {
        this.hibernateConfiguration = hibernateConfiguration;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
