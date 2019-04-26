/*
 * CoordinateTest.java
 * Create Date: Apr 26, 2019
 * Initial-Author: Janos Aron Kiss
 */
package printermechanicsofflatearth;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Janos Aron Kiss
 */
public class CoordinateTest {
    
    private static final int TESTCASE_A_X = 0;
    private static final int TESTCASE_A_Y = 0;
    private static final Coordinate TESTCASE_A = new Coordinate(TESTCASE_A_X, TESTCASE_A_Y);
    
    private static final int TESTCASE_B_X = -10;
    private static final int TESTCASE_B_Y = 10;
    private static final Coordinate TESTCASE_B = new Coordinate(TESTCASE_B_X, TESTCASE_B_Y);
    
    private static final int TESTCASE_C_X = 15;
    private static final int TESTCASE_C_Y = 15;
    private static final Coordinate TESTCASE_C = new Coordinate(TESTCASE_C_X, TESTCASE_C_Y);
    
    private static final int TESTCASE_AB_DISTANCE = 20;
    private static final int TESTCASE_BC_DISTANCE = 30;    
    
    public CoordinateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class Coordinate.
     */
    @Test
    public void testGetX() {
        assertEquals(TESTCASE_A.getX(), TESTCASE_A_X);
        assertEquals(TESTCASE_B.getX(), TESTCASE_B_X);
        assertEquals(TESTCASE_C.getX(), TESTCASE_C_X);
    }

    /**
     * Test of getY method, of class Coordinate.
     */
    @Test
    public void testGetY() {
        assertEquals(TESTCASE_A.getY(), TESTCASE_A_Y);
        assertEquals(TESTCASE_B.getY(), TESTCASE_B_Y);
        assertEquals(TESTCASE_C.getY(), TESTCASE_C_Y);
    }

    /**
     * Test of distance method, of class Coordinate.
     */
    @Test
    public void testDistance() {
        assertEquals(TESTCASE_A.distance(TESTCASE_B), TESTCASE_AB_DISTANCE);
        assertEquals(TESTCASE_B.distance(TESTCASE_C), TESTCASE_BC_DISTANCE);
    }
    
}
