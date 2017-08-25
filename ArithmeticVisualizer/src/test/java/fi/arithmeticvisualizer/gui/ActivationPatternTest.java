package fi.arithmeticvisualizer.gui;

import static fi.arithmeticvisualizer.gui.MaskState.Pattern.COLUMN;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ELEMENT;
import static fi.arithmeticvisualizer.gui.MaskState.Pattern.ROW;
import fi.arithmeticvisualizer.gui.OperationPattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActivationPatternTest {
    
    static OperationPattern matrixMultiplicationPattern;
    
    public ActivationPatternTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        matrixMultiplicationPattern = OperationPattern.MATRIXMULTIPLICATIONELEMENTWISE;
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
        assertEquals(ROW, matrixMultiplicationPattern.getLeftPattern());
    }
    
    @Test
    public void getRightWorks() {
        assertEquals(COLUMN, matrixMultiplicationPattern.getRightPattern());
    }
    
    @Test
    public void getResultWorks() {
        assertEquals(ELEMENT, matrixMultiplicationPattern.getResultPattern());
    }
}
