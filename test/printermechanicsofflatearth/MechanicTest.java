/*
 * MechanicTest.java
 * Create Date: Apr 26, 2019
 * Initial-Author: Janos Aron Kiss
 */
package printermechanicsofflatearth;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
public class MechanicTest {
    
    private static final String TESTCASE_A_NAME = "John";
    private static final int TESTCASE_A_FIXED_FEE = 1000;
    private static final int TESTCASE_A_KILOMETER_FEE = 500;
    private static final Coordinate TESTCASE_A_OFFICE = new Coordinate(10, 10);
    private static final Mechanic TESTCASE_A_MECHANIC = new Mechanic(TESTCASE_A_NAME, TESTCASE_A_FIXED_FEE, TESTCASE_A_KILOMETER_FEE, TESTCASE_A_OFFICE);
    private static final Coordinate TESTCASE_A_JOB = new Coordinate(0, 0);
    
    private static final String TESTCASE_B_NAME = "Paul";
    private static final int TESTCASE_B_FIXED_FEE = 2000;
    private static final int TESTCASE_B_KILOMETER_FEE = 250;
    private static final Coordinate TESTCASE_B_OFFICE = new Coordinate(15, 5);
    private static final Mechanic TESTCASE_B_MECHANIC = new Mechanic(TESTCASE_B_NAME, TESTCASE_B_FIXED_FEE, TESTCASE_B_KILOMETER_FEE, TESTCASE_B_OFFICE);
    private static final Coordinate TESTCASE_B_JOB = new Coordinate(-1, -10);

    private static final String TESTCASE_C_NAME = "George";
    private static final int TESTCASE_C_FIXED_FEE = 3000;
    private static final int TESTCASE_C_KILOMETER_FEE = 100;
    private static final Coordinate TESTCASE_C_OFFICE = new Coordinate(-5, 10);
    private static final Mechanic TESTCASE_C_MECHANIC = new Mechanic(TESTCASE_C_NAME, TESTCASE_C_FIXED_FEE, TESTCASE_C_KILOMETER_FEE, TESTCASE_C_OFFICE);
    private static final Coordinate TESTCASE_C_JOB = new Coordinate(20, 5);

    private static final int TESTCASE_AA_CALCULATED_FEE = 11000;
    private static final int TESTCASE_AB_CALCULATED_FEE = 16500;
    private static final int TESTCASE_AC_CALCULATED_FEE = 8500;    
    
    public MechanicTest() {
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
     * Test of getName method, of class Mechanic.
     */
    @Test
    public void testGetName() {
        assertEquals(TESTCASE_A_MECHANIC.getName(), TESTCASE_A_NAME);
        assertEquals(TESTCASE_B_MECHANIC.getName(), TESTCASE_B_NAME);
        assertEquals(TESTCASE_C_MECHANIC.getName(), TESTCASE_C_NAME);
    }

    /**
     * Test of getFixedFee method, of class Mechanic.
     */
    @Test
    public void testGetFixedFee() {
        assertEquals(TESTCASE_A_MECHANIC.getFixedFee(), TESTCASE_A_FIXED_FEE);
        assertEquals(TESTCASE_B_MECHANIC.getFixedFee(), TESTCASE_B_FIXED_FEE);
        assertEquals(TESTCASE_C_MECHANIC.getFixedFee(), TESTCASE_C_FIXED_FEE);
    }

    /**
     * Test of getKilometerFee method, of class Mechanic.
     */
    @Test
    public void testGetKilometerFee() {
        assertEquals(TESTCASE_A_MECHANIC.getKilometerFee(), TESTCASE_A_KILOMETER_FEE);
        assertEquals(TESTCASE_B_MECHANIC.getKilometerFee(), TESTCASE_B_KILOMETER_FEE);
        assertEquals(TESTCASE_C_MECHANIC.getKilometerFee(), TESTCASE_C_KILOMETER_FEE);
    }

    /**
     * Test of getOfficeCoordinate method, of class Mechanic.
     */
    @Test
    public void testGetOfficeCoordinate() {
        assertEquals(TESTCASE_A_MECHANIC.getOfficeCoordinate(), TESTCASE_A_OFFICE);
        assertEquals(TESTCASE_B_MECHANIC.getOfficeCoordinate(), TESTCASE_B_OFFICE);
        assertEquals(TESTCASE_C_MECHANIC.getOfficeCoordinate(), TESTCASE_C_OFFICE);
    }

    /**
     * Test of calcFees method, of class Mechanic.
     */
    @Test
    public void testCalcFees() {
        assertEquals(TESTCASE_A_MECHANIC.calcFees(TESTCASE_A_JOB), TESTCASE_AA_CALCULATED_FEE);
        assertEquals(TESTCASE_A_MECHANIC.calcFees(TESTCASE_B_JOB), TESTCASE_AB_CALCULATED_FEE);
        assertEquals(TESTCASE_A_MECHANIC.calcFees(TESTCASE_C_JOB), TESTCASE_AC_CALCULATED_FEE);
    }

    /**
     * Test of create method, of class Mechanic.
     */
    @Test
    public void testCreate() {
        String createString = 
                TESTCASE_A_NAME + '\n'
                + TESTCASE_A_FIXED_FEE + '\n'
                + TESTCASE_A_KILOMETER_FEE + '\n'
                + TESTCASE_A_OFFICE.getX() + ' ' + TESTCASE_A_OFFICE.getY() + '\n';        
        InputStream inputStream = new ByteArrayInputStream(createString.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();
        
        Mechanic mechanic = Mechanic.create(inputStream, outputStream);
        assertEquals(TESTCASE_A_NAME, mechanic.getName());
        assertEquals(TESTCASE_A_FIXED_FEE, mechanic.getFixedFee());
        assertEquals(TESTCASE_A_KILOMETER_FEE, mechanic.getKilometerFee());
        assertEquals(TESTCASE_A_OFFICE.getX(), mechanic.getOfficeCoordinate().getX());
        assertEquals(TESTCASE_A_OFFICE.getY(), mechanic.getOfficeCoordinate().getY());
    }

    /**
     * Test of cheapest method, of class Mechanic.
     */
    @Test
    public void testCheapest() {
        assertEquals(Mechanic.cheapest(TESTCASE_A_JOB, TESTCASE_A_MECHANIC, TESTCASE_B_MECHANIC, TESTCASE_C_MECHANIC), TESTCASE_C_MECHANIC);
        assertEquals(Mechanic.cheapest(TESTCASE_B_JOB, TESTCASE_A_MECHANIC, TESTCASE_B_MECHANIC, TESTCASE_C_MECHANIC), TESTCASE_C_MECHANIC);
        assertEquals(Mechanic.cheapest(TESTCASE_C_JOB, TESTCASE_A_MECHANIC, TESTCASE_B_MECHANIC, TESTCASE_C_MECHANIC), TESTCASE_B_MECHANIC);
    }
    
}
