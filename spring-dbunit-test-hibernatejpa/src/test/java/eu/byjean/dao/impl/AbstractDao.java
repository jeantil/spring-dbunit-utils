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
package eu.byjean.dao.impl;

import eu.byjean.dao.Dao;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * A classic generic DAO implementation
 *
 * @param <T> an entity type
 * @param <ID> the ID type associated to T, must be serializable
 *
 * @author Jean Helou - <a href="http://twitter.com/jeanhelou">@jeanhelou</a>
 */
public abstract class AbstractDao<T, ID extends Serializable> implements Dao {

  private final Class<T> persistentClass;

  @PersistenceContext
  private EntityManager entityManager;

  @SuppressWarnings("unchecked")
  public AbstractDao() {
    ParameterizedType genericType = (ParameterizedType) getClass().getGenericSuperclass();
    this.persistentClass = (Class<T>) genericType.getActualTypeArguments()[0];
  }

  protected EntityManager getEntityManager() {
    return entityManager;
  }

  protected final Session getSession() {
    return (Session) entityManager.getDelegate();
  }

  protected final Criteria getCriteria() {
    return getSession().createCriteria(persistentClass);
  }

  protected Class<T> getPersistentClass() {
    return persistentClass;
  }

}