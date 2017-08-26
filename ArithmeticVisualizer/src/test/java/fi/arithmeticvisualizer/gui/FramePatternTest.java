package fi.arithmeticvisualizer.gui;

import static fi.arithmeticvisualizer.gui.FramePattern.ArrayPattern.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FramePatternTest {
    
    static FramePattern matrixMultiplicationPattern;
    
    public FramePatternTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        matrixMultiplicationPattern = FramePattern.MATRIXMULTIPLICATIONELEMENTWISE;
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
