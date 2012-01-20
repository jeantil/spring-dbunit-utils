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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic hashmap-based cache implementation for {@link HibernatePersistenceCacheUnit} indexed by name
 *
 * @author "Jean Helou <jean.helou@gmail.com>"
 */
public class HibernatePersistenceCache {

  private static final Map<String, HibernatePersistenceCacheUnit> persistenceInformationMap = new HashMap<String, HibernatePersistenceCacheUnit>();

  public static void addPersistenceInformation(HibernatePersistenceCacheUnit persistenceInformation) {
    assert(persistenceInformation != null);
    assert(persistenceInformation.getName() != null);
    persistenceInformationMap.put(persistenceInformation.getName(), persistenceInformation);
  }

  public static List<HibernatePersistenceCacheUnit> getPersistenceInformations() {
    return new ArrayList<HibernatePersistenceCacheUnit>(persistenceInformationMap.values());
  }

  public static void clearPersistenceInformations() {
    persistenceInformationMap.clear();
  }

  public static HibernatePersistenceCacheUnit getPersistenceInformation(String name) {
    return persistenceInformationMap.get(name);
  }

}
