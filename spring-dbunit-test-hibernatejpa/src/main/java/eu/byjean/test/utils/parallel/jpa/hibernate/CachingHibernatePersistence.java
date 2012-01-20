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

import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import java.util.Map;

/**
 * Hibernate EJB3 persistence provider implementation capturing settings and
 * configuration in a cache unit.
 *
 * @author "Jean Helou <jean.helou@gmail.com>"
 */
public class CachingHibernatePersistence extends HibernatePersistence {

  @Override
  public EntityManagerFactory createEntityManagerFactory(String persistenceUnitName, Map overridenProperties) {
    Ejb3Configuration cfg = new Ejb3Configuration();
    Ejb3Configuration configured = cfg.configure(persistenceUnitName, overridenProperties);
    cacheConfig(persistenceUnitName, null, overridenProperties, configured);
    return configured != null ? configured.buildEntityManagerFactory() : null;
  }

  @Override
  public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map map) {
    Ejb3Configuration cfg = new Ejb3Configuration();
    Ejb3Configuration configured = cfg.configure(info, map);
    cacheConfig(info.getPersistenceUnitName(),info, map, configured);
    return configured != null ? configured.buildEntityManagerFactory() : null;
  }

  private void cacheConfig(String name, PersistenceUnitInfo info, Map map, Ejb3Configuration configured) {
    if (configured != null) {
      HibernatePersistenceCacheUnit cachedInfo = new HibernatePersistenceCacheUnit(name);
      cachedInfo.setPersistenceUnitInfo(info);
      cachedInfo.setPropertyMap(map);
      cachedInfo.setHibernateConfiguration(configured.getHibernateConfiguration());
      cachedInfo.setSettings(configured.buildSettings());
      HibernatePersistenceCache.addPersistenceInformation(cachedInfo);
    }
  }
}
