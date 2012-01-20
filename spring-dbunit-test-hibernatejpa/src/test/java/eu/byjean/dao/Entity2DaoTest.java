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
package eu.byjean.dao;

import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.DataSetTestExecutionListener;
import eu.byjean.model.Entity2;
import eu.byjean.test.utils.parallel.jpa.hibernate.JpaHibernateDbSetupTestListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * Test of the DAO for entity1
 *
 * @author Jean Helou - <a href="http://twitter.com/jeanhelou">@jeanhelou</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "applicationContext-test.xml","applicationContext-test-hsqldb.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,JpaHibernateDbSetupTestListener.class, DataSetTestExecutionListener.class})
@DataSet(value = "Entity2DaoTest.xml")
public class Entity2DaoTest {

    @Autowired
    private IEntity2Dao IEntity2Dao;
    
    @Test
    public void testFindByName() {
        Entity2 user = IEntity2Dao.findByName("a");
        assertNotNull(user);
    }

    //@Test
    public void testFindByMissingName() {
        Entity2 user = IEntity2Dao.findByName("c");
        assertNull(user);

    }

}