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


import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.util.Map;

/**
 * Spring test listener implementation which will attempt to reexport the
 * database schema configure for each EntityManagerFactory instance of the
 * current Spring context before the test class is executed.
 *
 * @author "Jean Helou <jean.helou@gmail.com>"
 */
public class JpaHibernateDbSetupTestListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        super.beforeTestMethod(testContext);
        Map<String, EntityManagerFactoryInfo> emfsMap = testContext.getApplicationContext().getBeansOfType(
                EntityManagerFactoryInfo.class);
        for (Map.Entry<String, EntityManagerFactoryInfo> emfByName : emfsMap.entrySet()) {
            EntityManagerFactoryInfo entityManagerFactoryInfo = emfByName.getValue();
            String puName = entityManagerFactoryInfo.getPersistenceUnitInfo().getPersistenceUnitName();
            HibernatePersistenceCacheUnit persistenceInformation = HibernatePersistenceCache.getPersistenceInformation(puName);
            SchemaExport schemaExport = new SchemaExport(persistenceInformation.getHibernateConfiguration(),
                    persistenceInformation.getSettings());
            schemaExport.execute(false, true, false, false);
        }
    }
}
