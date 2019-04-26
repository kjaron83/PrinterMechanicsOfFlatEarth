/*
 * PrintermechanicsofflatearthSuite.java
 * Create Date: Apr 26, 2019
 * Initial-Author: Janos Aron Kiss
 */
package printermechanicsofflatearth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Janos Aron Kiss
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({printermechanicsofflatearth.MechanicTest.class, printermechanicsofflatearth.CoordinateTest.class})
public class PrintermechanicsofflatearthSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
