package fi.arithmeticvisualizer.logic.visualization;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActivationPatternTest {
    
    static ActivationPattern matrixMultiplicationPattern;
    
    public ActivationPatternTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        matrixMultiplicationPattern = ActivationPattern.MATRIXMULTIPLICATION;
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

    @Test
    public void getLeftWorks() {
        assertEquals("row", matrixMultiplicationPattern.getLeftPattern());
    }
    
    @Test
    public void getRightWorks() {
        assertEquals("column", matrixMultiplicationPattern.getRightPattern());
    }
    
    @Test
    public void getResultWorks() {
        assertEquals("element", matrixMultiplicationPattern.getResultPattern());
    }
}
